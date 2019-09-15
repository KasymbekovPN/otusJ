package ru.otus.kasymbekovPN.HW08;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import ru.otus.kasymbekovPN.HW08.experimentVictims.EV1;
import ru.otus.kasymbekovPN.HW08.javaObjectWriter.JavaObjectWriterImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaObjectWriterImplTest {

    @Test
    void test() throws NoSuchFieldException, IllegalAccessException {
        EV1 original = new EV1();
        String jsonString = new JavaObjectWriterImpl(original).getObjectAsJsonStr();
        EV1 restored = new Gson().fromJson(jsonString, EV1.class);

        assertEquals(original, restored);
    }

}
