package jishe.steelthicknesspredict.service;

import com.fasterxml.jackson.databind.JsonNode;
import jishe.steelthicknesspredict.pojo.PredictionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
