package Manage.HelperClasses;

public class Post {

    private int postId;
    private int userId;
    String text;

    public Post(int postId, int userId, String text) {
        this.postId = postId;
        this.userId = userId;
        this.text = text;
    }

    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }
}
