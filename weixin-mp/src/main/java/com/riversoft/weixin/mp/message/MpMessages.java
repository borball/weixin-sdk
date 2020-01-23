package com.riversoft.weixin.mp.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 群发接口 Created by exizhai on 11/23/2015.
 */
public class MpMessages {

	private static Logger logger = LoggerFactory.getLogger(MpMessages.class);

	private WxClient wxClient;

	public static MpMessages defaultMpMessages() {
		return with(AppSetting.defaultSettings());
	}

	public static MpMessages with(AppSetting appSetting) {
		MpMessages messages = new MpMessages();
		messages.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
		return messages;
	}

	public void setWxClient(WxClient wxClient) {
		this.wxClient = wxClient;
	}

	/**
	 * 群发图文消息消息给所有人
	 *
	 * @param mpNews
	 * @return
	 */
	public long mpNews(String mpNews) {
		return send(new Filter(true, null), null, "mpnews", mpNews);
	}

	/**
	 * 群发图文消息给指定群组的人
	 *
	 * @param group
	 * @param mpNews
	 * @return
	 */
	public long mpNews(int group, String mpNews) {
		return send(new Filter(false, String.valueOf(group)), null, "mpnews", mpNews);
	}

	/**
	 * 群发图文消息给指定的人
	 *
	 * @param openIds
	 * @param mpNews
	 * @return
	 */
	public long mpNews(List<String> openIds, String mpNews) {
		return send(new Filter(false, null), openIds, "mpnews", mpNews);
	}

	/**
	 * 预览圖文消息
	 *
	 * @param wxName
	 * @param openId
	 * @param mediaId
	 * @return
	 */
	public long mpnewsPreview(String wxName, String openId, String mediaId) {
		return preview(wxName, openId, "mpnews", mediaId);
	}

	/**
	 * 群发文本消息, 所有人
	 *
	 * @param text
	 * @return
	 */
	public long text(String text) {
		return send(new Filter(true, null), null, "text", text);
	}

	/**
	 * 指定分组群发文本消息
	 *
	 * @param group
	 * @param text
	 * @return
	 */
	public long text(int group, String text) {
		return send(new Filter(false, String.valueOf(group)), null, "text", text);
	}

	/**
	 * 发文本消息给指定人
	 *
	 * @param openIds
	 * @param text
	 * @return
	 */
	public long text(List<String> openIds, String text) {
		return send(new Filter(false, null), openIds, "text", text);
	}

	/**
	 * 预览文本消息
	 *
	 * @param wxName
	 * @param openId
	 * @param text
	 * @return
	 */
	public long textPreview(String wxName, String openId, String text) {
		return preview(wxName, openId, "text", text);
	}

	/**
	 * 群发语音消息，所有人
	 *
	 * @param voice
	 * @return
	 */
	public long voice(String voice) {
		return send(new Filter(true, null), null, "voice", voice);
	}

	/**
	 * 群发语音消息给指定人
	 *
	 * @param openIds
	 * @param voice
	 * @return
	 */
	public long voice(List<String> openIds, String voice) {
		return send(new Filter(false, null), openIds, "voice", voice);
	}

	/**
	 * 群发语音消息给指定群组
	 *
	 * @param group
	 * @param voice
	 * @return
	 */
	public long voice(int group, String voice) {
		return send(new Filter(false, String.valueOf(group)), null, "voice", voice);
	}

	/**
	 * 预览语音消息
	 *
	 * @param wxName
	 * @param openId
	 * @param voice
	 * @return
	 */
	public long voicePreview(String wxName, String openId, String voice) {
		return preview(wxName, openId, "voice", voice);
	}

	/**
	 * 群发图片消息给所有人
	 *
	 * @param image
	 * @return
	 */
	public long image(String image) {
		return send(new Filter(true, null), null, "image", image);
	}

	/**
	 * 群发图片消息给指定群组的人
	 *
	 * @param group
	 * @param image
	 * @return
	 */
	public long image(int group, String image) {
		return send(new Filter(false, String.valueOf(group)), null, "image", image);
	}

	/**
	 * 群发图片消息给指定的人
	 *
	 * @param openIds
	 * @param image
	 * @return
	 */
	public long image(List<String> openIds, String image) {
		return send(new Filter(false, null), openIds, "image", image);
	}

