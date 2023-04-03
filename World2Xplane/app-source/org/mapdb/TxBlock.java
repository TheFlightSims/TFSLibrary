package org.mapdb;

public interface TxBlock {
  void tx(DB paramDB) throws TxRollbackException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\TxBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */