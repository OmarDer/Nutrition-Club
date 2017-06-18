<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Tutorijal 6, Zadatak 1</title>
  </head>
  <body>
    <?php
      if (isset($_REQUEST['vrijednost1'])&&isset($_REQUEST['vrijednost2'])) {
        print "<p>Poslali ste: ".(intval($_REQUEST['vrijednost1'])+intval($_REQUEST['vrijednost2']))."</p>";
      } 
    ?>
    <form action="t6z1.php" method="get">
      <p>Vrijednost 1:<br /><input type="text" name="vrijednost1"> </p>
      <p>Vrijednost 2:<br /><input type="text" name="vrijednost2"> </p>
      <input type="submit" value="Pošalji">
    </form>
  </body>
</html>
