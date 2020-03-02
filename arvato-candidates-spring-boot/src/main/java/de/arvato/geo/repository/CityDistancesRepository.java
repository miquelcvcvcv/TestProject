package de.arvato.geo.repository;
import de.arvato.geo.domain.CityDistances;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityDistancesRepository extends CrudRepository <CityDistances, Long> {

	
	
	
	
}
