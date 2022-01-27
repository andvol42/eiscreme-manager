#!/bin/bash
kill $(cat ./pid_backend.file)
rm ./pid_backend.file
rm ./log_backend.txt
rm -rf ./node_modules
rm -rf node_modules
rm package-lock.json
rm package.json