'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
var crud = angular.module('crud.services', ['ngResource']);
crud.
  value('version', '0.1');
crud.factory('customerSerivce', function($resource) {
	var self=this
	self.list = function(query){
//		$.ajax({
//			url : "services/customer/list",
//			type : "GET",
//			contentType : 'application/json',
//			dataType : "JSON",
//			success : function(result) {
//				callback(result);
//			}
//		});
		if(query==undefined){
			query=''
		}
		var data= $resource('services/customer/list', {query:query}, {
	        load: { method: "GET",isArray:true}
	    })
		return data.load()
	};
		self.del = function(id){
			$.ajax({
				url : "services/customer/delete/"+id,
				type : "GET",
				contentType : 'application/json',
				dataType : "JSON",
				success : function(result) {
				}
			});
		}
		self.add = function(data){
			var a= $resource('services/customer/add', {}, {
			        save: { method: "POST"}
			    })
			    a.save(data)
		}

		self.load = function(id){
			var a= $resource('services/customer/:id', {id:id}, {
		        load: { method: "GET"}
		    })
		    return a.load(id)
		}



	return self;
});
