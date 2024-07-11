<?php
include ("database.php");

if(!empty($_POST["ISBN"])) {
  $query = "SELECT * FROM knihy WHERE ISBN='" . $_POST["ISBN"] . "'";
  $result = mysqli_query($conn,$query);
  $count = mysqli_num_rows($result);
  if($count>0) {
    echo "<span style='color:red'> Kniha už je v zozname!</span>";
    echo "<script>$('#submit').prop('disabled',true);</script>";
  }else{
    echo "<script>$('#submit').prop('disabled',false);</script>";
  }
}
?>