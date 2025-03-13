package jishe.steelthicknesspredict.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "temperature_range_request")
public class TemperatureRangeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 保存原始的预测输入数据
    @OneToOne(cascade = CascadeType.ALL)
    private PredictionInput input;

    private double startTemperature;
    private double endTemperature;
}
