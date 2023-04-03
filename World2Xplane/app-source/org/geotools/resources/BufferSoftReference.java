/*    */ package org.geotools.resources;
/*    */ 
/*    */ import java.lang.ref.SoftReference;
/*    */ import java.nio.ByteBuffer;
/*    */ import org.geotools.util.WeakCollectionCleaner;
/*    */ 
/*    */ class BufferSoftReference extends SoftReference<ByteBuffer> {
/*    */   public BufferSoftReference(ByteBuffer referent) {
/* 33 */     super(referent, WeakCollectionCleaner.DEFAULT.getReferenceQueue());
/*    */   }
/*    */   
/*    */   public void clear() {
/* 38 */     ByteBuffer buffer = get();
/* 39 */     NIOUtilities.clean(buffer);
/* 40 */     super.clear();
/*    */   }
/*    */   
/*    */   public boolean equals(Object other) {
/* 45 */     if (!(other instanceof BufferSoftReference))
/* 46 */       return false; 
/* 49 */     ByteBuffer buffer = get();
/* 50 */     if (buffer == null)
/* 51 */       return false; 
/* 53 */     ByteBuffer otherBuffer = ((BufferSoftReference)other).get();
/* 54 */     if (otherBuffer == null)
/* 55 */       return false; 
/* 57 */     return (otherBuffer == buffer);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\BufferSoftReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */