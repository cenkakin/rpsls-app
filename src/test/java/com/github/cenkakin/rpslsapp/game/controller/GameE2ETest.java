package com.github.cenkakin.rpslsapp.game.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class GameE2ETest {

  private MockMvc mockMvc;

  @BeforeEach
  void setup(WebApplicationContext context) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  void shouldReturn200AndResultAndComputerHandWhenRequestIsValid() throws Exception {
    this.mockMvc.perform(post("/game").contentType(APPLICATION_JSON).content("{\"hand\": \"PAPER\"}"))
        .andExpect(content().string(containsString("result")))
        .andExpect(content().string(containsString("computerHand")))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturn200AndUnknownResultWhenHandHandIsUnknown() throws Exception {
    this.mockMvc.perform(post("/game").contentType(APPLICATION_JSON).content("{\"hand\": \"KICK\"}"))
        .andExpect(content().string("{\"result\":\"UNKNOWN\"}"))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturn400WhenRequestBodyIsInvalid() throws Exception {
    this.mockMvc.perform(post("/game").contentType(APPLICATION_JSON).content("invalidJson"))
        .andExpect(content().string("Request body is not valid!"))
        .andExpect(status().isBadRequest());
  }
}