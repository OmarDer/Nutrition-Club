(function(){
	
	var app = angular.module("SitAndFit");

	var UploadController = function($scope, $http, $location){

		$scope.uploadImage = function(){

			alert("Pozvano");

			var file = $scope.fileUpload;
    		var uploadUrl = "http://localhost:8084/korisnik/1/slika";

    		var fd = new FormData();
		    fd.append('file', file);
		    $http.post(uploadUrl, fd, {
			        transformRequest: angular.identity,
			        headers: {'Content-Type': undefined}
			      
			    });
			};

	}

	app.controller('UploadController', ['$scope', '$http','$location', UploadController]);

}())