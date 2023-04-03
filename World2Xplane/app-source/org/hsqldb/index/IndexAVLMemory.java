package org.hsqldb.index;

import org.hsqldb.Constraint;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.error.Error;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.types.Type;

public class IndexAVLMemory extends IndexAVL {
  public IndexAVLMemory(HsqlNameManager.HsqlName paramHsqlName, long paramLong, TableBase paramTableBase, int[] paramArrayOfint, boolean[] paramArrayOfboolean1, boolean[] paramArrayOfboolean2, Type[] paramArrayOfType, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    super(paramHsqlName, paramLong, paramTableBase, paramArrayOfint, paramArrayOfboolean1, paramArrayOfboolean2, paramArrayOfType, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
  }
  
  public void checkIndex(PersistentStore paramPersistentStore) {
    this.readLock.lock();
    try {
      NodeAVL nodeAVL1 = getAccessor(paramPersistentStore);
      NodeAVL nodeAVL2 = null;
      while (nodeAVL1 != null) {
        nodeAVL2 = nodeAVL1;
        checkNodes(paramPersistentStore, nodeAVL1);
        nodeAVL1 = nodeAVL1.nLeft;
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
    NodeAVL nodeAVL1 = paramNodeAVL.nLeft;
    NodeAVL nodeAVL2 = paramNodeAVL.nRight;
    if (nodeAVL1 != null && nodeAVL1.getBalance(paramPersistentStore) == -2)
      System.out.print("broken index - deleted"); 
    if (nodeAVL2 != null && nodeAVL2.getBalance(paramPersistentStore) == -2)
      System.out.print("broken index -deleted"); 
    if (nodeAVL1 != null && !paramNodeAVL.equals(nodeAVL1.getParent(paramPersistentStore)))
      System.out.print("broken index - no parent"); 
    if (nodeAVL2 != null && !paramNodeAVL.equals(nodeAVL2.getParent(paramPersistentStore)))
      System.out.print("broken index - no parent"); 
  }
  
  public void insert(Session paramSession, PersistentStore paramPersistentStore, Row paramRow) {
    boolean bool1 = true;
    int i = -1;
    Object[] arrayOfObject = paramRow.getData();
    boolean bool2 = (!this.isUnique || hasNulls(paramSession, arrayOfObject)) ? true : false;
    boolean bool = this.isSimple;
    this.writeLock.lock();
    try {
      NodeAVL nodeAVL1 = getAccessor(paramPersistentStore);
      NodeAVL nodeAVL2 = nodeAVL1;
      if (nodeAVL1 == null) {
        paramPersistentStore.setAccessor(this, ((RowAVL)paramRow).getNode(this.position));
        return;
      } 
      while (true) {
        Row row = nodeAVL1.row;
        i = 0;
        if (bool) {
          i = this.colTypes[0].compare(paramSession, arrayOfObject[this.colIndex[0]], row.getData()[this.colIndex[0]]);
          if (i == 0 && bool2)
            i = compareRowForInsertOrDelete(paramSession, paramRow, row, bool2, 1); 
        } else {
          i = compareRowForInsertOrDelete(paramSession, paramRow, row, bool2, 0);
        } 
        if (i == 0 && paramSession != null && !bool2 && paramSession.database.txManager.isMVRows() && !isEqualReadable(paramSession, paramPersistentStore, nodeAVL1)) {
          bool2 = true;
          i = compareRowForInsertOrDelete(paramSession, paramRow, row, bool2, this.colIndex.length);
        } 
        if (i == 0) {
          if (this.isConstraint) {
            Constraint constraint = ((Table)this.table).getUniqueConstraintForIndex(this);
            throw constraint.getException(paramRow.getData());
          } 
          throw Error.error(104, this.name.statementName);
        } 
        bool1 = (i < 0) ? true : false;
        nodeAVL2 = nodeAVL1;
        nodeAVL1 = bool1 ? nodeAVL2.nLeft : nodeAVL2.nRight;
        if (nodeAVL1 == null) {
          nodeAVL2 = nodeAVL2.set(paramPersistentStore, bool1, ((RowAVL)paramRow).getNode(this.position));
          balance(paramPersistentStore, nodeAVL2, bool1);
          return;
        } 
      } 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  void delete(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL) {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull -> 5
    //   4: return
    //   5: aload_0
    //   6: getfield writeLock : Ljava/util/concurrent/locks/Lock;
    //   9: invokeinterface lock : ()V
    //   14: aload_2
    //   15: getfield nLeft : Lorg/hsqldb/index/NodeAVL;
    //   18: ifnonnull -> 29
    //   21: aload_2
    //   22: getfield nRight : Lorg/hsqldb/index/NodeAVL;
    //   25: astore_3
    //   26: goto -> 312
    //   29: aload_2
    //   30: getfield nRight : Lorg/hsqldb/index/NodeAVL;
    //   33: ifnonnull -> 44
    //   36: aload_2
    //   37: getfield nLeft : Lorg/hsqldb/index/NodeAVL;
    //   40: astore_3
    //   41: goto -> 312
    //   44: aload_2
    //   45: astore #4
    //   47: aload_2
    //   48: getfield nLeft : Lorg/hsqldb/index/NodeAVL;
    //   51: astore_2
    //   52: aload_2
    //   53: getfield nRight : Lorg/hsqldb/index/NodeAVL;
    //   56: astore #5
    //   58: aload #5
    //   60: ifnonnull -> 66
    //   63: goto -> 72
    //   66: aload #5
    //   68: astore_2
    //   69: goto -> 52
    //   72: aload_2
    //   73: getfield nLeft : Lorg/hsqldb/index/NodeAVL;
    //   76: astore_3
    //   77: aload_2
    //   78: getfield iBalance : I
    //   81: istore #5
    //   83: aload_2
    //   84: aload #4
    //   86: getfield iBalance : I
    //   89: putfield iBalance : I
    //   92: aload #4
    //   94: iload #5
    //   96: putfield iBalance : I
    //   99: aload_2
    //   100: getfield nParent : Lorg/hsqldb/index/NodeAVL;
    //   103: astore #6
    //   105: aload #4
    //   107: getfield nParent : Lorg/hsqldb/index/NodeAVL;
    //   110: astore #7
    //   112: aload #4
    //   114: aload_1
    //   115: invokevirtual isRoot : (Lorg/hsqldb/persist/PersistentStore;)Z
    //   118: ifeq -> 129
    //   121: aload_1
    //   122: aload_0
    //   123: aload_2
    //   124: invokeinterface setAccessor : (Lorg/hsqldb/index/Index;Lorg/hsqldb/persist/CachedObject;)V
    //   129: aload_2
    //   130: aload #7
    //   132: putfield nParent : Lorg/hsqldb/index/NodeAVL;
    //   135: aload #7
    //   137: ifnull -> 165
    //   140: aload #7
    //   142: getfield nRight : Lorg/hsqldb/index/NodeAVL;
    //   145: aload #4
    //   147: if_acmpne -> 159
    //   150: aload #7
    //   152: aload_2
    //   153: putfield nRight : Lorg/hsqldb/index/NodeAVL;
    //   156: goto -> 165
    //   159: aload #7
    //   161: aload_2
    //   162: putfield nLeft : Lorg/hsqldb/index/NodeAVL;
    //   165: aload #4
    //   167: aload #6
    //   169: if_acmpne -> 231
    //   172: aload #4
    //   174: aload_2
    //   175: putfield nParent : Lorg/hsqldb/index/NodeAVL;
    //   178: aload #4
    //   180: getfield nLeft : Lorg/hsqldb/index/NodeAVL;
    //   183: aload_2
    //   184: if_acmpne -> 209
    //   187: aload_2
    //   188: aload #4
    //   190: putfield nLeft : Lorg/hsqldb/index/NodeAVL;
    //   193: aload #4
    //   195: getfield nRight : Lorg/hsqldb/index/NodeAVL;
    //   198: astore #8
    //   200: aload_2
    //   201: aload #8
    //   203: putfield nRight : Lorg/hsqldb/index/NodeAVL;
    //   206: goto -> 271
    //   209: aload_2
    //   210: aload #4
    //   212: putfield nRight : Lorg/hsqldb/index/NodeAVL;
    //   215: aload #4
    //   217: getfield nLeft : Lorg/hsqldb/index/NodeAVL;
    //   220: astore #8
    //   222: aload_2
    //   223: aload #8
    //   225: putfield nLeft : Lorg/hsqldb/index/NodeAVL;
    //   228: goto -> 271
    //   231: aload #4
    //   233: aload #6
    //   235: putfield nParent : Lorg/hsqldb/index/NodeAVL;
    //   238: aload #6
    //   240: aload #4
    //   242: putfield nRight : Lorg/hsqldb/index/NodeAVL;
    //   245: aload #4
    //   247: getfield nLeft : Lorg/hsqldb/index/NodeAVL;
    //   250: astore #8
    //   252: aload #4
    //   254: getfield nRight : Lorg/hsqldb/index/NodeAVL;
    //   257: astore #9
    //   259: aload_2
    //   260: aload #8
    //   262: putfield nLeft : Lorg/hsqldb/index/NodeAVL;
    //   265: aload_2
    //   266: aload #9
    //   268: putfield nRight : Lorg/hsqldb/index/NodeAVL;
    //   271: aload_2
    //   272: getfield nRight : Lorg/hsqldb/index/NodeAVL;
    //   275: aload_2
    //   276: putfield nParent : Lorg/hsqldb/index/NodeAVL;
    //   279: aload_2
    //   280: getfield nLeft : Lorg/hsqldb/index/NodeAVL;
    //   283: aload_2
    //   284: putfield nParent : Lorg/hsqldb/index/NodeAVL;
    //   287: aload #4
    //   289: aload_3
    //   290: putfield nLeft : Lorg/hsqldb/index/NodeAVL;
    //   293: aload_3
    //   294: ifnull -> 303
    //   297: aload_3
    //   298: aload #4
    //   300: putfield nParent : Lorg/hsqldb/index/NodeAVL;
    //   303: aload #4
    //   305: aconst_null
    //   306: putfield nRight : Lorg/hsqldb/index/NodeAVL;
    //   309: aload #4
    //   311: astore_2
    //   312: aload_2
    //   313: aload_1
    //   314: invokevirtual isFromLeft : (Lorg/hsqldb/persist/PersistentStore;)Z
    //   317: istore #4
    //   319: aload_2
    //   320: aload_1
    //   321: aload_0
    //   322: aload_3
    //   323: invokevirtual replace : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/Index;Lorg/hsqldb/index/NodeAVL;)V
    //   326: aload_2
    //   327: getfield nParent : Lorg/hsqldb/index/NodeAVL;
    //   330: astore_3
    //   331: aload_2
    //   332: invokevirtual delete : ()V
    //   335: aload_3
    //   336: ifnull -> 700
    //   339: aload_3
    //   340: astore_2
    //   341: iload #4
    //   343: ifeq -> 350
    //   346: iconst_1
    //   347: goto -> 351
    //   350: iconst_m1
    //   351: istore #5
    //   353: aload_2
    //   354: getfield iBalance : I
    //   357: iload #5
    //   359: imul
    //   360: tableswitch default -> 685, -1 -> 388, 0 -> 396, 1 -> 412
    //   388: aload_2
    //   389: iconst_0
    //   390: putfield iBalance : I
    //   393: goto -> 685
    //   396: aload_2
    //   397: iload #5
    //   399: putfield iBalance : I
    //   402: aload_0
    //   403: getfield writeLock : Ljava/util/concurrent/locks/Lock;
    //   406: invokeinterface unlock : ()V
    //   411: return
    //   412: aload_2
    //   413: aload_1
    //   414: iload #4
    //   416: ifne -> 423
    //   419: iconst_1
    //   420: goto -> 424
    //   423: iconst_0
    //   424: invokevirtual child : (Lorg/hsqldb/persist/PersistentStore;Z)Lorg/hsqldb/index/NodeAVL;
    //   427: astore #6
    //   429: aload #6
    //   431: getfield iBalance : I
    //   434: istore #7
    //   436: iload #7
    //   438: iload #5
    //   440: imul
    //   441: iflt -> 536
    //   444: aload_2
    //   445: aload_1
    //   446: aload_0
    //   447: aload #6
    //   449: invokevirtual replace : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/Index;Lorg/hsqldb/index/NodeAVL;)V
    //   452: aload #6
    //   454: aload_1
    //   455: iload #4
    //   457: invokevirtual child : (Lorg/hsqldb/persist/PersistentStore;Z)Lorg/hsqldb/index/NodeAVL;
    //   460: astore #8
    //   462: aload_2
    //   463: aload_1
    //   464: iload #4
    //   466: ifne -> 473
    //   469: iconst_1
    //   470: goto -> 474
    //   473: iconst_0
    //   474: aload #8
    //   476: invokevirtual set : (Lorg/hsqldb/persist/PersistentStore;ZLorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   479: pop
    //   480: aload #6
    //   482: aload_1
    //   483: iload #4
    //   485: aload_2
    //   486: invokevirtual set : (Lorg/hsqldb/persist/PersistentStore;ZLorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   489: pop
    //   490: iload #7
    //   492: ifne -> 519
    //   495: aload_2
    //   496: iload #5
    //   498: putfield iBalance : I
    //   501: aload #6
    //   503: iload #5
    //   505: ineg
    //   506: putfield iBalance : I
    //   509: aload_0
    //   510: getfield writeLock : Ljava/util/concurrent/locks/Lock;
    //   513: invokeinterface unlock : ()V
    //   518: return
    //   519: aload_2
    //   520: iconst_0
    //   521: putfield iBalance : I
    //   524: aload #6
    //   526: iconst_0
    //   527: putfield iBalance : I
    //   530: aload #6
    //   532: astore_2
    //   533: goto -> 685
    //   536: aload #6
    //   538: aload_1
    //   539: iload #4
    //   541: invokevirtual child : (Lorg/hsqldb/persist/PersistentStore;Z)Lorg/hsqldb/index/NodeAVL;
    //   544: astore #8
    //   546: aload_2
    //   547: aload_1
    //   548: aload_0
    //   549: aload #8
    //   551: invokevirtual replace : (Lorg/hsqldb/persist/PersistentStore;Lorg/hsqldb/index/Index;Lorg/hsqldb/index/NodeAVL;)V
    //   554: aload #8
    //   556: getfield iBalance : I
    //   559: istore #7
    //   561: aload #6
    //   563: aload_1
    //   564: iload #4
    //   566: aload #8
    //   568: aload_1
    //   569: iload #4
    //   571: ifne -> 578
    //   574: iconst_1
    //   575: goto -> 579
    //   578: iconst_0
    //   579: invokevirtual child : (Lorg/hsqldb/persist/PersistentStore;Z)Lorg/hsqldb/index/NodeAVL;
    //   582: invokevirtual set : (Lorg/hsqldb/persist/PersistentStore;ZLorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   585: pop
    //   586: aload #8
    //   588: aload_1
    //   589: iload #4
    //   591: ifne -> 598
    //   594: iconst_1
    //   595: goto -> 599
    //   598: iconst_0
    //   599: aload #6
    //   601: invokevirtual set : (Lorg/hsqldb/persist/PersistentStore;ZLorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   604: pop
    //   605: aload_2
    //   606: aload_1
    //   607: iload #4
    //   609: ifne -> 616
    //   612: iconst_1
    //   613: goto -> 617
    //   616: iconst_0
    //   617: aload #8
    //   619: aload_1
    //   620: iload #4
    //   622: invokevirtual child : (Lorg/hsqldb/persist/PersistentStore;Z)Lorg/hsqldb/index/NodeAVL;
    //   625: invokevirtual set : (Lorg/hsqldb/persist/PersistentStore;ZLorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   628: pop
    //   629: aload #8
    //   631: aload_1
    //   632: iload #4
    //   634: aload_2
    //   635: invokevirtual set : (Lorg/hsqldb/persist/PersistentStore;ZLorg/hsqldb/index/NodeAVL;)Lorg/hsqldb/index/NodeAVL;
    //   638: pop
    //   639: aload_2
    //   640: iload #7
    //   642: iload #5
    //   644: if_icmpne -> 653
    //   647: iload #5
    //   649: ineg
    //   650: goto -> 654
    //   653: iconst_0
    //   654: putfield iBalance : I
    //   657: aload #6
    //   659: iload #7
    //   661: iload #5
    //   663: ineg
    //   664: if_icmpne -> 672
    //   667: iload #5
    //   669: goto -> 673
    //   672: iconst_0
    //   673: putfield iBalance : I
    //   676: aload #8
    //   678: iconst_0
    //   679: putfield iBalance : I
    //   682: aload #8
    //   684: astore_2
    //   685: aload_2
    //   686: aload_1
    //   687: invokevirtual isFromLeft : (Lorg/hsqldb/persist/PersistentStore;)Z
    //   690: istore #4
    //   692: aload_2
    //   693: getfield nParent : Lorg/hsqldb/index/NodeAVL;
    //   696: astore_3
    //   697: goto -> 335
    //   700: aload_0
    //   701: getfield writeLock : Ljava/util/concurrent/locks/Lock;
    //   704: invokeinterface unlock : ()V
    //   709: goto -> 726
    //   712: astore #10
    //   714: aload_0
    //   715: getfield writeLock : Ljava/util/concurrent/locks/Lock;
    //   718: invokeinterface unlock : ()V
    //   723: aload #10
    //   725: athrow
    //   726: return
    // Exception table:
    //   from	to	target	type
    //   14	402	712	finally
    //   412	509	712	finally
    //   519	700	712	finally
    //   712	714	712	finally
  }
  
  NodeAVL next(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL) {
    NodeAVL nodeAVL1 = paramNodeAVL.nRight;
    if (nodeAVL1 != null) {
      paramNodeAVL = nodeAVL1;
      for (NodeAVL nodeAVL = paramNodeAVL.nLeft; nodeAVL != null; nodeAVL = paramNodeAVL.nLeft)
        paramNodeAVL = nodeAVL; 
      return paramNodeAVL;
    } 
    NodeAVL nodeAVL2 = paramNodeAVL;
    for (paramNodeAVL = paramNodeAVL.nParent; paramNodeAVL != null && nodeAVL2 == paramNodeAVL.nRight; paramNodeAVL = paramNodeAVL.nParent)
      nodeAVL2 = paramNodeAVL; 
    return paramNodeAVL;
  }
  
  NodeAVL last(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL) {
    if (paramNodeAVL == null)
      return null; 
    NodeAVL nodeAVL1 = paramNodeAVL.nLeft;
    if (nodeAVL1 != null) {
      paramNodeAVL = nodeAVL1;
      for (NodeAVL nodeAVL = paramNodeAVL.nRight; nodeAVL != null; nodeAVL = paramNodeAVL.nRight)
        paramNodeAVL = nodeAVL; 
      return paramNodeAVL;
    } 
    NodeAVL nodeAVL2 = paramNodeAVL;
    for (paramNodeAVL = paramNodeAVL.nParent; paramNodeAVL != null && nodeAVL2.equals(paramNodeAVL.nLeft); paramNodeAVL = paramNodeAVL.nParent)
      nodeAVL2 = paramNodeAVL; 
    return paramNodeAVL;
  }
  
  void balance(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL, boolean paramBoolean) {
    while (true) {
      NodeAVL nodeAVL;
      byte b = paramBoolean ? 1 : -1;
      switch (paramNodeAVL.iBalance * b) {
        case 1:
          paramNodeAVL.iBalance = 0;
          return;
        case 0:
          paramNodeAVL.iBalance = -b;
          break;
        case -1:
          nodeAVL = paramBoolean ? paramNodeAVL.nLeft : paramNodeAVL.nRight;
          if (nodeAVL.iBalance == -b) {
            paramNodeAVL.replace(paramPersistentStore, this, nodeAVL);
            paramNodeAVL.set(paramPersistentStore, paramBoolean, nodeAVL.child(paramPersistentStore, !paramBoolean));
            nodeAVL.set(paramPersistentStore, !paramBoolean, paramNodeAVL);
            paramNodeAVL.iBalance = 0;
            nodeAVL.iBalance = 0;
          } else {
            NodeAVL nodeAVL1 = !paramBoolean ? nodeAVL.nLeft : nodeAVL.nRight;
            paramNodeAVL.replace(paramPersistentStore, this, nodeAVL1);
            nodeAVL.set(paramPersistentStore, !paramBoolean, nodeAVL1.child(paramPersistentStore, paramBoolean));
            nodeAVL1.set(paramPersistentStore, paramBoolean, nodeAVL);
            paramNodeAVL.set(paramPersistentStore, paramBoolean, nodeAVL1.child(paramPersistentStore, !paramBoolean));
            nodeAVL1.set(paramPersistentStore, !paramBoolean, paramNodeAVL);
            int i = nodeAVL1.iBalance;
            paramNodeAVL.iBalance = (i == -b) ? b : 0;
            nodeAVL.iBalance = (i == b) ? -b : 0;
            nodeAVL1.iBalance = 0;
          } 
          return;
      } 
      if (paramNodeAVL.nParent == null)
        return; 
      paramBoolean = (paramNodeAVL.nParent == null || paramNodeAVL == paramNodeAVL.nParent.nLeft);
      paramNodeAVL = paramNodeAVL.nParent;
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\index\IndexAVLMemory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */