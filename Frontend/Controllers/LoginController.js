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

				    		sessionStorage.authentication_token = response.data;

				    		$http.get('http://localhost:8084/korisnici/' + username).then(function(response){
                                
                                sessionStorage.loggedIn = true;
				    			sessionStorage.user =JSON.stringify(response.data.korisnik);
                                //JSON.stringify(response.data.korisnik);
                                console.log(sessionStorage.user);
                                console.log("U ovoj sam sekciji");
                                $location.path('/');

				    		},
                            function(response){
                                if(response.status==403)
                            {
                                $scope.loginMsg='Korisnički račun nije aktivan';
                            }
                            
                            }
                            
                                                                                         );
				    	}

				    	
				    }, function(response){

				    	if(response.status == 401){
				    		$scope.loginMsg = response.data.message;
				    		$scope.authentication_token = null;
				    	}
                        else if(response.status==403)
                            {
                                $scope.loginMsg=response.message;
                            }

				    });

					};

	}

	app.controller('LoginController', ['$scope', '$http','$location', LoginController]);

}())

