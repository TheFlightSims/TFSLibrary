/*    */ package org.geotools.data.shapefile.files;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URL;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.channels.WritableByteChannel;
/*    */ 
/*    */ public class WritableByteChannelDecorator implements WritableByteChannel {
/*    */   private final WritableByteChannel wrapped;
/*    */   
/*    */   private final ShpFiles shapefileFiles;
/*    */   
/*    */   private final URL url;
/*    */   
/*    */   private final FileWriter requestor;
/*    */   
/*    */   private boolean closed;
/*    */   
/*    */   public WritableByteChannelDecorator(WritableByteChannel newChannel, ShpFiles shapefileFiles, URL url, FileWriter requestor) {
/* 46 */     this.wrapped = newChannel;
/* 47 */     this.shapefileFiles = shapefileFiles;
/* 48 */     this.url = url;
/* 49 */     this.requestor = requestor;
/* 50 */     this.closed = false;
/*    */   }
/*    */   
/*    */   public int write(ByteBuffer src) throws IOException {
/* 54 */     return this.wrapped.write(src);
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/*    */     try {
/* 59 */       this.wrapped.close();
/*    */     } finally {
/* 61 */       if (!this.closed) {
/* 62 */         this.closed = true;
/* 63 */         this.shapefileFiles.unlockWrite(this.url, this.requestor);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean isOpen() {
/* 69 */     return this.wrapped.isOpen();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\files\WritableByteChannelDecorator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */