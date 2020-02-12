package it.fastweb.apreTT.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bmc.arsys.api.ARException;
import com.bmc.arsys.api.ARServerUser;

import it.fastweb.apreTT.client.SessionRemedyClient;

public class SessionRemedyConfiguration {

	 private SessionRemedyClient sessionRemedy;
	 private static final Logger log = LoggerFactory.getLogger(SessionRemedyConfiguration.class);
	 private String servername ;
	 private String username;
	 private String password;
	 
	 //configurazione session
	 private ARServerUser ctx;  

	    @Autowired
	    public SessionRemedyConfiguration(SessionRemedyClient sessionRemedyClient) {
	        this.sessionRemedy = sessionRemedyClient;
	        log.info("Inizializzazione Session Remedy ");
	    	servername = sessionRemedyClient.getServerName();
	    	username = sessionRemedyClient.getUserName();
	    	password = sessionRemedyClient.getUserPassword();
	    	ctx = new ARServerUser();  
			ctx.setServer(sessionRemedyClient.getServerName()); 
			ctx.setUser(sessionRemedyClient.getUserName());  
			ctx.setPassword(sessionRemedyClient.getUserPassword()); 
			
			
			log.info("ServerName Remedy = " + sessionRemedyClient.getServerName());
	    	log.info("UserName Remedy = " + sessionRemedyClient.getUserName());
	    	log.info("Password Remedy = " + sessionRemedyClient.getUserPassword());
	    }
	    
	    public ARServerUser getSessionRemedy() {
			return ctx;
		}
	    
	    public void  openSessionRemedy() throws ARException {
	    		    	
	    	log.info("ServerName Remedy = " + this.servername);
	    	log.info("UserName Remedy = " + this.username);
	    	log.info("Password Remedy = " + this.password );
			ctx.login();
			log.info("Session Remedy aperta ");
			return;
		}
			
		
		public void  logoutSessionRemedy() throws ARException {
			ctx.logout();
			log.info("Session Remedy chiusa ");
			return;
		}
}
