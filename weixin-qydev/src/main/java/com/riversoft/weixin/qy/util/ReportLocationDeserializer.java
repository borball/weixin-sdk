package com.riversoft.weixin.qy.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.riversoft.weixin.qy.agent.bean.WritableAgent;

import java.io.IOException;

/**
 * Created by exizhai on 10/6/2015.
 */
public class ReportLocationDeserializer extends JsonDeserializer<WritableAgent.ReportLocation> {

    @Override
    public WritableAgent.ReportLocation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int report = jsonParser.getIntValue();
        if (1 == report)
            return WritableAgent.ReportLocation.ONLY_IN_SESSION;
        else if (2 == report)
            return WritableAgent.ReportLocation.ALWAYS;
        else
            return WritableAgent.ReportLocation.NO;
    }
}
