/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipException;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ 
/*     */ public class ZipArchiveEntry extends ZipEntry implements ArchiveEntry {
/*     */   public static final int PLATFORM_UNIX = 3;
/*     */   
/*     */   public static final int PLATFORM_FAT = 0;
/*     */   
/*     */   private static final int SHORT_MASK = 65535;
/*     */   
/*     */   private static final int SHORT_SHIFT = 16;
/*     */   
/*  69 */   private int method = -1;
/*     */   
/*  77 */   private long size = -1L;
/*     */   
/*  79 */   private int internalAttributes = 0;
/*     */   
/*  80 */   private int platform = 0;
/*     */   
/*  81 */   private long externalAttributes = 0L;
/*     */   
/*  82 */   private LinkedHashMap<ZipShort, ZipExtraField> extraFields = null;
/*     */   
/*  83 */   private UnparseableExtraFieldData unparseableExtra = null;
/*     */   
/*  84 */   private String name = null;
/*     */   
/*  85 */   private byte[] rawName = null;
/*     */   
/*  86 */   private GeneralPurposeBit gpb = new GeneralPurposeBit();
/*     */   
/*     */   public ZipArchiveEntry(String name) {
/*  97 */     super(name);
/*  98 */     setName(name);
/*     */   }
/*     */   
/*     */   public ZipArchiveEntry(ZipEntry entry) throws ZipException {
/* 111 */     super(entry);
/* 112 */     setName(entry.getName());
/* 113 */     byte[] extra = entry.getExtra();
/* 114 */     if (extra != null) {
/* 115 */       setExtraFields(ExtraFieldUtils.parse(extra, true, ExtraFieldUtils.UnparseableExtraField.READ));
/*     */     } else {
/* 120 */       setExtra();
/*     */     } 
/* 122 */     setMethod(entry.getMethod());
/* 123 */     this.size = entry.getSize();
/*     */   }
/*     */   
/*     */   public ZipArchiveEntry(ZipArchiveEntry entry) throws ZipException {
/* 136 */     this(entry);
/* 137 */     setInternalAttributes(entry.getInternalAttributes());
/* 138 */     setExternalAttributes(entry.getExternalAttributes());
/* 139 */     setExtraFields(entry.getExtraFields(true));
/*     */   }
/*     */   
/*     */   protected ZipArchiveEntry() {
/* 145 */     this("");
/*     */   }
/*     */   
/*     */   public ZipArchiveEntry(File inputFile, String entryName) {
/* 158 */     this((inputFile.isDirectory() && !entryName.endsWith("/")) ? (entryName + "/") : entryName);
/* 160 */     if (inputFile.isFile())
/* 161 */       setSize(inputFile.length()); 
/* 163 */     setTime(inputFile.lastModified());
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 173 */     ZipArchiveEntry e = (ZipArchiveEntry)super.clone();
/* 175 */     e.setInternalAttributes(getInternalAttributes());
/* 176 */     e.setExternalAttributes(getExternalAttributes());
/* 177 */     e.setExtraFields(getExtraFields(true));
/* 178 */     return e;
/*     */   }
/*     */   
/*     */   public int getMethod() {
/* 191 */     return this.method;
/*     */   }
/*     */   
/*     */   public void setMethod(int method) {
/* 203 */     if (method < 0)
/* 204 */       throw new IllegalArgumentException("ZIP compression method can not be negative: " + method); 
/* 207 */     this.method = method;
/*     */   }
/*     */   
/*     */   public int getInternalAttributes() {
/* 216 */     return this.internalAttributes;
/*     */   }
/*     */   
/*     */   public void setInternalAttributes(int value) {
/* 224 */     this.internalAttributes = value;
/*     */   }
/*     */   
/*     */   public long getExternalAttributes() {
/* 232 */     return this.externalAttributes;
/*     */   }
/*     */   
/*     */   public void setExternalAttributes(long value) {
/* 240 */     this.externalAttributes = value;
/*     */   }
/*     */   
/*     */   public void setUnixMode(int mode) {
/* 250 */     setExternalAttributes((mode << 16 | (((mode & 0x80) == 0) ? 1 : 0) | (isDirectory() ? 16 : 0)));
/* 256 */     this.platform = 3;
/*     */   }
/*     */   
/*     */   public int getUnixMode() {
/* 264 */     return (this.platform != 3) ? 0 : (int)(getExternalAttributes() >> 16L & 0xFFFFL);
/*     */   }
/*     */   
/*     */   public int getPlatform() {
/* 276 */     return this.platform;
/*     */   }
/*     */   
/*     */   protected void setPlatform(int platform) {
/* 284 */     this.platform = platform;
/*     */   }
/*     */   
/*     */   public void setExtraFields(ZipExtraField[] fields) {
/* 292 */     this.extraFields = new LinkedHashMap<ZipShort, ZipExtraField>();
/* 293 */     for (ZipExtraField field : fields) {
/* 294 */       if (field instanceof UnparseableExtraFieldData) {
/* 295 */         this.unparseableExtra = (UnparseableExtraFieldData)field;
/*     */       } else {
/* 297 */         this.extraFields.put(field.getHeaderId(), field);
/*     */       } 
/*     */     } 
/* 300 */     setExtra();
/*     */   }
/*     */   
/*     */   public ZipExtraField[] getExtraFields() {
/* 308 */     return getExtraFields(false);
/*     */   }
/*     */   
/*     */   public ZipExtraField[] getExtraFields(boolean includeUnparseable) {
/* 321 */     if (this.extraFields == null) {
/* 322 */       (new ZipExtraField[1])[0] = this.unparseableExtra;
/* 322 */       return (!includeUnparseable || this.unparseableExtra == null) ? new ZipExtraField[0] : new ZipExtraField[1];
/*     */     } 
/* 326 */     List<ZipExtraField> result = new ArrayList<ZipExtraField>(this.extraFields.values());
/* 328 */     if (includeUnparseable && this.unparseableExtra != null)
/* 329 */       result.add(this.unparseableExtra); 
/* 331 */     return result.<ZipExtraField>toArray(new ZipExtraField[0]);
/*     */   }
/*     */   
/*     */   public void addExtraField(ZipExtraField ze) {
/* 343 */     if (ze instanceof UnparseableExtraFieldData) {
/* 344 */       this.unparseableExtra = (UnparseableExtraFieldData)ze;
/*     */     } else {
/* 346 */       if (this.extraFields == null)
/* 347 */         this.extraFields = new LinkedHashMap<ZipShort, ZipExtraField>(); 
/* 349 */       this.extraFields.put(ze.getHeaderId(), ze);
/*     */     } 
/* 351 */     setExtra();
/*     */   }
/*     */   
/*     */   public void addAsFirstExtraField(ZipExtraField ze) {
/* 362 */     if (ze instanceof UnparseableExtraFieldData) {
/* 363 */       this.unparseableExtra = (UnparseableExtraFieldData)ze;
/*     */     } else {
/* 365 */       LinkedHashMap<ZipShort, ZipExtraField> copy = this.extraFields;
/* 366 */       this.extraFields = new LinkedHashMap<ZipShort, ZipExtraField>();
/* 367 */       this.extraFields.put(ze.getHeaderId(), ze);
/* 368 */       if (copy != null) {
/* 369 */         copy.remove(ze.getHeaderId());
/* 370 */         this.extraFields.putAll(copy);
/*     */       } 
/*     */     } 
/* 373 */     setExtra();
/*     */   }
/*     */   
/*     */   public void removeExtraField(ZipShort type) {
/* 381 */     if (this.extraFields == null)
/* 382 */       throw new NoSuchElementException(); 
/* 384 */     if (this.extraFields.remove(type) == null)
/* 385 */       throw new NoSuchElementException(); 
/* 387 */     setExtra();
/*     */   }
/*     */   
/*     */   public void removeUnparseableExtraFieldData() {
/* 396 */     if (this.unparseableExtra == null)
/* 397 */       throw new NoSuchElementException(); 
/* 399 */     this.unparseableExtra = null;
/* 400 */     setExtra();
/*     */   }
/*     */   
/*     */   public ZipExtraField getExtraField(ZipShort type) {
/* 409 */     if (this.extraFields != null)
/* 410 */       return this.extraFields.get(type); 
/* 412 */     return null;
/*     */   }
/*     */   
/*     */   public UnparseableExtraFieldData getUnparseableExtraFieldData() {
/* 423 */     return this.unparseableExtra;
/*     */   }
/*     */   
/*     */   public void setExtra(byte[] extra) throws RuntimeException {
/*     */     try {
/* 437 */       ZipExtraField[] local = ExtraFieldUtils.parse(extra, true, ExtraFieldUtils.UnparseableExtraField.READ);
/* 440 */       mergeExtraFields(local, true);
/* 441 */     } catch (ZipException e) {
/* 443 */       throw new RuntimeException("Error parsing extra fields for entry: " + getName() + " - " + e.getMessage(), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void setExtra() {
/* 455 */     super.setExtra(ExtraFieldUtils.mergeLocalFileDataData(getExtraFields(true)));
/*     */   }
/*     */   
/*     */   public void setCentralDirectoryExtra(byte[] b) {
/*     */     try {
/* 463 */       ZipExtraField[] central = ExtraFieldUtils.parse(b, false, ExtraFieldUtils.UnparseableExtraField.READ);
/* 466 */       mergeExtraFields(central, false);
/* 467 */     } catch (ZipException e) {
/* 468 */       throw new RuntimeException(e.getMessage(), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte[] getLocalFileDataExtra() {
/* 477 */     byte[] extra = getExtra();
/* 478 */     return (extra != null) ? extra : new byte[0];
/*     */   }
/*     */   
/*     */   public byte[] getCentralDirectoryExtra() {
/* 486 */     return ExtraFieldUtils.mergeCentralDirectoryData(getExtraFields(true));
/*     */   }
/*     */   
/*     */   public String getName() {
/* 495 */     return (this.name == null) ? super.getName() : this.name;
/*     */   }
/*     */   
/*     */   public boolean isDirectory() {
/* 504 */     return getName().endsWith("/");
/*     */   }
/*     */   
/*     */   protected void setName(String name) {
/* 512 */     if (name != null && getPlatform() == 0 && name.indexOf("/") == -1)
/* 514 */       name = name.replace('\\', '/'); 
/* 516 */     this.name = name;
/*     */   }
/*     */   
/*     */   public long getSize() {
/* 525 */     return this.size;
/*     */   }
/*     */   
/*     */   public void setSize(long size) {
/* 536 */     if (size < 0L)
/* 537 */       throw new IllegalArgumentException("invalid entry size"); 
/* 539 */     this.size = size;
/*     */   }
/*     */   
/*     */   protected void setName(String name, byte[] rawName) {
/* 552 */     setName(name);
/* 553 */     this.rawName = rawName;
/*     */   }
/*     */   
/*     */   public byte[] getRawName() {
/* 566 */     if (this.rawName != null) {
/* 567 */       byte[] b = new byte[this.rawName.length];
/* 568 */       System.arraycopy(this.rawName, 0, b, 0, this.rawName.length);
/* 569 */       return b;
/*     */     } 
/* 571 */     return null;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 585 */     return getName().hashCode();
/*     */   }
/*     */   
/*     */   public GeneralPurposeBit getGeneralPurposeBit() {
/* 593 */     return this.gpb;
/*     */   }
/*     */   
/*     */   public void setGeneralPurposeBit(GeneralPurposeBit b) {
/* 601 */     this.gpb = b;
/*     */   }
/*     */   
/*     */   private void mergeExtraFields(ZipExtraField[] f, boolean local) throws ZipException {
/* 614 */     if (this.extraFields == null) {
/* 615 */       setExtraFields(f);
/*     */     } else {
/* 617 */       for (ZipExtraField element : f) {
/*     */         ZipExtraField existing;
/* 619 */         if (element instanceof UnparseableExtraFieldData) {
/* 620 */           existing = this.unparseableExtra;
/*     */         } else {
/* 622 */           existing = getExtraField(element.getHeaderId());
/*     */         } 
/* 624 */         if (existing == null) {
/* 625 */           addExtraField(element);
/* 627 */         } else if (local) {
/* 628 */           byte[] b = element.getLocalFileDataData();
/* 629 */           existing.parseFromLocalFileData(b, 0, b.length);
/*     */         } else {
/* 631 */           byte[] b = element.getCentralDirectoryData();
/* 632 */           existing.parseFromCentralDirectoryData(b, 0, b.length);
/*     */         } 
/*     */       } 
/* 636 */       setExtra();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Date getLastModifiedDate() {
/* 642 */     return new Date(getTime());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 650 */     if (this == obj)
/* 651 */       return true; 
/* 653 */     if (obj == null || getClass() != obj.getClass())
/* 654 */       return false; 
/* 656 */     ZipArchiveEntry other = (ZipArchiveEntry)obj;
/* 657 */     String myName = getName();
/* 658 */     String otherName = other.getName();
/* 659 */     if (myName == null) {
/* 660 */       if (otherName != null)
/* 661 */         return false; 
/* 663 */     } else if (!myName.equals(otherName)) {
/* 664 */       return false;
/*     */     } 
/* 666 */     String myComment = getComment();
/* 667 */     String otherComment = other.getComment();
/* 668 */     if (myComment == null) {
/* 669 */       if (otherComment != null)
/* 670 */         return false; 
/* 672 */     } else if (!myComment.equals(otherComment)) {
/* 673 */       return false;
/*     */     } 
/* 675 */     return (getTime() == other.getTime() && getInternalAttributes() == other.getInternalAttributes() && getPlatform() == other.getPlatform() && getExternalAttributes() == other.getExternalAttributes() && getMethod() == other.getMethod() && getSize() == other.getSize() && getCrc() == other.getCrc() && getCompressedSize() == other.getCompressedSize() && Arrays.equals(getCentralDirectoryExtra(), other.getCentralDirectoryExtra()) && Arrays.equals(getLocalFileDataExtra(), other.getLocalFileDataExtra()) && this.gpb.equals(other.gpb));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\zip\ZipArchiveEntry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */