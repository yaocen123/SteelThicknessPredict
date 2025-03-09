package jishe.steelthicknesspredict.service;

import com.fasterxml.jackson.databind.JsonNode;
import jishe.steelthicknesspredict.pojo.ImgPredictionInput;
import jishe.steelthicknesspredict.pojo.PredictionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    // 预测某个温度下的冷态厚度 (为 ImgPredictionInput 添加温度)
    public double predictColdThicknessForImg(PredictionInput input, double temperature) {
        // 将输入数据转换为 JSON 格式
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 为每个预测传递具体的温度值
        input.setEndStartTemp(temperature);  // 设置 endStartTemp 为传入的 temperature

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
            return responseBody.get("predicted_cold_thickness").asDouble();  // 从响应中提取预测值
        }
        return -1;  // 如果出错，返回-1
    }

}
