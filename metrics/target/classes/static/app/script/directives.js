'use strict';

app.directive('grid', function() {
	return {
		restrict : 'E',
		link: function(scope, element, attrs, controller) {
		},
		replace : true,
		transclude : true,
		templateUrl : 'components/grid.html',
		scope : {
			items : '=items',
			cols : '=cols',
		},
	}
})

app.directive('metricChart', function() {
	return {
		restrict : 'E',
		replace : true,
		transclude : true,
		controller: MetricController,	
		templateUrl : 'components/metricChart.html',
		scope : {
			metric : '=data',
			del : '&deleteAction',
			update : '&updateAction'
		}
	}
})
