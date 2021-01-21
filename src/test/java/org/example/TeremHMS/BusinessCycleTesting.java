package org.example.TeremHMS;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("zaychik_user")
public class BusinessCycleTesting {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void starterPageTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TeremHMS")));
    }

    @Test
    @Sql(value = {"/messages-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void addMessageToSettling() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/messagePage")
                .param("text", "Привет! Я зайка-попрыгайка, хочу жить у вас с 21.01.2021 до 23.01.2021")
                .param("tag", "Поселение")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(1))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='1']").exists())
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='1']/div/span").
                        string("Привет! Я зайка-попрыгайка, хочу жить у вас с 21.01.2021 до 23.01.2021"))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='1']/div/i").
                        string("Поселение"));
    }

    @Test
    @Sql(value = {"/messages-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void addMessageToRenewal() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/messagePage")
                .param("text", "Я зайка-попрыгайка, хочу продлить жилье с 23.01.2021 на 25.01.2021")
                .param("tag", "Продление")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(1))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='1']").exists())
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='1']/div/span").
                        string("Я зайка-попрыгайка, хочу продлить жилье с 23.01.2021 на 25.01.2021"))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='1']/div/i").
                        string("Продление"));
    }

    @Test
    @Sql(value = {"/messages-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void addMessageToEviction() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/messagePage")
                .param("text", "Я зайка-попрыгайка, прошу выселить меня 24.01.2021")
                .param("tag", "Выселение")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(1))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='1']").exists())
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='1']/div/span").
                        string("Я зайка-попрыгайка, прошу выселить меня 24.01.2021"))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='1']/div/i").
                        string("Выселение"));
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
        this.mockMvc.perform(get("/messagePage").
                param("Я зайка-попрыгайка, прошу выселить меня 24.01.2021", "Выселение"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(1))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='1']").exists());
    }

    @Test
    public void getUsersList() throws Exception {
        this.mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Список пользователей")));
    }
}
