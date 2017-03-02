#!/usr/local/bin/expect -f
set numjobs [lindex $argv 0];
set nefold [lindex $argv 1];
set brokerjobid [lindex $argv 2];
set servicelevel [lindex $argv 3];
puts "$numjobs"
puts "$nefold"
puts "$brokerjobid"
puts "$servicelevel"
spawn ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no mengz@rs0.cs.man.ac.uk
expect "password: "
send "***********\r"
expect "$ "
send "/home/MSC11/mengz/Desktop/tests/job_submit_desktop.sh $numjobs $nefold $brokerjobid $servicelevel\r"
expect "$ "
sleep 18
send "scp -i /home/MSC11/mengz/Desktop/tests/ahe3_testbed.pem /home/MSC11/mengz/Desktop/tests/job_unread.txt ec2-user@ec2-34-251-31-88.eu-west-1.compute.amazonaws.com:/opt/test/\r"
sleep 5
expect "$ "
send "exit\r"