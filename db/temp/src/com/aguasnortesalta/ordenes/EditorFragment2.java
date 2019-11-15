package com.aguasnortesalta.ordenes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class EditorFragment2  extends Fragment{
	private static final String ARG_SECTION_NUMBER = "section_number";
	private WebView myWebView;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.editor_view, container,
				false);
		return rootView;
	}
	 @Override
	  public void onActivityCreated(Bundle state) {
	        super.onActivityCreated(state);		 
	       	           
	        myWebView = (WebView) getActivity().findViewById(R.id.txtOTDescripcion);
	        myWebView.loadUrl("http://ceseca2.cant1.com/ce/app/index.php?tabla=circular&accion=ver&id=28");
	        
	       
	       
	        
	 }
		public static EditorFragment2 newInstance(int sectionNumber) {
			EditorFragment2 fragment = new EditorFragment2();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);			
			return fragment;
		}
}
