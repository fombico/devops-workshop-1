package io.pivotal.devops;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pivotal.devops.models.VcapApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class VcapApplicationProvider {

    private ObjectMapper objectMapper;

    @Autowired
    VcapApplicationProvider(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    VcapApplication getVcapApplication() {
        try {
            return objectMapper.readValue(System.getenv("VCAP_APPLICATION"), VcapApplication.class);
        } catch (Exception e) {
            return null;
        }
    }
}
