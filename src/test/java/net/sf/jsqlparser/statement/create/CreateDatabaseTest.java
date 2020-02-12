/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.statement.create;

import static net.sf.jsqlparser.test.TestUtils.assertSqlCanBeParsedAndDeparsed;

import java.io.StringReader;

import org.junit.Test;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.database.CreateDatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;;

public class CreateDatabaseTest {
    
    private final CCJSqlParserManager parserManager = new CCJSqlParserManager();
    
    @Test
    public void testCreateDatabase() throws JSQLParserException {
        String statement = "CREATE DATABASE testdb";
        assertSqlCanBeParsedAndDeparsed(statement);
        
        Statement parsedStatement = parserManager.parse(new StringReader(statement));
        assertTrue("The parsed statement should be of type CreateDatabase", parsedStatement instanceof CreateDatabase);
        CreateDatabase createDB = (CreateDatabase) parsedStatement;
        assertEquals("The table should be called 'testdb' but is '" + createDB.getDatabase() + "'",  createDB.getDatabase(), "testdb");
    }
    
    @Test
    public void testCreateDatabaseIfNotExists() throws JSQLParserException {
        String statement = "CREATE DATABASE IF NOT EXISTS testdb";
        assertSqlCanBeParsedAndDeparsed(statement);
        
        Statement parsedStatement = parserManager.parse(new StringReader(statement));
        assertTrue("The parsed statement should be of type CreateDatabase", parsedStatement instanceof CreateDatabase);
        CreateDatabase createDB = (CreateDatabase) parsedStatement;
        assertEquals("The table should be called 'testdb' but is '" + createDB.getDatabase() + "'",  createDB.getDatabase(), "testdb");
        
        assertTrue("The statement should only create the database if it does not exist already", createDB.isIfNotExists());
    }
    
    @Test
    public void testCreateDatabaseDefCharSet() throws JSQLParserException {
        String statement = "CREATE DATABASE testdb DEFAULT CHARACTER SET latin1";
        assertSqlCanBeParsedAndDeparsed(statement);
        
        Statement parsedStatement = parserManager.parse(new StringReader(statement));
        assertTrue("The parsed statement should be of type CreateDatabase", parsedStatement instanceof CreateDatabase);
        CreateDatabase createDB = (CreateDatabase) parsedStatement;
        assertEquals("The table should be called 'testdb' but is '" + createDB.getDatabase() + "'",  createDB.getDatabase(), "testdb");
        
        assertEquals("The default character set should be called 'latin1' but is '" + createDB.getCharacterSet() + "'",  createDB.getCharacterSet(), "latin1");
    }
    
    @Test
    public void testCreateDatabaseCollate() throws JSQLParserException {
        String statement = "CREATE DATABASE testdb COLLATE latin1_swedish_ci";
        assertSqlCanBeParsedAndDeparsed(statement);
        
        Statement parsedStatement = parserManager.parse(new StringReader(statement));
        assertTrue("The parsed statement should be of type CreateDatabase", parsedStatement instanceof CreateDatabase);
        CreateDatabase createDB = (CreateDatabase) parsedStatement;
        assertEquals("The table should be called 'testdb' but is '" + createDB.getDatabase() + "'",  createDB.getDatabase(), "testdb");
        
        assertEquals("The collate should be called 'latin1_swedish_ci' but is '" + createDB.getCollate() + "'",  createDB.getCollate(), "latin1_swedish_ci");
    }
}

