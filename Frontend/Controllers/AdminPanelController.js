(function(){
	
	var app = angular.module("SitAndFit");

	var AdminPanelController = function($scope, $http, $location){
        
        if(sessionStorage.user == null)
            $location.path("/login");
        
        var user = JSON.parse(sessionStorage.user);
        
        $scope.odobriRegistracijuKorisnika = function(id){
            
            
            $http.get("http://localhost:8084/korisnici/odobri/" + id).then(function(response){
                
                
                var odgovor = response.data;
                
                if(odgovor.status == "Success"){
                    
                    alert("Registracija uspjesno odobrena!");
                    
                    $http.get("http://localhost:8084/korisnici").then(function(response){    
                        $scope.korisnici = response.data;   
                    });
                    
                }
                else{
                    alert(odgovor.msg);
                }
                
            });
            
        };
        
        
        if (user.rola == null || user.rola.nazivRole != "ROLE_ADMIN")
            $location.path("/error");
        
        $scope.korisnici = null;
        
        $http.get("http://localhost:8084/korisnici").then(function(response){    
            $scope.korisnici = response.data;   
        });
         
        
        
    }

	app.controller('AdminPanelController', ['$scope', '$http','$location', AdminPanelController]);

}())