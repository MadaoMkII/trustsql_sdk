# 腾讯区块链产品TrustSql调试测试使用案例

#基本概述
> 此案例为对腾讯TrustSql产品进行测试及使用。实现和模拟了实际开发中的使用场景。介于现今对于TrustSql的开发文献与实例及其稀少，希望能对正在使用此产品的开发者尽一份力，做一些抛砖引玉的工作，也欢迎广大开发者能够针对不足和改进地地方提出意见。
---
## 功能简介
> 本案例使用的为官方发布的java v1.1版的SDK 包 [下载地址][1]。在通过阅读学习 [官方文档][2]及参考网络上相关文献的基础上构建调试生成。

## 使用流程如下

1.产生公钥和密钥对并在Baas平台上注册
```java
//产生密钥对的方法为
 PairKey pairKey = TrustSDK.generatePairKey();
 //pairKey对象有两个属性
 //分别对应公钥pubKey和秘钥prvKey
 //使用密钥给平台加密并上传签名之后的结果
 //srcStr 为需要加密的内容在这里是被签名对象 “Tencent TrustSQL”
String sign = TrustSDK.signString(prvKey, srcStr.getBytes("UTF-8"));
 //之后上传sign作为结果，成功之后可以看到自己的机构id,以下简称mch_id
```
2.用户的注册和用户账号的注册
>作为每次通信必须有的参数

>seq_no是流水号，格式为C(32)，必须唯一，由案例自动生成

>mch_sign为数字签名之后的结果，由案例自动生成，个别情况需要特别设置

>time_stamp 为时间戳，由案例自动生成，理论上必然唯一。

1. 注册用户

通过调用RegisterUser类实现

2. 注册用户账户

通过调用RegisterUserAccount类实现

用户相关的接口调用跟上面共享信息和资产信息相关的接口调用有些差别，文档中有说明，详情查看 身份管理 中的报文规范 

> 所有场景默认采用UTF-8编码
HTTPS使用单向认证
证书由我方管理并发布
使用POST发送消息
消息请求基于HTTPS的POST方式
HTTP消息头要求
**HTTP请求消息中必须按照如下要求设置头部域：
‘Content-Length:’必须设置成消息体的长度 
‘Content-Type:’必须设置下面的值：
application/json;charset=UTF-8**


3.共享信息新增/追加
> IssAppendCommand.java 文件中的方法
> 调用该方法之前需要先注册开通 [[Bass开放平台]][3]，目前只支持企业用户注册，需要上传企业营业执照。
> 注册审核通过以后，可以在 账户中心->账户设置 中查看自己的 机构id(mch_id) 和 使用 
> 前两步生成的密钥对完成 密钥设置
> 



  [1]: https://baas.trustsql.qq.com/web/baas_blockchain/doc/v1.0/sdk/TrustSQL_SDK_java_v1.0.zip
  [2]: https://baas.trustsql.qq.com/web/baas_blockchain/doc/v1.0/index.shtml#_1
  [3]: https://baas.trustsql.qq.com/web/trust_blockchain/service/service.shtml
  [4]: https://baas.trustsql.qq.com/web/trust_blockchain/testchain/baseinfo.shtml?chain_id=ch_tencent_test&relate_id=testchain_ch_tencent_test_0
  [5]: https://github.com/yieio/trustSqlCase
