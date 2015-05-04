package core;

public class PostNotFoundException extends RuntimeException {
	private long id;
	
	public PostNotFoundException(long id) {
		this.id = id;
	}
	public long getPostId() {
		return this.id;
	}
}