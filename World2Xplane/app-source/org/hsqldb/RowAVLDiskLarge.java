package org.hsqldb;

import java.io.IOException;
import org.hsqldb.index.NodeAVL;
import org.hsqldb.index.NodeAVLDiskLarge;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.rowio.RowInputInterface;

public class RowAVLDiskLarge extends RowAVLDisk {
  public RowAVLDiskLarge(TableBase paramTableBase, Object[] paramArrayOfObject, PersistentStore paramPersistentStore) {
    super(paramTableBase, paramArrayOfObject, paramPersistentStore);
  }
  
  public RowAVLDiskLarge(TableBase paramTableBase, RowInputInterface paramRowInputInterface) throws IOException {
    super(paramTableBase);
    this.position = paramRowInputInterface.getPos();
    this.storageSize = paramRowInputInterface.getSize();
    int i = paramTableBase.getIndexCount();
    this.nPrimaryNode = (NodeAVL)new NodeAVLDiskLarge(this, paramRowInputInterface, 0);
    NodeAVL nodeAVL = this.nPrimaryNode;
    for (byte b = 1; b < i; b++) {
      nodeAVL.nNext = (NodeAVL)new NodeAVLDiskLarge(this, paramRowInputInterface, b);
      nodeAVL = nodeAVL.nNext;
    } 
    this.rowData = paramRowInputInterface.readData(this.table.getColumnTypes());
  }
  
  public void setNewNodes(PersistentStore paramPersistentStore) {
    int i = (paramPersistentStore.getAccessorKeys()).length;
    this.nPrimaryNode = (NodeAVL)new NodeAVLDiskLarge(this, 0);
    NodeAVL nodeAVL = this.nPrimaryNode;
    for (byte b = 1; b < i; b++) {
      nodeAVL.nNext = (NodeAVL)new NodeAVLDiskLarge(this, b);
      nodeAVL = nodeAVL.nNext;
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\RowAVLDiskLarge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */