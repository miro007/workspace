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
