初始化JsAPIs
JsAPIs.defaultJsAPIs();
***

JsAPIs.with(AppSetting appSetting);

***

* JsAPIs.createJsAPISignature(String url)；//获取js前端需求的签名，建议通过url缓存起来。url最好不要带参数，因为参数变化需要重新生成，调用有上限。

* JsAPIs.getJsAPITicket()//获取前端JS需要的临时票据