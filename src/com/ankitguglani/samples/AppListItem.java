package com.ankitguglani.samples;

public class AppListItem {
	private int id;
	private String displayName;
	private String className;
	private int imageResourceId;
	
	public AppListItem (int id, String displayName, String className, int imageResourceId)
	{
		setId(id);
		setDisplayName(displayName);
		setClassName(className);
		setImageResourceId(imageResourceId);
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
}
