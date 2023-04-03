package com.google.protobuf;

public interface RpcController {
  void reset();
  
  boolean failed();
  
  String errorText();
  
  void startCancel();
  
  void setFailed(String paramString);
  
  boolean isCanceled();
  
  void notifyOnCancel(RpcCallback<Object> paramRpcCallback);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\RpcController.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */