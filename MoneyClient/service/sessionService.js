money.factory('sessionService', function($http){
     return {
     	set:function(key, value){
        	return sessionStorage.setItem(key, value);
     		},
     	get:function(key){
			return sessionStorage.getItem(key);
     		},
     	destroy:function(key){
			return sessionStorage.removeItem(key);
    		}
	};
});