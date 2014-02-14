'use strict';

/* App Module */

var app = angular.module('app', [ 'ngResource', 'ngRoute', 'ngCookies', ]);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/home', {
		templateUrl : 'partials/home.html',
	}).when('/dashboards/:idDashboard', {
		templateUrl : 'partials/dashboard.html',
		controller : 'DashboardDetailsController'
	}).when('/dashboards', {
		templateUrl : 'partials/dashboards.html',
		controller : 'DashboardsController'
	}).when('/dashboards/:idDashboard/metrics/:idMetric', {
		templateUrl : 'partials/metric.html',
		controller : 'MetricController'
	}).otherwise({
		redirectTo : '/home'
	});
} ]);

function createGridData(rows, cols){
	
	
}


