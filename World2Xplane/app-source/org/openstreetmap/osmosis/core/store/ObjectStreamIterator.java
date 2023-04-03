/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*    */ 
/*    */ public class ObjectStreamIterator<T> extends ObjectDataInputIterator<T> implements ReleasableIterator<T> {
/* 21 */   private static final Logger LOG = Logger.getLogger(ObjectStreamIterator.class.getName());
/*    */   
/*    */   private DataInputStream inStream;
/*    */   
/*    */   public ObjectStreamIterator(DataInputStream inStream, ObjectReader objectReader) {
/* 35 */     super(objectReader);
/* 37 */     this.inStream = inStream;
/*    */   }
/*    */   
/*    */   public void release() {
/* 45 */     if (this.inStream != null) {
/*    */       try {
/* 47 */         this.inStream.close();
/* 48 */       } catch (IOException e) {
/* 50 */         LOG.log(Level.WARNING, "Unable to close input stream.", e);
/*    */       } 
/* 53 */       this.inStream = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\ObjectStreamIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */