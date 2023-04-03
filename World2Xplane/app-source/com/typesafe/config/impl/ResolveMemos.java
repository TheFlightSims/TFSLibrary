/*    */ package com.typesafe.config.impl;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ final class ResolveMemos {
/* 17 */   private final Map<MemoKey, AbstractConfigValue> memos = new HashMap<MemoKey, AbstractConfigValue>();
/*    */   
/*    */   AbstractConfigValue get(MemoKey key) {
/* 21 */     return this.memos.get(key);
/*    */   }
/*    */   
/*    */   void put(MemoKey key, AbstractConfigValue value) {
/* 25 */     this.memos.put(key, value);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ResolveMemos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */