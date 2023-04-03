/*    */ package org.openstreetmap.osmosis.osmbinary.file;
/*    */ 
/*    */ import com.google.protobuf.ByteString;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class FileBlockReference extends FileBlockPosition {
/*    */   protected InputStream input;
/*    */   
/*    */   protected FileBlockReference(String type, ByteString indexdata) {
/* 38 */     super(type, indexdata);
/*    */   }
/*    */   
/*    */   public FileBlock read() throws IOException {
/* 42 */     return read(this.input);
/*    */   }
/*    */   
/*    */   static FileBlockPosition newInstance(FileBlockBase base, InputStream input, long offset, int length) {
/* 47 */     FileBlockReference out = new FileBlockReference(base.type, base.indexdata);
/* 49 */     out.datasize = length;
/* 50 */     out.data_offset = offset;
/* 51 */     out.input = input;
/* 52 */     return out;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\osmbinary\file\FileBlockReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */