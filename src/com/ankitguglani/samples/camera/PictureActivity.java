package com.ankitguglani.samples.camera;

import java.io.IOException;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.ankitguglani.samples.R;
import com.ankitguglani.samples.SamplesListActivity;

public class PictureActivity extends Activity implements View.OnClickListener {
	
	private static final String TAG = "com.ankitguglani.samples.camera.PictureActivity";
	private static final Class<PictureActivity> SELF = com.ankitguglani.samples.camera.PictureActivity.class;	
	
	ImageView pictureImageView;
	Button setWallpaperButton;
	ImageButton takePictureImageButton;
	Intent takePictureIntent;
	final static int cameraData = 0;
	Bitmap bmp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picture_layout);
		initialize();
	}

	private void initialize() {
		pictureImageView = (ImageView)findViewById(R.id.pictureImageView);
		setWallpaperButton = (Button)findViewById(R.id.setWallpaperButton);
		takePictureImageButton = (ImageButton)findViewById(R.id.takePictureImageButton);
		
		// Initialize the picture view to the current wallpaper.
		if(bmp == null)
			{
				pictureImageView.setImageDrawable(getWallpaper());
				// set bmp to wallpaper here.
			}

		// Add OnClick Listeners
		setWallpaperButton.setOnClickListener(this);
		takePictureImageButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch(v.getId())
		{
			case R.id.takePictureImageButton:
			{
				takePictureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(takePictureIntent, cameraData);
				break;
			}
			
			case R.id.setWallpaperButton:
			{
				try {
					WallpaperManager.getInstance(getApplicationContext()).setBitmap(bmp);
					Toast.makeText(PictureActivity.this, "The new wallpaper has been set.", Toast.LENGTH_SHORT).show();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK)
		{
			Bundle extras = data.getExtras();
			bmp = (Bitmap)extras.get("data");
			pictureImageView.setImageBitmap(bmp);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
}
