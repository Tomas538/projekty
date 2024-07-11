<?php
include "database.php";
$id = $_GET["id"];

if (isset($_POST["submit"])) {
  $kniha = $_POST['kniha'];
  $ISBN = $_POST['ISBN'];
  $cena = $_POST['cena'];
  $kat = $_POST['kat'];
  $autor = $_POST['autor'];

  $sql = "UPDATE `knihy` SET `kniha`='$kniha',`ISBN`='$ISBN',`cena`='$cena',`kat`='$kat',`autor`='$autor' WHERE id = $id";

  $result = mysqli_query($conn, $sql);

  if ($result) {
    header("Location: http://localhost/test/");
  } else {
    echo "Failed: " . mysqli_error($conn);
  }
}
?>


<html>
<head>
    <meta charset="utf-8">
    <title>Uprava knihy</title>
    
    <!-- Bootstrap -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    <style type="text/css">
        body {
            background: linear-gradient(to right, orange, yellow, orange);
        }
        .container {
            margin: 0 auto;
            width: 800px;
            padding-bottom: 20px;
            background-color: white;
            margin-top: 20px;
            min-height: 95vh;
        }
        h1 {
            text-align: center;
            font-size: 42px;
            margin-bottom: 0px;
        }
        table {
            background: transparent;
            border: 0px;
            width: 100%;  
        }
        td {
            padding: 20px;
        }
        .in input {
            background: linear-gradient(to right, white, orange, white);
            border-radius: 6px;
            width: 97.5%;
            height: 30px;
            border: 2px solid grey;
            padding-left: 6px;
        }
        input:hover {
            background: linear-gradient(to right, orange, white, orange);
        }
        .in select {
            background: linear-gradient(to right, white, orange, white);
            width: 97.5%;
            height: 30px;
            border: 2px solid grey;
            padding-left: 2px;
            border-radius: 6px;
        }
        select:hover {
            background: linear-gradient(to right, orange, white, orange);
        }
        hr {
            margin-top: 50px;
            margin-bottom: 50px;
            height: 2px;
            background-color: grey;
        }
        .ex {
            font-weight: bold;
        }
        div.ex tr:nth-child(even) {  
            background-color: lightgray;  
        }  
        div.ex table { 
            border: 1px solid black; 
            background-color: bisque;
            table-layout: auto;
            width: 97%;
        }
        div.ex th, td {
            padding: 10px;
        }
        a {
            color: black;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <br>
        <h1>Editácia knihy</h1>
        <br>
        <?php
        $sql = "SELECT * FROM `knihy` WHERE id = $id LIMIT 1";
        $result = mysqli_query($conn, $sql);
        $row = mysqli_fetch_assoc($result);
        ?>

        <div class="in">
            <form action="" method="post" name="myform">
                <table>
                    <tr><td colspan="3"><input style="width: 99%;" type="text" value="<?php echo $row['kniha'] ?>" name="kniha" id="kniha"></td></tr>
                    <tr>
                        <td><input type="number" value="<?php echo $row['ISBN'] ?>" name="ISBN" id="ISBN"></td>
                        <td style="width: 80px;"></td>
                        <td><input type="number" value="<?php echo $row['cena'] ?>" step="0.01" name="cena" id="cena"></td>
                    </tr>
                    <tr>
                        <td>
                            <select name="kat" id="kat" value="<?php echo $row['kat'] ?>">
                                <option>Biografia</option>
                                <option>Detektívka</option>
                                <option>Deti</option>
                                <option>Dobrodružné</option>
                                <option>Dráma</option>
                                <option>Fantasy</option>
                                <option>Hobby</option>
                                <option>Informatika</option>
                                <option>Náučné</option>
                                <option>Poézia</option>
                                <option>Recepty</option>
                                <option>Reportáž</option>
                                <option>Rodina</option>
                                <option>Romantika</option>
                                <option>Román</option>
                                <option>Sci-fi</option>
                                <option>Triler</option>
                                <option>Zdravie</option>
                            </select>
                        </td>
                        <td></td>
                        <td><input type="text" value="<?php echo $row['autor'] ?>" name="autor" id="autor"></td>
                    </tr>
                        <tr>
                        <td><button type="submit" name="submit" class="btn btn-success">Upraviť</button>
                        <a href="index.php" class="btn btn-danger">Zrušiť</a></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>