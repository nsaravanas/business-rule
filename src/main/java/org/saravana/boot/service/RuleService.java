package org.saravana.boot.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.saravana.boot.model.Field;
import org.saravana.boot.model.Helper;
import org.saravana.boot.model.Rule;
import org.saravana.boot.model.RuleData;
import org.saravana.boot.repository.RuleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class RuleService {

	@Autowired
	private RuleDataRepository dataRepository;

	private static final Gson GSON = new Gson();

	public Rule getRuleByName(String name) {
		RuleData data = dataRepository.findOne(name);
		return GSON.fromJson(data.getRuleData(), Rule.class);
	}

	public List<Rule> getAllRules() {
		return dataRepository.findAll().stream().map(r -> r.getRuleData()).map(d -> GSON.fromJson(d, Rule.class))
				.collect(Collectors.toList());
	}

	public Rule saveRule(Rule rule) {
		RuleData rd = new RuleData();
		rd.setName(rule.getName());
		rd.setRuleData(GSON.toJson(rule));
		RuleData persisted = dataRepository.save(rd);
		return GSON.fromJson(persisted.getRuleData(), Rule.class);
	}

	public List<Helper> getHelpers() {
		Helper h1 = new Helper();
		h1.setName("BonyJournalCreationHelper");
		h1.setClaz("org.saravana.helper.BonyJournalCreationHelper.createJournal");
		Field f1 = new Field();
		f1.setName("event");
		Field f2 = new Field();
		f2.setName("accountId");
		Field f3 = new Field();
		f3.setName("quantity");
		Field f4 = new Field();
		f4.setName("ruleType");
		Field f5 = new Field();
		f5.setName("ruleId");
		List<Field> fields = Arrays.asList(f1, f2, f3, f4, f5);
		h1.setFields(fields);

		Helper h2 = new Helper();
		h2.setName("JournalCreationHelper");
		h2.setClaz("org.saravana.helper.JournalCreationHelper.createJournal");
		Field f6 = new Field();
		f6.setName("event");
		Field f7 = new Field();
		f7.setName("accountReference");
		Field f8 = new Field();
		f8.setName("quantity");
		Field f9 = new Field();
		f9.setName("ruleType");
		Field f10 = new Field();
		f10.setName("ruleId");
		List<Field> fields2 = Arrays.asList(f6, f7, f8, f9, f10);
		h2.setFields(fields2);

		Helper h3 = new Helper();
		h3.setName("ControlCreationHelper");
		h3.setClaz("org.saravana.helper.ControlCreationHelper.createControl");
		Field f11 = new Field();
		f11.setName("event");
		Field f12 = new Field();
		f12.setName("accountRef");
		Field f13 = new Field();
		f13.setName("quantity");
		Field f14 = new Field();
		f14.setName("ruleType");
		Field f15 = new Field();
		f15.setName("ruleId");
		List<Field> fields3 = Arrays.asList(f11, f12, f13, f14, f15);
		h3.setFields(fields3);

		return Arrays.asList(h1, h2, h3);
	}

	public void deleteRule(String name) {
		dataRepository.delete(name);
	}

}
