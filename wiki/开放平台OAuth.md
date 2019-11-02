
- 例子：

		OpenOAuth2s openOAuth2s = OpenOAuth2s.defaultOpenOAuth2s();
		//或者：
		AppSetting appSetting = new AppSetting("appId", "secret");
		OpenOAuth2s openOAuth2s = OpenOAuth2s.with(appSetting);

		//生成授权链接,默认scope: snsapi_base
		openOAuth2s.authenticationUrl("url");
		
		//生成授权链接,指定scope: snsapi_base or snsapi_userinfo
		openOAuth2s.authenticationUrl("url", "scope");
	
		//生成授权链接,指定scope，指定state
		openOAuth2s.authenticationUrl("url", "scope", "state");
		
		//获取AccessToken
		AccessToken accessToken = openOAuth2s.getAccessToken("code");
	
		//获取用户信息
		OpenUser user = openOAuth2s.userInfo(accessToken, "openId");