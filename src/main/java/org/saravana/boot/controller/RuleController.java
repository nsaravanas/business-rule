package org.saravana.boot.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.saravana.boot.model.Condition;
import org.saravana.boot.model.Helper;
import org.saravana.boot.model.Rule;
import org.saravana.boot.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
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
		return service.getAllRules().stream().map(Rule::getName).collect(Collectors.toList());
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Rule saveRule(@RequestBody Rule rule) {
		return service.saveRule(rule);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void deleteRule(@RequestParam("ruleId") String name) {
		service.deleteRule(name);
	}

	@RequestMapping(value = "/helpers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Helper> getHelpers() {
		return service.getHelpers();
	}

	public Rule createRule() {
		Rule r = new Rule();
		return r;
	}
}
