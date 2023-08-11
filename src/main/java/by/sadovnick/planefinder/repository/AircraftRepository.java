package by.sadovnick.planefinder.repository;

import by.sadovnick.planefinder.model.Aircraft;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends CrudRepository<Aircraft, Long> {
}
