package org.bigdatacenter.momcafe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by HP1 on 2/26/2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MomComment {
    private String replyToNick;
    private String writedt;
    private boolean refComment;
    private String replyToMemberId;
    private String writernick;
    private Long articleId;
    private String content;
    private int fonttype;
    private int emotion;
    private boolean deleted;
    private boolean articleWriter;
    private Long commentid;
    private String usingFontStyle;
    private Long refcommentid;
    private String emotionurl;
    private String writerid;
    private int font;
    private int stickerHeight;
    private int imageWidth;
    private String imagePath;
    private boolean isImageCropped;
    private String imageOriginUrl;
    private int imageThumbHeight;
    private int imageThumbWidth;
    private String imageThumbUrl;
    private String attachType;
    private String imageFileName;

    public String getReplyToNick() {
        return replyToNick;
    }

    public void setReplyToNick(String replyToNick) {
        this.replyToNick = replyToNick;
    }

    public String getWritedt() {
        return writedt;
    }

    public void setWritedt(String writedt) {
        this.writedt = writedt;
    }

    public boolean isRefComment() {
        return refComment;
    }

    public void setRefComment(boolean refComment) {
        this.refComment = refComment;
    }

    public String getReplyToMemberId() {
        return replyToMemberId;
    }

    public void setReplyToMemberId(String replyToMemberId) {
        this.replyToMemberId = replyToMemberId;
    }

    public String getWriternick() {
        return writernick;
    }

    public void setWriternick(String writernick) {
        this.writernick = writernick;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content.replaceAll("\"", "");
    }

    public void setContent(String content) {
        this.content = content.replaceAll("\"", "");
    }

    public int getFonttype() {
        return fonttype;
    }

    public void setFonttype(int fonttype) {
        this.fonttype = fonttype;
    }

    public int getEmotion() {
        return emotion;
    }

    public void setEmotion(int emotion) {
        this.emotion = emotion;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isArticleWriter() {
        return articleWriter;
    }

    public void setArticleWriter(boolean articleWriter) {
        this.articleWriter = articleWriter;
    }

    public Long getCommentid() {
        return commentid;
    }

    public void setCommentid(Long commentid) {
        this.commentid = commentid;
    }

    public String getUsingFontStyle() {
        return usingFontStyle;
    }

    public void setUsingFontStyle(String usingFontStyle) {
        this.usingFontStyle = usingFontStyle;
    }

    public Long getRefcommentid() {
        return refcommentid;
    }

    public void setRefcommentid(Long refcommentid) {
        this.refcommentid = refcommentid;
    }

    public String getEmotionurl() {
        return emotionurl;
    }

    public void setEmotionurl(String emotionurl) {
        this.emotionurl = emotionurl;
    }

    public String getWriterid() {
        return writerid;
    }

    public void setWriterid(String writerid) {
        this.writerid = writerid;
    }

    public int getFont() {
        return font;
    }

    public void setFont(int font) {
        this.font = font;
    }

    public int getStickerHeight() {
        return stickerHeight;
    }

    public void setStickerHeight(int stickerHeight) {
        this.stickerHeight = stickerHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isImageCropped() {
        return isImageCropped;
    }

    public void setImageCropped(boolean imageCropped) {
        isImageCropped = imageCropped;
    }

    public String getImageOriginUrl() {
        return imageOriginUrl;
    }

    public void setImageOriginUrl(String imageOriginUrl) {
        this.imageOriginUrl = imageOriginUrl;
    }

    public int getImageThumbHeight() {
        return imageThumbHeight;
    }

    public void setImageThumbHeight(int imageThumbHeight) {
        this.imageThumbHeight = imageThumbHeight;
    }

    public int getImageThumbWidth() {
        return imageThumbWidth;
    }

    public void setImageThumbWidth(int imageThumbWidth) {
        this.imageThumbWidth = imageThumbWidth;
    }

    public String getImageThumbUrl() {
        return imageThumbUrl;
    }

    public void setImageThumbUrl(String imageThumbUrl) {
        this.imageThumbUrl = imageThumbUrl;
    }

    public String getAttachType() {
        return attachType;
    }

    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    @Override
    public String toString() {
        return "MomComment{" +
                "replyToNick='" + replyToNick + '\'' +
                ", writedt='" + writedt + '\'' +
                ", refComment=" + refComment +
                ", replyToMemberId='" + replyToMemberId + '\'' +
                ", writernick='" + writernick + '\'' +
                ", articleId=" + articleId +
                ", content='" + content + '\'' +
                ", fonttype=" + fonttype +
                ", emotion=" + emotion +
                ", deleted=" + deleted +
                ", articleWriter=" + articleWriter +
                ", commentid=" + commentid +
                ", usingFontStyle='" + usingFontStyle + '\'' +
                ", refcommentid=" + refcommentid +
                ", emotionurl='" + emotionurl + '\'' +
                ", writerid='" + writerid + '\'' +
                ", font=" + font +
                ", stickerHeight=" + stickerHeight +
                ", imageWidth=" + imageWidth +
                ", imagePath='" + imagePath + '\'' +
                ", isImageCropped=" + isImageCropped +
                ", imageOriginUrl='" + imageOriginUrl + '\'' +
                ", imageThumbHeight=" + imageThumbHeight +
                ", imageThumbWidth=" + imageThumbWidth +
                ", imageThumbUrl='" + imageThumbUrl + '\'' +
                ", attachType='" + attachType + '\'' +
                ", imageFileName='" + imageFileName + '\'' +
                '}';
    }
}
