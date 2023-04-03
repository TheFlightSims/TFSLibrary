package org.hsqldb.index;

import org.hsqldb.Row;
import org.hsqldb.SchemaObject;
import org.hsqldb.Session;
import org.hsqldb.TableBase;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.types.Type;

public interface Index extends SchemaObject {
  public static final int INDEX_NONE = 0;
  
  public static final int INDEX_NON_UNIQUE = 1;
  
  public static final int INDEX_UNIQUE = 2;
  
  public static final double minimumSelectivity = 16.0D;
  
  public static final double cachedFactor = 8.0D;
  
  public static final int probeDepth = 4;
  
  public static final Index[] emptyArray = new Index[0];
  
  public static final IndexUse[] emptyUseArray = new IndexUse[0];
  
  IndexUse[] asArray();
  
  RowIterator emptyIterator();
  
  int getPosition();
  
  void setPosition(int paramInt);
  
  long getPersistenceId();
  
  int getColumnCount();
  
  boolean isUnique();
  
  boolean isConstraint();
  
  int[] getColumns();
  
  Type[] getColumnTypes();
  
  boolean[] getColumnDesc();
  
  int[] getDefaultColumnMap();
  
  int getIndexOrderValue();
  
  boolean isForward();
  
  void setTable(TableBase paramTableBase);
  
  void setClustered(boolean paramBoolean);
  
  boolean isClustered();
  
  long size(Session paramSession, PersistentStore paramPersistentStore);
  
  long sizeUnique(PersistentStore paramPersistentStore);
  
  double[] searchCost(Session paramSession, PersistentStore paramPersistentStore);
  
  boolean isEmpty(PersistentStore paramPersistentStore);
  
  void checkIndex(PersistentStore paramPersistentStore);
  
  void insert(Session paramSession, PersistentStore paramPersistentStore, Row paramRow);
  
  void delete(Session paramSession, PersistentStore paramPersistentStore, Row paramRow);
  
  boolean existsParent(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int[] paramArrayOfint);
  
  RowIterator findFirstRow(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, boolean[] paramArrayOfboolean);
  
  RowIterator findFirstRow(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject);
  
  RowIterator findFirstRow(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int[] paramArrayOfint);
  
  RowIterator findFirstRowNotNull(Session paramSession, PersistentStore paramPersistentStore);
  
  RowIterator firstRow(PersistentStore paramPersistentStore);
  
  RowIterator firstRow(Session paramSession, PersistentStore paramPersistentStore, int paramInt);
  
  RowIterator lastRow(Session paramSession, PersistentStore paramPersistentStore, int paramInt);
  
  int compareRowNonUnique(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfint);
  
  int compareRowNonUnique(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfint, int paramInt);
  
  int compareRowNonUnique(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt);
  
  int compareRow(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2);
  
  public static class IndexUse {
    public Index index;
    
    public int columnCount;
    
    public IndexUse(Index param1Index, int param1Int) {
      this.index = param1Index;
      this.columnCount = param1Int;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\index\Index.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */