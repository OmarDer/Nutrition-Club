(function(){
	
	var app = angular.module("SitAndFit");

	var LoginController = function($scope, $http){

		$scope.login = function(username, password){

					var url = 'http://localhost:8084/login';
				    var data = {username: username, password: password};
				    $http.post(url, data).then(function(response){
				    	alert(response.data);
				    });

					//alert(username + " " + password + " " + $scope.authentication_token);

					};

	}

	app.controller('LoginController', ['$scope', '$http', LoginController]);

}());