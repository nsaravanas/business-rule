package org.saravana.boot.service;

import java.util.Arrays;
import java.util.List;

import org.saravana.boot.model.Field;
import org.saravana.boot.model.Helper;
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

	public List<Helper> getHelpers() {
		Helper h1 = new Helper();
		h1.setName("BonyJournalCreationHelper");
		h1.setClaz("org.saravana.helper.BonyJournalCreationHelper.createJournal");
		Field f1 = new Field();
		f1.setName("event");
		Field f2 = new Field();
		f2.setName("account id");
		Field f3 = new Field();
		f3.setName("quantity");
		Field f4 = new Field();
		f4.setName("rule type");
		Field f5 = new Field();
		f5.setName("rule id");
		List<Field> fields = Arrays.asList(f1, f2, f3, f4, f5);
		h1.setFields(fields);

		Helper h2 = new Helper();
		h2.setName("JournalCreationHelper");
		h2.setClaz("org.saravana.helper.JournalCreationHelper.createJournal");
		Field f6 = new Field();
		f6.setName("event");
		Field f7 = new Field();
		f7.setName("account reference");
		Field f8 = new Field();
		f8.setName("quantity");
		Field f9 = new Field();
		f9.setName("rule type");
		Field f10 = new Field();
		f10.setName("rule id");
		List<Field> fields2 = Arrays.asList(f6, f7, f8, f9, f10);
		h2.setFields(fields2);
		return Arrays.asList(h1, h2);
	}

}
