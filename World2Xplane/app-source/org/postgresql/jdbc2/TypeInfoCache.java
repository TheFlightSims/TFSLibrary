/*     */ package org.postgresql.jdbc2;
/*     */ 
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.postgresql.core.BaseConnection;
/*     */ import org.postgresql.core.BaseStatement;
/*     */ import org.postgresql.core.TypeInfo;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PGobject;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public class TypeInfoCache implements TypeInfo {
/*     */   private Map _pgNameToSQLType;
/*     */   
/*     */   private Map _pgNameToJavaClass;
/*     */   
/*     */   private Map _oidToPgName;
/*     */   
/*     */   private Map _pgNameToOid;
/*     */   
/*     */   private Map _pgNameToPgObject;
/*     */   
/*     */   private Map _pgArrayToPgType;
/*     */   
/*     */   private Map _arrayOidToDelimiter;
/*     */   
/*     */   private BaseConnection _conn;
/*     */   
/*     */   private final int _unknownLength;
/*     */   
/*     */   private PreparedStatement _getOidStatement;
/*     */   
/*     */   private PreparedStatement _getNameStatement;
/*     */   
/*     */   private PreparedStatement _getArrayElementOidStatement;
/*     */   
/*     */   private PreparedStatement _getArrayDelimiterStatement;
/*     */   
/*     */   private PreparedStatement _getTypeInfoStatement;
/*     */   
/*  71 */   private static final Object[][] types = new Object[][] { 
/*  71 */       { "int2", new Integer(21), new Integer(5), "java.lang.Integer", new Integer(1005) }, { "int4", new Integer(23), new Integer(4), "java.lang.Integer", new Integer(1007) }, { "oid", new Integer(26), new Integer(-5), "java.lang.Long", new Integer(1028) }, { "int8", new Integer(20), new Integer(-5), "java.lang.Long", new Integer(1016) }, { "money", new Integer(790), new Integer(8), "java.lang.Double", new Integer(791) }, { "numeric", new Integer(1700), new Integer(2), "java.math.BigDecimal", new Integer(1231) }, { "float4", new Integer(700), new Integer(7), "java.lang.Float", new Integer(1021) }, { "float8", new Integer(701), new Integer(8), "java.lang.Double", new Integer(1022) }, { "char", new Integer(18), new Integer(1), "java.lang.String", new Integer(1002) }, { "bpchar", new Integer(1042), new Integer(1), "java.lang.String", new Integer(1014) }, 
/*  71 */       { "varchar", new Integer(1043), new Integer(12), "java.lang.String", new Integer(1015) }, { "text", new Integer(25), new Integer(12), "java.lang.String", new Integer(1009) }, { "name", new Integer(19), new Integer(12), "java.lang.String", new Integer(1003) }, { "bytea", new Integer(17), new Integer(-2), "[B", new Integer(1001) }, { "bool", new Integer(16), new Integer(-7), "java.lang.Boolean", new Integer(1000) }, { "bit", new Integer(1560), new Integer(-7), "java.lang.Boolean", new Integer(1561) }, { "date", new Integer(1082), new Integer(91), "java.sql.Date", new Integer(1182) }, { "time", new Integer(1083), new Integer(92), "java.sql.Time", new Integer(1183) }, { "timetz", new Integer(1266), new Integer(92), "java.sql.Time", new Integer(1270) }, { "timestamp", new Integer(1114), new Integer(93), "java.sql.Timestamp", new Integer(1115) }, 
/*  71 */       { "timestamptz", new Integer(1184), new Integer(93), "java.sql.Timestamp", new Integer(1185) } };
/*     */   
/* 102 */   private static final HashMap typeAliases = new HashMap<Object, Object>();
/*     */   
/*     */   static {
/* 103 */     typeAliases.put("smallint", "int2");
/* 104 */     typeAliases.put("integer", "int4");
/* 105 */     typeAliases.put("int", "int4");
/* 106 */     typeAliases.put("bigint", "int8");
/* 107 */     typeAliases.put("float", "float8");
/* 108 */     typeAliases.put("boolean", "bool");
/* 109 */     typeAliases.put("decimal", "numeric");
/*     */   }
/*     */   
/*     */   public TypeInfoCache(BaseConnection conn, int unknownLength) {
/* 114 */     this._conn = conn;
/* 115 */     this._unknownLength = unknownLength;
/* 116 */     this._oidToPgName = new HashMap<Object, Object>();
/* 117 */     this._pgNameToOid = new HashMap<Object, Object>();
/* 118 */     this._pgNameToJavaClass = new HashMap<Object, Object>();
/* 119 */     this._pgNameToPgObject = new HashMap<Object, Object>();
/* 120 */     this._pgArrayToPgType = new HashMap<Object, Object>();
/* 121 */     this._arrayOidToDelimiter = new HashMap<Object, Object>();
/* 125 */     this._pgNameToSQLType = Collections.synchronizedMap(new HashMap<Object, Object>());
/* 127 */     for (int i = 0; i < types.length; i++) {
/* 128 */       String pgTypeName = (String)types[i][0];
/* 129 */       Integer oid = (Integer)types[i][1];
/* 130 */       Integer sqlType = (Integer)types[i][2];
/* 131 */       String javaClass = (String)types[i][3];
/* 132 */       Integer arrayOid = (Integer)types[i][4];
/* 134 */       addCoreType(pgTypeName, oid, sqlType, javaClass, arrayOid);
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void addCoreType(String pgTypeName, Integer oid, Integer sqlType, String javaClass, Integer arrayOid) {
/* 141 */     this._pgNameToJavaClass.put(pgTypeName, javaClass);
/* 142 */     this._pgNameToOid.put(pgTypeName, oid);
/* 143 */     this._oidToPgName.put(oid, pgTypeName);
/* 144 */     this._pgArrayToPgType.put(arrayOid, oid);
/* 145 */     this._pgNameToSQLType.put(pgTypeName, sqlType);
/* 151 */     Character delim = new Character(',');
/* 152 */     this._arrayOidToDelimiter.put(oid, delim);
/* 154 */     String pgArrayTypeName = "_" + pgTypeName;
/* 155 */     this._pgNameToJavaClass.put(pgArrayTypeName, "java.sql.Array");
/* 156 */     this._pgNameToSQLType.put(pgArrayTypeName, new Integer(2003));
/*     */   }
/*     */   
/*     */   public synchronized void addDataType(String type, Class<?> klass) throws SQLException {
/* 162 */     if (!PGobject.class.isAssignableFrom(klass))
/* 163 */       throw new PSQLException(GT.tr("The class {0} does not implement org.postgresql.util.PGobject.", klass.toString()), PSQLState.INVALID_PARAMETER_TYPE); 
/* 165 */     this._pgNameToPgObject.put(type, klass);
/* 166 */     this._pgNameToJavaClass.put(type, klass.getName());
/*     */   }
/*     */   
/*     */   public Iterator getPGTypeNamesWithSQLTypes() {
/* 171 */     return this._pgNameToSQLType.keySet().iterator();
/*     */   }
/*     */   
/*     */   public int getSQLType(int oid) throws SQLException {
/* 176 */     return getSQLType(getPGType(oid));
/*     */   }
/*     */   
/*     */   public synchronized int getSQLType(String pgTypeName) throws SQLException {
/* 181 */     Integer i = (Integer)this._pgNameToSQLType.get(pgTypeName);
/* 182 */     if (i != null)
/* 183 */       return i.intValue(); 
/* 185 */     if (this._getTypeInfoStatement == null) {
/* 190 */       String sql = "SELECT typinput='array_in'::regproc, typtype FROM ";
/* 191 */       if (this._conn.haveMinimumServerVersion("7.3"))
/* 192 */         sql = sql + "pg_catalog."; 
/* 194 */       sql = sql + "pg_type WHERE typname = ?";
/* 196 */       this._getTypeInfoStatement = this._conn.prepareStatement(sql);
/*     */     } 
/* 199 */     this._getTypeInfoStatement.setString(1, pgTypeName);
/* 202 */     if (!((BaseStatement)this._getTypeInfoStatement).executeWithFlags(16))
/* 203 */       throw new PSQLException(GT.tr("No results were returned by the query."), PSQLState.NO_DATA); 
/* 205 */     ResultSet rs = this._getTypeInfoStatement.getResultSet();
/* 207 */     Integer type = null;
/* 208 */     if (rs.next()) {
/* 209 */       boolean isArray = rs.getBoolean(1);
/* 210 */       String typtype = rs.getString(2);
/* 211 */       if (isArray) {
/* 212 */         type = new Integer(2003);
/* 213 */       } else if ("c".equals(typtype)) {
/* 214 */         type = new Integer(2002);
/* 215 */       } else if ("d".equals(typtype)) {
/* 216 */         type = new Integer(2001);
/*     */       } 
/*     */     } 
/* 220 */     if (type == null)
/* 221 */       type = new Integer(1111); 
/* 223 */     rs.close();
/* 225 */     this._pgNameToSQLType.put(pgTypeName, type);
/* 227 */     return type.intValue();
/*     */   }
/*     */   
/*     */   public synchronized int getPGType(String pgTypeName) throws SQLException {
/* 232 */     Integer oid = (Integer)this._pgNameToOid.get(pgTypeName);
/* 233 */     if (oid != null)
/* 234 */       return oid.intValue(); 
/* 236 */     if (this._getOidStatement == null) {
/*     */       String sql;
/* 238 */       if (this._conn.haveMinimumServerVersion("7.3")) {
/* 239 */         sql = "SELECT oid FROM pg_catalog.pg_type WHERE typname = ?";
/*     */       } else {
/* 241 */         sql = "SELECT oid FROM pg_type WHERE typname = ?";
/*     */       } 
/* 244 */       this._getOidStatement = this._conn.prepareStatement(sql);
/*     */     } 
/* 247 */     this._getOidStatement.setString(1, pgTypeName);
/* 250 */     if (!((BaseStatement)this._getOidStatement).executeWithFlags(16))
/* 251 */       throw new PSQLException(GT.tr("No results were returned by the query."), PSQLState.NO_DATA); 
/* 253 */     oid = new Integer(0);
/* 254 */     ResultSet rs = this._getOidStatement.getResultSet();
/* 255 */     if (rs.next()) {
/* 256 */       oid = new Integer((int)rs.getLong(1));
/* 257 */       this._oidToPgName.put(oid, pgTypeName);
/*     */     } 
/* 259 */     this._pgNameToOid.put(pgTypeName, oid);
/* 260 */     rs.close();
/* 262 */     return oid.intValue();
/*     */   }
/*     */   
/*     */   public synchronized String getPGType(int oid) throws SQLException {
/* 267 */     if (oid == 0)
/* 268 */       return null; 
/* 270 */     String pgTypeName = (String)this._oidToPgName.get(new Integer(oid));
/* 271 */     if (pgTypeName != null)
/* 272 */       return pgTypeName; 
/* 274 */     if (this._getNameStatement == null) {
/*     */       String sql;
/* 276 */       if (this._conn.haveMinimumServerVersion("7.3")) {
/* 277 */         sql = "SELECT typname FROM pg_catalog.pg_type WHERE oid = ?";
/*     */       } else {
/* 279 */         sql = "SELECT typname FROM pg_type WHERE oid = ?";
/*     */       } 
/* 282 */       this._getNameStatement = this._conn.prepareStatement(sql);
/*     */     } 
/* 285 */     this._getNameStatement.setInt(1, oid);
/* 288 */     if (!((BaseStatement)this._getNameStatement).executeWithFlags(16))
/* 289 */       throw new PSQLException(GT.tr("No results were returned by the query."), PSQLState.NO_DATA); 
/* 291 */     ResultSet rs = this._getNameStatement.getResultSet();
/* 292 */     if (rs.next()) {
/* 293 */       pgTypeName = rs.getString(1);
/* 294 */       this._pgNameToOid.put(pgTypeName, new Integer(oid));
/* 295 */       this._oidToPgName.put(new Integer(oid), pgTypeName);
/*     */     } 
/* 297 */     rs.close();
/* 299 */     return pgTypeName;
/*     */   }
/*     */   
/*     */   public int getPGArrayType(String elementTypeName) throws SQLException {
/* 304 */     elementTypeName = getTypeForAlias(elementTypeName);
/* 305 */     return getPGType("_" + elementTypeName);
/*     */   }
/*     */   
/*     */   protected synchronized int convertArrayToBaseOid(int oid) {
/* 317 */     Integer i = (Integer)this._pgArrayToPgType.get(new Integer(oid));
/* 318 */     if (i == null)
/* 319 */       return oid; 
/* 320 */     return i.intValue();
/*     */   }
/*     */   
/*     */   public synchronized char getArrayDelimiter(int oid) throws SQLException {
/* 325 */     if (oid == 0)
/* 326 */       return ','; 
/* 328 */     Character delim = (Character)this._arrayOidToDelimiter.get(new Integer(oid));
/* 329 */     if (delim != null)
/* 330 */       return delim.charValue(); 
/* 332 */     if (this._getArrayDelimiterStatement == null) {
/*     */       String sql;
/* 334 */       if (this._conn.haveMinimumServerVersion("7.3")) {
/* 335 */         sql = "SELECT e.typdelim FROM pg_catalog.pg_type t, pg_catalog.pg_type e WHERE t.oid = ? and t.typelem = e.oid";
/*     */       } else {
/* 337 */         sql = "SELECT e.typdelim FROM pg_type t, pg_type e WHERE t.oid = ? and t.typelem = e.oid";
/*     */       } 
/* 339 */       this._getArrayDelimiterStatement = this._conn.prepareStatement(sql);
/*     */     } 
/* 342 */     this._getArrayDelimiterStatement.setInt(1, oid);
/* 345 */     if (!((BaseStatement)this._getArrayDelimiterStatement).executeWithFlags(16))
/* 346 */       throw new PSQLException(GT.tr("No results were returned by the query."), PSQLState.NO_DATA); 
/* 348 */     ResultSet rs = this._getArrayDelimiterStatement.getResultSet();
/* 349 */     if (!rs.next())
/* 350 */       throw new PSQLException(GT.tr("No results were returned by the query."), PSQLState.NO_DATA); 
/* 352 */     String s = rs.getString(1);
/* 353 */     delim = new Character(s.charAt(0));
/* 355 */     this._arrayOidToDelimiter.put(new Integer(oid), delim);
/* 357 */     rs.close();
/* 359 */     return delim.charValue();
/*     */   }
/*     */   
/*     */   public synchronized int getPGArrayElement(int oid) throws SQLException {
/* 364 */     if (oid == 0)
/* 365 */       return 0; 
/* 367 */     Integer pgType = (Integer)this._pgArrayToPgType.get(new Integer(oid));
/* 369 */     if (pgType != null)
/* 370 */       return pgType.intValue(); 
/* 372 */     if (this._getArrayElementOidStatement == null) {
/*     */       String sql;
/* 374 */       if (this._conn.haveMinimumServerVersion("7.3")) {
/* 375 */         sql = "SELECT e.oid, e.typname FROM pg_catalog.pg_type t, pg_catalog.pg_type e WHERE t.oid = ? and t.typelem = e.oid";
/*     */       } else {
/* 377 */         sql = "SELECT e.oid, e.typname FROM pg_type t, pg_type e WHERE t.oid = ? and t.typelem = e.oid";
/*     */       } 
/* 379 */       this._getArrayElementOidStatement = this._conn.prepareStatement(sql);
/*     */     } 
/* 382 */     this._getArrayElementOidStatement.setInt(1, oid);
/* 385 */     if (!((BaseStatement)this._getArrayElementOidStatement).executeWithFlags(16))
/* 386 */       throw new PSQLException(GT.tr("No results were returned by the query."), PSQLState.NO_DATA); 
/* 388 */     ResultSet rs = this._getArrayElementOidStatement.getResultSet();
/* 389 */     if (!rs.next())
/* 390 */       throw new PSQLException(GT.tr("No results were returned by the query."), PSQLState.NO_DATA); 
/* 392 */     pgType = new Integer((int)rs.getLong(1));
/* 393 */     this._pgArrayToPgType.put(new Integer(oid), pgType);
/* 394 */     this._pgNameToOid.put(rs.getString(2), pgType);
/* 395 */     this._oidToPgName.put(pgType, rs.getString(2));
/* 397 */     rs.close();
/* 399 */     return pgType.intValue();
/*     */   }
/*     */   
/*     */   public synchronized Class getPGobject(String type) {
/* 404 */     return (Class)this._pgNameToPgObject.get(type);
/*     */   }
/*     */   
/*     */   public synchronized String getJavaClass(int oid) throws SQLException {
/* 409 */     String pgTypeName = getPGType(oid);
/* 411 */     String result = (String)this._pgNameToJavaClass.get(pgTypeName);
/* 412 */     if (result != null)
/* 413 */       return result; 
/* 416 */     if (getSQLType(pgTypeName) == 2003) {
/* 417 */       result = "java.sql.Array";
/* 418 */       this._pgNameToJavaClass.put(pgTypeName, result);
/*     */     } 
/* 421 */     return result;
/*     */   }
/*     */   
/*     */   public String getTypeForAlias(String alias) {
/* 425 */     String type = (String)typeAliases.get(alias);
/* 426 */     if (type != null)
/* 427 */       return type; 
/* 428 */     return alias;
/*     */   }
/*     */   
/*     */   public int getPrecision(int oid, int typmod) {
/* 432 */     oid = convertArrayToBaseOid(oid);
/* 433 */     switch (oid) {
/*     */       case 21:
/* 435 */         return 5;
/*     */       case 23:
/*     */       case 26:
/* 439 */         return 10;
/*     */       case 20:
/* 442 */         return 19;
/*     */       case 700:
/* 448 */         return 8;
/*     */       case 701:
/* 451 */         return 17;
/*     */       case 1700:
/* 454 */         if (typmod == -1)
/* 455 */           return 0; 
/* 456 */         return (typmod - 4 & 0xFFFF0000) >> 16;
/*     */       case 16:
/*     */       case 18:
/* 460 */         return 1;
/*     */       case 1042:
/*     */       case 1043:
/* 464 */         if (typmod == -1)
/* 465 */           return this._unknownLength; 
/* 466 */         return typmod - 4;
/*     */       case 1082:
/*     */       case 1083:
/*     */       case 1114:
/*     */       case 1184:
/*     */       case 1186:
/*     */       case 1266:
/* 476 */         return getDisplaySize(oid, typmod);
/*     */       case 1560:
/* 479 */         return typmod;
/*     */       case 1562:
/* 482 */         if (typmod == -1)
/* 483 */           return this._unknownLength; 
/* 484 */         return typmod;
/*     */     } 
/* 489 */     return this._unknownLength;
/*     */   }
/*     */   
/*     */   public int getScale(int oid, int typmod) {
/* 494 */     oid = convertArrayToBaseOid(oid);
/* 495 */     switch (oid) {
/*     */       case 700:
/* 497 */         return 8;
/*     */       case 701:
/* 499 */         return 17;
/*     */       case 1700:
/* 501 */         if (typmod == -1)
/* 502 */           return 0; 
/* 503 */         return typmod - 4 & 0xFFFF;
/*     */       case 1083:
/*     */       case 1114:
/*     */       case 1184:
/*     */       case 1266:
/* 508 */         if (typmod == -1)
/* 509 */           return 6; 
/* 510 */         return typmod;
/*     */       case 1186:
/* 512 */         if (typmod == -1)
/* 513 */           return 6; 
/* 514 */         return typmod & 0xFFFF;
/*     */     } 
/* 516 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean isCaseSensitive(int oid) {
/* 521 */     oid = convertArrayToBaseOid(oid);
/* 522 */     switch (oid) {
/*     */       case 16:
/*     */       case 20:
/*     */       case 21:
/*     */       case 23:
/*     */       case 26:
/*     */       case 700:
/*     */       case 701:
/*     */       case 1082:
/*     */       case 1083:
/*     */       case 1114:
/*     */       case 1184:
/*     */       case 1186:
/*     */       case 1266:
/*     */       case 1560:
/*     */       case 1562:
/*     */       case 1700:
/* 539 */         return false;
/*     */     } 
/* 541 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isSigned(int oid) {
/* 546 */     oid = convertArrayToBaseOid(oid);
/* 547 */     switch (oid) {
/*     */       case 20:
/*     */       case 21:
/*     */       case 23:
/*     */       case 700:
/*     */       case 701:
/*     */       case 1700:
/* 554 */         return true;
/*     */     } 
/* 556 */     return false;
/*     */   }
/*     */   
/*     */   public int getDisplaySize(int oid, int typmod) {
/*     */     int secondSize, precision, scale;
/* 561 */     oid = convertArrayToBaseOid(oid);
/* 562 */     switch (oid) {
/*     */       case 21:
/* 564 */         return 6;
/*     */       case 23:
/* 566 */         return 11;
/*     */       case 26:
/* 568 */         return 10;
/*     */       case 20:
/* 570 */         return 20;
/*     */       case 700:
/* 574 */         return 15;
/*     */       case 701:
/* 576 */         return 25;
/*     */       case 18:
/* 578 */         return 1;
/*     */       case 16:
/* 580 */         return 1;
/*     */       case 1082:
/* 582 */         return 13;
/*     */       case 1083:
/*     */       case 1114:
/*     */       case 1184:
/*     */       case 1266:
/* 589 */         switch (typmod) {
/*     */           case -1:
/* 591 */             secondSize = 7;
/*     */             break;
/*     */           case 0:
/* 594 */             secondSize = 0;
/*     */             break;
/*     */           case 1:
/* 598 */             secondSize = 3;
/*     */             break;
/*     */           default:
/* 601 */             secondSize = typmod + 1;
/*     */             break;
/*     */         } 
/* 611 */         switch (oid) {
/*     */           case 1083:
/* 613 */             return 8 + secondSize;
/*     */           case 1266:
/* 615 */             return 8 + secondSize + 6;
/*     */           case 1114:
/* 617 */             return 22 + secondSize;
/*     */           case 1184:
/* 619 */             return 22 + secondSize + 6;
/*     */         } 
/*     */       case 1186:
/* 622 */         return 49;
/*     */       case 1042:
/*     */       case 1043:
/* 625 */         if (typmod == -1)
/* 626 */           return this._unknownLength; 
/* 627 */         return typmod - 4;
/*     */       case 1700:
/* 629 */         if (typmod == -1)
/* 630 */           return 131089; 
/* 631 */         precision = typmod - 4 >> 16 & 0xFFFF;
/* 632 */         scale = typmod - 4 & 0xFFFF;
/* 634 */         return 1 + precision + ((scale != 0) ? 1 : 0);
/*     */       case 1560:
/* 636 */         return typmod;
/*     */       case 1562:
/* 638 */         if (typmod == -1)
/* 639 */           return this._unknownLength; 
/* 640 */         return typmod;
/*     */       case 17:
/*     */       case 25:
/* 643 */         return this._unknownLength;
/*     */     } 
/* 645 */     return this._unknownLength;
/*     */   }
/*     */   
/*     */   public int getMaximumPrecision(int oid) {
/* 650 */     oid = convertArrayToBaseOid(oid);
/* 651 */     switch (oid) {
/*     */       case 1700:
/* 653 */         return 1000;
/*     */       case 1083:
/*     */       case 1266:
/* 658 */         return 6;
/*     */       case 1114:
/*     */       case 1184:
/*     */       case 1186:
/* 662 */         return 6;
/*     */       case 1042:
/*     */       case 1043:
/* 665 */         return 10485760;
/*     */       case 1560:
/*     */       case 1562:
/* 668 */         return 83886080;
/*     */     } 
/* 670 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean requiresQuoting(int oid) throws SQLException {
/* 676 */     int sqlType = getSQLType(oid);
/* 677 */     switch (sqlType) {
/*     */       case -6:
/*     */       case -5:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/* 687 */         return false;
/*     */     } 
/* 689 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc2\TypeInfoCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */