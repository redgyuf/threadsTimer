package multiThreadedTimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

	public static void main(String[] args) {
		System.out.println("Program started");
		List<Thread> threads = new CopyOnWriteArrayList();		
		Map<Thread, Timer> map = new HashMap();
		
		while(true){			
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			String[] command = input.split(" ", 2);			
			
			switch(command[0]){
			case "start": 
				boolean found = false;
				for (Thread thr : threads) {
					if(thr.getName().equals(command[1])){
						found = true;
						
						Timer timer = new Timer(map.get(thr).getTime());
						Thread thread = new Thread(timer);
						thread.setName(command[1]);
						thread.start();
						threads.add(thread);
						
						map.remove(thr);
						threads.remove(thr);
						map.put(thread, timer);
					}
				}
				
				if(!found){
					Timer timer = new Timer();
					Thread thread = new Thread(timer);
					thread.setName(command[1]);
					thread.start();
					threads.add(thread);
					map.put(thread, timer);
				}
				
				break;
				
			case "check":
				for (Thread thr : threads) {
					int time = map.get(thr).getTime();
					System.out.println("Name: " + thr.getName() + ", ID: " + thr.getId() + ", Seconds: " + time);
				}
				break;
				
			case "stop":
				for (Thread thr : threads) {
					if(thr.getName().equals(command[1])){
						thr.interrupt();
					}
				}
				break;
				
			case "exit":
				for (Thread thr : threads) {
					int time = map.get(thr).getTime();
					System.out.println("Name: " + thr.getName() + ", ID: " + thr.getId() + ", Seconds: " + time);
				}
				System.exit(0);
			}
		}	
		
	}

}
