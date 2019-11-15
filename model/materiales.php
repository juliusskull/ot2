<?php
        class Materiales
        {
          private $db;
public  $id_material;
public  $codigo;
public  $desc_material;
public  $observacion;
public  $umb;
 public function __CONSTRUCT()
          {
            try
            {
              $this->id_material=0;
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
                  ->prepare("DELETE FROM materiales WHERE $campo = ?");

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
                ->prepare("select * from materiales where id_material =  ?");

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
    $sql = "INSERT INTO materiales (id_material,codigo,desc_material,observacion,umb)
            VALUES (?,?,?,?,?)";

    $this->db->prepare($sql)
         ->execute(
        array(
				$data->id_material,$data->codigo,$data->desc_material,$data->observacion,$data->umb
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
      $sql = "UPDATE materiales SET
            codigo=?,desc_material=?,observacion=?,umb=?
            WHERE id_material = ".$this->id_material;

      $this->db->prepare($sql)
           ->execute(
            array(
					$data->codigo,$data->desc_material,$data->observacion,$data->umb
                  )
          );
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
public function commit(){
    if($this->id_material>0){
        $this->update($this);
    }else{
        $this->insert($this);
    }

    }
 public function ini($id)
  {
    try
    {
    $sql = "SELECT id_material,codigo,desc_material,observacion,umb FROM materiales where id_material=$id";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);

     foreach($resultado as $value){
        $this->id_material=$value->id_material;
$this->codigo=$value->codigo;
$this->desc_material=$value->desc_material;
$this->observacion=$value->observacion;
$this->umb=$value->umb;
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
    $sql = "SELECT id_material,codigo,desc_material,observacion,umb FROM materiales".$where;
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
    $sql = "SELECT id_material,codigo,desc_material,observacion,umb FROM materiales where id_material=?";
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
