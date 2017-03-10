


var money = angular.module('money',['ngRoute', 'advControllers']);
money.config(['$routeProvider', function($routeProvider){
	$routeProvider.
	when('/list',{ 
	       templateUrl: 'partials/list.html',
	        controller:'MoneyCtrl'}).
	when('/detailed/:advId',{
         templateUrl: 'partials/detailed.html',
	     controller:'DetailedCtrl'}).
	when('/login',{ 
	       templateUrl: 'partials/login.html',
	        controller:'MoneyCtrl'}).
    otherwise({redirectTo:'/list'});
}]);


