/*     */ package org.openstreetmap.osmosis.osmbinary.file;
/*     */ 
/*     */ import com.google.protobuf.ByteString;
/*     */ import com.google.protobuf.InvalidProtocolBufferException;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.zip.DataFormatException;
/*     */ import java.util.zip.Inflater;
/*     */ import org.openstreetmap.osmosis.osmbinary.Fileformat;
/*     */ 
/*     */ public class FileBlockPosition extends FileBlockBase {
/*     */   protected int datasize;
/*     */   
/*     */   long data_offset;
/*     */   
/*     */   protected FileBlockPosition(String type, ByteString indexdata) {
/*  42 */     super(type, indexdata);
/*     */   }
/*     */   
/*     */   FileBlock parseData(byte[] buf) throws InvalidProtocolBufferException {
/*  47 */     FileBlock out = FileBlock.newInstance(this.type, null, this.indexdata);
/*  48 */     Fileformat.Blob blob = Fileformat.Blob.parseFrom(buf);
/*  49 */     if (blob.hasRaw()) {
/*  50 */       out.data = blob.getRaw();
/*  51 */     } else if (blob.hasZlibData()) {
/*  52 */       byte[] buf2 = new byte[blob.getRawSize()];
/*  53 */       Inflater decompresser = new Inflater();
/*  54 */       decompresser.setInput(blob.getZlibData().toByteArray());
/*     */       try {
/*  57 */         decompresser.inflate(buf2);
/*  58 */       } catch (DataFormatException e) {
/*  59 */         e.printStackTrace();
/*  60 */         throw new Error(e);
/*     */       } 
/*  62 */       assert decompresser.finished();
/*  63 */       decompresser.end();
/*  64 */       out.data = ByteString.copyFrom(buf2);
/*     */     } 
/*  66 */     return out;
/*     */   }
/*     */   
/*     */   public int getDatasize() {
/*  70 */     return this.datasize;
/*     */   }
/*     */   
/*     */   static FileBlockPosition newInstance(FileBlockBase base, long offset, int length) {
/*  79 */     FileBlockPosition out = new FileBlockPosition(base.type, base.indexdata);
/*  80 */     out.datasize = length;
/*  81 */     out.data_offset = offset;
/*  82 */     return out;
/*     */   }
/*     */   
/*     */   public FileBlock read(InputStream input) throws IOException {
/*  86 */     if (input instanceof FileInputStream) {
/*  87 */       ((FileInputStream)input).getChannel().position(this.data_offset);
/*  88 */       byte[] buf = new byte[getDatasize()];
/*  89 */       (new DataInputStream(input)).readFully(buf);
/*  90 */       return parseData(buf);
/*     */     } 
/*  92 */     throw new Error("Random access binary reads require seekability");
/*     */   }
/*     */   
/*     */   public ByteString serialize() {
/* 101 */     throw new Error("TODO");
/*     */   }
/*     */   
/*     */   static FileBlockPosition parseFrom(ByteString b) {
/* 106 */     throw new Error("TODO");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\osmbinary\file\FileBlockPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */