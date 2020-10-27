/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showDialogDeleteComment(userId, commentId, articleId) {
    var result = confirm("You want to delete this comment?");
    if (result) {
        window.location.href = "MainController?btnAction=DeleteComment" +
            "&commentId=" + commentId +
            "&articleId=" + articleId +
            "&userId=" + userId;
    }
}

function showDialogDeleteArticle(userId, articleId) {
    var result = confirm("You want to delete this article?");
    if(result) {
        window.location.href = "MainController?btnAction=DeleteArticle" +
            "&userId=" + userId +
            "&articleId=" + articleId;
    }
}



//document.getElementById("post-article").addEventListener("click", function(event){
//    event.preventDefault()
//});

//
// if ( window.history.replaceState ) {
//         window.history.replaceState( null, null, window.location.href );
//     }

