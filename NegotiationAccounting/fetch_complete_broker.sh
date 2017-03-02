#!/usr/local/bin/expect -f
set jobid [lindex $argv 0];
puts "$jobid"
spawn ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no mengz@rs0.cs.man.ac.uk
expect "password: "
send "*********\r"
expect "$ "
send "scp -i /home/MSC11/mengz/Desktop/tests/ahe3_testbed.pem /home/MSC11/mengz/Desktop/tests/$jobid.txt ec2-user@ec2-34-251-31-88.eu-west-1.compute.amazonaws.com:/opt/test/\r"
sleep 8
expect "$ "
send "exit\r"