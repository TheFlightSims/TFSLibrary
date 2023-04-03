package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.map.ValuePool;
import org.hsqldb.result.Result;
import org.hsqldb.rights.User;
import org.hsqldb.types.Charset;
import org.hsqldb.types.Type;

public class ParserCommand extends ParserDDL {
  ParserCommand(Session paramSession, Scanner paramScanner) {
    super(paramSession, paramScanner);
  }
  
  Statement compileStatement(int paramInt) {
    Statement statement = compilePart(paramInt);
    if (this.token.tokenType == 872) {
      if (statement.getSchemaName() == null)
        statement.setSchemaHsqlName(this.session.getCurrentSchemaHsqlName()); 
      return statement;
    } 
    throw unexpectedToken();
  }
  
  HsqlArrayList compileStatements(String paramString, Result paramResult) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    Statement statement = null;
    reset(paramString);
    while (this.token.tokenType != 872) {
      try {
        this.lastError = null;
        statement = compilePart(paramResult.getExecuteProperties());
      } catch (HsqlException hsqlException) {
        if (this.lastError != null && this.lastError.getLevel() > hsqlException.getLevel())
          throw this.lastError; 
        throw hsqlException;
      } 
      if (!statement.isExplain && statement.getParametersMetaData().getColumnCount() > 0)
        throw Error.error(5575); 
      statement.setCompileTimestamp(this.database.txManager.getGlobalChangeTimestamp());
      hsqlArrayList.add(statement);
    } 
    int i = paramResult.getStatementType();
    if (i != 0) {
      int j = statement.getGroup();
      if (j == 2003) {
        if (i == 1)
          throw Error.error(1253); 
      } else if (i == 2) {
      
      } 
    } 
    return hsqlArrayList;
  }
  
  private Statement compilePart(int paramInt) {
    StatementQuery statementQuery;
    StatementDMQL statementDMQL;
    Statement statement;
    RangeGroup[] arrayOfRangeGroup;
    int i;
    this.compileContext.reset();
    setParsePosition(getPosition());
    if (this.token.tokenType == 873)
      read(); 
    switch (this.token.tokenType) {
      case 251:
      case 278:
      case 319:
      case 816:
        statementQuery = compileCursorSpecification(RangeGroup.emptyArray, paramInt, false);
        break;
      case 308:
        arrayOfRangeGroup = this.session.sessionContext.sessionVariableRangeGroups;
        this.compileContext.setOuterRanges(arrayOfRangeGroup);
        statementDMQL = compileShortCursorSpecification(paramInt);
        break;
      case 135:
        statementDMQL = compileInsertStatement(RangeGroup.emptyArray);
        break;
      case 303:
        statementDMQL = compileUpdateStatement(RangeGroup.emptyArray);
        break;
      case 166:
        statementDMQL = compileMergeStatement(RangeGroup.emptyArray);
        break;
      case 79:
        statement = compileDeleteStatement(RangeGroup.emptyArray);
        break;
      case 295:
        statement = compileTruncateStatement();
        break;
      case 25:
        statement = compileCallStatement(this.session.sessionContext.sessionVariableRangeGroups, false);
        break;
      case 254:
        statement = compileSet();
        break;
      case 119:
        statement = compileGetStatement(this.session.sessionContext.sessionVariableRangeGroups);
        break;
      case 267:
        statement = compileStartTransaction();
        break;
      case 44:
        statement = compileCommit();
        break;
      case 241:
        statement = compileRollback();
        break;
      case 246:
        statement = compileSavepoint();
        break;
      case 233:
        statement = compileReleaseSavepoint();
        break;
      case 55:
        statement = compileCreate();
        break;
      case 4:
        statement = compileAlter();
        break;
      case 88:
        statement = compileDrop();
        break;
      case 121:
      case 239:
        statement = compileGrantOrRevoke();
        break;
      case 576:
        statement = compileComment();
        break;
      case 601:
        statement = compileLock();
        break;
      case 47:
        statement = compileConnect();
        break;
      case 84:
        statement = compileDisconnect();
        break;
      case 626:
        statement = compileScript();
        break;
      case 628:
        statement = compileShutdown();
        break;
      case 562:
        statement = compileBackup();
        break;
      case 572:
        statement = compileCheckpoint();
        break;
      case 588:
        i = getPosition();
        statement = compileExplainPlan();
        statement.setSQL(getLastPart(i));
        break;
      case 77:
        statement = compileDeclare();
        break;
      default:
        throw unexpectedToken();
    } 
    switch (statement.type) {
      case 11:
      case 62:
      case 1062:
      case 1191:
        break;
      default:
        statement.setSQL(getLastPart());
        break;
    } 
    if (this.token.tokenType == 821) {
      read();
    } else if (this.token.tokenType == 872) {
    
    } 
    return statement;
  }
  
  private Statement compileDeclare() {
    StatementSession statementSession = compileDeclareLocalTableOrNull();
    if (statementSession != null)
      return statementSession; 
    ColumnSchema[] arrayOfColumnSchema = readLocalVariableDeclarationOrNull();
    if (arrayOfColumnSchema != null) {
      Object[] arrayOfObject = { arrayOfColumnSchema };
      return new StatementSession(1075, arrayOfObject);
    } 
    StatementQuery statementQuery = compileDeclareCursor(RangeGroup.emptyArray, false);
    if (statementQuery == null)
      throw (this.lastError == null) ? unexpectedToken() : this.lastError; 
    return statementQuery;
  }
  
  private Statement compileScript() {
    String str = null;
    read();
    if (this.token.tokenType == 869)
      str = readQuotedString(); 
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames();
    Object[] arrayOfObject = { str };
    return new StatementCommand(1004, arrayOfObject, null, arrayOfHsqlName);
  }
  
  private Statement compileConnect() {
    String str2 = null;
    read();
    readThis(305);
    checkIsSimpleName();
    String str1 = this.token.tokenString;
    read();
    if (!this.session.isProcessingLog()) {
      readThis(615);
      str2 = readPassword();
    } 
    Expression[] arrayOfExpression = { new ExpressionValue(str1, (Type)Type.SQL_VARCHAR), new ExpressionValue(str2, (Type)Type.SQL_VARCHAR) };
    return new StatementSession(76, arrayOfExpression);
  }
  
  private StatementCommand compileSetDefault() {
    HsqlNameManager.HsqlName hsqlName;
    Integer integer;
    byte b;
    Object[] arrayOfObject;
    read();
    switch (this.token.tokenType) {
      case 598:
        read();
        readThis(497);
        hsqlName = this.database.schemaManager.getSchemaHsqlName(this.token.tokenString);
        read();
        arrayOfObject = new Object[] { hsqlName };
        return new StatementCommand(1034, arrayOfObject);
      case 236:
        read();
        readThis(607);
        readThis(245);
        integer = readIntegerObject();
        arrayOfObject = new Object[] { integer };
        return new StatementCommand(1046, arrayOfObject);
      case 278:
        read();
        readThis(535);
        b = 4;
        switch (this.token.tokenType) {
          case 607:
            read();
            arrayOfObject = new Object[] { ValuePool.getInt(b) };
            return new StatementCommand(1035, arrayOfObject);
          case 570:
            b = 5;
        } 
        throw unexpectedToken();
      case 424:
        read();
        readThis(432);
        switch (this.token.tokenType) {
          case 480:
            read();
            readThis(365);
            b = 2;
            arrayOfObject = new Object[] { ValuePool.getInt(b) };
            return new StatementCommand(1053, arrayOfObject);
          case 506:
            read();
            b = 8;
            arrayOfObject = new Object[] { ValuePool.getInt(b) };
            return new StatementCommand(1053, arrayOfObject);
        } 
        throw unexpectedToken();
    } 
    throw unexpectedToken();
  }
  
  private StatementCommand compileSetProperty() {
    Object object;
    read();
    checkIsSimpleName();
    checkIsDelimitedIdentifier();
    String str = this.token.tokenString;
    read();
    if (this.token.tokenType == 294) {
      object = Boolean.TRUE;
    } else if (this.token.tokenType == 106) {
      object = Boolean.FALSE;
    } else {
      checkIsValue();
      object = this.token.tokenValue;
    } 
    read();
    Object[] arrayOfObject = { str, object };
    return new StatementCommand(1039, arrayOfObject);
  }
  
  private Statement compileSet() {
    Expression expression2;
    HsqlArrayList hsqlArrayList1;
    Expression expression1;
    Object[] arrayOfObject1;
    Boolean bool;
    Integer integer;
    int j;
    String str1;
    HsqlNameManager.HsqlName hsqlName;
    Expression[] arrayOfExpression;
    Object[] arrayOfObject4;
    HsqlArrayList hsqlArrayList2;
    Object[] arrayOfObject3;
    boolean bool1;
    Object[] arrayOfObject2;
    Object[] arrayOfObject5;
    StatementCommand statementCommand;
    String str2;
    int i = getPosition();
    read();
    switch (this.token.tokenType) {
      case 348:
        read();
        expression2 = XreadValueSpecificationOrNull();
        if (expression2 == null) {
          HsqlNameManager.HsqlName hsqlName1 = readSchemaName();
          Object[] arrayOfObject = { hsqlName1 };
          return new StatementSession(66, arrayOfObject);
        } 
        if (!expression2.getDataType().isCharacterType())
          throw Error.error(2200); 
        if (expression2.getType() != 1 && (expression2.getType() != 28 || !((FunctionSQL)expression2).isValueFunction()))
          throw Error.error(2200); 
        arrayOfExpression = new Expression[] { expression2 };
        return new StatementSession(66, arrayOfExpression);
      case 497:
        read();
        expression2 = XreadValueSpecificationOrNull();
        if (expression2 == null) {
          HsqlNameManager.HsqlName hsqlName1 = readSchemaName();
          Object[] arrayOfObject = { hsqlName1 };
          return new StatementSession(74, arrayOfObject);
        } 
        if (!expression2.getDataType().isCharacterType())
          throw Error.error(2200); 
        if (expression2.getType() != 1 && (expression2.getType() != 28 || !((FunctionSQL)expression2).isValueFunction()))
          throw Error.error(2200); 
        arrayOfExpression = new Expression[] { expression2 };
        return new StatementSession(74, arrayOfExpression);
      case 180:
        read();
        readThis(358);
        expression2 = null;
        if (readIfThis(112)) {
          hsqlArrayList1 = new HsqlArrayList();
          while (true) {
            SchemaObject schemaObject = readSchemaObjectName(14);
            hsqlArrayList1.add(schemaObject);
            if (this.token.tokenType == 804) {
              read();
              continue;
            } 
            break;
          } 
        } 
        arrayOfObject4 = new Object[] { null, Boolean.FALSE, hsqlArrayList1 };
        return new StatementSession(136, arrayOfObject4);
      case 358:
        read();
        expression1 = XreadValueSpecificationOrNull();
        if (expression1 == null || !expression1.getDataType().isCharacterType())
          throw Error.error(4650); 
        arrayOfObject4 = null;
        if (readIfThis(112)) {
          hsqlArrayList2 = new HsqlArrayList();
          while (true) {
            SchemaObject schemaObject = readSchemaObjectName(14);
            hsqlArrayList2.add(schemaObject);
            if (this.token.tokenType == 804) {
              read();
              continue;
            } 
            break;
          } 
        } 
        arrayOfObject5 = new Object[] { expression1, Boolean.TRUE, hsqlArrayList2 };
        return new StatementSession(136, arrayOfObject5);
      case 281:
        read();
        return compileSetTimeZone();
      case 490:
        read();
        return compileSetRole();
      case 508:
        read();
        return compileSessionSettings();
      case 526:
        read();
        arrayOfObject1 = processTransactionCharacteristics();
        if (arrayOfObject1[0] == null && arrayOfObject1[1] == null)
          throw unexpectedToken(); 
        return new StatementSession(75, arrayOfObject1);
      case 560:
        read();
        bool = processTrueOrFalseObject();
        arrayOfObject3 = new Object[] { bool };
        return new StatementSession(1064, arrayOfObject3);
      case 620:
        read();
        bool = processTrueOrFalseObject();
        arrayOfObject3 = new Object[] { bool };
        return new StatementSession(109, arrayOfObject3);
      case 594:
        read();
        bool = processTrueOrFalseObject();
        arrayOfObject3 = new Object[] { bool };
        return new StatementSession(1048, arrayOfObject3);
      case 605:
        read();
        integer = readIntegerObject();
        arrayOfObject3 = new Object[] { integer };
        return new StatementSession(1065, arrayOfObject3);
      case 78:
        read();
        readThis(278);
        readThis(535);
        j = 4;
        switch (this.token.tokenType) {
          case 607:
            read();
            arrayOfObject3 = new Object[] { ValuePool.getInt(j) };
            return new StatementCommand(1035, arrayOfObject3);
          case 570:
            j = 5;
        } 
        throw unexpectedToken();
      case 278:
        return compileSetTable();
      case 636:
        read();
        j = 0;
        if (this.token.tokenType == 294) {
          j = this.database.getProperties().getDefaultWriteDelay();
          read();
        } else if (this.token.tokenType == 106) {
          j = 0;
          read();
        } else {
          j = readInteger();
          if (j < 0)
            j = 0; 
          if (this.token.tokenType == 608) {
            read();
          } else {
            j *= 1000;
          } 
        } 
        arrayOfObject3 = new Object[] { new Integer(j) };
        return new StatementCommand(1033, arrayOfObject3, null, null);
      case 615:
        bool1 = false;
        read();
        if (readIfThis(586))
          bool1 = true; 
        str1 = readPassword();
        arrayOfObject5 = new Object[] { null, str1, Boolean.valueOf(bool1) };
        statementCommand = new StatementCommand(1062, arrayOfObject5);
        str2 = User.getSetCurrentPasswordDigestSQL(str1, bool1);
        statementCommand.setSQL(str2);
        return statementCommand;
      case 598:
        read();
        readThis(497);
        if (this.token.tokenType == 78) {
          str1 = null;
        } else {
          hsqlName = this.database.schemaManager.getSchemaHsqlName(this.token.tokenString);
        } 
        read();
        arrayOfObject2 = new Object[] { null, hsqlName };
        return new StatementCommand(1061, arrayOfObject2);
      case 590:
        return compileSetFilesProperty();
      case 582:
        return compileSetDatabaseProperty();
      case 618:
        return compileSetProperty();
    } 
    rewind(i);
    return compileSetStatement(this.session.sessionContext.sessionVariableRangeGroups, this.session.sessionContext.sessionVariablesRange);
  }
  
  StatementCommand compileSetTable() {
    boolean bool1;
    Boolean bool;
    String str;
    byte b;
    OrderedHashSet orderedHashSet;
    HsqlNameManager.HsqlName[] arrayOfHsqlName1;
    int[] arrayOfInt;
    read();
    Table table = readTableName();
    Object[] arrayOfObject = { table.getName(), null };
    switch (this.token.tokenType) {
      default:
        throw unexpectedToken();
      case 513:
        read();
        return compileTableSource(table);
      case 480:
        read();
        bool1 = false;
        if (this.token.tokenType == 547) {
          read();
        } else {
          readThis(195);
          bool1 = true;
        } 
        arrayOfObject[1] = Boolean.valueOf(bool1);
        return new StatementCommand(1194, arrayOfObject, null, new HsqlNameManager.HsqlName[] { table.getName() });
      case 620:
        read();
        bool = processTrueOrFalseObject();
        arrayOfObject[1] = bool;
        return new StatementCommand(1194, arrayOfObject, null, new HsqlNameManager.HsqlName[] { table.getName() });
      case 597:
        read();
        checkIsValue();
        str = this.token.tokenString;
        read();
        arrayOfObject[1] = str;
        return new StatementCommand(1193, arrayOfObject, null, new HsqlNameManager.HsqlName[] { table.getName() });
      case 535:
        read();
        if (this.token.tokenType == 570) {
          b = 5;
        } else if (this.token.tokenType == 607) {
          b = 4;
        } else {
          throw unexpectedToken();
        } 
        read();
        arrayOfObject[1] = new Integer(b);
        return new StatementCommand(1197, arrayOfObject, null, new HsqlNameManager.HsqlName[] { table.getName() });
      case 575:
        read();
        readThis(194);
        orderedHashSet = new OrderedHashSet();
        readThis(816);
        readSimpleColumnNames(orderedHashSet, table, false);
        readThis(802);
        arrayOfInt = table.getColumnIndexes(orderedHashSet);
        arrayOfObject[1] = arrayOfInt;
        return new StatementCommand(1198, arrayOfObject, null, new HsqlNameManager.HsqlName[] { table.getName() });
      case 179:
        read();
        readThis(514);
        arrayOfObject = new Object[] { table.getName() };
        arrayOfHsqlName1 = this.database.schemaManager.getCatalogAndBaseTableNames(table.getName());
        return new StatementCommand(1199, arrayOfObject, null, arrayOfHsqlName1);
      case 514:
        break;
    } 
    read();
    Integer integer = readIntegerObject();
    arrayOfObject = new Object[] { table.getName(), integer };
    HsqlNameManager.HsqlName[] arrayOfHsqlName2 = this.database.schemaManager.getCatalogAndBaseTableNames(table.getName());
    return new StatementCommand(1200, arrayOfObject, null, arrayOfHsqlName2);
  }
  
  StatementCommand compileSetDatabaseProperty() {
    String str1;
    Routine routine2;
    Boolean bool3;
    boolean bool2;
    Integer integer1;
    Routine routine1;
    boolean bool1;
    char c;
    String str2;
    byte b;
    Object[] arrayOfObject1;
    Object[] arrayOfObject4;
    Integer integer2;
    Object[] arrayOfObject3;
    Boolean bool4;
    Object[] arrayOfObject2;
    HsqlNameManager.HsqlName[] arrayOfHsqlName;
    Object[] arrayOfObject6;
    Integer integer3;
    Object[] arrayOfObject5;
    String str3;
    Object[] arrayOfObject7;
    read();
    checkDatabaseUpdateAuthorisation();
    switch (this.token.tokenType) {
      case 561:
        read();
        readThis(117);
        routine2 = readCreateDatabaseAuthenticationFunction();
        arrayOfObject4 = new Object[] { routine2 };
        return new StatementCommand(1036, arrayOfObject4, null, null);
      case 358:
        routine2 = null;
        read();
        checkIsSimpleName();
        str1 = this.token.tokenString;
        read();
        if (readIfThis(180)) {
          readThis(463);
          bool3 = Boolean.FALSE;
        } else if (readIfThis(463)) {
          readThis(514);
          bool3 = Boolean.TRUE;
        } 
        if (bool3 == null)
          if (this.session.isProcessingScript() && this.database.getProperties().isVersion18()) {
            bool3 = Boolean.FALSE;
          } else {
            bool3 = Boolean.TRUE;
          }  
        arrayOfObject4 = new Object[] { str1, bool3 };
        return new StatementCommand(1047, arrayOfObject4, null, null);
      case 78:
        return compileSetDefault();
      case 587:
        read();
        readThis(703);
        bool2 = readIfThis(261);
        readThis(432);
        integer2 = readIntegerObject();
        arrayOfObject6 = new Object[] { integer2, Boolean.valueOf(bool2) };
        return new StatementCommand(1016, arrayOfObject6, null, null);
      case 592:
        read();
        integer1 = readIntegerObject();
        arrayOfObject3 = new Object[] { integer1 };
        return new StatementCommand(1037, arrayOfObject3, null, null);
      case 615:
        read();
        readThis(37);
        readThis(117);
        routine1 = readCreatePasswordCheckFunction();
        arrayOfObject3 = new Object[] { routine1 };
        return new StatementCommand(1040, arrayOfObject3, null, null);
      case 621:
        read();
        readThis(596);
        bool1 = processTrueOrFalse();
        arrayOfObject3 = new Object[] { Boolean.valueOf(bool1) };
        return new StatementCommand(1049, arrayOfObject3, null, null);
      case 261:
        read();
        c = 'К';
        bool4 = Boolean.TRUE;
        integer3 = Integer.valueOf(0);
        str3 = null;
        switch (this.token.tokenType) {
          case 446:
            read();
            str3 = "sql.enforce_names";
            bool4 = processTrueOrFalseObject();
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
          case 622:
            read();
            readThis(446);
            str3 = "sql.regular_names";
            bool4 = processTrueOrFalseObject();
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
          case 222:
            read();
            bool4 = processTrueOrFalseObject();
            str3 = "sql.enforce_refs";
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
          case 512:
            read();
            bool4 = processTrueOrFalseObject();
            str3 = "sql.enforce_size";
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
          case 634:
            read();
            bool4 = processTrueOrFalseObject();
            str3 = "sql.enforce_types";
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
          case 630:
            read();
            if (readIfThis(79)) {
              str3 = "sql.enforce_tdc_delete";
            } else {
              readThis(303);
              str3 = "sql.enforce_tdc_update";
            } 
            bool4 = processTrueOrFalseObject();
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
          case 287:
            read();
            readThis(633);
            readThis(634);
            bool4 = processTrueOrFalseObject();
            str3 = "jdbc.translate_tti_types";
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
          case 656:
            read();
            readThis(451);
            bool4 = processTrueOrFalseObject();
            str3 = "sql.concat_nulls";
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
          case 451:
            read();
            if (readIfThis(401)) {
              str3 = "sql.nulls_first";
            } else {
              readThis(198);
              str3 = "sql.nulls_order";
            } 
            bool4 = processTrueOrFalseObject();
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
          case 299:
            read();
            readThis(451);
            bool4 = processTrueOrFalseObject();
            str3 = "sql.unique_nulls";
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
          case 49:
            read();
            readThis(295);
            bool4 = processTrueOrFalseObject();
            str3 = "sql.convert_trunc";
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
          case 16:
            read();
            readThis(496);
            integer3 = readIntegerObject();
            str3 = "sql.avg_scale";
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
          case 87:
            read();
            readThis(610);
            bool4 = processTrueOrFalseObject();
            str3 = "sql.double_nan";
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
          case 604:
            read();
            readThis(142);
            readThis(702);
            bool4 = processTrueOrFalseObject();
            str3 = "sql.longvar_is_lob";
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
          case 594:
            read();
            bool4 = processTrueOrFalseObject();
            str3 = "sql.ignore_case";
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
          case 629:
            read();
            if (this.token.tokenString.equals("DB2")) {
              read();
              str3 = "sql.syntax_db2";
            } else if (this.token.tokenString.equals("MSS")) {
              read();
              str3 = "sql.syntax_mss";
            } else if (this.token.tokenString.equals("MYS")) {
              read();
              str3 = "sql.syntax_mys";
            } else if (this.token.tokenString.equals("ORA")) {
              read();
              str3 = "sql.syntax_ora";
            } else if (this.token.tokenString.equals("PGS")) {
              read();
              str3 = "sql.syntax_pgs";
            } else {
              throw unexpectedToken();
            } 
            bool4 = processTrueOrFalseObject();
            arrayOfObject7 = new Object[] { str3, bool4, integer3 };
            return new StatementCommand(c, arrayOfObject7, null, null);
        } 
        throw unexpectedToken();
      case 632:
        read();
        readThis(278);
        readThis(381);
        str2 = readQuotedString();
        arrayOfObject2 = new Object[] { str2 };
        return new StatementCommand(1051, arrayOfObject2, null, null);
      case 526:
        read();
        if (readIfThis(241)) {
          readThis(194);
          if (!readIfThis(583))
            readThis(579); 
          Boolean bool = processTrueOrFalseObject();
          return new StatementCommand(1054, new Object[] { bool }, null, null);
        } 
        readThis(580);
        b = 0;
        switch (this.token.tokenType) {
          case 711:
            read();
            b = 2;
            break;
          case 712:
            read();
            b = 1;
            break;
          case 602:
            read();
            b = 0;
            break;
        } 
        arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames();
        arrayOfObject5 = new Object[] { ValuePool.getInt(b) };
        return new StatementCommand(1052, arrayOfObject5, null, arrayOfHsqlName);
      case 299:
        read();
        readThis(445);
        isUndelimitedSimpleName();
        str1 = this.token.tokenString;
        read();
        if (str1.length() != 16)
          throw Error.error(5555); 
        if (!Charset.isInSet(str1, Charset.unquotedIdentifier) || !Charset.startsWith(str1, Charset.uppercaseLetters))
          throw Error.error(5501); 
        arrayOfObject1 = new Object[] { str1 };
        return new StatementCommand(1055, arrayOfObject1, null, null);
    } 
    throw unexpectedToken();
  }
  
  StatementCommand compileSetFilesProperty() {
    int i;
    read();
    char c = Character.MIN_VALUE;
    Boolean bool1 = null;
    Integer integer = null;
    Boolean bool2 = null;
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogNameArray();
    checkDatabaseUpdateAuthorisation();
    switch (this.token.tokenType) {
      case 569:
        read();
        if (readIfThis(512)) {
          integer = readIntegerObject();
          c = 'ϵ';
        } else {
          readThis(245);
          integer = readIntegerObject();
          c = 'ϴ';
        } 
        if (readIfThis(180)) {
          readThis(37);
          bool2 = Boolean.TRUE;
        } 
        break;
      case 37:
        read();
        integer = readIntegerObject();
        c = '϶';
        arrayOfHsqlName = this.database.schemaManager.getCatalogNameArray();
        break;
      case 496:
        read();
        integer = readIntegerObject();
        c = 'Ё';
        arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames();
        break;
      case 514:
        read();
        bool1 = processTrueOrFalseObject();
        c = 'Ї';
        arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames();
        break;
      case 702:
        read();
        if (readIfThis(496)) {
          integer = readIntegerObject();
          c = 'Ϲ';
        } else {
          readThis(578);
          c = 'Ϻ';
          bool1 = processTrueOrFalseObject();
        } 
        arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames();
        break;
      case 584:
        read();
        c = 'Ϸ';
        integer = readIntegerObject();
        break;
      case 715:
        read();
        if (readIfThis(512)) {
          integer = readIntegerObject();
        } else {
          bool1 = processTrueOrFalseObject();
        } 
        c = 'Ͼ';
        break;
      case 562:
        read();
        c = 'ϳ';
        readThis(417);
        bool1 = processTrueOrFalseObject();
        break;
      case 703:
        read();
        if (readIfThis(512)) {
          c = 'Ͻ';
          integer = readIntegerObject();
          break;
        } 
        c = 'ϼ';
        bool1 = processTrueOrFalseObject();
        break;
      case 631:
        read();
        readThis(472);
        c = 'Ј';
        integer = readIntegerObject();
        break;
      case 547:
        read();
        readThis(585);
        c = 'Љ';
        i = 0;
        if (this.token.tokenType == 294) {
          i = this.database.getProperties().getDefaultWriteDelay();
          read();
        } else if (this.token.tokenType == 106) {
          i = 0;
          read();
        } else {
          i = readInteger();
          if (i < 0)
            i = 0; 
          if (this.token.tokenType == 608) {
            read();
          } else {
            i *= 1000;
          } 
        } 
        integer = new Integer(i);
        break;
      case 626:
        read();
        readThis(591);
        if (this.token.tokenType == 632) {
          read();
          integer = new Integer(0);
        } else {
          readThis(578);
          integer = new Integer(3);
        } 
        c = 'Ђ';
        break;
      default:
        throw unexpectedToken();
    } 
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = (bool1 == null) ? integer : bool1;
    arrayOfObject[1] = bool2;
    return new StatementCommand(c, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Object[] processTransactionCharacteristics() {
    byte b = 0;
    boolean bool = false;
    Object[] arrayOfObject = new Object[2];
    while (true) {
      switch (this.token.tokenType) {
        case 480:
          if (arrayOfObject[0] != null)
            throw unexpectedToken(); 
          read();
          if (this.token.tokenType == 195) {
            read();
            bool = true;
          } else {
            readThis(547);
            bool = false;
          } 
          arrayOfObject[0] = Boolean.valueOf(bool);
          continue;
        case 424:
          if (arrayOfObject[1] != null)
            throw unexpectedToken(); 
          read();
          readThis(432);
          switch (this.token.tokenType) {
            case 506:
              read();
              b = 8;
              break;
            case 480:
              read();
              if (this.token.tokenType == 365) {
                read();
                b = 2;
                break;
              } 
              if (this.token.tokenType == 537) {
                read();
                b = 1;
                break;
              } 
              throw unexpectedToken();
            case 482:
              read();
              readThis(480);
              b = 4;
              break;
            default:
              throw unexpectedToken();
          } 
          arrayOfObject[1] = new Integer(b);
          continue;
        case 804:
          if (arrayOfObject[0] == null && arrayOfObject[1] == null)
            throw unexpectedToken(); 
          read();
          continue;
      } 
      if (!bool && b == 1)
        throw unexpectedToken("WRITE"); 
      return arrayOfObject;
    } 
  }
  
  private Statement compileCommit() {
    boolean bool = false;
    read();
    readIfThis(546);
    if (this.token.tokenType == 5) {
      read();
      if (this.token.tokenType == 180) {
        read();
      } else {
        bool = true;
      } 
      readThis(350);
    } 
    String str = bool ? StatementSession.commitAndChainStatement.sql : StatementSession.commitNoChainStatement.sql;
    StatementSession statementSession = new StatementSession(11, new Object[] { Boolean.valueOf(bool) });
    statementSession.setSQL(str);
    return statementSession;
  }
  
  private Statement compileStartTransaction() {
    read();
    readThis(526);
    Object[] arrayOfObject = processTransactionCharacteristics();
    return new StatementSession(111, arrayOfObject);
  }
  
  private Statement compileLock() {
    read();
    readThis(278);
    OrderedHashSet orderedHashSet1 = new OrderedHashSet();
    OrderedHashSet orderedHashSet2 = new OrderedHashSet();
    while (true) {
      Table table = readTableName();
      switch (this.token.tokenType) {
        case 480:
          read();
          orderedHashSet1.add(table.getName());
          break;
        case 547:
          read();
          orderedHashSet2.add(table.getName());
          break;
        default:
          throw unexpectedToken();
      } 
      if (this.token.tokenType == 804) {
        read();
        continue;
      } 
      HsqlNameManager.HsqlName[] arrayOfHsqlName1 = new HsqlNameManager.HsqlName[orderedHashSet2.size()];
      orderedHashSet2.toArray((Object[])arrayOfHsqlName1);
      orderedHashSet1.removeAll((Object[])arrayOfHsqlName1);
      HsqlNameManager.HsqlName[] arrayOfHsqlName2 = new HsqlNameManager.HsqlName[orderedHashSet1.size()];
      orderedHashSet1.toArray((Object[])arrayOfHsqlName2);
      return new StatementSession(1063, arrayOfHsqlName2, arrayOfHsqlName1);
    } 
  }
  
  private Statement compileRollback() {
    boolean bool = false;
    String str1 = null;
    read();
    if (this.token.tokenType == 546)
      read(); 
    if (this.token.tokenType == 285) {
      read();
      readThis(246);
      checkIsSimpleName();
      str1 = this.token.tokenString;
      read();
      String str = getLastPart();
      Object[] arrayOfObject = { str1 };
      return new StatementSession(1067, arrayOfObject);
    } 
    if (this.token.tokenType == 5) {
      read();
      if (this.token.tokenType == 180) {
        read();
      } else {
        bool = true;
      } 
      readThis(350);
    } 
    String str2 = bool ? StatementSession.rollbackAndChainStatement.sql : StatementSession.rollbackNoChainStatement.sql;
    StatementSession statementSession = new StatementSession(62, new Object[] { Boolean.valueOf(bool) });
    statementSession.setSQL(str2);
    return statementSession;
  }
  
  private Statement compileSavepoint() {
    read();
    checkIsSimpleName();
    String str1 = this.token.tokenString;
    read();
    String str2 = getLastPart();
    Object[] arrayOfObject = { str1 };
    return new StatementSession(63, arrayOfObject);
  }
  
  private Statement compileReleaseSavepoint() {
    read();
    readThis(246);
    String str1 = this.token.tokenString;
    read();
    String str2 = getLastPart();
    Object[] arrayOfObject = { str1 };
    return new StatementSession(57, arrayOfObject);
  }
  
  private Statement compileSessionSettings() {
    Object[] arrayOfObject1;
    Expression expression;
    Integer integer;
    Expression[] arrayOfExpression;
    Object[] arrayOfObject2;
    switch (this.token.tokenType) {
      case 354:
        read();
        readThis(10);
        readThis(526);
        arrayOfObject1 = processTransactionCharacteristics();
        return new StatementSession(109, arrayOfObject1);
      case 15:
        read();
        expression = XreadValueSpecificationOrNull();
        if (expression == null)
          throw Error.error(5584); 
        expression.resolveTypes(this.session, null);
        if (expression.isUnresolvedParam())
          expression.dataType = (Type)Type.SQL_VARCHAR; 
        if (expression.dataType == null || !expression.dataType.isCharacterType())
          throw Error.error(5563); 
        arrayOfExpression = new Expression[] { expression, null };
        return new StatementSession(76, arrayOfExpression);
      case 236:
        read();
        readThis(607);
        readThis(245);
        integer = readIntegerObject();
        arrayOfObject2 = new Object[] { integer };
        return new StatementSession(1066, arrayOfObject2);
    } 
    throw unexpectedToken();
  }
  
  private Statement compileSetRole() {
    Expression expression;
    if (this.token.tokenType == 181) {
      read();
      expression = new ExpressionValue(null, (Type)Type.SQL_VARCHAR);
    } else {
      expression = XreadValueSpecificationOrNull();
      if (expression == null)
        throw Error.error(4100); 
      if (!expression.getDataType().isCharacterType())
        throw Error.error(2200); 
      if (expression.getType() != 1 && (expression.getType() != 28 || !((FunctionSQL)expression).isValueFunction()))
        throw Error.error(2200); 
    } 
    String str = getLastPart();
    return new StatementSession(73, new Expression[] { expression });
  }
  
  private Statement compileSetTimeZone() {
    Expression expression;
    readThis(549);
    if (this.token.tokenType == 157) {
      read();
      expression = new ExpressionValue(null, (Type)Type.SQL_INTERVAL_HOUR_TO_MINUTE);
    } else {
      expression = XreadIntervalValueExpression();
      HsqlList hsqlList = expression.resolveColumnReferences(this.session, RangeGroup.emptyGroup, RangeGroup.emptyArray, null);
      ExpressionColumn.checkColumnsResolved(hsqlList);
      expression.resolveTypes(this.session, null);
      if (expression.dataType == null)
        throw Error.error(5563); 
      if (expression.dataType.typeCode != 111)
        throw Error.error(5563); 
    } 
    String str = getLastPart();
    return new StatementSession(71, new Expression[] { expression });
  }
  
  private Statement compileShutdown() {
    this.session.checkAdmin();
    byte b = 2;
    read();
    switch (this.token.tokenType) {
      case 595:
        b = 1;
        read();
        break;
      case 577:
        b = 3;
        read();
        break;
      case 626:
        b = 4;
        read();
        break;
    } 
    if (this.token.tokenType == 821)
      read(); 
    if (this.token.tokenType != 872)
      throw unexpectedToken(); 
    String str = getLastPart();
    Object[] arrayOfObject = { new Integer(b) };
    return new StatementCommand(1003, arrayOfObject, null, null);
  }
  
  private Statement compileBackup() {
    Boolean bool1 = null;
    Boolean bool2 = null;
    Boolean bool3 = null;
    Boolean bool4 = null;
    read();
    readThis(582);
    readThis(285);
    String str = readQuotedString();
    str = str.trim();
    if (str.length() == 0)
      throw unexpectedToken(str); 
    while (true) {
      switch (this.token.tokenType) {
        case 566:
          if (bool1 != null)
            throw unexpectedToken(); 
          bool1 = Boolean.TRUE;
          read();
          continue;
        case 626:
          if (bool2 != null)
            throw unexpectedToken(); 
          bool2 = Boolean.TRUE;
          read();
          continue;
        case 578:
          if (bool3 != null)
            throw unexpectedToken(); 
          bool3 = Boolean.TRUE;
          read();
          continue;
        case 183:
          read();
          if (this.token.tokenType == 578) {
            if (bool3 != null)
              throw unexpectedToken(); 
            bool3 = Boolean.FALSE;
            read();
            continue;
          } 
          if (this.token.tokenType == 566) {
            if (bool1 != null)
              throw unexpectedToken(); 
            bool1 = Boolean.FALSE;
            read();
            continue;
          } 
          throw unexpectedToken();
        case 10:
          if (bool4 != null)
            throw unexpectedToken(); 
          read();
          readThis(590);
          bool4 = Boolean.TRUE;
          continue;
      } 
      if (bool2 == null)
        bool2 = Boolean.FALSE; 
      if (bool1 == null)
        bool1 = Boolean.TRUE; 
      if (bool3 == null)
        bool3 = Boolean.TRUE; 
      if (bool4 == null)
        bool4 = Boolean.FALSE; 
      if (bool2.booleanValue() && !bool1.booleanValue())
        throw unexpectedToken("NOT"); 
      HsqlNameManager.HsqlName[] arrayOfHsqlName = bool1.booleanValue() ? this.database.schemaManager.getCatalogAndBaseTableNames() : HsqlNameManager.HsqlName.emptyArray;
      Object[] arrayOfObject = { str, bool1, bool2, bool3, bool4 };
      return new StatementCommand(1001, arrayOfObject, null, arrayOfHsqlName);
    } 
  }
  
  private Statement compileCheckpoint() {
    boolean bool = false;
    read();
    if (this.token.tokenType == 584) {
      bool = true;
      read();
    } else if (this.token.tokenType == 821) {
      read();
    } 
    if (this.token.tokenType != 872)
      throw unexpectedToken(); 
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames();
    Object[] arrayOfObject = { Boolean.valueOf(bool) };
    return new StatementCommand(1002, arrayOfObject, null, arrayOfHsqlName);
  }
  
  public static Statement getAutoCheckpointStatement(Database paramDatabase) {
    HsqlNameManager.HsqlName[] arrayOfHsqlName = paramDatabase.schemaManager.getCatalogAndBaseTableNames();
    Object[] arrayOfObject = { Boolean.FALSE };
    StatementCommand statementCommand = new StatementCommand(1002, arrayOfObject, null, arrayOfHsqlName);
    statementCommand.setCompileTimestamp(paramDatabase.txManager.getGlobalChangeTimestamp());
    statementCommand.setSQL("CHECKPOINT");
    return statementCommand;
  }
  
  private Statement compileDisconnect() {
    read();
    String str = "DISCONNECT";
    return new StatementSession(22, (Object[])null);
  }
  
  private Statement compileExplainPlan() {
    read();
    readThis(616);
    readThis(112);
    Statement statement = compilePart(0);
    statement.setDescribe();
    return new StatementCommand(1191, new Object[] { statement });
  }
  
  private StatementCommand compileTableSource(Table paramTable) {
    String str1;
    boolean bool1 = false;
    boolean bool2 = false;
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = paramTable.getName();
    if (!paramTable.isText())
      HsqlException hsqlException = Error.error(321); 
    if (this.token.tokenType == 194) {
      read();
      String str = getLastPart();
      arrayOfObject[1] = Boolean.TRUE;
      return new StatementCommand(1195, arrayOfObject, null, new HsqlNameManager.HsqlName[] { paramTable.getName() });
    } 
    if (this.token.tokenType == 614) {
      read();
      String str = getLastPart();
      arrayOfObject[1] = Boolean.FALSE;
      return new StatementCommand(1195, arrayOfObject, null, new HsqlNameManager.HsqlName[] { paramTable.getName() });
    } 
    if (this.token.tokenType == 593) {
      read();
      bool1 = true;
    } 
    if (this.token.tokenType == 871) {
      str1 = this.token.tokenString;
      read();
    } else {
      str1 = readQuotedString();
    } 
    if (!bool1 && this.token.tokenType == 389) {
      bool2 = true;
      read();
    } 
    String str2 = getLastPart();
    arrayOfObject[2] = str1;
    arrayOfObject[3] = Boolean.valueOf(bool2);
    arrayOfObject[4] = Boolean.valueOf(bool1);
    char c = bool1 ? 'Ҭ' : 'ҫ';
    return new StatementCommand(c, arrayOfObject, null, new HsqlNameManager.HsqlName[] { paramTable.getName() });
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ParserCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */