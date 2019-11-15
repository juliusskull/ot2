<?php
        class Usuario
        {
          private $db;
public  $usuario;
public  $password;
public  $id_usuario;
public  $mail;
public  $tipo=0;
public  $fchalta;
 public function __CONSTRUCT()
          {
            try
            {
              $this->id_usuario=0;
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
                  ->prepare("DELETE FROM usuario WHERE $campo = ?");

      $stm->execute(array($valor));
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
  public function getPermiso(){
      return $this->tipo;
  }
  public function isExiste($us,$pas)
  {
    try
    {
      $stm = $this->db
                ->prepare("select rol_id tipo from usuarios where usuario=? and password=?");

      $stm->execute(array($us,$pas));
		if ( $stm->rowCount()>0){
            $resultado = $stm->fetchALL(PDO::FETCH_CLASS);

            foreach($resultado as $value){
                $this->tipo=$value->tipo;
                echo "tipo:".$this->tipo;
            }
			return $this->tipo;
		}else{
			return -1;
		}
    } catch (Exception $e)
    {
        return -1;

    }
  }
            public function isExisteUsuario($us)
            {
                try
                {
                    $stm = $this->db
                        ->prepare("select rol_id tipo from usuarios where usuario=?");

                    $stm->execute(array($us));
                    if ( $stm->rowCount()>0){
                        return true;
                    }else{
                        return false;
                    }
                } catch (Exception $e)
                {
                    return false;

                }
            }
            public function isPassword($us,$pas)
            {
                try
                {
                    $stm = $this->db
                        ->prepare("select rol_id tipo from usuarios where usuario=? and password=?");

                    $stm->execute(array($us,$pas));
                    if ( $stm->rowCount()>0){
                       return true;
                    }else{
                        return false;
                    }
                } catch (Exception $e)
                {
                    return false;

                }
            }
public function  borrar($campo,$id){
    try
    {
        $sql = "DELETE FROM usuario WHERE $campo=?";

        $this->db->prepare($sql)
            ->execute(
                array($id)
            );
        return true;
    } catch (Exception $e)
    {
        die($e->getMessage());
        return false;
    }
}
public function insert($data)
  {
    try
    {
    $sql = "INSERT INTO usuario (usuario,password,id_usuario,mail,tipo)
            VALUES (?,?,?,?,?)";

    $this->db->prepare($sql)
         ->execute(
        array(
				$data->usuario,$data->password,$data->id_usuario,$data->mail,$data->tipo
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
      $sql = "UPDATE usuario SET
            usuario=?,password=?,mail=?,tipo=?,fchalta=?
            WHERE id_usuario = ".$this->id_usuario;

      $this->db->prepare($sql)
           ->execute(
            array(
					$data->usuario,$data->password,$data->mail,$data->tipo
                  )
          );
    } catch (Exception $e)
    {
      die($e->getMessage());
    }
  }
public function commit(){
    if($this->id_usuario>0){
        $this->update($this);
    }else{
        $this->insert($this);
    }

    }
 public function ini($id)
  {
    try
    {
    $sql = "SELECT usuario,password,id_usuario,mail,tipo,fchalta FROM usuario where id_usuario=$id";
    $gsent = $this->db->prepare($sql);
    $gsent->execute();

     $resultado = $gsent->fetchALL(PDO::FETCH_CLASS);

     foreach($resultado as $value){
        $this->usuario=$value->usuario;
$this->password=$value->password;
$this->id_usuario=$value->id_usuario;
$this->mail=$value->mail;
$this->tipo=$value->tipo;
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
        $where=" and  ".$w;
    }
    try
    {
    $sql = "SELECT u.usuario,u.password,u.id_usuario,u.mail,u.tipo,u.fchalta, t.desc_tipo
    FROM usuario u,usuario_tipo t  where u.tipo=t.id_tipo_usuario and id_usuario<>1".$where;
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
            public function getAllTipo()
            {
                try
                {
                    $sql = "SELECT t.id_tipo_usuario, t.desc_tipo
    FROM usuario_tipo t  ";
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
    $sql = "SELECT u.usuario,u.password,u.id_usuario,u.mail,u.tipo,u.fchalta, t.desc_tipo
    FROM usuario u,usuario_tipo t  where u.tipo=t.id_tipo_usuario and u.id_usuario=?";
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

            public function cambiar_password($usuario,$password)
            {
                try
                {
                    $sql = "UPDATE usuarios SET password=?   WHERE usuario = ?";

                    $this->db->prepare($sql)
                        ->execute(
                            array(
                                $password,$usuario
                            )
                        );
                } catch (Exception $e)
                {
                    die($e->getMessage());
                }
            }

}
?>
