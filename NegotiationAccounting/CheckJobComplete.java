package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CheckJobComplete {
	//this is placed in redqueen
	public static void main(String[] args) throws IOException{
		String redqueen_job_id = args[0];
		String broker_job_id = args[1];
		
		if(Integer.parseInt(redqueen_job_id) == 0){
			final String path_1 = "/mnt/iusers01/zk01/mbaxjzm3/";
			//String broker_job_id = args[0];
			System.out.println("****** to get cluster job id");
					//to change job_unread.txt to job_read.txt
					File unread_file = new File(path_1 + "job_unread.txt");
					if(!unread_file.exists()){
						System.out.println("****** the job_unread file does not exit!");
						return;
					}
					else{
						BufferedReader read_br = null;
						read_br = new BufferedReader(new FileReader(path_1 + "job_unread.txt"));
						String sCurrentLine;
						//String broker_job_id;
						while ((sCurrentLine = read_br.readLine()) != null) {
							if (sCurrentLine.contains(broker_job_id)){
								redqueen_job_id = sCurrentLine.split(":")[0];
								System.out.println("!!!!!!! cluster job id is " + redqueen_job_id);
								break;
							}
					}
					}
		}
		
		String command = "qstat";
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(command);
		BufferedReader stdInput = new BufferedReader(new
	            InputStreamReader(pr.getInputStream()));

		
		String s;

		while ((s = stdInput.readLine()) != null){
			if(s.contains(redqueen_job_id)){
				System.out.println("**************** job not completed yet!!!");
				//job not complete yet
		        //to kill the job in the queue
		        String kill_command = "qdel " + redqueen_job_id;
		        rt.exec(kill_command);
		        
		        
		        final String path = "/mnt/iusers01/zk01/mbaxjzm3/";
				File job_file = new File(path + redqueen_job_id + ".txt");
				
				if(!job_file.exists()){
					job_file.createNewFile();
				}
				
		        break;
			}
		}
		
	}

}
