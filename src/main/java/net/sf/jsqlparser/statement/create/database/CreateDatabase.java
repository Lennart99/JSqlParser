/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.statement.create.database;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.StatementVisitor;

public class CreateDatabase implements Statement {

    private String database;
    
    private boolean ifNotExists = false;
    
    private String characterSet = null;
    private String collate = null;

    @Override
    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    public boolean isIfNotExists() {
        return ifNotExists;
    }

    public void setIfNotExists(boolean ifNotExists) {
        this.ifNotExists = ifNotExists;
    }
    
    public void setDatabase(String database) {
        this.database = database;
    }
    
    public String getDatabase() {
        return database;
    }
    
    public void setCharacterSet(String characterSet) {
        this.characterSet = characterSet;
    }
    
    public String getCharacterSet() {
        return characterSet;
    }
    
    public void setCollate(String collate) {
        this.collate = collate;
    }
    
    public String getCollate() {
        return collate;
    }

    @Override
    public String toString() {
        String sql = "CREATE DATABASE " + (ifNotExists ? "IF NOT EXISTS " : "") + database;
        
        if (characterSet != null) {
            sql += " DEFAULT CHARACTER SET " + characterSet;
        }
        
        if (collate != null) {
            sql += " COLLATE " + collate;
        }
        
        return sql;
    }
}
