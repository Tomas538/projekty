<?php
    include ("connect.php");
    $meno = $_POST["meno"];
    $prie = $_POST["prie"];
    $email = $_POST["email"];
    $tel = $_POST["tel"];
    $text = $_POST["text"];
   
    $posli = $conn->prepare("insert into spravy(meno, prie, email, tel, text)
        values(?,?,?,?,?)");
    $posli->bind_param("sssss", $meno, $prie, $email, $tel, $text);
    $posli->execute();
    $posli->close();
    $conn->close();
    header("Location: http://localhost/e-shop/postkontakt.html");
?>
