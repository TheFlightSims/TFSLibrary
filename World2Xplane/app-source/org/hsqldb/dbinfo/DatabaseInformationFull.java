package org.hsqldb.dbinfo;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.hsqldb.ColumnSchema;
import org.hsqldb.Constraint;
import org.hsqldb.Database;
import org.hsqldb.Expression;
import org.hsqldb.ExpressionColumn;
import org.hsqldb.HsqlException;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.NumberSequence;
import org.hsqldb.Routine;
import org.hsqldb.RoutineSchema;
import org.hsqldb.Schema;
import org.hsqldb.SchemaObject;
import org.hsqldb.SchemaObjectSet;
import org.hsqldb.Session;
import org.hsqldb.SqlInvariants;
import org.hsqldb.Statement;
import org.hsqldb.Table;
import org.hsqldb.TextTable;
import org.hsqldb.TriggerDef;
import org.hsqldb.View;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.LineGroupReader;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.lib.WrapperIterator;
import org.hsqldb.map.ValuePool;
import org.hsqldb.persist.DataFileCache;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.persist.TextCache;
import org.hsqldb.persist.TextFileSettings;
import org.hsqldb.result.Result;
import org.hsqldb.rights.Grantee;
import org.hsqldb.rights.Right;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.Charset;
import org.hsqldb.types.Collation;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

final class DatabaseInformationFull extends DatabaseInformationMain {
  static final HashMappedList statementMap;
  
  DatabaseInformationFull(Database paramDatabase) {
    super(paramDatabase);
  }
  
  protected Table generateTable(Session paramSession, PersistentStore paramPersistentStore, int paramInt) {
    switch (paramInt) {
      case 15:
        return SYSTEM_CACHEINFO(paramSession, paramPersistentStore);
      case 16:
        return SYSTEM_COLUMN_SEQUENCE_USAGE(paramSession, paramPersistentStore);
      case 17:
        return SYSTEM_COMMENTS(paramSession, paramPersistentStore);
      case 20:
        return SYSTEM_SESSIONINFO(paramSession, paramPersistentStore);
      case 19:
        return SYSTEM_PROPERTIES(paramSession, paramPersistentStore);
      case 21:
        return SYSTEM_SESSIONS(paramSession, paramPersistentStore);
      case 22:
        return SYSTEM_TEXTTABLES(paramSession, paramPersistentStore);
      case 23:
        return SYSTEM_TABLESTATS(paramSession, paramPersistentStore);
      case 24:
        return ADMINISTRABLE_ROLE_AUTHORIZATIONS(paramSession, paramPersistentStore);
      case 25:
        return APPLICABLE_ROLES(paramSession, paramPersistentStore);
      case 26:
        return ASSERTIONS(paramSession, paramPersistentStore);
      case 27:
        return AUTHORIZATIONS(paramSession, paramPersistentStore);
      case 28:
        return CHARACTER_SETS(paramSession, paramPersistentStore);
      case 29:
        return CHECK_CONSTRAINT_ROUTINE_USAGE(paramSession, paramPersistentStore);
      case 30:
        return CHECK_CONSTRAINTS(paramSession, paramPersistentStore);
      case 31:
        return COLLATIONS(paramSession, paramPersistentStore);
      case 32:
        return COLUMN_COLUMN_USAGE(paramSession, paramPersistentStore);
      case 33:
        return COLUMN_DOMAIN_USAGE(paramSession, paramPersistentStore);
      case 35:
        return COLUMN_UDT_USAGE(paramSession, paramPersistentStore);
      case 37:
        return CONSTRAINT_COLUMN_USAGE(paramSession, paramPersistentStore);
      case 38:
        return CONSTRAINT_TABLE_USAGE(paramSession, paramPersistentStore);
      case 36:
        return COLUMNS(paramSession, paramPersistentStore);
      case 39:
        return DATA_TYPE_PRIVILEGES(paramSession, paramPersistentStore);
      case 40:
        return DOMAIN_CONSTRAINTS(paramSession, paramPersistentStore);
      case 41:
        return DOMAINS(paramSession, paramPersistentStore);
      case 42:
        return ELEMENT_TYPES(paramSession, paramPersistentStore);
      case 43:
        return ENABLED_ROLES(paramSession, paramPersistentStore);
      case 45:
        return JAR_JAR_USAGE(paramSession, paramPersistentStore);
      case 46:
        return JARS(paramSession, paramPersistentStore);
      case 47:
        return KEY_COLUMN_USAGE(paramSession, paramPersistentStore);
      case 48:
        return METHOD_SPECIFICATIONS(paramSession, paramPersistentStore);
      case 49:
        return MODULE_COLUMN_USAGE(paramSession, paramPersistentStore);
      case 50:
        return MODULE_PRIVILEGES(paramSession, paramPersistentStore);
      case 51:
        return MODULE_TABLE_USAGE(paramSession, paramPersistentStore);
      case 52:
        return MODULES(paramSession, paramPersistentStore);
      case 53:
        return PARAMETERS(paramSession, paramPersistentStore);
      case 54:
        return REFERENTIAL_CONSTRAINTS(paramSession, paramPersistentStore);
      case 55:
        return ROLE_AUTHORIZATION_DESCRIPTORS(paramSession, paramPersistentStore);
      case 56:
        return ROLE_COLUMN_GRANTS(paramSession, paramPersistentStore);
      case 58:
        return ROLE_ROUTINE_GRANTS(paramSession, paramPersistentStore);
      case 59:
        return ROLE_TABLE_GRANTS(paramSession, paramPersistentStore);
      case 61:
        return ROLE_USAGE_GRANTS(paramSession, paramPersistentStore);
      case 60:
        return ROLE_UDT_GRANTS(paramSession, paramPersistentStore);
      case 62:
        return ROUTINE_COLUMN_USAGE(paramSession, paramPersistentStore);
      case 63:
        return ROUTINE_JAR_USAGE(paramSession, paramPersistentStore);
      case 64:
        return ROUTINE_PRIVILEGES(paramSession, paramPersistentStore);
      case 65:
        return ROUTINE_ROUTINE_USAGE(paramSession, paramPersistentStore);
      case 66:
        return ROUTINE_SEQUENCE_USAGE(paramSession, paramPersistentStore);
      case 67:
        return ROUTINE_TABLE_USAGE(paramSession, paramPersistentStore);
      case 68:
        return ROUTINES(paramSession, paramPersistentStore);
      case 69:
        return SCHEMATA(paramSession, paramPersistentStore);
      case 70:
        return SEQUENCES(paramSession, paramPersistentStore);
      case 71:
        return SQL_FEATURES(paramSession, paramPersistentStore);
      case 72:
        return SQL_IMPLEMENTATION_INFO(paramSession, paramPersistentStore);
      case 73:
        return SQL_PACKAGES(paramSession, paramPersistentStore);
      case 74:
        return SQL_PARTS(paramSession, paramPersistentStore);
      case 75:
        return SQL_SIZING(paramSession, paramPersistentStore);
      case 76:
        return SQL_SIZING_PROFILES(paramSession, paramPersistentStore);
      case 77:
        return TABLE_CONSTRAINTS(paramSession, paramPersistentStore);
      case 79:
        return TABLES(paramSession, paramPersistentStore);
      case 80:
        return TRANSLATIONS(paramSession, paramPersistentStore);
      case 85:
        return TRIGGERED_UPDATE_COLUMNS(paramSession, paramPersistentStore);
      case 81:
        return TRIGGER_COLUMN_USAGE(paramSession, paramPersistentStore);
      case 82:
        return TRIGGER_ROUTINE_USAGE(paramSession, paramPersistentStore);
      case 83:
        return TRIGGER_SEQUENCE_USAGE(paramSession, paramPersistentStore);
      case 84:
        return TRIGGER_TABLE_USAGE(paramSession, paramPersistentStore);
      case 86:
        return TRIGGERS(paramSession, paramPersistentStore);
      case 88:
        return UDT_PRIVILEGES(paramSession, paramPersistentStore);
      case 89:
        return USAGE_PRIVILEGES(paramSession, paramPersistentStore);
      case 90:
        return USER_DEFINED_TYPES(paramSession, paramPersistentStore);
      case 91:
        return VIEW_COLUMN_USAGE(paramSession, paramPersistentStore);
      case 92:
        return VIEW_ROUTINE_USAGE(paramSession, paramPersistentStore);
      case 93:
        return VIEW_TABLE_USAGE(paramSession, paramPersistentStore);
      case 94:
        return VIEWS(paramSession, paramPersistentStore);
    } 
    return super.generateTable(paramSession, paramPersistentStore, paramInt);
  }
  
