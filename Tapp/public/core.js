

;(function(){// ----------------------INTERCEPTOR---------------------------------------------------


  function authInterceptor(authService) {
    return {

      // automatically attach Authorization header
      request: function(config) {
        var token = authService.getToken();
        // (config.url.indexOf(API) === 0 &&
        if(token) {
          console.log(token);
          config.headers.Authorization = token;
        }

        return config;
      },

        // If a token was sent back, save it
      response: function(res) {
        if( res.data.token) {
          authService.saveToken(res.data.token);
        }
        return res;
      },
    }
  }
// -------------------------------------AUTH SRVICE-------------------------------------------------

  function authService($window) {


    this.isAuthed = function() {
      var token = this.getToken();
      if(token) {
        var params = this.parseJwt(token);
        return Math.round(new Date().getTime() / 1000) <= params.exp;
      } else {
        return false;
      }
    }

      this.logout = function() {
      $window.localStorage.removeItem('jwtToken');
    }

      this.parseJwt = function(token) {
          var base64Url = token.split('.')[1];
      var base64 = base64Url.replace('-', '+').replace('_', '/');
          return JSON.parse($window.atob(base64));
    }

      this.saveToken = function(token) {
      $window.localStorage['jwtToken'] = token;
    }

      this.getToken = function() {
      return $window.localStorage['jwtToken'];
    }


  }


// -----------------------------USER SRVICE---------------------------------------------------------

  function userService($http) {
    this.register = function(username, password) {
      return $http.post('/user/register', {
        username: username,
        password: password
      });
    }

    this.login = function(username, password) {
      return $http.post('/user/authenticate', {
        username: username,
        password: password
      });
    }

    this.logout = function() {
      return $http.post('/user/logout');
    }

  }

  // ------------------------------CONTROLLER--------------------------------------------------------//




  angular.module('app', [])
      .factory('authInterceptor', authInterceptor)
      .service('userService', userService)
      .service('authService', authService)
      .config(function($httpProvider) {
        $httpProvider.interceptors.push('authInterceptor');
      })
      .controller('Main', function ($scope, $http, userService, authService) {


        $scope.login = function() {
          userService.login($scope.username, $scope.password).then(function(res){
            // var token = res.data ? res.data.token : null;
            // if(token) { console.log('JWT:', token); }
            $scope.userId = res.data.user_id;
            $scope.message = res.data.message;
          });
        }

        $scope.register = function() {
          userService.register($scope.username, $scope.password)
              .then(function(res){
                $scope.message = res.data.message;
              }, function(err){
                $scope.message = err;
              })

        }



        $scope.logout = function() {
          userService.logout().then(function(res){
            $scope.message = res.data.message;
            $scope.todos = { };
            authService.logout();
          });
        }


        //----------------------------------TODOS-------------------------------------///

        $scope.getTodo = function(){
          $http.get('/todos')
              .then(function(res) {
                $scope.todos = res.data;
                console.log(res.data);
              }, function(res){
                console.log(res.data);
              })
        }

        $scope.deleteTodo = function(id){
          $http.delete('/todos/remove/' + id)
              .then(function(res) {
                $scope.todos = res.data;
                console.log(res.data);
              }, function(res){
                console.log(res.data);
              })
        }

        $scope.createTodo = function(){
          $http.post('/todos/add/',{text:$scope.text})
              .then(function(res) {
                $scope.todos = res.data;
                console.log(res.data);
              }, function(res){
                console.log(res.data);
              })
             $scope.text = ' ';
        }


        $scope.isAuthed = function() {
          return authService.isAuthed ? authService.isAuthed() : false
        }

      })
})();