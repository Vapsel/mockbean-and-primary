package com.example.mockbeanandprimary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SomeControllerTest {
    // suppose SomeController is really hard to instantiate
    @Autowired
    private SomeController sut;

    // the following naive approach fails to load the application context
    // as @MockBean does not know which one to mock (does not check for @Primary trait)
//    @MockBean
//    private SomeService someService;

    // the following also fails to load the application context
    // as the mocked bean has lost its @Primary trait,
    // and thus SomeController cannot be autowired
//    @MockBean(name = "someServiceImpl")
//    private SomeService someServiceMock;
    // the following variant also fails for the same reason:
//    @MockBean
//    private SomeServiceImpl someServiceMock;

    // very clumsy alternative, in conjunction with TestConfiguration
    @Autowired
    private SomeService someServiceMock;

    @TestConfiguration
    public static class SomeControllerTestConfiguration {

        // manually insert mocked primary service
        @Bean("someServiceImpl")
        @Primary
        public static SomeService someService() {
            return Mockito.mock(SomeService.class);
        }
    }

    // just some stupid test
    @Test
    public void someTest() throws Exception {
        sut.toString();
        // may use someServiceMock here now with Mockito
    }


}