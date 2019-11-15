<?php
/**
 * Created by PhpStorm.
 * User: pc
 * Da
 * */

$directorio = "./img/";
$gestor_dir = opendir($directorio);
while (false !== ($nombre_fichero = readdir($gestor_dir))) {
    $array_fichero=explode(".",$nombre_fichero);
    if(strtoupper($array_fichero[1])=="JPG"){
        $ficheros[] = $nombre_fichero;
    }

}

sort($ficheros);

print_r($ficheros);




//print_r(scanDirectories("./img/"));
//listar_directorios_ruta("./img/");
function listar_directorios_ruta($ruta){
    // abrir un directorio y listarlo recursivo
    if (is_dir($ruta)) {
        if ($dh = opendir($ruta)) {
            while (($file = readdir($dh)) !== false) {
                //esta línea la utilizaríamos si queremos listar todo lo que hay en el directorio
                //mostraría tanto archivos como directorios
                //echo "<br>Nombre de archivo: $file : Es un: " . filetype($ruta . $file);
                if (is_dir($ruta . $file) && $file!="." && $file!=".."){
                    //solo si el archivo es un directorio, distinto que "." y ".."
                    echo "<br>Directorio: $ruta$file";
                    listar_directorios_ruta($ruta . $file . "/");
                }
            }
            closedir($dh);
        }
    }else
        echo "<br>No es ruta valida";
}
function scanDirectories($rootDir, $allData=array()) {
    // set filenames invisible if you want
    $invisibleFileNames = array(".", "..", ".htaccess", ".htpasswd");
    // run through content of root directory
    $dirContent = scandir($rootDir);
    foreach($dirContent as $key => $content) {
        // filter all files not accessible
        $path = $rootDir.'/'.$content;
        if(!in_array($content, $invisibleFileNames)) {
            // if content is file & readable, add to array
            if(is_file($path) && is_readable($path)) {
                // save file name with path
                $allData[] = $path;
                // if content is a directory and readable, add path and name
            }elseif(is_dir($path) && is_readable($path)) {
                // recursive callback to open new directory
                $allData = scanDirectories($path, $allData);
            }
        }
    }
    return $allData;
}