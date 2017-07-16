app.controller('ruleController', [
		'$scope',
		'ruleService',
		function($scope, ruleService) {

			$scope.model = {
				data : [],
				selected : {}
			};

			var rules = function() {
				ruleService.getRules().then(function(response) {
					$scope.model.data = response.data;
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

			rules();

			$scope.currentJobIndex = 0;

			$scope.showNext = function(index) {
				$scope.currentJobIndex = ++index;
			};
			$scope.hideThis = function(index) {
				$scope.currentJobIndex = --index;
			};

		} ]);