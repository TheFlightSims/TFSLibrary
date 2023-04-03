/*    */ package org.openstreetmap.osmosis.osmbinary.file;
/*    */ 
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class BlockOutputStream {
/*    */   OutputStream outwrite;
/*    */   
/*    */   List<FileBlockPosition> writtenblocks;
/*    */   
/*    */   CompressFlags compression;
/*    */   
/*    */   public BlockOutputStream(OutputStream output) {
/* 72 */     this.writtenblocks = new ArrayList<FileBlockPosition>();
/*    */     this.outwrite = new DataOutputStream(output);
/*    */     this.compression = CompressFlags.DEFLATE;
/*    */   }
/*    */   
/*    */   public void setCompress(CompressFlags flag) {
/*    */     this.compression = flag;
/*    */   }
/*    */   
/*    */   public void setCompress(String s) {
/*    */     if (s.equals("none")) {
/*    */       this.compression = CompressFlags.NONE;
/*    */     } else if (s.equals("deflate")) {
/*    */       this.compression = CompressFlags.DEFLATE;
/*    */     } else {
/*    */       throw new Error("Unknown compression type: " + s);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void write(FileBlock block) throws IOException {
/*    */     write(block, this.compression);
/*    */   }
/*    */   
/*    */   public void write(FileBlock block, CompressFlags compression) throws IOException {
/*    */     FileBlockPosition ref = block.writeTo(this.outwrite, compression);
/*    */     this.writtenblocks.add(ref);
/*    */   }
/*    */   
/*    */   public void flush() throws IOException {
/*    */     this.outwrite.flush();
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/*    */     this.outwrite.flush();
/*    */     this.outwrite.close();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\osmbinary\file\BlockOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */