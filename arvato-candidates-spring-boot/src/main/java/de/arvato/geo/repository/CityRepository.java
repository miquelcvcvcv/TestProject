package de.arvato.geo.repository;
import de.arvato.geo.domain.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends CrudRepository <City, Long> {

	
	
}
