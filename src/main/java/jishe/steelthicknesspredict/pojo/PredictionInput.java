package jishe.steelthicknesspredict.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "prediction_input")
public class PredictionInput {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double constValue;
    private double averageThickness;
    private double endStartTemp;
    private double carbon;
    private double manganese;
    private double phosphorus;
    private double sulphur;
    private double silicon;
    private double copper;
    private double vanadium;
    private double columbium;
    private double chromium;
    private double nickel;
    private double molybdenum;
    private double tin;
    private double nitrogen;
    private double boron;
    private double aluminiumTotal;
    private double titanium;
    private double calcium;

    @Column(name = "`lead`") // 用反引号转义 MySQL 保留关键字
    private double lead;

    private double aluminiumSoluable;
    private double antimony;
    private double zinc;
    private double arsenic;
    private double cobalt;
    private double magnesium;
    private double zirconium;
    private double tungsten;
    private double tantalum;
}
