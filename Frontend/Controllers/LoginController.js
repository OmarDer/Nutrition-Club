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

                    //$http.defaults.headers.common['Content-Type']='application/json';
					var url = 'http://localhost:8084/login';
				    var data = {username: username, password: password};
				    $http({method:"POST", url: url,data:data,headers:{'Content-Type':" application/json"}}).then(function(response){

				    	if(response.status == 200){
				    		
				    		$http.defaults.headers.common['Authorization'] = response.data;
                            console.log(response);
				    		$scope.loginMsg = "";

				    		sessionStorage.loggedIn = true;
				    		sessionStorage.authentication_token = response.data;
                            alert('Uspjesan login');
                        
				    		//$http.get('http://localhost:8084/korisnici').then(function(response){


				    		$http.get('http://localhost:8084/korisnici/' + username).then(function(response){
				    			user = {
                                    id: response.data.korisnik.id,
                                    username: response.data.korisnik.userName,
                                    rola: response.data.korisnik.rola.nazivRole
                                    
                                }
                               

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