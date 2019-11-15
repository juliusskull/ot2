<?php 
require_once '../Twig/Autoloader.php';require_once '../db/database.php';

    function get_estados(){
        $json_data = file_get_contents( '../asset/estados.json');
        return json_decode($json_data);
    }
    
