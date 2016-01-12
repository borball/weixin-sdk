package com.riversoft.weixin.qy.contact;

import com.google.common.base.Joiner;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.qy.QyWxClientFactory;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.WxEndpoint;
import com.riversoft.weixin.qy.contact.job.JobResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by exizhai on 10/7/2015.
 */
public class Jobs {

    private static Logger logger = LoggerFactory.getLogger(Jobs.class);

    private WxClient wxClient;

    public static Jobs defaultJobs() {
        return with(CorpSetting.defaultSettings());
    }

    public static Jobs with(CorpSetting corpSetting) {
        Jobs jobs = new Jobs();
        jobs.setWxClient(QyWxClientFactory.getInstance().with(corpSetting));
        return jobs;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public String invite(List<String> users, List<Integer> departments, List<Integer> tags) {
        Map<String, String> maps = new HashMap<>();

        if (!isEmpty(users)) {
            maps.put("touser", Joiner.on("|").join(users));
        }

        if (!isEmpty(departments)) {
            maps.put("toparty", Joiner.on("|").join(departments));
        }

        if (!isEmpty(tags)) {
            maps.put("toparty", Joiner.on("|").join(tags));
        }

        String url = WxEndpoint.get("url.job.users.invite");
        String json = JsonMapper.defaultMapper().toJson(maps);
        logger.debug("invite users: {}", json);

        return submit(url, json);

    }

    private boolean isEmpty(List users) {
        return users == null || users.isEmpty();
    }

    public JobResult getResult(String id) {
        String url = WxEndpoint.get("url.job.result");
        String result = wxClient.get(String.format(url, id));
        logger.debug("get job result: {}", result);
        return JsonMapper.nonEmptyMapper().fromJson(result, JobResult.class);
    }

    public String syncUsers(File file) {
        String mediaId = mediaUpload(file);
        return syncUsers(mediaId);
    }

    public String syncUsers(String mediaId) {
        String json = String.format("{\"media_id\":\"%s\"}", mediaId);
        String url = WxEndpoint.get("url.job.users.sync");

        logger.debug("sync users: {}", json);
        return submit(url, json);
    }

    public String replaceUsers(File file) {
        String mediaId = mediaUpload(file);
        return replaceUsers(mediaId);
    }

    public String replaceUsers(String mediaId) {
        String json = String.format("{\"media_id\":\"%s\"}", mediaId);
        String url = WxEndpoint.get("url.job.users.replace");

        logger.debug("replace users: {}", json);
        return submit(url, json);
    }

    public String replaceDepartments(File file) {
        String mediaId = mediaUpload(file);
        return replaceDepartments(mediaId);
    }

    public String replaceDepartments(String mediaId) {
        String json = String.format("{\"media_id\":\"%s\"}", mediaId);
        String url = WxEndpoint.get("url.job.departments.replace");

        logger.debug("replace department: {}", json);
        return submit(url, json);
    }

    private String submit(String url, String json) {
        String response = wxClient.post(url, json);
        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("jobid")) {
            return result.get("jobid").toString();
        } else {
            //should never happen
            throw new WxRuntimeException(999, "cannot get job id.");
        }
    }

    private String mediaUpload(File file) {
        String url = WxEndpoint.get("url.media.upload");

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            String response = wxClient.post(String.format(url, "file"), fileInputStream, file.getName());

            Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
            if (result.containsKey("media_id")) {
                return result.get("media_id").toString();
            } else {
                logger.warn("media upload failed: {}", response);
                throw new WxRuntimeException(998, response);
            }
        } catch (Exception e) {
            logger.error("media upload failed.", e);
            throw new WxRuntimeException(998, "media upload failed" + e.getMessage());
        }
    }
}
