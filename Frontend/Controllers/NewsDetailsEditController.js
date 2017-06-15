(function(){

    var app = angular.module("SitAndFit");

    var NewsDetailsEditController = function($scope,$location){
        vm = $scope;
        vm.logged = false;
        vm.vijestTitle='';
        vm.vijestText='';
        vm.date=new Date();
        vm.active = 1;
        vm.tipvijesti=null;
        vm.kategorijavijesti=null

        $scope.$log=sessionStorage.loggedIn;
        if(sessionStorage.loggedIn==='true'){
            vm.logged = true;
        }
        else{
            vm.logged=false;
        }

        vm.editNews = function(vijestTitle,vijestText,date,active,tipvijesti,kategorijavijesti){
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
            location.path('/');
            $window.location.reload();
        };
    };

    app.controller('NewsDetailsEditController', ['$scope','$location', NewsDetailsEditController]);

}());