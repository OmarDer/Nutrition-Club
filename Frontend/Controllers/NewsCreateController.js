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
        $scope.$log=sessionStorage.loggedIn;
        if(sessionStorage.loggedIn==='true'){
            vm.logged = true;
            vm.authentication_token = sessionStorage.authentication_token;
            vm.loggedIn = sessionStorage.loggedIn;
            vm.user = JSON.parse(sessionStorage.user);
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
        
        vm.odaberiTip = function (choice) {
            vm.tipvijesti=choice;
        }

        vm.odaberiKategoriju = function (choice) {
            vm.kategorijavijesti = choice;
        }

        vm.status = {
            isopen: false
        };

        vm.toggled = function(open) {
            console.log('Dropdown is now: ', open);
        };

        vm.toggleDropdown = function($event) {
            vm.preventDefault();
            vm.stopPropagation();
            vm.status.isopen = !$scope.status.isopen;
        };

        $http.get(url1).then(function successCallback(response) {
            console.log(response.data);
            vm.tipovi=response.data;
        }, function errorCallback(response) {
            console.log(response);
        });

        vm.createNews = function(vijestTitle,vijestText,tipvijesti,kategorijavijesti){
             var data = {nazivVijesti: vijestTitle,textVijesti:vijestText ,datum:vm.date, aktivan:1 ,autorID:sessionStorage.user.id, tipVijesti:vm.tipvijesti, kategorijaVijesti:vm.kategorijavijesti };
            $http.put(url,data).then(function successCallback(response) {
                console.log(response.data);
                location.path('/vijesti');
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
            location.path('/');
            $window.location.reload();
        };

    };

    app.controller('NewsCreateController', ['$scope','$location','$http', NewsCreateController]);

}());