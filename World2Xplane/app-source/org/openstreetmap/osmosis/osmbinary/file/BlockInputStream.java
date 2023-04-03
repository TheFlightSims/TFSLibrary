/*    */ package org.openstreetmap.osmosis.osmbinary.file;
/*    */ 
/*    */ import java.io.EOFException;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class BlockInputStream {
/*    */   InputStream input;
/*    */   
/*    */   BlockReaderAdapter adaptor;
/*    */   
/*    */   public BlockInputStream(InputStream input, BlockReaderAdapter adaptor) {
/* 27 */     this.input = input;
/* 28 */     this.adaptor = adaptor;
/*    */   }
/*    */   
/*    */   public void process() throws IOException {
/*    */     try {
/*    */       while (true)
/* 34 */         FileBlock.process(this.input, this.adaptor); 
/* 36 */     } catch (EOFException e) {
/* 37 */       this.adaptor.complete();
/*    */       return;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 42 */     this.input.close();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\osmbinary\file\BlockInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */