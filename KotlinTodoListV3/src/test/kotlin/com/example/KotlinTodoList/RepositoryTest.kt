package com.example.KotlinTodoList.repositoryTest

import com.example.KotlinTodoList.config.AppConfig
import com.example.KotlinTodoList.database.Todo
import com.example.KotlinTodoList.repository.repositoryImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime


@ExtendWith(SpringExtension::class)   // 확장기능 사용
@SpringBootTest(classes = [repositoryImpl::class, AppConfig::class])  // Autowired 된 클래스들도 따로 설정해줘야함
class RepositoryTest {



    @Autowired
    lateinit var repositoryImpl: repositoryImpl

    @BeforeEach    // 스프링 테스트가 시작하기전 무조건 이메서드실행
    fun before(){
        repositoryImpl.todoDataBase.init()
    }

    @Test
    fun saveTest(){

        var data1 = Todo().apply {
            this.title = "save 테스트"
            this.description = "test"
            this.schedule = LocalDateTime.now()

        }

        val saved = repositoryImpl.save(data1)

        Assertions.assertEquals(1,saved?.index)    //saved 의 index가 1인걸 검사한다.
        Assertions.assertNotNull(data1.createdDate)
        Assertions.assertNotNull(data1.updatedDate)
    }

    @Test
    fun saveAllTest(){

        val todoList = mutableListOf(

            Todo().apply {
                this.title = "save 테스트"
                this.description = "test"
                this.schedule = LocalDateTime.now()

            },
            Todo().apply {
                this.title = "save 테스트"
                this.description = "test"
                this.schedule = LocalDateTime.now()

            },
            Todo().apply {
                this.title = "save 테스트"
                this.description = "test"
                this.schedule = LocalDateTime.now()

            }

        )

        var result = repositoryImpl.saveAll(todoList)

        Assertions.assertEquals(true, result)    // result =true 인걸 검사한다.

    }


    @Test
    fun findOneTest(){

        //repositoryImpl.todoDataBase.init()          //초기화

        val todoLists = mutableListOf(

            Todo().apply {
                this.title = "save 테스트1"
                this.description = "test"
                this.schedule = LocalDateTime.now()

            },
            Todo().apply {
                this.title = "save 테스트2"
                this.description = "test"
                this.schedule = LocalDateTime.now()

            },
            Todo().apply {
                this.title = "save 테스트3"
                this.description = "test"
                this.schedule = LocalDateTime.now()

            }

        )

        repositoryImpl.saveAll(todoLists)

        val result = repositoryImpl.findOne(2)
        println(result)


        Assertions.assertNotNull(result)
        Assertions.assertEquals("save 테스트2",result?.title)
    }

    @Test
    fun updateTest(){


    val todo1=Todo().apply {
            this.title = "save 테스트3"
            this.description = "test"
            this.schedule = LocalDateTime.now()
        }


        var savedTodo = repositoryImpl.save(todo1)


        val todo2 = Todo().apply {
            this.index = savedTodo?.index      // 인덱스같은거에다가 덮어씌우기
            this.title = "업데이트 테스트 타이틀"
            this.description = "업데이트 테스트"
            this.schedule = LocalDateTime.now()

        }

        val changedResult = repositoryImpl.save(todo2)

        Assertions.assertNotNull(changedResult)    //null이 아니여야하고
        Assertions.assertEquals("업데이트 테스트 타이틀",changedResult?.title)
        Assertions.assertEquals("업데이트 테스트",changedResult?.description)

    }

}