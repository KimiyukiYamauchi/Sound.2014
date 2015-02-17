package com.example.sound;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity 
	implements OnClickListener, OnCompletionListener
{
	
	private MediaPlayer player = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.WHITE);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		Button btn1 = new Button(this);
		btn1.setText("PLAY RESOURCE SOUND");
		btn1.setTag("btn1");
		btn1.setOnClickListener(this);
		layout.addView(btn1);
		
		Button btn2 = new Button(this);
		btn2.setText("PLAY SOUND FILE");
		btn2.setTag("btn2");
		btn2.setOnClickListener(this);
		layout.addView(btn2);
		
		Button btn3 = new Button(this);
		btn3.setText("STOP SOUND");
		btn3.setTag("btn3");
		btn3.setOnClickListener(this);
		layout.addView(btn3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		stopSound();
	}
	
	private void playSound(int resid){
		if(player != null){
			return;
		}
		player = MediaPlayer.create(this, resid);
		player.seekTo(0);
		player.setOnCompletionListener(this);
		player.start();
	}
	
	private void playSound(String path){
		if(player != null){
			return;
		}
		player = new MediaPlayer();
		try {
			player.setDataSource(path);
			player.prepare();
			player.seekTo(0);
			player.setOnCompletionListener(this);
			player.start();
		} catch (Exception e) {
		}
	}
	
	private void stopSound(){
		if(player == null){
			return;
		}
		player.stop();
		player.setOnCompletionListener(null);
		player.release();
		player = null;
	}

	@Override
	public void onClick(View v) {
		String tag = (String)v.getTag();
		if(tag == "btn1"){
			playSound(R.raw.test1);
		}else if(tag == "btn2"){
			playSound(
                Environment.getExternalStorageDirectory() 
                	+ "/Music/n75.mp3");
		}else if(tag == "btn3"){
			stopSound();
		}
	}
}
