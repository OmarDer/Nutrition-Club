(function(){

    var app = angular.module("SitAndFit");

    var KorisnikController = function($scope,$location,$window,$http){
        vm = $scope;
        vm.logged = false;
        vm.korisnik=null;
        vm.korisnikStats=null;
        console.log(sessionStorage.loggedIn);
        if(sessionStorage.loggedIn==='true'){
            vm.logged = true;
        }
        else{
            vm.logged=false;
        }
        var url=korisnikURL+'korisnici/'+sessionStorage.korisnikID;
        $http.get(url).then(function successCallback(response) {
            vm.korisnik = response.data.korisnik;
            console.log(response.data);
        }, function errorCallback(response) {
            console.log(response);
        });

        $http.get(url+'/stanjakorisnika').then(function successCallback(response) {
            vm.korisnikStats = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            console.log(response);
        });

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

    app.controller('KorisnikController', ['$scope','$location','$window','$http', KorisnikController]);

}());