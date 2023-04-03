package com.google.protobuf;

public interface BlockingService {
  Descriptors.ServiceDescriptor getDescriptorForType();
  
  Message callBlockingMethod(Descriptors.MethodDescriptor paramMethodDescriptor, RpcController paramRpcController, Message paramMessage) throws ServiceException;
  
  Message getRequestPrototype(Descriptors.MethodDescriptor paramMethodDescriptor);
  
  Message getResponsePrototype(Descriptors.MethodDescriptor paramMethodDescriptor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\BlockingService.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */