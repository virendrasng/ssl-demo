# ssl-demo

# All 4 apis below are REST apis. ssl-server-non-maven is a standalone programme to show how ssl enabled java applications communicate securely.
a. ssl-client-demo
b. ssl-demo-pkcs12
c. ssl-demo-pkcs12-jks
d. ssl-secured-client-demo


# ssl-demo-pkcs12-jks is a Hello world REST api to demonstrate minimum configuration for enabling SSL with JKS and PKCS12.

For below experiments register localhost server in hosts file as -> 192.168.56.1 localhost. All these examples are with PKCS12.
1. http api ssl-client-demo calls https api ssl-demo-pkcs12
2. https api ssl-secured-client-demo calls https api ssl-demo-pkcs12

For detail knowledge check README.md of ssl-demo-pkcs12-jks. Also check all the README files.