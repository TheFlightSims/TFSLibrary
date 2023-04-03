/*     */ package com.google.protobuf;
/*     */ 
/*     */ public final class RpcUtil {
/*     */   public static <Type extends Message> RpcCallback<Type> specializeCallback(RpcCallback<Message> originalCallback) {
/*  49 */     return (RpcCallback)originalCallback;
/*     */   }
/*     */   
/*     */   public static <Type extends Message> RpcCallback<Message> generalizeCallback(final RpcCallback<Type> originalCallback, final Class<Type> originalClass, final Type defaultInstance) {
/*  73 */     return new RpcCallback<Message>() {
/*     */         public void run(Message parameter) {
/*     */           Message message;
/*     */           try {
/*  77 */             message = originalClass.cast(parameter);
/*  78 */           } catch (ClassCastException ignored) {
/*  79 */             message = (Message)RpcUtil.copyAsType((Type)defaultInstance, parameter);
/*     */           } 
/*  81 */           originalCallback.run(message);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   private static <Type extends Message> Type copyAsType(Type typeDefaultInstance, Message source) {
/*  94 */     return (Type)typeDefaultInstance.newBuilderForType().mergeFrom(source).build();
/*     */   }
/*     */   
/*     */   public static <ParameterType> RpcCallback<ParameterType> newOneTimeCallback(final RpcCallback<ParameterType> originalCallback) {
/* 108 */     return new RpcCallback<ParameterType>() {
/*     */         private boolean alreadyCalled = false;
/*     */         
/*     */         public void run(ParameterType parameter) {
/* 112 */           synchronized (this) {
/* 113 */             if (this.alreadyCalled)
/* 114 */               throw new RpcUtil.AlreadyCalledException(); 
/* 116 */             this.alreadyCalled = true;
/*     */           } 
/* 119 */           originalCallback.run(parameter);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static final class AlreadyCalledException extends RuntimeException {
/*     */     private static final long serialVersionUID = 5469741279507848266L;
/*     */     
/*     */     public AlreadyCalledException() {
/* 131 */       super("This RpcCallback was already called and cannot be called multiple times.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\RpcUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */