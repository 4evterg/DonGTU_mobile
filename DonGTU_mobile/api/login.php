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

        $response["error"] = FALSE;
        $response["user"]["uid"] = $user["ID"];
        $response["user"]["user_nickname"] = $user["username"];
        $response["user"]["priv_class"] = $user["PrivilegueClass"];
        $response["user"]["priv_lvl"] = $user["PrivilegueLevel"];                
        $response["user"]["name"] = $user["Name"];
        $response["user"]["second_name"] = $user["Surname"];
        $response["user"]["third_name"] = $user["ThirdName"];
        $response["user"]["photo"] = "http://192.168.0.102/do/img/avatars/".$user["Photo"];
        $response["user"]["group_id"] = $user["GroupID"];
        $response["user"]["user_kafedra"] = $user["Kafedra"];
        $response["user"]["email"] = $user["email"];        
        $response["user"]["city"] = $user["City"];
        $response["user"]["country"] = $user["Country"];

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