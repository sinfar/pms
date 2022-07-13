echo 'The following Maven command installs your Maven-built Java application'
echo 'into the local Maven repository, which will ultimately be stored in'
echo 'Jenkins''s local Maven repository (and the "maven-repository" Docker data'
echo 'volume).'
mvn jar:jar install:install help:evaluate -Dexpression=project.name

echo 'The following complex command extracts the value of the <name/> element'
echo 'within <project/> of your Java/Maven project''s "pom.xml" file.'
NAME=`mvn help:evaluate -Dexpression=project.name | grep "^[^\[]"`

echo 'The following complex command behaves similarly to the previous one but'
echo 'extracts the value of the <version/> element within <project/> instead.'
VERSION=`mvn help:evaluate -Dexpression=project.version | grep "^[^\[]"`


echo 'The following command runs and outputs the execution of your Java'
echo 'application (which Jenkins built using Maven) to the Jenkins UI.'

cd start 
echo `pwd`
chmod 777 /var/jenkins_home/workspace/pms_master/start/target/start-1.0.0-SNAPSHOT.jar
java -jar /var/jenkins_home/workspace/pms_master/start/target/start-1.0.0-SNAPSHOT.jar
