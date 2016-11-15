package io.pivotal.devops;

import io.pivotal.devops.models.VcapApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ApiController {

    private VcapApplicationProvider vcapApplicationProvider;

    @Autowired
    public ApiController(VcapApplicationProvider vcapApplicationProvider) {
        this.vcapApplicationProvider = vcapApplicationProvider;
    }

    @RequestMapping(path = "/info", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getVcapInfo() throws IOException {
        VcapApplication vcapApplication = vcapApplicationProvider.getVcapApplication();
        if (vcapApplication != null) {
            return vcapApplication.toString();
        } else {
            return "VCAP is missing";
        }
    }
}
