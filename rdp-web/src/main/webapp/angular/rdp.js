'use strict';

// Declare app level module which depends on filters, and services
var rdp = angular.module('rdp', [ 'ngRoute', 'ngAnimate', 'services' ])

rdp.directive('meeting', function() {
	return {
		restrict : 'AE',
		replace : true,
		transclude : true,
		scope : {
			meeting : '=data',
		},
		templateUrl : 'partial/meeting.html'

	}
})

rdp.directive('strinput', function() {
	return {
		restrict : 'E',
		replace : true,
		transclude : true,
		scope:{
			name:'@',
			label:'@',
			value:'='
		},
		templateUrl : 'partial/input.html'

	}
})
rdp.directive('attr', function() {
	return {
		restrict : 'E',
		replace : true,
		transclude : true,
		scope:{
			name:'@',
			label:'@',
			value:'='
		},
		templateUrl : 'partial/input.html'

	}
})
rdp.config(function($httpProvider) {
  var interceptor = ['$rootScope','$q', function(scope, $q) {

    function success(response) {
      return response;
    }

    function error(response) {
      var status = response.status;

      if (status == 401) {
    	  alert('mam')

        scope.$broadcast('event:loginRequired');

      }
      // otherwise
      return $q.reject(response);

    }

    return function(promise) {
      return promise.then(success, error);
    }

  }];
  $httpProvider.responseInterceptors.push(interceptor);
});

function appController($scope, $rootScope, meetingService, loginService) {
	meetingService.list().then(function(data) {
		$scope.meetings = data;
	});

	$rootScope.$on('meetingChange', function() {
		meetingService.list().then(function(data) {
			$scope.meetings = data;
		});
	})

}

function memberController($scope, $rootScope, meetingService, loginService) {
	$scope.user = {}

	$scope.isLogged = function() {
		loginService.isLogged().then(function(data) {
			if (data == 'false') {
				$('#loginModal').modal('show')
			}
		});
	}
	$scope.login = function() {
		loginService.login($scope.user).then(function(data) {
			$('#loginModal').modal('hide')
		});
	}

	$scope.$on('event:loginRequired', function(){
		$('#loginModal').modal('show')
	})
}


function meetingController($scope, meetingService) {
	$scope.meeting={}
	$scope.addMember = function(id) {
		meetingService.addMember(id);
	}

	$scope.removeMember = function(id) {
		meetingService.removeMember(id);
	}

	$scope.deleteMeeting = function(id) {
		meetingService.deleteMeeting(id);
	}

	$scope.saveMeeting = function() {
		var meeting = $scope.meeting;
		try{
		meeting.tags=$scope.meeting.tags.split(",")
		}catch(ee){}
		meetingService.saveMeeting(meeting).then(function(){
			$('#meetingModal').modal('hide');
			$scope.meeting={}
		});
	}

	$scope.editMeeting = function(id) {
		meetingService.findMeeting(id).then(function(data){
			$scope.meeting=data;
			$('#meetingModal').modal('show')
		});
	}

}
