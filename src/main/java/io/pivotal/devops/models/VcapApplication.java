package io.pivotal.devops.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// See https://docs.run.pivotal.io/devguide/deploy-apps/environment-variable.html#VCAP-APPLICATION
@JsonIgnoreProperties(ignoreUnknown = true)
public class VcapApplication {

    @JsonProperty("application_id")
    private String applicationId;

    @JsonProperty("application_name")
    private String applicationName;

    @JsonProperty("application_uris")
    private List<String> applicationUris;

    @JsonProperty("application_version")
    private String applicationVersion;

    @JsonProperty("instance_id")
    private String instanceId;

    @JsonProperty("instance_index")
    private int instanceIndex;

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setApplicationUris(List<String> applicationUris) {
        this.applicationUris = applicationUris;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public void setInstanceIndex(int instanceIndex) {
        this.instanceIndex = instanceIndex;
    }

    @Override
    public String toString() {
        return
                "applicationId='" + applicationId + "'\n" +
                "applicationName='" + applicationName + "'\n" +
                "applicationUris='" + applicationUris + "'\n" +
                "applicationVersion='" + applicationVersion + "'\n" +
                "instanceId='" + instanceId + "'\n" +
                "instanceIndex='" + instanceIndex;
    }
}
