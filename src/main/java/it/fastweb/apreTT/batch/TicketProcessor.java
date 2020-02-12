package it.fastweb.apreTT.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.Nullable;

import it.fastweb.apreTT.client.SessionRemedyClient;
import it.fastweb.apreTT.config.SessionRemedyConfiguration;
import it.fastweb.apreTT.model.AperturaTicketData;
import it.fastweb.apreTT.model.Ticket;
import it.fastweb.apreTT.remedy.RemedyDao;

public class TicketProcessor implements ItemProcessor<Ticket, Ticket>{

	private SessionRemedyConfiguration sessionRemedy;
	private RemedyDao remedy_dao;
	
	private static final Logger log = LoggerFactory.getLogger(TicketProcessor.class);
	
	public TicketProcessor(SessionRemedyConfiguration sessionClient) {
		super();
		sessionRemedy = sessionClient;
		remedy_dao = new RemedyDao(sessionRemedy.getSessionRemedy());
		
	}


	@Nullable
	@Override
	public Ticket process(Ticket item) throws Exception {
		 log.info("*****Sto processando: " + item.getId_ticket_creato());

	        if (!item.getAccount().isEmpty() && !item.getTipo_problema().isEmpty()
	                && !item.getCategoria_problema().isEmpty() && !item.getSottocategoria_problema().isEmpty()) {

	            //crea ticket a Remedy
	        	
	            if (remedy_dao.creaTT(item)) {
	            	item.setEsito(true);
	            	log.info("*****Ticket creato: " + item.getId_ticket_creato());
	            }
	            	

	        } else {
	        	item.setEsito(false);
	        	item.setElaborato(true);
	        	item.setEsito_message("Campi necessari non presenti nell'entry sul DB!");
	            log.error("*****Errore campi mancanti ID " +item.getId_ticket_creato());
	        }

	        return item;
	}

}
