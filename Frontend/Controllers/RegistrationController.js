(function(){
	
	var app = angular.module("SitAndFit");

<<<<<<< HEAD
	var RegistrationController = function($scope,$http,$location){
=======
	var RegistrationController = function($scope, $http){
>>>>>>> 71a37a8752f8d158c1c66bab1ac9d6331530dce2

		$scope.ime = "";
        $scope.prezime="";
        $scope.adresa="";
        $scope.broj="";
        $scope.email="";
        $scope.username="";
        $scope.password="";
        $scope.repPassword="";
        
        $scope.registrationFun=function(){
            
<<<<<<< HEAD
            var url = 'http://localhost:8084/korisnici/registriraj';
            var data = {  
=======
            
            var noviKorisnik = {
                
>>>>>>> 71a37a8752f8d158c1c66bab1ac9d6331530dce2
                ime: $scope.ime,
                prezime: $scope.prezime,
                adresaPrebivalista: $scope.adresa,
                kontaktTelefon: $scope.broj,
<<<<<<< HEAD
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
=======
                userName: $scope.username,
                password: $scope.password,
                email: $scope.email,
                datumRegistracije: "2017-06-13"
            };
            
            $http.post("http://localhost:8084/korisnici/registriraj", noviKorisnik).then(function(response){
                
                
                var odgovor = response.data;
                
                if (odgovor.status == "Success"){
                    alert("Uspjesna registracija, molimo sačekajte na odobrenje od administratora!");
                }
                else{
                    alert(odgovor.msg);
                }
                
            });
>>>>>>> 71a37a8752f8d158c1c66bab1ac9d6331530dce2
            
        }
        

	};

<<<<<<< HEAD
	app.controller('RegistrationController', ['$scope','$http','$location', RegistrationController]);
=======
	app.controller('RegistrationController', ['$scope', '$http', RegistrationController]);
>>>>>>> 71a37a8752f8d158c1c66bab1ac9d6331530dce2

}());