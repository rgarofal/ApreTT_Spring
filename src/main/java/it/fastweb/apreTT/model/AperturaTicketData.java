package it.fastweb.apreTT.model;

import java.time.LocalDate;
import java.util.Date;

import it.fastweb.apreTT.config.Utility;

//
// Classe per leggere la Tabella apre_tt_mail
//
public class AperturaTicketData {

	String account_no;
	String Tipo_Problema;
	String Categoria_Problema;
	String Sottocategoria_Problema;
	String Descrizione;
	String processo;
	Date data_inserimento;
	Date data_invio_mail;
	String id_ticket_creato;
	int invio_mail;
	Date data_schedulata;
	String Priorita;
	String response_code;
	String response_message;
	String bpa_agent;
	


	public AperturaTicketData() {
		super();
	}
	public AperturaTicketData(String account_no, String tipo_Problema, String categoria_Problema,
			String sottocategoria_Problema, String descrizione, String processo, Date data_inserimento,
			Date data_invio_mail, String id_ticket_creato, int invio_mail, Date data_schedulata, String priorita,
			String response_code, String response_message, String bpa_agent) {
		super();
		this.account_no = account_no;
		Tipo_Problema = tipo_Problema;
		Categoria_Problema = categoria_Problema;
		Sottocategoria_Problema = sottocategoria_Problema;
		Descrizione = descrizione;
		this.processo = processo;
		this.data_inserimento = data_inserimento;
		this.data_invio_mail = data_invio_mail;
		this.id_ticket_creato = id_ticket_creato;
		this.invio_mail = invio_mail;
		this.data_schedulata = data_schedulata;
		Priorita = priorita;
		this.response_code = response_code;
		this.response_message = response_message;
		this.bpa_agent = bpa_agent;
	}
	
	
	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getTipo_Problema() {
		return Tipo_Problema;
	}

	public void setTipo_Problema(String tipo_Problema) {
		Tipo_Problema = tipo_Problema;
	}

	public String getCategoria_Problema() {
		return Categoria_Problema;
	}

	public void setCategoria_Problema(String categoria_Problema) {
		Categoria_Problema = categoria_Problema;
	}

	public String getSottocategoria_Problema() {
		return Sottocategoria_Problema;
	}

	public void setSottocategoria_Problema(String sottocategoria_Problema) {
		Sottocategoria_Problema = sottocategoria_Problema;
	}

	public String getDescrizione() {
		return Descrizione;
	}

	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public LocalDate getData_inserimento() {
		 LocalDate localDate;
		 if (data_inserimento != null) {
             localDate = Utility.convertDateToLocalDate(data_inserimento);
        }else {
        	localDate = null;
        }
		return localDate;
	}

	public void setData_inserimento(Date data_inserimento) {
		this.data_inserimento = data_inserimento;
	}

	public LocalDate getData_invio_mail() {
		 LocalDate localDate;
		 if (data_invio_mail != null) {
             localDate = Utility.convertDateToLocalDate(data_invio_mail);
        }else {
        	localDate = null;
        }
		return localDate;
	}

	public void setData_invio_mail(Date data_invio_mail) {
		this.data_invio_mail = data_invio_mail;
	}

	public String getId_ticket_creato() {
		return id_ticket_creato;
	}

	public void setId_ticket_creato(String id_ticket_creato) {
		this.id_ticket_creato = id_ticket_creato;
	}

	public int getInvio_mail() {
		return invio_mail;
	}

	public void setInvio_mail(int invio_mail) {
		this.invio_mail = invio_mail;
	}

	public LocalDate getData_schedulata() {
		LocalDate localDate;
		 if (data_schedulata != null) {
            localDate = Utility.convertDateToLocalDate(data_schedulata);
       }else {
       	localDate = null;
       }
	   return localDate;
	}

	public void setData_schedulata(Date data_schedulata) {
		this.data_schedulata = data_schedulata;
	}

	public String getPriorita() {
		return Priorita;
	}

	public void setPriorita(String priorita) {
		Priorita = priorita;
	}

	public String getResponse_code() {
		return response_code;
	}

	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}

	public String getResponse_message() {
		return response_message;
	}

	public void setResponse_message(String response_message) {
		this.response_message = response_message;
	}

	public String getBpa_agent() {
		return bpa_agent;
	}

	public void setBpa_agent(String bpa_agent) {
		this.bpa_agent = bpa_agent;
	}
	
}
