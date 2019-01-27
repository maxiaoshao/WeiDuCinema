package com.example.weiducinema.bean;

/**
 * created by fxb
 * 2019/1/27 14:54
 */
public class UserAttenBean {

        /**
         * id : 1
         * imageUrl : http://172.17.8.100/images/movie/stills/wbsys/wbsys1.jpg
         * name : 我不是药神
         * releaseTime : 1530720000000
         * summary : 一位不速之客的意外到访，打破了神油店老板程勇（徐峥 饰）的平凡人生，他从一个交不起房租的男性保健品商贩，一跃成为印度仿制药“格列宁”的独家代理商。收获巨额利润的他，生活剧烈变化，被病患们冠以“药神”的称号。但是，一场关于救赎的拉锯战也在波涛暗涌中慢慢展开......
         */

        private int id;
        private String imageUrl;
        private String name;
        private long releaseTime;
        private String summary;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

}
