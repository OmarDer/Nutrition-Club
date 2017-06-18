(function(){

    var app = angular.module("SitAndFit");

    var ProductsController = function($scope,$location,$window,$http){
        vm = $scope;
        vm.logged = false;
        $scope.admin=false;
        vm.proizvodi=[];
        
        if(sessionStorage.user!=null){
            
        var userJson = JSON.parse(sessionStorage.user);
        
        
        if(userJson!=null && userJson.rola.nazivRole=="ROLE_ADMIN")
            {
                $scope.admin=true;
            }
            
        }
        if(sessionStorage.loggedIn==='true'){
            vm.logged = true;
        }
        else{
            vm.logged=false;
        }
        
        $scope.programi=[];
        var urlProgrami=proizvodURL+'programi';
        
        $http.get(urlProgrami).then(function(response){
            $scope.programi = response.data;   
        });
        
        vm.isAdmin = false;
        
        if(vm.user != null && vm.user.rola.nazivRole == "ROLE_ADMIN")
           {vm.isAdmin = true;
            vm.loggedInUser = vm.user.ime + " " + vm.user.prezime;}
        
        /*var url=proizvodURL+'proizvodi';
            
        $http.get(url).then(function successCallback(response) {
            vm.proizvodi = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            console.log(response);
        });
        */
        $http.get(proizvodURL+'proizvodi').then(
            function(response){
                
                $scope.proizvodi=response.data;
                
            });
        
        $scope.proizvodi="";
        
        $scope.filtriraj=function(idPrograma){
            
            var url=proizvodURL + 'programi/' + idPrograma;
            $http.get(url).then(
            function(response){
             
                $scope.proizvodi=response.data.program.proizvodiList;
                console.log(response.data.program.proizvodiList);
                
            });            
        }
        
        $scope.prikaziSve=function(){
            
            $http.get(proizvodURL+'proizvodi').then(
            function(response){
                
                $scope.proizvodi=response.data;
                
            }
            );
            
            
        }
        vm.productDetails = function () {
            console.log('redirekt na detalje');
        }
        
        vm.logout = function(){
            sessionStorage.loggedIn=false;
            sessionStorage.authentication_token = null;
            sessionStorage.user = null;
            vm.logged=false;
            $window.location.reload();
        };
    };

    app.controller('ProductsController', ['$scope','$location','$window','$http', ProductsController]);

}());