'use strict';

// Declare app level module which depends on filters, and services
var rdp = angular.module('rdp', [ 'ngRoute', 'ngAnimate' ])

rdp.directive('fundooRating', function() {
	return {
		restrict : 'A',
		link : function(scope, elem, attrs) {
			console.log("Recognized the fundoo-rating directive usage");
		}
	}
});

rdp.directive('meeting', function() {
	return {
		restrict : 'AE',
		replace : true,
		transclude : true,
		scope : {
			meeting : '=data',
		},
		templateUrl : 'partials/meeting.html'

	}
})

function miro($scope) {
	$scope.meetings = [ {
		title : 'Nauka scala',description : 'Nauka scala',members:["Mirek Szajowski","Mirek Szajowski2","Mirek Szajowski3","Mirek Szajowski4","Mirek Szajowski5"],tags:["asd","asd2","asd3"]
	}, {
		title : 'Nauka scala'
	}, {
		title : 'Nauka scala'
	}, {
		title : 'Nauka scala',description : 'Nauka scala',members:["asd","asd2","asd3"],tags:["asd","asd2","asd3"]
	}, {
		title : 'Nauka scala'
	}, {
		title : 'Nauka scala',description : 'Nauka scala',members:["asd","asd2","asd3"],tags:["asd","asd2","asd3"]
	} ]
}
