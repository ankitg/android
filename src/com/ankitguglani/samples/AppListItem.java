package com.ankitguglani.samples;

public class AppListItem {
	private int id;
	private String displayName;
	private String className;
	private int imageResourceId;
	private boolean isInternal;
	
	public AppListItem (int id, String displayName, String className, int imageResourceId, boolean isIntenal)
	{
		setId(id);
		setDisplayName(displayName);
		setClassName(className);
		if(imageResourceId != 0)
		{
			setImageResourceId(imageResourceId);
		}
		else
		{
			setImageResourceId(R.drawable.ic_launcher);
		}
		setInternal(isIntenal);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public int getImageResourceId() {
		return imageResourceId;
	}
	public void setImageResourceId(int imageResourceId) {
		this.imageResourceId = imageResourceId;
	}

	public boolean isInternal() {
		return isInternal;
	}

	public void setInternal(boolean isInternal) {
		this.isInternal = isInternal;
	}
}
