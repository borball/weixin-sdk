package com.riversoft.weixin.qy.message.base;

import java.io.Serializable;

/**
 * Created by exizhai on 9/25/2015.
 */
public class Text implements Serializable {

    private String content;

    public Text(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
