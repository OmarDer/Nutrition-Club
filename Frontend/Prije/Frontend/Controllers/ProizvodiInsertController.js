(function(){
	
	var app = angular.module("SitAndFit");

	var ProizvodiInsertController = function($scope,$http){

		$scope.naziv = "";
        $scope.opis="";
        $scope.autorId="";
        $scope.aktivan="1";
        $scope.slika="";
        $scope.cijena="";
        
        $scope.insertFun=function(){
            
        var data = {
            naziv_proizvoda: $scope.naziv,
            opis_proizvoda:$scope.opis,
            aktivan: $scope.aktivan,
            autor_ID: $scope.autorId,
            slika: $scope.slika,
            cijena: $scope.cijena
        };
            
        console.log(sessionStorage.user);
        console.log($http.defaults.headers.common['Authorization']);
            
         $http.post('http://localhost:8083/proizvodi',data)
             .then(
         function(response){
             $scope.uneseni=data;
             console.log($scope);
             alert('Ispravno');
         },
        function(response){
                alert('Neispravno');
        });
	};
        }
    
	app.controller('ProizvodiInsertController', ['$scope','$http', ProizvodiInsertController]);

}());