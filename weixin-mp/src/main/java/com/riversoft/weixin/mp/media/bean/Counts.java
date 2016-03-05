package com.riversoft.weixin.mp.media.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by exizhai on 10/2/2015.
 */
public class Counts {

    @JsonProperty("image_count")
    private int image;

    @JsonProperty("voice_count")
    private int voice;

    @JsonProperty("video_count")
    private int video;

    @JsonProperty("news_count")
    private int news;

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

    public int getNews() {
        return news;
    }

    public void setNews(int news) {
        this.news = news;
    }
}
