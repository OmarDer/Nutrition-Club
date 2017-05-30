(function(){
	
	var app = angular.module("SitAndFit", ["ngRoute"]);

	app.config(function($routeProvider, $httpProvider){

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


		$httpProvider.defaults.headers.common = {};
		$httpProvider.defaults.headers.post = {};
		$httpProvider.defaults.headers.get = {};
		$httpProvider.defaults.headers.put = {};
		$httpProvider.defaults.headers.patch = {};
	});

	var AppController = function($scope){

		$scope.authentication_token = null;

	};

	app.controller("AppController", ["$scope", AppController]);


}());