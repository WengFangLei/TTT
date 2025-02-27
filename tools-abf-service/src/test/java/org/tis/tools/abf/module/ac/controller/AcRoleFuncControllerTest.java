package org.tis.tools.abf.module.ac.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.tis.tools.abf.base.BaseTest;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AcRoleFuncControllerTest extends BaseTest {

    @Test
    public void add() throws Exception{
        RequestBuilder request ;
        request = post("/acRoleFuncs/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"guidRole\":\"1111\",\"guidFunc\": \"22\", \"guidApp\": \"22\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception{
        RequestBuilder request ;
        request = put("/acRoleFuncs")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"guidRole\":\"1111\",\"guidFunc\": \"2222\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void delete() throws Exception{
        RequestBuilder request ;
        request = post("/acRoleFuncs/1111")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"guidRole\":\"1111\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void detail() throws Exception{

        RequestBuilder request ;
        request = get("/acRoleFuncs/1111")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"guidRole\":\"1111\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void list() throws Exception{
        RequestBuilder request ;
        request = post("/acRoleFuncs/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{" +
                        "\" page\":{\"current\":\"0\",\"size\": \"10\",\"orderByField\":\"guidRole\"}}," +
                        "\"condition\":{\"guidRole\":\"1111\"}" +
                        "}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void removeSomeRoleFunc() throws Exception{
        RequestBuilder request ;
        request = MockMvcRequestBuilders.delete("/acRoleFuncs/removeSomeRoleFunc")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"guidRole\":\"1111\",\"guidFunc\":\"11\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }


}