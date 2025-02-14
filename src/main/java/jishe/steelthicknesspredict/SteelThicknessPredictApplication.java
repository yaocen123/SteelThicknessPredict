package jishe.steelthicknesspredict;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class SteelThicknessPredictApplication {

    public static void main(String[] args) {
        SpringApplication.run(SteelThicknessPredictApplication.class, args);
    }

}
