(function(){
	
	var app = angular.module("SitAndFit");

	var RegistrationController = function($scope, $http){

		$scope.ime = "";
        $scope.prezime="";
        $scope.adresa="";
        $scope.broj="";
        $scope.email="";
        $scope.username="";
        $scope.password="";
        $scope.repPassword="";
        
        $scope.registrationFun=function(){
            
            
            var noviKorisnik = {
                
                ime: $scope.ime,
                prezime: $scope.prezime,
                adresaPrebivalista: $scope.adresa,
                kontaktTelefon: $scope.broj,
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
            
        }

	};

	app.controller('RegistrationController', ['$scope', '$http', RegistrationController]);

}());