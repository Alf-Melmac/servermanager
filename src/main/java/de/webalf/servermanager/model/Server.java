package de.webalf.servermanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static de.webalf.servermanager.model.Constants.CMD_EXE;
import static de.webalf.servermanager.model.Constants.EXECUTE_AND_TERMINATE;

/**
 * @author Alf
 * @since 28.01.2021
 */
@Getter
@AllArgsConstructor
public enum Server {
	TEST(new String[]{CMD_EXE, EXECUTE_AND_TERMINATE, "CALL \"Test File.lnk\""}, new String[]{CMD_EXE, EXECUTE_AND_TERMINATE, "CALL test.bat"}),
	ARMA(new String[]{CMD_EXE, EXECUTE_AND_TERMINATE, "CALL \"Server Autostart.lnk\""}, new String[]{CMD_EXE, EXECUTE_AND_TERMINATE, "CALL \"Server Autostop.lnk\""});

	private final String[] startFilePath;
	private final String[] stopFilePath;
}
