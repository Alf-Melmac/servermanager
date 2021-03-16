package de.webalf.servermanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import static de.webalf.servermanager.model.Constants.CMD_EXE;
import static de.webalf.servermanager.model.Constants.EXECUTE_AND_TERMINATE;

/**
 * @author Alf
 * @since 28.01.2021
 */
@Getter
@AllArgsConstructor
public enum Server {
	TEST(new String[]{CMD_EXE, EXECUTE_AND_TERMINATE, "CALL \"Test File.lnk\""}, "test"),
	ARMA(new String[]{CMD_EXE, EXECUTE_AND_TERMINATE, "CALL \"03_ServerNeustart.bat - Verknüpfung.lnk\""}, "185.232.71.213"),
	ARMA1(new String[]{CMD_EXE, EXECUTE_AND_TERMINATE, "CALL \"13_ServerNeustart.bat - Verknüpfung.lnk\""}, "185.232.71.213:2302"),
	ARMA2(new String[]{CMD_EXE, EXECUTE_AND_TERMINATE, "CALL \"23_ServerNeustart.bat - Verknüpfung.lnk\""}, "185.232.71.213:2308"),
	ARMA3(new String[]{CMD_EXE, EXECUTE_AND_TERMINATE, "CALL \"33_ServerNeustart.bat - Verknüpfung.lnk\""}, "185.232.71.213:2316");

	private final String[] restartFilePath;
	private final String ip;

	@Getter
	private static final Map<String, String> ipToEnumMap = new HashMap<>();

	static {
		for (Server server : EnumSet.allOf(Server.class)) {
			ipToEnumMap.put(server.getIp(), server.toString());
		}
	}
}
