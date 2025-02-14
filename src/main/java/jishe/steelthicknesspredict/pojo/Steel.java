package jishe.steelthicknesspredict.pojo;

public class Steel {

    private String pieceId;
    private String measureFlag;
    private double averageThickness;
    private double averageThicknessC;
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
    private double lead;
    private double aluminiumSoluble;
    private double antimony;
    private double zinc;
    private double arsenic;
    private double cobalt;
    private double magnesium;
    private double zirconium;
    private double tungsten;
    private double tantalum;
    private double endStartTemp;

    public Steel(String pieceId, String measureFlag, double averageThickness, double averageThicknessC, double carbon, double manganese,
                      double phosphorus, double sulphur, double silicon, double copper, double vanadium, double columbium, double chromium, double nickel,
                      double molybdenum, double tin, double nitrogen, double boron, double aluminiumTotal, double titanium, double calcium,
                      double lead, double aluminiumSoluble, double antimony, double zinc, double arsenic, double cobalt, double magnesium, double zirconium,
                      double tungsten, double tantalum, double endStartTemp) {
        this.pieceId = pieceId;
        this.measureFlag = measureFlag;
        this.averageThickness = averageThickness;
        this.averageThicknessC = averageThicknessC;
        this.carbon = carbon;
        this.manganese = manganese;
        this.phosphorus = phosphorus;
        this.sulphur = sulphur;
        this.silicon = silicon;
        this.copper = copper;
        this.vanadium = vanadium;
        this.columbium = columbium;
        this.chromium = chromium;
        this.nickel = nickel;
        this.molybdenum = molybdenum;
        this.tin = tin;
        this.nitrogen = nitrogen;
        this.boron = boron;
        this.aluminiumTotal = aluminiumTotal;
        this.titanium = titanium;
        this.calcium = calcium;
        this.lead = lead;
        this.aluminiumSoluble = aluminiumSoluble;
        this.antimony = antimony;
        this.zinc = zinc;
        this.arsenic = arsenic;
        this.cobalt = cobalt;
        this.magnesium = magnesium;
        this.zirconium = zirconium;
        this.tungsten = tungsten;
        this.tantalum = tantalum;
        this.endStartTemp = endStartTemp;
    }

    // Getter and Setter methods
    public String getPieceId() {
        return pieceId;
    }

    public void setPieceId(String pieceId) {
        this.pieceId = pieceId;
    }

    public String getMeasureFlag() {
        return measureFlag;
    }

    public void setMeasureFlag(String measureFlag) {
        this.measureFlag = measureFlag;
    }

    public double getAverageThickness() {
        return averageThickness;
    }

    public void setAverageThickness(double averageThickness) {
        this.averageThickness = averageThickness;
    }

    public double getAverageThicknessC() {
        return averageThicknessC;
    }

    public void setAverageThicknessC(double averageThicknessC) {
        this.averageThicknessC = averageThicknessC;
    }

    // Similar getter and setter methods for all other properties
    // You can implement them for the rest of the attributes similarly.

    public double getEndStartTemp() {
        return endStartTemp;
    }

    public void setEndStartTemp(double endStartTemp) {
        this.endStartTemp = endStartTemp;
    }

    @Override
    public String toString() {
        return "SteelPlate{" +
                "pieceId='" + pieceId + '\'' +
                ", measureFlag='" + measureFlag + '\'' +
                ", averageThickness=" + averageThickness +
                ", averageThicknessC=" + averageThicknessC +
                ", carbon=" + carbon +
                ", manganese=" + manganese +
                ", phosphorus=" + phosphorus +
                ", sulphur=" + sulphur +
                ", silicon=" + silicon +
                ", copper=" + copper +
                ", vanadium=" + vanadium +
                ", columbium=" + columbium +
                ", chromium=" + chromium +
                ", nickel=" + nickel +
                ", molybdenum=" + molybdenum +
                ", tin=" + tin +
                ", nitrogen=" + nitrogen +
                ", boron=" + boron +
                ", aluminiumTotal=" + aluminiumTotal +
                ", titanium=" + titanium +
                ", calcium=" + calcium +
                ", lead=" + lead +
                ", aluminiumSoluble=" + aluminiumSoluble +
                ", antimony=" + antimony +
                ", zinc=" + zinc +
                ", arsenic=" + arsenic +
                ", cobalt=" + cobalt +
                ", magnesium=" + magnesium +
                ", zirconium=" + zirconium +
                ", tungsten=" + tungsten +
                ", tantalum=" + tantalum +
                ", endStartTemp=" + endStartTemp +
                '}';
    }
}

