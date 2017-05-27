(function(){
	
	var app = angular.module("SitAndFit");

	var MainController = function($scope){

		$scope.yourName = "Anonymous user";

	};

	app.controller('MainController', ['$scope', MainController]);

}());