package com.example.weiducinema.bean;

/**
 * created by fxb
 * 2019/1/29 11:40
 */
public class CommentBean {
        /**
         * commentId : 3
         * commentTime : 1533117674000
         * greatNum : 0
         * replyNum : 0
         * commentHeadPic : http://172.17.8.100/images/head_pic/bwjy.jpg
         * hotComment : 0
         * isGreat : 1
         * movieComment : 恐龙界的霸主！
         * commentUserId : 6
         * commentUserName : 谁的益达
         */

        private int commentId;
        private long commentTime;
        private int greatNum;
        private int replyNum;
        private String commentHeadPic;
        private int hotComment;
        private int isGreat;
        private String movieComment;
        private int commentUserId;
        private String commentUserName;

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getHotComment() {
            return hotComment;
        }

        public void setHotComment(int hotComment) {
            this.hotComment = hotComment;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }

        public String getMovieComment() {
            return movieComment;
        }

        public void setMovieComment(String movieComment) {
            this.movieComment = movieComment;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

}
