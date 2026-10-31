#!/usr/bin/env bash

cd "/D/Software/kafka" || exit 1;
./bin/kafka-server-start.sh ./config/server.properties