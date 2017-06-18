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

     $brojac=0;

     foreach ($rezultat as $vijest) {
          print "<h1>".$vijest['naslov']."</h1>";
          print "<small>".$vijest['autor']."</small>";
          print "<p>".$vijest['text']."</p>";
          print "<small>".date("d.m.Y. (h:i)", $vijest['vrijeme2'])."</small>";
          print "<br>";
          print "<form action=\"t9.php\" method=\"post\">";
          print "<input type=\"text\" name=\"text\"></input>";
          print "<input type=\"submit\" name =\"button\" value=\"Potvrdi komentar\"></input>";
          print "<input type=\"hidden\" name=\"idVijest\" value=\"".$vijest['id']."\"></input>";
          print "<br>";
          print "</form>";
          $broj=$veza->query("select count(*) from komentar where vijest=".$vijest['id']);  
          $rezu=$broj->fetch();
          $brojac++;
          if ($rezu[0]===0)
          {
            print "<small> Nema komentara </small>";
          }
          else{

            print "<small>"." ".$rezu[0]." "."komentara"."</small>";
          }
  
     }

        if(isset ($_REQUEST['button']) && $_REQUEST['text'])
        {
            $veza->exec("insert into komentar set vijest='".$_REQUEST['idVijest']."', tekst='".$_REQUEST['text']."'" );
            var_dump($veza->errorInfo());
        }
    ?>
  </body>
</html>
