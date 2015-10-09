package com.riversoft.weixin.message.request.event;

import com.riversoft.weixin.message.base.EventType;

/**
 * Created by exizhai on 9/30/2015.
 */
public class SubscriptionEvent extends EventXmlRequest {

    private boolean sub;

    public boolean isSub() {
        return EventType.subscribe == this.getEventType();
    }
}
