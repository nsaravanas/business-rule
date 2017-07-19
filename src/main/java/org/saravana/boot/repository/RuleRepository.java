package org.saravana.boot.repository;

import java.util.ArrayList;
import java.util.List;

import org.saravana.boot.model.Rule;
import org.springframework.stereotype.Repository;

//@Repository
//public interface RuleRepository extends JpaRepository<Rule, Long> {
//
//	Rule findOneByRuleName(String name);
//
//}

@Repository
public class RuleRepository {

	private List<Rule> rules = new ArrayList<>();

	public Rule findOneByRuleName(String name) {
		return rules.stream().findFirst().orElse(new Rule());
	}

	public List<Rule> findAll() {
		return this.rules;
	}

	public Rule save(Rule rule) {
		rules.add(rule);
		return rule;
	}

}