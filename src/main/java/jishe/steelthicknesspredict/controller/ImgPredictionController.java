package jishe.steelthicknesspredict.controller;

import jishe.steelthicknesspredict.pojo.ImgPredictionInput;
import jishe.steelthicknesspredict.service.ImgPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/predict")
@CrossOrigin(origins = "*")
public class ImgPredictionController {

    @Autowired
    private ImgPredictionService imgPredictionService;

    @CrossOrigin(origins = "*")  // 允许跨域访问
    @PostMapping("/predictTemperatureRangeForImg")
    public Map<String, Object> predictTemperatureRangeForImg(@RequestBody ImgPredictionInput input) {

        // 获取输入的起始温度和终止温度
        double startTemp = input.getStartTemp();  // 从输入中获取 startTemp 和 endTemp
        double endTemp = input.getEndTemp();

        // 调用 ImgPredictionService 进行温度区间的预测
        double[] predictedValues = imgPredictionService.predictTemperatureRangeForImg(input, startTemp, endTemp);

        // 创建温度区间数组
        int rangeSize = (int) (endTemp - startTemp) + 1;
        double[] temperatures = new double[rangeSize];
        for (int i = 0; i < rangeSize; i++) {
            temperatures[i] = startTemp + i;
        }

        // 将预测结果和温度区间数据打包在一起
        Map<String, Object> response = new HashMap<>();
        response.put("temperatures", temperatures);
        response.put("predictedValues", predictedValues);

        return response;
    }
}
