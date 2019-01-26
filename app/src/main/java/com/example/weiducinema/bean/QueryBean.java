package com.example.weiducinema.bean;

/**
 * created by fxb
 * 2019/1/26 16:02
 */
public class QueryBean {


        /**
         * address : 北京海淀区海淀区清河中街68号五彩城购物中心东区7层
         * commentTotal : 0
         * distance : 0
         * followCinema : 0
         * id : 22
         * logo : http://mobile.bwstudent.com/images/movie/logo/CGVyc.jpg
         * name : CGV影城（清河店）
         */

        private String address;
        private int commentTotal;
        private int distance;
        private int followCinema;
        private int id;
        private String logo;
        private String name;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCommentTotal() {
            return commentTotal;
        }

        public void setCommentTotal(int commentTotal) {
            this.commentTotal = commentTotal;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getFollowCinema() {
            return followCinema;
        }

        public void setFollowCinema(int followCinema) {
            this.followCinema = followCinema;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}
