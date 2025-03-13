package jishe.steelthicknesspredict.service;

import com.fasterxml.jackson.databind.JsonNode;
import jishe.steelthicknesspredict.pojo.PredictionRequest;
import jishe.steelthicknesspredict.pojo.VoltageRangeRequest;
import jishe.steelthicknesspredict.repository.PredictionRequestRepository;
import jishe.steelthicknesspredict.repository.VoltageRangeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class VoltagePredictionService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PredictionRequestRepository predictionRequestRepository;

    @Autowired
    private VoltageRangeRequestRepository voltageRangeRequestRepository;

    @Value("${flask.api.url2}")
    private String flaskApiUrl;

    public JsonNode getPrediction(PredictionRequest request) {
        // 保存请求数据到数据库
        predictionRequestRepository.save(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PredictionRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(
                flaskApiUrl + "/predict",
                HttpMethod.POST,
                entity,
                JsonNode.class
        );

        return responseEntity.getBody();
    }

    /**
     * 根据电压区间（步长 0.01）内所有电压对应的厚度，并保存电压区间请求数据
     */
    public Map<Double, JsonNode> getBatchPredictions(PredictionRequest request, double startVoltage, double endVoltage) {
        // 构造 VoltageRangeRequest 对象
        VoltageRangeRequest vrRequest = new VoltageRangeRequest();
        vrRequest.setRequest(request);
        vrRequest.setStartVoltage(startVoltage);
        vrRequest.setEndVoltage(endVoltage);
        // 保存电压区间请求到数据库
        voltageRangeRequestRepository.save(vrRequest);

        Map<Double, JsonNode> predictions = new LinkedHashMap<>();

        double originalVoltage = request.getFmThkMeas();

        if (startVoltage > endVoltage) {
            double temp = startVoltage;
            startVoltage = endVoltage;
            endVoltage = temp;
        }

        for (double voltage = startVoltage; voltage <= endVoltage + 1e-8;
             voltage = Math.round((voltage + 0.01) * 100.0) / 100.0) {
            request.setFmThkMeas(voltage);
            JsonNode predictionResult = getPrediction(request);
            predictions.put(voltage, predictionResult);
        }

        request.setFmThkMeas(originalVoltage);
        return predictions;
    }
}
