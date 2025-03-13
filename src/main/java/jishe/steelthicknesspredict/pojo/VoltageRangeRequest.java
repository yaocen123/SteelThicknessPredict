package jishe.steelthicknesspredict.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "voltage_range_request")
public class VoltageRangeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 保存原始的预测请求数据
    @OneToOne(cascade = CascadeType.ALL)
    private PredictionRequest request;

    private double startVoltage;
    private double endVoltage;
}
