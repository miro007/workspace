function addMeeting() {
	var form = $("#meetingForm");
	var data = $(form).serialize()
	$.ajax({
		type : "POST",
		url : "meeting/add",
		data : data,
		success : function(data){
			refreshList();
		}
	});

	$('#myModal').modal('hide')


}

function  refreshList(){
	$.ajax({
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
			$("#content").empty()
			$("#content").html(data)
			refreshList();
		}
	});

}