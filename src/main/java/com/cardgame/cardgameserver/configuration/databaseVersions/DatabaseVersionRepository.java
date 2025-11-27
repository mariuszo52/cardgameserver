package com.cardgame.cardgameserver.configuration.databaseVersions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseVersionRepository extends CrudRepository<Version, Long> {
}
