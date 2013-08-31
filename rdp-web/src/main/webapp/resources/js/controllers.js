'use strict';
function Customer2($scope){
	$scope.name='mirek'
		$scope.hidden=true
}
function CustomerCtrl($routeParams,$scope,$rootScope, $dialog, customerSerivce) {
//	alert($routeParams.id)
	$scope.customer={firstName:'maja'}
	$rootScope.$on('customerChange', function(){
		$scope.loadCustomers();
	})
	$scope.find = function() {
		$scope.customers =  customerSerivce.list($scope.query)
	};


	$scope.loadCustomers = function() {
		$scope.customers =  customerSerivce.list()
	}
	$scope.deleteCustomer = function(id) {
		var window = openConfirmDelete($dialog);
		window.then(function(result) {
			if (result) {
				customerSerivce.del(id)
				$rootScope.$broadcast('customerChange');
			}
		});
	}
	$scope.loadDetails = function(id){
		$scope.customer=customerSerivce.load(id)
		$scope.addCustomerWindow = true;

	}
	$scope.addCustomer = function() {
		$scope.addCustomerWindow = true;
	};

	$scope.cancelAddCustomer = function() {
		$scope.addCustomerWindow = false;
	};

	$scope.saveCustomer = function() {
		customerSerivce.add($scope.customer)
		$scope.addCustomerWindow = false;
		$scope.customer={}
		$rootScope.$broadcast('customerChange');
	};
//	$scope.customer={}
	$scope.loadCustomers();

}



function openConfirmDelete($dialog) {
	var btns = [ {
		result : true,
		label : 'Tak',
		cssClass : 'btn-primary'
	}, {
		result : false,
		label : 'Nie'
	} ];

	return $dialog.messageBox('Potwierdzenie', 'Czy napewno usunąc element ?', btns).open();
}
