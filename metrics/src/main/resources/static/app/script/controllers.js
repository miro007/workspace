'use strict';


function DashboardsController(Dashboard, User, $scope, $location){
	$scope.go = function ( path ) {
		  $location.path( path );
		};
		
	$scope.dashboards = Dashboard.query();
    $scope.save = function () {
    	Dashboard.save($scope.dash,
            function () {
        		$scope.dashboards = Dashboard.query();
                $('#dashboardModal').modal('hide');
                $scope.clear();
            });
    };

    $scope.update = function (id) {
        $scope.dash = Dashboard.get({id: id});
        $('#dashboardModal').modal('show');
    };

    $scope.delete = function (id) {
    	Dashboard.delete({id: id},
            function () {
        		$scope.dashboards = Dashboard.query();
            });
    };

    $scope.clear = function () {
        $scope.dash = {};
    };
}

function DashboardDetailsController(Dashboard, Metric, $http,$scope, $routeParams){
	var idDashboard=$routeParams.idDashboard
	

	$scope.dashboard = Dashboard.get({id:idDashboard})
	
	$scope.initMetrics = function(){
		Metric.query({'idDashboard':idDashboard},function(result){
			$scope.metrics	=	convertToGrid(result, 3);
		});
	}
	$scope.initMetrics();
	

	
	$scope.save = function () {
    	var data =$scope.metric;
    	data.idDashboard = idDashboard;
    	Metric.save(data,
            function () {
    			$scope.initMetrics();
                $('#metricModal').modal('hide');
                $scope.clear();
            });
    };

    $scope.update = function (id) {
        $scope.metric = Metric.get({idDashboard:idDashboard, id: id});
        $('#metricModal').modal('show');
    };
    

    $scope.delete = function (id) {
    	Metric.delete({idDashboard:idDashboard,id: id},
            function () {
    			$scope.initMetrics();
            });
    };

    $scope.clear = function () {
        $scope.metric = {name:'', pullLink :'/rest/metrics/values/example'};
    };

	
}
function MenuController($location, $scope){
	$scope.location = $location.absUrl()
}

function MetricController(Dashboard, Metric, MetricValue, $http,$scope, $routeParams, $location){
	var idDashboard=$routeParams.idDashboard
	var idMetric=$routeParams.idMetric
	
	if(idMetric != undefined){
		$scope.idMetric = idMetric;
		$scope.metric = Metric.get({idDashboard:idDashboard,id:idMetric});
	}
	
	$scope.location = $location.absUrl()
	
	$scope.loadValues = function(id){
		MetricValue.query({
			idMetric : id
		}, function(data){
				var id = $scope.metric.id;
				var series=createChartSeries($scope.metric.name, data);
				
				createStockChart(id, series, MetricValue)	;
		})	
			
//		MetricValue.query({
//			idMetric : 3
//		}, function(data){
//				var id = $scope.metric.id;
//				var series=createChartSeries($scope.metric.name, data);
//				MetricValue.query({
//					idMetric : 4
//				}, function(data){
//						var id2 = $scope.metric.id;
//						var series2=createChartSeries($scope.metric.name, data);
//						
//						var sum = series
//						
//						series[1] = series2[0];
//						createStockChart(id, series)	;
//				})	
//				
//		})
		
		
	}		
}
