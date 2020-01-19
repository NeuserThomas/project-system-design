#! /bin/bash

echo "6Ah5Xz5eWecjEPu";
ssh -L 9100:10.244.1.46:80 -i '/home/robin/.jFed/login-certs/cea463f731dbfd13b218d22b2a43129e.pem' rgoussey@n075-06.wall1.ilabt.iminds.be -oPort=22 -oProxyCommand="ssh -i '/home/robin/.jFed/login-certs/cea463f731dbfd13b218d22b2a43129e.pem' -oPort=22 rgoussey@bastion.test.iminds.be -W %h:%p";
