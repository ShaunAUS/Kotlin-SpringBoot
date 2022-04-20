package com.example.mvc.controller.exception

import com.example.mvc.model.Human
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jayway.jsonpath.JsonPath
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.util.LinkedMultiValueMap
import java.time.LocalDateTime


@WebMvcTest   // 스프링의 전체기능을 사용하는게아닌 mvc 기능만 사용
@AutoConfigureMockMvc
class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc:MockMvc

    @Test
    fun helloTest(){

        mockMvc.perform(

            //요청  = postman
            MockMvcRequestBuilders.get("/api/exception/hello")  //요청 api
        ).andExpect(
            MockMvcResultMatchers.status().isOk                            //예상되는 hhtpStatus
        ).andExpect(
            MockMvcResultMatchers.content().string("강제예외발생")  //예상되는 return 값
        ).andDo(MockMvcResultHandlers.print())}                             //테스트 결과 출력




    @Test
    fun getTest(){

        val queryParam = LinkedMultiValueMap<String,String>()
        queryParam.add("name","min")
        queryParam.add("age","20")


        mockMvc.perform(

            MockMvcRequestBuilders.get("/api/exception/bye").queryParams(queryParam)

        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.content().string("min20")
        ).andDo(MockMvcResultHandlers.print())


    }

    @Test
    fun getFailTest(){

        val queryParam = LinkedMultiValueMap<String,String>()
        queryParam.add("name","min")
        queryParam.add("age","9")


        mockMvc.perform(

            MockMvcRequestBuilders.get("/api/exception/bye").queryParams(queryParam)

        ).andExpect(
            MockMvcResultMatchers.status().isBadRequest
        ).andExpect(
            MockMvcResultMatchers.content().string("min20")
        )/*.andExpect(
           jsonPath("\$.result_code").value("Fail")    //  Json 값
        )*/.andDo(MockMvcResultHandlers.print())


    }

    @Test
    fun post(){


        val sample = Human().apply {

            this.name = "shaun"
            this.age = "20"
            this.address = "seoul"
            this.email = "naver@naver.com"
            this.phoneNumber = "010-112-3334"
            this.createdAt = "2020-10-02 13:00:00"


        }

        //오브젝트를 ->json으로 바꿔준다.
        val json = jacksonObjectMapper().writeValueAsString(sample)


        mockMvc.perform(

            MockMvcRequestBuilders.post("/api/exception/post-test")
                .content(json)   // json 타입으로 온걸 일일이 다적을수 없으니 objectMapper사용
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andDo(MockMvcResultHandlers.print())
    }
    }





