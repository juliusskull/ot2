package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.GeometriaFrag.OnADDDistancia;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
public class GeometriaActivity extends ActionBarActivity {
	private DatabaseManager dm;
	private GeometriaFrag    geometria_one= new  GeometriaFrag();
	private GeometriaDistanciaFrag    geometria_one_distancia= new  GeometriaDistanciaFrag();
	private MenuItem okItem;
	public  static int PANTALLA_GEOMETRIA_ONE=1;
	public  static int PANTALLA_GEOMETRIA_ONE_DISTANCIA=2;
	private int pantalla_actual;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_geometrias);
		dm = new DatabaseManager(this);		
		//dm.set_ultimo_featid("323589");
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.container2, geometria_one).commit();		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		pantalla_actual=PANTALLA_GEOMETRIA_ONE;
		
		geometria_one.setOnEventListener(new OnADDDistancia() {
			
			@Override
			public void onClickList(int er) {
				// TODO Auto-generated method stub
				//Utils.showDialog(GeometriaActivity.this, "Add Distancia");
				pantalla_actual=PANTALLA_GEOMETRIA_ONE_DISTANCIA;
				getSupportFragmentManager().beginTransaction()
				.replace(R.id.container2, geometria_one_distancia).commit();	
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {	
				getMenuInflater().inflate(R.menu.main_geometria_one, menu);		
				okItem= menu.findItem(R.id.action_geom_ok);
				okItem.setOnMenuItemClickListener(onOkMenuItemClickListener);
						
		return super.onCreateOptionsMenu(menu);
	}  
	
	OnMenuItemClickListener onOkMenuItemClickListener=new OnMenuItemClickListener(){
		@Override
		public boolean onMenuItemClick(MenuItem item) {
			//  geometria_one.			
			//FirebaseFirestore db = FirebaseFirestore.getInstance();
			if(	pantalla_actual==PANTALLA_GEOMETRIA_ONE_DISTANCIA){
				 Utils.showDialog(GeometriaActivity.this, "Esta por agregar una nueva distancia", "Ok", "Cancelar"
						 ,new android.content.DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							
							geometria_one_distancia.add_distnacias();
						}
				 });
			
			}else{
				 Utils.showDialog(GeometriaActivity.this, "Esta por actualizar el tramo featid nro"+dm.get_ultimo_featid() , "Ok", "Cancelar"
						 ,new android.content.DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							
							 geometria_one.set_geometria();
						}
				 });
				
			}
			
			return true;
		}
	};
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		finish();
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.home) {
			 finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
