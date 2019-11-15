<?php
        class Materiales_ot
        {
          private $db;
public  $id_material_ot;
public  $codigo;
public  $ot;
public  $cantidad;
public  $usuario;
public  $fchalta;
public  $id_grupo;
 public function __CONSTRUCT()
          {
            try
            {
              $this->id_material_ot=0;
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
                  ->prepare("DELETE FROM materiales_ot WHERE $campo = ?");

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
                ->prepare("select * from materiales_ot where id_material_ot =  ?");

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
    $sql = "INSERT INTO materiales_ot (codigo,ot,cantidad,usuario,id_grupo)
            VALUES (?,?,?,?,?)";

    $this->db->prepare($sql)
         ->execute(
        array(
				$data->codigo,$data->ot,$data->cantidad,$data->usuario,$data->id_grupo
              )
      );
    $this->id_material_ot =  $this->db->lastInsertId();
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
public function insert2($data,$session)
            {
                try
                {
                    $sql = "INSERT INTO materiales_ot_log (id_material_ot,codigo,ot,cantidad,usuario,fchalta,session)
            VALUES (?,?,?,?,?,?,?)";

                    $this->db->prepare($sql)
                        ->execute(
                            array(
                                $data->id_material_ot,$data->codigo,$data->ot,$data->cantidad,$data->usuario,$data->fchalta
                                ,$session
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
      $sql = "UPDATE materiales_ot SET
            codigo=?,ot=?,cantidad=?,usuario=?,fchalta=?
            WHERE id_material_ot = ".$this->id_material_ot;

      $this->db->prepare($sql)
           ->execute(
            array(
					$data->codigo,$data->ot,$data->cantidad,$data->usuario,$data->fchalta
                  )
          );
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
public function commit(){
    if($this->id_material_ot>0){
        $this->update($this);
    }else{
        $this->insert($this);
    }

    }
public function update2($data){
    try
    {
        $sql = "UPDATE materiales_ot SET
            ot=?,cantidad=?,usuario=?
            WHERE codigo = '".$data->codigo."'";

        $this->db->prepare($sql)
            ->execute(
                array(
                    $data->ot,$data->cantidad,$data->usuario
                )
            );
    } catch (Exception $e)
    {
        die($e->getMessage());
    }
}
 public function ini($id)
  {
    try
    {
    $sql = "SELECT id_material_ot,codigo,ot,cantidad,usuario,fchalta FROM materiales_ot where id_material_ot=$id";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);

     foreach($resultado as $value){
        $this->id_material_ot=$value->id_material_ot;
$this->codigo=$value->codigo;
$this->ot=$value->ot;
$this->cantidad=$value->cantidad;
$this->usuario=$value->usuario;
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
    $sql = "SELECT id_material_ot, o.codigo, ot, cantidad, usuario, fchalta, m.desc_material
FROM materiales_ot o left join materiales m on  m.codigo = o.codigo ".$where;
    //echo     $sql;
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
            public function getAllGrupo($w=null)
            {
                /*
                $where="";

                if(isset($w)){
                    $where=" where ".$w;
                }*/
                try
                {
                    $sql = "SELECT id_material_ot, o.codigo, ot, cantidad, usuario, fchalta, m.desc_material
                    FROM materiales_ot o left join materiales m on  m.codigo = o.codigo
                    where id_grupo in (select id_grupo from grupo_ot where ot=$w)";
                   // echo     $sql;
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
    $sql = "SELECT id_material_ot, m.codigo, ot, cantidad, usuario, fchalta, m.desc_material
FROM materiales_ot o, materiales m
WHERE m.codigo = o.codigo and o.id_material_ot=?";
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
