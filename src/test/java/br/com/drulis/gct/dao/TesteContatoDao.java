/**
 * 
 */
package br.com.drulis.gct.dao;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Teste da classe ContatoDao
 * @author Victor Drulis Oliveira
 * @since 2 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TesteContatoDao {
    @Mock
    DataSource mockDataSource;
    @Mock
    Connection mockConn;
    @Mock
    PreparedStatement mockPreparedStmnt;
    @Mock
    ResultSet mockResultSet;
    int ContatoId = 100;

    public TesteContatoDao() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SQLException {
        when(mockDataSource.getConnection()).thenReturn(mockConn);
        when(mockDataSource.getConnection(anyString(), anyString())).thenReturn(mockConn);
        doNothing().when(mockConn).commit();
        when(mockConn.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStmnt);
        doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
        when(mockPreparedStmnt.execute()).thenReturn(Boolean.TRUE);
        when(mockPreparedStmnt.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
//        when(mockResultSet.getInt(Fields.GENERATED_KEYS)).thenReturn(ContatoId);
    }

    @After
    public void tearDown() {}

    @Test
    public void testCreateWithNoExceptions() throws SQLException {

//        ContatoDao instance = new ContatoDao(mockDataSource);
//        instance.create(new Contato());

        //verify and assert
        verify(mockConn, times(1)).prepareStatement(anyString(), anyInt());
        verify(mockPreparedStmnt, times(6)).setString(anyInt(), anyString());
        verify(mockPreparedStmnt, times(1)).execute();
        verify(mockConn, times(1)).commit();
        verify(mockResultSet, times(2)).next();
//        verify(mockResultSet, times(1)).getInt(Fields.GENERATED_KEYS);
    }

    @Test(expected = SQLException.class)
    public void testCreateWithPreparedStmntException() throws SQLException {

         //mock
         when(mockConn.prepareStatement(anyString(), anyInt())).thenThrow(new SQLException());

/*
        try {
            ContatoDao instance = new ContatoDao(mockDataSource);
            instance.create(new Contato());
        } catch (SQLException se) {
            //verify and assert
            verify(mockConn, times(1)).prepareStatement(anyString(), anyInt());
            verify(mockPreparedStmnt, times(0)).setString(anyInt(), anyString());
            verify(mockPreparedStmnt, times(0)).execute();
            verify(mockConn, times(0)).commit();
            verify(mockResultSet, times(0)).next();
            verify(mockResultSet, times(0)).getInt(Fields.GENERATED_KEYS);
            throw se;
        }
*/
    }
}
