package com.beliscosolutions.apiagriculturefarm.repository;

import com.beliscosolutions.apiagriculturefarm.domains.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Technology entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {

}
