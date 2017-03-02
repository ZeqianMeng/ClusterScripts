#!/usr/bin/expect -f
set rjobid [lindex $argv 0];
set bjobid [lindex $argv 1];
puts "$rjobid"
puts "$bjobid"
spawn ssh mbaxjzm3@csf2.itservices.manchester.ac.uk
expect "password: "
send "**********\r"
expect "$ "
send "java -Xmx1g -jar /mnt/iusers01/zk01/mbaxjzm3/CheckJobComplete.jar $rjobid $bjobid\r"
sleep 10
expect "$ "
send "scp /mnt/iusers01/zk01/mbaxjzm3/$rjobid.txt mengz@rs0.cs.man.ac.uk:/home/MSC11/mengz/Desktop/tests/\r"
expect "password: "
send "**********\r"
sleep 5
expect "$ "
send "exit\r"
