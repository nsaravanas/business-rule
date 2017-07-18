package org.saravana.boot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.saravana.boot.model.Condition;
import org.saravana.boot.model.Data;
import org.saravana.boot.model.Field;
import org.saravana.boot.model.MatchCriteria;
import org.saravana.boot.model.Rule;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuleController {

	@RequestMapping(value = "/rule", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Rule getRule(@RequestParam String name) {
		System.out.println("getRule called");

		Rule rule = createRule();

		List<Field> fields = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Field f = createField();
			f.setId(i);
			f.setField(f.getField() + i);
			f.setName(f.getName() + i);
			f.setValue(f.getValue() + i);
			fields.add(f);
		}

		MatchCriteria criteria = new MatchCriteria();
		criteria.setFields(fields);
		rule.setCriteria(criteria);

		Data journalData = new Data();
		journalData.setFields(fields);
		rule.setJournalData(journalData);

		Data controlData = new Data();
		controlData.setFields(fields);
		rule.setControlData(controlData);

		return rule;

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
		System.out.println("getRules called");
		List<Rule> rules = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Rule rule = getRule("");
			rule.getControlData().setId(i);
			rule.getCriteria().setId(i);
			rule.getJournalData().setId(i);
			rules.add(rule);
		}
		return rules;
	}

	@RequestMapping(value = "/conditions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Condition[] getConditions() {
		return Condition.values();
	}

	@RequestMapping(value = "/rulesname", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getRulesName() {
		System.out.println("getRulesName called");
		return getRules().stream().map(Rule::getName).collect(Collectors.toList());
	}

}
