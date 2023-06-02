package sk.fmfi.listng.course.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.fmfi.listng.course.domain.Period;

import java.util.List;

public interface PeriodRepository extends JpaRepository<Period, Long> {
    
    List<Period> findAllByOrderByStartDesc();
}
