(function(){
	
	var app = angular.module("SitAndFit", ["ngRoute","angularFileUpload"]);

    vijestURL = "http://localhost:8088/";
    korisnikURL = "http://localhost:8084/";
    proizvodURL = "http://localhost:8083/";
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
            .when("/uploadimage",{

                templateUrl:'Views/upload.html',
                controller:'uploadController'
            })
            .when("/upload",{
                templateUrl:'Views/imageupload.html',
                controller:'UploadController'
            })
            .when("/verification/:str",{
                templateUrl:'Views/submitmail.html',
                controller:'SubmitController'
            })
            .when("/vijesti",{
                templateUrl:'Views/News.html',
                controller:'NewsController'
            })
            .when("/vijesti/details",{
                templateUrl:'Views/NewsDetails.html',
                controller:'NewsDetailsController'
            })
            .when("/vijesti/details/edit",{
                templateUrl:'Views/NewsDetailsEdit.html',
                controller:'NewsDetailsEditController'
            })
            .when("/vijesti/new",{
                templateUrl:'Views/NewsCreate.html',
                controller:'NewsCreateController'
            })
            .when("/korisnik",{
                templateUrl:'Views/Korisnik.html',
                controller:'KorisnikController'
            })
            .when("/proizvodi",{
                templateUrl:'Views/products.html',
                controller:'ProductsController'
            })
            .when("/admin",{
                templateUrl:'Views/adminpanel.html',
                controller:'AdminPanelController'
            })
            .when("/korisnici",{
                templateUrl:'Views/Korisnici.html',
                controller:'KorisniciController'
            })
			.otherwise({redirectTo: "/error"});


		/*$httpProvider.defaults.headers.common = {};
		$httpProvider.defaults.headers.post = {};
		$httpProvider.defaults.headers.get = {};
		$httpProvider.defaults.headers.put = {};
		$httpProvider.defaults.headers.patch = {};*/
	});

	var AppController = function($scope){

<<<<<<< HEAD
		$scope.loggedInUser = "Gost";

		if(sessionStorage.user == null) {
            $scope.loggedInUser = "Gost";
=======
		$scope.loggedInUser = "Guest";

		if(sessionStorage.user == null) {
            $scope.loggedInUser = "Guest";
>>>>>>> 71a37a8752f8d158c1c66bab1ac9d6331530dce2
            console.log(sessionStorage.user);
            } else {
            if (sessionStorage.loggedIn === 'true') {
                console.log(sessionStorage.loggedIn);
                $scope.user = JSON.parse(sessionStorage.user);
                $scope.loggedInUser = $scope.user.ime + " " + $scope.user.prezime;
                console.log($scope.loggedInUser);
            }else {
<<<<<<< HEAD
                $scope.loggedInUser = "Gost";
=======
                $scope.loggedInUser = "Guest";
>>>>>>> 71a37a8752f8d158c1c66bab1ac9d6331530dce2
            }
		 }

	};

	app.controller("AppController", ["$scope", AppController]);


}());