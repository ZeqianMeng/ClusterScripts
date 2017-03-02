#!/bin/bash
rjobid="$1"
bjobid="$2"
java -Xmx1g -jar /mnt/iusers01/zk01/mbaxjzm3/CheckJobComplete.jar $rjobid $bjobid
sleep 8
exit

