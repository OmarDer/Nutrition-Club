(function(){
	
	var app = angular.module("SitAndFit");

	var ProgramiListController = function($scope, $http, $location, $routeParams, $window){
      
        
        vm = $scope;
        vm.logged = false;
        vm.proizvodi=[];
        
          vm.isAdmin = false;
        
        if(vm.user != null && vm.user.rola.nazivRole == "ROLE_ADMIN")
           {vm.isAdmin = true;
            vm.loggedInUser = vm.user.ime + " " + vm.user.prezime;}
        
        var userJson = JSON.parse(sessionStorage.user);
        $scope.admin=false;
        console.log(sessionStorage.user.id);
        if(userJson!=null && userJson.rola.nazivRole=="ROLE_ADMIN")
        {
            $scope.admin=true;
        }
    
        if(sessionStorage.loggedIn==='true'){
            vm.logged = true;
        }
        else{
            vm.logged=false;
        }
        
        $scope.programi=[];
        $scope.deleteMsg="";
        
        var url='http://localhost:8083/programi';
        $http.get(url).then(
        function(response){
            $scope.programi=response.data;
            console.log($scope.programi);
        },
        function(response){
            
        });
        
        $scope.deleteProgram=function(programId){
            
            $http.delete('http://localhost:8083/programi/'+programId).then(
            function(response){
                $http.get(url).then(
                    function(response){
                        $scope.programi=response.data;
                        console.log($scope.programi);
                    },
                    function(response){
                    });
                
            },
            function(response){
                 $scope.deleteMsg=response.poruka;
            });
               
        }
        $scope.updateProgram=function(id){
            sessionStorage.progId=id;
            $location.path('/programs/update');
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

	app.controller('ProgramiListController', ['$scope', '$http','$location', '$routeParams','$window',ProgramiListController]);

}())