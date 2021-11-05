package de.rexlNico.Signs.Servers;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerManger {

	private ArrayList<Server> server = new ArrayList<>();
	private HashMap<Server, String> serverType = new HashMap<>();
	private HashMap<String, Server> servers = new HashMap<>();
	private ArrayList<Server> used = new ArrayList<>();
	
	public ServerManger() {
		
	}

	private ArrayList<Server> getServer() {
		return server;
	}

	private void setServer(ArrayList<Server> server) {
		this.server = server;
	}

	private HashMap<Server, String> getServerType() {
		return serverType;
	}

	private void setServerType(HashMap<Server, String> serverType) {
		this.serverType = serverType;
	}

	private ArrayList<Server> getUsed() {
		return used;
	}

	private void setUsed(ArrayList<Server> used) {
		this.used = used;
	}
	
	
	
}
