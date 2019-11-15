package com.aguasnortesalta.ordenes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.aguasnortesalta.ordenes.ConfirmarFrag.OnOtClickOpciones;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.model.Pasos;
import com.aguasnortesalta.ordenes.model.PasosList;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;

import android.app.ProgressDialog;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.print.pdf.PrintedPdfDocument;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class ConfirmarFrag2 extends Fragment{
	private static final String ARG_SECTION_NUMBER = "";
	private LinearLayout ly;
	private static OnOtClickOpciones2 onOtClickOpciones2;
	private DatabaseManager dm;
	private ProgressDialog progress;
	private ImageButton btn_icon_modificar;
	private long template_id=2;
	private PasosList pasos_actuales=null;
	private List<InterfacePasos> interface_pasos = new ArrayList<InterfacePasos>();
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.confirmar, container,
				false);
		return rootView;
	}
	
	/*private void createPDF(FileOutputStream outputStream) throws IOException {
	    PrintedPdfDocument document = new PrintedPdfDocument(this,
	            getPrintAttributes());

	    // start a page
	    PdfDocument.Page page = document.startPage(1);

	    // draw something on the page
	    View content = ly.getContentView();
	    content.draw(page.getCanvas());

	    // finish the page
	    document.finishPage(page);
	    //. . .
	    // add more pages
	    //. . .
	    // write the document content
	    document.writeTo(outputStream);

	    //close the document
	    document.close();
	}*/
	
	 @Override
	  public void onActivityCreated(Bundle state) {
	       super.onActivityCreated(state);
	       dm=new  DatabaseManager(getActivity(),"CONFIRMAR");
	       ly=(LinearLayout)getActivity().findViewById(R.id.ly_trabajos_ejecutados);
	       btn_icon_modificar=(ImageButton)getActivity().findViewById(R.id.btn_icon_modificar);
	     
	        if(dm.RESPUESTA_ULTIMA_GET(getActivity())<=0){
	    	   //onOtClickOpciones.onClickOpcion(OPCIONES_RESPUESTAS);   
	       }  
	       /*
	       if(confirmar.getId_res()==0){
	    	 //  opciones_click(OPCIONES_RESPUESTAS);
	       }	
	       */
	       create_componentes();
	       
	       btn_icon_modificar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//ver_opciones("Modificar");
			}
		});
	       
	 }
	private void create_componentes() {
		// TODO Auto-generated method stub
		
		InterfaceDinamica interfaceDinamica = new InterfaceDinamica(getActivity());
		
		if(!dm.IS_PASOS_ACTUALES()){
			Pasos[] pasos_actuales2=null;
			pasos_actuales2 = dm.get_pasosxtemplate(template_id);
			PasosList pasoslist= new PasosList();
			pasoslist.data=Arrays.asList(pasos_actuales2);
			
			dm.setPasosActualesObject(pasos_actuales2);
		}
		 pasos_actuales = dm.getPasosActualesObject();		
		 interface_pasos = new ArrayList<InterfacePasos>();
		for (int i = 0; i < pasos_actuales.data.size(); i++) {
			
			if(pasos_actuales.data.get(i).getFoto()==0){
				
				interface_pasos.add(new InterfacePasos(interfaceDinamica.get_edit_text(pasos_actuales.data.get(i).getValor(), pasos_actuales.data.get(i).getId_paso())));
				ly.addView(interfaceDinamica.get_titulo_seccion(pasos_actuales.data.get(i).getDesc_paso()));
				//ly.addView(interfaceDinamica.get_edit_text("", pasos_actuales.data.get(i).getId_paso()));			
				ly.addView(interface_pasos.get(i).editeText);
				ly.addView(interfaceDinamica.get_espacio_en_blanco());
				ly.addView(interfaceDinamica.get_linea_secciones());
			}else{
				ly.addView(interfaceDinamica.get_titulo_seccion(pasos_actuales.data.get(i).getDesc_paso()));
				interface_pasos.add(new InterfacePasos(interfaceDinamica.get_edit_text_enable(pasos_actuales.data.get(i).getValor(), pasos_actuales.data.get(i).getId_paso())));
				ly.addView(interface_pasos.get(i).editeText);				
				//interface_pasos.add(new InterfacePasos(interfaceDinamica.get_button(pasos_actuales.data.get(i).getDesc_paso(), pasos_actuales.data.get(i).getId_paso())));				
				//ly.addView(interface_pasos.get(i).button);
				Button bb=interfaceDinamica.get_button(pasos_actuales.data.get(i).getDesc_paso(), pasos_actuales.data.get(i).getId_paso());
				final int index=i;
				bb.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int cant=0;
						if(interface_pasos.get(index).editeText.getText().toString().length()>0){
							cant=Integer.parseInt(interface_pasos.get(index).editeText.getText().toString());
						}
						cant++;
						interface_pasos.get(index).editeText.setText(String.valueOf(cant));
					}
				});
				ly.addView(bb);
			}
			
		}
		
		
	}
	public void confirmacion_guardar_fin(){		
		//dm.add_confirmacion(confirmar);
	    Ot ot_actual = dm.get_ultima_ot();	   
		dm.Ot_finalizarAdd2(ot_actual,  "", DatabaseManager.ESTADO_FINALIZAR,5);
		//request_confirmar_ot();
	}
	public void confirmacion_guardar(){
		for (int i = 0; i < pasos_actuales.data.size(); i++) {
			//if(pasos_actuales.data.get(i).getFoto()==0){
				
				Util.Log("=>Edit("+i+")="+interface_pasos.get(i).editeText.getText().toString());
				pasos_actuales.data.get(i).setValor(interface_pasos.get(i).editeText.getText().toString());
			
			//}
			
		}
		
		dm.setPasosActualesObject(pasos_actuales);
	}
//------------------------------------------------	
	public boolean confirmacion_guardar_controlar(){
		PasosList pasos_actuales2 = dm.getPasosActualesObject();
		boolean b= true;
		for (int i = 0; i < pasos_actuales2.data.size(); i++) {				
				if(pasos_actuales2.data.get(i).getObligatorio()==1){
					String s=interface_pasos.get(i).editeText.getText().toString();
					if(s.length()==0){
						Util.SpSet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.ULTIMO_ERROR, pasos_actuales2.data.get(i).getDesc_paso());
						b=false;
						break;
					}
					if(s.equals("")){
						Util.SpSet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.ULTIMO_ERROR, pasos_actuales2.data.get(i).getDesc_paso());
						
						b=false;
						break;
					}
					
				}		
		}
		return b;
		
	}
	
	public  interface OnOtClickOpciones2  {
	    // you can define any parameter as per your requirement
		void onClickOpcion( int opcion);
	}
	public OnOtClickOpciones2 getOnOtClickOpciones() {
		return onOtClickOpciones2;
	}
	public void setOnOtClickOpciones2(OnOtClickOpciones2 onOtClickOpciones) {
		this.onOtClickOpciones2 = onOtClickOpciones;
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		confirmacion_guardar();
		Util.Log("=>Pause->guardado");
		super.onPause();
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		Util.Log("=>Stop");
		super.onStop();
	}
	class InterfacePasos {
		EditText editeText;
		Button button;
		public InterfacePasos(EditText editeText){
			this.editeText=editeText;
		}
		public InterfacePasos(Button button){
			this.button=button;
		}
	}
	
}
