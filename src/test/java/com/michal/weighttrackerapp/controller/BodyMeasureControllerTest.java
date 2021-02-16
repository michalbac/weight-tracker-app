package com.michal.weighttrackerapp.controller;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.michal.weighttrackerapp.domain.BodyMeasure;
import com.michal.weighttrackerapp.domain.UserAccount;
import com.michal.weighttrackerapp.repository.UserRepository;
import com.michal.weighttrackerapp.service.BodyMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BodyMeasureController.class)
@ActiveProfiles(value = "test")
public class BodyMeasureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BodyMeasureService bodyMeasureService;

    @MockBean
    private UserRepository userRepository;

    WebClient webClient;

    @BeforeEach
    void setup(WebApplicationContext context) {
        webClient = MockMvcWebClientBuilder
                .webAppContextSetup(context, springSecurity())
                .build();
    }


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
        when(userRepository.findByUserName(any())).thenReturn(user);
        HtmlPage page = webClient.getPage("http://localhost:8080/body/new");
        HtmlForm form = page.getHtmlElementById("form");
        HtmlTextInput rbInput = page.getHtmlElementById("rightBicep");
        rbInput.setValueAttribute("30");
        HtmlTextInput lbInput = page.getHtmlElementById("leftBicep");
        lbInput.setValueAttribute("30");
        HtmlTextInput waistInput = page.getHtmlElementById("waist");
        waistInput.setValueAttribute("90");
        HtmlTextInput chestInput = page.getHtmlElementById("chest");
        chestInput.setValueAttribute("100");
        HtmlTextInput ltInput = page.getHtmlElementById("leftThigh");
        ltInput.setValueAttribute("60");
        HtmlTextInput rtInput = page.getHtmlElementById("rightThigh");
        rtInput.setValueAttribute("60");
        HtmlTextInput dateInput = page.getHtmlElementById("db4");
        dateInput.setValueAttribute("2021-02-13");


        //When & Then
        HtmlButton submit = form.getButtonByName("submit");
        WebResponse response = submit.click().getWebResponse();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(page.getForms().get(0).getInputByName("leftBicep").getValueAttribute()).isEqualTo("30");
        assertThat(page.getForms().get(0).getInputByName("rightBicep").getValueAttribute()).isEqualTo("30");
        assertThat(page.getForms().get(0).getInputByName("waist").getValueAttribute()).isEqualTo("90");
        assertThat(page.getForms().get(0).getInputByName("chest").getValueAttribute()).isEqualTo("100");
        assertThat(page.getForms().get(0).getInputByName("leftThigh").getValueAttribute()).isEqualTo("60");
        assertThat(page.getForms().get(0).getInputByName("rightThigh").getValueAttribute()).isEqualTo("60");
        assertThat(page.getForms().get(0).getInputByName("dateOfMeasure").getValueAttribute()).isEqualTo("2021-02-13");
    }

    @Test
    @WithMockUser(username = "test", password = "pwd", roles = "USER")
    public void shouldReturn400() throws Exception {
        //Given
        UserAccount user = new UserAccount();
        user.setId(1);
        user.setEmail("test@com.pl");
        user.setUserName("test");
        when(userRepository.findByUserName(any())).thenReturn(user);
        HtmlPage page = webClient.getPage("http://localhost:8080/body/new");
        HtmlForm form = page.getHtmlElementById("form");
        HtmlTextInput rbInput = page.getHtmlElementById("rightBicep");
        rbInput.setValueAttribute("30");
        HtmlTextInput lbInput = page.getHtmlElementById("leftBicep");
        lbInput.setValueAttribute("30");
        HtmlTextInput waistInput = page.getHtmlElementById("waist");
        waistInput.setValueAttribute("90");
        HtmlTextInput chestInput = page.getHtmlElementById("chest");
        chestInput.setValueAttribute("100");
        HtmlTextInput ltInput = page.getHtmlElementById("leftThigh");
        ltInput.setValueAttribute("60");


        //When & Then
        HtmlButton submit = form.getButtonByName("submit");
        try {
            submit.click().getWebResponse();
        } catch (FailingHttpStatusCodeException e){
            assertTrue(e.getStatusCode()==400);
        }


    }

    @Test
    @WithMockUser(username = "test", password = "pwd", roles = "USER")
    public void shouldDeleteUser() throws Exception {
        //Given When & Then
        mockMvc.perform(get("/body/1"))
                .andExpect(redirectedUrl("/body"));
        verify(bodyMeasureService, times(1)).deleteMeasure(1);



    }

}
