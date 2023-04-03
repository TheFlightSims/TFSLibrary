/*    */ package org.openstreetmap.osmosis.osmbinary.file;
/*    */ 
/*    */ import com.google.protobuf.ByteString;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import org.openstreetmap.osmosis.osmbinary.Fileformat;
/*    */ 
/*    */ public class FileBlockHead extends FileBlockReference {
/*    */   protected FileBlockHead(String type, ByteString indexdata) {
/* 38 */     super(type, indexdata);
/*    */   }
/*    */   
/*    */   static FileBlockHead readHead(InputStream input) throws IOException {
/* 46 */     DataInputStream datinput = new DataInputStream(input);
/* 47 */     int headersize = datinput.readInt();
/* 49 */     if (headersize > 65536)
/* 50 */       throw new FileFormatException("Unexpectedly long header 65536 bytes. Possibly corrupt file."); 
/* 53 */     byte[] buf = new byte[headersize];
/* 54 */     datinput.readFully(buf);
/* 56 */     Fileformat.BlobHeader header = Fileformat.BlobHeader.parseFrom(buf);
/* 58 */     FileBlockHead fileblock = new FileBlockHead(header.getType(), header.getIndexdata());
/* 61 */     fileblock.datasize = header.getDatasize();
/* 62 */     if (header.getDatasize() > 33554432)
/* 63 */       throw new FileFormatException("Unexpectedly long body 33554432 bytes. Possibly corrupt file."); 
/* 66 */     fileblock.input = input;
/* 67 */     if (input instanceof FileInputStream)
/* 68 */       fileblock.data_offset = ((FileInputStream)input).getChannel().position(); 
/* 71 */     return fileblock;
/*    */   }
/*    */   
/*    */   void skipContents(InputStream input) throws IOException {
/* 81 */     if (input.skip(getDatasize()) != getDatasize() && 
/* 82 */       !$assertionsDisabled)
/* 82 */       throw new AssertionError("SHORT READ"); 
/*    */   }
/*    */   
/*    */   FileBlock readContents(InputStream input) throws IOException {
/* 92 */     DataInputStream datinput = new DataInputStream(input);
/* 93 */     byte[] buf = new byte[getDatasize()];
/* 94 */     datinput.readFully(buf);
/* 95 */     return parseData(buf);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\osmbinary\file\FileBlockHead.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */