package de.arvato.geo.repository;
import de.arvato.geo.domain.City;
import de.arvato.geo.domain.Distances;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface DistancesRepository extends CrudRepository <Distances, Long> {

	
	
}
