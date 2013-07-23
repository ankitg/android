package com.ankitguglani.samples.notes;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ankitguglani.samples.R;

public class EditNoteActivity extends Activity implements View.OnClickListener {

	private boolean isInEditMode = true;
	
	EditText titleEditText, noteEditText;
	TextView dateTextView;
	Button saveButton, emailButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_note_layout);

		initialize();
		
		Serializable extra = getIntent().getSerializableExtra("Note");
		if(extra != null)
		{
			Note note = (Note)extra;
			titleEditText.setText(note.getTitle());
			noteEditText.setText(note.getNote());
			
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			dateTextView.setText(dateFormat.format(note.getDate()));
			
			isInEditMode = !isInEditMode;
			titleEditText.setEnabled(isInEditMode); // Toggle editable state of title
			noteEditText.setEnabled(isInEditMode); // Toggle editable state of the note
			saveButton.setText("Edit");
		}
		
		saveButton.setOnClickListener(this);
		emailButton.setOnClickListener(this);
	}

	private void initialize() {
		titleEditText = (EditText)findViewById(R.id.titleEditText);
		noteEditText = (EditText)findViewById(R.id.noteEditText);
		dateTextView = (TextView)findViewById(R.id.dateTextView);
		saveButton = (Button)findViewById(R.id.saveButton);
		emailButton = (Button)findViewById(R.id.emailButton);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notes_edit, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		int clickedViewId = v.getId();
		switch(clickedViewId)
		{
			case R.id.saveButton:
			{
				// Toggle edit mode.
				isInEditMode = !isInEditMode;
				titleEditText.setEnabled(isInEditMode); // Toggle editable state of title
				noteEditText.setEnabled(isInEditMode); // Toggle editable state of the note
	
				
				if(isInEditMode)
				{
					saveButton.setText("Save");
				}
				else
				{
					saveButton.setText("Edit");
	
					// Add date/time stamp.
	
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					String date = dateFormat.format(Calendar.getInstance().getTime());
					dateTextView.setText(date);
					
					Intent returnIntent = new Intent();
					Note note = new Note(titleEditText.getText().toString(), noteEditText.getText().toString(), Calendar.getInstance().getTime());
					returnIntent.putExtra("Note", note);
					setResult(RESULT_OK, returnIntent);
					finish();
				}
				break;
			}
			
			case R.id.emailButton:
			{
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				emailIntent.setType("plain/text");
//				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailaddress);
				if(titleEditText.getText().toString().length() > 0)
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, titleEditText.getText().toString());
				if(noteEditText.getText().toString().length() > 0)
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, noteEditText.getText().toString());
				if(titleEditText.getText().toString().length() > 0 || noteEditText.getText().toString().length() > 0)
				startActivity(emailIntent);
				break;
			}
			
			default:
				break;
		}
	}

}
