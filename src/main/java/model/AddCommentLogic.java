package model;

public class AddCommentLogic {
    public void executeAddComment(Board bo) {
        AddCommentDAO acdao = new AddCommentDAO(bo);
    }
}
