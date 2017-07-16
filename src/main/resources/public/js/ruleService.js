app.service('ruleService', function($http) {
	delete $http.defaults.headers.common['X-Requested-With'];

	this.getRule = function() {
		return $http({
			method : 'GET',
			url : '/rule'
		});
	};

	this.getRules = function() {
		return $http({
			method : 'GET',
			url : '/rules'
		});
	};

	this.getConditions = function() {
		return $http({
			method : 'GET',
			url : '/conditions'
		});
	}
});