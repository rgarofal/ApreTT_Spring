package it.fastweb.apreTT.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import it.fastweb.apreTT.client.DataClient;

public class DatabaseConfiguration {

    private DataClient dataClient;

    @Autowired
    public DatabaseConfiguration(DataClient dataClient) {
        this.dataClient = dataClient;
    }

    @Bean(name= "database_task")
    @Primary
    public DataSource taskDataSource() throws SQLException {

        final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new com.mysql.cj.jdbc.Driver());
        dataSource.setUrl(dataClient.getUrl_config());
        dataSource.setUsername(dataClient.getUsername_config());
        dataSource.setPassword(dataClient.getPassword_config());
        dataSource.setSchema(dataClient.getSchema_config());
        return dataSource;
    }

    @Bean(name= "database_remedy")
    public DataSource tmmDataSource() throws SQLException {

        final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new com.mysql.cj.jdbc.Driver());
        dataSource.setUrl(dataClient.getUrl_tts());
        dataSource.setUsername(dataClient.getUsername_tts());
        dataSource.setPassword(dataClient.getPassword_tts());
        dataSource.setSchema(dataClient.getSchema_tts());
        return dataSource;
    }
}
