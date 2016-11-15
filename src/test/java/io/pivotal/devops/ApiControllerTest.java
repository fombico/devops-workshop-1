package io.pivotal.devops;

import io.pivotal.devops.models.VcapApplication;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ApiControllerTest {

    @Test
    public void whenVcapAvailable_thenReturnsSystemInfo() throws Exception {
        VcapApplication vcapApplication = new VcapApplication();
        vcapApplication.setApplicationId("id");
        vcapApplication.setApplicationName("name");
        vcapApplication.setApplicationUris(Collections.singletonList("uris"));
        vcapApplication.setApplicationVersion("version");
        vcapApplication.setInstanceId("instanceId");
        vcapApplication.setInstanceIndex(1);

        VcapApplicationProvider vcapApplicationProvider = mock(VcapApplicationProvider.class);
        when(vcapApplicationProvider.getVcapApplication()).thenReturn(vcapApplication);

        ApiController apiController = new ApiController(vcapApplicationProvider);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
        String response = mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(vcapApplication.toString());
    }

    @Test
    public void whenVcapUnavailable_thenVcapIsMissing() throws Exception {
        VcapApplicationProvider vcapApplicationProvider = mock(VcapApplicationProvider.class);

        ApiController apiController = new ApiController(vcapApplicationProvider);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
        String response = mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo("VCAP is missing");
    }


}