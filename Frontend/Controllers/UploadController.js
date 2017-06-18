(function(){
	
	var app = angular.module("SitAndFit");

	var UploadController = function($scope, uploadService, FileUploader, $http, $location){
        
        $scope.errorMsg = "";
        
        /*
        var uploader = $scope.uploader = new FileUploader({
            url: 'http://localhost:8084/korisnici/1/slika',
            headers: {'Authorization': sessionStorage.authentication_token}
        
        });
        */
        
        var uploader = $scope.uploader = uploadService.createUploader('http://localhost:8084/korisnici/1/slika');

        // FILTERS

        uploader.filters.push({
            name: 'customFilter',
            fn: function(item , options) {
                
                $scope.errorMsg = "";
                return true;
                
            }
        });

        // CALLBACKS

        uploader.onWhenAddingFileFailed = function(item , filter, options) {
            console.info('onWhenAddingFileFailed', item, filter, options);
        };
        uploader.onAfterAddingFile = function(fileItem) {
            console.info('onAfterAddingFile', fileItem);
            
            if(!(fileItem._file.type == "image/png" || fileItem._file.type == "image/jpeg"))
            {
                uploader.removeFromQueue(fileItem);
                $scope.errorMsg = "Ekstenzija filea za upload mora biti jpg ili png."
            }
            else
                $scope.errorMsg = "";
            
            if (this.queue.length > 1){
                uploader.removeFromQueue(fileItem);
                $scope.errorMsg = "Moguće je uploadovati samo jednu sliku.";
                return false;
            }
                
            
        };
        uploader.onAfterAddingAll = function(addedFileItems) {
            console.info('onAfterAddingAll', addedFileItems);
        };
        uploader.onBeforeUploadItem = function(item) {
            console.info('onBeforeUploadItem', item);
        };
        uploader.onProgressItem = function(fileItem, progress) {
            console.info('onProgressItem', fileItem, progress);
        };
        uploader.onProgressAll = function(progress) {
            console.info('onProgressAll', progress);
        };
        uploader.onSuccessItem = function(fileItem, response, status, headers) {
            console.info('onSuccessItem', fileItem, response, status, headers);
        };
        uploader.onErrorItem = function(fileItem, response, status, headers) {
            console.info('onErrorItem', fileItem, response, status, headers);
        };
        uploader.onCancelItem = function(fileItem, response, status, headers) {
            console.info('onCancelItem', fileItem, response, status, headers);
        };
        uploader.onCompleteItem = function(fileItem, response, status, headers) {
            console.info('onCompleteItem', fileItem, response, status, headers);
        };
        uploader.onCompleteAll = function() {
            console.info('onCompleteAll');
        };

        console.info('uploader', uploader);
        

	}

	app.controller('UploadController', ['$scope', 'uploadService','FileUploader', '$http','$location', UploadController]);

}())