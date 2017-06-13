(function(){

    var app = angular.module("SitAndFit");

    var KorisnikController = function($scope,$location,$window){
        vm = $scope;
        vm.logged = false;
        vm.korisnik=null;
        console.log(sessionStorage.loggedIn);
        if(sessionStorage.loggedIn){
            vm.logged = true;
        }
        else{
            vm.logged=false;
        }
        var url=korisnikURL+'korisnik/'+sessionStorage.korisnikID;
        $http.get(url).then(function successCallback(response) {
            vm.korisnik = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            console.log(response);
        });

        vm.logout = function(){
            sessionStorage.loggedIn=false;
            sessionStorage.authentication_token = null;
            sessionStorage.user = null;
            vm.logged=false;
            $window.location.reload();
        };
    };

    app.controller('KorisnikController', ['$scope','$location','$window', KorisnikController]);

}());