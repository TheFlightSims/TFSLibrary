package org.openstreetmap.osmosis.core.store;

public interface StoreWriter {
  void writeBoolean(boolean paramBoolean);
  
  void writeByte(byte paramByte);
  
  void writeCharacter(char paramChar);
  
  void writeInteger(int paramInt);
  
  void writeLong(long paramLong);
  
  void writeDouble(double paramDouble);
  
  void writeString(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\StoreWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */