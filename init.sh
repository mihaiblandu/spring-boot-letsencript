#sudo snap install --classic certbot
sudo certbot certonly -a standalone -d $1
cd /etc/letsencrypt/live/$1
pwd
ls -la
openssl pkcs12 -export -in fullchain.pem -inkey privkey.pem -out keystore.p12 -name home -CAfile chain.pem -caname root

