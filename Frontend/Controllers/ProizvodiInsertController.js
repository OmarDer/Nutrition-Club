(function(){
	
	var app = angular.module("SitAndFit");
    
	var ProizvodiInsertController = function($scope,$http,uploadService,FileUploader){
        
    $scope.imageLink="";    
        
    //DIO ZA UPLOAD SLIKE
        $scope.user = JSON.parse(sessionStorage.user);
        $scope.errorMsg = "";
        
       var uploader = $scope.uploader = uploadService.createUploader('http://localhost:8083/proizvodi/image/upload');

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
       
        uploader.onCompleteItem = function(fileItem, response, status, headers) {
            //console.info('onCompleteItem', fileItem, response, status, headers);
          $scope.imageLink=response;
        };
        
        //console.info('uploader', uploader);
        
    //KRAJ UPLOADA SLIKE
        
		$scope.naziv = "";
        $scope.opis="";
        $scope.autorId=$scope.user.id.toString();
        $scope.aktivan="1";
        //$scope.slika="";
        $scope.cijena="";
        //SPREMANJE U BAZU PODATAKA PROIZVODA
        $scope.insertFun=function(){
            
        var data = {
            naziv_proizvoda: $scope.naziv,
            opis_proizvoda:$scope.opis,
            aktivan: $scope.aktivan,
            autor_ID: $scope.autorId,
            slika: $scope.imageLink,
            cijena: $scope.cijena
        };
            
        console.log(data);
        
            
        
         $http.post('http://localhost:8083/proizvodi',data)
             .then(
         function(response){
             console.log($scope);
              console.log(response);
             alert('Ispravno');
         },
        function(response){
                alert('Neispravno');
        });
	};
        }
    
	app.controller('ProizvodiInsertController', ['$scope','$http','uploadService','FileUploader', ProizvodiInsertController]);

}());