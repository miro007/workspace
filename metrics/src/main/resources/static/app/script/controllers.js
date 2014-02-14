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
    

	
//	$scope.location = $location.absUrl()
	



	
}


