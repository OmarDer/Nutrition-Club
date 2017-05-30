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
            .when("/registration",{
            templateUrl:'Views/userregistration.html',
            controller: 'RegistrationController' 
            
            })
			.otherwise({redirectTo: "/error"});


		$httpProvider.defaults.headers.common = {};
		$httpProvider.defaults.headers.post = {};
		$httpProvider.defaults.headers.get = {};
		$httpProvider.defaults.headers.put = {};
		$httpProvider.defaults.headers.patch = {};
	});

	var AppController = function($scope){

		$scope.loggedInUser = "Gost";

		if(sessionStorage.loggedIn){

			$scope.user = JSON.parse(sessionStorage.user);
			$scope.loggedInUser = $scope.user.ime + " " + $scope.user.prezime;

		}

	};

	app.controller("AppController", ["$scope", AppController]);


}());