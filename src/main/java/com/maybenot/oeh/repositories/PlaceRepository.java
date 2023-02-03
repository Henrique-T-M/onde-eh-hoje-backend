package com.maybenot.oeh.repositories;

import com.maybenot.oeh.entities.Place;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface PlaceRepository extends CrudRepository<Place, String> {
}
