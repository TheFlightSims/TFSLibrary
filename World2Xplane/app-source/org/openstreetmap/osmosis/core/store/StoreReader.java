package org.openstreetmap.osmosis.core.store;

public interface StoreReader {
  boolean readBoolean();
  
  byte readByte();
  
  char readCharacter();
  
  int readInteger();
  
  long readLong();
  
  double readDouble();
  
  String readString();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\StoreReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */