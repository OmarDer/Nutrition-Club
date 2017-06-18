(function(){
	
	var app = angular.module("SitAndFit");
    
	var ProgramiUpdateController = function($scope,$http){
        
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
        
          vm.isAdmin = false;
        
        if(vm.user != null && vm.user.rola.nazivRole == "ROLE_ADMIN")
           {vm.isAdmin = true;
            vm.loggedInUser = vm.user.ime + " " + vm.user.prezime;}
        
        $scope.naziv = "";
        $scope.opis="";
        $scope.autorId="";
        $scope.aktivan="1";
        $scope.proizvodi=[];
        $scope.proizvod="";
        $scope.errorMsg="";
        $scope.dodajMsg="";
        $scope.proizvodiLista=[];
        
        $scope.id="";
        $http.get('http://localhost:8083/programi/'+sessionStorage.progId).then(
        function(response){
            
            $scope.naziv=response.data.program.naziv_programa;
            $scope.opis=response.data.program.opis_programa;
            $scope.proizvodi=response.data.program.proizvodiList;
            $scope.id=response.data.program.programID;
            
        });
        
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
                
                $scope.proizvodiLista.push(lista[i].naziv_proizvoda);
            }
        })
         
        //DODAJ NOVI PROIZVOD U PROGRAM
        $scope.dodajFun=function(){
        var data={}; 
        $http.post('http://localhost:8083/programi/'+$scope.id+'/proizvod/'+dictionary[$scope.proizvod],data).then(
        function(response){
            $scope.dodajMsg="Uspješno dodan proizvod!";
             $http.get('http://localhost:8083/programi/'+sessionStorage.progId).then(
                     
                function(response){
                    $scope.proizvodi=response.data.program.proizvodiList;}); 
            
        },
            function(response){
                $scope.dodajMsg=response.poruka;
        });
          
        };
        
        //UPDATE PROGRAMA
        $scope.updateFun=function(){
            
            var data={
                naziv_programa: $scope.naziv,
                opis_programa: $scope.opis
            };
            $http.put('http://localhost:8083/programi/'+$scope.id,data).then(
            function(response){
                $scope.errorMsg="Uspjesno azuriran program!";
            },
            function(response){
                alert("Greska prilikom azuriranja!");
            });
        }
        
        //OBRISI PROIZVOD IZ PROGRAMA
        $scope.delProizvodInProgram=function(id){
           
            $http.delete('http://localhost:8083/programi/'+$scope.id+'/proizvod/'+id).
            then(
            function(response){
                 $http.get('http://localhost:8083/programi/'+sessionStorage.progId).then(
                     
                function(response){
                    $scope.proizvodi=response.data.program.proizvodiList;});  
                
            },
            function(response){
                alert("Bezuspjesno");
            });
            
        }
    }
    
	app.controller('ProgramiUpdateController', ['$scope','$http', ProgramiUpdateController]);

}());