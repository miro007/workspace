'use strict';

var rdp = angular.module('app', [])

function MyCtrl1($scope) {
	$scope.imie = 'mirek'

	// set up a $watch.
	$scope.$watch('imie', function(v) {
		$scope.last = v + 'baz';
	});

};

/* jasmine specs for controllers go here */

describe('MyCtrl1', function() {
	var myCtrl1;
	var scope;

	beforeEach(inject(function($rootScope, $controller) {
		 scope = $rootScope.$new();
		myCtrl1 = new MyCtrl1(scope);
	}));

	it('should set simple name', function() {
		expect(scope.imie).toBe('mirek');
	});

	it('should listen on name change', function() {
		scope.imie='mirek'
			scope.$apply()
		expect(scope.last).toBe('mirekbaz');
	});
});
