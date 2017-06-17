(function(){
	
	var app = angular.module("SitAndFit");

	var RegistrationController = function($scope,$http,$location){

		$scope.ime = "";
        $scope.prezime="";
        $scope.adresa="";
        $scope.broj="";
        $scope.email="";
        $scope.username="";
        $scope.password="";
        $scope.repPassword="";
        
        $scope.registrationFun=function(){
            
            var url = 'http://localhost:8084/korisnici/registriraj';
            var data = {  
                ime: $scope.ime,
                prezime: $scope.prezime,
                adresaPrebivalista: $scope.adresa,
                kontaktTelefon: $scope.broj,
                email: $scope.email,
                userName: $scope.username,
                password: $scope.password
            }
            
            $http.post(url,data).then(
            function(response){
                if(response.data.status==='Error'){
                    var poruka=response.data.msg;
                    alert(poruka);      
                }
                else 
                    {
                        alert('Prijava je uspjesna, administrator će u narednih 24 sata poslati verifikacijski mail!');
                        $location.path('/');
                    }
            },
            function(response){
                
                alert('Prijava nije uspjesna, pokusajte ponovno!');
                
            }
            
            )
        $scope.ime = "";
        $scope.prezime="";
        $scope.adresa="";
        $scope.broj="";
        $scope.email="";
        $scope.username="";
        $scope.password="";
        $scope.repPassword="";
            
        }
        

	};

	app.controller('RegistrationController', ['$scope','$http','$location', RegistrationController]);

}());