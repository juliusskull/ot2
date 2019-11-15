package com.aguasnortesalta.ordenes.model;

import java.util.ArrayList;
import java.util.List;

public class OperarioList extends BaseList{
	public List< Operario> data=null;
	public OperarioList(){
		super();
		data=new ArrayList<Operario>();	
	}
	
}