  Table SYSTEM_CACHEINFO(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[15];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[15]);
      addColumn(table, "CACHE_FILE", CHARACTER_DATA);
      addColumn(table, "MAX_CACHE_COUNT", CARDINAL_NUMBER);
      addColumn(table, "MAX_CACHE_BYTES", CARDINAL_NUMBER);
      addColumn(table, "CACHE_SIZE", CARDINAL_NUMBER);
      addColumn(table, "CACHE_BYTES", CARDINAL_NUMBER);
      addColumn(table, "FILE_LOST_BYTES", CARDINAL_NUMBER);
      addColumn(table, "FILE_FREE_POS", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[15]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0 }, true);
      return table;
    } 
    DataFileCache dataFileCache = null;
    HashSet hashSet = new HashSet();
    Iterator iterator2 = this.database.schemaManager.databaseObjectIterator(3);
    while (iterator2.hasNext()) {
      Table table1 = (Table)iterator2.next();
      PersistentStore persistentStore = table1.getRowStore(paramSession);
      if (paramSession.getGrantee().isFullyAccessibleByRole(table1.getName())) {
        if (persistentStore != null)
          dataFileCache = persistentStore.getCache(); 
        if (dataFileCache != null)
          hashSet.add(dataFileCache); 
      } 
    } 
    Iterator iterator1 = hashSet.iterator();
    while (iterator1.hasNext()) {
      dataFileCache = (DataFileCache)iterator1.next();
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = FileUtil.getFileUtil().canonicalOrAbsolutePath(dataFileCache.getFileName());
      arrayOfObject[1] = ValuePool.getLong(dataFileCache.capacity());
      arrayOfObject[2] = ValuePool.getLong(dataFileCache.bytesCapacity());
      arrayOfObject[3] = ValuePool.getLong(dataFileCache.getCachedObjectCount());
      arrayOfObject[4] = ValuePool.getLong(dataFileCache.getTotalCachedBlockSize());
      arrayOfObject[5] = ValuePool.getLong(dataFileCache.getLostBlockSize());
      arrayOfObject[6] = ValuePool.getLong(dataFileCache.getFileFreePos());
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table SYSTEM_COLUMN_SEQUENCE_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[16];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[16]);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "SEQUENCE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SEQUENCE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SEQUENCE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[16]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4 }, false);
      return table;
    } 
    Iterator iterator = allTables();
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if (!table1.hasIdentityColumn())
        continue; 
      OrderedHashSet orderedHashSet = paramSession.getGrantee().getColumnsForAllPrivileges((SchemaObject)table1);
      if (orderedHashSet.isEmpty())
        continue; 
      int i = table1.getColumnCount();
      for (byte b = 0; b < i; b++) {
        ColumnSchema columnSchema = table1.getColumn(b);
        if (columnSchema.isIdentity()) {
          NumberSequence numberSequence = columnSchema.getIdentitySequence();
          if (numberSequence.getName() != null && orderedHashSet.contains(columnSchema.getName())) {
            Object[] arrayOfObject = table.getEmptyRowData();
            arrayOfObject[0] = (this.database.getCatalogName()).name;
            arrayOfObject[1] = (table1.getSchemaName()).name;
            arrayOfObject[2] = (table1.getName()).name;
            arrayOfObject[3] = (columnSchema.getName()).name;
            arrayOfObject[4] = (this.database.getCatalogName()).name;
            arrayOfObject[5] = (numberSequence.getSchemaName()).name;
            arrayOfObject[6] = (numberSequence.getName()).name;
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } 
        } 
      } 
    } 
    return table;
  }
  
  Table SYSTEM_COMMENTS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[17];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[17]);
      addColumn(table, "OBJECT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_NAME", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_TYPE", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "COMMENT", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[17]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4 }, false);
      return table;
    } 
    DITableInfo dITableInfo = new DITableInfo();
    Iterator iterator = allTables();
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if (!paramSession.getGrantee().isAccessible((SchemaObject)table1))
        continue; 
      dITableInfo.setTable(table1);
      int i = table1.getColumnCount();
      for (byte b = 0; b < i; b++) {
        ColumnSchema columnSchema = table1.getColumn(b);
        if ((columnSchema.getName()).comment != null) {
          Object[] arrayOfObject1 = table.getEmptyRowData();
          arrayOfObject1[0] = (this.database.getCatalogName()).name;
          arrayOfObject1[1] = (table1.getSchemaName()).name;
          arrayOfObject1[2] = (table1.getName()).name;
          arrayOfObject1[3] = "COLUMN";
          arrayOfObject1[4] = (columnSchema.getName()).name;
          arrayOfObject1[5] = (columnSchema.getName()).comment;
          table.insertSys(paramSession, paramPersistentStore, arrayOfObject1);
        } 
      } 
      if (table1.getTableType() != 1 && (table1.getName()).comment == null)
        continue; 
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (table1.getSchemaName()).name;
      arrayOfObject[2] = (table1.getName()).name;
      arrayOfObject[3] = (table1.isView() || table1.getTableType() == 1) ? "VIEW" : "TABLE";
      arrayOfObject[4] = null;
      arrayOfObject[5] = dITableInfo.getRemark();
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    iterator = this.database.schemaManager.databaseObjectIterator(18);
    while (iterator.hasNext()) {
      SchemaObject schemaObject = (SchemaObject)iterator.next();
      if (!paramSession.getGrantee().isAccessible(schemaObject) || (schemaObject.getName()).comment == null)
        continue; 
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (schemaObject.getSchemaName()).name;
      arrayOfObject[2] = (schemaObject.getName()).name;
      arrayOfObject[3] = "ROUTINE";
      arrayOfObject[4] = null;
      arrayOfObject[5] = (schemaObject.getName()).comment;
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table SYSTEM_PROPERTIES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[19];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[19]);
      addColumn(table, "PROPERTY_SCOPE", CHARACTER_DATA);
      addColumn(table, "PROPERTY_NAMESPACE", CHARACTER_DATA);
      addColumn(table, "PROPERTY_NAME", CHARACTER_DATA);
      addColumn(table, "PROPERTY_VALUE", CHARACTER_DATA);
      addColumn(table, "PROPERTY_CLASS", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[19]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2 }, true);
      return table;
    } 
    String str1 = "SESSION";
    HsqlDatabaseProperties hsqlDatabaseProperties = this.database.getProperties();
    String str2 = "database.properties";
    Iterator iterator = hsqlDatabaseProperties.getUserDefinedPropertyData().iterator();
    while (iterator.hasNext()) {
      Object[] arrayOfObject2 = (Object[])iterator.next();
      Object[] arrayOfObject1 = table.getEmptyRowData();
      arrayOfObject1[0] = str1;
      arrayOfObject1[1] = str2;
      arrayOfObject1[2] = arrayOfObject2[0];
      arrayOfObject1[3] = this.database.logger.getValueStringForProperty((String)arrayOfObject1[2]);
      if (arrayOfObject1[3] == null)
        arrayOfObject1[3] = hsqlDatabaseProperties.getPropertyString((String)arrayOfObject1[2]); 
      arrayOfObject1[4] = arrayOfObject2[2];
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject1);
    } 
    return table;
  }
  
  Table SYSTEM_SESSIONINFO(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[20];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[20]);
      addColumn(table, "KEY", CHARACTER_DATA);
      addColumn(table, "VALUE", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[20]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0 }, true);
      return table;
    } 
    Object[] arrayOfObject = table.getEmptyRowData();
    arrayOfObject[0] = "SESSION ID";
    arrayOfObject[1] = String.valueOf(paramSession.getId());
    table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    arrayOfObject = table.getEmptyRowData();
    arrayOfObject[0] = "AUTOCOMMIT";
    arrayOfObject[1] = paramSession.isAutoCommit() ? "TRUE" : "FALSE";
    table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    arrayOfObject = table.getEmptyRowData();
    arrayOfObject[0] = "USER";
    arrayOfObject[1] = paramSession.getUsername();
    table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    arrayOfObject = table.getEmptyRowData();
    arrayOfObject[0] = "SESSION READONLY";
    arrayOfObject[1] = paramSession.isReadOnlyDefault() ? "TRUE" : "FALSE";
    table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    arrayOfObject = table.getEmptyRowData();
    arrayOfObject[0] = "DATABASE READONLY";
    arrayOfObject[1] = this.database.isReadOnly() ? "TRUE" : "FALSE";
    table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    arrayOfObject = table.getEmptyRowData();
    arrayOfObject[0] = "DATABASE";
    arrayOfObject[1] = this.database.getURI();
    table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    arrayOfObject = table.getEmptyRowData();
    arrayOfObject[0] = "IDENTITY";
    arrayOfObject[1] = String.valueOf(paramSession.getLastIdentity());
    table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    arrayOfObject = table.getEmptyRowData();
    arrayOfObject[0] = "CURRENT SCHEMA";
    arrayOfObject[1] = String.valueOf(paramSession.getSchemaName(null));
    table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    arrayOfObject = table.getEmptyRowData();
    arrayOfObject[0] = "ISOLATION LEVEL";
    arrayOfObject[1] = String.valueOf(paramSession.getIsolation());
    table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    arrayOfObject = table.getEmptyRowData();
    arrayOfObject[0] = "IGNORECASE";
    arrayOfObject[1] = paramSession.isIgnorecase() ? "TRUE" : "FALSE";
    table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    arrayOfObject = table.getEmptyRowData();
    arrayOfObject[0] = "CURRENT STATEMENT";
    arrayOfObject[1] = "";
    Statement statement = paramSession.sessionContext.currentStatement;
    if (statement != null)
      arrayOfObject[1] = statement.getSQL(); 
    table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    return table;
  }
  
  Table SYSTEM_SESSIONS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[21];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[21]);
      addColumn(table, "SESSION_ID", CARDINAL_NUMBER);
      addColumn(table, "CONNECTED", TIME_STAMP);
      addColumn(table, "USER_NAME", SQL_IDENTIFIER);
      addColumn(table, "IS_ADMIN", (Type)Type.SQL_BOOLEAN);
      addColumn(table, "AUTOCOMMIT", (Type)Type.SQL_BOOLEAN);
      addColumn(table, "READONLY", (Type)Type.SQL_BOOLEAN);
      addColumn(table, "LAST_IDENTITY", CARDINAL_NUMBER);
      addColumn(table, "SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TRANSACTION", (Type)Type.SQL_BOOLEAN);
      addColumn(table, "TRANSACTION_SIZE", CARDINAL_NUMBER);
      addColumn(table, "WAITING_FOR_THIS", CHARACTER_DATA);
      addColumn(table, "THIS_WAITING_FOR", CHARACTER_DATA);
      addColumn(table, "CURRENT_STATEMENT", CHARACTER_DATA);
      addColumn(table, "LATCH_COUNT", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[21]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0 }, true);
      return table;
    } 
    Session[] arrayOfSession = this.database.sessionManager.getVisibleSessions(paramSession);
    for (byte b = 0; b < arrayOfSession.length; b++) {
      if (!arrayOfSession[b].isClosed()) {
        Session session = arrayOfSession[b];
        Object[] arrayOfObject = table.getEmptyRowData();
        arrayOfObject[0] = ValuePool.getLong(session.getId());
        arrayOfObject[1] = new TimestampData(session.getConnectTime() / 1000L);
        arrayOfObject[2] = session.getUsername();
        arrayOfObject[3] = ValuePool.getBoolean(session.isAdmin());
        arrayOfObject[4] = session.sessionContext.isAutoCommit;
        arrayOfObject[5] = Boolean.valueOf(session.isReadOnlyDefault);
        Number number = session.getLastIdentity();
        if (number != null)
          arrayOfObject[6] = ValuePool.getLong(number.longValue()); 
        arrayOfObject[8] = Boolean.valueOf(session.isInMidTransaction());
        arrayOfObject[9] = ValuePool.getLong(session.getTransactionSize());
        HsqlNameManager.HsqlName hsqlName = session.getCurrentSchemaHsqlName();
        if (hsqlName != null)
          arrayOfObject[7] = hsqlName.name; 
        arrayOfObject[10] = "";
        arrayOfObject[11] = "";
        if (session.waitingSessions.size() > 0) {
          StringBuffer stringBuffer = new StringBuffer();
          Session[] arrayOfSession1 = new Session[session.waitingSessions.size()];
          session.waitingSessions.toArray((Object[])arrayOfSession1);
          for (byte b1 = 0; b1 < arrayOfSession1.length; b1++) {
            if (b1 > 0)
              stringBuffer.append(','); 
            stringBuffer.append(arrayOfSession1[b1].getId());
          } 
          arrayOfObject[10] = stringBuffer.toString();
        } 
        if (session.waitedSessions.size() > 0) {
          StringBuffer stringBuffer = new StringBuffer();
          Session[] arrayOfSession1 = new Session[session.waitedSessions.size()];
          session.waitedSessions.toArray((Object[])arrayOfSession1);
          for (byte b1 = 0; b1 < arrayOfSession1.length; b1++) {
            if (b1 > 0)
              stringBuffer.append(','); 
            stringBuffer.append(arrayOfSession1[b1].getId());
          } 
          arrayOfObject[11] = stringBuffer.toString();
        } 
        Statement statement = session.sessionContext.currentStatement;
        arrayOfObject[12] = (statement == null) ? "" : statement.getSQL();
        arrayOfObject[13] = new Long(session.latch.getCount());
        table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      } 
    } 
    return table;
  }
  
  Table SYSTEM_TEXTTABLES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[22];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[22]);
      addColumn(table, "TABLE_CAT", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "DATA_SOURCE_DEFINTION", CHARACTER_DATA);
      addColumn(table, "FILE_PATH", CHARACTER_DATA);
      addColumn(table, "FILE_ENCODING", CHARACTER_DATA);
      addColumn(table, "FIELD_SEPARATOR", CHARACTER_DATA);
      addColumn(table, "VARCHAR_SEPARATOR", CHARACTER_DATA);
      addColumn(table, "LONGVARCHAR_SEPARATOR", CHARACTER_DATA);
      addColumn(table, "IS_IGNORE_FIRST", (Type)Type.SQL_BOOLEAN);
      addColumn(table, "IS_ALL_QUOTED", (Type)Type.SQL_BOOLEAN);
      addColumn(table, "IS_QUOTED", (Type)Type.SQL_BOOLEAN);
      addColumn(table, "IS_DESC", (Type)Type.SQL_BOOLEAN);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[22]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(3);
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      PersistentStore persistentStore = table1.getRowStore(paramSession);
      if (!table1.isText() || !isAccessibleTable(paramSession, table1))
        continue; 
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (table1.getSchemaName()).name;
      arrayOfObject[2] = (table1.getName()).name;
      arrayOfObject[3] = ((TextTable)table1).getDataSource();
      TextCache textCache = (TextCache)persistentStore.getCache();
      if (textCache != null) {
        TextFileSettings textFileSettings = textCache.getTextFileSettings();
        arrayOfObject[4] = FileUtil.getFileUtil().canonicalOrAbsolutePath(textCache.getFileName());
        arrayOfObject[5] = textFileSettings.stringEncoding;
        arrayOfObject[6] = textFileSettings.fs;
        arrayOfObject[7] = textFileSettings.vs;
        arrayOfObject[8] = textFileSettings.lvs;
        arrayOfObject[9] = ValuePool.getBoolean(textFileSettings.ignoreFirst);
        arrayOfObject[10] = ValuePool.getBoolean(textFileSettings.isQuoted);
        arrayOfObject[11] = ValuePool.getBoolean(textFileSettings.isAllQuoted);
        arrayOfObject[12] = ((TextTable)table1).isDescDataSource() ? Boolean.TRUE : Boolean.FALSE;
      } 
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table SYSTEM_TABLESTATS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[23];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[23]);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "CARDINALITY", CARDINAL_NUMBER);
      addColumn(table, "SPACE_ID", CARDINAL_NUMBER);
      addColumn(table, "ALLOCATED_SPACE", CARDINAL_NUMBER);
      addColumn(table, "USED_SPACE", CARDINAL_NUMBER);
      addColumn(table, "USED_MEMORY", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[23]).name, false, 20);
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
        case 3:
        case 6:
        case 8:
          continue;
      } 
      PersistentStore persistentStore = table1.getRowStore(paramSession);
      arrayOfObject[3] = Long.valueOf(persistentStore.elementCount());
      if (table1.isCached()) {
        arrayOfObject[4] = Long.valueOf(table1.getSpaceID());
        arrayOfObject[6] = null;
        arrayOfObject[7] = null;
      } 
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table ADMINISTRABLE_ROLE_AUTHORIZATIONS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[24];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[24]);
      addColumn(table, "GRANTEE", SQL_IDENTIFIER);
      addColumn(table, "ROLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "IS_GRANTABLE", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[24]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2 }, false);
      return table;
    } 
    if (paramSession.isAdmin())
      insertRoles(paramSession, table, paramSession.getGrantee(), true); 
    return table;
  }
  
  Table APPLICABLE_ROLES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[25];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[25]);
      addColumn(table, "GRANTEE", SQL_IDENTIFIER);
      addColumn(table, "ROLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "IS_GRANTABLE", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[25]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2 }, false);
      return table;
    } 
    insertRoles(paramSession, table, paramSession.getGrantee(), paramSession.isAdmin());
    return table;
  }
  
  private void insertRoles(Session paramSession, Table paramTable, Grantee paramGrantee, boolean paramBoolean) {
    PersistentStore persistentStore = paramTable.getRowStore(paramSession);
    if (paramBoolean) {
      Set set = this.database.getGranteeManager().getRoleNames();
      Iterator iterator = set.iterator();
      while (iterator.hasNext()) {
        String str = (String)iterator.next();
        Object[] arrayOfObject = paramTable.getEmptyRowData();
        arrayOfObject[0] = paramGrantee.getName().getNameString();
        arrayOfObject[1] = str;
        arrayOfObject[2] = "YES";
        paramTable.insertSys(paramSession, persistentStore, arrayOfObject);
      } 
    } else {
      OrderedHashSet orderedHashSet = paramGrantee.getDirectRoles();
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        String str = (String)orderedHashSet.get(b);
        Object[] arrayOfObject = paramTable.getEmptyRowData();
        arrayOfObject[0] = paramGrantee.getName().getNameString();
        arrayOfObject[1] = str;
        arrayOfObject[2] = "NO";
        paramTable.insertSys(paramSession, persistentStore, arrayOfObject);
        paramGrantee = this.database.getGranteeManager().getRole(str);
        insertRoles(paramSession, paramTable, paramGrantee, paramBoolean);
      } 
    } 
  }
  
  Table ASSERTIONS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[26];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[26]);
      addColumn(table, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(table, "IS_DEFERRABLE", YES_OR_NO);
      addColumn(table, "INITIALLY_DEFERRED", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[26]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2 }, false);
      return table;
    } 
    return table;
  }
  
  Table AUTHORIZATIONS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[27];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[27]);
      addColumn(table, "AUTHORIZATION_NAME", SQL_IDENTIFIER);
      addColumn(table, "AUTHORIZATION_TYPE", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[27]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0 }, true);
      return table;
    } 
    Iterator iterator = paramSession.getGrantee().visibleGrantees().iterator();
    while (iterator.hasNext()) {
      Grantee grantee = (Grantee)iterator.next();
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = grantee.getName().getNameString();
      arrayOfObject[1] = grantee.isRole() ? "ROLE" : "USER";
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table CHARACTER_SETS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[28];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[28]);
      addColumn(table, "CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(table, "CHARACTER_REPERTOIRE", SQL_IDENTIFIER);
      addColumn(table, "FORM_OF_USE", SQL_IDENTIFIER);
      addColumn(table, "DEFAULT_COLLATE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "DEFAULT_COLLATE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "DEFAULT_COLLATE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[28]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(14);
    while (iterator.hasNext()) {
      Charset charset = (Charset)iterator.next();
      if (!paramSession.getGrantee().isAccessible((SchemaObject)charset))
        continue; 
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (charset.getSchemaName()).name;
      arrayOfObject[2] = (charset.getName()).name;
      arrayOfObject[3] = "UCS";
      arrayOfObject[4] = "UTF16";
      arrayOfObject[5] = arrayOfObject[0];
      if (charset.base == null) {
        arrayOfObject[6] = arrayOfObject[1];
        arrayOfObject[7] = arrayOfObject[2];
      } else {
        arrayOfObject[6] = charset.base.schema.name;
        arrayOfObject[7] = charset.base.name;
      } 
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table CHECK_CONSTRAINT_ROUTINE_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[29];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[29]);
      addColumn(table, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[29]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(5);
    while (iterator.hasNext()) {
      Constraint constraint;
      Table table1;
      Type type;
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)iterator.next();
      if (hsqlName.parent == null)
        continue; 
      switch (hsqlName.parent.type) {
        case 3:
          try {
            table1 = (Table)this.database.schemaManager.getSchemaObject(hsqlName.parent.name, hsqlName.parent.schema.name, 3);
          } catch (Exception exception) {
            continue;
          } 
          constraint = table1.getConstraint(hsqlName.name);
          if (constraint.getConstraintType() != 3)
            continue; 
          break;
        case 13:
          try {
            type = (Type)this.database.schemaManager.getSchemaObject(hsqlName.parent.name, hsqlName.parent.schema.name, 13);
          } catch (Exception exception) {
            continue;
          } 
          constraint = type.userTypeModifier.getConstraint(hsqlName.name);
          continue;
        default:
          continue;
      } 
      OrderedHashSet orderedHashSet = constraint.getReferences();
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
        if (hsqlName1.type == 24 && paramSession.getGrantee().isFullyAccessibleByRole(hsqlName1)) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (constraint.getSchemaName()).name;
          arrayOfObject[2] = (constraint.getName()).name;
          arrayOfObject[3] = (this.database.getCatalogName()).name;
          arrayOfObject[4] = hsqlName1.schema.name;
          arrayOfObject[5] = hsqlName1.name;
          table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        } 
      } 
    } 
    return table;
  }
  
  Table CHECK_CONSTRAINTS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[30];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[30]);
      addColumn(table, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(table, "CHECK_CLAUSE", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[30]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 2, 1, 0 }, false);
      return table;
    } 
    Iterator iterator1 = this.database.schemaManager.databaseObjectIterator(3);
    while (iterator1.hasNext()) {
      Table table1 = (Table)iterator1.next();
      if (table1.isView() || !paramSession.getGrantee().isFullyAccessibleByRole(table1.getName()))
        continue; 
      for (Constraint constraint : table1.getConstraints()) {
        if (constraint.getConstraintType() == 3) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (table1.getSchemaName()).name;
          arrayOfObject[2] = (constraint.getName()).name;
          try {
            arrayOfObject[3] = constraint.getCheckSQL();
          } catch (Exception exception) {}
          table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        } 
      } 
    } 
    Iterator iterator2 = this.database.schemaManager.databaseObjectIterator(13);
    while (iterator2.hasNext()) {
      Type type = (Type)iterator2.next();
      if (!type.isDomainType() || !paramSession.getGrantee().isFullyAccessibleByRole(type.getName()))
        continue; 
      for (Constraint constraint : type.userTypeModifier.getConstraints()) {
        Object[] arrayOfObject = table.getEmptyRowData();
        arrayOfObject[0] = (this.database.getCatalogName()).name;
        arrayOfObject[1] = (type.getSchemaName()).name;
        arrayOfObject[2] = (constraint.getName()).name;
        try {
          arrayOfObject[3] = constraint.getCheckSQL();
        } catch (Exception exception) {}
        table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      } 
    } 
    return table;
  }
  
  Table COLLATIONS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[31];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[31]);
      addColumn(table, "COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(table, "PAD_ATTRIBUTE", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[31]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2 }, false);
      return table;
    } 
    String str = "PAD SPACE";
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(15);
    while (iterator.hasNext()) {
      Object[] arrayOfObject = table.getEmptyRowData();
      Collation collation = (Collation)iterator.next();
      String str2 = (collation.getSchemaName()).name;
      String str1 = (collation.getName()).name;
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = str2;
      arrayOfObject[2] = str1;
      arrayOfObject[3] = collation.isPadSpace() ? "PAD SPACE" : "NO PAD";
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    iterator = Collation.nameToJavaName.keySet().iterator();
    while (iterator.hasNext()) {
      Object[] arrayOfObject = table.getEmptyRowData();
      String str2 = "INFORMATION_SCHEMA";
      String str1 = (String)iterator.next();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = str2;
      arrayOfObject[2] = str1;
      arrayOfObject[3] = str;
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table COLUMN_COLUMN_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[32];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[32]);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "DEPENDENT_COLUMN", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[32]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(3);
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if (table1.isView() || !paramSession.getGrantee().isFullyAccessibleByRole(table1.getName()) || !table1.hasGeneratedColumn())
        continue; 
      HsqlNameManager.HsqlName hsqlName = table1.getName();
      for (byte b = 0; b < table1.getColumnCount(); b++) {
        ColumnSchema columnSchema = table1.getColumn(b);
        if (columnSchema.isGenerated()) {
          OrderedHashSet orderedHashSet = columnSchema.getGeneratedColumnReferences();
          if (orderedHashSet != null)
            for (byte b1 = 0; b1 < orderedHashSet.size(); b1++) {
              Object[] arrayOfObject = table.getEmptyRowData();
              arrayOfObject[0] = (this.database.getCatalogName()).name;
              arrayOfObject[1] = hsqlName.schema.name;
              arrayOfObject[2] = hsqlName.name;
              arrayOfObject[3] = ((HsqlNameManager.HsqlName)orderedHashSet.get(b1)).name;
              arrayOfObject[4] = (columnSchema.getName()).name;
              table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            }  
        } 
      } 
    } 
    return table;
  }
  
  Table COLUMN_DOMAIN_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[33];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[33]);
      addColumn(table, "DOMAIN_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "DOMAIN_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "DOMAIN_NAME", SQL_IDENTIFIER);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[33]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5, 6 }, false);
      return table;
    } 
    Iterator iterator = allTables();
    Grantee grantee = paramSession.getGrantee();
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      int i = table1.getColumnCount();
      HsqlNameManager.HsqlName hsqlName = table1.getName();
      for (byte b = 0; b < i; b++) {
        ColumnSchema columnSchema = table1.getColumn(b);
        Type type = columnSchema.getDataType();
        if (type.isDomainType() && grantee.isFullyAccessibleByRole(type.getName())) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (type.getSchemaName()).name;
          arrayOfObject[2] = (type.getName()).name;
          arrayOfObject[3] = (this.database.getCatalogName()).name;
          arrayOfObject[4] = hsqlName.schema.name;
          arrayOfObject[5] = hsqlName.name;
          arrayOfObject[6] = columnSchema.getNameString();
          table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        } 
      } 
    } 
    return table;
  }
  
  Table COLUMN_UDT_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[35];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[35]);
      addColumn(table, "UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "UDT_NAME", SQL_IDENTIFIER);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[35]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5, 6 }, false);
      return table;
    } 
    Iterator iterator = allTables();
    Grantee grantee = paramSession.getGrantee();
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      int i = table1.getColumnCount();
      HsqlNameManager.HsqlName hsqlName = table1.getName();
      for (byte b = 0; b < i; b++) {
        ColumnSchema columnSchema = table1.getColumn(b);
        Type type = columnSchema.getDataType();
        if (type.isDistinctType() && grantee.isFullyAccessibleByRole(type.getName())) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (type.getSchemaName()).name;
          arrayOfObject[2] = (type.getName()).name;
          arrayOfObject[3] = (this.database.getCatalogName()).name;
          arrayOfObject[4] = hsqlName.schema.name;
          arrayOfObject[5] = hsqlName.name;
          arrayOfObject[6] = columnSchema.getNameString();
          table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        } 
      } 
    } 
    return table;
  }
  
  Table COLUMNS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[36];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[36]);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "ORDINAL_POSITION", CARDINAL_NUMBER);
      addColumn(table, "COLUMN_DEFAULT", CHARACTER_DATA);
      addColumn(table, "IS_NULLABLE", YES_OR_NO);
      addColumn(table, "DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "CHARACTER_MAXIMUM_LENGTH", CARDINAL_NUMBER);
      addColumn(table, "CHARACTER_OCTET_LENGTH", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(table, "DATETIME_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "INTERVAL_TYPE", CHARACTER_DATA);
      addColumn(table, "INTERVAL_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "CHARACTER_SET_CATALOG", CHARACTER_DATA);
      addColumn(table, "CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(table, "DOMAIN_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "DOMAIN_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "DOMAIN_NAME", SQL_IDENTIFIER);
      addColumn(table, "UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "UDT_NAME", SQL_IDENTIFIER);
      addColumn(table, "SCOPE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SCOPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SCOPE_NAME", SQL_IDENTIFIER);
      addColumn(table, "MAXIMUM_CARDINALITY", CARDINAL_NUMBER);
      addColumn(table, "DTD_IDENTIFIER", SQL_IDENTIFIER);
      addColumn(table, "IS_SELF_REFERENCING", YES_OR_NO);
      addColumn(table, "IS_IDENTITY", YES_OR_NO);
      addColumn(table, "IDENTITY_GENERATION", CHARACTER_DATA);
      addColumn(table, "IDENTITY_START", CHARACTER_DATA);
      addColumn(table, "IDENTITY_INCREMENT", CHARACTER_DATA);
      addColumn(table, "IDENTITY_MAXIMUM", CHARACTER_DATA);
      addColumn(table, "IDENTITY_MINIMUM", CHARACTER_DATA);
      addColumn(table, "IDENTITY_CYCLE", YES_OR_NO);
      addColumn(table, "IS_GENERATED", CHARACTER_DATA);
      addColumn(table, "GENERATION_EXPRESSION", CHARACTER_DATA);
      addColumn(table, "IS_UPDATABLE", YES_OR_NO);
      addColumn(table, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[36]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 3, 2, 1, 4 }, false);
      return table;
    } 
    Iterator iterator = allTables();
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      OrderedHashSet orderedHashSet = paramSession.getGrantee().getColumnsForAllPrivileges((SchemaObject)table1);
      if (orderedHashSet.isEmpty())
        continue; 
      int i = table1.getColumnCount();
      for (byte b = 0; b < i; b++) {
        ColumnSchema columnSchema = table1.getColumn(b);
        Type type = columnSchema.getDataType();
        if (orderedHashSet.contains(columnSchema.getName())) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (table1.getSchemaName()).name;
          arrayOfObject[2] = (table1.getName()).name;
          arrayOfObject[3] = (columnSchema.getName()).name;
          arrayOfObject[4] = ValuePool.getLong((b + 1));
          arrayOfObject[5] = columnSchema.getDefaultSQL();
          arrayOfObject[6] = columnSchema.isNullable() ? "YES" : "NO";
          arrayOfObject[7] = type.getFullNameString();
          if (type.isCharacterType()) {
            arrayOfObject[8] = ValuePool.getLong(type.precision);
            arrayOfObject[9] = ValuePool.getLong(type.precision * 2L);
            arrayOfObject[16] = (this.database.getCatalogName()).name;
            arrayOfObject[17] = (((CharacterType)type).getCharacterSet().getSchemaName()).name;
            arrayOfObject[18] = (((CharacterType)type).getCharacterSet().getName()).name;
            arrayOfObject[19] = (this.database.getCatalogName()).name;
            arrayOfObject[20] = (((CharacterType)type).getCollation().getSchemaName()).name;
            arrayOfObject[21] = (((CharacterType)type).getCollation().getName()).name;
          } else if (type.isNumberType()) {
            arrayOfObject[10] = ValuePool.getLong(((NumberType)type).getNumericPrecisionInRadix());
            arrayOfObject[45] = ValuePool.getLong(((NumberType)type).getNumericPrecisionInRadix());
            if (type.isExactNumberType()) {
              arrayOfObject[46] = ValuePool.getLong(type.scale);
              arrayOfObject[12] = ValuePool.getLong(type.scale);
            } 
            arrayOfObject[11] = ValuePool.getLong(type.getPrecisionRadix());
          } else if (!type.isBooleanType()) {
            if (type.isDateTimeType()) {
              arrayOfObject[13] = ValuePool.getLong(type.scale);
            } else if (type.isIntervalType()) {
              arrayOfObject[7] = "INTERVAL";
              (IntervalType)type;
              arrayOfObject[14] = IntervalType.getQualifier(type.typeCode);
              arrayOfObject[15] = ValuePool.getLong(type.precision);
              arrayOfObject[13] = ValuePool.getLong(type.scale);
            } else if (type.isBinaryType()) {
              arrayOfObject[8] = ValuePool.getLong(type.precision);
              arrayOfObject[9] = ValuePool.getLong(type.precision);
            } else if (type.isBitType()) {
              arrayOfObject[8] = ValuePool.getLong(type.precision);
              arrayOfObject[9] = ValuePool.getLong(type.precision);
            } else if (type.isArrayType()) {
              arrayOfObject[31] = ValuePool.getLong(type.arrayLimitCardinality());
              arrayOfObject[7] = "ARRAY";
            } 
          } 
          if (type.isDomainType()) {
            arrayOfObject[22] = (this.database.getCatalogName()).name;
            arrayOfObject[23] = (type.getSchemaName()).name;
            arrayOfObject[24] = (type.getName()).name;
          } 
          if (type.isDistinctType()) {
            arrayOfObject[25] = (this.database.getCatalogName()).name;
            arrayOfObject[26] = (type.getSchemaName()).name;
            arrayOfObject[27] = (type.getName()).name;
          } 
          arrayOfObject[28] = null;
          arrayOfObject[29] = null;
          arrayOfObject[30] = null;
          arrayOfObject[32] = type.getDefinition();
          arrayOfObject[33] = null;
          arrayOfObject[34] = columnSchema.isIdentity() ? "YES" : "NO";
          if (columnSchema.isIdentity()) {
            NumberSequence numberSequence = columnSchema.getIdentitySequence();
            arrayOfObject[35] = numberSequence.isAlways() ? "ALWAYS" : "BY DEFAULT";
            arrayOfObject[36] = Long.toString(numberSequence.getStartValue());
            arrayOfObject[37] = Long.toString(numberSequence.getIncrement());
            arrayOfObject[38] = Long.toString(numberSequence.getMaxValue());
            arrayOfObject[39] = Long.toString(numberSequence.getMinValue());
            arrayOfObject[40] = numberSequence.isCycle() ? "YES" : "NO";
          } 
          arrayOfObject[41] = "NEVER";
          if (columnSchema.isGenerated()) {
            arrayOfObject[41] = "ALWAYS";
            arrayOfObject[42] = columnSchema.getGeneratingExpression().getSQL();
          } 
          arrayOfObject[43] = table1.isWritable() ? "YES" : "NO";
          arrayOfObject[44] = arrayOfObject[7];
          if (type.isNumberType()) {
            arrayOfObject[45] = arrayOfObject[10];
            arrayOfObject[46] = arrayOfObject[12];
          } 
          table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        } 
      } 
    } 
    return table;
  }
  
  Table CONSTRAINT_COLUMN_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[37];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[37]);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[37]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5, 6 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(3);
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if (table1.isView() || !paramSession.getGrantee().isFullyAccessibleByRole(table1.getName()))
        continue; 
      Constraint[] arrayOfConstraint = table1.getConstraints();
      int i = arrayOfConstraint.length;
      String str1 = (this.database.getCatalogName()).name;
      String str2 = (table1.getSchemaName()).name;
      for (byte b = 0; b < i; b++) {
        Iterator iterator1;
        OrderedHashSet orderedHashSet;
        Table table2;
        int[] arrayOfInt;
        byte b1;
        Constraint constraint = arrayOfConstraint[b];
        String str = (constraint.getName()).name;
        switch (constraint.getConstraintType()) {
          case 3:
            orderedHashSet = constraint.getCheckColumnExpressions();
            if (orderedHashSet == null)
              break; 
            iterator1 = orderedHashSet.iterator();
            while (iterator1.hasNext()) {
              ExpressionColumn expressionColumn = (ExpressionColumn)iterator1.next();
              HsqlNameManager.HsqlName hsqlName = expressionColumn.getColumn().getName();
              if (hsqlName.type != 9)
                continue; 
              Object[] arrayOfObject = table.getEmptyRowData();
              arrayOfObject[0] = (this.database.getCatalogName()).name;
              arrayOfObject[1] = hsqlName.schema.name;
              arrayOfObject[2] = hsqlName.parent.name;
              arrayOfObject[3] = hsqlName.name;
              arrayOfObject[4] = str1;
              arrayOfObject[5] = str2;
              arrayOfObject[6] = str;
              try {
                table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
              } catch (HsqlException hsqlException) {}
            } 
            break;
          case 0:
          case 2:
          case 4:
            table2 = table1;
            arrayOfInt = constraint.getMainColumns();
            if (constraint.getConstraintType() == 0)
              arrayOfInt = constraint.getRefColumns(); 
            for (b1 = 0; b1 < arrayOfInt.length; b1++) {
              Object[] arrayOfObject = table.getEmptyRowData();
              arrayOfObject[0] = (this.database.getCatalogName()).name;
              arrayOfObject[1] = str2;
              arrayOfObject[2] = (table2.getName()).name;
              arrayOfObject[3] = (table2.getColumn(arrayOfInt[b1]).getName()).name;
              arrayOfObject[4] = str1;
              arrayOfObject[5] = str2;
              arrayOfObject[6] = str;
              try {
                table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
              } catch (HsqlException hsqlException) {}
            } 
            break;
        } 
      } 
    } 
    return table;
  }
  
  Table CONSTRAINT_TABLE_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[38];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[38]);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[38]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return table;
    } 
    Session session = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    Result result = session.executeDirectStatement("select DISTINCT TABLE_CATALOG, TABLE_SCHEMA, TABLE_NAME, CONSTRAINT_CATALOG, CONSTRAINT_SCHEMA, CONSTRAINT_NAME from INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE");
    table.insertSys(paramSession, paramPersistentStore, result);
    session.close();
    return table;
  }
  
  Table DATA_TYPE_PRIVILEGES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[39];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[39]);
      addColumn(table, "OBJECT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_NAME", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_TYPE", SQL_IDENTIFIER);
      addColumn(table, "DTD_IDENTIFIER", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[39]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4 }, false);
      return table;
    } 
    Session session = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    String str = (String)statementMap.get("/*data_type_privileges*/");
    Result result = session.executeDirectStatement(str);
    table.insertSys(paramSession, paramPersistentStore, result);
    session.close();
    return table;
  }
  
  Table DOMAIN_CONSTRAINTS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[40];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[40]);
      addColumn(table, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(table, "DOMAIN_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "DOMAIN_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "DOMAIN_NAME", SQL_IDENTIFIER);
      addColumn(table, "IS_DEFERRABLE", YES_OR_NO);
      addColumn(table, "INITIALLY_DEFERRED", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[40]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 4, 5, 6 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(13);
    while (iterator.hasNext()) {
      Type type = (Type)iterator.next();
      if (!type.isDomainType() || !paramSession.getGrantee().isFullyAccessibleByRole(type.getName()))
        continue; 
      Constraint[] arrayOfConstraint = type.userTypeModifier.getConstraints();
      for (byte b = 0; b < arrayOfConstraint.length; b++) {
        Object[] arrayOfObject = table.getEmptyRowData();
        arrayOfObject[3] = (this.database.getCatalogName()).name;
        arrayOfObject[0] = (this.database.getCatalogName()).name;
        arrayOfObject[4] = (type.getSchemaName()).name;
        arrayOfObject[1] = (type.getSchemaName()).name;
        arrayOfObject[2] = (arrayOfConstraint[b].getName()).name;
        arrayOfObject[5] = (type.getName()).name;
        arrayOfObject[6] = "NO";
        arrayOfObject[7] = "NO";
        table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      } 
    } 
    return table;
  }
  
  Table DOMAINS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[41];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[41]);
      addColumn(table, "DOMAIN_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "DOMAIN_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "DOMAIN_NAME", SQL_IDENTIFIER);
      addColumn(table, "DATA_TYPE", SQL_IDENTIFIER);
      addColumn(table, "CHARACTER_MAXIMUM_LENGTH", CARDINAL_NUMBER);
      addColumn(table, "CHARACTER_OCTET_LENGTH", CARDINAL_NUMBER);
      addColumn(table, "CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(table, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(table, "DATETIME_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "INTERVAL_TYPE", CHARACTER_DATA);
      addColumn(table, "INTERVAL_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "DOMAIN_DEFAULT", CHARACTER_DATA);
      addColumn(table, "MAXIMUM_CARDINALITY", CARDINAL_NUMBER);
      addColumn(table, "DTD_IDENTIFIER", SQL_IDENTIFIER);
      addColumn(table, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[41]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 4, 5, 6 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(13);
    while (iterator.hasNext()) {
      Type type = (Type)iterator.next();
      if (!type.isDomainType() || !paramSession.getGrantee().isAccessible((SchemaObject)type))
        continue; 
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (type.getSchemaName()).name;
      arrayOfObject[2] = (type.getName()).name;
      arrayOfObject[3] = type.getFullNameString();
      if (type.isCharacterType()) {
        arrayOfObject[4] = ValuePool.getLong(type.precision);
        arrayOfObject[5] = ValuePool.getLong(type.precision * 2L);
        arrayOfObject[6] = (this.database.getCatalogName()).name;
        arrayOfObject[7] = (((CharacterType)type).getCharacterSet().getSchemaName()).name;
        arrayOfObject[8] = (((CharacterType)type).getCharacterSet().getName()).name;
        arrayOfObject[9] = (this.database.getCatalogName()).name;
        arrayOfObject[10] = (((CharacterType)type).getCollation().getSchemaName()).name;
        arrayOfObject[11] = (((CharacterType)type).getCollation().getName()).name;
      } else if (type.isNumberType()) {
        arrayOfObject[12] = ValuePool.getLong(((NumberType)type).getNumericPrecisionInRadix());
        arrayOfObject[22] = ValuePool.getLong(((NumberType)type).getNumericPrecisionInRadix());
        if (type.isExactNumberType()) {
          arrayOfObject[23] = ValuePool.getLong(type.scale);
          arrayOfObject[14] = ValuePool.getLong(type.scale);
        } 
        arrayOfObject[13] = ValuePool.getLong(type.getPrecisionRadix());
      } else if (!type.isBooleanType()) {
        if (type.isDateTimeType()) {
          arrayOfObject[15] = ValuePool.getLong(type.scale);
        } else if (type.isIntervalType()) {
          arrayOfObject[3] = "INTERVAL";
          (IntervalType)type;
          arrayOfObject[16] = IntervalType.getQualifier(type.typeCode);
          arrayOfObject[17] = ValuePool.getLong(type.precision);
          arrayOfObject[15] = ValuePool.getLong(type.scale);
        } else if (type.isBinaryType()) {
          arrayOfObject[4] = ValuePool.getLong(type.precision);
          arrayOfObject[5] = ValuePool.getLong(type.precision);
        } else if (type.isBitType()) {
          arrayOfObject[4] = ValuePool.getLong(type.precision);
          arrayOfObject[5] = ValuePool.getLong(type.precision);
        } else if (type.isArrayType()) {
          arrayOfObject[19] = ValuePool.getLong(type.arrayLimitCardinality());
          arrayOfObject[3] = "ARRAY";
        } 
      } 
      arrayOfObject[20] = type.getDefinition();
      arrayOfObject[21] = arrayOfObject[3];
      Expression expression = type.userTypeModifier.getDefaultClause();
      if (expression != null)
        arrayOfObject[18] = expression.getSQL(); 
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table ELEMENT_TYPES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[42];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[42]);
      addColumn(table, "OBJECT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_NAME", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_TYPE", SQL_IDENTIFIER);
      addColumn(table, "COLLECTION_TYPE_IDENTIFIER", SQL_IDENTIFIER);
      addColumn(table, "DATA_TYPE", SQL_IDENTIFIER);
      addColumn(table, "CHARACTER_MAXIMUM_LENGTH", CARDINAL_NUMBER);
      addColumn(table, "CHARACTER_OCTET_LENGTH", CARDINAL_NUMBER);
      addColumn(table, "CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(table, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(table, "DATETIME_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "INTERVAL_TYPE", CHARACTER_DATA);
      addColumn(table, "INTERVAL_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "UDT_NAME", SQL_IDENTIFIER);
      addColumn(table, "SCOPE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SCOPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SCOPE_NAME", SQL_IDENTIFIER);
      addColumn(table, "MAXIMUM_CARDINALITY", CARDINAL_NUMBER);
      addColumn(table, "DTD_IDENTIFIER", SQL_IDENTIFIER);
      addColumn(table, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[42]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 4, 5, 27 }, true);
      return table;
    } 
    Iterator iterator1 = allTables();
    while (iterator1.hasNext()) {
      Table table1 = (Table)iterator1.next();
      OrderedHashSet orderedHashSet = paramSession.getGrantee().getColumnsForAllPrivileges((SchemaObject)table1);
      if (orderedHashSet.isEmpty())
        continue; 
      int i = table1.getColumnCount();
      for (byte b = 0; b < i; b++) {
        ColumnSchema columnSchema = table1.getColumn(b);
        if (orderedHashSet.contains(columnSchema.getName())) {
          Type type = columnSchema.getDataType();
          if (!type.isDistinctType() && !type.isDomainType() && type.isArrayType()) {
            Object[] arrayOfObject = table.getEmptyRowData();
            arrayOfObject[0] = (this.database.getCatalogName()).name;
            arrayOfObject[1] = (table1.getSchemaName()).name;
            arrayOfObject[2] = (table1.getName()).name;
            arrayOfObject[3] = "TABLE";
            arrayOfObject[4] = type.getDefinition();
            addTypeInfo(arrayOfObject, ((ArrayType)type).collectionBaseType());
            try {
              table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            } catch (HsqlException hsqlException) {}
          } 
        } 
      } 
    } 
    Iterator iterator2 = this.database.schemaManager.databaseObjectIterator(13);
    while (iterator2.hasNext()) {
      Type type = (Type)iterator2.next();
      if (!type.isDomainType() || !type.isArrayType() || !paramSession.getGrantee().isAccessible((SchemaObject)type))
        continue; 
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (type.getSchemaName()).name;
      arrayOfObject[2] = (type.getName()).name;
      arrayOfObject[3] = "DOMAIN";
      arrayOfObject[4] = type.getDefinition();
      addTypeInfo(arrayOfObject, ((ArrayType)type).collectionBaseType());
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    iterator2 = this.database.schemaManager.databaseObjectIterator(12);
    while (iterator2.hasNext()) {
      Type type = (Type)iterator2.next();
      if (!type.isDistinctType() || !type.isArrayType() || !paramSession.getGrantee().isAccessible((SchemaObject)type))
        continue; 
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (type.getSchemaName()).name;
      arrayOfObject[2] = (type.getName()).name;
      arrayOfObject[3] = "USER-DEFINED TYPE";
      arrayOfObject[4] = type.getDefinition();
      addTypeInfo(arrayOfObject, ((ArrayType)type).collectionBaseType());
      try {
        table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      } catch (HsqlException hsqlException) {}
    } 
    iterator2 = this.database.schemaManager.databaseObjectIterator(24);
    while (iterator2.hasNext()) {
      Routine routine = (Routine)iterator2.next();
      if (!paramSession.getGrantee().isAccessible((SchemaObject)routine))
        continue; 
      Type type1 = routine.isProcedure() ? null : routine.getReturnType();
      if (type1 != null && !type1.isDistinctType() && !type1.isDomainType() && type1.isArrayType()) {
        Object[] arrayOfObject = table.getEmptyRowData();
        arrayOfObject[0] = (this.database.getCatalogName()).name;
        arrayOfObject[1] = (routine.getSchemaName()).name;
        arrayOfObject[2] = (routine.getName()).name;
        arrayOfObject[3] = "ROUTINE";
        arrayOfObject[4] = type1.getDefinition();
        addTypeInfo(arrayOfObject, ((ArrayType)type1).collectionBaseType());
        try {
          table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        } catch (HsqlException hsqlException) {}
      } 
      Type type2 = type1;
      int i = routine.getParameterCount();
      for (byte b = 0; b < i; b++) {
        ColumnSchema columnSchema = routine.getParameter(b);
        type1 = columnSchema.getDataType();
        if (!type1.isDistinctType() && !type1.isDomainType() && type1.isArrayType() && !type1.equals(type2)) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (routine.getSchemaName()).name;
          arrayOfObject[2] = (routine.getName()).name;
          arrayOfObject[3] = "ROUTINE";
          arrayOfObject[4] = type1.getDefinition();
          addTypeInfo(arrayOfObject, ((ArrayType)type1).collectionBaseType());
          try {
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } catch (HsqlException hsqlException) {}
        } 
      } 
    } 
    return table;
  }
  
  void addTypeInfo(Object[] paramArrayOfObject, Type paramType) {
    paramArrayOfObject[5] = paramType.getFullNameString();
    if (paramType.isCharacterType()) {
      paramArrayOfObject[6] = ValuePool.getLong(paramType.precision);
      paramArrayOfObject[7] = ValuePool.getLong(paramType.precision * 2L);
      paramArrayOfObject[8] = (this.database.getCatalogName()).name;
      paramArrayOfObject[9] = (((CharacterType)paramType).getCharacterSet().getSchemaName()).name;
      paramArrayOfObject[10] = (((CharacterType)paramType).getCharacterSet().getName()).name;
      paramArrayOfObject[11] = (this.database.getCatalogName()).name;
      paramArrayOfObject[12] = (((CharacterType)paramType).getCollation().getSchemaName()).name;
      paramArrayOfObject[13] = (((CharacterType)paramType).getCollation().getName()).name;
    } else if (paramType.isNumberType()) {
      paramArrayOfObject[14] = ValuePool.getLong(((NumberType)paramType).getNumericPrecisionInRadix());
      paramArrayOfObject[29] = ValuePool.getLong(((NumberType)paramType).getNumericPrecisionInRadix());
      if (paramType.isExactNumberType()) {
        paramArrayOfObject[30] = ValuePool.getLong(paramType.scale);
        paramArrayOfObject[16] = ValuePool.getLong(paramType.scale);
      } 
      paramArrayOfObject[15] = ValuePool.getLong(paramType.getPrecisionRadix());
    } else if (!paramType.isBooleanType()) {
      if (paramType.isDateTimeType()) {
        paramArrayOfObject[17] = ValuePool.getLong(paramType.scale);
      } else if (paramType.isIntervalType()) {
        paramArrayOfObject[5] = "INTERVAL";
        (IntervalType)paramType;
        paramArrayOfObject[18] = IntervalType.getQualifier(paramType.typeCode);
        paramArrayOfObject[19] = ValuePool.getLong(paramType.precision);
        paramArrayOfObject[17] = ValuePool.getLong(paramType.scale);
      } else if (paramType.isBinaryType()) {
        paramArrayOfObject[6] = ValuePool.getLong(paramType.precision);
        paramArrayOfObject[7] = ValuePool.getLong(paramType.precision);
      } else if (paramType.isBitType()) {
        paramArrayOfObject[6] = ValuePool.getLong(paramType.precision);
        paramArrayOfObject[7] = ValuePool.getLong(paramType.precision);
      } else if (paramType.isArrayType()) {
        paramArrayOfObject[26] = ValuePool.getLong(paramType.arrayLimitCardinality());
      } 
    } 
    paramArrayOfObject[27] = paramType.getDefinition();
    paramArrayOfObject[28] = paramArrayOfObject[5];
  }
  
  Table ENABLED_ROLES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[43];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[43]);
      addColumn(table, "ROLE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[43]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0 }, true);
      return table;
    } 
    Iterator iterator = paramSession.getGrantee().getAllRoles().iterator();
    while (iterator.hasNext()) {
      Grantee grantee = (Grantee)iterator.next();
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = grantee.getName().getNameString();
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table JAR_JAR_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[45];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[45]);
      addColumn(table, "PATH_JAR_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "PATH_JAR_SCHAMA", SQL_IDENTIFIER);
      addColumn(table, "PATH_JAR_NAME", SQL_IDENTIFIER);
      addColumn(table, "JAR_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "JAR_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "JAR_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[45]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return table;
    } 
    return table;
  }
  
  Table JARS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[46];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[46]);
      addColumn(table, "JAR_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "JAR_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "JAR_NAME", SQL_IDENTIFIER);
      addColumn(table, "JAR_PATH", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[46]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3 }, false);
      return table;
    } 
    return table;
  }
  
  Table KEY_COLUMN_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[47];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[47]);
      addColumn(table, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "ORDINAL_POSITION", CARDINAL_NUMBER);
      addColumn(table, "POSITION_IN_UNIQUE_CONSTRAINT", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[47]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 2, 1, 0, 6, 7 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(3);
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      String str = (this.database.getCatalogName()).name;
      HsqlNameManager.HsqlName hsqlName = table1.getName();
      if (table1.isView() || !paramSession.getGrantee().isAccessible(hsqlName))
        continue; 
      Constraint[] arrayOfConstraint = table1.getConstraints();
      for (byte b = 0; b < arrayOfConstraint.length; b++) {
        Constraint constraint = arrayOfConstraint[b];
        if (constraint.getConstraintType() == 4 || constraint.getConstraintType() == 2 || constraint.getConstraintType() == 0) {
          String str1 = (constraint.getName()).name;
          int[] arrayOfInt1 = constraint.getMainColumns();
          int[] arrayOfInt2 = null;
          if (constraint.getConstraintType() == 0) {
            Table table2 = constraint.getMain();
            Constraint constraint1 = table2.getConstraint((constraint.getUniqueName()).name);
            int[] arrayOfInt = constraint1.getMainColumns();
            arrayOfInt2 = new int[arrayOfInt1.length];
            for (byte b1 = 0; b1 < arrayOfInt1.length; b1++)
              arrayOfInt2[b1] = ArrayUtil.find(arrayOfInt, arrayOfInt1[b1]); 
            arrayOfInt1 = constraint.getRefColumns();
          } 
          if (paramSession.getGrantee().hasColumnRights((SchemaObject)table1, arrayOfInt1))
            for (byte b1 = 0; b1 < arrayOfInt1.length; b1++) {
              Object[] arrayOfObject = table.getEmptyRowData();
              arrayOfObject[0] = str;
              arrayOfObject[1] = hsqlName.schema.name;
              arrayOfObject[2] = str1;
              arrayOfObject[3] = str;
              arrayOfObject[4] = hsqlName.schema.name;
              arrayOfObject[5] = hsqlName.name;
              arrayOfObject[6] = (table1.getColumn(arrayOfInt1[b1]).getName()).name;
              arrayOfObject[7] = ValuePool.getLong((b1 + 1));
              if (constraint.getConstraintType() == 0)
                arrayOfObject[8] = ValuePool.getInt(arrayOfInt2[b1] + 1); 
              table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            }  
        } 
      } 
    } 
    return table;
  }
  
  Table METHOD_SPECIFICATION_PARAMETERS(Session paramSession, PersistentStore paramPersistentStore) {
    return null;
  }
  
  Table METHOD_SPECIFICATIONS(Session paramSession, PersistentStore paramPersistentStore) {
    return null;
  }
  
  Table MODULE_COLUMN_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    return null;
  }
  
  Table MODULE_PRIVILEGES(Session paramSession, PersistentStore paramPersistentStore) {
    return null;
  }
  
  Table MODULE_TABLE_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    return null;
  }
  
  Table MODULES(Session paramSession, PersistentStore paramPersistentStore) {
    return null;
  }
  
  Table PARAMETERS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[53];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[53]);
      addColumn(table, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(table, "ORDINAL_POSITION", CARDINAL_NUMBER);
      addColumn(table, "PARAMETER_MODE", CHARACTER_DATA);
      addColumn(table, "IS_RESULT", YES_OR_NO);
      addColumn(table, "AS_LOCATOR", YES_OR_NO);
      addColumn(table, "PARAMETER_NAME", SQL_IDENTIFIER);
      addColumn(table, "FROM_SQL_SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "FROM_SQL_SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "FROM_SQL_SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(table, "TO_SQL_SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TO_SQL_SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TO_SQL_SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(table, "DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "CHARACTER_MAXIMUM_LENGTH", CARDINAL_NUMBER);
      addColumn(table, "CHARACTER_OCTET_LENGTH", CARDINAL_NUMBER);
      addColumn(table, "CHARACTER_SET_CATALOG", CHARACTER_DATA);
      addColumn(table, "CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(table, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(table, "DATETIME_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "INTERVAL_TYPE", CHARACTER_DATA);
      addColumn(table, "INTERVAL_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "UDT_NAME", SQL_IDENTIFIER);
      addColumn(table, "SCOPE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SCOPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SCOPE_NAME", SQL_IDENTIFIER);
      addColumn(table, "MAXIMUM_CARDINALITY", CARDINAL_NUMBER);
      addColumn(table, "DTD_IDENTIFIER", SQL_IDENTIFIER);
      addColumn(table, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[53]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3 }, false);
      return table;
    } 
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
          ColumnSchema columnSchema = routine.getParameter(b1);
          Type type = columnSchema.getDataType();
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (routine.getSchemaName()).name;
          arrayOfObject[2] = (routine.getSpecificName()).name;
          arrayOfObject[7] = (columnSchema.getName()).name;
          arrayOfObject[3] = ValuePool.getLong((b1 + 1));
          switch (columnSchema.getParameterMode()) {
            case 1:
              arrayOfObject[4] = "IN";
              break;
            case 4:
              arrayOfObject[4] = "OUT";
              break;
            case 2:
              arrayOfObject[4] = "INOUT";
              break;
          } 
          arrayOfObject[5] = "NO";
          arrayOfObject[6] = "NO";
          arrayOfObject[14] = type.getFullNameString();
          if (type.isCharacterType()) {
            arrayOfObject[15] = ValuePool.getLong(type.precision);
            arrayOfObject[16] = ValuePool.getLong(type.precision * 2L);
            arrayOfObject[17] = (this.database.getCatalogName()).name;
            arrayOfObject[18] = (((CharacterType)type).getCharacterSet().getSchemaName()).name;
            arrayOfObject[19] = (((CharacterType)type).getCharacterSet().getName()).name;
            arrayOfObject[20] = (this.database.getCatalogName()).name;
            arrayOfObject[21] = (((CharacterType)type).getCollation().getSchemaName()).name;
            arrayOfObject[22] = (((CharacterType)type).getCollation().getName()).name;
          } else if (type.isNumberType()) {
            arrayOfObject[23] = ValuePool.getLong(((NumberType)type).getNumericPrecisionInRadix());
            arrayOfObject[24] = ValuePool.getLong(type.getPrecisionRadix());
          } else if (!type.isBooleanType()) {
            if (type.isDateTimeType()) {
              arrayOfObject[26] = ValuePool.getLong(type.scale);
            } else if (type.isIntervalType()) {
              arrayOfObject[14] = "INTERVAL";
              (IntervalType)type;
              arrayOfObject[27] = IntervalType.getQualifier(type.typeCode);
              arrayOfObject[28] = ValuePool.getLong(type.precision);
              arrayOfObject[26] = ValuePool.getLong(type.scale);
            } else if (type.isBinaryType()) {
              arrayOfObject[15] = ValuePool.getLong(type.precision);
              arrayOfObject[16] = ValuePool.getLong(type.precision);
            } else if (type.isBitType()) {
              arrayOfObject[15] = ValuePool.getLong(type.precision);
              arrayOfObject[16] = ValuePool.getLong(type.precision);
            } else if (type.isArrayType()) {
              arrayOfObject[35] = ValuePool.getLong(type.arrayLimitCardinality());
              arrayOfObject[14] = "ARRAY";
            } 
          } 
          if (type.isDistinctType()) {
            arrayOfObject[29] = (this.database.getCatalogName()).name;
            arrayOfObject[30] = (type.getSchemaName()).name;
            arrayOfObject[31] = (type.getName()).name;
          } 
          arrayOfObject[36] = type.getDefinition();
          table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        } 
      } 
    } 
    return table;
  }
  
  Table REFERENTIAL_CONSTRAINTS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[54];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[54]);
      addColumn(table, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(table, "UNIQUE_CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "UNIQUE_CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "UNIQUE_CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(table, "MATCH_OPTION", CHARACTER_DATA);
      addColumn(table, "UPDATE_RULE", CHARACTER_DATA);
      addColumn(table, "DELETE_RULE", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[54]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(3);
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if (table1.isView() || !paramSession.getGrantee().hasNonSelectTableRight((SchemaObject)table1))
        continue; 
      Constraint[] arrayOfConstraint = table1.getConstraints();
      for (byte b = 0; b < arrayOfConstraint.length; b++) {
        Constraint constraint = arrayOfConstraint[b];
        if (constraint.getConstraintType() == 0) {
          HsqlNameManager.HsqlName hsqlName = constraint.getUniqueName();
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (constraint.getSchemaName()).name;
          arrayOfObject[2] = (constraint.getName()).name;
          if (isAccessibleTable(paramSession, constraint.getMain())) {
            arrayOfObject[3] = (this.database.getCatalogName()).name;
            arrayOfObject[4] = hsqlName.schema.name;
            arrayOfObject[5] = hsqlName.name;
          } 
          arrayOfObject[6] = "NONE";
          arrayOfObject[7] = constraint.getUpdateActionString();
          arrayOfObject[8] = constraint.getDeleteActionString();
          table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        } 
      } 
    } 
    return table;
  }
  
  Table ROLE_COLUMN_GRANTS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[56];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[56]);
      addColumn(table, "GRANTOR", SQL_IDENTIFIER);
      addColumn(table, "GRANTEE", SQL_IDENTIFIER);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(table, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(table, "IS_GRANTABLE", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[56]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 5, 6, 1, 0, 4, 3, 2 }, false);
      return table;
    } 
    Session session = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    Result result = session.executeDirectStatement("SELECT GRANTOR, GRANTEE, TABLE_CATALOG, TABLE_SCHEMA, TABLE_NAME, COLUMN_NAME, PRIVILEGE_TYPE, IS_GRANTABLE FROM INFORMATION_SCHEMA.COLUMN_PRIVILEGES JOIN INFORMATION_SCHEMA.APPLICABLE_ROLES ON GRANTEE = ROLE_NAME;");
    table.insertSys(paramSession, paramPersistentStore, result);
    session.close();
    return table;
  }
  
  Table ROLE_ROUTINE_GRANTS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[58];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[58]);
      addColumn(table, "GRANTOR", SQL_IDENTIFIER);
      addColumn(table, "GRANTEE", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_NAME", SQL_IDENTIFIER);
      addColumn(table, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(table, "IS_GRANTABLE", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[58]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, false);
      return table;
    } 
    Session session = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    Result result = session.executeDirectStatement("SELECT GRANTOR, GRANTEE, SPECIFIC_CATALOG, SPECIFIC_SCHEMA, SPECIFIC_NAME, ROUTINE_CATALOG, ROUTINE_SCHEMA, ROUTINE_NAME, PRIVILEGE_TYPE, IS_GRANTABLE FROM INFORMATION_SCHEMA.ROUTINE_PRIVILEGES JOIN INFORMATION_SCHEMA.APPLICABLE_ROLES ON GRANTEE = ROLE_NAME;");
    table.insertSys(paramSession, paramPersistentStore, result);
    session.close();
    return table;
  }
  
  Table ROLE_TABLE_GRANTS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[59];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[59]);
      addColumn(table, "GRANTOR", SQL_IDENTIFIER);
      addColumn(table, "GRANTEE", SQL_IDENTIFIER);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(table, "IS_GRANTABLE", YES_OR_NO);
      addColumn(table, "WITH_HIERARCHY", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[59]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 3, 4, 5, 0, 1 }, false);
      return table;
    } 
    Session session = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    Result result = session.executeDirectStatement("SELECT GRANTOR, GRANTEE, TABLE_CATALOG, TABLE_SCHEMA, TABLE_NAME, PRIVILEGE_TYPE, IS_GRANTABLE, 'NO' FROM INFORMATION_SCHEMA.TABLE_PRIVILEGES JOIN INFORMATION_SCHEMA.APPLICABLE_ROLES ON GRANTEE = ROLE_NAME;");
    table.insertSys(paramSession, paramPersistentStore, result);
    session.close();
    return table;
  }
  
  Table ROLE_UDT_GRANTS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[60];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[60]);
      addColumn(table, "GRANTOR", SQL_IDENTIFIER);
      addColumn(table, "GRANTEE", SQL_IDENTIFIER);
      addColumn(table, "UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "UDT_NAME", SQL_IDENTIFIER);
      addColumn(table, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(table, "IS_GRANTABLE", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[59]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, null, false);
      return table;
    } 
    Session session = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    Result result = session.executeDirectStatement("SELECT GRANTOR, GRANTEE, UDT_CATALOG, UDT_SCHEMA, UDT_NAME, PRIVILEGE_TYPE, IS_GRANTABLE FROM INFORMATION_SCHEMA.UDT_PRIVILEGES JOIN INFORMATION_SCHEMA.APPLICABLE_ROLES ON GRANTEE = ROLE_NAME;");
    table.insertSys(paramSession, paramPersistentStore, result);
    session.close();
    return table;
  }
  
  Table ROLE_USAGE_GRANTS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[61];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[61]);
      addColumn(table, "GRANTOR", SQL_IDENTIFIER);
      addColumn(table, "GRANTEE", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_NAME", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_TYPE", CHARACTER_DATA);
      addColumn(table, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(table, "IS_GRANTABLE", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[61]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5, 6, 7 }, false);
      return table;
    } 
    Session session = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    Result result = session.executeDirectStatement("SELECT GRANTOR, GRANTEE, OBJECT_CATALOG, OBJECT_SCHEMA, OBJECT_NAME, OBJECT_TYPE, PRIVILEGE_TYPE, IS_GRANTABLE FROM INFORMATION_SCHEMA.USAGE_PRIVILEGES JOIN INFORMATION_SCHEMA.APPLICABLE_ROLES ON GRANTEE = ROLE_NAME;");
    table.insertSys(paramSession, paramPersistentStore, result);
    session.close();
    return table;
  }
  
  Table ROUTINE_COLUMN_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[62];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[62]);
      addColumn(table, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_NAME", SQL_IDENTIFIER);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[62]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 3, 4, 5, 0, 1, 2, 6, 7, 8, 9 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(24);
    while (iterator.hasNext()) {
      Routine routine = (Routine)iterator.next();
      OrderedHashSet orderedHashSet = routine.getReferences();
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
        if (hsqlName.type == 9 && paramSession.getGrantee().isFullyAccessibleByRole(hsqlName)) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (routine.getSchemaName()).name;
          arrayOfObject[2] = (routine.getSpecificName()).name;
          arrayOfObject[3] = (this.database.getCatalogName()).name;
          arrayOfObject[4] = (routine.getSchemaName()).name;
          arrayOfObject[5] = (routine.getName()).name;
          arrayOfObject[6] = (this.database.getCatalogName()).name;
          arrayOfObject[7] = hsqlName.parent.schema.name;
          arrayOfObject[8] = hsqlName.parent.name;
          arrayOfObject[9] = hsqlName.name;
          try {
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } catch (HsqlException hsqlException) {}
        } 
      } 
    } 
    return table;
  }
  
  Table ROUTINE_PRIVILEGES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[64];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[64]);
      addColumn(table, "GRANTOR", SQL_IDENTIFIER);
      addColumn(table, "GRANTEE", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_NAME", SQL_IDENTIFIER);
      addColumn(table, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(table, "IS_GRANTABLE", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[64]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, false);
      return table;
    } 
    OrderedHashSet orderedHashSet = paramSession.getGrantee().visibleGrantees();
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(24);
    while (iterator.hasNext()) {
      Routine routine = (Routine)iterator.next();
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        Grantee grantee = (Grantee)orderedHashSet.get(b);
        OrderedHashSet orderedHashSet1 = grantee.getAllDirectPrivileges((SchemaObject)routine);
        OrderedHashSet orderedHashSet2 = grantee.getAllGrantedPrivileges((SchemaObject)routine);
        if (!orderedHashSet2.isEmpty()) {
          orderedHashSet2.addAll((Collection)orderedHashSet1);
          orderedHashSet1 = orderedHashSet2;
        } 
        for (byte b1 = 0; b1 < orderedHashSet1.size(); b1++) {
          Right right1 = (Right)orderedHashSet1.get(b1);
          Right right2 = right1.getGrantableRights();
          if (right1.canAccessFully(32)) {
            String str = "EXECUTE";
            Object[] arrayOfObject = table.getEmptyRowData();
            arrayOfObject[0] = (right1.getGrantor().getName()).name;
            arrayOfObject[1] = (right1.getGrantee().getName()).name;
            arrayOfObject[2] = (this.database.getCatalogName()).name;
            arrayOfObject[3] = (routine.getSchemaName()).name;
            arrayOfObject[4] = (routine.getSpecificName()).name;
            arrayOfObject[5] = (this.database.getCatalogName()).name;
            arrayOfObject[6] = (routine.getSchemaName()).name;
            arrayOfObject[7] = (routine.getName()).name;
            arrayOfObject[8] = str;
            arrayOfObject[9] = (right1.getGrantee() == routine.getOwner() || right2.canAccessFully(32)) ? "YES" : "NO";
            try {
              table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            } catch (HsqlException hsqlException) {}
          } 
        } 
      } 
    } 
    return table;
  }
  
  Table ROUTINE_JAR_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[63];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[63]);
      addColumn(table, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(table, "JAR_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "JAR_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "JAR_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[63]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return table;
    } 
    if (!paramSession.isAdmin())
      return table; 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(24);
    while (iterator.hasNext()) {
      Routine routine = (Routine)iterator.next();
      if (routine.getLanguage() != 1)
        continue; 
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (routine.getSchemaName()).name;
      arrayOfObject[2] = (routine.getSpecificName()).name;
      arrayOfObject[3] = (this.database.getCatalogName()).name;
      arrayOfObject[4] = (this.database.schemaManager.getSQLJSchemaHsqlName()).name;
      arrayOfObject[5] = "CLASSPATH";
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table ROUTINE_ROUTINE_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[65];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[65]);
      addColumn(table, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[65]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(24);
    while (iterator.hasNext()) {
      Routine routine = (Routine)iterator.next();
      OrderedHashSet orderedHashSet = routine.getReferences();
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
        if (hsqlName.type == 24 && paramSession.getGrantee().isFullyAccessibleByRole(hsqlName)) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (routine.getSchemaName()).name;
          arrayOfObject[2] = (routine.getSpecificName()).name;
          arrayOfObject[3] = (this.database.getCatalogName()).name;
          arrayOfObject[4] = hsqlName.schema.name;
          arrayOfObject[5] = hsqlName.name;
          try {
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } catch (HsqlException hsqlException) {}
        } 
      } 
    } 
    return table;
  }
  
  Table ROUTINE_SEQUENCE_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[66];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[66]);
      addColumn(table, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(table, "SEQUENCE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SEQUENCE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SEQUENCE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[66]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(24);
    while (iterator.hasNext()) {
      Routine routine = (Routine)iterator.next();
      OrderedHashSet orderedHashSet = routine.getReferences();
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
        if (hsqlName.type == 7 && paramSession.getGrantee().isFullyAccessibleByRole(hsqlName)) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (routine.getSchemaName()).name;
          arrayOfObject[2] = (routine.getSpecificName()).name;
          arrayOfObject[3] = (this.database.getCatalogName()).name;
          arrayOfObject[4] = hsqlName.schema.name;
          arrayOfObject[5] = hsqlName.name;
          try {
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } catch (HsqlException hsqlException) {}
        } 
      } 
    } 
    return table;
  }
  
  Table ROUTINE_TABLE_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[67];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[67]);
      addColumn(table, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_NAME", SQL_IDENTIFIER);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[67]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 3, 4, 5, 0, 1, 2, 6, 7, 8 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(24);
    while (iterator.hasNext()) {
      Routine routine = (Routine)iterator.next();
      OrderedHashSet orderedHashSet = routine.getReferences();
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
        if ((hsqlName.type == 3 || hsqlName.type == 4) && paramSession.getGrantee().isFullyAccessibleByRole(hsqlName)) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (routine.getSchemaName()).name;
          arrayOfObject[2] = (routine.getSpecificName()).name;
          arrayOfObject[3] = (this.database.getCatalogName()).name;
          arrayOfObject[4] = (routine.getSchemaName()).name;
          arrayOfObject[5] = (routine.getName()).name;
          arrayOfObject[6] = (this.database.getCatalogName()).name;
          arrayOfObject[7] = hsqlName.schema.name;
          arrayOfObject[8] = hsqlName.name;
          try {
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } catch (HsqlException hsqlException) {}
        } 
      } 
    } 
    return table;
  }
  
  Table ROUTINES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[68];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[68]);
      addColumn(table, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_NAME", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_TYPE", CHARACTER_DATA);
      addColumn(table, "MODULE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "MODULE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "MODULE_NAME", SQL_IDENTIFIER);
      addColumn(table, "UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "UDT_NAME", SQL_IDENTIFIER);
      addColumn(table, "DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "CHARACTER_MAXIMUM_LENGTH", CARDINAL_NUMBER);
      addColumn(table, "CHARACTER_OCTET_LENGTH", CARDINAL_NUMBER);
      addColumn(table, "CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(table, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(table, "DATETIME_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "INTERVAL_TYPE", CHARACTER_DATA);
      addColumn(table, "INTERVAL_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "TYPE_UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TYPE_UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TYPE_UDT_NAME", SQL_IDENTIFIER);
      addColumn(table, "SCOPE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SCOPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SCOPE_NAME", SQL_IDENTIFIER);
      addColumn(table, "MAXIMUM_CARDINALITY", CARDINAL_NUMBER);
      addColumn(table, "DTD_IDENTIFIER", SQL_IDENTIFIER);
      addColumn(table, "ROUTINE_BODY", CHARACTER_DATA);
      addColumn(table, "ROUTINE_DEFINITION", CHARACTER_DATA);
      addColumn(table, "EXTERNAL_NAME", CHARACTER_DATA);
      addColumn(table, "EXTERNAL_LANGUAGE", CHARACTER_DATA);
      addColumn(table, "PARAMETER_STYLE", CHARACTER_DATA);
      addColumn(table, "IS_DETERMINISTIC", YES_OR_NO);
      addColumn(table, "SQL_DATA_ACCESS", CHARACTER_DATA);
      addColumn(table, "IS_NULL_CALL", YES_OR_NO);
      addColumn(table, "SQL_PATH", CHARACTER_DATA);
      addColumn(table, "SCHEMA_LEVEL_ROUTINE", YES_OR_NO);
      addColumn(table, "MAX_DYNAMIC_RESULT_SETS", CARDINAL_NUMBER);
      addColumn(table, "IS_USER_DEFINED_CAST", YES_OR_NO);
      addColumn(table, "IS_IMPLICITLY_INVOCABLE", YES_OR_NO);
      addColumn(table, "SECURITY_TYPE", CHARACTER_DATA);
      addColumn(table, "TO_SQL_SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TO_SQL_SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TO_SQL_SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(table, "AS_LOCATOR", YES_OR_NO);
      addColumn(table, "CREATED", TIME_STAMP);
      addColumn(table, "LAST_ALTERED", TIME_STAMP);
      addColumn(table, "NEW_SAVEPOINT_LEVEL", YES_OR_NO);
      addColumn(table, "IS_UDT_DEPENDENT", YES_OR_NO);
      addColumn(table, "RESULT_CAST_FROM_DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "RESULT_CAST_AS_LOCATOR", YES_OR_NO);
      addColumn(table, "RESULT_CAST_CHAR_MAX_LENGTH", CARDINAL_NUMBER);
      addColumn(table, "RESULT_CAST_CHAR_OCTET_LENGTH", CARDINAL_NUMBER);
      addColumn(table, "RESULT_CAST_CHAR_SET_CATALOG", CHARACTER_DATA);
      addColumn(table, "RESULT_CAST_CHAR_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "RESULT_CAST_CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(table, "RESULT_CAST_COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "RESULT_CAST_COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "RESULT_CAST_COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(table, "RESULT_CAST_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "RESULT_CAST_NUMERIC_RADIX", CARDINAL_NUMBER);
      addColumn(table, "RESULT_CAST_NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(table, "RESULT_CAST_DATETIME_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "RESULT_CAST_INTERVAL_TYPE", CHARACTER_DATA);
      addColumn(table, "RESULT_CAST_INTERVAL_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "RESULT_CAST_TYPE_UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "RESULT_CAST_TYPE_UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "RESULT_CAST_TYPE_UDT_NAME", SQL_IDENTIFIER);
      addColumn(table, "RESULT_CAST_SCOPE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "RESULT_CAST_SCOPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "RESULT_CAST_SCOPE_NAME", SQL_IDENTIFIER);
      addColumn(table, "RESULT_CAST_MAX_CARDINALITY", CARDINAL_NUMBER);
      addColumn(table, "RESULT_CAST_DTD_IDENTIFIER", CHARACTER_DATA);
      addColumn(table, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(table, "RESULT_CAST_FROM_DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "RESULT_CAST_DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "RESULT_CAST_DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[68]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 3, 4, 5, 0, 1, 2 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(24);
    while (iterator.hasNext()) {
      Routine routine = (Routine)iterator.next();
      if (!paramSession.getGrantee().isAccessible((SchemaObject)routine))
        continue; 
      boolean bool = paramSession.getGrantee().isFullyAccessibleByRole(routine.getName());
      Object[] arrayOfObject = table.getEmptyRowData();
      Type type = routine.isProcedure() ? null : routine.getReturnType();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (routine.getSchemaName()).name;
      arrayOfObject[2] = (routine.getSpecificName()).name;
      arrayOfObject[3] = (this.database.getCatalogName()).name;
      arrayOfObject[4] = (routine.getSchemaName()).name;
      arrayOfObject[5] = (routine.getName()).name;
      arrayOfObject[6] = routine.isProcedure() ? "PROCEDURE" : "FUNCTION";
      arrayOfObject[7] = null;
      arrayOfObject[8] = null;
      arrayOfObject[9] = null;
      arrayOfObject[10] = null;
      arrayOfObject[11] = null;
      arrayOfObject[12] = null;
      arrayOfObject[13] = (type == null) ? null : type.getNameString();
      if (type != null) {
        if (type.isCharacterType()) {
          arrayOfObject[14] = ValuePool.getLong(type.precision);
          arrayOfObject[15] = ValuePool.getLong(type.precision * 2L);
          arrayOfObject[16] = (this.database.getCatalogName()).name;
          arrayOfObject[17] = (((CharacterType)type).getCharacterSet().getSchemaName()).name;
          arrayOfObject[18] = (((CharacterType)type).getCharacterSet().getName()).name;
          arrayOfObject[19] = (this.database.getCatalogName()).name;
          arrayOfObject[20] = (((CharacterType)type).getCollation().getSchemaName()).name;
          arrayOfObject[21] = (((CharacterType)type).getCollation().getName()).name;
        } else if (type.isNumberType()) {
          arrayOfObject[22] = ValuePool.getLong(((NumberType)type).getNumericPrecisionInRadix());
          arrayOfObject[83] = ValuePool.getLong(((NumberType)type).getNumericPrecisionInRadix());
          if (type.isExactNumberType()) {
            arrayOfObject[84] = ValuePool.getLong(type.scale);
            arrayOfObject[24] = ValuePool.getLong(type.scale);
          } 
          arrayOfObject[23] = ValuePool.getLong(type.getPrecisionRadix());
        } else if (!type.isBooleanType()) {
          if (type.isDateTimeType()) {
            arrayOfObject[25] = ValuePool.getLong(type.scale);
          } else if (type.isIntervalType()) {
            arrayOfObject[13] = "INTERVAL";
            (IntervalType)type;
            arrayOfObject[26] = IntervalType.getQualifier(type.typeCode);
            arrayOfObject[27] = ValuePool.getLong(type.precision);
            arrayOfObject[25] = ValuePool.getLong(type.scale);
          } else if (type.isBinaryType()) {
            arrayOfObject[14] = ValuePool.getLong(type.precision);
            arrayOfObject[15] = ValuePool.getLong(type.precision);
          } else if (type.isBitType()) {
            arrayOfObject[14] = ValuePool.getLong(type.precision);
            arrayOfObject[15] = ValuePool.getLong(type.precision);
          } else if (type.isArrayType()) {
            arrayOfObject[34] = ValuePool.getLong(type.arrayLimitCardinality());
            arrayOfObject[13] = "ARRAY";
          } 
        } 
        arrayOfObject[35] = type.getDefinition();
        arrayOfObject[82] = arrayOfObject[13];
      } 
      arrayOfObject[28] = null;
      arrayOfObject[29] = null;
      arrayOfObject[30] = null;
      arrayOfObject[31] = null;
      arrayOfObject[32] = null;
      arrayOfObject[33] = null;
      arrayOfObject[36] = (routine.getLanguage() == 1) ? "EXTERNAL" : "SQL";
      arrayOfObject[37] = bool ? routine.getSQL() : null;
      arrayOfObject[38] = routine.getExternalName();
      arrayOfObject[39] = (routine.getLanguage() == 1) ? "JAVA" : null;
      arrayOfObject[40] = (routine.getLanguage() == 1) ? "JAVA" : null;
      arrayOfObject[41] = routine.isDeterministic() ? "YES" : "NO";
      arrayOfObject[42] = routine.getDataImpactString();
      arrayOfObject[43] = (type == null) ? null : (routine.isNullInputOutput() ? "YES" : "NO");
      arrayOfObject[44] = null;
      arrayOfObject[45] = "YES";
      arrayOfObject[46] = ValuePool.getLong(0L);
      arrayOfObject[47] = (type == null) ? null : "NO";
      arrayOfObject[48] = null;
      arrayOfObject[49] = "DEFINER";
      arrayOfObject[50] = null;
      arrayOfObject[51] = null;
      arrayOfObject[52] = null;
      arrayOfObject[53] = (type == null) ? null : "NO";
      arrayOfObject[54] = null;
      arrayOfObject[55] = null;
      arrayOfObject[56] = "YES";
      arrayOfObject[57] = null;
      arrayOfObject[58] = null;
      arrayOfObject[59] = null;
      arrayOfObject[60] = null;
      arrayOfObject[61] = null;
      arrayOfObject[62] = null;
      arrayOfObject[63] = null;
      arrayOfObject[64] = null;
      arrayOfObject[65] = null;
      arrayOfObject[66] = null;
      arrayOfObject[67] = null;
      arrayOfObject[68] = null;
      arrayOfObject[69] = null;
      arrayOfObject[70] = null;
      arrayOfObject[71] = null;
      arrayOfObject[72] = null;
      arrayOfObject[73] = null;
      arrayOfObject[74] = null;
      arrayOfObject[75] = null;
      arrayOfObject[76] = null;
      arrayOfObject[77] = null;
      arrayOfObject[78] = null;
      arrayOfObject[79] = null;
      arrayOfObject[80] = null;
      arrayOfObject[81] = null;
      arrayOfObject[82] = arrayOfObject[13];
      arrayOfObject[83] = arrayOfObject[22];
      arrayOfObject[84] = arrayOfObject[24];
      arrayOfObject[85] = null;
      arrayOfObject[86] = null;
      arrayOfObject[87] = null;
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table SCHEMATA(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[69];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[69]);
      addColumn(table, "CATALOG_NAME", SQL_IDENTIFIER);
      addColumn(table, "SCHEMA_NAME", SQL_IDENTIFIER);
      addColumn(table, "SCHEMA_OWNER", SQL_IDENTIFIER);
      addColumn(table, "DEFAULT_CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "DEFAULT_CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "DEFAULT_CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(table, "SQL_PATH", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[69]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1 }, false);
      return table;
    } 
    String str1 = "INFORMATION_SCHEMA";
    String str2 = "SQL_TEXT";
    Object object = null;
    Grantee grantee = paramSession.getGrantee();
    Schema[] arrayOfSchema = this.database.schemaManager.getAllSchemas();
    for (byte b = 0; b < arrayOfSchema.length; b++) {
      Schema schema = arrayOfSchema[b];
      if (grantee.hasSchemaUpdateOrGrantRights(schema.getName().getNameString())) {
        Object[] arrayOfObject = table.getEmptyRowData();
        arrayOfObject[0] = (this.database.getCatalogName()).name;
        arrayOfObject[1] = schema.getName().getNameString();
        arrayOfObject[2] = schema.getOwner().getName().getNameString();
        arrayOfObject[3] = (this.database.getCatalogName()).name;
        arrayOfObject[4] = str1;
        arrayOfObject[5] = str2;
        arrayOfObject[6] = object;
        table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      } 
    } 
    return table;
  }
  
  Table SQL_FEATURES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[71];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[71]);
      addColumn(table, "FEATURE_ID", CHARACTER_DATA);
      addColumn(table, "FEATURE_NAME", CHARACTER_DATA);
      addColumn(table, "SUB_FEATURE_ID", CHARACTER_DATA);
      addColumn(table, "SUB_FEATURE_NAME", CHARACTER_DATA);
      addColumn(table, "IS_SUPPORTED", YES_OR_NO);
      addColumn(table, "IS_VERIFIED_BY", CHARACTER_DATA);
      addColumn(table, "COMMENTS", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[71]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 2 }, false);
      return table;
    } 
    Session session = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    String str = (String)statementMap.get("/*sql_features*/");
    Result result = session.executeDirectStatement(str);
    table.insertSys(paramSession, paramPersistentStore, result);
    return table;
  }
  
  Table SQL_IMPLEMENTATION_INFO(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[72];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[72]);
      addColumn(table, "IMPLEMENTATION_INFO_ID", CARDINAL_NUMBER);
      addColumn(table, "IMPLEMENTATION_INFO_NAME", CHARACTER_DATA);
      addColumn(table, "INTEGER_VALUE", CARDINAL_NUMBER);
      addColumn(table, "CHARACTER_VALUE", CHARACTER_DATA);
      addColumn(table, "COMMENTS", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[72]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0 }, false);
      return table;
    } 
    Session session = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    String str = (String)statementMap.get("/*sql_implementation_info*/");
    Result result = session.executeDirectStatement(str);
    table.insertSys(paramSession, paramPersistentStore, result);
    return table;
  }
  
  Table SQL_PACKAGES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[73];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[73]);
      addColumn(table, "ID", CHARACTER_DATA);
      addColumn(table, "NAME", CHARACTER_DATA);
      addColumn(table, "IS_SUPPORTED", YES_OR_NO);
      addColumn(table, "IS_VERIFIED_BY", CHARACTER_DATA);
      addColumn(table, "COMMENTS", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[73]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0 }, false);
      return table;
    } 
    Session session = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    String str = (String)statementMap.get("/*sql_packages*/");
    Result result = session.executeDirectStatement(str);
    table.insertSys(paramSession, paramPersistentStore, result);
    return table;
  }
  
  Table SQL_PARTS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[74];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[74]);
      addColumn(table, "PART", CHARACTER_DATA);
      addColumn(table, "NAME", CHARACTER_DATA);
      addColumn(table, "IS_SUPPORTED", YES_OR_NO);
      addColumn(table, "IS_VERIFIED_BY", CHARACTER_DATA);
      addColumn(table, "COMMENTS", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[74]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0 }, false);
      return table;
    } 
    Session session = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    String str = (String)statementMap.get("/*sql_parts*/");
    Result result = session.executeDirectStatement(str);
    table.insertSys(paramSession, paramPersistentStore, result);
    return table;
  }
  
  Table SQL_SIZING(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[75];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[75]);
      addColumn(table, "SIZING_ID", CARDINAL_NUMBER);
      addColumn(table, "SIZING_NAME", CHARACTER_DATA);
      addColumn(table, "SUPPORTED_VALUE", CARDINAL_NUMBER);
      addColumn(table, "COMMENTS", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[75]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0 }, false);
      return table;
    } 
    Session session = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    String str = (String)statementMap.get("/*sql_sizing*/");
    Result result = session.executeDirectStatement(str);
    table.insertSys(paramSession, paramPersistentStore, result);
    return table;
  }
  
  Table SQL_SIZING_PROFILES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[76];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[76]);
      addColumn(table, "SIZING_ID", CARDINAL_NUMBER);
      addColumn(table, "SIZING_NAME", CHARACTER_DATA);
      addColumn(table, "PROFILE_ID", CARDINAL_NUMBER);
      addColumn(table, "PROFILE_NAME", CHARACTER_DATA);
      addColumn(table, "REQUIRED_VALUE", CARDINAL_NUMBER);
      addColumn(table, "COMMENTS", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[76]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0 }, false);
      return table;
    } 
    Session session = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    return table;
  }
  
  Table TABLE_CONSTRAINTS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table1 = this.sysTables[77];
    if (table1 == null) {
      table1 = createBlankTable(sysTableHsqlNames[77]);
      addColumn(table1, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(table1, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table1, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(table1, "CONSTRAINT_TYPE", CHARACTER_DATA);
      addColumn(table1, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table1, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table1, "IS_DEFERRABLE", YES_OR_NO);
      addColumn(table1, "INITIALLY_DEFERRED", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[77]).name, false, 20);
      table1.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 4, 5, 6 }, false);
      return table1;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(3);
    Table table2 = null;
    while (iterator.hasNext()) {
      table2 = (Table)iterator.next();
      if (table2.isView() || !paramSession.getGrantee().hasNonSelectTableRight((SchemaObject)table2))
        continue; 
      Constraint[] arrayOfConstraint = table2.getConstraints();
      int i = arrayOfConstraint.length;
      byte b = 0;
      while (b < i) {
        Constraint constraint = arrayOfConstraint[b];
        Object[] arrayOfObject = table1.getEmptyRowData();
        switch (constraint.getConstraintType()) {
          case 3:
            arrayOfObject[3] = "CHECK";
            break;
          case 2:
            arrayOfObject[3] = "UNIQUE";
            break;
          case 0:
            arrayOfObject[3] = "FOREIGN KEY";
            table2 = constraint.getRef();
            break;
          case 4:
            arrayOfObject[3] = "PRIMARY KEY";
            break;
          default:
            b++;
            continue;
        } 
        String str1 = (this.database.getCatalogName()).name;
        String str2 = (table2.getSchemaName()).name;
        arrayOfObject[0] = str1;
        arrayOfObject[1] = str2;
        arrayOfObject[2] = (constraint.getName()).name;
        arrayOfObject[4] = str1;
        arrayOfObject[5] = str2;
        arrayOfObject[6] = (table2.getName()).name;
        arrayOfObject[7] = "NO";
        arrayOfObject[8] = "NO";
        table1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      } 
    } 
    return table1;
  }
  
  Table TRANSLATIONS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[80];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[80]);
      addColumn(table, "TRANSLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TRANSLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TRANSLATION_NAME", SQL_IDENTIFIER);
      addColumn(table, "SOURCE_CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SOURCE_CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SOURCE_CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(table, "TARGET_CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TARGET_CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TARGET_CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(table, "TRANSLATION_SOURCE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TRANSLATION_SOURCE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TRANSLATION_SOURCE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[80]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2 }, false);
      return table;
    } 
    return table;
  }
  
  Table TRIGGER_COLUMN_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[81];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[81]);
      addColumn(table, "TRIGGER_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TRIGGER_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TRIGGER_NAME", SQL_IDENTIFIER);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[81]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5, 6 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(8);
    while (iterator.hasNext()) {
      TriggerDef triggerDef = (TriggerDef)iterator.next();
      if (!paramSession.getGrantee().isFullyAccessibleByRole(triggerDef.getName()))
        continue; 
      OrderedHashSet orderedHashSet = triggerDef.getReferences();
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
        if (hsqlName.type == 9 && paramSession.getGrantee().isAccessible(hsqlName)) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (triggerDef.getSchemaName()).name;
          arrayOfObject[2] = (triggerDef.getName()).name;
          arrayOfObject[3] = (this.database.getCatalogName()).name;
          arrayOfObject[4] = hsqlName.parent.schema.name;
          arrayOfObject[5] = hsqlName.parent.name;
          arrayOfObject[6] = hsqlName.name;
          try {
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } catch (HsqlException hsqlException) {}
        } 
      } 
    } 
    return table;
  }
  
  Table TRIGGER_ROUTINE_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[82];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[82]);
      addColumn(table, "TRIGGER_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TRIGGER_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TRIGGER_NAME", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[82]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(8);
    while (iterator.hasNext()) {
      TriggerDef triggerDef = (TriggerDef)iterator.next();
      if (!paramSession.getGrantee().isFullyAccessibleByRole(triggerDef.getName()))
        continue; 
      OrderedHashSet orderedHashSet = triggerDef.getReferences();
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
        if (hsqlName.type == 24) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (triggerDef.getSchemaName()).name;
          arrayOfObject[2] = (triggerDef.getName()).name;
          arrayOfObject[3] = (this.database.getCatalogName()).name;
          arrayOfObject[4] = hsqlName.schema.name;
          arrayOfObject[5] = hsqlName.name;
          try {
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } catch (HsqlException hsqlException) {}
        } 
      } 
    } 
    return table;
  }
  
  Table TRIGGER_SEQUENCE_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[83];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[83]);
      addColumn(table, "TRIGGER_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TRIGGER_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TRIGGER_NAME", SQL_IDENTIFIER);
      addColumn(table, "SEQUENCE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SEQUENCE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SEQUENCE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[83]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(8);
    while (iterator.hasNext()) {
      TriggerDef triggerDef = (TriggerDef)iterator.next();
      if (!paramSession.getGrantee().isFullyAccessibleByRole(triggerDef.getName()))
        continue; 
      OrderedHashSet orderedHashSet = triggerDef.getReferences();
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
        if (hsqlName.type == 7) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (triggerDef.getSchemaName()).name;
          arrayOfObject[2] = (triggerDef.getName()).name;
          arrayOfObject[3] = (this.database.getCatalogName()).name;
          arrayOfObject[4] = hsqlName.schema.name;
          arrayOfObject[5] = hsqlName.name;
          try {
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } catch (HsqlException hsqlException) {}
        } 
      } 
    } 
    return table;
  }
  
  Table TRIGGER_TABLE_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[84];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[84]);
      addColumn(table, "TRIGGER_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TRIGGER_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TRIGGER_NAME", SQL_IDENTIFIER);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[84]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(8);
    while (iterator.hasNext()) {
      TriggerDef triggerDef = (TriggerDef)iterator.next();
      if (!paramSession.getGrantee().isFullyAccessibleByRole(triggerDef.getName()))
        continue; 
      OrderedHashSet orderedHashSet = triggerDef.getReferences();
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
        if (hsqlName.type == 3 || hsqlName.type == 4) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (triggerDef.getSchemaName()).name;
          arrayOfObject[2] = (triggerDef.getName()).name;
          arrayOfObject[3] = (this.database.getCatalogName()).name;
          arrayOfObject[4] = hsqlName.schema.name;
          arrayOfObject[5] = hsqlName.name;
          try {
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } catch (HsqlException hsqlException) {}
        } 
      } 
    } 
    return table;
  }
  
  Table TRIGGERS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[86];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[86]);
      addColumn(table, "TRIGGER_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TRIGGER_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TRIGGER_NAME", SQL_IDENTIFIER);
      addColumn(table, "EVENT_MANIPULATION", SQL_IDENTIFIER);
      addColumn(table, "EVENT_OBJECT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "EVENT_OBJECT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "EVENT_OBJECT_TABLE", SQL_IDENTIFIER);
      addColumn(table, "ACTION_ORDER", CARDINAL_NUMBER);
      addColumn(table, "ACTION_CONDITION", CHARACTER_DATA);
      addColumn(table, "ACTION_STATEMENT", CHARACTER_DATA);
      addColumn(table, "ACTION_ORIENTATION", CHARACTER_DATA);
      addColumn(table, "ACTION_TIMING", CHARACTER_DATA);
      addColumn(table, "ACTION_REFERENCE_OLD_TABLE", SQL_IDENTIFIER);
      addColumn(table, "ACTION_REFERENCE_NEW_TABLE", SQL_IDENTIFIER);
      addColumn(table, "ACTION_REFERENCE_OLD_ROW", SQL_IDENTIFIER);
      addColumn(table, "ACTION_REFERENCE_NEW_ROW", SQL_IDENTIFIER);
      addColumn(table, "CREATED", TIME_STAMP);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[86]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(8);
    while (iterator.hasNext()) {
      TriggerDef triggerDef = (TriggerDef)iterator.next();
      boolean bool = paramSession.getGrantee().isFullyAccessibleByRole(triggerDef.getName());
      if (!paramSession.getGrantee().hasNonSelectTableRight((SchemaObject)triggerDef.getTable()))
        continue; 
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (triggerDef.getSchemaName()).name;
      arrayOfObject[2] = (triggerDef.getName()).name;
      arrayOfObject[3] = triggerDef.getEventTypeString();
      arrayOfObject[4] = (this.database.getCatalogName()).name;
      arrayOfObject[5] = (triggerDef.getTable().getSchemaName()).name;
      arrayOfObject[6] = (triggerDef.getTable().getName()).name;
      int i = triggerDef.getTable().getTriggerIndex((triggerDef.getName()).name);
      arrayOfObject[7] = ValuePool.getLong(i);
      arrayOfObject[8] = bool ? triggerDef.getConditionSQL() : null;
      arrayOfObject[9] = bool ? triggerDef.getProcedureSQL() : null;
      arrayOfObject[10] = triggerDef.getActionOrientationString();
      arrayOfObject[11] = triggerDef.getActionTimingString();
      arrayOfObject[12] = triggerDef.getOldTransitionTableName();
      arrayOfObject[13] = triggerDef.getNewTransitionTableName();
      arrayOfObject[14] = triggerDef.getOldTransitionRowName();
      arrayOfObject[15] = triggerDef.getNewTransitionRowName();
      arrayOfObject[16] = null;
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table TRIGGERED_UPDATE_COLUMNS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[85];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[85]);
      addColumn(table, "TRIGGER_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TRIGGER_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TRIGGER_NAME", SQL_IDENTIFIER);
      addColumn(table, "EVENT_OBJECT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "EVENT_OBJECT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "EVENT_OBJECT_TABLE", SQL_IDENTIFIER);
      addColumn(table, "EVENT_OBJECT_COLUMN", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[85]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5, 6 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(8);
    while (iterator.hasNext()) {
      TriggerDef triggerDef = (TriggerDef)iterator.next();
      if (!paramSession.getGrantee().isAccessible((SchemaObject)triggerDef))
        continue; 
      int[] arrayOfInt = triggerDef.getUpdateColumnIndexes();
      if (arrayOfInt == null)
        continue; 
      for (byte b = 0; b < arrayOfInt.length; b++) {
        ColumnSchema columnSchema = triggerDef.getTable().getColumn(arrayOfInt[b]);
        Object[] arrayOfObject = table.getEmptyRowData();
        arrayOfObject[0] = (this.database.getCatalogName()).name;
        arrayOfObject[1] = (triggerDef.getSchemaName()).name;
        arrayOfObject[2] = (triggerDef.getName()).name;
        arrayOfObject[3] = (this.database.getCatalogName()).name;
        arrayOfObject[4] = (triggerDef.getTable().getSchemaName()).name;
        arrayOfObject[5] = (triggerDef.getTable().getName()).name;
        arrayOfObject[6] = columnSchema.getNameString();
        table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      } 
    } 
    return table;
  }
  
  Table UDT_PRIVILEGES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[88];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[88]);
      addColumn(table, "GRANTOR", SQL_IDENTIFIER);
      addColumn(table, "GRANTEE", SQL_IDENTIFIER);
      addColumn(table, "UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "UDT_NAME", SQL_IDENTIFIER);
      addColumn(table, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(table, "IS_GRANTABLE", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[88]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(12);
    OrderedHashSet orderedHashSet = paramSession.getGrantee().getGranteeAndAllRolesWithPublic();
    while (iterator.hasNext()) {
      SchemaObject schemaObject = (SchemaObject)iterator.next();
      if (schemaObject.getType() != 12)
        continue; 
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        Grantee grantee = (Grantee)orderedHashSet.get(b);
        OrderedHashSet orderedHashSet1 = grantee.getAllDirectPrivileges(schemaObject);
        OrderedHashSet orderedHashSet2 = grantee.getAllGrantedPrivileges(schemaObject);
        if (!orderedHashSet2.isEmpty()) {
          orderedHashSet2.addAll((Collection)orderedHashSet1);
          orderedHashSet1 = orderedHashSet2;
        } 
        for (byte b1 = 0; b1 < orderedHashSet1.size(); b1++) {
          Right right1 = (Right)orderedHashSet1.get(b1);
          Right right2 = right1.getGrantableRights();
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (right1.getGrantor().getName()).name;
          arrayOfObject[1] = (right1.getGrantee().getName()).name;
          arrayOfObject[2] = (this.database.getCatalogName()).name;
          arrayOfObject[3] = (schemaObject.getSchemaName()).name;
          arrayOfObject[4] = (schemaObject.getName()).name;
          arrayOfObject[5] = "USAGE";
          arrayOfObject[6] = (right1.getGrantee() == schemaObject.getOwner() || right2.isFull()) ? "YES" : "NO";
          try {
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } catch (HsqlException hsqlException) {}
        } 
      } 
    } 
    return table;
  }
  
  Table USAGE_PRIVILEGES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[89];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[89]);
      addColumn(table, "GRANTOR", SQL_IDENTIFIER);
      addColumn(table, "GRANTEE", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_NAME", SQL_IDENTIFIER);
      addColumn(table, "OBJECT_TYPE", CHARACTER_DATA);
      addColumn(table, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(table, "IS_GRANTABLE", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[89]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5, 6, 7 }, false);
      return table;
    } 
    WrapperIterator wrapperIterator = new WrapperIterator(this.database.schemaManager.databaseObjectIterator(7), this.database.schemaManager.databaseObjectIterator(15));
    wrapperIterator = new WrapperIterator((Iterator)wrapperIterator, this.database.schemaManager.databaseObjectIterator(14));
    wrapperIterator = new WrapperIterator((Iterator)wrapperIterator, this.database.schemaManager.databaseObjectIterator(13));
    OrderedHashSet orderedHashSet = paramSession.getGrantee().getGranteeAndAllRolesWithPublic();
    while (wrapperIterator.hasNext()) {
      SchemaObject schemaObject = (SchemaObject)wrapperIterator.next();
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        Grantee grantee = (Grantee)orderedHashSet.get(b);
        OrderedHashSet orderedHashSet1 = grantee.getAllDirectPrivileges(schemaObject);
        OrderedHashSet orderedHashSet2 = grantee.getAllGrantedPrivileges(schemaObject);
        if (!orderedHashSet2.isEmpty()) {
          orderedHashSet2.addAll((Collection)orderedHashSet1);
          orderedHashSet1 = orderedHashSet2;
        } 
        for (byte b1 = 0; b1 < orderedHashSet1.size(); b1++) {
          Right right1 = (Right)orderedHashSet1.get(b1);
          Right right2 = right1.getGrantableRights();
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (right1.getGrantor().getName()).name;
          arrayOfObject[1] = (right1.getGrantee().getName()).name;
          arrayOfObject[2] = (this.database.getCatalogName()).name;
          arrayOfObject[3] = (schemaObject.getSchemaName()).name;
          arrayOfObject[4] = (schemaObject.getName()).name;
          arrayOfObject[5] = SchemaObjectSet.getName((schemaObject.getName()).type);
          arrayOfObject[6] = "USAGE";
          arrayOfObject[7] = (right1.getGrantee() == schemaObject.getOwner() || right2.isFull()) ? "YES" : "NO";
          try {
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } catch (HsqlException hsqlException) {}
        } 
      } 
    } 
    return table;
  }
  
  Table USER_DEFINED_TYPES(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[90];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[90]);
      addColumn(table, "USER_DEFINED_TYPE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "USER_DEFINED_TYPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "USER_DEFINED_TYPE_NAME", SQL_IDENTIFIER);
      addColumn(table, "USER_DEFINED_TYPE_CATEGORY", SQL_IDENTIFIER);
      addColumn(table, "IS_INSTANTIABLE", YES_OR_NO);
      addColumn(table, "IS_FINAL", YES_OR_NO);
      addColumn(table, "ORDERING_FORM", SQL_IDENTIFIER);
      addColumn(table, "ORDERING_CATEGORY", SQL_IDENTIFIER);
      addColumn(table, "ORDERING_ROUTINE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "ORDERING_ROUTINE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "ORDERING_ROUTINE_NAME", SQL_IDENTIFIER);
      addColumn(table, "REFERENCE_TYPE", SQL_IDENTIFIER);
      addColumn(table, "DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "CHARACTER_MAXIMUM_LENGTH", CARDINAL_NUMBER);
      addColumn(table, "CHARACTER_OCTET_LENGTH", CARDINAL_NUMBER);
      addColumn(table, "CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(table, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(table, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(table, "DATETIME_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "INTERVAL_TYPE", CHARACTER_DATA);
      addColumn(table, "INTERVAL_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "SOURCE_DTD_IDENTIFIER", CHARACTER_DATA);
      addColumn(table, "REF_DTD_IDENTIFIER", CHARACTER_DATA);
      addColumn(table, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(table, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(table, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(table, "MAXIMUM_CARDINALITY", CARDINAL_NUMBER);
      addColumn(table, "EXTERNAL_NAME", CHARACTER_DATA);
      addColumn(table, "EXTERNAL_LANGUAGE", CHARACTER_DATA);
      addColumn(table, "JAVA_INTERFACE", CHARACTER_DATA);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[90]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 4, 5, 6 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(12);
    while (iterator.hasNext()) {
      Type type = (Type)iterator.next();
      if (!type.isDistinctType())
        continue; 
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (type.getSchemaName()).name;
      arrayOfObject[2] = (type.getName()).name;
      arrayOfObject[12] = type.getFullNameString();
      arrayOfObject[3] = "DISTINCT";
      arrayOfObject[4] = "YES";
      arrayOfObject[5] = "YES";
      arrayOfObject[6] = "FULL";
      if (type.isCharacterType()) {
        arrayOfObject[13] = ValuePool.getLong(type.precision);
        arrayOfObject[14] = ValuePool.getLong(type.precision * 2L);
        arrayOfObject[15] = (this.database.getCatalogName()).name;
        arrayOfObject[16] = (((CharacterType)type).getCharacterSet().getSchemaName()).name;
        arrayOfObject[17] = (((CharacterType)type).getCharacterSet().getName()).name;
        arrayOfObject[18] = (this.database.getCatalogName()).name;
        arrayOfObject[19] = (((CharacterType)type).getCollation().getSchemaName()).name;
        arrayOfObject[20] = (((CharacterType)type).getCollation().getName()).name;
      } else if (type.isNumberType()) {
        arrayOfObject[21] = ValuePool.getLong(((NumberType)type).getNumericPrecisionInRadix());
        arrayOfObject[30] = ValuePool.getLong(((NumberType)type).getNumericPrecisionInRadix());
        if (type.isExactNumberType()) {
          arrayOfObject[31] = ValuePool.getLong(type.scale);
          arrayOfObject[23] = ValuePool.getLong(type.scale);
        } 
        arrayOfObject[22] = ValuePool.getLong(type.getPrecisionRadix());
      } else if (!type.isBooleanType()) {
        if (type.isDateTimeType()) {
          arrayOfObject[24] = ValuePool.getLong(type.scale);
        } else if (type.isIntervalType()) {
          arrayOfObject[12] = "INTERVAL";
          (IntervalType)type;
          arrayOfObject[25] = IntervalType.getQualifier(type.typeCode);
          arrayOfObject[26] = ValuePool.getLong(type.precision);
          arrayOfObject[24] = ValuePool.getLong(type.scale);
        } else if (type.isBinaryType()) {
          arrayOfObject[13] = ValuePool.getLong(type.precision);
          arrayOfObject[14] = ValuePool.getLong(type.precision);
        } else if (type.isBitType()) {
          arrayOfObject[13] = ValuePool.getLong(type.precision);
          arrayOfObject[14] = ValuePool.getLong(type.precision);
        } else if (type.isArrayType()) {
          arrayOfObject[32] = ValuePool.getLong(type.arrayLimitCardinality());
          arrayOfObject[12] = "ARRAY";
        } 
      } 
      arrayOfObject[27] = type.getDefinition();
      arrayOfObject[29] = arrayOfObject[12];
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table VIEW_COLUMN_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[91];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[91]);
      addColumn(table, "VIEW_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "VIEW_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "VIEW_NAME", SQL_IDENTIFIER);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "COLUMN_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[91]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5, 6 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(3);
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if (table1.isView() && paramSession.getGrantee().isFullyAccessibleByRole(table1.getName())) {
        String str1 = (this.database.getCatalogName()).name;
        String str2 = (table1.getSchemaName()).name;
        String str3 = (table1.getName()).name;
        View view = (View)table1;
        OrderedHashSet orderedHashSet = view.getReferences();
        Iterator iterator1 = orderedHashSet.iterator();
        while (iterator1.hasNext()) {
          HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)iterator1.next();
          if (hsqlName.type != 9)
            continue; 
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = str1;
          arrayOfObject[1] = str2;
          arrayOfObject[2] = str3;
          arrayOfObject[3] = str1;
          arrayOfObject[4] = hsqlName.parent.schema.name;
          arrayOfObject[5] = hsqlName.parent.name;
          arrayOfObject[6] = hsqlName.name;
          try {
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } catch (HsqlException hsqlException) {}
        } 
      } 
    } 
    return table;
  }
  
  Table VIEW_ROUTINE_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[92];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[92]);
      addColumn(table, "VIEW_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "VIEW_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "VIEW_NAME", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "SPECIFIC_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[92]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(3);
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if (!table1.isView())
        continue; 
      OrderedHashSet orderedHashSet = table1.getReferences();
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
        if (hsqlName.type == 24 && paramSession.getGrantee().isFullyAccessibleByRole(hsqlName)) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (table1.getSchemaName()).name;
          arrayOfObject[2] = (table1.getName()).name;
          arrayOfObject[3] = (this.database.getCatalogName()).name;
          arrayOfObject[4] = hsqlName.schema.name;
          arrayOfObject[5] = hsqlName.name;
          try {
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } catch (HsqlException hsqlException) {}
        } 
      } 
    } 
    return table;
  }
  
  Table VIEW_TABLE_USAGE(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[93];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[93]);
      addColumn(table, "VIEW_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "VIEW_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "VIEW_NAME", SQL_IDENTIFIER);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[93]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return table;
    } 
    Iterator iterator = this.database.schemaManager.databaseObjectIterator(3);
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if (!table1.isView())
        continue; 
      OrderedHashSet orderedHashSet = table1.getReferences();
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
        if ((hsqlName.type == 3 || hsqlName.type == 4) && paramSession.getGrantee().isFullyAccessibleByRole(hsqlName)) {
          Object[] arrayOfObject = table.getEmptyRowData();
          arrayOfObject[0] = (this.database.getCatalogName()).name;
          arrayOfObject[1] = (table1.getSchemaName()).name;
          arrayOfObject[2] = (table1.getName()).name;
          arrayOfObject[3] = (this.database.getCatalogName()).name;
          arrayOfObject[4] = hsqlName.schema.name;
          arrayOfObject[5] = hsqlName.name;
          try {
            table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          } catch (HsqlException hsqlException) {}
        } 
      } 
    } 
    return table;
  }
  
  Table VIEWS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[94];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[94]);
      addColumn(table, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(table, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(table, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "VIEW_DEFINITION", CHARACTER_DATA);
      addColumn(table, "CHECK_OPTION", CHARACTER_DATA);
      addColumn(table, "IS_UPDATABLE", YES_OR_NO);
      addColumn(table, "INSERTABLE_INTO", YES_OR_NO);
      addColumn(table, "IS_TRIGGER_UPDATABLE", YES_OR_NO);
      addColumn(table, "IS_TRIGGER_DELETABLE", YES_OR_NO);
      addColumn(table, "IS_TRIGGER_INSERTABLE_INTO", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[94]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 1, 2, 0 }, false);
      return table;
    } 
    Iterator iterator = allTables();
    while (iterator.hasNext()) {
      Table table1 = (Table)iterator.next();
      if ((!table1.isView() && table1.getSchemaName() != SqlInvariants.INFORMATION_SCHEMA_HSQLNAME) || !isAccessibleTable(paramSession, table1))
        continue; 
      Object[] arrayOfObject = table.getEmptyRowData();
      arrayOfObject[0] = (this.database.getCatalogName()).name;
      arrayOfObject[1] = (table1.getSchemaName()).name;
      arrayOfObject[2] = (table1.getName()).name;
      String str = "NONE";
      if (table1 instanceof View) {
        if (paramSession.getGrantee().isFullyAccessibleByRole(table1.getName()))
          arrayOfObject[3] = ((View)table1).getStatement(); 
        switch (((View)table1).getCheckOption()) {
          case 1:
            str = "LOCAL";
            break;
          case 2:
            str = "CASCADED";
            break;
        } 
      } 
      arrayOfObject[4] = str;
      arrayOfObject[5] = table1.isUpdatable() ? "YES" : "NO";
      arrayOfObject[6] = table1.isInsertable() ? "YES" : "NO";
      arrayOfObject[7] = table1.isTriggerUpdatable() ? "YES" : "NO";
      arrayOfObject[8] = table1.isTriggerDeletable() ? "YES" : "NO";
      arrayOfObject[9] = table1.isTriggerInsertable() ? "YES" : "NO";
      table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    } 
    return table;
  }
  
  Table ROLE_AUTHORIZATION_DESCRIPTORS(Session paramSession, PersistentStore paramPersistentStore) {
    Table table = this.sysTables[55];
    if (table == null) {
      table = createBlankTable(sysTableHsqlNames[55]);
      addColumn(table, "ROLE_NAME", SQL_IDENTIFIER);
      addColumn(table, "GRANTEE", SQL_IDENTIFIER);
      addColumn(table, "GRANTOR", SQL_IDENTIFIER);
      addColumn(table, "IS_GRANTABLE", YES_OR_NO);
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName((sysTableHsqlNames[55]).name, false, 20);
      table.createPrimaryKeyConstraint(hsqlName, new int[] { 0, 1 }, true);
      return table;
    } 
    String str = "_SYSTEM";
    Iterator iterator = paramSession.getGrantee().visibleGrantees().iterator();
    while (iterator.hasNext()) {
      Grantee grantee = (Grantee)iterator.next();
      String str1 = grantee.getName().getNameString();
      Iterator iterator1 = grantee.getDirectRoles().iterator();
      String str2 = grantee.isAdmin() ? "YES" : "NO";
      while (iterator1.hasNext()) {
        Grantee grantee1 = (Grantee)iterator1.next();
        Object[] arrayOfObject = table.getEmptyRowData();
        arrayOfObject[0] = grantee1.getName().getNameString();
        arrayOfObject[1] = str1;
        arrayOfObject[2] = str;
        arrayOfObject[3] = str2;
        table.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      } 
    } 
    return table;
  }
  
  static {
    synchronized (DatabaseInformationFull.class) {
      String[] arrayOfString = { "/*" };
      InputStream inputStream = AccessController.<InputStream>doPrivileged(new PrivilegedAction<InputStream>() {
            public InputStream run() {
              return getClass().getResourceAsStream("/org/hsqldb/resources/information-schema.sql");
            }
          });
      InputStreamReader inputStreamReader = null;
      try {
        inputStreamReader = new InputStreamReader(inputStream, "ISO-8859-1");
      } catch (Exception exception) {}
      LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
      LineGroupReader lineGroupReader = new LineGroupReader(lineNumberReader, arrayOfString);
      statementMap = lineGroupReader.getAsMap();
      lineGroupReader.close();
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\dbinfo\DatabaseInformationFull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */