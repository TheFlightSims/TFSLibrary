/*     */ package org.openstreetmap.osmosis.osmbinary.file;
/*     */ 
/*     */ import com.google.protobuf.ByteString;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.zip.Deflater;
/*     */ import org.openstreetmap.osmosis.osmbinary.Fileformat;
/*     */ 
/*     */ public class FileBlock extends FileBlockBase {
/*     */   ByteString data;
/*     */   
/*  39 */   static int warncount = 0;
/*     */   
/*     */   private FileBlock(String type, ByteString blob, ByteString indexdata) {
/*  42 */     super(type, indexdata);
/*  43 */     this.data = blob;
/*     */   }
/*     */   
/*     */   public static FileBlock newInstance(String type, ByteString blob, ByteString indexdata) {
/*  48 */     if (blob != null && blob.size() > 16777216) {
/*  49 */       System.err.println("Warning: Fileblock has body size too large and may be considered corrupt");
/*  50 */       if (blob != null && blob.size() > 32505856)
/*  51 */         throw new Error("This file has too many entities in a block. Parsers will reject it."); 
/*     */     } 
/*  54 */     if (indexdata != null && indexdata.size() > 32768) {
/*  55 */       System.err.println("Warning: Fileblock has indexdata too large and may be considered corrupt");
/*  56 */       if (indexdata != null && indexdata.size() > 65024)
/*  57 */         throw new Error("This file header is too large. Parsers will reject it."); 
/*     */     } 
/*  60 */     return new FileBlock(type, blob, indexdata);
/*     */   }
/*     */   
/*     */   protected void deflateInto(Fileformat.Blob.Builder blobbuilder) {
/*  64 */     int size = this.data.size();
/*  65 */     Deflater deflater = new Deflater();
/*  66 */     deflater.setInput(this.data.toByteArray());
/*  67 */     deflater.finish();
/*  68 */     byte[] out = new byte[size];
/*  69 */     deflater.deflate(out);
/*  71 */     if (!deflater.finished()) {
/*  73 */       warncount++;
/*  74 */       if (warncount > 10 && warncount % 100 == 0)
/*  75 */         System.out.println("Compressed buffers are too short, causing extra copy"); 
/*  76 */       out = Arrays.copyOf(out, size + size / 64 + 16);
/*  77 */       deflater.deflate(out, deflater.getTotalOut(), out.length - deflater.getTotalOut());
/*  79 */       if (!deflater.finished())
/*  80 */         throw new Error("Internal error in compressor"); 
/*     */     } 
/*  83 */     ByteString compressed = ByteString.copyFrom(out, 0, deflater.getTotalOut());
/*  85 */     blobbuilder.setZlibData(compressed);
/*  86 */     deflater.end();
/*     */   }
/*     */   
/*     */   public FileBlockPosition writeTo(OutputStream outwrite, CompressFlags flags) throws IOException {
/*  91 */     Fileformat.BlobHeader.Builder builder = Fileformat.BlobHeader.newBuilder();
/*  93 */     if (this.indexdata != null)
/*  94 */       builder.setIndexdata(this.indexdata); 
/*  95 */     builder.setType(this.type);
/*  97 */     Fileformat.Blob.Builder blobbuilder = Fileformat.Blob.newBuilder();
/*  98 */     if (flags == CompressFlags.NONE) {
/*  99 */       blobbuilder.setRaw(this.data);
/* 100 */       blobbuilder.setRawSize(this.data.size());
/*     */     } else {
/* 102 */       blobbuilder.setRawSize(this.data.size());
/* 103 */       if (flags == CompressFlags.DEFLATE) {
/* 104 */         deflateInto(blobbuilder);
/*     */       } else {
/* 106 */         throw new Error("Compression flag not understood");
/*     */       } 
/*     */     } 
/* 108 */     Fileformat.Blob blob = blobbuilder.build();
/* 110 */     builder.setDatasize(blob.getSerializedSize());
/* 111 */     Fileformat.BlobHeader message = builder.build();
/* 112 */     int size = message.getSerializedSize();
/* 116 */     (new DataOutputStream(outwrite)).writeInt(size);
/* 117 */     message.writeTo(outwrite);
/* 118 */     long offset = -1L;
/* 120 */     if (outwrite instanceof FileOutputStream)
/* 121 */       offset = ((FileOutputStream)outwrite).getChannel().position(); 
/* 123 */     blob.writeTo(outwrite);
/* 124 */     return FileBlockPosition.newInstance(this, offset, size);
/*     */   }
/*     */   
/*     */   static void process(InputStream input, BlockReaderAdapter callback) throws IOException {
/* 130 */     FileBlockHead fileblock = FileBlockHead.readHead(input);
/* 131 */     if (callback.skipBlock(fileblock)) {
/* 133 */       fileblock.skipContents(input);
/*     */     } else {
/* 135 */       callback.handleBlock(fileblock.readContents(input));
/*     */     } 
/*     */   }
/*     */   
/*     */   public ByteString getData() {
/* 140 */     return this.data;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\osmbinary\file\FileBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */