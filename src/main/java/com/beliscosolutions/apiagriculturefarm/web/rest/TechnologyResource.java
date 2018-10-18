package com.beliscosolutions.apiagriculturefarm.web.rest;

import com.beliscosolutions.apiagriculturefarm.domains.Technology;
import com.beliscosolutions.apiagriculturefarm.repository.TechnologyRepository;
import com.beliscosolutions.apiagriculturefarm.web.rest.errors.BadRequestAlertException;
import com.beliscosolutions.apiagriculturefarm.web.rest.utils.HeaderUtil;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Technology.
 */
@RestController
@RequestMapping("/api")
public class TechnologyResource {

    private final Logger log = LoggerFactory.getLogger(TechnologyResource.class);

    private static final String ENTITY_NAME = "technology";

    private final TechnologyRepository technologyRepository;

    public TechnologyResource(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    /**
     * POST  /technologies : Create a new technology.
     *
     * @param technology the technology to create
     * @return the ResponseEntity with status 201 (Created) and with body the new technology, or with status 400 (Bad Request) if the technology has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/technologies")
    @Timed
    public ResponseEntity<Technology> createTechnology(@Valid @RequestBody Technology technology) throws URISyntaxException {
        log.debug("REST request to save Technology : {}", technology);
        if (technology.getId() != null) {
            throw new BadRequestAlertException("A new technology cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Technology result = technologyRepository.save(technology);
        return ResponseEntity.created(new URI("/api/technologies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /technologies : Updates an existing technology.
     *
     * @param technology the technology to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated technology,
     * or with status 400 (Bad Request) if the technology is not valid,
     * or with status 500 (Internal Server Error) if the technology couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/technologies")
    @Timed
    public ResponseEntity<Technology> updateTechnology(@Valid @RequestBody Technology technology) throws URISyntaxException {
        log.debug("REST request to update Technology : {}", technology);
        if (technology.getId() == null) {
            return createTechnology(technology);
        }
        Technology result = technologyRepository.save(technology);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, technology.getId().toString()))
            .body(result);
    }

    /**
     * GET  /technologies : get all the technologies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of technologies in body
     */
    @GetMapping("/technologies")
    @Timed
    public List<Technology> getAllTechnologies() {
        log.debug("REST request to get all Technologies");
        return technologyRepository.findAll();
        }

    /**
     * GET  /technologies/:id : get the "id" technology.
     *
     * @param id the id of the technology to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the technology, or with status 404 (Not Found)
     */
    @GetMapping("/technologies/{id}")
    @Timed
    public ResponseEntity<Technology> getTechnology(@PathVariable Long id) {
        log.debug("REST request to get Technology : {}", id);
        Optional<Technology> technology = technologyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(technology);
    }

    /**
     * DELETE  /technologies/:id : delete the "id" technology.
     *
     * @param id the id of the technology to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/technologies/{id}")
    @Timed
    public ResponseEntity<Void> deleteTechnology(@PathVariable Long id) {
        log.debug("REST request to delete Technology : {}", id);
        technologyRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
