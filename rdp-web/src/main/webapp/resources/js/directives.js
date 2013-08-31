'use strict';

/* Directives */

angular.module('crud.directives', []).directive('appVersion', [ 'version', function(version) {
	return function(scope, elm, attrs) {
		scope.$watch('name', function() {
			elm.text(version);
			elm.append("asdasd")
		});

	};
} ]).directive('field', function() {
	return {
		restrict : 'AE',
		replace : true,
		transclude : true,
		scope : {
			id : '@',
			value : '=',
			label : '@'
		},
		templateUrl : 'partials/field.html'

	}
}).directive('dbTable', function() {
	return {
		restrict : 'E',
		transclude : false,
		templateUrl : 'partials/table.html',
		replace : true,
		scope : {
			val : '='
		}

	}
}).controller('tabController', function($scope) {
	$scope.panes = [ 'a' ];
	$scope.addPane = function(pane) {
		$scope.panes.push(pane)
	}
	$scope.select = function(pane) {
		pane.selected = !pane.selected;
	}
}).directive('tab', function() {
	return {
		restrict : 'E',
		controller : 'tabController',
		replace : true,
		transclude : true,
		templateUrl : 'partials/tab.html',

	}
}).directive('tabElement', function() {
	return {
		restrict : 'E',
		require : '^tab',
		replace : true,
		transclude : true,
		templateUrl : 'partials/tabElement.html',
		link : function postLink(scope, iElement, iAttrs) {
			scope.selected = false;
			scope.addPane(iAttrs['heading']);
		}

	}
}).directive('chart', function() {
	return {
		restrict : 'E',
		transclude : true,
		link : function postLink(scope, iElement, iAttrs) {
			scope.$watch(iAttrs.source, function() {
				plot1.series[0].data = scope[iAttrs.source];
				plot1.replot({
					resetAxes : true
				});
			}, true) // te true jest wazne jak cholera porownouje wartosc zamiast referencji

			var data = scope[iAttrs.source];
			var plot1 = $.jqplot(iAttrs.id, [ data ], {
				series : [ {
					showMarker : true
				} ],
				axes : {
					xaxis : {
						label : 'Angle (radians)'
					},
					yaxis : {
						label : 'Cosine'
					}
				},
				animate : true,
				animateReplot : true,

			});

		}
	}

	// function doit(){
	// var newData = [[0.1,1],[0.4,1],[0.7,5]];
	// plot1.series[0].data = newData;
	// plot1.replot({ resetAxes: true });
	// }

}).directive('slideToggle', function() {
	return {
		restrict : 'A',
		scope : {
			isOpen : "=slideToggle",
			slideDuration : "=slideToggleDuration"
		},
		controller : function($scope) {

		},
		link : function(scope, element, attr) {
			element.click(function() {
				scope.isOpen = !scope.isOpen;
				scope.$apply()
				console.log(scope.isOpen)

			})
			scope.$watch('isOpen', function(newVal, oldVal) {

				var slideSpeed = (parseInt(scope.slideDuration, 10) || "fast");
				console.log(scope.slideDuration)
				if (newVal) {
					element.slideDown(slideSpeed);
				} else {
					element.slideUp(slideSpeed);
				}
			});
		}
	};
}).controller('DirectiveController', function($scope) {
	$scope.duration=2000
});
