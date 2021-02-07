package com.michal.weighttrackerapp.controller;

import com.michal.weighttrackerapp.domain.BodyMeasure;
import com.michal.weighttrackerapp.domain.UserAccount;
import com.michal.weighttrackerapp.repository.UserRepository;
import com.michal.weighttrackerapp.security.SecurityConfiguration;
import com.michal.weighttrackerapp.service.BodyMeasureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.validation.constraints.NotNull;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BodyMeasureController.class)
@ActiveProfiles(value = "test")
public class BodyMeasureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BodyMeasureService bodyMeasureService;

    @MockBean
    private UserRepository userRepository;


    @Test
    @WithMockUser(username = "test", password = "pwd", roles = "USER")
    public void shouldFetchEmptyList() throws Exception{
        //Given
        List<BodyMeasure> bodyMeasureList = new ArrayList<>();
        UserAccount user = new UserAccount();
        user.setId(1);
        user.setEmail("test@com.pl");
        user.setUserName("test");
        when(bodyMeasureService.getAllUserBodyMeasures(1)).thenReturn(bodyMeasureList);
        when(userRepository.findByUserName(any())).thenReturn(user);

        //When & Then
        mockMvc.perform(get("/body").contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
        .andExpect(model().attribute("measures", equalTo(bodyMeasureList)));
    }

    @Test
    @WithMockUser(username = "test", password = "pwd", roles = "USER")
    public void shouldFetchBodyMeasureList() throws Exception{
        //Given
        List<BodyMeasure> bodyMeasureList = new ArrayList<>();
        UserAccount user = new UserAccount();
        user.setId(1);
        user.setEmail("test@com.pl");
        user.setUserName("test");
        BodyMeasure bm1 = new BodyMeasure(1, 30, 30, 90, 100,
                60, 60, new Date(System.currentTimeMillis()), user);
        BodyMeasure bm2 = new BodyMeasure(2, 30.5, 30.5, 90, 101,
                61, 61, new Date(System.currentTimeMillis()), user);
        bodyMeasureList.add(bm1);
        bodyMeasureList.add(bm2);
        when(bodyMeasureService.getAllUserBodyMeasures(1)).thenReturn(bodyMeasureList);
        when(userRepository.findByUserName(any())).thenReturn(user);

        //When & Then
        mockMvc.perform(get("/body").contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(model().attribute("measures", equalTo(bodyMeasureList)))
        .andExpect(model().attribute("measures", hasSize(2)));

    }

    @Test
    @WithMockUser(username = "test", password = "pwd", roles = "USER")
    public void shouldAddMeasure() throws Exception{
        //Given
        UserAccount user = new UserAccount();
        user.setId(1);
        user.setEmail("test@com.pl");
        user.setUserName("test");
        Date date = new Date(System.currentTimeMillis());
        BodyMeasure bm1 = new BodyMeasure(1, 30, 30, 90, 100,
                60, 60, date, user);
        when(userRepository.findByUserName(any())).thenReturn(user);

        //When & Then
        String rbParamName = "rightBicep";
        String lbParamName = "leftBicep";
        String waistParamName = "waist";
        String chestParamName = "chest";
        String ltParamName = "leftThigh";
        String rtParamName = "rightThigh";
        String db4ParamName = "db4";

        mockMvc.perform(get("/body/new"))
                .andExpect(xpath("//input[@name='" + rbParamName + "']").exists())
                .andExpect(xpath("//input[@name='" + lbParamName + "']").exists())
                .andExpect(xpath("//input[@name='" + waistParamName + "']").exists())
                .andExpect(xpath("//input[@name='" + chestParamName + "']").exists())
                .andExpect(xpath("//input[@name='" + ltParamName + "']").exists())
                .andExpect(xpath("//input[@name='" + rtParamName + "']").exists())
                .andExpect(xpath("//input[@name='" + db4ParamName + "']").exists());



        MockHttpServletRequestBuilder createMeasure = post("/body/save")
                .characterEncoding("UTF-8")
                .param("rightBicep", "30")
                .param("leftBicep", "30")
                .param("waist", "90")
                .param("chest", "100")
                .param("leftThigh", "60")
                .param("rightThigh", "60")
                .param("db4", String.valueOf(date));

        mockMvc.perform(createMeasure)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/body"));

    }
}
