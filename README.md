#微信公众平台(订阅号、服务号、企业号)SDK

weixin-sdk 是对微信公众平台API的封装：

 - 服务号，订阅号：weixin-mp
 - 企业号：weixin-qydev

##如何获取？

 - 服务号，订阅号：

		<dependency>
	      <groupId>cn.com.riversoft</groupId>
	      <artifactId>weixin-mp</artifactId>
	      <version>0.3.3</version>
	    </dependency>

 - 企业号：
 
	    <dependency>
	      <groupId>cn.com.riversoft</groupId>
	      <artifactId>weixin-qydev</artifactId>
	      <version>0.3.3</version>
	    </dependency>


##如何使用：

###企业号


#### 配置


- 如果只有一个corpId和corpSecret
	
	可以直接在 classpath 下面增加 wx-qy-settings.xml，内容如下：

		<DefaultSettings>
		    <corpSetting>
		        <corpId>your coropId</corpId>
	            <corpSecret>your corpSecret</corpSecret>
		    </corpSetting>
		    <agentSetting>
		        <agentId>30</agentId>
		        <name>default</name>
		        <callbackUrl>http://somesite.com</callbackUrl>
		        <token>your agent token</token>
		        <aesKey>your agent aesKey</aesKey>
		    </agentSetting>
		</DefaultSettings>

	其中agentSetting 为默认的Agent（应用）配置。 API接口里面需要agent ID的地方如果调用者不提供，SDK将使用上面配置的默认Agent。

- 如果有多个corpId和corpSecret
    
	TODO：	


----------

#### 接收消息和事件


- 代码

		XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(content);
	
- XmlMessageHeader
 
	XmlMessageHeader 是消息和事件的抽象类，可以向下转型为具体的微信消息和事件。

	消息：

	- text消息：QyTextRequest
	- image消息：QyImageRequest
	- voice消息：QyVoiceRequest
	- video消息：QyVideoRequest
	- 小视频消息：QyShortVideoRequest
	- Location消息：QyLocationRequest

	事件：

	- 用户关注取消：QySubscriptionEvent
	- 上报地理位置：QyLocationReportEvent
	- 菜单点击：QyClickEvent
	- 菜单链接：QyViewEvent
	- 扫码：	QyScanEvent
	- 拍照，照片选择：QyPhotoEvent
	- 弹出地理位置选择器：QyLocationSelectEvent
	- 用户进入应用：EnterAgentEvent


----------


#### 被动响应消息	

- 消息类型

	- text消息：TextXmlMessage
	- image消息：ImageXmlMessage
	- voice消息：VoiceXmlMessage
	- video消息：VideoXmlMessage
	- news消息：NewsXmlMessage

----------

#### 发送消息

- 消息类型

	- text消息：TextMessage
	- image消息：ImageMessage
	- voice消息：VoiceMessage
	- video消息：VideoMessage
	- file消息：FileMessage
	- news消息：NewsMessage
	- mpnews消息：MpNewsMessage

- 发送消息

		//文本消息：
	    JsonMessage textMessage = new TextMessage();
		textMessage.text("文本消息").safe().toUser("borball").toParty("1|2").toTag("1|2");

        Messages.defaultMessages().send(textMessage);

#### 自定义菜单

#### 通讯录管理

#### 资源和素材管理

#### 应用管理

###订阅号，服务号

- 配置

- 接收消息和事件
	