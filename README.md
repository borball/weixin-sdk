#微信公众平台(订阅号、服务号、企业号)和微信支付JAVA SDK

[![Build Status](https://semaphoreci.com/api/v1/projects/5b0c7b61-9b88-4b88-95fa-ea6bbd11e495/617516/badge.svg)](https://semaphoreci.com/borball/weixin-sdk)[![](https://jitpack.io/v/borball/weixin-sdk.svg)](https://jitpack.io/#borball/weixin-sdk)


weixin-sdk 是对微信公众平台API已经微信支付API的JAVA版封装：

 - 服务号，订阅号： weixin-mp
 - 企业号： weixin-qydev
 - 支付： weixin-pay

API详情请参考WIKI: [wiki](https://github.com/borball/weixin-sdk/wiki)

或者参考：SDK DEMO: [demo](https://github.com/borball/weixin-sdk-demo)

##如何获取？

 - 服务号，订阅号：

		<dependency>
	      <groupId>cn.com.riversoft</groupId>
	      <artifactId>weixin-mp</artifactId>
	      <version>${weixin-sdk.version}</version>
	    </dependency>

 - 企业号：
 
	    <dependency>
	      <groupId>cn.com.riversoft</groupId>
	      <artifactId>weixin-qydev</artifactId>
	      <version>${weixin-sdk.version}</version>
	    </dependency>

 - 支付：

		<dependency>
		  <groupId>cn.com.riversoft</groupId>
		  <artifactId>weixin-pay</artifactId>
		  <version>${weixin-sdk.version}</version>
		</dependency>
		
也可以直接从Maven中央仓库获取SNAPSHOT版本。
	