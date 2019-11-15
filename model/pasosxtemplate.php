<?php
        class Pasosxtemplate
        {
          private $db;
public  $id_pasosxtemplate;
public  $id_paso;
public  $id_template;
 public function __CONSTRUCT()
          {
            try
            {
              $this->id_pasosxtemplate=0;
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
                  ->prepare("DELETE FROM pasosxtemplate WHERE $campo = ?");

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
                ->prepare("select * from pasosxtemplate where id_pasosxtemplate =  ?");

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
    $sql = "INSERT INTO pasosxtemplate (id_pasosxtemplate,id_paso,id_template)
            VALUES (?,?,?)";

    $this->db->prepare($sql)
         ->execute(
        array(
				$data->id_pasosxtemplate,$data->id_paso,$data->id_template
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
      $sql = "UPDATE pasosxtemplate SET
            id_paso=?,id_template=?
            WHERE id_pasosxtemplate = ".$this->id_pasosxtemplate;

      $this->db->prepare($sql)
           ->execute(
            array(
					$data->id_paso,$data->id_template
                  )
          );
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
public function commit(){
    if($this->id_pasosxtemplate>0){
        $this->update($this);
    }else{
        $this->insert($this);
    }

    }
 public function ini($id)
  {
    try
    {
    $sql = "SELECT id_pasosxtemplate,id_paso,id_template FROM pasosxtemplate where id_pasosxtemplate=$id";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);

     foreach($resultado as $value){
        $this->id_pasosxtemplate=$value->id_pasosxtemplate;
$this->id_paso=$value->id_paso;
$this->id_template=$value->id_template;
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
    $sql = "SELECT id_pasosxtemplate,id_paso,id_template,titulo,desc_campo FROM pasosxtemplate_view".$where;
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
    $sql = "SELECT id_pasosxtemplate,id_paso,id_template,titulo,desc_campo FROM pasosxtemplate_view where id_pasosxtemplate=?";
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
