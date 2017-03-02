#!/usr/local/bin/expect -f
spawn ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no mengz@rs0.cs.man.ac.uk
expect "password: "
send "*********\r"
expect "$ "
send "/home/MSC11/mengz/Desktop/tests/fetch_file_desktop.sh\r"
sleep 28
expect "$ "
send "scp -i /home/MSC11/mengz/Desktop/tests/ahe3_testbed.pem /home/MSC11/mengz/Desktop/tests/job_duration.txt ec2-user@ec2-34-251-31-88.eu-west-1.compute.amazonaws.com:/opt/test/\r"
sleep 5
expect "$ "
send "exit\r"