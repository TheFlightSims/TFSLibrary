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

public class NodeAVLDisk extends NodeAVL {
  final RowAVLDisk row;
  
  private int iLeft = -1;
  
  private int iRight = -1;
  
  private int iParent = -1;
  
  private int iId;
  
  public static final int SIZE_IN_BYTE = 16;
  
  public NodeAVLDisk(RowAVLDisk paramRowAVLDisk, RowInputInterface paramRowInputInterface, int paramInt) throws IOException {
    this.row = paramRowAVLDisk;
    this.iId = paramInt;
    this.iBalance = paramRowInputInterface.readInt();
    this.iLeft = paramRowInputInterface.readInt();
    this.iRight = paramRowInputInterface.readInt();
    this.iParent = paramRowInputInterface.readInt();
    if (this.iLeft <= 0)
      this.iLeft = -1; 
    if (this.iRight <= 0)
      this.iRight = -1; 
    if (this.iParent <= 0)
      this.iParent = -1; 
  }
  
  public NodeAVLDisk(RowAVLDisk paramRowAVLDisk, int paramInt) {
    this.row = paramRowAVLDisk;
    this.iId = paramInt;
  }
  
  public void delete() {
    this.iLeft = -1;
    this.iRight = -1;
    this.iParent = -1;
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
  
  private NodeAVLDisk findNode(PersistentStore paramPersistentStore, int paramInt) {
    NodeAVLDisk nodeAVLDisk = null;
    RowAVLDisk rowAVLDisk = (RowAVLDisk)paramPersistentStore.get(paramInt, false);
    if (rowAVLDisk != null)
      nodeAVLDisk = (NodeAVLDisk)rowAVLDisk.getNode(this.iId); 
    return nodeAVLDisk;
  }
  
  boolean isLeft(NodeAVL paramNodeAVL) {
    return (paramNodeAVL == null) ? ((this.iLeft == -1)) : ((this.iLeft == paramNodeAVL.getPos()));
  }
  
  boolean isRight(NodeAVL paramNodeAVL) {
    return (paramNodeAVL == null) ? ((this.iRight == -1)) : ((this.iRight == paramNodeAVL.getPos()));
  }
  
  NodeAVL getLeft(PersistentStore paramPersistentStore) {
    NodeAVLDisk nodeAVLDisk = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.isInMemory()) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, false);
      nodeAVLDisk = (NodeAVLDisk)rowAVLDisk.getNode(this.iId);
    } 
    if (nodeAVLDisk.iLeft == -1)
      return null; 
    if (nodeAVLDisk.nLeft == null || !nodeAVLDisk.nLeft.isInMemory()) {
      nodeAVLDisk.nLeft = findNode(paramPersistentStore, nodeAVLDisk.iLeft);
      nodeAVLDisk.nLeft.nParent = nodeAVLDisk;
    } 
    return nodeAVLDisk.nLeft;
  }
  
  NodeAVL getRight(PersistentStore paramPersistentStore) {
    NodeAVLDisk nodeAVLDisk = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.isInMemory()) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, false);
      nodeAVLDisk = (NodeAVLDisk)rowAVLDisk.getNode(this.iId);
    } 
    if (nodeAVLDisk.iRight == -1)
      return null; 
    if (nodeAVLDisk.nRight == null || !nodeAVLDisk.nRight.isInMemory()) {
      nodeAVLDisk.nRight = findNode(paramPersistentStore, nodeAVLDisk.iRight);
      nodeAVLDisk.nRight.nParent = nodeAVLDisk;
    } 
    return nodeAVLDisk.nRight;
  }
  
  NodeAVL getParent(PersistentStore paramPersistentStore) {
    NodeAVLDisk nodeAVLDisk = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.isInMemory()) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, false);
      nodeAVLDisk = (NodeAVLDisk)rowAVLDisk.getNode(this.iId);
    } 
    if (nodeAVLDisk.iParent == -1)
      return null; 
    if (nodeAVLDisk.nParent == null || !nodeAVLDisk.nParent.isInMemory())
      nodeAVLDisk.nParent = findNode(paramPersistentStore, this.iParent); 
    return nodeAVLDisk.nParent;
  }
  
  public int getBalance(PersistentStore paramPersistentStore) {
    NodeAVLDisk nodeAVLDisk = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.isInMemory()) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, false);
      nodeAVLDisk = (NodeAVLDisk)rowAVLDisk.getNode(this.iId);
    } 
    return nodeAVLDisk.iBalance;
  }
  
  boolean isRoot(PersistentStore paramPersistentStore) {
    NodeAVLDisk nodeAVLDisk = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.isInMemory()) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, false);
      nodeAVLDisk = (NodeAVLDisk)rowAVLDisk.getNode(this.iId);
    } 
    return (nodeAVLDisk.iParent == -1);
  }
  
  boolean isFromLeft(PersistentStore paramPersistentStore) {
    NodeAVLDisk nodeAVLDisk = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.isInMemory()) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, false);
      nodeAVLDisk = (NodeAVLDisk)rowAVLDisk.getNode(this.iId);
    } 
    if (nodeAVLDisk.iParent == -1)
      return true; 
    if (nodeAVLDisk.nParent == null || !nodeAVLDisk.nParent.isInMemory())
      nodeAVLDisk.nParent = findNode(paramPersistentStore, this.iParent); 
    return (rowAVLDisk.getPos() == ((NodeAVLDisk)nodeAVLDisk.nParent).iLeft);
  }
  
  public NodeAVL child(PersistentStore paramPersistentStore, boolean paramBoolean) {
    return paramBoolean ? getLeft(paramPersistentStore) : getRight(paramPersistentStore);
  }
  
  NodeAVL setParent(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL) {
    NodeAVLDisk nodeAVLDisk = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.keepInMemory(true)) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, true);
      nodeAVLDisk = (NodeAVLDisk)rowAVLDisk.getNode(this.iId);
    } 
    if (!rowAVLDisk.isInMemory()) {
      rowAVLDisk.keepInMemory(false);
      throw Error.runtimeError(201, "NodeAVLDisk");
    } 
    rowAVLDisk.setNodesChanged();
    nodeAVLDisk.iParent = (paramNodeAVL == null) ? -1 : (int)paramNodeAVL.getPos();
    nodeAVLDisk.nParent = paramNodeAVL;
    rowAVLDisk.keepInMemory(false);
    return nodeAVLDisk;
  }
  
  public NodeAVL setBalance(PersistentStore paramPersistentStore, int paramInt) {
    NodeAVLDisk nodeAVLDisk = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.keepInMemory(true)) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, true);
      nodeAVLDisk = (NodeAVLDisk)rowAVLDisk.getNode(this.iId);
    } 
    if (!rowAVLDisk.isInMemory())
      throw Error.runtimeError(201, "NodeAVLDisk"); 
    rowAVLDisk.setNodesChanged();
    nodeAVLDisk.iBalance = paramInt;
    rowAVLDisk.keepInMemory(false);
    return nodeAVLDisk;
  }
  
  NodeAVL setLeft(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL) {
    NodeAVLDisk nodeAVLDisk = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.keepInMemory(true)) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, true);
      nodeAVLDisk = (NodeAVLDisk)rowAVLDisk.getNode(this.iId);
    } 
    if (!rowAVLDisk.isInMemory())
      throw Error.runtimeError(201, "NodeAVLDisk"); 
    rowAVLDisk.setNodesChanged();
    nodeAVLDisk.iLeft = (paramNodeAVL == null) ? -1 : (int)paramNodeAVL.getPos();
    nodeAVLDisk.nLeft = paramNodeAVL;
    rowAVLDisk.keepInMemory(false);
    return nodeAVLDisk;
  }
  
  NodeAVL setRight(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL) {
    NodeAVLDisk nodeAVLDisk = this;
    RowAVLDisk rowAVLDisk = this.row;
    if (!rowAVLDisk.keepInMemory(true)) {
      rowAVLDisk = (RowAVLDisk)paramPersistentStore.get((CachedObject)this.row, true);
      nodeAVLDisk = (NodeAVLDisk)rowAVLDisk.getNode(this.iId);
    } 
    if (!rowAVLDisk.isInMemory())
      throw Error.runtimeError(201, "NodeAVLDisk"); 
    rowAVLDisk.setNodesChanged();
    nodeAVLDisk.iRight = (paramNodeAVL == null) ? -1 : (int)paramNodeAVL.getPos();
    nodeAVLDisk.nRight = paramNodeAVL;
    rowAVLDisk.keepInMemory(false);
    return nodeAVLDisk;
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
    if (this.iParent == -1) {
      if (paramNodeAVL != null)
        paramNodeAVL = paramNodeAVL.setParent(paramPersistentStore, null); 
      paramPersistentStore.setAccessor(paramIndex, paramNodeAVL);
    } else {
      boolean bool = isFromLeft(paramPersistentStore);
      getParent(paramPersistentStore).set(paramPersistentStore, bool, paramNodeAVL);
    } 
  }
  
  boolean equals(NodeAVL paramNodeAVL) {
    return (paramNodeAVL instanceof NodeAVLDisk) ? ((this == paramNodeAVL || getPos() == ((NodeAVLDisk)paramNodeAVL).getPos())) : false;
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
        if (this.row.getPos() == ((NodeAVLDisk)this.nParent).iLeft) {
          this.nParent.nLeft = null;
        } else {
          this.nParent.nRight = null;
        }  
      this.nLeft = this.nRight = this.nParent = null;
    } 
  }
  
  public void write(RowOutputInterface paramRowOutputInterface) {
    paramRowOutputInterface.writeInt(this.iBalance);
    paramRowOutputInterface.writeInt((this.iLeft == -1) ? 0 : this.iLeft);
    paramRowOutputInterface.writeInt((this.iRight == -1) ? 0 : this.iRight);
    paramRowOutputInterface.writeInt((this.iParent == -1) ? 0 : this.iParent);
  }
  
  public void write(RowOutputInterface paramRowOutputInterface, LongLookup paramLongLookup) {
    paramRowOutputInterface.writeInt(this.iBalance);
    paramRowOutputInterface.writeInt(getTranslatePointer(this.iLeft, paramLongLookup));
    paramRowOutputInterface.writeInt(getTranslatePointer(this.iRight, paramLongLookup));
    paramRowOutputInterface.writeInt(getTranslatePointer(this.iParent, paramLongLookup));
  }
  
  private static int getTranslatePointer(int paramInt, LongLookup paramLongLookup) {
    int i = 0;
    if (paramInt != -1)
      if (paramLongLookup == null) {
        i = paramInt;
      } else {
        i = (int)paramLongLookup.lookup(paramInt);
      }  
    return i;
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
  
  public boolean isNew() {
    return false;
  }
  
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\index\NodeAVLDisk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */