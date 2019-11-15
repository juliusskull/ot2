<?php
        class Grupo
        {
          private $db;
public  $id_grupo;
public  $descripcion;
public  $fchalta;
 public function __CONSTRUCT()
          {
            try
            {
              $this->id_grupo=0;
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
                  ->prepare("DELETE FROM grupo WHERE $campo = ?");

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
                ->prepare("select * from grupo where id_grupo =  ?");

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
    $sql = "INSERT INTO grupo (id_grupo,descripcion,fchalta)
            VALUES (?,?,?)";

    $this->db->prepare($sql)
         ->execute(
        array(
				$data->id_grupo,$data->descripcion,$data->fchalta
              )
      );
     $this->id_grupo =  $this->db->lastInsertId();
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
 public function update($data)
  {
    try
    {
      $sql = "UPDATE grupo SET
            descripcion=?,fchalta=?
            WHERE id_grupo = ".$this->id_grupo;

      $this->db->prepare($sql)
           ->execute(
            array(
					$data->descripcion,$data->fchalta
                  )
          );
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
public function commit(){
    if($this->id_grupo>0){
        $this->update($this);
    }else{
        $this->insert($this);
    }

    }
 public function ini($id)
  {
    try
    {
    $sql = "SELECT id_grupo,descripcion,fchalta FROM grupo where id_grupo=$id";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);

     foreach($resultado as $value){
        $this->id_grupo=$value->id_grupo;
$this->descripcion=$value->descripcion;
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
    $sql = "SELECT id_grupo,descripcion,fchalta FROM grupo".$where;
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
 public function crear_ot_x_pasos($id_grupo){
     $sql = "select operario, id_operario from pasos_aux where id_grupo=? group by operario, id_operario";
     $gsent = $this->db->prepare($sql);
     $gsent->execute(array($id_grupo));

     $resultado = $gsent->fetchALL(PDO::FETCH_ASSOC);
     $rows = array();

     foreach($resultado as $value){
         $motivo="generada automaticamente GrupoNro$id_grupo ".date("j-n-Y H:i:s");
         $operario= $value["operario"];
         $id_operario= $value["id_operario"];
         //echo "$id_operario->$operario;$id_grupo</p>";

         $sql = "insert into ot(motivo,cod_empleado_asig,nombre_empleado_asig,id_loc,id_bar,id_motivo)VALUES (?,?,?,?,?,?)";
         //echo $sql."</p>";

         $gsent = $this->db->prepare($sql);
         $gsent->execute(array($motivo,$id_operario,$operario,0,0,0));

         $ultimo_id=$this->db->lastInsertId();
         $this->db->prepare("update ot set nro_ot=id where id=".$ultimo_id)->execute();

         $sql = "insert into pasos(	id_paso,desc_campo,	tipo ,foto,obligatorio,ot)
	                select 0 as id_paso,desc_campo,tipo, IF(upper(trim(tipo))='FOTO' ,1, 0)  foto, 1 obligatorio, $ultimo_id ot
                 from pasos_aux where id_grupo=? and id_operario=?";

         $gsent = $this->db->prepare($sql);
         $gsent->execute(array($id_grupo,$id_operario));

         $gsent = $this->db->prepare("update pasos set id_paso= _id where id_paso=0");
         $gsent->execute();

         $gsent = $this->db->prepare("insert into grupo_ot (ot,id_grupo)values(?,?)");
         $gsent->execute(array($ultimo_id,$id_grupo));


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
    $sql = "SELECT id_grupo,descripcion,fchalta FROM grupo where id_grupo=?";
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
