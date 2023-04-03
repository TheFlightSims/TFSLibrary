/*    */ package com.google.protobuf;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class InvalidProtocolBufferException extends IOException {
/*    */   private static final long serialVersionUID = -1616151763072450476L;
/*    */   
/*    */   public InvalidProtocolBufferException(String description) {
/* 45 */     super(description);
/*    */   }
/*    */   
/*    */   static InvalidProtocolBufferException truncatedMessage() {
/* 49 */     return new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
/*    */   }
/*    */   
/*    */   static InvalidProtocolBufferException negativeSize() {
/* 57 */     return new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
/*    */   }
/*    */   
/*    */   static InvalidProtocolBufferException malformedVarint() {
/* 63 */     return new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
/*    */   }
/*    */   
/*    */   static InvalidProtocolBufferException invalidTag() {
/* 68 */     return new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
/*    */   }
/*    */   
/*    */   static InvalidProtocolBufferException invalidEndTag() {
/* 73 */     return new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
/*    */   }
/*    */   
/*    */   static InvalidProtocolBufferException invalidWireType() {
/* 78 */     return new InvalidProtocolBufferException("Protocol message tag had invalid wire type.");
/*    */   }
/*    */   
/*    */   static InvalidProtocolBufferException recursionLimitExceeded() {
/* 83 */     return new InvalidProtocolBufferException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
/*    */   }
/*    */   
/*    */   static InvalidProtocolBufferException sizeLimitExceeded() {
/* 89 */     return new InvalidProtocolBufferException("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\InvalidProtocolBufferException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */