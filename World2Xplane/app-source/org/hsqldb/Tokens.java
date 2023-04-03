package org.hsqldb;

import org.hsqldb.lib.IntKeyHashMap;
import org.hsqldb.lib.IntValueHashMap;
import org.hsqldb.lib.OrderedIntHashSet;

public class Tokens {
  static final String T_ABS = "ABS";
  
  public static final String T_ALL = "ALL";
  
  static final String T_ALLOCATE = "ALLOCATE";
  
  public static final String T_ALTER = "ALTER";
  
  static final String T_AND = "AND";
  
  public static final String T_ANY = "ANY";
  
  static final String T_ARE = "ARE";
  
  public static final String T_ARRAY = "ARRAY";
  
  public static final String T_ARRAY_AGG = "ARRAY_AGG";
  
  public static final String T_AS = "AS";
  
  static final String T_ASENSITIVE = "ASENSITIVE";
  
  static final String T_ASYMMETRIC = "ASYMMETRIC";
  
  static final String T_AT = "AT";
  
  static final String T_ATOMIC = "ATOMIC";
  
  public static final String T_AUTHORIZATION = "AUTHORIZATION";
  
  public static final String T_AVG = "AVG";
  
  static final String T_BEGIN = "BEGIN";
  
  static final String T_BETWEEN = "BETWEEN";
  
  public static final String T_BIGINT = "BIGINT";
  
  public static final String T_BINARY = "BINARY";
  
  static final String T_BIT_LENGTH = "BIT_LENGTH";
  
  public static final String T_BLOB = "BLOB";
  
  public static final String T_BOOLEAN = "BOOLEAN";
  
  static final String T_BOTH = "BOTH";
  
  static final String T_BY = "BY";
  
  public static final String T_CALL = "CALL";
  
  static final String T_CALLED = "CALLED";
  
  static final String T_CARDINALITY = "CARDINALITY";
  
  public static final String T_CASCADED = "CASCADED";
  
  static final String T_CASE = "CASE";
  
  static final String T_CAST = "CAST";
  
  static final String T_CEIL = "CEIL";
  
  static final String T_CEILING = "CEILING";
  
  public static final String T_CHAR = "CHAR";
  
  static final String T_CHAR_LENGTH = "CHAR_LENGTH";
  
  public static final String T_CHARACTER = "CHARACTER";
  
  static final String T_CHARACTER_LENGTH = "CHARACTER_LENGTH";
  
  public static final String T_CHECK = "CHECK";
  
  public static final String T_CLOB = "CLOB";
  
  static final String T_CLOSE = "CLOSE";
  
  static final String T_COALESCE = "COALESCE";
  
  public static final String T_COLLATE = "COLLATE";
  
  static final String T_COLLECT = "COLLECT";
  
  static final String T_COLUMN = "COLUMN";
  
  public static final String T_COMMIT = "COMMIT";
  
  static final String T_CONDITION = "CONDIITON";
  
  public static final String T_CONNECT = "CONNECT";
  
  public static final String T_CONSTRAINT = "CONSTRAINT";
  
  public static final String T_CONVERT = "CONVERT";
  
  static final String T_CORR = "CORR";
  
  static final String T_CORRESPONDING = "CORRESPONDING";
  
  static final String T_COUNT = "COUNT";
  
  static final String T_COVAR_POP = "COVAR_POP";
  
  static final String T_COVAR_SAMP = "COVAR_SAMP";
  
  public static final String T_CREATE = "CREATE";
  
  static final String T_CROSS = "CROSS";
  
  static final String T_CUBE = "CUBE";
  
  static final String T_CUME_DIST = "CUME_DIST";
  
  static final String T_CURRENT = "CURRENT";
  
  static final String T_CURRENT_CATALOG = "CURRENT_CATALOG";
  
  static final String T_CURRENT_DATE = "CURRENT_DATE";
  
  static final String T_CURRENT_DEFAULT_TRANSFORM_GROUP = "CURRENT_DEFAULT_TRANSFORM_GROUP";
  
  static final String T_CURRENT_PATH = "CURRENT_PATH";
  
  static final String T_CURRENT_ROLE = "CURRENT_ROLE";
  
  static final String T_CURRENT_SCHEMA = "CURRENT_SCHEMA";
  
  static final String T_CURRENT_TIME = "CURRENT_TIME";
  
  static final String T_CURRENT_TIMESTAMP = "CURRENT_TIMESTAMP";
  
  static final String T_CURRENT_TRANSFORM_GROUP_FOR_TYPE = "CURRENT_TRANSFORM_GROUP_FOR_TYPE";
  
  static final String T_CURRENT_USER = "CURRENT_USER";
  
  static final String T_CURSOR = "CURSOR";
  
  static final String T_CYCLE = "CYCLE";
  
  public static final String T_DATE = "DATE";
  
  public static final String T_DAY = "DAY";
  
  static final String T_DEALLOCATE = "DEALLOCATE";
  
  public static final String T_DEC = "DEC";
  
  public static final String T_DECIMAL = "DECIMAL";
  
  static final String T_DECLARE = "DECLARE";
  
  public static final String T_DEFAULT = "DEFAULT";
  
  public static final String T_DELETE = "DELETE";
  
  static final String T_DENSE_RANK = "DENSE_RANK";
  
  static final String T_DEREF = "DEREF";
  
  static final String T_DESCRIBE = "DESCRIBE";
  
  static final String T_DETERMINISTIC = "DETERMINISTIC";
  
  static final String T_DISCONNECT = "DISCONNECT";
  
  static final String T_DISTINCT = "DISTINCT";
  
  public static final String T_DO = "DO";
  
  public static final String T_DOUBLE = "DOUBLE";
  
  static final String T_DROP = "DROP";
  
  static final String T_DYNAMIC = "DYNAMIC";
  
  static final String T_EACH = "EACH";
  
  static final String T_ELEMENT = "ELEMENT";
  
  static final String T_ELSE = "ELSE";
  
  static final String T_ELSEIF = "ELSEIF";
  
  static final String T_END = "END";
  
  static final String T_END_EXEC = "END_EXEC";
  
  static final String T_ESCAPE = "ESCAPE";
  
  static final String T_EVERY = "EVERY";
  
  static final String T_EXCEPT = "EXCEPT";
  
  static final String T_EXEC = "EXEC";
  
  public static final String T_EXECUTE = "EXECUTE";
  
  static final String T_EXISTS = "EXISTS";
  
  static final String T_EXP = "EXP";
  
  public static final String T_EXTERNAL = "EXTERNAL";
  
  static final String T_EXTRACT = "EXTRACT";
  
  public static final String T_FALSE = "FALSE";
  
  static final String T_FETCH = "FETCH";
  
  static final String T_FILTER = "FILTER";
  
  static final String T_FIRST_VALUE = "FIRST_VALUE";
  
  public static final String T_FLOAT = "FLOAT";
  
  static final String T_FLOOR = "FLOOR";
  
  public static final String T_FOR = "FOR";
  
  public static final String T_FOREIGN = "FOREIGN";
  
  static final String T_FREE = "FREE";
  
  public static final String T_FROM = "FROM";
  
  static final String T_FULL = "FULL";
  
  public static final String T_FUNCTION = "FUNCTION";
  
  static final String T_FUSION = "FUSION";
  
  public static final String T_GET = "GET";
  
  static final String T_GLOBAL = "GLOBAL";
  
  public static final String T_GRANT = "GRANT";
  
  static final String T_GROUP = "GROUP";
  
  static final String T_GROUPING = "GROUPING";
  
  static final String T_HANDLER = "HANDLER";
  
  static final String T_HAVING = "HAVING";
  
  static final String T_HOLD = "HOLD";
  
  public static final String T_HOUR = "HOUR";
  
  static final String T_IDENTITY = "IDENTITY";
  
  static final String T_IF = "IF";
  
  static final String T_IMPORT = "IMPORT";
  
  static final String T_IN = "IN";
  
  static final String T_INDICATOR = "INDICATOR";
  
  static final String T_INNER = "INNER";
  
  static final String T_INOUT = "INOUT";
  
  static final String T_INSENSITIVE = "INSENSITIVE";
  
  public static final String T_INSERT = "INSERT";
  
  public static final String T_INT = "INT";
  
  public static final String T_INTEGER = "INTEGER";
  
  static final String T_INTERSECT = "INTERSECT";
  
  static final String T_INTERSECTION = "INTERSECTION";
  
  public static final String T_INTERVAL = "INTERVAL";
  
  static final String T_INTO = "INTO";
  
  static final String T_ITERATE = "ITERATE";
  
  public static final String T_IS = "IS";
  
  static final String T_JAR = "JAR";
  
  static final String T_JOIN = "JOIN";
  
  static final String T_LAG = "LAG";
  
  public static final String T_LANGUAGE = "LANGUAGE";
  
  static final String T_LARGE = "LARGE";
  
  static final String T_LAST_VALUE = "LAST_VALUE";
  
  static final String T_LATERAL = "LATERAL";
  
  static final String T_LEAD = "LEAD";
  
  static final String T_LEADING = "LEADING";
  
  static final String T_LEAVE = "LEAVE";
  
  static final String T_LEFT = "LEFT";
  
  static final String T_LIKE = "LIKE";
  
  static final String T_LIKE_REGX = "LIKE_REGX";
  
  static final String T_LN = "LN";
  
  public static final String T_LOCAL = "LOCAL";
  
  static final String T_LOCALTIME = "LOCALTIME";
  
  static final String T_LOCALTIMESTAMP = "LOCALTIMESTAMP";
  
  public static final String T_LOOP = "LOOP";
  
  static final String T_LOWER = "LOWER";
  
  static final String T_MATCH = "MATCH";
  
  static final String T_MAX = "MAX";
  
  static final String T_MAX_CARDINALITY = "MAX_CARDINALITY";
  
  static final String T_MEMBER = "MEMBER";
  
  static final String T_MERGE = "MERGE";
  
  static final String T_METHOD = "METHOD";
  
  static final String T_MIN = "MIN";
  
  public static final String T_MINUTE = "MINUTE";
  
  static final String T_MOD = "MOD";
  
  static final String T_MODIFIES = "MODIFIES";
  
  static final String T_MODULE = "MODULE";
  
  public static final String T_MONTH = "MONTH";
  
  public static final String T_MULTISET = "MULTISET";
  
  static final String T_NATIONAL = "NATIONAL";
  
  static final String T_NATURAL = "NATURAL";
  
  static final String T_NCHAR = "NCHAR";
  
  static final String T_NCLOB = "NCLOB";
  
  static final String T_NEW = "NEW";
  
  public static final String T_NO = "NO";
  
  public static final String T_NONE = "NONE";
  
  static final String T_NORMALIZE = "NORMALIZE";
  
  static final String T_NOT = "NOT";
  
  static final String T_NTH_VALUE = "NTH_VALUE";
  
  static final String T_NTILE = "NTILE";
  
  public static final String T_NULL = "NULL";
  
  public static final String T_NULLIF = "NULLIF";
  
  public static final String T_NUMERIC = "NUMERIC";
  
  static final String T_OCCURRENCES_REGEX = "OCCURRENCES_REGEX";
  
  static final String T_OCTET_LENGTH = "OCTET_LENGTH";
  
  static final String T_OF = "OF";
  
  static final String T_OFFSET = "OFFSET";
  
  static final String T_OLD = "OLD";
  
  public static final String T_ON = "ON";
  
  public static final String T_ONLY = "ONLY";
  
  static final String T_OPEN = "OPEN";
  
  static final String T_OR = "OR";
  
  public static final String T_ORDER = "ORDER";
  
  static final String T_OUT = "OUT";
  
  static final String T_OUTER = "OUTER";
  
  static final String T_OVER = "OVER";
  
  static final String T_OVERLAPS = "OVERLAPS";
  
  static final String T_OVERLAY = "OVERLAY";
  
  static final String T_PARAMETER = "PARAMETER";
  
  static final String T_PARTITION = "PARTITION";
  
  static final String T_PERCENT_RANK = "PERCENT_RANK";
  
  static final String T_PERCENTILE_CONT = "PERCENTILE_CONT";
  
  static final String T_PERCENTILE_DISC = "PERCENTILE_DISC";
  
  static final String T_POSITION = "POSITION";
  
  static final String T_POSITION_REGEX = "POSITION_REGEX";
  
  static final String T_POWER = "POWER";
  
  static final String T_PRECISION = "PRECISION";
  
  static final String T_PREPARE = "PREPARE";
  
  static final String T_PRIMARY = "PRIMARY";
  
  public static final String T_PROCEDURE = "PROCEDURE";
  
  static final String T_RANGE = "RANGE";
  
  static final String T_RANK = "RANK";
  
  static final String T_READS = "READS";
  
  public static final String T_REAL = "REAL";
  
  static final String T_RECURSIVE = "RECURSIVE";
  
  static final String T_REF = "REF";
  
  public static final String T_REFERENCES = "REFERENCES";
  
  static final String T_REFERENCING = "REFERENCING";
  
  static final String T_REGR_AVGX = "REGR_AVGX";
  
  static final String T_REGR_AVGY = "REGR_AVGY";
  
  static final String T_REGR_COUNT = "REGR_COUNT";
  
  static final String T_REGR_INTERCEPT = "REGR_INTERCEPT";
  
  static final String T_REGR_R2 = "REGR_R2";
  
  static final String T_REGR_SLOPE = "REGR_SLOPE";
  
  static final String T_REGR_SXX = "REGR_SXX";
  
  static final String T_REGR_SXY = "REGR_SXY";
  
  static final String T_REGR_SYY = "REGR_SYY";
  
  static final String T_RELEASE = "RELEASE";
  
  static final String T_REPEAT = "REPEAT";
  
  static final String T_RESIGNAL = "RESIGNAL";
  
  public static final String T_RESULT = "RESULT";
  
  static final String T_RETURN = "RETURN";
  
  static final String T_RETURNS = "RETURNS";
  
  static final String T_REVOKE = "REVOKE";
  
  static final String T_RIGHT = "RIGHT";
  
  public static final String T_ROLLBACK = "ROLLBACK";
  
  static final String T_ROLLUP = "ROLLUP";
  
  public static final String T_ROW = "ROW";
  
  static final String T_ROW_NUMBER = "ROW_NUMBER";
  
  public static final String T_ROWS = "ROWS";
  
  static final String T_SAVEPOINT = "SAVEPOINT";
  
  static final String T_SCOPE = "SCOPE";
  
  static final String T_SCROLL = "SCROLL";
  
  public static final String T_SEARCH = "SEARCH";
  
  public static final String T_SECOND = "SECOND";
  
  public static final String T_SELECT = "SELECT";
  
  static final String T_SENSITIVE = "SENSITIVE";
  
  static final String T_SESSION_USER = "SESSION_USER";
  
  public static final String T_SET = "SET";
  
  static final String T_SIGNAL = "SIGNAL";
  
  static final String T_SIMILAR = "SIMILAR";
  
  public static final String T_SMALLINT = "SMALLINT";
  
  static final String T_SOME = "SOME";
  
  public static final String T_SPECIFIC = "SPECIFIC";
  
  static final String T_SPECIFICTYPE = "SPECIFICTYPE";
  
  public static final String T_SQL = "SQL";
  
  static final String T_SQLEXCEPTION = "SQLEXCEPTION";
  
  static final String T_SQLSTATE = "SQLSTATE";
  
  static final String T_SQLWARNING = "SQLWARNING";
  
  static final String T_SQRT = "SQRT";
  
  static final String T_START = "START";
  
  static final String T_STATIC = "STATIC";
  
  static final String T_STDDEV_POP = "STDDEV_POP";
  
  static final String T_STDDEV_SAMP = "STDDEV_SAMP";
  
  static final String T_SUBMULTISET = "SUBMULTISET";
  
  static final String T_SUBSTRING = "SUBSTRING";
  
  static final String T_SUBSTRING_REGEX = "SUBSTRING_REGEX";
  
  static final String T_SUM = "SUM";
  
  static final String T_SYMMETRIC = "SYMMETRIC";
  
  static final String T_SYSTEM = "SYSTEM";
  
  static final String T_SYSTEM_USER = "SYSTEM_USER";
  
  public static final String T_TABLE = "TABLE";
  
  static final String T_TABLESAMPLE = "TABLESAMPLE";
  
  static final String T_THEN = "THEN";
  
  public static final String T_TIME = "TIME";
  
  public static final String T_TIMESTAMP = "TIMESTAMP";
  
  public static final String T_TIMEZONE_HOUR = "TIMEZONE_HOUR";
  
  public static final String T_TIMEZONE_MINUTE = "TIMEZONE_MINUTE";
  
  public static final String T_TO = "TO";
  
  static final String T_TRAILING = "TRAILING";
  
  public static final String T_TRANSLATE = "TRANSLATE";
  
  static final String T_TRANSLATE_REGEX = "TRANSLATE_REGEX";
  
  static final String T_TRANSLATION = "TRANSLATION";
  
  static final String T_TREAT = "TREAT";
  
  public static final String T_TRIGGER = "TRIGGER";
  
  static final String T_TRIM = "TRIM";
  
  static final String T_TRIM_ARRAY = "TRIM_ARRAY";
  
  public static final String T_TRUE = "TRUE";
  
  public static final String T_TRUNCATE = "TRUNCATE";
  
  static final String T_UESCAPE = "UESCAPE";
  
  static final String T_UNION = "UNION";
  
  public static final String T_UNIQUE = "UNIQUE";
  
  public static final String T_UNKNOWN = "UNKNOWN";
  
  static final String T_UNNEST = "UNNEST";
  
  static final String T_UNTIL = "UNTIL";
  
  public static final String T_UPDATE = "UPDATE";
  
  static final String T_UPPER = "UPPER";
  
  public static final String T_USER = "USER";
  
  public static final String T_USING = "USING";
  
  static final String T_VALUE = "VALUE";
  
  static final String T_VALUES = "VALUES";
  
  static final String T_VAR_POP = "VAR_POP";
  
  static final String T_VAR_SAMP = "VAR_SAMP";
  
  public static final String T_VARBINARY = "VARBINARY";
  
  public static final String T_VARCHAR = "VARCHAR";
  
  static final String T_VARYING = "VARYING";
  
  static final String T_WHEN = "WHEN";
  
  static final String T_WHENEVER = "WHENEVER";
  
  static final String T_WHERE = "WHERE";
  
  public static final String T_WHILE = "WHILE";
  
  static final String T_WIDTH_BUCKET = "WIDTH_BUCKET";
  
  static final String T_WINDOW = "WINDOW";
  
  public static final String T_WITH = "WITH";
  
  static final String T_WITHIN = "WITHIN";
  
  static final String T_WITHOUT = "WITHOUT";
  
  public static final String T_YEAR = "YEAR";
  
  static final String T_ASTERISK = "*";
  
  public static final String T_COMMA = ",";
  
  static final String T_CIRCUMFLEX = "^";
  
  public static final String T_CLOSEBRACKET = ")";
  
  static final String T_COLON = ":";
  
  static final String T_CONCAT = "||";
  
  public static final String T_DIVIDE = "/";
  
  static final String T_EQUALS = "=";
  
  static final String T_GREATER = ">";
  
  static final String T_GREATER_EQUALS = ">=";
  
  public static final String T_LEFTBRACKET = "[";
  
  static final String T_LESS = "<";
  
  static final String T_LESS_EQUALS = "<=";
  
  static final String T_PERCENT = "%";
  
  static final String T_PLUS = "+";
  
  static final String T_MINUS = "-";
  
  static final String T_NOT_EQUALS = "<>";
  
  static final String T_NOT_EQUALS_ALT = "!=";
  
  public static final String T_OPENBRACKET = "(";
  
  static final String T_QUESTION = "?";
  
  public static final String T_RIGHTBRACKET = "]";
  
  static final String T_SEMICOLON = ";";
  
  static final String T_DOUBLE_COLON = "::";
  
  static final String T_A = "A";
  
  static final String T_ABSOLUTE = "ABSOLUTE";
  
  static final String T_ACTION = "ACTION";
  
  static final String T_ADA = "ADA";
  
  static final String T_ADD = "ADD";
  
  static final String T_ADMIN = "ADMIN";
  
  static final String T_AFTER = "AFTER";
  
  static final String T_ALWAYS = "ALWAYS";
  
  static final String T_ASC = "ASC";
  
  static final String T_ASSERTION = "ASSERTION";
  
  static final String T_ASSIGNMENT = "ASSIGNMENT";
  
  static final String T_ATTRIBUTE = "ATTRIBUTE";
  
  static final String T_ATTRIBUTES = "ATTRIBUTES";
  
  static final String T_BEFORE = "BEFORE";
  
  static final String T_BERNOULLI = "BERNOULLI";
  
  public static final String T_BIT = "BIT";
  
  static final String T_BITLENGTH = "BITLENGTH";
  
  static final String T_BREADTH = "BREADTH";
  
  static final String T_C = "C";
  
  static final String T_CASCADE = "CASCADE";
  
  public static final String T_CATALOG = "CATALOG";
  
  public static final String T_CATALOG_NAME = "CATALOG_NAME";
  
  static final String T_CHAIN = "CHAIN";
  
  static final String T_CHARACTER_SET_CATALOG = "CHARACTER_SET_CATALOG";
  
  static final String T_CHARACTER_SET_NAME = "CHARACTER_SET_NAME";
  
  static final String T_CHARACTER_SET_SCHEMA = "CHARACTER_SET_SCHEMA";
  
  static final String T_CHARACTERISTICS = "CHARACTERISTICS";
  
  static final String T_CHARACTERS = "CHARACTERS";
  
  static final String T_CLASS_ORIGIN = "CLASS_ORIGIN";
  
  static final String T_COBOL = "COBOL";
  
  public static final String T_COLLATION = "COLLATION";
  
  static final String T_COLLATION_CATALOG = "COLLATION_CATALOG";
  
  static final String T_COLLATION_NAME = "COLLATION_NAME";
  
  static final String T_COLLATION_SCHEMA = "COLLATION_SCHEMA";
  
  static final String T_COLUMN_NAME = "COLUMN_NAME";
  
  static final String T_COMMAND_FUNCTION = "COMMAND_FUNCTION";
  
  static final String T_COMMAND_FUNCTION_CODE = "COMMAND_FUNCTION_CODE";
  
  public static final String T_COMMITTED = "COMMITTED";
  
  static final String T_COMPARABLE = "COMPARABLE";
  
  static final String T_CONDITION_IDENTIFIER = "CONDIITON_IDENTIFIER";
  
  static final String T_CONDITION_NUMBER = "CONDITION_NUMBER";
  
  static final String T_CONNECTION_NAME = "CONNECTION_NAME";
  
  static final String T_CONSTRAINT_CATALOG = "CONSTRAINT_CATALOG";
  
  static final String T_CONSTRAINT_NAME = "CONSTRAINT_NAME";
  
  static final String T_CONSTRAINT_SCHEMA = "CONSTRAINT_SCHEMA";
  
  static final String T_CONSTRAINTS = "CONSTRAINTS";
  
  static final String T_CONSTRUCTOR = "CONSTRUCTOR";
  
  static final String T_CONTAINS = "CONTAINS";
  
  static final String T_CONTINUE = "CONTINUE";
  
  static final String T_CURRENT_COLLATION = "CURRENT_COLLATION";
  
  static final String T_CURSOR_NAME = "CURSOR_NAME";
  
  public static final String T_DATA = "DATA";
  
  static final String T_DATETIME_INTERVAL_CODE = "DATETIME_INTERVAL_CODE";
  
  static final String T_DATETIME_INTERVAL_PRECISION = "DATETIME_INTERVAL_PRECISION";
  
  public static final String T_DEFAULTS = "DEFAULTS";
  
  static final String T_DEFERRABLE = "DEFERRABLE";
  
  static final String T_DEFERRED = "DEFERRED";
  
  static final String T_DEFINED = "DEFINED";
  
  static final String T_DEFINER = "DEFINER";
  
  static final String T_DEGREE = "DEGREE";
  
  static final String T_DEPTH = "DEPTH";
  
  static final String T_DERIVED = "DERIVED";
  
  static final String T_DESC = "DESC";
  
  static final String T_DESCRIPTOR = "DESCRIPTOR";
  
  static final String T_DIAGNOSTICS = "DIAGNOSTICS";
  
  static final String T_DISPATCH = "DISPATCH";
  
  public static final String T_DOMAIN = "DOMAIN";
  
  static final String T_DYNAMIC_FUNCTION = "DYNAMIC_FUNCTION";
  
  static final String T_DYNAMIC_FUNCTION_CODE = "DYNAMIC_FUNCTION_CODE";
  
  static final String T_EXCEPTION = "EXCEPTION";
  
  static final String T_EXCLUDE = "EXCLUDE";
  
  static final String T_EXCLUDING = "EXCLUDING";
  
  static final String T_EXIT = "EXIT";
  
  static final String T_FINAL = "FINAL";
  
  public static final String T_FIRST = "FIRST";
  
  static final String T_FOLLOWING = "FOLLOWING";
  
  static final String T_FORTRAN = "FORTRAN";
  
  static final String T_FOUND = "FOUND";
  
  public static final String T_G_FACTOR = "G";
  
  static final String T_GENERATED = "GENERATED";
  
  static final String T_GENERAL = "GENERAL";
  
  static final String T_GO = "GO";
  
  static final String T_GOTO = "GOTO";
  
  static final String T_GRANTED = "GRANTED";
  
  static final String T_HIERARCHY = "HIERARCHY";
  
  static final String T_IMPLEMENTATION = "IMPLEMENTATION";
  
  static final String T_INCLUDING = "INCLUDING";
  
  public static final String T_INCREMENT = "INCREMENT";
  
  static final String T_INITIALLY = "INITIALLY";
  
  static final String T_INPUT = "INPUT";
  
  static final String T_INSTANCE = "INSTANCE";
  
  static final String T_INSTANTIABLE = "INSTANTIABLE";
  
  static final String T_INSTEAD = "INSTEAD";
  
  static final String T_INTERFACE = "INTERFACE";
  
  static final String T_INVOKER = "INVOKER";
  
  public static final String T_ISOLATION = "ISOLATION";
  
  public static final String T_JAVA = "JAVA";
  
  public static final String T_K_FACTOR = "K";
  
  static final String T_KEY = "KEY";
  
  static final String T_KEY_MEMBER = "KEY_MEMBER";
  
  static final String T_KEY_TYPE = "KEY_TYPE";
  
  static final String T_LAST = "LAST";
  
  static final String T_LENGTH = "LENGTH";
  
  public static final String T_LEVEL = "LEVEL";
  
  public static final String T_LIBRARY = "LIBRARY";
  
  static final String T_LOCATOR = "LOCATOR";
  
  public static final String T_M_FACTOR = "M";
  
  static final String T_MAP = "MAP";
  
  static final String T_MATCHED = "MATCHED";
  
  static final String T_MAXVALUE = "MAXVALUE";
  
  static final String T_MESSAGE_LENGTH = "MESSAGE_LENGTH";
  
  static final String T_MESSAGE_OCTET_LENGTH = "MESSAGE_OCTET_LENGTH";
  
  static final String T_MESSAGE_TEXT = "MESSAGE_TEXT";
  
  static final String T_MINVALUE = "MINVALUE";
  
  static final String T_MORE = "MORE";
  
  static final String T_MUMPS = "MUMPS";
  
  public static final String T_NAME = "NAME";
  
  public static final String T_NAMES = "NAMES";
  
  static final String T_NESTING = "NESTING";
  
  static final String T_NEXT = "NEXT";
  
  static final String T_NORMALIZED = "NORMALIZED";
  
  static final String T_NULLABLE = "NULLABLE";
  
  public static final String T_NULLS = "NULLS";
  
  static final String T_NUMBER = "NUMBER";
  
  public static final String T_OBJECT = "OBJECT";
  
  static final String T_OCTETS = "OCTETS";
  
  static final String T_OPTION = "OPTION";
  
  static final String T_OPTIONS = "OPTIONS";
  
  static final String T_ORDERING = "ORDERING";
  
  static final String T_ORDINALITY = "ORDINALITY";
  
  static final String T_OTHERS = "OTHERS";
  
  public static final String T_OVERRIDING = "OVERRIDING";
  
  public static final String T_P_FACTOR = "P";
  
  public static final String T_PAD = "PAD";
  
  static final String T_PARAMETER_MODE = "PARAMETER_MODE";
  
  static final String T_PARAMETER_NAME = "PARAMETER_NAME";
  
  static final String T_PARAMETER_ORDINAL_POSITION = "PARAMETER_ORDINAL_POSITION";
  
  static final String T_PARAMETER_SPECIFIC_CATALOG = "PARAMETER_SPECIFIC_CATALOG";
  
  static final String T_PARAMETER_SPEC_NAME = "PARAMETER_SPECIFIC_NAME";
  
  static final String T_PARAMETER_SPEC_SCHEMA = "PARAMETER_SPECIFIC_SCHEMA";
  
  static final String T_PARTIAL = "PARTIAL";
  
  static final String T_PASCAL = "PASCAL";
  
  public static final String T_PATH = "PATH";
  
  static final String T_PLACING = "PLACING";
  
  static final String T_PLI = "PLI";
  
  static final String T_PRECEDING = "PRECEDING";
  
  static final String T_PRESERVE = "PRESERVE";
  
  static final String T_PRIOR = "PRIOR";
  
  static final String T_PRIVILEGES = "PRIVILEGES";
  
  public static final String T_PUBLIC = "PUBLIC";
  
  public static final String T_READ = "READ";
  
  static final String T_RELATIVE = "RELATIVE";
  
  static final String T_REPEATABLE = "REPEATABLE";
  
  static final String T_RESTART = "RESTART";
  
  static final String T_RESET = "RESET";
  
  static final String T_RETURNED_CARDINALITY = "RETURNED_CARDINALITY";
  
  static final String T_RETURNED_LENGTH = "RETURNED_LENGTH";
  
  static final String T_RETURNED_OCTET_LENGTH = "RETURNED_OCTET_LENGTH";
  
  static final String T_RETURNED_SQLSTATE = "RETURNED_SQLSTATE";
  
  public static final String T_ROLE = "ROLE";
  
  public static final String T_ROUTINE = "ROUTINE";
  
  static final String T_ROUTINE_CATALOG = "ROUTINE_CATALOG";
  
  static final String T_ROUTINE_NAME = "ROUTINE_NAME";
  
  static final String T_ROUTINE_SCHEMA = "ROUTINE_SCHEMA";
  
  static final String T_ROW_COUNT = "ROW_COUNT";
  
  public static final String T_SCALE = "SCALE";
  
  public static final String T_SCHEMA = "SCHEMA";
  
  static final String T_SCHEMA_NAME = "SCHEMA_NAME";
  
  static final String T_SCOPE_CATALOG = "SCOPE_CATALOG";
  
  static final String T_SCOPE_NAME = "SCOPE_NAME";
  
  static final String T_SCOPE_SCHEMA = "SCOPE_SCHEMA";
  
  static final String T_SECTION = "SECTION";
  
  static final String T_SECURITY = "SECURITY";
  
  static final String T_SELF = "SELF";
  
  public static final String T_SEQUENCE = "SEQUENCE";
  
  static final String T_SERIAL = "SERIAL";
  
  public static final String T_SERIALIZABLE = "SERIALIZABLE";
  
  public static final String T_SERVER = "SERVER";
  
  static final String T_SERVER_NAME = "SERVER_NAME";
  
  public static final String T_SESSION = "SESSION";
  
  static final String T_SETS = "SETS";
  
  static final String T_SIMPLE = "SIMPLE";
  
  public static final String T_SIZE = "SIZE";
  
  static final String T_SOURCE = "SOURCE";
  
  public static final String T_SPACE = "SPACE";
  
  static final String T_SPECIFIC_NAME = "SPECIFIC_NAME";
  
  static final String T_SQLDATA = "SQLDATA";
  
  static final String T_STACKED = "STACKED";
  
  static final String T_STATE = "STATE";
  
  static final String T_STATEMENT = "STATEMENT";
  
  static final String T_STRUCTURE = "STRUCTURE";
  
  static final String T_STYLE = "STYLE";
  
  static final String T_SUBCLASS_ORIGIN = "SUBCLASS_ORIGIN";
  
  public static final String T_T_FACTOR = "T";
  
  static final String T_TABLE_NAME = "TABLE_NAME";
  
  static final String T_TEMPORARY = "TEMPORARY";
  
  static final String T_TIES = "TIES";
  
  static final String T_TOP_LEVEL_COUNT = "TOP_LEVEL_COUNT";
  
  public static final String T_TRANSACTION = "TRANSACTION";
  
  static final String T_TRANSACTS_COMMITTED = "TRANSACTIONS_COMMITTED";
  
  static final String T_TRANSACTS_ROLLED_BACK = "TRANSACTIONS_ROLLED_BACK";
  
  static final String T_TRANSACTION_ACTIVE = "TRANSACTION_ACTIVE";
  
  static final String T_TRANSFORM = "TRANSFORM";
  
  static final String T_TRANSFORMS = "TRANSFORMS";
  
  static final String T_TRIGGER_CATALOG = "TRIGGER_CATALOG";
  
  static final String T_TRIGGER_NAME = "TRIGGER_NAME";
  
  static final String T_TRIGGER_SCHEMA = "TRIGGER_SCHEMA";
  
  public static final String T_TYPE = "TYPE";
  
  static final String T_UNBOUNDED = "UNBOUNDED";
  
  static final String T_UNCOMMITTED = "UNCOMMITTED";
  
  static final String T_UNDER = "UNDER";
  
  static final String T_UNDO = "UNDO";
  
  static final String T_UNNAMED = "UNNAMED";
  
  public static final String T_USAGE = "USAGE";
  
  static final String T_USER_DEFINED_TYPE_CATALOG = "USER_DEFINED_TYPE_CATALOG";
  
  static final String T_USER_DEFINED_TYPE_CODE = "USER_DEFINED_TYPE_CODE";
  
  static final String T_USER_DEFINED_TYPE_NAME = "USER_DEFINED_TYPE_NAME";
  
  static final String T_USER_DEFINED_TYPE_SCHEMA = "USER_DEFINED_TYPE_SCHEMA";
  
  static final String T_VIEW = "VIEW";
  
  static final String T_WORK = "WORK";
  
  public static final String T_WRAPPER = "WRAPPER";
  
  public static final String T_WRITE = "WRITE";
  
  public static final String T_ZONE = "ZONE";
  
  static final String T_ALIAS = "ALIAS";
  
  static final String T_AGGREGATE = "AGGREGATE";
  
  public static final String T_AUTHENTICATION = "AUTHENTICATION";
  
  static final String T_AUTO_INCREMENT = "AUTO_INCREMENT";
  
  static final String T_AUTOCOMMIT = "AUTOCOMMIT";
  
  public static final String T_BACKUP = "BACKUP";
  
  static final String T_BIGSERIAL = "BIGSERIAL";
  
  static final String T_BINARY_DOUBLE = "BINARY_DOUBLE";
  
  static final String T_BINARY_FLOAT = "BINARY_FLOAT";
  
  static final String T_BODY = "BODY";
  
  static final String T_BYTE = "BYTE";
  
  public static final String T_CACHE = "CACHE";
  
  static final String T_CACHED = "CACHED";
  
  static final String T_CASEWHEN = "CASEWHEN";
  
  static final String T_CHECKPOINT = "CHECKPOINT";
  
  static final String T_CITEXT = "CITEXT";
  
  static final String T_CLASS = "CLASS";
  
  static final String T_CLUSTERED = "CLUSTERED";
  
  static final String T_COMMENT = "COMMENT";
  
  static final String T_COMPACT = "COMPACT";
  
  public static final String T_COMPRESSED = "COMPRESSED";
  
  public static final String T_CONFLICT = "CONFLICT";
  
  public static final String T_CONTROL = "CONTROL";
  
  static final String T_CURDATE = "CURDATE";
  
  static final String T_CURRVAL = "CURRVAL";
  
  static final String T_CURTIME = "CURTIME";
  
  public static final String T_DATABASE = "DATABASE";
  
  public static final String T_DATETIME = "DATETIME";
  
  public static final String T_DB2 = "DB2";
  
  public static final String T_DEADLOCK = "DEADLOCK";
  
  public static final String T_DEFRAG = "DEFRAG";
  
  public static final String T_DELAY = "DELAY";
  
  public static final String T_DIGEST = "DIGEST";
  
  static final String T_DUAL = "DUAL";
  
  static final String T_EXPLAIN = "EXPLAIN";
  
  public static final String T_EVENT = "EVENT";
  
  static final String T_FILE = "FILE";
  
  public static final String T_FILES = "FILES";
  
  static final String T_FOLD = "FOLD";
  
  static final String T_FORMAT = "FORMAT";
  
  static final String T_GROUP_CONCAT = "GROUP_CONCAT";
  
  static final String T_HEADER = "HEADER";
  
  static final String T_IFNULL = "IFNULL";
  
  public static final String T_IGNORECASE = "IGNORECASE";
  
  static final String T_IMMEDIATELY = "IMMEDIATELY";
  
  public static final String T_INDEX = "INDEX";
  
  public static final String T_INDEXER = "INDEXER";
  
  public static final String T_INITIAL = "INITIAL";
  
  public static final String T_INTEGRITY = "INTEGRITY";
  
  static final String T_IS_AUTOCOMMIT = "IS_AUTOCOMMIT";
  
  static final String T_IS_READONLY_DATABASE = "IS_READONLY_DATABASE";
  
  static final String T_IS_READONLY_DATABASE_FILES = "IS_READONLY_DATABASE_FILES";
  
  static final String T_IS_READONLY_SESSION = "IS_READONLY_SESSION";
  
  static final String T_ISNULL = "ISNULL";
  
  static final String T_LASTVAL = "LASTVAL";
  
  static final String T_LIMIT = "LIMIT";
  
  public static final String T_LOB = "LOB";
  
  public static final String T_LOCK = "LOCK";
  
  public static final String T_LOCKS = "LOCKS";
  
  public static final String T_LONG = "LONG";
  
  public static final String T_LONGBLOB = "LONGBLOB";
  
  public static final String T_LONGTEXT = "LONGTEXT";
  
  public static final String T_LONGVAR = "LONGVAR";
  
  public static final String T_LONGVARBINARY = "LONGVARBINARY";
  
  public static final String T_LONGVARCHAR = "LONGVARCHAR";
  
  static final String T_MAXROWS = "MAXROWS";
  
  static final String T_MEDIAN = "MEDIAN";
  
  static final String T_MEDIUMBLOB = "MEDIUMBLOB";
  
  static final String T_MEDIUMTEXT = "MEDIUMTEXT";
  
  public static final String T_MEMORY = "MEMORY";
  
  public static final String T_MILLIS = "MILLIS";
  
  static final String T_MINUS_EXCEPT = "MINUS";
  
  public static final String T_MSS = "MSS";
  
  public static final String T_MVCC = "MVCC";
  
  public static final String T_MVLOCKS = "MVLOCKS";
  
  public static final String T_MYS = "MYS";
  
  public static final String T_NAN = "NAN";
  
  static final String T_NEXTVAL = "NEXTVAL";
  
  public static final String T_NIO = "NIO";
  
  static final String T_NOWAIT = "NOWAIT";
  
  public static final String T_NVARCHAR = "NVARCHAR";
  
  public static final String T_NVARCHAR2 = "NVARCHAR2";
  
  static final String T_NVL = "NVL";
  
  static final String T_NVL2 = "NVL2";
  
  static final String T_OCTETLENGTH = "OCTETLENGTH";
  
  static final String T_OFF = "OFF";
  
  public static final String T_OTHER = "OTHER";
  
  public static final String T_ORA = "ORA";
  
  public static final String T_PASSWORD = "PASSWORD";
  
  static final String T_PLAN = "PLAN";
  
  public static final String T_PGS = "PGS";
  
  static final String T_PREVVAL = "PREVVAL";
  
  static final String T_PROPERTY = "PROPERTY";
  
  static final String T_QUEUE = "QUEUE";
  
  static final String T_RAW = "RAW";
  
  static final String T_READONLY = "READONLY";
  
  static final String T_REFERENTIAL = "REFERENTIAL";
  
  public static final String T_REGULAR = "REGULAR";
  
  static final String T_RENAME = "RENAME";
  
  static final String T_RESTRICT = "RESTRICT";
  
  static final String T_ROWNUM = "ROWNUM";
  
  static final String T_SCRIPT = "SCRIPT";
  
  static final String T_SEPARATOR = "SEPARATOR";
  
  static final String T_BLOCKING = "BLOCKING";
  
  static final String T_SHUTDOWN = "SHUTDOWN";
  
  static final String T_SQL_TSI_DAY = "SQL_TSI_DAY";
  
  static final String T_SQL_TSI_FRAC_SECOND = "SQL_TSI_FRAC_SECOND";
  
  static final String T_SQL_TSI_MILLI_SECOND = "SQL_TSI_MILLI_SECOND";
  
  static final String T_SQL_TSI_HOUR = "SQL_TSI_HOUR";
  
  static final String T_SQL_TSI_MINUTE = "SQL_TSI_MINUTE";
  
  static final String T_SQL_TSI_MONTH = "SQL_TSI_MONTH";
  
  static final String T_SQL_TSI_QUARTER = "SQL_TSI_QUARTER";
  
  static final String T_SQL_TSI_SECOND = "SQL_TSI_SECOND";
  
  static final String T_SQL_TSI_WEEK = "SQL_TSI_WEEK";
  
  static final String T_SQL_TSI_YEAR = "SQL_TSI_YEAR";
  
  static final String T_SQL_BIGINT = "SQL_BIGINT";
  
  static final String T_SQL_BINARY = "SQL_BINARY";
  
  static final String T_SQL_BIT = "SQL_BIT";
  
  static final String T_SQL_BLOB = "SQL_BLOB";
  
  static final String T_SQL_BOOLEAN = "SQL_BOOLEAN";
  
  static final String T_SQL_CHAR = "SQL_CHAR";
  
  static final String T_SQL_CLOB = "SQL_CLOB";
  
  static final String T_SQL_DATE = "SQL_DATE";
  
  static final String T_SQL_DECIMAL = "SQL_DECIMAL";
  
  static final String T_SQL_DATALINK = "SQL_DATALINK";
  
  static final String T_SQL_DOUBLE = "SQL_DOUBLE";
  
  static final String T_SQL_FLOAT = "SQL_FLOAT";
  
  static final String T_SQL_INTEGER = "SQL_INTEGER";
  
  static final String T_SQL_LONGVARBINARY = "SQL_LONGVARBINARY";
  
  static final String T_SQL_LONGNVARCHAR = "SQL_LONGNVARCHAR";
  
  static final String T_SQL_LONGVARCHAR = "SQL_LONGVARCHAR";
  
  static final String T_SQL_NCHAR = "SQL_NCHAR";
  
  static final String T_SQL_NCLOB = "SQL_NCLOB";
  
  static final String T_SQL_NUMERIC = "SQL_NUMERIC";
  
  static final String T_SQL_NVARCHAR = "SQL_NVARCHAR";
  
  static final String T_SQL_REAL = "SQL_REAL";
  
  static final String T_SQL_ROWID = "SQL_ROWID";
  
  static final String T_SQL_SQLXML = "SQL_SQLXML";
  
  static final String T_SQL_SMALLINT = "SQL_SMALLINT";
  
  static final String T_SQL_TIME = "SQL_TIME";
  
  static final String T_SQL_TIMESTAMP = "SQL_TIMESTAMP";
  
  static final String T_SQL_TINYINT = "SQL_TINYINT";
  
  static final String T_SQL_VARBINARY = "SQL_VARBINARY";
  
  static final String T_SQL_VARCHAR = "SQL_VARCHAR";
  
  public static final String T_SYNTAX = "SYNTAX";
  
  public static final String T_TDC = "TDC";
  
  public static final String T_TEMP = "TEMP";
  
  public static final String T_TEXT = "TEXT";
  
  static final String T_TIMESTAMP_WITH_ZONE = "TIMESTAMP_WITH_ZONE";
  
  static final String T_TIMESTAMPADD = "TIMESTAMPADD";
  
  static final String T_TIMESTAMPDIFF = "TIMESTAMPDIFF";
  
  public static final String T_TINYBLOB = "TINYBLOB";
  
  public static final String T_TINYINT = "TINYINT";
  
  public static final String T_TINYTEXT = "TINYTEXT";
  
  static final String T_TOP = "TOP";
  
  public static final String T_TTI = "TTI";
  
  public static final String T_TYPES = "TYPES";
  
  public static final String T_VARCHAR_IGNORECASE = "VARCHAR_IGNORECASE";
  
  public static final String T_VARCHAR2 = "VARCHAR2";
  
  public static final String T_UTF16 = "UTF16";
  
  static final String T_WRITE_DELAY = "WRITE_DELAY";
  
  public static final String T_YES = "YES";
  
  public static final String T_DAY_NAME = "DAY_NAME";
  
  public static final String T_MONTH_NAME = "MONTH_NAME";
  
  public static final String T_QUARTER = "QUARTER";
  
  public static final String T_DAY_OF_WEEK = "DAY_OF_WEEK";
  
  public static final String T_DAY_OF_MONTH = "DAY_OF_MONTH";
  
  public static final String T_DAY_OF_YEAR = "DAY_OF_YEAR";
  
  public static final String T_WEEK_OF_YEAR = "WEEK_OF_YEAR";
  
  static final String T_DAYNAME = "DAYNAME";
  
  static final String T_MONTHNAME = "MONTHNAME";
  
  static final String T_DAYOFMONTH = "DAYOFMONTH";
  
  static final String T_DAYOFWEEK = "DAYOFWEEK";
  
  static final String T_DAYOFYEAR = "DAYOFYEAR";
  
  static final String T_WEEK = "WEEK";
  
  static final String T_DAYS = "DAYS";
  
  static final String T_ACOS = "ACOS";
  
  static final String T_ACTION_ID = "ACTION_ID";
  
  static final String T_ADD_MONTHS = "ADD_MONTHS";
  
  static final String T_ARRAY_SORT = "ARRAY_SORT";
  
  static final String T_ASCII = "ASCII";
  
  static final String T_ASIN = "ASIN";
  
  static final String T_ATAN = "ATAN";
  
  static final String T_ATAN2 = "ATAN2";
  
  static final String T_BITAND = "BITAND";
  
  static final String T_BITANDNOT = "BITANDNOT";
  
  static final String T_BITNOT = "BITNOT";
  
  static final String T_BITOR = "BITOR";
  
  static final String T_BITXOR = "BITXOR";
  
  public static final String T_CONCAT_WORD = "CONCAT";
  
  static final String T_CONCAT_WS = "CONCAT_WS";
  
  static final String T_CHR = "CHR";
  
  static final String T_COS = "COS";
  
  static final String T_COT = "COT";
  
  static final String T_CRYPT_KEY = "CRYPT_KEY";
  
  static final String T_DATABASE_NAME = "DATABASE_NAME";
  
  static final String T_DATE_ADD = "DATE_ADD";
  
  static final String T_DATE_SUB = "DATE_SUB";
  
  static final String T_DATEADD = "DATEADD";
  
  static final String T_DATEDIFF = "DATEDIFF";
  
  static final String T_DBTIMEZONE = "DBTIMEZONE";
  
  static final String T_DECODE = "DECODE";
  
  static final String T_DEGREES = "DEGREES";
  
  static final String T_DIFFERENCE = "DIFFERENCE";
  
  static final String T_DMOD = "DMOD";
  
  static final String T_FROM_TZ = "FROM_TZ";
  
  public static final String T_GC = "GC";
  
  static final String T_GREATEST = "GREATEST";
  
  static final String T_HEXTORAW = "HEXTORAW";
  
  static final String T_INSTR = "INSTR";
  
  static final String T_LCASE = "LCASE";
  
  static final String T_LEAST = "LEAST";
  
  static final String T_LOAD_FILE = "LOAD_FILE";
  
  static final String T_LOCATE = "LOCATE";
  
  public static final String T_LOG = "LOG";
  
  static final String T_LOG10 = "LOG10";
  
  static final String T_LAST_DAY = "LAST_DAY";
  
  static final String T_LPAD = "LPAD";
  
  static final String T_LTRIM = "LTRIM";
  
  static final String T_MONTHS_BETWEEN = "MONTHS_BETWEEN";
  
  static final String T_NEXT_DAY = "NEXT_DAY";
  
  static final String T_NEW_TIME = "NEW_TIME";
  
  static final String T_NOW = "NOW";
  
  static final String T_NUMTODSINTERVAL = "NUMTODSINTERVAL";
  
  static final String T_NUMTOYMINTERVAL = "NUMTOYMINTERVAL";
  
  static final String T_PI = "PI";
  
  static final String T_POSITION_ARRAY = "POSITION_ARRAY";
  
  static final String T_RADIANS = "RADIANS";
  
  static final String T_RAND = "RAND";
  
  static final String T_RAWTOHEX = "RAWTOHEX";
  
  static final String T_REGEXP_MATCHES = "REGEXP_MATCHES";
  
  static final String T_REGEXP_SUBSTRING = "REGEXP_SUBSTRING";
  
  static final String T_REGEXP_SUBSTRING_ARRAY = "REGEXP_SUBSTRING_ARRAY";
  
  static final String T_REPLACE = "REPLACE";
  
  static final String T_REVERSE = "REVERSE";
  
  static final String T_ROUND = "ROUND";
  
  static final String T_ROUNDMAGIC = "ROUNDMAGIC";
  
  static final String T_RPAD = "RPAD";
  
  static final String T_RTRIM = "RTRIM";
  
  public static final String T_SECONDS_MIDNIGHT = "SECONDS_SINCE_MIDNIGHT";
  
  static final String T_SESSIONTIMEZONE = "SESSIONTIMEZONE";
  
  static final String T_SIGN = "SIGN";
  
  static final String T_SIN = "SIN";
  
  static final String T_SORT_ARRAY = "SORT_ARRAY";
  
  static final String T_SOUNDEX = "SOUNDEX";
  
  static final String T_SUBSTR = "SUBSTR";
  
  static final String T_SYS_EXTRACT_UTC = "SYS_EXTRACT_UTC";
  
  static final String T_SYSDATE = "SYSDATE";
  
  static final String T_SYSTIMESTAMP = "SYSTIMESTAMP";
  
  static final String T_TAN = "TAN";
  
  static final String T_TO_CHAR = "TO_CHAR";
  
  static final String T_TO_DATE = "TO_DATE";
  
  static final String T_TO_DSINTERVAL = "TO_DSINTERVAL";
  
  static final String T_TO_YMINTERVAL = "TO_YMINTERVAL";
  
  static final String T_TO_NUMBER = "TO_NUMBER";
  
  static final String T_TO_TIMESTAMP = "TO_TIMESTAMP";
  
  static final String T_TO_TIMESTAMP_TZ = "TO_TIMESTAMP_TZ";
  
  static final String T_TZ_OFFSET = "TZ_OFFSET";
  
  static final String T_TRANSACTION_SIZE = "TRANSACTION_SIZE";
  
  static final String T_TRANSACTION_ID = "TRANSACTION_ID";
  
  static final String T_TRUNC = "TRUNC";
  
  static final String T_TODAY = "TODAY";
  
  static final String T_UCASE = "UCASE";
  
  static final String T_UUID = "UUID";
  
  static final String T_UNIX_MILLIS = "UNIX_MILLIS";
  
  static final String T_UNIX_TIMESTAMP = "UNIX_TIMESTAMP";
  
  static final String T_ISOLATION_LEVEL = "ISOLATION_LEVEL";
  
  static final String T_SESSION_ISOLATION_LEVEL = "SESSION_ISOLATION_LEVEL";
  
  static final String T_DATABASE_ISOLATION_LEVEL = "DATABASE_ISOLATION_LEVEL";
  
  static final String T_TRANSACTION_CONTROL = "TRANSACTION_CONTROL";
  
  static final String T_TIMEZONE = "TIMEZONE";
  
  static final String T_SESSION_TIMEZONE = "SESSION_TIMEZONE";
  
  static final String T_DATABASE_TIMEZONE = "DATABASE_TIMEZONE";
  
  static final String T_DATABASE_VERSION = "DATABASE_VERSION";
  
  static final String T_SESSION_ID = "SESSION_ID";
  
  static final String T_LOB_ID = "LOB_ID";
  
  static final String T_SEQUENCE_ARRAY = "SEQUENCE_ARRAY";
  
  public static final int ABS = 1;
  
  public static final int ALL = 2;
  
  public static final int ALLOCATE = 3;
  
  public static final int ALTER = 4;
  
  public static final int AND = 5;
  
  public static final int ANY = 6;
  
  public static final int ARE = 7;
  
  public static final int ARRAY = 8;
  
  public static final int ARRAY_AGG = 9;
  
  public static final int AS = 10;
  
  public static final int ASENSITIVE = 11;
  
  public static final int ASYMMETRIC = 12;
  
  public static final int AT = 13;
  
  public static final int ATOMIC = 14;
  
  public static final int AUTHORIZATION = 15;
  
  public static final int AVG = 16;
  
  public static final int BEGIN = 17;
  
  public static final int BETWEEN = 18;
  
  public static final int BIGINT = 19;
  
  public static final int BINARY = 20;
  
  public static final int BLOB = 21;
  
  public static final int BOOLEAN = 22;
  
  public static final int BOTH = 23;
  
  public static final int BY = 24;
  
  public static final int CALL = 25;
  
  public static final int CALLED = 26;
  
  public static final int CARDINALITY = 27;
  
  public static final int CASCADED = 28;
  
  public static final int CASE = 29;
  
  public static final int CAST = 30;
  
  public static final int CEIL = 31;
  
  public static final int CEILING = 32;
  
  public static final int CHAR = 33;
  
  public static final int CHAR_LENGTH = 34;
  
  public static final int CHARACTER = 35;
  
  public static final int CHARACTER_LENGTH = 36;
  
  public static final int CHECK = 37;
  
  public static final int CLOB = 38;
  
  public static final int CLOSE = 39;
  
  public static final int COALESCE = 40;
  
  public static final int COLLATE = 41;
  
  public static final int COLLECT = 42;
  
  public static final int COLUMN = 43;
  
  public static final int COMMIT = 44;
  
  public static final int COMPARABLE = 45;
  
  public static final int CONDITION = 46;
  
  public static final int CONNECT = 47;
  
  public static final int CONSTRAINT = 48;
  
  public static final int CONVERT = 49;
  
  public static final int CORR = 50;
  
  public static final int CORRESPONDING = 51;
  
  public static final int COUNT = 52;
  
  public static final int COVAR_POP = 53;
  
  public static final int COVAR_SAMP = 54;
  
  public static final int CREATE = 55;
  
  public static final int CROSS = 56;
  
  public static final int CUBE = 57;
  
  public static final int CUME_DIST = 58;
  
  public static final int CURRENT = 59;
  
  public static final int CURRENT_CATALOG = 60;
  
  public static final int CURRENT_DATE = 61;
  
  public static final int CURRENT_DEFAULT_TRANSFORM_GROUP = 62;
  
  public static final int CURRENT_PATH = 63;
  
  public static final int CURRENT_ROLE = 64;
  
  public static final int CURRENT_SCHEMA = 65;
  
  public static final int CURRENT_TIME = 66;
  
  public static final int CURRENT_TIMESTAMP = 67;
  
  public static final int CURRENT_TRANSFORM_GROUP_FOR_TYPE = 68;
  
  public static final int CURRENT_USER = 69;
  
  public static final int CURSOR = 70;
  
  public static final int CYCLE = 71;
  
  public static final int DATE = 72;
  
  public static final int DAY = 73;
  
  public static final int DEALLOCATE = 74;
  
  public static final int DEC = 75;
  
  public static final int DECIMAL = 76;
  
  public static final int DECLARE = 77;
  
  public static final int DEFAULT = 78;
  
  public static final int DELETE = 79;
  
  public static final int DENSE_RANK = 80;
  
  public static final int DEREF = 81;
  
  public static final int DESCRIBE = 82;
  
  public static final int DETERMINISTIC = 83;
  
  public static final int DISCONNECT = 84;
  
  public static final int DISTINCT = 85;
  
  public static final int DO = 86;
  
  public static final int DOUBLE = 87;
  
  public static final int DROP = 88;
  
  public static final int DYNAMIC = 89;
  
  public static final int EACH = 90;
  
  public static final int ELEMENT = 91;
  
  public static final int ELSE = 92;
  
  public static final int ELSEIF = 93;
  
  public static final int END = 94;
  
  public static final int END_EXEC = 95;
  
  public static final int ESCAPE = 96;
  
  public static final int EVERY = 97;
  
  public static final int EXCEPT = 98;
  
  public static final int EXEC = 99;
  
  public static final int EXECUTE = 100;
  
  public static final int EXISTS = 101;
  
  public static final int EXIT = 102;
  
  public static final int EXP = 103;
  
  public static final int EXTERNAL = 104;
  
  public static final int EXTRACT = 105;
  
  public static final int FALSE = 106;
  
  public static final int FETCH = 107;
  
  public static final int FILTER = 108;
  
  public static final int FIRST_VALUE = 109;
  
  public static final int FLOAT = 110;
  
  public static final int FLOOR = 111;
  
  public static final int FOR = 112;
  
  public static final int FOREIGN = 113;
  
  public static final int FREE = 114;
  
  public static final int FROM = 115;
  
  public static final int FULL = 116;
  
  public static final int FUNCTION = 117;
  
  public static final int FUSION = 118;
  
  public static final int GET = 119;
  
  public static final int GLOBAL = 120;
  
  public static final int GRANT = 121;
  
  public static final int GROUP = 122;
  
  public static final int GROUPING = 123;
  
  public static final int HANDLER = 124;
  
  public static final int HAVING = 125;
  
  public static final int HOLD = 126;
  
  public static final int HOUR = 127;
  
  public static final int IDENTITY = 128;
  
  public static final int IMPORT = 129;
  
  public static final int IN = 130;
  
  public static final int INDICATOR = 131;
  
  public static final int INNER = 132;
  
  public static final int INOUT = 133;
  
  public static final int INSENSITIVE = 134;
  
  public static final int INSERT = 135;
  
  public static final int INT = 136;
  
  public static final int INTEGER = 137;
  
  public static final int INTERSECT = 138;
  
  public static final int INTERSECTION = 139;
  
  public static final int INTERVAL = 140;
  
  public static final int INTO = 141;
  
  public static final int IS = 142;
  
  public static final int ITERATE = 143;
  
  public static final int JOIN = 144;
  
  public static final int LAG = 145;
  
  public static final int LANGUAGE = 146;
  
  public static final int LARGE = 147;
  
  public static final int LAST_VALUE = 148;
  
  public static final int LATERAL = 149;
  
  public static final int LEAD = 150;
  
  public static final int LEADING = 151;
  
  public static final int LEAVE = 152;
  
  public static final int LEFT = 153;
  
  public static final int LIKE = 154;
  
  public static final int LIKE_REGEX = 155;
  
  public static final int LN = 156;
  
  public static final int LOCAL = 157;
  
  public static final int LOCALTIME = 158;
  
  public static final int LOCALTIMESTAMP = 159;
  
  public static final int LOOP = 160;
  
  public static final int LOWER = 161;
  
  public static final int MATCH = 162;
  
  public static final int MAX = 163;
  
  public static final int MAX_CARDINALITY = 164;
  
  public static final int MEMBER = 165;
  
  public static final int MERGE = 166;
  
  public static final int METHOD = 167;
  
  public static final int MIN = 168;
  
  public static final int MINUTE = 169;
  
  public static final int MOD = 170;
  
  public static final int MODIFIES = 171;
  
  public static final int MODULE = 172;
  
  public static final int MONTH = 173;
  
  public static final int MULTISET = 174;
  
  public static final int NATIONAL = 175;
  
  public static final int NATURAL = 176;
  
  public static final int NCHAR = 177;
  
  public static final int NCLOB = 178;
  
  public static final int NEW = 179;
  
  public static final int NO = 180;
  
  public static final int NONE = 181;
  
  public static final int NORMALIZE = 182;
  
  public static final int NOT = 183;
  
  public static final int NTH_VALUE = 184;
  
  public static final int NTILE = 185;
  
  public static final int NULL = 186;
  
  public static final int NULLIF = 187;
  
  public static final int NUMERIC = 188;
  
  public static final int OCCURRENCES_REGEX = 189;
  
  public static final int OCTET_LENGTH = 190;
  
  public static final int OF = 191;
  
  public static final int OFFSET = 192;
  
  public static final int OLD = 193;
  
  public static final int ON = 194;
  
  public static final int ONLY = 195;
  
  public static final int OPEN = 196;
  
  public static final int OR = 197;
  
  public static final int ORDER = 198;
  
  public static final int OUT = 199;
  
  public static final int OUTER = 200;
  
  public static final int OVER = 201;
  
  public static final int OVERLAPS = 202;
  
  public static final int OVERLAY = 203;
  
  public static final int PARAMETER = 204;
  
  public static final int PARTITION = 205;
  
  public static final int PERCENT_RANK = 206;
  
  public static final int PERCENTILE_CONT = 207;
  
  public static final int PERCENTILE_DISC = 208;
  
  public static final int POSITION = 209;
  
  public static final int POSITION_REGEX = 210;
  
  public static final int POWER = 211;
  
  public static final int PRECISION = 212;
  
  public static final int PREPARE = 213;
  
  public static final int PRIMARY = 214;
  
  public static final int PROCEDURE = 215;
  
  public static final int RANGE = 216;
  
  public static final int RANK = 217;
  
  public static final int READS = 218;
  
  public static final int REAL = 219;
  
  public static final int RECURSIVE = 220;
  
  public static final int REF = 221;
  
  public static final int REFERENCES = 222;
  
  public static final int REFERENCING = 223;
  
  public static final int REGR_AVGX = 224;
  
  public static final int REGR_AVGY = 225;
  
  public static final int REGR_COUNT = 226;
  
  public static final int REGR_INTERCEPT = 227;
  
  public static final int REGR_R2 = 228;
  
  public static final int REGR_SLOPE = 229;
  
  public static final int REGR_SXX = 230;
  
  public static final int REGR_SXY = 231;
  
  public static final int REGR_SYY = 232;
  
  public static final int RELEASE = 233;
  
  public static final int REPEAT = 234;
  
  public static final int RESIGNAL = 235;
  
  public static final int RESULT = 236;
  
  public static final int RETURN = 237;
  
  public static final int RETURNS = 238;
  
  public static final int REVOKE = 239;
  
  public static final int RIGHT = 240;
  
  public static final int ROLLBACK = 241;
  
  public static final int ROLLUP = 242;
  
  public static final int ROW = 243;
  
  public static final int ROW_NUMBER = 244;
  
  public static final int ROWS = 245;
  
  public static final int SAVEPOINT = 246;
  
  public static final int SCOPE = 247;
  
  public static final int SCROLL = 248;
  
  public static final int SEARCH = 249;
  
  public static final int SECOND = 250;
  
  public static final int SELECT = 251;
  
  public static final int SENSITIVE = 252;
  
  public static final int SESSION_USER = 253;
  
  public static final int SET = 254;
  
  public static final int SIGNAL = 255;
  
  public static final int SIMILAR = 256;
  
  public static final int SMALLINT = 257;
  
  public static final int SOME = 258;
  
  public static final int SPECIFIC = 259;
  
  public static final int SPECIFICTYPE = 260;
  
  public static final int SQL = 261;
  
  public static final int SQLEXCEPTION = 262;
  
  public static final int SQLSTATE = 263;
  
  public static final int SQLWARNING = 264;
  
  public static final int SQRT = 265;
  
  public static final int STACKED = 266;
  
  public static final int START = 267;
  
  public static final int STATIC = 268;
  
  public static final int STDDEV_POP = 269;
  
  public static final int STDDEV_SAMP = 270;
  
  public static final int SUBMULTISET = 271;
  
  public static final int SUBSTRING = 272;
  
  public static final int SUBSTRING_REGEX = 273;
  
  public static final int SUM = 274;
  
  public static final int SYMMETRIC = 275;
  
  public static final int SYSTEM = 276;
  
  public static final int SYSTEM_USER = 277;
  
  public static final int TABLE = 278;
  
  public static final int TABLESAMPLE = 279;
  
  public static final int THEN = 280;
  
  public static final int TIME = 281;
  
  public static final int TIMESTAMP = 282;
  
  public static final int TIMEZONE_HOUR = 283;
  
  public static final int TIMEZONE_MINUTE = 284;
  
  public static final int TO = 285;
  
  public static final int TRAILING = 286;
  
  public static final int TRANSLATE = 287;
  
  public static final int TRANSLATE_REGEX = 288;
  
  public static final int TRANSLATION = 289;
  
  public static final int TREAT = 290;
  
  public static final int TRIGGER = 291;
  
  public static final int TRIM = 292;
  
  public static final int TRIM_ARRAY = 293;
  
  public static final int TRUE = 294;
  
  public static final int TRUNCATE = 295;
  
  public static final int UESCAPE = 296;
  
  public static final int UNDO = 297;
  
  public static final int UNION = 298;
  
  public static final int UNIQUE = 299;
  
  public static final int UNKNOWN = 300;
  
  public static final int UNNEST = 301;
  
  public static final int UNTIL = 302;
  
  public static final int UPDATE = 303;
  
  public static final int UPPER = 304;
  
  public static final int USER = 305;
  
  public static final int USING = 306;
  
  public static final int VALUE = 307;
  
  public static final int VALUES = 308;
  
  public static final int VAR_POP = 309;
  
  public static final int VAR_SAMP = 310;
  
  public static final int VARBINARY = 311;
  
  public static final int VARCHAR = 312;
  
  public static final int VARYING = 313;
  
  public static final int WHEN = 314;
  
  public static final int WHENEVER = 315;
  
  public static final int WHERE = 316;
  
  public static final int WIDTH_BUCKET = 317;
  
  public static final int WINDOW = 318;
  
  public static final int WITH = 319;
  
  public static final int WITHIN = 320;
  
  public static final int WITHOUT = 321;
  
  public static final int WHILE = 322;
  
  public static final int YEAR = 323;
  
  public static final int A = 330;
  
  public static final int ABSOLUTE = 331;
  
  public static final int ACTION = 332;
  
  public static final int ADA = 333;
  
  public static final int ADD = 334;
  
  public static final int ADMIN = 335;
  
  public static final int AFTER = 336;
  
  public static final int ALWAYS = 337;
  
  public static final int ASC = 338;
  
  public static final int ASSERTION = 339;
  
  public static final int ASSIGNMENT = 340;
  
  public static final int ATTRIBUTE = 341;
  
  public static final int ATTRIBUTES = 342;
  
  public static final int BEFORE = 343;
  
  public static final int BERNOULLI = 344;
  
  public static final int BREADTH = 345;
  
  public static final int C = 346;
  
  public static final int CASCADE = 347;
  
  public static final int CATALOG = 348;
  
  public static final int CATALOG_NAME = 349;
  
  public static final int CHAIN = 350;
  
  public static final int CHARACTER_SET_CATALOG = 351;
  
  public static final int CHARACTER_SET_NAME = 352;
  
  public static final int CHARACTER_SET_SCHEMA = 353;
  
  public static final int CHARACTERISTICS = 354;
  
  public static final int CHARACTERS = 355;
  
  public static final int CLASS_ORIGIN = 356;
  
  public static final int COBOL = 357;
  
  public static final int COLLATION = 358;
  
  public static final int COLLATION_CATALOG = 359;
  
  public static final int COLLATION_NAME = 360;
  
  public static final int COLLATION_SCHEMA = 361;
  
  public static final int COLUMN_NAME = 362;
  
  public static final int COMMAND_FUNCTION = 363;
  
  public static final int COMMAND_FUNCTION_CODE = 364;
  
  public static final int COMMITTED = 365;
  
  public static final int CONDITION_IDENTIFIER = 366;
  
  public static final int CONDITION_NUMBER = 367;
  
  public static final int CONNECTION = 368;
  
  public static final int CONNECTION_NAME = 369;
  
  public static final int CONSTRAINT_CATALOG = 370;
  
  public static final int CONSTRAINT_NAME = 371;
  
  public static final int CONSTRAINT_SCHEMA = 372;
  
  public static final int CONSTRAINTS = 373;
  
  public static final int CONSTRUCTOR = 374;
  
  public static final int CONTAINS = 375;
  
  public static final int CONTINUE = 376;
  
  public static final int CURSOR_NAME = 377;
  
  public static final int DATA = 378;
  
  public static final int DATETIME_INTERVAL_CODE = 379;
  
  public static final int DATETIME_INTERVAL_PRECISION = 380;
  
  public static final int DEFAULTS = 381;
  
  public static final int DEFERRABLE = 382;
  
  public static final int DEFERRED = 383;
  
  public static final int DEFINED = 384;
  
  public static final int DEFINER = 385;
  
  public static final int DEGREE = 386;
  
  public static final int DEPTH = 387;
  
  public static final int DERIVED = 388;
  
  public static final int DESC = 389;
  
  public static final int DESCRIPTOR = 390;
  
  public static final int DIAGNOSTICS = 391;
  
  public static final int DISPATCH = 392;
  
  public static final int DOMAIN = 393;
  
  public static final int DYNAMIC_FUNCTION = 394;
  
  public static final int DYNAMIC_FUNCTION_CODE = 395;
  
  public static final int EQUALS = 396;
  
  public static final int EXCEPTION = 397;
  
  public static final int EXCLUDE = 398;
  
  public static final int EXCLUDING = 399;
  
  public static final int FINAL = 400;
  
  public static final int FIRST = 401;
  
  public static final int FOLLOWING = 402;
  
  public static final int FORTRAN = 403;
  
  public static final int FOUND = 404;
  
  public static final int G = 405;
  
  public static final int GENERAL = 406;
  
  public static final int GENERATED = 407;
  
  public static final int GO = 408;
  
  public static final int GOTO = 409;
  
  public static final int GRANTED = 410;
  
  public static final int HIERARCHY = 411;
  
  public static final int IF = 412;
  
  public static final int IGNORE = 413;
  
  public static final int IMMEDIATE = 414;
  
  public static final int IMPLEMENTATION = 415;
  
  public static final int INCLUDING = 416;
  
  public static final int INCREMENT = 417;
  
  public static final int INITIALLY = 418;
  
  public static final int INPUT = 419;
  
  public static final int INSTANCE = 420;
  
  public static final int INSTANTIABLE = 421;
  
  public static final int INSTEAD = 422;
  
  public static final int INVOKER = 423;
  
  public static final int ISOLATION = 424;
  
  public static final int JAVA = 425;
  
  public static final int K = 426;
  
  public static final int KEY = 427;
  
  public static final int KEY_MEMBER = 428;
  
  public static final int KEY_TYPE = 429;
  
  public static final int LAST = 430;
  
  public static final int LENGTH = 431;
  
  public static final int LEVEL = 432;
  
  public static final int LIBRARY = 433;
  
  public static final int LOCATOR = 434;
  
  public static final int M = 435;
  
  public static final int MAP = 436;
  
  public static final int MATCHED = 437;
  
  public static final int MAXVALUE = 438;
  
  public static final int MESSAGE_LENGTH = 439;
  
  public static final int MESSAGE_OCTET_LENGTH = 440;
  
  public static final int MESSAGE_TEXT = 441;
  
  public static final int MINVALUE = 442;
  
  public static final int MORE = 443;
  
  public static final int MUMPS = 444;
  
  public static final int NAME = 445;
  
  public static final int NAMES = 446;
  
  public static final int NESTING = 447;
  
  public static final int NEXT = 448;
  
  public static final int NORMALIZED = 449;
  
  public static final int NULLABLE = 450;
  
  public static final int NULLS = 451;
  
  public static final int NUMBER = 452;
  
  public static final int OBJECT = 453;
  
  public static final int OCTETS = 454;
  
  public static final int OPTION = 455;
  
  public static final int OPTIONS = 456;
  
  public static final int ORDERING = 457;
  
  public static final int ORDINALITY = 458;
  
  public static final int OTHERS = 459;
  
  public static final int OUTPUT = 460;
  
  public static final int OVERRIDING = 461;
  
  public static final int P = 462;
  
  public static final int PAD = 463;
  
  public static final int PARAMETER_MODE = 464;
  
  public static final int PARAMETER_NAME = 465;
  
  public static final int PARAMETER_ORDINAL_POSITION = 466;
  
  public static final int PARAMETER_SPECIFIC_CATALOG = 467;
  
  public static final int PARAMETER_SPECIFIC_NAME = 468;
  
  public static final int PARAMETER_SPECIFIC_SCHEMA = 469;
  
  public static final int PARTIAL = 470;
  
  public static final int PASCAL = 471;
  
  public static final int PATH = 472;
  
  public static final int PLACING = 473;
  
  public static final int PLI = 474;
  
  public static final int PRECEDING = 475;
  
  public static final int PRESERVE = 476;
  
  public static final int PRIOR = 477;
  
  public static final int PRIVILEGES = 478;
  
  public static final int PUBLIC = 479;
  
  public static final int READ = 480;
  
  public static final int RELATIVE = 481;
  
  public static final int REPEATABLE = 482;
  
  public static final int RESPECT = 483;
  
  public static final int RESTART = 484;
  
  public static final int RESTRICT = 485;
  
  public static final int RETURNED_CARDINALITY = 486;
  
  public static final int RETURNED_LENGTH = 487;
  
  public static final int RETURNED_OCTET_LENGTH = 488;
  
  public static final int RETURNED_SQLSTATE = 489;
  
  public static final int ROLE = 490;
  
  public static final int ROUTINE = 491;
  
  public static final int ROUTINE_CATALOG = 492;
  
  public static final int ROUTINE_NAME = 493;
  
  public static final int ROUTINE_SCHEMA = 494;
  
  public static final int ROW_COUNT = 495;
  
  public static final int SCALE = 496;
  
  public static final int SCHEMA = 497;
  
  public static final int SCHEMA_NAME = 498;
  
  public static final int SCOPE_CATALOG = 499;
  
  public static final int SCOPE_NAME = 500;
  
  public static final int SCOPE_SCHEMA = 501;
  
  public static final int SECTION = 502;
  
  public static final int SECURITY = 503;
  
  public static final int SELF = 504;
  
  public static final int SEQUENCE = 505;
  
  public static final int SERIALIZABLE = 506;
  
  public static final int SERVER_NAME = 507;
  
  public static final int SESSION = 508;
  
  public static final int SERVER = 509;
  
  public static final int SETS = 510;
  
  public static final int SIMPLE = 511;
  
  public static final int SIZE = 512;
  
  public static final int SOURCE = 513;
  
  public static final int SPACE = 514;
  
  public static final int SPECIFIC_NAME = 515;
  
  public static final int STATE = 516;
  
  public static final int STATEMENT = 517;
  
  public static final int STRUCTURE = 518;
  
  public static final int STYLE = 519;
  
  public static final int SUBCLASS_ORIGIN = 520;
  
  public static final int T = 521;
  
  public static final int TABLE_NAME = 522;
  
  public static final int TEMPORARY = 523;
  
  public static final int TIES = 524;
  
  public static final int TOP_LEVEL_COUNT = 525;
  
  public static final int TRANSACTION = 526;
  
  public static final int TRANSACTION_ACTIVE = 527;
  
  public static final int TRANSACTIONS_COMMITTED = 528;
  
  public static final int TRANSACTIONS_ROLLED_BACK = 529;
  
  public static final int TRANSFORM = 530;
  
  public static final int TRANSFORMS = 531;
  
  public static final int TRIGGER_CATALOG = 532;
  
  public static final int TRIGGER_NAME = 533;
  
  public static final int TRIGGER_SCHEMA = 534;
  
  public static final int TYPE = 535;
  
  public static final int UNBOUNDED = 536;
  
  public static final int UNCOMMITTED = 537;
  
  public static final int UNDER = 538;
  
  public static final int UNNAMED = 539;
  
  public static final int USAGE = 540;
  
  public static final int USER_DEFINED_TYPE_CATALOG = 541;
  
  public static final int USER_DEFINED_TYPE_CODE = 542;
  
  public static final int USER_DEFINED_TYPE_NAME = 543;
  
  public static final int USER_DEFINED_TYPE_SCHEMA = 544;
  
  public static final int VIEW = 545;
  
  public static final int WORK = 546;
  
  public static final int WRITE = 547;
  
  public static final int WRAPPER = 548;
  
  public static final int ZONE = 549;
  
  static final int ALIAS = 558;
  
  static final int AGGREGATE = 559;
  
  static final int AUTOCOMMIT = 560;
  
  static final int AUTHENTICATION = 561;
  
  static final int BACKUP = 562;
  
  static final int BINARY_FLOAT = 563;
  
  static final int BINARY_DOUBLE = 564;
  
  static final int BIT = 565;
  
  static final int BLOCKING = 566;
  
  static final int BODY = 567;
  
  static final int BYTE = 568;
  
  static final int CACHE = 569;
  
  static final int CACHED = 570;
  
  static final int CASEWHEN = 571;
  
  static final int CHECKPOINT = 572;
  
  static final int CITEXT = 573;
  
  static final int CLASS = 574;
  
  static final int CLUSTERED = 575;
  
  static final int COMMENT = 576;
  
  static final int COMPACT = 577;
  
  static final int COMPRESSED = 578;
  
  static final int CONFLICT = 579;
  
  static final int CONTROL = 580;
  
  static final int CURRVAL = 581;
  
  static final int DATABASE = 582;
  
  static final int DEADLOCK = 583;
  
  static final int DEFRAG = 584;
  
  static final int DELAY = 585;
  
  static final int DIGEST = 586;
  
  static final int EVENT = 587;
  
  static final int EXPLAIN = 588;
  
  static final int FILE = 589;
  
  static final int FILES = 590;
  
  static final int FORMAT = 591;
  
  static final int GC = 592;
  
  static final int HEADER = 593;
  
  static final int IGNORECASE = 594;
  
  static final int IMMEDIATELY = 595;
  
  static final int INTEGRITY = 596;
  
  static final int INDEX = 597;
  
  static final int INITIAL = 598;
  
  static final int LASTVAL = 599;
  
  static final int LIMIT = 600;
  
  static final int LOCK = 601;
  
  static final int LOCKS = 602;
  
  static final int LONG = 603;
  
  static final int LONGVAR = 604;
  
  static final int MAXROWS = 605;
  
  static final int MEDIAN = 606;
  
  static final int MEMORY = 607;
  
  static final int MILLIS = 608;
  
  static final int MINUS_EXCEPT = 609;
  
  static final int NAN = 610;
  
  static final int NEXTVAL = 611;
  
  static final int NVARCHAR2 = 612;
  
  static final int NVL2 = 613;
  
  static final int OFF = 614;
  
  static final int PASSWORD = 615;
  
  static final int PLAN = 616;
  
  static final int PREVVAL = 617;
  
  static final int PROPERTY = 618;
  
  static final int RAW = 619;
  
  static final int READONLY = 620;
  
  static final int REFERENTIAL = 621;
  
  static final int REGULAR = 622;
  
  static final int RENAME = 623;
  
  static final int RESET = 624;
  
  static final int ROWNUM = 625;
  
  static final int SCRIPT = 626;
  
  static final int SEPARATOR = 627;
  
  static final int SHUTDOWN = 628;
  
  static final int SYNTAX = 629;
  
  static final int TDC = 630;
  
  static final int TEMP = 631;
  
  static final int TEXT = 632;
  
  static final int TTI = 633;
  
  static final int TYPES = 634;
  
  static final int VARCHAR2 = 635;
  
  static final int WRITE_DELAY = 636;
  
  static final int INDEXER = 637;
  
  static final int ACOS = 640;
  
  static final int ACTION_ID = 641;
  
  static final int ARRAY_SORT = 642;
  
  static final int ASCII = 643;
  
  static final int ADD_MONTHS = 644;
  
  static final int ASIN = 645;
  
  static final int ATAN = 646;
  
  static final int ATAN2 = 647;
  
  static final int BIT_LENGTH = 648;
  
  static final int BITAND = 649;
  
  static final int BITANDNOT = 650;
  
  static final int BITLENGTH = 651;
  
  static final int BITNOT = 652;
  
  static final int BITOR = 653;
  
  static final int BITXOR = 654;
  
  static final int CHR = 655;
  
  static final int CONCAT_WORD = 656;
  
  static final int CONCAT_WS = 657;
  
  static final int COS = 658;
  
  static final int COT = 659;
  
  static final int CRYPT_KEY = 660;
  
  static final int CURDATE = 661;
  
  static final int CURTIME = 662;
  
  static final int DATABASE_ISOLATION_LEVEL = 663;
  
  static final int DATABASE_NAME = 664;
  
  static final int DATABASE_TIMEZONE = 665;
  
  static final int DATABASE_VERSION = 666;
  
  static final int DATE_ADD = 667;
  
  static final int DATE_SUB = 668;
  
  static final int DATEADD = 669;
  
  static final int DATEDIFF = 670;
  
  public static final int DAY_NAME = 671;
  
  public static final int DAY_OF_MONTH = 672;
  
  public static final int DAY_OF_WEEK = 673;
  
  public static final int DAY_OF_YEAR = 674;
  
  static final int DAYNAME = 675;
  
  static final int DAYOFMONTH = 676;
  
  static final int DAYOFWEEK = 677;
  
  static final int DAYOFYEAR = 678;
  
  static final int DAYS = 679;
  
  static final int DBTIMEZONE = 680;
  
  static final int DECODE = 681;
  
  static final int DEGREES = 682;
  
  static final int DIFFERENCE = 683;
  
  static final int DMOD = 684;
  
  static final int FROM_TZ = 685;
  
  static final int HEXTORAW = 686;
  
  static final int GREATEST = 687;
  
  static final int GROUP_CONCAT = 688;
  
  static final int IFNULL = 689;
  
  static final int INSTR = 690;
  
  static final int IS_AUTOCOMMIT = 691;
  
  static final int IS_READONLY_DATABASE = 692;
  
  static final int IS_READONLY_DATABASE_FILES = 693;
  
  static final int IS_READONLY_SESSION = 694;
  
  static final int ISOLATION_LEVEL = 695;
  
  static final int ISNULL = 696;
  
  static final int LAST_DAY = 697;
  
  static final int LCASE = 698;
  
  static final int LEAST = 699;
  
  static final int LOAD_FILE = 700;
  
  static final int LOCATE = 701;
  
  static final int LOB = 702;
  
  static final int LOG = 703;
  
  static final int LOG10 = 704;
  
  static final int LPAD = 705;
  
  static final int LTRIM = 706;
  
  static final int LOB_ID = 707;
  
  public static final int MONTH_NAME = 708;
  
  static final int MONTHNAME = 709;
  
  static final int MONTHS_BETWEEN = 710;
  
  static final int MVCC = 711;
  
  static final int MVLOCKS = 712;
  
  static final int NEW_TIME = 713;
  
  static final int NEXT_DAY = 714;
  
  static final int NIO = 715;
  
  static final int NOW = 716;
  
  static final int NUMTODSINTERVAL = 717;
  
  static final int NUMTOYMINTERVAL = 718;
  
  static final int OCTETLENGTH = 719;
  
  static final int PI = 720;
  
  static final int POSITION_ARRAY = 721;
  
  public static final int QUARTER = 722;
  
  static final int RADIANS = 723;
  
  static final int RAND = 724;
  
  static final int RAWTOHEX = 725;
  
  static final int REGEXP_MATCHES = 726;
  
  static final int REGEXP_SUBSTRING = 727;
  
  static final int REGEXP_SUBSTRING_ARRAY = 728;
  
  static final int REPLACE = 729;
  
  static final int REVERSE = 730;
  
  static final int ROUND = 731;
  
  static final int ROUNDMAGIC = 732;
  
  static final int RTRIM = 733;
  
  static final int RPAD = 734;
  
  public static final int SECONDS_MIDNIGHT = 735;
  
  static final int SEQUENCE_ARRAY = 736;
  
  static final int SESSION_ID = 737;
  
  static final int SESSION_ISOLATION_LEVEL = 738;
  
  static final int SESSION_TIMEZONE = 739;
  
  static final int SESSIONTIMEZONE = 740;
  
  static final int SIGN = 741;
  
  static final int SIN = 742;
  
  static final int SORT_ARRAY = 743;
  
  static final int SOUNDEX = 744;
  
  static final int SPACE_WORD = 755;
  
  static final int SUBSTR = 756;
  
  static final int SYS_EXTRACT_UTC = 757;
  
  static final int SYSDATE = 758;
  
  static final int SYSTIMESTAMP = 759;
  
  static final int TAN = 760;
  
  static final int TIMESTAMP_WITH_ZONE = 761;
  
  static final int TIMESTAMPADD = 762;
  
  static final int TIMESTAMPDIFF = 763;
  
  static final int TIMEZONE = 764;
  
  static final int TO_CHAR = 765;
  
  static final int TO_DATE = 766;
  
  static final int TO_DSINTERVAL = 767;
  
  static final int TO_YMINTERVAL = 768;
  
  static final int TO_NUMBER = 769;
  
  static final int TO_TIMESTAMP = 770;
  
  static final int TO_TIMESTAMP_TZ = 771;
  
  static final int TODAY = 772;
  
  static final int TOP = 773;
  
  static final int TRANSACTION_CONTROL = 774;
  
  static final int TRANSACTION_ID = 775;
  
  static final int TRANSACTION_SIZE = 776;
  
  static final int TRUNC = 777;
  
  static final int TZ_OFFSET = 778;
  
  static final int UCASE = 779;
  
  static final int UNIX_MILLIS = 780;
  
  static final int UNIX_TIMESTAMP = 781;
  
  static final int UUID = 782;
  
  static final int WEEK = 790;
  
  public static final int WEEK_OF_YEAR = 791;
  
  static final int LONGBLOB = 792;
  
  static final int LONGTEXT = 793;
  
  static final int MEDIUMBLOB = 794;
  
  static final int MEDIUMTEXT = 795;
  
  static final int TINYBLOB = 796;
  
  static final int TINYTEXT = 797;
  
  static final int ASTERISK = 801;
  
  static final int CLOSEBRACKET = 802;
  
  static final int COLON = 803;
  
  static final int COMMA = 804;
  
  static final int CONCAT = 805;
  
  static final int DIVIDE = 806;
  
  static final int DOUBLE_COLON_OP = 807;
  
  static final int DOUBLE_PERIOD_OP = 808;
  
  static final int GREATER = 809;
  
  static final int GREATER_EQUALS = 810;
  
  static final int LEFTBRACKET = 811;
  
  static final int LESS = 812;
  
  static final int LESS_EQUALS = 813;
  
  public static final int MINUS = 814;
  
  static final int NOT_EQUALS = 815;
  
  static final int OPENBRACKET = 816;
  
  static final int PLUS = 817;
  
  static final int QUESTION = 818;
  
  static final int RIGHT_ARROW_OP = 819;
  
  static final int RIGHTBRACKET = 820;
  
  static final int SEMICOLON = 821;
  
  public static final int SQL_BIGINT = 822;
  
  public static final int SQL_BINARY = 823;
  
  public static final int SQL_BIT = 824;
  
  public static final int SQL_BLOB = 825;
  
  public static final int SQL_BOOLEAN = 826;
  
  public static final int SQL_CHAR = 827;
  
  public static final int SQL_CLOB = 828;
  
  public static final int SQL_DATE = 829;
  
  public static final int SQL_DECIMAL = 831;
  
  public static final int SQL_DATALINK = 832;
  
  public static final int SQL_DOUBLE = 833;
  
  public static final int SQL_FLOAT = 834;
  
  public static final int SQL_INTEGER = 835;
  
  public static final int SQL_LONGVARBINARY = 836;
  
  public static final int SQL_LONGNVARCHAR = 837;
  
  public static final int SQL_LONGVARCHAR = 838;
  
  public static final int SQL_NCHAR = 839;
  
  public static final int SQL_NCLOB = 840;
  
  public static final int SQL_NUMERIC = 841;
  
  public static final int SQL_NVARCHAR = 842;
  
  public static final int SQL_REAL = 843;
  
  public static final int SQL_ROWID = 844;
  
  public static final int SQL_SQLXML = 845;
  
  public static final int SQL_SMALLINT = 846;
  
  public static final int SQL_TIME = 847;
  
  public static final int SQL_TIMESTAMP = 848;
  
  public static final int SQL_TINYINT = 849;
  
  public static final int SQL_VARBINARY = 850;
  
  public static final int SQL_VARCHAR = 851;
  
  static final int SQL_TSI_FRAC_SECOND = 852;
  
  static final int SQL_TSI_MILLI_SECOND = 853;
  
  static final int SQL_TSI_SECOND = 854;
  
  static final int SQL_TSI_MINUTE = 855;
  
  static final int SQL_TSI_HOUR = 856;
  
  static final int SQL_TSI_DAY = 857;
  
  static final int SQL_TSI_WEEK = 858;
  
  static final int SQL_TSI_MONTH = 859;
  
  static final int SQL_TSI_QUARTER = 860;
  
  static final int SQL_TSI_YEAR = 861;
  
  static final int X_KEYSET = 863;
  
  static final int X_OPTION = 864;
  
  static final int X_REPEAT = 865;
  
  static final int X_POS_INTEGER = 866;
  
  public static final int X_VALUE = 869;
  
  public static final int X_IDENTIFIER = 870;
  
  public static final int X_DELIMITED_IDENTIFIER = 871;
  
  public static final int X_ENDPARSE = 872;
  
  public static final int X_STARTPARSE = 873;
  
  public static final int X_REMARK = 874;
  
  public static final int X_NULL = 875;
  
  public static final int X_LOB_SIZE = 876;
  
  public static final int X_MALFORMED_STRING = 877;
  
  public static final int X_MALFORMED_NUMERIC = 878;
  
  public static final int X_MALFORMED_BIT_STRING = 879;
  
  public static final int X_MALFORMED_BINARY_STRING = 880;
  
  public static final int X_MALFORMED_UNICODE_STRING = 881;
  
  public static final int X_MALFORMED_COMMENT = 882;
  
  public static final int X_MALFORMED_IDENTIFIER = 883;
  
  public static final int X_MALFORMED_UNICODE_ESCAPE = 884;
  
  public static final int X_UNKNOWN_TOKEN = -1;
  
  private static final IntValueHashMap reservedKeys = new IntValueHashMap(351);
  
  private static final IntValueHashMap commandSet = new IntValueHashMap(299);
  
  private static final OrderedIntHashSet coreReservedWords = new OrderedIntHashSet(128);
  
  public static final short[] SQL_INTERVAL_FIELD_CODES = new short[] { 323, 173, 73, 127, 169, 250 };
  
  public static final String[] SQL_INTERVAL_FIELD_NAMES = new String[] { "YEAR", "MONTH", "DAY", "HOUR", "MINUTE", "SECOND" };
  
  private static final IntKeyHashMap sqlTSILookup = new IntKeyHashMap(10);
  
  static int get(String paramString) {
    int i = reservedKeys.get(paramString, -1);
    return (i == -1) ? commandSet.get(paramString, -1) : i;
  }
  
  public static boolean isCoreKeyword(int paramInt) {
    return coreReservedWords.contains(paramInt);
  }
  
  public static boolean isKeyword(String paramString) {
    return reservedKeys.containsKey(paramString);
  }
  
  public static int getKeywordID(String paramString, int paramInt) {
    return reservedKeys.get(paramString, paramInt);
  }
  
  public static int getNonKeywordID(String paramString, int paramInt) {
    return commandSet.get(paramString, paramInt);
  }
  
  public static String getKeyword(int paramInt) {
    null = (String)reservedKeys.getKey(paramInt);
    return (null != null) ? null : (String)commandSet.getKey(paramInt);
  }
  
  public static String getSQLTSIString(int paramInt) {
    return (String)sqlTSILookup.get(paramInt);
  }
  
  static {
    sqlTSILookup.put(857, "SQL_TSI_DAY");
    sqlTSILookup.put(852, "SQL_TSI_FRAC_SECOND");
    sqlTSILookup.put(853, "SQL_TSI_MILLI_SECOND");
    sqlTSILookup.put(856, "SQL_TSI_HOUR");
    sqlTSILookup.put(855, "SQL_TSI_MINUTE");
    sqlTSILookup.put(859, "SQL_TSI_MONTH");
    sqlTSILookup.put(860, "SQL_TSI_QUARTER");
    sqlTSILookup.put(854, "SQL_TSI_SECOND");
    sqlTSILookup.put(858, "SQL_TSI_WEEK");
    sqlTSILookup.put(861, "SQL_TSI_YEAR");
  }
  
  static {
    reservedKeys.put("ABS", 1);
    reservedKeys.put("AGGREGATE", 559);
    reservedKeys.put("ALL", 2);
    reservedKeys.put("ALLOCATE", 3);
    reservedKeys.put("ALTER", 4);
    reservedKeys.put("AND", 5);
    reservedKeys.put("ANY", 6);
    reservedKeys.put("ARE", 7);
    reservedKeys.put("ARRAY", 8);
    reservedKeys.put("ARRAY_AGG", 9);
    reservedKeys.put("AS", 10);
    reservedKeys.put("ASENSITIVE", 11);
    reservedKeys.put("ASYMMETRIC", 12);
    reservedKeys.put("AT", 13);
    reservedKeys.put("ATOMIC", 14);
    reservedKeys.put("AUTHORIZATION", 15);
    reservedKeys.put("AVG", 16);
    reservedKeys.put("BEGIN", 17);
    reservedKeys.put("BETWEEN", 18);
    reservedKeys.put("BIGINT", 19);
    reservedKeys.put("BINARY", 20);
    reservedKeys.put("BIT_LENGTH", 648);
    reservedKeys.put("BLOB", 21);
    reservedKeys.put("BOOLEAN", 22);
    reservedKeys.put("BOTH", 23);
    reservedKeys.put("BY", 24);
    reservedKeys.put("CALL", 25);
    reservedKeys.put("CALLED", 26);
    reservedKeys.put("CARDINALITY", 27);
    reservedKeys.put("CASCADED", 28);
    reservedKeys.put("CASE", 29);
    reservedKeys.put("CAST", 30);
    reservedKeys.put("CEIL", 31);
    reservedKeys.put("CEILING", 32);
    reservedKeys.put("CHAR", 33);
    reservedKeys.put("CHAR_LENGTH", 34);
    reservedKeys.put("CHARACTER", 35);
    reservedKeys.put("CHARACTER_LENGTH", 36);
    reservedKeys.put("CHECK", 37);
    reservedKeys.put("CLOB", 38);
    reservedKeys.put("CLOSE", 39);
    reservedKeys.put("COALESCE", 40);
    reservedKeys.put("COLLATE", 41);
    reservedKeys.put("COLLECT", 42);
    reservedKeys.put("COLUMN", 43);
    reservedKeys.put("COMMIT", 44);
    reservedKeys.put("COMPARABLE", 45);
    reservedKeys.put("CONDIITON", 46);
    reservedKeys.put("CONNECT", 47);
    reservedKeys.put("CONSTRAINT", 48);
    reservedKeys.put("CONVERT", 49);
    reservedKeys.put("CORR", 50);
    reservedKeys.put("CORRESPONDING", 51);
    reservedKeys.put("COUNT", 52);
    reservedKeys.put("COVAR_POP", 53);
    reservedKeys.put("COVAR_SAMP", 54);
    reservedKeys.put("CREATE", 55);
    reservedKeys.put("CROSS", 56);
    reservedKeys.put("CUBE", 57);
    reservedKeys.put("CUME_DIST", 58);
    reservedKeys.put("CURRENT", 59);
    reservedKeys.put("CURRENT_CATALOG", 60);
    reservedKeys.put("CURRENT_DATE", 61);
    reservedKeys.put("CURRENT_DEFAULT_TRANSFORM_GROUP", 62);
    reservedKeys.put("CURRENT_PATH", 63);
    reservedKeys.put("CURRENT_ROLE", 64);
    reservedKeys.put("CURRENT_SCHEMA", 65);
    reservedKeys.put("CURRENT_TIME", 66);
    reservedKeys.put("CURRENT_TIMESTAMP", 67);
    reservedKeys.put("DO", 86);
    reservedKeys.put("CURRENT_TRANSFORM_GROUP_FOR_TYPE", 68);
    reservedKeys.put("CURRENT_USER", 69);
    reservedKeys.put("CURSOR", 70);
    reservedKeys.put("CYCLE", 71);
    reservedKeys.put("DATE", 72);
    reservedKeys.put("DAY", 73);
    reservedKeys.put("DEALLOCATE", 74);
    reservedKeys.put("DEC", 75);
    reservedKeys.put("DECIMAL", 76);
    reservedKeys.put("DECLARE", 77);
    reservedKeys.put("DEFAULT", 78);
    reservedKeys.put("DELETE", 79);
    reservedKeys.put("DENSE_RANK", 80);
    reservedKeys.put("DEREF", 81);
    reservedKeys.put("DESCRIBE", 82);
    reservedKeys.put("DETERMINISTIC", 83);
    reservedKeys.put("DISCONNECT", 84);
    reservedKeys.put("DISTINCT", 85);
    reservedKeys.put("DOUBLE", 87);
    reservedKeys.put("DROP", 88);
    reservedKeys.put("DYNAMIC", 89);
    reservedKeys.put("EACH", 90);
    reservedKeys.put("ELEMENT", 91);
    reservedKeys.put("ELSE", 92);
    reservedKeys.put("ELSEIF", 93);
    reservedKeys.put("END", 94);
    reservedKeys.put("END_EXEC", 95);
    reservedKeys.put("ESCAPE", 96);
    reservedKeys.put("EVERY", 97);
    reservedKeys.put("EXCEPT", 98);
    reservedKeys.put("EXEC", 99);
    reservedKeys.put("EXECUTE", 100);
    reservedKeys.put("EXISTS", 101);
    reservedKeys.put("EXIT", 102);
    reservedKeys.put("EXP", 103);
    reservedKeys.put("EXTERNAL", 104);
    reservedKeys.put("EXTRACT", 105);
    reservedKeys.put("FALSE", 106);
    reservedKeys.put("FETCH", 107);
    reservedKeys.put("FILTER", 108);
    reservedKeys.put("FIRST_VALUE", 109);
    reservedKeys.put("FLOAT", 110);
    reservedKeys.put("FLOOR", 111);
    reservedKeys.put("FOR", 112);
    reservedKeys.put("FOREIGN", 113);
    reservedKeys.put("FREE", 114);
    reservedKeys.put("FROM", 115);
    reservedKeys.put("FULL", 116);
    reservedKeys.put("FUNCTION", 117);
    reservedKeys.put("FUSION", 118);
    reservedKeys.put("GET", 119);
    reservedKeys.put("GLOBAL", 120);
    reservedKeys.put("GRANT", 121);
    reservedKeys.put("GROUP", 122);
    reservedKeys.put("GROUPING", 123);
    reservedKeys.put("HANDLER", 124);
    reservedKeys.put("HAVING", 125);
    reservedKeys.put("HOLD", 126);
    reservedKeys.put("HOUR", 127);
    reservedKeys.put("IDENTITY", 128);
    reservedKeys.put("IF", 412);
    reservedKeys.put("IMPORT", 129);
    reservedKeys.put("IN", 130);
    reservedKeys.put("INDICATOR", 131);
    reservedKeys.put("INNER", 132);
    reservedKeys.put("INOUT", 133);
    reservedKeys.put("INSENSITIVE", 134);
    reservedKeys.put("INSERT", 135);
    reservedKeys.put("INT", 136);
    reservedKeys.put("INTEGER", 137);
    reservedKeys.put("INTERSECT", 138);
    reservedKeys.put("INTERSECTION", 139);
    reservedKeys.put("INTERVAL", 140);
    reservedKeys.put("INTO", 141);
    reservedKeys.put("IS", 142);
    reservedKeys.put("ITERATE", 143);
    reservedKeys.put("JOIN", 144);
    reservedKeys.put("LAG", 145);
    reservedKeys.put("LANGUAGE", 146);
    reservedKeys.put("LARGE", 147);
    reservedKeys.put("LAST_VALUE", 148);
    reservedKeys.put("LATERAL", 149);
    reservedKeys.put("LEAD", 150);
    reservedKeys.put("LEADING", 151);
    reservedKeys.put("LEAVE", 152);
    reservedKeys.put("LEFT", 153);
    reservedKeys.put("LIKE", 154);
    reservedKeys.put("LIKE_REGX", 155);
    reservedKeys.put("LN", 156);
    reservedKeys.put("LOCAL", 157);
    reservedKeys.put("LOCALTIME", 158);
    reservedKeys.put("LOCALTIMESTAMP", 159);
    reservedKeys.put("LOOP", 160);
    reservedKeys.put("LOWER", 161);
    reservedKeys.put("MATCH", 162);
    reservedKeys.put("MAX", 163);
    reservedKeys.put("MAX_CARDINALITY", 164);
    reservedKeys.put("MEMBER", 165);
    reservedKeys.put("MERGE", 166);
    reservedKeys.put("METHOD", 167);
    reservedKeys.put("MIN", 168);
    reservedKeys.put("MINUTE", 169);
    reservedKeys.put("MOD", 170);
    reservedKeys.put("MODIFIES", 171);
    reservedKeys.put("MODULE", 172);
    reservedKeys.put("MONTH", 173);
    reservedKeys.put("MULTISET", 174);
    reservedKeys.put("NATIONAL", 175);
    reservedKeys.put("NATURAL", 176);
    reservedKeys.put("NCHAR", 177);
    reservedKeys.put("NCLOB", 178);
    reservedKeys.put("NEW", 179);
    reservedKeys.put("NO", 180);
    reservedKeys.put("NONE", 181);
    reservedKeys.put("NORMALIZE", 182);
    reservedKeys.put("NOT", 183);
    reservedKeys.put("NTH_VALUE", 184);
    reservedKeys.put("NTILE", 185);
    reservedKeys.put("NULL", 186);
    reservedKeys.put("NULLIF", 187);
    reservedKeys.put("NUMERIC", 188);
    reservedKeys.put("OCCURRENCES_REGEX", 189);
    reservedKeys.put("OCTET_LENGTH", 190);
    reservedKeys.put("OF", 191);
    reservedKeys.put("OFFSET", 192);
    reservedKeys.put("OLD", 193);
    reservedKeys.put("ON", 194);
    reservedKeys.put("ONLY", 195);
    reservedKeys.put("OPEN", 196);
    reservedKeys.put("OR", 197);
    reservedKeys.put("ORDER", 198);
    reservedKeys.put("OUT", 199);
    reservedKeys.put("OUTER", 200);
    reservedKeys.put("OVER", 201);
    reservedKeys.put("OVERLAPS", 202);
    reservedKeys.put("OVERLAY", 203);
    reservedKeys.put("PARAMETER", 204);
    reservedKeys.put("PARTITION", 205);
    reservedKeys.put("PERCENT_RANK", 206);
    reservedKeys.put("PERCENTILE_CONT", 207);
    reservedKeys.put("PERCENTILE_DISC", 208);
    reservedKeys.put("POSITION", 209);
    reservedKeys.put("POSITION_REGEX", 210);
    reservedKeys.put("POWER", 211);
    reservedKeys.put("PRECISION", 212);
    reservedKeys.put("PREPARE", 213);
    reservedKeys.put("PRIMARY", 214);
    reservedKeys.put("PROCEDURE", 215);
    reservedKeys.put("RANGE", 216);
    reservedKeys.put("RANK", 217);
    reservedKeys.put("READS", 218);
    reservedKeys.put("REAL", 219);
    reservedKeys.put("RECURSIVE", 220);
    reservedKeys.put("REF", 221);
    reservedKeys.put("REFERENCES", 222);
    reservedKeys.put("REFERENCING", 223);
    reservedKeys.put("REGR_AVGX", 224);
    reservedKeys.put("REGR_AVGY", 225);
    reservedKeys.put("REGR_COUNT", 226);
    reservedKeys.put("REGR_INTERCEPT", 227);
    reservedKeys.put("REGR_R2", 228);
    reservedKeys.put("REGR_SLOPE", 229);
    reservedKeys.put("REGR_SXX", 230);
    reservedKeys.put("REGR_SXY", 231);
    reservedKeys.put("REGR_SYY", 232);
    reservedKeys.put("RELEASE", 233);
    reservedKeys.put("REPEAT", 234);
    reservedKeys.put("RESIGNAL", 235);
    reservedKeys.put("RETURN", 237);
    reservedKeys.put("RETURNS", 238);
    reservedKeys.put("REVOKE", 239);
    reservedKeys.put("RIGHT", 240);
    reservedKeys.put("ROLLBACK", 241);
    reservedKeys.put("ROLLUP", 242);
    reservedKeys.put("ROW", 243);
    reservedKeys.put("ROW_NUMBER", 244);
    reservedKeys.put("ROWS", 245);
    reservedKeys.put("SAVEPOINT", 246);
    reservedKeys.put("SCOPE", 247);
    reservedKeys.put("SCROLL", 248);
    reservedKeys.put("SEARCH", 249);
    reservedKeys.put("SECOND", 250);
    reservedKeys.put("SELECT", 251);
    reservedKeys.put("SENSITIVE", 252);
    reservedKeys.put("SESSION_USER", 253);
    reservedKeys.put("SET", 254);
    reservedKeys.put("SIGNAL", 255);
    reservedKeys.put("SIMILAR", 256);
    reservedKeys.put("SMALLINT", 257);
    reservedKeys.put("SOME", 258);
    reservedKeys.put("SPECIFIC", 259);
    reservedKeys.put("SPECIFICTYPE", 260);
    reservedKeys.put("SQL", 261);
    reservedKeys.put("SQLEXCEPTION", 262);
    reservedKeys.put("SQLSTATE", 263);
    reservedKeys.put("SQLWARNING", 264);
    reservedKeys.put("SQRT", 265);
    reservedKeys.put("STACKED", 266);
    reservedKeys.put("START", 267);
    reservedKeys.put("STATIC", 268);
    reservedKeys.put("STDDEV_POP", 269);
    reservedKeys.put("STDDEV_SAMP", 270);
    reservedKeys.put("SUBMULTISET", 271);
    reservedKeys.put("SUBSTRING", 272);
    reservedKeys.put("SUBSTRING_REGEX", 273);
    reservedKeys.put("SUM", 274);
    reservedKeys.put("SYMMETRIC", 275);
    reservedKeys.put("SYSTEM", 276);
    reservedKeys.put("SYSTEM_USER", 277);
    reservedKeys.put("TABLE", 278);
    reservedKeys.put("TABLESAMPLE", 279);
    reservedKeys.put("THEN", 280);
    reservedKeys.put("TIME", 281);
    reservedKeys.put("TIMESTAMP", 282);
    reservedKeys.put("TIMEZONE_HOUR", 283);
    reservedKeys.put("TIMEZONE_MINUTE", 284);
    reservedKeys.put("TO", 285);
    reservedKeys.put("TRAILING", 286);
    reservedKeys.put("TRANSLATE", 287);
    reservedKeys.put("TRANSLATE_REGEX", 288);
    reservedKeys.put("TRANSLATION", 289);
    reservedKeys.put("TREAT", 290);
    reservedKeys.put("TRIGGER", 291);
    reservedKeys.put("TRIM", 292);
    reservedKeys.put("TRIM_ARRAY", 293);
    reservedKeys.put("TRUE", 294);
    reservedKeys.put("TRUNCATE", 295);
    reservedKeys.put("UESCAPE", 296);
    reservedKeys.put("UNDO", 297);
    reservedKeys.put("UNION", 298);
    reservedKeys.put("UNIQUE", 299);
    reservedKeys.put("UNKNOWN", 300);
    reservedKeys.put("UNNEST", 301);
    reservedKeys.put("UNTIL", 302);
    reservedKeys.put("UPDATE", 303);
    reservedKeys.put("UPPER", 304);
    reservedKeys.put("USER", 305);
    reservedKeys.put("USING", 306);
    reservedKeys.put("VALUE", 307);
    reservedKeys.put("VALUES", 308);
    reservedKeys.put("VAR_POP", 309);
    reservedKeys.put("VAR_SAMP", 310);
    reservedKeys.put("VARBINARY", 311);
    reservedKeys.put("VARCHAR", 312);
    reservedKeys.put("VARYING", 313);
    reservedKeys.put("WHEN", 314);
    reservedKeys.put("WHENEVER", 315);
    reservedKeys.put("WHERE", 316);
    reservedKeys.put("WIDTH_BUCKET", 317);
    reservedKeys.put("WINDOW", 318);
    reservedKeys.put("WITH", 319);
    reservedKeys.put("WITHIN", 320);
    reservedKeys.put("WITHOUT", 321);
    reservedKeys.put("WHILE", 322);
    reservedKeys.put("YEAR", 323);
  }
  
  static {
    commandSet.put("ACTION", 332);
    commandSet.put("ADD", 334);
    commandSet.put("ADMIN", 335);
    commandSet.put("AFTER", 336);
    commandSet.put("ALIAS", 558);
    commandSet.put("ALWAYS", 337);
    commandSet.put("ASC", 338);
    commandSet.put("AUTHENTICATION", 561);
    commandSet.put("AUTOCOMMIT", 560);
    commandSet.put("BACKUP", 562);
    commandSet.put("BEFORE", 343);
    commandSet.put("BINARY_DOUBLE", 564);
    commandSet.put("BINARY_FLOAT", 563);
    commandSet.put("BIT", 565);
    commandSet.put("BYTE", 568);
    commandSet.put("BLOCKING", 566);
    commandSet.put("BODY", 567);
    commandSet.put("CACHE", 569);
    commandSet.put("CACHED", 570);
    commandSet.put("CASCADE", 347);
    commandSet.put("CATALOG", 348);
    commandSet.put("CHARACTERISTICS", 354);
    commandSet.put("CHARACTERS", 355);
    commandSet.put("CHECKPOINT", 572);
    commandSet.put("CITEXT", 573);
    commandSet.put("CRYPT_KEY", 660);
    commandSet.put("CLASS", 574);
    commandSet.put("CLUSTERED", 575);
    commandSet.put("COLLATE", 41);
    commandSet.put("COLLATION", 358);
    commandSet.put("COMMENT", 576);
    commandSet.put("COMMITTED", 365);
    commandSet.put("COMPACT", 577);
    commandSet.put("COMPRESSED", 578);
    commandSet.put("CONDIITON_IDENTIFIER", 366);
    commandSet.put("CONFLICT", 579);
    commandSet.put("CONTAINS", 375);
    commandSet.put("CONTINUE", 376);
    commandSet.put("CONTROL", 580);
    commandSet.put("CURDATE", 661);
    commandSet.put("CURRVAL", 581);
    commandSet.put("CURTIME", 662);
    commandSet.put("DATA", 378);
    commandSet.put("DATABASE", 582);
    commandSet.put("DEADLOCK", 583);
    commandSet.put("DEFAULTS", 381);
    commandSet.put("DEFRAG", 584);
    commandSet.put("DELAY", 585);
    commandSet.put("DESC", 389);
    commandSet.put("DIAGNOSTICS", 391);
    commandSet.put("DIGEST", 586);
    commandSet.put("DOMAIN", 393);
    commandSet.put("EVENT", 587);
    commandSet.put("EXCLUDING", 399);
    commandSet.put("EXPLAIN", 588);
    commandSet.put("FILE", 589);
    commandSet.put("FILES", 590);
    commandSet.put("FINAL", 400);
    commandSet.put("FIRST", 401);
    commandSet.put("FORMAT", 591);
    commandSet.put("FOUND", 404);
    commandSet.put("G", 405);
    commandSet.put("GC", 592);
    commandSet.put("GENERATED", 407);
    commandSet.put("GRANTED", 410);
    commandSet.put("GROUP_CONCAT", 688);
    commandSet.put("HEADER", 593);
    commandSet.put("IF", 412);
    commandSet.put("IGNORECASE", 594);
    commandSet.put("IMMEDIATELY", 595);
    commandSet.put("INCLUDING", 416);
    commandSet.put("INCREMENT", 417);
    commandSet.put("INDEX", 597);
    commandSet.put("INDEXER", 637);
    commandSet.put("INITIAL", 598);
    commandSet.put("INPUT", 419);
    commandSet.put("INSTEAD", 422);
    commandSet.put("INTEGRITY", 596);
    commandSet.put("IS_AUTOCOMMIT", 691);
    commandSet.put("ISOLATION", 424);
    commandSet.put("IS_READONLY_DATABASE", 692);
    commandSet.put("IS_READONLY_DATABASE_FILES", 693);
    commandSet.put("IS_READONLY_SESSION", 694);
    commandSet.put("JAVA", 425);
    commandSet.put("K", 426);
    commandSet.put("KEY", 427);
    commandSet.put("LAST", 430);
    commandSet.put("LASTVAL", 599);
    commandSet.put("LENGTH", 431);
    commandSet.put("LEVEL", 432);
    commandSet.put("LIBRARY", 433);
    commandSet.put("LIMIT", 600);
    commandSet.put("LOB", 702);
    commandSet.put("LOCK", 601);
    commandSet.put("LOCKS", 602);
    commandSet.put("LONG", 603);
    commandSet.put("LONGBLOB", 792);
    commandSet.put("LONGTEXT", 793);
    commandSet.put("LONGVAR", 604);
    commandSet.put("M", 435);
    commandSet.put("MATCHED", 437);
    commandSet.put("MAXROWS", 605);
    commandSet.put("MAXVALUE", 438);
    commandSet.put("MEDIAN", 606);
    commandSet.put("MEDIUMBLOB", 794);
    commandSet.put("MEDIUMTEXT", 795);
    commandSet.put("MEMORY", 607);
    commandSet.put("MESSAGE_TEXT", 441);
    commandSet.put("MILLIS", 608);
    commandSet.put("MINUS", 609);
    commandSet.put("MINVALUE", 442);
    commandSet.put("MORE", 443);
    commandSet.put("MVCC", 711);
    commandSet.put("MVLOCKS", 712);
    commandSet.put("NAME", 445);
    commandSet.put("NEXT", 448);
    commandSet.put("NEXTVAL", 611);
    commandSet.put("NAN", 610);
    commandSet.put("NIO", 715);
    commandSet.put("NOW", 716);
    commandSet.put("NULLS", 451);
    commandSet.put("NUMBER", 452);
    commandSet.put("NVARCHAR2", 612);
    commandSet.put("NVL2", 613);
    commandSet.put("OBJECT", 453);
    commandSet.put("OCTETS", 454);
    commandSet.put("OFF", 614);
    commandSet.put("OPTION", 455);
    commandSet.put("ORDINALITY", 458);
    commandSet.put("OVERRIDING", 461);
    commandSet.put("P", 462);
    commandSet.put("PAD", 463);
    commandSet.put("PARTIAL", 470);
    commandSet.put("PASSWORD", 615);
    commandSet.put("PLACING", 473);
    commandSet.put("PLAN", 616);
    commandSet.put("PRESERVE", 476);
    commandSet.put("PREVVAL", 617);
    commandSet.put("PRIVILEGES", 478);
    commandSet.put("PROPERTY", 618);
    commandSet.put("RAW", 619);
    commandSet.put("READ", 480);
    commandSet.put("READONLY", 620);
    commandSet.put("REFERENTIAL", 621);
    commandSet.put("REGULAR", 622);
    commandSet.put("RENAME", 623);
    commandSet.put("REPEATABLE", 482);
    commandSet.put("RESET", 624);
    commandSet.put("RESTART", 484);
    commandSet.put("RESTRICT", 485);
    commandSet.put("RESULT", 236);
    commandSet.put("ROLE", 490);
    commandSet.put("ROUTINE", 491);
    commandSet.put("ROW_COUNT", 495);
    commandSet.put("ROWNUM", 625);
    commandSet.put("SCALE", 496);
    commandSet.put("SCHEMA", 497);
    commandSet.put("SCRIPT", 626);
    commandSet.put("SEQUENCE", 505);
    commandSet.put("SEPARATOR", 627);
    commandSet.put("SERIALIZABLE", 506);
    commandSet.put("SERVER", 509);
    commandSet.put("SESSION", 508);
    commandSet.put("SETS", 510);
    commandSet.put("SHUTDOWN", 628);
    commandSet.put("SIMPLE", 511);
    commandSet.put("SIZE", 512);
    commandSet.put("SOURCE", 513);
    commandSet.put("SQL_BIGINT", 822);
    commandSet.put("SQL_BINARY", 823);
    commandSet.put("SQL_BIT", 824);
    commandSet.put("SQL_BLOB", 825);
    commandSet.put("SQL_BOOLEAN", 826);
    commandSet.put("SQL_CHAR", 827);
    commandSet.put("SQL_CLOB", 828);
    commandSet.put("SQL_DATALINK", 832);
    commandSet.put("SQL_DATE", 829);
    commandSet.put("SQL_DECIMAL", 831);
    commandSet.put("SQL_DOUBLE", 833);
    commandSet.put("SQL_FLOAT", 834);
    commandSet.put("SQL_INTEGER", 835);
    commandSet.put("SQL_LONGNVARCHAR", 837);
    commandSet.put("SQL_LONGVARBINARY", 836);
    commandSet.put("SQL_LONGVARCHAR", 838);
    commandSet.put("SQL_NCHAR", 839);
    commandSet.put("SQL_NCLOB", 840);
    commandSet.put("SQL_NUMERIC", 841);
    commandSet.put("SQL_NVARCHAR", 842);
    commandSet.put("SQL_REAL", 843);
    commandSet.put("SQL_ROWID", 844);
    commandSet.put("SQL_SMALLINT", 846);
    commandSet.put("SQL_SQLXML", 845);
    commandSet.put("SQL_TIME", 847);
    commandSet.put("SQL_TIMESTAMP", 848);
    commandSet.put("SQL_TINYINT", 849);
    commandSet.put("SQL_VARBINARY", 850);
    commandSet.put("SQL_VARCHAR", 851);
    commandSet.put("SQL_TSI_DAY", 857);
    commandSet.put("SQL_TSI_FRAC_SECOND", 852);
    commandSet.put("SQL_TSI_MILLI_SECOND", 853);
    commandSet.put("SQL_TSI_HOUR", 856);
    commandSet.put("SQL_TSI_MINUTE", 855);
    commandSet.put("SQL_TSI_MONTH", 859);
    commandSet.put("SQL_TSI_QUARTER", 860);
    commandSet.put("SQL_TSI_SECOND", 854);
    commandSet.put("SQL_TSI_WEEK", 858);
    commandSet.put("SQL_TSI_YEAR", 861);
    commandSet.put("STATEMENT", 517);
    commandSet.put("STYLE", 519);
    commandSet.put("SYNTAX", 629);
    commandSet.put("T", 521);
    commandSet.put("TDC", 630);
    commandSet.put("TEMP", 631);
    commandSet.put("TEMPORARY", 523);
    commandSet.put("TEXT", 632);
    commandSet.put("TYPES", 634);
    commandSet.put("TIMESTAMP_WITH_ZONE", 761);
    commandSet.put("TIMESTAMPADD", 762);
    commandSet.put("TIMESTAMPDIFF", 763);
    commandSet.put("TINYBLOB", 796);
    commandSet.put("TINYTEXT", 797);
    commandSet.put("TOP", 773);
    commandSet.put("TRANSACTION", 526);
    commandSet.put("TRANSACTION_ACTIVE", 527);
    commandSet.put("TRANSACTIONS_COMMITTED", 528);
    commandSet.put("TRANSACTIONS_ROLLED_BACK", 529);
    commandSet.put("TTI", 633);
    commandSet.put("TYPE", 535);
    commandSet.put("UNCOMMITTED", 537);
    commandSet.put("USAGE", 540);
    commandSet.put("VIEW", 545);
    commandSet.put("VARCHAR2", 635);
    commandSet.put("WORK", 546);
    commandSet.put("WRAPPER", 548);
    commandSet.put("WRITE", 547);
    commandSet.put("WRITE_DELAY", 636);
    commandSet.put("ZONE", 549);
    commandSet.put("ACOS", 640);
    commandSet.put("ACTION_ID", 641);
    commandSet.put("ADD_MONTHS", 644);
    commandSet.put("ARRAY_SORT", 642);
    commandSet.put("ASCII", 643);
    commandSet.put("ASIN", 645);
    commandSet.put("ATAN", 646);
    commandSet.put("ATAN2", 647);
    commandSet.put("BITAND", 649);
    commandSet.put("BITANDNOT", 650);
    commandSet.put("BITLENGTH", 651);
    commandSet.put("BITNOT", 652);
    commandSet.put("BITOR", 653);
    commandSet.put("BITXOR", 654);
    commandSet.put("CASEWHEN", 571);
    commandSet.put("CONCAT", 656);
    commandSet.put("CONCAT_WS", 657);
    commandSet.put("CHR", 655);
    commandSet.put("COS", 658);
    commandSet.put("COT", 659);
    commandSet.put("DATABASE_NAME", 664);
    commandSet.put("DATE_ADD", 667);
    commandSet.put("DATE_SUB", 668);
    commandSet.put("DATEADD", 669);
    commandSet.put("DATEDIFF", 670);
    commandSet.put("DAY_NAME", 671);
    commandSet.put("DAY_OF_MONTH", 672);
    commandSet.put("DAY_OF_WEEK", 673);
    commandSet.put("DAY_OF_YEAR", 674);
    commandSet.put("DAYNAME", 675);
    commandSet.put("DAYOFMONTH", 676);
    commandSet.put("DAYOFWEEK", 677);
    commandSet.put("DAYOFYEAR", 678);
    commandSet.put("DAYS", 679);
    commandSet.put("DBTIMEZONE", 680);
    commandSet.put("DECODE", 681);
    commandSet.put("DEGREES", 682);
    commandSet.put("DIFFERENCE", 683);
    commandSet.put("DMOD", 684);
    commandSet.put("FROM_TZ", 685);
    commandSet.put("GREATEST", 687);
    commandSet.put("HEXTORAW", 686);
    commandSet.put("INSTR", 690);
    commandSet.put("IFNULL", 689);
    commandSet.put("ISNULL", 696);
    commandSet.put("LAST_DAY", 697);
    commandSet.put("LCASE", 698);
    commandSet.put("LEAST", 699);
    commandSet.put("LOAD_FILE", 700);
    commandSet.put("LOCATE", 701);
    commandSet.put("LOG", 703);
    commandSet.put("LOG10", 704);
    commandSet.put("LPAD", 705);
    commandSet.put("LTRIM", 706);
    commandSet.put("MONTH_NAME", 708);
    commandSet.put("MONTHNAME", 709);
    commandSet.put("MONTHS_BETWEEN", 710);
    commandSet.put("NAMES", 446);
    commandSet.put("NEW_TIME", 713);
    commandSet.put("NEXT_DAY", 714);
    commandSet.put("NUMTODSINTERVAL", 717);
    commandSet.put("NUMTOYMINTERVAL", 718);
    commandSet.put("NVL", 689);
    commandSet.put("OCTETLENGTH", 719);
    commandSet.put("PI", 720);
    commandSet.put("POSITION_ARRAY", 721);
    commandSet.put("QUARTER", 722);
    commandSet.put("RADIANS", 723);
    commandSet.put("RAND", 724);
    commandSet.put("RAWTOHEX", 725);
    commandSet.put("REGEXP_MATCHES", 726);
    commandSet.put("REGEXP_SUBSTRING", 727);
    commandSet.put("REGEXP_SUBSTRING_ARRAY", 728);
    commandSet.put("REPLACE", 729);
    commandSet.put("REVERSE", 730);
    commandSet.put("ROUND", 731);
    commandSet.put("ROUNDMAGIC", 732);
    commandSet.put("RPAD", 734);
    commandSet.put("RTRIM", 733);
    commandSet.put("SECONDS_SINCE_MIDNIGHT", 735);
    commandSet.put("SESSION_ID", 737);
    commandSet.put("SESSIONTIMEZONE", 740);
    commandSet.put("SIGN", 741);
    commandSet.put("SIN", 742);
    commandSet.put("SORT_ARRAY", 743);
    commandSet.put("SOUNDEX", 744);
    commandSet.put("SPACE", 514);
    commandSet.put("SUBSTR", 756);
    commandSet.put("SYS_EXTRACT_UTC", 757);
    commandSet.put("SYSDATE", 758);
    commandSet.put("SYSTIMESTAMP", 759);
    commandSet.put("TAN", 760);
    commandSet.put("TO_CHAR", 765);
    commandSet.put("TO_DATE", 766);
    commandSet.put("TO_DSINTERVAL", 767);
    commandSet.put("TO_YMINTERVAL", 768);
    commandSet.put("TO_NUMBER", 769);
    commandSet.put("TO_TIMESTAMP", 770);
    commandSet.put("TO_TIMESTAMP_TZ", 771);
    commandSet.put("TODAY", 772);
    commandSet.put("TRUNC", 777);
    commandSet.put("UCASE", 779);
    commandSet.put("TRANSACTION_ID", 775);
    commandSet.put("TRANSACTION_SIZE", 776);
    commandSet.put("TZ_OFFSET", 778);
    commandSet.put("UUID", 782);
    commandSet.put("UNIX_MILLIS", 780);
    commandSet.put("UNIX_TIMESTAMP", 781);
    commandSet.put("WEEK", 790);
    commandSet.put("WEEK_OF_YEAR", 791);
    commandSet.put("ISOLATION_LEVEL", 695);
    commandSet.put("SESSION_ISOLATION_LEVEL", 738);
    commandSet.put("DATABASE_ISOLATION_LEVEL", 663);
    commandSet.put("TRANSACTION_CONTROL", 774);
    commandSet.put("TIMEZONE", 764);
    commandSet.put("SESSION_TIMEZONE", 739);
    commandSet.put("DATABASE_TIMEZONE", 665);
    commandSet.put("DATABASE_VERSION", 666);
    commandSet.put("LOB_ID", 707);
    commandSet.put("SEQUENCE_ARRAY", 736);
    commandSet.put("*", 801);
    commandSet.put(")", 802);
    commandSet.put(":", 803);
    commandSet.put(",", 804);
    commandSet.put("||", 805);
    commandSet.put("/", 806);
    commandSet.put("=", 396);
    commandSet.put(">", 809);
    commandSet.put(">=", 810);
    commandSet.put("[", 811);
    commandSet.put("<", 812);
    commandSet.put("<=", 813);
    commandSet.put("-", 814);
    commandSet.put("<>", 815);
    commandSet.put("!=", 815);
    commandSet.put("(", 816);
    commandSet.put("+", 817);
    commandSet.put("?", 818);
    commandSet.put("]", 820);
    commandSet.put(";", 821);
  }
  
  static {
    short[] arrayOfShort = { 
        10, 5, 2, 6, 13, 16, 24, 18, 23, 25, 
        29, 30, 51, 49, 52, 40, 55, 56, 78, 85, 
        86, 88, 92, 97, 101, 98, 112, 115, 116, 121, 
        122, 125, 141, 142, 130, 138, 144, 132, 153, 151, 
        154, 163, 168, 176, 187, 183, 194, 198, 197, 200, 
        214, 222, 240, 251, 254, 258, 269, 270, 274, 278, 
        280, 285, 286, 291, 298, 299, 306, 308, 309, 310, 
        314, 316, 319 };
    for (byte b = 0; b < arrayOfShort.length; b++)
      coreReservedWords.add(arrayOfShort[b]); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\Tokens.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */