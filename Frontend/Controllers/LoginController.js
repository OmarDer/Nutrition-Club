(function(){
	
	var app = angular.module("SitAndFit");

	var LoginController = function($scope, $http, $location){

		$scope.loggedIn = false;

		if(sessionStorage.loggedIn){

		    $scope.authentication_token = sessionStorage.authentication_token;
		    $scope.loggedIn = sessionStorage.loggedIn;

		}


		//if($scope.loggedIn){
		//	$location.path("/");
		//}

		$scope.login = function(username, password){

					var url = 'http://localhost:8084/login';
				    var data = {username: username, password: password};
				    $http.post(url, data).then(function(response){

				    	if(response.status == 200){
				    		
				    		$http.defaults.headers.common['Authorization'] = response.data;
				    		$scope.loginMsg = "";

				    		sessionStorage.loggedIn = true;
				    		sessionStorage.authentication_token = response.data;

				    		$http.get('http://localhost:8084/korisnici/' + username).then(function(response){

				    			sessionStorage.user = response.data.korisnik;

				    		});
				    	}

				    	
				    }, function(response){

				    	if(response.status == 401){
				    		$scope.loginMsg = response.data.message;
				    		$scope.authentication_token = null;
				    	}

				    });

					};

	}

	app.controller('LoginController', ['$scope', '$http','$location', LoginController]);

}());