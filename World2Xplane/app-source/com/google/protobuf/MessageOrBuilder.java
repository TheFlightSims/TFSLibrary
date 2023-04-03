package com.google.protobuf;

import java.util.Map;

public interface MessageOrBuilder extends MessageLiteOrBuilder {
  Message getDefaultInstanceForType();
  
  Descriptors.Descriptor getDescriptorForType();
  
  Map<Descriptors.FieldDescriptor, Object> getAllFields();
  
  boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor);
  
  Object getField(Descriptors.FieldDescriptor paramFieldDescriptor);
  
  int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor);
  
  Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt);
  
  UnknownFieldSet getUnknownFields();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\MessageOrBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */