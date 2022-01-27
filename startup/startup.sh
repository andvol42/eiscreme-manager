#! /bin/bash
cd ..

./mvnw clean package
nohup java -jar ./target/eiscrememanager-0.0.1-SNAPSHOT.jar > ./startup/log_backend.txt 2>&1 &
echo $! > ./startup/pid_backend.file

npm --prefix ./startup install ./src/main/frontend/
cd startup/node_modules/frontend
npm install
npm start