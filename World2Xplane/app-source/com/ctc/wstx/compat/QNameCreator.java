/*    */ package com.ctc.wstx.compat;
/*    */ 
/*    */ import java.util.logging.Logger;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ public final class QNameCreator {
/*    */   private static final Helper _helper;
/*    */   
/*    */   static {
/* 32 */     Helper h = null;
/*    */     try {
/* 35 */       Helper h0 = new Helper();
/* 36 */       h0.create("elem", "http://dummy", "ns");
/* 37 */       h = h0;
/* 38 */     } catch (Throwable t) {
/* 39 */       String msg = "Could not construct QNameCreator.Helper; assume 3-arg QName constructor not available and use 2-arg method instead. Problem: " + t.getMessage();
/*    */       try {
/* 41 */         Logger.getLogger("com.ctc.wstx.compat.QNameCreator").warning(msg);
/* 42 */       } catch (Throwable t2) {
/* 43 */         System.err.println("ERROR: failed to log error using Logger (problem " + t.getMessage() + "), original problem: " + msg);
/*    */       } 
/*    */     } 
/* 46 */     _helper = h;
/*    */   }
/*    */   
/*    */   public static QName create(String uri, String localName, String prefix) {
/* 51 */     if (_helper == null)
/* 52 */       return new QName(uri, localName); 
/* 54 */     return _helper.create(uri, localName, prefix);
/*    */   }
/*    */   
/*    */   private static final class Helper {
/*    */     public QName create(String localName, String nsURI, String prefix) {
/* 66 */       return new QName(localName, nsURI, prefix);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\compat\QNameCreator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */