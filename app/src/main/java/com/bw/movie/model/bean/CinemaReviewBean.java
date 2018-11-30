package com.bw.movie.model.bean;

import java.util.List;

/**
 * @author hyy
 * @date 2018/11/19
 */
public class CinemaReviewBean {

    /**
     * result : [{"commentContent":"666","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920095505.png","commentId":549,"commentTime":1540292808000,"commentUserId":413,"commentUserName":"皮皮猪之王","greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"666","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920095505.png","commentId":546,"commentTime":1540291804000,"commentUserId":413,"commentUserName":"皮皮猪之王","greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"斤斤计较","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-08-31/20180831154205.png","commentId":450,"commentTime":1536927588000,"commentUserId":226,"commentUserName":"阎王爷","greatNum":5,"hotComment":0,"isGreat":0},{"commentContent":"123","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-11/20180911193642.jpg","commentId":420,"commentTime":1536822514000,"commentUserId":321,"commentUserName":"\brapper","greatNum":4,"hotComment":0,"isGreat":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commentContent : 666
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920095505.png
         * commentId : 549
         * commentTime : 1540292808000
         * commentUserId : 413
         * commentUserName : 皮皮猪之王
         * greatNum : 1
         * hotComment : 0
         * isGreat : 0
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int hotComment;
        private int isGreat;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

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

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
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
    }
}
