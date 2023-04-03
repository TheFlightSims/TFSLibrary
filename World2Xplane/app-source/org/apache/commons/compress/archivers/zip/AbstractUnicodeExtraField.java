/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.zip.CRC32;
/*     */ import java.util.zip.ZipException;
/*     */ 
/*     */ public abstract class AbstractUnicodeExtraField implements ZipExtraField {
/*     */   private long nameCRC32;
/*     */   
/*     */   private byte[] unicodeName;
/*     */   
/*     */   private byte[] data;
/*     */   
/*     */   protected AbstractUnicodeExtraField() {}
/*     */   
/*     */   protected AbstractUnicodeExtraField(String text, byte[] bytes, int off, int len) {
/*  52 */     CRC32 crc32 = new CRC32();
/*  53 */     crc32.update(bytes, off, len);
/*  54 */     this.nameCRC32 = crc32.getValue();
/*     */     try {
/*  57 */       this.unicodeName = text.getBytes("UTF-8");
/*  58 */     } catch (UnsupportedEncodingException e) {
/*  59 */       throw new RuntimeException("FATAL: UTF-8 encoding not supported.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected AbstractUnicodeExtraField(String text, byte[] bytes) {
/*  72 */     this(text, bytes, 0, bytes.length);
/*     */   }
/*     */   
/*     */   private void assembleData() {
/*  76 */     if (this.unicodeName == null)
/*     */       return; 
/*  80 */     this.data = new byte[5 + this.unicodeName.length];
/*  82 */     this.data[0] = 1;
/*  83 */     System.arraycopy(ZipLong.getBytes(this.nameCRC32), 0, this.data, 1, 4);
/*  84 */     System.arraycopy(this.unicodeName, 0, this.data, 5, this.unicodeName.length);
/*     */   }
/*     */   
/*     */   public long getNameCRC32() {
/*  92 */     return this.nameCRC32;
/*     */   }
/*     */   
/*     */   public void setNameCRC32(long nameCRC32) {
/* 100 */     this.nameCRC32 = nameCRC32;
/* 101 */     this.data = null;
/*     */   }
/*     */   
/*     */   public byte[] getUnicodeName() {
/* 108 */     byte[] b = null;
/* 109 */     if (this.unicodeName != null) {
/* 110 */       b = new byte[this.unicodeName.length];
/* 111 */       System.arraycopy(this.unicodeName, 0, b, 0, b.length);
/*     */     } 
/* 113 */     return b;
/*     */   }
/*     */   
/*     */   public void setUnicodeName(byte[] unicodeName) {
/* 120 */     if (unicodeName != null) {
/* 121 */       this.unicodeName = new byte[unicodeName.length];
/* 122 */       System.arraycopy(unicodeName, 0, this.unicodeName, 0, unicodeName.length);
/*     */     } else {
/* 125 */       this.unicodeName = null;
/*     */     } 
/* 127 */     this.data = null;
/*     */   }
/*     */   
/*     */   public byte[] getCentralDirectoryData() {
/* 132 */     if (this.data == null)
/* 133 */       assembleData(); 
/* 135 */     byte[] b = null;
/* 136 */     if (this.data != null) {
/* 137 */       b = new byte[this.data.length];
/* 138 */       System.arraycopy(this.data, 0, b, 0, b.length);
/*     */     } 
/* 140 */     return b;
/*     */   }
/*     */   
/*     */   public ZipShort getCentralDirectoryLength() {
/* 145 */     if (this.data == null)
/* 146 */       assembleData(); 
/* 148 */     return new ZipShort(this.data.length);
/*     */   }
/*     */   
/*     */   public byte[] getLocalFileDataData() {
/* 153 */     return getCentralDirectoryData();
/*     */   }
/*     */   
/*     */   public ZipShort getLocalFileDataLength() {
/* 158 */     return getCentralDirectoryLength();
/*     */   }
/*     */   
/*     */   public void parseFromLocalFileData(byte[] buffer, int offset, int length) throws ZipException {
/* 165 */     if (length < 5)
/* 166 */       throw new ZipException("UniCode path extra data must have at least 5 bytes."); 
/* 169 */     int version = buffer[offset];
/* 171 */     if (version != 1)
/* 172 */       throw new ZipException("Unsupported version [" + version + "] for UniCode path extra data."); 
/* 176 */     this.nameCRC32 = ZipLong.getValue(buffer, offset + 1);
/* 177 */     this.unicodeName = new byte[length - 5];
/* 178 */     System.arraycopy(buffer, offset + 5, this.unicodeName, 0, length - 5);
/* 179 */     this.data = null;
/*     */   }
/*     */   
/*     */   public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {
/* 189 */     parseFromLocalFileData(buffer, offset, length);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\zip\AbstractUnicodeExtraField.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */