package org.hsqldb.index;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.hsqldb.Constraint;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.SchemaObject;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.ReadWriteLockDummy;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.persist.CachedObject;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.rights.Grantee;
import org.hsqldb.types.Type;

public class IndexAVL implements Index {
  private static final IndexRowIterator emptyIterator = new IndexRowIterator(null, (PersistentStore)null, null, null, 0, false, false);
  
  private final long persistenceId;
  
  protected final HsqlNameManager.HsqlName name;
  
  private final boolean[] colCheck;
  
  final int[] colIndex;
  
  private final int[] defaultColMap;
  
  final Type[] colTypes;
  
  private final boolean[] colDesc;
  
  private final boolean[] nullsLast;
  
  final boolean isSimpleOrder;
  
  final boolean isSimple;
  
  protected final boolean isPK;
  
  protected final boolean isUnique;
  
  protected final boolean isConstraint;
  
  private final boolean isForward;
  
  private boolean isClustered;
  
  protected TableBase table;
  
  int position;
  
  private Index.IndexUse[] asArray;
  
  Object[] nullData;
  
  ReadWriteLock lock;
  
  Lock readLock;
  
  Lock writeLock;
  
  public IndexAVL(HsqlNameManager.HsqlName paramHsqlName, long paramLong, TableBase paramTableBase, int[] paramArrayOfint, boolean[] paramArrayOfboolean1, boolean[] paramArrayOfboolean2, Type[] paramArrayOfType, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    this.persistenceId = paramLong;
    this.name = paramHsqlName;
    this.colIndex = paramArrayOfint;
    this.colTypes = paramArrayOfType;
    this.colDesc = (paramArrayOfboolean1 == null) ? new boolean[paramArrayOfint.length] : paramArrayOfboolean1;
    this.nullsLast = (paramArrayOfboolean2 == null) ? new boolean[paramArrayOfint.length] : paramArrayOfboolean2;
    this.isPK = paramBoolean1;
    this.isUnique = paramBoolean2;
    this.isConstraint = paramBoolean3;
    this.isForward = paramBoolean4;
    this.table = paramTableBase;
    this.colCheck = paramTableBase.getNewColumnCheckList();
    this.asArray = new Index.IndexUse[] { new Index.IndexUse(this, this.colIndex.length) };
    ArrayUtil.intIndexesToBooleanArray(this.colIndex, this.colCheck);
    this.defaultColMap = new int[paramArrayOfint.length];
    ArrayUtil.fillSequence(this.defaultColMap);
    boolean bool = (this.colIndex.length > 0) ? true : false;
    for (byte b = 0; b < this.colDesc.length; b++) {
      if (this.colDesc[b] || this.nullsLast[b])
        bool = false; 
    } 
    this.isSimpleOrder = bool;
    this.isSimple = (this.isSimpleOrder && this.colIndex.length == 1);
    this.nullData = new Object[this.colIndex.length];
    switch (paramTableBase.getTableType()) {
      case 4:
      case 5:
      case 7:
        this.lock = new ReentrantReadWriteLock();
        break;
      default:
        this.lock = (ReadWriteLock)new ReadWriteLockDummy();
        break;
    } 
    this.readLock = this.lock.readLock();
    this.writeLock = this.lock.writeLock();
  }
  
  public int getType() {
    return 20;
  }
  
  public HsqlNameManager.HsqlName getName() {
    return this.name;
  }
  
  public HsqlNameManager.HsqlName getCatalogName() {
    return this.name.schema.schema;
  }
  
  public HsqlNameManager.HsqlName getSchemaName() {
    return this.name.schema;
  }
  
  public Grantee getOwner() {
    return this.name.schema.owner;
  }
  
  public OrderedHashSet getReferences() {
    return new OrderedHashSet();
  }
  
  public OrderedHashSet getComponents() {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {}
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer = new StringBuffer(64);
    stringBuffer.append("CREATE").append(' ');
    if (isUnique())
      stringBuffer.append("UNIQUE").append(' '); 
    stringBuffer.append("INDEX").append(' ');
    stringBuffer.append((getName()).statementName);
    stringBuffer.append(' ').append("ON").append(' ');
    stringBuffer.append(((Table)this.table).getName().getSchemaQualifiedStatementName());
    stringBuffer.append(((Table)this.table).getColumnListSQL(this.colIndex, this.colIndex.length));
    return stringBuffer.toString();
  }
  
  public long getChangeTimestamp() {
    return 0L;
  }
  
  public Index.IndexUse[] asArray() {
    return this.asArray;
  }
  
  public RowIterator emptyIterator() {
    return emptyIterator;
  }
  
  public int getPosition() {
    return this.position;
  }
  
  public void setPosition(int paramInt) {
    this.position = paramInt;
  }
  
  public long getPersistenceId() {
    return this.persistenceId;
  }
  
  public int getColumnCount() {
    return this.colIndex.length;
  }
  
  public boolean isUnique() {
    return this.isUnique;
  }
  
  public boolean isConstraint() {
    return this.isConstraint;
  }
  
  public int[] getColumns() {
    return this.colIndex;
  }
  
  public Type[] getColumnTypes() {
    return this.colTypes;
  }
  
  public boolean[] getColumnDesc() {
    return this.colDesc;
  }
  
  public int[] getDefaultColumnMap() {
    return this.defaultColMap;
  }
  
  public int getIndexOrderValue() {
    return this.isPK ? 0 : (this.isConstraint ? (this.isForward ? 4 : (this.isUnique ? 0 : 1)) : 2);
  }
  
  public boolean isForward() {
    return this.isForward;
  }
  
  public void setTable(TableBase paramTableBase) {
    this.table = paramTableBase;
  }
  
  public void setClustered(boolean paramBoolean) {
    this.isClustered = paramBoolean;
  }
  
  public boolean isClustered() {
    return this.isClustered;
  }
  
  public long size(Session paramSession, PersistentStore paramPersistentStore) {
    this.readLock.lock();
    try {
      return paramPersistentStore.elementCount(paramSession);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public long sizeUnique(PersistentStore paramPersistentStore) {
    this.readLock.lock();
    try {
      return paramPersistentStore.elementCountUnique(this);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public double[] searchCost(Session paramSession, PersistentStore paramPersistentStore) {
    boolean bool = false;
    byte b = 1;
    double[] arrayOfDouble = new double[this.colIndex.length];
    int i = 0;
    int[] arrayOfInt = new int[1];
    this.readLock.lock();
    try {
      NodeAVL nodeAVL1 = getAccessor(paramPersistentStore);
      NodeAVL nodeAVL2 = nodeAVL1;
      if (nodeAVL1 == null)
        return arrayOfDouble; 
      while (true) {
        nodeAVL1 = nodeAVL2;
        nodeAVL2 = nodeAVL1.getLeft(paramPersistentStore);
        if (nodeAVL2 == null)
          break; 
        if (i == 4) {
          bool = true;
          break;
        } 
        i++;
      } 
      while (true) {
        nodeAVL2 = next(paramPersistentStore, nodeAVL1, i, 4, arrayOfInt);
        i = arrayOfInt[0];
        if (nodeAVL2 == null) {
          if (bool) {
            double[] arrayOfDouble1 = new double[this.colIndex.length];
            int j = probeFactor(paramSession, paramPersistentStore, arrayOfDouble1, true) + probeFactor(paramSession, paramPersistentStore, arrayOfDouble1, false);
            for (byte b2 = 0; b2 < this.colIndex.length; b2++) {
              arrayOfDouble1[b2] = arrayOfDouble1[b2] / 2.0D;
              for (byte b3 = 0; b3 < arrayOfDouble1[b2]; b3++)
                arrayOfDouble[b2] = arrayOfDouble[b2] * 2.0D; 
            } 
          } 
          long l = paramPersistentStore.elementCount();
          for (byte b1 = 0; b1 < this.colIndex.length; b1++) {
            if (arrayOfDouble[b1] == 0.0D)
              arrayOfDouble[b1] = 1.0D; 
            arrayOfDouble[b1] = l / arrayOfDouble[b1];
            if (arrayOfDouble[b1] < 2.0D)
              arrayOfDouble[b1] = 2.0D; 
          } 
          return arrayOfDouble;
        } 
        compareRowForChange(paramSession, nodeAVL1.getData(paramPersistentStore), nodeAVL2.getData(paramPersistentStore), arrayOfDouble);
        nodeAVL1 = nodeAVL2;
        b++;
      } 
    } finally {
      this.readLock.unlock();
    } 
  }
  
  int probeFactor(Session paramSession, PersistentStore paramPersistentStore, double[] paramArrayOfdouble, boolean paramBoolean) {
    byte b = 0;
    NodeAVL nodeAVL1 = getAccessor(paramPersistentStore);
    NodeAVL nodeAVL2 = nodeAVL1;
    if (nodeAVL1 == null)
      return 0; 
    while (nodeAVL2 != null) {
      nodeAVL1 = nodeAVL2;
      nodeAVL2 = paramBoolean ? nodeAVL1.getLeft(paramPersistentStore) : nodeAVL1.getRight(paramPersistentStore);
      if (++b > 4 && nodeAVL2 != null)
        compareRowForChange(paramSession, nodeAVL1.getData(paramPersistentStore), nodeAVL2.getData(paramPersistentStore), paramArrayOfdouble); 
    } 
    return b - 4;
  }
  
  public long getNodeCount(Session paramSession, PersistentStore paramPersistentStore) {
    long l = 0L;
    this.readLock.lock();
    try {
      RowIterator rowIterator = firstRow(paramSession, paramPersistentStore, 0);
      while (rowIterator.hasNext()) {
        rowIterator.getNextRow();
        l++;
      } 
      return l;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public boolean isEmpty(PersistentStore paramPersistentStore) {
    this.readLock.lock();
    try {
      return (getAccessor(paramPersistentStore) == null);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public void unlinkNodes(NodeAVL paramNodeAVL) {
    this.writeLock.lock();
    try {
      NodeAVL nodeAVL1 = paramNodeAVL;
      for (NodeAVL nodeAVL2 = nodeAVL1; nodeAVL2 != null; nodeAVL2 = nodeAVL1.getLeft(null))
        nodeAVL1 = nodeAVL2; 
      while (nodeAVL1 != null) {
        NodeAVL nodeAVL = nextUnlink(nodeAVL1);
        nodeAVL1 = nodeAVL;
      } 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  private NodeAVL nextUnlink(NodeAVL paramNodeAVL) {
    NodeAVL nodeAVL = paramNodeAVL.getRight(null);
    if (nodeAVL != null) {
      paramNodeAVL = nodeAVL;
      for (nodeAVL = paramNodeAVL.getLeft(null); nodeAVL != null; nodeAVL = paramNodeAVL.getLeft(null))
        paramNodeAVL = nodeAVL; 
      return paramNodeAVL;
    } 
    nodeAVL = paramNodeAVL;
    for (paramNodeAVL = paramNodeAVL.getParent(null); paramNodeAVL != null && paramNodeAVL.isRight(nodeAVL); paramNodeAVL = paramNodeAVL.getParent(null)) {
      paramNodeAVL.nRight = null;
      nodeAVL.getRow(null).destroy();
      nodeAVL.delete();
      nodeAVL = paramNodeAVL;
    } 
    if (paramNodeAVL != null)
      paramNodeAVL.nLeft = null; 
    nodeAVL.getRow(null).destroy();
    nodeAVL.delete();
    return paramNodeAVL;
  }
  
  public void checkIndex(PersistentStore paramPersistentStore) {
    this.readLock.lock();
    try {
      NodeAVL nodeAVL1 = getAccessor(paramPersistentStore);
      NodeAVL nodeAVL2 = null;
      while (nodeAVL1 != null) {
        nodeAVL2 = nodeAVL1;
        checkNodes(paramPersistentStore, nodeAVL1);
        nodeAVL1 = nodeAVL1.getLeft(paramPersistentStore);
      } 
      nodeAVL1 = nodeAVL2;
      while (nodeAVL2 != null) {
        checkNodes(paramPersistentStore, nodeAVL2);
        nodeAVL2 = next(paramPersistentStore, nodeAVL2);
      } 
    } finally {
      this.readLock.unlock();
    } 
  }
  
  void checkNodes(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL) {
    NodeAVL nodeAVL1 = paramNodeAVL.getLeft(paramPersistentStore);
    NodeAVL nodeAVL2 = paramNodeAVL.getRight(paramPersistentStore);
    if (nodeAVL1 != null && nodeAVL1.getBalance(paramPersistentStore) == -2)
      System.out.print("broken index - deleted"); 
    if (nodeAVL2 != null && nodeAVL2.getBalance(paramPersistentStore) == -2)
      System.out.print("broken index -deleted"); 
    if (nodeAVL1 != null && !paramNodeAVL.equals(nodeAVL1.getParent(paramPersistentStore)))
      System.out.print("broken index - no parent"); 
    if (nodeAVL2 != null && !paramNodeAVL.equals(nodeAVL2.getParent(paramPersistentStore)))
      System.out.print("broken index - no parent"); 
  }
  
  public int compareRowNonUnique(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfint) {
    int i = paramArrayOfint.length;
    for (byte b = 0; b < i; b++) {
      int j = this.colTypes[b].compare(paramSession, paramArrayOfObject1[this.colIndex[b]], paramArrayOfObject2[paramArrayOfint[b]]);
      if (j != 0)
        return j; 
    } 
    return 0;
  }
  
  public int compareRowNonUnique(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfint, int paramInt) {
    for (byte b = 0; b < paramInt; b++) {
      int i = this.colTypes[b].compare(paramSession, paramArrayOfObject1[this.colIndex[b]], paramArrayOfObject2[paramArrayOfint[b]]);
      if (i != 0)
        return i; 
    } 
    return 0;
  }
  
  public int compareRowNonUnique(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt) {
    for (byte b = 0; b < paramInt; b++) {
      int i = this.colTypes[b].compare(paramSession, paramArrayOfObject1[this.colIndex[b]], paramArrayOfObject2[this.colIndex[b]]);
      if (i != 0)
        return i; 
    } 
    return 0;
  }
  
  public void compareRowForChange(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, double[] paramArrayOfdouble) {
    for (byte b = 0; b < this.colIndex.length; b++) {
      int i = this.colTypes[b].compare(paramSession, paramArrayOfObject1[this.colIndex[b]], paramArrayOfObject2[this.colIndex[b]]);
      if (i != 0)
        while (b < this.colIndex.length) {
          paramArrayOfdouble[b] = paramArrayOfdouble[b] + 1.0D;
          b++;
        }  
    } 
  }
  
  public int compareRow(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2) {
    for (byte b = 0; b < this.colIndex.length; b++) {
      int i = this.colTypes[b].compare(paramSession, paramArrayOfObject1[this.colIndex[b]], paramArrayOfObject2[this.colIndex[b]]);
      if (i != 0) {
        if (this.isSimpleOrder)
          return i; 
        boolean bool = (paramArrayOfObject1[this.colIndex[b]] == null || paramArrayOfObject2[this.colIndex[b]] == null) ? true : false;
        if (this.colDesc[b] && !bool)
          i = -i; 
        if (this.nullsLast[b] && bool)
          i = -i; 
        return i;
      } 
    } 
    return 0;
  }
  
  int compareRowForInsertOrDelete(Session paramSession, Row paramRow1, Row paramRow2, boolean paramBoolean, int paramInt) {
    Object[] arrayOfObject1 = paramRow1.getData();
    Object[] arrayOfObject2 = paramRow2.getData();
    for (int i = paramInt; i < this.colIndex.length; i++) {
      int j = this.colTypes[i].compare(paramSession, arrayOfObject1[this.colIndex[i]], arrayOfObject2[this.colIndex[i]]);
      if (j != 0) {
        if (this.isSimpleOrder)
          return j; 
        boolean bool = (arrayOfObject1[this.colIndex[i]] == null || arrayOfObject2[this.colIndex[i]] == null) ? true : false;
        if (this.colDesc[i] && !bool)
          j = -j; 
        if (this.nullsLast[i] && bool)
          j = -j; 
        return j;
      } 
    } 
    if (paramBoolean) {
      long l = paramRow1.getPos() - paramRow2.getPos();
      return (l == 0L) ? 0 : ((l > 0L) ? 1 : -1);
    } 
    return 0;
  }
  
  int compareObject(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfint, int paramInt1, int paramInt2) {
    return this.colTypes[paramInt1].compare(paramSession, paramArrayOfObject1[this.colIndex[paramInt1]], paramArrayOfObject2[paramArrayOfint[paramInt1]], paramInt2);
  }
  
  boolean hasNulls(Session paramSession, Object[] paramArrayOfObject) {
    if (this.colIndex.length == 1)
      return (paramArrayOfObject[this.colIndex[0]] == null); 
    boolean bool = (paramSession == null) ? true : paramSession.database.sqlUniqueNulls;
    for (byte b = 0; b < this.colIndex.length; b++) {
      if (paramArrayOfObject[this.colIndex[b]] == null) {
        if (bool)
          return true; 
      } else if (!bool) {
        return false;
      } 
    } 
    return !bool;
  }
  
  public void insert(Session paramSession, PersistentStore paramPersistentStore, Row paramRow) {
    boolean bool1 = true;
    int i = -1;
    boolean bool2 = (!this.isUnique || hasNulls(paramSession, paramRow.getData())) ? true : false;
    this.writeLock.lock();
    paramPersistentStore.writeLock();
    try {
      NodeAVL nodeAVL1 = getAccessor(paramPersistentStore);
      NodeAVL nodeAVL2 = nodeAVL1;
      if (nodeAVL1 == null) {
        paramPersistentStore.setAccessor(this, ((RowAVL)paramRow).getNode(this.position));
        return;
      } 
      while (true) {
        RowAVL rowAVL = nodeAVL1.getRow(paramPersistentStore);
        i = compareRowForInsertOrDelete(paramSession, paramRow, (Row)rowAVL, bool2, 0);
        if (i == 0 && paramSession != null && !bool2 && paramSession.database.txManager.isMVRows() && !isEqualReadable(paramSession, paramPersistentStore, nodeAVL1)) {
          bool2 = true;
          i = compareRowForInsertOrDelete(paramSession, paramRow, (Row)rowAVL, bool2, this.colIndex.length);
        } 
        if (i == 0) {
          Constraint constraint = null;
          if (this.isConstraint)
            constraint = ((Table)this.table).getUniqueConstraintForIndex(this); 
          if (constraint == null)
            throw Error.error(104, this.name.statementName); 
          throw constraint.getException(paramRow.getData());
        } 
        bool1 = (i < 0) ? true : false;
        nodeAVL2 = nodeAVL1;
        nodeAVL1 = nodeAVL2.child(paramPersistentStore, bool1);
        if (nodeAVL1 == null) {
          nodeAVL2 = nodeAVL2.set(paramPersistentStore, bool1, ((RowAVL)paramRow).getNode(this.position));
          balance(paramPersistentStore, nodeAVL2, bool1);
          return;
        } 
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } finally {
      paramPersistentStore.writeUnlock();
      this.writeLock.unlock();
    } 
  }
  
  public void delete(Session paramSession, PersistentStore paramPersistentStore, Row paramRow) {
    if (!paramRow.isInMemory())
      paramRow = (Row)paramPersistentStore.get((CachedObject)paramRow, false); 
    NodeAVL nodeAVL = ((RowAVL)paramRow).getNode(this.position);
    if (nodeAVL != null)
      delete(paramPersistentStore, nodeAVL); 
  }
  
  void delete(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL) {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull -> 5
    //   4: return
    //   5: aload_0
    //   6: getfield writeLock : Ljava/util/concurrent/locks/Lock;
    //   9: invokeinterface lock : ()V
    //   14: aload_1
    //   15: invokeinterface writeLock : ()V
    //   20: aload_2
    //   21: aload_1
    //   22: invokevirtual getLeft : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   25: ifnonnull -> 37
    //   28: aload_2
    //   29: aload_1
    //   30: invokevirtual getRight : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   33: astore_3
    //   34: goto -> 384
    //   37: aload_2
    //   38: aload_1
    //   39: invokevirtual getRight : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   42: ifnonnull -> 54
    //   45: aload_2
    //   46: aload_1
    //   47: invokevirtual getLeft : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   50: astore_3
    //   51: goto -> 384
    //   54: aload_2
    //   55: astore #4
    //   57: aload_2
    //   58: aload_1
    //   59: invokevirtual getLeft : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   62: astore_2
    //   63: aload_2
    //   64: aload_1
    //   65: invokevirtual getRight : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   68: astore #5
    //   70: aload #5
    //   72: ifnonnull -> 78
    //   75: goto -> 84
    //   78: aload #5
    //   80: astore_2
    //   81: goto -> 63
    //   84: aload_2
    //   85: aload_1
    //   86: invokevirtual getLeft : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   89: astore_3
    //   90: aload_2
    //   91: aload_1
    //   92: invokevirtual getBalance : (Lorg/hsqldb/persist/PersistentStore;)I
    //   95: istore #5
    //   97: aload_2
    //   98: aload_1
    //   99: aload #4
    //   101: aload_1
    //   102: invokevirtual getBalance : (Lorg/hsqldb/persist/PersistentStore;)I
    //   105: invokevirtual setBalance : (Lorg/hsqldb/persist/PersistentStore;I)Lorg/hsqldb/index/NodeAVL;
    //   108: astore_2
    //   109: aload #4
    //   111: aload_1
    //   112: iload #5
    //   114: invokevirtual setBalance : (Lorg/hsqldb/persist/PersistentStore;I)Lorg/hsqldb/index/NodeAVL;
    //   117: astore #4
    //   119: aload_2
    //   120: aload_1
    //   121: invokevirtual getParent : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   124: astore #6
    //   126: aload #4
    //   128: aload_1
    //   129: invokevirtual getParent : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   132: astore #7
    //   134: aload #4
    //   136: aload_1
    //   137: invokevirtual isRoot : (Lorg/hsqldb/persist/PersistentStore;)Z
    //   140: ifeq -> 151
    //   143: aload_1
    //   144: aload_0
    //   145: aload_2
    //   146: invokeinterface setAccessor : (Lorg/hsqldb/index/Index;Lorg/hsqldb/persist/CachedObject;)V
    //   151: aload_2
    //   152: aload_1
    //   153: aload #7
    //   155: invokevirtual setParent : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   158: astore_2
    //   159: aload #7
    //   161: ifnull -> 195
    //   164: aload #7
    //   166: aload #4
    //   168: invokevirtual isRight : (Lorg/hsqldb/index/NodeAVL;)Z
    //   171: ifeq -> 186
    //   174: aload #7
    //   176: aload_1
    //   177: aload_2
    //   178: invokevirtual setRight : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   181: astore #7
    //   183: goto -> 195
    //   186: aload #7
    //   188: aload_1
    //   189: aload_2
    //   190: invokevirtual setLeft : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   193: astore #7
    //   195: aload #4
    //   197: aload #6
    //   199: invokevirtual equals : (Lorg/hsqldb/index/NodeAVL;)Z
    //   202: ifeq -> 277
    //   205: aload #4
    //   207: aload_1
    //   208: aload_2
    //   209: invokevirtual setParent : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   212: astore #4
    //   214: aload #4
    //   216: aload_2
    //   217: invokevirtual isLeft : (Lorg/hsqldb/index/NodeAVL;)Z
    //   220: ifeq -> 250
    //   223: aload_2
    //   224: aload_1
    //   225: aload #4
    //   227: invokevirtual setLeft : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   230: astore_2
    //   231: aload #4
    //   233: aload_1
    //   234: invokevirtual getRight : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   237: astore #8
    //   239: aload_2
    //   240: aload_1
    //   241: aload #8
    //   243: invokevirtual setRight : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   246: astore_2
    //   247: goto -> 329
    //   250: aload_2
    //   251: aload_1
    //   252: aload #4
    //   254: invokevirtual setRight : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   257: astore_2
    //   258: aload #4
    //   260: aload_1
    //   261: invokevirtual getLeft : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   264: astore #8
    //   266: aload_2
    //   267: aload_1
    //   268: aload #8
    //   270: invokevirtual setLeft : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   273: astore_2
    //   274: goto -> 329
    //   277: aload #4
    //   279: aload_1
    //   280: aload #6
    //   282: invokevirtual setParent : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   285: astore #4
    //   287: aload #6
    //   289: aload_1
    //   290: aload #4
    //   292: invokevirtual setRight : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   295: astore #6
    //   297: aload #4
    //   299: aload_1
    //   300: invokevirtual getLeft : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   303: astore #8
    //   305: aload #4
    //   307: aload_1
    //   308: invokevirtual getRight : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   311: astore #9
    //   313: aload_2
    //   314: aload_1
    //   315: aload #8
    //   317: invokevirtual setLeft : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   320: astore_2
    //   321: aload_2
    //   322: aload_1
    //   323: aload #9
    //   325: invokevirtual setRight : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   328: astore_2
    //   329: aload_2
    //   330: aload_1
    //   331: invokevirtual getRight : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   334: aload_1
    //   335: aload_2
    //   336: invokevirtual setParent : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   339: pop
    //   340: aload_2
    //   341: aload_1
    //   342: invokevirtual getLeft : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   345: aload_1
    //   346: aload_2
    //   347: invokevirtual setParent : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   350: pop
    //   351: aload #4
    //   353: aload_1
    //   354: aload_3
    //   355: invokevirtual setLeft : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   358: astore #4
    //   360: aload_3
    //   361: ifnull -> 372
    //   364: aload_3
    //   365: aload_1
    //   366: aload #4
    //   368: invokevirtual setParent : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   371: astore_3
    //   372: aload #4
    //   374: aload_1
    //   375: aconst_null
    //   376: invokevirtual setRight : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   379: astore #4
    //   381: aload #4
    //   383: astore_2
    //   384: aload_2
    //   385: aload_1
    //   386: invokevirtual isFromLeft : (Lorg/hsqldb/persist/PersistentStore;)Z
    //   389: istore #4
    //   391: aload_2
    //   392: aload_1
    //   393: aload_0
    //   394: aload_3
    //   395: invokevirtual replace : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/Index;Lorg/hsqldb/index/NodeAVL;)V
    //   398: aload_2
    //   399: aload_1
    //   400: invokevirtual getParent : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   403: astore_3
    //   404: aload_2
    //   405: invokevirtual delete : ()V
    //   408: aload_3
    //   409: ifnull -> 813
    //   412: aload_3
    //   413: astore_2
    //   414: iload #4
    //   416: ifeq -> 423
    //   419: iconst_1
    //   420: goto -> 424
    //   423: iconst_m1
    //   424: istore #5
    //   426: aload_2
    //   427: aload_1
    //   428: invokevirtual getBalance : (Lorg/hsqldb/persist/PersistentStore;)I
    //   431: iload #5
    //   433: imul
    //   434: tableswitch default -> 797, -1 -> 460, 0 -> 470, 1 -> 494
    //   460: aload_2
    //   461: aload_1
    //   462: iconst_0
    //   463: invokevirtual setBalance : (Lorg/hsqldb/persist/PersistentStore;I)Lorg/hsqldb/index/NodeAVL;
    //   466: astore_2
    //   467: goto -> 797
    //   470: aload_2
    //   471: aload_1
    //   472: iload #5
    //   474: invokevirtual setBalance : (Lorg/hsqldb/persist/PersistentStore;I)Lorg/hsqldb/index/NodeAVL;
    //   477: astore_2
    //   478: aload_1
    //   479: invokeinterface writeUnlock : ()V
    //   484: aload_0
    //   485: getfield writeLock : Ljava/util/concurrent/locks/Lock;
    //   488: invokeinterface unlock : ()V
    //   493: return
    //   494: aload_2
    //   495: aload_1
    //   496: iload #4
    //   498: ifne -> 505
    //   501: iconst_1
    //   502: goto -> 506
    //   505: iconst_0
    //   506: invokevirtual child : (Lorg/hsqldb/persist/PersistentStore;Z)Lorg/hsqldb/index/NodeAVL;
    //   509: astore #6
    //   511: aload #6
    //   513: aload_1
    //   514: invokevirtual getBalance : (Lorg/hsqldb/persist/PersistentStore;)I
    //   517: istore #7
    //   519: iload #7
    //   521: iload #5
    //   523: imul
    //   524: iflt -> 636
    //   527: aload_2
    //   528: aload_1
    //   529: aload_0
    //   530: aload #6
    //   532: invokevirtual replace : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/Index;Lorg/hsqldb/index/NodeAVL;)V
    //   535: aload #6
    //   537: aload_1
    //   538: iload #4
    //   540: invokevirtual child : (Lorg/hsqldb/persist/PersistentStore;Z)Lorg/hsqldb/index/NodeAVL;
    //   543: astore #8
    //   545: aload_2
    //   546: aload_1
    //   547: iload #4
    //   549: ifne -> 556
    //   552: iconst_1
    //   553: goto -> 557
    //   556: iconst_0
    //   557: aload #8
    //   559: invokevirtual set : (Lorg/hsqldb/persist/PersistentStore;ZLorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   562: astore_2
    //   563: aload #6
    //   565: aload_1
    //   566: iload #4
    //   568: aload_2
    //   569: invokevirtual set : (Lorg/hsqldb/persist/PersistentStore;ZLorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   572: astore #6
    //   574: iload #7
    //   576: ifne -> 614
    //   579: aload_2
    //   580: aload_1
    //   581: iload #5
    //   583: invokevirtual setBalance : (Lorg/hsqldb/persist/PersistentStore;I)Lorg/hsqldb/index/NodeAVL;
    //   586: astore_2
    //   587: aload #6
    //   589: aload_1
    //   590: iload #5
    //   592: ineg
    //   593: invokevirtual setBalance : (Lorg/hsqldb/persist/PersistentStore;I)Lorg/hsqldb/index/NodeAVL;
    //   596: astore #6
    //   598: aload_1
    //   599: invokeinterface writeUnlock : ()V
    //   604: aload_0
    //   605: getfield writeLock : Ljava/util/concurrent/locks/Lock;
    //   608: invokeinterface unlock : ()V
    //   613: return
    //   614: aload_2
    //   615: aload_1
    //   616: iconst_0
    //   617: invokevirtual setBalance : (Lorg/hsqldb/persist/PersistentStore;I)Lorg/hsqldb/index/NodeAVL;
    //   620: astore_2
    //   621: aload #6
    //   623: aload_1
    //   624: iconst_0
    //   625: invokevirtual setBalance : (Lorg/hsqldb/persist/PersistentStore;I)Lorg/hsqldb/index/NodeAVL;
    //   628: astore #6
    //   630: aload #6
    //   632: astore_2
    //   633: goto -> 797
    //   636: aload #6
    //   638: aload_1
    //   639: iload #4
    //   641: invokevirtual child : (Lorg/hsqldb/persist/PersistentStore;Z)Lorg/hsqldb/index/NodeAVL;
    //   644: astore #8
    //   646: aload_2
    //   647: aload_1
    //   648: aload_0
    //   649: aload #8
    //   651: invokevirtual replace : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/Index;Lorg/hsqldb/index/NodeAVL;)V
    //   654: aload #8
    //   656: aload_1
    //   657: invokevirtual getBalance : (Lorg/hsqldb/persist/PersistentStore;)I
    //   660: istore #7
    //   662: aload #6
    //   664: aload_1
    //   665: iload #4
    //   667: aload #8
    //   669: aload_1
    //   670: iload #4
    //   672: ifne -> 679
    //   675: iconst_1
    //   676: goto -> 680
    //   679: iconst_0
    //   680: invokevirtual child : (Lorg/hsqldb/persist/PersistentStore;Z)Lorg/hsqldb/index/NodeAVL;
    //   683: invokevirtual set : (Lorg/hsqldb/persist/PersistentStore;ZLorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   686: astore #6
    //   688: aload #8
    //   690: aload_1
    //   691: iload #4
    //   693: ifne -> 700
    //   696: iconst_1
    //   697: goto -> 701
    //   700: iconst_0
    //   701: aload #6
    //   703: invokevirtual set : (Lorg/hsqldb/persist/PersistentStore;ZLorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   706: astore #8
    //   708: aload_2
    //   709: aload_1
    //   710: iload #4
    //   712: ifne -> 719
    //   715: iconst_1
    //   716: goto -> 720
    //   719: iconst_0
    //   720: aload #8
    //   722: aload_1
    //   723: iload #4
    //   725: invokevirtual child : (Lorg/hsqldb/persist/PersistentStore;Z)Lorg/hsqldb/index/NodeAVL;
    //   728: invokevirtual set : (Lorg/hsqldb/persist/PersistentStore;ZLorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   731: astore_2
    //   732: aload #8
    //   734: aload_1
    //   735: iload #4
    //   737: aload_2
    //   738: invokevirtual set : (Lorg/hsqldb/persist/PersistentStore;ZLorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   741: astore #8
    //   743: aload_2
    //   744: aload_1
    //   745: iload #7
    //   747: iload #5
    //   749: if_icmpne -> 758
    //   752: iload #5
    //   754: ineg
    //   755: goto -> 759
    //   758: iconst_0
    //   759: invokevirtual setBalance : (Lorg/hsqldb/persist/PersistentStore;I)Lorg/hsqldb/index/NodeAVL;
    //   762: astore_2
    //   763: aload #6
    //   765: aload_1
    //   766: iload #7
    //   768: iload #5
    //   770: ineg
    //   771: if_icmpne -> 779
    //   774: iload #5
    //   776: goto -> 780
    //   779: iconst_0
    //   780: invokevirtual setBalance : (Lorg/hsqldb/persist/PersistentStore;I)Lorg/hsqldb/index/NodeAVL;
    //   783: astore #6
    //   785: aload #8
    //   787: aload_1
    //   788: iconst_0
    //   789: invokevirtual setBalance : (Lorg/hsqldb/persist/PersistentStore;I)Lorg/hsqldb/index/NodeAVL;
    //   792: astore #8
    //   794: aload #8
    //   796: astore_2
    //   797: aload_2
    //   798: aload_1
    //   799: invokevirtual isFromLeft : (Lorg/hsqldb/persist/PersistentStore;)Z
    //   802: istore #4
    //   804: aload_2
    //   805: aload_1
    //   806: invokevirtual getParent : (Lorg/hsqldb/persist/PersistentStore;)Lorg/hsqldb/index/NodeAVL;
    //   809: astore_3
    //   810: goto -> 408
    //   813: aload_1
    //   814: invokeinterface writeUnlock : ()V
    //   819: aload_0
    //   820: getfield writeLock : Ljava/util/concurrent/locks/Lock;
    //   823: invokeinterface unlock : ()V
    //   828: goto -> 856
    //   831: astore #4
    //   833: aload #4
    //   835: athrow
    //   836: astore #10
    //   838: aload_1
    //   839: invokeinterface writeUnlock : ()V
    //   844: aload_0
    //   845: getfield writeLock : Ljava/util/concurrent/locks/Lock;
    //   848: invokeinterface unlock : ()V
    //   853: aload #10
    //   855: athrow
    //   856: return
    // Exception table:
    //   from	to	target	type
    //   20	478	831	java/lang/RuntimeException
    //   20	478	836	finally
    //   494	598	831	java/lang/RuntimeException
    //   494	598	836	finally
    //   614	813	831	java/lang/RuntimeException
    //   614	813	836	finally
    //   831	838	836	finally
  }
  
  public boolean existsParent(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int[] paramArrayOfint) {
    NodeAVL nodeAVL = findNode(paramSession, paramPersistentStore, paramArrayOfObject, paramArrayOfint, paramArrayOfint.length, 40, 2, false);
    return (nodeAVL != null);
  }
  
  public RowIterator findFirstRow(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, boolean[] paramArrayOfboolean) {
    NodeAVL nodeAVL = findNode(paramSession, paramPersistentStore, paramArrayOfObject, this.defaultColMap, paramInt1, paramInt3, 0, paramBoolean);
    return (nodeAVL == null) ? emptyIterator : new IndexRowIterator(paramSession, paramPersistentStore, this, nodeAVL, paramInt2, false, paramBoolean);
  }
  
  public RowIterator findFirstRow(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject) {
    NodeAVL nodeAVL = findNode(paramSession, paramPersistentStore, paramArrayOfObject, this.colIndex, this.colIndex.length, 40, 0, false);
    return (nodeAVL == null) ? emptyIterator : new IndexRowIterator(paramSession, paramPersistentStore, this, nodeAVL, 0, false, false);
  }
  
  public RowIterator findFirstRow(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int[] paramArrayOfint) {
    NodeAVL nodeAVL = findNode(paramSession, paramPersistentStore, paramArrayOfObject, paramArrayOfint, paramArrayOfint.length, 40, 0, false);
    return (nodeAVL == null) ? emptyIterator : new IndexRowIterator(paramSession, paramPersistentStore, this, nodeAVL, 0, false, false);
  }
  
  public RowIterator findFirstRowNotNull(Session paramSession, PersistentStore paramPersistentStore) {
    NodeAVL nodeAVL = findNode(paramSession, paramPersistentStore, this.nullData, this.defaultColMap, 1, 48, 0, false);
    return (nodeAVL == null) ? emptyIterator : new IndexRowIterator(paramSession, paramPersistentStore, this, nodeAVL, 0, false, false);
  }
  
  public RowIterator firstRow(Session paramSession, PersistentStore paramPersistentStore, int paramInt) {
    this.readLock.lock();
    try {
      NodeAVL nodeAVL1 = getAccessor(paramPersistentStore);
      for (NodeAVL nodeAVL2 = nodeAVL1; nodeAVL2 != null; nodeAVL2 = nodeAVL1.getLeft(paramPersistentStore))
        nodeAVL1 = nodeAVL2; 
      while (paramSession != null && nodeAVL1 != null) {
        RowAVL rowAVL = nodeAVL1.getRow(paramPersistentStore);
        if (paramSession.database.txManager.canRead(paramSession, paramPersistentStore, (Row)rowAVL, 0, null))
          break; 
        nodeAVL1 = next(paramPersistentStore, nodeAVL1);
      } 
      if (nodeAVL1 == null)
        return emptyIterator; 
      return new IndexRowIterator(paramSession, paramPersistentStore, this, nodeAVL1, paramInt, false, false);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public RowIterator firstRow(PersistentStore paramPersistentStore) {
    this.readLock.lock();
    try {
      NodeAVL nodeAVL1 = getAccessor(paramPersistentStore);
      for (NodeAVL nodeAVL2 = nodeAVL1; nodeAVL2 != null; nodeAVL2 = nodeAVL1.getLeft(paramPersistentStore))
        nodeAVL1 = nodeAVL2; 
      if (nodeAVL1 == null)
        return emptyIterator; 
      return new IndexRowIterator(null, paramPersistentStore, this, nodeAVL1, 0, false, false);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public RowIterator lastRow(Session paramSession, PersistentStore paramPersistentStore, int paramInt) {
    this.readLock.lock();
    try {
      NodeAVL nodeAVL1 = getAccessor(paramPersistentStore);
      for (NodeAVL nodeAVL2 = nodeAVL1; nodeAVL2 != null; nodeAVL2 = nodeAVL1.getRight(paramPersistentStore))
        nodeAVL1 = nodeAVL2; 
      while (paramSession != null && nodeAVL1 != null) {
        RowAVL rowAVL = nodeAVL1.getRow(paramPersistentStore);
        if (paramSession.database.txManager.canRead(paramSession, paramPersistentStore, (Row)rowAVL, 0, null))
          break; 
        nodeAVL1 = last(paramPersistentStore, nodeAVL1);
      } 
      if (nodeAVL1 == null)
        return emptyIterator; 
      return new IndexRowIterator(paramSession, paramPersistentStore, this, nodeAVL1, paramInt, false, true);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  NodeAVL next(Session paramSession, PersistentStore paramPersistentStore, NodeAVL paramNodeAVL, int paramInt) {
    if (paramNodeAVL == null)
      return null; 
    if (paramInt != 0)
      return findDistinctNode(paramSession, paramPersistentStore, paramNodeAVL, paramInt, false); 
    while (true) {
      paramNodeAVL = next(paramPersistentStore, paramNodeAVL);
      if (paramNodeAVL == null)
        return paramNodeAVL; 
      if (paramSession == null)
        return paramNodeAVL; 
      RowAVL rowAVL = paramNodeAVL.getRow(paramPersistentStore);
      if (paramSession.database.txManager.canRead(paramSession, paramPersistentStore, (Row)rowAVL, 0, null))
        return paramNodeAVL; 
    } 
  }
  
  NodeAVL last(Session paramSession, PersistentStore paramPersistentStore, NodeAVL paramNodeAVL, int paramInt) {
    if (paramNodeAVL == null)
      return null; 
    if (paramInt != 0)
      return findDistinctNode(paramSession, paramPersistentStore, paramNodeAVL, paramInt, true); 
    while (true) {
      paramNodeAVL = last(paramPersistentStore, paramNodeAVL);
      if (paramNodeAVL == null)
        return paramNodeAVL; 
      if (paramSession == null)
        return paramNodeAVL; 
      RowAVL rowAVL = paramNodeAVL.getRow(paramPersistentStore);
      if (paramSession.database.txManager.canRead(paramSession, paramPersistentStore, (Row)rowAVL, 0, null))
        return paramNodeAVL; 
    } 
  }
  
  NodeAVL next(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL) {
    if (paramNodeAVL == null)
      return null; 
    RowAVL rowAVL = paramNodeAVL.getRow(paramPersistentStore);
    paramNodeAVL = rowAVL.getNode(this.position);
    NodeAVL nodeAVL = paramNodeAVL.getRight(paramPersistentStore);
    if (nodeAVL != null) {
      paramNodeAVL = nodeAVL;
      for (nodeAVL = paramNodeAVL.getLeft(paramPersistentStore); nodeAVL != null; nodeAVL = paramNodeAVL.getLeft(paramPersistentStore))
        paramNodeAVL = nodeAVL; 
      return paramNodeAVL;
    } 
    nodeAVL = paramNodeAVL;
    for (paramNodeAVL = paramNodeAVL.getParent(paramPersistentStore); paramNodeAVL != null && paramNodeAVL.isRight(nodeAVL); paramNodeAVL = paramNodeAVL.getParent(paramPersistentStore))
      nodeAVL = paramNodeAVL; 
    return paramNodeAVL;
  }
  
  NodeAVL next(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL, int paramInt1, int paramInt2, int[] paramArrayOfint) {
    NodeAVL nodeAVL = (paramInt1 == paramInt2) ? null : paramNodeAVL.getRight(paramPersistentStore);
    if (nodeAVL != null) {
      paramInt1++;
      paramNodeAVL = nodeAVL;
      for (nodeAVL = (paramInt1 == paramInt2) ? null : paramNodeAVL.getLeft(paramPersistentStore); nodeAVL != null; nodeAVL = paramNodeAVL.getLeft(paramPersistentStore)) {
        paramInt1++;
        paramNodeAVL = nodeAVL;
        if (paramInt1 == paramInt2) {
          nodeAVL = null;
          continue;
        } 
      } 
      paramArrayOfint[0] = paramInt1;
      return paramNodeAVL;
    } 
    nodeAVL = paramNodeAVL;
    paramNodeAVL = paramNodeAVL.getParent(paramPersistentStore);
    paramInt1--;
    while (paramNodeAVL != null && paramNodeAVL.isRight(nodeAVL)) {
      nodeAVL = paramNodeAVL;
      paramNodeAVL = paramNodeAVL.getParent(paramPersistentStore);
      paramInt1--;
    } 
    paramArrayOfint[0] = paramInt1;
    return paramNodeAVL;
  }
  
  NodeAVL last(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL) {
    if (paramNodeAVL == null)
      return null; 
    RowAVL rowAVL = paramNodeAVL.getRow(paramPersistentStore);
    paramNodeAVL = rowAVL.getNode(this.position);
    NodeAVL nodeAVL = paramNodeAVL.getLeft(paramPersistentStore);
    if (nodeAVL != null) {
      paramNodeAVL = nodeAVL;
      for (nodeAVL = paramNodeAVL.getRight(paramPersistentStore); nodeAVL != null; nodeAVL = paramNodeAVL.getRight(paramPersistentStore))
        paramNodeAVL = nodeAVL; 
      return paramNodeAVL;
    } 
    nodeAVL = paramNodeAVL;
    for (paramNodeAVL = paramNodeAVL.getParent(paramPersistentStore); paramNodeAVL != null && paramNodeAVL.isLeft(nodeAVL); paramNodeAVL = paramNodeAVL.getParent(paramPersistentStore))
      nodeAVL = paramNodeAVL; 
    return paramNodeAVL;
  }
  
  boolean isEqualReadable(Session paramSession, PersistentStore paramPersistentStore, NodeAVL paramNodeAVL) {
    NodeAVL nodeAVL = paramNodeAVL;
    RowAVL rowAVL = paramNodeAVL.getRow(paramPersistentStore);
    paramSession.database.txManager.setTransactionInfo(paramPersistentStore, (CachedObject)rowAVL);
    if (paramSession.database.txManager.canRead(paramSession, paramPersistentStore, (Row)rowAVL, 1, null))
      return true; 
    Object[] arrayOfObject = paramNodeAVL.getData(paramPersistentStore);
    while (true) {
      nodeAVL = last(paramPersistentStore, nodeAVL);
      if (nodeAVL == null)
        break; 
      Object[] arrayOfObject1 = nodeAVL.getData(paramPersistentStore);
      if (compareRow(paramSession, arrayOfObject, arrayOfObject1) == 0) {
        rowAVL = nodeAVL.getRow(paramPersistentStore);
        paramSession.database.txManager.setTransactionInfo(paramPersistentStore, (CachedObject)rowAVL);
        if (paramSession.database.txManager.canRead(paramSession, paramPersistentStore, (Row)rowAVL, 1, null))
          return true; 
        continue;
      } 
      break;
    } 
    while (true) {
      nodeAVL = next(paramSession, paramPersistentStore, paramNodeAVL, 0);
      if (nodeAVL == null)
        break; 
      Object[] arrayOfObject1 = nodeAVL.getData(paramPersistentStore);
      if (compareRow(paramSession, arrayOfObject, arrayOfObject1) == 0) {
        rowAVL = nodeAVL.getRow(paramPersistentStore);
        paramSession.database.txManager.setTransactionInfo(paramPersistentStore, (CachedObject)rowAVL);
        if (paramSession.database.txManager.canRead(paramSession, paramPersistentStore, (Row)rowAVL, 1, null))
          return true; 
        continue;
      } 
      break;
    } 
    return false;
  }
  
  NodeAVL findNode(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    this.readLock.lock();
    try {
      NodeAVL nodeAVL1 = getAccessor(paramPersistentStore);
      NodeAVL nodeAVL2 = null;
      NodeAVL nodeAVL3 = null;
      RowAVL rowAVL = null;
      if (paramInt2 != 40 && paramInt2 != 47) {
        paramInt1--;
        if (paramInt2 == 44 || paramInt2 == 45 || paramInt2 == 74)
          paramBoolean = true; 
      } 
      while (nodeAVL1 != null) {
        rowAVL = nodeAVL1.getRow(paramPersistentStore);
        int i = 0;
        if (paramInt1 > 0)
          i = compareRowNonUnique(paramSession, rowAVL.getData(), paramArrayOfObject, paramArrayOfint, paramInt1); 
        if (i == 0) {
          switch (paramInt2) {
            case 40:
            case 47:
            case 74:
              nodeAVL3 = nodeAVL1;
              if (paramBoolean) {
                nodeAVL2 = nodeAVL1.getRight(paramPersistentStore);
                break;
              } 
              nodeAVL2 = nodeAVL1.getLeft(paramPersistentStore);
              break;
            case 43:
            case 48:
              i = compareObject(paramSession, rowAVL.getData(), paramArrayOfObject, paramArrayOfint, paramInt1, paramInt2);
              if (i <= 0) {
                nodeAVL2 = nodeAVL1.getRight(paramPersistentStore);
                break;
              } 
              nodeAVL3 = nodeAVL1;
              nodeAVL2 = nodeAVL1.getLeft(paramPersistentStore);
              break;
            case 41:
            case 42:
              i = compareObject(paramSession, rowAVL.getData(), paramArrayOfObject, paramArrayOfint, paramInt1, paramInt2);
              if (i < 0) {
                nodeAVL2 = nodeAVL1.getRight(paramPersistentStore);
                break;
              } 
              nodeAVL3 = nodeAVL1;
              nodeAVL2 = nodeAVL1.getLeft(paramPersistentStore);
              break;
            case 44:
              i = compareObject(paramSession, rowAVL.getData(), paramArrayOfObject, paramArrayOfint, paramInt1, paramInt2);
              if (i < 0) {
                nodeAVL3 = nodeAVL1;
                nodeAVL2 = nodeAVL1.getRight(paramPersistentStore);
                break;
              } 
              nodeAVL2 = nodeAVL1.getLeft(paramPersistentStore);
              break;
            case 45:
              i = compareObject(paramSession, rowAVL.getData(), paramArrayOfObject, paramArrayOfint, paramInt1, paramInt2);
              if (i <= 0) {
                nodeAVL3 = nodeAVL1;
                nodeAVL2 = nodeAVL1.getRight(paramPersistentStore);
                break;
              } 
              nodeAVL2 = nodeAVL1.getLeft(paramPersistentStore);
              break;
            default:
              Error.runtimeError(201, "Index");
              break;
          } 
        } else if (i < 0) {
          nodeAVL2 = nodeAVL1.getRight(paramPersistentStore);
        } else if (i > 0) {
          nodeAVL2 = nodeAVL1.getLeft(paramPersistentStore);
        } 
        if (nodeAVL2 == null)
          break; 
        nodeAVL1 = nodeAVL2;
      } 
      if (paramSession == null)
        return nodeAVL3; 
      while (nodeAVL3 != null) {
        rowAVL = nodeAVL3.getRow(paramPersistentStore);
        if (paramSession.database.txManager.canRead(paramSession, paramPersistentStore, (Row)rowAVL, paramInt3, this.colIndex))
          break; 
        nodeAVL3 = paramBoolean ? last(paramPersistentStore, nodeAVL3) : next(paramPersistentStore, nodeAVL3);
        if (nodeAVL3 == null)
          break; 
        rowAVL = nodeAVL3.getRow(paramPersistentStore);
        if (paramInt1 > 0 && compareRowNonUnique(paramSession, rowAVL.getData(), paramArrayOfObject, paramArrayOfint, paramInt1) != 0) {
          nodeAVL3 = null;
          break;
        } 
      } 
      return nodeAVL3;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  NodeAVL findDistinctNode(Session paramSession, PersistentStore paramPersistentStore, NodeAVL paramNodeAVL, int paramInt, boolean paramBoolean) {
    this.readLock.lock();
    try {
      NodeAVL nodeAVL1 = getAccessor(paramPersistentStore);
      NodeAVL nodeAVL2 = null;
      NodeAVL nodeAVL3 = null;
      RowAVL rowAVL = null;
      Object[] arrayOfObject = paramNodeAVL.getData(paramPersistentStore);
      while (nodeAVL1 != null) {
        rowAVL = nodeAVL1.getRow(paramPersistentStore);
        int i = 0;
        i = compareRowNonUnique(paramSession, rowAVL.getData(), arrayOfObject, this.colIndex, paramInt);
        if (paramBoolean) {
          if (i < 0) {
            nodeAVL3 = nodeAVL1;
            nodeAVL2 = nodeAVL1.getRight(paramPersistentStore);
          } else {
            nodeAVL2 = nodeAVL1.getLeft(paramPersistentStore);
          } 
        } else if (i <= 0) {
          nodeAVL2 = nodeAVL1.getRight(paramPersistentStore);
        } else {
          nodeAVL3 = nodeAVL1;
          nodeAVL2 = nodeAVL1.getLeft(paramPersistentStore);
        } 
        if (nodeAVL2 == null)
          break; 
        nodeAVL1 = nodeAVL2;
      } 
      if (paramSession == null)
        return nodeAVL3; 
      while (nodeAVL3 != null) {
        rowAVL = nodeAVL3.getRow(paramPersistentStore);
        if (paramSession.database.txManager.canRead(paramSession, paramPersistentStore, (Row)rowAVL, 0, this.colIndex))
          break; 
        nodeAVL3 = paramBoolean ? last(paramPersistentStore, nodeAVL3) : next(paramPersistentStore, nodeAVL3);
      } 
      return nodeAVL3;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  void balance(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL, boolean paramBoolean) {
    while (true) {
      NodeAVL nodeAVL;
      byte b = paramBoolean ? 1 : -1;
      switch (paramNodeAVL.getBalance(paramPersistentStore) * b) {
        case 1:
          paramNodeAVL = paramNodeAVL.setBalance(paramPersistentStore, 0);
          return;
        case 0:
          paramNodeAVL = paramNodeAVL.setBalance(paramPersistentStore, -b);
          break;
        case -1:
          nodeAVL = paramNodeAVL.child(paramPersistentStore, paramBoolean);
          if (nodeAVL.getBalance(paramPersistentStore) == -b) {
            paramNodeAVL.replace(paramPersistentStore, this, nodeAVL);
            paramNodeAVL = paramNodeAVL.set(paramPersistentStore, paramBoolean, nodeAVL.child(paramPersistentStore, !paramBoolean));
            nodeAVL = nodeAVL.set(paramPersistentStore, !paramBoolean, paramNodeAVL);
            paramNodeAVL = paramNodeAVL.setBalance(paramPersistentStore, 0);
            nodeAVL = nodeAVL.setBalance(paramPersistentStore, 0);
          } else {
            NodeAVL nodeAVL1 = nodeAVL.child(paramPersistentStore, !paramBoolean);
            paramNodeAVL.replace(paramPersistentStore, this, nodeAVL1);
            nodeAVL = nodeAVL.set(paramPersistentStore, !paramBoolean, nodeAVL1.child(paramPersistentStore, paramBoolean));
            nodeAVL1 = nodeAVL1.set(paramPersistentStore, paramBoolean, nodeAVL);
            paramNodeAVL = paramNodeAVL.set(paramPersistentStore, paramBoolean, nodeAVL1.child(paramPersistentStore, !paramBoolean));
            nodeAVL1 = nodeAVL1.set(paramPersistentStore, !paramBoolean, paramNodeAVL);
            int i = nodeAVL1.getBalance(paramPersistentStore);
            paramNodeAVL = paramNodeAVL.setBalance(paramPersistentStore, (i == -b) ? b : 0);
            nodeAVL = nodeAVL.setBalance(paramPersistentStore, (i == b) ? -b : 0);
            nodeAVL1 = nodeAVL1.setBalance(paramPersistentStore, 0);
          } 
          return;
      } 
      if (paramNodeAVL.isRoot(paramPersistentStore))
        return; 
      paramBoolean = paramNodeAVL.isFromLeft(paramPersistentStore);
      paramNodeAVL = paramNodeAVL.getParent(paramPersistentStore);
    } 
  }
  
  NodeAVL getAccessor(PersistentStore paramPersistentStore) {
    return (NodeAVL)paramPersistentStore.getAccessor(this);
  }
  
  IndexRowIterator getIterator(Session paramSession, PersistentStore paramPersistentStore, NodeAVL paramNodeAVL, boolean paramBoolean1, boolean paramBoolean2) {
    return (paramNodeAVL == null) ? emptyIterator : new IndexRowIterator(paramSession, paramPersistentStore, this, paramNodeAVL, 0, paramBoolean1, paramBoolean2);
  }
  
  public static final class IndexRowIterator implements RowIterator {
    final Session session;
    
    final PersistentStore store;
    
    final IndexAVL index;
    
    NodeAVL nextnode;
    
    Row lastrow;
    
    int distinctCount;
    
    boolean single;
    
    boolean reversed;
    
    public IndexRowIterator(Session param1Session, PersistentStore param1PersistentStore, IndexAVL param1IndexAVL, NodeAVL param1NodeAVL, int param1Int, boolean param1Boolean1, boolean param1Boolean2) {
      this.session = param1Session;
      this.store = param1PersistentStore;
      this.index = param1IndexAVL;
      this.distinctCount = param1Int;
      this.single = param1Boolean1;
      this.reversed = param1Boolean2;
      if (param1IndexAVL == null)
        return; 
      this.nextnode = param1NodeAVL;
    }
    
    public boolean hasNext() {
      return (this.nextnode != null);
    }
    
    public Row getNextRow() {
      if (this.nextnode == null) {
        release();
        return null;
      } 
      NodeAVL nodeAVL = this.nextnode;
      if (this.single) {
        this.nextnode = null;
      } else {
        this.index.readLock.lock();
        this.store.writeLock();
        try {
          if (this.reversed) {
            this.nextnode = this.index.last(this.session, this.store, this.nextnode, this.distinctCount);
          } else {
            this.nextnode = this.index.next(this.session, this.store, this.nextnode, this.distinctCount);
          } 
        } finally {
          this.store.writeUnlock();
          this.index.readLock.unlock();
        } 
      } 
      this.lastrow = (Row)nodeAVL.getRow(this.store);
      return this.lastrow;
    }
    
    public Object[] getNext() {
      Row row = getNextRow();
      return (row == null) ? null : row.getData();
    }
    
    public void removeCurrent() {
      this.store.delete(this.session, this.lastrow);
      this.store.remove((CachedObject)this.lastrow);
    }
    
    public void release() {}
    
    public boolean setRowColumns(boolean[] param1ArrayOfboolean) {
      return false;
    }
    
    public long getRowId() {
      return this.nextnode.getPos();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\index\IndexAVL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */