<?php
        class Ot
        {
          private $db;
public  $id;
public  $nro_ot;
public  $id_loc;
public  $localidad;
public  $zona;
public  $id_bar;
public  $barrio;
public  $id_cal;
public  $calle;
public  $altura;
public  $id_motivo;
public  $motivo;
public  $cod_empleado_asig;
public  $nombre_empleado_asig;
public  $cod_cuadrilla_asig;
public  $contratista_asig;
public  $fchalta;
public  $observacion;
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
                  ->prepare("DELETE FROM ot WHERE $campo = ?");

      $stm->execute(array($valor));
       return true;
    } catch (Exception $e)
    {
        return false;
      die($e->getMessage());
    }
  }
  public function isExiste($valor)
  {
    try
    {
      $stm = $this->db
                ->prepare("select * from ot where id =  ?");

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
    $sql = "INSERT INTO ot (id,nro_ot,id_loc,localidad,zona,id_bar,barrio,id_cal,calle,altura,id_motivo,motivo,cod_empleado_asig,nombre_empleado_asig,cod_cuadrilla_asig,contratista_asig,observacion)
            VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    $this->db->prepare($sql)
         ->execute(
        array(
				$data->id,$data->nro_ot,$data->id_loc,$data->localidad,$data->zona,$data->id_bar,$data->barrio,$data->id_cal,$data->calle,$data->altura,$data->id_motivo,$data->motivo,$data->cod_empleado_asig,$data->nombre_empleado_asig,$data->cod_cuadrilla_asig,$data->contratista_asig
            ,$data->observacion
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
      $sql = "UPDATE ot SET
            nro_ot=?,id_loc=?,localidad=?,zona=?,id_bar=?,barrio=?,id_cal=?,calle=?,altura=?,id_motivo=?,motivo=?,cod_empleado_asig=?,nombre_empleado_asig=?,cod_cuadrilla_asig=?,contratista_asig=?,fchalta=?,observacion=?
            WHERE id = ".$this->id;

      $this->db->prepare($sql)
           ->execute(
            array(
					$data->nro_ot,$data->id_loc,$data->localidad,$data->zona,$data->id_bar,$data->barrio,$data->id_cal,$data->calle,$data->altura,$data->id_motivo,$data->motivo,$data->cod_empleado_asig,$data->nombre_empleado_asig,$data->cod_cuadrilla_asig,$data->contratista_asig,$data->fchalta,$data->observacion
                  )
          );
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
            public function update_cavecera($data)
            {
                try
                {
                    $sql = "UPDATE ot SET
                    cod_empleado_asig=?,nombre_empleado_asig=?,observacion=?,motivo=?
            WHERE nro_ot = ".$this->nro_ot;

                    $this->db->prepare($sql)
                        ->execute(
                            array(
                                $data->cod_empleado_asig
                                ,$data->nombre_empleado_asig
                            ,$data->observacion
                            ,$data->motivo
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
        return $this->id;
    }else{
        $this->insert($this);
        $ultimo_id=$this->db->lastInsertId();
        $this->db->prepare("update ot set nro_ot=id where id=".$ultimo_id)->execute();


        return $ultimo_id;
    }

  }

 public function ini($id)
  {
    try
    {
    $sql = "SELECT id,nro_ot,id_loc,localidad,zona,id_bar,barrio,id_cal,calle,altura,id_motivo,motivo,cod_empleado_asig,nombre_empleado_asig,cod_cuadrilla_asig,contratista_asig,fchalta FROM ot where id=$id";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);

     foreach($resultado as $value){
        $this->id=$value->id;
$this->nro_ot=$value->nro_ot;
$this->id_loc=$value->id_loc;
$this->localidad=$value->localidad;
$this->zona=$value->zona;
$this->id_bar=$value->id_bar;
$this->barrio=$value->barrio;
$this->id_cal=$value->id_cal;
$this->calle=$value->calle;
$this->altura=$value->altura;
$this->id_motivo=$value->id_motivo;
$this->motivo=$value->motivo;
$this->cod_empleado_asig=$value->cod_empleado_asig;
$this->nombre_empleado_asig=$value->nombre_empleado_asig;
$this->cod_cuadrilla_asig=$value->cod_cuadrilla_asig;
$this->contratista_asig=$value->contratista_asig;
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
    $sql = "SELECT id,nro_ot,id_loc,localidad,zona,id_bar,barrio,id_cal,calle,altura,id_motivo,motivo,cod_empleado_asig,nombre_empleado_asig,cod_cuadrilla_asig,contratista_asig,fchalta,template_titulo,'-24.7918987' as lat, '-65.429728'as lng, observacion FROM ot_view".$where ." order by id desc";
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
/*
 public function getAll_json()
  {
        echo json_encode($this->getAll());
  }*/
public function getAll_json($w=null)
{
        $data = array(
        'status'=> 'success',
        'code'  => 200,
        'msg'  => 'ok',
        'total_items'  => 0,
        'pagina_actual' => 1,
        'item_por_pagina' => 1,
        'total_paginas' => 1,
        'data' => $this->getAll($w)
        );
    echo json_encode($data);
}

 public function getOne($id)
  {
    try
    {
    $sql = "SELECT id,nro_ot,id_loc,localidad,zona,id_bar,barrio,id_cal,calle,altura,id_motivo,motivo,cod_empleado_asig,nombre_empleado_asig,cod_cuadrilla_asig,contratista_asig,fchalta,template_titulo,observacion FROM ot_view where id=?";
    //echo "$sql</p>";
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
