app.controller('ruleController', [ '$scope', '$window', 'ruleService',
		function($scope, $window, ruleService) {

			$scope.model = {
				data : [],
				selected : {},
				conditions : [],
				user : '',
				types : [ 'Developer', 'Business Analyst' ]
			};

			var rules = function() {
				ruleService.getRules().then(function(response) {
					$scope.model.data = response.data;
				});
			};

			var loadConditions = function() {
				ruleService.getConditions().then(function(response) {
					$scope.model.conditions = response.data;
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

		} ]);