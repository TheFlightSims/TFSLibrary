package org.hsqldb.dbinfo;

import org.hsqldb.ColumnSchema;
import org.hsqldb.Constraint;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.NumberSequence;
import org.hsqldb.Routine;
import org.hsqldb.RoutineSchema;
import org.hsqldb.SchemaObject;
import org.hsqldb.Session;
import org.hsqldb.SqlInvariants;
import org.hsqldb.Table;
import org.hsqldb.TypeInvariants;
import org.hsqldb.index.Index;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.WrapperIterator;
import org.hsqldb.map.ValuePool;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.rights.Grantee;
import org.hsqldb.rights.GranteeManager;
import org.hsqldb.rights.Right;
import org.hsqldb.rights.User;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.Charset;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.Type;

class DatabaseInformationMain extends DatabaseInformation {
  static Type CARDINAL_NUMBER = TypeInvariants.CARDINAL_NUMBER;
  
  static Type YES_OR_NO = TypeInvariants.YES_OR_NO;
  
  static Type CHARACTER_DATA = TypeInvariants.CHARACTER_DATA;
  
  static Type SQL_IDENTIFIER = TypeInvariants.SQL_IDENTIFIER;
  
  static Type TIME_STAMP = TypeInvariants.TIME_STAMP;
  
  protected static final HsqlNameManager.HsqlName[] sysTableHsqlNames;
  
  protected static final boolean[] sysTableSessionDependent = new boolean[sysTableNames.length];
  
  protected static final HashSet nonCachedTablesSet;
  
  protected static final String[] tableTypes = new String[] { "GLOBAL TEMPORARY", "SYSTEM TABLE", "TABLE", "VIEW" };
  
  protected final Table[] sysTables = new Table[sysTableNames.length];
  
  DatabaseInformationMain(Database paramDatabase) {
    super(paramDatabase);
    Session session = paramDatabase.sessionManager.getSysSession();
    init(session);
  }
  
  protected final void addColumn(Table paramTable, String paramString, Type paramType) {
    HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaColumnName(paramString, paramTable.getName());
    ColumnSchema columnSchema = new ColumnSchema(hsqlName, paramType, true, false, null);
    paramTable.addColumn(columnSchema);
  }
  
  protected final Iterator allTables() {
    return (Iterator)new WrapperIterator(this.database.schemaManager.databaseObjectIterator(3), (Iterator)new WrapperIterator((Object[])this.sysTables, true));
  }
  
  protected final void cacheClear(Session paramSession) {
    int i = this.sysTables.length;
    while (i-- > 0) {
      Table table = this.sysTables[i];
      if (table != null)
        table.clearAllData(paramSession); 
    } 
  }
  
  protected Table generateTable(Session paramSession, PersistentStore paramPersistentStore, int paramInt) {
    switch (paramInt) {
      case 0:
        return SYSTEM_BESTROWIDENTIFIER(paramSession, paramPersistentStore);
      case 1:
        return SYSTEM_COLUMNS(paramSession, paramPersistentStore);
      case 18:
        return SYSTEM_CONNECTION_PROPERTIES(paramSession, paramPersistentStore);
      case 2:
        return SYSTEM_CROSSREFERENCE(paramSession, paramPersistentStore);
      case 3:
        return SYSTEM_INDEXINFO(paramSession, paramPersistentStore);
      case 4:
        return SYSTEM_PRIMARYKEYS(paramSession, paramPersistentStore);
      case 5:
        return SYSTEM_PROCEDURECOLUMNS(paramSession, paramPersistentStore);
      case 6:
        return SYSTEM_PROCEDURES(paramSession, paramPersistentStore);
      case 7:
        return SYSTEM_SCHEMAS(paramSession, paramPersistentStore);
      case 14:
        return SYSTEM_SEQUENCES(paramSession, paramPersistentStore);
      case 8:
        return SYSTEM_TABLES(paramSession, paramPersistentStore);
      case 9:
        return SYSTEM_TABLETYPES(paramSession, paramPersistentStore);
      case 10:
        return SYSTEM_TYPEINFO(paramSession, paramPersistentStore);
      case 12:
        return SYSTEM_USERS(paramSession, paramPersistentStore);
      case 11:
        return SYSTEM_UDTS(paramSession, paramPersistentStore);
      case 13:
        return SYSTEM_VERSIONCOLUMNS(paramSession, paramPersistentStore);
      case 34:
        return COLUMN_PRIVILEGES(paramSession, paramPersistentStore);
      case 70:
        return SEQUENCES(paramSession, paramPersistentStore);
      case 78:
        return TABLE_PRIVILEGES(paramSession, paramPersistentStore);
      case 44:
        return INFORMATION_SCHEMA_CATALOG_NAME(paramSession, paramPersistentStore);
    } 
    return null;
  }
  
  protected final void init(Session paramSession) {
    for (byte b1 = 0; b1 < this.sysTables.length; b1++) {
      Table table = this.sysTables[b1] = generateTable(paramSession, (PersistentStore)null, b1);
      if (table != null)
        table.setDataReadOnly(true); 
    } 
    GranteeManager granteeManager = this.database.getGranteeManager();
    Right right = new Right();
    right.set(1, null);
    for (byte b2 = 0; b2 < sysTableHsqlNames.length; b2++) {
      if (this.sysTables[b2] != null)
        granteeManager.grantSystemToPublic((SchemaObject)this.sysTables[b2], right); 
    } 
    right = Right.fullRights;
    granteeManager.grantSystemToPublic((SchemaObject)Charset.SQL_CHARACTER, right);
    granteeManager.grantSystemToPublic((SchemaObject)Charset.SQL_IDENTIFIER_CHARSET, right);
    granteeManager.grantSystemToPublic((SchemaObject)Charset.SQL_TEXT, right);
    granteeManager.grantSystemToPublic((SchemaObject)TypeInvariants.SQL_IDENTIFIER, right);
    granteeManager.grantSystemToPublic((SchemaObject)TypeInvariants.YES_OR_NO, right);
    granteeManager.grantSystemToPublic((SchemaObject)TypeInvariants.TIME_STAMP, right);
    granteeManager.grantSystemToPublic((SchemaObject)TypeInvariants.CARDINAL_NUMBER, right);
    granteeManager.grantSystemToPublic((SchemaObject)TypeInvariants.CHARACTER_DATA, right);
  }
  
  protected final boolean isAccessibleTable(Session paramSession, Table paramTable) {
    return paramSession.getGrantee().isAccessible((SchemaObject)paramTable);
  }
  
  protected final Table createBlankTable(HsqlNameManager.HsqlName paramHsqlName) {
    return new Table(this.database, paramHsqlName, 1);
  }
  
  public final Table getSystemTable(Session paramSession, String paramString) {
    if (!isSystemTable(paramString))
      return null; 
    int i = getSysTableID(paramString);
    Table table = this.sysTables[i];
    return (table == null) ? table : (!this.withContent ? table : table);
  }
  
  public boolean isNonCachedTable(String paramString) {
    return nonCachedTablesSet.contains(paramString);
  }
  
  public final void setStore(Session paramSession, Table paramTable, PersistentStore paramPersistentStore) {
    long l = this.database.schemaManager.getSchemaChangeTimestamp();
    if (paramPersistentStore.getTimestamp() == l && !isNonCachedTable((paramTable.getName()).name))
      return; 
    paramPersistentStore.removeAll();
    paramPersistentStore.setTimestamp(l);
    int i = getSysTableID((paramTable.getName()).name);
    generateTable(paramSession, paramPersistentStore, i);
  }
  
  final Table SYSTEM_BESTROWIDENTIFIER(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[0];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[0]);
      addColumn(table, "SCOPE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "DATA_TYPE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "TYPE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_SIZE", (Type)Type.SQL_INTEGER);
      addColumn(table, "BUFFER_LENGTH", (Type)Type.SQL_INTEGER);
      addColumn(table, "DECIMAL_DIGITS", (Type)Type.SQL_SMALLINT);
      addColumn(table, "PSEUDO_COLUMN", (Type)Type.SQL_SMALLINT);
      addColumn(table, "TABLE_CAT", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "NULLABLE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "IN_KEY", (Type)Type.SQL_BOOLEAN);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[0]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 8, 9, 10, 1 }, false);
      return table;
    } 
    DITableInfo dITableInfo = new DITableInfo();
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(3);
    boolean bool = this.database.sqlTranslateTTI;
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if (table1.isView() || !isAccessibleTable(paramSession, table1))
        continue; 
      int[] arrayOfInt = table1.getBestRowIdentifiers();
      if (arrayOfInt == null)
        continue; 
      dITableInfo.setTable(table1);
      Boolean bool1 = ValuePool.getBoolean(table1.isBestRowIdentifiersStrict());
      String str1 = (table1.getCatalogName()).name;
      String str2 = (table1.getSchemaName()).name;
      String str3 = (table1.getName()).name;
      Type[] arrayOfType = table1.getColumnTypes();
      Integer integer1 = dITableInfo.getBRIScope();
      Integer integer2 = dITableInfo.getBRIPseudo();
      for (byte b = 0; b < arrayOfInt.length; b++) {
        DateTimeType dateTimeType;
        ColumnSchema columnSchema = table1.getColumn(b);
        Type type = arrayOfType[b];
        if (bool) {
          CharacterType characterType;
          if (type.isIntervalType()) {
            characterType = ((IntervalType)type).getCharacterType();
          } else if (characterType.isDateTimeTypeWithZone()) {
            dateTimeType = ((DateTimeType)characterType).getDateTimeTypeWithoutZone();
          } 
        } 
        Object[] arrayOfObject = table.getEmptyRowData();
        arrayOfObject[0] = integer1;
        arrayOfObject[1] = (columnSchema.getName()).name;
        arrayOfObject[2] = ValuePool.getInt(dateTimeType.getJDBCTypeCode());
        arrayOfObject[3] = dateTimeType.getNameString();
        arrayOfObject[4] = ValuePool.getInt(dateTimeType.getJDBCPrecision());
        arrayOfObject[5] = null;
        arrayOfObject[6] = dateTimeType.acceptsScale() ? ValuePool.getInt(dateTimeType.getJDBCScale()) : null;
        arrayOfObject[7] = integer2;
        arrayOfObject[8] = str1;
        arrayOfObject[9] = str2;
        arrayOfObject[10] = str3;
        arrayOfObject[11] = ValuePool.getInt(columnSchema.getNullability());
        arrayOfObject[12] = bool1;
        table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      } 
    } 
    return table;
  }
  
  final Table SYSTEM_COLUMNS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[1];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[1]);
      addColumn(table, "TABLE_CAT", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "DATA_TYPE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "TYPE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_SIZE", (Type)Type.SQL_INTEGER);
      addColumn(table, "BUFFER_LENGTH", (Type)Type.SQL_INTEGER);
      addColumn(table, "DECIMAL_DIGITS", (Type)Type.SQL_INTEGER);
      addColumn(table, "NUM_PREC_RADIX", (Type)Type.SQL_INTEGER);
      addColumn(table, "NULLABLE", (Type)Type.SQL_INTEGER);
      addColumn(table, "REMARKS", CHARACTER_DATA);
      addColumn(table, "COLUMN_DEF", CHARACTER_DATA);
      addColumn(table, "SQL_DATA_TYPE", (Type)Type.SQL_INTEGER);
      addColumn(table, "SQL_DATETIME_SUB", (Type)Type.SQL_INTEGER);
      addColumn(table, "CHAR_OCTET_LENGTH", (Type)Type.SQL_INTEGER);
      addColumn(table, "ORDINAL_POSITION", (Type)Type.SQL_INTEGER);
      addColumn(table, "IS_NULLABLE", YES_OR_NO);
      addColumn(table, "SCOPE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SCOPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SCOPE_TABLE", SQL_IDENTIFIER);
      addColumn(table, "SOURCE_DATA_TYPE", SQL_IDENTIFIER);
      addColumn(table, "IS_AUTOINCREMENT", YES_OR_NO);
      addColumn(table, "IS_GENERATEDCOLUMN", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[1]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 16 }, false);
      return table;
    } 
    Iterator iterator = allTables();
    DITableInfo dITableInfo = new DITableInfo();
    boolean bool = this.database.sqlTranslateTTI;
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if (!isAccessibleTable(paramSession, table1))
        continue; 
      dITableInfo.setTable(table1);
      String str1 = (table1.getCatalogName()).name;
      String str2 = (table1.getSchemaName()).name;
      String str3 = (table1.getName()).name;
      int i = table1.getColumnCount();
      for (byte b = 0; b < i; b++) {
        DateTimeType dateTimeType;
        ColumnSchema columnSchema = table1.getColumn(b);
        Type type = columnSchema.getDataType();
        if (bool) {
          CharacterType characterType;
          if (type.isIntervalType()) {
            characterType = ((IntervalType)type).getCharacterType();
          } else if (characterType.isDateTimeTypeWithZone()) {
            dateTimeType = ((DateTimeType)characterType).getDateTimeTypeWithoutZone();
          } 
        } 
        Object[] arrayOfObject = table.getEmptyRowData();
        arrayOfObject[0] = str1;
        arrayOfObject[1] = str2;
        arrayOfObject[2] = str3;
        arrayOfObject[3] = (columnSchema.getName()).name;
        arrayOfObject[4] = ValuePool.getInt(dateTimeType.getJDBCTypeCode());
        arrayOfObject[5] = dateTimeType.getNameString();
        arrayOfObject[6] = ValuePool.INTEGER_0;
        arrayOfObject[15] = ValuePool.INTEGER_0;
        if (dateTimeType.isArrayType())
          arrayOfObject[5] = dateTimeType.getDefinition(); 
        if (dateTimeType.isCharacterType()) {
          arrayOfObject[6] = ValuePool.getInt(dateTimeType.getJDBCPrecision());
          arrayOfObject[15] = ValuePool.getInt(dateTimeType.getJDBCPrecision());
        } 
        if (dateTimeType.isBinaryType()) {
          arrayOfObject[6] = ValuePool.getInt(dateTimeType.getJDBCPrecision());
          arrayOfObject[15] = ValuePool.getInt(dateTimeType.getJDBCPrecision());
        } 
        if (dateTimeType.isNumberType()) {
          arrayOfObject[6] = ValuePool.getInt(((NumberType)dateTimeType).getNumericPrecisionInRadix());
          arrayOfObject[9] = ValuePool.getInt(dateTimeType.getPrecisionRadix());
          if (dateTimeType.isExactNumberType())
            arrayOfObject[8] = ValuePool.getLong(((Type)dateTimeType).scale); 
        } 
        if (dateTimeType.isDateTimeType()) {
          int j = columnSchema.getDataType().displaySize();
          arrayOfObject[6] = ValuePool.getInt(j);
          arrayOfObject[14] = ValuePool.getInt(dateTimeType.getSqlDateTimeSub());
        } 
        arrayOfObject[10] = ValuePool.getInt(columnSchema.getNullability());
        arrayOfObject[11] = dITableInfo.getColRemarks(b);
        arrayOfObject[12] = columnSchema.getDefaultSQL();
        arrayOfObject[13] = ValuePool.getInt(((Type)dateTimeType).typeCode);
        arrayOfObject[16] = ValuePool.getInt(b + 1);
        arrayOfObject[17] = columnSchema.isNullable() ? "YES" : "NO";
        if (dateTimeType.isDistinctType())
          arrayOfObject[21] = dateTimeType.getName().getSchemaQualifiedStatementName(); 
        arrayOfObject[22] = columnSchema.isIdentity() ? "YES" : "NO";
        arrayOfObject[23] = columnSchema.isGenerated() ? "YES" : "NO";
        table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      } 
    } 
    return table;
  }
  
  final Table SYSTEM_CROSSREFERENCE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[2];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[2]);
      addColumn(table, "PKTABLE_CAT", SQL_IDENTIFIER);
      addColumn(table, "PKTABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(table, "PKTABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "PKCOLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "FKTABLE_CAT", SQL_IDENTIFIER);
      addColumn(table, "FKTABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(table, "FKTABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "FKCOLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "KEY_SEQ", (Type)Type.SQL_SMALLINT);
      addColumn(table, "UPDATE_RULE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "DELETE_RULE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "FK_NAME", SQL_IDENTIFIER);
      addColumn(table, "PK_NAME", SQL_IDENTIFIER);
      addColumn(table, "DEFERRABILITY", (Type)Type.SQL_SMALLINT);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[2]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 4, 5, 6, 8, 11 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(3);
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if (table1.isView() || !isAccessibleTable(paramSession, table1))
        continue; 
      for (Constraint constraint : table1.getConstraints()) {
        if (constraint.getConstraintType() == 0 && isAccessibleTable(paramSession, constraint.getRef()))
          hsqlArrayList.add(constraint); 
      } 
    } 
    for (byte b = 0; b < hsqlArrayList.size(); b++) {
      Constraint constraint = (Constraint)hsqlArrayList.get(b);
      Table table2 = constraint.getMain();
      String str3 = (table2.getName()).name;
      Table table1 = constraint.getRef();
      String str6 = (table1.getName()).name;
      String str1 = (table2.getCatalogName()).name;
      String str2 = (table2.getSchemaName()).name;
      String str4 = (table1.getCatalogName()).name;
      String str5 = (table1.getSchemaName()).name;
      int[] arrayOfInt1 = constraint.getMainColumns();
      int[] arrayOfInt2 = constraint.getRefColumns();
      int i = arrayOfInt2.length;
      String str7 = (constraint.getRefName()).name;
      String str8 = (constraint.getUniqueName()).name;
      Integer integer3 = ValuePool.getInt(constraint.getDeferability());
      Integer integer2 = ValuePool.getInt(constraint.getDeleteAction());
      Integer integer1 = ValuePool.getInt(constraint.getUpdateAction());
      for (byte b1 = 0; b1 < i; b1++) {
        Integer integer = ValuePool.getInt(b1 + 1);
        String str9 = table2.getColumn(arrayOfInt1[b1]).getNameString();
        String str10 = table1.getColumn(arrayOfInt2[b1]).getNameString();
        Object[] arrayOfObject = table.getEmptyRowData();
        arrayOfObject[0] = str1;
        arrayOfObject[1] = str2;
        arrayOfObject[2] = str3;
        arrayOfObject[3] = str9;
        arrayOfObject[4] = str4;
        arrayOfObject[5] = str5;
        arrayOfObject[6] = str6;
        arrayOfObject[7] = str10;
        arrayOfObject[8] = integer;
        arrayOfObject[9] = integer1;
        arrayOfObject[10] = integer2;
        arrayOfObject[11] = str7;
        arrayOfObject[12] = str8;
        arrayOfObject[13] = integer3;
        table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      } 
    } 
    return table;
  }
  
  final Table SYSTEM_INDEXINFO(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[3];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[3]);
      addColumn(table, "TABLE_CAT", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "NON_UNIQUE", (Type)Type.SQL_BOOLEAN);
      addColumn(table, "INDEX_QUALIFIER", SQL_IDENTIFIER);
      addColumn(table, "INDEX_NAME", SQL_IDENTIFIER);
      addColumn(table, "TYPE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "ORDINAL_POSITION", (Type)Type.SQL_SMALLINT);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "ASC_OR_DESC", CHARACTER_DATA);
      addColumn(table, "CARDINALITY", (Type)Type.SQL_INTEGER);
      addColumn(table, "PAGES", (Type)Type.SQL_INTEGER);
      addColumn(table, "FILTER_CONDITION", CHARACTER_DATA);
      addColumn(table, "ROW_CARDINALITY", (Type)Type.SQL_INTEGER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[3]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5, 7 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(3);
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if (table1.isView() || !isAccessibleTable(paramSession, table1))
        continue; 
      String str1 = (table1.getCatalogName()).name;
      String str2 = (table1.getSchemaName()).name;
      String str3 = (table1.getName()).name;
      Object object = null;
      String str4 = str1;
      int i = table1.getIndexCount();
      for (byte b = 0; b < i; b++) {
        Index index = table1.getIndex(b);
        int j = table1.getIndex(b).getColumnCount();
        if (j >= 1) {
          String str = (index.getName()).name;
          Boolean bool = index.isUnique() ? Boolean.FALSE : Boolean.TRUE;
          Object object1 = null;
          Integer integer2 = ValuePool.INTEGER_0;
          Object object2 = null;
          int[] arrayOfInt = index.getColumns();
          Integer integer1 = ValuePool.getInt(3);
          for (byte b1 = 0; b1 < j; b1++) {
            int k = arrayOfInt[b1];
            Object[] arrayOfObject = table.getEmptyRowData();
            arrayOfObject[0] = str1;
            arrayOfObject[1] = str2;
            arrayOfObject[2] = str3;
            arrayOfObject[3] = bool;
            arrayOfObject[4] = str4;
            arrayOfObject[5] = str;
            arrayOfObject[6] = integer1;
            arrayOfObject[7] = ValuePool.getInt(b1 + 1);
            arrayOfObject[8] = (table1.getColumn(arrayOfInt[b1]).getName()).name;
            arrayOfObject[9] = "A";
            arrayOfObject[10] = object1;
            arrayOfObject[11] = integer2;
            arrayOfObject[13] = object2;
            arrayOfObject[12] = object;
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } 
        } 
      } 
    } 
    return table;
  }
  
  final Table SYSTEM_PRIMARYKEYS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[4];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[4]);
      addColumn(table, "TABLE_CAT", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "KEY_SEQ", (Type)Type.SQL_SMALLINT);
      addColumn(table, "PK_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[4]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 3, 2, 1, 0 }, false);
      return table;
    } 
    HsqlDatabaseProperties hsqlDatabaseProperties = this.database.getProperties();
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(3);
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if (table1.isView() || !isAccessibleTable(paramSession, table1) || !table1.hasPrimaryKey())
        continue; 
      Constraint constraint = table1.getPrimaryConstraint();
      String str1 = (table1.getCatalogName()).name;
      String str2 = (table1.getSchemaName()).name;
      String str3 = (table1.getName()).name;
      String str4 = (constraint.getName()).name;
      int[] arrayOfInt = constraint.getMainColumns();
      int i = arrayOfInt.length;
      for (byte b = 0; b < i; b++) {
        Object[] arrayOfObject = table.getEmptyRowData();
        arrayOfObject[0] = str1;
        arrayOfObject[1] = str2;
        arrayOfObject[2] = str3;
        arrayOfObject[3] = (table1.getColumn(arrayOfInt[b]).getName()).name;
        arrayOfObject[4] = ValuePool.getInt(b + 1);
        arrayOfObject[5] = str4;
        table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      } 
    } 
    return table;
  }
  
  Table SYSTEM_PROCEDURECOLUMNS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[5];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[5]);
      addColumn(table, "PROCEDURE_CAT", SQL_IDENTIFIER);
      addColumn(table, "PROCEDURE_SCHEM", SQL_IDENTIFIER);
      addColumn(table, "PROCEDURE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_TYPE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "DATA_TYPE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "TYPE_NAME", SQL_IDENTIFIER);
      addColumn(table, "PRECISION", (Type)Type.SQL_INTEGER);
      addColumn(table, "LENGTH", (Type)Type.SQL_INTEGER);
      addColumn(table, "SCALE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "RADIX", (Type)Type.SQL_SMALLINT);
      addColumn(table, "NULLABLE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "REMARKS", CHARACTER_DATA);
      addColumn(table, "COLUMN_DEF", CHARACTER_DATA);
      addColumn(table, "SQL_DATA_TYPE", (Type)Type.SQL_INTEGER);
      addColumn(table, "SQL_DATETIME_SUB", (Type)Type.SQL_INTEGER);
      addColumn(table, "CHAR_OCTET_LENGTH", (Type)Type.SQL_INTEGER);
      addColumn(table, "ORDINAL_POSITION", (Type)Type.SQL_INTEGER);
      addColumn(table, "IS_NULLABLE", CHARACTER_DATA);
      addColumn(table, "SPECIFIC_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[5]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 19, 17 }, false);
      return table;
    } 
    boolean bool = this.database.sqlTranslateTTI;
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(18);
    while (iterator.hasNext()) {
      RoutineSchema routineSchema = (RoutineSchema)iterator.next();
      if (!paramSession.getGrantee().isAccessible((SchemaObject)routineSchema))
        continue; 
      Routine[] arrayOfRoutine = routineSchema.getSpecificRoutines();
      for (byte b = 0; b < arrayOfRoutine.length; b++) {
        Routine routine = arrayOfRoutine[b];
        int i = routine.getParameterCount();
        for (byte b1 = 0; b1 < i; b1++) {
          DateTimeType dateTimeType;
          ColumnSchema columnSchema = routine.getParameter(b1);
          Object[] arrayOfObject = table.getEmptyRowData();
          Type type = columnSchema.getDataType();
          if (bool) {
            CharacterType characterType;
            if (type.isIntervalType()) {
              characterType = ((IntervalType)type).getCharacterType();
            } else if (characterType.isDateTimeTypeWithZone()) {
              dateTimeType = ((DateTimeType)characterType).getDateTimeTypeWithoutZone();
            } 
          } 
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (routine.getSchemaName()).name;
          arrayOfObject[19] = (routine.getSpecificName()).name;
          arrayOfObject[2] = (routine.getName()).name;
          arrayOfObject[3] = (columnSchema.getName()).name;
          arrayOfObject[17] = ValuePool.getInt(b1 + 1);
          arrayOfObject[4] = ValuePool.getInt(columnSchema.getParameterMode());
          arrayOfObject[6] = dateTimeType.getFullNameString();
          arrayOfObject[5] = ValuePool.getInt(dateTimeType.getJDBCTypeCode());
          arrayOfObject[7] = ValuePool.INTEGER_0;
          arrayOfObject[16] = ValuePool.INTEGER_0;
          if (dateTimeType.isCharacterType()) {
            arrayOfObject[7] = ValuePool.getInt(dateTimeType.getJDBCPrecision());
            arrayOfObject[16] = ValuePool.getInt(dateTimeType.getJDBCPrecision());
          } 
          if (dateTimeType.isBinaryType()) {
            arrayOfObject[7] = ValuePool.getInt(dateTimeType.getJDBCPrecision());
            arrayOfObject[16] = ValuePool.getInt(dateTimeType.getJDBCPrecision());
          } 
          if (dateTimeType.isNumberType()) {
            arrayOfObject[7] = ValuePool.getInt(((NumberType)dateTimeType).getNumericPrecisionInRadix());
            arrayOfObject[10] = ValuePool.getLong(dateTimeType.getPrecisionRadix());
            if (dateTimeType.isExactNumberType())
              arrayOfObject[9] = ValuePool.getLong(((Type)dateTimeType).scale); 
          } 
          if (dateTimeType.isDateTimeType()) {
            int j = columnSchema.getDataType().displaySize();
            arrayOfObject[7] = ValuePool.getInt(j);
          } 
          arrayOfObject[14] = ValuePool.getInt((columnSchema.getDataType()).typeCode);
          arrayOfObject[11] = ValuePool.getInt(columnSchema.getNullability());
          arrayOfObject[18] = columnSchema.isNullable() ? "YES" : "NO";
          table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        } 
      } 
    } 
    return table;
  }
  
  Table SYSTEM_PROCEDURES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[6];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[6]);
      addColumn(table, "PROCEDURE_CAT", SQL_IDENTIFIER);
      addColumn(table, "PROCEDURE_SCHEM", SQL_IDENTIFIER);
      addColumn(table, "PROCEDURE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COL_4", (Type)Type.SQL_INTEGER);
      addColumn(table, "COL_5", (Type)Type.SQL_INTEGER);
      addColumn(table, "COL_6", (Type)Type.SQL_INTEGER);
      addColumn(table, "REMARKS", CHARACTER_DATA);
      addColumn(table, "PROCEDURE_TYPE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "SPECIFIC_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[6]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 8 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(24);
    while (iterator.hasNext()) {
      Routine routine = (Routine)iterator.next();
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (routine.getSchemaName()).name;
      arrayOfObject[2] = (routine.getName()).name;
      arrayOfObject[6] = (routine.getName()).comment;
      arrayOfObject[7] = routine.isProcedure() ? ValuePool.INTEGER_1 : ValuePool.INTEGER_2;
      arrayOfObject[8] = (routine.getSpecificName()).name;
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  final Table SYSTEM_CONNECTION_PROPERTIES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[18];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[18]);
      addColumn(table, "NAME", SQL_IDENTIFIER);
      addColumn(table, "MAX_LEN", (Type)Type.SQL_INTEGER);
      addColumn(table, "DEFAULT_VALUE", SQL_IDENTIFIER);
      addColumn(table, "DESCRIPTION", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[4]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0 }, true);
      return table;
    } 
    Iterator iterator = HsqlDatabaseProperties.getPropertiesMetaIterator();
    while (iterator.hasNext()) {
      Object[] arrayOfObject = (Object[])iterator.next();
      int i = ((Integer)arrayOfObject[1]).intValue();
      if ((i == 1) ? ("readonly".equals(arrayOfObject[0]) || "files_readonly".equals(arrayOfObject[0])) : (i == 2)) {
        Object[] arrayOfObject1 = table.getEmptyRowData();
        Object object = arrayOfObject[4];
        arrayOfObject1[0] = arrayOfObject[0];
        arrayOfObject1[1] = ValuePool.getInt(8);
        arrayOfObject1[2] = (object == null) ? null : object.toString();
        arrayOfObject1[3] = "see HyperSQL guide";
        table.insertSys(paramSession, paramPersistentStore, arrayOfObject1);
      } 
    } 
    return table;
  }
  
  protected void addProcRows(Session paramSession, Table paramTable, HsqlArrayList paramHsqlArrayList, String paramString1, String paramString2, String paramString3, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString4, Integer paramInteger4, String paramString5, String paramString6) {
    PersistentStore persistentStore = paramTable.getRowStore(paramSession);
    Object[] arrayOfObject = paramTable.getEmptyRowData();
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = paramString2;
    arrayOfObject[2] = paramString3;
    arrayOfObject[3] = paramInteger1;
    arrayOfObject[4] = paramInteger2;
    arrayOfObject[5] = paramInteger3;
    arrayOfObject[6] = paramString4;
    arrayOfObject[7] = paramInteger4;
    arrayOfObject[9] = paramString6;
    arrayOfObject[8] = paramString5;
    paramTable.insertSys(paramSession, persistentStore, arrayOfObject);
    if (paramHsqlArrayList != null) {
      int i = paramHsqlArrayList.size();
      for (byte b = 0; b < i; b++) {
        arrayOfObject = paramTable.getEmptyRowData();
        paramString3 = (String)paramHsqlArrayList.get(b);
        arrayOfObject[0] = paramString1;
        arrayOfObject[1] = paramString2;
        arrayOfObject[2] = paramString3;
        arrayOfObject[3] = paramInteger1;
        arrayOfObject[4] = paramInteger2;
        arrayOfObject[5] = paramInteger3;
        arrayOfObject[6] = paramString4;
        arrayOfObject[7] = paramInteger4;
        arrayOfObject[9] = "ALIAS";
        arrayOfObject[8] = paramString5;
        paramTable.insertSys(paramSession, persistentStore, arrayOfObject);
      } 
    } 
  }
  
  protected void addPColRows(Session paramSession, Table paramTable, HsqlArrayList paramHsqlArrayList, String paramString1, String paramString2, String paramString3, String paramString4, Integer paramInteger1, Integer paramInteger2, String paramString5, Integer paramInteger3, Integer paramInteger4, Integer paramInteger5, Integer paramInteger6, Integer paramInteger7, String paramString6, String paramString7, Integer paramInteger8, Integer paramInteger9, Integer paramInteger10, Integer paramInteger11, String paramString8, String paramString9) {
    PersistentStore persistentStore = paramTable.getRowStore(paramSession);
    Object[] arrayOfObject = paramTable.getEmptyRowData();
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = paramString2;
    arrayOfObject[2] = paramString3;
    arrayOfObject[3] = paramString4;
    arrayOfObject[4] = paramInteger1;
    arrayOfObject[5] = paramInteger2;
    arrayOfObject[6] = paramString5;
    arrayOfObject[7] = paramInteger3;
    arrayOfObject[8] = paramInteger4;
    arrayOfObject[9] = paramInteger5;
    arrayOfObject[10] = paramInteger6;
    arrayOfObject[11] = paramInteger7;
    arrayOfObject[12] = paramString6;
    arrayOfObject[13] = paramString7;
    arrayOfObject[14] = paramInteger8;
    arrayOfObject[15] = paramInteger9;
    arrayOfObject[16] = paramInteger10;
    arrayOfObject[17] = paramInteger11;
    arrayOfObject[18] = paramString8;
    arrayOfObject[19] = paramString9;
    paramTable.insertSys(paramSession, persistentStore, arrayOfObject);
    if (paramHsqlArrayList != null) {
      int i = paramHsqlArrayList.size();
      for (byte b = 0; b < i; b++) {
        arrayOfObject = paramTable.getEmptyRowData();
        paramString3 = (String)paramHsqlArrayList.get(b);
        arrayOfObject[0] = paramString1;
        arrayOfObject[1] = paramString2;
        arrayOfObject[2] = paramString3;
        arrayOfObject[3] = paramString4;
        arrayOfObject[4] = paramInteger1;
        arrayOfObject[5] = paramInteger2;
        arrayOfObject[6] = paramString5;
        arrayOfObject[7] = paramInteger3;
        arrayOfObject[8] = paramInteger4;
        arrayOfObject[9] = paramInteger5;
        arrayOfObject[10] = paramInteger6;
        arrayOfObject[11] = paramInteger7;
        arrayOfObject[12] = paramString6;
        arrayOfObject[13] = paramString7;
        arrayOfObject[14] = paramInteger8;
        arrayOfObject[15] = paramInteger9;
        arrayOfObject[16] = paramInteger10;
        arrayOfObject[17] = paramInteger11;
        arrayOfObject[18] = paramString8;
        arrayOfObject[19] = paramString9;
        paramTable.insertSys(paramSession, persistentStore, arrayOfObject);
      } 
    } 
  }
  
  final Table SYSTEM_SCHEMAS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[7];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[7]);
      addColumn(table, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "IS_DEFAULT", (Type)Type.SQL_BOOLEAN);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[7]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0 }, true);
      return table;
    } 
    String[] arrayOfString = this.database.schemaManager.getSchemaNamesArray();
    String str = (this.database.schemaManager.getDefaultSchemaHsqlName()).name;
    for (byte b = 0; b < arrayOfString.length; b++) {
      Object[] arrayOfObject = table.getEmptyRowData();
      String str1 = arrayOfString[b];
      arrayOfObject[0] = str1;
      arrayOfObject[1] = (this.database.getCatalogName()).name;
      arrayOfObject[2] = str1.equals(str) ? Boolean.TRUE : Boolean.FALSE;
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  final Table SYSTEM_TABLES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[8];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[8]);
      addColumn(table, "TABLE_CAT", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "TABLE_TYPE", CHARACTER_DATA);
      addColumn(table, "REMARKS", CHARACTER_DATA);
      addColumn(table, "TYPE_CAT", SQL_IDENTIFIER);
      addColumn(table, "TYPE_SCHEM", SQL_IDENTIFIER);
      addColumn(table, "TYPE_NAME", SQL_IDENTIFIER);
      addColumn(table, "SELF_REFERENCING_COL_NAME", SQL_IDENTIFIER);
      addColumn(table, "REF_GENERATION", CHARACTER_DATA);
      addColumn(table, "HSQLDB_TYPE", SQL_IDENTIFIER);
      addColumn(table, "READ_ONLY", (Type)Type.SQL_BOOLEAN);
      addColumn(table, "COMMIT_ACTION", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[8]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 3, 1, 2, 0 }, false);
      return table;
    } 
    Iterator iterator = allTables();
    DITableInfo dITableInfo = new DITableInfo();
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if (!isAccessibleTable(paramSession, table1))
        continue; 
      dITableInfo.setTable(table1);
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (table1.getSchemaName()).name;
      arrayOfObject[2] = (table1.getName()).name;
      arrayOfObject[3] = dITableInfo.getJDBCStandardType();
      arrayOfObject[4] = dITableInfo.getRemark();
      arrayOfObject[10] = dITableInfo.getHsqlType();
      arrayOfObject[11] = table1.isDataReadOnly() ? Boolean.TRUE : Boolean.FALSE;
      arrayOfObject[12] = table1.isTemp() ? (table1.onCommitPreserve() ? "PRESERVE" : "DELETE") : null;
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table SYSTEM_TABLETYPES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[9];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[9]);
      addColumn(table, "TABLE_TYPE", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[9]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0 }, true);
      return table;
    } 
    for (byte b = 0; b < tableTypes.length; b++) {
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = tableTypes[b];
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  final Table SYSTEM_TYPEINFO(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[10];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[10]);
      addColumn(table, "TYPE_NAME", SQL_IDENTIFIER);
      addColumn(table, "DATA_TYPE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "PRECISION", (Type)Type.SQL_INTEGER);
      addColumn(table, "LITERAL_PREFIX", CHARACTER_DATA);
      addColumn(table, "LITERAL_SUFFIX", CHARACTER_DATA);
      addColumn(table, "CREATE_PARAMS", CHARACTER_DATA);
      addColumn(table, "NULLABLE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "CASE_SENSITIVE", (Type)Type.SQL_BOOLEAN);
      addColumn(table, "SEARCHABLE", (Type)Type.SQL_INTEGER);
      addColumn(table, "UNSIGNED_ATTRIBUTE", (Type)Type.SQL_BOOLEAN);
      addColumn(table, "FIXED_PREC_SCALE", (Type)Type.SQL_BOOLEAN);
      addColumn(table, "AUTO_INCREMENT", (Type)Type.SQL_BOOLEAN);
      addColumn(table, "LOCAL_TYPE_NAME", SQL_IDENTIFIER);
      addColumn(table, "MINIMUM_SCALE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "MAXIMUM_SCALE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "SQL_DATA_TYPE", (Type)Type.SQL_INTEGER);
      addColumn(table, "SQL_DATETIME_SUB", (Type)Type.SQL_INTEGER);
      addColumn(table, "NUM_PREC_RADIX", (Type)Type.SQL_INTEGER);
      addColumn(table, "INTERVAL_PRECISION", (Type)Type.SQL_INTEGER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[10]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 1, 0 }, true);
      return table;
    } 
    Iterator iterator = Type.typeNames.keySet().iterator();
    boolean bool = this.database.sqlTranslateTTI;
    while (iterator.hasNext()) {
      DateTimeType dateTimeType;
      String str = (String)iterator.next();
      int i = Type.typeNames.get(str);
      Type type = Type.getDefaultType(i);
      if (type == null)
        continue; 
      if (bool) {
        CharacterType characterType;
        if (type.isIntervalType()) {
          characterType = ((IntervalType)type).getCharacterType();
        } else if (characterType.isDateTimeTypeWithZone()) {
          dateTimeType = ((DateTimeType)characterType).getDateTimeTypeWithoutZone();
        } 
      } 
      Object[] arrayOfObject1 = table.getEmptyRowData();
      arrayOfObject1[0] = str;
      arrayOfObject1[1] = ValuePool.getInt(dateTimeType.getJDBCTypeCode());
      long l = dateTimeType.getMaxPrecision();
      arrayOfObject1[2] = (l > 2147483647L) ? ValuePool.INTEGER_MAX : ValuePool.getInt((int)l);
      if (dateTimeType.isBinaryType() || dateTimeType.isCharacterType() || dateTimeType.isDateTimeType() || dateTimeType.isIntervalType()) {
        arrayOfObject1[3] = "'";
        arrayOfObject1[4] = "'";
      } 
      if (dateTimeType.acceptsPrecision() && dateTimeType.acceptsScale()) {
        arrayOfObject1[5] = "PRECISION,SCALE";
      } else if (dateTimeType.acceptsPrecision()) {
        arrayOfObject1[5] = dateTimeType.isNumberType() ? "PRECISION" : "LENGTH";
      } else if (dateTimeType.acceptsScale()) {
        arrayOfObject1[5] = "SCALE";
      } 
      arrayOfObject1[6] = ValuePool.INTEGER_1;
      arrayOfObject1[7] = (dateTimeType.isCharacterType() && dateTimeType.getCollation().isCaseSensitive()) ? Boolean.TRUE : Boolean.FALSE;
      if (dateTimeType.isLobType()) {
        arrayOfObject1[8] = ValuePool.INTEGER_0;
      } else if (dateTimeType.isCharacterType() || (dateTimeType.isBinaryType() && !dateTimeType.isBitType())) {
        arrayOfObject1[8] = ValuePool.getInt(3);
      } else {
        arrayOfObject1[8] = ValuePool.getInt(2);
      } 
      arrayOfObject1[9] = Boolean.FALSE;
      arrayOfObject1[10] = (((Type)dateTimeType).typeCode == 2 || ((Type)dateTimeType).typeCode == 3) ? Boolean.TRUE : Boolean.FALSE;
      arrayOfObject1[11] = dateTimeType.isIntegralType() ? Boolean.TRUE : Boolean.FALSE;
      arrayOfObject1[12] = null;
      arrayOfObject1[13] = ValuePool.INTEGER_0;
      arrayOfObject1[14] = ValuePool.getInt(dateTimeType.getMaxScale());
      arrayOfObject1[15] = null;
      arrayOfObject1[16] = null;
      arrayOfObject1[17] = ValuePool.getInt(dateTimeType.getPrecisionRadix());
      if (dateTimeType.isIntervalType())
        arrayOfObject1[18] = null; 
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject1);
    } 
    Object[] arrayOfObject = table.getEmptyRowData();
    arrayOfObject[0] = "DISTINCT";
    arrayOfObject[1] = ValuePool.getInt(2001);
    return table;
  }
  
  Table SYSTEM_UDTS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[11];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[11]);
      addColumn(table, "TYPE_CAT", SQL_IDENTIFIER);
      addColumn(table, "TYPE_SCHEM", SQL_IDENTIFIER);
      addColumn(table, "TYPE_NAME", SQL_IDENTIFIER);
      addColumn(table, "CLASS_NAME", CHARACTER_DATA);
      addColumn(table, "DATA_TYPE", (Type)Type.SQL_INTEGER);
      addColumn(table, "REMARKS", CHARACTER_DATA);
      addColumn(table, "BASE_TYPE", (Type)Type.SQL_SMALLINT);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[11]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, null, false);
      return table;
    } 
    boolean bool = this.database.sqlTranslateTTI;
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(12);
    while (iterator.hasNext()) {
      DateTimeType dateTimeType;
      Type type1 = (Type)iterator.next();
      if (!type1.isDistinctType())
        continue; 
      Object[] arrayOfObject = table.getEmptyRowData();
      Type type2 = type1;
      if (bool) {
        CharacterType characterType;
        if (type2.isIntervalType()) {
          characterType = ((IntervalType)type2).getCharacterType();
        } else if (characterType.isDateTimeTypeWithZone()) {
          dateTimeType = ((DateTimeType)characterType).getDateTimeTypeWithoutZone();
        } 
      } 
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (type1.getSchemaName()).name;
      arrayOfObject[2] = (type1.getName()).name;
      arrayOfObject[3] = dateTimeType.getJDBCClassName();
      arrayOfObject[4] = ValuePool.getInt(2001);
      arrayOfObject[5] = null;
      arrayOfObject[6] = ValuePool.getInt(dateTimeType.getJDBCTypeCode());
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table SYSTEM_VERSIONCOLUMNS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[13];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[13]);
      addColumn(table, "SCOPE", (Type)Type.SQL_INTEGER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "DATA_TYPE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "TYPE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_SIZE", (Type)Type.SQL_SMALLINT);
      addColumn(table, "BUFFER_LENGTH", (Type)Type.SQL_INTEGER);
      addColumn(table, "DECIMAL_DIGITS", (Type)Type.SQL_SMALLINT);
      addColumn(table, "PSEUDO_COLUMN", (Type)Type.SQL_SMALLINT);
      addColumn(table, "TABLE_CAT", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[13]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, null, false);
      return table;
    } 
    return table;
  }
  
  Table SYSTEM_USERS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[12];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[12]);
      addColumn(table, "USER_NAME", SQL_IDENTIFIER);
      addColumn(table, "ADMIN", (Type)Type.SQL_BOOLEAN);
      addColumn(table, "INITIAL_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "AUTHENTICATION", SQL_IDENTIFIER);
      addColumn(table, "PASSWORD_DIGEST", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[12]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0 }, true);
      return table;
    } 
    HsqlArrayList hsqlArrayList = this.database.getUserManager().listVisibleUsers(paramSession);
    for (byte b = 0; b < hsqlArrayList.size(); b++) {
      Object[] arrayOfObject = table.getEmptyRowData();
      User user = (User)hsqlArrayList.get(b);
      HsqlNameManager.HsqlName hsqlName = user.getInitialSchema();
      arrayOfObject[0] = user.getName().getNameString();
      arrayOfObject[1] = ValuePool.getBoolean(user.isAdmin());
      arrayOfObject[2] = (hsqlName == null) ? null : hsqlName.name;
      arrayOfObject[3] = user.isLocalOnly ? "LOCAL" : (user.isExternalOnly ? "EXTERNAL" : "ANY");
      arrayOfObject[4] = user.getPasswordDigest();
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  final Table COLUMN_PRIVILEGES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[34];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[34]);
      addColumn(table, "GRANTOR", SQL_IDENTIFIER);
      addColumn(table, "GRANTEE", SQL_IDENTIFIER);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(table, "IS_GRANTABLE", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[34]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 2, 3, 4, 5, 6, 1, 0 }, false);
      return table;
    } 
    OrderedHashSet orderedHashSet = paramSession.getGrantee().getGranteeAndAllRolesWithPublic();
    Iterator iterator = allTables();
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      String str3 = (table1.getName()).name;
      String str1 = (this.database.getCatalogName()).name;
      String str2 = (table1.getSchemaName()).name;
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        Grantee grantee = (Grantee)orderedHashSet.get(b);
        OrderedHashSet orderedHashSet1 = grantee.getAllDirectPrivileges((SchemaObject)table1);
        OrderedHashSet orderedHashSet2 = grantee.getAllGrantedPrivileges((SchemaObject)table1);
        if (!orderedHashSet2.isEmpty()) {
          orderedHashSet2.addAll((Collection)orderedHashSet1);
          orderedHashSet1 = orderedHashSet2;
        } 
        for (byte b1 = 0; b1 < orderedHashSet1.size(); b1++) {
          Right right1 = (Right)orderedHashSet1.get(b1);
          Right right2 = right1.getGrantableRights();
          for (byte b2 = 0; b2 < Right.privilegeTypes.length; b2++) {
            OrderedHashSet orderedHashSet3 = right1.getColumnsForPrivilege(table1, Right.privilegeTypes[b2]);
            OrderedHashSet orderedHashSet4 = right2.getColumnsForPrivilege(table1, Right.privilegeTypes[b2]);
            for (byte b3 = 0; b3 < orderedHashSet3.size(); b3++) {
              HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet3.get(b3);
              Object[] arrayOfObject = table.getEmptyRowData();
              arrayOfObject[0] = (right1.getGrantor().getName()).name;
              arrayOfObject[1] = (right1.getGrantee().getName()).name;
              arrayOfObject[2] = str1;
              arrayOfObject[3] = str2;
              arrayOfObject[4] = str3;
              arrayOfObject[5] = hsqlName.name;
              arrayOfObject[6] = Right.privilegeNames[b2];
              arrayOfObject[7] = (right1.getGrantee() == table1.getOwner() || orderedHashSet4.contains(hsqlName)) ? "YES" : "NO";
              try {
                table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
              } catch (HsqlException hsqlException) {}
            } 
          } 
        } 
      } 
    } 
    return table;
  }
  
  final Table SEQUENCES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[70];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[70]);
      addColumn(table, "SEQUENCE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SEQUENCE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SEQUENCE_NAME", SQL_IDENTIFIER);
      addColumn(table, "DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(table, "MAXIMUM_VALUE", CHARACTER_DATA);
      addColumn(table, "MINIMUM_VALUE", CHARACTER_DATA);
      addColumn(table, "INCREMENT", CHARACTER_DATA);
      addColumn(table, "CYCLE_OPTION", YES_OR_NO);
      addColumn(table, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(table, "START_WITH", CHARACTER_DATA);
      addColumn(table, "NEXT_VALUE", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[70]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(7);
    while (iterator.hasNext()) {
      NumberSequence numberSequence = (NumberSequence)iterator.next();
      if (!paramSession.getGrantee().isAccessible((SchemaObject)numberSequence))
        continue; 
      Object[] arrayOfObject = table.getEmptyRowData();
      NumberType numberType = (NumberType)numberSequence.getDataType();
      byte b = (numberType.typeCode == 2 || numberType.typeCode == 3) ? 10 : 2;
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (numberSequence.getSchemaName()).name;
      arrayOfObject[2] = (numberSequence.getName()).name;
      arrayOfObject[3] = numberSequence.getDataType().getFullNameString();
      arrayOfObject[4] = ValuePool.getInt(numberType.getPrecision());
      arrayOfObject[5] = ValuePool.getInt(b);
      arrayOfObject[6] = ValuePool.INTEGER_0;
      arrayOfObject[7] = String.valueOf(numberSequence.getMaxValue());
      arrayOfObject[8] = String.valueOf(numberSequence.getMinValue());
      arrayOfObject[9] = String.valueOf(numberSequence.getIncrement());
      arrayOfObject[10] = numberSequence.isCycle() ? "YES" : "NO";
      arrayOfObject[11] = arrayOfObject[3];
      arrayOfObject[12] = arrayOfObject[4];
      arrayOfObject[13] = arrayOfObject[13];
      arrayOfObject[14] = String.valueOf(numberSequence.getStartValue());
      arrayOfObject[15] = String.valueOf(numberSequence.peek());
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  final Table SYSTEM_SEQUENCES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[14];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[14]);
      addColumn(table, "SEQUENCE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SEQUENCE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SEQUENCE_NAME", SQL_IDENTIFIER);
      addColumn(table, "DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(table, "MAXIMUM_VALUE", CHARACTER_DATA);
      addColumn(table, "MINIMUM_VALUE", CHARACTER_DATA);
      addColumn(table, "INCREMENT", CHARACTER_DATA);
      addColumn(table, "CYCLE_OPTION", YES_OR_NO);
      addColumn(table, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(table, "START_WITH", CHARACTER_DATA);
      addColumn(table, "NEXT_VALUE", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[14]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(7);
    while (iterator.hasNext()) {
      NumberSequence numberSequence = (NumberSequence)iterator.next();
      if (!paramSession.getGrantee().isAccessible((SchemaObject)numberSequence))
        continue; 
      Object[] arrayOfObject = table.getEmptyRowData();
      NumberType numberType = (NumberType)numberSequence.getDataType();
      byte b = (numberType.typeCode == 2 || numberType.typeCode == 3) ? 10 : 2;
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (numberSequence.getSchemaName()).name;
      arrayOfObject[2] = (numberSequence.getName()).name;
      arrayOfObject[3] = numberSequence.getDataType().getFullNameString();
      arrayOfObject[4] = ValuePool.getInt(numberType.getPrecision());
      arrayOfObject[5] = ValuePool.getInt(b);
      arrayOfObject[6] = ValuePool.INTEGER_0;
      arrayOfObject[7] = String.valueOf(numberSequence.getMaxValue());
      arrayOfObject[8] = String.valueOf(numberSequence.getMinValue());
      arrayOfObject[9] = String.valueOf(numberSequence.getIncrement());
      arrayOfObject[10] = numberSequence.isCycle() ? "YES" : "NO";
      arrayOfObject[11] = arrayOfObject[3];
      arrayOfObject[12] = arrayOfObject[4];
      arrayOfObject[13] = arrayOfObject[13];
      arrayOfObject[14] = String.valueOf(numberSequence.getStartValue());
      arrayOfObject[15] = String.valueOf(numberSequence.peek());
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  final Table TABLE_PRIVILEGES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[78];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[78]);
      addColumn(table, "GRANTOR", SQL_IDENTIFIER);
      addColumn(table, "GRANTEE", SQL_IDENTIFIER);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(table, "IS_GRANTABLE", YES_OR_NO);
      addColumn(table, "WITH_HIERARCHY", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[70]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5, 6 }, false);
      return table;
    } 
    OrderedHashSet orderedHashSet = paramSession.getGrantee().getGranteeAndAllRolesWithPublic();
    Iterator iterator = allTables();
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      String str3 = (table1.getName()).name;
      String str1 = (table1.getCatalogName()).name;
      String str2 = (table1.getSchemaName()).name;
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        Grantee grantee = (Grantee)orderedHashSet.get(b);
        OrderedHashSet orderedHashSet1 = grantee.getAllDirectPrivileges((SchemaObject)table1);
        OrderedHashSet orderedHashSet2 = grantee.getAllGrantedPrivileges((SchemaObject)table1);
        if (!orderedHashSet2.isEmpty()) {
          orderedHashSet2.addAll((Collection)orderedHashSet1);
          orderedHashSet1 = orderedHashSet2;
        } 
        for (byte b1 = 0; b1 < orderedHashSet1.size(); b1++) {
          Right right1 = (Right)orderedHashSet1.get(b1);
          Right right2 = right1.getGrantableRights();
          for (byte b2 = 0; b2 < Right.privilegeTypes.length; b2++) {
            if (right1.canAccessFully(Right.privilegeTypes[b2])) {
              String str = Right.privilegeNames[b2];
              Object[] arrayOfObject = table.getEmptyRowData();
              arrayOfObject[0] = (right1.getGrantor().getName()).name;
              arrayOfObject[1] = (right1.getGrantee().getName()).name;
              arrayOfObject[2] = str1;
              arrayOfObject[3] = str2;
              arrayOfObject[4] = str3;
              arrayOfObject[5] = str;
              arrayOfObject[6] = (right1.getGrantee() == table1.getOwner() || right2.canAccessFully(Right.privilegeTypes[b2])) ? "YES" : "NO";
              arrayOfObject[7] = "NO";
              try {
                table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
              } catch (HsqlException hsqlException) {}
            } 
          } 
        } 
      } 
    } 
    return table;
  }
  
  Table TABLES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[79];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[79]);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "TABLE_TYPE", CHARACTER_DATA);
      addColumn(table, "SELF_REFERENCING_COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "REFERENCE_GENERATION", CHARACTER_DATA);
      addColumn(table, "USER_DEFINED_TYPE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "USER_DEFINED_TYPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "USER_DEFINED_TYPE_NAME", SQL_IDENTIFIER);
      addColumn(table, "IS_INSERTABLE_INTO", YES_OR_NO);
      addColumn(table, "IS_TYPED", YES_OR_NO);
      addColumn(table, "COMMIT_ACTION", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[79]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2 }, false);
      return table;
    } 
    Iterator iterator = allTables();
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if (!isAccessibleTable(paramSession, table1))
        continue; 
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (table1.getSchemaName()).name;
      arrayOfObject[2] = (table1.getName()).name;
      switch (table1.getTableType()) {
        case 1:
        case 8:
          arrayOfObject[3] = "VIEW";
          arrayOfObject[9] = table1.isInsertable() ? "YES" : "NO";
          break;
        case 3:
        case 6:
          arrayOfObject[3] = "GLOBAL TEMPORARY";
          arrayOfObject[9] = "YES";
          break;
        default:
          arrayOfObject[3] = "BASE TABLE";
          arrayOfObject[9] = table1.isInsertable() ? "YES" : "NO";
          break;
      } 
      arrayOfObject[4] = null;
      arrayOfObject[5] = null;
      arrayOfObject[6] = null;
      arrayOfObject[7] = null;
      arrayOfObject[8] = null;
      arrayOfObject[10] = "NO";
      arrayOfObject[11] = table1.isTemp() ? (table1.onCommitPreserve() ? "PRESERVE" : "DELETE") : null;
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  final Table INFORMATION_SCHEMA_CATALOG_NAME(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[44];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[44]);
      addColumn(table, "CATALOG_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[44]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0 }, true);
      return table;
    } 
    Object[] arrayOfObject = table.getEmptyRowData();
    arrayOfObject[0] = (this.database.getCatalogName()).name;
    table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    return table;
  }
  
  static {
    synchronized (DatabaseInformationMain.class) {
      nonCachedTablesSet = new HashSet();
      sysTableHsqlNames = new HsqlNameManager.HsqlName[sysTableNames.length];
      for (byte b = 0; b < sysTableNames.length; b++) {
        sysTableHsqlNames[b] = HsqlNameManager.newInfoSchemaTableName(sysTableNames[b]);
        (sysTableHsqlNames[b]).schema = SqlInvariants.INFORMATION_SCHEMA_HSQLNAME;
        sysTableSessionDependent[b] = true;
      } 
      nonCachedTablesSet.add("SYSTEM_CACHEINFO");
      nonCachedTablesSet.add("SYSTEM_SESSIONINFO");
      nonCachedTablesSet.add("SYSTEM_SESSIONS");
      nonCachedTablesSet.add("SYSTEM_PROPERTIES");
      nonCachedTablesSet.add("SYSTEM_SEQUENCES");
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\dbinfo\DatabaseInformationMain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */