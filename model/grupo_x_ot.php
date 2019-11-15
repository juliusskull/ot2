<?php
        class Grupo_x_ot
        {
          private $db;
public  $descripcion;
public  $motivo;
public       $id_grupo_ot;
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
                  ->prepare("DELETE FROM grupo_x_ot WHERE $campo = ?");

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
                ->prepare("select * from grupo_x_ot where  =  ?");

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
    $sql = "INSERT INTO grupo_x_ot (descripcion,motivo)
            VALUES (?,?)";

    $this->db->prepare($sql)
         ->execute(
        array(
				$data->descripcion,$data->motivo
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
      $sql = "UPDATE grupo_x_ot SET
            descripcion=?,motivo=?
            WHERE  = ".$this->id_grupo_ot;

      $this->db->prepare($sql)
           ->execute(
            array(
					$data->descripcion,$data->motivo
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
    $sql = "SELECT descripcion,motivo FROM grupo_x_ot where =$id";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);

     foreach($resultado as $value){
        $this->descripcion=$value->descripcion;
$this->motivo=$value->motivo;
     }

    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
 public function get_grupo_id($nro){

     $sql = "SELECT MAX( id_grupo ) as id_grupo
            FROM grupo_x_ot
            WHERE nro_ot =$nro";
     echo $sql."</p>";

     $gsent = $this->db->prepare($sql);
     $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_ASSOC);

     return $resultado[0]["id_grupo"];
 }
 public function getAll($w=null)
        {
            $where="";
            if(isset($w)){
                $where=" where ".$w;
            }
            try
            {
                $sql = "SELECT id_grupo_ot,descripcion,nro_ot,motivo FROM grupo_x_ot".$where;
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
            public function getAllPasos($w=null)
            {
                $where="";
                if(isset($w)){
                    $where=" where ".$w;
                }
                try
                {
                    $sql = "select ot.*
                        from grupo_ot g left join  ot_finalizada_valor_view ot on ot.OT= g.ot
                        $where
                        order by  ot.OT asc";
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
    $sql = "SELECT descripcion,motivo FROM grupo_x_ot where =?";
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
