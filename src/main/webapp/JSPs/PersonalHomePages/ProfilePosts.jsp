<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.math.*,DataBaseConnection.BaseConnector, java.util.*,Manage.HelperClasses.*,java.io.IOException,java.sql.SQLException" %>
<html>

<head>

    <title> სალამი ${username} </title>
    <h1> ჩემი პოსტები: </h1>
</head>
<body>

        <%
            Map<Post,List<Comment>> all = ( Map<Post,List<Comment>>)request.getSession().getAttribute("all");
            if(all !=  null){
                for(Post post : all.keySet()){
                    String text = post.getText();
                    int id = post.getPostId();
                    List<Comment> commentList = all.get(post);
                    %>
                    <p><%=text%></p>
                    <%
                    if(commentList == null||commentList.size()==0){
                        %>   უკომენტაროდ <%
                    }else{
                        for(Comment comment : commentList){
                            int user_id = comment.getUserId();
                            String commentorName ="";
                            try{
                                UserById ubi = new UserById(new BaseConnector());
                                User user = ubi.getUser(user_id);
                                commentorName=user.getUserName();
                            }catch (SQLException throwables) {
                                throwables.printStackTrace();
                             } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                             }
                            %>
                           <%=commentorName%>-ს კომენტარი: <%=comment.getComment()%>
                           <p> </p>
                           <%
                        }
                    }
                    %>
                     <form action="/addComment" method="post">
                     <input type = "text" name="commentText" id="commentText" size="30">
                     <button> დაამატე კომენტარი </button>
                     <input type = "hidden" name="username" value = ${username}>
                     <input type = "hidden" name="postId" value = <%=id%> >
                     </form>
               <% }
            }
        %>
         <form action="/addPost" method="post">
         <input type = "text" name="postText" id="postText" size="30">
         <button> დაამატე პოსტი </button>
         <input type = "hidden" name="username" value = ${username}>
         </form>
</body>
</html>