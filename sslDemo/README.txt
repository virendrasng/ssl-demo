Concepts taken from -> https://howtodoinjava.com/spring-boot/spring-boot-ssl-https-example/
Command -> keytool -genkey -alias selfsigned_keypair -keyalg RSA -keysize 2048 -validity 700 -keypass 123456 -storepass 123456 -keystore ssl-server.jks

------------------------------------------------------------------------------
Another solution from https://www.drissamri.be/blog/java/enable-https-in-spring-boot/
keytool -genkeypair -alias selfsigned_keypair -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore ssl-server.p12 -validity 3650
Command to export jks file to PKCS12  format -> keytool -importkeystore -srckeystore ssl-server.jks -destkeystore ssl-server-from-jks.p12 -deststoretype PKCS12
In this case becareful 'alias' should remain same.