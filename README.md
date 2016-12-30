# 微信公众平台(服务号、订阅号、企业号、小程序)、微信开放平台和微信支付JAVA SDK

[![Build Status](https://semaphoreci.com/api/v1/projects/5b0c7b61-9b88-4b88-95fa-ea6bbd11e495/617516/badge.svg)](https://semaphoreci.com/borball/weixin-sdk) [![](https://jitpack.io/v/borball/weixin-sdk.svg)](https://jitpack.io/#borball/weixin-sdk) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/cn.com.riversoft/weixin-sdk/badge.svg)](https://maven-badges.herokuapp.com/maven-central/cn.com.riversoft/weixin-sdk)


weixin-sdk 是对微信公众平台(订阅号、服务号、企业号、小程序)、微信开放平台和微信支付的JAVA版封装：

 - 服务号，订阅号： 	weixin-mp
 - 企业号： 		weixin-qydev
 - 开放平台： 		weixin-open
 - 支付： 		weixin-pay
 - 小程序： weixin-app
 
[API使用指南](https://github.com/borball/weixin-sdk/wiki)

[SDK DEMO](https://github.com/borball/weixin-sdk-demo)

## 如何获取

### Maven

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

 - 开放平台：
 
	    <dependency>
	      <groupId>cn.com.riversoft</groupId>
	      <artifactId>weixin-open</artifactId>
	      <version>${weixin-sdk.version}</version>
	    </dependency>

 - 支付：

	    <dependency>
	      <groupId>cn.com.riversoft</groupId>
	      <artifactId>weixin-pay</artifactId>
	      <version>${weixin-sdk.version}</version>
	    </dependency>
	    
 - 小程序：

	    <dependency>
	      <groupId>cn.com.riversoft</groupId>
	      <artifactId>weixin-app</artifactId>
	      <version>${weixin-sdk.version}</version>
	    </dependency>	   
		
也可以直接从Maven中央仓库获取SNAPSHOT版本。

### JitPack

或者使用：[JitPack](https://jitpack.io/#borball/weixin-sdk/)，比如：

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

	<dependency>
	    <groupId>com.github.borball.weixin-sdk</groupId>
	    <artifactId>weixin-mp</artifactId>
	    <version>${weixin-sdk.version}</version>
	</dependency>