	/**
	 * 预览图片消息
	 *
	 * @param wxName
	 * @param openId
	 * @param image
	 * @return
	 */
	public long imagePreview(String wxName, String openId, String image) {
		return preview(wxName, openId, "image", image);
	}

	/**
	 * 群发视频消息给所有人
	 *
	 * @param mediaId
	 * @param title
	 * @param desc
	 * @return
	 */
	public long video(String mediaId, String title, String desc) {
		return video(new Filter(true, null), null, mediaId, title, desc);
	}

	/**
	 * 群发视频消息给特定分组的人
	 *
	 * @param group
	 * @param mediaId
	 * @param title
	 * @param desc
	 * @return
	 */
	public long video(int group, String mediaId, String title, String desc) {
		return video(new Filter(false, String.valueOf(group)), null, mediaId, title, desc);
	}

	/**
	 * 群发视频消息给特定用户
	 *
	 * @param openIds
	 * @param mediaId
	 * @param title
	 * @param desc
	 * @return
	 */
	public long video(List<String> openIds, String mediaId, String title, String desc) {
		return video(new Filter(false, null), openIds, mediaId, title, desc);
	}

	/**
	 * 群发视频预览给特定用户
	 *
	 * @param wxName
	 * @param openId
	 * @param mediaId
	 * @param title
	 * @param desc
	 * @return
	 */
	public long videoPreview(String wxName, String openId, String mediaId, String title, String desc) {
		Map<String, String> uploadRequest = new HashMap<>();
		uploadRequest.put("media_id", mediaId);
		uploadRequest.put("title", title);
		uploadRequest.put("description", desc);
		String uploadUrl = WxEndpoint.get("url.mass.message.video.upload");
		String json = JsonMapper.nonEmptyMapper().toJson(uploadUrl);

		String response = wxClient.post(uploadUrl, json);
		Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
		if (result.containsKey("media_id")) {
			return preview(wxName, openId, "mpvideo", (String) result.get("media_id"));
		} else {
			throw new WxRuntimeException(999, "send mp video preview failed.");
		}
	}

	/**
	 * 群发卡券给所有人
	 *
	 * @param wxcard
	 * @return
	 */
	public long card(String wxcard) {
		return send(new Filter(true, null), null, "wxcard", wxcard);
	}

	/**
	 * 群发卡券给指定分组的人
	 *
	 * @param group
	 * @param wxcard
	 * @return
	 */
	public long card(int group, String wxcard) {
		return send(new Filter(false, String.valueOf(group)), null, "wxcard", wxcard);
	}

	/**
	 * 群发卡券给指定的人
	 *
	 * @param openIds
	 * @param wxcard
	 * @return
	 */
	public long card(List<String> openIds, String wxcard) {
		return send(new Filter(false, null), openIds, "wxcard", wxcard);
	}

	/**
	 * 预览图片消息
	 *
	 * @param wxName
	 * @param openId
	 * @param wxcard
	 * @return
	 */
	public long cardPreview(String wxName, String openId, String wxcard) {
		return preview(wxName, openId, "wxcard", wxcard);
	}

	/**
	 * 删除群发消息 1、只有已经发送成功的消息才能删除 2、删除消息是将消息的图文详情页失效，已经收到的用户，还是能在其本地看到消息卡片。
	 * 3、删除群发消息只能删除图文消息和视频消息，其他类型的消息一经发送，无法删除。
	 * 4、如果多次群发发送的是一个图文消息，那么删除其中一次群发，就会删除掉这个图文消息也，导致所有群发都失效
	 *
	 * @param msgId
	 */
	public void delete(long msgId) {
		String url = WxEndpoint.get("url.mass.message.delete");
		String request = "{\"msg_id\":%s}";

		logger.debug("delete message: {}", msgId);
		wxClient.post(url, String.format(request, msgId));
	}

	/**
	 * 查询群发消息状态
	 *
	 * @param msgId
	 * @return
	 */
	public boolean success(long msgId) {
		String url = WxEndpoint.get("url.mass.message.status");
		String request = "{\"msg_id\":%s}";

		logger.debug("check message sending status: {}", msgId);
		String response = wxClient.post(url, String.format(request, msgId));
		Map<String, Object> map = JsonMapper.defaultMapper().json2Map(response);
		return map.containsKey("msg_status") && "SEND_SUCCESS".equalsIgnoreCase(map.get("msg_status").toString());
	}

