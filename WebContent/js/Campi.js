function ValidateForm()
{
	var regione = new String(document.forms["SearchByFields"]["regione"].value);
	var provincia = new String(document.forms["SearchByFields"]["provincia"].value);
	var comune = new String(document.forms["SearchByFields"]["comune"].value);
	var radio = new String(document.forms["SearchByFields"]["linguaggio"].value);
	
	if (regione == null || regione.trim() == "") 
	{
        alert("Selezionare la Regione!");
        return false;
    }
	
	if (provincia == null || provincia.trim() == "") 
	{
        alert("Selezionare la Provincia!");
        return false;
    }
	
	if (comune == null || comune.trim() == "") 
	{
        alert("Selezionare il Comune!");
        return false;
    }
	
	if (radio == null || radio.trim() == "") 
	{
        alert("Selezionare il tipo di ricerca!");
        return false;
    }
}