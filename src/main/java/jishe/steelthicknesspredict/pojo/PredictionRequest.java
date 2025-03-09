package jishe.steelthicknesspredict.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PredictionRequest {

    @JsonProperty("Length_From_Head")
    private double lengthFromHead;

    @JsonProperty("Number_of_Measurements")
    private int numberOfMeasurements;

    @JsonProperty("Carbon")
    private double carbon;

    @JsonProperty("Manganese")
    private double manganese;

    @JsonProperty("Phosphorus")
    private double phosphorus;

    @JsonProperty("Sulphur")
    private double sulphur;

    @JsonProperty("Silicon")
    private double silicon;

    @JsonProperty("Copper")
    private double copper;

    @JsonProperty("Vanadium")
    private double vanadium;

    @JsonProperty("Columbium")
    private double columbium;

    @JsonProperty("Chromium")
    private double chromium;

    @JsonProperty("Nickel")
    private double nickel;

    @JsonProperty("Molybdenum")
    private double molybdenum;

    @JsonProperty("Tin")
    private double tin;

    @JsonProperty("Nitrogen")
    private double nitrogen;

    @JsonProperty("Boron")
    private double boron;

    @JsonProperty("Aluminium_Total")
    private double aluminiumTotal;

    @JsonProperty("Titanium")
    private double titanium;

    @JsonProperty("Calcium")
    private double calcium;

    @JsonProperty("Lead")
    private double lead;

    @JsonProperty("Aluminium_Soluable")
    private double aluminiumSoluable;

    @JsonProperty("Antimony")
    private double antimony;

    @JsonProperty("Zinc")
    private double zinc;

    @JsonProperty("Arsenic")
    private double arsenic;

    @JsonProperty("Cobalt")
    private double cobalt;

    @JsonProperty("Magnesium")
    private double magnesium;

    @JsonProperty("Zirconium")
    private double zirconium;

    @JsonProperty("Tungsten")
    private double tungsten;

    @JsonProperty("Tantalum")
    private double tantalum;

    @JsonProperty("Phase_Start_Temp")
    private double phaseStartTemp;

    @JsonProperty("End_Start_Temp")
    private double endStartTemp;

    @JsonProperty("FmThk_Meas")
    private double fmThkMeas;
}
