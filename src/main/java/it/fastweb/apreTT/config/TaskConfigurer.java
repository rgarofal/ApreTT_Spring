package it.fastweb.apreTT.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.export.MBeanExporter;

@Configuration
@Import({ DatabaseConfiguration.class, MBeanExporter.class  })
public class TaskConfigurer extends DefaultTaskConfigurer{

	public TaskConfigurer(@Qualifier("database_task") DataSource dataSource) 
    {
        super(dataSource);
    }
}
