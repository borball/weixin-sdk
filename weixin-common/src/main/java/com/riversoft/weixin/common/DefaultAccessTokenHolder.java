package com.riversoft.weixin.common;

/**
 * @borball on 8/14/2016.
 */
public class DefaultAccessTokenHolder extends AccessTokenHolder {

    private AccessToken accessToken;

    public DefaultAccessTokenHolder(String tokenUrl, String clientId, String clientSecret){
        super(tokenUrl, clientId, clientSecret);
    }

    @Override
    public synchronized AccessToken getAccessToken() {
        if (accessToken == null || accessToken.expired()) {
            refreshToken();
        }
        return accessToken;
    }

    @Override
    public synchronized void refreshToken() {
        if (accessToken == null || accessToken.expired()) {
            String content = fetchAccessToken();
            AccessToken accessToken = AccessToken.fromJson(content);
            this.accessToken = accessToken;
        }
    }

    @Override
    public void expireToken() {
        accessToken.setExpiresIn(-30);//强制设置为无效
    }


}
