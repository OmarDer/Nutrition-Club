<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Tutorijal 7, Zadatak1</title>
  </head>
  <body>
    <?php
    
	if(isset($_REQUEST['Mijenjaj']))
	{
		$ime=$_POST['imeN'];
		$prezime=$_POST['prezimeN'];
		$broj=$_POST['brojN'];
		$red=$_POST['redN'];
		$novi="";
		$redovi=file("imenik.csv");
		$brojac2=0;
		foreach($redovi as $r)
		{
			if($brojac2 == $red)
			{
				$r=$ime.','.$prezime.','.$broj."\n";
			}
			
			$novi=$novi.$r;
			$brojac2++;
		}
		file_put_contents("imenik.csv",$novi);
	}
	
	if(isset($_REQUEST['edit']))
	{
		$red=$_POST['red'];

		print "<form action=\"Tutorijal7.php\" method=\"post\">
		<p>
		Novo ime:
		</p>
         <input type=\"text\" name=\"imeN\" value=\"\">
		 <p> 
		 Novo prezime:
		 </p>
		<input type=\"text\" name=\"prezimeN\" value=\"\">
		
		<p>
		broj:
		</p>
		<input type=\"text\" name=\"brojN\" value=\"\">
		<input type=\"hidden\" name=\"redN\" value=\"$red\">
		
	   <input type=\"submit\" name=\"Mijenjaj\" value=\"Promijeni\">
	   </form>";
	}
	
	if(isset($_POST['slanjeNovog']))
	{
		$ime=$_POST['ime'];
		$prezime=$_POST['prezime'];
		$broj=$_POST['broj'];
		file_put_contents("imenik.csv",$ime.','.$prezime.','.$broj."\n", FILE_APPEND);
	}
	
	if(isset($_POST['del']))
	{
		$red=$_POST['red'];
		$novi="";
		$redovi=file("imenik.csv");
		$brojac1=0;
		foreach($redovi as $r)
		{
			if($brojac1!=$red)
				{
					$novi=$novi.$r;
				}

				$brojac1++;	
		file_put_contents("imenik.csv",$novi);
		}
	}
	
	$redovi = file("imenik.csv");
	print "<table>";
	print "<TR><TH>Ime</TH><TH>Prezime</TH><TH>Broj</TH><TH>Akcija</TH></TR>";
	$brojac=0;
	foreach($redovi as $r)
	{
		print "<TR>";
		$celije=explode(',',$r);
		print "</TR>";
		foreach($celije as $c){
		print "<td>".$c."</td>";}	
		print "<td> <form action= \"Tutorijal7.php\" method=\"post\">
		<input type=\"hidden\" name=\"red\" value=\"$brojac\">
		<input type=\"submit\" name=\"edit\" value=\"E\">
      	<input type=\"submit\" name=\"del\" value=\"X\">
     	</form></td>";  
  		$brojac++;
  		
	
}
print "</table>";
	  
    ?>
    <form action="Tutorijal7.php" method="post">
      <p>
	  Ime:
	  </p>
      <input type="text" name="ime" value=""> 
	   <p>
	  Prezime:
	  </p>
	  <input type="text" name="prezime" value="">
	  <p>
	  Broj:
	  </p>
	  <input type="text" name="broj" value="">
	   <input type="submit" name="slanjeNovog" value="Posalji">
	  
    </form>
  </body>
</html>