/*     */ package org.postgresql.jdbc2;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.Date;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ import org.postgresql.Driver;
/*     */ import org.postgresql.core.BaseConnection;
/*     */ import org.postgresql.core.BaseStatement;
/*     */ import org.postgresql.core.Field;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public abstract class AbstractJdbc2Array {
/*     */   private static class PgArrayList extends ArrayList {
/*     */     private static final long serialVersionUID = 2052783752654562677L;
/*     */     
/*     */     private PgArrayList() {}
/*     */     
/*  54 */     int dimensionsCount = 1;
/*     */   }
/*     */   
/*  61 */   private BaseConnection connection = null;
/*     */   
/*     */   private int oid;
/*     */   
/*  71 */   private String fieldString = null;
/*     */   
/*     */   private final boolean useObjects;
/*     */   
/*     */   private final boolean haveMinServer82;
/*     */   
/*     */   private PgArrayList arrayList;
/*     */   
/*     */   public AbstractJdbc2Array(BaseConnection connection, int oid, String fieldString) throws SQLException {
/* 101 */     this.connection = connection;
/* 102 */     this.oid = oid;
/* 103 */     this.fieldString = fieldString;
/* 104 */     this.useObjects = connection.haveMinimumCompatibleVersion("8.3");
/* 105 */     this.haveMinServer82 = connection.haveMinimumServerVersion("8.2");
/*     */   }
/*     */   
/*     */   public Object getArray() throws SQLException {
/* 110 */     return getArrayImpl(1L, 0, null);
/*     */   }
/*     */   
/*     */   public Object getArray(long index, int count) throws SQLException {
/* 115 */     return getArrayImpl(index, count, null);
/*     */   }
/*     */   
/*     */   public Object getArrayImpl(Map map) throws SQLException {
/* 120 */     return getArrayImpl(1L, 0, map);
/*     */   }
/*     */   
/*     */   public Object getArrayImpl(long index, int count, Map map) throws SQLException {
/* 127 */     if (map != null && !map.isEmpty())
/* 129 */       throw Driver.notImplemented(getClass(), "getArrayImpl(long,int,Map)"); 
/* 133 */     if (index < 1L)
/* 135 */       throw new PSQLException(GT.tr("The array index is out of range: {0}", new Long(index)), PSQLState.DATA_ERROR); 
/* 138 */     buildArrayList();
/* 140 */     if (count == 0)
/* 141 */       count = this.arrayList.size(); 
/* 144 */     if (--index + count > this.arrayList.size())
/* 146 */       throw new PSQLException(GT.tr("The array index is out of range: {0}, number of elements: {1}.", new Object[] { new Long(index + count), new Long(this.arrayList.size()) }), PSQLState.DATA_ERROR); 
/* 149 */     return buildArray(this.arrayList, (int)index, count);
/*     */   }
/*     */   
/*     */   private synchronized void buildArrayList() throws SQLException {
/* 160 */     if (this.arrayList != null)
/*     */       return; 
/* 163 */     this.arrayList = new PgArrayList();
/* 165 */     char delim = this.connection.getTypeInfo().getArrayDelimiter(this.oid);
/* 167 */     if (this.fieldString != null) {
/* 170 */       char[] chars = this.fieldString.toCharArray();
/* 171 */       StringBuffer buffer = null;
/* 172 */       boolean insideString = false;
/* 173 */       boolean wasInsideString = false;
/* 175 */       Vector<PgArrayList> dims = new Vector();
/* 176 */       PgArrayList curArray = this.arrayList;
/* 188 */       int startOffset = 0;
/* 190 */       if (chars[0] == '[') {
/* 192 */         while (chars[startOffset] != '=')
/* 194 */           startOffset++; 
/* 196 */         startOffset++;
/*     */       } 
/* 200 */       for (int i = startOffset; i < chars.length; i++) {
/* 204 */         if (chars[i] == '\\') {
/* 205 */           i++;
/*     */         } else {
/* 208 */           if (!insideString && chars[i] == '{') {
/* 210 */             if (dims.size() == 0) {
/* 212 */               dims.add(this.arrayList);
/*     */             } else {
/* 216 */               PgArrayList a = new PgArrayList();
/* 217 */               PgArrayList p = dims.lastElement();
/* 218 */               p.add((E)a);
/* 219 */               dims.add(a);
/*     */             } 
/* 221 */             curArray = dims.lastElement();
/* 225 */             for (int t = i + 1; t < chars.length; t++) {
/* 226 */               if (!Character.isWhitespace(chars[t]))
/* 227 */                 if (chars[t] == '{') {
/* 227 */                   curArray.dimensionsCount++;
/*     */                 } else {
/*     */                   break;
/*     */                 }  
/*     */             } 
/* 232 */             buffer = new StringBuffer();
/*     */             continue;
/*     */           } 
/* 237 */           if (chars[i] == '"') {
/* 239 */             insideString = !insideString;
/* 240 */             wasInsideString = true;
/*     */             continue;
/*     */           } 
/* 245 */           if (!insideString && Character.isWhitespace(chars[i]))
/*     */             continue; 
/* 251 */           if ((!insideString && (chars[i] == delim || chars[i] == '}')) || i == chars.length - 1) {
/* 255 */             if (chars[i] != '"' && chars[i] != '}' && chars[i] != delim && buffer != null)
/* 257 */               buffer.append(chars[i]); 
/* 260 */             String b = (buffer == null) ? null : buffer.toString();
/* 263 */             if (b != null && (b.length() > 0 || wasInsideString))
/* 265 */               curArray.add((!wasInsideString && this.haveMinServer82 && b.equals("NULL")) ? null : (E)b); 
/* 268 */             wasInsideString = false;
/* 269 */             buffer = new StringBuffer();
/* 272 */             if (chars[i] == '}') {
/* 274 */               dims.remove(dims.size() - 1);
/* 277 */               if (dims.size() > 0)
/* 279 */                 curArray = dims.lastElement(); 
/* 282 */               buffer = null;
/*     */             } 
/*     */             continue;
/*     */           } 
/*     */         } 
/* 288 */         if (buffer != null)
/* 289 */           buffer.append(chars[i]); 
/*     */         continue;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private Object buildArray(PgArrayList input, int index, int count) throws SQLException {
/* 302 */     if (count < 0)
/* 303 */       count = input.size(); 
/* 306 */     Object ret = null;
/* 309 */     int dims = input.dimensionsCount;
/* 312 */     int[] dimsLength = (dims > 1) ? new int[dims] : null;
/* 313 */     if (dims > 1)
/* 314 */       for (int i = 0; i < dims; i++)
/* 315 */         dimsLength[i] = (i == 0) ? count : 0;  
/* 320 */     int length = 0;
/* 323 */     int type = this.connection.getTypeInfo().getSQLType(this.connection.getTypeInfo().getPGArrayElement(this.oid));
/* 325 */     if (type == -7) {
/* 327 */       boolean[] pa = null;
/* 328 */       Object[] oa = null;
/* 330 */       if (dims > 1 || this.useObjects) {
/* 332 */         ret = oa = (Object[])((dims > 1) ? Array.newInstance(this.useObjects ? Boolean.class : boolean.class, dimsLength) : new Boolean[count]);
/*     */       } else {
/* 336 */         ret = pa = new boolean[count];
/*     */       } 
/* 340 */       for (; count > 0; count--) {
/* 342 */         Object o = input.get(index++);
/* 344 */         if (dims > 1 || this.useObjects) {
/* 346 */           oa[length++] = (o == null) ? null : ((dims > 1) ? buildArray((PgArrayList)o, 0, -1) : new Boolean(AbstractJdbc2ResultSet.toBoolean((String)o)));
/*     */         } else {
/* 350 */           pa[length++] = (o == null) ? false : AbstractJdbc2ResultSet.toBoolean((String)o);
/*     */         } 
/*     */       } 
/* 355 */     } else if (type == 5 || type == 4) {
/* 357 */       int[] pa = null;
/* 358 */       Object[] oa = null;
/* 360 */       if (dims > 1 || this.useObjects) {
/* 362 */         ret = oa = (Object[])((dims > 1) ? Array.newInstance(this.useObjects ? Integer.class : int.class, dimsLength) : new Integer[count]);
/*     */       } else {
/* 366 */         ret = pa = new int[count];
/*     */       } 
/* 369 */       for (; count > 0; count--) {
/* 371 */         Object o = input.get(index++);
/* 373 */         if (dims > 1 || this.useObjects) {
/* 375 */           oa[length++] = (o == null) ? null : ((dims > 1) ? buildArray((PgArrayList)o, 0, -1) : new Integer(AbstractJdbc2ResultSet.toInt((String)o)));
/*     */         } else {
/* 379 */           pa[length++] = (o == null) ? 0 : AbstractJdbc2ResultSet.toInt((String)o);
/*     */         } 
/*     */       } 
/* 384 */     } else if (type == -5) {
/* 386 */       long[] pa = null;
/* 387 */       Object[] oa = null;
/* 389 */       if (dims > 1 || this.useObjects) {
/* 391 */         ret = oa = (Object[])((dims > 1) ? Array.newInstance(this.useObjects ? Long.class : long.class, dimsLength) : new Long[count]);
/*     */       } else {
/* 396 */         ret = pa = new long[count];
/*     */       } 
/* 399 */       for (; count > 0; count--) {
/* 401 */         Object o = input.get(index++);
/* 403 */         if (dims > 1 || this.useObjects) {
/* 405 */           oa[length++] = (o == null) ? null : ((dims > 1) ? buildArray((PgArrayList)o, 0, -1) : new Long(AbstractJdbc2ResultSet.toLong((String)o)));
/*     */         } else {
/* 409 */           pa[length++] = (o == null) ? 0L : AbstractJdbc2ResultSet.toLong((String)o);
/*     */         } 
/*     */       } 
/* 414 */     } else if (type == 2) {
/* 416 */       Object[] oa = null;
/* 417 */       ret = oa = (Object[])((dims > 1) ? Array.newInstance(BigDecimal.class, dimsLength) : new BigDecimal[count]);
/* 419 */       for (; count > 0; count--) {
/* 421 */         Object v = input.get(index++);
/* 422 */         oa[length++] = (dims > 1 && v != null) ? buildArray((PgArrayList)v, 0, -1) : ((v == null) ? null : AbstractJdbc2ResultSet.toBigDecimal((String)v, -1));
/*     */       } 
/* 426 */     } else if (type == 7) {
/* 428 */       float[] pa = null;
/* 429 */       Object[] oa = null;
/* 431 */       if (dims > 1 || this.useObjects) {
/* 433 */         ret = oa = (Object[])((dims > 1) ? Array.newInstance(this.useObjects ? Float.class : float.class, dimsLength) : new Float[count]);
/*     */       } else {
/* 437 */         ret = pa = new float[count];
/*     */       } 
/* 440 */       for (; count > 0; count--) {
/* 442 */         Object o = input.get(index++);
/* 444 */         if (dims > 1 || this.useObjects) {
/* 446 */           oa[length++] = (o == null) ? null : ((dims > 1) ? buildArray((PgArrayList)o, 0, -1) : new Float(AbstractJdbc2ResultSet.toFloat((String)o)));
/*     */         } else {
/* 450 */           pa[length++] = (o == null) ? 0.0F : AbstractJdbc2ResultSet.toFloat((String)o);
/*     */         } 
/*     */       } 
/* 455 */     } else if (type == 8) {
/* 457 */       double[] pa = null;
/* 458 */       Object[] oa = null;
/* 460 */       if (dims > 1 || this.useObjects) {
/* 462 */         ret = oa = (Object[])((dims > 1) ? Array.newInstance(this.useObjects ? Double.class : double.class, dimsLength) : new Double[count]);
/*     */       } else {
/* 466 */         ret = pa = new double[count];
/*     */       } 
/* 469 */       for (; count > 0; count--) {
/* 471 */         Object o = input.get(index++);
/* 473 */         if (dims > 1 || this.useObjects) {
/* 475 */           oa[length++] = (o == null) ? null : ((dims > 1) ? buildArray((PgArrayList)o, 0, -1) : new Double(AbstractJdbc2ResultSet.toDouble((String)o)));
/*     */         } else {
/* 479 */           pa[length++] = (o == null) ? 0.0D : AbstractJdbc2ResultSet.toDouble((String)o);
/*     */         } 
/*     */       } 
/* 484 */     } else if (type == 1 || type == 12) {
/* 486 */       Object[] oa = null;
/* 487 */       ret = oa = (Object[])((dims > 1) ? Array.newInstance(String.class, dimsLength) : new String[count]);
/* 489 */       for (; count > 0; count--) {
/* 491 */         Object v = input.get(index++);
/* 492 */         oa[length++] = (dims > 1 && v != null) ? buildArray((PgArrayList)v, 0, -1) : v;
/*     */       } 
/* 496 */     } else if (type == 91) {
/* 498 */       Object[] oa = null;
/* 499 */       ret = oa = (Object[])((dims > 1) ? Array.newInstance(Date.class, dimsLength) : new Date[count]);
/* 501 */       for (; count > 0; count--) {
/* 503 */         Object v = input.get(index++);
/* 504 */         oa[length++] = (dims > 1 && v != null) ? buildArray((PgArrayList)v, 0, -1) : ((v == null) ? null : this.connection.getTimestampUtils().toDate(null, (String)v));
/*     */       } 
/* 508 */     } else if (type == 92) {
/* 510 */       Object[] oa = null;
/* 511 */       ret = oa = (Object[])((dims > 1) ? Array.newInstance(Time.class, dimsLength) : new Time[count]);
/* 513 */       for (; count > 0; count--) {
/* 515 */         Object v = input.get(index++);
/* 516 */         oa[length++] = (dims > 1 && v != null) ? buildArray((PgArrayList)v, 0, -1) : ((v == null) ? null : this.connection.getTimestampUtils().toTime(null, (String)v));
/*     */       } 
/* 520 */     } else if (type == 93) {
/* 522 */       Object[] oa = null;
/* 523 */       ret = oa = (Object[])((dims > 1) ? Array.newInstance(Timestamp.class, dimsLength) : new Timestamp[count]);
/* 525 */       for (; count > 0; count--) {
/* 527 */         Object v = input.get(index++);
/* 528 */         oa[length++] = (dims > 1 && v != null) ? buildArray((PgArrayList)v, 0, -1) : ((v == null) ? null : this.connection.getTimestampUtils().toTimestamp(null, (String)v));
/*     */       } 
/*     */     } else {
/* 535 */       if (this.connection.getLogger().logDebug())
/* 536 */         this.connection.getLogger().debug("getArrayImpl(long,int,Map) with " + getBaseTypeName()); 
/* 538 */       throw Driver.notImplemented(getClass(), "getArrayImpl(long,int,Map)");
/*     */     } 
/* 541 */     return ret;
/*     */   }
/*     */   
/*     */   public int getBaseType() throws SQLException {
/* 546 */     return this.connection.getTypeInfo().getSQLType(getBaseTypeName());
/*     */   }
/*     */   
/*     */   public String getBaseTypeName() throws SQLException {
/* 551 */     buildArrayList();
/* 552 */     int elementOID = this.connection.getTypeInfo().getPGArrayElement(this.oid);
/* 553 */     return this.connection.getTypeInfo().getPGType(elementOID);
/*     */   }
/*     */   
/*     */   public ResultSet getResultSet() throws SQLException {
/* 558 */     return getResultSetImpl(1L, 0, null);
/*     */   }
/*     */   
/*     */   public ResultSet getResultSet(long index, int count) throws SQLException {
/* 563 */     return getResultSetImpl(index, count, null);
/*     */   }
/*     */   
/*     */   public ResultSet getResultSetImpl(Map map) throws SQLException {
/* 568 */     return getResultSetImpl(1L, 0, map);
/*     */   }
/*     */   
/*     */   public ResultSet getResultSetImpl(long index, int count, Map map) throws SQLException {
/* 575 */     if (map != null && !map.isEmpty())
/* 577 */       throw Driver.notImplemented(getClass(), "getResultSetImpl(long,int,Map)"); 
/* 581 */     if (index < 1L)
/* 583 */       throw new PSQLException(GT.tr("The array index is out of range: {0}", new Long(index)), PSQLState.DATA_ERROR); 
/* 586 */     buildArrayList();
/* 588 */     if (count == 0)
/* 590 */       count = this.arrayList.size(); 
/* 594 */     if (--index + count > this.arrayList.size())
/* 596 */       throw new PSQLException(GT.tr("The array index is out of range: {0}, number of elements: {1}.", new Object[] { new Long(index + count), new Long(this.arrayList.size()) }), PSQLState.DATA_ERROR); 
/* 599 */     Vector<byte[][]> rows = new Vector();
/* 601 */     Field[] fields = new Field[2];
/* 604 */     if (this.arrayList.dimensionsCount <= 1) {
/* 607 */       int baseOid = this.connection.getTypeInfo().getPGArrayElement(this.oid);
/* 608 */       fields[0] = new Field("INDEX", 23);
/* 609 */       fields[1] = new Field("VALUE", baseOid);
/* 611 */       for (int i = 0; i < count; i++) {
/* 613 */         int offset = (int)index + i;
/* 614 */         byte[][] t = new byte[2][0];
/* 615 */         String v = (String)this.arrayList.get(offset);
/* 616 */         t[0] = this.connection.encodeString(Integer.toString(offset + 1));
/* 617 */         t[1] = (v == null) ? null : this.connection.encodeString(v);
/* 618 */         rows.add(t);
/*     */       } 
/*     */     } else {
/* 625 */       fields[0] = new Field("INDEX", 23);
/* 626 */       fields[1] = new Field("VALUE", this.oid);
/* 627 */       for (int i = 0; i < count; i++) {
/* 629 */         int offset = (int)index + i;
/* 630 */         byte[][] t = new byte[2][0];
/* 631 */         Object v = this.arrayList.get(offset);
/* 633 */         t[0] = this.connection.encodeString(Integer.toString(offset + 1));
/* 634 */         t[1] = (v == null) ? null : this.connection.encodeString(toString((PgArrayList)v));
/* 635 */         rows.add(t);
/*     */       } 
/*     */     } 
/* 639 */     BaseStatement stat = (BaseStatement)this.connection.createStatement(1004, 1007);
/* 640 */     return stat.createDriverResultSet(fields, rows);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 645 */     return this.fieldString;
/*     */   }
/*     */   
/*     */   private String toString(PgArrayList list) throws SQLException {
/* 653 */     StringBuffer b = (new StringBuffer()).append('{');
/* 655 */     char delim = this.connection.getTypeInfo().getArrayDelimiter(this.oid);
/* 657 */     for (int i = 0; i < list.size(); i++) {
/* 659 */       Object v = list.get(i);
/* 661 */       if (i > 0)
/* 662 */         b.append(delim); 
/* 664 */       if (v == null) {
/* 665 */         b.append("NULL");
/* 667 */       } else if (v instanceof PgArrayList) {
/* 668 */         b.append(toString((PgArrayList)v));
/*     */       } else {
/* 671 */         escapeArrayElement(b, (String)v);
/*     */       } 
/*     */     } 
/* 674 */     b.append('}');
/* 676 */     return b.toString();
/*     */   }
/*     */   
/*     */   public static void escapeArrayElement(StringBuffer b, String s) {
/* 681 */     b.append('"');
/* 682 */     for (int j = 0; j < s.length(); j++) {
/* 683 */       char c = s.charAt(j);
/* 684 */       if (c == '"' || c == '\\')
/* 685 */         b.append('\\'); 
/* 688 */       b.append(c);
/*     */     } 
/* 690 */     b.append('"');
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc2\AbstractJdbc2Array.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */