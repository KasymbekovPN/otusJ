package ru.otus.kasymbekovPN.HW09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.kasymbekovPN.HW09.h2.DataSourceH2Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Testing of DataSourceH2Impl methods")
class DataSourceH2ImplTest {

    private static Object[][] getDataForTestGetConnectionWithoutArgs(){
        return new Object[][]{
                {"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"}
        };
    }

    @DisplayName("getConnection without args")
    @ParameterizedTest
    @MethodSource("getDataForTestGetConnectionWithoutArgs")
    void testGetConnectionWithoutArgs(String url) throws SQLException {

        Connection conn0 = DriverManager.getConnection(url);
        conn0.setAutoCommit(false);
        Connection conn1 = new DataSourceH2Impl().getConnection();

        assertThat(extractUrl(conn0)).isEqualTo(extractUrl(conn1));
    }

    @DisplayName("getConnection with args")
    @Test
    void testGetConnectionWithArgs() {
        assertThatThrownBy(()->{
            new DataSourceH2Impl().getConnection("", "");
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("getLogWriter")
    @Test
    void testGetLogWriter(){
        assertThatThrownBy(()->{
            new DataSourceH2Impl().getLogWriter();
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("getLoginTimeout")
    @Test
    void testGetLoginTimeout(){
        assertThatThrownBy(()->{
            new DataSourceH2Impl().getLoginTimeout();
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("setLoginTimeout")
    @Test
    void testSetLoginTimeout(){
        assertThatThrownBy(()->{
            new DataSourceH2Impl().setLoginTimeout(0);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("unwrap")
    @Test
    void testUnwrap(){
        assertThatThrownBy(()->{
            new DataSourceH2Impl().unwrap(Object.class);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("isWrapperFor")
    @Test
    void testIsWrapperFor(){
        assertThatThrownBy(()->{
            new DataSourceH2Impl().isWrapperFor(Object.class);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("getParentLogger")
    @Test
    void testGetParentLogger(){
        assertThatThrownBy(()->{
            new DataSourceH2Impl().getParentLogger();
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    private String extractUrl(Connection connection){
        String sConn = String.valueOf(connection);
        String[] sArr = sConn.split(" ");
        sConn = sArr[1].split("=")[1];

        return sConn;
    }
}
