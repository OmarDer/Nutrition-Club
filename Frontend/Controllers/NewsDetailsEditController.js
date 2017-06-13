(function(){

    var app = angular.module("SitAndFit");

    var NewsDetailsEditController = function($scope,$location){
        vm = $scope;
        vm.logged = false;
        $scope.$log=sessionStorage.loggedIn;
        if(sessionStorage.loggedIn){
            vm.logged = true;
        }
        else{
            vm.logged=false;
        }


        vm.logout = function(){
            sessionStorage.loggedIn=false;
            sessionStorage.authentication_token = null;
            sessionStorage.user = null;
            vm.logged=false;
            location.path('');
            $window.location.reload();
        };
    };

    app.controller('NewsDetailsEditController', ['$scope','$location', NewsDetailsEditController]);

}());