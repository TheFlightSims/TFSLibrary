/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ public final class GeneralPurposeBit {
/*     */   private static final int ENCRYPTION_FLAG = 1;
/*     */   
/*     */   private static final int DATA_DESCRIPTOR_FLAG = 8;
/*     */   
/*     */   private static final int STRONG_ENCRYPTION_FLAG = 64;
/*     */   
/*     */   public static final int UFT8_NAMES_FLAG = 2048;
/*     */   
/*     */   private boolean languageEncodingFlag = false;
/*     */   
/*     */   private boolean dataDescriptorFlag = false;
/*     */   
/*     */   private boolean encryptionFlag = false;
/*     */   
/*     */   private boolean strongEncryptionFlag = false;
/*     */   
/*     */   public boolean usesUTF8ForNames() {
/*  64 */     return this.languageEncodingFlag;
/*     */   }
/*     */   
/*     */   public void useUTF8ForNames(boolean b) {
/*  71 */     this.languageEncodingFlag = b;
/*     */   }
/*     */   
/*     */   public boolean usesDataDescriptor() {
/*  79 */     return this.dataDescriptorFlag;
/*     */   }
/*     */   
/*     */   public void useDataDescriptor(boolean b) {
/*  87 */     this.dataDescriptorFlag = b;
/*     */   }
/*     */   
/*     */   public boolean usesEncryption() {
/*  94 */     return this.encryptionFlag;
/*     */   }
/*     */   
/*     */   public void useEncryption(boolean b) {
/* 101 */     this.encryptionFlag = b;
/*     */   }
/*     */   
/*     */   public boolean usesStrongEncryption() {
/* 108 */     return (this.encryptionFlag && this.strongEncryptionFlag);
/*     */   }
/*     */   
/*     */   public void useStrongEncryption(boolean b) {
/* 115 */     this.strongEncryptionFlag = b;
/* 116 */     if (b)
/* 117 */       useEncryption(true); 
/*     */   }
/*     */   
/*     */   public byte[] encode() {
/* 125 */     return ZipShort.getBytes((this.dataDescriptorFlag ? 8 : 0) | (this.languageEncodingFlag ? 2048 : 0) | (this.encryptionFlag ? 1 : 0) | (this.strongEncryptionFlag ? 64 : 0));
/*     */   }
/*     */   
/*     */   public static GeneralPurposeBit parse(byte[] data, int offset) {
/* 142 */     int generalPurposeFlag = ZipShort.getValue(data, offset);
/* 143 */     GeneralPurposeBit b = new GeneralPurposeBit();
/* 144 */     b.useDataDescriptor(((generalPurposeFlag & 0x8) != 0));
/* 145 */     b.useUTF8ForNames(((generalPurposeFlag & 0x800) != 0));
/* 146 */     b.useStrongEncryption(((generalPurposeFlag & 0x40) != 0));
/* 148 */     b.useEncryption(((generalPurposeFlag & 0x1) != 0));
/* 149 */     return b;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 154 */     return 3 * (7 * (13 * (17 * (this.encryptionFlag ? 1 : 0) + (this.strongEncryptionFlag ? 1 : 0)) + (this.languageEncodingFlag ? 1 : 0)) + (this.dataDescriptorFlag ? 1 : 0));
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 162 */     if (!(o instanceof GeneralPurposeBit))
/* 163 */       return false; 
/* 165 */     GeneralPurposeBit g = (GeneralPurposeBit)o;
/* 166 */     return (g.encryptionFlag == this.encryptionFlag && g.strongEncryptionFlag == this.strongEncryptionFlag && g.languageEncodingFlag == this.languageEncodingFlag && g.dataDescriptorFlag == this.dataDescriptorFlag);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\zip\GeneralPurposeBit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */