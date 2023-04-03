/*     */ package org.postgresql.jdbc2;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public class EscapedFunctions {
/*     */   public static final String ABS = "abs";
/*     */   
/*     */   public static final String ACOS = "acos";
/*     */   
/*     */   public static final String ASIN = "asin";
/*     */   
/*     */   public static final String ATAN = "atan";
/*     */   
/*     */   public static final String ATAN2 = "atan2";
/*     */   
/*     */   public static final String CEILING = "ceiling";
/*     */   
/*     */   public static final String COS = "cos";
/*     */   
/*     */   public static final String COT = "cot";
/*     */   
/*     */   public static final String DEGREES = "degrees";
/*     */   
/*     */   public static final String EXP = "exp";
/*     */   
/*     */   public static final String FLOOR = "floor";
/*     */   
/*     */   public static final String LOG = "log";
/*     */   
/*     */   public static final String LOG10 = "log10";
/*     */   
/*     */   public static final String MOD = "mod";
/*     */   
/*     */   public static final String PI = "pi";
/*     */   
/*     */   public static final String POWER = "power";
/*     */   
/*     */   public static final String RADIANS = "radians";
/*     */   
/*     */   public static final String ROUND = "round";
/*     */   
/*     */   public static final String SIGN = "sign";
/*     */   
/*     */   public static final String SIN = "sin";
/*     */   
/*     */   public static final String SQRT = "sqrt";
/*     */   
/*     */   public static final String TAN = "tan";
/*     */   
/*     */   public static final String TRUNCATE = "truncate";
/*     */   
/*     */   public static final String ASCII = "ascii";
/*     */   
/*     */   public static final String CHAR = "char";
/*     */   
/*     */   public static final String CONCAT = "concat";
/*     */   
/*     */   public static final String INSERT = "insert";
/*     */   
/*     */   public static final String LCASE = "lcase";
/*     */   
/*     */   public static final String LEFT = "left";
/*     */   
/*     */   public static final String LENGTH = "length";
/*     */   
/*     */   public static final String LOCATE = "locate";
/*     */   
/*     */   public static final String LTRIM = "ltrim";
/*     */   
/*     */   public static final String REPEAT = "repeat";
/*     */   
/*     */   public static final String REPLACE = "replace";
/*     */   
/*     */   public static final String RIGHT = "right";
/*     */   
/*     */   public static final String RTRIM = "rtrim";
/*     */   
/*     */   public static final String SPACE = "space";
/*     */   
/*     */   public static final String SUBSTRING = "substring";
/*     */   
/*     */   public static final String UCASE = "ucase";
/*     */   
/*     */   public static final String CURDATE = "curdate";
/*     */   
/*     */   public static final String CURTIME = "curtime";
/*     */   
/*     */   public static final String DAYNAME = "dayname";
/*     */   
/*     */   public static final String DAYOFMONTH = "dayofmonth";
/*     */   
/*     */   public static final String DAYOFWEEK = "dayofweek";
/*     */   
/*     */   public static final String DAYOFYEAR = "dayofyear";
/*     */   
/*     */   public static final String HOUR = "hour";
/*     */   
/*     */   public static final String MINUTE = "minute";
/*     */   
/*     */   public static final String MONTH = "month";
/*     */   
/*     */   public static final String MONTHNAME = "monthname";
/*     */   
/*     */   public static final String NOW = "now";
/*     */   
/*     */   public static final String QUARTER = "quarter";
/*     */   
/*     */   public static final String SECOND = "second";
/*     */   
/*     */   public static final String WEEK = "week";
/*     */   
/*     */   public static final String YEAR = "year";
/*     */   
/*     */   public static final String TIMESTAMPADD = "timestampadd";
/*     */   
/*     */   public static final String TIMESTAMPDIFF = "timestampdiff";
/*     */   
/*     */   public static final String SQL_TSI_ROOT = "SQL_TSI_";
/*     */   
/*     */   public static final String SQL_TSI_DAY = "DAY";
/*     */   
/*     */   public static final String SQL_TSI_FRAC_SECOND = "FRAC_SECOND";
/*     */   
/*     */   public static final String SQL_TSI_HOUR = "HOUR";
/*     */   
/*     */   public static final String SQL_TSI_MINUTE = "MINUTE";
/*     */   
/*     */   public static final String SQL_TSI_MONTH = "MONTH";
/*     */   
/*     */   public static final String SQL_TSI_QUARTER = "QUARTER";
/*     */   
/*     */   public static final String SQL_TSI_SECOND = "SECOND";
/*     */   
/*     */   public static final String SQL_TSI_WEEK = "WEEK";
/*     */   
/*     */   public static final String SQL_TSI_YEAR = "YEAR";
/*     */   
/*     */   public static final String DATABASE = "database";
/*     */   
/*     */   public static final String IFNULL = "ifnull";
/*     */   
/*     */   public static final String USER = "user";
/*     */   
/* 119 */   private static Map functionMap = createFunctionMap();
/*     */   
/*     */   private static Map createFunctionMap() {
/* 122 */     Method[] arrayMeths = EscapedFunctions.class.getDeclaredMethods();
/* 123 */     Map<Object, Object> functionMap = new HashMap<Object, Object>(arrayMeths.length * 2);
/* 124 */     for (int i = 0; i < arrayMeths.length; i++) {
/* 125 */       Method meth = arrayMeths[i];
/* 126 */       if (meth.getName().startsWith("sql"))
/* 127 */         functionMap.put(meth.getName().toLowerCase(Locale.US), meth); 
/*     */     } 
/* 129 */     return functionMap;
/*     */   }
/*     */   
/*     */   public static Method getFunction(String functionName) {
/* 138 */     return (Method)functionMap.get("sql" + functionName.toLowerCase(Locale.US));
/*     */   }
/*     */   
/*     */   public static String sqlceiling(List parsedArgs) throws SQLException {
/* 144 */     StringBuffer buf = new StringBuffer();
/* 145 */     buf.append("ceil(");
/* 146 */     if (parsedArgs.size() != 1)
/* 147 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "ceiling"), PSQLState.SYNTAX_ERROR); 
/* 150 */     buf.append(parsedArgs.get(0));
/* 151 */     return buf.append(')').toString();
/*     */   }
/*     */   
/*     */   public static String sqllog(List parsedArgs) throws SQLException {
/* 156 */     StringBuffer buf = new StringBuffer();
/* 157 */     buf.append("ln(");
/* 158 */     if (parsedArgs.size() != 1)
/* 159 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "log"), PSQLState.SYNTAX_ERROR); 
/* 162 */     buf.append(parsedArgs.get(0));
/* 163 */     return buf.append(')').toString();
/*     */   }
/*     */   
/*     */   public static String sqllog10(List parsedArgs) throws SQLException {
/* 168 */     StringBuffer buf = new StringBuffer();
/* 169 */     buf.append("log(");
/* 170 */     if (parsedArgs.size() != 1)
/* 171 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "log10"), PSQLState.SYNTAX_ERROR); 
/* 174 */     buf.append(parsedArgs.get(0));
/* 175 */     return buf.append(')').toString();
/*     */   }
/*     */   
/*     */   public static String sqlpower(List parsedArgs) throws SQLException {
/* 180 */     StringBuffer buf = new StringBuffer();
/* 181 */     buf.append("pow(");
/* 182 */     if (parsedArgs.size() != 2)
/* 183 */       throw new PSQLException(GT.tr("{0} function takes two and only two arguments.", "power"), PSQLState.SYNTAX_ERROR); 
/* 186 */     buf.append(parsedArgs.get(0)).append(',').append(parsedArgs.get(1));
/* 187 */     return buf.append(')').toString();
/*     */   }
/*     */   
/*     */   public static String sqltruncate(List parsedArgs) throws SQLException {
/* 192 */     StringBuffer buf = new StringBuffer();
/* 193 */     buf.append("trunc(");
/* 194 */     if (parsedArgs.size() != 2)
/* 195 */       throw new PSQLException(GT.tr("{0} function takes two and only two arguments.", "truncate"), PSQLState.SYNTAX_ERROR); 
/* 198 */     buf.append(parsedArgs.get(0)).append(',').append(parsedArgs.get(1));
/* 199 */     return buf.append(')').toString();
/*     */   }
/*     */   
/*     */   public static String sqlchar(List parsedArgs) throws SQLException {
/* 205 */     StringBuffer buf = new StringBuffer();
/* 206 */     buf.append("chr(");
/* 207 */     if (parsedArgs.size() != 1)
/* 208 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "char"), PSQLState.SYNTAX_ERROR); 
/* 211 */     buf.append(parsedArgs.get(0));
/* 212 */     return buf.append(')').toString();
/*     */   }
/*     */   
/*     */   public static String sqlconcat(List parsedArgs) {
/* 217 */     StringBuffer buf = new StringBuffer();
/* 218 */     buf.append('(');
/* 219 */     for (int iArg = 0; iArg < parsedArgs.size(); iArg++) {
/* 220 */       buf.append(parsedArgs.get(iArg));
/* 221 */       if (iArg != parsedArgs.size() - 1)
/* 222 */         buf.append(" || "); 
/*     */     } 
/* 224 */     return buf.append(')').toString();
/*     */   }
/*     */   
/*     */   public static String sqlinsert(List parsedArgs) throws SQLException {
/* 229 */     StringBuffer buf = new StringBuffer();
/* 230 */     buf.append("overlay(");
/* 231 */     if (parsedArgs.size() != 4)
/* 232 */       throw new PSQLException(GT.tr("{0} function takes four and only four argument.", "insert"), PSQLState.SYNTAX_ERROR); 
/* 235 */     buf.append(parsedArgs.get(0)).append(" placing ").append(parsedArgs.get(3));
/* 236 */     buf.append(" from ").append(parsedArgs.get(1)).append(" for ").append(parsedArgs.get(2));
/* 237 */     return buf.append(')').toString();
/*     */   }
/*     */   
/*     */   public static String sqllcase(List parsedArgs) throws SQLException {
/* 242 */     StringBuffer buf = new StringBuffer();
/* 243 */     buf.append("lower(");
/* 244 */     if (parsedArgs.size() != 1)
/* 245 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "lcase"), PSQLState.SYNTAX_ERROR); 
/* 248 */     buf.append(parsedArgs.get(0));
/* 249 */     return buf.append(')').toString();
/*     */   }
/*     */   
/*     */   public static String sqlleft(List parsedArgs) throws SQLException {
/* 254 */     StringBuffer buf = new StringBuffer();
/* 255 */     buf.append("substring(");
/* 256 */     if (parsedArgs.size() != 2)
/* 257 */       throw new PSQLException(GT.tr("{0} function takes two and only two arguments.", "left"), PSQLState.SYNTAX_ERROR); 
/* 260 */     buf.append(parsedArgs.get(0)).append(" for ").append(parsedArgs.get(1));
/* 261 */     return buf.append(')').toString();
/*     */   }
/*     */   
/*     */   public static String sqllength(List parsedArgs) throws SQLException {
/* 266 */     StringBuffer buf = new StringBuffer();
/* 267 */     buf.append("length(trim(trailing from ");
/* 268 */     if (parsedArgs.size() != 1)
/* 269 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "length"), PSQLState.SYNTAX_ERROR); 
/* 272 */     buf.append(parsedArgs.get(0));
/* 273 */     return buf.append("))").toString();
/*     */   }
/*     */   
/*     */   public static String sqllocate(List<String> parsedArgs) throws SQLException {
/* 278 */     if (parsedArgs.size() == 2)
/* 279 */       return "position(" + parsedArgs.get(0) + " in " + parsedArgs.get(1) + ")"; 
/* 280 */     if (parsedArgs.size() == 3) {
/* 281 */       String tmp = "position(" + parsedArgs.get(0) + " in substring(" + parsedArgs.get(1) + " from " + parsedArgs.get(2) + "))";
/* 282 */       return "(" + parsedArgs.get(2) + "*sign(" + tmp + ")+" + tmp + ")";
/*     */     } 
/* 284 */     throw new PSQLException(GT.tr("{0} function takes two or three arguments.", "locate"), PSQLState.SYNTAX_ERROR);
/*     */   }
/*     */   
/*     */   public static String sqlltrim(List parsedArgs) throws SQLException {
/* 291 */     StringBuffer buf = new StringBuffer();
/* 292 */     buf.append("trim(leading from ");
/* 293 */     if (parsedArgs.size() != 1)
/* 294 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "ltrim"), PSQLState.SYNTAX_ERROR); 
/* 297 */     buf.append(parsedArgs.get(0));
/* 298 */     return buf.append(')').toString();
/*     */   }
/*     */   
/*     */   public static String sqlright(List parsedArgs) throws SQLException {
/* 303 */     StringBuffer buf = new StringBuffer();
/* 304 */     buf.append("substring(");
/* 305 */     if (parsedArgs.size() != 2)
/* 306 */       throw new PSQLException(GT.tr("{0} function takes two and only two arguments.", "right"), PSQLState.SYNTAX_ERROR); 
/* 309 */     buf.append(parsedArgs.get(0)).append(" from (length(").append(parsedArgs.get(0)).append(")+1-").append(parsedArgs.get(1));
/* 310 */     return buf.append("))").toString();
/*     */   }
/*     */   
/*     */   public static String sqlrtrim(List parsedArgs) throws SQLException {
/* 315 */     StringBuffer buf = new StringBuffer();
/* 316 */     buf.append("trim(trailing from ");
/* 317 */     if (parsedArgs.size() != 1)
/* 318 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "rtrim"), PSQLState.SYNTAX_ERROR); 
/* 321 */     buf.append(parsedArgs.get(0));
/* 322 */     return buf.append(')').toString();
/*     */   }
/*     */   
/*     */   public static String sqlspace(List parsedArgs) throws SQLException {
/* 327 */     StringBuffer buf = new StringBuffer();
/* 328 */     buf.append("repeat(' ',");
/* 329 */     if (parsedArgs.size() != 1)
/* 330 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "space"), PSQLState.SYNTAX_ERROR); 
/* 333 */     buf.append(parsedArgs.get(0));
/* 334 */     return buf.append(')').toString();
/*     */   }
/*     */   
/*     */   public static String sqlsubstring(List<String> parsedArgs) throws SQLException {
/* 339 */     if (parsedArgs.size() == 2)
/* 340 */       return "substr(" + parsedArgs.get(0) + "," + parsedArgs.get(1) + ")"; 
/* 341 */     if (parsedArgs.size() == 3)
/* 342 */       return "substr(" + parsedArgs.get(0) + "," + parsedArgs.get(1) + "," + parsedArgs.get(2) + ")"; 
/* 344 */     throw new PSQLException(GT.tr("{0} function takes two or three arguments.", "substring"), PSQLState.SYNTAX_ERROR);
/*     */   }
/*     */   
/*     */   public static String sqlucase(List parsedArgs) throws SQLException {
/* 351 */     StringBuffer buf = new StringBuffer();
/* 352 */     buf.append("upper(");
/* 353 */     if (parsedArgs.size() != 1)
/* 354 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "ucase"), PSQLState.SYNTAX_ERROR); 
/* 357 */     buf.append(parsedArgs.get(0));
/* 358 */     return buf.append(')').toString();
/*     */   }
/*     */   
/*     */   public static String sqlcurdate(List parsedArgs) throws SQLException {
/* 363 */     if (parsedArgs.size() != 0)
/* 364 */       throw new PSQLException(GT.tr("{0} function doesn''t take any argument.", "curdate"), PSQLState.SYNTAX_ERROR); 
/* 367 */     return "current_date";
/*     */   }
/*     */   
/*     */   public static String sqlcurtime(List parsedArgs) throws SQLException {
/* 372 */     if (parsedArgs.size() != 0)
/* 373 */       throw new PSQLException(GT.tr("{0} function doesn''t take any argument.", "curtime"), PSQLState.SYNTAX_ERROR); 
/* 376 */     return "current_time";
/*     */   }
/*     */   
/*     */   public static String sqldayname(List<String> parsedArgs) throws SQLException {
/* 381 */     if (parsedArgs.size() != 1)
/* 382 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "dayname"), PSQLState.SYNTAX_ERROR); 
/* 385 */     return "to_char(" + parsedArgs.get(0) + ",'Day')";
/*     */   }
/*     */   
/*     */   public static String sqldayofmonth(List<String> parsedArgs) throws SQLException {
/* 390 */     if (parsedArgs.size() != 1)
/* 391 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "dayofmonth"), PSQLState.SYNTAX_ERROR); 
/* 394 */     return "extract(day from " + parsedArgs.get(0) + ")";
/*     */   }
/*     */   
/*     */   public static String sqldayofweek(List<String> parsedArgs) throws SQLException {
/* 400 */     if (parsedArgs.size() != 1)
/* 401 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "dayofweek"), PSQLState.SYNTAX_ERROR); 
/* 404 */     return "extract(dow from " + parsedArgs.get(0) + ")+1";
/*     */   }
/*     */   
/*     */   public static String sqldayofyear(List<String> parsedArgs) throws SQLException {
/* 409 */     if (parsedArgs.size() != 1)
/* 410 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "dayofyear"), PSQLState.SYNTAX_ERROR); 
/* 413 */     return "extract(doy from " + parsedArgs.get(0) + ")";
/*     */   }
/*     */   
/*     */   public static String sqlhour(List<String> parsedArgs) throws SQLException {
/* 418 */     if (parsedArgs.size() != 1)
/* 419 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "hour"), PSQLState.SYNTAX_ERROR); 
/* 422 */     return "extract(hour from " + parsedArgs.get(0) + ")";
/*     */   }
/*     */   
/*     */   public static String sqlminute(List<String> parsedArgs) throws SQLException {
/* 427 */     if (parsedArgs.size() != 1)
/* 428 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "minute"), PSQLState.SYNTAX_ERROR); 
/* 431 */     return "extract(minute from " + parsedArgs.get(0) + ")";
/*     */   }
/*     */   
/*     */   public static String sqlmonth(List<String> parsedArgs) throws SQLException {
/* 436 */     if (parsedArgs.size() != 1)
/* 437 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "month"), PSQLState.SYNTAX_ERROR); 
/* 440 */     return "extract(month from " + parsedArgs.get(0) + ")";
/*     */   }
/*     */   
/*     */   public static String sqlmonthname(List<String> parsedArgs) throws SQLException {
/* 445 */     if (parsedArgs.size() != 1)
/* 446 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "monthname"), PSQLState.SYNTAX_ERROR); 
/* 449 */     return "to_char(" + parsedArgs.get(0) + ",'Month')";
/*     */   }
/*     */   
/*     */   public static String sqlquarter(List<String> parsedArgs) throws SQLException {
/* 454 */     if (parsedArgs.size() != 1)
/* 455 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "quarter"), PSQLState.SYNTAX_ERROR); 
/* 458 */     return "extract(quarter from " + parsedArgs.get(0) + ")";
/*     */   }
/*     */   
/*     */   public static String sqlsecond(List<String> parsedArgs) throws SQLException {
/* 463 */     if (parsedArgs.size() != 1)
/* 464 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "second"), PSQLState.SYNTAX_ERROR); 
/* 467 */     return "extract(second from " + parsedArgs.get(0) + ")";
/*     */   }
/*     */   
/*     */   public static String sqlweek(List<String> parsedArgs) throws SQLException {
/* 472 */     if (parsedArgs.size() != 1)
/* 473 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "week"), PSQLState.SYNTAX_ERROR); 
/* 476 */     return "extract(week from " + parsedArgs.get(0) + ")";
/*     */   }
/*     */   
/*     */   public static String sqlyear(List<String> parsedArgs) throws SQLException {
/* 481 */     if (parsedArgs.size() != 1)
/* 482 */       throw new PSQLException(GT.tr("{0} function takes one and only one argument.", "year"), PSQLState.SYNTAX_ERROR); 
/* 485 */     return "extract(year from " + parsedArgs.get(0) + ")";
/*     */   }
/*     */   
/*     */   public static String sqltimestampadd(List<E> parsedArgs) throws SQLException {
/* 490 */     if (parsedArgs.size() != 3)
/* 491 */       throw new PSQLException(GT.tr("{0} function takes three and only three arguments.", "timestampadd"), PSQLState.SYNTAX_ERROR); 
/* 494 */     String interval = constantToInterval(parsedArgs.get(0).toString(), parsedArgs.get(1).toString());
/* 495 */     StringBuffer buf = new StringBuffer();
/* 496 */     buf.append("(").append(interval).append("+");
/* 497 */     buf.append(parsedArgs.get(2)).append(")");
/* 498 */     return buf.toString();
/*     */   }
/*     */   
/*     */   private static final String constantToInterval(String type, String value) throws SQLException {
/* 502 */     if (!type.startsWith("SQL_TSI_"))
/* 503 */       throw new PSQLException(GT.tr("Interval {0} not yet implemented", type), PSQLState.SYNTAX_ERROR); 
/* 505 */     String shortType = type.substring("SQL_TSI_".length());
/* 506 */     if ("DAY".equalsIgnoreCase(shortType))
/* 507 */       return "CAST(" + value + " || ' day' as interval)"; 
/* 508 */     if ("SECOND".equalsIgnoreCase(shortType))
/* 509 */       return "CAST(" + value + " || ' second' as interval)"; 
/* 510 */     if ("HOUR".equalsIgnoreCase(shortType))
/* 511 */       return "CAST(" + value + " || ' hour' as interval)"; 
/* 512 */     if ("MINUTE".equalsIgnoreCase(shortType))
/* 513 */       return "CAST(" + value + " || ' minute' as interval)"; 
/* 514 */     if ("MONTH".equalsIgnoreCase(shortType))
/* 515 */       return "CAST(" + value + " || ' month' as interval)"; 
/* 516 */     if ("QUARTER".equalsIgnoreCase(shortType))
/* 517 */       return "CAST((" + value + "::int * 3) || ' month' as interval)"; 
/* 518 */     if ("WEEK".equalsIgnoreCase(shortType))
/* 519 */       return "CAST(" + value + " || ' week' as interval)"; 
/* 520 */     if ("YEAR".equalsIgnoreCase(shortType))
/* 521 */       return "CAST(" + value + " || ' year' as interval)"; 
/* 522 */     if ("FRAC_SECOND".equalsIgnoreCase(shortType))
/* 523 */       throw new PSQLException(GT.tr("Interval {0} not yet implemented", "SQL_TSI_FRAC_SECOND"), PSQLState.SYNTAX_ERROR); 
/* 525 */     throw new PSQLException(GT.tr("Interval {0} not yet implemented", type), PSQLState.SYNTAX_ERROR);
/*     */   }
/*     */   
/*     */   public static String sqltimestampdiff(List<E> parsedArgs) throws SQLException {
/* 532 */     if (parsedArgs.size() != 3)
/* 533 */       throw new PSQLException(GT.tr("{0} function takes three and only three arguments.", "timestampdiff"), PSQLState.SYNTAX_ERROR); 
/* 536 */     String datePart = constantToDatePart(parsedArgs.get(0).toString());
/* 537 */     StringBuffer buf = new StringBuffer();
/* 538 */     buf.append("extract( ").append(datePart).append(" from (").append(parsedArgs.get(2)).append("-").append(parsedArgs.get(1)).append("))");
/* 540 */     return buf.toString();
/*     */   }
/*     */   
/*     */   private static final String constantToDatePart(String type) throws SQLException {
/* 544 */     if (!type.startsWith("SQL_TSI_"))
/* 545 */       throw new PSQLException(GT.tr("Interval {0} not yet implemented", type), PSQLState.SYNTAX_ERROR); 
/* 547 */     String shortType = type.substring("SQL_TSI_".length());
/* 548 */     if ("DAY".equalsIgnoreCase(shortType))
/* 549 */       return "day"; 
/* 550 */     if ("SECOND".equalsIgnoreCase(shortType))
/* 551 */       return "second"; 
/* 552 */     if ("HOUR".equalsIgnoreCase(shortType))
/* 553 */       return "hour"; 
/* 554 */     if ("MINUTE".equalsIgnoreCase(shortType))
/* 555 */       return "minute"; 
/* 565 */     if ("FRAC_SECOND".equalsIgnoreCase(shortType))
/* 566 */       throw new PSQLException(GT.tr("Interval {0} not yet implemented", "SQL_TSI_FRAC_SECOND"), PSQLState.SYNTAX_ERROR); 
/* 568 */     throw new PSQLException(GT.tr("Interval {0} not yet implemented", type), PSQLState.SYNTAX_ERROR);
/*     */   }
/*     */   
/*     */   public static String sqldatabase(List parsedArgs) throws SQLException {
/* 574 */     if (parsedArgs.size() != 0)
/* 575 */       throw new PSQLException(GT.tr("{0} function doesn''t take any argument.", "database"), PSQLState.SYNTAX_ERROR); 
/* 578 */     return "current_database()";
/*     */   }
/*     */   
/*     */   public static String sqlifnull(List<String> parsedArgs) throws SQLException {
/* 583 */     if (parsedArgs.size() != 2)
/* 584 */       throw new PSQLException(GT.tr("{0} function takes two and only two arguments.", "ifnull"), PSQLState.SYNTAX_ERROR); 
/* 587 */     return "coalesce(" + parsedArgs.get(0) + "," + parsedArgs.get(1) + ")";
/*     */   }
/*     */   
/*     */   public static String sqluser(List parsedArgs) throws SQLException {
/* 592 */     if (parsedArgs.size() != 0)
/* 593 */       throw new PSQLException(GT.tr("{0} function doesn''t take any argument.", "user"), PSQLState.SYNTAX_ERROR); 
/* 596 */     return "user";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc2\EscapedFunctions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */