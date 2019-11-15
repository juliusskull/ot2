package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.utils.Util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class FiltrosFragment extends Fragment{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.filtro_mapas, container,
				false);
		 Util.Log("=>(0)onCreateView") ;
		return rootView;
	}
	 @Override
	  public void onActivityCreated(Bundle state) {
	        super.onActivityCreated(state);
	       /* txt_html=(TextView)getActivity().findViewById(R.id.txt_content_html);
	        */
	      Util.Log("=>(1)onActivityCreated") ;
	   
	     
	 }
}
