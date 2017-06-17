(function(){

    var app = angular.module("SitAndFit");

    var NewsDetailsController = function($scope,$http,$location,$window){
        vm = $scope;
        vm.logged = false;
        vm.isAdmin = false;
        vm.isAutor = false;
        console.log(sessionStorage.user);
        if(sessionStorage.loggedIn==='true'){
            vm.logged = true;
            vm.authentication_token = sessionStorage.authentication_token;
            vm.loggedIn = sessionStorage.loggedIn;
            vm.korisnik =  JSON.parse(sessionStorage.user);
            if(vm.korisnik.rola.nazivRole =='ROLE_ADMIN'){
                vm.isAdmin=true;
            }
        }
        else{
            vm.logged=false;
        }
        vm.singleNews=null;
        var url=vijestURL+'vijesti/'+sessionStorage.newsID;
        $http.get(url).then(function successCallback(response) {
            vm.singleNews = JSON.parse(JSON.stringify(response.data.vijest));
            console.log(vm.singleNews);
            if(vm.logged){
            if(vm.singleNews.autorID == vm.korisnik.id) {
                vm.isAutor = true;
            }
            }
        }, function errorCallback(response) {
            console.log(response);
        });
        
        vm.delNews = function (newsID) {
            var delUrl=vijestURL+'vijesti/'+sessionStorage.newsID;
            $http.delete(delUrl).then(function successCallback(response) {
                console.log(response);
                $location.path('/vijesti');
            }, function errorCallback(response) {
                console.log(response);
            });
        };

        vm.editNews = function (newsID) {
          $location.path('/vijesti/details/edit');
        };

        vm.nadjiKorisnika = function(autorID){
            sessionStorage.korisnikID = autorID;
            $location.path('/korisnik');
        };

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

    app.controller('NewsDetailsController', ['$scope','$http','$location','$window', NewsDetailsController]);

}());