package com.lanou.tong.fun.bean;

import java.util.List;

/**
 * Created by zt on 16/3/7.
 */
public class NewsBean {

    /**
     * postid : PHOT3DK8000100AN
     * hasCover : false
     * hasHead : 1
     * replyCount : 5444
     * hasImg : 1
     * digest :
     * hasIcon : true
     * docid : 9IG74V5H00963VRO_BHARV751liziweiupdateDoc
     * title : 盘点历届代表委员会场拍照 姿势各异
     * order : 1
     * priority : 254
     * lmodify : 2016-03-04 15:13:32
     * boardid : photoview_bbs
     * ads : [{"title":"政协文艺界分组讨论 莫言表情抢眼","tag":"photoset","imgsrc":"http://img6.cache.netease.com/3g/2016/3/4/20160304155556c7af1.jpg","subtitle":"","url":"00AN0001|112267"},{"title":"人大会议发布会 傅莹回应热点问题","tag":"photoset","imgsrc":"http://img6.cache.netease.com/3g/2016/3/4/20160304120653bac75.jpg","subtitle":"","url":"00AN0001|112244"},{"title":"土耳其警方击毙两名女性恐怖分子","tag":"photoset","imgsrc":"http://img1.cache.netease.com/3g/2016/3/4/2016030407562172870.jpg","subtitle":"","url":"00AO0001|112217"},{"title":"1600只纸熊猫占领泰国大皇宫广场","tag":"photoset","imgsrc":"http://img6.cache.netease.com/3g/2016/3/4/2016030418503925b30.jpg","subtitle":"","url":"00AO0001|112276"},{"title":"人大预备会议开始 代表步入会场","tag":"photoset","imgsrc":"http://img2.cache.netease.com/3g/2016/3/4/201603041111218ff8a.jpg","subtitle":"","url":"00AN0001|112238"}]
     * photosetID : 00AN0001|112264
     * template : manual
     * votecount : 4432
     * skipID : 00AN0001|112264
     * alias : Top News
     * skipType : photoset
     * cid : C1348646712614
     * hasAD : 1
     * imgextra : [{"imgsrc":"http://img3.cache.netease.com/3g/2016/3/4/2016030415144982a21.jpg"},{"imgsrc":"http://img4.cache.netease.com/3g/2016/3/4/20160304151451c861e.jpg"}]
     * ename : iosnews
     * tname : 头条
     * imgsrc : http://img2.cache.netease.com/3g/2016/3/4/201603041514467a679.jpg
     * ptime : 2016-03-04 15:13:32
     */

    private String postid;
    private boolean hasCover;
    private int hasHead;
    private int replyCount;
    private int hasImg;
    private String digest;
    private boolean hasIcon;
    private String docid;
    private String title;
    private int order;
    private int priority;
    private String lmodify;
    private String boardid;
    private String photosetID;
    private String template;
    private int votecount;
    private String skipID;
    private String alias;
    private String skipType;
    private String cid;
    private int hasAD;
    private String ename;
    private String tname;
    private String imgsrc;
    private String ptime;
    /**
     * title : 政协文艺界分组讨论 莫言表情抢眼
     * tag : photoset
     * imgsrc : http://img6.cache.netease.com/3g/2016/3/4/20160304155556c7af1.jpg
     * subtitle :
     * url : 00AN0001|112267
     */

    private List<AdsEntity> ads;
    /**
     * imgsrc : http://img3.cache.netease.com/3g/2016/3/4/2016030415144982a21.jpg
     */

    private List<ImgextraEntity> imgextra;

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public void setHasHead(int hasHead) {
        this.hasHead = hasHead;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public void setHasImg(int hasImg) {
        this.hasImg = hasImg;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public void setAds(List<AdsEntity> ads) {
        this.ads = ads;
    }

    public void setImgextra(List<ImgextraEntity> imgextra) {
        this.imgextra = imgextra;
    }

    public String getPostid() {
        return postid;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public int getHasHead() {
        return hasHead;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public int getHasImg() {
        return hasImg;
    }

    public String getDigest() {
        return digest;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public String getDocid() {
        return docid;
    }

    public String getTitle() {
        return title;
    }

    public int getOrder() {
        return order;
    }

    public int getPriority() {
        return priority;
    }

    public String getLmodify() {
        return lmodify;
    }

    public String getBoardid() {
        return boardid;
    }

    public String getPhotosetID() {
        return photosetID;
    }

    public String getTemplate() {
        return template;
    }

    public int getVotecount() {
        return votecount;
    }

    public String getSkipID() {
        return skipID;
    }

    public String getAlias() {
        return alias;
    }

    public String getSkipType() {
        return skipType;
    }

    public String getCid() {
        return cid;
    }

    public int getHasAD() {
        return hasAD;
    }

    public String getEname() {
        return ename;
    }

    public String getTname() {
        return tname;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public String getPtime() {
        return ptime;
    }

    public List<AdsEntity> getAds() {
        return ads;
    }

    public List<ImgextraEntity> getImgextra() {
        return imgextra;
    }

    public static class AdsEntity {
        private String title;
        private String tag;
        private String imgsrc;
        private String subtitle;
        private String url;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public String getTag() {
            return tag;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class ImgextraEntity {
        private String imgsrc;

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getImgsrc() {
            return imgsrc;
        }
    }
}
