package it.fastweb.apreTT.batch;

import it.fastweb.apreTT.model.Ticket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TicketWriter implements ItemWriter<Ticket> {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource_business;
    private boolean esitoUpdate;
    private static final Logger log = LoggerFactory.getLogger(TicketWriter.class);
    
    
    
  
    
    private static String updateResponseCodeOk = "UPDATE apre_tt_mail SET response_code='OK',response_message= ?, "
                                                  + " bpa_agent='API', data_invio_mail=NOW()," 
    		                                      + " id_ticket_creato= ?" 
                                                  +	" WHERE chiave_processo_master= ? "
    		                                      + " AND processo= ?";
    		
    		
    		
    private static String updateResponseCodeError = "UPDATE apre_tt_mail SET response_code='Errore',response_message= ?, "
                                                    + " bpa_agent='API', data_invio_mail=NOW() " 
                                                    + " WHERE chiave_processo_master= ? "
                                                    + " AND processo= ?";

    @Autowired
    public TicketWriter(JdbcTemplate jdbcTemplate, @Qualifier("database_remedy")DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource_business = dataSource;
    }

    @Override
    public void write(List<? extends Ticket> listaTickets) throws Exception {
            
            listaTickets.forEach(ticketSingolo -> {
                log.info("Ticket " + ticketSingolo.getAccount());
                if (ticketSingolo.getElaborato()) {
                    if (ticketSingolo.getEsito()) {
                        
                        log.info("*****Inserimento ticket id: " + ticketSingolo.getIdentificativo() + " andato a buon fine.");
                        try {
                            boolean forQuery = true;
                            esitoUpdate = updateEsito(ticketSingolo.getIdentificativo(), ticketSingolo.getEsito_message(),ticketSingolo.getChiave_processo_master(), ticketSingolo.getProcesso() ,forQuery);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        if (!esitoUpdate) {
                            log.error("*****Errore nell'inserimento del ticket nr " + ticketSingolo.getIdentificativo());
                        }
                    } else {
                        log.info("*****Errore inserimento " + ticketSingolo.getIdentificativo());

                        try {
                            boolean forQuery = false;
                            esitoUpdate = updateEsito(null, ticketSingolo.getEsito_message(),ticketSingolo.getChiave_processo_master(), ticketSingolo.getProcesso(), forQuery);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        if (!esitoUpdate) {
                            log.error("*****Errore aggiornamento db - Evento nr " + ticketSingolo.getIdentificativo() + " NON creato");
                        }
                    }
                }
            });
    }

    private Boolean updateEsito(String id_ticket_creato, String esito_messaggio, String chiave_processo_master, String processo,  boolean forQuery) throws SQLException {

        String sql = "";

        if (forQuery) {
            sql = updateResponseCodeOk;
        } else {
        	sql = updateResponseCodeError;
        }

        if (forQuery ) {
            return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
                   @Override
                   public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

                         ps.setString(1, esito_messaggio);
                         ps.setString(2, id_ticket_creato);
                         ps.setString(3, chiave_processo_master);
                         ps.setString(4, processo);

                         return ps.execute();
                   }
            });
        } else {
        	return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
                @Override
                public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

                	 ps.setString(1, esito_messaggio);
                     ps.setString(2, chiave_processo_master);
                     ps.setString(3, processo);

                      return ps.execute();
                }
         });
        }
        	
    }
	
}
