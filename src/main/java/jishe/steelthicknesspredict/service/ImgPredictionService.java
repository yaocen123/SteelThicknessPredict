package jishe.steelthicknesspredict.service;

import jishe.steelthicknesspredict.pojo.ImgPredictionInput;
import jishe.steelthicknesspredict.pojo.PredictionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImgPredictionService {

    @Autowired
    private PredictionService predictionService;

    // 根据起始温度和终止温度返回预测的温度区间值
    public double[] predictTemperatureRangeForImg(ImgPredictionInput input, double startTemp, double endTemp) {
        // 计算温度区间的大小
        int rangeSize = (int) (endTemp - startTemp) + 1;
        double[] temperatures = new double[rangeSize];
        double[] predictedValues = new double[rangeSize];

        PredictionInput inp = new PredictionInput();

        inp.setConstValue(input.getConstValue());
        inp.setAverageThickness(input.getAverageThickness());
        inp.setEndStartTemp(input.getStartTemp());
        inp.setCarbon(input.getCarbon());
        inp.setManganese(input.getManganese());
        inp.setPhosphorus(input.getPhosphorus());
        inp.setSulphur(input.getSulphur());
        inp.setSilicon(input.getSilicon());
        inp.setCopper(input.getCopper());
        inp.setVanadium(input.getVanadium());
        inp.setColumbium(input.getColumbium());
        inp.setChromium(input.getChromium());
        inp.setNickel(input.getNickel());
        inp.setMolybdenum(input.getMolybdenum());
        inp.setTin(input.getTin());
        inp.setNitrogen(input.getNitrogen());
        inp.setBoron(input.getBoron());
        inp.setAluminiumTotal(input.getAluminiumTotal());
        inp.setTitanium(input.getTitanium());
        inp.setCalcium(input.getCalcium());
        inp.setLead(input.getLead());
        inp.setAluminiumSoluable(input.getAluminiumSoluable());
        inp.setAntimony(input.getAntimony());
        inp.setZinc(input.getZinc());
        inp.setArsenic(input.getArsenic());
        inp.setCobalt(input.getCobalt());
        inp.setMagnesium(input.getMagnesium());
        inp.setZirconium(input.getZirconium());
        inp.setTungsten(input.getTungsten());
        inp.setTantalum(input.getTantalum());





        // 对每个温度点进行预测
        for (int i = 0; i < rangeSize; i++) {
            double currentTemp = startTemp + i;
            temperatures[i] = currentTemp;
            predictedValues[i] = predictionService.predictColdThicknessForImg(inp, currentTemp); // 调用 PredictionService 的方法
        }

        // 返回预测的温度值和对应的预测结果
        return predictedValues;
    }
}
