/*     */ package org.apache.commons.compress.archivers.cpio;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ 
/*     */ public class CpioArchiveEntry implements CpioConstants, ArchiveEntry {
/*     */   private final short fileFormat;
/*     */   
/*     */   private final int headerSize;
/*     */   
/*     */   private final int alignmentBoundary;
/*     */   
/* 163 */   private long chksum = 0L;
/*     */   
/* 166 */   private long filesize = 0L;
/*     */   
/* 168 */   private long gid = 0L;
/*     */   
/* 170 */   private long inode = 0L;
/*     */   
/* 172 */   private long maj = 0L;
/*     */   
/* 174 */   private long min = 0L;
/*     */   
/* 176 */   private long mode = 0L;
/*     */   
/* 178 */   private long mtime = 0L;
/*     */   
/*     */   private String name;
/*     */   
/* 182 */   private long nlink = 0L;
/*     */   
/* 184 */   private long rmaj = 0L;
/*     */   
/* 186 */   private long rmin = 0L;
/*     */   
/* 188 */   private long uid = 0L;
/*     */   
/*     */   public CpioArchiveEntry(short format) {
/* 205 */     switch (format) {
/*     */       case 1:
/* 207 */         this.headerSize = 110;
/* 208 */         this.alignmentBoundary = 4;
/*     */         break;
/*     */       case 2:
/* 211 */         this.headerSize = 110;
/* 212 */         this.alignmentBoundary = 4;
/*     */         break;
/*     */       case 4:
/* 215 */         this.headerSize = 76;
/* 216 */         this.alignmentBoundary = 0;
/*     */         break;
/*     */       case 8:
/* 219 */         this.headerSize = 26;
/* 220 */         this.alignmentBoundary = 2;
/*     */         break;
/*     */       default:
/* 223 */         throw new IllegalArgumentException("Unknown header type");
/*     */     } 
/* 225 */     this.fileFormat = format;
/*     */   }
/*     */   
/*     */   public CpioArchiveEntry(String name) {
/* 236 */     this((short)1, name);
/*     */   }
/*     */   
/*     */   public CpioArchiveEntry(short format, String name) {
/* 257 */     this(format);
/* 258 */     this.name = name;
/*     */   }
/*     */   
/*     */   public CpioArchiveEntry(String name, long size) {
/* 271 */     this(name);
/* 272 */     setSize(size);
/*     */   }
/*     */   
/*     */   public CpioArchiveEntry(short format, String name, long size) {
/* 296 */     this(format, name);
/* 297 */     setSize(size);
/*     */   }
/*     */   
/*     */   public CpioArchiveEntry(File inputFile, String entryName) {
/* 311 */     this((short)1, inputFile, entryName);
/*     */   }
/*     */   
/*     */   public CpioArchiveEntry(short format, File inputFile, String entryName) {
/* 336 */     this(format, entryName, inputFile.isFile() ? inputFile.length() : 0L);
/* 337 */     long mode = 0L;
/* 338 */     if (inputFile.isDirectory()) {
/* 339 */       mode |= 0x4000L;
/* 340 */     } else if (inputFile.isFile()) {
/* 341 */       mode |= 0x8000L;
/*     */     } else {
/* 343 */       throw new IllegalArgumentException("Cannot determine type of file " + inputFile.getName());
/*     */     } 
/* 347 */     setMode(mode);
/* 348 */     setTime(inputFile.lastModified() / 1000L);
/*     */   }
/*     */   
/*     */   private void checkNewFormat() {
/* 355 */     if ((this.fileFormat & 0x3) == 0)
/* 356 */       throw new UnsupportedOperationException(); 
/*     */   }
/*     */   
/*     */   private void checkOldFormat() {
/* 364 */     if ((this.fileFormat & 0xC) == 0)
/* 365 */       throw new UnsupportedOperationException(); 
/*     */   }
/*     */   
/*     */   public long getChksum() {
/* 377 */     checkNewFormat();
/* 378 */     return this.chksum;
/*     */   }
/*     */   
/*     */   public long getDevice() {
/* 390 */     checkOldFormat();
/* 391 */     return this.min;
/*     */   }
/*     */   
/*     */   public long getDeviceMaj() {
/* 403 */     checkNewFormat();
/* 404 */     return this.maj;
/*     */   }
/*     */   
/*     */   public long getDeviceMin() {
/* 414 */     checkNewFormat();
/* 415 */     return this.min;
/*     */   }
/*     */   
/*     */   public long getSize() {
/* 425 */     return this.filesize;
/*     */   }
/*     */   
/*     */   public short getFormat() {
/* 434 */     return this.fileFormat;
/*     */   }
/*     */   
/*     */   public long getGID() {
/* 443 */     return this.gid;
/*     */   }
/*     */   
/*     */   public int getHeaderSize() {
/* 452 */     return this.headerSize;
/*     */   }
/*     */   
/*     */   public int getAlignmentBoundary() {
/* 461 */     return this.alignmentBoundary;
/*     */   }
/*     */   
/*     */   public int getHeaderPadCount() {
/* 470 */     if (this.alignmentBoundary == 0)
/* 470 */       return 0; 
/* 471 */     int size = this.headerSize + this.name.length() + 1;
/* 472 */     int remain = size % this.alignmentBoundary;
/* 473 */     if (remain > 0)
/* 474 */       return this.alignmentBoundary - remain; 
/* 476 */     return 0;
/*     */   }
/*     */   
/*     */   public int getDataPadCount() {
/* 485 */     if (this.alignmentBoundary == 0)
/* 485 */       return 0; 
/* 486 */     long size = this.filesize;
/* 487 */     int remain = (int)(size % this.alignmentBoundary);
/* 488 */     if (remain > 0)
/* 489 */       return this.alignmentBoundary - remain; 
/* 491 */     return 0;
/*     */   }
/*     */   
/*     */   public long getInode() {
/* 500 */     return this.inode;
/*     */   }
/*     */   
/*     */   public long getMode() {
/* 509 */     return (this.mode == 0L && !"TRAILER!!!".equals(this.name)) ? 32768L : this.mode;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 518 */     return this.name;
/*     */   }
/*     */   
/*     */   public long getNumberOfLinks() {
/* 527 */     return (this.nlink == 0L) ? (isDirectory() ? 2L : 1L) : this.nlink;
/*     */   }
/*     */   
/*     */   public long getRemoteDevice() {
/* 541 */     checkOldFormat();
/* 542 */     return this.rmin;
/*     */   }
/*     */   
/*     */   public long getRemoteDeviceMaj() {
/* 554 */     checkNewFormat();
/* 555 */     return this.rmaj;
/*     */   }
/*     */   
/*     */   public long getRemoteDeviceMin() {
/* 567 */     checkNewFormat();
/* 568 */     return this.rmin;
/*     */   }
/*     */   
/*     */   public long getTime() {
/* 577 */     return this.mtime;
/*     */   }
/*     */   
/*     */   public Date getLastModifiedDate() {
/* 582 */     return new Date(1000L * getTime());
/*     */   }
/*     */   
/*     */   public long getUID() {
/* 591 */     return this.uid;
/*     */   }
/*     */   
/*     */   public boolean isBlockDevice() {
/* 600 */     return ((this.mode & 0xF000L) == 24576L);
/*     */   }
/*     */   
/*     */   public boolean isCharacterDevice() {
/* 609 */     return ((this.mode & 0xF000L) == 8192L);
/*     */   }
/*     */   
/*     */   public boolean isDirectory() {
/* 618 */     return ((this.mode & 0xF000L) == 16384L);
/*     */   }
/*     */   
/*     */   public boolean isNetwork() {
/* 627 */     return ((this.mode & 0xF000L) == 36864L);
/*     */   }
/*     */   
/*     */   public boolean isPipe() {
/* 636 */     return ((this.mode & 0xF000L) == 4096L);
/*     */   }
/*     */   
/*     */   public boolean isRegularFile() {
/* 645 */     return ((this.mode & 0xF000L) == 32768L);
/*     */   }
/*     */   
/*     */   public boolean isSocket() {
/* 654 */     return ((this.mode & 0xF000L) == 49152L);
/*     */   }
/*     */   
/*     */   public boolean isSymbolicLink() {
/* 663 */     return ((this.mode & 0xF000L) == 40960L);
/*     */   }
/*     */   
/*     */   public void setChksum(long chksum) {
/* 674 */     checkNewFormat();
/* 675 */     this.chksum = chksum;
/*     */   }
/*     */   
/*     */   public void setDevice(long device) {
/* 688 */     checkOldFormat();
/* 689 */     this.min = device;
/*     */   }
/*     */   
/*     */   public void setDeviceMaj(long maj) {
/* 699 */     checkNewFormat();
/* 700 */     this.maj = maj;
/*     */   }
/*     */   
/*     */   public void setDeviceMin(long min) {
/* 710 */     checkNewFormat();
/* 711 */     this.min = min;
/*     */   }
/*     */   
/*     */   public void setSize(long size) {
/* 721 */     if (size < 0L || size > 4294967295L)
/* 722 */       throw new IllegalArgumentException("invalid entry size <" + size + ">"); 
/* 725 */     this.filesize = size;
/*     */   }
/*     */   
/*     */   public void setGID(long gid) {
/* 735 */     this.gid = gid;
/*     */   }
/*     */   
/*     */   public void setInode(long inode) {
/* 745 */     this.inode = inode;
/*     */   }
/*     */   
/*     */   public void setMode(long mode) {
/* 755 */     long maskedMode = mode & 0xF000L;
/* 756 */     switch ((int)maskedMode) {
/*     */       case 4096:
/*     */       case 8192:
/*     */       case 16384:
/*     */       case 24576:
/*     */       case 32768:
/*     */       case 36864:
/*     */       case 40960:
/*     */       case 49152:
/*     */         break;
/*     */       default:
/* 767 */         throw new IllegalArgumentException("Unknown mode. Full: " + Long.toHexString(mode) + " Masked: " + Long.toHexString(maskedMode));
/*     */     } 
/* 773 */     this.mode = mode;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 783 */     this.name = name;
/*     */   }
/*     */   
/*     */   public void setNumberOfLinks(long nlink) {
/* 793 */     this.nlink = nlink;
/*     */   }
/*     */   
/*     */   public void setRemoteDevice(long device) {
/* 806 */     checkOldFormat();
/* 807 */     this.rmin = device;
/*     */   }
/*     */   
/*     */   public void setRemoteDeviceMaj(long rmaj) {
/* 820 */     checkNewFormat();
/* 821 */     this.rmaj = rmaj;
/*     */   }
/*     */   
/*     */   public void setRemoteDeviceMin(long rmin) {
/* 834 */     checkNewFormat();
/* 835 */     this.rmin = rmin;
/*     */   }
/*     */   
/*     */   public void setTime(long time) {
/* 845 */     this.mtime = time;
/*     */   }
/*     */   
/*     */   public void setUID(long uid) {
/* 855 */     this.uid = uid;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 863 */     int prime = 31;
/* 864 */     int result = 1;
/* 865 */     result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
/* 866 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 874 */     if (this == obj)
/* 875 */       return true; 
/* 877 */     if (obj == null || getClass() != obj.getClass())
/* 878 */       return false; 
/* 880 */     CpioArchiveEntry other = (CpioArchiveEntry)obj;
/* 881 */     if (this.name == null) {
/* 882 */       if (other.name != null)
/* 883 */         return false; 
/* 885 */     } else if (!this.name.equals(other.name)) {
/* 886 */       return false;
/*     */     } 
/* 888 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\cpio\CpioArchiveEntry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */