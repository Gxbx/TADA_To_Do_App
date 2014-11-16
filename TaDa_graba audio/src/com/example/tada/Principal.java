package com.example.tada;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class Principal extends ActionBarActivity implements OnClickListener{

	Button btn_record, btn_stop, btn_play; 
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
        
        btn_record.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_play.setOnClickListener(this);
        
        btn_stop.setEnabled(false);
        btn_play.setEnabled(false);
    }
	@Override
	public void onClick(View v) {
		
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
		         mediaPlayer.setVolume(1, 9);
		         informacion.setText("Reporduciendo");
		     } catch (IOException e) {
		         Log.e(LOG_TAG, "Fallo en reproducción");
		     }
		   }
		}
	}
