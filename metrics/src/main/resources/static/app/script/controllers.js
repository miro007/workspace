'use strict';


function DashboardsController(Dashboard, User, $scope, $location){
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

function DashboardDetailsController(Dashboard, Metric, $http,$scope, $routeParams){
	var idDashboard=$routeParams.idDashboard
	$scope.dashboard = Dashboard.get({id:idDashboard})
	
	Metric.query({'idDashboard':idDashboard},function(result){
		$scope.metrics	=	convertToGrid(result,2);
	})
	
	$scope.testPullLink = function(){
	    $http({method: 'GET', url: $scope.metric.pull}).success(function(data, status, headers, config) {
	    	$scope.test=true;
	    }). error(function(data, status, headers, config) {
	    	$scope.test=false;
	    });
	}
}


function MetricsController(Metric, User, $scope, $location){
		
	$scope.metrics = Metric.query();
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