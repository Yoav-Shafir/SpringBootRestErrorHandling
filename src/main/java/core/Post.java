package core;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Post {
	private long id;
	
	@NotNull
	@Size(max=5, message="{max5}")
	private String title; 
	
	@NotNull
	@Size(min=25, message="{min25}")
	private String desc;
	
	public Post() {
		
	}
	
	public Post(long id, String title, String desc) {
		this.setId(id);
		this.setTitle(title);
		this.setDesc(desc);
	}
	
	public String getDesc() {
		return desc;
	}
	
	public long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
