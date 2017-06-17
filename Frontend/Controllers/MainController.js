(function(){
	
	var app = angular.module("SitAndFit");

	var MainController = function($scope,$location,$window){
		vm = $scope;
		vm.logged = false;
		vm.user = null;
		console.log(sessionStorage.loggedIn);
		if(sessionStorage.loggedIn === 'true'){
			vm.logged = true;
<<<<<<< HEAD
            
=======
			vm.user = JSON.parse(sessionStorage.user);
>>>>>>> 71a37a8752f8d158c1c66bab1ac9d6331530dce2
		}
		else {
            vm.logged = false;
        }
<<<<<<< HEAD
=======

        vm.profile = function () {
            sessionStorage.korisnikID = vm.user.id;
            $location.path('/korisnik');
        };

>>>>>>> 71a37a8752f8d158c1c66bab1ac9d6331530dce2
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

	app.directive('navbar',function () {
		return{
			templateUrl:navbar.html
		};
    })
}());