<?php
        class Pasos
        {
          private $db;
public  $id_paso;
public  $desc_campo;
public  $tipo;
public  $foto;
public  $obligatorio;
public  $_id;
public  $ot;
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
                  ->prepare("DELETE FROM pasos WHERE $campo = ?");

      $stm->execute(array($valor));
      return true;
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
                ->prepare("select * from pasos where _id =  ?");

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
            public function delete_aux(){
                $sql = "delete from pasos_aux ";

                $this->db->prepare($sql)->execute();
            }

            public function insert_aux($data,$id_grupo)
            {
                try
                {
                    //$this->delete_aux();
                    $sql = "INSERT INTO pasos_aux (desc_campo,tipo,id_grupo) VALUES (?,?,?)";

                    $this->db->prepare($sql)
                        ->execute(
                            array(
                                $data->desc_campo,$data->tipo,$id_grupo
                            )
                        );
                    $sql = "SELECT max(_id) ID  FROM pasos ";

                    $gsent = $this->db->prepare($sql);
                    $gsent->execute();

                    $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);
                    foreach($resultado as $value){
                        $sql2 = "UPDATE pasos SET    id_paso=$value->ID   WHERE _id = ".$value->ID;
                        $this->db->prepare($sql2)->execute();


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
    $sql = "INSERT INTO pasos (id_paso,desc_campo,tipo,foto,obligatorio,_id,ot) VALUES (?,?,?,?,?,?,?)";

    $this->db->prepare($sql)
         ->execute(
        array(
				$data->id_paso,$data->desc_campo,$data->tipo,$data->foto,$data->obligatorio,$data->_id,$data->ot
              )
      );
     //$sql = "SELECT max(_id) ID  FROM pasos ";
     $this->_id=$this->db->lastInsertId();

     $sql2 = "UPDATE pasos SET    id_paso=$this->_id   WHERE _id = ".$this->_id;
     $this->db->prepare($sql2)->execute();
    /*
     $gsent = $this->db->prepare($sql);
     $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);
     foreach($resultado as $value){
         $sql2 = "UPDATE pasos SET    id_paso=$value->ID   WHERE _id = ".$value->ID;
         $this->db->prepare($sql2)->execute();


     }
        */

    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }

 public function update($data)
  {
    try
    {
      $sql = "UPDATE pasos SET
            id_paso=?,desc_campo=?,tipo=?,foto=?,obligatorio=?
            WHERE _id = ".$this->_id;



      $this->db->prepare($sql)
           ->execute(
            array(
					$data->id_paso,$data->desc_campo,$data->tipo,$data->foto,$data->obligatorio
                  )
          );
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
public function commit(){
    /*
    if($this->_id>0){
        $this->update($this);
    }else{
        $this->insert($this);
    }
    */
    //$gsent = $this->db->prepare("delete from pasos where ot=".$this->ot);

    if($this->isExiste($this->_id)){
        $this->update($this);
    }else{
        $this->insert($this);
    }


   // $gsent->execute();
    //$this->insert($this);

    }
 public function ini($id)
  {
    try
    {
    $sql = "SELECT id_paso,desc_campo,tipo,foto,obligatorio,_id FROM pasos where _id=$id";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);

     foreach($resultado as $value){
        $this->id_paso=$value->id_paso;
        $this->desc_campo=$value->desc_campo;
        $this->tipo=$value->tipo;
        $this->foto=$value->foto;
        $this->obligatorio=$value->obligatorio;
        $this->_id=$value->_id;
     }

    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
            public function get_max_id()
            {

                try
                {
                    $sql = "SELECT max(_id) ID FROM pasos";
                    $gsent = $this->db->prepare($sql);
                    $gsent->execute();

                    $resultado = $gsent->fetchALL(PDO::FETCH_ASSOC);
                    $rows = array();
                    $S=0;
                    foreach($resultado as $value){
                        $S=$value["ID"];
                        $rows[] = $value;
                    }

                    return $S;

                } catch (Exception $e)
                {
                    die($e->getMessage());
                }
            }
            public function getAll_aux($w=null)
            {
                $where="";
                if(isset($w)){
                    $where=" where ".$w;
                }
                try
                {
                    $sql = "select * from pasos_aux".$where;
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
            public function getUpdate_aux($id,$id_operario,$operario)
            {

                try
                {
                    $sql = "update pasos_aux set id_operario=$id_operario,operario='$operario' where id=$id";
                    $gsent = $this->db->prepare($sql);
                    $gsent->execute();



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
    $sql = "SELECT id_paso,desc_campo,tipo,foto,obligatorio,_id
     ,case obligatorio
        when 1 then 'SI'
        else 'NO'
        END AS obligatorio_desc FROM pasos".$where;
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
 public function getAll_json($w=null)
  {
        echo json_encode($this->getAll($w));
  }
 public function getOne($id)
  {
    try
    {
    $sql = "SELECT id_paso,desc_campo,tipo,foto,obligatorio,_id FROM pasos where _id=?";
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
