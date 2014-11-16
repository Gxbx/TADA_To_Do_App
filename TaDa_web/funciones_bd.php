<?php
 
class funciones_BD {
 
    private $db;
 
    // constructor

    function __construct() {
        require_once 'connectbd.php';
        // connecting to database

        $this->db = new DB_Connect();
        $this->db->connect();

    }
 
    // destructor
    function __destruct() {
 
    }
 
    /**
     * agregar nuevo usuario
     */
    public function adduser($username, $password) {

    $result = mysql_query("INSERT INTO usuario(username,passw) VALUES('$username', '$password')");
        // check for successful store

        if ($result) {

            return true;

        } else {

            return false;
        }

    }
	
	public function crea_nota($hora,$fecha,$latitud,$longitud,$nota,$categoria) {

    $result = mysql_query("INSERT INTO notatexto(hora,fecha,latitud,longitud,nota,categoria) VALUES('$hora', '$fecha', '$latitud', '$longitud', '$nota', '$categoria')");
        // check for successful store
        if ($result) {

            return true;

        } else {

            return false;
        }

    }
 
 
     /**
     * Verificar si el usuario ya existe por el username
     */

    public function isuserexist($username) {

        $result = mysql_query("SELECT username from usuario WHERE username = '$username'");

        $num_rows = mysql_num_rows($result); //numero de filas retornadas

        if ($num_rows > 0) {	

            // el usuario existe 

            return true;
        } else {
            // no existe
            return false;
        }
    }
 
   
	public function login($user,$passw){

	$result=mysql_query("SELECT COUNT(*) FROM usuario WHERE username='$user' AND passw='$passw' "); 
	$count = mysql_fetch_row($result);
	/*como el usuario debe ser unico cuenta el numero de ocurrencias con esos datos*/
		if ($count[0]==0){
		return true;
		}else{
		return false;
		}
	}
}
 
?>
