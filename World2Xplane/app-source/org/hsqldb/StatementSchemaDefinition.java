package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.result.Result;

public class StatementSchemaDefinition extends StatementSchema {
  StatementSchema[] statements;
  
  StatementSchemaDefinition(StatementSchema[] paramArrayOfStatementSchema) {
    super(64, 2001);
    this.statements = paramArrayOfStatementSchema;
  }
  
  public Result execute(Session paramSession) {
    Result result;
    try {
      result = getResult(paramSession);
    } catch (Throwable throwable) {
      result = Result.newErrorResult(throwable, null);
    } 
    if (result.isError())
      result.getException().setStatementType(this.group, this.type); 
    return result;
  }
  
  Result getResult(Session paramSession) {
    HsqlNameManager.HsqlName hsqlName1 = this.statements[0].getSchemaName();
    if (this.isExplain)
      return Result.newSingleColumnStringResult("OPERATION", describe(paramSession)); 
    Result result = this.statements[0].execute(paramSession);
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    StatementSchema statementSchema = new StatementSchema(null, 1201);
    if (this.statements.length == 1 || result.isError())
      return result; 
    HsqlNameManager.HsqlName hsqlName2 = paramSession.getCurrentSchemaHsqlName();
    byte b;
    for (b = 1; b < this.statements.length; b++) {
      try {
        paramSession.setSchema(hsqlName1.name);
      } catch (HsqlException hsqlException) {}
      this.statements[b].setSchemaHsqlName(hsqlName1);
      paramSession.parser.reset(this.statements[b].getSQL());
      try {
        StatementSchema statementSchema1;
        HsqlNameManager.HsqlName hsqlName;
        Table table;
        paramSession.parser.read();
        switch (this.statements[b].getType()) {
          case 48:
          case 49:
            result = this.statements[b].execute(paramSession);
            break;
          case 77:
            statementSchema1 = paramSession.parser.compileCreate();
            statementSchema1.isSchemaDefinition = true;
            statementSchema1.setSchemaHsqlName(hsqlName1);
            if (paramSession.parser.token.tokenType != 872)
              throw paramSession.parser.unexpectedToken(); 
            statementSchema1.isLogged = false;
            result = statementSchema1.execute(paramSession);
            hsqlName = ((Table)statementSchema1.arguments[0]).getName();
            table = (Table)paramSession.database.schemaManager.getSchemaObject(hsqlName);
            hsqlArrayList.addAll((Collection)statementSchema1.arguments[1]);
            ((HsqlArrayList)statementSchema1.arguments[1]).clear();
            statementSchema.sql = table.getSQL();
            statementSchema.execute(paramSession);
            break;
          case 8:
          case 10:
          case 61:
          case 83:
          case 133:
            result = this.statements[b].execute(paramSession);
            break;
          case 14:
          case 23:
          case 80:
          case 84:
          case 1073:
            statementSchema1 = paramSession.parser.compileCreate();
            statementSchema1.isSchemaDefinition = true;
            statementSchema1.setSchemaHsqlName(hsqlName1);
            if (paramSession.parser.token.tokenType != 872)
              throw paramSession.parser.unexpectedToken(); 
            result = statementSchema1.execute(paramSession);
            break;
          case 6:
          case 52:
          case 79:
          case 114:
          case 117:
            throw paramSession.parser.unsupportedFeature();
          default:
            throw Error.runtimeError(201, "");
        } 
        if (result.isError())
          break; 
      } catch (HsqlException hsqlException) {
        result = Result.newErrorResult(hsqlException, this.statements[b].getSQL());
        break;
      } 
    } 
    if (!result.isError())
      try {
        for (b = 0; b < hsqlArrayList.size(); b++) {
          Constraint constraint = (Constraint)hsqlArrayList.get(b);
          Table table = paramSession.database.schemaManager.getUserTable(paramSession, constraint.core.refTableName);
          ParserDDL.addForeignKey(paramSession, table, constraint, (HsqlArrayList)null);
          statementSchema.sql = constraint.getSQL();
          statementSchema.execute(paramSession);
        } 
      } catch (HsqlException hsqlException) {
        result = Result.newErrorResult(hsqlException, this.sql);
      }  
    if (result.isError())
      try {
        paramSession.database.schemaManager.dropSchema(paramSession, hsqlName1.name, true);
        paramSession.database.logger.writeOtherStatement(paramSession, getDropSchemaStatement(hsqlName1));
      } catch (HsqlException hsqlException) {} 
    paramSession.setCurrentSchemaHsqlName(hsqlName2);
    return result;
  }
  
  String getDropSchemaStatement(HsqlNameManager.HsqlName paramHsqlName) {
    return "DROP SCHEMA " + paramHsqlName.statementName + " " + "CASCADE";
  }
  
  public boolean isAutoCommitStatement() {
    return true;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementSchemaDefinition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */