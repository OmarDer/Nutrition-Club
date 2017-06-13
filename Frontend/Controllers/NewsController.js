(function(){

    var app = angular.module("SitAndFit");

    var NewsController = function($scope,$http,$location,$window){
        vm = $scope;
        vm.allNews=[];
        vm.logged = false;
        vm.$log=sessionStorage.loggedIn;
        if(sessionStorage.loggedIn){
            vm.logged = true;
            vm.authentication_token = sessionStorage.authentication_token;
            vm.loggedIn = sessionStorage.loggedIn;
        }else {
            vm.logged = false;
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

        vm.logout = function(){
            sessionStorage.loggedIn=false;
            sessionStorage.authentication_token = null;
            sessionStorage.user = null;
            vm.logged=false;
            $window.location.reload();
        };
    };

    app.controller('NewsController', ['$scope', '$http','$location','$window', NewsController]);

}());