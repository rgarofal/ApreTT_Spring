package it.fastweb.apreTT.client;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aprett-conf")
public class DataClient {

    //configurazione db task data flow
    private String url_config;
    private String username_config;
    private String password_config;
    private String schema_config;

    //configurazione db remedy
    private String url_tts;
    private String username_tts;
    private String password_tts;
    private String schema_tts;


    public String getUrl_config() {
        return url_config;
    }

    public void setUrl_config(String url_config) {
        this.url_config = url_config;
    }

    public String getUsername_config() {
        return username_config;
    }

    public void setUsername_config(String username_config) {
        this.username_config = username_config;
    }

    public String getPassword_config() {
        return password_config;
    }

    public void setPassword_config(String password_config) {
        this.password_config = password_config;
    }

    public String getSchema_config() {
        return schema_config;
    }

    public void setSchema_config(String schema_config) {
        this.schema_config = schema_config;
    }

	public String getUsername_tts() {
		return username_tts;
	}

	public void setUsername_tts(String username_tts) {
		this.username_tts = username_tts;
	}

	public String getUrl_tts() {
		return url_tts;
	}

	public void setUrl_tts(String url_tts) {
		this.url_tts = url_tts;
	}

	public String getPassword_tts() {
		return password_tts;
	}

	public void setPassword_tts(String password_tts) {
		this.password_tts = password_tts;
	}

	public String getSchema_tts() {
		return schema_tts;
	}

	public void setSchema_tts(String schema_tts) {
		this.schema_tts = schema_tts;
	}

   
}
