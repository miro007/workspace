function saveMeeting() {
	var form = $("#meetingForm");
	var data = $(form).serialize()
	$.ajax({
		type : "POST",
		url : "meeting/save",
		data : data,
		success : function(data){
			$('#myModal').modal('hide')
			$("#meetingForm").get(0).reset();
			refreshList();
		}
	});

	

}

function editMeeting(id) {
	$.ajax({
		type : "GET",
		url : "meeting/"+id,
		contentType:'application/json',
		success : function(data){
			$("#id").val(data.id);
			$("#name").val(data.name);
			$("#description").val(data.description);
			$("#tags").val(data.tags);
			$('#myModal').modal('show')
		}
	});
	
	
}

function login() {
	var form = $("#loginForm");
	var data = $(form).serialize()
	$.ajax({
		type : "POST",
		url : "member/login",
		data : data,
		success : function(data){
			$('#loginModal').modal('hide')
		}
	});

	


}
function  refreshList(){
	$.ajax({
		 headers: { 
		        Accept : "text/html; charset=utf-8",
		    },
		type : "GET",
		url : "meeting/list",
		success : function(data){
			$("#content").empty()
			$("#content").html(data)
		}
	});
}

function deleteMeeting(subject){
	$.ajax({
		type : "GET",
		url : "meeting/delete/"+$(subject).attr('db-id'),
		success : function(data){
			refreshList();
		}
	});

}


function addMember(id){
	$.ajax({
		type : "GET",
		url : "meeting/"+id+"/addMember",
		success : function(data){
			refreshList();
		}
	});

}

function removeMember(id){
	$.ajax({
		type : "GET",
		url : "meeting/"+id+"/removeMember",
		success : function(data){
			refreshList();
		}
	});

}