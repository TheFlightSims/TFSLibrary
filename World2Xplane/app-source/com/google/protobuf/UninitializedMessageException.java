/*    */ package com.google.protobuf;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ public class UninitializedMessageException extends RuntimeException {
/*    */   private static final long serialVersionUID = -7466929953374883507L;
/*    */   
/*    */   private final List<String> missingFields;
/*    */   
/*    */   public UninitializedMessageException(MessageLite message) {
/* 53 */     super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
/* 55 */     this.missingFields = null;
/*    */   }
/*    */   
/*    */   public UninitializedMessageException(List<String> missingFields) {
/* 59 */     super(buildDescription(missingFields));
/* 60 */     this.missingFields = missingFields;
/*    */   }
/*    */   
/*    */   public List<String> getMissingFields() {
/* 72 */     return Collections.unmodifiableList(this.missingFields);
/*    */   }
/*    */   
/*    */   public InvalidProtocolBufferException asInvalidProtocolBufferException() {
/* 81 */     return new InvalidProtocolBufferException(getMessage());
/*    */   }
/*    */   
/*    */   private static String buildDescription(List<String> missingFields) {
/* 86 */     StringBuilder description = new StringBuilder("Message missing required fields: ");
/* 88 */     boolean first = true;
/* 89 */     for (String field : missingFields) {
/* 90 */       if (first) {
/* 91 */         first = false;
/*    */       } else {
/* 93 */         description.append(", ");
/*    */       } 
/* 95 */       description.append(field);
/*    */     } 
/* 97 */     return description.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\UninitializedMessageException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */