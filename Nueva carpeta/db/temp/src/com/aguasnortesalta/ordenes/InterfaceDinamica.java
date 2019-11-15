package com.aguasnortesalta.ordenes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.aguasnortesalta.ordenes.utils.Utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TimePicker;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class InterfaceDinamica {
	Activity context;
    int LINEA_SECCIONES =3;
    int LINEA_ITEM =1;
	public InterfaceDinamica(Activity context){
		this.context=context;
		
	}
	public View get_linea_secciones(){
		View view=new View(context);
		view.setLayoutParams(new FrameLayout.LayoutParams(
	            LayoutParams.WRAP_CONTENT,
	            LINEA_SECCIONES
	    ));
		view.setBackgroundColor(Color.BLACK);
		return view;
	}
	public View get_linea_item(){
		View view=new View(context);
		view.setLayoutParams(new FrameLayout.LayoutParams(
	            LayoutParams.WRAP_CONTENT,
	            LINEA_ITEM
	    ));
		view.setBackgroundColor(Color.BLACK);
		return view;
	}
	public TextView get_label(String titulo, String descripcion) {
		TextView linea_tipo1= new TextView(context);
		linea_tipo1.setText(Html.fromHtml("<b>"+titulo+":</b>"+descripcion));		
		return linea_tipo1;
	}
	public TextView get_label_referencia(String descripcion) {
		TextView linea_tipo1= new TextView(context);
		linea_tipo1.setText(Html.fromHtml(descripcion));		
		return linea_tipo1;
	}
	public TextView get_titulo_seccion(String titulo) {
		TextView linea_tipo1= new TextView(context);
		linea_tipo1.setText(Html.fromHtml("<h3><b>"+titulo+"</b></h3>"));		
		return linea_tipo1;
	}
	public TextView get_titulo_label(String titulo) {
		TextView linea_tipo1= new TextView(context);
		linea_tipo1.setText(Html.fromHtml("<b>"+titulo+"</b>"));		
		return linea_tipo1;
	}
	public TextView get_espacio_en_blanco(){
		TextView linea3= new TextView(context);
		linea3.setLineSpacing(2, 1);
		return linea3;
	}
	public CheckBox get_check_box(String titulo){
		
		CheckBox chek= new CheckBox(context);
		
		chek.setText(titulo);
		chek.setChecked(true);
		//chek.setEnabled(false);
		return chek;
	}
	public EditText get_edit_text(String titulo){		
	    EditText edit= new EditText(context);		
	    edit.setText(titulo);
	  
		return edit;
	}
	public EditText get_edit_text(String titulo, int id){		
	    EditText edit= new EditText(context);		
	    edit.setText(titulo);
	    edit.setId(id);
		return edit;
	}
	public EditText get_edit_text_enable(String titulo, int id){		
	    EditText edit= new EditText(context);
	    edit.setEnabled(false);
	    edit.setText(titulo);
	    edit.setId(id);
		return edit;
	}
	
	
	public EditText get_edit_date(String titulo, int id){		
	    final EditText fch= new EditText(context);		
	    fch.setText(titulo);
	    fch.setId(id);
	    fch.setKeyListener(null);
	    
	    final Calendar myCalendar = Calendar.getInstance();
    	final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

		    @Override
		    public void onDateSet(DatePicker view, int year, int monthOfYear,
		            int dayOfMonth) {
		        // TODO Auto-generated method stub
		        myCalendar.set(Calendar.YEAR, year);
		        myCalendar.set(Calendar.MONTH, monthOfYear);
		        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		        
		        String myFormat = "dd/MM/yy"; //In which you need put here
			    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
			    //Utils.showDialog(context, "F:"+sdf.format(myCalendar.getTime()));				    
			    fch.setText(sdf.format(myCalendar.getTime()));
		    }

		};
		fch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  new DatePickerDialog(context, date1, myCalendar
			                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
			                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
	    
		return fch;
	}
	
	public EditText get_edit_time(String titulo, int id){
	    final EditText fch= new EditText(context);		
	    fch.setText(titulo);
	    fch.setId(id);
	    fch.setKeyListener(null);
	    
	    final Calendar myCalendar = Calendar.getInstance();
	    /*
    	final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

		    @Override
		    public void onDateSet(DatePicker view, int year, int monthOfYear,
		            int dayOfMonth) {
		        // TODO Auto-generated method stub
		   	 Calendar mcurrentTime = Calendar.getInstance();
	            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
	            int minute = mcurrentTime.get(Calendar.MINUTE);
	            TimePickerDialog mTimePicker;
	            mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
	                @Override
	                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
	                	fch.setText( selectedHour + ":" + selectedMinute);
	                }
	            }, hour, minute, true);//Yes 24 hour time
	            mTimePicker.setTitle("Select Time");
	            mTimePicker.show();
		    }

		};
		*/
		fch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Calendar mcurrentTime = Calendar.getInstance();
		            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
		            int minute = mcurrentTime.get(Calendar.MINUTE);
		            TimePickerDialog mTimePicker;
		            mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
		                @Override
		                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
		                	fch.setText( selectedHour + ":" + selectedMinute);
		                }
		            }, hour, minute, true);//Yes 24 hour time
		            mTimePicker.setTitle("Select Time");
		            mTimePicker.show();
			}
		});
	    
		return fch;
	}
	public EditText get_edit_text(String titulo, int id, int tipo){		
	    EditText edit= new EditText(context);		
	    edit.setText(titulo);
	    edit.setId(id);
	    edit.setInputType(tipo);
		return edit;
	}
	public Spinner get_spinner(String titulo, int id,int index,  SpinnerAdapter adapter){		
		Spinner edit= new Spinner(context);		
	    edit.setAdapter(adapter);
	    edit.setId(id);
	    edit.setSelection(index);
		return edit;
	}
	public Button get_button(String titulo, int id){
		 Button btn = new Button(context);	
		 btn.setText(titulo);
		 btn.setId(id);
		 return btn;
	}
	public Button get_button_foto(String titulo, int id){
		 Button btn = new Button(context);	
		 btn.setText(titulo);
		 btn.setId(id);
		 return btn;
	}
}
