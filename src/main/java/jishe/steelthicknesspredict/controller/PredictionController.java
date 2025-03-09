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
    public double predictColdThickness(@RequestBody PredictionInput input) {
        return predictionService.predictColdThickness(input);
    }

    @PostMapping("/voltagepredict")
    public JsonNode predictVoltage(@RequestBody PredictionRequest request) {
        return voltagePredictionService.getPrediction(request);  // 直接返回 JsonNode
    }

    @PostMapping("/temppredictRange")
    public ResponseEntity<Map<Double, Double>> predictTemperatureRange(
            @RequestBody TemperatureRangeRequest rangeRequest) { // 使用新的请求体类
        Map<Double, Double> predictions = predictionService.predictColdThicknessInTemperatureRange(
                rangeRequest.getInput(),
                rangeRequest.getStartTemperature(),
                rangeRequest.getEndTemperature()
        );
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
