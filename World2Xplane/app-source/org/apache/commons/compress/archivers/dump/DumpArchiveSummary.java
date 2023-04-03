/*     */ package org.apache.commons.compress.archivers.dump;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class DumpArchiveSummary {
/*     */   private long dumpDate;
/*     */   
/*     */   private long previousDumpDate;
/*     */   
/*     */   private int volume;
/*     */   
/*     */   private String label;
/*     */   
/*     */   private int level;
/*     */   
/*     */   private String filesys;
/*     */   
/*     */   private String devname;
/*     */   
/*     */   private String hostname;
/*     */   
/*     */   private int flags;
/*     */   
/*     */   private int firstrec;
/*     */   
/*     */   private int ntrec;
/*     */   
/*     */   DumpArchiveSummary(byte[] buffer) {
/*  45 */     this.dumpDate = 1000L * DumpArchiveUtil.convert32(buffer, 4);
/*  46 */     this.previousDumpDate = 1000L * DumpArchiveUtil.convert32(buffer, 8);
/*  47 */     this.volume = DumpArchiveUtil.convert32(buffer, 12);
/*  48 */     this.label = (new String(buffer, 676, 16)).trim();
/*  49 */     this.level = DumpArchiveUtil.convert32(buffer, 692);
/*  50 */     this.filesys = (new String(buffer, 696, 64)).trim();
/*  51 */     this.devname = (new String(buffer, 760, 64)).trim();
/*  52 */     this.hostname = (new String(buffer, 824, 64)).trim();
/*  53 */     this.flags = DumpArchiveUtil.convert32(buffer, 888);
/*  54 */     this.firstrec = DumpArchiveUtil.convert32(buffer, 892);
/*  55 */     this.ntrec = DumpArchiveUtil.convert32(buffer, 896);
/*     */   }
/*     */   
/*     */   public Date getDumpDate() {
/*  65 */     return new Date(this.dumpDate);
/*     */   }
/*     */   
/*     */   public void setDumpDate(Date dumpDate) {
/*  72 */     this.dumpDate = dumpDate.getTime();
/*     */   }
/*     */   
/*     */   public Date getPreviousDumpDate() {
/*  80 */     return new Date(this.previousDumpDate);
/*     */   }
/*     */   
/*     */   public void setPreviousDumpDate(Date previousDumpDate) {
/*  87 */     this.previousDumpDate = previousDumpDate.getTime();
/*     */   }
/*     */   
/*     */   public int getVolume() {
/*  95 */     return this.volume;
/*     */   }
/*     */   
/*     */   public void setVolume(int volume) {
/* 102 */     this.volume = volume;
/*     */   }
/*     */   
/*     */   public int getLevel() {
/* 114 */     return this.level;
/*     */   }
/*     */   
/*     */   public void setLevel(int level) {
/* 121 */     this.level = level;
/*     */   }
/*     */   
/*     */   public String getLabel() {
/* 130 */     return this.label;
/*     */   }
/*     */   
/*     */   public void setLabel(String label) {
/* 138 */     this.label = label;
/*     */   }
/*     */   
/*     */   public String getFilesystem() {
/* 146 */     return this.filesys;
/*     */   }
/*     */   
/*     */   public void setFilesystem(String filesystem) {
/* 153 */     this.filesys = filesystem;
/*     */   }
/*     */   
/*     */   public String getDevname() {
/* 161 */     return this.devname;
/*     */   }
/*     */   
/*     */   public void setDevname(String devname) {
/* 169 */     this.devname = devname;
/*     */   }
/*     */   
/*     */   public String getHostname() {
/* 177 */     return this.hostname;
/*     */   }
/*     */   
/*     */   public void setHostname(String hostname) {
/* 184 */     this.hostname = hostname;
/*     */   }
/*     */   
/*     */   public int getFlags() {
/* 192 */     return this.flags;
/*     */   }
/*     */   
/*     */   public void setFlags(int flags) {
/* 200 */     this.flags = flags;
/*     */   }
/*     */   
/*     */   public int getFirstRecord() {
/* 208 */     return this.firstrec;
/*     */   }
/*     */   
/*     */   public void setFirstRecord(int firstrec) {
/* 216 */     this.firstrec = firstrec;
/*     */   }
/*     */   
/*     */   public int getNTRec() {
/* 225 */     return this.ntrec;
/*     */   }
/*     */   
/*     */   public void setNTRec(int ntrec) {
/* 232 */     this.ntrec = ntrec;
/*     */   }
/*     */   
/*     */   public boolean isNewHeader() {
/* 242 */     return ((this.flags & 0x1) == 1);
/*     */   }
/*     */   
/*     */   public boolean isNewInode() {
/* 251 */     return ((this.flags & 0x2) == 2);
/*     */   }
/*     */   
/*     */   public boolean isCompressed() {
/* 260 */     return ((this.flags & 0x80) == 128);
/*     */   }
/*     */   
/*     */   public boolean isMetaDataOnly() {
/* 268 */     return ((this.flags & 0x100) == 256);
/*     */   }
/*     */   
/*     */   public boolean isExtendedAttributes() {
/* 276 */     return ((this.flags & 0x8000) == 32768);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 284 */     int hash = 17;
/* 286 */     if (this.label != null)
/* 287 */       hash = this.label.hashCode(); 
/* 290 */     hash = (int)(hash + 31L * this.dumpDate);
/* 292 */     if (this.hostname != null)
/* 293 */       hash = 31 * this.hostname.hashCode() + 17; 
/* 296 */     if (this.devname != null)
/* 297 */       hash = 31 * this.devname.hashCode() + 17; 
/* 300 */     return hash;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 308 */     if (this == o)
/* 309 */       return true; 
/* 312 */     if (o == null || !o.getClass().equals(getClass()))
/* 313 */       return false; 
/* 316 */     DumpArchiveSummary rhs = (DumpArchiveSummary)o;
/* 318 */     if (this.dumpDate != rhs.dumpDate)
/* 319 */       return false; 
/* 322 */     if (getHostname() == null || !getHostname().equals(rhs.getHostname()))
/* 324 */       return false; 
/* 327 */     if (getDevname() == null || !getDevname().equals(rhs.getDevname()))
/* 328 */       return false; 
/* 331 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\dump\DumpArchiveSummary.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */