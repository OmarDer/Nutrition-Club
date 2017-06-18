(function(){

    var app = angular.module("SitAndFit");

    var KorisnikController = function($scope,$location,$window,$http){
        vm = $scope;
        
        $http.defaults.headers.common['Authorization'] = sessionStorage.authentication_token;
        
        vm.stariPass = null;
        vm.noviPass = null;
        vm.noviPassPonovo = null;
        vm.poruka = null;
        
        vm.user = JSON.parse(sessionStorage.user);
        
        vm.isAdmin = false;
        
        if(vm.user != null && vm.user.rola.nazivRole == "ROLE_ADMIN")
            vm.isAdmin = true;
            vm.loggedInUser = vm.user.ime + " " + vm.user.prezime;
        
        vm.promijeniPassword = function(){
            
            if(vm.stariPass != vm.user.password){
                vm.poruka = "Stara lozinka nije ispravna!";
                return;
            }
            else if(vm.noviPass == null || vm.noviPassPonovo == null){
                vm.poruka = "Nova lozinka ne može biti prazna!";
                return;
            }
            else if(vm.noviPass != vm.noviPassPonovo){
                vm.poruka = "Nove lozinke se ne slažu!";
                return;
            }
            else{
                vm.poruka = null;
            }
            
            var data = {password:vm.noviPass};
            
            $http.put("http://localhost:8084/korisnici/"+vm.user.id.toString(), data).then(function(response){
                
                 var odgovor = response.data;
                
                if (odgovor.status == "Success"){
                    vm.poruka = "Lozinka je uspješno promijenjena!";
                }
                else{
                    vm.poruka = odgovor.msg;
                }
                
            });
            
            
        };
        
        vm.logged = false;
        vm.korisnik=null;
        vm.korisnikStats=null;
        console.log(sessionStorage.loggedIn);
        if(sessionStorage.loggedIn==='true'){
            vm.logged = true;
        }
        else{
            vm.logged=false;
        }
        var url=korisnikURL+'korisnici/'+sessionStorage.korisnikID;
        $http.get(url).then(function successCallback(response) {
            vm.korisnik = response.data.korisnik;
            console.log(response.data);
        }, function errorCallback(response) {
            console.log(response);
        });

        $http.get(url+'/stanjakorisnika').then(function successCallback(response) {
            vm.korisnikStats = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            console.log(response);
        });

        vm.profile = function () {
            sessionStorage.korisnikID = vm.user.id;
            $location.path('/korisnik');
        };

        vm.logout = function(){
            sessionStorage.loggedIn=false;
            sessionStorage.authentication_token = null;
            sessionStorage.user = null;
            vm.logged=false;
            $location.path('/');
            $window.location.reload();
        };
    };

    app.controller('KorisnikController', ['$scope','$location','$window','$http', KorisnikController]);

}());