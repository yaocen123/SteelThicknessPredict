package jishe.steelthicknesspredict.controller;

import com.fasterxml.jackson.databind.JsonNode;
import jishe.steelthicknesspredict.pojo.PredictionInput;
import jishe.steelthicknesspredict.pojo.PredictionRequest;
import jishe.steelthicknesspredict.pojo.TemperatureRangeRequest;
import jishe.steelthicknesspredict.pojo.VoltageRangeRequest;
import jishe.steelthicknesspredict.service.PredictionService;
import jishe.steelthicknesspredict.service.VoltagePredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/prediction")
@CrossOrigin(origins = "*")
public class PredictionController {

    @Autowired
    private PredictionService predictionService;
    @Autowired
    private VoltagePredictionService voltagePredictionService;

    @CrossOrigin(origins = "*")  // 允许特定源
    @PostMapping("/predictColdThickness")
    public Map<String, Double> predictColdThickness(@RequestBody PredictionInput input) {
        double predictedThickness = predictionService.predictColdThickness(input);

        // 将预测结果包装成带有 "prediction" 键的 Map
        Map<String, Double> response = new LinkedHashMap<>();
        response.put("prediction", predictedThickness);

        return response;
    }


    @PostMapping("/voltagepredict")
    public JsonNode predictVoltage(@RequestBody PredictionRequest request) {
        return voltagePredictionService.getPrediction(request);  // 直接返回 JsonNode
    }

    @PostMapping("/temppredictRange")
    public ResponseEntity<Map<Double, Map<String, Double>>> predictTemperatureRange(
            @RequestBody TemperatureRangeRequest rangeRequest) { // 使用新的请求体类
        // 获取预测结果
        Map<Double, Map<String, Double>> predictions = predictionService.predictColdThicknessInTemperatureRange(
                rangeRequest.getInput(),
                rangeRequest.getStartTemperature(),
                rangeRequest.getEndTemperature()
        );

        // 返回预测结果
        return ResponseEntity.ok(predictions);
    }


    @PostMapping("/volpredictRange")
    public ResponseEntity<Map<Double, JsonNode>> predictVoltageRange(
            @RequestBody VoltageRangeRequest rangeRequest) { // 使用新的请求体类
        Map<Double, JsonNode> predictions = voltagePredictionService.getBatchPredictions(
                rangeRequest.getRequest(),
                rangeRequest.getStartVoltage(),
                rangeRequest.getEndVoltage()
        );
        return ResponseEntity.ok(predictions);
    }

}
