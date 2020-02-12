package it.fastweb.apreTT.config;

import org.springframework.jdbc.core.RowMapper;

import it.fastweb.apreTT.model.AperturaTicketData;
import it.fastweb.apreTT.model.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DbRowMapperTicket implements RowMapper<Ticket> {

    private static final String DATE = "date";
    
    
    private static final String ACCOUNT_NO = "account_no";
    private static final String TIPO_PROBLEMA = "Tipo_Problema";
	private static final String CATEGORIA_PROBLEMA = "Categoria_Problema";
	private static final String SOTTCAT_PROBLEMA = "Sottocategoria_Problema";
	private static final String DESCRIZIONE = "Descrizione";
	private static final String PROCESSO = "processo";
	private static final String DATA_INSERIMENTO = "data_inserimento";
	private static final String DATA_INVIO_MAIL = "data_invio_mail";
	private static final String ID_TICKET_CREATO = "id_ticket_creato";
	private static final String INVIO_MAIL = "invio_mail";
	private static final String DATA_SCHEDULATA = "data_schedulata";
	private static final String PRIORITA = "Priorita";
	private static final String RESPONSE_CODE = "response_code";
	private static final String RESPONSE_MESSAGE = "response_message";
	private static final String BPA_AGENT = "bpa_agent";

    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {

    	Ticket dati_apertura_ticket = new Ticket();

    	dati_apertura_ticket.setAccount(rs.getString(ACCOUNT_NO));
    	dati_apertura_ticket.setTipo_problema(rs.getString(TIPO_PROBLEMA));
    	dati_apertura_ticket.setCategoria_problema(rs.getString(CATEGORIA_PROBLEMA));
    	dati_apertura_ticket.setSottocategoria_problema(rs.getString(SOTTCAT_PROBLEMA));
    	dati_apertura_ticket.setDescrizione(rs.getString(DESCRIZIONE));
    	dati_apertura_ticket.setProcesso(rs.getString(PROCESSO));
    	
    	
    	dati_apertura_ticket.setData_inserimento(rs.getDate(DATA_INSERIMENTO));
    	dati_apertura_ticket.setData_invio_mail(rs.getDate(DATA_INVIO_MAIL));
    	dati_apertura_ticket.setId_ticket_creato(rs.getString(ID_TICKET_CREATO));
    	dati_apertura_ticket.setInvio_mail(rs.getInt(INVIO_MAIL));
    	dati_apertura_ticket.setData_schedulata(rs.getDate(DATA_SCHEDULATA));
    	dati_apertura_ticket.setPriorita(rs.getString(PRIORITA));
    	dati_apertura_ticket.setResponse_code(rs.getString(RESPONSE_CODE));
    	dati_apertura_ticket.setResponse_message(rs.getString(RESPONSE_MESSAGE));
    	dati_apertura_ticket.setBpa_agent(rs.getString(BPA_AGENT));

    	

        return dati_apertura_ticket;
    }

}
