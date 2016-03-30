var money = angular.module('money',['jcs-autoValidate']);

/*money.run(function(defaultErrorMesssageResolver){
defaultErrorMesssageResolver.getErrorMessages().then(function(errorMessages){
   errorMessages['wrongSimbolsInName'] = 'You shoul use only letters, digits and underscore simbol only';
 });
});*/

money.controller('moneyCtrl', function($scope, $http){
$http.get('http://localhost:8080/Money/adv/list').success(function(responce) {
    $scope.advertisements = responce; 
   });

     $scope.advertisement={};
     

     $scope.edit = function(adv){
     	console.log(adv);
      $scope.advertisement=adv;
        console.log($scope.advertisement);
      }    

    $scope.add = function(){
    $http.post('http://localhost:8080/Money/adv/add', $scope.advertisement).
            success(function(responce){
            	$scope.advertisement={};
      	      console.log('ok')
     }).error(function(responce){
              console.log("error")
   });
 };
});



