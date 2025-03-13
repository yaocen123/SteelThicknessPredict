package jishe.steelthicknesspredict.controller;

import jishe.steelthicknesspredict.pojo.PredictionInput;
import jishe.steelthicknesspredict.pojo.PredictionRequest;
import jishe.steelthicknesspredict.pojo.TemperatureRangeRequest;
import jishe.steelthicknesspredict.pojo.VoltageRangeRequest;
import jishe.steelthicknesspredict.repository.PredictionInputRepository;
import jishe.steelthicknesspredict.repository.PredictionRequestRepository;
import jishe.steelthicknesspredict.repository.TemperatureRangeRequestRepository;
import jishe.steelthicknesspredict.repository.VoltageRangeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class PredictionDataController {

    @Autowired
    private PredictionInputRepository predictionInputRepository;

    @Autowired
    private PredictionRequestRepository predictionRequestRepository;

    @Autowired
    private TemperatureRangeRequestRepository temperatureRangeRequestRepository;

    @Autowired
    private VoltageRangeRequestRepository voltageRangeRequestRepository;

    // 获取所有冷态厚度预测的输入数据
    @GetMapping("/prediction-inputs")
    public List<PredictionInput> getAllPredictionInputs() {
        return predictionInputRepository.findAll();
    }

    // 获取所有电压预测请求数据
    @GetMapping("/prediction-requests")
    public List<PredictionRequest> getAllPredictionRequests() {
        return predictionRequestRepository.findAll();
    }

    // 获取所有温度区间请求数据
    @GetMapping("/temperature-range-requests")
    public List<TemperatureRangeRequest> getAllTemperatureRangeRequests() {
        return temperatureRangeRequestRepository.findAll();
    }

    // 获取所有电压区间请求数据
    @GetMapping("/voltage-range-requests")
    public List<VoltageRangeRequest> getAllVoltageRangeRequests() {
        return voltageRangeRequestRepository.findAll();
    }
}
