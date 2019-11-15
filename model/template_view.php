<?php
        class Template_view
        {
          private $db;
public  $id;
public  $motivo;
public  $cantidad_pasos;
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

public function copiar($id){
    try
    {
        $stm = $this->db->prepare("select max(id) ID FROM ot ");
        $stm->execute(array());
        $resultado = $stm->fetchALL(PDO::FETCH_CLASS);

        $new_id=0;
        foreach($resultado as $value){
            $new_id=$value->ID;
            break;
        }
        $new_id++;
        $sql="insert into ot (`id`,
            `nro_ot` ,	`id_loc` ,
            `localidad` ,	`zona` ,	`id_bar` ,	`barrio` ,
            `id_cal` ,	`calle` ,	`altura` ,	`id_motivo`,
            `motivo` ,	`cod_empleado_asig` ,	`nombre_empleado_asig` ,	`cod_cuadrilla_asig` ,
            `contratista_asig` ,	`fchalta`  )
        select
        $new_id,$new_id,	`id_loc` ,	`localidad` ,	`zona` ,
            `id_bar` ,	`barrio` ,	`id_cal` ,	`calle` ,
            `altura` ,	`id_motivo`,	`motivo` ,	`cod_empleado_asig` ,
            `nombre_empleado_asig` ,	`cod_cuadrilla_asig` ,	`contratista_asig` ,
            `fchalta`   from  ot where id=$id";
        $stm = $this->db->prepare($sql);
        $stm->execute(array());

        $sql2="insert into pasos(id_paso,desc_campo,tipo,foto,obligatorio,ot)
        select id_paso,desc_campo,tipo,foto,obligatorio,$new_id from pasos where ot=$id";

        $stm = $this->db->prepare($sql2);
        $stm->execute(array());
        return $new_id;

    } catch (Exception $e)
    {
        return 0;
        die($e->getMessage());
    }
}

public function delete($campo, $valor)
  {
    try
    {
      $stm = $this->db
                  ->prepare("DELETE FROM template_view WHERE $campo = ?");

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
                ->prepare("select * from template_view where  =  ?");

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
    $sql = "INSERT INTO template_view (id,motivo,cantidad_pasos)
            VALUES (?,?,?)";

    $this->db->prepare($sql)
         ->execute(
        array(
				$data->id,$data->motivo,$data->cantidad_pasos
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
      $sql = "UPDATE template_view SET
            id=?,motivo=?,cantidad_pasos=?
            WHERE  id= ".$this->id;

      $this->db->prepare($sql)
           ->execute(
            array(
					$data->id,$data->motivo,$data->cantidad_pasos
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
    $sql = "SELECT id,motivo,cantidad_pasos FROM template_view where =$id";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);

     foreach($resultado as $value){
        $this->id=$value->id;
$this->motivo=$value->motivo;
$this->cantidad_pasos=$value->cantidad_pasos;
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
    //$sql = "SELECT id,motivo,cantidad_pasos FROM template_view".$where;
    $sql="select ot.nro_ot as id, ot.motivo,(select count(*) from  pasos where ot=ot.nro_ot) as cantidad_pasos
          from ot   where exists(select 1 from  ot_finalizada where ot= ot.nro_ot) order by 1 desc";
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
    //$sql = "SELECT id,motivo,cantidad_pasos FROM template_view where id=?";
    $sql="select ot.nro_ot as id, ot.motivo,(select count(*) from  pasos where ot=ot.nro_ot) as cantidad_pasos
          from ot   where exists(select 1 from  ot_finalizada where ot= ot.nro_ot) and ot.nro_ot=?  order by 1 desc";
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
