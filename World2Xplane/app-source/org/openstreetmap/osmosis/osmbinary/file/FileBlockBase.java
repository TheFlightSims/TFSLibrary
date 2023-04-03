/*    */ package org.openstreetmap.osmosis.osmbinary.file;
/*    */ 
/*    */ import com.google.protobuf.ByteString;
/*    */ 
/*    */ public class FileBlockBase {
/*    */   static final int MAX_HEADER_SIZE = 65536;
/*    */   
/*    */   static final int MAX_BODY_SIZE = 33554432;
/*    */   
/*    */   protected final String type;
/*    */   
/*    */   protected final ByteString indexdata;
/*    */   
/*    */   protected FileBlockBase(String type, ByteString indexdata) {
/* 39 */     this.type = type;
/* 40 */     this.indexdata = indexdata;
/*    */   }
/*    */   
/*    */   public String getType() {
/* 52 */     return this.type;
/*    */   }
/*    */   
/*    */   public ByteString getIndexData() {
/* 56 */     return this.indexdata;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\osmbinary\file\FileBlockBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */