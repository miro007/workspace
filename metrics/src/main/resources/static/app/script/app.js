'use strict';

/* App Module */

var app = angular.module('app', [ 'ngResource', 'ngRoute', 'ngCookies' ]);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/home', {
		templateUrl : 'partials/home.html',
		controller : 'HomeController'
	}).when('/home/:idDashboard', {
		templateUrl : 'partials/dashboard.html',
		controller : 'DashboardController'
	}).otherwise({
		redirectTo : '/home'
	});
} ]);

function createGridData(rows, cols){
	
	
}


