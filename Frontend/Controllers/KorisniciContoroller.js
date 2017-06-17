(function(){

    var app = angular.module("SitAndFit");

    var KorisniciController = function($scope,$location,$window,$http){
        
        $http.defaults.headers.common['Authorization'] = sessionStorage.authentication_token;
        
        vm = $scope;
        
        vm.isAdmin = false;
        
        vm.user = JSON.parse(sessionStorage.user);
        
        if(vm.user.rola.nazivRole != "ROLE_ADMIN")
            $location.path("/");
        else
            vm.isAdmin = true;
        
        vm.logged = false;
        vm.korisnici=[];

        if(sessionStorage.loggedIn==='true'){
            vm.logged = true;
        }
        else{
            vm.logged=false;
        }
        
        
        vm.errorPoruka = null;
        
        vm.dodajNovogKorisnika = function(ime, prezime, adresa, email, tel, username, password, repPassword){

            if (password != repPassword){
                vm.errorPoruka = "Unesene lozinke se na slažu!";
                return;
            }
            
             vm.errorPoruka = null;
            
            var datum = new Date();
            
            var noviKorisnik =  {

                            ime: ime,
                            prezime: prezime,
                            adresaPrebivalista: adresa,
                            kontaktTelefon: tel,
                            userName: username,
                            password: password,
                            email: email,
                            odobren: 1,
                            datumRegistracije: datum,
                            aktivan: true,
                            rola: {
                                id: vm.selectedRola.id,
                                nazivRole: vm.selectedRola.nazivRole,
                                opisRole: vm.selectedRola.opisRole,
                                aktivna: true
                    }
            };
            
            
            $http.post("http://localhost:8084/korisnici", noviKorisnik).then(function(response){
                
                var odgovor = response.data;
                
                if (odgovor.status == "Success"){
                    vm.errorPoruka = "Dodavanje korisnika uspješno!";
                }
                else{
                    vm.errorPoruka = odgovor.msg;
                }
            });
            
        };
        
        vm.selectedRola = null;
        
        vm.roleDataSource = null;
        
        $http.get("http://localhost:8084/role").then(function(response){
            
            vm.roleDataSource = JSON.stringify(response.data);
            
            vm.roleDataSource = JSON.parse(vm.roleDataSource);
            
            vm.selectedRola = vm.roleDataSource.filter(function( rola ) {
                                                      return rola.nazivRole === "ROLE_USER";
                                                    })[0];
            
        });
        
        var url=korisnikURL+'korisnici/';
        $http.get(url).then(function successCallback(response) {
            vm.korisnici = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            console.log(response);
        });
        
        vm.delUser = function (userID) {
            $http.del(url+userID).then(function successCallback(response) {
                console.log(response.data);
            }, function errorCallback(response) {
                console.log(response);
            });
        }
        
        vm.accUser = function (userID) {

            $http.get(url+'odobri/'+userID).then(function successCallback(response) {
                console.log(response.data);
            }, function errorCallback(response) {
                console.log(response);
            });
        }
        
        vm.findUser = function (userID) {
            sessionStorage.korisnikID=userID;
            $location.path('/korisnik');
        }

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

    app.controller('KorisniciController', ['$scope','$location','$window','$http', KorisniciController]);

}());