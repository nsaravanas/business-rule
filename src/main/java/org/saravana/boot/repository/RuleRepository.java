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
		return rules.get(0);
	}

	public List<Rule> findAll() {
		if (rules.isEmpty()) {
			init();
		}
		return this.rules;
	}

	private void init() {
		Rule r = new Rule();
		r.setAuthor("nagasara");
		r.setId(1);
		r.setName("Settlement Deliver");
		r.setPackageName("org.saravana.drools.rule");
		rules.add(r);
	}

	public Rule save(Rule rule) {
		return rules.get(0);
	}

}