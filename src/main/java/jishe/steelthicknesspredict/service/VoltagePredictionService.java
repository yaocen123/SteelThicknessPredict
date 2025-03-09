package jishe.steelthicknesspredict.service;

import com.fasterxml.jackson.databind.JsonNode;
import jishe.steelthicknesspredict.pojo.PredictionRequest;
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

    @Value("${flask.api.url2}")
    private String flaskApiUrl;

    public JsonNode getPrediction(PredictionRequest request) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 创建请求体
        HttpEntity<PredictionRequest> entity = new HttpEntity<>(request, headers);

        // 发送 POST 请求
        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(
                flaskApiUrl + "/predict",
                HttpMethod.POST,
                entity,
                JsonNode.class
        );

        // 获取返回的响应数据
        JsonNode responseBody = responseEntity.getBody();
        return responseBody;  // 直接返回 JsonNode
    }


    /**
     * 根据两个电压值预测区间内所有电压对应的厚度（步长 0.01）
     * @param request 原有的预测请求参数，其中电压字段将会被动态修改
     * @param startVoltage 起始电压
     * @param endVoltage 结束电压
     * @return 一个 Map，key 为电压值，value 为预测结果（JsonNode）
     */
    public Map<Double, JsonNode> getBatchPredictions(PredictionRequest request, double startVoltage, double endVoltage) {
        // 用于存放所有电压值对应的预测结果，保持插入顺序
        Map<Double, JsonNode> predictions = new LinkedHashMap<>();

        // 保存原始的电压值，便于后续恢复
        double originalVoltage = request.getFmThkMeas();

        // 如果输入的起始电压大于结束电压，则交换
        if (startVoltage > endVoltage) {
            double temp = startVoltage;
            startVoltage = endVoltage;
            endVoltage = temp;
        }

        // 遍历电压区间，步长为 0.01（注意防止浮点数精度问题）
        for (double voltage = startVoltage; voltage <= endVoltage + 1e-8; voltage = Math.round((voltage + 0.01) * 100.0) / 100.0) {
            // 设置当前电压
            request.setFmThkMeas(voltage);
            // 调用预测方法，获取预测结果
            JsonNode predictionResult = getPrediction(request);
            // 将结果保存到 Map 中，key 为当前电压
            predictions.put(voltage, predictionResult);
        }

        // 恢复原始电压值
        request.setFmThkMeas(originalVoltage);

        return predictions;
    }
}
