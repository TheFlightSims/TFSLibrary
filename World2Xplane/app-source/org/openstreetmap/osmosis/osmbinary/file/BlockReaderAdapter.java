package org.openstreetmap.osmosis.osmbinary.file;

public interface BlockReaderAdapter {
  boolean skipBlock(FileBlockPosition paramFileBlockPosition);
  
  void handleBlock(FileBlock paramFileBlock);
  
  void complete();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\osmbinary\file\BlockReaderAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */