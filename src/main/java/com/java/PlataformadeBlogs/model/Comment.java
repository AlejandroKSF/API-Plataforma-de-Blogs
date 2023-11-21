package com.java.PlataformadeBlogs.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Comment implements Serializable{
	private static final long serialVersionUID = 1L;
	private static Long nextId=1L;
    private Long id;
    private Long postId;
    private String commenter;
    private String content;
    private LocalDateTime commentedDate = LocalDateTime.now();
    
    public Comment() { }

    public Comment(Long id) {  this.id = id; }

    public Long generateId() {
        return nextId++;
    }
    // getters e setters
    // hashCode e equals

	public static Long getNextId() {
		return nextId;
	}

	public static void setNextId(Long nextId) {
		Comment.nextId = nextId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {this.id = id;}
	
    public void setCommenter(String commenter) {this.commenter = commenter;}
    public String getCommenter() {return commenter;}
    
	public void setPostId(Long postId) {this.postId = postId;}
    public Long getPostId() {return postId;}
    

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}
    
    public LocalDateTime getCommentedDate() {return commentedDate;}
    public void CommentedDate(LocalDateTime commentedDate) {this.commentedDate = commentedDate;}
    
	public static long getSerialversionuid() {return serialVersionUID;}
	
	@Override
	public int hashCode() {return Objects.hash(postId, id, content, commenter, commentedDate);}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(id, other.id);
	}
}
