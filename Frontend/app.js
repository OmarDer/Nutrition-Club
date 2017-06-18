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
            .when("/programs/list",{
                templateUrl:'Views/listaPrograma.html',
                controller:'ProgramiListController'
            })
            .when("/programs/update",{
                templateUrl:'Views/programiUpdate.html',
                controller:'ProgramiUpdateController'
            })
            .when("/proizvodi/update",{
                templateUrl:'Views/proizvodiUpdate.html',
                controller:'ProizvodiUpdateController'
            })
            .when("/proizvodi/list",{
                templateUrl:'Views/proizvodiList.html',
                controller:'ProizvodiListController'
            })
			.otherwise({redirectTo: "/error"});


		/*$httpProvider.defaults.headers.common = {};
		$httpProvider.defaults.headers.post = {};
		$httpProvider.defaults.headers.get = {};
		$httpProvider.defaults.headers.put = {};
		$httpProvider.defaults.headers.patch = {};*/
	});

	var AppController = function($scope){


		$scope.loggedInUser = "Guest";

		if(sessionStorage.user == null) {
            //$scope.loggedInUser = "Gost";

		$scope.loggedInUser = "Guest";

		if(sessionStorage.user == null) {
            $scope.loggedInUser = "Guest";

            console.log(sessionStorage.user);
            } else {
            if (sessionStorage.loggedIn === 'true') {
                console.log(sessionStorage.loggedIn);
                $scope.user = JSON.parse(sessionStorage.user);
                $scope.loggedInUser = $scope.user.ime + " " + $scope.user.prezime;
                console.log($scope.loggedInUser);
            }else {

                //$scope.loggedInUser = "Gost";

                $scope.loggedInUser = "Guest";

            }
		 }
        }
	};

	app.controller("AppController", ["$scope", AppController]);


}())