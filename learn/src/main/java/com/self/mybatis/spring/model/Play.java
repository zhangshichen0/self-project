package com.self.mybatis.spring.model;

import java.util.Date;

/*
 *
 * @date 2019-07-15
 * @author functions/user.html
 */
public class Play {

    /**
     * 剧本id
     */
    private Integer playId;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 去logo平台id
     */
    private Integer filmPlatformId;

    /**
     * 点赞数量
     */
    private Integer likeCount;

    /**
     * 剧本时长  单位毫秒
     */
    private Integer playLength;

    /**
     * 剧本状态 1显示 2隐藏 3草稿 4删除
     */
    private Integer playStatus;

    /**
     * 剧本参与人数
     */
    private Integer playerNum;

    /**
     * 分享数量
     */
    private Integer shareCount;

    /**
     * 剧本播放量
     */
    private Integer viewCount;

    /**
     * 剧本封面
     */
    private String cover;

    /**
     * 剧本简介
     */
    private String playIntro;

    /**
     * 剧本名称
     */
    private String playName;

    /**
     * 剧本视频地址
     */
    private String playUrl;

    /**
     * 分享图片
     */
    private String shareImg;

    /**
     * 剧本创建人 可为空
     */
    private Long uid;

    /**
     * 视频封面
     */
    private String videoCover;

    /**
     * 视频分辨率
     */
    private String videoFormat;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 配音次数
     */
    private Integer workCount;


    /**
     * 贴纸id列表，用 “,” 分隔
     */
    private String pasters;

    /**
     * 特色台词对应的角色头像
     */
    private String specialLine;

    /**
     * 特色台词对应的角色头像
     */
    private String specialImg;

    /**
     * 0代表非热门 非0为热门
     */
    private Integer hotSort;

    /**
     * 剧本精彩片段地址
     */
    private String playClipsUrl;

    /**
     * 剧本背景音
     */
    private String playBackgroundUrl;


    public String getPlayClipsUrl() {
        return playClipsUrl;
    }

    public void setPlayClipsUrl(String playClipsUrl) {
        this.playClipsUrl = playClipsUrl;
    }

    public Play() {
    }


    /**
     * 剧本id
     *
     * @return
     */
    public Integer getPlayId() {
        return playId;
    }

    /**
     * 剧本id
     *
     * @param playId
     */
    public void setPlayId(Integer playId) {
        this.playId = playId;
    }


    /**
     * 评论数
     *
     * @return
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 评论数
     *
     * @param commentCount
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }


    /**
     * 去logo平台id
     *
     * @return
     */
    public Integer getFilmPlatformId() {
        return filmPlatformId;
    }

    /**
     * 去logo平台id
     *
     * @param filmPlatformId
     */
    public void setFilmPlatformId(Integer filmPlatformId) {
        this.filmPlatformId = filmPlatformId;
    }


    /**
     * 点赞数量
     *
     * @return
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * 点赞数量
     *
     * @param likeCount
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }


    /**
     * 剧本时长  单位毫秒
     *
     * @return
     */
    public Integer getPlayLength() {
        return playLength;
    }

    /**
     * 剧本时长  单位毫秒
     *
     * @param playLength
     */
    public void setPlayLength(Integer playLength) {
        this.playLength = playLength;
    }


    /**
     * 剧本状态 1显示 2隐藏 3草稿 4删除
     *
     * @return
     */
    public Integer getPlayStatus() {
        return playStatus;
    }

    /**
     * 剧本状态 1显示 2隐藏 3草稿 4删除
     *
     * @param playStatus
     */
    public void setPlayStatus(Integer playStatus) {
        this.playStatus = playStatus;
    }


    /**
     * 剧本参与人数
     *
     * @return
     */
    public Integer getPlayerNum() {
        return playerNum;
    }

    /**
     * 剧本参与人数
     *
     * @param playerNum
     */
    public void setPlayerNum(Integer playerNum) {
        this.playerNum = playerNum;
    }


    /**
     * 分享数量
     *
     * @return
     */
    public Integer getShareCount() {
        return shareCount;
    }

    /**
     * 分享数量
     *
     * @param shareCount
     */
    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }


    /**
     * 剧本播放量
     *
     * @return
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * 剧本播放量
     *
     * @param viewCount
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }


    /**
     * 剧本封面
     *
     * @return
     */
    public String getCover() {
        return cover;
    }

    /**
     * 剧本封面
     *
     * @param cover
     */
    public void setCover(String cover) {
        this.cover = cover;
    }


    /**
     * 剧本简介
     *
     * @return
     */
    public String getPlayIntro() {
        return playIntro;
    }

    /**
     * 剧本简介
     *
     * @param playIntro
     */
    public void setPlayIntro(String playIntro) {
        this.playIntro = playIntro;
    }


    /**
     * 剧本名称
     *
     * @return
     */
    public String getPlayName() {
        return playName;
    }

    /**
     * 剧本名称
     *
     * @param playName
     */
    public void setPlayName(String playName) {
        this.playName = playName;
    }


    /**
     * 剧本视频地址
     *
     * @return
     */
    public String getPlayUrl() {
        return playUrl;
    }

    /**
     * 剧本视频地址
     *
     * @param playUrl
     */
    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }


    /**
     * 分享图片
     *
     * @return
     */
    public String getShareImg() {
        return shareImg;
    }

    /**
     * 分享图片
     *
     * @param shareImg
     */
    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }


    /**
     * 剧本创建人 可为空
     *
     * @return
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 剧本创建人 可为空
     *
     * @param uid
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }


    /**
     * 视频封面
     *
     * @return
     */
    public String getVideoCover() {
        return videoCover;
    }

    /**
     * 视频封面
     *
     * @param videoCover
     */
    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }


    /**
     * 视频分辨率
     *
     * @return
     */
    public String getVideoFormat() {
        return videoFormat;
    }

    /**
     * 视频分辨率
     *
     * @param videoFormat
     */
    public void setVideoFormat(String videoFormat) {
        this.videoFormat = videoFormat;
    }


    /**
     * 创建时间
     *
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getWorkCount() {
        return workCount;
    }

    public void setWorkCount(Integer workCount) {
        this.workCount = workCount;
    }


    /**
     * 贴纸
     *
     * @return
     */
    public String getPasters() {
        return pasters;
    }

    /**
     * 贴纸
     *
     * @return
     */
    public void setPasters(String pasters) {
        this.pasters = pasters;
    }

    public String getSpecialLine() {
        return specialLine;
    }

    public void setSpecialLine(String specialLine) {
        this.specialLine = specialLine;
    }

    public String getSpecialImg() {
        return specialImg;
    }

    public void setSpecialImg(String specialImg) {
        this.specialImg = specialImg;
    }

    public Integer getHotSort() {
        return hotSort;
    }

    public void setHotSort(Integer hotSort) {
        this.hotSort = hotSort;
    }

    public String getPlayBackgroundUrl() {
        return playBackgroundUrl;
    }

    public void setPlayBackgroundUrl(String playBackgroundUrl) {
        this.playBackgroundUrl = playBackgroundUrl;
    }

    @Override
    public String toString() {
        return "Play{" +
                "playId=" + playId +
                ", commentCount=" + commentCount +
                ", filmPlatformId=" + filmPlatformId +
                ", likeCount=" + likeCount +
                ", playLength=" + playLength +
                ", playStatus=" + playStatus +
                ", playerNum=" + playerNum +
                ", shareCount=" + shareCount +
                ", viewCount=" + viewCount +
                ", cover='" + cover + '\'' +
                ", playIntro='" + playIntro + '\'' +
                ", playName='" + playName + '\'' +
                ", playUrl='" + playUrl + '\'' +
                ", shareImg='" + shareImg + '\'' +
                ", uid=" + uid +
                ", videoCover='" + videoCover + '\'' +
                ", videoFormat='" + videoFormat + '\'' +
                ", createTime=" + createTime +
                ", workCount=" + workCount +
                ", pasters='" + pasters + '\'' +
                ", specialLine='" + specialLine + '\'' +
                ", specialImg='" + specialImg + '\'' +
                ", hotSort=" + hotSort +
                ", playClipsUrl='" + playClipsUrl + '\'' +
                ", playBackgroundUrl='" + playBackgroundUrl + '\'' +
                '}';
    }
}