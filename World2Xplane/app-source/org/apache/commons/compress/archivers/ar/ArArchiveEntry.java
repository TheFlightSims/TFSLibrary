/*     */ package org.apache.commons.compress.archivers.ar;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ 
/*     */ public class ArArchiveEntry implements ArchiveEntry {
/*     */   public static final String HEADER = "!<arch>\n";
/*     */   
/*     */   public static final String TRAILER = "`\n";
/*     */   
/*     */   private final String name;
/*     */   
/*     */   private final int userId;
/*     */   
/*     */   private final int groupId;
/*     */   
/*     */   private final int mode;
/*     */   
/*     */   private static final int DEFAULT_MODE = 33188;
/*     */   
/*     */   private final long lastModified;
/*     */   
/*     */   private final long length;
/*     */   
/*     */   public ArArchiveEntry(String name, long length) {
/*  85 */     this(name, length, 0, 0, 33188, System.currentTimeMillis() / 1000L);
/*     */   }
/*     */   
/*     */   public ArArchiveEntry(String name, long length, int userId, int groupId, int mode, long lastModified) {
/* 101 */     this.name = name;
/* 102 */     this.length = length;
/* 103 */     this.userId = userId;
/* 104 */     this.groupId = groupId;
/* 105 */     this.mode = mode;
/* 106 */     this.lastModified = lastModified;
/*     */   }
/*     */   
/*     */   public ArArchiveEntry(File inputFile, String entryName) {
/* 114 */     this(entryName, inputFile.isFile() ? inputFile.length() : 0L, 0, 0, 33188, inputFile.lastModified() / 1000L);
/*     */   }
/*     */   
/*     */   public long getSize() {
/* 120 */     return getLength();
/*     */   }
/*     */   
/*     */   public String getName() {
/* 125 */     return this.name;
/*     */   }
/*     */   
/*     */   public int getUserId() {
/* 129 */     return this.userId;
/*     */   }
/*     */   
/*     */   public int getGroupId() {
/* 133 */     return this.groupId;
/*     */   }
/*     */   
/*     */   public int getMode() {
/* 137 */     return this.mode;
/*     */   }
/*     */   
/*     */   public long getLastModified() {
/* 144 */     return this.lastModified;
/*     */   }
/*     */   
/*     */   public Date getLastModifiedDate() {
/* 149 */     return new Date(1000L * getLastModified());
/*     */   }
/*     */   
/*     */   public long getLength() {
/* 153 */     return this.length;
/*     */   }
/*     */   
/*     */   public boolean isDirectory() {
/* 158 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 164 */     int prime = 31;
/* 165 */     int result = 1;
/* 166 */     result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
/* 167 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 173 */     if (this == obj)
/* 174 */       return true; 
/* 176 */     if (obj == null || getClass() != obj.getClass())
/* 177 */       return false; 
/* 179 */     ArArchiveEntry other = (ArArchiveEntry)obj;
/* 180 */     if (this.name == null) {
/* 181 */       if (other.name != null)
/* 182 */         return false; 
/* 184 */     } else if (!this.name.equals(other.name)) {
/* 185 */       return false;
/*     */     } 
/* 187 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\ar\ArArchiveEntry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */