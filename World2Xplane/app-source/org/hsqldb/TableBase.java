package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.types.Type;

public class TableBase {
  public static final int INFO_SCHEMA_TABLE = 1;
  
  public static final int SYSTEM_SUBQUERY = 2;
  
  public static final int TEMP_TABLE = 3;
  
  public static final int MEMORY_TABLE = 4;
  
  public static final int CACHED_TABLE = 5;
  
  public static final int TEMP_TEXT_TABLE = 6;
  
  public static final int TEXT_TABLE = 7;
  
  public static final int VIEW_TABLE = 8;
  
  public static final int RESULT_TABLE = 9;
  
  public static final int TRANSITION_TABLE = 10;
  
  public static final int FUNCTION_TABLE = 11;
  
  public static final int SYSTEM_TABLE = 12;
  
  public static final int CHANGE_SET_TABLE = 13;
  
  public static final int SCOPE_ROUTINE = 20;
  
  public static final int SCOPE_STATEMENT = 21;
  
  public static final int SCOPE_TRANSACTION = 22;
  
  public static final int SCOPE_SESSION = 23;
  
  public static final int SCOPE_FULL = 24;
  
  public PersistentStore store;
  
  public int persistenceScope;
  
  public long persistenceId;
  
  int tableSpace = 7;
  
  int[] primaryKeyCols;
  
  Type[] primaryKeyTypes;
  
  int[] primaryKeyColsSequence;
  
  Index[] indexList;
  
  public Database database;
  
  int[] bestRowIdentifierCols;
  
  boolean bestRowIdentifierStrict;
  
  int[] bestIndexForColumn;
  
  Index bestIndex;
  
  Index fullIndex;
  
  boolean[] colNotNull;
  
  Type[] colTypes;
  
  protected int columnCount;
  
  int tableType;
  
  protected boolean isReadOnly;
  
  protected boolean isTemp;
  
  protected boolean isCached;
  
  protected boolean isText;
  
  boolean isView;
  
  protected boolean isWithDataSource;
  
  public boolean isSessionBased;
  
  protected boolean isSchemaBased;
  
  protected boolean isLogged;
  
  private boolean isTransactional = true;
  
  boolean hasLobColumn;
  
  TableBase() {}
  
  public TableBase(Session paramSession, Database paramDatabase, int paramInt1, int paramInt2, Type[] paramArrayOfType) {
    this.tableType = paramInt2;
    this.persistenceScope = paramInt1;
    this.isSessionBased = true;
    this.persistenceId = paramDatabase.persistentStoreCollection.getNextId();
    this.database = paramDatabase;
    this.colTypes = paramArrayOfType;
    this.columnCount = paramArrayOfType.length;
    this.primaryKeyCols = new int[0];
    this.primaryKeyTypes = Type.emptyArray;
    this.indexList = Index.emptyArray;
    createPrimaryIndex(this.primaryKeyCols, this.primaryKeyTypes, null);
  }
  
  public TableBase duplicate() {
    TableBase tableBase = new TableBase();
    tableBase.tableType = this.tableType;
    tableBase.persistenceScope = this.persistenceScope;
    tableBase.isSessionBased = this.isSessionBased;
    tableBase.persistenceId = this.database.persistentStoreCollection.getNextId();
    tableBase.database = this.database;
    tableBase.colTypes = this.colTypes;
    tableBase.columnCount = this.columnCount;
    tableBase.primaryKeyCols = this.primaryKeyCols;
    tableBase.primaryKeyTypes = this.primaryKeyTypes;
    tableBase.indexList = this.indexList;
    return tableBase;
  }
  
  public final int getTableType() {
    return this.tableType;
  }
  
  public long getPersistenceId() {
    return this.persistenceId;
  }
  
  public int getSpaceID() {
    return this.tableSpace;
  }
  
  public void setSpaceID(int paramInt) {
    this.tableSpace = paramInt;
  }
  
  int getId() {
    return 0;
  }
  
  public final boolean onCommitPreserve() {
    return (this.persistenceScope == 23);
  }
  
  public final RowIterator rowIterator(Session paramSession) {
    PersistentStore persistentStore = getRowStore(paramSession);
    return getPrimaryIndex().firstRow(paramSession, persistentStore, 0);
  }
  
  public final RowIterator rowIterator(PersistentStore paramPersistentStore) {
    return getPrimaryIndex().firstRow(paramPersistentStore);
  }
  
  public final int getIndexCount() {
    return this.indexList.length;
  }
  
  public final Index getPrimaryIndex() {
    return (this.indexList.length > 0) ? this.indexList[0] : null;
  }
  
  public final Type[] getPrimaryKeyTypes() {
    return this.primaryKeyTypes;
  }
  
  public final boolean hasPrimaryKey() {
    return (this.primaryKeyCols.length != 0);
  }
  
  public final int[] getPrimaryKey() {
    return this.primaryKeyCols;
  }
  
  public final Type[] getColumnTypes() {
    return this.colTypes;
  }
  
  public Index getFullIndex() {
    return this.fullIndex;
  }
  
  public final Index getIndex(int paramInt) {
    return this.indexList[paramInt];
  }
  
  public final Index[] getIndexList() {
    return this.indexList;
  }
  
  public final boolean[] getNewColumnCheckList() {
    return new boolean[getColumnCount()];
  }
  
  public int getColumnCount() {
    return this.columnCount;
  }
  
  public final int getDataColumnCount() {
    return this.colTypes.length;
  }
  
  public boolean isTransactional() {
    return this.isTransactional;
  }
  
  public void setTransactional(boolean paramBoolean) {
    this.isTransactional = paramBoolean;
  }
  
  public final void setBestRowIdentifiers() {
    int[] arrayOfInt = null;
    int i = 0;
    boolean bool = false;
    int j = 0;
    if (this.colNotNull == null)
      return; 
    this.bestIndex = null;
    this.bestIndexForColumn = new int[this.colTypes.length];
    ArrayUtil.fillArray(this.bestIndexForColumn, -1);
    for (byte b = 0; b < this.indexList.length; b++) {
      Index index = this.indexList[b];
      int[] arrayOfInt1 = index.getColumns();
      int k = index.getColumnCount();
      if (k != 0) {
        if (b == 0)
          bool = true; 
        if (this.bestIndexForColumn[arrayOfInt1[0]] == -1) {
          this.bestIndexForColumn[arrayOfInt1[0]] = b;
        } else {
          Index index1 = this.indexList[this.bestIndexForColumn[arrayOfInt1[0]]];
          if (k > (index1.getColumns()).length)
            this.bestIndexForColumn[arrayOfInt1[0]] = b; 
        } 
        if (!index.isUnique()) {
          if (this.bestIndex == null)
            this.bestIndex = index; 
        } else {
          byte b1 = 0;
          for (byte b2 = 0; b2 < k; b2++) {
            if (this.colNotNull[arrayOfInt1[b2]])
              b1++; 
          } 
          if (this.bestIndex != null)
            this.bestIndex = index; 
          if (b1 == k) {
            if (arrayOfInt == null || i != j || k < i) {
              arrayOfInt = arrayOfInt1;
              i = k;
              j = k;
              bool = true;
            } 
          } else if (!bool && (arrayOfInt == null || k < i || b1 > j)) {
            arrayOfInt = arrayOfInt1;
            i = k;
            j = b1;
          } 
        } 
      } 
    } 
    if (arrayOfInt == null || i == arrayOfInt.length) {
      this.bestRowIdentifierCols = arrayOfInt;
    } else {
      this.bestRowIdentifierCols = ArrayUtil.arraySlice(arrayOfInt, 0, i);
    } 
    this.bestRowIdentifierStrict = bool;
    if (this.indexList[0].getColumnCount() > 0)
      this.bestIndex = this.indexList[0]; 
  }
  
  public boolean[] getColumnNotNull() {
    return this.colNotNull;
  }
  
  public final void createPrimaryIndex(int[] paramArrayOfint, Type[] paramArrayOfType, HsqlNameManager.HsqlName paramHsqlName) {
    long l = this.database.persistentStoreCollection.getNextId();
    Index index = this.database.logger.newIndex(paramHsqlName, l, this, paramArrayOfint, null, null, paramArrayOfType, true, (paramArrayOfint.length > 0), (paramArrayOfint.length > 0), false);
    try {
      addIndex(null, index);
    } catch (HsqlException hsqlException) {}
  }
  
  public final Index createAndAddIndexStructure(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, int[] paramArrayOfint, boolean[] paramArrayOfboolean1, boolean[] paramArrayOfboolean2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    Index index = createIndexStructure(paramHsqlName, paramArrayOfint, paramArrayOfboolean1, paramArrayOfboolean2, paramBoolean1, paramBoolean2, paramBoolean3);
    addIndex(paramSession, index);
    return index;
  }
  
  final Index createIndexStructure(HsqlNameManager.HsqlName paramHsqlName, int[] paramArrayOfint, boolean[] paramArrayOfboolean1, boolean[] paramArrayOfboolean2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    if (this.primaryKeyCols == null)
      throw Error.runtimeError(201, "createIndex"); 
    int i = paramArrayOfint.length;
    int[] arrayOfInt = new int[i];
    Type[] arrayOfType = new Type[i];
    for (byte b = 0; b < i; b++) {
      arrayOfInt[b] = paramArrayOfint[b];
      arrayOfType[b] = this.colTypes[arrayOfInt[b]];
    } 
    long l = this.database.persistentStoreCollection.getNextId();
    return this.database.logger.newIndex(paramHsqlName, l, this, arrayOfInt, paramArrayOfboolean1, paramArrayOfboolean2, arrayOfType, false, paramBoolean1, paramBoolean2, paramBoolean3);
  }
  
  public void dropIndex(Session paramSession, int paramInt) {
    this.indexList = (Index[])ArrayUtil.toAdjustedArray(this.indexList, null, paramInt, -1);
    for (byte b = 0; b < this.indexList.length; b++)
      this.indexList[b].setPosition(b); 
    setBestRowIdentifiers();
    if (this.store != null)
      this.store.resetAccessorKeys(paramSession, this.indexList); 
  }
  
  final void addIndex(Session paramSession, Index paramIndex) {
    byte b;
    for (b = 0; b < this.indexList.length; b++) {
      Index index = this.indexList[b];
      int i = paramIndex.getIndexOrderValue() - index.getIndexOrderValue();
      if (i < 0)
        break; 
    } 
    this.indexList = (Index[])ArrayUtil.toAdjustedArray(this.indexList, paramIndex, b, 1);
    for (b = 0; b < this.indexList.length; b++)
      this.indexList[b].setPosition(b); 
    if (this.store != null)
      try {
        this.store.resetAccessorKeys(paramSession, this.indexList);
      } catch (HsqlException hsqlException) {
        this.indexList = (Index[])ArrayUtil.toAdjustedArray(this.indexList, null, paramIndex.getPosition(), -1);
        for (b = 0; b < this.indexList.length; b++)
          this.indexList[b].setPosition(b); 
        throw hsqlException;
      }  
    setBestRowIdentifiers();
  }
  
  final void removeIndex(int paramInt) {
    setBestRowIdentifiers();
  }
  
  public final void setIndexes(Index[] paramArrayOfIndex) {
    this.indexList = paramArrayOfIndex;
  }
  
  public final Object[] getEmptyRowData() {
    return new Object[getDataColumnCount()];
  }
  
  public final Index createIndex(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, int[] paramArrayOfint, boolean[] paramArrayOfboolean1, boolean[] paramArrayOfboolean2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    return createAndAddIndexStructure(paramSession, paramHsqlName, paramArrayOfint, paramArrayOfboolean1, paramArrayOfboolean2, paramBoolean1, paramBoolean2, paramBoolean3);
  }
  
  public void clearAllData(Session paramSession) {
    PersistentStore persistentStore = getRowStore(paramSession);
    persistentStore.removeAll();
  }
  
  public void clearAllData(PersistentStore paramPersistentStore) {
    paramPersistentStore.removeAll();
  }
  
  public final boolean isEmpty(Session paramSession) {
    if (getIndexCount() == 0)
      return true; 
    PersistentStore persistentStore = getRowStore(paramSession);
    return getIndex(0).isEmpty(persistentStore);
  }
  
  public PersistentStore getRowStore(Session paramSession) {
    return (this.store == null) ? paramSession.sessionData.persistentStoreCollection.getStore(this) : this.store;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\TableBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */