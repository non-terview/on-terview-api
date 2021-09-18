package site.askephoenix.restapi.resolver;

import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import site.askephoenix.restapi.user.model.UserInfo;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class UserHandlerMethodArgumentResolverTest {

    private final UserHandlerMethodArgumentResolver userHandlerMethodArgumentResolver = new UserHandlerMethodArgumentResolver();


    @Test
    void supportsParameter() throws NoSuchMethodException {
        Class<? extends UserHandlerMethodArgumentResolver> aClass = userHandlerMethodArgumentResolver.getClass();
        Method method = aClass.getDeclaredMethod("supportsParameter", UserInfo.class);
        MethodParameter returnType = new MethodParameter(method, 0);
        assertTrue(userHandlerMethodArgumentResolver.supportsParameter(returnType));
    }

    /*@Test
    void resolveArgument() {
        fail();
    }*/

}