'use strict';


function HomeController(Dashboard, User, $scope, $location){
	$scope.go = function ( path ) {
		  $location.path( path );
		};
		
	$scope.dashboards = Dashboard.query();
    $scope.create = function () {
    	Dashboard.save($scope.dash,
            function () {
        		$scope.dashboards = Dashboard.query();
                $('#saveFooModal').modal('hide');
                $scope.clear();
            });
    };

    $scope.update = function (id) {
        $scope.dash = Dashboard.get({id: id});
        $('#saveFooModal').modal('show');
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

function DashboardController(Metric, $scope, $routeParams){
	var idDashboard=$routeParams.idDashboard
	
	Metric.query({'idDashboard':idDashboard},function(result){
		$scope.metrics	=	convertToGrid(result,2);
	})
	
}

function Chart(MetricValue, $routeParams){
	MetricValue.query({idDashboard: $routeParams.idDashboard}, function(data){
		createChart(createChartSeries( data))
		
	})
}

