(function(){
	
	var app = angular.module("SitAndFit");

	var RegistrationController = function($scope,$http){

		$scope.ime = "";
        $scope.prezime="";
        $scope.adresa="";
        $scope.broj="";
        $scope.email="";
        $scope.username="";
        $scope.password="";
        $scope.repPassword="";
        $scope.ispravanPassword="Ispravan";
        
        $scope.registrationFun=function(){
            
            var url='http://localhost:8084/korisnici/registration';
            var data={
                ime: $scope.ime,
                prezime: $scope.prezime,
                adresa: $scope.adresa,
                broj: $scope.broj,
                email: $scope.email,
                username: $scope.username,
                password: $scope.password
                
            }
             $http.post(url, data).then(
             function(response){
                 alert('Vaša registracija je uspješna, administrator će najkasnije u naredna 24 sata poslati verifikacijski mail!');
             },
                 function(){
                     alert('Registracija nije uspješna!');
                 }
             )
            
            
        }
        
        $scope.provjeriPassword()
        {
            if($scope.password!==$scope.repPassword) return false;
            return true;      
        }

	};

	app.controller('RegistrationController', ['$scope','http', RegistrationController]);

}());