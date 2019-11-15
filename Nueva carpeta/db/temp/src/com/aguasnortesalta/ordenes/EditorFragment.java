package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.model.Editor;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class EditorFragment extends Fragment{
	private static final String ARG_SECTION_NUMBER = "";
	private Editor editor;
	private int editor_activo=0;
	private WebView myWebView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.editor_view, container,
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
	      myWebView = (WebView) getActivity().findViewById(R.id.txtOTDescripcion);	      
	     
	      if(editor_activo==1) {
	    	   myWebView.loadData(Util.justifyText(editor.getContent()),"text/html;charset=utf-8", null);
	      }
	     
	 }
	 

	public static EditorFragment newInstance(int sectionNumber, Editor er) {
		EditorFragment fragment = new EditorFragment();
		fragment.setArgumentos(er);
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		
		return fragment;
	}
	public void setArgumentos(Editor er) {
		// TODO Auto-generated method stub
		editor_activo=1;
		this.editor=er;
		//WebView myWebView = (WebView) getActivity().findViewById(R.id.webView1);
		Util.Log("editor_activo(3)=>"+editor_activo);
        Util.Log(editor.getContent());
        
       // myWebView = (WebView) getActivity().findViewById(R.id.webView1);
       //  myWebView.loadData(justifyText(editor.getContent()),"text/html;charset=utf-8", null);
        // myWebView.loadUrl(editor.getContent());
	}

}
