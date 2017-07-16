package org.saravana.boot.controller;

import java.util.ArrayList;
import java.util.List;

import org.saravana.boot.model.Condition;
import org.saravana.boot.model.Data;
import org.saravana.boot.model.Field;
import org.saravana.boot.model.MatchCriteria;
import org.saravana.boot.model.Rule;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuleController {

	@RequestMapping(value = "/rule", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Rule getRule() {

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
		journalData.setData(fields);
		rule.setJournalData(journalData);

		Data controlData = new Data();
		controlData.setData(fields);
		rule.setControlData(controlData);

		return rule;

	}

	private Rule createRule() {
		Rule rule = new Rule();
		rule.setAuthor("nagasara");
		rule.setControlHelper("JournalHelper.createJournal");
		rule.setJournalHelper("JournalHelper.createJournal");
		rule.setName("Settlement");
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
		List<Rule> rules = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Rule rule = getRule();
			rule.setId(i);
			rule.getControlData().setId(i);
			rule.getCriteria().setId(i);
			rule.getJournalData().setId(i);
			rules.add(rule);
		}
		return rules.subList(rules.size() - 1, rules.size());
	}

	@RequestMapping(value = "/conditions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Condition[] getConditions() {
		return Condition.values();
	}

}
