package jishe.steelthicknesspredict.service;

import com.fasterxml.jackson.databind.JsonNode;
import jishe.steelthicknesspredict.pojo.PredictionInput;
import jishe.steelthicknesspredict.pojo.TemperatureRangeRequest;
import jishe.steelthicknesspredict.repository.PredictionInputRepository;
import jishe.steelthicknesspredict.repository.TemperatureRangeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class PredictionService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PredictionInputRepository predictionInputRepository;

    @Autowired
    private TemperatureRangeRequestRepository temperatureRangeRequestRepository;

    // Flask API URL
    private static final String FLASK_API_URL = "http://127.0.0.1:5000/predict";

    public double predictColdThickness(PredictionInput input) {
        // 保存输入数据到数据库（只保存输入数据）
        predictionInputRepository.save(input);

        // 将输入数据转换为 JSON 格式并调用 Flask API
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PredictionInput> requestEntity = new HttpEntity<>(input, headers);

        ResponseEntity<JsonNode> response = restTemplate.exchange(
                FLASK_API_URL,
                HttpMethod.POST,
                requestEntity,
                JsonNode.class
        );

        JsonNode responseBody = response.getBody();
        if (responseBody != null) {
            return responseBody.get("predicted_cold_thickness").asDouble();
        }
        return -1;  // 返回-1表示出错
    }

    /**
     * 在指定温度区间（步长 0.01）内预测，并保存温度区间请求数据
     */
    public Map<Double, Map<String, Double>> predictColdThicknessInTemperatureRange(PredictionInput input, double startTemperature, double endTemperature) {
        // 构造 TemperatureRangeRequest 对象
        TemperatureRangeRequest trRequest = new TemperatureRangeRequest();
        trRequest.setInput(input);
        trRequest.setStartTemperature(startTemperature);
        trRequest.setEndTemperature(endTemperature);
        // 保存温度区间请求到数据库
        temperatureRangeRequestRepository.save(trRequest);

        Map<Double, Map<String, Double>> predictions = new LinkedHashMap<>();

        // 保存原始温度值，便于恢复
        double originalTemperature = input.getEndStartTemp();

        if (startTemperature > endTemperature) {
            double temp = startTemperature;
            startTemperature = endTemperature;
            endTemperature = temp;
        }

        for (double temp = startTemperature; temp <= endTemperature + 1e-8;
             temp = Math.round((temp + 0.01) * 100.0) / 100.0) {
            input.setEndStartTemp(temp);
            double predictedThickness = predictColdThickness(input);

            // 创建一个新的 Map 存储 prediction
            Map<String, Double> prediction = new LinkedHashMap<>();
            prediction.put("prediction", predictedThickness);

            // 将当前温度和对应的 prediction 存入 predictions Map
            predictions.put(temp, prediction);
        }

        input.setEndStartTemp(originalTemperature);

        return predictions;
    }

}
