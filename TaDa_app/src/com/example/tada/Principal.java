package com.example.tada;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Principal extends ActionBarActivity implements OnClickListener{

	Button btn_record, btn_stop, btn_play, btn_guardar;
	EditText nota_texto;
	TextView informacion;
	private static final String LOG_TAG = "Grabadora";          
	private MediaRecorder mediaRecorder;
	private MediaPlayer mediaPlayer;
	private static String fichero = Environment.getExternalStorageDirectory().getAbsolutePath()+"/audio.3gp";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        
        informacion = (TextView) findViewById(R.id.info);
        btn_record = (Button) findViewById(R.id.bGrabar);
        btn_stop = (Button) findViewById(R.id.bDetener);
        btn_play = (Button) findViewById(R.id.bReproducir);
        btn_guardar = (Button) findViewById(R.id.bGuardar);
        nota_texto = (EditText) findViewById(R.id.et_enviar);
        
        btn_record.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_play.setOnClickListener(this);
        btn_guardar.setOnClickListener(this);
        
        btn_stop.setEnabled(false);
        btn_play.setEnabled(false);
    }
	@Override
	public void onClick(View v) {
//---------------------------------------------------NOTAS DE VOZ--------------------------------------------------
		switch (v.getId()) {
		case R.id.bGrabar:
			mediaRecorder = new MediaRecorder();
		    mediaRecorder.setOutputFile(fichero);
		    mediaRecorder.setAudioChannels(1);
		    mediaRecorder.setAudioSamplingRate(200);
		    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT); 
		    try {
		        mediaRecorder.prepare();
		    } catch (IOException e) {
		        Log.e(LOG_TAG, "Fallo en grabación");
		    }
		    mediaRecorder.start();
		    informacion.setText("grabando");
		    btn_record.setEnabled(false);
		    btn_stop.setEnabled(true);
		    btn_play.setEnabled(false);
			break;
		case R.id.bDetener:
		     mediaRecorder.stop();
		     mediaRecorder.release();
		     informacion.setText("detenido");
			    btn_record.setEnabled(false);
			    btn_stop.setEnabled(false);
			    btn_play.setEnabled(true);
			break;
		case R.id.bReproducir:
			mediaPlayer = new MediaPlayer();
		     try {
				    btn_record.setEnabled(true);
				    btn_stop.setEnabled(false);
				    btn_play.setEnabled(true);
		         mediaPlayer.setDataSource(fichero);
		         mediaPlayer.prepare();
		         mediaPlayer.start();
		         informacion.setText("Reporduciendo");
		     } catch (IOException e) {
		         Log.e(LOG_TAG, "Fallo en reproducción");
		     }
		     break;
//---------------------------------------------------NOTAS DE TEXTO--------------------------------------------------		     
		case R.id.bGuardar:
//--------------------------------CAPTURA DE CATEGORIA-----------------------------------------------------------
//--------------------------------CAPTURA DE TEXTO---------------------------------------------------------------
			String text2send = nota_texto.getText().toString();
//--------------------------------CAPTURA DE FECHA---------------------------------------------------------------
	        Calendar calendario = new GregorianCalendar();
	        Date date = calendario.getTime();
	        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	        
	        String formatteDate = df.format(date);
//--------------------------------CAPTURA DE HORA----------------------------------------------------------------

        	Date dt = new Date();
        	SimpleDateFormat dh = new SimpleDateFormat("HH:mm:ss");
        	String formatteHour = dh.format(dt.getTime());
        	
    		//informacion.setText(text2send +"/"+ formatteDate +"/"+ formatteHour);
//--------------------------------CAPTURA DE POSICION------------------------------------------------------------
	        
			break;
		   }
	}
	}
