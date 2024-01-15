package com.globalids;

import com.globalids.service.Service;

import java.util.List;
import java.util.Scanner;

public class GidMain {
	static String appName = "Globalid";
	static Arguments arguments = new Arguments(appName);
	
	public static void main(String[] args) {
		
		arguments
			.addAvailableAgrAndDetails("app", Arguments.ArgType.Optional, "add,edit,delete and get all Application")
			.addAvailableAgrAndDetails("be", Arguments.ArgType.Optional, "add,edit,delete and get all Business entity")
			.addAvailableAgrAndDetails("glossary", Arguments.ArgType.Optional, "add,import,delete and get all Glossary")
			.addAvailableAgrAndDetails("connection", Arguments.ArgType.Optional, "add,edit,delete and get all Databse connections")
			.addAvailableAgrAndDetails("object", Arguments.ArgType.Optional, "add,edit,delete and get all Semantic Objects")
			.addAvailableAgrAndDetails("h", Arguments.ArgType.Optional, "help");
		
		
		System.out.println("--- Global Id Data Management System ---");
		openInteractiveShell();
	}
	
	private static void openInteractiveShell(){
		Scanner scanner = new Scanner(System.in);
		Arguments arg = new Arguments("gid");
		
		while(true) {
			try{
				System.out.print("GID@1.0 $ : ");
				String args = scanner.nextLine();
				
				if(args.length()<1)
					continue;
				
				arg.clear();
				
				arg.setArgs(args.split(" "));
				
				arg.parse();
				
				List<String> value = arg.getValueByArg("gid");
				
				String commandBase = value.get(0);
				
				if(commandBase.equalsIgnoreCase("quit") || commandBase.equalsIgnoreCase("q")){
					break;
				}
				
				Topic topic = Topic.valueOf(commandBase);
				
				topic.process(args.split(" "));
				
			} catch (Exception exception) {
				System.err.println("Invalid command!");
				arguments.help();
			}
			
			
		}
	}
}
