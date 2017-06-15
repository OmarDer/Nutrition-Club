(function(){

    var app = angular.module("SitAndFit");

    var ProductsController = function($scope,$location,$window,$http){
        vm = $scope;
        vm.logged = false;
        vm.proizvodi=[];
        console.log(sessionStorage.loggedIn);
        if(sessionStorage.loggedIn==='true'){
            vm.logged = true;
        }
        else{
            vm.logged=false;
        }
        var url=proizvodURL+'proizvodi';
        $http.get(url).then(function successCallback(response) {
            vm.proizvodi = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            console.log(response);
        });

        vm.productDetails = function () {
            console.log('redirekt na detalje');
        }

        vm.logout = function(){
            sessionStorage.loggedIn=false;
            sessionStorage.authentication_token = null;
            sessionStorage.user = null;
            vm.logged=false;
            $window.location.reload();
        };
    };

    app.controller('ProductsController', ['$scope','$location','$window','$http', ProductsController]);

}());