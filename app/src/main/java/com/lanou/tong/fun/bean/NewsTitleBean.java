package com.lanou.tong.fun.bean;

import java.util.List;

/**
 * Created by zt on 16/3/5.
 */
public class NewsTitleBean {

    /**
     * template : manual
     * topicid : 00040BGE
     * hasCover : false
     * alias : The Truth
     * subnum : 924.9万
     * recommendOrder : 0
     * isNew : 0
     * img : http://img2.cache.netease.com/m/newsapp/banner/zhenhua.png
     * isHot : 0
     * hasIcon : true
     * cid : C1348654575297
     * recommend : 0
     * headLine : false
     * color :
     * bannerOrder : 105
     * tname : 原创
     * ename : zhenhua
     * showType : comment
     * special : 0
     * tid : T1370583240249
     */

    private List<TListEntity> tList;

    public void setTList(List<TListEntity> tList) {
        this.tList = tList;
    }

    public List<TListEntity> getTList() {
        return tList;
    }

    public static class TListEntity {
        private String template;
        private String topicid;
        private boolean hasCover;
        private String alias;
        private String subnum;
        private int recommendOrder;
        private int isNew;
        private String img;
        private int isHot;
        private boolean hasIcon;
        private String cid;
        private String recommend;
        private boolean headLine;
        private String color;
        private int bannerOrder;
        private String tname;
        private String ename;
        private String showType;
        private int special;
        private String tid;

        public void setTemplate(String template) {
            this.template = template;
        }

        public void setTopicid(String topicid) {
            this.topicid = topicid;
        }

        public void setHasCover(boolean hasCover) {
            this.hasCover = hasCover;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public void setSubnum(String subnum) {
            this.subnum = subnum;
        }

        public void setRecommendOrder(int recommendOrder) {
            this.recommendOrder = recommendOrder;
        }

        public void setIsNew(int isNew) {
            this.isNew = isNew;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        public void setHasIcon(boolean hasIcon) {
            this.hasIcon = hasIcon;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public void setHeadLine(boolean headLine) {
            this.headLine = headLine;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setBannerOrder(int bannerOrder) {
            this.bannerOrder = bannerOrder;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public void setShowType(String showType) {
            this.showType = showType;
        }

        public void setSpecial(int special) {
            this.special = special;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getTemplate() {
            return template;
        }

        public String getTopicid() {
            return topicid;
        }

        public boolean isHasCover() {
            return hasCover;
        }

        public String getAlias() {
            return alias;
        }

        public String getSubnum() {
            return subnum;
        }

        public int getRecommendOrder() {
            return recommendOrder;
        }

        public int getIsNew() {
            return isNew;
        }

        public String getImg() {
            return img;
        }

        public int getIsHot() {
            return isHot;
        }

        public boolean isHasIcon() {
            return hasIcon;
        }

        public String getCid() {
            return cid;
        }

        public String getRecommend() {
            return recommend;
        }

        public boolean isHeadLine() {
            return headLine;
        }

        public String getColor() {
            return color;
        }

        public int getBannerOrder() {
            return bannerOrder;
        }

        public String getTname() {
            return tname;
        }

        public String getEname() {
            return ename;
        }

        public String getShowType() {
            return showType;
        }

        public int getSpecial() {
            return special;
        }

        public String getTid() {
            return tid;
        }
    }
}