	private long send(Filter filter, List<String> openIds, String msgType, String message) {
		String url;
		Map<String, Object> request = new HashMap<>();

		if (openIds != null && !openIds.isEmpty()) {// 指定openid发送
			request.put("touser", openIds);
			url = WxEndpoint.get("url.mass.message.send");
		} else {
			if (filter != null) {
				request.put("filter", filter);
			}
			url = WxEndpoint.get("url.mass.message.sendall");
		}

		request.put("msgtype", msgType);

		if ("wxcard".equalsIgnoreCase(msgType)) {
			request.put(msgType, new Card(message));
		} else if ("text".equalsIgnoreCase(msgType)) {
			request.put(msgType, new Text(message));
		} else {
			request.put(msgType, new Media(message));
		}

		String json = JsonMapper.nonEmptyMapper().toJson(request);
		logger.debug("send message: {}", json);
		String result = wxClient.post(url, json);
		Response response = JsonMapper.defaultMapper().fromJson(result, Response.class);

		return response.getMsgId();
	}

	private long preview(String wxName, String openId, String msgType, String message) {
		String url = WxEndpoint.get("url.mass.message.preview");
		Map<String, Object> request = new HashMap<>();
		if (wxName != null && !"".equals(wxName)) {
			request.put("towxname", wxName);
		}

		if (openId != null && !"".equals(openId)) {
			request.put("touser", openId);
		}

		request.put("msgtype", msgType);

		if ("wxcard".equalsIgnoreCase(msgType)) {
			request.put(msgType, new Card(message));
		} else if ("text".equalsIgnoreCase(msgType)) {
			request.put(msgType, new Text(message));
		} else {
			request.put(msgType, new Media(message));
		}

		String json = JsonMapper.nonEmptyMapper().toJson(request);
		logger.debug("preview message: {}", json);
		String response = wxClient.post(url, json);
		Response r = JsonMapper.defaultMapper().fromJson(response, Response.class);

		return r.getMsgId();
	}

	private long video(Filter filter, List<String> openIds, String mediaId, String title, String desc) {
		Map<String, String> uploadRequest = new HashMap<>();
		uploadRequest.put("media_id", mediaId);
		uploadRequest.put("title", title);
		uploadRequest.put("description", desc);
		String uploadUrl = WxEndpoint.get("url.mass.message.video.upload");
		String json = JsonMapper.nonEmptyMapper().toJson(uploadUrl);

		String response = wxClient.post(uploadUrl, json);
		Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
		if (result.containsKey("media_id")) {
			return send(filter, openIds, "mpvideo", (String) result.get("media_id"));
		} else {
			throw new WxRuntimeException(999, "send mp video failed.");
		}
	}

	public static class Filter {

		@JsonProperty("is_to_all")
		private boolean toALl;

		@JsonProperty("group_id")
		private String group;

		public Filter(boolean toALl, String group) {
			this.toALl = toALl;
			this.group = group;
		}

		public boolean isToALl() {
			return toALl;
		}

		public void setToALl(boolean toALl) {
			this.toALl = toALl;
		}

		public String getGroup() {
			return group;
		}

		public void setGroup(String group) {
			this.group = group;
		}
	}

	public static class Media {

		@JsonProperty("media_id")
		private String mediaId;

		public Media(String mediaId) {
			this.mediaId = mediaId;
		}

		public String getMediaId() {
			return mediaId;
		}

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}
	}

	public static class Card {

		@JsonProperty("card_id")
		private String cardId;

		public Card(String cardId) {
			this.cardId = cardId;
		}

		public String getCardId() {
			return cardId;
		}

		public void setCardId(String cardId) {
			this.cardId = cardId;
		}

	}

	public static class Text {

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

	public static class Response {

		@JsonProperty("msg_id")
		private long msgId;

		@JsonProperty("msg_data_id")
		private long msgDataId;

		public long getMsgId() {
			return msgId;
		}

		public void setMsgId(long msgId) {
			this.msgId = msgId;
		}

		public long getMsgDataId() {
			return msgDataId;
		}

		public void setMsgDataId(long msgDataId) {
			this.msgDataId = msgDataId;
		}
	}

}
