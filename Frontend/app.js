(function(){
	
	var app = angular.module("SitAndFit", ["ngRoute"]);

	app.config(function($routeProvider){

		$routeProvider
			.when("/", {
				templateUrl: 'Views/main.html',
				controller: 'MainController'

			})
			.when("/login", {
				templateUrl: 'Views/login.html',
				controller: 'LoginController'

			})
			.otherwise({redirectTo: "/error"});


	});

	var AppController = function($scope){

		$scope.authentication_token = null;

	};

	app.controller("AppController", ["$scope", AppController]);


}());