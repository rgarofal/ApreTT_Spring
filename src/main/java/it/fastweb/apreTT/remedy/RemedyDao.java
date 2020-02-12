package it.fastweb.apreTT.remedy;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmc.arsys.api.ARException;
import com.bmc.arsys.api.ARServerUser;
import com.bmc.arsys.api.Entry;
import com.bmc.arsys.api.QualifierInfo;
import com.bmc.arsys.api.Value;
import it.fastweb.apreTT.model.Ticket;

public class RemedyDao {

    private static ARServerUser ctx;
    private static String form_gruppi = "CC Definizione Problemi";
    private static String form = "CC Trouble Ticket";
  
    private static final Logger logger = LoggerFactory.getLogger(RemedyDao.class.getName());
    

    public RemedyDao(ARServerUser session) {
		super();
		ctx = session;
	}


	public  boolean creaTT(Ticket ticket ) {
        boolean enable_remedy = false;
        try {

        	int[] intArray = new int[7];
			intArray[0] = 600030001; // Tipo Problema
			intArray[1] = 600030002; // Cateogria
			intArray[2] = 600030003; // Sottocategoria
			intArray[3] = 600030014; // Descrizione
			intArray[4] = 600000001; // Account
			intArray[5] = 112; // Assegnato al Gruppo DB
			intArray[6] = 4;  // Assegnato al Gruppo
			
			String queryString = "'Tipo Problema' = \"" +ticket.getTipo_problema()+"\" "
					+ "AND 'Categoria del Problema' = \"" +ticket.getCategoria_problema()+"\" "
					+ "AND 'Sottocategoria del Problema' = \"" +ticket.getSottocategoria_problema()+"\"";
			
			QualifierInfo qual = ctx.parseQualification(form_gruppi, queryString);   
			List<Entry> entries = ctx.getListEntryObjects(form_gruppi, qual, 0, 0, null, null, false, null); 
			
			Entry entryForm = ctx.getEntry(form_gruppi, entries.get(0).getEntryId(), null); 
			
			
			logger.info("Elaborazione Ticket su remedy");
					
			ticket.setElaborato(true);

			if (enable_remedy) {
				Entry entry = new Entry();
	            
	            entry.put(intArray[0],new Value(ticket.getTipo_problema()));
				entry.put(intArray[1],new Value(ticket.getCategoria_problema()));
				entry.put(intArray[2],new Value(ticket.getSottocategoria_problema()));
				entry.put(intArray[3],new Value(ticket.getDescrizione()));
				entry.put(intArray[4],new Value(ticket.getAccount()));
				
				Value numeroGruppo = entryForm.get(112);
				entry.put(intArray[5],new Value(numeroGruppo.toString()));
				
				
				Value gruppo = entryForm.get(600030005);
				entry.put(intArray[6],new Value(gruppo.toString()));
				
	            ticket.setIdentificativo(ctx.createEntry(form, entry));  
				
				ticket.setEsito(true);
			}else {
                ticket.setIdentificativo("XXXXXX");  
				ticket.setEsito(true);
			}
            

			logger.info("Ticket nr. "+ticket.getIdentificativo()+" creato correttamente.");
			
        } catch (ARException e) {
            logger.error(e.getMessage());
            ticket.setElaborato(true);
            ticket.setErrore(e.getMessage());
            return false;
        } catch(Exception e)
		{
			logger.error("IL TASK E' FERMO!!! Errore: " + e.getMessage()+" - Chiave processo master: "+ticket.getChiave_processo_master()+". Questa terna puo' essere aperta da RCO? In tal caso, controllare la sintassi della tripletta (es: maiuscole/minuscole..).");
			// Exit
			return false;
		}
        return true;
    }
}
