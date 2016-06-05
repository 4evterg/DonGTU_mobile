<?php
class DB_Connect {
    private $conn;
 
    // Connecting to database
    public function connect() {
        define("DB_SERVER", "localhost");
        define("DB_USER", "root");
        define("DB_PASS", "");
        define("DB_NAME", "korot149_test");

         
        // Connecting to mysql database
        $this->conn = new mysqli(DB_SERVER, DB_USER, DB_PASS, DB_NAME);
        //фиксим ошибку с вопросительными знаками
        mysqli_set_charset($this->conn, "utf8");
        // return database handler
        return $this->conn;
    }
}
 
?>