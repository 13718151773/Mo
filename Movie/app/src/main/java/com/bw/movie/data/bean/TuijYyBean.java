package com.bw.movie.data.bean;

import java.util.List;

/**
 * 推荐影院
 * 张娜
 */
public class TuijYyBean {


    /**
     * result : [{"address":"朝阳区湖景东路11号新奥购物中心B1(东A口)","commentTotal":95,"distance":0,"
     * followCinema":2,"id":5,"
     * logo":"http://mobile.bwstudent.com/images/movie/logo/CGVxx.jpg",
     * "name":"CGV星星影城"},{
     * "address":"北京市朝阳区三丰北里2号楼悠唐广场B1层",
     * "commentTotal":23,"distance":0,"followCinema":2,"
     * id":8,"logo":"http://mobile.bwstudent.com/images/movie/logo/bn.jpg",
     * "name":"北京博纳影城朝阳门旗舰店"},{"address":"朝阳区建国路93号万达广场三层","commentTotal":60,"distance":0,"followCinema":2,"id":6,"logo":"http://mobile.bwstudent.com/images/movie/logo/wd.jpg","name":"北京CBD万达广场店"},{"address":"海淀区中关村广场购物中心津乐汇三层（鼎好一期西侧）","commentTotal":22,"distance":0,"followCinema":2,"id":12,"logo":"http://mobile.bwstudent.com/images/movie/logo/mjhlyc.jpg","name":"美嘉欢乐影城中关村店"},{"address":"北京市海淀区新街口外大街25号","commentTotal":5,"distance":0,"followCinema":2,"id":14,"logo":"http://mobile.bwstudent.com/images/movie/logo/zygj.jpg","name":"中影国际影城北京小西天店"},{"address":"北京市朝阳区建国门外大街1号国贸商城区域三地下一层3B120","commentTotal":2,"distance":0,"followCinema":2,"id":7,"logo":"http://mobile.bwstudent.com/images/movie/logo/blg.jpg","name":"北京百丽宫影城"},{"address":"北京市崇文区崇文门外大街18号国瑞城首层、地下二层","commentTotal":0,"distance":0,"followCinema":2,"id":9,"logo":"http://mobile.bwstudent.com/images/movie/logo/blh.jpg","name":"北京百老汇影城国瑞购物中心店"},{"address":"朝阳区广顺北大街16号望京华彩商业中心B1","commentTotal":0,"distance":0,"followCinema":2,"id":10,"logo":"http://mobile.bwstudent.com/images/movie/logo/hyxd.jpg","name":"华谊兄弟影院"},{"address":"海淀区远大路1号金源时代购物中心5层东首","commentTotal":4,"distance":0,"followCinema":2,"id":11,"logo":"http://mobile.bwstudent.com/images/movie/logo/xmgj.jpg","name":"星美国际影城"},{"address":"海淀区复兴路69号五棵松卓展时代百货五层东侧","commentTotal":0,"distance":0,"followCinema":2,"id":13,"logo":"http://mobile.bwstudent.com/images/movie/logo/bjalclgj.jpg","name":"北京耀莱成龙国际影城（五棵松店）"}]
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
         * address : 朝阳区湖景东路11号新奥购物中心B1(东A口)
         * commentTotal : 95
         * distance : 0
         * followCinema : 2
         * id : 5
         * logo : http://mobile.bwstudent.com/images/movie/logo/CGVxx.jpg
         * name : CGV星星影城
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
}
