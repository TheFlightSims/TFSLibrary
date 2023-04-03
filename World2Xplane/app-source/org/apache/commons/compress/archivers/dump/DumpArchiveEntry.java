/*     */ package org.apache.commons.compress.archivers.dump;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.EnumSet;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ 
/*     */ public class DumpArchiveEntry implements ArchiveEntry {
/*     */   private String name;
/*     */   
/* 181 */   private TYPE type = TYPE.UNKNOWN;
/*     */   
/*     */   private int mode;
/*     */   
/* 183 */   private Set<PERMISSION> permissions = Collections.emptySet();
/*     */   
/*     */   private long size;
/*     */   
/*     */   private long atime;
/*     */   
/*     */   private long mtime;
/*     */   
/*     */   private int uid;
/*     */   
/*     */   private int gid;
/*     */   
/* 193 */   private DumpArchiveSummary summary = null;
/*     */   
/* 196 */   private TapeSegmentHeader header = new TapeSegmentHeader();
/*     */   
/*     */   private String simpleName;
/*     */   
/*     */   private String originalName;
/*     */   
/*     */   private int volume;
/*     */   
/*     */   private long offset;
/*     */   
/*     */   private int ino;
/*     */   
/*     */   private int nlink;
/*     */   
/*     */   private long ctime;
/*     */   
/*     */   private int generation;
/*     */   
/*     */   private boolean isDeleted;
/*     */   
/*     */   public DumpArchiveEntry() {}
/*     */   
/*     */   public DumpArchiveEntry(String name, String simpleName) {
/* 221 */     setName(name);
/* 222 */     this.simpleName = simpleName;
/*     */   }
/*     */   
/*     */   protected DumpArchiveEntry(String name, String simpleName, int ino, TYPE type) {
/* 235 */     setType(type);
/* 236 */     setName(name);
/* 237 */     this.simpleName = simpleName;
/* 238 */     this.ino = ino;
/* 239 */     this.offset = 0L;
/*     */   }
/*     */   
/*     */   public String getSimpleName() {
/* 253 */     return this.simpleName;
/*     */   }
/*     */   
/*     */   protected void setSimpleName(String simpleName) {
/* 260 */     this.simpleName = simpleName;
/*     */   }
/*     */   
/*     */   public int getIno() {
/* 267 */     return this.header.getIno();
/*     */   }
/*     */   
/*     */   public int getNlink() {
/* 274 */     return this.nlink;
/*     */   }
/*     */   
/*     */   public void setNlink(int nlink) {
/* 281 */     this.nlink = nlink;
/*     */   }
/*     */   
/*     */   public Date getCreationTime() {
/* 288 */     return new Date(this.ctime);
/*     */   }
/*     */   
/*     */   public void setCreationTime(Date ctime) {
/* 295 */     this.ctime = ctime.getTime();
/*     */   }
/*     */   
/*     */   public int getGeneration() {
/* 302 */     return this.generation;
/*     */   }
/*     */   
/*     */   public void setGeneration(int generation) {
/* 309 */     this.generation = generation;
/*     */   }
/*     */   
/*     */   public boolean isDeleted() {
/* 316 */     return this.isDeleted;
/*     */   }
/*     */   
/*     */   public void setDeleted(boolean isDeleted) {
/* 323 */     this.isDeleted = isDeleted;
/*     */   }
/*     */   
/*     */   public long getOffset() {
/* 330 */     return this.offset;
/*     */   }
/*     */   
/*     */   public void setOffset(long offset) {
/* 337 */     this.offset = offset;
/*     */   }
/*     */   
/*     */   public int getVolume() {
/* 344 */     return this.volume;
/*     */   }
/*     */   
/*     */   public void setVolume(int volume) {
/* 351 */     this.volume = volume;
/*     */   }
/*     */   
/*     */   public DumpArchiveConstants.SEGMENT_TYPE getHeaderType() {
/* 358 */     return this.header.getType();
/*     */   }
/*     */   
/*     */   public int getHeaderCount() {
/* 365 */     return this.header.getCount();
/*     */   }
/*     */   
/*     */   public int getHeaderHoles() {
/* 372 */     return this.header.getHoles();
/*     */   }
/*     */   
/*     */   public boolean isSparseRecord(int idx) {
/* 379 */     return ((this.header.getCdata(idx) & 0x1) == 0);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 387 */     return this.ino;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 395 */     if (o == this)
/* 396 */       return true; 
/* 397 */     if (o == null || !o.getClass().equals(getClass()))
/* 398 */       return false; 
/* 401 */     DumpArchiveEntry rhs = (DumpArchiveEntry)o;
/* 403 */     if (this.header == null || rhs.header == null)
/* 404 */       return false; 
/* 407 */     if (this.ino != rhs.ino)
/* 408 */       return false; 
/* 411 */     if ((this.summary == null && rhs.summary != null) || (this.summary != null && !this.summary.equals(rhs.summary)))
/* 413 */       return false; 
/* 416 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 424 */     return getName();
/*     */   }
/*     */   
/*     */   static DumpArchiveEntry parse(byte[] buffer) {
/* 435 */     DumpArchiveEntry entry = new DumpArchiveEntry();
/* 436 */     TapeSegmentHeader header = entry.header;
/* 438 */     header.type = DumpArchiveConstants.SEGMENT_TYPE.find(DumpArchiveUtil.convert32(buffer, 0));
/* 444 */     header.volume = DumpArchiveUtil.convert32(buffer, 12);
/* 446 */     entry.ino = header.ino = DumpArchiveUtil.convert32(buffer, 20);
/* 450 */     int m = DumpArchiveUtil.convert16(buffer, 32);
/* 453 */     entry.setType(TYPE.find(m >> 12 & 0xF));
/* 456 */     entry.setMode(m);
/* 458 */     entry.nlink = DumpArchiveUtil.convert16(buffer, 34);
/* 460 */     entry.setSize(DumpArchiveUtil.convert64(buffer, 40));
/* 462 */     long t = 1000L * DumpArchiveUtil.convert32(buffer, 48) + (DumpArchiveUtil.convert32(buffer, 52) / 1000);
/* 464 */     entry.setAccessTime(new Date(t));
/* 465 */     t = 1000L * DumpArchiveUtil.convert32(buffer, 56) + (DumpArchiveUtil.convert32(buffer, 60) / 1000);
/* 467 */     entry.setLastModifiedDate(new Date(t));
/* 468 */     t = 1000L * DumpArchiveUtil.convert32(buffer, 64) + (DumpArchiveUtil.convert32(buffer, 68) / 1000);
/* 470 */     entry.ctime = t;
/* 476 */     entry.generation = DumpArchiveUtil.convert32(buffer, 140);
/* 477 */     entry.setUserId(DumpArchiveUtil.convert32(buffer, 144));
/* 478 */     entry.setGroupId(DumpArchiveUtil.convert32(buffer, 148));
/* 480 */     header.count = DumpArchiveUtil.convert32(buffer, 160);
/* 482 */     header.holes = 0;
/* 484 */     for (int i = 0; i < 512 && i < header.count; i++) {
/* 485 */       if (buffer[164 + i] == 0)
/* 486 */         header.holes++; 
/*     */     } 
/* 490 */     System.arraycopy(buffer, 164, header.cdata, 0, 512);
/* 492 */     entry.volume = header.getVolume();
/* 495 */     return entry;
/*     */   }
/*     */   
/*     */   void update(byte[] buffer) {
/* 502 */     this.header.volume = DumpArchiveUtil.convert32(buffer, 16);
/* 503 */     this.header.count = DumpArchiveUtil.convert32(buffer, 160);
/* 505 */     this.header.holes = 0;
/* 507 */     for (int i = 0; i < 512 && i < this.header.count; i++) {
/* 508 */       if (buffer[164 + i] == 0)
/* 509 */         this.header.holes++; 
/*     */     } 
/* 513 */     System.arraycopy(buffer, 164, this.header.cdata, 0, 512);
/*     */   }
/*     */   
/*     */   static class TapeSegmentHeader {
/*     */     private DumpArchiveConstants.SEGMENT_TYPE type;
/*     */     
/*     */     private int volume;
/*     */     
/*     */     private int ino;
/*     */     
/*     */     private int count;
/*     */     
/*     */     private int holes;
/*     */     
/* 526 */     private byte[] cdata = new byte[512];
/*     */     
/*     */     public DumpArchiveConstants.SEGMENT_TYPE getType() {
/* 529 */       return this.type;
/*     */     }
/*     */     
/*     */     public int getVolume() {
/* 533 */       return this.volume;
/*     */     }
/*     */     
/*     */     public int getIno() {
/* 537 */       return this.ino;
/*     */     }
/*     */     
/*     */     void setIno(int ino) {
/* 541 */       this.ino = ino;
/*     */     }
/*     */     
/*     */     public int getCount() {
/* 545 */       return this.count;
/*     */     }
/*     */     
/*     */     public int getHoles() {
/* 549 */       return this.holes;
/*     */     }
/*     */     
/*     */     public int getCdata(int idx) {
/* 553 */       return this.cdata[idx];
/*     */     }
/*     */   }
/*     */   
/*     */   public String getName() {
/* 562 */     return this.name;
/*     */   }
/*     */   
/*     */   String getOriginalName() {
/* 570 */     return this.originalName;
/*     */   }
/*     */   
/*     */   public final void setName(String name) {
/* 577 */     this.originalName = name;
/* 578 */     if (name != null) {
/* 579 */       if (isDirectory() && !name.endsWith("/"))
/* 580 */         name = name + "/"; 
/* 582 */       if (name.startsWith("./"))
/* 583 */         name = name.substring(2); 
/*     */     } 
/* 586 */     this.name = name;
/*     */   }
/*     */   
/*     */   public Date getLastModifiedDate() {
/* 591 */     return new Date(this.mtime);
/*     */   }
/*     */   
/*     */   public boolean isDirectory() {
/* 598 */     return (this.type == TYPE.DIRECTORY);
/*     */   }
/*     */   
/*     */   public boolean isFile() {
/* 605 */     return (this.type == TYPE.FILE);
/*     */   }
/*     */   
/*     */   public boolean isSocket() {
/* 612 */     return (this.type == TYPE.SOCKET);
/*     */   }
/*     */   
/*     */   public boolean isChrDev() {
/* 619 */     return (this.type == TYPE.CHRDEV);
/*     */   }
/*     */   
/*     */   public boolean isBlkDev() {
/* 626 */     return (this.type == TYPE.BLKDEV);
/*     */   }
/*     */   
/*     */   public boolean isFifo() {
/* 633 */     return (this.type == TYPE.FIFO);
/*     */   }
/*     */   
/*     */   public TYPE getType() {
/* 640 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(TYPE type) {
/* 647 */     this.type = type;
/*     */   }
/*     */   
/*     */   public int getMode() {
/* 654 */     return this.mode;
/*     */   }
/*     */   
/*     */   public void setMode(int mode) {
/* 661 */     this.mode = mode & 0xFFF;
/* 662 */     this.permissions = PERMISSION.find(mode);
/*     */   }
/*     */   
/*     */   public Set<PERMISSION> getPermissions() {
/* 669 */     return this.permissions;
/*     */   }
/*     */   
/*     */   public long getSize() {
/* 676 */     return isDirectory() ? -1L : this.size;
/*     */   }
/*     */   
/*     */   long getEntrySize() {
/* 683 */     return this.size;
/*     */   }
/*     */   
/*     */   public void setSize(long size) {
/* 690 */     this.size = size;
/*     */   }
/*     */   
/*     */   public void setLastModifiedDate(Date mtime) {
/* 697 */     this.mtime = mtime.getTime();
/*     */   }
/*     */   
/*     */   public Date getAccessTime() {
/* 704 */     return new Date(this.atime);
/*     */   }
/*     */   
/*     */   public void setAccessTime(Date atime) {
/* 711 */     this.atime = atime.getTime();
/*     */   }
/*     */   
/*     */   public int getUserId() {
/* 718 */     return this.uid;
/*     */   }
/*     */   
/*     */   public void setUserId(int uid) {
/* 725 */     this.uid = uid;
/*     */   }
/*     */   
/*     */   public int getGroupId() {
/* 732 */     return this.gid;
/*     */   }
/*     */   
/*     */   public void setGroupId(int gid) {
/* 739 */     this.gid = gid;
/*     */   }
/*     */   
/*     */   public enum TYPE {
/* 743 */     WHITEOUT(14),
/* 744 */     SOCKET(12),
/* 745 */     LINK(10),
/* 746 */     FILE(8),
/* 747 */     BLKDEV(6),
/* 748 */     DIRECTORY(4),
/* 749 */     CHRDEV(2),
/* 750 */     FIFO(1),
/* 751 */     UNKNOWN(15);
/*     */     
/*     */     private int code;
/*     */     
/*     */     TYPE(int code) {
/* 756 */       this.code = code;
/*     */     }
/*     */     
/*     */     public static TYPE find(int code) {
/* 760 */       TYPE type = UNKNOWN;
/* 762 */       for (TYPE t : values()) {
/* 763 */         if (code == t.code)
/* 764 */           type = t; 
/*     */       } 
/* 768 */       return type;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum PERMISSION {
/* 773 */     SETUID(2048),
/* 774 */     SETGUI(1024),
/* 775 */     STICKY(512),
/* 776 */     USER_READ(256),
/* 777 */     USER_WRITE(128),
/* 778 */     USER_EXEC(64),
/* 779 */     GROUP_READ(32),
/* 780 */     GROUP_WRITE(16),
/* 781 */     GROUP_EXEC(8),
/* 782 */     WORLD_READ(4),
/* 783 */     WORLD_WRITE(2),
/* 784 */     WORLD_EXEC(1);
/*     */     
/*     */     private int code;
/*     */     
/*     */     PERMISSION(int code) {
/* 789 */       this.code = code;
/*     */     }
/*     */     
/*     */     public static Set<PERMISSION> find(int code) {
/* 793 */       Set<PERMISSION> set = new HashSet<PERMISSION>();
/* 795 */       for (PERMISSION p : values()) {
/* 796 */         if ((code & p.code) == p.code)
/* 797 */           set.add(p); 
/*     */       } 
/* 801 */       if (set.isEmpty())
/* 802 */         return Collections.emptySet(); 
/* 805 */       return EnumSet.copyOf(set);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\dump\DumpArchiveEntry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */