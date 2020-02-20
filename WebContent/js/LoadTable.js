$.ajax({
	type : "GET",
	url : "./LoadRegione",
	success: function(responseText){
		var json = eval('(' + responseText + ')');
		var i;
		
		if(json.length > 0)
		{
			for(i=0;i<json.length;i++)
			{
				$("#Regione").append(new Option(json[i].nome,json[i].nome));
			}
			
		}
	},
	error: function(data){
	
		alert("Impossibile Caricare Le Regioni!");
	}
});

$("#Regione").change(function(){
	
	var x = document.getElementById('Regione').value;
	
	$.ajax({
		type : "GET",
		url : "./LoadProvincia",
		data: "regione=" + x,
		success: function(responseText){
			var json = eval('(' + responseText + ')');
			var i;
			
			if(json.length > 0)
			{
				for(i=0;i<json.length;i++)
				{
					$("#Provincia").append(new Option(json[i].nome,json[i].nome));
				}
				
			}
		},
		error: function(data){
		
			alert("Impossibile Caricare Le Provincie!");
		}
	});
});

$("#Provincia").change(function(){
	
	var x = document.getElementById('Regione').value;
	var y = document.getElementById('Provincia').value;
	
	$.ajax({
		type : "GET",
		url : "./LoadComune",
		data: "regione=" + x + "&provincia=" + y,
		success: function(responseText){
			var json = eval('(' + responseText + ')');
			var i;
			
			if(json.length > 0)
			{
				for(i=0;i<json.length;i++)
				{
					$("#Comune").append(new Option(json[i].nome,json[i].nome));
				}
				
			}
		},
		error: function(data){
		
			alert("Impossibile Caricare Le Provincie!");
		}
	});
});

$("#Regione").change(function()
{
	$("#Provincia").find("option").remove().end().append('<option value=""></option>');
	$("#Comune").find("option").remove().end().append('<option value=""></option>');
});

$("#Provincia").change(function()
{
			$("#Comune").find("option").remove().end().append('<option value=""></option>');
});