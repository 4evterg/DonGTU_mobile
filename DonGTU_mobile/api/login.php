<?php
require_once 'DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['login']) && isset($_POST['password'])) {
 
    // receiving the post params
    $login = $_POST['login'];
    $password = $_POST['password'];
 
    // get the user by email and password
    $user = $db->getUserByEmailAndPassword($login, $password);
 
    if ($user != false) {
        // use is found
/*        $query = "SELECT GN FROM groups WHERE ID = '".$user["GroupID"]."' ";
        $result = mysqli_query($conn, $query);
        $group = mysqli_fetch_assoc($result);
        // $querygroup = mysql_query("SELECT GN FROM groups WHERE ID = '".$user["GroupID"]."' ");
        // $group = mysql_fetch_field($querygroup);
        $query = "SELECT KafedraName FROM kafedra WHERE ID = '".$user["Kafedra"]."' ";
        $result = mysqli_query($conn, $query);
        $cathedra = mysqli_fetch_assoc($result);*/
        //$querycath = mysql_query("SELECT KafedraName FROM kafedra WHERE ID = '".$user["Kafedra"]."' ");
        //$cathedra = mysql_fetch_field($querycath);

        /*$user_get_cathedra = "SELECT KafedraName FROM kafedra WHERE ID = '".$user["Kafedra"]."' ";
        $user_get_group = "SELECT GN FROM groups WHERE ID = '".$user["GroupID"]."' ";*/

        $response["error"] = FALSE;
        $response["user"]["uid"] = $user["ID"];
        $response["user"]["user_nickname"] = $user["username"];
        /*  $response["user"]["priv_lvl"] = $user["PrivilegueLevel"]; */               
        $response["user"]["name"] = $user["Name"];
        $response["user"]["second_name"] = $user["Surname"];
        $response["user"]["third_name"] = $user["ThirdName"];
       /* $response["user"]["photo"] = "http://192.168.0.102/do/img/avatars/".$user["Photo"];
        $response["user"]["group_id"] = $user["GroupID"];
        $response["user"]["user_kafedra"] = $user["Kafedra"];
        $response["user"]["email"] = $user["email"];*/        
        $response["user"]["city"] = $user["City"];
        $response["user"]["country"] = $user["Country"];
        $response["user"]["cathedra"] = $user["Kafedra"];
        $response["user"]["group"] = $user["GroupID"];
        $response["user"]["post"] = $user["PrivilegueClass"];

        echo json_encode($response);
    } else {
        // user is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg"] = "Login credentials are wrong. Please try again!";
        echo json_encode($response);
    }
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters login or password is missing!!";
    echo json_encode($response);
}
?>