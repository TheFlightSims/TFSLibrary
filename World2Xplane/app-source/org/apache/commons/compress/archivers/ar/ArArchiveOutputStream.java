/*     */ package org.apache.commons.compress.archivers.ar;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveOutputStream;
/*     */ import org.apache.commons.compress.utils.ArchiveUtils;
/*     */ 
/*     */ public class ArArchiveOutputStream extends ArchiveOutputStream {
/*     */   public static final int LONGFILE_ERROR = 0;
/*     */   
/*     */   public static final int LONGFILE_BSD = 1;
/*     */   
/*     */   private final OutputStream out;
/*     */   
/*  42 */   private long entryOffset = 0L;
/*     */   
/*     */   private ArArchiveEntry prevEntry;
/*     */   
/*     */   private boolean haveUnclosedEntry = false;
/*     */   
/*  45 */   private int longFileMode = 0;
/*     */   
/*     */   private boolean finished = false;
/*     */   
/*     */   public ArArchiveOutputStream(OutputStream pOut) {
/*  51 */     this.out = pOut;
/*     */   }
/*     */   
/*     */   public void setLongFileMode(int longFileMode) {
/*  63 */     this.longFileMode = longFileMode;
/*     */   }
/*     */   
/*     */   private long writeArchiveHeader() throws IOException {
/*  67 */     byte[] header = ArchiveUtils.toAsciiBytes("!<arch>\n");
/*  68 */     this.out.write(header);
/*  69 */     return header.length;
/*     */   }
/*     */   
/*     */   public void closeArchiveEntry() throws IOException {
/*  75 */     if (this.finished)
/*  76 */       throw new IOException("Stream has already been finished"); 
/*  78 */     if (this.prevEntry == null || !this.haveUnclosedEntry)
/*  79 */       throw new IOException("No current entry to close"); 
/*  81 */     if (this.entryOffset % 2L != 0L)
/*  82 */       this.out.write(10); 
/*  84 */     this.haveUnclosedEntry = false;
/*     */   }
/*     */   
/*     */   public void putArchiveEntry(ArchiveEntry pEntry) throws IOException {
/*  90 */     if (this.finished)
/*  91 */       throw new IOException("Stream has already been finished"); 
/*  94 */     ArArchiveEntry pArEntry = (ArArchiveEntry)pEntry;
/*  95 */     if (this.prevEntry == null) {
/*  96 */       writeArchiveHeader();
/*     */     } else {
/*  98 */       if (this.prevEntry.getLength() != this.entryOffset)
/*  99 */         throw new IOException("length does not match entry (" + this.prevEntry.getLength() + " != " + this.entryOffset); 
/* 102 */       if (this.haveUnclosedEntry)
/* 103 */         closeArchiveEntry(); 
/*     */     } 
/* 107 */     this.prevEntry = pArEntry;
/* 109 */     writeEntryHeader(pArEntry);
/* 111 */     this.entryOffset = 0L;
/* 112 */     this.haveUnclosedEntry = true;
/*     */   }
/*     */   
/*     */   private long fill(long pOffset, long pNewOffset, char pFill) throws IOException {
/* 116 */     long diff = pNewOffset - pOffset;
/* 118 */     if (diff > 0L)
/* 119 */       for (int i = 0; i < diff; i++)
/* 120 */         write(pFill);  
/* 124 */     return pNewOffset;
/*     */   }
/*     */   
/*     */   private long write(String data) throws IOException {
/* 128 */     byte[] bytes = data.getBytes("ascii");
/* 129 */     write(bytes);
/* 130 */     return bytes.length;
/*     */   }
/*     */   
/*     */   private long writeEntryHeader(ArArchiveEntry pEntry) throws IOException {
/* 135 */     long offset = 0L;
/* 136 */     boolean mustAppendName = false;
/* 138 */     String n = pEntry.getName();
/* 139 */     if (0 == this.longFileMode && n.length() > 16)
/* 140 */       throw new IOException("filename too long, > 16 chars: " + n); 
/* 142 */     if (1 == this.longFileMode && (n.length() > 16 || n.indexOf(" ") > -1)) {
/* 144 */       mustAppendName = true;
/* 145 */       offset += write("#1/" + String.valueOf(n.length()));
/*     */     } else {
/* 148 */       offset += write(n);
/*     */     } 
/* 151 */     offset = fill(offset, 16L, ' ');
/* 152 */     String m = "" + pEntry.getLastModified();
/* 153 */     if (m.length() > 12)
/* 154 */       throw new IOException("modified too long"); 
/* 156 */     offset += write(m);
/* 158 */     offset = fill(offset, 28L, ' ');
/* 159 */     String u = "" + pEntry.getUserId();
/* 160 */     if (u.length() > 6)
/* 161 */       throw new IOException("userid too long"); 
/* 163 */     offset += write(u);
/* 165 */     offset = fill(offset, 34L, ' ');
/* 166 */     String g = "" + pEntry.getGroupId();
/* 167 */     if (g.length() > 6)
/* 168 */       throw new IOException("groupid too long"); 
/* 170 */     offset += write(g);
/* 172 */     offset = fill(offset, 40L, ' ');
/* 173 */     String fm = "" + Integer.toString(pEntry.getMode(), 8);
/* 174 */     if (fm.length() > 8)
/* 175 */       throw new IOException("filemode too long"); 
/* 177 */     offset += write(fm);
/* 179 */     offset = fill(offset, 48L, ' ');
/* 180 */     String s = String.valueOf(pEntry.getLength() + (mustAppendName ? n.length() : 0L));
/* 183 */     if (s.length() > 10)
/* 184 */       throw new IOException("size too long"); 
/* 186 */     offset += write(s);
/* 188 */     offset = fill(offset, 58L, ' ');
/* 190 */     offset += write("`\n");
/* 192 */     if (mustAppendName)
/* 193 */       offset += write(n); 
/* 196 */     return offset;
/*     */   }
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 201 */     this.out.write(b, off, len);
/* 202 */     count(len);
/* 203 */     this.entryOffset += len;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 211 */     if (!this.finished)
/* 212 */       finish(); 
/* 214 */     this.out.close();
/* 215 */     this.prevEntry = null;
/*     */   }
/*     */   
/*     */   public ArchiveEntry createArchiveEntry(File inputFile, String entryName) throws IOException {
/* 222 */     if (this.finished)
/* 223 */       throw new IOException("Stream has already been finished"); 
/* 225 */     return new ArArchiveEntry(inputFile, entryName);
/*     */   }
/*     */   
/*     */   public void finish() throws IOException {
/* 231 */     if (this.haveUnclosedEntry)
/* 232 */       throw new IOException("This archive contains unclosed entries."); 
/* 233 */     if (this.finished)
/* 234 */       throw new IOException("This archive has already been finished"); 
/* 236 */     this.finished = true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\ar\ArArchiveOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */