<?php
    include ("database.php");
    $kniha = $_POST["kniha"];
    $ISBN = $_POST["ISBN"];
    $cena = $_POST["cena"];
    $kat = $_POST["kat"];
    $autor = $_POST["autor"];
   
    $posli = $conn->prepare("insert into knihy(kniha, ISBN, cena, kat, autor)
        values(?,?,?,?,?)");
    $posli->bind_param("sssss", $kniha, $ISBN, $cena, $kat, $autor);
    $posli->execute();
    $posli->close();
    $conn->close();
    header("Location: http://localhost/test/");
?>
