package jishe.steelthicknesspredict.pojo;

import lombok.Data;

@Data
public class TemperatureRangeRequest {
    private PredictionInput input;        // 原有的输入参数
    private double startTemperature;     // 新增：起始温度
    private double endTemperature;       // 新增：结束温度
}