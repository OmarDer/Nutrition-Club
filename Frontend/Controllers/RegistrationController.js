(function(){
	
	var app = angular.module("SitAndFit");

<<<<<<< HEAD
=======

>>>>>>> 0ae38b46d6a309cd5ad243bb38a0a46c3e76b174
	var RegistrationController = function($scope,$http,$location){

	//var RegistrationController = function($scope, $http){

        vm = $scope;
        vm.logged = false;
        vm.proizvodi=[];
        var userJson = JSON.parse(sessionStorage.user);
       
        if(sessionStorage.loggedIn==='true'){
            vm.logged = true;
        }
        else{
            vm.logged=false;
        }
        
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

<<<<<<< HEAD
            
           // var noviKorisnik = {
                
=======
>>>>>>> 0ae38b46d6a309cd5ad243bb38a0a46c3e76b174
                ime: $scope.ime,
                prezime: $scope.prezime,
                adresaPrebivalista: $scope.adresa,
                kontaktTelefon: $scope.broj,
<<<<<<< HEAD

=======
>>>>>>> 0ae38b46d6a309cd5ad243bb38a0a46c3e76b174
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
                
            });
        
/*$scope.ime = "";
        $scope.prezime="";
        $scope.adresa="";
        $scope.broj="";
        $scope.email="";
        $scope.username="";
        $scope.password="";
        $scope.repPassword="";

<<<<<<< HEAD
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

=======
>>>>>>> 0ae38b46d6a309cd5ad243bb38a0a46c3e76b174
            
        }
        

	};
<<<<<<< HEAD
*/
        }
    };
	app.controller('RegistrationController', ['$scope','$http','$location', RegistrationController]);
=======

	app.controller('RegistrationController', ['$scope','$http','$location', RegistrationController]);

>>>>>>> 0ae38b46d6a309cd5ad243bb38a0a46c3e76b174

	//app.controller('RegistrationController', ['$scope', '$http', RegistrationController]);

}())