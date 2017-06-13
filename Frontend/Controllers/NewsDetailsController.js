(function(){

    var app = angular.module("SitAndFit");

    var NewsDetailsController = function($scope,$http,$location,$window){
        vm = $scope;
        vm.logged = false;
        vm.isAdmin = false;
        vm.isAutor = false;
        console.log(sessionStorage.user);
        vm.korisnik =  JSON.stringify(sessionStorage.user.rola);
        //console.log(vm.korisnik.rola);
        // if(sessionStorage.user.rola.nazivRole =='ROLE_ADMIN'){
        //     vm.isAdmin=true;
        // }
        vm.$log=sessionStorage.loggedIn;
        if(sessionStorage.loggedIn){
            vm.logged = true;
            vm.authentication_token = sessionStorage.authentication_token;
            vm.loggedIn = sessionStorage.loggedIn;
        }
        else{
            vm.logged=false;
        }
        vm.singleNews=null;
        var url=vijestURL+'vijesti/'+sessionStorage.newsID;
        $http.get(url).then(function successCallback(response) {
            vm.singleNews = JSON.parse(JSON.stringify(response.data.vijest));
            console.log(vm.singleNews);
            if(vm.singleNews.autorID == sessionStorage.user.id){
                vm.isAutor=true;
            }
        }, function errorCallback(response) {
            console.log(response);
        });
        
        vm.delNews = function (newsID) {
            var delUrl=vijestURL+'vijesti/'+sessionStorage.newsID;
            $http.delete(delUrl).then(function successCallback(response) {
                console.log(response);
                location.path('/vijesti');
            }, function errorCallback(response) {
                console.log(response);
            });
        };

        vm.editNews = function (newsID) {
          location.path('/vijesti/details/edit');
        };

        vm.logout = function(){
            sessionStorage.loggedIn=false;
            sessionStorage.authentication_token = null;
            sessionStorage.user = null;
            vm.logged=false;
            $window.location.reload();
        };
    };

    app.controller('NewsDetailsController', ['$scope','$http','$location','$window', NewsDetailsController]);

}());