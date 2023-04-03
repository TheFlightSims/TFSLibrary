package org.hsqldb;

public interface StatementTypes {
  public static final int ALLOCATE_CURSOR = 1;
  
  public static final int ALLOCATE_DESCRIPTOR = 2;
  
  public static final int ALTER_DOMAIN = 3;
  
  public static final int ALTER_ROUTINE = 17;
  
  public static final int ALTER_SEQUENCE = 134;
  
  public static final int ALTER_TYPE = 60;
  
  public static final int ALTER_TABLE = 4;
  
  public static final int ALTER_TRANSFORM = 127;
  
  public static final int CREATE_ASSERTION = 6;
  
  public static final int CALL = 7;
  
  public static final int CREATE_CHARACTER_SET = 8;
  
  public static final int CLOSE_CURSOR = 9;
  
  public static final int CREATE_COLLATION = 10;
  
  public static final int COMMIT_WORK = 11;
  
  public static final int CONNECT = 13;
  
  public static final int DEALLOCATE_DESCRIPTOR = 15;
  
  public static final int DEALLOCATE_PREPARE = 16;
  
  public static final int DELETE_CURSOR = 18;
  
  public static final int DELETE_WHERE = 19;
  
  public static final int DESCRIBE = 20;
  
  public static final int SELECT_DIRECT_SINGLE = 21;
  
  public static final int DISCONNECT = 22;
  
  public static final int CREATE_DOMAIN = 23;
  
  public static final int DROP_ASSERTION = 24;
  
  public static final int DROP_CHARACTER_SET = 25;
  
  public static final int DROP_COLLATION = 26;
  
  public static final int DROP_TYPE = 35;
  
  public static final int DROP_DOMAIN = 27;
  
  public static final int DROP_ROLE = 29;
  
  public static final int DROP_ROUTINE = 30;
  
  public static final int DROP_SCHEMA = 31;
  
  public static final int DROP_SEQUENCE = 135;
  
  public static final int DROP_TABLE = 32;
  
  public static final int DROP_TRANSFORM = 116;
  
  public static final int DROP_TRANSLATION = 33;
  
  public static final int DROP_TRIGGER = 34;
  
  public static final int DROP_CAST = 78;
  
  public static final int DROP_ORDERING = 115;
  
  public static final int DROP_VIEW = 36;
  
  public static final int DYNAMIC_CLOSE = 37;
  
  public static final int DYNAMIC_DELETE_CURSOR = 38;
  
  public static final int DYNAMIC_FETCH = 39;
  
  public static final int DYNAMIC_OPEN = 40;
  
  public static final int SELECT_CURSOR = 85;
  
  public static final int SELECT_SINGLE_DYNAMIC = 41;
  
  public static final int DYNAMIC_UPDATE_CURSOR = 42;
  
  public static final int EXECUTE_IMMEDIATE = 43;
  
  public static final int EXECUTE = 44;
  
  public static final int FETCH = 45;
  
  public static final int FREE_LOCATOR = 98;
  
  public static final int GET_DESCRIPTOR = 47;
  
  public static final int HOLD_LOCATOR = 99;
  
  public static final int GRANT = 48;
  
  public static final int GRANT_ROLE = 49;
  
  public static final int INSERT = 50;
  
  public static final int MERGE = 128;
  
  public static final int OPEN = 53;
  
  public static final int PREPARABLE_DYNAMIC_DELETE_CURSOR = 54;
  
  public static final int PREPARABLE_DYNAMIC_UPDATE_CURSOR = 55;
  
  public static final int PREPARE = 56;
  
  public static final int RELEASE_SAVEPOINT = 57;
  
  public static final int RETURN = 58;
  
  public static final int REVOKE = 59;
  
  public static final int REVOKE_ROLE = 129;
  
  public static final int CREATE_ROLE = 61;
  
  public static final int ROLLBACK_WORK = 62;
  
  public static final int SAVEPOINT = 63;
  
  public static final int CREATE_SCHEMA = 64;
  
  public static final int CREATE_ROUTINE = 14;
  
  public static final int SELECT_SINGLE = 65;
  
  public static final int CREATE_SEQUENCE = 133;
  
  public static final int SET_CATALOG = 66;
  
  public static final int SET_CONNECTION = 67;
  
  public static final int SET_CONSTRAINT = 68;
  
  public static final int SET_DESCRIPTOR = 70;
  
  public static final int SET_TIME_ZONE = 71;
  
  public static final int SET_NAMES = 72;
  
  public static final int SET_PATH = 69;
  
  public static final int SET_ROLE = 73;
  
  public static final int SET_SCHEMA = 74;
  
  public static final int SET_SESSION_AUTHORIZATION = 76;
  
  public static final int SET_SESSION_CHARACTERISTICS = 109;
  
  public static final int SET_COLLATION = 136;
  
  public static final int SET_TRANSFORM_GROUP = 118;
  
  public static final int SET_TRANSACTION = 75;
  
  public static final int START_TRANSACTION = 111;
  
  public static final int CREATE_TABLE = 77;
  
  public static final int CREATE_TRANSFORM = 117;
  
  public static final int CREATE_TRANSLATION = 79;
  
  public static final int CREATE_TRIGGER = 80;
  
  public static final int UPDATE_CURSOR = 81;
  
  public static final int UPDATE_WHERE = 82;
  
  public static final int CREATE_CAST = 52;
  
  public static final int CREATE_TYPE = 83;
  
  public static final int CREATE_ORDERING = 114;
  
  public static final int CREATE_VIEW = 84;
  
  public static final int ASSIGNMENT = 5;
  
  public static final int CASE = 86;
  
  public static final int BEGIN_END = 12;
  
  public static final int DROP_MODULE = 28;
  
  public static final int FOR = 46;
  
  public static final int IF = 88;
  
  public static final int ITERATE = 102;
  
  public static final int LEAVE = 89;
  
  public static final int LOOP = 90;
  
  public static final int RESIGNAL = 91;
  
  public static final int REPEAT = 95;
  
  public static final int SIGNAL = 92;
  
  public static final int CREATE_MODULE = 51;
  
  public static final int WHILE = 97;
  
  public static final int ALTER_FOREIGN_TABLE = 104;
  
  public static final int ALTER_USER_MAPPING = 123;
  
  public static final int DROP_FOREIGN_DATA_WRAPPER = 121;
  
  public static final int DROP_SERVER = 110;
  
  public static final int DROP_FOREIGN_TABLE = 105;
  
  public static final int DROP_ROUTINE_MAPPING = 131;
  
  public static final int DROP_USER_MAPPING = 124;
  
  public static final int CREATE_FOREIGN_DATA_WRAPPER = 119;
  
  public static final int CREATE_SERVER = 107;
  
  public static final int CREATE_FOREIGN_TABLE = 103;
  
  public static final int IMPORT_FOREIGN_SCHEMA = 125;
  
  public static final int CREATE_ROUTINE_MAPPING = 132;
  
  public static final int SET_PASSTHROUGH = 126;
  
  public static final int CREATE_USER_MAPPING = 122;
  
  public static final int DATABASE_BACKUP = 1001;
  
  public static final int DATABASE_CHECKPOINT = 1002;
  
  public static final int DATABASE_SHUTDOWN = 1003;
  
  public static final int DATABASE_SCRIPT = 1004;
  
  public static final int ALTER_SESSION = 1005;
  
  public static final int SET_DATABASE_FILES_BACKUP_INCREMENT = 1011;
  
  public static final int SET_DATABASE_FILES_CACHE_ROWS = 1012;
  
  public static final int SET_DATABASE_FILES_CACHE_SIZE = 1013;
  
  public static final int SET_DATABASE_FILES_CHECK = 1014;
  
  public static final int SET_DATABASE_FILES_DEFRAG = 1015;
  
  public static final int SET_DATABASE_FILES_EVENT_LOG = 1016;
  
  public static final int SET_DATABASE_FILES_LOBS_SCALE = 1017;
  
  public static final int SET_DATABASE_FILES_LOBS_COMPRESSED = 1018;
  
  public static final int SET_DATABASE_FILES_UNUSED_TYPE_SETTING = 1019;
  
  public static final int SET_DATABASE_FILES_LOG = 1020;
  
  public static final int SET_DATABASE_FILES_LOG_SIZE = 1021;
  
  public static final int SET_DATABASE_FILES_NIO = 1022;
  
  public static final int SET_DATABASE_FILES_READ_ONLY = 1023;
  
  public static final int SET_DATABASE_FILES_READ_ONLY_FILES = 1024;
  
  public static final int SET_DATABASE_FILES_SCALE = 1025;
  
  public static final int SET_DATABASE_FILES_SCRIPT_FORMAT = 1026;
  
  public static final int SET_DATABASE_FILES_SPACE = 1031;
  
  public static final int SET_DATABASE_FILES_TEMP_PATH = 1032;
  
  public static final int SET_DATABASE_FILES_WRITE_DELAY = 1033;
  
  public static final int SET_DATABASE_DEFAULT_INITIAL_SCHEMA = 1034;
  
  public static final int SET_DATABASE_DEFAULT_TABLE_TYPE = 1035;
  
  public static final int SET_DATABASE_AUTHENTICATION = 1036;
  
  public static final int SET_DATABASE_GC = 1037;
  
  public static final int SET_DATABASE_PROPERTY = 1039;
  
  public static final int SET_DATABASE_PASSWORD_CHECK = 1040;
  
  public static final int SET_DATABASE_READ_ONLY = 1041;
  
  public static final int SET_DATABASE_READ_ONLY_FILES = 1042;
  
  public static final int SET_DATABASE_RESULT_MEMORY_ROWS = 1046;
  
  public static final int SET_DATABASE_SQL_COLLATION = 1047;
  
  public static final int SET_SESSION_SQL_IGNORECASE = 1048;
  
  public static final int SET_DATABASE_SQL_REFERENTIAL_INTEGRITY = 1049;
  
  public static final int SET_DATABASE_SQL = 1050;
  
  public static final int SET_DATABASE_TEXT_SOURCE = 1051;
  
  public static final int SET_DATABASE_TRANSACTION_CONTROL = 1052;
  
  public static final int SET_DATABASE_DEFAULT_ISOLATION_LEVEL = 1053;
  
  public static final int SET_DATABASE_TRANSACTION_CONFLICT = 1054;
  
  public static final int SET_DATABASE_UNIQUE_NAME = 1055;
  
  public static final int SET_USER_LOCAL = 1060;
  
  public static final int SET_USER_INITIAL_SCHEMA = 1061;
  
  public static final int SET_USER_PASSWORD = 1062;
  
  public static final int TRANSACTION_LOCK_TABLE = 1063;
  
  public static final int SET_SESSION_AUTOCOMMIT = 1064;
  
  public static final int SET_SESSION_RESULT_MAX_ROWS = 1065;
  
  public static final int SET_SESSION_RESULT_MEMORY_ROWS = 1066;
  
  public static final int ROLLBACK_SAVEPOINT = 1067;
  
  public static final int DECLARE_SESSION_TABLE = 1068;
  
  public static final int ALTER_INDEX = 1069;
  
  public static final int ALTER_VIEW = 1070;
  
  public static final int COMMENT = 1071;
  
  public static final int CREATE_ALIAS = 1072;
  
  public static final int CREATE_INDEX = 1073;
  
  public static final int CREATE_USER = 1074;
  
  public static final int DECLARE_VARIABLE = 1075;
  
  public static final int DROP_COLUMN = 1076;
  
  public static final int DROP_INDEX = 1077;
  
  public static final int DROP_CONSTRAINT = 1078;
  
  public static final int DROP_USER = 1079;
  
  public static final int DROP_DEFAULT = 1080;
  
  public static final int ADD_COLUMN = 1081;
  
  public static final int ADD_CONSTRAINT = 1082;
  
  public static final int ADD_DEFAULT = 1083;
  
  public static final int ALTER_COLUMN_TYPE = 1084;
  
  public static final int ALTER_COLUMN_SEQUENCE = 1085;
  
  public static final int ALTER_COLUMN_NULL = 1086;
  
  public static final int ALTER_COLUMN_DEFAULT = 1087;
  
  public static final int ALTER_COLUMN_DROP_DEFAULT = 1088;
  
  public static final int ALTER_COLUMN_DROP_GENERATED = 1089;
  
  public static final int ALTER_COLUMN_TYPE_IDENTITY = 1090;
  
  public static final int EXPLAIN_PLAN = 1191;
  
  public static final int RENAME_OBJECT = 1192;
  
  public static final int SET_TABLE_INDEX = 1193;
  
  public static final int SET_TABLE_READONLY = 1194;
  
  public static final int SET_TABLE_SOURCE = 1195;
  
  public static final int SET_TABLE_SOURCE_HEADER = 1196;
  
  public static final int SET_TABLE_TYPE = 1197;
  
  public static final int SET_TABLE_CLUSTERED = 1198;
  
  public static final int SET_TABLE_NEW_TABLESPACE = 1199;
  
  public static final int SET_TABLE_SET_TABLESPACE = 1200;
  
  public static final int LOG_SCHEMA_STATEMENT = 1201;
  
  public static final int CONDITION = 1211;
  
  public static final int HANDLER = 1212;
  
  public static final int DDL = 1213;
  
  public static final int CHECK = 1214;
  
  public static final int TRUNCATE = 1215;
  
  public static final int CREATE_SEARCH = 1301;
  
  public static final int DROP_SEARCH = 1302;
  
  public static final int X_SQL_SCHEMA_DEFINITION = 2001;
  
  public static final int X_SQL_SCHEMA_MANIPULATION = 2002;
  
  public static final int X_SQL_DATA = 2003;
  
  public static final int X_SQL_DATA_CHANGE = 2004;
  
  public static final int X_SQL_TRANSACTION = 2005;
  
  public static final int X_SQL_CONNECTION = 2006;
  
  public static final int X_SQL_CONTROL = 2007;
  
  public static final int X_SQL_SESSION = 2008;
  
  public static final int X_SQL_DIAGNOSTICS = 2009;
  
  public static final int X_SQL_DYNAMIC = 2010;
  
  public static final int X_HSQLDB_SESSION = 2011;
  
  public static final int X_HSQLDB_SCHEMA_MANIPULATION = 2012;
  
  public static final int X_HSQLDB_SETTING = 2013;
  
  public static final int X_HSQLDB_DATABASE_OPERATION = 2014;
  
  public static final int X_HSQLDB_TRANSACTION = 2015;
  
  public static final int X_DYNAMIC = 2016;
  
  public static final int RETURN_ANY = 0;
  
  public static final int RETURN_COUNT = 1;
  
  public static final int RETURN_RESULT = 2;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementTypes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */