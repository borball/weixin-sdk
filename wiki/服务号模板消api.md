服务号的模板消息功能在本sdk中也支撑的
初始化
Templates templates=Templates.defaultTemplates 或者

***

Templates templates= Templates.with(AppSetting appSetting)；

***

templates.setIndustries  //设置所属行业

***

templates.getIndustries  //获取所属行业

***

templates.fetch //获取模板
***
templates.list //获取所有模板
***
templates.delete //删除模板
***
发送模板消息
templates.send(String toUser, String templateId, String url, Map<String, Data> messages) 
***
templates.send(String toUser, String templateId, MiniProgram miniProgram, Map<String, Data> messages)
***
 url和miniprogram都是非必填字段，若都不传则模板无跳转；
     * 若都传，会优先跳转至小程序。
     * 开发者可根据实际需要选择其中一种跳转方式即可。
     * 当用户的微信客户端版本不支持跳小程序时，将会跳转至url
templates.send(String toUser, String templateId, String url, MiniProgram miniProgram, Map<String, Data> messages) 
***
templates.
***
templates.