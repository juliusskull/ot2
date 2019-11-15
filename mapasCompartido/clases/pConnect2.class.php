<?php
class DatabaseConnection
{
  public static function get()
  {
    static $db = null;
    if ( $db == null )
    {
      if($db = new DatabaseConnection())
      {	return $db;}
      else
      { return false;}
    }
    else
    {
    	return $db;
    }	
  }

 private $_handle = null;

  private function __construct()
  {	
		
		$connect=oci_connect('HMONTESINO', '2018horacio','gesp');
		if(!$connect)		
  		{
			echo "error en la conexion";
  			return false;
  		}
		$this->_handle =$connect;
		return true;
	
  }
  
  public function handle()
  {
    return $this->_handle;
  }
}

Class Query
{
	private $query;	
	function executeQuery($query)
	{
		
		$connection=DatabaseConnection::get()->handle();
		$this->query= oci_parse($connection,$query);
		oci_execute($this->query);
		return 	$this->query;	
	}

}

Class QueryBind
{
  private $queryBind; 
  private $queryParse;


  function parse($queryBind)
  {    
    $connection=DatabaseConnection::get()->handle();
    $this->queryParse= oci_parse($connection,$queryBind);    
    return  $this->queryParse; 
  }

  function bindVariable($bind,$value)
  {    
    
    oci_bind_by_name($this->queryParse, ":".$bind, $value);    
    return  $this->queryParse; 
  }

  function executeQuery()
  {
    
    oci_execute($this->queryParse);
    return  $this->queryParse; 
  }

}


?>