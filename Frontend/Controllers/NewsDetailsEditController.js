(function(){

    var app = angular.module("SitAndFit");

    var NewsDetailsEditController = function($scope,$location,$http){
        vm = $scope;
        vm.logged = false;
        vm.vijestTitle='';
        vm.vijestText='';
        vm.date=new Date();
        vm.active = 1;
        vm.tipvijesti=null;
        vm.kategorijavijesti=null;
        vm.autor = 0;
        vm.user=null;

        $scope.$log=sessionStorage.loggedIn;
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
        var url = vijestURL+'vijesti/'+sessionStorage.newsID;

        $http.get(url).then(function successCallback(response) {
            console.log(response.data);
            vm.vijest = response.data.vijest;
            vm.vijestTitle = vm.vijest.nazivVijesti;
            vm.vijestText = vm.vijest.textVijesti;
            vm.active =vm.vijest.aktivan;
            vm.autor = vm.vijest.autorID;
            vm.tipvijesti = vm.vijest.tipVijesti;
            vm.kategorijavijesti = vm.vijest.kategorijaVijesti;

        }, function errorCallback(response) {
            console.log(response);
        });

        vm.editNews = function(vijestTitle,vijestText){
            var data = {nazivVijesti: vijestTitle,textVijesti:vijestText ,datum:vm.date, aktivan:vm.active ,autorID:vm.autor, tipVijesti:vm.tipvijesti, kategorijaVijesti:vm.kategorijavijesti };
            $http.put(url,data).then(function successCallback(response) {
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

    app.controller('NewsDetailsEditController', ['$scope','$location','$http', NewsDetailsEditController]);

}());