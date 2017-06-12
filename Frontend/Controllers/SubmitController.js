(function(){
	
	var app = angular.module("SitAndFit");

	var SubmitController = function($scope, $http, $location, $routeParams){
        $scope.routePar=$routeParams.str;
		$scope.potvrdiMail = function(){

			alert("Pozvano");
            
    		var url = "http://localhost:8084/submit/potvrdi/"+$scope.routePar;

		    $http.get(url)
            .then(function(response){
                alert("Uspjesna potvrda");
                console.log($scope.routePar);
            },
            function(response){
                alert("Neuspjesan");
                console.log($scope.routePar);
            })
			};

	}

	app.controller('SubmitController', ['$scope', '$http','$location', '$routeParams', SubmitController]);

}())