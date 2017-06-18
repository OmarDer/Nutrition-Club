<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Tutorijal 7, Zadatak1</title>
  </head>
  <body>
    <?php
    //Treba dodati mjenjanje
    //Treba dodati dodavanje i brisanje
    //MOra se dodavati hidden koji ce biti brojac i koji ce pratiti koji je red u pitanju
    //isset($_POST)


    if(isset(_POST["edit"]))
    {

    }


     $redovi=file(imenik.csv);
     print "<table>";
     print "<tr><th>Ime</th><th>Prezime</th><th>Broj</th><th>Akcija</th></tr>";
     $brojac=0;
     foreach ($redovi as $r){
      print "<tr>";
      $celije=explode(",",$r);
      foreach($celije as $c){

      print "<td>".$c."</td>";
      print "<td><form action=\"t7z1.php\" method=\"post\">
      <input type=\"hidden\" name=\"red\" value=\"(string)$brojac\" >
      <input type=\"submit\" name=\"edit\" value=\"X\">
      <input type=\"submit\" name=\"del\" value=\"E\">
     </form>"
      $brojac++;


        }
     }

     print "</table>"

    ?>
    <form action="t7z1.php" method="post">
      <input type="text" name="ime" >
      <input type="text" name="prezime" >
      <input type="text" name="broj" >
      <input type="submit" value="slanjeNovog">
    </form>
  </body>
</html>

