<?php
        class Template
        {
          private $db;
public  $id_template;
public  $desc_template;
public  $observacion;
public  $titulo;
public  $_id;
 public function __CONSTRUCT()
          {
            try
            {
              $this->_id=0;
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
                  ->prepare("DELETE FROM template WHERE $campo = ?");

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
                ->prepare("select * from template where _id =  ?");

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
    $sql = "INSERT INTO template (id_template,desc_template,observacion,titulo,_id)
            VALUES (?,?,?,?,?)";

    $this->db->prepare($sql)
         ->execute(
        array(
				$data->id_template,$data->desc_template,$data->observacion,$data->titulo,$data->_id
              )
      );

        $sql = "SELECT max(_id) ID  FROM template ";

        $gsent = $this->db->prepare($sql);
        $gsent->execute();

        $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);
        foreach($resultado as $value){
            $sql2 = "UPDATE template SET    id_template=$value->ID   WHERE _id = ".$value->ID;
            $this->db->prepare($sql2)->execute();

        }
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
 public function update($data)
  {
    try
    {
      $sql = "UPDATE template SET
            id_template=?,desc_template=?,observacion=?,titulo=?
            WHERE _id = ".$this->_id;

      $this->db->prepare($sql)
           ->execute(
            array(
					$data->id_template,$data->desc_template,$data->observacion,$data->titulo
                  )
          );
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
public function commit(){
    if($this->_id>0){
        $this->update($this);
    }else{
        $this->insert($this);
    }

    }
 public function ini($id)
  {
    try
    {
    $sql = "SELECT id_template,desc_template,observacion,titulo,_id FROM template where _id=$id";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);

     foreach($resultado as $value){
        $this->id_template=$value->id_template;
$this->desc_template=$value->desc_template;
$this->observacion=$value->observacion;
$this->titulo=$value->titulo;
$this->_id=$value->_id;
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
    $sql = "SELECT id_template,desc_template,observacion,titulo,_id FROM template".$where;
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
    $sql = "SELECT id_template,desc_template,observacion,titulo,_id FROM template where _id=?";
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
