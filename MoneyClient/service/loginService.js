money.factory('loginService', function($http, $location, sessionService){
return{
	login: function(user, scope){
     $http.post('http://localhost:8080/Money/user/info', user)
      .success(function(responce){
      	if(responce.name !== null){
      		sessionService.set(responce.id, responce.name);
      		  $location.path('/list');
      	}else{
      		scope.msg="Please check yor input or sign in";
      		  $location.path('/login');
      			} 
    		});
 		},
 		logout:function(user){
 			sessionService.destroy('1');
 			 $location.path('/login');
 		}
	}
});