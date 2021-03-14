#!/bin/sh
DOMAIN=$1
EMAIL=$2
PASSWORD=$3
DEST=$4
ALIAS=$5

if [ -z $DEST ]
then
    read -p "Enter DOMAIN: " DOMAIN
else
      echo ${DOMAIN} "is NOT empty"
fi

if [ -z $EMAIL ]
then
    read -p "Enter EMAIL: " EMAIL
else
      echo ${EMAIL} "is NOT empty"
fi

if [ -z $PASSWORD ]
then
    read -p "Enter PASSWORD: " PASSWORD
else
      echo ${PASSWORD} "is NOT empty"
fi

if [ -z $DEST ]
then
    read -p "Enter DEST: " DEST
else
      echo ${DEST} "is NOT empty"
fi

if [ -z $ALIAS ]
then
    read -p "Enter ALIAS: " ALIAS
else
      echo ${ALIAS} "is NOT empty"
fi

echo $DOMAIN
echo $EMAIL
echo $PASSWORD
echo $DEST
echo $ALIAS
# Add the staging option (--staging) to certbot-auto if you wish to validate the procedure
certbot certonly --debug --non-interactive --email ${EMAIL} --agree-tos --standalone -d ${DOMAIN} --keep-until-expiring
openssl pkcs12 -export -in /etc/letsencrypt/live/${DOMAIN}/cert.pem -inkey /etc/letsencrypt/live/${DOMAIN}/privkey.pem -out ${DEST} -name ${ALIAS} -CAfile /etc/letsencrypt/live/${DOMAIN}/chain.pem -caname root -passout pass:${PASSWORD}
