'use strict';

app.directive('grid', function() {
	return {
		restrict : 'E',
		replace : true,
		transclude : true,
		templateUrl : 'components/grid.html',
		scope : {
			items : '=items',
			cols : '=cols',
		},
	}
})

app.directive('metric', function() {
	return {
		restrict : 'E',
		replace : true,
		transclude : true,
		controller : function(MetricValue, $scope) {
			MetricValue.query({
				idMetric : $scope.metric.id
			}, function(data){createChart($scope.metric.id, createArray(data))})

		},
		templateUrl : 'components/metric.html',
		scope : {
			metric : '=data',
		},
	}
})