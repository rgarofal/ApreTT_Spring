package it.fastweb.apreTT.config;

import it.fastweb.apreTT.batch.TicketProcessor;
import it.fastweb.apreTT.batch.TicketWriter;
import it.fastweb.apreTT.client.SessionRemedyClient;
import it.fastweb.apreTT.model.AperturaTicketData;
import it.fastweb.apreTT.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.transaction.*;

import javax.sql.DataSource;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Configuration
@EnableBatchProcessing
@EnableTask
@Import({DatabaseConfiguration.class,SessionRemedyConfiguration.class, MBeanExporter.class})
public class JobConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    @Qualifier("database_task")
    private DataSource dataSource_config;
    @Autowired
    @Qualifier("database_remedy")
    private DataSource dataSource_business;
    @Autowired
    private SimpleJobLauncher jobLauncher;
    @Autowired
    private SessionRemedyConfiguration sessionClient;

    private static final Logger log = LoggerFactory.getLogger(JobConfiguration.class);
    private static final JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();


    @Bean(name = "jdbcTemplate")
    JdbcTemplate jdbcTemplate(@Qualifier("database_remedy") DataSource dataSource_business) {
        return new JdbcTemplate(dataSource_business);
    }

    @Bean
    public ResourcelessTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public JobRepository jobRepository(@Qualifier("database_task") DataSource dataSource_config, @Qualifier("database_remedy") DataSource dataSource_business) throws Exception {
        jobRepositoryFactoryBean.setDataSource(dataSource_config);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager());
        jobRepositoryFactoryBean.afterPropertiesSet();
        return jobRepositoryFactoryBean.getObject();
    }

    @Bean
    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }

    @Bean
    public void runJobScheduled() throws Exception {

        log.info("Job Started at :" + new Date());

        JobParameters param = new JobParametersBuilder().addString("apreTT", String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution = jobLauncher.run(apreTT(), param);

        log.info("Job finished with status :" + execution.getStatus());
    }

    @Bean
    public Job apreTT() throws URISyntaxException {
        return jobBuilderFactory.get("apreTT")
                .incrementer(new RunIdIncrementer())
                .listener(new JobListener(sessionClient))
                .flow(step1(sessionClient))
                .end()
                .build();
    }
    
    @Bean
    public JdbcCursorItemReader<Ticket> reader(){
        JdbcCursorItemReader<Ticket> databaseReader = new JdbcCursorItemReader<Ticket>();
        databaseReader.setDataSource(dataSource_business);
        databaseReader.setSql("SELECT * FROM apre_tt_mail WHERE data_invio_mail IS NULL ORDER BY data_inserimento");
        databaseReader.setRowMapper(new DbRowMapperTicket());
        databaseReader.setMaxRows(50);
        System.out.println("Reading " + databaseReader.getSql());
        return databaseReader;
    }

    @Bean
    public Step step1(SessionRemedyConfiguration sessionClient) {
        return stepBuilderFactory.get("step1")
                .<Ticket, Ticket>chunk(1)
                .reader(reader())
                .processor(new TicketProcessor(sessionClient))
                .writer(new TicketWriter(jdbcTemplate(dataSource_business), dataSource_business))
                .startLimit(1)
                .build();
    }
}
