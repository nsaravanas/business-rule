app.service('ruleService', function($http) {
	delete $http.defaults.headers.common['X-Requested-With'];

	this.getRule = function(name) {
		return $http({
			method : 'GET',
			url : '/rule',
			params : {
				'name' : name
			}
		});
	};

	this.saveRule = function(rule) {
		return $http({
			method : 'POST',
			url : '/save',
			data : rule
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
	};

	this.getRulesName = function() {
		return $http({
			method : 'GET',
			url : '/rulesname'
		});
	};

});