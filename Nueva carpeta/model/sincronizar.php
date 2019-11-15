<?php
        class Sincronizar
        {
          private $db;
public  $id;
public  $envio;
public  $tipo;
public  $valor;
public  $lat;
public  $lng;
public  $fchalta;
public  $usuario;
public  $imei;
public  $precision;
public  $gps;
public  $red;
public  $version;
public  $aplicacion;
public  $actualizada;
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
                  ->prepare("DELETE FROM sincronizar WHERE $campo = ?");

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
                ->prepare("select * from sincronizar where  =  ?");

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

    $sql = 'INSERT INTO sincronizar (envio,tipo,valor,lat,lng,usuario,imei,gps,red,aplicacion)
            VALUES (?,?,?,?,?,?,?,?,?,?)';

    $this->db->prepare($sql)
         ->execute(
        array(
				 $data->envio
                ,$data->tipo
                ,$data->valor
                ,$data->lat
                ,$data->lng
                ,$data->usuario
                ,$data->imei
                ,$data->gps
                ,$data->red
                ,$data->aplicacion
              )
      );

        /*
        $sql = "INSERT INTO sincronizar (envio,tipo,valor,lat,lng,usuario,imei,precision,gps,red,version,aplicacion)
            VALUES ('$data->envio','$data->tipo','$data->valor','$data->lat','$data->lng','$data->usuario','$data->imei','$data->precision','$data->gps','$data->red','$data->version','$data->aplicacion')";
        echo"</p>$sql</p>";
        */
       // $this->db->prepare($sql)->execute();
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
 public function update($data)
  {
    try
    {
      $sql = "UPDATE sincronizar SET
            id=?,envio=?,tipo=?,valor=?,lat=?,lng=?,fchalta=?,usuario=?,imei=?,precision=?,gps=?,red=?,version=?,aplicacion=?,actualizada=?
            WHERE  = ".$this->id;

      $this->db->prepare($sql)
           ->execute(
            array(
					$data->id,$data->envio,$data->tipo,$data->valor,$data->lat,$data->lng,$data->fchalta,$data->usuario,$data->imei,$data->precision,$data->gps,$data->red,$data->version,$data->aplicacion,$data->actualizada
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
    $sql = "SELECT id,envio,tipo,valor,lat,lng,fchalta,usuario,imei,precision,gps,red,version,aplicacion,actualizada FROM sincronizar where =$id";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);

     foreach($resultado as $value){
        $this->id=$value->id;
$this->envio=$value->envio;
$this->tipo=$value->tipo;
$this->valor=$value->valor;
$this->lat=$value->lat;
$this->lng=$value->lng;
$this->fchalta=$value->fchalta;
$this->usuario=$value->usuario;
$this->imei=$value->imei;
$this->precision=$value->precision;
$this->gps=$value->gps;
$this->red=$value->red;
$this->version=$value->version;
$this->aplicacion=$value->aplicacion;
$this->actualizada=$value->actualizada;
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
    $sql = "SELECT id,envio,tipo,valor,lat,lng,fchalta,usuario,imei,`precision`,gps,red,`version`,aplicacion,actualizada FROM sincronizar".$where;
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
    $sql = "SELECT id,envio,tipo,valor,lat,lng,fchalta,usuario,imei,precision,gps,red,version,aplicacion,actualizada FROM sincronizar where =?";
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

function getUbicacionActualImei($fecha,$gerencia,$legajo,$imei){


    $sql="select usuario as legajo,'' as nombre, 0 as CODIGO_CUADRILLA,
'prueba' as geren, lat, lng, fchalta
from sincronizar
where  DATE_FORMAT(STR_TO_DATE(fchalta, ".'"%Y-%m-%d"),"%d-%m-%Y"'. ")='$fecha'";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();


    $resultado = $gsent->fetchALL(PDO::FETCH_ASSOC);
    $rows = array();

    foreach($resultado as $value){

        $rows[] = $value;
    }
    $salida= json_encode($rows);
    return $salida;

    }

public  function getAllJson2($swhere=null)	{

    $where="";
    if (isset($swhere)){
        $where=" where $swhere";
    }
/*
    $table="(
			select  r.LAT, r.LNG as LON, to_char(r.FCHalta ,'DD/MM/YYYY hh24:mi:ss') FCH, r.LEGAJO
			, c.codigo as C ,'xx1' equipo ,nvl(r.gps,0) as GPS,nvl(r.red,0) as RED ,nvl(r.version,'1.1') version from rastreo r, capataz c
			where c.id=r.legajo)
			";*/
    $sql="select  LAT, LNG AS LON, fchalta AS FCH, usuario as LEGAJO
		,'0' as  C,'' as EQUIPO,version as VERSION ,GPS,RED from sincronizar  $where ORDER BY FCH ASC";


    $gsent = $this->db->prepare($sql);
    $gsent->execute();


    $resultado = $gsent->fetchALL(PDO::FETCH_ASSOC);
    $rows = array();

    foreach($resultado as $value){

        $rows[] = $value;
    }
    $salida= json_encode($rows);
    return $salida;
}

function get_ultima_ubicacion(){
    $sql="select s.usuario, (select max(id) from sincronizar where usuario =s.usuario) as t
          from sincronizar s where usuario   is not null  group by s.usuario";


    $gsent = $this->db->prepare($sql);
    $gsent->execute();

    $resultado = $gsent->fetchALL(PDO::FETCH_ASSOC);
    $consulta="";
    foreach($resultado as $value){
        if($consulta!=""){
            $consulta.=",";
        }
        $consulta.=$value["t"];

    }



    $sql="select * from sincronizar where id in($consulta)";

    $gsent = $this->db->prepare($sql);
    $gsent->execute();


    $resultado = $gsent->fetchALL(PDO::FETCH_ASSOC);
    $rows = array();

    foreach($resultado as $value){

        $rows[] = $value;

    }

    return $rows;
}
function getUbicacionActual($fecha,$gerencia,$legajo){
    $sql="SELECT DISTINCT S.usuario LEGAJO, S.usuario NOMBRE, 1 CUADRILLA,
		     'G' GEREN, s.LAT, s.LNG, s.FCHALTA from sincronizar s
						WHERE S.lat IS NOT NULL
						AND S.usuario='$legajo'
						AND DATE_FORMAT(STR_TO_DATE(fchalta, ".'"%Y-%m-%d"),"%d-%m-%Y"'.") ='$fecha'";

    $gsent = $this->db->prepare($sql);
    $gsent->execute();


    $resultado = $gsent->fetchALL(PDO::FETCH_ASSOC);
    $rows = array();

    foreach($resultado as $value){

        $rows[] = $value;
    }
    $salida= json_encode($rows);
    return $salida;
}
        }
?>
