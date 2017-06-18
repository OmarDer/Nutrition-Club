(function(){

    var app = angular.module("SitAndFit");

    var NewsCreateController = function($scope,$location,$http){
        vm = $scope;
        vm.logged = false;
        vm.vijestTitle='';
        vm.vijestText='';
        vm.date=new Date();
        vm.active = 1;
        vm.tipvijesti=null;
        vm.kategorijavijesti=null;
        vm.tipovi = [];
        vm.kategorije = [];
        vm.user = null;
        if(sessionStorage.loggedIn==='true'){
            vm.logged = true;
            vm.authentication_token = sessionStorage.authentication_token;
            vm.loggedIn = sessionStorage.loggedIn;
            vm.user = JSON.parse(sessionStorage.user);
            $http.defaults.headers.common['Authorization'] =vm.authentication_token;
            vm.loggedInUser = vm.user.ime + " " + vm.user.prezime;
        }
        else{
            vm.logged=false;
        }
        var url = vijestURL+'vijesti';
        var url1 = vijestURL+'tipvijesti';
        var url2 = vijestURL+'kategorijevijesti';
        $http.get(url2).then(function successCallback(response) {
            console.log(response.data);
            vm.kategorije=response.data;
        }, function errorCallback(response) {
            console.log(response);
        });

        $http.get(url1).then(function successCallback(response) {
            console.log(response.data);
            vm.tipovi=response.data;
        }, function errorCallback(response) {
            console.log(response);
        });

        vm.createNews = function(vijestTitle,vijestText,tipvijesti,kategorijavijesti){
             var data = {nazivVijesti: vijestTitle,textVijesti:vijestText ,datum:vm.date, aktivan:1 ,autorID:vm.user.id, tipVijesti:vm.tipvijesti, kategorijaVijesti:vm.kategorijavijesti };
            $http.post(url,data).then(function successCallback(response) {
                console.log(response.data);
                $location.path('/vijesti');
            }, function errorCallback(response) {
                console.log(response);
            });
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

    app.controller('NewsCreateController', ['$scope','$location','$http', NewsCreateController]);

}());