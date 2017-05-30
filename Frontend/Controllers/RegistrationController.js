(function(){
	
	var app = angular.module("SitAndFit");

	var RegistrationController = function($scope){

		$scope.ime = "";
        $scope.prezime="";
        $scope.adresa="";
        $scope.broj="";
        $scope.email="";
        $scope.username="";
        $scope.password="";
        $scope.repPassword="";
        
        $scope.registrationFun=function(){
            
        }

	};

	app.controller('RegistrationController', ['$scope', RegistrationController]);

}());