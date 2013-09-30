'use strict';


// Declare app level module which depends on filters, and services
angular.module('crud', ['crud.filters', 'crud.services', 'crud.directives','ui.bootstrap','ngRoute','ngAnimate']).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/list', {templateUrl: 'partials/list.html', controller: CustomerCtrl});
    $routeProvider.when('/elem/:id', {templateUrl: 'partials/details.html', controller: CustomerCtrl});
    $routeProvider.when('/directive', {templateUrl: 'partials/directive.html', controller: CustomerCtrl});
    $routeProvider.when('/fun', {templateUrl: 'partials/fun.html', controller: FunCtrl});
    $routeProvider.otherwise({redirectTo: '/'});
  }]);

myapp.config(function($httpProvider) {
	  var interceptor = ['$rootScope','$q', function(scope, $q) {

	    function success(response) {
	      return response;
	    }

	    function error(response) {
	      var status = response.status;

	      if (status == 401) {
	        var deferred = $q.defer();
	        var req = {
	          config: response.config,
	          deferred: deferred
	        }
	        scope.requests401.push(req);
	        scope.$broadcast('event:loginRequired');
	        return deferred.promise;
	      }
	      // otherwise
	      return $q.reject(response);

	    }

	    return function(promise) {
	      return promise.then(success, error);
	    }

	  }];
	  $httpProvider.responseInterceptors.push(interceptor);
	});
