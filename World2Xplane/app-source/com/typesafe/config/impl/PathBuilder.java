/*    */ package com.typesafe.config.impl;
/*    */ 
/*    */ import com.typesafe.config.ConfigException;
/*    */ import java.util.Stack;
/*    */ 
/*    */ final class PathBuilder {
/* 16 */   private final Stack<String> keys = new Stack<String>();
/*    */   
/*    */   private Path result;
/*    */   
/*    */   private void checkCanAppend() {
/* 20 */     if (this.result != null)
/* 21 */       throw new ConfigException.BugOrBroken("Adding to PathBuilder after getting result"); 
/*    */   }
/*    */   
/*    */   void appendKey(String key) {
/* 26 */     checkCanAppend();
/* 28 */     this.keys.push(key);
/*    */   }
/*    */   
/*    */   void appendPath(Path path) {
/* 32 */     checkCanAppend();
/* 34 */     String first = path.first();
/* 35 */     Path remainder = path.remainder();
/*    */     while (true) {
/* 37 */       this.keys.push(first);
/* 38 */       if (remainder != null) {
/* 39 */         first = remainder.first();
/* 40 */         remainder = remainder.remainder();
/*    */         continue;
/*    */       } 
/*    */       break;
/*    */     } 
/*    */   }
/*    */   
/*    */   Path result() {
/* 50 */     if (this.result == null) {
/* 51 */       Path remainder = null;
/* 52 */       while (!this.keys.isEmpty()) {
/* 53 */         String key = this.keys.pop();
/* 54 */         remainder = new Path(key, remainder);
/*    */       } 
/* 56 */       this.result = remainder;
/*    */     } 
/* 58 */     return this.result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\PathBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */