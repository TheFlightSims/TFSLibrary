package org.hsqldb.dbinfo;

import java.lang.reflect.Constructor;
import org.hsqldb.Database;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.lib.IntValueHashMap;
import org.hsqldb.persist.PersistentStore;

public class DatabaseInformation {
  protected static final int SYSTEM_BESTROWIDENTIFIER = 0;
  
  protected static final int SYSTEM_COLUMNS = 1;
  
  protected static final int SYSTEM_CROSSREFERENCE = 2;
  
  protected static final int SYSTEM_INDEXINFO = 3;
  
  protected static final int SYSTEM_PRIMARYKEYS = 4;
  
  protected static final int SYSTEM_PROCEDURECOLUMNS = 5;
  
  protected static final int SYSTEM_PROCEDURES = 6;
  
  protected static final int SYSTEM_SCHEMAS = 7;
  
  protected static final int SYSTEM_TABLES = 8;
  
  protected static final int SYSTEM_TABLETYPES = 9;
  
  protected static final int SYSTEM_TYPEINFO = 10;
  
  protected static final int SYSTEM_UDTS = 11;
  
  protected static final int SYSTEM_USERS = 12;
  
  protected static final int SYSTEM_VERSIONCOLUMNS = 13;
  
  protected static final int SYSTEM_SEQUENCES = 14;
  
  protected static final int SYSTEM_CACHEINFO = 15;
  
  protected static final int SYSTEM_COLUMN_SEQUENCE_USAGE = 16;
  
  protected static final int SYSTEM_COMMENTS = 17;
  
  protected static final int SYSTEM_CONNECTION_PROPERTIES = 18;
  
  protected static final int SYSTEM_PROPERTIES = 19;
  
  protected static final int SYSTEM_SESSIONINFO = 20;
  
  protected static final int SYSTEM_SESSIONS = 21;
  
  protected static final int SYSTEM_TEXTTABLES = 22;
  
  protected static final int SYSTEM_TABLESTATS = 23;
  
  protected static final int ADMINISTRABLE_ROLE_AUTHORIZATIONS = 24;
  
  protected static final int APPLICABLE_ROLES = 25;
  
  protected static final int ASSERTIONS = 26;
  
  protected static final int AUTHORIZATIONS = 27;
  
  protected static final int CHARACTER_SETS = 28;
  
  protected static final int CHECK_CONSTRAINT_ROUTINE_USAGE = 29;
  
  protected static final int CHECK_CONSTRAINTS = 30;
  
  protected static final int COLLATIONS = 31;
  
  protected static final int COLUMN_COLUMN_USAGE = 32;
  
  protected static final int COLUMN_DOMAIN_USAGE = 33;
  
  protected static final int COLUMN_PRIVILEGES = 34;
  
  protected static final int COLUMN_UDT_USAGE = 35;
  
  protected static final int COLUMNS = 36;
  
  protected static final int CONSTRAINT_COLUMN_USAGE = 37;
  
  protected static final int CONSTRAINT_TABLE_USAGE = 38;
  
  protected static final int DATA_TYPE_PRIVILEGES = 39;
  
  protected static final int DOMAIN_CONSTRAINTS = 40;
  
  protected static final int DOMAINS = 41;
  
  protected static final int ELEMENT_TYPES = 42;
  
  protected static final int ENABLED_ROLES = 43;
  
  protected static final int INFORMATION_SCHEMA_CATALOG_NAME = 44;
  
  protected static final int JAR_JAR_USAGE = 45;
  
  protected static final int JARS = 46;
  
  protected static final int KEY_COLUMN_USAGE = 47;
  
  protected static final int METHOD_SPECIFICATIONS = 48;
  
  protected static final int MODULE_COLUMN_USAGE = 49;
  
  protected static final int MODULE_PRIVILEGES = 50;
  
  protected static final int MODULE_TABLE_USAGE = 51;
  
  protected static final int MODULES = 52;
  
  protected static final int PARAMETERS = 53;
  
  protected static final int REFERENTIAL_CONSTRAINTS = 54;
  
  protected static final int ROLE_AUTHORIZATION_DESCRIPTORS = 55;
  
  protected static final int ROLE_COLUMN_GRANTS = 56;
  
  protected static final int ROLE_MODULE_GRANTS = 57;
  
  protected static final int ROLE_ROUTINE_GRANTS = 58;
  
  protected static final int ROLE_TABLE_GRANTS = 59;
  
  protected static final int ROLE_UDT_GRANTS = 60;
  
  protected static final int ROLE_USAGE_GRANTS = 61;
  
  protected static final int ROUTINE_COLUMN_USAGE = 62;
  
  protected static final int ROUTINE_JAR_USAGE = 63;
  
  protected static final int ROUTINE_PRIVILEGES = 64;
  
  protected static final int ROUTINE_ROUTINE_USAGE = 65;
  
  protected static final int ROUTINE_SEQUENCE_USAGE = 66;
  
  protected static final int ROUTINE_TABLE_USAGE = 67;
  
  protected static final int ROUTINES = 68;
  
  protected static final int SCHEMATA = 69;
  
  protected static final int SEQUENCES = 70;
  
  protected static final int SQL_FEATURES = 71;
  
  protected static final int SQL_IMPLEMENTATION_INFO = 72;
  
  protected static final int SQL_PACKAGES = 73;
  
  protected static final int SQL_PARTS = 74;
  
  protected static final int SQL_SIZING = 75;
  
  protected static final int SQL_SIZING_PROFILES = 76;
  
  protected static final int TABLE_CONSTRAINTS = 77;
  
  protected static final int TABLE_PRIVILEGES = 78;
  
  protected static final int TABLES = 79;
  
  protected static final int TRANSLATIONS = 80;
  
  protected static final int TRIGGER_COLUMN_USAGE = 81;
  
  protected static final int TRIGGER_ROUTINE_USAGE = 82;
  
  protected static final int TRIGGER_SEQUENCE_USAGE = 83;
  
  protected static final int TRIGGER_TABLE_USAGE = 84;
  
  protected static final int TRIGGERED_UPDATE_COLUMNS = 85;
  
  protected static final int TRIGGERS = 86;
  
  protected static final int TYPE_JAR_USAGE = 87;
  
  protected static final int UDT_PRIVILEGES = 88;
  
  protected static final int USAGE_PRIVILEGES = 89;
  
  protected static final int USER_DEFINED_TYPES = 90;
  
  protected static final int VIEW_COLUMN_USAGE = 91;
  
  protected static final int VIEW_ROUTINE_USAGE = 92;
  
  protected static final int VIEW_TABLE_USAGE = 93;
  
  protected static final int VIEWS = 94;
  
  protected static final String[] sysTableNames = new String[] { 
      "SYSTEM_BESTROWIDENTIFIER", "SYSTEM_COLUMNS", "SYSTEM_CROSSREFERENCE", "SYSTEM_INDEXINFO", "SYSTEM_PRIMARYKEYS", "SYSTEM_PROCEDURECOLUMNS", "SYSTEM_PROCEDURES", "SYSTEM_SCHEMAS", "SYSTEM_TABLES", "SYSTEM_TABLETYPES", 
      "SYSTEM_TYPEINFO", "SYSTEM_UDTS", "SYSTEM_USERS", "SYSTEM_VERSIONCOLUMNS", "SYSTEM_SEQUENCES", "SYSTEM_CACHEINFO", "SYSTEM_COLUMN_SEQUENCE_USAGE", "SYSTEM_COMMENTS", "SYSTEM_CONNECTION_PROPERTIES", "SYSTEM_PROPERTIES", 
      "SYSTEM_SESSIONINFO", "SYSTEM_SESSIONS", "SYSTEM_TEXTTABLES", "SYSTEM_TABLESTATS", "ADMINISTRABLE_ROLE_AUTHORIZATIONS", "APPLICABLE_ROLES", "ASSERTIONS", "AUTHORIZATIONS", "CHARACTER_SETS", "CHECK_CONSTRAINT_ROUTINE_USAGE", 
      "CHECK_CONSTRAINTS", "COLLATIONS", "COLUMN_COLUMN_USAGE", "COLUMN_DOMAIN_USAGE", "COLUMN_PRIVILEGES", "COLUMN_UDT_USAGE", "COLUMNS", "CONSTRAINT_COLUMN_USAGE", "CONSTRAINT_TABLE_USAGE", "DATA_TYPE_PRIVILEGES", 
      "DOMAIN_CONSTRAINTS", "DOMAINS", "ELEMENT_TYPES", "ENABLED_ROLES", "INFORMATION_SCHEMA_CATALOG_NAME", "JAR_JAR_USAGE", "JARS", "KEY_COLUMN_USAGE", "METHOD_SPECIFICATIONS", "MODULE_COLUMN_USAGE", 
      "MODULE_PRIVILEGES", "MODULE_TABLE_USAGE", "MODULES", "PARAMETERS", "REFERENTIAL_CONSTRAINTS", "ROLE_AUTHORIZATION_DESCRIPTORS", "ROLE_COLUMN_GRANTS", "ROLE_MODULE_GRANTS", "ROLE_ROUTINE_GRANTS", "ROLE_TABLE_GRANTS", 
      "ROLE_UDT_GRANTS", "ROLE_USAGE_GRANTS", "ROUTINE_COLUMN_USAGE", "ROUTINE_JAR_USAGE", "ROUTINE_PRIVILEGES", "ROUTINE_ROUTINE_USAGE", "ROUTINE_SEQUENCE_USAGE", "ROUTINE_TABLE_USAGE", "ROUTINES", "SCHEMATA", 
      "SEQUENCES", "SQL_FEATURES", "SQL_IMPLEMENTATION_INFO", "SQL_PACKAGES", "SQL_PARTS", "SQL_SIZING", "SQL_SIZING_PROFILES", "TABLE_CONSTRAINTS", "TABLE_PRIVILEGES", "TABLES", 
      "TRANSLATIONS", "TRIGGER_COLUMN_USAGE", "TRIGGER_ROUTINE_USAGE", "TRIGGER_SEQUENCE_USAGE", "TRIGGER_TABLE_USAGE", "TRIGGERED_UPDATE_COLUMNS", "TRIGGERS", "TYPE_JAR_USAGE", "UDT_PRIVILEGES", "USAGE_PRIVILEGES", 
      "USER_DEFINED_TYPES", "VIEW_COLUMN_USAGE", "VIEW_ROUTINE_USAGE", "VIEW_TABLE_USAGE", "VIEWS" };
  
  protected static final IntValueHashMap sysTableNamesMap;
  
  protected final Database database;
  
  protected boolean withContent = false;
  
  static int getSysTableID(String paramString) {
    return sysTableNamesMap.get(paramString, -1);
  }
  
  public static final DatabaseInformation newDatabaseInformation(Database paramDatabase) {
    Class<?> clazz = null;
    try {
      clazz = Class.forName("org.hsqldb.dbinfo.DatabaseInformationFull");
    } catch (Exception exception) {
      try {
        clazz = Class.forName("org.hsqldb.dbinfo.DatabaseInformationMain");
      } catch (Exception exception1) {
        clazz = DatabaseInformation.class;
      } 
    } 
    try {
      Class[] arrayOfClass = { Database.class };
      Object[] arrayOfObject = { paramDatabase };
      Constructor<?> constructor = clazz.getDeclaredConstructor(arrayOfClass);
      return (DatabaseInformation)constructor.newInstance(arrayOfObject);
    } catch (Exception exception) {
      exception.printStackTrace();
      return new DatabaseInformation(paramDatabase);
    } 
  }
  
  DatabaseInformation(Database paramDatabase) {
    this.database = paramDatabase;
  }
  
  final boolean isSystemTable(String paramString) {
    return sysTableNamesMap.containsKey(paramString);
  }
  
  public Table getSystemTable(Session paramSession, String paramString) {
    return null;
  }
  
  public void setStore(Session paramSession, Table paramTable, PersistentStore paramPersistentStore) {}
  
  public final void setWithContent(boolean paramBoolean) {
    this.withContent = paramBoolean;
  }
  
  static {
    synchronized (DatabaseInformation.class) {
      sysTableNamesMap = new IntValueHashMap(97);
      for (byte b = 0; b < sysTableNames.length; b++)
        sysTableNamesMap.put(sysTableNames[b], b); 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\dbinfo\DatabaseInformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */