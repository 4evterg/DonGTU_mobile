<?php

class DB_Functions {
 
    private $conn;
 
    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $db = new Db_Connect();
        $this->conn = $db->connect();
    }
 
    // destructor
    function __destruct() {
         
    }
 
    /**
     * Storing new user
     * returns user details
     */
   /*
 public function storeUser($name, $email, $password) {
        $uuid = uniqid('', true);
        $hash = $this->hashSSHA($password);
        $encrypted_password = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"]; // salt
 
        $stmt = $this->conn->prepare("INSERT INTO users(unique_id, name, email, encrypted_password, salt, created_at) VALUES(?, ?, ?, ?, ?, NOW())");
        $stmt->bind_param("sssss", $uuid, $name, $email, $encrypted_password, $salt);
        $result = $stmt->execute();
        $stmt->close();
 
        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM users WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
 
            return $user;
        } else {
            return false;
        }
    }
   */
 
    /**
     * Get user by email and password
     */
/*    $dbusername="Unlucky";
    $dbpassword="Unlucky";
    $name = "Unlucky";
    $id = "Unlucky";
    $surname = "Unlucky";
    $r= "Unlucky";*/


    public function getUserByEmailAndPassword($login, $password) {

        $user_get = "SELECT * FROM users WHERE username = '".$login."' AND password = '".$password."'";

        //$query =mysql_query($user_get);

        $stmt = $this->conn->prepare($user_get);

        if ($stmt->execute()) {

            $user = $stmt->get_result()->fetch_assoc();
            $query = "SELECT GN FROM groups WHERE ID = '".$user["GroupID"]."' ";
            $result_group = $this->conn->prepare($query);
            $query = "SELECT KafedraName FROM kafedra WHERE ID = '".$user["Kafedra"]."' ";
            $result_cath = $this->conn->prepare($query);

          if ($result_group->execute())  {
            $group = $result_group->get_result()->fetch_assoc();               
            $result_group->close();
          }
          if ($result_cath->execute())  {          
            $cathedra  = $result_cath->get_result()->fetch_assoc();    
            $result_cath ->close();
          }
            $user["GroupID"] = $group["GN"];
            $user["Kafedra"] = $cathedra["KafedraName"];            
            $stmt->close();
                return $user;
        } 



       // $numrows=mysql_num_rows($query);
       // if($numrows!=0){
            //return $query;
       // }
       // else{
      //       return NULL;
      //  }

        // if($numrows!=0)
        //     while($row=mysql_fetch_assoc($query))
        //     {
        //         $dbusername=$row['username'];
        //         $dbpassword=$row['password'];
        //         $name = $row['Name'];
        //         $id = $row['ID'];
        //         $surname = $row['Surname'];
        //         $r=$row['PrivilegueLevel'];
        //     }

            
        
 
     /*   if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            
            // verifying user password
            //$salt = $user['salt'];
            //$encrypted_password = $user['encrypted_password'];
            //$hash = $this->checkhashSSHA($salt, $password);
            // check for password equality
           // if ($encrypted_password == $hash) {
                // user authentication details are correct
                return $user;
            //}
        } else {
            return NULL;
        }*/
    }


 /*   public function GetName(){
        return $name;
    }
    public function GetSecondName(){
        return $surname;
    }*/
 
    /**
     * Check user is existed or not
     */
/*    public function isUserExisted($email) {
        $stmt = $this->conn->prepare("SELECT email from users WHERE email = ?");
 
        $stmt->bind_param("s", $email);
 
        $stmt->execute();
 
        $stmt->store_result();
 
        if ($stmt->num_rows > 0) {
            // user existed 
            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    }*/
 
    /**
     * Encrypting password
     * @param password
     * returns salt and encrypted password
     */
   /* public function hashSSHA($password) {
 
        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
        $hash = array("salt" => $salt, "encrypted" => $encrypted);
        return $hash;
    }*/
 
    /**
     * Decrypting password
     * @param salt, password
     * returns hash string
     */
/*    public function checkhashSSHA($salt, $password) {
 
        $hash = base64_encode(sha1($password . $salt, true) . $salt);
 
        return $hash;
    }*/
 
}
 
?>