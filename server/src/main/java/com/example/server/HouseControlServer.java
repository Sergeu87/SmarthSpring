package com.example.server;


import com.example.server.handlers.UsersHandler;
import com.example.server.httpentities.InitialValuesLoader;
import com.example.utils.DomoBusConfigLoader;
import com.example.utils.domain.HomeConfigEntity;
import com.example.server.httpentities.DeviceStateResponse;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type House control server.
 */
public class HouseControlServer {

	/**
	 * The constant PORT.
	 */
	public static int PORT = 9000;
	private static String CONFIG_FILENAME = "basic_config_1.xml";
	private static String INITIAL_VALUES_FILENAME = "initial_values.json";

	private static HomeConfigEntity homeConfiguration;
	private HttpServer server;


	private static ConcurrentHashMap<String, DeviceStateResponse> devicesValues = new ConcurrentHashMap<>();

	/**
	 * Device values to devices response list.
	 *
	 * @return the list
	 */
	public static List<DeviceStateResponse> deviceValuesToDevicesResponse(){
		List<DeviceStateResponse> list = new ArrayList<>();
		for (Map.Entry<String, DeviceStateResponse> entry : devicesValues.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}

	/**
	 * Get devices values concurrent hash map.
	 *
	 * @return the concurrent hash map
	 */
	public static ConcurrentHashMap<String, DeviceStateResponse> getDevicesValues(){
		return devicesValues;
	}

	/**
	 * Get home configuration entity home config entity.
	 *
	 * @return the home config entity
	 */
	public static HomeConfigEntity getHomeConfigurationEntity(){
		return homeConfiguration;
	}

	private HouseControlServer(){

		devicesValues = InitialValuesLoader.getDeviceInitialValues(INITIAL_VALUES_FILENAME);
	}


	/**
	 * Gets home configuration.
	 *
	 * @return the home configuration
	 */
	public HomeConfigEntity getHomeConfiguration() {
		return homeConfiguration;
	}

	/**
	 * Sets home configuration.
	 *
	 * @param homeConfiguration the home configuration
	 */
	public void setHomeConfiguration(HomeConfigEntity homeConfiguration) {
		this.homeConfiguration = homeConfiguration;
	}


	/**
	 * Populate device values.
	 */
	public void populateDeviceValues(){

	}

	/**
	 * Start server.
	 *
	 * @param port the port
	 */
	public void startServer(int port) {
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
			System.out.println("Server started at " + port);

			server.createContext("/", new HandlersOLD.RootHandler());
			server.createContext("/echoHeader", new HandlersOLD.EchoHeaderHandler());
			server.createContext("/echoGet", new HandlersOLD.EchoGetHandler());
			server.createContext("/echoPost", new HandlersOLD.EchoPostHandler());
			server.createContext("/devices", new HandlersOLD.DevicesHandler());
			server.createContext("/divisions", new HandlersOLD.DivisionsHandler());
			server.createContext("/events", new HandlersOLD.EventsHandler());
			server.createContext("/overview", new HandlersOLD.OverviewHandler());
			server.createContext("/config", new HandlersOLD.ConfigHandler());
			server.createContext("/login", new UsersHandler());
			server.setExecutor(null);
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stop.
	 */
	public void Stop() {
		server.stop(0);
		System.out.println("server stopped");
	}


	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 * @throws IOException the io exception
	 */
	public static void main(String[] args) throws IOException {

		System.out.println("=> Creating Server");
		HouseControlServer houseControl = new HouseControlServer();

		System.out.println("=> Parsing configuration file ...");
		DomoBusConfigLoader configLoader = new DomoBusConfigLoader(CONFIG_FILENAME, true);
		HomeConfigEntity homeConfig = configLoader.getHomeConfig();

		if (homeConfig == null) {
			System.out.println("!!! Error parsing the house configuration file! :( !!!");
		} else {
			houseControl.setHomeConfiguration(configLoader.getHomeConfig());

			System.out.println("=> Starting HTTP Server ...");
			houseControl.startServer(PORT);

			System.out.println("=> Starting Command Receiver ...");
			ControlCommandReceiver commandReceiver = new ControlCommandReceiver();
			commandReceiver.start();

			System.out.println("=> Press [ENTER] to stop HTTP server ...");
			System.in.read();
			houseControl.Stop();
		}
	}
}
