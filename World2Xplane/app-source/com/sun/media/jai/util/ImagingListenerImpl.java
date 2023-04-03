/*    */ package com.sun.media.jai.util;
/*    */ 
/*    */ import java.lang.ref.SoftReference;
/*    */ import javax.media.jai.util.ImagingListener;
/*    */ 
/*    */ public class ImagingListenerImpl implements ImagingListener {
/* 33 */   private static SoftReference reference = new SoftReference(null);
/*    */   
/*    */   public static ImagingListenerImpl getInstance() {
/* 40 */     synchronized (reference) {
/*    */       ImagingListenerImpl listener;
/* 41 */       Object referent = reference.get();
/* 43 */       if (referent == null) {
/* 45 */         reference = new SoftReference(listener = new ImagingListenerImpl());
/*    */       } else {
/* 49 */         listener = (ImagingListenerImpl)referent;
/*    */       } 
/* 52 */       return listener;
/*    */     } 
/*    */   }
/*    */   
/*    */   public synchronized boolean errorOccurred(String message, Throwable thrown, Object where, boolean isRetryable) throws RuntimeException {
/* 68 */     if (thrown instanceof RuntimeException && !(where instanceof javax.media.jai.OperationRegistry))
/* 70 */       throw (RuntimeException)thrown; 
/* 72 */     System.err.println("Error: " + message);
/* 73 */     System.err.println("Occurs in: " + ((where instanceof Class) ? ((Class)where).getName() : where.getClass().getName()));
/* 77 */     thrown.printStackTrace(System.err);
/* 78 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\ImagingListenerImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */