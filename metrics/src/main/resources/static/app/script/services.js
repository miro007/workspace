'use strict';

app.factory('Dashboard', [ '$resource', function($resource) {
	return $resource('../../rest/dashboards/:id', {}, {
		'query' : {
			method : 'GET',
			isArray : true
		}
	});
} ]);

app.factory('Metric', [ '$resource', function($resource) {
	return $resource('../../rest/dashboards/:idDashboard/metrics/:id', {idDashboard:'@idDashboard'}, {
		'query' : {
			method : 'GET',
			isArray : true
		},
		'get' : {
			method : 'GET'
		},
		'checkPullLink':{
			url :'../../rest/dashboards/:idDashboard/metrics/checkPullLink',
			method:'GET'
		}
	});
} ]);

app.factory('Event', [ '$resource', function($resource) {
	return $resource('../../rest/dashboards/:idDashboard/event', {idDashboard:'@idDashboard'}, {
		'listEvent' : {
			method : 'GET',
			isArray : true
		},
		'addEvent':{
			method:'POST'
		},
		'removeEvent':{
			method:'DELETE'
		},
	});
} ]);

app.factory('MetricValue', [ '$resource', function($resource) {
	return $resource('../../rest/metrics/values/:id', {}, {
		'query' : {
			method : 'GET',
			isArray : true
		},
		'get' : {
			method : 'GET'
		}
	});
} ]);

app.factory('User', function() {
	this.getIdAccount = function() {
	    return 1
	  }
	return this
});
