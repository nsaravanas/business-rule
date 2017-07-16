app.controller('ruleController', [ '$scope', '$window', 'ruleService',
		function($scope, $window, ruleService) {

			$scope.model = {
				data : {},
				selected : null,
				conditions : [],
				user : '',
				types : [ 'Developer', 'Business Analyst' ],
				rulesname : []
			};

			var rules = function() {
				ruleService.getRules().then(function(response) {
					$scope.model.data = response.data[0];
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

			$scope.addField = function(criteria) {

				if (criteria == null) {
					criteria = {};
					criteria.fields = [];
				}

				criteria.fields.push({
					'name' : '',
					'condition' : 'EQUALS',
					'value' : ''
				});
			};

			$scope.saveFields = function(rule, fields, type) {
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
				showToast();
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

			var showToast = function() {
				console.log('Toast!');
				var x = document.getElementById("snackbar");
				x.className = "show";
				setTimeout(function() {
					x.className = x.className.replace("show", "");
				}, 3000);
				console.log('Done!');
				alert('Saved');
			};

			loadConditions();
			rules();
			rulesname();

		} ]);