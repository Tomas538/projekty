<!DOCTYPE html>

<?php 
include ("sort.php");
include ("database.php");
?>
<html>
<head>
    <meta charset="utf-8">
    <title>Knižnica</title>
    <!-- arrows/trashcan -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    
    <style type="text/css">
        body {
            background: lightgrey;
        }
        .container {
            margin: 0 auto;
            width: 800px;
            padding-bottom: 20px;
            background-color: white;
        }
        h1 {
            text-align: center;
            font-size: 42px;
            margin-bottom: 0px;
        }
        table {
            width: 100%;  
            background: transparent;
        }
        td {
            padding: 20px;
        }
        .in input {
            background: linear-gradient(to right, white, yellow, white);
            border-radius: 6px;
            width: 97.5%;
            height: 30px;
            border: 2px solid grey;
            padding-left: 6px;
        }
        input:hover {
            background: linear-gradient(to right, yellow, white, yellow);
        }
        .in select {
            background: linear-gradient(to right, white, yellow, white);
            width: 101.5%;
            height: 35px;
            border: 2px solid grey;
            padding-left: 2px;
            border-radius: 6px;
        }
        select:hover {
            background: linear-gradient(to right, yellow, white, yellow);
        }
        hr {
            border: 0px;
            margin-top: 50px;
            margin-bottom: 50px;
            height: 3px;
            background-color: grey;
        }
        .ex {
            font-weight: bold;
        }
        div.ex tr:nth-child(even) {  
            background-color: lightgray;  
        }  
        div.ex table { 
            background-color: white;
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
        .banner {
            background-color: lightseagreen;
            position: relative;
            width: 100%;
            margin-top: -10px;
        }
        .banner-text {
            margin: 0 auto;
            text-align: center;
            color: black;
            word-spacing: 4px;
            line-height: 2;
        }
    </style>
</head>

<body>
    <div class="container">
        <h1>Knižnica</h1>
        <div class="in">
            <form action="send.php" method="post" name="myform">
                <table>
                    <tr><td colspan="3"><input style="width: 99%;" type="text" placeholder="Názov knihy" name="kniha" id="kniha" required></td></tr>
                    <tr>
                        <td><input type="number" placeholder="ISBN" name="ISBN" id="ISBN" onInput="checkISBN()" required></td>
                        <td style="width: 80px;"></td>
                        <td><input type="number" placeholder="Cena v €" step="0.01" name="cena" id="cena" required></td>
                    </tr>
                    <tr>
                        <td>
                            <select name="kat" id="kat" required>
                                <option disabled="disabled" selected="selected">Kategória</option>
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
                        <td><input type="text" placeholder="Autor" name="autor" id="autor" required></td>
                    </tr>
                    <tr>
                        <td>* všetky polia sú povinné</td>
                        <td></td>
                        <td><input id="submit" type="submit" style="width: 101.6%; height: 30px; border-radius: 8px; border-bottom: 3px solid grey;" value="Pridať knihu do knižnice"></td>
                    </tr>
                    <tr><td><span id="check-ISBN"></span></td></tr>
                </table>
            </form>
        </div>

                                        <hr>  

        <div class="ex">
            <table align="center" cellspacing="0">
                <thead>
                <tr style="background-color:lightseagreen;";>
                        <th><a href="index.php?column=kniha&order=<?php echo $asc_or_desc; ?>">Názov knihy<i class="fas fa-sort<?php echo $column == 'kniha' ? '-' . $up_or_down : ''; ?>"></i></a></th>
                        <th>ISBN</th>
                        <th><a href="index.php?column=cena&order=<?php echo $asc_or_desc; ?>">Cena<i class="fas fa-sort<?php echo $column == 'cena' ? '-' . $up_or_down : ''; ?>"></i></a></th>
                        <th style="width: 80px;"><a href="index.php?column=kat&order=<?php echo $asc_or_desc; ?>">Kategória<i class="fas fa-sort<?php echo $column == 'cena' ? '-' . $up_or_down : ''; ?>"></i></a></th>
                        <th><a href="index.php?column=autor&order=<?php echo $asc_or_desc; ?>">Autor<i class="fas fa-sort<?php echo $column == 'cena' ? '-' . $up_or_down : ''; ?>"></i></a></th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                    <?php while ($row = $result->fetch_assoc()): ?>
                    <tr>
                        <td><?php echo $column == 'kniha' ? "" : ''; ?><?php echo $row['kniha']; ?></td>
                        <td><?php echo $column == 'ISBN' ? "" : ''; ?><?php echo $row['ISBN']; ?></td>
                        <td><?php echo $column == 'cena' ? "" : ''; ?><?php echo $row['cena']." €"; ?></td>
                        <td><?php echo $column == 'kat' ? "" : ''; ?><?php echo $row['kat']; ?></td>
                        <td><?php echo $column == 'autor' ? "" : ''; ?><?php echo $row['autor']; ?></td>
                        <td>
                            <a href="edit.php?id=<?php echo $row["id"] ?>"><i class="fa-solid fa-pen-to-square fs-5 me-3"></i></a>
                            <a href="delete.php?id=<?php echo $row["id"]; ?>"><i class="fa-solid fa-trash fs-5"></i></a>
                        </td>
                    </tr>
                    <?php endwhile; ?>
                </tbody>
                <tfoot style="background-color: lightseagreen;">
                        <tr><td colspan="5" style="padding:0px; padding-left: 10px">
                        <span id="date"></span>
                        <script src="date.js"></script>
                        </td>
                        <td style="padding:0px; padding-right: 10px">
                        <span id="time"></span>
                        <script src="date.js"></script>
                        </td>
                        </tr>
                </tfoot>
            </table>
        </div>
    </div>
    
<!-- autocomplete -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="conn.js"></script>
 
<!-- check duplicity -->
<script>
function checkISBN() {
    jQuery.ajax({
    url: "check.php",
    data:'ISBN='+$("#ISBN").val(),
    type: "POST",
    success:function(data){
        $("#check-ISBN").html(data);
    },
    error:function (){}
    });
}
</script>

</body>
</html>