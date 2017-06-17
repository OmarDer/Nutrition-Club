(function(){
	
	var app = angular.module("SitAndFit");

	var ProgramiInsertController = function($scope,$http){

		$scope.naziv = "";
        $scope.opis="";
        $scope.autorId="";
        $scope.aktivan="1";
        $scope.proizvodi=[];
        $scope.proizvod="";
        $scope.errorMsg="";
        $scope.dodajMsg="";
        
        var url='http://localhost:8083/proizvodi';
        var dictionary={};
        
        //Dobaviti sve proizvode da bi se dodali u program.
        $http.get(url).then(
        function(response){
            var lista=[];
            var size=response.data.length;
            lista=response.data;
                
            for(var i = 0; i<size;i++)
            {
                var temp={
                    key:lista[i].naziv_proizvoda,
                    value:lista[i].proizvod_ID
                } 
                dictionary[lista[i].naziv_proizvoda] = lista[i].proizvod_ID;
                
                $scope.proizvodi.push(lista[i].naziv_proizvoda);
            }
        })
        
        var programId="";
        
        $scope.insertFun=function(){
          
        var data = {
            naziv_programa: $scope.naziv,
            opis_programa:$scope.opis,
            aktivan: $scope.aktivan
        };
<<<<<<< HEAD
               
=======
            
        console.log(sessionStorage.user);
        console.log($http.defaults.headers.common['Authorization']);
            
>>>>>>> 71a37a8752f8d158c1c66bab1ac9d6331530dce2
         $http({ method: "post", url: 'http://localhost:8083/programi', data: data })
             .then(
         function(response){
             programId=response.data.program.programID;
             $scope.errorMsg="USPJEŠNO DODAN PROGRAM!";
         },
        function(response){
                $scope.errorMsg=response.poruka;
        });
        };
        
        
        $scope.dodajFun=function(){
            
        var data={}; 
        $http.post('http://localhost:8083/programi/'+programId+'/proizvod/'+dictionary[$scope.proizvod],data).then(
        function(response){
            $scope.dodajMsg="Uspješno dodan proizvod!";
        },
            function(response){
                $scope.dodajMsg=response.poruka;
        });
          
        };
        
        
        }
    
	app.controller('ProgramiInsertController', ['$scope','$http', ProgramiInsertController]);

}());