package it.fastweb.apreTT.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.bmc.arsys.apitransport.session.ARServerContext;

import it.fastweb.apreTT.config.JobListener;

import com.bmc.arsys.api.ARException;  
import com.bmc.arsys.api.ARServerUser;  

@Component
@ConfigurationProperties(prefix = "aprett-conf")
public class SessionRemedyClient {

    
    
    private String serverName;
    private String userName;
    private String userPassword;
    
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


    
}
