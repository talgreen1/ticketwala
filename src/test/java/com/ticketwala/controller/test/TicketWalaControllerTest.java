package com.ticketwala.controller.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketwala.TicketWalaWebApplication;
import com.ticketwala.command.input.CreateMovieShowInput;
import com.ticketwala.dao.api.DataAccessService;
import com.ticketwala.dao.impl.DataAccessServiceImpl;
import com.ticketwala.model.MovieShow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TicketWalaWebApplication.class)
@AutoConfigureMockMvc
public class TicketWalaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private void prepareDatabase() {
        DataAccessService das = new DataAccessServiceImpl();
        das.deleteAllMovieShows();
        das.createMovieShow(new MovieShow("12345", "Star Wars III", LocalDateTime.now(), 120));
        das.createMovieShow(new MovieShow("12346", "Star Wars V", LocalDateTime.now(), 120));
    }

    @Test
    public void testGetMovieShow() throws Exception {
        prepareDatabase();

        mockMvc.perform(get("/movie_shows/12345"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", equalTo("12345")))
                .andExpect(jsonPath("movieName", equalTo("Star Wars III")));
    }

    @Test
    public void testGetMovieShows() throws Exception {
        prepareDatabase();

        mockMvc.perform(get("/movie_shows"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id", hasItems("12346", "12345")));
    }

    @Test
    public void testCreateMovieShow() throws Exception {
        ObjectMapper om = new ObjectMapper();
        CreateMovieShowInput movieShow = new CreateMovieShowInput("Superman", 87, "2017-12-31T18:09");
        prepareDatabase();

        mockMvc.perform(post("/movie_shows").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(movieShow)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}
