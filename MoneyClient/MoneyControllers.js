var money = angular.module('money',[]);

money.controller('moneyCtrl', function($scope, $http){
$http.get('http://localhost:8080/Money/adv/list').success(function(data) {
    $scope.advertisements = data; 
   });

    $scope.advertisement={};

    $scope.add = function(){
    $http.post('http://localhost:8080/Money/adv/add', $scope.advertisement).
            success(function(responce){
      	      console.log('ok')
     }).error(function(responce){
              console.log("error")
   });
 };
});



