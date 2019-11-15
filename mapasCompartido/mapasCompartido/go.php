<?php	
		
		if ( isset($_GET['password1']) && isset($_GET['distrito']) && isset($_GET['gerencia']) )
		{
			session_start();				
			$_SESSION['pass']=$_GET['password1'];								
			$_SESSION['distrito']=$_GET['distrito'];								
			$_SESSION['gerencia']=$_GET['gerencia'];	
			$dominio = 'Location:http://'.$_GET['url'].'index.php';						
			//echo 'Location:'.$dominio.'index.php';
			//header('location:http://localhost/mapasRuta/index.php');				
			header($dominio);				
			//header('Location:'.'index.php');				
		} else {
			//header('Location:'.$dominio'/index.php');	
			//header('Location: ' . $_SERVER['HTTP_REFERER']);			
			header("location:javascript://history.go(-1)");
		}
?>