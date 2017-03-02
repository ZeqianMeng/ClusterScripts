#!/usr/bin/expect -f
set numjobs [lindex $argv 0];
set nefold [lindex $argv 1];
set brokerjobid [lindex $argv 2];
set servicelevel [lindex $argv 3];
puts "$numjobs"
puts "$nefold"
puts "$brokerjobid"
puts "$servicelevel"
spawn ssh mbaxjzm3@csf2.itservices.manchester.ac.uk
expect "password: "
send "**********\r"
expect "$ "
send "java -Xmx1g -jar /mnt/iusers01/zk01/mbaxjzm3/JobSubmitCluster.jar $numjobs $nefold $brokerjobid $servicelevel\r"
sleep 8
expect "$ "
send "scp /mnt/iusers01/zk01/mbaxjzm3/job_unread.txt mengz@rs0.cs.man.ac.uk:/home/MSC11/mengz/Desktop/tests/\r"
expect "password: "
send "mM_20110919\r"
sleep 8
expect "$ "
send "exit\r"