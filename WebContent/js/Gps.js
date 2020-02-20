function init()
{
	if(navigator.geolocation)
	{
		navigator.geolocation.getCurrentPosition(showMaps , getError);
	}
	else
	{
		alert("Geolocalizzazione non supportata dal browser");
	}
}

function getError(error)
{
	switch(error.code)
	{
		case 0: 
		document.getElementById("status").innerHTML = "Errore sconosciuto";
		break;
		case 1:
		document.getElementById("status").innerHTML = "Richiesta rifiutata dall'utente";
		break;
		case 2:
		document.getElementById("status").innerHTML = "La posizione non può essere determinata";
		break;
		case 3: 
		document.getElementById("status").innerHTML = "Timeout Scaduto";
		break;
	}
}

function showMaps(position)
{
	var lat = position.coords.latitude;
	var lon = position.coords.longitude;
	
	var punto = new google.maps.LatLng(lat , lon);
	
	options={
			zoom: 14.5,
			center: punto,
			mapTypeId:google.maps.MapTypeId.ROADMAP
	}
	
	map_div=document.getElementById("mdiv");
	map = new google.maps.Map(mdiv,options);
	
	marker = new google.maps.Marker(
	{position:punto,
	map:map,
	title:"Questa e' la tua posizione"});
	
	var pinColor = "0000FF";
    var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
    new google.maps.Size(21, 34),
    new google.maps.Point(0,0),
    new google.maps.Point(10, 34));
    
	$.ajax({
		type : "GET",
		url : "./SearchByPosition",
		data: "lon=" + lon + "&lat=" + lat,
		success: function(responseText)
		{
			var json = eval('(' + responseText + ')');
			var i;
			
			for(i=0;i<json.length;i++)
			{
				if(json[i].latitudine > 0.0 && json[i].longitudine > 0.0)
				{
					var punto1 = new google.maps.LatLng(json[i].latitudine, json[i].longitudine);
				
					marker1 = new google.maps.Marker(
					{position:punto1,
					map:map,
					icon: pinImage,
					title: "" + json[i].denominazione + " \n" + json[i].indirizzo});
				}
			}
		},
		error: function(data){
		
			alert("Impossibile mostrare i risultati!");
		}
	});
}