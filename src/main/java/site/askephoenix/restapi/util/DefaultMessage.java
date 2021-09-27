package site.askephoenix.restapi.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.HashMap;

public class DefaultMessage {
    final static HashMap<Integer, String> NumberMessage = Maps.newHashMap(
            ImmutableMap.<Integer, String>builder()
                    .put(-1, "not login")
                    .put(0, "not found")
                    .put(1, "success")
                    .put(2, "not validate")
                    .put(3, "error")
                    .put(4, "exists value")   // 값이 존재하는지
                    .build()
    );
    public static String getMessage(Integer value) {
        return NumberMessage.get(value);
    }
}
