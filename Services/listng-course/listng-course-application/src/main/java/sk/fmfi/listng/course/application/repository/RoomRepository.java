package sk.fmfi.listng.course.application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sk.fmfi.listng.course.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
