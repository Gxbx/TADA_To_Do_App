package com.example.tada;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class HistorialVoz extends ActionBarActivity implements OnClickListener, OnItemClickListener, OnItemLongClickListener {

	String ruta;
	private static final String LOG_TAG = "Grabadora";
	private MediaPlayer mediaPlayer;
	Button btn_reproducir;
	String [] datoslista;
	ListView lv;
	TextView tvoz;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.historial_voz);
		btn_reproducir = (Button) findViewById(R.id.btnreproducir);
		tvoz = (TextView) findViewById(R.id.txt_voz_m);
		lv = (ListView) findViewById(R.id.lista_nota_texto1);
		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
		datoslista = getResources().getStringArray(R.array.llenar);
		btn_reproducir.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		ruta = Environment.getExternalStorageDirectory().getPath()+"/TaDa/NotaDeAudio1.3gp";
		mediaPlayer = new MediaPlayer();
	    try {
	        mediaPlayer.setDataSource(ruta);
	        mediaPlayer.prepare();
	        mediaPlayer.start();
	    } catch (IOException e) {
	        Log.e(LOG_TAG, "Fallo en reproducción");
	    }
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		tvoz.setText(datoslista[position]);
	}
}
