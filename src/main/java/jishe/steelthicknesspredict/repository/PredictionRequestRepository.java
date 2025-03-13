package jishe.steelthicknesspredict.repository;

import jishe.steelthicknesspredict.pojo.PredictionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictionRequestRepository extends JpaRepository<PredictionRequest, Long> {
}
