package com.ankitguglani.samples.notes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ankitguglani.samples.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NotesListActivity extends Activity {
	
	private List<Note> notes = new ArrayList<Note>();
	private ListView notesListView;
	private int editingNoteIndex = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notes_list_layout);
		
		notesListView = (ListView)findViewById(R.id.notesListView);
		
		notesListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int itemIndex,
					long id) {
				Intent editNoteIntent = new Intent(view.getContext(), EditNoteActivity.class);
				editNoteIntent.putExtra("Note", notes.get(itemIndex));
				//startActivity(editNoteIntent);
				editingNoteIndex = itemIndex;
				startActivityForResult(editNoteIntent, 1);
			}
		});
		
		registerForContextMenu(notesListView);
		
		notes.add(new Note("Mano", "Hi Mano. This is a sample note.", new Date()));
		notes.add(new Note("David", "Hey Dave. Good luck with fatherhood buddy!", new Date()));
		
		populateList();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.notes_context_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		//return super.onContextItemSelected(item);
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
		notes.remove(info.position);
		populateList();
		return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notes_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent editNoteIntent = new Intent(this, EditNoteActivity.class);
//		startActivity(editNoteIntent);
		editingNoteIndex = -1;
		startActivityForResult(editNoteIntent, 1);
		
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data != null)
		{
			Serializable extra = data.getSerializableExtra("Note");
			if(extra != null)
			{
				Note newNote = (Note)extra;
				if(newNote.getTitle().toString().length() > 0 || newNote.getNote().toString().length() > 0)
				{

					if(editingNoteIndex > -1)
					{
						notes.set(editingNoteIndex, newNote);
					}
					else
					{
						notes.add(newNote);
					}
					populateList();
				}
			}
		}
	}

	private void populateList() {

		List<String> values = new ArrayList<String>();
		for (Note note:notes)
		{
			values.add(note.getTitle());
		}
		
		ArrayAdapter<String> notesListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		notesListView.setAdapter(notesListAdapter);
	}
}
