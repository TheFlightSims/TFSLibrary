/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.zip.CRC32;
/*     */ 
/*     */ public abstract class ZipUtil {
/*  34 */   private static final byte[] DOS_TIME_MIN = ZipLong.getBytes(8448L);
/*     */   
/*     */   public static ZipLong toDosTime(Date time) {
/*  42 */     return new ZipLong(toDosTime(time.getTime()));
/*     */   }
/*     */   
/*     */   public static byte[] toDosTime(long t) {
/*  53 */     Calendar c = Calendar.getInstance();
/*  54 */     c.setTimeInMillis(t);
/*  56 */     int year = c.get(1);
/*  57 */     if (year < 1980)
/*  58 */       return copy(DOS_TIME_MIN); 
/*  60 */     int month = c.get(2) + 1;
/*  61 */     long value = (year - 1980 << 25 | month << 21 | c.get(5) << 16 | c.get(11) << 11 | c.get(12) << 5 | c.get(13) >> 1);
/*  67 */     return ZipLong.getBytes(value);
/*     */   }
/*     */   
/*     */   public static long adjustToLong(int i) {
/*  81 */     if (i < 0)
/*  82 */       return 4294967296L + i; 
/*  84 */     return i;
/*     */   }
/*     */   
/*     */   public static Date fromDosTime(ZipLong zipDosTime) {
/*  95 */     long dosTime = zipDosTime.getValue();
/*  96 */     return new Date(dosToJavaTime(dosTime));
/*     */   }
/*     */   
/*     */   public static long dosToJavaTime(long dosTime) {
/* 104 */     Calendar cal = Calendar.getInstance();
/* 106 */     cal.set(1, (int)(dosTime >> 25L & 0x7FL) + 1980);
/* 107 */     cal.set(2, (int)(dosTime >> 21L & 0xFL) - 1);
/* 108 */     cal.set(5, (int)(dosTime >> 16L) & 0x1F);
/* 109 */     cal.set(11, (int)(dosTime >> 11L) & 0x1F);
/* 110 */     cal.set(12, (int)(dosTime >> 5L) & 0x3F);
/* 111 */     cal.set(13, (int)(dosTime << 1L) & 0x3E);
/* 113 */     return cal.getTime().getTime();
/*     */   }
/*     */   
/*     */   static void setNameAndCommentFromExtraFields(ZipArchiveEntry ze, byte[] originalNameBytes, byte[] commentBytes) {
/* 124 */     UnicodePathExtraField name = (UnicodePathExtraField)ze.getExtraField(UnicodePathExtraField.UPATH_ID);
/* 126 */     String originalName = ze.getName();
/* 127 */     String newName = getUnicodeStringIfOriginalMatches(name, originalNameBytes);
/* 129 */     if (newName != null && !originalName.equals(newName))
/* 130 */       ze.setName(newName); 
/* 133 */     if (commentBytes != null && commentBytes.length > 0) {
/* 134 */       UnicodeCommentExtraField cmt = (UnicodeCommentExtraField)ze.getExtraField(UnicodeCommentExtraField.UCOM_ID);
/* 136 */       String newComment = getUnicodeStringIfOriginalMatches(cmt, commentBytes);
/* 138 */       if (newComment != null)
/* 139 */         ze.setComment(newComment); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String getUnicodeStringIfOriginalMatches(AbstractUnicodeExtraField f, byte[] orig) {
/* 154 */     if (f != null) {
/* 155 */       CRC32 crc32 = new CRC32();
/* 156 */       crc32.update(orig);
/* 157 */       long origCRC32 = crc32.getValue();
/* 159 */       if (origCRC32 == f.getNameCRC32())
/*     */         try {
/* 161 */           return ZipEncodingHelper.UTF8_ZIP_ENCODING.decode(f.getUnicodeName());
/* 163 */         } catch (IOException ex) {
/* 168 */           return null;
/*     */         }  
/*     */     } 
/* 172 */     return null;
/*     */   }
/*     */   
/*     */   static byte[] copy(byte[] from) {
/* 180 */     if (from != null) {
/* 181 */       byte[] to = new byte[from.length];
/* 182 */       System.arraycopy(from, 0, to, 0, to.length);
/* 183 */       return to;
/*     */     } 
/* 185 */     return null;
/*     */   }
/*     */   
/*     */   static boolean canHandleEntryData(ZipArchiveEntry entry) {
/* 192 */     return (supportsEncryptionOf(entry) && supportsMethodOf(entry));
/*     */   }
/*     */   
/*     */   private static boolean supportsEncryptionOf(ZipArchiveEntry entry) {
/* 202 */     return !entry.getGeneralPurposeBit().usesEncryption();
/*     */   }
/*     */   
/*     */   private static boolean supportsMethodOf(ZipArchiveEntry entry) {
/* 212 */     return (entry.getMethod() == 0 || entry.getMethod() == 8);
/*     */   }
/*     */   
/*     */   static void checkRequestedFeatures(ZipArchiveEntry ze) throws UnsupportedZipFeatureException {
/* 222 */     if (!supportsEncryptionOf(ze))
/* 223 */       throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.ENCRYPTION, ze); 
/* 227 */     if (!supportsMethodOf(ze))
/* 228 */       throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.METHOD, ze); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\zip\ZipUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */