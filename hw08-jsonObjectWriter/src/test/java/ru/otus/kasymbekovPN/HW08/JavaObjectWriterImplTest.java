package ru.otus.kasymbekovPN.HW08;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.kasymbekovPN.HW08.experimentVictims.EV1;
import ru.otus.kasymbekovPN.HW08.javaObjectWriter.JavaObjectWriterImpl;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест json-парсера")
class JavaObjectWriterImplTest {

    @DisplayName("проверка: корневой элемент - объект со вложенностью")
    @Test
    void test() throws NoSuchFieldException, IllegalAccessException {
        EV1 original = new EV1();
        String jsonString = new JavaObjectWriterImpl(original).getObjectAsJsonStr();
        EV1 restored = new Gson().fromJson(jsonString, EV1.class);

        assertEquals(original, restored);
    }

    @DisplayName("проверка: простых элементов, строк, коллекций")
    @Test
    void customTest() throws NoSuchFieldException, IllegalAccessException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        assertEquals(gson.toJson(null), new JavaObjectWriterImpl(null).getObjectAsJsonStr());
        assertEquals(gson.toJson((byte)1), new JavaObjectWriterImpl((byte)1).getObjectAsJsonStr());
        assertEquals(gson.toJson((short)1f), new JavaObjectWriterImpl((short)1f).getObjectAsJsonStr());
        assertEquals(gson.toJson(1), new JavaObjectWriterImpl(1).getObjectAsJsonStr());
        assertEquals(gson.toJson(1L), new JavaObjectWriterImpl(1L).getObjectAsJsonStr());
        assertEquals(gson.toJson(1f), new JavaObjectWriterImpl(1f).getObjectAsJsonStr());
        assertEquals(gson.toJson(1d), new JavaObjectWriterImpl(1d).getObjectAsJsonStr());
        assertEquals(gson.toJson("aaa"), new JavaObjectWriterImpl("aaa").getObjectAsJsonStr());
        assertEquals(gson.toJson('a'), new JavaObjectWriterImpl('a').getObjectAsJsonStr());
        assertEquals(gson.toJson(new int[] {1, 2, 3}), new JavaObjectWriterImpl(new int[] {1, 2, 3}).getObjectAsJsonStr());
        assertEquals(gson.toJson(List.of(1, 2 ,3)), new JavaObjectWriterImpl(List.of(1, 2 ,3)).getObjectAsJsonStr());
        assertEquals(gson.toJson(Collections.singletonList(1)), new JavaObjectWriterImpl(Collections.singletonList(1)).getObjectAsJsonStr());
    }
}
