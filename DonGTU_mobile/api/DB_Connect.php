<?php
class DB_Connect {
    private $conn;
 
    // Connecting to database
    public function connect() {
        require_once 'constants.php';
         
        // Connecting to mysql database
        $this->conn = new mysqli(DB_SERVER, DB_USER, DB_PASS, DB_NAME);
        
        // return database handler
        return $this->conn;
    }
}
 
?>