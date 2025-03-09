package jishe.steelthicknesspredict.service;

import com.fasterxml.jackson.databind.JsonNode;
import jishe.steelthicknesspredict.pojo.PredictionInput;
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

    // Flask API URL
    private static final String FLASK_API_URL = "http://127.0.0.1:5000/predict";

    public double predictColdThickness(PredictionInput input) {
        // 将输入数据转换为 JSON 格式
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 创建请求体
        HttpEntity<PredictionInput> requestEntity = new HttpEntity<>(input, headers);

        // 发送 POST 请求到 Flask API
        ResponseEntity<JsonNode> response = restTemplate.exchange(
                FLASK_API_URL,
                HttpMethod.POST,
                requestEntity,
                JsonNode.class
        );

        // 获取返回的预测冷态厚度
        JsonNode responseBody = response.getBody();
        if (responseBody != null) {
            return responseBody.get("predicted_cold_thickness").asDouble();
        }
        return -1;  // 返回-1表示出错
    }

    /**
     * 在指定的温度区间内（步长 0.01）预测冷态厚度。
     * 这里假设 PredictionInput 中的 endStartTemp 字段用于表示温度。
     *
     * @param input          原始的预测请求参数对象
     * @param startTemperature 温度区间起始值
     * @param endTemperature   温度区间结束值
     * @return 返回一个 Map，key 为温度值，value 为该温度下预测的冷态厚度
     */
    public Map<Double, Double> predictColdThicknessInTemperatureRange(PredictionInput input, double startTemperature, double endTemperature) {
        Map<Double, Double> predictions = new LinkedHashMap<>();

        // 保存原始温度，便于后续恢复
        double originalTemperature = input.getEndStartTemp();

        // 如果起始温度大于结束温度，交换两者
        if (startTemperature > endTemperature) {
            double temp = startTemperature;
            startTemperature = endTemperature;
            endTemperature = temp;
        }

        // 以 0.01 的步长遍历温度区间，注意防止浮点误差
        for (double temp = startTemperature; temp <= endTemperature + 1e-8; temp = Math.round((temp + 0.01) * 100.0) / 100.0) {
            // 设置当前温度值
            input.setEndStartTemp(temp);
            // 调用预测方法获取预测结果
            double predictedThickness = predictColdThickness(input);
            // 保存结果到 Map 中
            predictions.put(temp, predictedThickness);
        }

        // 恢复原始温度值
        input.setEndStartTemp(originalTemperature);

        return predictions;
    }
}
