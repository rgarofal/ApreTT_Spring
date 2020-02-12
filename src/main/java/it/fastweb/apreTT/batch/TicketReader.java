package it.fastweb.apreTT.batch;


import it.fastweb.apreTT.config.Utility;
import it.fastweb.apreTT.config.DbRowMapper;
import it.fastweb.apreTT.config.JobListener;
import it.fastweb.apreTT.model.AperturaTicketData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import javax.sql.DataSource;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class TicketReader implements ItemReader<List<AperturaTicketData>> {

    
    private DataSource dataSource;
  
    private AperturaTicketData dati_apertura_ticket;
    private static final Logger log = LoggerFactory.getLogger(TicketReader.class);

    @Autowired
    public TicketReader(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Nullable
    @Override
    public List<AperturaTicketData> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {


        List<AperturaTicketData> exportList = new ArrayList<>();


        if (exportList.size() == 0) {
            log.info("Non ci sono Ticket da creare nel writer");
            return null;
        } else {
            exportList.forEach(export -> {
            });
            return exportList;
        }
    }


 
}
