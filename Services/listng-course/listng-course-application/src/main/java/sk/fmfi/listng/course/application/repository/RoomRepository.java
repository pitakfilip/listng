package sk.fmfi.listng.course.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.fmfi.listng.domain.administration.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
