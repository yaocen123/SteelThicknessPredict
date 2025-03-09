package jishe.steelthicknesspredict.controller;

import com.fasterxml.jackson.databind.JsonNode;
import jishe.steelthicknesspredict.pojo.PredictionInput;
import jishe.steelthicknesspredict.pojo.PredictionRequest;
import jishe.steelthicknesspredict.service.PredictionService;
import jishe.steelthicknesspredict.service.VoltagePredictionService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
