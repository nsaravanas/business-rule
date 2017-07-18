app.controller('ruleController', [ '$scope', '$window', 'ruleService',
		function($scope, $window, ruleService) {

			$scope.model = {
				data : {},
				selected : null,
				conditions : [],
				types : [ 'Developer', 'Business Analyst' ],
				user : '',
				rulesname : []
			};

			var rules = function() {
				ruleService.getRules().then(function(response) {
					// $scope.model.data = response.data[0];
				});
			};

			var rule = function(name) {
				ruleService.getRule(name).then(function(response) {
					$scope.model.data = response.data;
				});
			};

			var loadConditions = function() {
				ruleService.getConditions().then(function(response) {
					$scope.model.conditions = response.data;
				});
			};

			var rulesname = function() {
				ruleService.getRulesName().then(function(response) {
					console.log('rules ' + response.data);
					$scope.model.rulesname = response.data;
				});
			};

			$scope.reset = function() {
				$scope.model.selected = {};
			};

			$scope.deleteField = function(criteria, index) {
				console.log(index);
				criteria.fields.splice(index, 1);
			};

			$scope.addField = function(rule, fields, type) {
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

			$scope.saveFields = function(rule, fields, type) {
				console.log('save field begin' + fields.length);
				switch (type) {
				case "criteria":
					rule.criteria.fields = fields;
					break;
				case "journal":
					rule.journalData.fields = fields;
					break;
				case "control":
					rule.controlData.fields = fields;
					break;
				}
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

			// var stubRule = function() {
			// return {
			// "id" : -1,
			// "name" : null,
			// "packageName" : null,
			// "author" : null,
			// "journalHelper" : null,
			// "controlHelper" : null,
			// "criteria" : {
			// "id" : -1,
			// "fields" : [ {
			// "id" : -1,
			// "name" : null,
			// "condition" : null,
			// "field" : null,
			// "value" : null,
			// "override" : null
			// } ],
			// "override" : null
			// },
			// "journalData" : {
			// "id" : -1,
			// "fields" : [ {
			// "id" : -1,
			// "name" : null,
			// "condition" : null,
			// "field" : null,
			// "value" : null,
			// "override" : null
			// } ]
			// },
			// "controlData" : {
			// "id" : -1,
			// "fields" : [ {
			// "id" : -1,
			// "name" : null,
			// "condition" : null,
			// "field" : null,
			// "value" : null,
			// "override" : null
			// } ]
			// }
			// }
			// };

			loadConditions();
			rules();
			rulesname();

		} ]);