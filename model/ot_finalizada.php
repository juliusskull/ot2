<?php
        class Ot_finalizada
        {
          private $db;
public  $id;
public  $OT;
public  $fechainicio;
public  $fechafinalizo;
public  $idmotivofinaliza;
public  $lat;
public  $lng;
public  $altura;
public  $estado;
public  $t;
public  $fch;
public  $fchalta;
 public function __CONSTRUCT()
          {
            try
            {
              $this->id=0;
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
                ->prepare("select * from ot_finalizada where id =  ?");

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
    $sql = "INSERT INTO ot_finalizada (id,OT,fechainicio,fechafinalizo,idmotivofinaliza,lat,lng,altura,estado,t,fch,fchalta)
            VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

    $this->db->prepare($sql)
         ->execute(
        array(
				$data->id,$data->OT,$data->fechainicio,$data->fechafinalizo,$data->idmotivofinaliza,$data->lat,$data->lng,$data->altura,$data->estado,$data->t,$data->fch,$data->fchalta
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
      $sql = "UPDATE ot_finalizada SET
            OT=?,fechainicio=?,fechafinalizo=?,idmotivofinaliza=?,lat=?,lng=?,altura=?,estado=?,t=?,fch=?,fchalta=?
            WHERE id = ".$this->id;

      $this->db->prepare($sql)
           ->execute(
            array(
					$data->OT,$data->fechainicio,$data->fechafinalizo,$data->idmotivofinaliza,$data->lat,$data->lng,$data->altura,$data->estado,$data->t,$data->fch,$data->fchalta
                  )
          );
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
public function commit(){
    if($this->id>0){
        $this->update($this);
    }else{
        $this->insert($this);
    }

    }
 public function ini($id)
  {
    try
    {
    $sql = "SELECT id,OT,fechainicio,fechafinalizo,idmotivofinaliza,lat,lng,altura,estado,t,fch,fchalta FROM ot_finalizada where id=$id";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);

     foreach($resultado as $value){
        $this->id=$value->id;
        $this->OT=$value->OT;
        $this->fechainicio=$value->fechainicio;
        $this->fechafinalizo=$value->fechafinalizo;
        $this->idmotivofinaliza=$value->idmotivofinaliza;
        $this->lat=$value->lat;
        $this->lng=$value->lng;
        $this->altura=$value->altura;
        $this->estado=$value->estado;
        $this->t=$value->t;
        $this->fch=$value->fch;
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
    $sql = "SELECT id,OT,fechainicio,fechafinalizo,idmotivofinaliza,lat,lng,altura,estado,t,fch,fchalta FROM ot_finalizada".$where;
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
            public function get_cant_finalizada(){
                try
                {
                    $sql="select * from ot_finalizada where fechafinalizo<>'' and fechafinalizo is not null";

                    $gsent = $this->db->prepare($sql);
                    $gsent->execute();
                    $resultado = $gsent->fetchALL(PDO::FETCH_ASSOC);

                    return sizeof($resultado);
                } catch (Exception $e)
                {
                    die($e->getMessage());
                    return 10;
                }
            }

 public function get_cant_sin_finalizada(){
     try
     {
     $sql="select * from ot_finalizada where (fechafinalizo='' or fechafinalizo is  null) and t<>'usuario'";

     $gsent = $this->db->prepare($sql);
     $gsent->execute();

     return $gsent->rowCount();
     } catch (Exception $e)
     {
         die($e->getMessage());
         return 0;
     }
 }

 public function getOne($id)
  {
    try
    {
    $sql = "SELECT id,OT,fechainicio,fechafinalizo,idmotivofinaliza,lat,lng,altura,estado,t,fch,fchalta FROM ot_finalizada where id=?";
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

public static function getUltimasOtJson2_1($swhere=null)	{
    $query=new Query();
    $where="";
    if (isset($swhere)){
        $where=" where $swhere";
    }
    $table="ot_finalizar_view";
    //echo "$table</p>";
    //echo "select * from $table $where ORDER BY FECHA_INICIO ASC";
    $result=$query->executeQuery("select * from $table $where ORDER BY FECHA_INICIO ASC");

    //echo "select * from $table $where ORDER BY FECHA_INICIO ASC</p>";
    $return_arr = Array();
    while ($row = oci_fetch_array($result, OCI_ASSOC)) {

        $array2=array_map('utf8_encode', $row);
        array_push($return_arr,$array2);

    }

    $salida= json_encode($return_arr);
    return str_replace( "'","",str_replace("\/","/", $salida));
}

 public  function getUltimasOtJson($swhere=null)	{
               // $query=new Query();
                $where="";
                if (isset($swhere)){
                    $where=" where $swhere";
                }
                $table="ot_finalizar_view";
                //echo "$table</p>";
                //echo "select * from $table $where ORDER BY FECHA_INICIO ASC";
                $sql="select * from $table $where ORDER BY FECHA_INICIO ASC";

                $gsent = $this->db->prepare($sql);
                $gsent->execute();


                $resultado = $gsent->fetchALL(PDO::FETCH_ASSOC);
                $rows = array();

                foreach($resultado as $value){

                    $rows[] = $value;
                }
                 $salida= json_encode($rows);
                 return $salida;/* str_replace( "'","",str_replace("\/","/", $salida));*/
            }

  }
?>
