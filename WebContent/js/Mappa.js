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
	var latitude = position.coords.latitude;
	var longitude = position.coords.longitude;
	
	var first = Number(getUrlVars()["lat"]);
	var second = Number(getUrlVars()["lon"]);
	
	var punto = new google.maps.LatLng(latitude , longitude);
	var punto1 = new google.maps.LatLng(first , second);
	
	options={
	zoom: 14.5,
	center: punto1,
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
    
	marker1 = new google.maps.Marker(
	{position:punto1,
	map:map,
	icon: pinImage,
	title:"Questa e' la tua destinazione"});
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}