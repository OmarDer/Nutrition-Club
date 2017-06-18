function provjera()
{
	var n;
	var ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200)
		{
			document.getElementById("potvrda").innerHTML = ajax.responseText;
			n=ajax.responseText;
		}
		if (ajax.readyState == 4 && ajax.status == 404)
			document.getElementById("potvrda").innerHTML = "Greska: nepoznat URL";
	}
	ajax.open("GET", "http://zamger.etf.unsa.ba/provjeriGrad.php?grad="+document.getElementById("textbox").value, true);
	ajax.send();
	var pom ="NOT";
	if(n.indexOf(pom) > -1)
	{
		document.getElementById("textbox").focus();
	}

}