package it.fastweb.apreTT.config;

import com.bmc.arsys.api.ARServerUser;
import com.jcraft.jsch.Session;

import it.fastweb.apreTT.client.SessionRemedyClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import java.net.URISyntaxException;

public class JobListener implements JobExecutionListener, StepExecutionListener {

    private final SessionRemedyConfiguration sessionRemedyClient;
    private ARServerUser session;
    private static final Logger log = LoggerFactory.getLogger(JobListener.class);

    @Autowired
    public JobListener(SessionRemedyConfiguration sessionClient) throws URISyntaxException {
        this.sessionRemedyClient = sessionClient;
    }

    public void openSession() {

        try {
//            String user = sessionRemedyClient.getUserName();
//            String password = sessionRemedyClient.getUserPassword();
//            String server = sessionRemedyClient.getServerName();
        	log.info("Call apertura sessione di remedy nel listener");
            sessionRemedyClient.openSessionRemedy();

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("Sessione remedy aperta nel listener");
        return;
    }

    public void closeSession() {

        try {
        	log.info("Call chiusura sessione di remedy nel listener");
        	sessionRemedyClient.logoutSessionRemedy();

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("Sessione remedy chiusa nel listener" );
        return ;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
    	log.info("*****Login server Remedy");
    	openSession();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
    	log.info("*****Logout server Remedy");
        closeSession();
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
    }

    @Nullable
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        return null;
    }
}
