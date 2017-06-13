(function(){
    
    var app = angular.module("SitAndFit");
    
    app.factory('uploadService', ['FileUploader', function(FileUploader) {
        
        var uploader = null;
        
        var uploader_service = {
          
            createUploader: function(_url){
              
                var uploader = new FileUploader({
                    url: _url,
                    headers: {'Authorization': sessionStorage.authentication_token}
                }); 
                
                return uploader;
              
            }
        };

      
      return uploader_service;
      
    }]);
    
    
}());