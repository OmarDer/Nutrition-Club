<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Tutorijal 8, Uvod</title>
  </head>
  <body>
    <h1>Vijesti</h1>
    <?php
     $veza = new PDO("mysql:dbname=wt8;host=localhost;port=3307;charset=utf8", "wt8user", "wt8pass");
     $veza->exec("set names utf8");
     $rezultat = $veza->query("select id, naslov, text, UNIX_TIMESTAMP(vrijeme) vrijeme2, autor from vijest order by vrijeme desc");

if (!$rezultat) {
          $greska = $veza->errorInfo();
          print "SQL greška: " . $greska[2];
          exit();
     }


     foreach ($rezultat as $vijest) {
          print "<h1>".$vijest['naslov']."</h1>";
          print "<small>".$vijest['autor']."</small>";
          print "<p>".$vijest['text']."</p>";
          print "<small>".date("d.m.Y. (h:i)", $vijest['vrijeme2'])."</small>";
          print "<br>";
          $broj=$veza->query("select count(*) from komentar where vijest=".$vijest['id']);  
          $rezu=$broj->fetch();
		  var_dump($broj1);
          if ($rezu[0]===0)
          {
            print "<small> Nema komentara </small>";
          }
          else{

            print "<small>"." ".$rezu[0]." "."komentara"."</small>";
          }
  
     }
    ?>
  </body>
</html>
