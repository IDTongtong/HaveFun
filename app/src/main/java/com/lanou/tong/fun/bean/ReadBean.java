package com.lanou.tong.fun.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zt on 16/3/8.
 */
public class ReadBean {


    /**
     * boardid : dy_wemedia_bbs
     * clkNum : 0
     * digest : 奥瑞利安索尔是下一位要登录召唤师峡谷的英雄，它代替了之前被取消的熬兴。当它心意所动时，索尔也能翱翔天际，飞越地形。
     * docid : BHN3BL4A05268DJR
     * downTimes : 1
     * id : BHN3BL4A05268DJR
     * img : http://p1.music.126.net/vW53wGJCVu9-SuuVVkVZ-Q==/17831879579408486.jpg
     * imgType : 0
     * imgsrc : http://p1.music.126.net/vW53wGJCVu9-SuuVVkVZ-Q==/17831879579408486.jpg
     * picCount : 7
     * pixel : 484*360
     * program : HY
     * prompt : 成功为您推荐20条新内容
     * ptime : 2016-03-09 08:58:00
     * recType : -1
     * replyCount : 23
     * replyid : BHN3BL4A05268DJR
     * source : 英雄小助手
     * template : normal
     * tid : T1443519878001
     * title : LOL第130位新英雄“奥瑞利安 索尔“发布！我去，这是龙？
     * unlikeReason : ["端游/1","广告/6","来源:英雄小助手/4","内容重复/6"]
     * upTimes : 1
     */

    @SerializedName("推荐")
    private List<recommendedEntity> recommended;

    public List<recommendedEntity> getRecommended() {
        return recommended;
    }

    public void setRecommended(List<recommendedEntity> recommended) {
        this.recommended = recommended;
    }


    public static class recommendedEntity {
        private String boardid;
        private int clkNum;
        private String digest;
        private String docid;
        private int downTimes;
        private String id;
        private String img;
        private int imgType;
        private String imgsrc;
        private int picCount;
        private String pixel;
        private String program;
        private String prompt;
        private String ptime;
        private int recType;
        private int replyCount;
        private String replyid;
        private String source;
        private String template;
        private String tid;
        private String title;
        private int upTimes;
        private List<String> unlikeReason;

        public void setBoardid(String boardid) {
            this.boardid = boardid;
        }

        public void setClkNum(int clkNum) {
            this.clkNum = clkNum;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public void setDownTimes(int downTimes) {
            this.downTimes = downTimes;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setImgType(int imgType) {
            this.imgType = imgType;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public void setPicCount(int picCount) {
            this.picCount = picCount;
        }

        public void setPixel(String pixel) {
            this.pixel = pixel;
        }

        public void setProgram(String program) {
            this.program = program;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public void setRecType(int recType) {
            this.recType = recType;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public void setReplyid(String replyid) {
            this.replyid = replyid;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUpTimes(int upTimes) {
            this.upTimes = upTimes;
        }

        public void setUnlikeReason(List<String> unlikeReason) {
            this.unlikeReason = unlikeReason;
        }

        public String getBoardid() {
            return boardid;
        }

        public int getClkNum() {
            return clkNum;
        }

        public String getDigest() {
            return digest;
        }

        public String getDocid() {
            return docid;
        }

        public int getDownTimes() {
            return downTimes;
        }

        public String getId() {
            return id;
        }

        public String getImg() {
            return img;
        }

        public int getImgType() {
            return imgType;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public int getPicCount() {
            return picCount;
        }

        public String getPixel() {
            return pixel;
        }

        public String getProgram() {
            return program;
        }

        public String getPrompt() {
            return prompt;
        }

        public String getPtime() {
            return ptime;
        }

        public int getRecType() {
            return recType;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public String getReplyid() {
            return replyid;
        }

        public String getSource() {
            return source;
        }

        public String getTemplate() {
            return template;
        }

        public String getTid() {
            return tid;
        }

        public String getTitle() {
            return title;
        }

        public int getUpTimes() {
            return upTimes;
        }

        public List<String> getUnlikeReason() {
            return unlikeReason;
        }
    }
}
