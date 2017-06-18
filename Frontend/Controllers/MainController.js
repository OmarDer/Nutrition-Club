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

			vm.user = JSON.parse(sessionStorage.user);
            vm.loggedInUser = vm.user.ime + " " + vm.user.prezime;

		}
		else {
            vm.logged = false;
        }

   
        vm.isAdmin = false;
        
        if(vm.user != null && vm.user.rola.nazivRole == "ROLE_ADMIN")
            vm.isAdmin = true;


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
			//templateUrl:navbar.html
		};
    })*/
}());