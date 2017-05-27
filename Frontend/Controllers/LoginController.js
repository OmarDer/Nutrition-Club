(function(){
	
	var app = angular.module("SitAndFit");

	var LoginController = function($scope){

		$scope.login = function(){

					alert($scope.username + " " + $scope.password + " " + $scope.authentication_token);

					};

	}

	app.controller('LoginController', ['$scope', LoginController]);

}());