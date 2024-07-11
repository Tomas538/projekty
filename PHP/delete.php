<?php
include ("database.php");

$id = $_GET["id"];
$sql = "DELETE FROM `knihy` WHERE id = $id";
$result = mysqli_query($conn, $sql);

if ($result) {
  header("Location: http://localhost/test/");
} else {
  echo "Failed: " . mysqli_error($conn);
}
?>