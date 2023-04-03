/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.zip.Inflater;
/*     */ import java.util.zip.InflaterInputStream;
/*     */ import java.util.zip.ZipException;
/*     */ 
/*     */ public class ZipFile {
/*     */   private static final int HASH_SIZE = 509;
/*     */   
/*     */   static final int NIBLET_MASK = 15;
/*     */   
/*     */   static final int BYTE_SHIFT = 8;
/*     */   
/*     */   private static final int POS_0 = 0;
/*     */   
/*     */   private static final int POS_1 = 1;
/*     */   
/*     */   private static final int POS_2 = 2;
/*     */   
/*     */   private static final int POS_3 = 3;
/*     */   
/*  87 */   private final Map<ZipArchiveEntry, OffsetEntry> entries = new LinkedHashMap<ZipArchiveEntry, OffsetEntry>(509);
/*     */   
/*  93 */   private final Map<String, ZipArchiveEntry> nameMap = new HashMap<String, ZipArchiveEntry>(509);
/*     */   
/*     */   private final String encoding;
/*     */   
/*     */   private final ZipEncoding zipEncoding;
/*     */   
/*     */   private final String archiveName;
/*     */   
/*     */   private final RandomAccessFile archive;
/*     */   
/*     */   private final boolean useUnicodeExtraFields;
/*     */   
/*     */   private boolean closed;
/*     */   
/*     */   private static final int CFH_LEN = 42;
/*     */   
/*     */   private static final class OffsetEntry {
/*     */     private OffsetEntry() {}
/*     */     
/*  97 */     private long headerOffset = -1L;
/*     */     
/*  98 */     private long dataOffset = -1L;
/*     */   }
/*     */   
/*     */   public ZipFile(File f) throws IOException {
/* 143 */     this(f, "UTF8");
/*     */   }
/*     */   
/*     */   public ZipFile(String name) throws IOException {
/* 154 */     this(new File(name), "UTF8");
/*     */   }
/*     */   
/*     */   public ZipFile(String name, String encoding) throws IOException {
/* 168 */     this(new File(name), encoding, true);
/*     */   }
/*     */   
/*     */   public ZipFile(File f, String encoding) throws IOException {
/* 182 */     this(f, encoding, true);
/*     */   }
/*     */   
/*     */   public String getEncoding() {
/* 228 */     return this.encoding;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 239 */     this.closed = true;
/* 241 */     this.archive.close();
/*     */   }
/*     */   
/*     */   public static void closeQuietly(ZipFile zipfile) {
/* 250 */     if (zipfile != null)
/*     */       try {
/* 252 */         zipfile.close();
/* 253 */       } catch (IOException e) {} 
/*     */   }
/*     */   
/*     */   public Enumeration<ZipArchiveEntry> getEntries() {
/* 268 */     return Collections.enumeration(this.entries.keySet());
/*     */   }
/*     */   
/*     */   public Enumeration<ZipArchiveEntry> getEntriesInPhysicalOrder() {
/* 282 */     ZipArchiveEntry[] allEntries = (ZipArchiveEntry[])this.entries.keySet().toArray((Object[])new ZipArchiveEntry[0]);
/* 284 */     Arrays.sort(allEntries, this.OFFSET_COMPARATOR);
/* 285 */     return Collections.enumeration(Arrays.asList(allEntries));
/*     */   }
/*     */   
/*     */   public ZipArchiveEntry getEntry(String name) {
/* 296 */     return this.nameMap.get(name);
/*     */   }
/*     */   
/*     */   public boolean canReadEntryData(ZipArchiveEntry ze) {
/* 307 */     return ZipUtil.canHandleEntryData(ze);
/*     */   }
/*     */   
/*     */   public InputStream getInputStream(ZipArchiveEntry ze) throws IOException, ZipException {
/*     */     final Inflater inflater;
/* 320 */     OffsetEntry offsetEntry = this.entries.get(ze);
/* 321 */     if (offsetEntry == null)
/* 322 */       return null; 
/* 324 */     ZipUtil.checkRequestedFeatures(ze);
/* 325 */     long start = offsetEntry.dataOffset;
/* 326 */     BoundedInputStream bis = new BoundedInputStream(start, ze.getCompressedSize());
/* 328 */     switch (ze.getMethod()) {
/*     */       case 0:
/* 330 */         return bis;
/*     */       case 8:
/* 332 */         bis.addDummy();
/* 333 */         inflater = new Inflater(true);
/* 334 */         return new InflaterInputStream(bis, inflater) {
/*     */             public void close() throws IOException {
/* 337 */               super.close();
/* 338 */               inflater.end();
/*     */             }
/*     */           };
/*     */     } 
/* 342 */     throw new ZipException("Found unsupported compression method " + ze.getMethod());
/*     */   }
/*     */   
/*     */   protected void finalize() throws Throwable {
/*     */     try {
/* 355 */       if (!this.closed) {
/* 356 */         System.err.println("Cleaning up unclosed ZipFile for archive " + this.archiveName);
/* 358 */         close();
/*     */       } 
/*     */     } finally {
/* 361 */       super.finalize();
/*     */     } 
/*     */   }
/*     */   
/* 387 */   private static final long CFH_SIG = ZipLong.getValue(ZipArchiveOutputStream.CFH_SIG);
/*     */   
/*     */   private static final int MIN_EOCD_SIZE = 22;
/*     */   
/*     */   private static final int MAX_EOCD_SIZE = 65557;
/*     */   
/*     */   private static final int CFD_LOCATOR_OFFSET = 16;
/*     */   
/*     */   private static final int ZIP64_EOCDL_LENGTH = 20;
/*     */   
/*     */   private static final int ZIP64_EOCDL_LOCATOR_OFFSET = 8;
/*     */   
/*     */   private static final int ZIP64_EOCD_CFD_LOCATOR_OFFSET = 48;
/*     */   
/*     */   private static final long LFH_OFFSET_FOR_FILENAME_LENGTH = 26L;
/*     */   
/*     */   private final Comparator<ZipArchiveEntry> OFFSET_COMPARATOR;
/*     */   
/*     */   private Map<ZipArchiveEntry, NameAndComment> populateFromCentralDirectory() throws IOException {
/* 403 */     HashMap<ZipArchiveEntry, NameAndComment> noUTF8Flag = new HashMap<ZipArchiveEntry, NameAndComment>();
/* 406 */     positionAtCentralDirectory();
/* 408 */     byte[] signatureBytes = new byte[4];
/* 409 */     this.archive.readFully(signatureBytes);
/* 410 */     long sig = ZipLong.getValue(signatureBytes);
/* 412 */     if (sig != CFH_SIG && startsWithLocalFileHeader())
/* 413 */       throw new IOException("central directory is empty, can't expand corrupt archive."); 
/* 417 */     while (sig == CFH_SIG) {
/* 418 */       readCentralDirectoryEntry(noUTF8Flag);
/* 419 */       this.archive.readFully(signatureBytes);
/* 420 */       sig = ZipLong.getValue(signatureBytes);
/*     */     } 
/* 422 */     return noUTF8Flag;
/*     */   }
/*     */   
/*     */   private void readCentralDirectoryEntry(Map<ZipArchiveEntry, NameAndComment> noUTF8Flag) throws IOException {
/* 437 */     byte[] cfh = new byte[42];
/* 439 */     this.archive.readFully(cfh);
/* 440 */     int off = 0;
/* 441 */     ZipArchiveEntry ze = new ZipArchiveEntry();
/* 443 */     int versionMadeBy = ZipShort.getValue(cfh, off);
/* 444 */     off += 2;
/* 445 */     ze.setPlatform(versionMadeBy >> 8 & 0xF);
/* 447 */     off += 2;
/* 449 */     GeneralPurposeBit gpFlag = GeneralPurposeBit.parse(cfh, off);
/* 450 */     boolean hasUTF8Flag = gpFlag.usesUTF8ForNames();
/* 451 */     ZipEncoding entryEncoding = hasUTF8Flag ? ZipEncodingHelper.UTF8_ZIP_ENCODING : this.zipEncoding;
/* 453 */     ze.setGeneralPurposeBit(gpFlag);
/* 455 */     off += 2;
/* 457 */     ze.setMethod(ZipShort.getValue(cfh, off));
/* 458 */     off += 2;
/* 460 */     long time = ZipUtil.dosToJavaTime(ZipLong.getValue(cfh, off));
/* 461 */     ze.setTime(time);
/* 462 */     off += 4;
/* 464 */     ze.setCrc(ZipLong.getValue(cfh, off));
/* 465 */     off += 4;
/* 467 */     ze.setCompressedSize(ZipLong.getValue(cfh, off));
/* 468 */     off += 4;
/* 470 */     ze.setSize(ZipLong.getValue(cfh, off));
/* 471 */     off += 4;
/* 473 */     int fileNameLen = ZipShort.getValue(cfh, off);
/* 474 */     off += 2;
/* 476 */     int extraLen = ZipShort.getValue(cfh, off);
/* 477 */     off += 2;
/* 479 */     int commentLen = ZipShort.getValue(cfh, off);
/* 480 */     off += 2;
/* 482 */     int diskStart = ZipShort.getValue(cfh, off);
/* 483 */     off += 2;
/* 485 */     ze.setInternalAttributes(ZipShort.getValue(cfh, off));
/* 486 */     off += 2;
/* 488 */     ze.setExternalAttributes(ZipLong.getValue(cfh, off));
/* 489 */     off += 4;
/* 491 */     byte[] fileName = new byte[fileNameLen];
/* 492 */     this.archive.readFully(fileName);
/* 493 */     ze.setName(entryEncoding.decode(fileName), fileName);
/* 496 */     OffsetEntry offset = new OffsetEntry();
/* 497 */     offset.headerOffset = ZipLong.getValue(cfh, off);
/* 499 */     this.entries.put(ze, offset);
/* 501 */     this.nameMap.put(ze.getName(), ze);
/* 503 */     byte[] cdExtraData = new byte[extraLen];
/* 504 */     this.archive.readFully(cdExtraData);
/* 505 */     ze.setCentralDirectoryExtra(cdExtraData);
/* 507 */     setSizesAndOffsetFromZip64Extra(ze, offset, diskStart);
/* 509 */     byte[] comment = new byte[commentLen];
/* 510 */     this.archive.readFully(comment);
/* 511 */     ze.setComment(entryEncoding.decode(comment));
/* 513 */     if (!hasUTF8Flag && this.useUnicodeExtraFields)
/* 514 */       noUTF8Flag.put(ze, new NameAndComment(fileName, comment)); 
/*     */   }
/*     */   
/*     */   private void setSizesAndOffsetFromZip64Extra(ZipArchiveEntry ze, OffsetEntry offset, int diskStart) throws IOException {
/* 534 */     Zip64ExtendedInformationExtraField z64 = (Zip64ExtendedInformationExtraField)ze.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
/* 537 */     if (z64 != null) {
/* 538 */       boolean hasUncompressedSize = (ze.getSize() == 4294967295L);
/* 539 */       boolean hasCompressedSize = (ze.getCompressedSize() == 4294967295L);
/* 540 */       boolean hasRelativeHeaderOffset = (offset.headerOffset == 4294967295L);
/* 542 */       z64.reparseCentralDirectoryData(hasUncompressedSize, hasCompressedSize, hasRelativeHeaderOffset, (diskStart == 65535));
/* 547 */       if (hasUncompressedSize) {
/* 548 */         ze.setSize(z64.getSize().getLongValue());
/* 549 */       } else if (hasCompressedSize) {
/* 550 */         z64.setSize(new ZipEightByteInteger(ze.getSize()));
/*     */       } 
/* 553 */       if (hasCompressedSize) {
/* 554 */         ze.setCompressedSize(z64.getCompressedSize().getLongValue());
/* 555 */       } else if (hasUncompressedSize) {
/* 556 */         z64.setCompressedSize(new ZipEightByteInteger(ze.getCompressedSize()));
/*     */       } 
/* 559 */       if (hasRelativeHeaderOffset)
/* 560 */         offset.headerOffset = z64.getRelativeHeaderOffset().getLongValue(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void positionAtCentralDirectory() throws IOException {
/* 666 */     boolean found = tryToLocateSignature(42L, 65577L, ZipArchiveOutputStream.ZIP64_EOCD_LOC_SIG);
/* 670 */     if (!found) {
/* 672 */       positionAtCentralDirectory32();
/*     */     } else {
/* 674 */       positionAtCentralDirectory64();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void positionAtCentralDirectory64() throws IOException {
/* 686 */     skipBytes(8);
/* 687 */     byte[] zip64EocdOffset = new byte[8];
/* 688 */     this.archive.readFully(zip64EocdOffset);
/* 689 */     this.archive.seek(ZipEightByteInteger.getLongValue(zip64EocdOffset));
/* 690 */     byte[] sig = new byte[4];
/* 691 */     this.archive.readFully(sig);
/* 692 */     if (sig[0] != ZipArchiveOutputStream.ZIP64_EOCD_SIG[0] || sig[1] != ZipArchiveOutputStream.ZIP64_EOCD_SIG[1] || sig[2] != ZipArchiveOutputStream.ZIP64_EOCD_SIG[2] || sig[3] != ZipArchiveOutputStream.ZIP64_EOCD_SIG[3])
/* 697 */       throw new ZipException("archive's ZIP64 end of central directory locator is corrupt."); 
/* 700 */     skipBytes(44);
/* 702 */     byte[] cfdOffset = new byte[8];
/* 703 */     this.archive.readFully(cfdOffset);
/* 704 */     this.archive.seek(ZipEightByteInteger.getLongValue(cfdOffset));
/*     */   }
/*     */   
/*     */   private void positionAtCentralDirectory32() throws IOException {
/* 714 */     boolean found = tryToLocateSignature(22L, 65557L, ZipArchiveOutputStream.EOCD_SIG);
/* 716 */     if (!found)
/* 717 */       throw new ZipException("archive is not a ZIP archive"); 
/* 719 */     skipBytes(16);
/* 720 */     byte[] cfdOffset = new byte[4];
/* 721 */     this.archive.readFully(cfdOffset);
/* 722 */     this.archive.seek(ZipLong.getValue(cfdOffset));
/*     */   }
/*     */   
/*     */   private boolean tryToLocateSignature(long minDistanceFromEnd, long maxDistanceFromEnd, byte[] sig) throws IOException {
/* 733 */     boolean found = false;
/* 734 */     long off = this.archive.length() - minDistanceFromEnd;
/* 735 */     long stopSearching = Math.max(0L, this.archive.length() - maxDistanceFromEnd);
/* 737 */     if (off >= 0L)
/* 738 */       for (; off >= stopSearching; off--) {
/* 739 */         this.archive.seek(off);
/* 740 */         int curr = this.archive.read();
/* 741 */         if (curr == -1)
/*     */           break; 
/* 744 */         if (curr == sig[0]) {
/* 745 */           curr = this.archive.read();
/* 746 */           if (curr == sig[1]) {
/* 747 */             curr = this.archive.read();
/* 748 */             if (curr == sig[2]) {
/* 749 */               curr = this.archive.read();
/* 750 */               if (curr == sig[3]) {
/* 751 */                 found = true;
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }  
/* 759 */     if (found)
/* 760 */       this.archive.seek(off); 
/* 762 */     return found;
/*     */   }
/*     */   
/*     */   private void skipBytes(int count) throws IOException {
/* 770 */     int totalSkipped = 0;
/* 771 */     while (totalSkipped < count) {
/* 772 */       int skippedNow = this.archive.skipBytes(count - totalSkipped);
/* 773 */       if (skippedNow <= 0)
/* 774 */         throw new EOFException(); 
/* 776 */       totalSkipped += skippedNow;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void resolveLocalFileHeaderData(Map<ZipArchiveEntry, NameAndComment> entriesWithoutUTF8Flag) throws IOException {
/* 809 */     Map<ZipArchiveEntry, OffsetEntry> origMap = new LinkedHashMap<ZipArchiveEntry, OffsetEntry>(this.entries);
/* 811 */     this.entries.clear();
/* 812 */     for (Map.Entry<ZipArchiveEntry, OffsetEntry> ent : origMap.entrySet()) {
/* 813 */       ZipArchiveEntry ze = ent.getKey();
/* 814 */       OffsetEntry offsetEntry = ent.getValue();
/* 815 */       long offset = offsetEntry.headerOffset;
/* 816 */       this.archive.seek(offset + 26L);
/* 817 */       byte[] b = new byte[2];
/* 818 */       this.archive.readFully(b);
/* 819 */       int fileNameLen = ZipShort.getValue(b);
/* 820 */       this.archive.readFully(b);
/* 821 */       int extraFieldLen = ZipShort.getValue(b);
/* 822 */       int lenToSkip = fileNameLen;
/* 823 */       while (lenToSkip > 0) {
/* 824 */         int skipped = this.archive.skipBytes(lenToSkip);
/* 825 */         if (skipped <= 0)
/* 826 */           throw new IOException("failed to skip file name in local file header"); 
/* 829 */         lenToSkip -= skipped;
/*     */       } 
/* 831 */       byte[] localExtraData = new byte[extraFieldLen];
/* 832 */       this.archive.readFully(localExtraData);
/* 833 */       ze.setExtra(localExtraData);
/* 834 */       offsetEntry.dataOffset = offset + 26L + 2L + 2L + fileNameLen + extraFieldLen;
/* 837 */       if (entriesWithoutUTF8Flag.containsKey(ze)) {
/* 838 */         String orig = ze.getName();
/* 839 */         NameAndComment nc = entriesWithoutUTF8Flag.get(ze);
/* 840 */         ZipUtil.setNameAndCommentFromExtraFields(ze, nc.name, nc.comment);
/* 842 */         if (!orig.equals(ze.getName())) {
/* 843 */           this.nameMap.remove(orig);
/* 844 */           this.nameMap.put(ze.getName(), ze);
/*     */         } 
/*     */       } 
/* 847 */       this.entries.put(ze, offsetEntry);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean startsWithLocalFileHeader() throws IOException {
/* 856 */     this.archive.seek(0L);
/* 857 */     byte[] start = new byte[4];
/* 858 */     this.archive.readFully(start);
/* 859 */     for (int i = 0; i < start.length; i++) {
/* 860 */       if (start[i] != ZipArchiveOutputStream.LFH_SIG[i])
/* 861 */         return false; 
/*     */     } 
/* 864 */     return true;
/*     */   }
/*     */   
/*     */   private class BoundedInputStream extends InputStream {
/*     */     private long remaining;
/*     */     
/*     */     private long loc;
/*     */     
/*     */     private boolean addDummyByte = false;
/*     */     
/*     */     BoundedInputStream(long start, long remaining) {
/* 878 */       this.remaining = remaining;
/* 879 */       this.loc = start;
/*     */     }
/*     */     
/*     */     public int read() throws IOException {
/* 884 */       if (this.remaining-- <= 0L) {
/* 885 */         if (this.addDummyByte) {
/* 886 */           this.addDummyByte = false;
/* 887 */           return 0;
/*     */         } 
/* 889 */         return -1;
/*     */       } 
/* 891 */       synchronized (ZipFile.this.archive) {
/* 892 */         ZipFile.this.archive.seek(this.loc++);
/* 893 */         return ZipFile.this.archive.read();
/*     */       } 
/*     */     }
/*     */     
/*     */     public int read(byte[] b, int off, int len) throws IOException {
/* 899 */       if (this.remaining <= 0L) {
/* 900 */         if (this.addDummyByte) {
/* 901 */           this.addDummyByte = false;
/* 902 */           b[off] = 0;
/* 903 */           return 1;
/*     */         } 
/* 905 */         return -1;
/*     */       } 
/* 908 */       if (len <= 0)
/* 909 */         return 0; 
/* 912 */       if (len > this.remaining)
/* 913 */         len = (int)this.remaining; 
/* 915 */       int ret = -1;
/* 916 */       synchronized (ZipFile.this.archive) {
/* 917 */         ZipFile.this.archive.seek(this.loc);
/* 918 */         ret = ZipFile.this.archive.read(b, off, len);
/*     */       } 
/* 920 */       if (ret > 0) {
/* 921 */         this.loc += ret;
/* 922 */         this.remaining -= ret;
/*     */       } 
/* 924 */       return ret;
/*     */     }
/*     */     
/*     */     void addDummy() {
/* 932 */       this.addDummyByte = true;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class NameAndComment {
/*     */     private final byte[] name;
/*     */     
/*     */     private final byte[] comment;
/*     */     
/*     */     private NameAndComment(byte[] name, byte[] comment) {
/* 940 */       this.name = name;
/* 941 */       this.comment = comment;
/*     */     }
/*     */   }
/*     */   
/*     */   public ZipFile(File f, String encoding, boolean useUnicodeExtraFields) throws IOException {
/* 953 */     this.OFFSET_COMPARATOR = new Comparator<ZipArchiveEntry>() {
/*     */         public int compare(ZipArchiveEntry e1, ZipArchiveEntry e2) {
/* 956 */           if (e1 == e2)
/* 957 */             return 0; 
/* 960 */           ZipFile.OffsetEntry off1 = (ZipFile.OffsetEntry)ZipFile.this.entries.get(e1);
/* 961 */           ZipFile.OffsetEntry off2 = (ZipFile.OffsetEntry)ZipFile.this.entries.get(e2);
/* 962 */           if (off1 == null)
/* 963 */             return 1; 
/* 965 */           if (off2 == null)
/* 966 */             return -1; 
/* 968 */           long val = off1.headerOffset - off2.headerOffset;
/* 969 */           return (val == 0L) ? 0 : ((val < 0L) ? -1 : 1);
/*     */         }
/*     */       };
/*     */     this.archiveName = f.getAbsolutePath();
/*     */     this.encoding = encoding;
/*     */     this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
/*     */     this.useUnicodeExtraFields = useUnicodeExtraFields;
/*     */     this.archive = new RandomAccessFile(f, "r");
/*     */     boolean success = false;
/*     */     try {
/*     */       Map<ZipArchiveEntry, NameAndComment> entriesWithoutUTF8Flag = populateFromCentralDirectory();
/*     */       resolveLocalFileHeaderData(entriesWithoutUTF8Flag);
/*     */       success = true;
/*     */     } finally {
/*     */       if (!success)
/*     */         try {
/*     */           this.closed = true;
/*     */           this.archive.close();
/*     */         } catch (IOException e2) {} 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\zip\ZipFile.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */