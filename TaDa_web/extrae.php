<?php

require_once 'config.php';
require_once 'funciones_bd.php';
$db = new funciones_BD();

	
	$result = mysql_query("SELECT * FROM notatexto");
        $num_rows = mysql_num_rows($result); //numero de filas retornadas
        if ($num_rows > 0){
			
			$myarray = array();
            while($tabla = mysql_fetch_assoc($result)){
				$myarray[] = $tabla;
			}
        }else{
           echo'no esta trayendo nada de la BD';
        } 
echo json_encode($myarray);
?>
