## 生成https数字证书
keytool -genkey -alias https -keyalg RSA -keysize 2048 -keystore ppms.p12 -validity 365