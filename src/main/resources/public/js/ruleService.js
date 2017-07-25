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

	this.deleteRule = function(ruleId) {
		return $http({
			method : 'DELETE',
			url : '/delete',
			params : {
				'ruleId' : ruleId
			}
		});
	};

	this.downloadRule = function(ruleId) {
		return $http({
			method : 'GET',
			url : '/download',
			params : {
				'ruleId' : ruleId
			}
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

	this.getHelpers = function() {
		return $http({
			method : 'GET',
			url : '/helpers'
		});
	};

});