package org.example.TeremHMS;

import org.example.TeremHMS.controllers.MainController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
// import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin_sel")
//@TestPropertySource("/application-test.properties")
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainController controller;

    @Test
    public void getHomePageTest() throws Exception {
        this.mockMvc.perform(get("/loginPage"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='navbarSupportedContent']/div").string("admin_sel"));
    }

    @Test
    @Sql(value = {"/messages-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void addMessageToListTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/messagePage")
                .param("text", "Привет")
                .param("tag", "Выселение")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(1))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='1']").exists())
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='1']/div/span").string("Привет"))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='1']/div/i").string("Выселение"));
    }

    @Test
    public void getMessageListTest() throws Exception {
        this.mockMvc.perform(get("/messagePage"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='message-list']/div").nodeCount(1));
    }

    @Test
    public void filterMessageTest() throws Exception {
        this.mockMvc.perform(get("/messagePage").param("Привет", "Выселение"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(1))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='1']").exists());
    }
}