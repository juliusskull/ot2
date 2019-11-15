<?php
        class Grupo_ot
        {
          private $db;
public  $id_grupo_ot;
public  $ot;
public  $id_grupo;
public  $fchalta;
 public function __CONSTRUCT()
          {
            try
            {
              $this->id_grupo_ot=0;
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
                  ->prepare("DELETE FROM grupo_ot WHERE $campo = ?");

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
                ->prepare("select * from grupo_ot where  =  ?");

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
    $sql = "INSERT INTO grupo_ot (id_grupo_ot,ot,id_grupo,fchalta)
            VALUES (?,?,?,?)";

    $this->db->prepare($sql)
         ->execute(
        array(
				$data->id_grupo_ot,$data->ot,$data->id_grupo,$data->fchalta
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
      $sql = "UPDATE grupo_ot SET
            id_grupo_ot=?,ot=?,id_grupo=?,fchalta=?
            WHERE  = ".$this->id_grupo_ot;

      $this->db->prepare($sql)
           ->execute(
            array(
					$data->id_grupo_ot,$data->ot,$data->id_grupo,$data->fchalta
                  )
          );
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
public function commit(){
    if($this->id_grupo_ot>0){
        $this->update($this);
    }else{
        $this->insert($this);
    }

    }
 public function ini($id)
  {
    try
    {
    $sql = "SELECT id_grupo_ot,ot,id_grupo,fchalta FROM grupo_ot where =$id";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);

     foreach($resultado as $value){
        $this->id_grupo_ot=$value->id_grupo_ot;
$this->ot=$value->ot;
$this->id_grupo=$value->id_grupo;
$this->fchalta=$value->fchalta;
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
    $sql = "SELECT id_grupo_ot,ot,id_grupo,fchalta FROM grupo_ot".$where;
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
    $sql = "SELECT id_grupo_ot,ot,id_grupo,fchalta FROM grupo_ot where =?";
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
