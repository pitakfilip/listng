# Build all artifacts

cd ../../..
HOME_DIR=$PWD
SERVICES_DIR=$HOME_DIR/Services

cd $HOME_DIR/Infrastructure/listng-infrastructure
mvn clean install

cd $SERVICES_DIR/listng-consul-develop
mvn clean install

cd $SERVICES_DIR/listng-user
mvn clean install

cd $SERVICES_DIR/listng-course
mvn clean install

cd $SERVICES_DIR/listng-task
mvn clean install

#cd $SERVICES_DIR/listng-solution
#mvn clean install

#cd $SERVICES_DIR/listng-test
#mvn clean install

#cd $SERVICES_DIR/listng-file
#mvn clean install

cd $HOME_DIR/GUI/listng-rest
mvn clean install

cd $HOME_DIR/GUI/listng-ui
npm install
npm run build

