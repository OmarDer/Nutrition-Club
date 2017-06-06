(function(){
	
	var app = angular.module("SitAndFit", ["ngRoute"]);
    
    user={
        id: 0,
        username: "",
        rola: ""
    };

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
            .when("/programs/insert",{
            templateUrl:'Views/insertprograms.html',
            controller:'ProgramiInsertController'
            })
            .when("/proizvodi/insert",{
            templateUrl:'Views/insertproducts.html',
            controller:'ProizvodiInsertController'
            })
            .when("/upload",{
<<<<<<< HEAD
            templateUrl:'Views/upload.html',
            controller:'uploadController'
        })
=======
            templateUrl:'Views/imageupload.html',
            controller:'UploadController'
            })
        
>>>>>>> 1266ab22bf985d22ca31caf115b60ce44c9cd110
			.otherwise({redirectTo: "/error"});


		/*$httpProvider.defaults.headers.common = {};
		$httpProvider.defaults.headers.post = {};
		$httpProvider.defaults.headers.get = {};
		$httpProvider.defaults.headers.put = {};
		$httpProvider.defaults.headers.patch = {};*/
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