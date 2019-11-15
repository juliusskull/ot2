<?php
        class Novedades
        {
          private $db;
public  $id_novedad;
public  $sucursal_id;
public  $moneda;
public  $tipo;
 public function __CONSTRUCT()
          {
            try
            {
              $this->id_novedad=0;
              $this->db = Database::Conectar();
            }
            catch(Exception $e)
            {
              die($e->getMessage());
            }
          }
public function delete($campo, $valor)
  {
    try
    {
      $stm = $this->db
                  ->prepare("DELETE FROM novedades WHERE $campo = ?");

      $stm->execute(array($valor));
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
  public function isExiste($valor)
  {
    try
    {
      $stm = $this->db
                ->prepare("select * from novedades where id_novedad =  ?");

      $stm->execute(array($valor));
		if ( $stm->rowCount()>0){
			return true;
		}else{
			return false;
		}
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
public function insert($data)
  {
    try
    {
    $sql = "INSERT INTO novedades (id_novedad,sucursal_id,moneda,tipo)
            VALUES (?,?,?,?)";

    $this->db->prepare($sql)
         ->execute(
        array(
				$data->id_novedad,$data->sucursal_id,$data->moneda,$data->tipo
              )
      );
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
 public function update($data)
  {
    try
    {
      $sql = "UPDATE novedades SET
            sucursal_id=?,moneda=?,tipo=?
            WHERE id_novedad = ".$this->id_novedad;

      $this->db->prepare($sql)
           ->execute(
            array(
					$data->sucursal_id,$data->moneda,$data->tipo
                  )
          );
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
public function commit(){
    if($this->id_novedad>0){
        $this->update($this);
    }else{
        $this->insert($this);
    }

    }
 public function ini($id)
  {
    try
    {
    $sql = "SELECT id_novedad,sucursal_id,moneda,tipo FROM novedades where id_novedad=$id";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);

     foreach($resultado as $value){
        $this->id_novedad=$value->id_novedad;
$this->sucursal_id=$value->sucursal_id;
$this->moneda=$value->moneda;
$this->tipo=$value->tipo;
     }

    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
 public function getAll($w=null)
  {
    $where="";
    if(isset($w)){
        $where=" where ".$w;
    }
    try
    {
    $sql = "SELECT id_novedad,sucursal_id,moneda,tipo FROM novedades".$where;
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

    $resultado = $gsent->fetchALL(PDO::FETCH_ASSOC);
    $rows = array();

    foreach($resultado as $value){

        $rows[] = $value;
    }

    return $rows;

    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
 public function getAll_json()
  {
        echo json_encode($this->getAll());
  }
 public function getOne($id)
  {
    try
    {
    $sql = "SELECT id_novedad,sucursal_id,moneda,tipo FROM novedades where id_novedad=?";
    $gsent = $this->db->prepare($sql);
    $gsent->execute(array($id));

    $gsent->execute();

    $resultado = $gsent->fetchALL(PDO::FETCH_ASSOC);
    $rows = array();

    foreach($resultado as $value){

        $rows[] = $value;
    }

    return $rows;

    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
} ?>
