package org.saravana.boot.app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

		Rule rule = new Rule();
		rule.setAuthor("nagasara");
		rule.setControlHelper("JournalHelper.createJournal");
		rule.setJournalHelper("JournalHelper.createJournal");
		rule.setName("Settlement");
		rule.setPackageName("org.saravana");

		Field field1 = new Field();
		field1.setCondition(Condition.EQUALS);
		field1.setField("eventType");
		field1.setName("event type");
		field1.setValue("535/545");
		field1.setOverride("535 or 545");

		Field field2 = new Field();
		field2.setCondition(Condition.EQUALS);
		field2.setField("eventSubType");
		field2.setName("event sub type");
		field2.setValue("123/345");
		field2.setOverride("123 or 345");

		List<Field> fields = Arrays.asList(field1, field2);

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

	@RequestMapping(value = "/rules", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Rule> getRules() {
		return IntStream.range(0, 5).mapToObj(i -> getRule()).collect(Collectors.toList());
	}

}
