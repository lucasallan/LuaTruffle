# LuaTruffle -  A Java implementation of the Lua language

Master: [![Build Status](https://travis-ci.org/lucasallan/luatruffle.png?branch=master)](https://travis-ci.org/lucasallan/luatruffle)

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

## Performance

Fibonacci is of course a terrible benchmark, but it's all we can run at the
moment. We also probably don't implement Lua correctly yet.

Compare:

    lua src/test/resources/fibonacci.lua
    luajit src/test/resources/fibonacci.lua
    JAVACMD=../graalvm-jdk1.8.0/bin/java bin/jlua src/test/resources/fibonacci.lua

We're around 6x compared to `lua`, and a third as fast as `luajit`.
