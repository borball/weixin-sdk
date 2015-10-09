#微信企业号SDK

weixin-sdk 是对微信公众平台API的封装；目前只实现了企业号API的封装：weixin-qydev。

##如何获取？

    <dependency>
      <groupId>cn.com.riversoft</groupId>
      <artifactId>weixin-qydev</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>

##如何使用：

###单元测试是比较好的入手方式。

1.	修改wx-settings-test.xml，填写企业号相关基本信息。

2.	修改wx-test.properties，填写单元测试所需的微信ID，该微信ID需已经关注您在第一步使用的企业号。注意不要随意提交您的企业号相关信息。

3.	high起来吧。

或者：参考 [weixin-sdk-demo](https://github.com/borball/weixin-sdk-demo "weixin-sdk-demo")

###正式使用：

1.	同单元测试类似，在classpath里面添加文件：wx-settings.xml，填写企业号相关信息。

2.	如果您只有一个企业号并且没有区分权限（比如不同corpSecret）：

	1.	直接调用weixin-qydev里面各种带s的工具集，比如获取默认Agent的信息：
	
	        Agent agent = Agents.defaultAgents().get(Settings.buildIn().getDefaultAgent());
    	
		
	2.	再比如下发微信消息：
	
			JsonMessage textMessage = new TextMessage().text("测试流式API").toUser(testUser());
	        Messages.defaultMessages().send(textMessage);


3.	如果你需要多个企业号或者同一企业号但不同corpSecret：

	    CorpSetting corpSetting = new CorpSetting("corpId", "corpSecret");
		WxClient wxClient = new WxClient(corpSetting);
	
	    Messages messages = new Messages();
		messages.setWxClient(wxClient);
	
		//剩下的和单企业号类似

	
	如果使用Spring，则您也可以初始化几套CorpSetting和WxClient和各种不同的工具集。
	