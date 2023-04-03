package org.hsqldb.navigator;

import java.io.IOException;
import org.hsqldb.HsqlException;
import org.hsqldb.QueryExpression;
import org.hsqldb.QuerySpecification;
import org.hsqldb.Row;
import org.hsqldb.Session;
import org.hsqldb.SortAndSlice;
import org.hsqldb.TableBase;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class RowSetNavigatorDataTable extends RowSetNavigatorData {
  final Session session;
  
  public TableBase table;
  
  public PersistentStore store;
  
  RowIterator iterator;
  
  Row currentRow;
  
  int maxMemoryRowCount;
  
  boolean isClosed;
  
  int visibleColumnCount;
  
  boolean isAggregate;
  
  boolean isSimpleAggregate;
  
  Object[] simpleAggregateData;
  
  Object[] tempRowData;
  
  boolean reindexTable;
  
  private Index mainIndex;
  
  private Index fullIndex;
  
  private Index orderIndex;
  
  private Index groupIndex;
  
  private Index idIndex;
  
  public RowSetNavigatorDataTable(Session paramSession, QuerySpecification paramQuerySpecification) {
    super(paramSession);
    this.session = paramSession;
    this.rangePosition = paramQuerySpecification.resultRangePosition;
    this.maxMemoryRowCount = paramSession.getResultMemoryRowCount();
    this.visibleColumnCount = paramQuerySpecification.indexLimitVisible;
    this.table = paramQuerySpecification.resultTable.duplicate();
    this.table.store = this.store = paramSession.sessionData.getNewResultRowStore(this.table, !paramQuerySpecification.isAggregated);
    this.isAggregate = paramQuerySpecification.isAggregated;
    this.isSimpleAggregate = (paramQuerySpecification.isAggregated && !paramQuerySpecification.isGrouped);
    this.reindexTable = paramQuerySpecification.isGrouped;
    this.mainIndex = paramQuerySpecification.mainIndex;
    this.fullIndex = paramQuerySpecification.fullIndex;
    this.orderIndex = paramQuerySpecification.orderIndex;
    this.groupIndex = paramQuerySpecification.groupIndex;
    this.idIndex = paramQuerySpecification.idIndex;
    this.tempRowData = new Object[1];
  }
  
  public RowSetNavigatorDataTable(Session paramSession, QuerySpecification paramQuerySpecification, RowSetNavigatorData paramRowSetNavigatorData) {
    this(paramSession, paramQuerySpecification);
    paramRowSetNavigatorData.reset();
    while (paramRowSetNavigatorData.hasNext())
      add(paramRowSetNavigatorData.getNext()); 
  }
  
  public RowSetNavigatorDataTable(Session paramSession, QueryExpression paramQueryExpression) {
    super(paramSession);
    this.session = paramSession;
    this.maxMemoryRowCount = paramSession.getResultMemoryRowCount();
    this.table = paramQueryExpression.resultTable.duplicate();
    this.visibleColumnCount = this.table.getColumnCount();
    this.table.store = this.store = paramSession.sessionData.getNewResultRowStore(this.table, true);
    this.mainIndex = paramQueryExpression.mainIndex;
    this.fullIndex = paramQueryExpression.fullIndex;
  }
  
  public RowSetNavigatorDataTable(Session paramSession, TableBase paramTableBase) {
    super(paramSession);
    this.session = paramSession;
    this.maxMemoryRowCount = paramSession.getResultMemoryRowCount();
    this.table = paramTableBase;
    this.visibleColumnCount = paramTableBase.getColumnCount();
    this.store = paramTableBase.getRowStore(paramSession);
    this.mainIndex = paramTableBase.getPrimaryIndex();
    this.fullIndex = paramTableBase.getFullIndex();
    this.size = (int)this.mainIndex.size(paramSession, this.store);
    reset();
  }
  
  public void sortFull(Session paramSession) {
    if (this.reindexTable)
      this.store.indexRows(paramSession); 
    this.mainIndex = this.fullIndex;
    if (this.iterator != null)
      this.iterator.release(); 
    reset();
  }
  
  public void sortOrder(Session paramSession) {
    if (this.orderIndex != null) {
      if (this.reindexTable)
        this.store.indexRows(paramSession); 
      this.mainIndex = this.orderIndex;
      if (this.iterator != null)
        this.iterator.release(); 
      reset();
    } 
  }
  
  public void sortOrderUnion(Session paramSession, SortAndSlice paramSortAndSlice) {
    if (paramSortAndSlice.index != null) {
      this.mainIndex = paramSortAndSlice.index;
      reset();
    } 
  }
  
  public void add(Object[] paramArrayOfObject) {
    try {
      Row row = (Row)this.store.getNewCachedObject(this.session, paramArrayOfObject, false);
      this.store.indexRow(this.session, row);
      this.size++;
    } catch (HsqlException hsqlException) {}
  }
  
  void addAdjusted(Object[] paramArrayOfObject, int[] paramArrayOfint) {
    try {
      if (paramArrayOfint == null) {
        paramArrayOfObject = (Object[])ArrayUtil.resizeArrayIfDifferent(paramArrayOfObject, this.visibleColumnCount);
      } else {
        Object[] arrayOfObject = new Object[this.visibleColumnCount];
        ArrayUtil.projectRow(paramArrayOfObject, paramArrayOfint, arrayOfObject);
        paramArrayOfObject = arrayOfObject;
      } 
      add(paramArrayOfObject);
    } catch (HsqlException hsqlException) {}
  }
  
  public void update(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2) {
    if (this.isSimpleAggregate)
      return; 
    RowIterator rowIterator = this.groupIndex.findFirstRow(this.session, this.store, paramArrayOfObject1);
    if (rowIterator.hasNext()) {
      Row row = rowIterator.getNextRow();
      rowIterator.removeCurrent();
      rowIterator.release();
      this.size--;
      add(paramArrayOfObject2);
    } 
  }
  
  public boolean absolute(int paramInt) {
    return super.absolute(paramInt);
  }
  
  public Object[] getCurrent() {
    return this.currentRow.getData();
  }
  
  public Row getCurrentRow() {
    return this.currentRow;
  }
  
  public boolean next() {
    boolean bool = super.next();
    this.currentRow = this.iterator.getNextRow();
    return bool;
  }
  
  public void removeCurrent() {
    if (this.currentRow != null) {
      this.iterator.removeCurrent();
      this.currentRow = null;
      this.currentPos--;
      this.size--;
    } 
  }
  
  public void reset() {
    super.reset();
    if (this.iterator != null)
      this.iterator.release(); 
    this.iterator = this.mainIndex.firstRow(this.session, this.store, 0);
  }
  
  public void release() {
    if (this.isClosed)
      return; 
    this.iterator.release();
    this.store.release();
    this.isClosed = true;
  }
  
  public void clear() {
    this.table.clearAllData(this.store);
    this.size = 0;
    reset();
  }
  
  public boolean isMemory() {
    return this.store.isMemory();
  }
  
  public void read(RowInputInterface paramRowInputInterface, ResultMetaData paramResultMetaData) throws IOException {}
  
  public void write(RowOutputInterface paramRowOutputInterface, ResultMetaData paramResultMetaData) throws IOException {
    reset();
    paramRowOutputInterface.writeLong(this.id);
    paramRowOutputInterface.writeInt(this.size);
    paramRowOutputInterface.writeInt(0);
    paramRowOutputInterface.writeInt(this.size);
    while (hasNext()) {
      Object[] arrayOfObject = getNext();
      paramRowOutputInterface.writeData(paramResultMetaData.getExtendedColumnCount(), paramResultMetaData.columnTypes, arrayOfObject, null, null);
    } 
    reset();
  }
  
  public Object[] getData(Long paramLong) {
    this.tempRowData[0] = paramLong;
    RowIterator rowIterator = this.idIndex.findFirstRow(this.session, this.store, this.tempRowData, this.idIndex.getDefaultColumnMap());
    return rowIterator.getNext();
  }
  
  public void copy(RowSetNavigatorData paramRowSetNavigatorData, int[] paramArrayOfint) {
    while (paramRowSetNavigatorData.hasNext()) {
      Object[] arrayOfObject = paramRowSetNavigatorData.getNext();
      addAdjusted(arrayOfObject, paramArrayOfint);
    } 
    paramRowSetNavigatorData.release();
  }
  
  public void union(Session paramSession, RowSetNavigatorData paramRowSetNavigatorData) {
    int i = (this.table.getColumnTypes()).length;
    removeDuplicates(paramSession);
    paramRowSetNavigatorData.reset();
    while (paramRowSetNavigatorData.hasNext()) {
      Object[] arrayOfObject = paramRowSetNavigatorData.getNext();
      RowIterator rowIterator = findFirstRow(arrayOfObject);
      if (!rowIterator.hasNext()) {
        arrayOfObject = (Object[])ArrayUtil.resizeArrayIfDifferent(arrayOfObject, i);
        add(arrayOfObject);
      } 
    } 
    paramRowSetNavigatorData.release();
  }
  
  public void intersect(Session paramSession, RowSetNavigatorData paramRowSetNavigatorData) {
    removeDuplicates(paramSession);
    paramRowSetNavigatorData.sortFull(paramSession);
    while (hasNext()) {
      Object[] arrayOfObject = getNext();
      boolean bool = paramRowSetNavigatorData.containsRow(arrayOfObject);
      if (!bool)
        removeCurrent(); 
    } 
    paramRowSetNavigatorData.release();
  }
  
  public void intersectAll(Session paramSession, RowSetNavigatorData paramRowSetNavigatorData) {
    Object[] arrayOfObject1 = null;
    Row row = null;
    Object[] arrayOfObject2 = null;
    sortFull(paramSession);
    paramRowSetNavigatorData.sortFull(paramSession);
    RowIterator rowIterator = this.fullIndex.emptyIterator();
    while (hasNext()) {
      Object[] arrayOfObject = getNext();
      boolean bool = (arrayOfObject1 == null || this.fullIndex.compareRowNonUnique(paramSession, arrayOfObject, arrayOfObject1, this.fullIndex.getColumnCount()) != 0) ? true : false;
      if (bool) {
        arrayOfObject1 = arrayOfObject;
        rowIterator = paramRowSetNavigatorData.findFirstRow(arrayOfObject);
      } 
      row = rowIterator.getNextRow();
      arrayOfObject2 = (row == null) ? null : row.getData();
      if (arrayOfObject2 != null && this.fullIndex.compareRowNonUnique(paramSession, arrayOfObject, arrayOfObject2, this.fullIndex.getColumnCount()) == 0)
        continue; 
      removeCurrent();
    } 
    paramRowSetNavigatorData.release();
  }
  
  public void except(Session paramSession, RowSetNavigatorData paramRowSetNavigatorData) {
    removeDuplicates(paramSession);
    paramRowSetNavigatorData.sortFull(paramSession);
    while (hasNext()) {
      Object[] arrayOfObject = getNext();
      boolean bool = paramRowSetNavigatorData.containsRow(arrayOfObject);
      if (bool)
        removeCurrent(); 
    } 
    paramRowSetNavigatorData.release();
  }
  
  public void exceptAll(Session paramSession, RowSetNavigatorData paramRowSetNavigatorData) {
    Object[] arrayOfObject1 = null;
    Row row = null;
    Object[] arrayOfObject2 = null;
    sortFull(paramSession);
    paramRowSetNavigatorData.sortFull(paramSession);
    RowIterator rowIterator = this.fullIndex.emptyIterator();
    while (hasNext()) {
      Object[] arrayOfObject = getNext();
      boolean bool = (arrayOfObject1 == null || this.fullIndex.compareRowNonUnique(paramSession, arrayOfObject, arrayOfObject1, this.fullIndex.getColumnCount()) != 0) ? true : false;
      if (bool) {
        arrayOfObject1 = arrayOfObject;
        rowIterator = paramRowSetNavigatorData.findFirstRow(arrayOfObject);
      } 
      row = rowIterator.getNextRow();
      arrayOfObject2 = (row == null) ? null : row.getData();
      if (arrayOfObject2 != null && this.fullIndex.compareRowNonUnique(paramSession, arrayOfObject, arrayOfObject2, this.fullIndex.getColumnCount()) == 0)
        removeCurrent(); 
    } 
    paramRowSetNavigatorData.release();
  }
  
  public boolean hasUniqueNotNullRows(Session paramSession) {
    sortFull(paramSession);
    for (Object[] arrayOfObject = null; hasNext(); arrayOfObject = arrayOfObject1) {
      Object[] arrayOfObject1 = getNext();
      if (hasNull(arrayOfObject1))
        continue; 
      if (arrayOfObject != null && this.fullIndex.compareRow(paramSession, arrayOfObject, arrayOfObject1) == 0)
        return false; 
    } 
    return true;
  }
  
  public void removeDuplicates(Session paramSession) {
    sortFull(paramSession);
    for (Object[] arrayOfObject = null; next(); arrayOfObject = arrayOfObject1) {
      Object[] arrayOfObject1 = getCurrent();
      if (arrayOfObject != null && this.fullIndex.compareRow(paramSession, arrayOfObject, arrayOfObject1) == 0) {
        removeCurrent();
        continue;
      } 
    } 
    reset();
  }
  
  public void trim(int paramInt1, int paramInt2) {
    if (this.size == 0)
      return; 
    if (paramInt1 >= this.size) {
      clear();
      return;
    } 
    if (paramInt1 != 0) {
      reset();
      for (byte b1 = 0; b1 < paramInt1; b1++) {
        next();
        removeCurrent();
      } 
    } 
    if (paramInt2 == 0 || paramInt2 >= this.size)
      return; 
    reset();
    for (byte b = 0; b < paramInt2; b++)
      next(); 
    while (hasNext()) {
      next();
      removeCurrent();
    } 
  }
  
  boolean hasNull(Object[] paramArrayOfObject) {
    for (byte b = 0; b < this.visibleColumnCount; b++) {
      if (paramArrayOfObject[b] == null)
        return true; 
    } 
    return false;
  }
  
  public Object[] getGroupData(Object[] paramArrayOfObject) {
    if (this.isSimpleAggregate) {
      if (this.simpleAggregateData == null) {
        this.simpleAggregateData = paramArrayOfObject;
        return null;
      } 
      return this.simpleAggregateData;
    } 
    RowIterator rowIterator = this.groupIndex.findFirstRow(this.session, this.store, paramArrayOfObject);
    if (rowIterator.hasNext()) {
      Row row = rowIterator.getNextRow();
      if (this.isAggregate)
        row.setChanged(true); 
      return row.getData();
    } 
    return null;
  }
  
  boolean containsRow(Object[] paramArrayOfObject) {
    RowIterator rowIterator = this.mainIndex.findFirstRow(this.session, this.store, paramArrayOfObject);
    boolean bool = rowIterator.hasNext();
    rowIterator.release();
    return bool;
  }
  
  RowIterator findFirstRow(Object[] paramArrayOfObject) {
    return this.mainIndex.findFirstRow(this.session, this.store, paramArrayOfObject);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\navigator\RowSetNavigatorDataTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */