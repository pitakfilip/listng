package sk.fmfi.listng.course.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.fmfi.listng.domain.course.Period;

public interface PeriodRepository extends JpaRepository<Period, Long> {
    
}
