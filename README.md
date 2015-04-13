# telldus-rest
Rest wrapper around the Telldus CLI (tdtool)

[![Build Status](https://travis-ci.org/ezand/telldus-rest.svg?branch=master)](https://travis-ci.org/ezand/telldus-rest)
[![Javadoc](http://javadoc-badge.appspot.com/org.ezand.telldus/telldus-rest.svg?label=telldus-rest)](http://ezand.org/javadocs/telldus-rest/release/1.0/)

# Maven
    <dependency>
        <groupId>org.ezand.telldus</groupId>
        <artifactId>telldus-rest</artifactId>
        <version>1.0</version>
    </dependency>

# Usage
Start a standalone webapp using the following commando:

    java -jar telldus-rest-<version>.jar --telldus.tdtool=<path_to_tdtoll> --server.port=<port_number> --security.user.name=<username> --security.user.password=<password>
    
    Ex.:
    java -jar telldus-rest-1.0.jar --telldus.tdtool=/usr/bin/tdtool --server.port=8081 --security.user.name=user --security.user.password=pass