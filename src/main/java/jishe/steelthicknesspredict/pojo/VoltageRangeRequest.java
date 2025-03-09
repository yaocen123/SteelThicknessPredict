package jishe.steelthicknesspredict.pojo;

import lombok.Data;

@Data
public class VoltageRangeRequest {
    private PredictionRequest request;    // 原有的输入参数
    private double startVoltage;          // 新增：起始电压
    private double endVoltage;            // 新增：结束电压
}