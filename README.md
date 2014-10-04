# JLua -  A Java implementation of the Lua language

Master: [![Build Status](https://travis-ci.org/lucasallan/jlua.png?branch=master)](https://travis-ci.org/lucasallan/jlua)

## Compiling

    mvn package

## Running

    bin/jlua my.lua

## Running With Graal

Download one of:

* http://lafo.ssw.uni-linz.ac.at/graalvm/openjdk-8-graalvm-b132-linux-x86_64-0.5.tar.gz
* http://lafo.ssw.uni-linz.ac.at/graalvm/openjdk-8-graalvm-b132-macosx-x86_64-0.5.tar.gz

Then run:

    JAVACMD=../graalvm-jdk1.8.0/bin/java my.lua

## Options

To pass options to the JVM, prefix with `-J`. For example, `-J-Xmx1G`.
