package org.hsqldb;

import java.lang.reflect.Method;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.map.ValuePool;
import org.hsqldb.rights.Grantee;
import org.hsqldb.rights.GranteeManager;
import org.hsqldb.rights.Right;
import org.hsqldb.rights.User;
import org.hsqldb.types.Charset;
import org.hsqldb.types.Collation;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.Type;
import org.hsqldb.types.UserTypeModifier;

public class ParserDDL extends ParserRoutine {
  static final int[] schemaCommands = new int[] { 55, 121 };
  
  static final short[] startStatementTokens = new short[] { 55, 121, 4, 88 };
  
  static final short[] startStatementTokensSchema = new short[] { 55, 121 };
  
  ParserDDL(Session paramSession, Scanner paramScanner) {
    super(paramSession, paramScanner);
  }
  
  void reset(String paramString) {
    super.reset(paramString);
  }
  
  StatementSchema compileCreate() {
    int i = 4;
    boolean bool1 = false;
    boolean bool2 = false;
    read();
    switch (this.token.tokenType) {
      case 120:
        read();
        readThis(523);
        readIfThis(607);
        readThis(278);
        bool1 = true;
        i = 3;
        break;
      case 631:
        read();
        readThis(278);
        bool1 = true;
        i = 3;
        break;
      case 523:
        read();
        readThis(278);
        bool1 = true;
        i = 3;
        break;
      case 607:
        read();
        readThis(278);
        bool1 = true;
        break;
      case 570:
        read();
        readThis(278);
        bool1 = true;
        i = 5;
        break;
      case 632:
        read();
        readThis(278);
        bool1 = true;
        i = 7;
        break;
      case 278:
        read();
        bool1 = true;
        i = this.database.schemaManager.getDefaultTableType();
        break;
      case 197:
        if (this.database.sqlSyntaxOra) {
          read();
          readThis(729);
          switch (this.token.tokenType) {
            case 117:
            case 215:
            case 291:
            case 535:
            case 545:
              break;
            default:
              throw unexpectedToken("OR");
          } 
          bool2 = true;
        } 
        break;
    } 
    if (bool1)
      return compileCreateTable(i); 
    switch (this.token.tokenType) {
      case 558:
        return compileCreateAlias();
      case 505:
        return compileCreateSequence();
      case 497:
        return compileCreateSchema();
      case 291:
        return compileCreateTrigger(bool2);
      case 305:
        return compileCreateUser();
      case 490:
        return compileCreateRole();
      case 545:
        return compileCreateView(false, bool2);
      case 393:
        return compileCreateDomain();
      case 535:
        return compileCreateType(bool2);
      case 35:
        return compileCreateCharacterSet();
      case 358:
        return compileCreateCollation();
      case 299:
        read();
        checkIsThis(597);
        return compileCreateIndex(true);
      case 597:
        return compileCreateIndex(false);
      case 117:
      case 215:
      case 559:
        return compileCreateProcedureOrFunction(bool2);
    } 
    throw unexpectedToken();
  }
  
  Statement compileAlter() {
    HsqlNameManager.HsqlName hsqlName;
    String str1;
    Index index;
    Table table;
    int[] arrayOfInt;
    String str2;
    Object[] arrayOfObject;
    HsqlNameManager.HsqlName[] arrayOfHsqlName;
    read();
    switch (this.token.tokenType) {
      case 597:
        read();
        hsqlName = readNewSchemaObjectName(20, true);
        hsqlName.setSchemaIfNull(this.session.getCurrentSchemaHsqlName());
        if (this.token.tokenType == 623) {
          read();
          readThis(285);
          return compileRenameObject(hsqlName, 20);
        } 
        readThis(10);
        index = (Index)this.database.schemaManager.getSchemaObject(hsqlName);
        if (index == null)
          throw Error.error(5501); 
        table = (Table)this.database.schemaManager.getSchemaObject((index.getName()).parent);
        arrayOfInt = readColumnList(table, true);
        str2 = getLastPart();
        arrayOfObject = new Object[] { table, arrayOfInt, index.getName() };
        arrayOfHsqlName = new HsqlNameManager.HsqlName[] { this.database.getCatalogName(), table.getName() };
        return new StatementSchema(str2, 1069, arrayOfObject, null, arrayOfHsqlName);
      case 497:
        read();
        hsqlName = readSchemaName();
        readThis(623);
        readThis(285);
        return compileRenameObject(hsqlName, 2);
      case 348:
        read();
        checkIsSimpleName();
        str1 = this.token.tokenString;
        checkValidCatalogName(str1);
        read();
        readThis(623);
        readThis(285);
        return compileRenameObject(this.database.getCatalogName(), 1);
      case 505:
        return compileAlterSequence();
      case 278:
        return compileAlterTable();
      case 305:
        return compileAlterUser();
      case 393:
        return compileAlterDomain();
      case 545:
        return compileCreateView(true, false);
      case 508:
        return compileAlterSession();
      case 259:
        return compileAlterSpecificRoutine();
      case 491:
        return compileAlterRoutine();
    } 
    throw unexpectedToken();
  }
  
  Statement compileAlterRoutine() {
    readThis(491);
    RoutineSchema routineSchema = (RoutineSchema)readSchemaObjectName(18);
    readThis(623);
    readThis(285);
    return compileRenameObject(routineSchema.getName(), (routineSchema.getName()).type);
  }
  
  Statement compileDrop() {
    byte b;
    char c;
    User user;
    Grantee grantee;
    Schema schema;
    SchemaObject schemaObject;
    HsqlNameManager.HsqlName[] arrayOfHsqlName;
    boolean bool5;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    read();
    int i = this.token.tokenType;
    switch (i) {
      case 597:
        read();
        c = 'е';
        b = 20;
        bool3 = true;
        break;
      case 339:
        read();
        c = '\030';
        b = 6;
        bool1 = true;
        break;
      case 259:
        read();
        switch (this.token.tokenType) {
          case 117:
          case 215:
          case 491:
            read();
            break;
          default:
            throw unexpectedToken();
        } 
        c = '\036';
        b = 24;
        bool1 = true;
        bool3 = true;
        break;
      case 215:
        read();
        c = '\036';
        b = 17;
        bool1 = true;
        bool3 = true;
        break;
      case 117:
        read();
        c = '\036';
        b = 16;
        bool1 = true;
        bool3 = true;
        break;
      case 497:
        read();
        c = '\037';
        b = 2;
        bool1 = true;
        bool3 = true;
        break;
      case 505:
        read();
        c = '';
        b = 7;
        bool1 = true;
        bool3 = true;
        break;
      case 291:
        read();
        c = '"';
        b = 8;
        bool1 = false;
        bool3 = true;
        break;
      case 305:
        read();
        c = 'з';
        b = 11;
        bool1 = true;
        break;
      case 490:
        read();
        c = '\035';
        b = 11;
        bool1 = true;
        break;
      case 393:
        read();
        c = '\033';
        b = 13;
        bool1 = true;
        bool3 = true;
        break;
      case 535:
        read();
        c = '#';
        b = 12;
        bool1 = true;
        bool3 = true;
        break;
      case 35:
        read();
        readThis(254);
        c = '\031';
        b = 14;
        bool1 = false;
        bool3 = true;
        break;
      case 358:
        read();
        c = '\032';
        b = 15;
        bool1 = false;
        bool3 = true;
        break;
      case 545:
        read();
        c = '$';
        b = 4;
        bool1 = true;
        bool3 = true;
        break;
      case 278:
        read();
        c = ' ';
        b = 3;
        bool1 = true;
        bool3 = true;
        break;
      default:
        throw unexpectedToken();
    } 
    if (bool3 && this.token.tokenType == 412) {
      int j = getPosition();
      read();
      if (this.token.tokenType == 101) {
        read();
        bool4 = true;
      } else {
        rewind(j);
      } 
    } 
    checkIsIdentifier();
    HsqlNameManager.HsqlName hsqlName = null;
    switch (i) {
      case 305:
        checkIsSimpleName();
        checkDatabaseUpdateAuthorisation();
        user = this.database.getUserManager().get(this.token.tokenString);
        read();
        break;
      case 490:
        checkIsSimpleName();
        checkDatabaseUpdateAuthorisation();
        grantee = this.database.getGranteeManager().getRole(this.token.tokenString);
        read();
        break;
      case 497:
        hsqlName = readNewSchemaName();
        schema = this.database.schemaManager.findSchema(hsqlName.name);
        break;
      case 278:
        bool5 = (this.token.namePrePrefix == null && ("MODULE".equals(this.token.namePrefix) || "SESSION".equals(this.token.namePrefix))) ? true : false;
        if (bool5) {
          hsqlName = readNewSchemaObjectName(b, false);
          if (!bool4 && this.token.tokenType == 412) {
            read();
            readThis(101);
            bool4 = true;
          } 
          Object[] arrayOfObject1 = { hsqlName, Boolean.valueOf(bool4) };
          return new StatementSession(32, arrayOfObject1);
        } 
      default:
        hsqlName = readNewSchemaObjectName(b, false);
        hsqlName.setSchemaIfNull(this.session.getCurrentSchemaHsqlName());
        schemaObject = this.database.schemaManager.findSchemaObject(hsqlName.name, hsqlName.schema.name, hsqlName.type);
        break;
    } 
    if (!bool4 && bool3 && this.token.tokenType == 412) {
      read();
      readThis(101);
      bool4 = true;
    } 
    if (bool1)
      if (this.token.tokenType == 347) {
        bool2 = true;
        read();
      } else if (this.token.tokenType == 485) {
        read();
      }  
    if (schemaObject == null) {
      arrayOfHsqlName = null;
    } else {
      hsqlName = schemaObject.getName();
      arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(hsqlName);
    } 
    String str = getLastPart();
    Object[] arrayOfObject = { hsqlName, new Integer(b), Boolean.valueOf(bool2), Boolean.valueOf(bool4) };
    return new StatementSchema(str, c, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterTable() {
    HsqlNameManager.HsqlName hsqlName2;
    boolean bool1;
    String str2;
    int i;
    boolean bool2;
    ColumnSchema columnSchema;
    read();
    String str1 = this.token.tokenString;
    HsqlNameManager.HsqlName hsqlName1 = this.session.getSchemaHsqlName(this.token.namePrefix);
    checkSchemaUpdateAuthorisation(hsqlName1);
    Table table = this.database.schemaManager.getUserTable(this.session, str1, hsqlName1.name);
    read();
    switch (this.token.tokenType) {
      case 623:
        read();
        readThis(285);
        return compileRenameObject(table.getName(), 3);
      case 334:
        read();
        hsqlName2 = null;
        if (this.token.tokenType == 48) {
          read();
          hsqlName2 = readNewDependentSchemaObjectName(table.getName(), 5);
        } 
        switch (this.token.tokenType) {
          case 113:
            read();
            readThis(427);
            return compileAlterTableAddForeignKeyConstraint(table, hsqlName2);
          case 299:
            read();
            return compileAlterTableAddUniqueConstraint(table, hsqlName2);
          case 37:
            read();
            return compileAlterTableAddCheckConstraint(table, hsqlName2);
          case 214:
            read();
            readThis(427);
            return compileAlterTableAddPrimaryKey(table, hsqlName2);
          case 43:
            if (hsqlName2 != null)
              throw unexpectedToken(); 
            read();
            checkIsSimpleName();
            return compileAlterTableAddColumn(table);
        } 
        if (hsqlName2 != null)
          throw unexpectedToken(); 
        checkIsSimpleName();
        return compileAlterTableAddColumn(table);
      case 88:
        read();
        switch (this.token.tokenType) {
          case 214:
            bool1 = false;
            read();
            readThis(427);
            return compileAlterTableDropPrimaryKey(table);
          case 48:
            read();
            return compileAlterTableDropConstraint(table);
          case 43:
            read();
            break;
        } 
        checkIsSimpleName();
        str2 = this.token.tokenString;
        bool2 = false;
        read();
        if (this.token.tokenType == 485) {
          read();
        } else if (this.token.tokenType == 347) {
          read();
          bool2 = true;
        } 
        return compileAlterTableDropColumn(table, str2, bool2);
      case 4:
        read();
        if (this.token.tokenType == 43)
          read(); 
        i = table.getColumnIndex(this.token.tokenString);
        columnSchema = table.getColumn(i);
        read();
        return compileAlterColumn(table, columnSchema, i);
    } 
    throw unexpectedToken();
  }
  
  private Statement compileAlterTableDropConstraint(Table paramTable) {
    boolean bool = false;
    SchemaObject schemaObject = readSchemaObjectName(paramTable.getSchemaName(), 5);
    if (this.token.tokenType == 485) {
      read();
    } else if (this.token.tokenType == 347) {
      read();
      bool = true;
    } 
    String str = getLastPart();
    Object[] arrayOfObject = { schemaObject.getName(), ValuePool.getInt(5), Boolean.valueOf(bool), Boolean.valueOf(false) };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    HsqlNameManager.HsqlName hsqlName = ((Constraint)schemaObject).getMainTableName();
    if (hsqlName != null && hsqlName != paramTable.getName())
      arrayOfHsqlName = (HsqlNameManager.HsqlName[])ArrayUtil.toAdjustedArray(arrayOfHsqlName, hsqlName, arrayOfHsqlName.length, 1); 
    return new StatementSchema(str, 1078, arrayOfObject, null, arrayOfHsqlName);
  }
  
  private Statement compileAlterTableDropPrimaryKey(Table paramTable) {
    boolean bool = false;
    if (this.token.tokenType == 485) {
      read();
    } else if (this.token.tokenType == 347) {
      read();
      bool = true;
    } 
    if (!paramTable.hasPrimaryKey())
      throw Error.error(5501); 
    String str = getLastPart();
    Constraint constraint = paramTable.getPrimaryConstraint();
    Object[] arrayOfObject = { constraint.getName(), ValuePool.getInt(5), Boolean.valueOf(bool), Boolean.valueOf(false) };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 1078, arrayOfObject, null, arrayOfHsqlName);
  }
  
  StatementSession compileDeclareLocalTableOrNull() {
    int i = getPosition();
    try {
      readThis(77);
      readThis(157);
      readThis(523);
      readThis(278);
    } catch (HsqlException hsqlException) {
      this.lastError = hsqlException;
      rewind(i);
      return null;
    } 
    if (this.token.namePrePrefix == null && (this.token.namePrefix == null || "MODULE".equals(this.token.namePrefix) || "SESSION".equals(this.token.namePrefix))) {
      HsqlNameManager.HsqlName hsqlName = readNewSchemaObjectName(3, false);
      hsqlName.schema = SqlInvariants.MODULE_HSQLNAME;
      Table table = new Table(this.database, hsqlName, 3);
      StatementSchema statementSchema = compileCreateTableBody(table, false);
      HsqlArrayList hsqlArrayList = (HsqlArrayList)statementSchema.arguments[1];
      for (byte b = 0; b < hsqlArrayList.size(); b++) {
        Constraint constraint = (Constraint)hsqlArrayList.get(b);
        if (constraint.getConstraintType() == 0)
          throw unexpectedToken("FOREIGN"); 
      } 
      return new StatementSession(1068, statementSchema.arguments);
    } 
    throw unexpectedToken();
  }
  
  StatementSchema compileCreateTable(int paramInt) {
    TextTable textTable;
    boolean bool = false;
    if (this.token.tokenType == 412) {
      int i = getPosition();
      read();
      if (this.token.tokenType == 183) {
        read();
        readThis(101);
        bool = true;
      } else {
        rewind(i);
      } 
    } 
    HsqlNameManager.HsqlName hsqlName = readNewSchemaObjectName(3, false);
    hsqlName.setSchemaIfNull(this.session.getCurrentSchemaHsqlName());
    switch (paramInt) {
      case 6:
      case 7:
        textTable = new TextTable(this.database, hsqlName, paramInt);
        return compileCreateTableBody(textTable, bool);
    } 
    Table table = new Table(this.database, hsqlName, paramInt);
    return compileCreateTableBody(table, bool);
  }
  
  StatementSchema compileCreateTableBody(Table paramTable, boolean paramBoolean) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    if (this.token.tokenType == 10)
      return readTableAsSubqueryDefinition(paramTable); 
    int i = getPosition();
    readThis(816);
    Constraint constraint = new Constraint(null, null, 5);
    hsqlArrayList.add(constraint);
    boolean bool1 = true;
    boolean bool2 = true;
    boolean bool3 = false;
    while (!bool3) {
      ColumnSchema[] arrayOfColumnSchema;
      byte b1;
      switch (this.token.tokenType) {
        case 154:
          arrayOfColumnSchema = readLikeTable(paramTable);
          for (b1 = 0; b1 < arrayOfColumnSchema.length; b1++)
            paramTable.addColumn(arrayOfColumnSchema[b1]); 
          bool1 = false;
          bool2 = false;
          continue;
        case 37:
        case 48:
        case 113:
        case 214:
        case 299:
          if (!bool2)
            throw unexpectedToken(); 
          readConstraint(paramTable, hsqlArrayList);
          bool1 = false;
          bool2 = false;
          continue;
        case 804:
          if (bool2)
            throw unexpectedToken(); 
          read();
          bool2 = true;
          continue;
        case 802:
          read();
          bool3 = true;
          continue;
      } 
      if (!bool2)
        throw unexpectedToken(); 
      checkIsSchemaObjectName();
      HsqlNameManager.HsqlName hsqlName = this.database.nameManager.newColumnHsqlName(paramTable.getName(), this.token.tokenString, isDelimitedIdentifier());
      read();
      ColumnSchema columnSchema = readColumnDefinitionOrNull(paramTable, hsqlName, hsqlArrayList);
      if (columnSchema == null) {
        if (bool1) {
          rewind(i);
          return readTableAsSubqueryDefinition(paramTable);
        } 
        throw Error.error(5000);
      } 
      paramTable.addColumn(columnSchema);
      bool1 = false;
      bool2 = false;
    } 
    if (paramTable.getColumnCount() == 0)
      throw Error.error(5591); 
    if (this.token.tokenType == 194) {
      if (!paramTable.isTemp())
        throw unexpectedToken(); 
      read();
      readThis(44);
      if (this.token.tokenType != 79 && this.token.tokenType == 476)
        paramTable.persistenceScope = 23; 
      read();
      readThis(245);
    } 
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    orderedHashSet.add(this.database.getCatalogName());
    for (byte b = 0; b < hsqlArrayList.size(); b++) {
      Constraint constraint1 = (Constraint)hsqlArrayList.get(b);
      HsqlNameManager.HsqlName hsqlName = constraint1.getMainTableName();
      if (hsqlName != null) {
        Table table = this.database.schemaManager.findUserTable(null, hsqlName.name, hsqlName.schema.name);
        if (table != null && !table.isTemp())
          orderedHashSet.add(paramTable.getName()); 
      } 
    } 
    String str = getLastPart();
    Object[] arrayOfObject = { paramTable, hsqlArrayList, null, Boolean.valueOf(paramBoolean) };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = new HsqlNameManager.HsqlName[orderedHashSet.size()];
    orderedHashSet.toArray((Object[])arrayOfHsqlName);
    return new StatementSchema(str, 77, arrayOfObject, null, arrayOfHsqlName);
  }
  
  private ColumnSchema[] readLikeTable(Table paramTable) {
    read();
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    Table table = readTableName();
    OrderedIntHashSet orderedIntHashSet = new OrderedIntHashSet();
    while (true) {
      ColumnSchema[] arrayOfColumnSchema1;
      ColumnSchema[] arrayOfColumnSchema2;
      ColumnSchema[] arrayOfColumnSchema3;
      ColumnSchema[] arrayOfColumnSchema4;
      boolean bool = (this.token.tokenType == 416) ? true : false;
      if (!bool && this.token.tokenType != 399) {
        arrayOfColumnSchema4 = new ColumnSchema[table.getColumnCount()];
        for (byte b = 0; b < arrayOfColumnSchema4.length; b++) {
          ColumnSchema columnSchema = table.getColumn(b).duplicate();
          HsqlNameManager.HsqlName hsqlName = this.database.nameManager.newColumnSchemaHsqlName(paramTable.getName(), columnSchema.getName());
          columnSchema.setName(hsqlName);
          columnSchema.setNullable(true);
          columnSchema.setPrimaryKey(false);
          if (bool2) {
            if (columnSchema.isIdentity())
              columnSchema.setIdentity(columnSchema.getIdentitySequence().duplicate()); 
          } else {
            columnSchema.setIdentity((NumberSequence)null);
          } 
          if (!bool3)
            columnSchema.setDefaultExpression((Expression)null); 
          if (!bool1)
            columnSchema.setGeneratingExpression((Expression)null); 
          arrayOfColumnSchema4[b] = columnSchema;
        } 
        return arrayOfColumnSchema4;
      } 
      read();
      switch (this.token.tokenType) {
        case 407:
          if (!orderedIntHashSet.add(this.token.tokenType))
            throw unexpectedToken(); 
          arrayOfColumnSchema1 = arrayOfColumnSchema4;
          break;
        case 128:
          if (!orderedIntHashSet.add(this.token.tokenType))
            throw unexpectedToken(); 
          arrayOfColumnSchema2 = arrayOfColumnSchema4;
          break;
        case 381:
          if (!orderedIntHashSet.add(this.token.tokenType))
            throw unexpectedToken(); 
          arrayOfColumnSchema3 = arrayOfColumnSchema4;
          break;
        default:
          throw unexpectedToken();
      } 
      read();
    } 
  }
  
  StatementSchema readTableAsSubqueryDefinition(Table paramTable) {
    HsqlNameManager.HsqlName[] arrayOfHsqlName1 = null;
    boolean bool = true;
    HsqlNameManager.HsqlName[] arrayOfHsqlName2 = null;
    StatementQuery statementQuery = null;
    if (this.token.tokenType == 816)
      arrayOfHsqlName2 = readColumnNames(paramTable.getName()); 
    readThis(10);
    readThis(816);
    QueryExpression queryExpression = XreadQueryExpression();
    queryExpression.setReturningResult();
    queryExpression.resolve(this.session);
    readThis(802);
    readThis(319);
    if (this.token.tokenType == 180) {
      read();
      bool = false;
    } else if (paramTable.getTableType() == 7) {
      throw unexpectedTokenRequire("NO");
    } 
    readThis(378);
    if (this.token.tokenType == 194) {
      if (!paramTable.isTemp())
        throw unexpectedToken(); 
      read();
      readThis(44);
      if (this.token.tokenType != 79 && this.token.tokenType == 476)
        paramTable.persistenceScope = 23; 
      read();
      readThis(245);
    } 
    if (arrayOfHsqlName2 == null) {
      arrayOfHsqlName2 = queryExpression.getResultColumnNames();
    } else if (arrayOfHsqlName2.length != queryExpression.getColumnCount()) {
      throw Error.error(5593);
    } 
    TableUtil.setColumnsInSchemaTable(paramTable, arrayOfHsqlName2, queryExpression.getColumnTypes());
    paramTable.createPrimaryKey();
    if (paramTable.isTemp() && paramTable.hasLobColumn())
      throw Error.error(5534); 
    if (bool) {
      statementQuery = new StatementQuery(this.session, queryExpression, this.compileContext);
      arrayOfHsqlName1 = statementQuery.getTableNamesForRead();
    } 
    Object[] arrayOfObject = { paramTable, new HsqlArrayList(), statementQuery, Boolean.FALSE };
    String str = getLastPart();
    HsqlNameManager.HsqlName[] arrayOfHsqlName3 = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str, 77, arrayOfObject, arrayOfHsqlName1, arrayOfHsqlName3);
  }
  
  static Table addTableConstraintDefinitions(Session paramSession, Table paramTable, HsqlArrayList paramHsqlArrayList1, HsqlArrayList paramHsqlArrayList2, boolean paramBoolean) {
    Constraint constraint = (Constraint)paramHsqlArrayList1.get(0);
    String str = (constraint.getName() == null) ? null : (constraint.getName()).name;
    HsqlNameManager.HsqlName hsqlName = paramSession.database.nameManager.newAutoName("IDX", str, paramTable.getSchemaName(), paramTable.getName(), 20);
    constraint.setColumnsIndexes(paramTable);
    paramTable.createPrimaryKey(hsqlName, constraint.core.mainCols, true);
    if (constraint.core.mainCols != null) {
      Constraint constraint1 = new Constraint(constraint.getName(), paramTable, paramTable.getPrimaryIndex(), 4);
      paramTable.addConstraint(constraint1);
      if (paramBoolean)
        paramSession.database.schemaManager.addSchemaObject(constraint1); 
    } 
    for (byte b = 1; b < paramHsqlArrayList1.size(); b++) {
      Index index;
      Constraint constraint1;
      constraint = (Constraint)paramHsqlArrayList1.get(b);
      switch (constraint.constType) {
        case 2:
          constraint.setColumnsIndexes(paramTable);
          if (paramTable.getUniqueConstraintForColumns(constraint.core.mainCols) != null)
            throw Error.error(5522); 
          hsqlName = paramSession.database.nameManager.newAutoName("IDX", (constraint.getName()).name, paramTable.getSchemaName(), paramTable.getName(), 20);
          index = paramTable.createAndAddIndexStructure(paramSession, hsqlName, constraint.core.mainCols, null, null, true, true, false);
          constraint1 = new Constraint(constraint.getName(), paramTable, index, 2);
          paramTable.addConstraint(constraint1);
          if (paramBoolean)
            paramSession.database.schemaManager.addSchemaObject(constraint1); 
          break;
        case 0:
          addForeignKey(paramSession, paramTable, constraint, paramHsqlArrayList2);
          break;
        case 3:
          try {
            constraint.prepareCheckConstraint(paramSession, paramTable);
          } catch (HsqlException hsqlException) {
            if (paramSession.isProcessingScript())
              break; 
            throw hsqlException;
          } 
          paramTable.addConstraint(constraint);
          if (constraint.isNotNull()) {
            ColumnSchema columnSchema = paramTable.getColumn(constraint.notNullColumnIndex);
            columnSchema.setNullable(false);
            paramTable.setColumnTypeVars(constraint.notNullColumnIndex);
          } 
          if (paramBoolean)
            paramSession.database.schemaManager.addSchemaObject(constraint); 
          break;
      } 
    } 
    return paramTable;
  }
  
  static void addForeignKey(Session paramSession, Table paramTable, Constraint paramConstraint, HsqlArrayList paramHsqlArrayList) {
    HsqlNameManager.HsqlName hsqlName1 = paramConstraint.getMainTableName();
    if (hsqlName1 == paramTable.getName()) {
      paramConstraint.core.mainTable = paramTable;
    } else {
      Table table = paramSession.database.schemaManager.findUserTable(paramSession, hsqlName1.name, hsqlName1.schema.name);
      if (table == null) {
        if (paramHsqlArrayList == null)
          throw Error.error(5501, hsqlName1.name); 
        paramHsqlArrayList.add(paramConstraint);
        return;
      } 
      paramConstraint.core.mainTable = table;
    } 
    paramConstraint.setColumnsIndexes(paramTable);
    TableWorks tableWorks = new TableWorks(paramSession, paramTable);
    tableWorks.checkCreateForeignKey(paramConstraint);
    Constraint constraint = paramConstraint.core.mainTable.getUniqueConstraintForColumns(paramConstraint.core.mainCols);
    if (constraint == null)
      throw Error.error(5523); 
    Index index1 = constraint.getMainIndex();
    boolean bool = (paramConstraint.core.mainTable.getSchemaName() != paramTable.getSchemaName()) ? true : false;
    int i = paramSession.database.schemaManager.getTableIndex(paramTable);
    if (i != -1 && i < paramSession.database.schemaManager.getTableIndex(paramConstraint.core.mainTable))
      bool = true; 
    HsqlNameManager.HsqlName hsqlName2 = paramSession.database.nameManager.newAutoName("IDX", paramTable.getSchemaName(), paramTable.getName(), 20);
    Index index2 = paramTable.createAndAddIndexStructure(paramSession, hsqlName2, paramConstraint.core.refCols, null, null, false, true, bool);
    HsqlNameManager.HsqlName hsqlName3 = paramSession.database.nameManager.newAutoName("REF", (paramConstraint.getName()).name, paramTable.getSchemaName(), paramTable.getName(), 20);
    paramConstraint.core.uniqueName = constraint.getName();
    paramConstraint.core.mainName = hsqlName3;
    paramConstraint.core.mainIndex = index1;
    paramConstraint.core.refTable = paramTable;
    paramConstraint.core.refName = paramConstraint.getName();
    paramConstraint.core.refIndex = index2;
    paramConstraint.isForward = bool;
    paramTable.addConstraint(paramConstraint);
    paramConstraint.core.mainTable.addConstraint(new Constraint(hsqlName3, paramConstraint));
    paramSession.database.schemaManager.addSchemaObject(paramConstraint);
  }
  
  private Constraint readFKReferences(Table paramTable, HsqlNameManager.HsqlName paramHsqlName, OrderedHashSet paramOrderedHashSet) {
    HsqlNameManager.HsqlName hsqlName1;
    HsqlNameManager.HsqlName hsqlName2;
    OrderedHashSet orderedHashSet = null;
    readThis(222);
    if (this.token.namePrefix == null) {
      hsqlName2 = paramTable.getSchemaName();
    } else {
      hsqlName2 = this.database.schemaManager.getSchemaHsqlName(this.token.namePrefix);
    } 
    if (paramTable.getSchemaName() == hsqlName2 && (paramTable.getName()).name.equals(this.token.tokenString)) {
      hsqlName1 = paramTable.getName();
      read();
    } else {
      hsqlName1 = readFKTableName(hsqlName2);
    } 
    if (this.token.tokenType == 816)
      orderedHashSet = readColumnNames(false); 
    byte b1 = 59;
    if (this.token.tokenType == 162) {
      read();
      switch (this.token.tokenType) {
        case 511:
          read();
          break;
        case 470:
          throw unsupportedFeature();
        case 116:
          read();
          b1 = 61;
          break;
        default:
          throw unexpectedToken();
      } 
    } 
    byte b2 = 3;
    byte b3 = 3;
    OrderedIntHashSet orderedIntHashSet = new OrderedIntHashSet();
    while (this.token.tokenType == 194) {
      read();
      if (!orderedIntHashSet.add(this.token.tokenType))
        throw unexpectedToken(); 
      if (this.token.tokenType == 79) {
        read();
        if (this.token.tokenType == 254) {
          read();
          switch (this.token.tokenType) {
            case 78:
              read();
              b2 = 4;
              continue;
            case 186:
              read();
              b2 = 2;
              continue;
          } 
          throw unexpectedToken();
        } 
        if (this.token.tokenType == 347) {
          read();
          b2 = 0;
          continue;
        } 
        if (this.token.tokenType == 485) {
          read();
          continue;
        } 
        readThis(180);
        readThis(332);
        continue;
      } 
      if (this.token.tokenType == 303) {
        read();
        if (this.token.tokenType == 254) {
          read();
          switch (this.token.tokenType) {
            case 78:
              read();
              b3 = 4;
              continue;
            case 186:
              read();
              b3 = 2;
              continue;
          } 
          throw unexpectedToken();
        } 
        if (this.token.tokenType == 347) {
          read();
          b3 = 0;
          continue;
        } 
        if (this.token.tokenType == 485) {
          read();
          continue;
        } 
        readThis(180);
        readThis(332);
        continue;
      } 
      throw unexpectedToken();
    } 
    if (paramHsqlName == null)
      paramHsqlName = this.database.nameManager.newAutoName("FK", paramTable.getSchemaName(), paramTable.getName(), 5); 
    return new Constraint(paramHsqlName, paramTable.getName(), paramOrderedHashSet, hsqlName1, orderedHashSet, 0, b2, b3, b1);
  }
  
  private HsqlNameManager.HsqlName readFKTableName(HsqlNameManager.HsqlName paramHsqlName) {
    HsqlNameManager.HsqlName hsqlName;
    checkIsSchemaObjectName();
    Table table = this.database.schemaManager.findUserTable(this.session, this.token.tokenString, paramHsqlName.name);
    if (table == null) {
      hsqlName = this.database.nameManager.newHsqlName(paramHsqlName, this.token.tokenString, isDelimitedIdentifier(), 3);
    } else {
      hsqlName = table.getName();
    } 
    read();
    return hsqlName;
  }
  
  StatementSchema compileCreateView(boolean paramBoolean1, boolean paramBoolean2) {
    QueryExpression queryExpression;
    read();
    HsqlNameManager.HsqlName hsqlName = readNewSchemaObjectName(4, true);
    hsqlName.setSchemaIfNull(this.session.getCurrentSchemaHsqlName());
    checkSchemaUpdateAuthorisation(hsqlName.schema);
    HsqlNameManager.HsqlName[] arrayOfHsqlName1 = null;
    if (this.token.tokenType == 816)
      try {
        arrayOfHsqlName1 = readColumnNames(hsqlName);
      } catch (HsqlException hsqlException) {
        if (this.session.isProcessingScript() && this.database.getProperties().isVersion18()) {
          while (this.token.tokenType != 10)
            read(); 
        } else {
          throw hsqlException;
        } 
      }  
    readThis(10);
    startRecording();
    int i = getPosition();
    try {
      queryExpression = XreadQueryExpression();
    } catch (HsqlException hsqlException) {
      queryExpression = XreadJoinedTableAsView();
    } 
    Token[] arrayOfToken = getRecordedStatement();
    byte b = 0;
    if (this.token.tokenType == 319) {
      read();
      b = 2;
      if (readIfThis(157)) {
        b = 1;
      } else {
        readIfThis(28);
      } 
      readThis(37);
      readThis(455);
    } 
    View view = new View(this.database, hsqlName, arrayOfHsqlName1, b);
    queryExpression.setView(view);
    queryExpression.resolve(this.session);
    view.setStatement(Token.getSQL(arrayOfToken));
    StatementQuery statementQuery = new StatementQuery(this.session, queryExpression, this.compileContext);
    String str = getLastPart();
    Object[] arrayOfObject = { view };
    boolean bool = paramBoolean1 ? true : true;
    HsqlNameManager.HsqlName[] arrayOfHsqlName2 = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str, bool, arrayOfObject, statementQuery.readTableNames, arrayOfHsqlName2);
  }
  
  StatementSchema compileCreateSequence() {
    read();
    boolean bool = false;
    if (this.token.tokenType == 412) {
      int i = getPosition();
      read();
      if (this.token.tokenType == 183) {
        read();
        readThis(101);
        bool = true;
      } else {
        rewind(i);
      } 
    } 
    HsqlNameManager.HsqlName hsqlName = readNewSchemaObjectName(7, false);
    NumberSequence numberSequence = new NumberSequence(hsqlName, (Type)Type.SQL_INTEGER);
    readSequenceOptions(numberSequence, true, false, false);
    String str = getLastPart();
    Object[] arrayOfObject = { numberSequence, Boolean.valueOf(bool) };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str, 133, arrayOfObject, null, arrayOfHsqlName);
  }
  
  StatementSchema compileCreateDomain() {
    UserTypeModifier userTypeModifier = null;
    read();
    HsqlNameManager.HsqlName hsqlName = readNewSchemaObjectName(13, false);
    readIfThis(10);
    Type type = readTypeDefinition(true, false).duplicate();
    Expression expression = null;
    if (readIfThis(78))
      expression = readDefaultClause(type); 
    userTypeModifier = new UserTypeModifier(hsqlName, 13, type);
    userTypeModifier.setDefaultClause(expression);
    type.userTypeModifier = userTypeModifier;
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    this.compileContext.currentDomain = type;
    while (true) {
      byte b = 0;
      switch (this.token.tokenType) {
        case 37:
        case 48:
          readConstraint((SchemaObject)type, hsqlArrayList);
          break;
        default:
          b = 1;
          break;
      } 
      if (b) {
        this.compileContext.currentDomain = null;
        for (b = 0; b < hsqlArrayList.size(); b++) {
          Constraint constraint = (Constraint)hsqlArrayList.get(b);
          constraint.prepareCheckConstraint(this.session, null);
          userTypeModifier.addConstraint(constraint);
        } 
        String str = getLastPart();
        Object[] arrayOfObject = { type };
        HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
        return new StatementSchema(str, 23, arrayOfObject, null, arrayOfHsqlName);
      } 
    } 
  }
  
  StatementSchema compileCreateType(boolean paramBoolean) {
    read();
    HsqlNameManager.HsqlName hsqlName = readNewSchemaObjectName(12, false);
    readThis(10);
    Type type = readTypeDefinition(true, false).duplicate();
    readIfThis(400);
    UserTypeModifier userTypeModifier = new UserTypeModifier(hsqlName, 12, type);
    type.userTypeModifier = userTypeModifier;
    String str = getLastPart();
    Object[] arrayOfObject = { type };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str, 83, arrayOfObject, null, arrayOfHsqlName);
  }
  
  StatementSchema compileCreateCharacterSet() {
    read();
    readThis(254);
    HsqlNameManager.HsqlName hsqlName = readNewSchemaObjectName(14, false);
    readIfThis(10);
    readThis(119);
    String str1 = this.token.namePrefix;
    Charset charset1 = (Charset)this.database.schemaManager.getCharacterSet(this.session, this.token.tokenString, str1);
    read();
    if (this.token.tokenType == 358) {
      read();
      readThis(115);
      readThis(78);
    } 
    Charset charset2 = new Charset(hsqlName);
    charset2.base = charset1.getName();
    String str2 = getLastPart();
    Object[] arrayOfObject = { charset2 };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str2, 8, arrayOfObject, null, arrayOfHsqlName);
  }
  
  StatementSchema compileCreateCollation() {
    read();
    HsqlNameManager.HsqlName hsqlName1 = readNewSchemaObjectName(15, false);
    hsqlName1.setSchemaIfNull(this.session.getCurrentSchemaHsqlName());
    readThis(112);
    HsqlNameManager.HsqlName hsqlName2 = readNewSchemaObjectName(14, false);
    readThis(115);
    HsqlNameManager.HsqlName hsqlName3 = readNewSchemaObjectName(15, false);
    Boolean bool = null;
    if (readIfThis(180)) {
      readThis(463);
      bool = Boolean.FALSE;
    } else if (readIfThis(463)) {
      readThis(514);
      bool = Boolean.TRUE;
    } 
    String str1 = (hsqlName2.schema == null) ? null : hsqlName2.schema.name;
    Charset charset = (Charset)this.database.schemaManager.getCharacterSet(this.session, hsqlName2.name, str1);
    if (charset == null)
      throw Error.error(5501, hsqlName2.getSchemaQualifiedStatementName()); 
    str1 = (hsqlName3.schema == null) ? null : hsqlName3.schema.name;
    Collation collation1 = this.database.schemaManager.getCollation(this.session, hsqlName3.name, str1);
    Collation collation2 = new Collation(hsqlName1, collation1, charset, bool);
    String str2 = getLastPart();
    Object[] arrayOfObject = { collation2 };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str2, 10, arrayOfObject, null, arrayOfHsqlName);
  }
  
  StatementSchema compileCreateAlias() {
    String str1;
    HsqlNameManager.HsqlName hsqlName = null;
    Routine[] arrayOfRoutine = null;
    String str2 = null;
    if (!this.session.isProcessingScript())
      throw unsupportedFeature(); 
    read();
    try {
      str1 = this.token.tokenString;
      read();
      readThis(112);
      str2 = this.token.tokenString;
      read();
    } catch (HsqlException hsqlException) {
      str1 = null;
    } 
    if (str1 != null) {
      HsqlNameManager.HsqlName hsqlName1 = this.database.schemaManager.getDefaultSchemaHsqlName();
      hsqlName = this.database.nameManager.newHsqlName(hsqlName1, str1, 16);
      Method[] arrayOfMethod = Routine.getMethods(str2);
      arrayOfRoutine = Routine.newRoutines(this.session, arrayOfMethod);
    } 
    String str3 = getLastPart();
    Object[] arrayOfObject = { hsqlName, arrayOfRoutine };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str3, 1072, arrayOfObject, null, arrayOfHsqlName);
  }
  
  StatementSchema compileCreateTrigger(boolean paramBoolean) {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: iconst_0
    //   3: istore #4
    //   5: iconst_0
    //   6: istore #5
    //   8: iconst_0
    //   9: istore #6
    //   11: aconst_null
    //   12: astore #12
    //   14: aconst_null
    //   15: astore #13
    //   17: aconst_null
    //   18: astore #14
    //   20: aload_0
    //   21: invokevirtual read : ()V
    //   24: aload_0
    //   25: bipush #8
    //   27: iconst_1
    //   28: invokevirtual readNewSchemaObjectName : (IZ)Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   31: astore #11
    //   33: aload_0
    //   34: getfield token : Lorg/hsqldb/Token;
    //   37: getfield tokenType : I
    //   40: lookupswitch default -> 117, 336 -> 98, 343 -> 98, 422 -> 76
    //   76: sipush #422
    //   79: invokestatic getTiming : (I)I
    //   82: istore #7
    //   84: aload_0
    //   85: invokevirtual read : ()V
    //   88: aload_0
    //   89: sipush #191
    //   92: invokevirtual readThis : (I)V
    //   95: goto -> 122
    //   98: aload_0
    //   99: getfield token : Lorg/hsqldb/Token;
    //   102: getfield tokenType : I
    //   105: invokestatic getTiming : (I)I
    //   108: istore #7
    //   110: aload_0
    //   111: invokevirtual read : ()V
    //   114: goto -> 122
    //   117: aload_0
    //   118: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   121: athrow
    //   122: aload_0
    //   123: getfield token : Lorg/hsqldb/Token;
    //   126: getfield tokenType : I
    //   129: lookupswitch default -> 243, 79 -> 164, 135 -> 164, 303 -> 183
    //   164: aload_0
    //   165: getfield token : Lorg/hsqldb/Token;
    //   168: getfield tokenType : I
    //   171: invokestatic getOperationType : (I)I
    //   174: istore #8
    //   176: aload_0
    //   177: invokevirtual read : ()V
    //   180: goto -> 248
    //   183: aload_0
    //   184: getfield token : Lorg/hsqldb/Token;
    //   187: getfield tokenType : I
    //   190: invokestatic getOperationType : (I)I
    //   193: istore #8
    //   195: aload_0
    //   196: invokevirtual read : ()V
    //   199: aload_0
    //   200: getfield token : Lorg/hsqldb/Token;
    //   203: getfield tokenType : I
    //   206: sipush #191
    //   209: if_icmpne -> 248
    //   212: iload #7
    //   214: bipush #6
    //   216: if_icmpeq -> 248
    //   219: aload_0
    //   220: invokevirtual read : ()V
    //   223: new org/hsqldb/lib/OrderedHashSet
    //   226: dup
    //   227: invokespecial <init> : ()V
    //   230: astore #13
    //   232: aload_0
    //   233: aload #13
    //   235: aconst_null
    //   236: iconst_0
    //   237: invokevirtual readColumnNameList : (Lorg/hsqldb/lib/OrderedHashSet;Lorg/hsqldb/map/BitMap;Z)V
    //   240: goto -> 248
    //   243: aload_0
    //   244: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   247: athrow
    //   248: aload_0
    //   249: sipush #194
    //   252: invokevirtual readThis : (I)V
    //   255: aload_0
    //   256: invokevirtual readTableName : ()Lorg/hsqldb/Table;
    //   259: astore_2
    //   260: aload_0
    //   261: getfield token : Lorg/hsqldb/Token;
    //   264: getfield tokenType : I
    //   267: sipush #343
    //   270: if_icmpne -> 290
    //   273: aload_0
    //   274: invokevirtual read : ()V
    //   277: aload_0
    //   278: invokevirtual checkIsSimpleName : ()V
    //   281: aload_0
    //   282: bipush #8
    //   284: iconst_1
    //   285: invokevirtual readNewSchemaObjectName : (IZ)Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   288: astore #12
    //   290: aload #11
    //   292: aload_2
    //   293: invokevirtual getSchemaName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   296: invokevirtual setSchemaIfNull : (Lorg/hsqldb/HsqlNameManager$HsqlName;)V
    //   299: aload_0
    //   300: aload #11
    //   302: getfield schema : Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   305: invokevirtual checkSchemaUpdateAuthorisation : (Lorg/hsqldb/HsqlNameManager$HsqlName;)V
    //   308: iload #7
    //   310: bipush #6
    //   312: if_icmpne -> 348
    //   315: aload_2
    //   316: invokevirtual isView : ()Z
    //   319: ifeq -> 333
    //   322: aload_2
    //   323: checkcast org/hsqldb/View
    //   326: invokevirtual getCheckOption : ()I
    //   329: iconst_2
    //   330: if_icmpne -> 370
    //   333: sipush #5538
    //   336: aload #11
    //   338: getfield schema : Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   341: getfield name : Ljava/lang/String;
    //   344: invokestatic error : (ILjava/lang/String;)Lorg/hsqldb/HsqlException;
    //   347: athrow
    //   348: aload_2
    //   349: invokevirtual isView : ()Z
    //   352: ifeq -> 370
    //   355: sipush #5538
    //   358: aload #11
    //   360: getfield schema : Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   363: getfield name : Ljava/lang/String;
    //   366: invokestatic error : (ILjava/lang/String;)Lorg/hsqldb/HsqlException;
    //   369: athrow
    //   370: aload #11
    //   372: getfield schema : Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   375: aload_2
    //   376: invokevirtual getSchemaName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   379: if_acmpeq -> 397
    //   382: sipush #5505
    //   385: aload #11
    //   387: getfield schema : Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   390: getfield name : Ljava/lang/String;
    //   393: invokestatic error : (ILjava/lang/String;)Lorg/hsqldb/HsqlException;
    //   396: athrow
    //   397: aload #11
    //   399: aload_2
    //   400: invokevirtual getName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   403: putfield parent : Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   406: aload_0
    //   407: getfield database : Lorg/hsqldb/Database;
    //   410: getfield schemaManager : Lorg/hsqldb/SchemaManager;
    //   413: aload #11
    //   415: invokevirtual checkSchemaObjectNotExists : (Lorg/hsqldb/HsqlNameManager$HsqlName;)V
    //   418: aload #13
    //   420: ifnull -> 474
    //   423: aload_2
    //   424: aload #13
    //   426: invokevirtual getColumnIndexes : (Lorg/hsqldb/lib/OrderedHashSet;)[I
    //   429: astore #14
    //   431: iconst_0
    //   432: istore #15
    //   434: iload #15
    //   436: aload #14
    //   438: arraylength
    //   439: if_icmpge -> 474
    //   442: aload #14
    //   444: iload #15
    //   446: iaload
    //   447: iconst_m1
    //   448: if_icmpne -> 468
    //   451: sipush #5544
    //   454: aload #13
    //   456: iload #15
    //   458: invokevirtual get : (I)Ljava/lang/Object;
    //   461: checkcast java/lang/String
    //   464: invokestatic error : (ILjava/lang/String;)Lorg/hsqldb/HsqlException;
    //   467: athrow
    //   468: iinc #15, 1
    //   471: goto -> 434
    //   474: aconst_null
    //   475: astore #15
    //   477: aconst_null
    //   478: astore #16
    //   480: aconst_null
    //   481: astore #17
    //   483: aconst_null
    //   484: astore #18
    //   486: aconst_null
    //   487: astore #19
    //   489: iconst_4
    //   490: anewarray org/hsqldb/Table
    //   493: astore #20
    //   495: iconst_4
    //   496: anewarray org/hsqldb/RangeVariable
    //   499: astore #21
    //   501: aconst_null
    //   502: astore #22
    //   504: iconst_1
    //   505: anewarray org/hsqldb/RangeGroup
    //   508: dup
    //   509: iconst_0
    //   510: new org/hsqldb/RangeGroup$RangeGroupSimple
    //   513: dup
    //   514: aload #21
    //   516: iconst_0
    //   517: invokespecial <init> : ([Lorg/hsqldb/RangeVariable;Z)V
    //   520: aastore
    //   521: astore #23
    //   523: aload_0
    //   524: getfield token : Lorg/hsqldb/Token;
    //   527: getfield tokenType : I
    //   530: sipush #223
    //   533: if_icmpne -> 1277
    //   536: aload_0
    //   537: invokevirtual read : ()V
    //   540: aload_0
    //   541: getfield token : Lorg/hsqldb/Token;
    //   544: getfield tokenType : I
    //   547: sipush #193
    //   550: if_icmpeq -> 571
    //   553: aload_0
    //   554: getfield token : Lorg/hsqldb/Token;
    //   557: getfield tokenType : I
    //   560: sipush #179
    //   563: if_icmpeq -> 571
    //   566: aload_0
    //   567: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   570: athrow
    //   571: aload_0
    //   572: getfield token : Lorg/hsqldb/Token;
    //   575: getfield tokenType : I
    //   578: sipush #193
    //   581: if_icmpne -> 924
    //   584: iload #8
    //   586: bipush #50
    //   588: if_icmpne -> 596
    //   591: aload_0
    //   592: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   595: athrow
    //   596: aload_0
    //   597: invokevirtual read : ()V
    //   600: aload_0
    //   601: getfield token : Lorg/hsqldb/Token;
    //   604: getfield tokenType : I
    //   607: sipush #278
    //   610: if_icmpne -> 779
    //   613: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   616: aload_3
    //   617: invokevirtual equals : (Ljava/lang/Object;)Z
    //   620: ifne -> 634
    //   623: aload #16
    //   625: ifnonnull -> 634
    //   628: iload #7
    //   630: iconst_4
    //   631: if_icmpne -> 639
    //   634: aload_0
    //   635: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   638: athrow
    //   639: aload_0
    //   640: invokevirtual read : ()V
    //   643: aload_0
    //   644: bipush #10
    //   646: invokevirtual readIfThis : (I)Z
    //   649: pop
    //   650: aload_0
    //   651: invokevirtual checkIsSimpleName : ()V
    //   654: aload_0
    //   655: invokevirtual read : ()V
    //   658: aload_0
    //   659: getfield token : Lorg/hsqldb/Token;
    //   662: getfield tokenString : Ljava/lang/String;
    //   665: astore #16
    //   667: aload #16
    //   669: astore #24
    //   671: aload #24
    //   673: aload #17
    //   675: invokevirtual equals : (Ljava/lang/Object;)Z
    //   678: ifne -> 701
    //   681: aload #24
    //   683: aload #18
    //   685: invokevirtual equals : (Ljava/lang/Object;)Z
    //   688: ifne -> 701
    //   691: aload #24
    //   693: aload #19
    //   695: invokevirtual equals : (Ljava/lang/Object;)Z
    //   698: ifeq -> 706
    //   701: aload_0
    //   702: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   705: athrow
    //   706: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   709: astore_3
    //   710: aload_0
    //   711: getfield database : Lorg/hsqldb/Database;
    //   714: getfield nameManager : Lorg/hsqldb/HsqlNameManager;
    //   717: aload_2
    //   718: invokevirtual getSchemaName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   721: aload #24
    //   723: aload_0
    //   724: invokevirtual isDelimitedIdentifier : ()Z
    //   727: bipush #10
    //   729: invokevirtual newHsqlName : (Lorg/hsqldb/HsqlNameManager$HsqlName;Ljava/lang/String;ZI)Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   732: astore #25
    //   734: new org/hsqldb/Table
    //   737: dup
    //   738: aload_2
    //   739: aload #25
    //   741: invokespecial <init> : (Lorg/hsqldb/Table;Lorg/hsqldb/HsqlNameManager$HsqlName;)V
    //   744: astore #26
    //   746: new org/hsqldb/RangeVariable
    //   749: dup
    //   750: aload #26
    //   752: aconst_null
    //   753: aconst_null
    //   754: aconst_null
    //   755: aload_0
    //   756: getfield compileContext : Lorg/hsqldb/ParserDQL$CompileContext;
    //   759: invokespecial <init> : (Lorg/hsqldb/Table;Lorg/hsqldb/HsqlNameManager$SimpleName;Lorg/hsqldb/lib/OrderedHashSet;[Lorg/hsqldb/HsqlNameManager$SimpleName;Lorg/hsqldb/ParserDQL$CompileContext;)V
    //   762: astore #27
    //   764: aload #20
    //   766: iconst_2
    //   767: aload #26
    //   769: aastore
    //   770: aload #21
    //   772: iconst_2
    //   773: aload #27
    //   775: aastore
    //   776: goto -> 571
    //   779: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   782: aload_3
    //   783: invokevirtual equals : (Ljava/lang/Object;)Z
    //   786: ifne -> 794
    //   789: aload #18
    //   791: ifnull -> 799
    //   794: aload_0
    //   795: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   798: athrow
    //   799: aload_0
    //   800: sipush #243
    //   803: invokevirtual readIfThis : (I)Z
    //   806: pop
    //   807: aload_0
    //   808: bipush #10
    //   810: invokevirtual readIfThis : (I)Z
    //   813: pop
    //   814: aload_0
    //   815: invokevirtual checkIsSimpleName : ()V
    //   818: aload_0
    //   819: getfield token : Lorg/hsqldb/Token;
    //   822: getfield tokenString : Ljava/lang/String;
    //   825: aload_0
    //   826: getfield token : Lorg/hsqldb/Token;
    //   829: getfield isDelimitedIdentifier : Z
    //   832: invokestatic getSimpleName : (Ljava/lang/String;Z)Lorg/hsqldb/HsqlNameManager$SimpleName;
    //   835: astore #18
    //   837: aload_0
    //   838: invokevirtual read : ()V
    //   841: aload #18
    //   843: getfield name : Ljava/lang/String;
    //   846: astore #24
    //   848: aload #24
    //   850: aload #17
    //   852: invokevirtual equals : (Ljava/lang/Object;)Z
    //   855: ifne -> 878
    //   858: aload #24
    //   860: aload #16
    //   862: invokevirtual equals : (Ljava/lang/Object;)Z
    //   865: ifne -> 878
    //   868: aload #24
    //   870: aload #19
    //   872: invokevirtual equals : (Ljava/lang/Object;)Z
    //   875: ifeq -> 883
    //   878: aload_0
    //   879: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   882: athrow
    //   883: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   886: astore_3
    //   887: new org/hsqldb/RangeVariable
    //   890: dup
    //   891: aload_2
    //   892: getfield columnList : Lorg/hsqldb/lib/HashMappedList;
    //   895: aload #18
    //   897: iconst_0
    //   898: iconst_2
    //   899: invokespecial <init> : (Lorg/hsqldb/lib/HashMappedList;Lorg/hsqldb/HsqlNameManager$SimpleName;ZI)V
    //   902: astore #25
    //   904: aload #25
    //   906: iconst_0
    //   907: putfield rangePosition : I
    //   910: aload #20
    //   912: iconst_0
    //   913: aconst_null
    //   914: aastore
    //   915: aload #21
    //   917: iconst_0
    //   918: aload #25
    //   920: aastore
    //   921: goto -> 571
    //   924: aload_0
    //   925: getfield token : Lorg/hsqldb/Token;
    //   928: getfield tokenType : I
    //   931: sipush #179
    //   934: if_icmpne -> 1277
    //   937: iload #8
    //   939: bipush #19
    //   941: if_icmpne -> 949
    //   944: aload_0
    //   945: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   948: athrow
    //   949: aload_0
    //   950: invokevirtual read : ()V
    //   953: aload_0
    //   954: getfield token : Lorg/hsqldb/Token;
    //   957: getfield tokenType : I
    //   960: sipush #278
    //   963: if_icmpne -> 1132
    //   966: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   969: aload_3
    //   970: invokevirtual equals : (Ljava/lang/Object;)Z
    //   973: ifne -> 987
    //   976: aload #17
    //   978: ifnonnull -> 987
    //   981: iload #7
    //   983: iconst_4
    //   984: if_icmpne -> 992
    //   987: aload_0
    //   988: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   991: athrow
    //   992: aload_0
    //   993: invokevirtual read : ()V
    //   996: aload_0
    //   997: bipush #10
    //   999: invokevirtual readIfThis : (I)Z
    //   1002: pop
    //   1003: aload_0
    //   1004: invokevirtual checkIsSimpleName : ()V
    //   1007: aload_0
    //   1008: getfield token : Lorg/hsqldb/Token;
    //   1011: getfield tokenString : Ljava/lang/String;
    //   1014: astore #17
    //   1016: aload_0
    //   1017: invokevirtual read : ()V
    //   1020: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   1023: astore_3
    //   1024: aload #17
    //   1026: astore #24
    //   1028: aload #24
    //   1030: aload #16
    //   1032: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1035: ifne -> 1058
    //   1038: aload #24
    //   1040: aload #18
    //   1042: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1045: ifne -> 1058
    //   1048: aload #24
    //   1050: aload #19
    //   1052: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1055: ifeq -> 1063
    //   1058: aload_0
    //   1059: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   1062: athrow
    //   1063: aload_0
    //   1064: getfield database : Lorg/hsqldb/Database;
    //   1067: getfield nameManager : Lorg/hsqldb/HsqlNameManager;
    //   1070: aload_2
    //   1071: invokevirtual getSchemaName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   1074: aload #24
    //   1076: aload_0
    //   1077: invokevirtual isDelimitedIdentifier : ()Z
    //   1080: bipush #10
    //   1082: invokevirtual newHsqlName : (Lorg/hsqldb/HsqlNameManager$HsqlName;Ljava/lang/String;ZI)Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   1085: astore #25
    //   1087: new org/hsqldb/Table
    //   1090: dup
    //   1091: aload_2
    //   1092: aload #25
    //   1094: invokespecial <init> : (Lorg/hsqldb/Table;Lorg/hsqldb/HsqlNameManager$HsqlName;)V
    //   1097: astore #26
    //   1099: new org/hsqldb/RangeVariable
    //   1102: dup
    //   1103: aload #26
    //   1105: aconst_null
    //   1106: aconst_null
    //   1107: aconst_null
    //   1108: aload_0
    //   1109: getfield compileContext : Lorg/hsqldb/ParserDQL$CompileContext;
    //   1112: invokespecial <init> : (Lorg/hsqldb/Table;Lorg/hsqldb/HsqlNameManager$SimpleName;Lorg/hsqldb/lib/OrderedHashSet;[Lorg/hsqldb/HsqlNameManager$SimpleName;Lorg/hsqldb/ParserDQL$CompileContext;)V
    //   1115: astore #27
    //   1117: aload #20
    //   1119: iconst_3
    //   1120: aload #26
    //   1122: aastore
    //   1123: aload #21
    //   1125: iconst_3
    //   1126: aload #27
    //   1128: aastore
    //   1129: goto -> 571
    //   1132: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   1135: aload_3
    //   1136: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1139: ifne -> 1147
    //   1142: aload #19
    //   1144: ifnull -> 1152
    //   1147: aload_0
    //   1148: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   1151: athrow
    //   1152: aload_0
    //   1153: sipush #243
    //   1156: invokevirtual readIfThis : (I)Z
    //   1159: pop
    //   1160: aload_0
    //   1161: bipush #10
    //   1163: invokevirtual readIfThis : (I)Z
    //   1166: pop
    //   1167: aload_0
    //   1168: invokevirtual checkIsSimpleName : ()V
    //   1171: aload_0
    //   1172: getfield token : Lorg/hsqldb/Token;
    //   1175: getfield tokenString : Ljava/lang/String;
    //   1178: aload_0
    //   1179: getfield token : Lorg/hsqldb/Token;
    //   1182: getfield isDelimitedIdentifier : Z
    //   1185: invokestatic getSimpleName : (Ljava/lang/String;Z)Lorg/hsqldb/HsqlNameManager$SimpleName;
    //   1188: astore #19
    //   1190: aload_0
    //   1191: invokevirtual read : ()V
    //   1194: aload #19
    //   1196: getfield name : Ljava/lang/String;
    //   1199: astore #24
    //   1201: aload #24
    //   1203: aload #16
    //   1205: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1208: ifne -> 1231
    //   1211: aload #24
    //   1213: aload #17
    //   1215: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1218: ifne -> 1231
    //   1221: aload #24
    //   1223: aload #18
    //   1225: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1228: ifeq -> 1236
    //   1231: aload_0
    //   1232: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   1235: athrow
    //   1236: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   1239: astore_3
    //   1240: new org/hsqldb/RangeVariable
    //   1243: dup
    //   1244: aload_2
    //   1245: getfield columnList : Lorg/hsqldb/lib/HashMappedList;
    //   1248: aload #19
    //   1250: iconst_0
    //   1251: iconst_2
    //   1252: invokespecial <init> : (Lorg/hsqldb/lib/HashMappedList;Lorg/hsqldb/HsqlNameManager$SimpleName;ZI)V
    //   1255: astore #25
    //   1257: aload #25
    //   1259: iconst_1
    //   1260: putfield rangePosition : I
    //   1263: aload #20
    //   1265: iconst_1
    //   1266: aconst_null
    //   1267: aastore
    //   1268: aload #21
    //   1270: iconst_1
    //   1271: aload #25
    //   1273: aastore
    //   1274: goto -> 571
    //   1277: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   1280: aload_3
    //   1281: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1284: ifeq -> 1307
    //   1287: aload_0
    //   1288: getfield token : Lorg/hsqldb/Token;
    //   1291: getfield tokenType : I
    //   1294: bipush #112
    //   1296: if_icmpeq -> 1307
    //   1299: aload_0
    //   1300: ldc_w 'FOR'
    //   1303: invokevirtual unexpectedTokenRequire : (Ljava/lang/String;)Lorg/hsqldb/HsqlException;
    //   1306: athrow
    //   1307: aload_0
    //   1308: getfield token : Lorg/hsqldb/Token;
    //   1311: getfield tokenType : I
    //   1314: bipush #112
    //   1316: if_icmpne -> 1414
    //   1319: aload_0
    //   1320: invokevirtual read : ()V
    //   1323: aload_0
    //   1324: bipush #90
    //   1326: invokevirtual readThis : (I)V
    //   1329: aload_0
    //   1330: getfield token : Lorg/hsqldb/Token;
    //   1333: getfield tokenType : I
    //   1336: sipush #243
    //   1339: if_icmpne -> 1364
    //   1342: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   1345: aload_3
    //   1346: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1349: ifeq -> 1357
    //   1352: aload_0
    //   1353: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   1356: athrow
    //   1357: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   1360: astore_3
    //   1361: goto -> 1410
    //   1364: aload_0
    //   1365: getfield token : Lorg/hsqldb/Token;
    //   1368: getfield tokenType : I
    //   1371: sipush #517
    //   1374: if_icmpne -> 1405
    //   1377: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   1380: aload_3
    //   1381: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1384: ifne -> 1393
    //   1387: iload #7
    //   1389: iconst_4
    //   1390: if_icmpne -> 1398
    //   1393: aload_0
    //   1394: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   1397: athrow
    //   1398: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   1401: astore_3
    //   1402: goto -> 1410
    //   1405: aload_0
    //   1406: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   1409: athrow
    //   1410: aload_0
    //   1411: invokevirtual read : ()V
    //   1414: aload #21
    //   1416: iconst_2
    //   1417: aaload
    //   1418: ifnull -> 1421
    //   1421: aload #21
    //   1423: iconst_3
    //   1424: aaload
    //   1425: ifnull -> 1428
    //   1428: ldc_w 'QUEUE'
    //   1431: aload_0
    //   1432: getfield token : Lorg/hsqldb/Token;
    //   1435: getfield tokenString : Ljava/lang/String;
    //   1438: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1441: ifeq -> 1457
    //   1444: aload_0
    //   1445: invokevirtual read : ()V
    //   1448: aload_0
    //   1449: invokevirtual readInteger : ()I
    //   1452: istore #6
    //   1454: iconst_1
    //   1455: istore #5
    //   1457: ldc_w 'NOWAIT'
    //   1460: aload_0
    //   1461: getfield token : Lorg/hsqldb/Token;
    //   1464: getfield tokenString : Ljava/lang/String;
    //   1467: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1470: ifeq -> 1480
    //   1473: aload_0
    //   1474: invokevirtual read : ()V
    //   1477: iconst_1
    //   1478: istore #4
    //   1480: aload_0
    //   1481: getfield token : Lorg/hsqldb/Token;
    //   1484: getfield tokenType : I
    //   1487: sipush #314
    //   1490: if_icmpne -> 1599
    //   1493: iload #7
    //   1495: bipush #6
    //   1497: if_icmpeq -> 1599
    //   1500: aload_0
    //   1501: invokevirtual read : ()V
    //   1504: aload_0
    //   1505: sipush #816
    //   1508: invokevirtual readThis : (I)V
    //   1511: aload_0
    //   1512: invokevirtual getPosition : ()I
    //   1515: istore #24
    //   1517: aload_0
    //   1518: iconst_1
    //   1519: putfield isCheckOrTriggerCondition : Z
    //   1522: aload_0
    //   1523: invokevirtual XreadBooleanValueExpression : ()Lorg/hsqldb/Expression;
    //   1526: astore #15
    //   1528: aload_0
    //   1529: iload #24
    //   1531: invokevirtual getLastPart : (I)Ljava/lang/String;
    //   1534: astore #22
    //   1536: aload_0
    //   1537: iconst_0
    //   1538: putfield isCheckOrTriggerCondition : Z
    //   1541: aload_0
    //   1542: sipush #802
    //   1545: invokevirtual readThis : (I)V
    //   1548: aload #15
    //   1550: aload_0
    //   1551: getfield session : Lorg/hsqldb/Session;
    //   1554: aload #23
    //   1556: iconst_0
    //   1557: aaload
    //   1558: aload #23
    //   1560: aconst_null
    //   1561: invokevirtual resolveColumnReferences : (Lorg/hsqldb/Session;Lorg/hsqldb/RangeGroup;[Lorg/hsqldb/RangeGroup;Lorg/hsqldb/lib/HsqlList;)Lorg/hsqldb/lib/HsqlList;
    //   1564: astore #25
    //   1566: aload #25
    //   1568: invokestatic checkColumnsResolved : (Lorg/hsqldb/lib/HsqlList;)V
    //   1571: aload #15
    //   1573: aload_0
    //   1574: getfield session : Lorg/hsqldb/Session;
    //   1577: aconst_null
    //   1578: invokevirtual resolveTypes : (Lorg/hsqldb/Session;Lorg/hsqldb/Expression;)V
    //   1581: aload #15
    //   1583: invokevirtual getDataType : ()Lorg/hsqldb/types/Type;
    //   1586: getstatic org/hsqldb/types/Type.SQL_BOOLEAN : Lorg/hsqldb/types/BooleanType;
    //   1589: if_acmpeq -> 1599
    //   1592: sipush #5568
    //   1595: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   1598: athrow
    //   1599: aload_3
    //   1600: ifnonnull -> 1607
    //   1603: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   1606: astore_3
    //   1607: aload_0
    //   1608: getfield token : Lorg/hsqldb/Token;
    //   1611: getfield tokenType : I
    //   1614: bipush #25
    //   1616: if_icmpne -> 1774
    //   1619: aload_0
    //   1620: invokevirtual getPosition : ()I
    //   1623: istore #24
    //   1625: aload_0
    //   1626: invokevirtual read : ()V
    //   1629: aload_0
    //   1630: invokevirtual checkIsSimpleName : ()V
    //   1633: aload_0
    //   1634: invokevirtual checkIsDelimitedIdentifier : ()V
    //   1637: aload_0
    //   1638: getfield token : Lorg/hsqldb/Token;
    //   1641: getfield tokenString : Ljava/lang/String;
    //   1644: astore #9
    //   1646: aload_0
    //   1647: invokevirtual read : ()V
    //   1650: aload_0
    //   1651: getfield token : Lorg/hsqldb/Token;
    //   1654: getfield tokenType : I
    //   1657: sipush #816
    //   1660: if_icmpne -> 1668
    //   1663: aload_0
    //   1664: invokevirtual unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   1667: athrow
    //   1668: new org/hsqldb/TriggerDef
    //   1671: dup
    //   1672: aload #11
    //   1674: iload #7
    //   1676: iload #8
    //   1678: aload_3
    //   1679: invokevirtual booleanValue : ()Z
    //   1682: aload_2
    //   1683: aload #20
    //   1685: aload #21
    //   1687: aload #15
    //   1689: aload #22
    //   1691: aload #14
    //   1693: aload #9
    //   1695: iload #4
    //   1697: iload #6
    //   1699: invokespecial <init> : (Lorg/hsqldb/HsqlNameManager$HsqlName;IIZLorg/hsqldb/Table;[Lorg/hsqldb/Table;[Lorg/hsqldb/RangeVariable;Lorg/hsqldb/Expression;Ljava/lang/String;[ILjava/lang/String;ZI)V
    //   1702: astore #10
    //   1704: aload_0
    //   1705: invokevirtual getLastPart : ()Ljava/lang/String;
    //   1708: astore #25
    //   1710: iconst_2
    //   1711: anewarray java/lang/Object
    //   1714: dup
    //   1715: iconst_0
    //   1716: aload #10
    //   1718: aastore
    //   1719: dup
    //   1720: iconst_1
    //   1721: aload #12
    //   1723: aastore
    //   1724: astore #26
    //   1726: iconst_2
    //   1727: anewarray org/hsqldb/HsqlNameManager$HsqlName
    //   1730: dup
    //   1731: iconst_0
    //   1732: aload_0
    //   1733: getfield database : Lorg/hsqldb/Database;
    //   1736: invokevirtual getCatalogName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   1739: aastore
    //   1740: dup
    //   1741: iconst_1
    //   1742: aload_2
    //   1743: invokevirtual getName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   1746: aastore
    //   1747: astore #27
    //   1749: new org/hsqldb/StatementSchema
    //   1752: dup
    //   1753: aload #25
    //   1755: bipush #80
    //   1757: aload #26
    //   1759: aconst_null
    //   1760: aload #27
    //   1762: invokespecial <init> : (Ljava/lang/String;I[Ljava/lang/Object;[Lorg/hsqldb/HsqlNameManager$HsqlName;[Lorg/hsqldb/HsqlNameManager$HsqlName;)V
    //   1765: areturn
    //   1766: astore #25
    //   1768: aload_0
    //   1769: iload #24
    //   1771: invokevirtual rewind : (I)V
    //   1774: iload #5
    //   1776: ifeq -> 1787
    //   1779: aload_0
    //   1780: ldc_w 'QUEUE'
    //   1783: invokevirtual unexpectedToken : (Ljava/lang/String;)Lorg/hsqldb/HsqlException;
    //   1786: athrow
    //   1787: iload #4
    //   1789: ifeq -> 1800
    //   1792: aload_0
    //   1793: ldc_w 'NOWAIT'
    //   1796: invokevirtual unexpectedToken : (Ljava/lang/String;)Lorg/hsqldb/HsqlException;
    //   1799: athrow
    //   1800: aload_0
    //   1801: aload_2
    //   1802: aload #21
    //   1804: iload #7
    //   1806: iload #8
    //   1808: invokevirtual compileTriggerRoutine : (Lorg/hsqldb/Table;[Lorg/hsqldb/RangeVariable;II)Lorg/hsqldb/Routine;
    //   1811: astore #24
    //   1813: new org/hsqldb/TriggerDefSQL
    //   1816: dup
    //   1817: aload #11
    //   1819: iload #7
    //   1821: iload #8
    //   1823: aload_3
    //   1824: invokevirtual booleanValue : ()Z
    //   1827: aload_2
    //   1828: aload #20
    //   1830: aload #21
    //   1832: aload #15
    //   1834: aload #22
    //   1836: aload #14
    //   1838: aload #24
    //   1840: invokespecial <init> : (Lorg/hsqldb/HsqlNameManager$HsqlName;IIZLorg/hsqldb/Table;[Lorg/hsqldb/Table;[Lorg/hsqldb/RangeVariable;Lorg/hsqldb/Expression;Ljava/lang/String;[ILorg/hsqldb/Routine;)V
    //   1843: astore #10
    //   1845: aload_0
    //   1846: invokevirtual getLastPart : ()Ljava/lang/String;
    //   1849: astore #25
    //   1851: iconst_2
    //   1852: anewarray java/lang/Object
    //   1855: dup
    //   1856: iconst_0
    //   1857: aload #10
    //   1859: aastore
    //   1860: dup
    //   1861: iconst_1
    //   1862: aload #12
    //   1864: aastore
    //   1865: astore #26
    //   1867: new org/hsqldb/StatementSchema
    //   1870: dup
    //   1871: aload #25
    //   1873: bipush #80
    //   1875: aload #26
    //   1877: aconst_null
    //   1878: iconst_2
    //   1879: anewarray org/hsqldb/HsqlNameManager$HsqlName
    //   1882: dup
    //   1883: iconst_0
    //   1884: aload_0
    //   1885: getfield database : Lorg/hsqldb/Database;
    //   1888: invokevirtual getCatalogName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   1891: aastore
    //   1892: dup
    //   1893: iconst_1
    //   1894: aload_2
    //   1895: invokevirtual getName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   1898: aastore
    //   1899: invokespecial <init> : (Ljava/lang/String;I[Ljava/lang/Object;[Lorg/hsqldb/HsqlNameManager$HsqlName;[Lorg/hsqldb/HsqlNameManager$HsqlName;)V
    //   1902: areturn
    // Exception table:
    //   from	to	target	type
    //   1625	1765	1766	org/hsqldb/HsqlException
  }
  
  Routine compileTriggerRoutine(Table paramTable, RangeVariable[] paramArrayOfRangeVariable, int paramInt1, int paramInt2) {
    byte b = (paramInt1 == 4) ? 3 : 4;
    Routine routine = new Routine(paramTable, paramArrayOfRangeVariable, b, paramInt1, paramInt2);
    startRecording();
    StatementCompound statementCompound = new StatementCompound(12, null);
    statementCompound.rangeVariables = paramArrayOfRangeVariable;
    Statement statement = compileSQLProcedureStatementOrNull(routine, (StatementCompound)null);
    if (statement == null)
      throw unexpectedToken(); 
    Token[] arrayOfToken = getRecordedStatement();
    String str = Token.getSQL(arrayOfToken);
    statement.setSQL(str);
    routine.setProcedure(statement);
    routine.resolve(this.session);
    return routine;
  }
  
  ColumnSchema readColumnDefinitionOrNull(Table paramTable, HsqlNameManager.HsqlName paramHsqlName, HsqlArrayList paramHsqlArrayList) {
    Type type;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    Expression expression1 = null;
    boolean bool5 = true;
    Expression expression2 = null;
    NumberType numberType = null;
    NumberSequence numberSequence = null;
    switch (this.token.tokenType) {
      case 407:
        read();
        readThis(337);
        bool1 = true;
        bool4 = true;
        throw unexpectedToken("GENERATED");
      case 128:
        read();
        bool2 = true;
        bool3 = true;
        numberType = Type.SQL_INTEGER;
        numberSequence = new NumberSequence(null, 0L, 1L, (Type)numberType);
        break;
      case 804:
        return null;
      case 802:
        return null;
      default:
        if (this.token.isUndelimitedIdentifier)
          if ("SERIAL".equals(this.token.tokenString)) {
            if (this.database.sqlSyntaxMys) {
              read();
              bool2 = true;
              bool3 = true;
              numberType = Type.SQL_BIGINT;
              numberSequence = new NumberSequence(null, 1L, 1L, (Type)numberType);
              break;
            } 
            if (this.database.sqlSyntaxPgs) {
              read();
              bool2 = true;
              numberType = Type.SQL_INTEGER;
              numberSequence = new NumberSequence(null, 1L, 1L, (Type)numberType);
              break;
            } 
          } else if ("BIGSERIAL".equals(this.token.tokenString) && this.database.sqlSyntaxPgs) {
            read();
            bool2 = true;
            bool3 = true;
            numberType = Type.SQL_BIGINT;
            numberSequence = new NumberSequence(null, 1L, 1L, (Type)numberType);
            break;
          }  
        type = readTypeDefinition(true, true);
        break;
    } 
    if (!bool1 && !bool2) {
      if (this.database.sqlSyntaxMys)
        switch (this.token.tokenType) {
          case 186:
            read();
            break;
          case 183:
            read();
            readThis(186);
            bool5 = false;
            break;
        }  
      switch (this.token.tokenType) {
        case 319:
          if (this.database.sqlSyntaxDb2) {
            read();
          } else {
            throw unexpectedToken();
          } 
        case 78:
          read();
          expression2 = readDefaultClause(type);
          if (expression2.opType == 12 && this.database.sqlSyntaxPgs) {
            numberSequence = ((ExpressionColumn)expression2).sequence;
            expression2 = null;
            bool2 = true;
          } 
          break;
        case 407:
          read();
          if (this.token.tokenType == 24) {
            read();
            readThis(78);
          } else {
            readThis(337);
            bool4 = true;
          } 
          readThis(10);
          if (this.token.tokenType == 128) {
            read();
            numberSequence = new NumberSequence(null, type);
            numberSequence.setAlways(bool4);
            if (this.token.tokenType == 816) {
              read();
              readSequenceOptions(numberSequence, false, false, true);
              readThis(802);
            } 
            bool2 = true;
            break;
          } 
          if (this.token.tokenType == 816) {
            if (!bool4)
              throw unexpectedTokenRequire("IDENTITY"); 
            bool1 = true;
            break;
          } 
          if (this.token.tokenType == 505) {
            if (bool4)
              throw unexpectedToken(); 
            read();
            if (this.token.namePrefix != null && !this.token.namePrefix.equals((paramTable.getSchemaName()).name))
              throw unexpectedToken(this.token.namePrefix); 
            numberSequence = this.database.schemaManager.getSequence(this.token.tokenString, (paramTable.getSchemaName()).name, true);
            bool2 = true;
            read();
          } 
          break;
        case 128:
          read();
          bool2 = true;
          bool3 = true;
          numberSequence = new NumberSequence(null, 0L, 1L, type);
          break;
      } 
    } 
    if (bool1) {
      readThis(816);
      expression1 = XreadValueExpression();
      readThis(802);
    } 
    if (!bool1 && !bool2 && this.database.sqlSyntaxMys && this.token.isUndelimitedIdentifier && "AUTO_INCREMENT".equals(this.token.tokenString)) {
      read();
      bool2 = true;
      numberSequence = new NumberSequence(null, 0L, 1L, type);
    } 
    ColumnSchema columnSchema = new ColumnSchema(paramHsqlName, type, bool5, false, expression2);
    columnSchema.setGeneratingExpression(expression1);
    readColumnConstraints(paramTable, columnSchema, paramHsqlArrayList);
    if (this.token.tokenType == 128 && !bool2) {
      read();
      bool2 = true;
      bool3 = true;
      numberSequence = new NumberSequence(null, 0L, 1L, type);
    } 
    if (this.token.tokenType == 407 && !bool2 && !bool1) {
      read();
      if (this.token.tokenType == 24) {
        read();
        readThis(78);
      } else {
        readThis(337);
        bool4 = true;
      } 
      readThis(10);
      readThis(128);
      numberSequence = new NumberSequence(null, type);
      numberSequence.setAlways(bool4);
      if (this.token.tokenType == 816) {
        read();
        readSequenceOptions(numberSequence, false, false, true);
        readThis(802);
      } 
      bool2 = true;
    } 
    if (bool2)
      columnSchema.setIdentity(numberSequence); 
    if (bool3 && !columnSchema.isPrimaryKey()) {
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      orderedHashSet.add((columnSchema.getName()).name);
      HsqlNameManager.HsqlName hsqlName = this.database.nameManager.newAutoName("PK", paramTable.getSchemaName(), paramTable.getName(), 5);
      Constraint constraint = new Constraint(hsqlName, orderedHashSet, 4);
      paramHsqlArrayList.set(0, constraint);
      columnSchema.setPrimaryKey(true);
    } 
    if (this.database.sqlSyntaxPgs && this.token.tokenType == 78 && columnSchema.getDefaultExpression() == null && columnSchema.getIdentitySequence() == null) {
      read();
      expression2 = readDefaultClause(type);
      if (expression2.opType == 12) {
        numberSequence = ((ExpressionColumn)expression2).sequence;
        expression2 = null;
      } 
      columnSchema.setDefaultExpression(expression2);
      columnSchema.setIdentity(numberSequence);
    } 
    return columnSchema;
  }
  
  private void readSequenceOptions(NumberSequence paramNumberSequence, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    OrderedIntHashSet orderedIntHashSet = new OrderedIntHashSet();
    while (true) {
      long l;
      boolean bool = false;
      if (orderedIntHashSet.contains(this.token.tokenType))
        throw unexpectedToken(); 
      switch (this.token.tokenType) {
        case 10:
          if (paramBoolean1) {
            orderedIntHashSet.add(this.token.tokenType);
            read();
            Type type = readTypeDefinition(false, true);
            paramNumberSequence.setDefaults(paramNumberSequence.getName(), type);
            break;
          } 
          throw unexpectedToken();
        case 267:
          orderedIntHashSet.add(this.token.tokenType);
          read();
          readThis(319);
          l = readBigint();
          paramNumberSequence.setStartValueNoCheck(l);
          if (paramBoolean3)
            readIfThis(804); 
          break;
        case 484:
          if (!paramBoolean2) {
            bool = true;
            break;
          } 
          orderedIntHashSet.add(this.token.tokenType);
          read();
          if (readIfThis(319)) {
            l = readBigint();
            paramNumberSequence.setCurrentValueNoCheck(l);
            break;
          } 
          paramNumberSequence.setStartValueDefault();
          break;
        case 417:
          orderedIntHashSet.add(this.token.tokenType);
          read();
          readThis(24);
          l = readBigint();
          paramNumberSequence.setIncrement(l);
          break;
        case 180:
          read();
          if (orderedIntHashSet.contains(this.token.tokenType))
            throw unexpectedToken(); 
          if (this.token.tokenType == 438) {
            paramNumberSequence.setDefaultMaxValue();
          } else if (this.token.tokenType == 442) {
            paramNumberSequence.setDefaultMinValue();
          } else if (this.token.tokenType == 71) {
            paramNumberSequence.setCycle(false);
          } else {
            throw unexpectedToken();
          } 
          orderedIntHashSet.add(this.token.tokenType);
          read();
          break;
        case 438:
          orderedIntHashSet.add(this.token.tokenType);
          read();
          l = readBigint();
          paramNumberSequence.setMaxValueNoCheck(l);
          break;
        case 442:
          orderedIntHashSet.add(this.token.tokenType);
          read();
          l = readBigint();
          paramNumberSequence.setMinValueNoCheck(l);
          break;
        case 71:
          orderedIntHashSet.add(this.token.tokenType);
          read();
          paramNumberSequence.setCycle(true);
          break;
        default:
          if ((this.database.sqlSyntaxOra || this.database.sqlSyntaxDb2) && isSimpleName()) {
            if (this.token.tokenString.equals("NOCACHE") || this.token.tokenString.equals("NOCYCLE") || this.token.tokenString.equals("NOMAXVALUE") || this.token.tokenString.equals("NOMINVALUE") || this.token.tokenString.equals("NOORDER") || this.token.tokenString.equals("ORDER")) {
              read();
              break;
            } 
            if (this.token.tokenString.equals("CACHE")) {
              read();
              readBigint();
              break;
            } 
          } 
          bool = true;
          break;
      } 
      if (bool) {
        paramNumberSequence.checkValues();
        return;
      } 
    } 
  }
  
  private void readConstraint(SchemaObject paramSchemaObject, HsqlArrayList paramHsqlArrayList) {
    Constraint constraint2;
    OrderedHashSet orderedHashSet1;
    Constraint constraint1;
    OrderedHashSet orderedHashSet2;
    Constraint constraint3;
    Constraint constraint4;
    HsqlNameManager.HsqlName hsqlName = null;
    if (this.token.tokenType == 48) {
      read();
      hsqlName = readNewDependentSchemaObjectName(paramSchemaObject.getName(), 5);
    } 
    switch (this.token.tokenType) {
      case 214:
        if ((paramSchemaObject.getName()).type != 3)
          throw unexpectedTokenRequire("CHECK"); 
        read();
        readThis(427);
        constraint2 = (Constraint)paramHsqlArrayList.get(0);
        if (constraint2.constType == 4)
          throw Error.error(5532); 
        if (hsqlName == null)
          hsqlName = this.database.nameManager.newAutoName("PK", paramSchemaObject.getSchemaName(), paramSchemaObject.getName(), 5); 
        orderedHashSet2 = readColumnNames(false);
        constraint4 = new Constraint(hsqlName, orderedHashSet2, 4);
        paramHsqlArrayList.set(0, constraint4);
        return;
      case 299:
        if ((paramSchemaObject.getName()).type != 3)
          throw unexpectedTokenRequire("CHECK"); 
        read();
        orderedHashSet1 = readColumnNames(false);
        if (hsqlName == null)
          hsqlName = this.database.nameManager.newAutoName("CT", paramSchemaObject.getSchemaName(), paramSchemaObject.getName(), 5); 
        constraint3 = new Constraint(hsqlName, orderedHashSet1, 2);
        paramHsqlArrayList.add(constraint3);
        return;
      case 113:
        if ((paramSchemaObject.getName()).type != 3)
          throw unexpectedTokenRequire("CHECK"); 
        read();
        readThis(427);
        orderedHashSet1 = readColumnNames(false);
        constraint3 = readFKReferences((Table)paramSchemaObject, hsqlName, orderedHashSet1);
        paramHsqlArrayList.add(constraint3);
        return;
      case 37:
        read();
        if (hsqlName == null)
          hsqlName = this.database.nameManager.newAutoName("CT", paramSchemaObject.getSchemaName(), paramSchemaObject.getName(), 5); 
        constraint1 = new Constraint(hsqlName, null, 3);
        readCheckConstraintCondition(constraint1);
        paramHsqlArrayList.add(constraint1);
        return;
    } 
    if (hsqlName != null)
      throw unexpectedToken(); 
  }
  
  void readColumnConstraints(Table paramTable, ColumnSchema paramColumnSchema, HsqlArrayList paramHsqlArrayList) {
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    do {
      Constraint constraint2;
      OrderedHashSet orderedHashSet1;
      Constraint constraint1;
      OrderedHashSet orderedHashSet3;
      Constraint constraint3;
      OrderedHashSet orderedHashSet2;
      Constraint constraint4;
      byte b;
      HsqlNameManager.HsqlName hsqlName = null;
      if (this.token.tokenType == 48) {
        read();
        hsqlName = readNewDependentSchemaObjectName(paramTable.getName(), 5);
      } 
      switch (this.token.tokenType) {
        case 214:
          if (bool3 || bool4)
            throw unexpectedToken(); 
          read();
          readThis(427);
          constraint2 = (Constraint)paramHsqlArrayList.get(0);
          if (constraint2.constType == 4)
            throw Error.error(5532); 
          orderedHashSet3 = new OrderedHashSet();
          orderedHashSet3.add((paramColumnSchema.getName()).name);
          if (hsqlName == null)
            hsqlName = this.database.nameManager.newAutoName("PK", paramTable.getSchemaName(), paramTable.getName(), 5); 
          constraint4 = new Constraint(hsqlName, orderedHashSet3, 4);
          paramHsqlArrayList.set(0, constraint4);
          paramColumnSchema.setPrimaryKey(true);
          bool4 = true;
          break;
        case 299:
          read();
          orderedHashSet1 = new OrderedHashSet();
          orderedHashSet1.add((paramColumnSchema.getName()).name);
          if (hsqlName == null)
            hsqlName = this.database.nameManager.newAutoName("CT", paramTable.getSchemaName(), paramTable.getName(), 5); 
          constraint3 = new Constraint(hsqlName, orderedHashSet1, 2);
          paramHsqlArrayList.add(constraint3);
          break;
        case 113:
          read();
          readThis(427);
        case 222:
          orderedHashSet1 = new OrderedHashSet();
          orderedHashSet1.add((paramColumnSchema.getName()).name);
          constraint3 = readFKReferences(paramTable, hsqlName, orderedHashSet1);
          paramHsqlArrayList.add(constraint3);
          break;
        case 37:
          read();
          if (hsqlName == null)
            hsqlName = this.database.nameManager.newAutoName("CT", paramTable.getSchemaName(), paramTable.getName(), 5); 
          constraint1 = new Constraint(hsqlName, null, 3);
          readCheckConstraintCondition(constraint1);
          orderedHashSet2 = constraint1.getCheckColumnExpressions();
          for (b = 0; b < orderedHashSet2.size(); b++) {
            ExpressionColumn expressionColumn = (ExpressionColumn)orderedHashSet2.get(b);
            if ((paramColumnSchema.getName()).name.equals(expressionColumn.getColumnName())) {
              if (expressionColumn.getSchemaName() != null && expressionColumn.getSchemaName() != (paramTable.getSchemaName()).name)
                throw Error.error(5505); 
            } else {
              throw Error.error(5501);
            } 
          } 
          paramHsqlArrayList.add(constraint1);
          break;
        case 183:
          if (bool2 || bool3)
            throw unexpectedToken(); 
          read();
          readThis(186);
          if (hsqlName == null)
            hsqlName = this.database.nameManager.newAutoName("CT", paramTable.getSchemaName(), paramTable.getName(), 5); 
          constraint1 = new Constraint(hsqlName, null, 3);
          constraint1.check = new ExpressionLogical(paramColumnSchema);
          paramHsqlArrayList.add(constraint1);
          bool2 = true;
          break;
        case 186:
          if (bool2 || bool3 || bool4)
            throw unexpectedToken(); 
          if (hsqlName != null)
            throw unexpectedToken(); 
          read();
          bool3 = true;
          break;
        default:
          bool1 = true;
          break;
      } 
    } while (!bool1);
  }
  
  void readCheckConstraintCondition(Constraint paramConstraint) {
    readThis(816);
    startRecording();
    this.isCheckOrTriggerCondition = true;
    Expression expression = XreadBooleanValueExpression();
    this.isCheckOrTriggerCondition = false;
    Token[] arrayOfToken = getRecordedStatement();
    readThis(802);
    paramConstraint.check = expression;
  }
  
  StatementSchema compileCreateIndex(boolean paramBoolean) {
    String[] arrayOfString = null;
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    read();
    HsqlNameManager.HsqlName hsqlName1 = readNewSchemaObjectName(20, true);
    while (this.token.tokenType != 194) {
      checkIsIdentifier();
      hsqlArrayList.add(this.token.tokenString);
      read();
    } 
    arrayOfString = new String[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfString);
    readThis(194);
    Table table = readTableName();
    HsqlNameManager.HsqlName hsqlName2 = table.getSchemaName();
    hsqlName1.setSchemaIfNull(hsqlName2);
    hsqlName1.parent = table.getName();
    if (hsqlName1.schema != hsqlName2)
      throw Error.error(5505); 
    hsqlName1.schema = table.getSchemaName();
    int[] arrayOfInt = readColumnList(table, true);
    String str = getLastPart();
    Object[] arrayOfObject = { table, arrayOfInt, hsqlName1, Boolean.valueOf(paramBoolean), arrayOfString };
    return new StatementSchema(str, 1073, arrayOfObject, null, new HsqlNameManager.HsqlName[] { this.database.getCatalogName(), table.getName() });
  }
  
  StatementSchema compileCreateSchema() {
    HsqlNameManager.HsqlName hsqlName1 = null;
    String str = null;
    HsqlNameManager.HsqlName hsqlName2 = null;
    read();
    if (this.token.tokenType != 15)
      hsqlName1 = readNewSchemaName(); 
    if (this.token.tokenType == 15) {
      read();
      checkIsSimpleName();
      str = this.token.tokenString;
      read();
      if (hsqlName1 == null) {
        Grantee grantee1 = this.database.getGranteeManager().get(str);
        if (grantee1 == null)
          throw Error.error(4001, str); 
        hsqlName1 = this.database.nameManager.newHsqlName((grantee1.getName()).name, isDelimitedIdentifier(), 2);
        SqlInvariants.checkSchemaNameNotSystem(this.token.tokenString);
      } 
    } 
    if ("PUBLIC".equals(str))
      throw Error.error(4002, str); 
    Grantee grantee = (str == null) ? this.session.getGrantee() : this.database.getGranteeManager().get(str);
    if (grantee == null)
      throw Error.error(4001, str); 
    if (!this.session.getGrantee().isSchemaCreator())
      throw Error.error(2051, this.session.getGrantee().getName().getNameString()); 
    if (grantee instanceof User && ((User)grantee).isExternalOnly)
      throw Error.error(2000, this.session.getGrantee().getName().getNameString()); 
    if (!this.database.schemaManager.schemaExists(hsqlName1.name) || (this.session.isProcessingScript() && "PUBLIC".equals(hsqlName1.name))) {
      if (hsqlName1.name.equals("SYSTEM_LOBS")) {
        hsqlName1 = SqlInvariants.LOBS_SCHEMA_HSQLNAME;
        grantee = hsqlName1.owner;
      } 
      if (readIfThis(78)) {
        readThis(35);
        readThis(254);
        hsqlName2 = readNewSchemaObjectName(14, false);
      } 
      String str1 = getLastPart();
      Object[] arrayOfObject = { hsqlName1, grantee };
      HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
      StatementSchema statementSchema = new StatementSchema(str1, 64, arrayOfObject, null, arrayOfHsqlName);
      statementSchema.setSchemaHsqlName(hsqlName1);
      HsqlArrayList hsqlArrayList = new HsqlArrayList();
      hsqlArrayList.add(statementSchema);
      getCompiledStatementBody((HsqlList)hsqlArrayList);
      StatementSchema[] arrayOfStatementSchema = new StatementSchema[hsqlArrayList.size()];
      hsqlArrayList.toArray(arrayOfStatementSchema);
      while (true) {
        boolean bool = false;
        for (byte b = 0; b < arrayOfStatementSchema.length - 1; b++) {
          if ((arrayOfStatementSchema[b]).order > (arrayOfStatementSchema[b + 1]).order) {
            StatementSchema statementSchema1 = arrayOfStatementSchema[b + 1];
            arrayOfStatementSchema[b + 1] = arrayOfStatementSchema[b];
            arrayOfStatementSchema[b] = statementSchema1;
            bool = true;
          } 
        } 
        if (!bool)
          return new StatementSchemaDefinition(arrayOfStatementSchema); 
      } 
    } 
    throw Error.error(5504, hsqlName1.name);
  }
  
  void getCompiledStatementBody(HsqlList paramHsqlList) {
    boolean bool = false;
    while (!bool) {
      String str;
      char c;
      StatementSchema statementSchema = null;
      int i = getPosition();
      switch (this.token.tokenType) {
        case 55:
          read();
          switch (this.token.tokenType) {
            case 299:
            case 305:
            case 497:
              throw unexpectedToken();
            case 597:
              c = 'б';
              str = getStatement(i, startStatementTokensSchema);
              statementSchema = new StatementSchema(str, c);
              break;
            case 505:
              statementSchema = compileCreateSequence();
              statementSchema.sql = getLastPart(i);
              break;
            case 490:
              statementSchema = compileCreateRole();
              statementSchema.sql = getLastPart(i);
              break;
            case 393:
              c = '\027';
              str = getStatement(i, startStatementTokensSchema);
              statementSchema = new StatementSchema(str, c);
              break;
            case 535:
              statementSchema = compileCreateType(false);
              statementSchema.sql = getLastPart(i);
              break;
            case 35:
              statementSchema = compileCreateCharacterSet();
              statementSchema.sql = getLastPart(i);
              break;
            case 339:
              throw unexpectedToken();
            case 120:
            case 278:
            case 523:
            case 570:
            case 607:
            case 631:
            case 632:
              c = 'M';
              str = getStatement(i, startStatementTokensSchema);
              statementSchema = new StatementSchema(str, c);
              break;
            case 291:
              c = 'P';
              str = getStatement(i, startStatementTokensSchema);
              statementSchema = new StatementSchema(str, c);
              break;
            case 545:
              c = 'T';
              str = getStatement(i, startStatementTokensSchema);
              statementSchema = new StatementSchema(str, c);
              break;
            case 117:
              c = '\016';
              str = getStatementForRoutine(i, startStatementTokensSchema);
              statementSchema = new StatementSchema(str, c);
              break;
            case 215:
              c = '\016';
              str = getStatementForRoutine(i, startStatementTokensSchema);
              statementSchema = new StatementSchema(str, c);
              break;
          } 
          throw unexpectedToken();
        case 121:
          statementSchema = compileGrantOrRevoke();
          statementSchema.sql = getLastPart(i);
          break;
        case 821:
          read();
          bool = true;
          break;
        case 872:
          bool = true;
          break;
        default:
          throw unexpectedToken();
      } 
      if (statementSchema != null) {
        statementSchema.isSchemaDefinition = true;
        paramHsqlList.add(statementSchema);
      } 
    } 
  }
  
  StatementSchema compileCreateRole() {
    read();
    HsqlNameManager.HsqlName hsqlName = readNewUserIdentifier();
    String str = getLastPart();
    Object[] arrayOfObject = { hsqlName };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str, 61, arrayOfObject, null, arrayOfHsqlName);
  }
  
  StatementSchema compileCreateUser() {
    Boolean bool1 = Boolean.FALSE;
    Boolean bool2 = Boolean.FALSE;
    Grantee grantee = this.session.getGrantee();
    read();
    HsqlNameManager.HsqlName hsqlName = readNewUserIdentifier();
    readThis(615);
    if (readIfThis(586))
      bool2 = Boolean.TRUE; 
    String str1 = readPassword();
    if (this.token.tokenType == 335) {
      read();
      bool1 = Boolean.TRUE;
    } 
    checkDatabaseUpdateAuthorisation();
    String str2 = getLastPart();
    Object[] arrayOfObject = { hsqlName, str1, grantee, bool1, bool2 };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str2, 1074, arrayOfObject, null, arrayOfHsqlName);
  }
  
  HsqlNameManager.HsqlName readNewUserIdentifier() {
    checkIsSimpleName();
    String str = this.token.tokenString;
    boolean bool = isDelimitedIdentifier();
    if (str.equalsIgnoreCase("SA")) {
      str = "SA";
      bool = false;
    } 
    HsqlNameManager.HsqlName hsqlName = this.database.nameManager.newHsqlName(str, bool, 11);
    read();
    return hsqlName;
  }
  
  String readPassword() {
    String str = this.token.tokenString;
    if (isUndelimitedSimpleName() || isDelimitedSimpleName()) {
      read();
    } else {
      readQuotedString();
    } 
    return str;
  }
  
  Statement compileRenameObject(HsqlNameManager.HsqlName paramHsqlName, int paramInt) {
    Object[] arrayOfObject;
    HsqlNameManager.HsqlName[] arrayOfHsqlName;
    HsqlNameManager.HsqlName hsqlName = readNewSchemaObjectName(paramInt, true);
    String str = getLastPart();
    switch (paramInt) {
      case 1:
        arrayOfObject = new Object[] { paramHsqlName, hsqlName };
        arrayOfHsqlName = this.database.schemaManager.getCatalogNameArray();
        return new StatementSchema(str, 1192, arrayOfObject, null, arrayOfHsqlName);
      case 2:
        checkSchemaUpdateAuthorisation(this.session, paramHsqlName);
    } 
    paramHsqlName.setSchemaIfNull(this.session.getCurrentSchemaHsqlName());
    checkSchemaUpdateAuthorisation(this.session, paramHsqlName.schema);
  }
  
  Statement compileAlterTableAddUniqueConstraint(Table paramTable, HsqlNameManager.HsqlName paramHsqlName) {
    if (paramHsqlName == null)
      paramHsqlName = this.database.nameManager.newAutoName("CT", paramTable.getSchemaName(), paramTable.getName(), 5); 
    int[] arrayOfInt = readColumnList(paramTable, false);
    HsqlNameManager.HsqlName hsqlName = this.database.nameManager.newAutoName("IDX", paramHsqlName.name, paramTable.getSchemaName(), paramTable.getName(), 20);
    Index index = paramTable.createIndexStructure(hsqlName, arrayOfInt, null, null, true, true, false);
    Constraint constraint = new Constraint(paramHsqlName, paramTable, index, 2);
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1082), paramTable, constraint };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterTableAddForeignKeyConstraint(Table paramTable, HsqlNameManager.HsqlName paramHsqlName) {
    if (paramHsqlName == null)
      paramHsqlName = this.database.nameManager.newAutoName("FK", paramTable.getSchemaName(), paramTable.getName(), 5); 
    OrderedHashSet orderedHashSet = readColumnNames(false);
    Constraint constraint = readFKReferences(paramTable, paramHsqlName, orderedHashSet);
    HsqlNameManager.HsqlName hsqlName = constraint.getMainTableName();
    constraint.core.mainTable = this.database.schemaManager.getTable(this.session, hsqlName.name, hsqlName.schema.name);
    constraint.setColumnsIndexes(paramTable);
    if (constraint.core.mainCols.length != constraint.core.refCols.length)
      throw Error.error(5593); 
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1082), paramTable, constraint };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    if (hsqlName != paramTable.getName())
      arrayOfHsqlName = (HsqlNameManager.HsqlName[])ArrayUtil.toAdjustedArray(arrayOfHsqlName, hsqlName, arrayOfHsqlName.length, 1); 
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterTableAddCheckConstraint(Table paramTable, HsqlNameManager.HsqlName paramHsqlName) {
    if (paramHsqlName == null)
      paramHsqlName = this.database.nameManager.newAutoName("CT", paramTable.getSchemaName(), paramTable.getName(), 5); 
    Constraint constraint = new Constraint(paramHsqlName, null, 3);
    readCheckConstraintCondition(constraint);
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1082), paramTable, constraint };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = { this.database.getCatalogName(), paramTable.getName() };
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterTableAddColumn(Table paramTable) {
    int i = paramTable.getColumnCount();
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    Constraint constraint = new Constraint(null, null, 5);
    hsqlArrayList.add(constraint);
    checkIsSchemaObjectName();
    HsqlNameManager.HsqlName hsqlName = this.database.nameManager.newColumnHsqlName(paramTable.getName(), this.token.tokenString, isDelimitedIdentifier());
    read();
    ColumnSchema columnSchema = readColumnDefinitionOrNull(paramTable, hsqlName, hsqlArrayList);
    if (columnSchema == null)
      throw Error.error(5000); 
    if (this.token.tokenType == 343) {
      read();
      i = paramTable.getColumnIndex(this.token.tokenString);
      read();
    } 
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1081), paramTable, columnSchema, new Integer(i), hsqlArrayList };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterTableAddPrimaryKey(Table paramTable, HsqlNameManager.HsqlName paramHsqlName) {
    if (paramHsqlName == null)
      paramHsqlName = this.session.database.nameManager.newAutoName("PK", paramTable.getSchemaName(), paramTable.getName(), 5); 
    OrderedHashSet orderedHashSet = readColumnNames(false);
    Constraint constraint = new Constraint(paramHsqlName, orderedHashSet, 4);
    constraint.setColumnsIndexes(paramTable);
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1082), paramTable, constraint };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterTableDropColumn(Table paramTable, String paramString, boolean paramBoolean) {
    int i = paramTable.getColumnIndex(paramString);
    if (paramTable.getColumnCount() == 1)
      throw Error.error(5591); 
    String str = getLastPart();
    Object[] arrayOfObject = { paramTable.getColumn(i).getName(), ValuePool.getInt(9), Boolean.valueOf(paramBoolean), Boolean.valueOf(false) };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 1076, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterColumn(Table paramTable, ColumnSchema paramColumnSchema, int paramInt) {
    Type type;
    Expression expression;
    String str;
    Object[] arrayOfObject;
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    int i = getPosition();
    switch (this.token.tokenType) {
      case 623:
        read();
        readThis(285);
        return compileAlterColumnRename(paramTable, paramColumnSchema);
      case 88:
        read();
        if (this.token.tokenType == 78) {
          read();
          String str1 = getLastPart();
          Object[] arrayOfObject1 = { Integer.valueOf(1088), paramTable, paramColumnSchema, Integer.valueOf(paramInt) };
          return new StatementSchema(str1, 4, arrayOfObject1, null, arrayOfHsqlName);
        } 
        if (this.token.tokenType == 407) {
          read();
          String str1 = getLastPart();
          Object[] arrayOfObject1 = { Integer.valueOf(1089), paramTable, paramColumnSchema, Integer.valueOf(paramInt) };
          return new StatementSchema(str1, 4, arrayOfObject1, null, arrayOfHsqlName);
        } 
        throw unexpectedToken();
      case 254:
        read();
        switch (this.token.tokenType) {
          case 378:
            read();
            readThis(535);
            return compileAlterColumnDataType(paramTable, paramColumnSchema);
          case 78:
            read();
            type = paramColumnSchema.getDataType();
            expression = readDefaultClause(type);
            str = getLastPart();
            arrayOfObject = new Object[] { Integer.valueOf(1087), paramTable, paramColumnSchema, Integer.valueOf(paramInt), expression };
            return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
          case 183:
            read();
            readThis(186);
            return compileAlterColumnSetNullability(paramTable, paramColumnSchema, false);
          case 186:
            read();
            return compileAlterColumnSetNullability(paramTable, paramColumnSchema, true);
          case 407:
            return compileAlterColumnAddSequence(paramTable, paramColumnSchema, paramInt);
        } 
        rewind(i);
        read();
        break;
      case 407:
        return compileAlterColumnAddSequence(paramTable, paramColumnSchema, paramInt);
    } 
    if (this.token.tokenType == 254 || this.token.tokenType == 484) {
      if (!paramColumnSchema.isIdentity())
        throw Error.error(5535); 
      return compileAlterColumnSequenceOptions(paramTable, paramColumnSchema, paramInt);
    } 
    return compileAlterColumnDataTypeIdentity(paramTable, paramColumnSchema);
  }
  
  private Statement compileAlterColumnDataTypeIdentity(Table paramTable, ColumnSchema paramColumnSchema) {
    if (paramColumnSchema.isGenerated())
      throw Error.error(5561); 
    NumberSequence numberSequence = paramColumnSchema.getIdentitySequence();
    Type type = paramColumnSchema.getDataType();
    if (this.token.tokenType == 128) {
      read();
      if (!type.isIntegralType())
        throw Error.error(5561); 
      if (numberSequence == null)
        numberSequence = new NumberSequence(null, type); 
    } else {
      String str1;
      Object[] arrayOfObject1;
      HsqlNameManager.HsqlName[] arrayOfHsqlName1;
      type = readTypeDefinition(true, true);
      switch (this.token.tokenType) {
        case 128:
          if (!type.isIntegralType())
            throw Error.error(5561); 
          read();
          if (numberSequence == null)
            numberSequence = new NumberSequence(null, type); 
          str1 = getLastPart();
          arrayOfObject1 = new Object[] { Integer.valueOf(1090), paramTable, paramColumnSchema, type, numberSequence };
          arrayOfHsqlName1 = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
          return new StatementSchema(str1, 4, arrayOfObject1, null, arrayOfHsqlName1);
        case 407:
          numberSequence = readSequence(paramColumnSchema);
          str1 = getLastPart();
          arrayOfObject1 = new Object[] { Integer.valueOf(1090), paramTable, paramColumnSchema, type, numberSequence };
          arrayOfHsqlName1 = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
          return new StatementSchema(str1, 4, arrayOfObject1, null, arrayOfHsqlName1);
      } 
      numberSequence = null;
    } 
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1090), paramTable, paramColumnSchema, type, numberSequence };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  private Statement compileAlterColumnDataType(Table paramTable, ColumnSchema paramColumnSchema) {
    if (paramColumnSchema.isGenerated())
      throw Error.error(5561); 
    Type type = readTypeDefinition(true, true);
    if (paramColumnSchema.isIdentity() && !type.isIntegralType())
      throw Error.error(5561); 
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1084), paramTable, paramColumnSchema, type };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  private Statement compileAlterColumnSetNullability(Table paramTable, ColumnSchema paramColumnSchema, boolean paramBoolean) {
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1086), paramTable, paramColumnSchema, Boolean.valueOf(paramBoolean) };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterSequence() {
    read();
    HsqlNameManager.HsqlName hsqlName = this.session.getSchemaHsqlName(this.token.namePrefix);
    NumberSequence numberSequence1 = this.database.schemaManager.getSequence(this.token.tokenString, hsqlName.name, true);
    read();
    if (this.token.tokenType == 623) {
      read();
      readThis(285);
      return compileRenameObject(numberSequence1.getName(), 7);
    } 
    checkSchemaUpdateAuthorisation(this.session, (numberSequence1.getName()).schema);
    NumberSequence numberSequence2 = numberSequence1.duplicate();
    readSequenceOptions(numberSequence2, false, true, false);
    String str = getLastPart();
    Object[] arrayOfObject = { numberSequence1, numberSequence2 };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogNameArray();
    return new StatementSchema(str, 134, arrayOfObject, null, arrayOfHsqlName);
  }
  
  StatementSchema compileAlterColumnAddSequence(Table paramTable, ColumnSchema paramColumnSchema, int paramInt) {
    if (!paramColumnSchema.getDataType().isIntegralType())
      throw Error.error(5525); 
    if (paramColumnSchema.isIdentity())
      throw Error.error(5525); 
    NumberSequence numberSequence = readSequence(paramColumnSchema);
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1085), paramTable, paramColumnSchema, Integer.valueOf(paramInt), numberSequence };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  NumberSequence readSequence(ColumnSchema paramColumnSchema) {
    readThis(407);
    NumberSequence numberSequence = new NumberSequence(null, paramColumnSchema.getDataType());
    boolean bool = false;
    if (this.token.tokenType == 24) {
      read();
      readThis(78);
    } else {
      readThis(337);
      bool = true;
    } 
    readThis(10);
    readThis(128);
    numberSequence.setAlways(bool);
    if (this.token.tokenType == 816) {
      read();
      readSequenceOptions(numberSequence, false, false, false);
      readThis(802);
    } 
    numberSequence.checkValues();
    return numberSequence;
  }
  
  StatementSchema compileAlterColumnSequenceOptions(Table paramTable, ColumnSchema paramColumnSchema, int paramInt) {
    OrderedIntHashSet orderedIntHashSet = new OrderedIntHashSet();
    NumberSequence numberSequence = paramColumnSchema.getIdentitySequence().duplicate();
    while (true) {
      long l;
      boolean bool = false;
      switch (this.token.tokenType) {
        case 484:
          if (!orderedIntHashSet.add(this.token.tokenType))
            throw unexpectedToken(); 
          read();
          if (readIfThis(319)) {
            long l1 = readBigint();
            numberSequence.setCurrentValueNoCheck(l1);
            break;
          } 
          numberSequence.reset();
          break;
        case 254:
          read();
          switch (this.token.tokenType) {
            case 417:
              if (!orderedIntHashSet.add(this.token.tokenType))
                throw unexpectedToken(); 
              read();
              readThis(24);
              l = readBigint();
              numberSequence.setIncrement(l);
              break;
            case 180:
              read();
              if (this.token.tokenType == 438) {
                numberSequence.setDefaultMaxValue();
              } else if (this.token.tokenType == 442) {
                numberSequence.setDefaultMinValue();
              } else if (this.token.tokenType == 71) {
                numberSequence.setCycle(false);
              } else {
                throw unexpectedToken();
              } 
              if (!orderedIntHashSet.add(this.token.tokenType))
                throw unexpectedToken(); 
              read();
              break;
            case 438:
              if (!orderedIntHashSet.add(this.token.tokenType))
                throw unexpectedToken(); 
              read();
              l = readBigint();
              numberSequence.setMaxValueNoCheck(l);
              break;
            case 442:
              if (!orderedIntHashSet.add(this.token.tokenType))
                throw unexpectedToken(); 
              read();
              l = readBigint();
              numberSequence.setMinValueNoCheck(l);
              break;
            case 71:
              if (!orderedIntHashSet.add(this.token.tokenType))
                throw unexpectedToken(); 
              read();
              numberSequence.setCycle(true);
              break;
          } 
          throw unexpectedToken();
        default:
          bool = true;
          break;
      } 
      if (bool) {
        numberSequence.checkValues();
        String str = getLastPart();
        Object[] arrayOfObject = { Integer.valueOf(1085), paramTable, paramColumnSchema, Integer.valueOf(paramInt), numberSequence };
        HsqlNameManager.HsqlName[] arrayOfHsqlName = { this.database.getCatalogName(), paramTable.getName() };
        return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
      } 
    } 
  }
  
  private Statement compileAlterColumnRename(Table paramTable, ColumnSchema paramColumnSchema) {
    checkIsSimpleName();
    HsqlNameManager.HsqlName hsqlName = readNewSchemaObjectName(9, true);
    if (paramTable.findColumn(hsqlName.name) > -1)
      throw Error.error(5504, hsqlName.name); 
    this.database.schemaManager.checkColumnIsReferenced(paramTable.getName(), paramColumnSchema.getName());
    String str = getLastPart();
    Object[] arrayOfObject = { paramColumnSchema.getName(), hsqlName };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = { this.database.getCatalogName(), paramTable.getName() };
    return new StatementSchema(str, 1192, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterSchemaRename() {
    HsqlNameManager.HsqlName hsqlName1 = readSchemaName();
    checkSchemaUpdateAuthorisation(hsqlName1);
    readThis(623);
    readThis(285);
    HsqlNameManager.HsqlName hsqlName2 = readNewSchemaName();
    String str = getLastPart();
    Object[] arrayOfObject = { hsqlName1, hsqlName2 };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogNameArray();
    return new StatementSchema(str, 1192, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterUser() {
    String str1;
    Boolean bool1;
    boolean bool;
    HsqlNameManager.HsqlName hsqlName2;
    Object[] arrayOfObject;
    StatementCommand statementCommand;
    String str2;
    read();
    HsqlNameManager.HsqlName hsqlName1 = readNewUserIdentifier();
    User user = this.database.getUserManager().get(hsqlName1.name);
    if (hsqlName1.name.equals("PUBLIC"))
      throw Error.error(5503); 
    readThis(254);
    switch (this.token.tokenType) {
      case 157:
        read();
        bool1 = processTrueOrFalseObject();
        arrayOfObject = new Object[] { user, bool1 };
        return new StatementCommand(1060, arrayOfObject);
      case 615:
        read();
        bool = false;
        if (readIfThis(586))
          bool = Boolean.TRUE.booleanValue(); 
        str1 = readPassword();
        arrayOfObject = new Object[] { user, str1, Boolean.valueOf(bool) };
        statementCommand = new StatementCommand(1062, arrayOfObject);
        str2 = User.getSetUserPasswordDigestSQL(user, str1, bool);
        statementCommand.setSQL(str2);
        return statementCommand;
      case 598:
        read();
        readThis(497);
        if (this.token.tokenType == 78) {
          hsqlName2 = null;
        } else {
          hsqlName2 = this.database.schemaManager.getSchemaHsqlName(this.token.tokenString);
        } 
        read();
        arrayOfObject = new Object[] { user, hsqlName2 };
        return new StatementCommand(1061, arrayOfObject);
    } 
    throw unexpectedToken();
  }
  
  Statement compileAlterDomain() {
    Expression expression;
    String str;
    Object[] arrayOfObject;
    HsqlNameManager.HsqlName[] arrayOfHsqlName;
    read();
    HsqlNameManager.HsqlName hsqlName = this.session.getSchemaHsqlName(this.token.namePrefix);
    checkSchemaUpdateAuthorisation(hsqlName);
    Type type = this.database.schemaManager.getDomain(this.token.tokenString, hsqlName.name, true);
    read();
    switch (this.token.tokenType) {
      case 623:
        read();
        readThis(285);
        return compileRenameObject(type.getName(), 13);
      case 88:
        read();
        if (this.token.tokenType == 78) {
          read();
          String str1 = getLastPart();
          Object[] arrayOfObject1 = { Integer.valueOf(1080), type };
          HsqlNameManager.HsqlName[] arrayOfHsqlName1 = this.database.schemaManager.getCatalogAndBaseTableNames(type.getName());
          return new StatementSchema(str1, 3, arrayOfObject1, null, arrayOfHsqlName1);
        } 
        if (this.token.tokenType == 48) {
          read();
          checkIsSchemaObjectName();
          HsqlNameManager.HsqlName hsqlName1 = this.database.schemaManager.getSchemaObjectName(type.getSchemaName(), this.token.tokenString, 5, true);
          read();
          String str1 = getLastPart();
          Object[] arrayOfObject1 = { Integer.valueOf(1078), type, hsqlName1 };
          HsqlNameManager.HsqlName[] arrayOfHsqlName1 = this.database.schemaManager.getCatalogAndBaseTableNames(type.getName());
          return new StatementSchema(str1, 3, arrayOfObject1, null, arrayOfHsqlName1);
        } 
        throw unexpectedToken();
      case 254:
        read();
        readThis(78);
        expression = readDefaultClause(type);
        str = getLastPart();
        arrayOfObject = new Object[] { Integer.valueOf(1083), type, expression };
        arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(type.getName());
        return new StatementSchema(str, 3, arrayOfObject, null, arrayOfHsqlName);
      case 334:
        read();
        if (this.token.tokenType == 48 || this.token.tokenType == 37) {
          HsqlArrayList hsqlArrayList = new HsqlArrayList();
          this.compileContext.currentDomain = type;
          readConstraint((SchemaObject)type, hsqlArrayList);
          this.compileContext.currentDomain = null;
          Constraint constraint = (Constraint)hsqlArrayList.get(0);
          String str1 = getLastPart();
          Object[] arrayOfObject1 = { Integer.valueOf(1082), type, constraint };
          HsqlNameManager.HsqlName[] arrayOfHsqlName1 = this.database.schemaManager.getCatalogAndBaseTableNames(type.getName());
          return new StatementSchema(str1, 3, arrayOfObject1, null, arrayOfHsqlName1);
        } 
        break;
    } 
    throw unexpectedToken();
  }
  
  private boolean isGrantToken() {
    switch (this.token.tokenType) {
      case 2:
      case 79:
      case 100:
      case 135:
      case 222:
      case 251:
      case 303:
      case 540:
        return true;
    } 
    return false;
  }
  
  StatementSchema compileGrantOrRevoke() {
    boolean bool = (this.token.tokenType == 121) ? true : false;
    read();
    return (isGrantToken() || (!bool && (this.token.tokenType == 121 || this.token.tokenType == 411))) ? compileRightGrantOrRevoke(bool) : compileRoleGrantOrRevoke(bool);
  }
  
  private StatementSchema compileRightGrantOrRevoke(boolean paramBoolean) {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    Grantee grantee = null;
    Right right = null;
    HsqlNameManager.HsqlName hsqlName = null;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool5 = false;
    boolean bool6 = false;
    if (!paramBoolean)
      if (this.token.tokenType == 121) {
        read();
        readThis(455);
        readThis(112);
        bool5 = true;
      } else if (this.token.tokenType == 411) {
        throw unsupportedFeature();
      }  
    if (this.token.tokenType == 2) {
      read();
      if (this.token.tokenType == 478)
        read(); 
      right = Right.fullRights;
      bool4 = true;
    } else {
      right = new Right();
      boolean bool = true;
      while (bool) {
        checkIsNotQuoted();
        int i = GranteeManager.getCheckSingleRight(this.token.tokenString);
        int j = this.token.tokenType;
        OrderedHashSet orderedHashSet1 = null;
        read();
        switch (j) {
          case 135:
          case 222:
          case 251:
          case 303:
            if (this.token.tokenType == 816)
              orderedHashSet1 = readColumnNames(false); 
          case 291:
            if (right == null)
              right = new Right(); 
            right.set(i, orderedHashSet1);
            bool1 = true;
            break;
          case 79:
            if (right == null)
              right = new Right(); 
            right.set(i, null);
            bool1 = true;
            break;
          case 540:
            if (bool1)
              throw unexpectedToken(); 
            right = Right.fullRights;
            bool2 = true;
            bool = false;
            continue;
          case 100:
            if (bool1)
              throw unexpectedToken(); 
            right = Right.fullRights;
            bool3 = true;
            bool = false;
            continue;
        } 
        if (this.token.tokenType == 804)
          read(); 
      } 
    } 
    readThis(194);
    byte b = 0;
    switch (this.token.tokenType) {
      case 574:
        if (!bool3 && !bool4)
          throw unexpectedToken(); 
        read();
        if (!isSimpleName() || !isDelimitedIdentifier())
          throw Error.error(5569); 
        b = 16;
        hsqlName = readNewSchemaObjectName(16, false);
        break;
      case 259:
        if (!bool3 && !bool4)
          throw unexpectedToken(); 
        read();
        switch (this.token.tokenType) {
          case 117:
          case 215:
          case 491:
            read();
            break;
          default:
            throw unexpectedToken();
        } 
        b = 24;
        break;
      case 117:
        if (!bool3 && !bool4)
          throw unexpectedToken(); 
        read();
        b = 16;
        break;
      case 215:
        if (!bool3 && !bool4)
          throw unexpectedToken(); 
        read();
        b = 17;
        break;
      case 491:
        if (!bool3 && !bool4)
          throw unexpectedToken(); 
        read();
        b = 18;
        break;
      case 535:
        if (!bool2 && !bool4)
          throw unexpectedToken(); 
        read();
        b = 12;
        break;
      case 393:
        if (!bool2 && !bool4)
          throw unexpectedToken(); 
        read();
        b = 13;
        break;
      case 505:
        if (!bool2 && !bool4)
          throw unexpectedToken(); 
        read();
        b = 7;
        break;
      case 35:
        if (!bool2 && !bool4)
          throw unexpectedToken(); 
        read();
        readThis(254);
        b = 14;
        break;
      default:
        if (!bool1 && !bool4)
          throw unexpectedToken(); 
        readIfThis(278);
        b = 3;
        break;
    } 
    hsqlName = readNewSchemaObjectName(b, false);
    if (paramBoolean) {
      readThis(285);
    } else {
      readThis(115);
    } 
    while (true) {
      checkIsSimpleName();
      orderedHashSet.add(this.token.tokenString);
      read();
      if (this.token.tokenType == 804) {
        read();
        continue;
      } 
      if (paramBoolean) {
        if (this.token.tokenType == 319) {
          read();
          readThis(121);
          readThis(455);
          bool5 = true;
        } 
        if (this.token.tokenType == 410) {
          read();
          readThis(24);
          if (this.token.tokenType == 69) {
            read();
          } else {
            readThis(64);
            if (this.session.getRole() == null)
              throw Error.error(2200); 
            grantee = this.session.getRole();
          } 
        } 
      } else if (this.token.tokenType == 347) {
        bool6 = true;
        read();
      } else {
        readThis(485);
      } 
      String str = getLastPart();
      byte b1 = paramBoolean ? 48 : 59;
      Object[] arrayOfObject = { orderedHashSet, hsqlName, right, grantee, Boolean.valueOf(bool6), Boolean.valueOf(bool5) };
      HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogNameArray();
      return new StatementSchema(str, b1, arrayOfObject, null, arrayOfHsqlName);
    } 
  }
  
  private StatementSchema compileRoleGrantOrRevoke(boolean paramBoolean) {
    Grantee grantee = this.session.getGrantee();
    OrderedHashSet orderedHashSet1 = new OrderedHashSet();
    OrderedHashSet orderedHashSet2 = new OrderedHashSet();
    boolean bool = false;
    if (!paramBoolean && this.token.tokenType == 335)
      throw unsupportedFeature(); 
    while (true) {
      checkIsSimpleName();
      orderedHashSet1.add(this.token.tokenString);
      read();
      if (this.token.tokenType == 804) {
        read();
        continue;
      } 
      if (paramBoolean) {
        readThis(285);
      } else {
        readThis(115);
      } 
      while (true) {
        checkIsSimpleName();
        orderedHashSet2.add(this.token.tokenString);
        read();
        if (this.token.tokenType == 804) {
          read();
          continue;
        } 
        if (paramBoolean && this.token.tokenType == 319)
          throw unsupportedFeature(); 
        if (this.token.tokenType == 410) {
          read();
          readThis(24);
          if (this.token.tokenType == 69) {
            read();
          } else {
            readThis(64);
            if (this.session.getRole() == null)
              throw Error.error(2200); 
            grantee = this.session.getRole();
          } 
        } 
        if (!paramBoolean)
          if (this.token.tokenType == 347) {
            bool = true;
            read();
          } else {
            readThis(485);
          }  
        String str = getLastPart();
        boolean bool1 = paramBoolean ? true : true;
        Object[] arrayOfObject = { orderedHashSet2, orderedHashSet1, grantee, Boolean.valueOf(bool) };
        HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogNameArray();
        return new StatementSchema(str, bool1, arrayOfObject, null, arrayOfHsqlName);
      } 
      break;
    } 
  }
  
  void checkSchemaUpdateAuthorisation(HsqlNameManager.HsqlName paramHsqlName) {
    if (this.session.isProcessingLog())
      return; 
    SqlInvariants.checkSchemaNameNotSystem(paramHsqlName.name);
    if (this.isSchemaDefinition) {
      if (paramHsqlName != this.session.getCurrentSchemaHsqlName())
        throw Error.error(5505); 
    } else {
      this.session.getGrantee().checkSchemaUpdateOrGrantRights(paramHsqlName.name);
    } 
    this.session.checkDDLWrite();
  }
  
  void checkDatabaseUpdateAuthorisation() {
    this.session.checkAdmin();
    this.session.checkDDLWrite();
  }
  
  StatementSchema compileComment() {
    HsqlNameManager.HsqlName hsqlName;
    byte b;
    String str;
    Object[] arrayOfObject;
    readThis(576);
    readThis(194);
    switch (this.token.tokenType) {
      case 278:
      case 491:
        b = (this.token.tokenType == 491) ? 18 : 3;
        read();
        checkIsSchemaObjectName();
        hsqlName = this.database.nameManager.newHsqlName(this.token.tokenString, this.token.isDelimitedIdentifier, b);
        if (this.token.namePrefix == null) {
          hsqlName.schema = this.session.getCurrentSchemaHsqlName();
        } else {
          hsqlName.schema = this.database.nameManager.newHsqlName(this.token.namePrefix, this.token.isDelimitedPrefix, 2);
        } 
        read();
        readThis(142);
        str = readQuotedString();
        arrayOfObject = new Object[] { hsqlName, str };
        return new StatementSchema(null, 1071, arrayOfObject, null, null);
      case 43:
        read();
        checkIsSchemaObjectName();
        hsqlName = this.database.nameManager.newHsqlName(this.token.tokenString, this.token.isDelimitedIdentifier, 9);
        if (this.token.namePrefix == null)
          throw Error.error(5501); 
        hsqlName.parent = this.database.nameManager.newHsqlName(this.token.namePrefix, this.token.isDelimitedPrefix, 3);
        if (this.token.namePrePrefix == null) {
          hsqlName.parent.schema = this.session.getCurrentSchemaHsqlName();
        } else {
          hsqlName.parent.schema = this.database.nameManager.newHsqlName(this.token.namePrePrefix, this.token.isDelimitedPrePrefix, 3);
        } 
        read();
        readThis(142);
        str = readQuotedString();
        arrayOfObject = new Object[] { hsqlName, str };
        return new StatementSchema(null, 1071, arrayOfObject, null, null);
    } 
    throw unexpectedToken();
  }
  
  Statement compileAlterSession() {
    Object[] arrayOfObject;
    read();
    this.session.checkAdmin();
    if (this.token.tokenType == 624) {
      Object[] arrayOfObject1;
      read();
      int j = this.token.tokenType;
      switch (this.token.tokenType) {
        case 2:
          read();
          arrayOfObject1 = new Object[] { Long.valueOf(this.session.getId()), Integer.valueOf(j) };
          return new StatementCommand(1005, arrayOfObject1);
        case 236:
          read();
          readThis(510);
          arrayOfObject1 = new Object[] { Long.valueOf(this.session.getId()), Integer.valueOf(j) };
          return new StatementCommand(1005, arrayOfObject1);
        case 278:
          read();
          readThis(378);
          arrayOfObject1 = new Object[] { Long.valueOf(this.session.getId()), Integer.valueOf(j) };
          return new StatementCommand(1005, arrayOfObject1);
      } 
      throw unexpectedTokenRequire("ALL,RESULT,TABLE");
    } 
    long l = readBigint();
    Session session = this.database.sessionManager.getSession(l);
    if (session == null)
      throw Error.error(4500); 
    int i = this.token.tokenType;
    switch (this.token.tokenType) {
      case 39:
        read();
        arrayOfObject = new Object[] { Long.valueOf(l), Integer.valueOf(i) };
        return new StatementCommand(1005, arrayOfObject);
      case 233:
        read();
        arrayOfObject = new Object[] { Long.valueOf(l), Integer.valueOf(i) };
        return new StatementCommand(1005, arrayOfObject);
    } 
    throw unexpectedToken();
  }
  
  boolean processTrueOrFalse() {
    if (this.token.namePrefix != null)
      throw unexpectedToken(); 
    if (this.token.tokenType == 294) {
      read();
      return true;
    } 
    if (this.token.tokenType == 106) {
      read();
      return false;
    } 
    throw unexpectedToken();
  }
  
  Boolean processTrueOrFalseObject() {
    return Boolean.valueOf(processTrueOrFalse());
  }
  
  void checkSchemaUpdateAuthorisation(Session paramSession, HsqlNameManager.HsqlName paramHsqlName) {
    if (paramSession.isProcessingLog())
      return; 
    if (SqlInvariants.isSystemSchemaName(paramHsqlName.name))
      throw Error.error(5503); 
    if (paramSession.parser.isSchemaDefinition) {
      if (paramHsqlName == paramSession.getCurrentSchemaHsqlName())
        return; 
      Error.error(5505, paramHsqlName.name);
    } 
    paramSession.getGrantee().checkSchemaUpdateOrGrantRights(paramHsqlName.name);
    paramSession.checkDDLWrite();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ParserDDL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */