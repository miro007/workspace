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
		controller: function($scope, $routeParams, MetricValue){
				$scope.loadValues = function(id){
					MetricValue.query({
						idMetric : $scope.metric.id
					}, function(data){
							var id = $scope.metric.id;
							var series=createChartSeries($scope.metric.name, data);
							createChart(id, series)
					})
					
				}		
		},
		templateUrl : 'components/metricChart.html',
		scope : {
			metric : '=data',
			del : '&deleteAction',
			update : '&updateAction'
		}
	}
})
