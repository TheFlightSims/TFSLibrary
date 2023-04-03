package org.hsqldb.index;

import java.io.IOException;
import org.hsqldb.RowAVL;
import org.hsqldb.RowAVLDisk;
import org.hsqldb.error.Error;
import org.hsqldb.lib.LongLookup;
import org.hsqldb.persist.CachedObject;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class NodeAVLDiskLarge extends NodeAVL {
  final RowAVLDisk row;
  
  private long iLeft = -1L;
  
  private long iRight = -1L;
  
  private long iParent = -1L;
  
  private int iId;
  
  public static final int SIZE_IN_BYTE = 16;
  
  public NodeAVLDiskLarge(RowAVLDisk paramRowAVLDisk, RowInputInterface paramRowInputInterface, int paramInt) throws IOException {
    this.row = paramRowAVLDisk;
    this.iId = paramInt;
    int i = paramRowInputInterface.readInt();
    this.iBalance = (byte)i;
    this.iLeft = paramRowInputInterface.readInt() & 0xFFFFFFFFL;
    this.iRight = paramRowInputInterface.readInt() & 0xFFFFFFFFL;
    this.iParent = paramRowInputInterface.readInt() & 0xFFFFFFFFL;
    if (i > 255) {
      this.iParent |= i << 8L & 0xFF00000000L;
      this.iLeft |= i << 16L & 0xFF00000000L;
      this.iRight |= i << 24L & 0xFF00000000L;
    } 
    if (this.iLeft == 0L)
      this.iLeft = -1L; 
    if (this.iRight == 0L)
      this.iRight = -1L; 
    if (this.iParent == 0L)
      this.iParent = -1L; 
  }
  
  public NodeAVLDiskLarge(RowAVLDisk paramRowAVLDisk, int paramInt) {
    this.row = paramRowAVLDisk;
    this.iId = paramInt;
  }
  
  public void delete() {
    this.iLeft = -1L;
    this.iRight = -1L;
    this.iParent = -1L;
    this.nLeft = null;
    this.nRight = null;
    this.nParent = null;
    this.iBalance = 0;
    this.row.setNodesChanged();
  }
  
  public boolean isInMemory() {
    return this.row.isInMemory();
  }
  
  public boolean isMemory() {
    return false;
  }
  
  public long getPos() {
    return this.row.getPos();
  }
  
  public RowAVL getRow(PersistentStore paramPersistentStore) {
    if (!this.row.isInMemory())
      return (RowAVL)paramPersistentStore.get((CachedObject)this.row, false); 
    this.row.updateAccessCount(paramPersistentStore.getAccessCount());
    return (RowAVL)this.row;
  }
  
  public Object[] getData(PersistentStore paramPersistentStore) {
    return this.row.getData();
  }
  
  private NodeAVLDiskLarge findNode(PersistentStore paramPersistentStore, long paramLong) {
    NodeAVLDiskLarge nodeAVLDiskLarge = null;
    RowAVLDisk rowAVLDisk = (RowAVLDisk)paramPersistentStore.get(paramLong, false);
    if (rowAVLDisk != null)
      nodeAVLDiskLarge = (NodeAVLDiskLarge)rowAVLDisk.getNode(this.iId); 
    return nodeAVLDiskLarge;
  }
  
  boolean isLeft(NodeAVL paramNodeAVL) {
    return (paramNodeAVL == null) ? ((this.iLeft == -1L)) : ((this.iLeft == paramNodeAVL.getPos()));
  }
  
  boolean isRight(NodeAVL paramNodeAVL) {
    return (paramNodeAVL == null) ? ((this.iRight == -1L)) : ((this.iRight == paramNodeAVL.getPos()));
  }
  
  NodeAVL getLeft(PersistentStore paramPersistentStore) {
    NodeAVLDiskLarge nodeAVLDiskLarge = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.isInMemory()) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, false);
      nodeAVLDiskLarge = (NodeAVLDiskLarge)rowAVLDisk.getNode(this.iId);
    } 
    if (nodeAVLDiskLarge.iLeft == -1L)
      return null; 
    if (nodeAVLDiskLarge.nLeft == null || !nodeAVLDiskLarge.nLeft.isInMemory()) {
      nodeAVLDiskLarge.nLeft = findNode(paramPersistentStore, nodeAVLDiskLarge.iLeft);
      nodeAVLDiskLarge.nLeft.nParent = nodeAVLDiskLarge;
    } 
    return nodeAVLDiskLarge.nLeft;
  }
  
  NodeAVL getRight(PersistentStore paramPersistentStore) {
    NodeAVLDiskLarge nodeAVLDiskLarge = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.isInMemory()) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, false);
      nodeAVLDiskLarge = (NodeAVLDiskLarge)rowAVLDisk.getNode(this.iId);
    } 
    if (nodeAVLDiskLarge.iRight == -1L)
      return null; 
    if (nodeAVLDiskLarge.nRight == null || !nodeAVLDiskLarge.nRight.isInMemory()) {
      nodeAVLDiskLarge.nRight = findNode(paramPersistentStore, nodeAVLDiskLarge.iRight);
      nodeAVLDiskLarge.nRight.nParent = nodeAVLDiskLarge;
    } 
    return nodeAVLDiskLarge.nRight;
  }
  
  NodeAVL getParent(PersistentStore paramPersistentStore) {
    NodeAVLDiskLarge nodeAVLDiskLarge = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.isInMemory()) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, false);
      nodeAVLDiskLarge = (NodeAVLDiskLarge)rowAVLDisk.getNode(this.iId);
    } 
    if (nodeAVLDiskLarge.iParent == -1L)
      return null; 
    if (nodeAVLDiskLarge.nParent == null || !nodeAVLDiskLarge.nParent.isInMemory())
      nodeAVLDiskLarge.nParent = findNode(paramPersistentStore, this.iParent); 
    return nodeAVLDiskLarge.nParent;
  }
  
  public int getBalance(PersistentStore paramPersistentStore) {
    NodeAVLDiskLarge nodeAVLDiskLarge = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.isInMemory()) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, false);
      nodeAVLDiskLarge = (NodeAVLDiskLarge)rowAVLDisk.getNode(this.iId);
    } 
    return nodeAVLDiskLarge.iBalance;
  }
  
  boolean isRoot(PersistentStore paramPersistentStore) {
    NodeAVLDiskLarge nodeAVLDiskLarge = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.isInMemory()) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, false);
      nodeAVLDiskLarge = (NodeAVLDiskLarge)rowAVLDisk.getNode(this.iId);
    } 
    return (nodeAVLDiskLarge.iParent == -1L);
  }
  
  boolean isFromLeft(PersistentStore paramPersistentStore) {
    NodeAVLDiskLarge nodeAVLDiskLarge = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.isInMemory()) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, false);
      nodeAVLDiskLarge = (NodeAVLDiskLarge)rowAVLDisk.getNode(this.iId);
    } 
    if (nodeAVLDiskLarge.iParent == -1L)
      return true; 
    if (nodeAVLDiskLarge.nParent == null || !nodeAVLDiskLarge.nParent.isInMemory())
      nodeAVLDiskLarge.nParent = findNode(paramPersistentStore, this.iParent); 
    return (rowAVLDisk.getPos() == ((NodeAVLDiskLarge)nodeAVLDiskLarge.nParent).iLeft);
  }
  
  public NodeAVL child(PersistentStore paramPersistentStore, boolean paramBoolean) {
    return paramBoolean ? getLeft(paramPersistentStore) : getRight(paramPersistentStore);
  }
  
  NodeAVL setParent(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL) {
    NodeAVLDiskLarge nodeAVLDiskLarge = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.keepInMemory(true)) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, true);
      nodeAVLDiskLarge = (NodeAVLDiskLarge)rowAVLDisk.getNode(this.iId);
    } 
    if (!rowAVLDisk.isInMemory()) {
      rowAVLDisk.keepInMemory(false);
      throw Error.runtimeError(201, "NodeAVLDisk");
    } 
    rowAVLDisk.setNodesChanged();
    nodeAVLDiskLarge.iParent = (paramNodeAVL == null) ? -1L : paramNodeAVL.getPos();
    if (paramNodeAVL != null && !paramNodeAVL.isInMemory())
      paramNodeAVL = findNode(paramPersistentStore, paramNodeAVL.getPos()); 
    nodeAVLDiskLarge.nParent = paramNodeAVL;
    rowAVLDisk.keepInMemory(false);
    return nodeAVLDiskLarge;
  }
  
  public NodeAVL setBalance(PersistentStore paramPersistentStore, int paramInt) {
    NodeAVLDiskLarge nodeAVLDiskLarge = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.keepInMemory(true)) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, true);
      nodeAVLDiskLarge = (NodeAVLDiskLarge)rowAVLDisk.getNode(this.iId);
    } 
    if (!rowAVLDisk.isInMemory())
      throw Error.runtimeError(201, "NodeAVLDisk"); 
    rowAVLDisk.setNodesChanged();
    nodeAVLDiskLarge.iBalance = paramInt;
    rowAVLDisk.keepInMemory(false);
    return nodeAVLDiskLarge;
  }
  
  NodeAVL setLeft(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL) {
    NodeAVLDiskLarge nodeAVLDiskLarge = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.keepInMemory(true)) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, true);
      nodeAVLDiskLarge = (NodeAVLDiskLarge)rowAVLDisk.getNode(this.iId);
    } 
    if (!rowAVLDisk.isInMemory())
      throw Error.runtimeError(201, "NodeAVLDisk"); 
    rowAVLDisk.setNodesChanged();
    nodeAVLDiskLarge.iLeft = (paramNodeAVL == null) ? -1L : paramNodeAVL.getPos();
    if (paramNodeAVL != null && !paramNodeAVL.isInMemory())
      paramNodeAVL = findNode(paramPersistentStore, paramNodeAVL.getPos()); 
    nodeAVLDiskLarge.nLeft = paramNodeAVL;
    rowAVLDisk.keepInMemory(false);
    return nodeAVLDiskLarge;
  }
  
  NodeAVL setRight(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL) {
    NodeAVLDiskLarge nodeAVLDiskLarge = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.keepInMemory(true)) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, true);
      nodeAVLDiskLarge = (NodeAVLDiskLarge)rowAVLDisk.getNode(this.iId);
    } 
    if (!rowAVLDisk.isInMemory())
      throw Error.runtimeError(201, "NodeAVLDisk"); 
    rowAVLDisk.setNodesChanged();
    nodeAVLDiskLarge.iRight = (paramNodeAVL == null) ? -1L : paramNodeAVL.getPos();
    if (paramNodeAVL != null && !paramNodeAVL.isInMemory())
      paramNodeAVL = findNode(paramPersistentStore, paramNodeAVL.getPos()); 
    nodeAVLDiskLarge.nRight = paramNodeAVL;
    rowAVLDisk.keepInMemory(false);
    return nodeAVLDiskLarge;
  }
  
  public NodeAVL set(PersistentStore paramPersistentStore, boolean paramBoolean, NodeAVL paramNodeAVL) {
    NodeAVL nodeAVL;
    if (paramBoolean) {
      nodeAVL = setLeft(paramPersistentStore, paramNodeAVL);
    } else {
      nodeAVL = setRight(paramPersistentStore, paramNodeAVL);
    } 
    if (paramNodeAVL != null)
      paramNodeAVL.setParent(paramPersistentStore, this); 
    return nodeAVL;
  }
  
  public void replace(PersistentStore paramPersistentStore, Index paramIndex, NodeAVL paramNodeAVL) {
    NodeAVLDiskLarge nodeAVLDiskLarge = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.keepInMemory(true)) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, true);
      nodeAVLDiskLarge = (NodeAVLDiskLarge)rowAVLDisk.getNode(this.iId);
    } 
    if (nodeAVLDiskLarge.iParent == -1L) {
      if (paramNodeAVL != null)
        paramNodeAVL = paramNodeAVL.setParent(paramPersistentStore, null); 
      paramPersistentStore.setAccessor(paramIndex, paramNodeAVL);
    } else {
      boolean bool = nodeAVLDiskLarge.isFromLeft(paramPersistentStore);
      nodeAVLDiskLarge.getParent(paramPersistentStore).set(paramPersistentStore, bool, paramNodeAVL);
    } 
    rowAVLDisk.keepInMemory(false);
  }
  
  boolean equals(NodeAVL paramNodeAVL) {
    return (paramNodeAVL instanceof NodeAVLDiskLarge) ? ((this == paramNodeAVL || this.row.getPos() == ((NodeAVLDiskLarge)paramNodeAVL).getPos())) : false;
  }
  
  public int getRealSize(RowOutputInterface paramRowOutputInterface) {
    return 16;
  }
  
  public void setInMemory(boolean paramBoolean) {
    if (!paramBoolean) {
      if (this.nLeft != null)
        this.nLeft.nParent = null; 
      if (this.nRight != null)
        this.nRight.nParent = null; 
      if (this.nParent != null)
        if (this.row.getPos() == ((NodeAVLDiskLarge)this.nParent).iLeft) {
          this.nParent.nLeft = null;
        } else {
          this.nParent.nRight = null;
        }  
      this.nLeft = this.nRight = this.nParent = null;
    } 
  }
  
  public void write(RowOutputInterface paramRowOutputInterface) {
    write(paramRowOutputInterface, (LongLookup)null);
  }
  
  public void write(RowOutputInterface paramRowOutputInterface, LongLookup paramLongLookup) {
    long l1 = getTranslatePointer(this.iLeft, paramLongLookup);
    long l2 = getTranslatePointer(this.iRight, paramLongLookup);
    long l3 = getTranslatePointer(this.iParent, paramLongLookup);
    int i = 0;
    i |= (int)((l3 & 0xFF00000000L) >> 8L);
    i |= (int)((l1 & 0xFF00000000L) >> 16L);
    i |= (int)((l2 & 0xFF00000000L) >> 24L);
    if (i == 0) {
      i = this.iBalance;
    } else {
      i |= this.iBalance & 0xFF;
    } 
    paramRowOutputInterface.writeInt(i);
    paramRowOutputInterface.writeInt((int)l1);
    paramRowOutputInterface.writeInt((int)l2);
    paramRowOutputInterface.writeInt((int)l3);
  }
  
  private static long getTranslatePointer(long paramLong, LongLookup paramLongLookup) {
    long l = 0L;
    if (paramLong != -1L)
      if (paramLongLookup == null) {
        l = paramLong;
      } else {
        l = paramLongLookup.lookup(paramLong);
      }  
    return l;
  }
  
  public void restore() {}
  
  public void destroy() {}
  
  public void updateAccessCount(int paramInt) {}
  
  public int getAccessCount() {
    return 0;
  }
  
  public void setStorageSize(int paramInt) {}
  
  public int getStorageSize() {
    return 0;
  }
  
  public void setPos(long paramLong) {}
  
  public boolean hasChanged() {
    return false;
  }
  
  public boolean isKeepInMemory() {
    return false;
  }
  
  public boolean keepInMemory(boolean paramBoolean) {
    return false;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\index\NodeAVLDiskLarge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */