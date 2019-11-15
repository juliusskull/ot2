<?php
        class Ot_finalizar_view
        {
          private $db;
public  $ot;
public  $FECHA_INICIO;
public  $FECHA_FINALIZO;
public  $MOT;
public  $LAT;
public  $LON;
public  $LAT_INI;
public  $LON_INI;
public  $C;
public  $OBS;
public  $direccion;
public  $ACTUAL;
public  $MOT_OT;
public  $capataz;
public  $equipos;
public  $legajo;
public  $movil;
 public function __CONSTRUCT()
          {
            try
            {
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
                  ->prepare("DELETE FROM ot_finalizada WHERE $campo = ?");

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
                ->prepare("select * from ot_finalizar_view where  =  ?");

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
    $sql = "INSERT INTO ot_finalizar_view (ot,FECHA_INICIO,FECHA_FINALIZO,MOT,LAT,LON,LAT_INI,LON_INI,C,OBS,direccion,ACTUAL,MOT_OT,capataz,equipos,legajo,movil)
            VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    $this->db->prepare($sql)
         ->execute(
        array(
				$data->ot,$data->FECHA_INICIO,$data->FECHA_FINALIZO,$data->MOT,$data->LAT,$data->LON,$data->LAT_INI,$data->LON_INI,$data->C,$data->OBS,$data->direccion,$data->ACTUAL,$data->MOT_OT,$data->capataz,$data->equipos,$data->legajo,$data->movil
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
      $sql = "UPDATE ot_finalizar_view SET
            ot=?,FECHA_INICIO=?,FECHA_FINALIZO=?,MOT=?,LAT=?,LON=?,LAT_INI=?,LON_INI=?,C=?,OBS=?,direccion=?,ACTUAL=?,MOT_OT=?,capataz=?,equipos=?,legajo=?,movil=?
            WHERE  = ";

      $this->db->prepare($sql)
           ->execute(
            array(
					$data->ot,$data->FECHA_INICIO,$data->FECHA_FINALIZO,$data->MOT,$data->LAT,$data->LON,$data->LAT_INI,$data->LON_INI,$data->C,$data->OBS,$data->direccion,$data->ACTUAL,$data->MOT_OT,$data->capataz,$data->equipos,$data->legajo,$data->movil
                  )
          );
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
public function commit(){
  /*  if($this->){
        $this->update($this);
    }else{
        $this->insert($this);
    }
*/
    }
 public function ini($id)
  {
    try
    {
    $sql = "SELECT ot,FECHA_INICIO,FECHA_FINALIZO,MOT,LAT,LON,LAT_INI,LON_INI,C,OBS,direccion,ACTUAL,MOT_OT,equipos,legajo,movil FROM ot_finalizar_view where =$id";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);

     foreach($resultado as $value){
        $this->ot=$value->ot;
        $this->FECHA_INICIO=$value->FECHA_INICIO;
        $this->FECHA_FINALIZO=$value->FECHA_FINALIZO;
        $this->MOT=$value->MOT;
        $this->LAT=$value->LAT;
        $this->LON=$value->LON;
        $this->LAT_INI=$value->LAT_INI;
        $this->LON_INI=$value->LON_INI;
        $this->C=$value->C;
        $this->OBS=$value->OBS;
        $this->direccion=$value->direccion;
        $this->ACTUAL=$value->ACTUAL;
        $this->MOT_OT=$value->MOT_OT;
        $this->capataz=$value->capataz;
        $this->equipos=$value->equipos;
        $this->legajo=$value->legajo;
        $this->movil=$value->movil;
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
    $sql = "SELECT ot,FECHA_INICIO,FECHA_FINALIZO,MOT,LAT,LON,LAT_INI,LON_INI,C,OBS,direccion,ACTUAL,MOT_OT,equipos,legajo,movil FROM ot_finalizar_view".$where;

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
    $sql = "SELECT ot,FECHA_INICIO,FECHA_FINALIZO,MOT,LAT,LON,LAT_INI,LON_INI,C,OBS,direccion,ACTUAL,MOT_OT,equipos,legajo,movil FROM ot_finalizar_view where ot=?";
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
