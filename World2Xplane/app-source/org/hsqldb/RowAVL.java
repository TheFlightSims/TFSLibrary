package org.hsqldb;

import org.hsqldb.index.NodeAVL;
import org.hsqldb.lib.java.JavaSystem;
import org.hsqldb.persist.PersistentStore;

public class RowAVL extends Row {
  public NodeAVL nPrimaryNode;
  
  protected RowAVL(TableBase paramTableBase, Object[] paramArrayOfObject) {
    super(paramTableBase, paramArrayOfObject);
  }
  
  public RowAVL(TableBase paramTableBase, Object[] paramArrayOfObject, int paramInt, PersistentStore paramPersistentStore) {
    super(paramTableBase, paramArrayOfObject);
    this.position = paramInt;
    setNewNodes(paramPersistentStore);
  }
  
  public void setNewNodes(PersistentStore paramPersistentStore) {
    int i = (paramPersistentStore.getAccessorKeys()).length;
    this.nPrimaryNode = new NodeAVL(this);
    NodeAVL nodeAVL = this.nPrimaryNode;
    for (byte b = 1; b < i; b++) {
      nodeAVL.nNext = new NodeAVL(this);
      nodeAVL = nodeAVL.nNext;
    } 
  }
  
  public NodeAVL getNode(int paramInt) {
    NodeAVL nodeAVL;
    for (nodeAVL = this.nPrimaryNode; paramInt-- > 0; nodeAVL = nodeAVL.nNext);
    return nodeAVL;
  }
  
  NodeAVL getNextNode(NodeAVL paramNodeAVL) {
    if (paramNodeAVL == null) {
      paramNodeAVL = this.nPrimaryNode;
    } else {
      paramNodeAVL = paramNodeAVL.nNext;
    } 
    return paramNodeAVL;
  }
  
  public NodeAVL insertNode(int paramInt) {
    NodeAVL nodeAVL1 = getNode(paramInt - 1);
    NodeAVL nodeAVL2 = new NodeAVL(this);
    nodeAVL2.nNext = nodeAVL1.nNext;
    nodeAVL1.nNext = nodeAVL2;
    return nodeAVL2;
  }
  
  public void clearNonPrimaryNodes() {
    for (NodeAVL nodeAVL = this.nPrimaryNode.nNext; nodeAVL != null; nodeAVL = nodeAVL.nNext)
      nodeAVL.delete(); 
  }
  
  public void delete(PersistentStore paramPersistentStore) {
    for (NodeAVL nodeAVL = this.nPrimaryNode; nodeAVL != null; nodeAVL = nodeAVL.nNext)
      nodeAVL.delete(); 
  }
  
  public void restore() {}
  
  public void destroy() {
    JavaSystem.memoryRecords++;
    clearNonPrimaryNodes();
    NodeAVL nodeAVL = this.nPrimaryNode;
    while (nodeAVL != null) {
      NodeAVL nodeAVL1 = nodeAVL;
      nodeAVL = nodeAVL.nNext;
      nodeAVL1.nNext = null;
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\RowAVL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */