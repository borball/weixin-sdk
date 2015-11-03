package com.riversoft.weixin.qy.media.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by exizhai on 10/2/2015.
 */
public class Counts {

    @JsonProperty("total_count")
    private int total;

    @JsonProperty("image_count")
    private int image;

    @JsonProperty("voice_count")
    private int voice;

    @JsonProperty("video_count")
    private int video;

    @JsonProperty("file_count")
    private int file;

    @JsonProperty("mpnews_count")
    private int mpNews;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public int getMpNews() {
        return mpNews;
    }

    public void setMpNews(int mpNews) {
        this.mpNews = mpNews;
    }
}
