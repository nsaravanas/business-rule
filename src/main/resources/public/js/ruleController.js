app.controller('ruleController', [ '$scope', 'ruleService',
		function($scope, ruleService) {

			$scope.data = [];

			var rules = function() {
				ruleService.getRules().then(function(response) {
					$scope.data = response.data;
				});
			};

			function AppCtrl($scope) {
				$scope.currentNavItem = 'page1';
			}

			rules();

		} ]);