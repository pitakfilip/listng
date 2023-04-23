package sk.posam.risng.mur.ui;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * trieda, ktora na jmx vystrkuje informacie o aplikacii ako verzia aplikacie, git commit id, zdravie a podobne
 *
 * @author rucka
 */
public class HealthCheckMBean {

    /**
     * version of module
     */
    private String mavenVersion;
    /**
     * project name, this is the same as properties folder on web app server
     */
    private String mavenProjectName;

    /**
     * version control system information about commit id, user, message, time and so on...
     */
    private String vcsCommitCount;
    private String vcsCommitId;
    private String vcsCommitUserName;
    private String vcsCommitMessage;
    private String vcsCommitTime;
    private String vcsBranch;

    private String vcsBuildTime;

    private String springProfile;

    /**
     * if module contains some services, this property has their state, in cxf case will be enum @BusState
     */
    private Object serviceState;

    private String logDir;
    private String propertiesDir;

    public String getMavenVersion() {
        return mavenVersion;
    }

    public void setMavenVersion(String mavenVersion) {
        this.mavenVersion = mavenVersion;
    }

    public String getMavenProjectName() {
        return mavenProjectName;
    }

    public void setMavenProjectName(String mavenProjectName) {
        this.mavenProjectName = mavenProjectName;
    }

    public String getVcsCommitCount() {
        return vcsCommitCount;
    }

    public void setVcsCommitCount(String vcsCommitCount) {
        this.vcsCommitCount = vcsCommitCount;
    }

    public String getVcsCommitId() {
        return vcsCommitId;
    }

    public void setVcsCommitId(String vcsCommitId) {
        this.vcsCommitId = vcsCommitId;
    }

    public String getVcsCommitUserName() {
        return vcsCommitUserName;
    }

    public void setVcsCommitUserName(String vcsCommitUserName) {
        this.vcsCommitUserName = vcsCommitUserName;
    }

    public String getVcsCommitMessage() {
        return vcsCommitMessage;
    }

    public void setVcsCommitMessage(String vcsCommitMessage) {
        this.vcsCommitMessage = vcsCommitMessage;
    }

    public String getVcsCommitTime() {
        return vcsCommitTime;
    }

    public void setVcsCommitTime(String vcsCommitTime) {
        this.vcsCommitTime = vcsCommitTime;
    }

    public String getVcsBranch() {
        return vcsBranch;
    }

    public void setVcsBranch(String vcsBranch) {
        this.vcsBranch = vcsBranch;
    }

    public String getVcsBuildTime() {
        return vcsBuildTime;
    }

    public void setVcsBuildTime(String vcsBuildTime) {
        this.vcsBuildTime = vcsBuildTime;
    }

    public String getSpringProfile() {
        return springProfile;
    }

    public void setSpringProfile(String springProfile) {
        this.springProfile = springProfile;
    }

    public Object getServiceState() {
        return serviceState != null ? serviceState.toString() : null;
    }

    public void setServiceState(Object serviceState) {
        this.serviceState = serviceState;
    }

    public String getLogDir() {
        return logDir;
    }

    public void setLogDir(String logDir) {
        this.logDir = logDir;
    }

    public String getPropertiesDir() {
        return propertiesDir;
    }

    public void setPropertiesDir(String propertiesDir) {
        this.propertiesDir = propertiesDir;
    }

    public Map toMap(ObjectMapper objectMapper) {
        Map<String, Object> result = objectMapper.convertValue(this, Map.class);
        return result.entrySet().stream().filter(entry -> entry.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
