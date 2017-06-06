(function(){
	
	var app = angular.module("SitAndFit");

	var ProgramiInsertController = function($scope,$http){

		$scope.naziv = "";
        $scope.opis="";
        $scope.autorId="";
        $scope.aktivan="1";
        $scope.insertFun=function(){
          
        var data = {
            naziv_programa: $scope.naziv,
            opis_programa:$scope.opis,
            aktivan: $scope.aktivan
        };
            
        console.log(sessionStorage.user);
              
         $http({ method: "post", url: 'http://localhost:8083/programi', data: data })
             .then(
         function(response){
             console.log(response);
             alert('Ispravno');
         },
        function(response){
                alert('Neispravno');
        });
	};
        }
    
	app.controller('ProgramiInsertController', ['$scope','$http', ProgramiInsertController]);

}());