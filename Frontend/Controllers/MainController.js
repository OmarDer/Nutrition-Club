(function(){
	
	var app = angular.module("SitAndFit");

	var MainController = function($scope,$location,$window){
		vm = $scope;
		vm.logged = false;
		console.log(sessionStorage.loggedIn);
		if(sessionStorage.loggedIn){
			vm.logged = true;
		}
		else{
			vm.logged=false;
		}
		vm.logout = function(){
                sessionStorage.loggedIn=false;
                sessionStorage.authentication_token = null;
                sessionStorage.user = null;
				vm.logged=false;
				$window.location.reload();
		};
    };

	app.controller('MainController', ['$scope','$location','$window', MainController]);

}());