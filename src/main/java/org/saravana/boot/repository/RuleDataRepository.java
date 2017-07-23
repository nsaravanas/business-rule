package org.saravana.boot.repository;

import org.saravana.boot.model.RuleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleDataRepository extends JpaRepository<RuleData, String> {

}
