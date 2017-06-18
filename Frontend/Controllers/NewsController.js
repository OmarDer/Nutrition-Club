(function(){

    var app = angular.module("SitAndFit");

    var NewsController = function($scope,$http,$location,$window){
        vm = $scope;
        vm.allNews=[];
        vm.logged = false;
        if(sessionStorage.loggedIn==='true'){
            vm.logged = true;
            vm.authentication_token = sessionStorage.authentication_token;
            vm.loggedIn = sessionStorage.loggedIn;
            vm.user = JSON.parse(sessionStorage.user);
            $http.defaults.headers.common['Authorization'] =vm.authentication_token;
        }else {
            vm.logged = false;
        }
         vm.isAdmin = false;
        
        if(vm.user != null && vm.user.rola.nazivRole === "ROLE_ADMIN"){
            vm.isAdmin = true;
            vm.loggedInUser = vm.user.ime + " " + vm.user.prezime;
        }
        
        vm.date = new Date();
        var url=vijestURL+'vijesti';
        $http.get(url).then(function successCallback(response) {
                vm.allNews = response.data;
                console.log(response.data);
        }, function errorCallback(response) {
                console.log(response);
        });

        vm.pickNews = function(newsID){
            sessionStorage.newsID=newsID;
            $location.path('/vijesti/details')
        };

        vm.pickAuthor = function(authorID){
            sessionStorage.korisnikID=authorID;
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

    app.controller('NewsController', ['$scope', '$http','$location','$window', NewsController]);

}());