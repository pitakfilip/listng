package sk.fmfi.listng.consuldevelop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Implements waiting for Consul cluster leader election end
 */
@Component
public class ConsulLeaderElection {

    private static final Logger logger = LoggerFactory.getLogger(ConsulLeaderElection.class);

    @Value("${consul.infoUrl}")
    String infoUrl;

    private URL siteURL;

    @PostConstruct
    public void initUrl() throws MalformedURLException {
        siteURL = new URL(this.infoUrl);
    }

    private boolean checkLeaderElected() throws IOException {
        HttpURLConnection connection = createInfoRequest();

        String response = new BufferedReader(new InputStreamReader(connection.getInputStream()))
                .lines()
                .collect(Collectors.joining(""));

        String leader = JsonPath.read(response, "$.Stats.consul.leader");

        return "true".equals(leader);
    }

    public void waitUntilLeaderElected() {
        boolean leaderElected = false;

        while (!leaderElected) {
            try {
                leaderElected = checkLeaderElected();
                if (!leaderElected) {
                    logger.debug("Waiting for Consul cluster leader elected.");
                    Thread.sleep(1000);
                }
            }
            catch (Exception e) {
                logger.debug("Expected exception during waiting.", e);
                logger.debug("Waiting for Consul cluster leader elected.");
            }
        }
        logger.info("Consul cluster leader was elected");
    }

    private HttpURLConnection createInfoRequest() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);
        connection.connect();

        return connection;
    }
}
