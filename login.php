<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 24/01/18
 * Time: 10:05
 */
session_start();
$_SESSION['count'] = 0;
$_SESSION['usuario']=null;
header('Location: index.php');