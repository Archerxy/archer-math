# mvn install to local repository
mvn install:install-file -DgroupId="com.archer" -DartifactId="archer-math" -Dversion="${version}" -Dpackaging=jar -Dfile=${path:}/archer-math-1.1.1.jar


# pom
<dependency>
    <groupId>com.archer</groupId>
    <artifactId>archer-math</artifactId>
    <version>${version}</version>
</dependency>
