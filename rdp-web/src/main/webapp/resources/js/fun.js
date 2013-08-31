function FunCtrl($scope){
	$scope.tasks=[]
	$scope.task={}
	$scope.name='mirek'
		$scope.data=[[0.1,1],[0.4,1],[0.7,5]]

	$scope.add = function(value){
		var val = parseInt(value)
		$scope.data.push([val,val]);
	}

	$scope.addTask = function(task){
		$scope.tasks.push(task);
		$scope.task={}
	}
}

function FunInnerCtrl($scope){
	$scope.name='maja'
	}