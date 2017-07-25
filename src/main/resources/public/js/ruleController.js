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

							var downloadRuleService = function(ruleId){
								ruleService.downloadRule(ruleId).then(
									function(response){
											$("downloadAlert").fadeIn();
											$("downloadAlert").fadeOut(3000);
											var data = response.data;
								        	var blob = new Blob([data], { type: 'text/plain' });
								        	var url = $window.URL || $window.webkitURL;
											return url.createObjectURL(blob);							
										}).catch(function(error){
											$("#downloadFail").fadeIn();
											$("#downloadFail").fadeOut(3000);									
									});
							};
							
							var saveRuleService = function(rule) {
								ruleService.saveRule(rule).then(
									function(response){
										 initData(response.data);
										 setSelection(response.data.name);
										 $("#saveAlert").fadeIn();
										 $("#saveAlert").fadeOut(3000);
										 loadRuleNames();
									}
								).catch(function(error){
									console.log(error);
									$("#failAlert").fadeIn();
									$("#failAlert").fadeOut(3000);
								});
							};
							
							var initData = function(data){
								 $scope.model.data = data;
								 $scope.model.selected = $scope.model.data.name;
								 if($scope.model.data.journalHelper != null){
									 $scope.model.jour_curr = $scope.model.data.journalHelper.name;
									 $scope.model.jour_prev = $scope.model.data.journalHelper.name;
								 }
								 if($scope.model.data.controlHelper != null){
									 $scope.model.cntl_curr = $scope.model.data.controlHelper.name;
									 $scope.model.cntl_prev = $scope.model.data.controlHelper.name;
								 }
							};
							
							var deleteRuleService = function(ruleId){
								ruleService.deleteRule(ruleId).then(
									function(response){
										$("#deleteAlert").fadeIn();
										$("#deleteAlert").fadeOut(3000);
										reset();							
									}
								).catch(function(error){
									console.log(error);
									$("#failAlert").fadeIn();
									$("#failAlert").fadeOut(3000);
								});
							};

							var loadRule = function(name) {
								ruleService.getRule(name).then(
										function(response) {											
											initData(response.data);
										}).catch(function(error){
											console.log(error);
											$("#failAlert").fadeIn();
											$("#failAlert").fadeOut(3000);
										});
							};
							
							var loadConditions = function() {
								ruleService
										.getConditions()
										.then(
												function(response) {
													$scope.model.conditions = response.data;
												}).catch(function(error){
													console.log(error);
													$("#failAlert").fadeIn();
													$("#failAlert").fadeOut(3000);
												});
							};

							var loadRuleNames = function() {
								ruleService
										.getRulesName()
										.then(
												function(response) {
													$scope.model.rulesname = response.data;
												}).catch(function(error){
													console.log(error);
													$("#failAlert").fadeIn();
													$("#failAlert").fadeOut(3000);
												});
							};

							var loadHelper = function() {
								ruleService
										.getHelpers()
										.then(
												function(response) {
													$scope.model.helpers = response.data;
												}).catch(function(error){
													console.log(error);
													$("#failAlert").fadeIn();
													$("#failAlert").fadeOut(3000);
												});
							};
							
							var setSelection = function(selection){
								$scope.model.selected = selection;
							};
							
							var reset = function(){
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
								loadConditions();
								loadRuleNames();
								loadHelper();
							};

							$scope.changeRole = function(){
								var curr = $scope.model.user;
								var types = $scope.model.types;
								for(i = 0; i < types.length; i++){
									if(types[i] != curr){
										$scope.model.user = types[i];
									}
								};
								console.log('role changed');
							};
				
							$scope.saveFields = function() {
								var rule = $scope.model.data;
								save(rule);
								console.log('save field end');
							};

							$scope.changeRule = function() {
								console.log('change rule method');
								if ($scope.model.selected != null) {
									loadRule($scope.model.selected);
								}
							};

							$scope.createRule = function() {								
								console.log('create rule method');
								if ($scope.model.selected != null) {
									$("#unsavedChanges").modal();
								}
								$scope.model.data = {};
							};

							$scope.saveRule = function() {
								console.log('save method called');
								var data = $scope.model.data;
								if($scope.model.data.name != null){
									saveRuleService(data);
								}
							};

							$scope.deleteRule = function() {								
								if ($scope.model.selected != null) {
									$("#deleteConfirm").modal();
								}
							};

							$scope.downloadRule = function(){
								console.log('download rule method');
								if ($scope.model.data.name != null) {
									downloadRuleService($scope.model.selected);
								};
							};
							
							$scope.confirmedDelete = function() {
								console.log('delete rule method');
								if ($scope.model.selected != null) {
									deleteRuleService($scope.model.selected);
								};
							};

							$scope.confirmedCreate = function() {
								console.log('create rule method');
								if ($scope.model.selected != null) {
									$scope.model.selected = null;
									$scope.model.data = {};
								}
							};

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
							};

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
								if (type == 'journal') {
									console.log('selection ' + $scope.model.jour_curr);
									if ($scope.model.jour_curr == $scope.model.jour_prev) {
										console.log('set helper matched');
										return;
									}
									for (i = 0; i < helpers.length; i++) {
										if ($scope.model.jour_curr == helpers[i].name) {
											$scope.model.jour_prev = $scope.model.jour_curr;
											$scope.model.data.journalHelper = JSON.parse(JSON.stringify(helpers[i]));
										}
									}
									console.log('set helper end');
								}
								if (type == 'control') {
									console.log('selection '+ $scope.model.cntl_curr);
									if ($scope.model.cntl_curr == $scope.model.cntl_prev) {
										console.log('set helper matched');
										return;
									}
									for (i = 0; i < helpers.length; i++) {
										if ($scope.model.cntl_curr == helpers[i].name) {
											$scope.model.cntl_prev = $scope.model.cntl_curr;
											$scope.model.data.controlHelper = JSON.parse(JSON.stringify(helpers[i]));
										}
									}
									console.log('set helper end');
								}
							};

							function toggleIcon(e) {
								$(e.target).prev('.panel-heading').find(
										".up-down").toggleClass(
										'glyphicon-upload glyphicon-download');
							}

							$('.panel-group').on('hidden.bs.collapse',
									toggleIcon);

							$('.panel-group').on('shown.bs.collapse',
									toggleIcon);

							$(document).ready(function() {
								$('[data-toggle="tooltip"]').tooltip();
							});
							
							loadConditions();
							loadRuleNames();
							loadHelper();

						} ]);