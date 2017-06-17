(function(){

    var app = angular.module("SitAndFit");

    var KorisniciController = function($scope,$location,$window,$http){
        vm = $scope;
        vm.logged = false;
        vm.korisnici=[];
        console.log(sessionStorage.loggedIn);
        if(sessionStorage.loggedIn==='true'){
            vm.logged = true;
        }
        else{
            vm.logged=false;
        }
        var url=korisnikURL+'korisnici/';
        $http.get(url).then(function successCallback(response) {
            vm.korisnici = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            console.log(response);
        });
        
        vm.delUser = function (userID) {
            $http.del(url+userID).then(function successCallback(response) {
                console.log(response.data);
            }, function errorCallback(response) {
                console.log(response);
            });
        }
        
        vm.accUser = function (userID) {

            $http.get(url+'odobri/'+userID).then(function successCallback(response) {
                console.log(response.data);
            }, function errorCallback(response) {
                console.log(response);
            });
        }
        
        vm.findUser = function (userID) {
            sessionStorage.korisnikID=userID;
            $location.path('/korisnik');
        }

        vm.profile = function () {
            sessionStorage.korisnikID = vm.user.id;
            $location.path('/korisnik');
        };

        vm.logout = function(){
            sessionStorage.loggedIn=false;
            sessionStorage.authentication_token = null;
            sessionStorage.user = null;
            vm.logged=false;
            $location.path('/');
            $window.location.reload();
        };
    };

    app.controller('KorisniciController', ['$scope','$location','$window','$http', KorisniciController]);

}());