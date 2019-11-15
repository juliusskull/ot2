package com.aguasnortesalta.ordenes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import android.view.View;

@SuppressLint("NewApi")
public class ViewPrintAdapter extends PrintDocumentAdapter {

@SuppressLint("NewApi")
private PrintedPdfDocument mDocument;
private Context mContext;
private View mView;

public ViewPrintAdapter(Context context, View view) {
    mContext = context;
    mView = view;
}

@Override
public void onLayout(PrintAttributes oldAttributes,
		PrintAttributes newAttributes, CancellationSignal cancellationSignal,
		LayoutResultCallback callback, Bundle extras) {
	// TODO Auto-generated method stub
	
}

@Override
public void onWrite(PageRange[] pages, ParcelFileDescriptor destination,
		CancellationSignal cancellationSignal, WriteResultCallback callback) {
	// TODO Auto-generated method stub
	
}




}