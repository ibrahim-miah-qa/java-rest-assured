package com.globalids.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globalids.Function;
import com.globalids.Arguments;
import com.globalids.forms.AddApplication;
import com.globalids.forms.Application;
import com.globalids.http.AppHttp;
import light.core.api.RequestMaker;


public class AppService implements Service {
	protected Arguments arguments;
	protected String appName = "app";
	ObjectMapper mapper = new ObjectMapper()
		.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
	
	RequestMaker requestMaker;
	
	boolean printHelp = false;
	
	String inputFileName = "";
	String ruleFileName = "";
	String outputFileName = "";
	int totalNumber = 0;
	
	public AppService() {
		// set mandatory and option arguments for this app
		arguments = new Arguments(appName);
		arguments
			.setAppDetails("app provide functionality to add, edit, delete and update application in GlobalID server")
			.setNote("-d or -r one of them is mandatory")
			.addAvailableAgrAndDetails("d", Arguments.ArgType.Optional, "data json file from where you want to add applications")
			.addAvailableAgrAndDetails("r", Arguments.ArgType.Optional,"rules json file where you write a rule in json schema to create applications")
			.addAvailableAgrAndDetails("o", Arguments.ArgType.Optional, "output file where you want to get the created application data as json")
			.addAvailableAgrAndDetails("n", Arguments.ArgType.Optional, "how manu number of application you want to create");
		
		
		// create request maker
		requestMaker= new RequestMaker
			.Builder()
			.setBaseUrl("https://reqres.in")
			.build();
	}
	
	public void create() throws Exception {
		if(ruleFileName.isEmpty() && inputFileName.isEmpty())
			throw new Exception("provide rule or data file name with the parameter -d or -r\nexample: app add -d data.json\nor\napp add -r rule.json");
		
		File file = new File(inputFileName.isEmpty()?ruleFileName:inputFileName);
		if (!file.exists()) {
			throw new Exception("input file not present!");
		}
		
		List<AddApplication> applications = mapper.readValue(file, new TypeReference<List<AddApplication>>() {});
		
		if(totalNumber == 0) {
			totalNumber = applications.size();
		}
		
		int i =0;
		System.out.println("total app processed : " + totalNumber);
		
		File generatedFile = new File(outputFileName);
		if(generatedFile.exists()) {
			throw new Exception("file already present : " + outputFileName);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper
			.writerWithDefaultPrettyPrinter()
			.writeValue(generatedFile, applications);
		
		System.out.println("output file generated " + generatedFile.getAbsolutePath());
	}
	
	
	public void update() {
		System.out.println("Update app");
	}
	public void delete() {
		System.out.println("Delete app");
	}
	
	public void find() {
		System.out.println("Find app");
	}
	
	public void all() throws Exception {
		AppHttp appHttp = new AppHttp();
		List<Application> apps = appHttp.getApplication();
		
		if(outputFileName.isEmpty()) {
			throw new Exception("output file not present!");
		}
		
		File generatedFile = new File(outputFileName);
		
		if(generatedFile.exists()) {
			throw new Exception("output file already exists!");
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper
			.writerWithDefaultPrettyPrinter()
			.writeValue(generatedFile, apps);
		
		System.out.println("output file generated : " + generatedFile.getAbsolutePath());
	}
	
	@Override
	public void execute(Function function, String[] arg) {
		this.arguments.setArgs(arg);
		
		try {
			if (!arguments.parse()) {
				this.arguments.help();
				throw new Exception("missing arguments");
			}
			
			if(arguments.find("h")) {
				arguments.help();
				return;
			}
			
			switch (function) {
				case add:
					this.create();
					break;
				
				case update:
					this.update();
					break;
				
				case delete:
					this.delete();
					break;
				
				case find:
					this.find();
					break;
				
				case all:
					this.all();
					break;
			}
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
		}
	}
	
	public void execute(Function function) throws Exception {
		switch (function) {
			case add:
				this.create();
				break;
			
			case update:
				this.update();
				break;
			
			case delete:
				this.delete();
				break;
			
			case find:
				this.find();
				break;
			
			case all:
				this.all();
				break;
		}
	}
	
	@Override
	public void help() {
		System.out.println("app help");
		this.arguments.help();
	}
	
	@Override
	public void process(String[] s) {
		arguments.setArgs(s);
		try {
			arguments.parse();
			
			List<String> values = arguments.getValueByArg(this.appName);
			
			if(arguments.find("h")) {
				printHelp = true;
				throw new Exception("--- help ---");
			}
			
			if(values.size()<2){
				printHelp = true;
				throw new Exception("Invalid command");
			}
			
			String option = values.get(1);
			
			Function function;
			try{
				function = Function.valueOf(option);
			}catch (Exception e) {
				printHelp = true;
				throw new Exception("Invalid option");
			}
			
			if(this.arguments.find("d")) {
				List<String> v = this.arguments.getValueByArg("d");
				
				if( v.size()>=1) {
					inputFileName = v.get(0);
				}
			}
			
			if(arguments.find("r")){
				List<String> r = this.arguments.getValueByArg("r");
				if(r.size()>=1){
					ruleFileName = r.get(0);
				}
			}
			
			if( this.arguments.find("o")) {
				List<String> names = this.arguments.getValueByArg("o");
				
				if(names.size() >=1) {
					outputFileName = names.get(0);
				}
			}else{
				outputFileName = new Date()
					.toString()
					.replace(" ", "_")
					.replace(":","_") + ".json";
			}
			
			if(arguments.find("n")) {
				if(arguments.getValueByArg("n").size() == 0) {
					throw new Exception("provide number how many application to you want to process.");
				}
				
				totalNumber = Integer.parseInt(arguments.getValueByArg("n").get(0));
			}
			
			this.execute(function);
			
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			if(printHelp)
				arguments.help();
			
			printHelp = false;
		}
	}
}
