#!/bin/bash

echo "Checking Env for production-ready-feature project"

: "${ENABLE_PAYLOAD_ENCRYPTION?"ENABLE_PAYLOAD_ENCRYPTION is not set"}"
: "${BIG_JVM_OPTIONS?"BIG_JVM_OPTIONS is not set"}"

