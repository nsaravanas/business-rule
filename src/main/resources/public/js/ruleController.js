app
		.controller(
				'ruleController',
				[
						'$scope',
						'$window',
						'ruleService',
						function($scope, $window, ruleService) {

							$scope.model = {
								data : {},
								selected : null,
								conditions : [],
								types : [ 'Developer', 'Business Analyst' ],
								user : 'Business Analyst',
								rulesname : [],
								helpers : [],
								jour_curr : null,
								jour_prev : null,
								cntl_curr : null,
								cntl_prev : null
							};

							var rule = function(name) {
								ruleService.getRule(name).then(
										function(response) {
											$scope.model.data = response.data;
										});
							};

							var loadConditions = function() {
								ruleService
										.getConditions()
										.then(
												function(response) {
													$scope.model.conditions = response.data;
												});
							};

							var rulesname = function() {
								ruleService
										.getRulesName()
										.then(
												function(response) {
													$scope.model.rulesname = response.data;
												});
							};

							var helpers = function() {
								ruleService
										.getHelpers()
										.then(
												function(response) {
													$scope.model.helpers = response.data;
												});
							};

							$scope.reset = function() {
								$scope.model.selected = {};
							};

							$scope.saveFields = function() {
								var rule = $scope.model.data;
								save(rule);
								console.log('save field end');
							};

							$scope.reload = function(type) {
								console.log(type + '--->' + $scope.model.user);
							};

							$scope.updateRule = function() {
								console.log('update rule method');
								if ($scope.model.selected != null) {
									rule($scope.model.selected);
								}
							}

							$scope.createRule = function() {
								console.log('create rule method');
								$scope.model.data = {};
							}

							$scope.editRule = function() {
								console.log('update rule method');
								if ($scope.model.selected != null) {
									rule($scope.model.selected);
								}
							}

							$scope.save = function() {
								var data = $scope.model.data;
								ruleService
										.saveRule(data)
										.then(
												function(response) {
													$scope.model.data = response.data;
													$scope.model.selected = response.data.name;
													rulesname();
												});
							}

							$scope.deleteRule = function() {
								console.log('delete rule method');
								if ($scope.model.selected != null) {
									rule($scope.model.selected);
								}
							}

							$scope.deleteRow = function(type, idx) {
								var rule = $scope.model.data;
								switch (type) {
								case "criteria":
									if (rule.criteria != null) {
										rule.criteria.fields.splice(idx, 1);
									}
									break;
								case "journal":
									if (rule.journalData != null) {
										rule.criteria.fields.splice(idx, 1);
									}
									break;
								case "control":
									if (rule.controlData != null) {
										rule.criteria.fields.splice(idx, 1);
									}
									break;
								}
							}

							$scope.deleteRow = function(type) {
								var rule = $scope.model.data;
								switch (type) {
								case "criteria":
									if (rule.criteria != null) {
										rule.criteria.fields.splice(-1, 1);
									}
									break;
								case "journal":
									if (rule.journalData != null) {
										rule.criteria.fields.splice(-1, 1);
									}
									break;
								case "control":
									if (rule.controlData != null) {
										rule.criteria.fields.splice(-1, 1);
									}
									break;
								}
							};

							$scope.addRow = function(type) {
								var rule = $scope.model.data;
								var field = {
									'name' : '',
									'condition' : 'EQUALS',
									'value' : ''
								};
								console.log('add field begin');
								switch (type) {
								case "criteria":
									if (rule.criteria == null) {
										rule.criteria = {};
										rule.criteria.fields = [];
									}
									rule.criteria.fields.push(field);
									break;
								case "journal":
									if (rule.journalData == null) {
										rule.journalData = {};
										rule.journalData.fields = [];
									}
									rule.journalData.fields.push(field);
									break;
								case "control":
									if (rule.controlData == null) {
										rule.controlData = {};
										rule.controlData.fields = [];
									}
									rule.controlData.fields.push(field);
									break;
								}
								console.log('add field end');
							};

							$scope.setHelper = function(type) {
								var helpers = $scope.model.helpers;
								if (type == 'control') {
									console.log('selection ' + $scope.model.data.jour_curr);
									if ($scope.model.data.jour_curr == $scope.model.data.jour_prev) {
										console.log('set helper matched');
										return;
									}
									for (i = 0; i < helpers.length; i++) {
										if ($scope.model.data.jour_curr == helpers[i].name) {
											$scope.model.data.jour_prev = $scope.model.data.jour_curr;
											$scope.model.data.journalHelper = helpers[i];
											console.log('selected ' + helpers[i].name);
										}
									}
									console.log('set helper end');
								}
								if (type == 'journal') {
									console.log('selection ' + $scope.model.data.cntl_curr);
									if ($scope.model.data.cntl_curr == $scope.model.data.cntl_prev) {
										console.log('set helper matched');
										return;
									}
									for (i = 0; i < helpers.length; i++) {
										if ($scope.model.data.cntl_curr == helpers[i].name) {
											$scope.model.data.cntl_prev = $scope.model.data.cntl_curr;
											$scope.model.data.controlHelper = helpers[i];
											console.log('selected ' + helpers[i].name);
										}
									}
									console.log('set helper end');
								}
							};

							loadConditions();
							rulesname();
							helpers();

						} ]);