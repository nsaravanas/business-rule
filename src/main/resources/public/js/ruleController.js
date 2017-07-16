app.controller('ruleController', [
		'$scope',
		'ruleService',
		function($scope, ruleService) {

			$scope.model = {
				data : [],
				selected : {},
				conditions : []
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

			// gets the template to ng-include for a table row / item
			$scope.getTemplate = function(field) {
				console.log('Field Template ' + field.id);
				if (field.id === $scope.model.selected.id)
					return 'edit';
				else
					return 'display';
			};

			$scope.editField = function(field) {
				$scope.model.selected = angular.copy(field);
			};

			$scope.saveField = function(idx) {
				console.log("Saving field");
				$scope.model.data[idx] = angular.copy($scope.model.selected);
				console.log('persisted '
						+ JSON.stringify($scope.model.selected));
				$scope.reset();
			};

			$scope.reset = function() {
				$scope.model.selected = {};
			};

			$scope.deleteField = function(criteria, index) {
				console.log(index);
				$scope.model.data[0].criteria.fields.splice(index, 1);
			};

			$scope.addField = function(criteria, index) {
				console.log(index);
				criteria.fields.push({
					'name' : '',
					'condition' : '',
					'value' : ''
				});
			};

			$scope.saveCriteria = function(criteria) {
				console.log('Criteria');
				console.log(JSON.stringify(criteria));
				$scope.model.data[0].criteria = criteria;
			};

			// init
			loadConditions();
			rules();

		} ]);