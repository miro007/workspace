function klik(){
	 $.ajax({
		 url: "data.json",
		 type: "POST",
		 contentType: 'application/json',
		 data: JSON.stringify({id : 1}),
		 dataType: "JSON",
		 success: function(result) {
	 	    alert(result);
	       }
		 });

	 $.ajax({
		 url: "template",
		 type: "POST",
		 contentType: 'application/json',
		 data: JSON.stringify({id : 1,name:'mirek'}),
		 success: function(result) {
			$("body").append(result);
	       },
	       error :function(err){
	    	   alert('err ='+err)
	       }
		 });

}

function loadPartial(){
	var appPane = $('#AppPane');//JQuery request for the app pane element.
	appPane.html(data);//The dynamically loaded data
	$compile(appPane.contents())($scope);
}
