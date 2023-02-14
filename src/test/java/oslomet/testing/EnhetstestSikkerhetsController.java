package oslomet.testing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.mock.web.MockHttpSession;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestSikkerhetsController {

    @InjectMocks
    // denne skal testes
    private Sikkerhet sikkerhetsController;

    @Mock
    // denne skal Mock'es
    private BankRepository repository;

    @Mock
    // denne skal Mock'es
    private MockHttpSession session;

    @Before
    public void initSession(){
        Map<String,Object> attributes = new HashMap<>();

        doAnswer(new Answer<Object>(){
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable{
                String key = (String) invocation.getArguments()[0];
                return attributes.get(key);
            }
        }).when(session).getAttribute(anyString());

        doAnswer(new Answer<Object>(){
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable{
                String key = (String) invocation.getArguments()[0];
                Object value = invocation.getArguments()[1];
                attributes.put(key,value);
                return attributes.get(key);
            }
        }).when(session).setAttribute(anyString(),any());
    }

    @Test
    public void test_sjekkLoggetInn() {

        // arrange
        when(repository.sjekkLoggInn(anyString(),anyString())).thenReturn("OK");

        // setningen under setter ikke attributten, dvs. at det ikke er mulig å sette en attributt i dette oppsettet
        session.setAttribute("Innlogget", "12345678901");

        // act
        String resultat = sikkerhetsController.sjekkLoggInn("12345678901","HeiHeiHei");

        // assert
        assertEquals("OK", resultat);
    }



    @Test
    public void test_loggUt(){

        // arrange
        session.setAttribute("Innlogget", "09876543210");

        // act
        sikkerhetsController.loggUt();
        String resultat = (String) session.getAttribute("Innlogget");

        // assert
        assertNull(resultat);
    }

}
