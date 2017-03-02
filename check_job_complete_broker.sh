#!/usr/local/bin/expect -f
set rjobid [lindex $argv 0];
set bjobid [lindex $argv 1];
puts "$rjobid"
puts "$bjobid"
spawn ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no mengz@rs0.cs.man.ac.uk
expect "password: "
send "mM_20110919\r"
expect "$ "
send "/home/MSC11/mengz/Desktop/tests/check_job_complete_desktop.sh $rjobid $bjobid\r"
sleep 20
expect "$ "
send "exit\r"
