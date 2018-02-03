1、编辑settings.xml配置文件
1.1、在节点servers中添加：
<server>
	<id>release</id>
	<username>liunan</username>
	<password>csc86</password>
</server>
<server>
	<id>snapshot</id>
	<username>liunan</username>
	<password>csc86</password>
</server>
1.2、在profile->properties节点下, 添加:
<com.csc.release>release</com.csc.release>
<com.csc.release.url>http://192.168.0.100:8081/artifactory/simple/ext-release-local</com.csc.release.url>
<com.csc.snapshot>snapshot</com.csc.snapshot>
<com.csc.snapshot.url>http://192.168.0.100:8081/artifactory/simple/ext-snapshot-local</com.csc.snapshot.url>
1.3、在profile->repositories节点下, 添加:
<repository>
	<id>${com.csc.release}</id>
	<url>${com.csc.release.url}</url>
	<name>Release Repository</name>
</repository>
<repository>
	<id>${com.csc.snapshot}</id>
	<url>${com.csc.snapshot.url}</url>
	<name>snapshot Repository</name>
</repository>

<repository>
	<id>spring</id>
	<name>Spring Maven Release Repository</name>
	<url>http://maven.springframework.org/release</url>
</repository>
<repository>
	<id>jboss</id>
	<name>Jboass Maven Release Repository</name>
	<url>https://repository.jboss.org/nexus/content/repositories/releases</url>
</repository>
<repository>
	<id>repo1</id>
	<name>public Maven Release Repository</name>
	<url>http://repo1.maven.org/maven2</url>
</repository>
<repository>
	<id>mvnrepository</id>
	<name>public Maven Release Repository</name>
	<url>http://mvnrepository.com/artifact</url>
</repository>

2、本地部署
mvn clean deploy -P release -DuploadVersion=1.0.0
mvn clean deploy -P snapshot -DuploadVersion=1.0.0

3、hudson中maven部署 
A、选择"This build is parameterized",添加如下参数:
	1、String类型参数version, 设置默认值:1.0.0
	2、Choice类型参数type, 设置值为: release、snapshot
B、在"Build"项中，设置"Goals and options"值如下:
clean deploy -P ${type} -DuploadVersion=${DuploadVersion}