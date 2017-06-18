function provjeriDrzave()
{
	var n;
	var ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200)
		{
			document.getElementById("Postavi").innerHTML = ajax.responseText;
			n=ajax.responseText;
		}
		if (ajax.readyState == 4 && ajax.status == 404)
			document.getElementById("Postavi").innerHTML = "Greska: nepoznat URL";
	}
	ajax.open("GET", "https://restcountries.eu/rest/v1/alpha?codes="+document.getElementById("Drzava").value, true);
	ajax.send();
	var pom ="NOT";
	if(n.indexOf(pom) > -1)
	{
		document.getElementById("Drzava").focus();
	}

}