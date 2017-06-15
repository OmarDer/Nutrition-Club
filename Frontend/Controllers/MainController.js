(function(){
	
	var app = angular.module("SitAndFit");

	var MainController = function($scope,$location,$window){
		vm = $scope;
		vm.logged = false;
		console.log(sessionStorage.loggedIn);
		if(sessionStorage.loggedIn === 'true'){
			vm.logged = true;
		}
		else {
            vm.logged = false;
        }
		$scope.logout = function(){
                sessionStorage.loggedIn=false;
                sessionStorage.authentication_token = null;
                sessionStorage.user = null;
				vm.logged=false;
            	$location.path('/');
            	$window.location.reload();
		};
    };

	app.controller('MainController', ['$scope','$location','$window', MainController]);

}());