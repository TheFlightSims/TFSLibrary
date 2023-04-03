package com.google.protobuf;

import java.util.List;

public interface LazyStringList extends List<String> {
  ByteString getByteString(int paramInt);
  
  void add(ByteString paramByteString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\LazyStringList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */