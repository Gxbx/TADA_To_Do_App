<?php
$usuario = $_POST['usuario'];
$passw = $_POST['password'];

require_once 'funciones_bd.php';
$db = new funciones_BD();

	if($db->isuserexist($usuario,$passw)){
		$resultado[]=array("logstatus"=>"2");
		echo json_encode($resultado);
	}else{

		if($db->adduser($usuario,$passw)){
		$resultado[]=array("logstatus"=>"0");
		}else{
		$resultado[]=array("logstatus"=>"1");
		}
echo json_encode($resultado);
	}
?>



