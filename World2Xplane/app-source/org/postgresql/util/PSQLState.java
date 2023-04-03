/*     */ package org.postgresql.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class PSQLState implements Serializable {
/*     */   private String state;
/*     */   
/*     */   public String getState() {
/*  22 */     return this.state;
/*     */   }
/*     */   
/*     */   public PSQLState(String state) {
/*  27 */     this.state = state;
/*     */   }
/*     */   
/*  32 */   public static final PSQLState UNKNOWN_STATE = new PSQLState("");
/*     */   
/*  34 */   public static final PSQLState TOO_MANY_RESULTS = new PSQLState("0100E");
/*     */   
/*  36 */   public static final PSQLState NO_DATA = new PSQLState("02000");
/*     */   
/*  38 */   public static final PSQLState INVALID_PARAMETER_TYPE = new PSQLState("07006");
/*     */   
/*  44 */   public static final PSQLState CONNECTION_UNABLE_TO_CONNECT = new PSQLState("08001");
/*     */   
/*  46 */   public static final PSQLState CONNECTION_DOES_NOT_EXIST = new PSQLState("08003");
/*     */   
/*  53 */   public static final PSQLState CONNECTION_REJECTED = new PSQLState("08004");
/*     */   
/*  58 */   public static final PSQLState CONNECTION_FAILURE = new PSQLState("08006");
/*     */   
/*  59 */   public static final PSQLState CONNECTION_FAILURE_DURING_TRANSACTION = new PSQLState("08007");
/*     */   
/*  66 */   public static final PSQLState PROTOCOL_VIOLATION = new PSQLState("08P01");
/*     */   
/*  68 */   public static final PSQLState COMMUNICATION_ERROR = new PSQLState("08S01");
/*     */   
/*  70 */   public static final PSQLState NOT_IMPLEMENTED = new PSQLState("0A000");
/*     */   
/*  72 */   public static final PSQLState DATA_ERROR = new PSQLState("22000");
/*     */   
/*  73 */   public static final PSQLState NUMERIC_VALUE_OUT_OF_RANGE = new PSQLState("22003");
/*     */   
/*  74 */   public static final PSQLState BAD_DATETIME_FORMAT = new PSQLState("22007");
/*     */   
/*  75 */   public static final PSQLState DATETIME_OVERFLOW = new PSQLState("22008");
/*     */   
/*  76 */   public static final PSQLState MOST_SPECIFIC_TYPE_DOES_NOT_MATCH = new PSQLState("2200G");
/*     */   
/*  77 */   public static final PSQLState INVALID_PARAMETER_VALUE = new PSQLState("22023");
/*     */   
/*  79 */   public static final PSQLState INVALID_CURSOR_STATE = new PSQLState("24000");
/*     */   
/*  81 */   public static final PSQLState TRANSACTION_STATE_INVALID = new PSQLState("25000");
/*     */   
/*  82 */   public static final PSQLState ACTIVE_SQL_TRANSACTION = new PSQLState("25001");
/*     */   
/*  83 */   public static final PSQLState NO_ACTIVE_SQL_TRANSACTION = new PSQLState("25P01");
/*     */   
/*  85 */   public static final PSQLState STATEMENT_NOT_ALLOWED_IN_FUNCTION_CALL = new PSQLState("2F003");
/*     */   
/*  87 */   public static final PSQLState INVALID_SAVEPOINT_SPECIFICATION = new PSQLState("3B000");
/*     */   
/*  89 */   public static final PSQLState SYNTAX_ERROR = new PSQLState("42601");
/*     */   
/*  90 */   public static final PSQLState UNDEFINED_COLUMN = new PSQLState("42703");
/*     */   
/*  91 */   public static final PSQLState WRONG_OBJECT_TYPE = new PSQLState("42809");
/*     */   
/*  92 */   public static final PSQLState NUMERIC_CONSTANT_OUT_OF_RANGE = new PSQLState("42820");
/*     */   
/*  93 */   public static final PSQLState DATA_TYPE_MISMATCH = new PSQLState("42821");
/*     */   
/*  94 */   public static final PSQLState UNDEFINED_FUNCTION = new PSQLState("42883");
/*     */   
/*  95 */   public static final PSQLState INVALID_NAME = new PSQLState("42602");
/*     */   
/*  97 */   public static final PSQLState OUT_OF_MEMORY = new PSQLState("53200");
/*     */   
/*  98 */   public static final PSQLState OBJECT_NOT_IN_STATE = new PSQLState("55000");
/*     */   
/* 100 */   public static final PSQLState SYSTEM_ERROR = new PSQLState("60000");
/*     */   
/* 102 */   public static final PSQLState UNEXPECTED_ERROR = new PSQLState("99999");
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresq\\util\PSQLState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */