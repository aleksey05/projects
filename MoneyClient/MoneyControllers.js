var advControllers = angular.module('advControllers',['jcs-autoValidate']);

/*money.run(function(defaultErrorMesssageResolver){
defaultErrorMesssageResolver.getErrorMessages().then(function(errorMessages){
   errorMessages['wrongSimbolsInName'] = 'You shoul use only letters, digits and underscore simbol only';
 });
});*/

advControllers.controller('MoneyCtrl', function($scope, $http, loginService){
$http.get('http://localhost:8080/Money/advertisement').success(function(responce) {
      $scope.advertisements = responce; 
    });
    $scope.msg=' ';
    $scope.advertisement={};
       
     $scope.edit = function(adv){
     	  console.log(adv);
     $scope.advertisement=adv;
        console.log($scope.advertisement);
      }    
      $scope.getAdvByid = function(advId){
       $http.get('http://localhost:8080/Money/advertisement/' + advId)
        .succsses(function(responce){
           $scope.singleAdv  = responce;
                })
      }
      $scope.delete = function(advId){
        $http.delete('http://localhost:8080/Money/advertisement/'+ advId)
      }    

      $scope.login = function(){
        

        loginService.login($scope.user, $scope);
      }    
       $scope.logout = function(){
        loginService.logout($scope.user);
      }    


      $scope.add = function(){
        $http.post('http://localhost:8080/Money/advertisement', $scope.advertisement).
          success(function(responce){
            $scope.advertisements = responce; 
            $scope.advertisement={};
          }).error(function(responce){
              console.log("error")
    });
  };
});


advControllers.controller('DetailedCtrl', function($scope,$http,$routeParams){
      $http.get('http://localhost:8080/Money/advertisement/' + $routeParams.advId).success(function(responce){
       $scope.singleAdv  = responce;
   });
});

