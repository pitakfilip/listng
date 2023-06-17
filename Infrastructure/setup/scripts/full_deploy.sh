# Build all projects and deploy on localhost
HOME_DIR=$PWD
SERVICES_DIR=$HOME_DIR/services

TARGET=/etc/systemd/system

./build.sh

cp $SERVICES_DIR/consul.service $TARGET/consul.service
cp $SERVICES_DIR/listng-course-01.service $TARGET/listng-course-01.service
#cp $SERVICES_DIR/listng-course-02.service $TARGET/listng-course-02.service
cp $SERVICES_DIR/listng-user-01.service $TARGET/listng-user-01.service
#cp $SERVICES_DIR/listng-user-02.service $TARGET/listng-user-02.service
cp $SERVICES_DIR/listng-rest.service $TARGET/listng-rest.service
cp $SERVICES_DIR/listng-ui.service $TARGET/listng-ui.service

systemctl daemon-reload
systemctl enable consul
systemctl enable listng-course-01
systemctl enable listng-course-02 
systemctl enable listng-user-01
systemctl enable listng-user-02
systemctl enable listng-rest
systemctl enable listng-ui



