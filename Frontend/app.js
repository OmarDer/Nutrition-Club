(function(){
	
	var app = angular.module("SitAndFit", ["ngRoute", "angularFileUpload"]);
    
    user={
        id: 0,
        username: "",
        rola: ""
    };

	app.config(function($routeProvider, $httpProvider){

		$routeProvider
			.when("/", {
				templateUrl: 'index.html',
				controller: 'AppController'

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
            .when("/uploadimage",{
=======

            templateUrl:'Views/upload.html',
            controller:'uploadController'
            })
            .when("/upload",{
>>>>>>> 88dbeb092afdda8622791ab866151592b848a4e2
            templateUrl:'Views/imageupload.html',
            controller:'UploadController'
            })
            .when("/verification/:str",{
            templateUrl:'Views/submitmail.html',
            controller:'SubmitController'
            })
        

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