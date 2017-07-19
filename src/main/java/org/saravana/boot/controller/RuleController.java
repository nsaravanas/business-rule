package org.saravana.boot.controller;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.saravana.boot.model.Condition;
import org.saravana.boot.model.Field;
import org.saravana.boot.model.Rule;
import org.saravana.boot.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuleController {

	@Autowired
	private RuleService service;

	@RequestMapping(value = "/rule", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Rule getRule(@RequestParam String name) {
		return service.getRuleByName(name);

	}

	private Rule createRule() {
		Rule rule = new Rule();
		Random r = new Random();
		rule.setId(r.nextInt(100));
		rule.setName(rule.getName() + r.nextInt(200));
		rule.setAuthor("nagasara");
		rule.setControlHelper("JournalHelper.createJournal");
		rule.setJournalHelper("JournalHelper.createJournal");
		rule.setPackageName("org.saravana");
		return rule;
	}

	private Field createField() {
		Field field = new Field();
		field.setCondition(Condition.EQUALS);
		field.setField("eventSubType");
		field.setName("event sub type");
		field.setValue("123/345");
		field.setOverride("123 or 345");
		return field;
	}

	@RequestMapping(value = "/rules", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Rule> getRules() {
		return service.getAllRules();
	}

	@RequestMapping(value = "/conditions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Condition[] getConditions() {
		return Condition.values();
	}

	@RequestMapping(value = "/rulesname", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getRulesName() {
		System.out.println("getRulesName called");
		return service.getAllRules().stream().map(Rule::getName).collect(Collectors.toList());
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Rule saveRule(Rule rule) {
		System.out.println("save");
		return service.saveRule(rule);
	}
}
