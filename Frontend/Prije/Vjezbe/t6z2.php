
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Tutorijal 6, Zadatak2</title>
  </head>
  <body>
    <?php
      if (isset($_REQUEST['vrijednost'])) {
      	 $a = htmlEntities($_GET['vrijednost'], ENT_QUOTES);
        print "<p>Poslali ste: ".$a."</p>";
      } 

    ?>
    <form action="t6z2.php" method="get">
      <p>Vrijednost:<br /><input type="text" name="vrijednost"> </p>
      <input type="submit" value="Pošalji">
    </form>
  </body>
</html>
