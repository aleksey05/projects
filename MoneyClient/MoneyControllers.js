var advControllers = angular.module('advControllers',['jcs-autoValidate']);

/*money.run(function(defaultErrorMesssageResolver){
defaultErrorMesssageResolver.getErrorMessages().then(function(errorMessages){
   errorMessages['wrongSimbolsInName'] = 'You shoul use only letters, digits and underscore simbol only';
 });
});*/

advControllers.controller('MoneyCtrl', function($scope, $http){
$http.get('http://localhost:8080/Money/adv/list').success(function(responce) {
      $scope.advertisements = responce; 
    });

    $scope.advertisement={};
    $scope.persons = {
      name:'Jim',
      lastname:'Bean'
    };
     $scope.edit = function(adv){
     	console.log(adv);
      $scope.advertisement=adv;
        console.log($scope.advertisement);
      }    
      $scope.getAdvByid = function(advId){
      $http.get('http://localhost:8080/Money/adv/add' + advId)
      .succsses(function(responce){
                  $scope.singleAdv  = responce;
                })
      }
      $scope.delete = function(advId){
      $http.delete('http://localhost:8080/Money/adv/list/'+ advId)
      }    
     $scope.add = function(){
     $http.post('http://localhost:8080/Money/adv/add', $scope.advertisement).
            success(function(responce){
            	$scope.advertisement={};
     }).error(function(responce){
              console.log("error")
   });
 };
});


advControllers.controller('DetailedCtrl',function($scope,$http,$routeParams){
  $scope.advId = $routeParams.advId;

      $http.get('http://localhost:8080/Money/adv/list/' + $routeParams.advId).success(function(responce){
                  $scope.singleAdv  = responce;
                });
});
