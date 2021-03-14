#!/bin/bash
# Make sure to reboot the SpringBoot application
DEST=$1
DOMAIN=$2
PASSWORD=$3

certbot renew
openssl pkcs12 -export -in /etc/letsencrypt/live/${DOMAIN}/cert.pem -inkey /etc/letsencrypt/live/${DOMAIN}/privkey.pem -out ${DEST} -name tomcat -CAfile /etc/letsencrypt/live/${DOMAIN}/chain.pem -caname root -passout pass:${PASSWORD}
