(function(){

    var app = angular.module("SitAndFit");

    var NewsCreateController = function($scope,$location){
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
        $scope.$log=sessionStorage.loggedIn;
        if(sessionStorage.loggedIn){
            vm.logged = true;
        }
        else{
            vm.logged=false;
        }
        var url = vijestURL+'vijesti';
        var url1 = vijestURL+'tipvijesti';
        var url2 = vijestURL+'kategorijevijesti';
        $http.get(url1,data).then(function successCallback(response) {
            console.log(response.data);
            vm.kategorije=response.data;
        }, function errorCallback(response) {
            console.log(response);
        });

        vm.createNews = function(vijestTitle,vijestText,date,active,tipvijesti,kategorijavijesti){
             var data = {nazivVijesti: vijestTitle,textVijesti:vijestText ,datum:date, aktivan:active ,autorID:sessionStorage.user.id, tipVijesti:tipvijesti, kategorijaVijesti:kategorijavijesti };
            $http.post(url,data).then(function successCallback(response) {
                console.log(response.data);
                location.path('/vijesti');
            }, function errorCallback(response) {
                console.log(response);
            });
        };

        vm.logout = function(){
            sessionStorage.loggedIn=false;
            sessionStorage.authentication_token = null;
            sessionStorage.user = null;
            vm.logged=false;
            location.path('');
            $window.location.reload();
        };

    };

    app.controller('NewsCreateController', ['$scope','$location', NewsCreateController]);

}());