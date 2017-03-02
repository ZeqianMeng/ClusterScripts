package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ClusterAccounting {
	
	public static void main(String[] args) throws IOException {
		final String file_path = "/mnt/iusers01/zk01/mbaxjzm3/";
		//final String job_unread = "job_unread.txt";
		File job_unread = new File(file_path + "job_unread.txt");
		//if there is job complete data not been processed
		if(job_unread.exists()) { 
			//to process the data
			//to rename file_unread to file_read
			File file_read = new File(file_path + "job_read.txt");
			File file_broker = new File(file_path + "job_duration.txt");
			//System.out.println("2    hi I am here");
			if(!file_broker.exists()){
				file_broker.createNewFile();
    		}
			FileWriter fileWritter = new FileWriter(file_broker.getName(),true);
			PrintWriter pw = new PrintWriter(fileWritter);
	        //BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			
			if(job_unread.renameTo(file_read)){
				String command_1 = "chmod 777 /mnt/iusers01/zk01/mbaxjzm3/job_read.txt";
				//String command = "ls /opt/";
			    Runtime rt = Runtime.getRuntime();
			    rt.exec(command_1);
			    
			    String command_2 = "chmod 777 /mnt/iusers01/zk01/mbaxjzm3/job_duration.txt";
				//String command = "ls /opt/";
			    //Runtime rt_2 = Runtime.getRuntime();
			    rt.exec(command_2);
			    
				BufferedReader br = new BufferedReader(new FileReader(file_read));
				String old_record;
				String duration = null;
				String cluster_command;
				//boolean finished = false;
			    while ((old_record = br.readLine()) != null) {
			    	String[] ids = old_record.split(":");
			    	String redqueen_job_id = ids[0];
			    	//System.out.println("------ redqueen job id: " + redqueen_job_id);
			    	String broker_job_id = ids[1];
			    	//System.out.println("++++++ broker job id: " + broker_job_id);
			    	String job_type = ids[2];
			    	//System.out.println("====== job type: " + job_type);
			    	//String duration;
			    	String new_record = null;
			    	if(job_type.equalsIgnoreCase("s")){
			    		cluster_command = "qacct -j " + redqueen_job_id;
			    	    Process pr = rt.exec(cluster_command);
			    	    //Process pr = rt.exec("ls /opt/test/ && sudo chmod 777 -R /opt/test/");

			    	    String s = null;

			    	    BufferedReader stdInput = new BufferedReader(new
			    	        InputStreamReader(pr.getInputStream()));

			    	    System.out.println("Here is the output of the data fetch procedure:\n");
			    	    while ((s = stdInput.readLine()) != null){
			    	    //while ((s = stdInput.readLine()) != null && s.contains("cpu")) {
			    	    	if(s.contains("cpu")){
			    	    		//finished = true;
			    	            System.out.println(s);
			    	            duration = s.substring(13, 22);
			    	            new_record = broker_job_id + ":" + duration;
					    	    pw.println(new_record);
			    	            break;
			    	    	}
			    	    	if(s.contains("error")){
			    	    		System.out.println(s);
			    	    		new_record = broker_job_id + ":0";
			    	    		pw.println(new_record);
			    	            break;
			    	    	}
			    	    }

			    	    //duration = processSerialData(redqueen_job_id);
			    	}
			    	if(job_type.equalsIgnoreCase("p")){
			    		double pallel_duration = 0;
			    		String command = "qacct -j " + redqueen_job_id;
			    	    Process pr = rt.exec(command);
			    	    //Process pr = rt.exec("ls /opt/test/ && sudo chmod 777 -R /opt/test/");

			    	    String s = null;

			    	    BufferedReader stdInput = new BufferedReader(new
			    	        InputStreamReader(pr.getInputStream()));

			    	    System.out.println("Here is the output of the data fetch procedure:\n");
			    	    while ((s = stdInput.readLine()) != null){
			    	    	if(s.contains("cpu")){
			    	    //while ((s = stdInput.readLine()) != null && s.contains("cpu")) {
			    	            System.out.println(s);
			    	            pallel_duration = pallel_duration + Double.parseDouble(s.substring(13, 22));
			    	            new_record = broker_job_id + ":" + Double.toString(pallel_duration);
					    	    pw.println(new_record);
			    	    	}
			    	    	if(s.contains("error")){
			    	    		System.out.println(s);
			    	    		new_record = broker_job_id + ":0";
			    	    		pw.println(new_record);
			    	            break;
			    	    	}
			    	    }
			    		//duration = processParallelData(redqueen_job_id);

			    	}
			    	//pw.println(new_record);
			    	//bufferWritter.write(new_record);
			    	//bufferWritter.newLine();
			    }
			    pw.close();
			    //bufferWritter.close();
			    br.close();
			    //file_read.delete();
			}
			else{
				System.out.println("Rename file failed");
			}
		}
	}

}
