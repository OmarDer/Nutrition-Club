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

			vm.user = JSON.parse(sessionStorage.user);
=======
			vm.user = JSON.parse(sessionStorage.user);
            vm.loggedInUser = vm.user.ime + " " + vm.user.prezime;
>>>>>>> 0ae38b46d6a309cd5ad243bb38a0a46c3e76b174

		}
		else {
            vm.logged = false;
        }

<<<<<<< HEAD

=======
        
        vm.isAdmin = false;
        
        if(vm.user != null && vm.user.rola.nazivRole == "ROLE_ADMIN")
            vm.isAdmin = true;
>>>>>>> 0ae38b46d6a309cd5ad243bb38a0a46c3e76b174

        vm.profile = function () {
            sessionStorage.korisnikID = vm.user.id;
            $location.path('/korisnik');
        };

<<<<<<< HEAD
=======

>>>>>>> 0ae38b46d6a309cd5ad243bb38a0a46c3e76b174
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