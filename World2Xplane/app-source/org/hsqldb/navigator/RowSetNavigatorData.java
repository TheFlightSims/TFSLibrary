package org.hsqldb.navigator;

import java.io.IOException;
import java.util.Comparator;
import java.util.TreeMap;
import org.hsqldb.QueryExpression;
import org.hsqldb.QuerySpecification;
import org.hsqldb.Row;
import org.hsqldb.Session;
import org.hsqldb.SortAndSlice;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArraySort;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class RowSetNavigatorData extends RowSetNavigator implements Comparator {
  public static final Object[][] emptyTable = new Object[0][];
  
  int currentOffset;
  
  int baseBlockSize;
  
  Object[][] table = emptyTable;
  
  final Session session;
  
  QueryExpression queryExpression;
  
  int visibleColumnCount;
  
  boolean isSimpleAggregate;
  
  Object[] simpleAggregateData;
  
  private Index mainIndex;
  
  TreeMap rowMap;
  
  LongKeyHashMap idMap;
  
  RowSetNavigatorData(Session paramSession) {
    this.session = paramSession;
  }
  
  public RowSetNavigatorData(Session paramSession, QuerySpecification paramQuerySpecification) {
    this.session = paramSession;
    this.queryExpression = (QueryExpression)paramQuerySpecification;
    this.rangePosition = paramQuerySpecification.resultRangePosition;
    this.visibleColumnCount = paramQuerySpecification.getColumnCount();
    this.isSimpleAggregate = (paramQuerySpecification.isAggregated && !paramQuerySpecification.isGrouped);
    if (paramQuerySpecification.isGrouped) {
      this.mainIndex = paramQuerySpecification.groupIndex;
      this.rowMap = new TreeMap<Object, Object>(this);
    } 
    if (paramQuerySpecification.idIndex != null)
      this.idMap = new LongKeyHashMap(); 
  }
  
  public RowSetNavigatorData(Session paramSession, QueryExpression paramQueryExpression) {
    this.session = paramSession;
    this.queryExpression = paramQueryExpression;
    this.visibleColumnCount = paramQueryExpression.getColumnCount();
  }
  
  public RowSetNavigatorData(Session paramSession, RowSetNavigator paramRowSetNavigator) {
    this.session = paramSession;
    setCapacity(paramRowSetNavigator.size);
    while (paramRowSetNavigator.hasNext())
      add(paramRowSetNavigator.getNext()); 
  }
  
  public void sortFull(Session paramSession) {
    this.mainIndex = this.queryExpression.fullIndex;
    ArraySort.sort((Object[])this.table, 0, this.size, this);
    reset();
  }
  
  public void sortOrder(Session paramSession) {
    if (this.queryExpression.orderIndex != null) {
      this.mainIndex = this.queryExpression.orderIndex;
      ArraySort.sort((Object[])this.table, 0, this.size, this);
    } 
    reset();
  }
  
  public void sortOrderUnion(Session paramSession, SortAndSlice paramSortAndSlice) {
    if (paramSortAndSlice.index != null) {
      this.mainIndex = paramSortAndSlice.index;
      ArraySort.sort((Object[])this.table, 0, this.size, this);
      reset();
    } 
  }
  
  public void add(Object[] paramArrayOfObject) {
    ensureCapacity();
    this.table[this.size] = paramArrayOfObject;
    this.size++;
    if (this.rowMap != null)
      this.rowMap.put(paramArrayOfObject, paramArrayOfObject); 
    if (this.idMap != null) {
      Long long_ = (Long)paramArrayOfObject[this.visibleColumnCount];
      this.idMap.put(long_.longValue(), paramArrayOfObject);
    } 
  }
  
  public boolean addRow(Row paramRow) {
    throw Error.runtimeError(201, "RowSetNavigatorClient");
  }
  
  public void update(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2) {}
  
  void addAdjusted(Object[] paramArrayOfObject, int[] paramArrayOfint) {
    paramArrayOfObject = projectData(paramArrayOfObject, paramArrayOfint);
    add(paramArrayOfObject);
  }
  
  void insertAdjusted(Object[] paramArrayOfObject, int[] paramArrayOfint) {
    projectData(paramArrayOfObject, paramArrayOfint);
    insert(paramArrayOfObject);
  }
  
  Object[] projectData(Object[] paramArrayOfObject, int[] paramArrayOfint) {
    if (paramArrayOfint == null) {
      paramArrayOfObject = (Object[])ArrayUtil.resizeArrayIfDifferent(paramArrayOfObject, this.visibleColumnCount);
    } else {
      Object[] arrayOfObject = new Object[this.visibleColumnCount];
      ArrayUtil.projectRow(paramArrayOfObject, paramArrayOfint, arrayOfObject);
      paramArrayOfObject = arrayOfObject;
    } 
    return paramArrayOfObject;
  }
  
  void insert(Object[] paramArrayOfObject) {
    ensureCapacity();
    System.arraycopy(this.table, this.currentPos, this.table, this.currentPos + 1, this.size - this.currentPos);
    this.table[this.currentPos] = paramArrayOfObject;
    this.size++;
  }
  
  public void release() {
    this.table = emptyTable;
    this.size = 0;
    reset();
  }
  
  public void clear() {
    this.table = emptyTable;
    this.size = 0;
    reset();
  }
  
  public boolean absolute(int paramInt) {
    return super.absolute(paramInt);
  }
  
  public Object[] getCurrent() {
    if (this.currentPos < 0 || this.currentPos >= this.size)
      return null; 
    if (this.currentPos == this.currentOffset + this.table.length)
      getBlock(this.currentOffset + this.table.length); 
    return this.table[this.currentPos - this.currentOffset];
  }
  
  public Row getCurrentRow() {
    throw Error.runtimeError(201, "RowSetNavigatorClient");
  }
  
  public Object[] getNextRowData() {
    return next() ? getCurrent() : null;
  }
  
  public boolean next() {
    return super.next();
  }
  
  public void removeCurrent() {
    System.arraycopy(this.table, this.currentPos + 1, this.table, this.currentPos, this.size - this.currentPos - 1);
    this.table[this.size - 1] = null;
    this.currentPos--;
    this.size--;
  }
  
  public void reset() {
    super.reset();
  }
  
  public boolean isMemory() {
    return true;
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
  
  public Object[] getData(long paramLong) {
    return (Object[])this.idMap.get(paramLong);
  }
  
  public void copy(RowIterator paramRowIterator, int[] paramArrayOfint) {
    while (paramRowIterator.hasNext()) {
      Object[] arrayOfObject = paramRowIterator.getNext();
      addAdjusted(arrayOfObject, paramArrayOfint);
    } 
  }
  
  public void union(Session paramSession, RowSetNavigatorData paramRowSetNavigatorData) {
    removeDuplicates(paramSession);
    paramRowSetNavigatorData.removeDuplicates(paramSession);
    this.mainIndex = this.queryExpression.fullIndex;
    while (paramRowSetNavigatorData.hasNext()) {
      Object[] arrayOfObject = paramRowSetNavigatorData.getNext();
      int i = ArraySort.searchFirst((Object[])this.table, 0, this.size, arrayOfObject, this);
      if (i < 0) {
        i = -i - 1;
        this.currentPos = i;
        insert(arrayOfObject);
      } 
    } 
    reset();
  }
  
  public void unionAll(Session paramSession, RowSetNavigatorData paramRowSetNavigatorData) {
    paramRowSetNavigatorData.reset();
    while (paramRowSetNavigatorData.hasNext()) {
      Object[] arrayOfObject = paramRowSetNavigatorData.getNext();
      add(arrayOfObject);
    } 
    reset();
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
    reset();
  }
  
  public void intersectAll(Session paramSession, RowSetNavigatorData paramRowSetNavigatorData) {
    Object[] arrayOfObject1 = null;
    Object[] arrayOfObject2 = null;
    sortFull(paramSession);
    paramRowSetNavigatorData.sortFull(paramSession);
    RowIterator rowIterator = this.queryExpression.fullIndex.emptyIterator();
    while (hasNext()) {
      Object[] arrayOfObject = getNext();
      boolean bool = (arrayOfObject1 == null || this.queryExpression.fullIndex.compareRowNonUnique(paramSession, arrayOfObject, arrayOfObject1, this.visibleColumnCount) != 0) ? true : false;
      if (bool) {
        arrayOfObject1 = arrayOfObject;
        rowIterator = paramRowSetNavigatorData.findFirstRow(arrayOfObject);
      } 
      arrayOfObject2 = rowIterator.getNext();
      if (arrayOfObject2 != null && this.queryExpression.fullIndex.compareRowNonUnique(paramSession, arrayOfObject, arrayOfObject2, this.visibleColumnCount) == 0)
        continue; 
      removeCurrent();
    } 
    reset();
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
    reset();
  }
  
  public void exceptAll(Session paramSession, RowSetNavigatorData paramRowSetNavigatorData) {
    Object[] arrayOfObject1 = null;
    Object[] arrayOfObject2 = null;
    sortFull(paramSession);
    paramRowSetNavigatorData.sortFull(paramSession);
    RowIterator rowIterator = this.queryExpression.fullIndex.emptyIterator();
    while (hasNext()) {
      Object[] arrayOfObject = getNext();
      boolean bool = (arrayOfObject1 == null || this.queryExpression.fullIndex.compareRowNonUnique(paramSession, arrayOfObject, arrayOfObject1, this.queryExpression.fullIndex.getColumnCount()) != 0) ? true : false;
      if (bool) {
        arrayOfObject1 = arrayOfObject;
        rowIterator = paramRowSetNavigatorData.findFirstRow(arrayOfObject);
      } 
      arrayOfObject2 = rowIterator.getNext();
      if (arrayOfObject2 != null && this.queryExpression.fullIndex.compareRowNonUnique(paramSession, arrayOfObject, arrayOfObject2, this.queryExpression.fullIndex.getColumnCount()) == 0)
        removeCurrent(); 
    } 
    reset();
  }
  
  public boolean hasUniqueNotNullRows(Session paramSession) {
    sortFull(paramSession);
    reset();
    for (Object[] arrayOfObject = null; hasNext(); arrayOfObject = arrayOfObject1) {
      Object[] arrayOfObject1 = getNext();
      if (hasNull(arrayOfObject1))
        continue; 
      if (arrayOfObject != null && this.queryExpression.fullIndex.compareRow(paramSession, arrayOfObject, arrayOfObject1) == 0)
        return false; 
    } 
    return true;
  }
  
  public void removeDuplicates(Session paramSession) {
    sortFull(paramSession);
    reset();
    int i = -1;
    Object[] arrayOfObject = null;
    while (hasNext()) {
      Object[] arrayOfObject1 = getNext();
      if (arrayOfObject == null) {
        i = this.currentPos;
        arrayOfObject = arrayOfObject1;
        continue;
      } 
      if (this.queryExpression.fullIndex.compareRow(paramSession, arrayOfObject, arrayOfObject1) != 0) {
        i++;
        arrayOfObject = arrayOfObject1;
        this.table[i] = arrayOfObject1;
      } 
    } 
    for (int j = i + 1; j < this.size; j++)
      this.table[j] = null; 
    this.size = i + 1;
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
    if (paramInt2 >= this.size)
      return; 
    reset();
    for (byte b = 0; b < paramInt2; b++)
      next(); 
    while (hasNext()) {
      next();
      removeCurrent();
    } 
    reset();
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
    return (Object[])this.rowMap.get(paramArrayOfObject);
  }
  
  boolean containsRow(Object[] paramArrayOfObject) {
    int i = ArraySort.searchFirst((Object[])this.table, 0, this.size, paramArrayOfObject, this);
    return (i >= 0);
  }
  
  RowIterator findFirstRow(Object[] paramArrayOfObject) {
    int i = ArraySort.searchFirst((Object[])this.table, 0, this.size, paramArrayOfObject, this);
    if (i < 0) {
      i = this.size;
    } else {
      i--;
    } 
    return new DataIterator(i);
  }
  
  void getBlock(int paramInt) {}
  
  private void setCapacity(int paramInt) {
    if (this.size > this.table.length)
      this.table = new Object[paramInt][]; 
  }
  
  private void ensureCapacity() {
    if (this.size == this.table.length) {
      boolean bool = (this.size == 0) ? true : (this.size * 2);
      Object[][] arrayOfObject = new Object[bool][];
      System.arraycopy(this.table, 0, arrayOfObject, 0, this.size);
      this.table = arrayOfObject;
    } 
  }
  
  void implement() {
    throw Error.error(201, "RSND");
  }
  
  public int compare(Object paramObject1, Object paramObject2) {
    return this.mainIndex.compareRow(this.session, (Object[])paramObject1, (Object[])paramObject2);
  }
  
  class DataIterator implements RowIterator {
    int pos;
    
    DataIterator(int param1Int) {
      this.pos = param1Int;
    }
    
    public Row getNextRow() {
      return null;
    }
    
    public Object[] getNext() {
      if (hasNext()) {
        this.pos++;
        return RowSetNavigatorData.this.table[this.pos];
      } 
      return null;
    }
    
    public boolean hasNext() {
      return (this.pos < RowSetNavigatorData.this.size - 1);
    }
    
    public void removeCurrent() {}
    
    public boolean setRowColumns(boolean[] param1ArrayOfboolean) {
      return false;
    }
    
    public void release() {}
    
    public long getRowId() {
      return 0L;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\navigator\RowSetNavigatorData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */