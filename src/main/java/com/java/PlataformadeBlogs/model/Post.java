package com.java.PlataformadeBlogs.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Long nextId=1L;
    private Long id;
    private String title;
    private String content;
    private String author;
    private String hashtags;
    private LocalDateTime createdDate = LocalDateTime.now();
    private int inative;
    
    public Post() {}
    public Post(Long id) {this.id = id;}
    public Long generateId() {return nextId++;}
    public static void setNextId(Long nextId) {Post.nextId = nextId;}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}
    public String getHashtags() {return hashtags;}
    public void setHashtags(String hashtags) {this.hashtags = hashtags;}
    public LocalDateTime getCreatedDate() {return createdDate;}
    public void setCreatedDate() {this.createdDate = LocalDateTime.now();}
    public int getInative() {return inative;}
    public void setInative(int inative) {this.inative = inative;}
	public static long getSerialversionuid() {return serialVersionUID;}
	
	@Override
	public int hashCode() {return Objects.hash(title, id, content, author, hashtags, createdDate, inative);}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(id, other.id);
	}
}
