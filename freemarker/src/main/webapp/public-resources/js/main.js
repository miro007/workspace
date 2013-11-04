
function showDeleteConfirmDialog(action){
	$('#deleteConfirmDialog').modal()
	$('#deleteConfirmAction').attr('href', action)
}
