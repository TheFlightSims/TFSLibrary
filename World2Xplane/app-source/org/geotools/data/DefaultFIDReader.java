/*    */ package org.geotools.data;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ 
/*    */ public class DefaultFIDReader implements FIDReader {
/*    */   protected static final String CLOSE_MESG = "Close has already been called on this FIDReader";
/*    */   
/*    */   private int len;
/*    */   
/* 39 */   protected int index = 0;
/*    */   
/*    */   protected StringBuffer buffer;
/*    */   
/*    */   public DefaultFIDReader(String typeName) {
/* 43 */     this.buffer = new StringBuffer(typeName);
/* 44 */     this.buffer.append('.');
/* 45 */     this.len = typeName.length() + 1;
/*    */   }
/*    */   
/*    */   public DefaultFIDReader(SimpleFeatureType featureType) {
/* 49 */     this(featureType.getTypeName());
/*    */   }
/*    */   
/*    */   public void close() {
/* 56 */     this.index = -1;
/*    */   }
/*    */   
/*    */   public boolean hasNext() throws IOException {
/* 67 */     if (this.index < 0)
/* 68 */       throw new IOException("Close has already been called on this FIDReader"); 
/* 71 */     return (this.index < Integer.MAX_VALUE);
/*    */   }
/*    */   
/*    */   public String next() throws IOException {
/* 82 */     if (this.index < 0)
/* 83 */       throw new IOException("Close has already been called on this FIDReader"); 
/* 86 */     this.buffer.setLength(this.len);
/* 87 */     this.buffer.append(++this.index);
/* 89 */     return this.buffer.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DefaultFIDReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */