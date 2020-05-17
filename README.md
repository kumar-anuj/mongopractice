# mongopractice
mongodb practice

1.
Start MongoDB.

You can start the mongod process by issuing the following command:

sudo systemctl start mongod

If you receive an error similar to the following when starting mongod:
Failed to start mongod.service: Unit mongod.service not found.

Run the following command first:

sudo systemctl daemon-reload

Then run the start command above again.

2.
Verify that MongoDB has started successfully.

sudo systemctl status mongod

You can optionally ensure that MongoDB will start following a system reboot by issuing the following command:

sudo systemctl enable mongod

3.
Stop MongoDB.

As needed, you can stop the mongod process by issuing the following command:

sudo systemctl stop mongod

4.
Restart MongoDB.

You can restart the mongod process by issuing the following command:

sudo systemctl restart mongod

You can follow the state of the process for errors or important messages by watching the output in the /var/log/mongodb/mongod.log file.

5.
Begin using MongoDB.

Start a mongo shell on the same host machine as the mongod. You can run the mongo shell without any command-line options to connect to a mongod that is running on your localhost with default port 27017:

mongo

