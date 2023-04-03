/*      */ package org.postgresql.jdbc2;
/*      */ 
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.CharArrayReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.Reader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.net.URL;
/*      */ import java.sql.Array;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Date;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.Ref;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.Statement;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import org.postgresql.Driver;
/*      */ import org.postgresql.PGRefCursorResultSet;
/*      */ import org.postgresql.PGResultSetMetaData;
/*      */ import org.postgresql.core.BaseConnection;
/*      */ import org.postgresql.core.BaseResultSet;
/*      */ import org.postgresql.core.BaseStatement;
/*      */ import org.postgresql.core.Encoding;
/*      */ import org.postgresql.core.Field;
/*      */ import org.postgresql.core.Query;
/*      */ import org.postgresql.core.ResultCursor;
/*      */ import org.postgresql.core.ResultHandler;
/*      */ import org.postgresql.core.Utils;
/*      */ import org.postgresql.largeobject.LargeObject;
/*      */ import org.postgresql.largeobject.LargeObjectManager;
/*      */ import org.postgresql.util.GT;
/*      */ import org.postgresql.util.PGbytea;
/*      */ import org.postgresql.util.PGobject;
/*      */ import org.postgresql.util.PGtokenizer;
/*      */ import org.postgresql.util.PSQLException;
/*      */ import org.postgresql.util.PSQLState;
/*      */ 
/*      */ public abstract class AbstractJdbc2ResultSet implements BaseResultSet, PGRefCursorResultSet {
/*      */   private boolean updateable = false;
/*      */   
/*      */   private boolean doingUpdates = false;
/*      */   
/*   44 */   private HashMap updateValues = null;
/*      */   
/*      */   private boolean usingOID = false;
/*      */   
/*      */   private Vector primaryKeys;
/*      */   
/*      */   private boolean singleTable = false;
/*      */   
/*   48 */   private String onlyTable = "";
/*      */   
/*   49 */   private String tableName = null;
/*      */   
/*   50 */   private PreparedStatement updateStatement = null;
/*      */   
/*   51 */   private PreparedStatement insertStatement = null;
/*      */   
/*   52 */   private PreparedStatement deleteStatement = null;
/*      */   
/*   53 */   private PreparedStatement selectStatement = null;
/*      */   
/*      */   private final int resultsettype;
/*      */   
/*      */   private final int resultsetconcurrency;
/*      */   
/*   56 */   private int fetchdirection = 1002;
/*      */   
/*      */   protected final BaseConnection connection;
/*      */   
/*      */   protected final BaseStatement statement;
/*      */   
/*      */   protected final Field[] fields;
/*      */   
/*      */   protected final Query originalQuery;
/*      */   
/*      */   protected final int maxRows;
/*      */   
/*      */   protected final int maxFieldSize;
/*      */   
/*      */   protected Vector rows;
/*      */   
/*   66 */   protected int current_row = -1;
/*      */   
/*      */   protected int row_offset;
/*      */   
/*      */   protected byte[][] this_row;
/*      */   
/*   69 */   protected SQLWarning warnings = null;
/*      */   
/*      */   protected boolean wasNullFlag = false;
/*      */   
/*      */   protected boolean onInsertRow = false;
/*      */   
/*   78 */   private byte[][] rowBuffer = (byte[][])null;
/*      */   
/*      */   protected int fetchSize;
/*      */   
/*      */   protected ResultCursor cursor;
/*      */   
/*      */   private HashMap columnNameIndexMap;
/*      */   
/*      */   private String refCursorName;
/*      */   
/*      */   public abstract ResultSetMetaData getMetaData() throws SQLException;
/*      */   
/*      */   public AbstractJdbc2ResultSet(Query originalQuery, BaseStatement statement, Field[] fields, Vector tuples, ResultCursor cursor, int maxRows, int maxFieldSize, int rsType, int rsConcurrency) throws SQLException {
/*   92 */     this.originalQuery = originalQuery;
/*   93 */     this.connection = (BaseConnection)statement.getConnection();
/*   94 */     this.statement = statement;
/*   95 */     this.fields = fields;
/*   96 */     this.rows = tuples;
/*   97 */     this.cursor = cursor;
/*   98 */     this.maxRows = maxRows;
/*   99 */     this.maxFieldSize = maxFieldSize;
/*  100 */     this.resultsettype = rsType;
/*  101 */     this.resultsetconcurrency = rsConcurrency;
/*      */   }
/*      */   
/*      */   public URL getURL(int columnIndex) throws SQLException {
/*  106 */     checkClosed();
/*  107 */     throw Driver.notImplemented(getClass(), "getURL(int)");
/*      */   }
/*      */   
/*      */   public URL getURL(String columnName) throws SQLException {
/*  113 */     return getURL(findColumn(columnName));
/*      */   }
/*      */   
/*      */   protected Object internalGetObject(int columnIndex, Field field) throws SQLException {
/*  118 */     switch (getSQLType(columnIndex)) {
/*      */       case -7:
/*  122 */         return getBoolean(columnIndex) ? Boolean.TRUE : Boolean.FALSE;
/*      */       case -6:
/*      */       case 4:
/*      */       case 5:
/*  126 */         return new Integer(getInt(columnIndex));
/*      */       case -5:
/*  128 */         return new Long(getLong(columnIndex));
/*      */       case 2:
/*      */       case 3:
/*  131 */         return getBigDecimal(columnIndex, (field.getMod() == -1) ? -1 : (field.getMod() - 4 & 0xFFFF));
/*      */       case 7:
/*  134 */         return new Float(getFloat(columnIndex));
/*      */       case 6:
/*      */       case 8:
/*  137 */         return new Double(getDouble(columnIndex));
/*      */       case -1:
/*      */       case 1:
/*      */       case 12:
/*  141 */         return getString(columnIndex);
/*      */       case 91:
/*  143 */         return getDate(columnIndex);
/*      */       case 92:
/*  145 */         return getTime(columnIndex);
/*      */       case 93:
/*  147 */         return getTimestamp(columnIndex, (Calendar)null);
/*      */       case -4:
/*      */       case -3:
/*      */       case -2:
/*  151 */         return getBytes(columnIndex);
/*      */       case 2003:
/*  153 */         return getArray(columnIndex);
/*      */       case 2005:
/*  155 */         return getClob(columnIndex);
/*      */       case 2004:
/*  157 */         return getBlob(columnIndex);
/*      */     } 
/*  160 */     String type = getPGType(columnIndex);
/*  163 */     if (type.equals("unknown"))
/*  164 */       return getString(columnIndex); 
/*  166 */     if (type.equals("uuid"))
/*  167 */       return getUUID(getString(columnIndex)); 
/*  170 */     if (type.equals("refcursor")) {
/*  173 */       String cursorName = getString(columnIndex);
/*  175 */       StringBuffer sb = new StringBuffer("FETCH ALL IN ");
/*  176 */       Utils.appendEscapedIdentifier(sb, cursorName);
/*  188 */       ResultSet rs = this.connection.execSQLQuery(sb.toString(), this.resultsettype, 1007);
/*  194 */       sb.setLength(0);
/*  195 */       sb.append("CLOSE ");
/*  196 */       Utils.appendEscapedIdentifier(sb, cursorName);
/*  197 */       this.connection.execSQLUpdate(sb.toString());
/*  198 */       ((AbstractJdbc2ResultSet)rs).setRefCursor(cursorName);
/*  199 */       return rs;
/*      */     } 
/*  203 */     return null;
/*      */   }
/*      */   
/*      */   private void checkScrollable() throws SQLException {
/*  209 */     checkClosed();
/*  210 */     if (this.resultsettype == 1003)
/*  211 */       throw new PSQLException(GT.tr("Operation requires a scrollable ResultSet, but this ResultSet is FORWARD_ONLY."), PSQLState.INVALID_CURSOR_STATE); 
/*      */   }
/*      */   
/*      */   public boolean absolute(int index) throws SQLException {
/*      */     int internalIndex;
/*  217 */     checkScrollable();
/*  222 */     if (index == 0) {
/*  224 */       beforeFirst();
/*  225 */       return false;
/*      */     } 
/*  228 */     int rows_size = this.rows.size();
/*  232 */     if (index < 0) {
/*  234 */       if (index >= -rows_size) {
/*  235 */         internalIndex = rows_size + index;
/*      */       } else {
/*  238 */         beforeFirst();
/*  239 */         return false;
/*      */       } 
/*  247 */     } else if (index <= rows_size) {
/*  248 */       internalIndex = index - 1;
/*      */     } else {
/*  251 */       afterLast();
/*  252 */       return false;
/*      */     } 
/*  256 */     this.current_row = internalIndex;
/*  257 */     initRowBuffer();
/*  258 */     this.onInsertRow = false;
/*  260 */     return true;
/*      */   }
/*      */   
/*      */   public void afterLast() throws SQLException {
/*  266 */     checkScrollable();
/*  268 */     int rows_size = this.rows.size();
/*  269 */     if (rows_size > 0)
/*  270 */       this.current_row = rows_size; 
/*  272 */     this.onInsertRow = false;
/*  273 */     this.this_row = (byte[][])null;
/*  274 */     this.rowBuffer = (byte[][])null;
/*      */   }
/*      */   
/*      */   public void beforeFirst() throws SQLException {
/*  280 */     checkScrollable();
/*  282 */     if (this.rows.size() > 0)
/*  283 */       this.current_row = -1; 
/*  285 */     this.onInsertRow = false;
/*  286 */     this.this_row = (byte[][])null;
/*  287 */     this.rowBuffer = (byte[][])null;
/*      */   }
/*      */   
/*      */   public boolean first() throws SQLException {
/*  293 */     checkScrollable();
/*  295 */     if (this.rows.size() <= 0)
/*  296 */       return false; 
/*  298 */     this.current_row = 0;
/*  299 */     initRowBuffer();
/*  300 */     this.onInsertRow = false;
/*  302 */     return true;
/*      */   }
/*      */   
/*      */   public Array getArray(String colName) throws SQLException {
/*  308 */     return getArray(findColumn(colName));
/*      */   }
/*      */   
/*      */   public Array getArray(int i) throws SQLException {
/*  314 */     checkResultSet(i);
/*  315 */     if (this.wasNullFlag)
/*  316 */       return null; 
/*  318 */     return createArray(i);
/*      */   }
/*      */   
/*      */   public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
/*  324 */     return getBigDecimal(columnIndex, -1);
/*      */   }
/*      */   
/*      */   public BigDecimal getBigDecimal(String columnName) throws SQLException {
/*  330 */     return getBigDecimal(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public Blob getBlob(String columnName) throws SQLException {
/*  336 */     return getBlob(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public abstract Blob getBlob(int paramInt) throws SQLException;
/*      */   
/*      */   public Reader getCharacterStream(String columnName) throws SQLException {
/*  345 */     return getCharacterStream(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public Reader getCharacterStream(int i) throws SQLException {
/*  351 */     checkResultSet(i);
/*  352 */     if (this.wasNullFlag)
/*  353 */       return null; 
/*  355 */     if (((AbstractJdbc2Connection)this.connection).haveMinimumCompatibleVersion("7.2"))
/*  363 */       return new CharArrayReader(getString(i).toCharArray()); 
/*  368 */     Encoding encoding = this.connection.getEncoding();
/*  369 */     InputStream input = getBinaryStream(i);
/*      */     try {
/*  373 */       return encoding.getDecodingReader(input);
/*  375 */     } catch (IOException ioe) {
/*  377 */       throw new PSQLException(GT.tr("Unexpected error while decoding character data from a large object."), PSQLState.UNEXPECTED_ERROR, ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Clob getClob(String columnName) throws SQLException {
/*  385 */     return getClob(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public abstract Clob getClob(int paramInt) throws SQLException;
/*      */   
/*      */   public int getConcurrency() throws SQLException {
/*  394 */     checkClosed();
/*  395 */     return this.resultsetconcurrency;
/*      */   }
/*      */   
/*      */   public Date getDate(int i, Calendar cal) throws SQLException {
/*  401 */     checkResultSet(i);
/*  402 */     if (this.wasNullFlag)
/*  403 */       return null; 
/*  405 */     if (cal != null)
/*  406 */       cal = (Calendar)cal.clone(); 
/*  408 */     return this.connection.getTimestampUtils().toDate(cal, getString(i));
/*      */   }
/*      */   
/*      */   public Time getTime(int i, Calendar cal) throws SQLException {
/*  414 */     checkResultSet(i);
/*  415 */     if (this.wasNullFlag)
/*  416 */       return null; 
/*  418 */     if (cal != null)
/*  419 */       cal = (Calendar)cal.clone(); 
/*  421 */     return this.connection.getTimestampUtils().toTime(cal, getString(i));
/*      */   }
/*      */   
/*      */   public Timestamp getTimestamp(int i, Calendar cal) throws SQLException {
/*  427 */     checkResultSet(i);
/*  428 */     if (this.wasNullFlag)
/*  429 */       return null; 
/*  431 */     if (cal != null)
/*  432 */       cal = (Calendar)cal.clone(); 
/*  437 */     return this.connection.getTimestampUtils().toTimestamp(cal, getString(i));
/*      */   }
/*      */   
/*      */   public Date getDate(String c, Calendar cal) throws SQLException {
/*  443 */     return getDate(findColumn(c), cal);
/*      */   }
/*      */   
/*      */   public Time getTime(String c, Calendar cal) throws SQLException {
/*  449 */     return getTime(findColumn(c), cal);
/*      */   }
/*      */   
/*      */   public Timestamp getTimestamp(String c, Calendar cal) throws SQLException {
/*  455 */     return getTimestamp(findColumn(c), cal);
/*      */   }
/*      */   
/*      */   public int getFetchDirection() throws SQLException {
/*  461 */     checkClosed();
/*  462 */     return this.fetchdirection;
/*      */   }
/*      */   
/*      */   public Object getObjectImpl(String columnName, Map map) throws SQLException {
/*  468 */     return getObjectImpl(findColumn(columnName), map);
/*      */   }
/*      */   
/*      */   public Object getObjectImpl(int i, Map map) throws SQLException {
/*  479 */     checkClosed();
/*  480 */     if (map == null || map.isEmpty())
/*  481 */       return getObject(i); 
/*  483 */     throw Driver.notImplemented(getClass(), "getObjectImpl(int,Map)");
/*      */   }
/*      */   
/*      */   public Ref getRef(String columnName) throws SQLException {
/*  489 */     return getRef(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public Ref getRef(int i) throws SQLException {
/*  495 */     checkClosed();
/*  497 */     throw Driver.notImplemented(getClass(), "getRef(int)");
/*      */   }
/*      */   
/*      */   public int getRow() throws SQLException {
/*  503 */     checkClosed();
/*  505 */     if (this.onInsertRow)
/*  506 */       return 0; 
/*  508 */     int rows_size = this.rows.size();
/*  510 */     if (this.current_row < 0 || this.current_row >= rows_size)
/*  511 */       return 0; 
/*  513 */     return this.row_offset + this.current_row + 1;
/*      */   }
/*      */   
/*      */   public Statement getStatement() throws SQLException {
/*  520 */     checkClosed();
/*  521 */     return (Statement)this.statement;
/*      */   }
/*      */   
/*      */   public int getType() throws SQLException {
/*  527 */     checkClosed();
/*  528 */     return this.resultsettype;
/*      */   }
/*      */   
/*      */   public boolean isAfterLast() throws SQLException {
/*  534 */     checkClosed();
/*  535 */     if (this.onInsertRow)
/*  536 */       return false; 
/*  538 */     int rows_size = this.rows.size();
/*  539 */     return (this.current_row >= rows_size && rows_size > 0);
/*      */   }
/*      */   
/*      */   public boolean isBeforeFirst() throws SQLException {
/*  545 */     checkClosed();
/*  546 */     if (this.onInsertRow)
/*  547 */       return false; 
/*  549 */     return (this.row_offset + this.current_row < 0 && this.rows.size() > 0);
/*      */   }
/*      */   
/*      */   public boolean isFirst() throws SQLException {
/*  555 */     checkClosed();
/*  556 */     if (this.onInsertRow)
/*  557 */       return false; 
/*  559 */     return (this.row_offset + this.current_row == 0);
/*      */   }
/*      */   
/*      */   public boolean isLast() throws SQLException {
/*  565 */     checkClosed();
/*  566 */     if (this.onInsertRow)
/*  567 */       return false; 
/*  569 */     int rows_size = this.rows.size();
/*  571 */     if (rows_size == 0)
/*  572 */       return false; 
/*  574 */     if (this.current_row != rows_size - 1)
/*  575 */       return false; 
/*  579 */     if (this.cursor == null)
/*  582 */       return true; 
/*  585 */     if (this.maxRows > 0 && this.row_offset + this.current_row == this.maxRows)
/*  588 */       return true; 
/*  600 */     this.row_offset += rows_size - 1;
/*  603 */     int fetchRows = this.fetchSize;
/*  604 */     if (this.maxRows != 0)
/*  606 */       if (fetchRows == 0 || this.row_offset + fetchRows > this.maxRows)
/*  607 */         fetchRows = this.maxRows - this.row_offset;  
/*  611 */     this.connection.getQueryExecutor().fetch(this.cursor, new CursorResultHandler(), fetchRows);
/*  614 */     this.rows.insertElementAt(this.this_row, 0);
/*  615 */     this.current_row = 0;
/*  618 */     return (this.rows.size() == 1);
/*      */   }
/*      */   
/*      */   public boolean last() throws SQLException {
/*  623 */     checkScrollable();
/*  625 */     int rows_size = this.rows.size();
/*  626 */     if (rows_size <= 0)
/*  627 */       return false; 
/*  629 */     this.current_row = rows_size - 1;
/*  630 */     initRowBuffer();
/*  631 */     this.onInsertRow = false;
/*  633 */     return true;
/*      */   }
/*      */   
/*      */   public boolean previous() throws SQLException {
/*  639 */     checkScrollable();
/*  641 */     if (this.onInsertRow)
/*  642 */       throw new PSQLException(GT.tr("Can''t use relative move methods while on the insert row."), PSQLState.INVALID_CURSOR_STATE); 
/*  645 */     if (this.current_row - 1 < 0) {
/*  647 */       this.current_row = -1;
/*  648 */       this.this_row = (byte[][])null;
/*  649 */       this.rowBuffer = (byte[][])null;
/*  650 */       return false;
/*      */     } 
/*  654 */     this.current_row--;
/*  656 */     initRowBuffer();
/*  657 */     return true;
/*      */   }
/*      */   
/*      */   public boolean relative(int rows) throws SQLException {
/*  663 */     checkScrollable();
/*  665 */     if (this.onInsertRow)
/*  666 */       throw new PSQLException(GT.tr("Can''t use relative move methods while on the insert row."), PSQLState.INVALID_CURSOR_STATE); 
/*  670 */     return absolute(this.current_row + 1 + rows);
/*      */   }
/*      */   
/*      */   public void setFetchDirection(int direction) throws SQLException {
/*  676 */     checkClosed();
/*  677 */     switch (direction) {
/*      */       case 1000:
/*      */         break;
/*      */       case 1001:
/*      */       case 1002:
/*  683 */         checkScrollable();
/*      */         break;
/*      */       default:
/*  686 */         throw new PSQLException(GT.tr("Invalid fetch direction constant: {0}.", new Integer(direction)), PSQLState.INVALID_PARAMETER_VALUE);
/*      */     } 
/*  690 */     this.fetchdirection = direction;
/*      */   }
/*      */   
/*      */   public synchronized void cancelRowUpdates() throws SQLException {
/*  697 */     checkClosed();
/*  698 */     if (this.onInsertRow)
/*  700 */       throw new PSQLException(GT.tr("Cannot call cancelRowUpdates() when on the insert row."), PSQLState.INVALID_CURSOR_STATE); 
/*  704 */     if (this.doingUpdates) {
/*  706 */       this.doingUpdates = false;
/*  708 */       clearRowBuffer(true);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void deleteRow() throws SQLException {
/*  716 */     checkUpdateable();
/*  718 */     if (this.onInsertRow)
/*  720 */       throw new PSQLException(GT.tr("Cannot call deleteRow() when on the insert row."), PSQLState.INVALID_CURSOR_STATE); 
/*  724 */     if (isBeforeFirst())
/*  726 */       throw new PSQLException(GT.tr("Currently positioned before the start of the ResultSet.  You cannot call deleteRow() here."), PSQLState.INVALID_CURSOR_STATE); 
/*  729 */     if (isAfterLast())
/*  731 */       throw new PSQLException(GT.tr("Currently positioned after the end of the ResultSet.  You cannot call deleteRow() here."), PSQLState.INVALID_CURSOR_STATE); 
/*  734 */     if (this.rows.size() == 0)
/*  736 */       throw new PSQLException(GT.tr("There are no rows in this ResultSet."), PSQLState.INVALID_CURSOR_STATE); 
/*  741 */     int numKeys = this.primaryKeys.size();
/*  742 */     if (this.deleteStatement == null) {
/*  746 */       StringBuffer deleteSQL = (new StringBuffer("DELETE FROM ")).append(this.onlyTable).append(this.tableName).append(" where ");
/*  748 */       for (int j = 0; j < numKeys; j++) {
/*  750 */         Utils.appendEscapedIdentifier(deleteSQL, ((PrimaryKey)this.primaryKeys.get(j)).name);
/*  751 */         deleteSQL.append(" = ?");
/*  752 */         if (j < numKeys - 1)
/*  754 */           deleteSQL.append(" and "); 
/*      */       } 
/*  758 */       this.deleteStatement = this.connection.prepareStatement(deleteSQL.toString());
/*      */     } 
/*  760 */     this.deleteStatement.clearParameters();
/*  762 */     for (int i = 0; i < numKeys; i++)
/*  764 */       this.deleteStatement.setObject(i + 1, ((PrimaryKey)this.primaryKeys.get(i)).getValue()); 
/*  768 */     this.deleteStatement.executeUpdate();
/*  770 */     this.rows.removeElementAt(this.current_row);
/*  771 */     this.current_row--;
/*  772 */     moveToCurrentRow();
/*      */   }
/*      */   
/*      */   public synchronized void insertRow() throws SQLException {
/*  779 */     checkUpdateable();
/*  781 */     if (!this.onInsertRow)
/*  783 */       throw new PSQLException(GT.tr("Not on the insert row."), PSQLState.INVALID_CURSOR_STATE); 
/*  785 */     if (this.updateValues.size() == 0)
/*  787 */       throw new PSQLException(GT.tr("You must specify at least one column value to insert a row."), PSQLState.INVALID_PARAMETER_VALUE); 
/*  797 */     StringBuffer insertSQL = (new StringBuffer("INSERT INTO ")).append(this.tableName).append(" (");
/*  798 */     StringBuffer paramSQL = new StringBuffer(") values (");
/*  800 */     Iterator<String> columnNames = this.updateValues.keySet().iterator();
/*  801 */     int numColumns = this.updateValues.size();
/*  803 */     for (int i = 0; columnNames.hasNext(); i++) {
/*  805 */       String columnName = columnNames.next();
/*  807 */       Utils.appendEscapedIdentifier(insertSQL, columnName);
/*  808 */       if (i < numColumns - 1) {
/*  810 */         insertSQL.append(", ");
/*  811 */         paramSQL.append("?,");
/*      */       } else {
/*  815 */         paramSQL.append("?)");
/*      */       } 
/*      */     } 
/*  820 */     insertSQL.append(paramSQL.toString());
/*  821 */     this.insertStatement = this.connection.prepareStatement(insertSQL.toString());
/*  823 */     Iterator<String> keys = this.updateValues.keySet().iterator();
/*  825 */     for (int j = 1; keys.hasNext(); j++) {
/*  827 */       String key = keys.next();
/*  828 */       Object o = this.updateValues.get(key);
/*  829 */       this.insertStatement.setObject(j, o);
/*      */     } 
/*  832 */     this.insertStatement.executeUpdate();
/*  834 */     if (this.usingOID) {
/*  838 */       long insertedOID = ((AbstractJdbc2Statement)this.insertStatement).getLastOID();
/*  840 */       this.updateValues.put("oid", new Long(insertedOID));
/*      */     } 
/*  845 */     updateRowBuffer();
/*  847 */     this.rows.addElement(this.rowBuffer);
/*  851 */     this.this_row = this.rowBuffer;
/*  854 */     clearRowBuffer(false);
/*      */   }
/*      */   
/*      */   public synchronized void moveToCurrentRow() throws SQLException {
/*  864 */     checkUpdateable();
/*  866 */     if (this.current_row < 0 || this.current_row >= this.rows.size()) {
/*  868 */       this.this_row = (byte[][])null;
/*  869 */       this.rowBuffer = (byte[][])null;
/*      */     } else {
/*  873 */       initRowBuffer();
/*      */     } 
/*  876 */     this.onInsertRow = false;
/*  877 */     this.doingUpdates = false;
/*      */   }
/*      */   
/*      */   public synchronized void moveToInsertRow() throws SQLException {
/*  884 */     checkUpdateable();
/*  886 */     if (this.insertStatement != null)
/*  888 */       this.insertStatement = null; 
/*  893 */     clearRowBuffer(false);
/*  895 */     this.onInsertRow = true;
/*  896 */     this.doingUpdates = false;
/*      */   }
/*      */   
/*      */   private synchronized void clearRowBuffer(boolean copyCurrentRow) throws SQLException {
/*  905 */     this.rowBuffer = new byte[this.fields.length][];
/*  908 */     if (copyCurrentRow)
/*  910 */       System.arraycopy(this.this_row, 0, this.rowBuffer, 0, this.this_row.length); 
/*  914 */     this.updateValues.clear();
/*      */   }
/*      */   
/*      */   public boolean rowDeleted() throws SQLException {
/*  921 */     checkClosed();
/*  922 */     return false;
/*      */   }
/*      */   
/*      */   public boolean rowInserted() throws SQLException {
/*  928 */     checkClosed();
/*  929 */     return false;
/*      */   }
/*      */   
/*      */   public boolean rowUpdated() throws SQLException {
/*  935 */     checkClosed();
/*  936 */     return false;
/*      */   }
/*      */   
/*      */   public synchronized void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
/*  946 */     if (x == null) {
/*  948 */       updateNull(columnIndex);
/*      */       return;
/*      */     } 
/*      */     try {
/*  954 */       InputStreamReader reader = new InputStreamReader(x, "ASCII");
/*  955 */       char[] data = new char[length];
/*  956 */       int numRead = 0;
/*      */       do {
/*  959 */         int n = reader.read(data, numRead, length - numRead);
/*  960 */         if (n == -1)
/*      */           break; 
/*  963 */         numRead += n;
/*  965 */       } while (numRead != length);
/*  968 */       updateString(columnIndex, new String(data, 0, numRead));
/*  970 */     } catch (UnsupportedEncodingException uee) {
/*  972 */       throw new PSQLException(GT.tr("The JVM claims not to support the encoding: {0}", "ASCII"), PSQLState.UNEXPECTED_ERROR, uee);
/*  974 */     } catch (IOException ie) {
/*  976 */       throw new PSQLException(GT.tr("Provided InputStream failed."), null, ie);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
/*  985 */     updateValue(columnIndex, x);
/*      */   }
/*      */   
/*      */   public synchronized void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
/*  995 */     if (x == null) {
/*  997 */       updateNull(columnIndex);
/*      */       return;
/*      */     } 
/* 1001 */     byte[] data = new byte[length];
/* 1002 */     int numRead = 0;
/*      */     try {
/*      */       do {
/* 1007 */         int n = x.read(data, numRead, length - numRead);
/* 1008 */         if (n == -1)
/*      */           break; 
/* 1011 */         numRead += n;
/* 1013 */       } while (numRead != length);
/* 1017 */     } catch (IOException ie) {
/* 1019 */       throw new PSQLException(GT.tr("Provided InputStream failed."), null, ie);
/*      */     } 
/* 1022 */     if (numRead == length) {
/* 1024 */       updateBytes(columnIndex, data);
/*      */     } else {
/* 1030 */       byte[] data2 = new byte[numRead];
/* 1031 */       System.arraycopy(data, 0, data2, 0, numRead);
/* 1032 */       updateBytes(columnIndex, data2);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void updateBoolean(int columnIndex, boolean x) throws SQLException {
/* 1040 */     updateValue(columnIndex, new Boolean(x));
/*      */   }
/*      */   
/*      */   public synchronized void updateByte(int columnIndex, byte x) throws SQLException {
/* 1047 */     updateValue(columnIndex, String.valueOf(x));
/*      */   }
/*      */   
/*      */   public synchronized void updateBytes(int columnIndex, byte[] x) throws SQLException {
/* 1054 */     updateValue(columnIndex, x);
/*      */   }
/*      */   
/*      */   public synchronized void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
/* 1064 */     if (x == null) {
/* 1066 */       updateNull(columnIndex);
/*      */       return;
/*      */     } 
/*      */     try {
/* 1072 */       char[] data = new char[length];
/* 1073 */       int numRead = 0;
/*      */       do {
/* 1076 */         int n = x.read(data, numRead, length - numRead);
/* 1077 */         if (n == -1)
/*      */           break; 
/* 1080 */         numRead += n;
/* 1082 */       } while (numRead != length);
/* 1085 */       updateString(columnIndex, new String(data, 0, numRead));
/* 1087 */     } catch (IOException ie) {
/* 1089 */       throw new PSQLException(GT.tr("Provided Reader failed."), null, ie);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void updateDate(int columnIndex, Date x) throws SQLException {
/* 1097 */     updateValue(columnIndex, x);
/*      */   }
/*      */   
/*      */   public synchronized void updateDouble(int columnIndex, double x) throws SQLException {
/* 1104 */     updateValue(columnIndex, new Double(x));
/*      */   }
/*      */   
/*      */   public synchronized void updateFloat(int columnIndex, float x) throws SQLException {
/* 1111 */     updateValue(columnIndex, new Float(x));
/*      */   }
/*      */   
/*      */   public synchronized void updateInt(int columnIndex, int x) throws SQLException {
/* 1118 */     updateValue(columnIndex, new Integer(x));
/*      */   }
/*      */   
/*      */   public synchronized void updateLong(int columnIndex, long x) throws SQLException {
/* 1125 */     updateValue(columnIndex, new Long(x));
/*      */   }
/*      */   
/*      */   public synchronized void updateNull(int columnIndex) throws SQLException {
/* 1132 */     checkColumnIndex(columnIndex);
/* 1133 */     String columnTypeName = this.connection.getTypeInfo().getPGType(this.fields[columnIndex - 1].getOID());
/* 1134 */     updateValue(columnIndex, new NullObject(columnTypeName));
/*      */   }
/*      */   
/*      */   public synchronized void updateObject(int columnIndex, Object x) throws SQLException {
/* 1141 */     updateValue(columnIndex, x);
/*      */   }
/*      */   
/*      */   public synchronized void updateObject(int columnIndex, Object x, int scale) throws SQLException {
/* 1148 */     updateObject(columnIndex, x);
/*      */   }
/*      */   
/*      */   public void refreshRow() throws SQLException {
/* 1155 */     checkUpdateable();
/* 1156 */     if (this.onInsertRow)
/* 1157 */       throw new PSQLException(GT.tr("Can''t refresh the insert row."), PSQLState.INVALID_CURSOR_STATE); 
/* 1160 */     if (isBeforeFirst() || isAfterLast() || this.rows.size() == 0)
/*      */       return; 
/* 1163 */     StringBuffer selectSQL = new StringBuffer("select ");
/* 1165 */     ResultSetMetaData rsmd = getMetaData();
/* 1166 */     PGResultSetMetaData pgmd = (PGResultSetMetaData)rsmd;
/* 1167 */     for (int i = 1; i <= rsmd.getColumnCount(); i++) {
/* 1168 */       if (i > 1)
/* 1169 */         selectSQL.append(", "); 
/* 1171 */       selectSQL.append(pgmd.getBaseColumnName(i));
/*      */     } 
/* 1173 */     selectSQL.append(" from ").append(this.onlyTable).append(this.tableName).append(" where ");
/* 1175 */     int numKeys = this.primaryKeys.size();
/* 1177 */     for (int k = 0; k < numKeys; k++) {
/* 1180 */       PrimaryKey primaryKey = this.primaryKeys.get(k);
/* 1181 */       selectSQL.append(primaryKey.name).append("= ?");
/* 1183 */       if (k < numKeys - 1)
/* 1185 */         selectSQL.append(" and "); 
/*      */     } 
/* 1188 */     if (this.connection.getLogger().logDebug())
/* 1189 */       this.connection.getLogger().debug("selecting " + selectSQL.toString()); 
/* 1190 */     this.selectStatement = this.connection.prepareStatement(selectSQL.toString());
/* 1193 */     for (int j = 0, m = 1; j < numKeys; j++, m++)
/* 1195 */       this.selectStatement.setObject(m, ((PrimaryKey)this.primaryKeys.get(j)).getValue()); 
/* 1198 */     AbstractJdbc2ResultSet rs = (AbstractJdbc2ResultSet)this.selectStatement.executeQuery();
/* 1200 */     if (rs.next())
/* 1202 */       this.rowBuffer = rs.this_row; 
/* 1205 */     this.rows.setElementAt(this.rowBuffer, this.current_row);
/* 1206 */     this.this_row = this.rowBuffer;
/* 1208 */     this.connection.getLogger().debug("done updates");
/* 1210 */     rs.close();
/* 1211 */     this.selectStatement.close();
/* 1212 */     this.selectStatement = null;
/*      */   }
/*      */   
/*      */   public synchronized void updateRow() throws SQLException {
/* 1220 */     checkUpdateable();
/* 1222 */     if (this.onInsertRow)
/* 1224 */       throw new PSQLException(GT.tr("Cannot call updateRow() when on the insert row."), PSQLState.INVALID_CURSOR_STATE); 
/* 1228 */     if (isBeforeFirst() || isAfterLast() || this.rows.size() == 0)
/* 1230 */       throw new PSQLException(GT.tr("Cannot update the ResultSet because it is either before the start or after the end of the results."), PSQLState.INVALID_CURSOR_STATE); 
/* 1234 */     if (!this.doingUpdates)
/*      */       return; 
/* 1237 */     StringBuffer updateSQL = new StringBuffer("UPDATE " + this.onlyTable + this.tableName + " SET  ");
/* 1239 */     int numColumns = this.updateValues.size();
/* 1240 */     Iterator<String> columns = this.updateValues.keySet().iterator();
/* 1242 */     for (int i = 0; columns.hasNext(); i++) {
/* 1244 */       String column = columns.next();
/* 1245 */       Utils.appendEscapedIdentifier(updateSQL, column);
/* 1246 */       updateSQL.append(" = ?");
/* 1248 */       if (i < numColumns - 1)
/* 1249 */         updateSQL.append(", "); 
/*      */     } 
/* 1252 */     updateSQL.append(" WHERE ");
/* 1254 */     int numKeys = this.primaryKeys.size();
/*      */     int k;
/* 1256 */     for (k = 0; k < numKeys; k++) {
/* 1258 */       PrimaryKey primaryKey = this.primaryKeys.get(k);
/* 1259 */       Utils.appendEscapedIdentifier(updateSQL, primaryKey.name);
/* 1260 */       updateSQL.append(" = ?");
/* 1262 */       if (k < numKeys - 1)
/* 1263 */         updateSQL.append(" and "); 
/*      */     } 
/* 1266 */     if (this.connection.getLogger().logDebug())
/* 1267 */       this.connection.getLogger().debug("updating " + updateSQL.toString()); 
/* 1268 */     this.updateStatement = this.connection.prepareStatement(updateSQL.toString());
/* 1270 */     k = 0;
/* 1271 */     Iterator iterator = this.updateValues.values().iterator();
/* 1272 */     for (; iterator.hasNext(); k++) {
/* 1274 */       Object o = iterator.next();
/* 1275 */       this.updateStatement.setObject(k + 1, o);
/*      */     } 
/* 1278 */     for (int j = 0; j < numKeys; j++, k++)
/* 1280 */       this.updateStatement.setObject(k + 1, ((PrimaryKey)this.primaryKeys.get(j)).getValue()); 
/* 1283 */     this.updateStatement.executeUpdate();
/* 1284 */     this.updateStatement.close();
/* 1285 */     this.updateStatement = null;
/* 1287 */     updateRowBuffer();
/* 1289 */     this.connection.getLogger().debug("copying data");
/* 1290 */     System.arraycopy(this.rowBuffer, 0, this.this_row, 0, this.rowBuffer.length);
/* 1291 */     this.rows.setElementAt(this.rowBuffer, this.current_row);
/* 1293 */     this.connection.getLogger().debug("done updates");
/* 1294 */     this.updateValues.clear();
/* 1295 */     this.doingUpdates = false;
/*      */   }
/*      */   
/*      */   public synchronized void updateShort(int columnIndex, short x) throws SQLException {
/* 1302 */     updateValue(columnIndex, new Short(x));
/*      */   }
/*      */   
/*      */   public synchronized void updateString(int columnIndex, String x) throws SQLException {
/* 1309 */     updateValue(columnIndex, x);
/*      */   }
/*      */   
/*      */   public synchronized void updateTime(int columnIndex, Time x) throws SQLException {
/* 1316 */     updateValue(columnIndex, x);
/*      */   }
/*      */   
/*      */   public synchronized void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
/* 1323 */     updateValue(columnIndex, x);
/*      */   }
/*      */   
/*      */   public synchronized void updateNull(String columnName) throws SQLException {
/* 1331 */     updateNull(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public synchronized void updateBoolean(String columnName, boolean x) throws SQLException {
/* 1338 */     updateBoolean(findColumn(columnName), x);
/*      */   }
/*      */   
/*      */   public synchronized void updateByte(String columnName, byte x) throws SQLException {
/* 1345 */     updateByte(findColumn(columnName), x);
/*      */   }
/*      */   
/*      */   public synchronized void updateShort(String columnName, short x) throws SQLException {
/* 1352 */     updateShort(findColumn(columnName), x);
/*      */   }
/*      */   
/*      */   public synchronized void updateInt(String columnName, int x) throws SQLException {
/* 1359 */     updateInt(findColumn(columnName), x);
/*      */   }
/*      */   
/*      */   public synchronized void updateLong(String columnName, long x) throws SQLException {
/* 1366 */     updateLong(findColumn(columnName), x);
/*      */   }
/*      */   
/*      */   public synchronized void updateFloat(String columnName, float x) throws SQLException {
/* 1373 */     updateFloat(findColumn(columnName), x);
/*      */   }
/*      */   
/*      */   public synchronized void updateDouble(String columnName, double x) throws SQLException {
/* 1380 */     updateDouble(findColumn(columnName), x);
/*      */   }
/*      */   
/*      */   public synchronized void updateBigDecimal(String columnName, BigDecimal x) throws SQLException {
/* 1387 */     updateBigDecimal(findColumn(columnName), x);
/*      */   }
/*      */   
/*      */   public synchronized void updateString(String columnName, String x) throws SQLException {
/* 1394 */     updateString(findColumn(columnName), x);
/*      */   }
/*      */   
/*      */   public synchronized void updateBytes(String columnName, byte[] x) throws SQLException {
/* 1401 */     updateBytes(findColumn(columnName), x);
/*      */   }
/*      */   
/*      */   public synchronized void updateDate(String columnName, Date x) throws SQLException {
/* 1408 */     updateDate(findColumn(columnName), x);
/*      */   }
/*      */   
/*      */   public synchronized void updateTime(String columnName, Time x) throws SQLException {
/* 1415 */     updateTime(findColumn(columnName), x);
/*      */   }
/*      */   
/*      */   public synchronized void updateTimestamp(String columnName, Timestamp x) throws SQLException {
/* 1422 */     updateTimestamp(findColumn(columnName), x);
/*      */   }
/*      */   
/*      */   public synchronized void updateAsciiStream(String columnName, InputStream x, int length) throws SQLException {
/* 1432 */     updateAsciiStream(findColumn(columnName), x, length);
/*      */   }
/*      */   
/*      */   public synchronized void updateBinaryStream(String columnName, InputStream x, int length) throws SQLException {
/* 1442 */     updateBinaryStream(findColumn(columnName), x, length);
/*      */   }
/*      */   
/*      */   public synchronized void updateCharacterStream(String columnName, Reader reader, int length) throws SQLException {
/* 1452 */     updateCharacterStream(findColumn(columnName), reader, length);
/*      */   }
/*      */   
/*      */   public synchronized void updateObject(String columnName, Object x, int scale) throws SQLException {
/* 1459 */     updateObject(findColumn(columnName), x);
/*      */   }
/*      */   
/*      */   public synchronized void updateObject(String columnName, Object x) throws SQLException {
/* 1466 */     updateObject(findColumn(columnName), x);
/*      */   }
/*      */   
/*      */   boolean isUpdateable() throws SQLException {
/* 1476 */     checkClosed();
/* 1478 */     if (this.resultsetconcurrency == 1007)
/* 1479 */       throw new PSQLException(GT.tr("ResultSets with concurrency CONCUR_READ_ONLY cannot be updated."), PSQLState.INVALID_CURSOR_STATE); 
/* 1482 */     if (this.updateable)
/* 1483 */       return true; 
/* 1485 */     this.connection.getLogger().debug("checking if rs is updateable");
/* 1487 */     parseQuery();
/* 1489 */     if (!this.singleTable) {
/* 1491 */       this.connection.getLogger().debug("not a single table");
/* 1492 */       return false;
/*      */     } 
/* 1495 */     this.connection.getLogger().debug("getting primary keys");
/* 1501 */     this.primaryKeys = new Vector();
/* 1507 */     this.usingOID = false;
/* 1508 */     int oidIndex = findColumnIndex("oid");
/* 1510 */     int i = 0;
/* 1515 */     if (oidIndex > 0) {
/* 1517 */       i++;
/* 1518 */       this.primaryKeys.add(new PrimaryKey(oidIndex, "oid"));
/* 1519 */       this.usingOID = true;
/*      */     } else {
/* 1524 */       String[] s = quotelessTableName(this.tableName);
/* 1525 */       String quotelessTableName = s[0];
/* 1526 */       String quotelessSchemaName = s[1];
/* 1527 */       ResultSet rs = this.connection.getMetaData().getPrimaryKeys("", quotelessSchemaName, quotelessTableName);
/* 1528 */       for (; rs.next(); i++) {
/* 1530 */         String columnName = rs.getString(4);
/* 1531 */         int index = findColumn(columnName);
/* 1533 */         if (index > 0)
/* 1535 */           this.primaryKeys.add(new PrimaryKey(index, columnName)); 
/*      */       } 
/* 1539 */       rs.close();
/*      */     } 
/* 1542 */     if (this.connection.getLogger().logDebug())
/* 1543 */       this.connection.getLogger().debug("no of keys=" + i); 
/* 1545 */     if (i < 1)
/* 1547 */       throw new PSQLException(GT.tr("No primary key found for table {0}.", this.tableName), PSQLState.DATA_ERROR); 
/* 1551 */     this.updateable = (this.primaryKeys.size() > 0);
/* 1553 */     if (this.connection.getLogger().logDebug())
/* 1554 */       this.connection.getLogger().debug("checking primary key " + this.updateable); 
/* 1556 */     return this.updateable;
/*      */   }
/*      */   
/*      */   public static String[] quotelessTableName(String fullname) {
/* 1573 */     StringBuffer buf = new StringBuffer(fullname);
/* 1574 */     String[] parts = { null, "" };
/* 1575 */     StringBuffer acc = new StringBuffer();
/* 1576 */     boolean betweenQuotes = false;
/* 1577 */     for (int i = 0; i < buf.length(); i++) {
/* 1579 */       char c = buf.charAt(i);
/* 1580 */       switch (c) {
/*      */         case '"':
/* 1583 */           if (i < buf.length() - 1 && buf.charAt(i + 1) == '"') {
/* 1586 */             i++;
/* 1587 */             acc.append(c);
/*      */             break;
/*      */           } 
/* 1591 */           betweenQuotes = !betweenQuotes;
/*      */           break;
/*      */         case '.':
/* 1595 */           if (betweenQuotes) {
/* 1597 */             acc.append(c);
/*      */             break;
/*      */           } 
/* 1601 */           parts[1] = acc.toString();
/* 1602 */           acc = new StringBuffer();
/*      */           break;
/*      */         default:
/* 1606 */           acc.append(betweenQuotes ? c : Character.toLowerCase(c));
/*      */           break;
/*      */       } 
/*      */     } 
/* 1611 */     parts[0] = acc.toString();
/* 1612 */     return parts;
/*      */   }
/*      */   
/*      */   private void parseQuery() {
/* 1617 */     String l_sql = this.originalQuery.toString(null);
/* 1618 */     StringTokenizer st = new StringTokenizer(l_sql, " \r\t\n");
/* 1619 */     boolean tableFound = false, tablesChecked = false;
/* 1620 */     String name = "";
/* 1622 */     this.singleTable = true;
/* 1624 */     while (!tableFound && !tablesChecked && st.hasMoreTokens()) {
/* 1626 */       name = st.nextToken();
/* 1627 */       if (!tableFound) {
/* 1629 */         if ("from".equalsIgnoreCase(name)) {
/* 1631 */           this.tableName = st.nextToken();
/* 1632 */           if ("only".equalsIgnoreCase(this.tableName)) {
/* 1633 */             this.tableName = st.nextToken();
/* 1634 */             this.onlyTable = "ONLY ";
/*      */           } 
/* 1636 */           tableFound = true;
/*      */         } 
/*      */         continue;
/*      */       } 
/* 1641 */       tablesChecked = true;
/* 1643 */       this.singleTable = !name.equalsIgnoreCase(",");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateRowBuffer() throws SQLException {
/* 1652 */     Iterator<String> columns = this.updateValues.keySet().iterator();
/* 1654 */     while (columns.hasNext()) {
/* 1656 */       String columnName = columns.next();
/* 1657 */       int columnIndex = findColumn(columnName) - 1;
/* 1659 */       Object valueObject = this.updateValues.get(columnName);
/* 1660 */       if (valueObject instanceof NullObject) {
/* 1662 */         this.rowBuffer[columnIndex] = null;
/*      */         continue;
/*      */       } 
/* 1666 */       switch (getSQLType(columnIndex + 1)) {
/*      */         case 91:
/* 1675 */           this.rowBuffer[columnIndex] = this.connection.encodeString(this.connection.getTimestampUtils().toString((Calendar)null, (Date)valueObject));
/*      */           continue;
/*      */         case 92:
/* 1680 */           this.rowBuffer[columnIndex] = this.connection.encodeString(this.connection.getTimestampUtils().toString((Calendar)null, (Time)valueObject));
/*      */           continue;
/*      */         case 93:
/* 1685 */           this.rowBuffer[columnIndex] = this.connection.encodeString(this.connection.getTimestampUtils().toString((Calendar)null, (Timestamp)valueObject));
/*      */           continue;
/*      */         case 0:
/*      */           continue;
/*      */         case -4:
/*      */         case -3:
/*      */         case -2:
/* 1696 */           if (this.fields[columnIndex].getFormat() == 1) {
/* 1697 */             this.rowBuffer[columnIndex] = (byte[])valueObject;
/*      */             continue;
/*      */           } 
/*      */           try {
/* 1700 */             this.rowBuffer[columnIndex] = PGbytea.toPGString((byte[])valueObject).getBytes("ISO-8859-1");
/* 1701 */           } catch (UnsupportedEncodingException e) {
/* 1702 */             throw new PSQLException(GT.tr("The JVM claims not to support the encoding: {0}", "ISO-8859-1"), PSQLState.UNEXPECTED_ERROR, e);
/*      */           } 
/*      */           continue;
/*      */       } 
/* 1708 */       this.rowBuffer[columnIndex] = this.connection.encodeString(String.valueOf(valueObject));
/*      */     } 
/*      */   }
/*      */   
/*      */   public class CursorResultHandler implements ResultHandler {
/*      */     private SQLException error;
/*      */     
/*      */     public void handleResultRows(Query fromQuery, Field[] fields, Vector tuples, ResultCursor cursor) {
/* 1720 */       AbstractJdbc2ResultSet.this.rows = tuples;
/* 1721 */       AbstractJdbc2ResultSet.this.cursor = cursor;
/*      */     }
/*      */     
/*      */     public void handleCommandStatus(String status, int updateCount, long insertOID) {
/* 1725 */       handleError((SQLException)new PSQLException(GT.tr("Unexpected command status: {0}.", status), PSQLState.PROTOCOL_VIOLATION));
/*      */     }
/*      */     
/*      */     public void handleWarning(SQLWarning warning) {
/* 1730 */       AbstractJdbc2ResultSet.this.addWarning(warning);
/*      */     }
/*      */     
/*      */     public void handleError(SQLException newError) {
/* 1734 */       if (this.error == null) {
/* 1735 */         this.error = newError;
/*      */       } else {
/* 1737 */         this.error.setNextException(newError);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void handleCompletion() throws SQLException {
/* 1741 */       if (this.error != null)
/* 1742 */         throw this.error; 
/*      */     }
/*      */   }
/*      */   
/*      */   public BaseStatement getPGStatement() {
/* 1748 */     return this.statement;
/*      */   }
/*      */   
/*      */   public String getRefCursor() {
/* 1762 */     return this.refCursorName;
/*      */   }
/*      */   
/*      */   private void setRefCursor(String refCursorName) {
/* 1766 */     this.refCursorName = refCursorName;
/*      */   }
/*      */   
/*      */   public void setFetchSize(int rows) throws SQLException {
/* 1771 */     checkClosed();
/* 1772 */     if (rows < 0)
/* 1773 */       throw new PSQLException(GT.tr("Fetch size must be a value greater to or equal to 0."), PSQLState.INVALID_PARAMETER_VALUE); 
/* 1775 */     this.fetchSize = rows;
/*      */   }
/*      */   
/*      */   public int getFetchSize() throws SQLException {
/* 1780 */     checkClosed();
/* 1781 */     return this.fetchSize;
/*      */   }
/*      */   
/*      */   public boolean next() throws SQLException {
/* 1786 */     checkClosed();
/* 1788 */     if (this.onInsertRow)
/* 1789 */       throw new PSQLException(GT.tr("Can''t use relative move methods while on the insert row."), PSQLState.INVALID_CURSOR_STATE); 
/* 1792 */     if (this.current_row + 1 >= this.rows.size()) {
/* 1794 */       if (this.cursor == null || (this.maxRows > 0 && this.row_offset + this.rows.size() >= this.maxRows)) {
/* 1796 */         this.current_row = this.rows.size();
/* 1797 */         this.this_row = (byte[][])null;
/* 1798 */         this.rowBuffer = (byte[][])null;
/* 1799 */         return false;
/*      */       } 
/* 1803 */       this.row_offset += this.rows.size();
/* 1805 */       int fetchRows = this.fetchSize;
/* 1806 */       if (this.maxRows != 0)
/* 1808 */         if (fetchRows == 0 || this.row_offset + fetchRows > this.maxRows)
/* 1809 */           fetchRows = this.maxRows - this.row_offset;  
/* 1813 */       this.connection.getQueryExecutor().fetch(this.cursor, new CursorResultHandler(), fetchRows);
/* 1815 */       this.current_row = 0;
/* 1818 */       if (this.rows.size() == 0) {
/* 1820 */         this.this_row = (byte[][])null;
/* 1821 */         this.rowBuffer = (byte[][])null;
/* 1822 */         return false;
/*      */       } 
/*      */     } else {
/* 1827 */       this.current_row++;
/*      */     } 
/* 1830 */     initRowBuffer();
/* 1831 */     return true;
/*      */   }
/*      */   
/*      */   public void close() throws SQLException {
/* 1837 */     this.rows = null;
/* 1838 */     if (this.cursor != null) {
/* 1839 */       this.cursor.close();
/* 1840 */       this.cursor = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean wasNull() throws SQLException {
/* 1846 */     checkClosed();
/* 1847 */     return this.wasNullFlag;
/*      */   }
/*      */   
/*      */   public String getString(int columnIndex) throws SQLException {
/* 1852 */     checkResultSet(columnIndex);
/* 1853 */     if (this.wasNullFlag)
/* 1854 */       return null; 
/* 1856 */     Encoding encoding = this.connection.getEncoding();
/*      */     try {
/* 1859 */       return trimString(columnIndex, encoding.decode(this.this_row[columnIndex - 1]));
/* 1861 */     } catch (IOException ioe) {
/* 1863 */       throw new PSQLException(GT.tr("Invalid character data was found.  This is most likely caused by stored data containing characters that are invalid for the character set the database was created in.  The most common example of this is storing 8bit data in a SQL_ASCII database."), PSQLState.DATA_ERROR, ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean getBoolean(int columnIndex) throws SQLException {
/* 1869 */     checkResultSet(columnIndex);
/* 1870 */     if (this.wasNullFlag)
/* 1871 */       return false; 
/* 1873 */     return toBoolean(getString(columnIndex));
/*      */   }
/*      */   
/* 1876 */   private static final BigInteger BYTEMAX = new BigInteger(Byte.toString(127));
/*      */   
/* 1877 */   private static final BigInteger BYTEMIN = new BigInteger(Byte.toString(-128));
/*      */   
/*      */   public byte getByte(int columnIndex) throws SQLException {
/* 1881 */     checkResultSet(columnIndex);
/* 1882 */     if (this.wasNullFlag)
/* 1883 */       return 0; 
/* 1885 */     String s = getString(columnIndex);
/* 1887 */     if (s != null) {
/* 1889 */       s = s.trim();
/* 1890 */       if (s.length() == 0)
/* 1891 */         return 0; 
/*      */       try {
/* 1895 */         return Byte.parseByte(s);
/* 1897 */       } catch (NumberFormatException e) {
/*      */         try {
/* 1902 */           BigDecimal n = new BigDecimal(s);
/* 1903 */           BigInteger i = n.toBigInteger();
/* 1905 */           int gt = i.compareTo(BYTEMAX);
/* 1906 */           int lt = i.compareTo(BYTEMIN);
/* 1908 */           if (gt > 0 || lt < 0)
/* 1910 */             throw new PSQLException(GT.tr("Bad value for type {0} : {1}", new Object[] { "byte", s }), PSQLState.NUMERIC_VALUE_OUT_OF_RANGE); 
/* 1913 */           return i.byteValue();
/* 1915 */         } catch (NumberFormatException ex) {
/* 1917 */           throw new PSQLException(GT.tr("Bad value for type {0} : {1}", new Object[] { "byte", s }), PSQLState.NUMERIC_VALUE_OUT_OF_RANGE);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1922 */     return 0;
/*      */   }
/*      */   
/* 1925 */   private static final BigInteger SHORTMAX = new BigInteger(Short.toString('翿'));
/*      */   
/* 1926 */   private static final BigInteger SHORTMIN = new BigInteger(Short.toString(-32768));
/*      */   
/*      */   public short getShort(int columnIndex) throws SQLException {
/* 1930 */     checkResultSet(columnIndex);
/* 1931 */     if (this.wasNullFlag)
/* 1932 */       return 0; 
/* 1934 */     String s = getFixedString(columnIndex);
/* 1936 */     if (s != null) {
/* 1938 */       s = s.trim();
/*      */       try {
/* 1941 */         return Short.parseShort(s);
/* 1943 */       } catch (NumberFormatException e) {
/*      */         try {
/* 1947 */           BigDecimal n = new BigDecimal(s);
/* 1948 */           BigInteger i = n.toBigInteger();
/* 1949 */           int gt = i.compareTo(SHORTMAX);
/* 1950 */           int lt = i.compareTo(SHORTMIN);
/* 1952 */           if (gt > 0 || lt < 0)
/* 1954 */             throw new PSQLException(GT.tr("Bad value for type {0} : {1}", new Object[] { "short", s }), PSQLState.NUMERIC_VALUE_OUT_OF_RANGE); 
/* 1957 */           return i.shortValue();
/* 1960 */         } catch (NumberFormatException ne) {
/* 1962 */           throw new PSQLException(GT.tr("Bad value for type {0} : {1}", new Object[] { "short", s }), PSQLState.NUMERIC_VALUE_OUT_OF_RANGE);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1967 */     return 0;
/*      */   }
/*      */   
/*      */   public int getInt(int columnIndex) throws SQLException {
/* 1972 */     checkResultSet(columnIndex);
/* 1973 */     if (this.wasNullFlag)
/* 1974 */       return 0; 
/* 1976 */     Encoding encoding = this.connection.getEncoding();
/* 1977 */     if (encoding.hasAsciiNumbers())
/*      */       try {
/* 1979 */         return getFastInt(columnIndex);
/* 1980 */       } catch (NumberFormatException ex) {} 
/* 1983 */     return toInt(getFixedString(columnIndex));
/*      */   }
/*      */   
/*      */   public long getLong(int columnIndex) throws SQLException {
/* 1988 */     checkResultSet(columnIndex);
/* 1989 */     if (this.wasNullFlag)
/* 1990 */       return 0L; 
/* 1992 */     Encoding encoding = this.connection.getEncoding();
/* 1993 */     if (encoding.hasAsciiNumbers())
/*      */       try {
/* 1995 */         return getFastLong(columnIndex);
/* 1996 */       } catch (NumberFormatException ex) {} 
/* 1999 */     return toLong(getFixedString(columnIndex));
/*      */   }
/*      */   
/* 2007 */   private static final NumberFormatException FAST_NUMBER_FAILED = new NumberFormatException();
/*      */   
/*      */   private long getFastLong(int columnIndex) throws SQLException, NumberFormatException {
/*      */     int start;
/*      */     boolean neg;
/* 2025 */     byte[] bytes = this.this_row[columnIndex - 1];
/* 2027 */     if (bytes.length == 0)
/* 2028 */       throw FAST_NUMBER_FAILED; 
/* 2031 */     long val = 0L;
/* 2034 */     if (bytes[0] == 45) {
/* 2035 */       neg = true;
/* 2036 */       start = 1;
/* 2037 */       if (bytes.length == 1 || bytes.length > 19)
/* 2038 */         throw FAST_NUMBER_FAILED; 
/*      */     } else {
/* 2041 */       start = 0;
/* 2042 */       neg = false;
/* 2043 */       if (bytes.length > 18)
/* 2044 */         throw FAST_NUMBER_FAILED; 
/*      */     } 
/* 2048 */     while (start < bytes.length) {
/* 2049 */       byte b = bytes[start++];
/* 2050 */       if (b < 48 || b > 57)
/* 2051 */         throw FAST_NUMBER_FAILED; 
/* 2054 */       val *= 10L;
/* 2055 */       val += (b - 48);
/*      */     } 
/* 2058 */     if (neg)
/* 2059 */       val = -val; 
/* 2062 */     return val;
/*      */   }
/*      */   
/*      */   private int getFastInt(int columnIndex) throws SQLException, NumberFormatException {
/*      */     int start;
/*      */     boolean neg;
/* 2080 */     byte[] bytes = this.this_row[columnIndex - 1];
/* 2082 */     if (bytes.length == 0)
/* 2083 */       throw FAST_NUMBER_FAILED; 
/* 2086 */     int val = 0;
/* 2089 */     if (bytes[0] == 45) {
/* 2090 */       neg = true;
/* 2091 */       start = 1;
/* 2092 */       if (bytes.length == 1 || bytes.length > 10)
/* 2093 */         throw FAST_NUMBER_FAILED; 
/*      */     } else {
/* 2096 */       start = 0;
/* 2097 */       neg = false;
/* 2098 */       if (bytes.length > 9)
/* 2099 */         throw FAST_NUMBER_FAILED; 
/*      */     } 
/* 2103 */     while (start < bytes.length) {
/* 2104 */       byte b = bytes[start++];
/* 2105 */       if (b < 48 || b > 57)
/* 2106 */         throw FAST_NUMBER_FAILED; 
/* 2109 */       val *= 10;
/* 2110 */       val += b - 48;
/*      */     } 
/* 2113 */     if (neg)
/* 2114 */       val = -val; 
/* 2117 */     return val;
/*      */   }
/*      */   
/*      */   private BigDecimal getFastBigDecimal(int columnIndex) throws SQLException, NumberFormatException {
/*      */     int start;
/*      */     boolean neg;
/* 2135 */     byte[] bytes = this.this_row[columnIndex - 1];
/* 2137 */     if (bytes.length == 0)
/* 2138 */       throw FAST_NUMBER_FAILED; 
/* 2141 */     int scale = 0;
/* 2142 */     long val = 0L;
/* 2145 */     if (bytes[0] == 45) {
/* 2146 */       neg = true;
/* 2147 */       start = 1;
/* 2148 */       if (bytes.length == 1 || bytes.length > 19)
/* 2149 */         throw FAST_NUMBER_FAILED; 
/*      */     } else {
/* 2152 */       start = 0;
/* 2153 */       neg = false;
/* 2154 */       if (bytes.length > 18)
/* 2155 */         throw FAST_NUMBER_FAILED; 
/*      */     } 
/* 2159 */     int periodsSeen = 0;
/* 2160 */     while (start < bytes.length) {
/* 2161 */       byte b = bytes[start++];
/* 2162 */       if (b < 48 || b > 57) {
/* 2163 */         if (b == 46) {
/* 2164 */           scale = bytes.length - start;
/* 2165 */           periodsSeen++;
/*      */           continue;
/*      */         } 
/* 2168 */         throw FAST_NUMBER_FAILED;
/*      */       } 
/* 2170 */       val *= 10L;
/* 2171 */       val += (b - 48);
/*      */     } 
/* 2174 */     int numNonSignChars = neg ? (bytes.length - 1) : bytes.length;
/* 2175 */     if (periodsSeen > 1 || periodsSeen == numNonSignChars)
/* 2176 */       throw FAST_NUMBER_FAILED; 
/* 2178 */     if (neg)
/* 2179 */       val = -val; 
/* 2182 */     return BigDecimal.valueOf(val, scale);
/*      */   }
/*      */   
/*      */   public float getFloat(int columnIndex) throws SQLException {
/* 2187 */     checkResultSet(columnIndex);
/* 2188 */     if (this.wasNullFlag)
/* 2189 */       return 0.0F; 
/* 2191 */     return toFloat(getFixedString(columnIndex));
/*      */   }
/*      */   
/*      */   public double getDouble(int columnIndex) throws SQLException {
/* 2196 */     checkResultSet(columnIndex);
/* 2197 */     if (this.wasNullFlag)
/* 2198 */       return 0.0D; 
/* 2200 */     return toDouble(getFixedString(columnIndex));
/*      */   }
/*      */   
/*      */   public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
/* 2205 */     checkResultSet(columnIndex);
/* 2206 */     if (this.wasNullFlag)
/* 2207 */       return null; 
/* 2209 */     Encoding encoding = this.connection.getEncoding();
/* 2210 */     if (encoding.hasAsciiNumbers())
/*      */       try {
/* 2212 */         return getFastBigDecimal(columnIndex);
/* 2213 */       } catch (NumberFormatException ex) {} 
/* 2217 */     return toBigDecimal(getFixedString(columnIndex), scale);
/*      */   }
/*      */   
/*      */   public byte[] getBytes(int columnIndex) throws SQLException {
/* 2237 */     checkResultSet(columnIndex);
/* 2238 */     if (this.wasNullFlag)
/* 2239 */       return null; 
/* 2241 */     if (this.fields[columnIndex - 1].getFormat() == 1)
/* 2244 */       return this.this_row[columnIndex - 1]; 
/* 2246 */     if (this.connection.haveMinimumCompatibleVersion("7.2")) {
/* 2249 */       if (this.fields[columnIndex - 1].getOID() == 17)
/* 2251 */         return trimBytes(columnIndex, PGbytea.toBytes(this.this_row[columnIndex - 1])); 
/* 2255 */       return trimBytes(columnIndex, this.this_row[columnIndex - 1]);
/*      */     } 
/* 2262 */     if (this.fields[columnIndex - 1].getOID() == 26) {
/* 2264 */       LargeObjectManager lom = this.connection.getLargeObjectAPI();
/* 2265 */       LargeObject lob = lom.open(getLong(columnIndex));
/* 2266 */       byte[] buf = lob.read(lob.size());
/* 2267 */       lob.close();
/* 2268 */       return trimBytes(columnIndex, buf);
/*      */     } 
/* 2272 */     return trimBytes(columnIndex, this.this_row[columnIndex - 1]);
/*      */   }
/*      */   
/*      */   public Date getDate(int columnIndex) throws SQLException {
/* 2279 */     return getDate(columnIndex, (Calendar)null);
/*      */   }
/*      */   
/*      */   public Time getTime(int columnIndex) throws SQLException {
/* 2284 */     return getTime(columnIndex, (Calendar)null);
/*      */   }
/*      */   
/*      */   public Timestamp getTimestamp(int columnIndex) throws SQLException {
/* 2289 */     return getTimestamp(columnIndex, (Calendar)null);
/*      */   }
/*      */   
/*      */   public InputStream getAsciiStream(int columnIndex) throws SQLException {
/* 2294 */     checkResultSet(columnIndex);
/* 2295 */     if (this.wasNullFlag)
/* 2296 */       return null; 
/* 2298 */     if (this.connection.haveMinimumCompatibleVersion("7.2"))
/*      */       try {
/* 2308 */         return new ByteArrayInputStream(getString(columnIndex).getBytes("ASCII"));
/* 2310 */       } catch (UnsupportedEncodingException l_uee) {
/* 2312 */         throw new PSQLException(GT.tr("The JVM claims not to support the encoding: {0}", "ASCII"), PSQLState.UNEXPECTED_ERROR, l_uee);
/*      */       }  
/* 2318 */     return getBinaryStream(columnIndex);
/*      */   }
/*      */   
/*      */   public InputStream getUnicodeStream(int columnIndex) throws SQLException {
/* 2324 */     checkResultSet(columnIndex);
/* 2325 */     if (this.wasNullFlag)
/* 2326 */       return null; 
/* 2328 */     if (this.connection.haveMinimumCompatibleVersion("7.2"))
/*      */       try {
/* 2338 */         return new ByteArrayInputStream(getString(columnIndex).getBytes("UTF-8"));
/* 2340 */       } catch (UnsupportedEncodingException l_uee) {
/* 2342 */         throw new PSQLException(GT.tr("The JVM claims not to support the encoding: {0}", "UTF-8"), PSQLState.UNEXPECTED_ERROR, l_uee);
/*      */       }  
/* 2348 */     return getBinaryStream(columnIndex);
/*      */   }
/*      */   
/*      */   public InputStream getBinaryStream(int columnIndex) throws SQLException {
/* 2354 */     checkResultSet(columnIndex);
/* 2355 */     if (this.wasNullFlag)
/* 2356 */       return null; 
/* 2358 */     if (this.connection.haveMinimumCompatibleVersion("7.2")) {
/* 2366 */       byte[] b = getBytes(columnIndex);
/* 2367 */       if (b != null)
/* 2368 */         return new ByteArrayInputStream(b); 
/* 2373 */     } else if (this.fields[columnIndex - 1].getOID() == 26) {
/* 2375 */       LargeObjectManager lom = this.connection.getLargeObjectAPI();
/* 2376 */       LargeObject lob = lom.open(getLong(columnIndex));
/* 2377 */       return lob.getInputStream();
/*      */     } 
/* 2380 */     return null;
/*      */   }
/*      */   
/*      */   public String getString(String columnName) throws SQLException {
/* 2385 */     return getString(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public boolean getBoolean(String columnName) throws SQLException {
/* 2390 */     return getBoolean(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public byte getByte(String columnName) throws SQLException {
/* 2396 */     return getByte(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public short getShort(String columnName) throws SQLException {
/* 2401 */     return getShort(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public int getInt(String columnName) throws SQLException {
/* 2406 */     return getInt(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public long getLong(String columnName) throws SQLException {
/* 2411 */     return getLong(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public float getFloat(String columnName) throws SQLException {
/* 2416 */     return getFloat(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public double getDouble(String columnName) throws SQLException {
/* 2421 */     return getDouble(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public BigDecimal getBigDecimal(String columnName, int scale) throws SQLException {
/* 2426 */     return getBigDecimal(findColumn(columnName), scale);
/*      */   }
/*      */   
/*      */   public byte[] getBytes(String columnName) throws SQLException {
/* 2431 */     return getBytes(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public Date getDate(String columnName) throws SQLException {
/* 2436 */     return getDate(findColumn(columnName), (Calendar)null);
/*      */   }
/*      */   
/*      */   public Time getTime(String columnName) throws SQLException {
/* 2441 */     return getTime(findColumn(columnName), (Calendar)null);
/*      */   }
/*      */   
/*      */   public Timestamp getTimestamp(String columnName) throws SQLException {
/* 2446 */     return getTimestamp(findColumn(columnName), (Calendar)null);
/*      */   }
/*      */   
/*      */   public InputStream getAsciiStream(String columnName) throws SQLException {
/* 2451 */     return getAsciiStream(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public InputStream getUnicodeStream(String columnName) throws SQLException {
/* 2456 */     return getUnicodeStream(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public InputStream getBinaryStream(String columnName) throws SQLException {
/* 2461 */     return getBinaryStream(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public SQLWarning getWarnings() throws SQLException {
/* 2466 */     checkClosed();
/* 2467 */     return this.warnings;
/*      */   }
/*      */   
/*      */   public void clearWarnings() throws SQLException {
/* 2472 */     checkClosed();
/* 2473 */     this.warnings = null;
/*      */   }
/*      */   
/*      */   protected void addWarning(SQLWarning warnings) {
/* 2478 */     if (this.warnings != null) {
/* 2479 */       this.warnings.setNextWarning(warnings);
/*      */     } else {
/* 2481 */       this.warnings = warnings;
/*      */     } 
/*      */   }
/*      */   
/*      */   public String getCursorName() throws SQLException {
/* 2486 */     checkClosed();
/* 2487 */     return null;
/*      */   }
/*      */   
/*      */   public Object getObject(int columnIndex) throws SQLException {
/* 2508 */     checkResultSet(columnIndex);
/* 2509 */     if (this.wasNullFlag)
/* 2510 */       return null; 
/* 2512 */     Field field = this.fields[columnIndex - 1];
/* 2515 */     if (field == null) {
/* 2517 */       this.wasNullFlag = true;
/* 2518 */       return null;
/*      */     } 
/* 2521 */     Object result = internalGetObject(columnIndex, field);
/* 2522 */     if (result != null)
/* 2523 */       return result; 
/* 2525 */     return this.connection.getObject(getPGType(columnIndex), getString(columnIndex));
/*      */   }
/*      */   
/*      */   public Object getObject(String columnName) throws SQLException {
/* 2530 */     return getObject(findColumn(columnName));
/*      */   }
/*      */   
/*      */   public int findColumn(String columnName) throws SQLException {
/* 2538 */     checkClosed();
/* 2540 */     int col = findColumnIndex(columnName);
/* 2541 */     if (col == 0)
/* 2542 */       throw new PSQLException(GT.tr("The column name {0} was not found in this ResultSet.", columnName), PSQLState.UNDEFINED_COLUMN); 
/* 2544 */     return col;
/*      */   }
/*      */   
/*      */   private int findColumnIndex(String columnName) {
/* 2549 */     if (this.columnNameIndexMap == null) {
/* 2551 */       this.columnNameIndexMap = new HashMap<Object, Object>(this.fields.length * 2);
/* 2555 */       for (int i = this.fields.length - 1; i >= 0; i--)
/* 2557 */         this.columnNameIndexMap.put(this.fields[i].getColumnLabel().toLowerCase(Locale.US), new Integer(i + 1)); 
/*      */     } 
/* 2561 */     Integer index = (Integer)this.columnNameIndexMap.get(columnName);
/* 2562 */     if (index != null)
/* 2564 */       return index.intValue(); 
/* 2567 */     index = (Integer)this.columnNameIndexMap.get(columnName.toLowerCase(Locale.US));
/* 2568 */     if (index != null) {
/* 2570 */       this.columnNameIndexMap.put(columnName, index);
/* 2571 */       return index.intValue();
/*      */     } 
/* 2574 */     return 0;
/*      */   }
/*      */   
/*      */   public int getColumnOID(int field) {
/* 2583 */     return this.fields[field - 1].getOID();
/*      */   }
/*      */   
/*      */   public String getFixedString(int col) throws SQLException {
/* 2594 */     String s = getString(col);
/* 2595 */     if (s == null)
/* 2596 */       return null; 
/* 2599 */     if (s.length() < 2)
/* 2600 */       return s; 
/* 2603 */     char ch = s.charAt(0);
/* 2607 */     if (ch > '-')
/* 2608 */       return s; 
/* 2611 */     if (ch == '(') {
/* 2613 */       s = "-" + PGtokenizer.removePara(s).substring(1);
/* 2615 */     } else if (ch == '$') {
/* 2617 */       s = s.substring(1);
/* 2619 */     } else if (ch == '-' && s.charAt(1) == '$') {
/* 2621 */       s = "-" + s.substring(2);
/*      */     } 
/* 2624 */     return s;
/*      */   }
/*      */   
/*      */   protected String getPGType(int column) throws SQLException {
/* 2629 */     return this.connection.getTypeInfo().getPGType(this.fields[column - 1].getOID());
/*      */   }
/*      */   
/*      */   protected int getSQLType(int column) throws SQLException {
/* 2634 */     return this.connection.getTypeInfo().getSQLType(this.fields[column - 1].getOID());
/*      */   }
/*      */   
/*      */   private void checkUpdateable() throws SQLException {
/* 2639 */     checkClosed();
/* 2641 */     if (!isUpdateable())
/* 2642 */       throw new PSQLException(GT.tr("ResultSet is not updateable.  The query that generated this result set must select only one table, and must select all primary keys from that table. See the JDBC 2.1 API Specification, section 5.6 for more details."), PSQLState.INVALID_CURSOR_STATE); 
/* 2645 */     if (this.updateValues == null)
/* 2648 */       this.updateValues = new HashMap<Object, Object>((int)(this.fields.length / 0.75D), 0.75F); 
/*      */   }
/*      */   
/*      */   protected void checkClosed() throws SQLException {
/* 2653 */     if (this.rows == null)
/* 2654 */       throw new PSQLException(GT.tr("This ResultSet is closed."), PSQLState.OBJECT_NOT_IN_STATE); 
/*      */   }
/*      */   
/*      */   protected void checkColumnIndex(int column) throws SQLException {
/* 2659 */     if (column < 1 || column > this.fields.length)
/* 2660 */       throw new PSQLException(GT.tr("The column index is out of range: {0}, number of columns: {1}.", new Object[] { new Integer(column), new Integer(this.fields.length) }), PSQLState.INVALID_PARAMETER_VALUE); 
/*      */   }
/*      */   
/*      */   protected void checkResultSet(int column) throws SQLException {
/* 2673 */     checkClosed();
/* 2674 */     if (this.this_row == null)
/* 2675 */       throw new PSQLException(GT.tr("ResultSet not positioned properly, perhaps you need to call next."), PSQLState.INVALID_CURSOR_STATE); 
/* 2677 */     checkColumnIndex(column);
/* 2678 */     this.wasNullFlag = (this.this_row[column - 1] == null);
/*      */   }
/*      */   
/*      */   public static boolean toBoolean(String s) {
/* 2685 */     if (s != null) {
/* 2687 */       s = s.trim();
/* 2689 */       if (s.equalsIgnoreCase("t") || s.equalsIgnoreCase("true") || s.equals("1"))
/* 2690 */         return true; 
/* 2692 */       if (s.equalsIgnoreCase("f") || s.equalsIgnoreCase("false") || s.equals("0"))
/* 2693 */         return false; 
/*      */       try {
/* 2697 */         if (Double.valueOf(s).doubleValue() == 1.0D)
/* 2698 */           return true; 
/* 2700 */       } catch (NumberFormatException e) {}
/*      */     } 
/* 2704 */     return false;
/*      */   }
/*      */   
/* 2707 */   private static final BigInteger INTMAX = new BigInteger(Integer.toString(2147483647));
/*      */   
/* 2708 */   private static final BigInteger INTMIN = new BigInteger(Integer.toString(-2147483648));
/*      */   
/*      */   public static int toInt(String s) throws SQLException {
/* 2712 */     if (s != null)
/*      */       try {
/* 2716 */         s = s.trim();
/* 2717 */         return Integer.parseInt(s);
/* 2719 */       } catch (NumberFormatException e) {
/*      */         try {
/* 2723 */           BigDecimal n = new BigDecimal(s);
/* 2724 */           BigInteger i = n.toBigInteger();
/* 2726 */           int gt = i.compareTo(INTMAX);
/* 2727 */           int lt = i.compareTo(INTMIN);
/* 2729 */           if (gt > 0 || lt < 0)
/* 2731 */             throw new PSQLException(GT.tr("Bad value for type {0} : {1}", new Object[] { "int", s }), PSQLState.NUMERIC_VALUE_OUT_OF_RANGE); 
/* 2734 */           return i.intValue();
/* 2737 */         } catch (NumberFormatException ne) {
/* 2739 */           throw new PSQLException(GT.tr("Bad value for type {0} : {1}", new Object[] { "int", s }), PSQLState.NUMERIC_VALUE_OUT_OF_RANGE);
/*      */         } 
/*      */       }  
/* 2744 */     return 0;
/*      */   }
/*      */   
/* 2746 */   private static final BigInteger LONGMAX = new BigInteger(Long.toString(Long.MAX_VALUE));
/*      */   
/* 2747 */   private static final BigInteger LONGMIN = new BigInteger(Long.toString(Long.MIN_VALUE));
/*      */   
/*      */   public static long toLong(String s) throws SQLException {
/* 2751 */     if (s != null)
/*      */       try {
/* 2755 */         s = s.trim();
/* 2756 */         return Long.parseLong(s);
/* 2758 */       } catch (NumberFormatException e) {
/*      */         try {
/* 2762 */           BigDecimal n = new BigDecimal(s);
/* 2763 */           BigInteger i = n.toBigInteger();
/* 2764 */           int gt = i.compareTo(LONGMAX);
/* 2765 */           int lt = i.compareTo(LONGMIN);
/* 2767 */           if (gt > 0 || lt < 0)
/* 2769 */             throw new PSQLException(GT.tr("Bad value for type {0} : {1}", new Object[] { "long", s }), PSQLState.NUMERIC_VALUE_OUT_OF_RANGE); 
/* 2772 */           return i.longValue();
/* 2774 */         } catch (NumberFormatException ne) {
/* 2776 */           throw new PSQLException(GT.tr("Bad value for type {0} : {1}", new Object[] { "long", s }), PSQLState.NUMERIC_VALUE_OUT_OF_RANGE);
/*      */         } 
/*      */       }  
/* 2781 */     return 0L;
/*      */   }
/*      */   
/*      */   public static BigDecimal toBigDecimal(String s, int scale) throws SQLException {
/* 2787 */     if (s != null) {
/*      */       BigDecimal val;
/*      */       try {
/* 2791 */         s = s.trim();
/* 2792 */         val = new BigDecimal(s);
/* 2794 */       } catch (NumberFormatException e) {
/* 2796 */         throw new PSQLException(GT.tr("Bad value for type {0} : {1}", new Object[] { "BigDecimal", s }), PSQLState.NUMERIC_VALUE_OUT_OF_RANGE);
/*      */       } 
/* 2799 */       if (scale == -1)
/* 2800 */         return val; 
/*      */       try {
/* 2803 */         return val.setScale(scale);
/* 2805 */       } catch (ArithmeticException e) {
/* 2807 */         throw new PSQLException(GT.tr("Bad value for type {0} : {1}", new Object[] { "BigDecimal", s }), PSQLState.NUMERIC_VALUE_OUT_OF_RANGE);
/*      */       } 
/*      */     } 
/* 2811 */     return null;
/*      */   }
/*      */   
/*      */   public static float toFloat(String s) throws SQLException {
/* 2816 */     if (s != null)
/*      */       try {
/* 2820 */         s = s.trim();
/* 2821 */         return Float.parseFloat(s);
/* 2823 */       } catch (NumberFormatException e) {
/* 2825 */         throw new PSQLException(GT.tr("Bad value for type {0} : {1}", new Object[] { "float", s }), PSQLState.NUMERIC_VALUE_OUT_OF_RANGE);
/*      */       }  
/* 2829 */     return 0.0F;
/*      */   }
/*      */   
/*      */   public static double toDouble(String s) throws SQLException {
/* 2834 */     if (s != null)
/*      */       try {
/* 2838 */         s = s.trim();
/* 2839 */         return Double.parseDouble(s);
/* 2841 */       } catch (NumberFormatException e) {
/* 2843 */         throw new PSQLException(GT.tr("Bad value for type {0} : {1}", new Object[] { "double", s }), PSQLState.NUMERIC_VALUE_OUT_OF_RANGE);
/*      */       }  
/* 2847 */     return 0.0D;
/*      */   }
/*      */   
/*      */   private void initRowBuffer() {
/* 2852 */     this.this_row = this.rows.elementAt(this.current_row);
/* 2855 */     if (this.resultsetconcurrency == 1008) {
/* 2856 */       this.rowBuffer = new byte[this.this_row.length][];
/* 2857 */       System.arraycopy(this.this_row, 0, this.rowBuffer, 0, this.this_row.length);
/*      */     } else {
/* 2859 */       this.rowBuffer = (byte[][])null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean isColumnTrimmable(int columnIndex) throws SQLException {
/* 2865 */     switch (getSQLType(columnIndex)) {
/*      */       case -4:
/*      */       case -3:
/*      */       case -2:
/*      */       case -1:
/*      */       case 1:
/*      */       case 12:
/* 2873 */         return true;
/*      */     } 
/* 2875 */     return false;
/*      */   }
/*      */   
/*      */   private byte[] trimBytes(int p_columnIndex, byte[] p_bytes) throws SQLException {
/* 2882 */     if (this.maxFieldSize > 0 && p_bytes.length > this.maxFieldSize && isColumnTrimmable(p_columnIndex)) {
/* 2884 */       byte[] l_bytes = new byte[this.maxFieldSize];
/* 2885 */       System.arraycopy(p_bytes, 0, l_bytes, 0, this.maxFieldSize);
/* 2886 */       return l_bytes;
/*      */     } 
/* 2890 */     return p_bytes;
/*      */   }
/*      */   
/*      */   private String trimString(int p_columnIndex, String p_string) throws SQLException {
/* 2898 */     if (this.maxFieldSize > 0 && p_string.length() > this.maxFieldSize && isColumnTrimmable(p_columnIndex))
/* 2900 */       return p_string.substring(0, this.maxFieldSize); 
/* 2904 */     return p_string;
/*      */   }
/*      */   
/*      */   protected void updateValue(int columnIndex, Object value) throws SQLException {
/* 2909 */     checkUpdateable();
/* 2911 */     if (!this.onInsertRow && (isBeforeFirst() || isAfterLast() || this.rows.size() == 0))
/* 2913 */       throw new PSQLException(GT.tr("Cannot update the ResultSet because it is either before the start or after the end of the results."), PSQLState.INVALID_CURSOR_STATE); 
/* 2917 */     checkColumnIndex(columnIndex);
/* 2919 */     this.doingUpdates = !this.onInsertRow;
/* 2920 */     if (value == null) {
/* 2921 */       updateNull(columnIndex);
/*      */     } else {
/* 2923 */       PGResultSetMetaData md = (PGResultSetMetaData)getMetaData();
/* 2924 */       this.updateValues.put(md.getBaseColumnName(columnIndex), value);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Object getUUID(String data) throws SQLException {
/* 2934 */     return data;
/*      */   }
/*      */   
/*      */   private class PrimaryKey {
/*      */     int index;
/*      */     
/*      */     String name;
/*      */     
/*      */     PrimaryKey(int index, String name) {
/* 2944 */       this.index = index;
/* 2945 */       this.name = name;
/*      */     }
/*      */     
/*      */     Object getValue() throws SQLException {
/* 2949 */       return AbstractJdbc2ResultSet.this.getObject(this.index);
/*      */     }
/*      */   }
/*      */   
/*      */   static class NullObject extends PGobject {
/*      */     NullObject(String type) {
/* 2961 */       setType(type);
/*      */     }
/*      */     
/*      */     public String getValue() {
/* 2965 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   void addRows(Vector tuples) {
/* 2975 */     this.rows.addAll(tuples);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc2\AbstractJdbc2ResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */