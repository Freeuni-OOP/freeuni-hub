package Manage.HelperClasses;

public class Comment {

    private Post post;
    private String comment;
    private int userId;

    public Comment(Post post, String comment, int userId) {
        this.post = post;
        this.comment = comment;
        this.userId = userId;
    }

    public Post getPost() {
        return post;
    }

    public String getComment() {
        return comment;
    }

    public int getUserId() {
        return userId;
    }
}
