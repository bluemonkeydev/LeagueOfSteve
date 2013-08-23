package com.bluemonkeydev.leagueofsteve;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends Activity implements OnCompletionListener {

	private boolean playing = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);

		VideoView vView = (VideoView)findViewById(R.id.videoView1);  

		vView.requestFocus();

		String vSource = "android.resource://com.bluemonkeydev.leagueofsteve/" + R.raw.losvideo;  
		vView.setVideoURI(Uri.parse(vSource));  
		vView.setMediaController(new MediaController(this));
		vView.setClickable(true);
		vView.setZOrderMediaOverlay(true);
		vView.setOnCompletionListener(this);
		vView.start();
		playing = true;
	}
	
	@Override
	public void onBackPressed() {
		if (playing) {
			Toast.makeText(this, "Sorry, you must watch the full video", Toast.LENGTH_LONG).show();
			return;
		}
		finish();
	}
	
	@Override
	public void onCompletion(MediaPlayer mp) {
		playing = false;
	}
}
