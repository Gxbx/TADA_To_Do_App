<?php
/*CREAR_NOTA*/
$hora		= $_POST['hora'];
$fecha		= $_POST['fecha'];
$latitud	= $_POST['latitud'];
$longitud	= $_POST['longitud'];
$nota		= $_POST['nota'];
$categoria	= $_POST['categoria'];

require_once 'funciones_bd.php';
$db = new funciones_BD();

	if($db->crea_nota($hora,$fecha,$latitud,$longitud,$nota,$categoria)){
		$resultado[]=array("logstatus"=>"0");
	}else{
		$resultado[]=array("logstatus"=>"1");
	}

echo json_encode($resultado);
?>
