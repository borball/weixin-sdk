package com.riversoft.weixin.qy.contact.tag;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by exizhai on 10/5/2015.
 */
public class TagList {

    @JsonProperty("taglist")
    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
