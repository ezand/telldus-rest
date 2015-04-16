# telldus-rest
Rest wrapper around the Telldus CLI (tdtool)

[![Build Status](https://travis-ci.org/ezand/telldus-rest.svg?branch=master)](https://travis-ci.org/ezand/telldus-rest)
[![Javadoc](http://javadoc-badge.appspot.com/org.ezand.telldus/telldus-core.svg?label=telldus-core)](http://ezand.org/javadocs/telldus-core/release/1.1/)
[![Javadoc](http://javadoc-badge.appspot.com/org.ezand.telldus/telldus-java.svg?label=telldus-java)](http://ezand.org/javadocs/telldus-java/release/1.2/)
[![Javadoc](http://javadoc-badge.appspot.com/org.ezand.telldus/telldus-rest.svg?label=telldus-rest)](http://ezand.org/javadocs/telldus-rest/release/1.1/)

# Maven
    <dependency>
        <groupId>org.ezand.telldus</groupId>
        <artifactId>telldus-rest</artifactId>
        <version>1.0</version>
    </dependency>

# Usage
Start a standalone webapp using the following commando:

    java -jar telldus-rest-<version>.jar --telldus.tdtool=<path_to_tdtool> --server.port=<port_number> --security.user.name=<username> --security.user.password=<password>
    
    Ex.:
    java -jar telldus-rest-1.0.jar --telldus.tdtool=/usr/bin/tdtool --server.port=8081 --security.user.name=user --security.user.password=pass
    
You can ommit the --server.port=<port_number> to start the webapp at port 8080 by default.
