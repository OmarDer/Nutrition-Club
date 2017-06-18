(function(){
	
	var app = angular.module("SitAndFit");

	var ProizvodiListController = function($scope, $http, $location, $routeParams){
      
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
        
        $scope.proizvodi=[];
        
        var url='http://localhost:8083/proizvodi';
        $http.get(url).then(
        function(response){
            $scope.proizvodi=response.data;
        },
        function(response){
            
        });
        
        $scope.deleteProizvod=function(id){
            
            $http.delete('http://localhost:8083/proizvodi/'+id).then(
            function(response){
                $http.get(url).then(
                    function(response){
                        $scope.proizvodi=response.data;
                    },
                    function(response){
                    });
                
            },
            function(response){
                 $scope.deleteMsg=response.poruka;
            });
               
        }
        $scope.updateProizvod=function(id){
            sessionStorage.proizId=id;
            $location.path('/proizvodi/update');
        }
        
        
        vm.logout = function(){
            sessionStorage.loggedIn=false;
            sessionStorage.authentication_token = null;
            sessionStorage.user = null;
            vm.logged=false;
            $location.path('/');
            $window.location.reload();
        };

	}

	app.controller('ProizvodiListController', ['$scope', '$http','$location', '$routeParams',ProizvodiListController]);

}())