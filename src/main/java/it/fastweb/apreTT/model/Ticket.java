package it.fastweb.apreTT.model;

import java.time.LocalDate;
import java.util.Date;

import it.fastweb.apreTT.config.Utility;

public class Ticket {

    private String identificativo;
    private String chiave_processo_master;
    private String stato; // Nuovo
    

    private String account;
    private String tipo_problema;
    private String categoria_problema;
    private String sottocategoria_problema;
    private String descrizione;
    private String processo;
    
    
    
//    String account_no;
//	String Tipo_Problema;
//	String Categoria_Problema;
//	String Sottocategoria_Problema;
//	String Descrizione;
//	String processo;
	private Date data_inserimento;
	private Date data_invio_mail;
	private String id_ticket_creato;
	private int invio_mail;
	private Date data_schedulata;
	private String Priorita;
	private String response_code;
	private String response_message;
	private String bpa_agent;
    
 // Serve per capire se l'elemento è elaborato, al di la del suo esito.
 	// Serve per non aggiornare il db in caso di eccezioni in questa classe.
    private boolean esito;
    private String esito_message;
    
    public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	private boolean elaborato;
    private String errore;

    
    public Ticket(String account, String tipo_problema, String categoria_problema, 
			String sottocategoria_problema, String descrizione, String chiave_processo_master, String processo)
	{
		this.setAccount(account);
		this.setTipo_problema(tipo_problema);
		this.setCategoria_problema(categoria_problema);
		this.setSottocategoria_problema(sottocategoria_problema);
		this.descrizione = descrizione;
		this.setChiave_processo_master(chiave_processo_master);
		this.setProcesso(processo);
		this.esito = false;
		this.elaborato = false;
		this.setEsito_message("");
	}
    
    public String getIdentificativo() {
        return identificativo;
    }

    public void setIdentificativo(String identificativo) {
        this.identificativo = identificativo;
    }

    public boolean getEsito() {
        return esito;
    }

    public void setEsito(boolean esito) {
        this.esito = esito;
    }

    public boolean getElaborato() {
        return elaborato;
    }

    public void setElaborato(boolean elaborato) {
        this.elaborato = elaborato;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }

    

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

	public String getSottocategoria_problema() {
		return sottocategoria_problema;
	}

	public void setSottocategoria_problema(String sottocategoria_problema) {
		this.sottocategoria_problema = sottocategoria_problema;
	}

	public String getCategoria_problema() {
		return categoria_problema;
	}

	public void setCategoria_problema(String categoria_problema) {
		this.categoria_problema = categoria_problema;
	}

	public String getTipo_problema() {
		return tipo_problema;
	}

	public void setTipo_problema(String tipo_problema) {
		this.tipo_problema = tipo_problema;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getChiave_processo_master() {
		return chiave_processo_master;
	}

	public void setChiave_processo_master(String chiave_processo_master) {
		this.chiave_processo_master = chiave_processo_master;
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

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getEsito_message() {
		return esito_message;
	}

	public void setEsito_message(String esito_message) {
		this.esito_message = esito_message;
	}
	
}
