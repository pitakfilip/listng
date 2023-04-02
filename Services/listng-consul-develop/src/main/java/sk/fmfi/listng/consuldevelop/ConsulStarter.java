package sk.fmfi.listng.consuldevelop;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ConsulStarter {

    private static final Logger logger = LoggerFactory.getLogger(ConsulStarter.class);

    @Value("${consul.executable.filename}")
    String consulExecutablePathInClasspath;

    @Value("${consul.executable.command-lineArguments.start}")
    String startArguments;

    @Value("${consul.executable.command-lineArguments.stop}")
    String stopArguments;

    @Inject
    private ConsulLeaderElection leaderElection;

    /**
     * Launches Consul server on localhost.
     * Method blocks until Consul server elects new leader.
     * Returns launched Consul as subprocess
     */
    public Process start() throws IOException {
        Process process = startConsul();
        waitUntilLeaderElected();
        addShutDownHook();

        return process;
    }

    private void addShutDownHook() {
        Thread shutDownHook = new Thread(() -> {
            try {
                String stopCommand = createStopCommand();
                Runtime.getRuntime().exec(stopCommand);
            } catch (IOException e) {
                logger.error("Nastala chyba pr vypinani Consulu. Treba zakillovat proces beziaceho Consulu rucne.", e);
            }
        });

        Runtime.getRuntime().addShutdownHook(shutDownHook);
    }

    private Process startConsul() throws IOException {
        String command = createStartCommand();
        Process process = Runtime.getRuntime().exec(command);
        StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), logger::info);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        return process;
    }

    private String createStartCommand() throws IOException {
        return "cmd /c " + getExecutablePath() + " " + startArguments;
    }

    private String createStopCommand() throws IOException {
        return "cmd /c " + getExecutablePath() + " " + stopArguments;
    }

    private String getExecutablePath() throws IOException {
        ClassPathResource resource = new ClassPathResource(consulExecutablePathInClasspath);
        return Paths.get(resource.getURI()).toString();
    }

    private void waitUntilLeaderElected() {
        leaderElection.waitUntilLeaderElected();
    }

}
