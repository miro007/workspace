'use strict';

/* Services */

// Demonstrate how to register services
// In this case it is a simple value service.
var services = angular.module('services', [ 'ngResource' ]);
services.factory('meetingService', function($resource, $http, $q, $rootScope) {
	var self = this
	self.list = function() {
		var d = $q.defer();
		$http({
			method : 'GET',
			url : '/test/spring/meeting/list'
		}).success(function(data, status, headers, config) {
			d.resolve(data);
		});
		return d.promise;
	};

	self.addMember = function(id) {
		$http({
			method : 'GET',
			url : '/test/spring/meeting/' + id + '/addMember'
		}).success(function(data, status, headers, config) {
			$rootScope.$broadcast('meetingChange');
		});
	}

	self.removeMember = function(id) {
		$http({
			method : 'GET',
			url : '/test/spring/meeting/' + id + '/removeMember'
		}).success(function(data, status, headers, config) {
			$rootScope.$broadcast('meetingChange');
		});
	}
	self.deleteMeeting = function(id) {
		$http({
			method : 'GET',
			url : '/test/spring/meeting/delete/' + id
		}).success(function(data, status, headers, config) {
			$rootScope.$broadcast('meetingChange');
		});
	}
	
	self.saveMeeting = function(meeting) {
		var d = $q.defer();
		$http({
			method : 'POST',
			data:meeting,
			url : '/test/spring/meeting/save'
		}).success(function(data, status, headers, config) {
			$rootScope.$broadcast('meetingChange');
			d.resolve(data);
		});
		return d.promise;
	}
	
	self.findMeeting = function(id) {
		var d = $q.defer();
		$http({
			method : 'GET',
			url : '/test/spring/meeting/'+id
		}).success(function(data, status, headers, config) {
			d.resolve(data);
		});
		return d.promise;
	}
	return self;
});
services.factory('loginService', function($resource, $http, $q, $rootScope) {
	var self = this
	self.isLogged = function() {
		var d = $q.defer();
		$http({
			method : 'GET',
			url : '/test/spring/member/isLogged'
		}).success(function(data, status, headers, config) {
			d.resolve(data);
		});
		return d.promise;
	};

	self.login = function(user) {
		var d = $q.defer();
		$http({
			method : 'POST',
			data : user,
			url : '/test/spring/member/login'
		}).success(function(data, status, headers, config) {
			d.resolve(data);
		});
		return d.promise;
	}

	return self;
});
