<?php

include('database.php'); 

$searchTerm = $_GET['term'];
$sql = "SELECT * FROM knihy WHERE autor LIKE '%".$searchTerm."%'"; 
$results = $conn->query($sql); 
if ($results->num_rows > 0) {
  $tutorialData = array(); 
  while($row = $results->fetch_assoc()) {

   $data['id']    = $row['id']; 
   $data['value'] = $row['autor'];
   array_push($tutorialData, $data);
} 
}
 echo json_encode($tutorialData);
?>