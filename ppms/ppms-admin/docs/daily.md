## 生成https数字证书
keytool -genkey -alias https -keyalg RSA -keysize 2048 -keystore ppms.p12 -validity 365

## ppms系统swagger ui地址
> https://localhost:6888/doc.html

## 编译
gradle :ppms-admin:build