package com.aguasnortesalta.ordenes.model.geometrias2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties {

@SerializedName("featid")
@Expose
private Integer featid;
@SerializedName("material")
@Expose
private String material;
@SerializedName("id_categoria")
@Expose
private Integer idCategoria;
@SerializedName("id_tipo_servicio")
@Expose
private Integer idTipoServicio;

public Integer getFeatid() {
return featid;
}

public void setFeatid(Integer featid) {
this.featid = featid;
}

public String getMaterial() {
return material;
}

public void setMaterial(String material) {
this.material = material;
}

public Integer getIdCategoria() {
return idCategoria;
}

public void setIdCategoria(Integer idCategoria) {
this.idCategoria = idCategoria;
}

public Integer getIdTipoServicio() {
return idTipoServicio;
}

public void setIdTipoServicio(Integer idTipoServicio) {
this.idTipoServicio = idTipoServicio;
}

}