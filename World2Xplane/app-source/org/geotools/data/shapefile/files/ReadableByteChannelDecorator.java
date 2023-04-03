/*    */ package org.geotools.data.shapefile.files;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URL;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.channels.ReadableByteChannel;
/*    */ 
/*    */ public class ReadableByteChannelDecorator implements ReadableByteChannel {
/*    */   private final ReadableByteChannel wrapped;
/*    */   
/*    */   private final ShpFiles shapefileFiles;
/*    */   
/*    */   private final URL url;
/*    */   
/*    */   private final FileReader requestor;
/*    */   
/*    */   private boolean closed;
/*    */   
/*    */   public ReadableByteChannelDecorator(ReadableByteChannel newChannel, ShpFiles shapefileFiles, URL url, FileReader requestor) {
/* 46 */     this.wrapped = newChannel;
/* 47 */     this.shapefileFiles = shapefileFiles;
/* 48 */     this.url = url;
/* 49 */     this.requestor = requestor;
/* 50 */     this.closed = false;
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/*    */     try {
/* 55 */       this.wrapped.close();
/*    */     } finally {
/* 57 */       if (!this.closed) {
/* 58 */         this.closed = true;
/* 59 */         this.shapefileFiles.unlockRead(this.url, this.requestor);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean isOpen() {
/* 65 */     return this.wrapped.isOpen();
/*    */   }
/*    */   
/*    */   public int read(ByteBuffer dst) throws IOException {
/* 69 */     return this.wrapped.read(dst);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\files\ReadableByteChannelDecorator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */