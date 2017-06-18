(function(){
	
	var app = angular.module("SitAndFit");

	var MainController = function($scope,$location,$window){
		vm = $scope;
		vm.logged = false;
		vm.user = null;
		console.log(sessionStorage.loggedIn);
		if(sessionStorage.loggedIn === 'true'){
			vm.logged = true;

			vm.user = JSON.parse(sessionStorage.user);

		}
		else {
            vm.logged = false;
        }



        vm.profile = function () {
            sessionStorage.korisnikID = vm.user.id;
            $location.path('/korisnik');
        };

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

	/*app.directive('navbar',function () {
		return{
			templateUrl:navbar.html
		};
    })*/
}());