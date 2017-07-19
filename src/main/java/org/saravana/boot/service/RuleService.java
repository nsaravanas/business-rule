package org.saravana.boot.service;

import java.util.List;

import org.saravana.boot.model.Rule;
import org.saravana.boot.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleService {

	@Autowired
	private RuleRepository repository;

	public Rule getRuleByName(String name) {
		return repository.findOneByRuleName(name);
	}

	public List<Rule> getAllRules() {
		return repository.findAll();
	}

	public Rule saveRule(Rule rule) {
		return repository.save(rule);
	}

}
