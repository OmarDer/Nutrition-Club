(function(){
	
	var app = angular.module("SitAndFit");

	var LoginController = function($scope, $http){

		$scope.login = function(username, password){

					var url = 'http://localhost:8084/login';
				    var data = {username: username, password: password};
				    $http.post(url, data).then(function(response){

				    	alert(response.data);

				    	$http({
						    method: 'GET',
						    url: 'http://localhost:8084/korisnici',
						    headers: {
						        'Authorization': response.data
						         //or
						         //'Authorization': 'Basic ' + 'token'
						    }
						}).then(function successCallback(response) {
						    alert(response.data)
						}, function errorCallback(response) {
						    if(response.status = 401){ // If you have set 401
						        
						    }
						});

				    });

					//alert(username + " " + password + " " + $scope.authentication_token);

					};

	}

	app.controller('LoginController', ['$scope', '$http', LoginController]);

}());