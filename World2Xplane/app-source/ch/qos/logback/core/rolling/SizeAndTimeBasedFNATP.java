/*     */ package ch.qos.logback.core.rolling;
/*     */ 
/*     */ import ch.qos.logback.core.joran.spi.NoAutoStart;
/*     */ import ch.qos.logback.core.rolling.helper.ArchiveRemover;
/*     */ import ch.qos.logback.core.rolling.helper.CompressionMode;
/*     */ import ch.qos.logback.core.rolling.helper.FileFilterUtil;
/*     */ import ch.qos.logback.core.rolling.helper.SizeAndTimeBasedArchiveRemover;
/*     */ import ch.qos.logback.core.util.FileSize;
/*     */ import java.io.File;
/*     */ import java.util.Date;
/*     */ 
/*     */ @NoAutoStart
/*     */ public class SizeAndTimeBasedFNATP<E> extends TimeBasedFileNamingAndTriggeringPolicyBase<E> {
/*  29 */   int currentPeriodsCounter = 0;
/*     */   
/*     */   FileSize maxFileSize;
/*     */   
/*     */   String maxFileSizeAsString;
/*     */   
/*     */   private int invocationCounter;
/*     */   
/*     */   public void start() {
/*  37 */     super.start();
/*  39 */     this.archiveRemover = (ArchiveRemover)new SizeAndTimeBasedArchiveRemover(this.tbrp.fileNamePattern, this.rc);
/*  40 */     this.archiveRemover.setContext(this.context);
/*  45 */     String regex = this.tbrp.fileNamePattern.toRegexForFixedDate(this.dateInCurrentPeriod);
/*  46 */     String stemRegex = FileFilterUtil.afterLastSlash(regex);
/*  49 */     computeCurrentPeriodsHighestCounterValue(stemRegex);
/*  51 */     this.started = true;
/*     */   }
/*     */   
/*     */   void computeCurrentPeriodsHighestCounterValue(String stemRegex) {
/*  55 */     File file = new File(getCurrentPeriodsFileNameWithoutCompressionSuffix());
/*  56 */     File parentDir = file.getParentFile();
/*  58 */     File[] matchingFileArray = FileFilterUtil.filesInFolderMatchingStemRegex(parentDir, stemRegex);
/*  61 */     if (matchingFileArray == null || matchingFileArray.length == 0) {
/*  62 */       this.currentPeriodsCounter = 0;
/*     */       return;
/*     */     } 
/*  65 */     this.currentPeriodsCounter = FileFilterUtil.findHighestCounter(matchingFileArray, stemRegex);
/*  69 */     if (this.tbrp.getParentsRawFileProperty() != null || this.tbrp.compressionMode != CompressionMode.NONE)
/*  71 */       this.currentPeriodsCounter++; 
/*     */   }
/*     */   
/*  80 */   private int invocationMask = 1;
/*     */   
/*     */   public boolean isTriggeringEvent(File activeFile, E event) {
/*  84 */     long time = getCurrentTime();
/*  85 */     if (time >= this.nextCheck) {
/*  86 */       Date dateInElapsedPeriod = this.dateInCurrentPeriod;
/*  87 */       this.elapsedPeriodsFileName = this.tbrp.fileNamePatternWCS.convertMultipleArguments(new Object[] { dateInElapsedPeriod, Integer.valueOf(this.currentPeriodsCounter) });
/*  89 */       this.currentPeriodsCounter = 0;
/*  90 */       setDateInCurrentPeriod(time);
/*  91 */       computeNextCheck();
/*  92 */       return true;
/*     */     } 
/*  96 */     if ((++this.invocationCounter & this.invocationMask) != this.invocationMask)
/*  97 */       return false; 
/*  99 */     if (this.invocationMask < 15)
/* 100 */       this.invocationMask = (this.invocationMask << 1) + 1; 
/* 103 */     if (activeFile.length() >= this.maxFileSize.getSize()) {
/* 104 */       this.elapsedPeriodsFileName = this.tbrp.fileNamePatternWCS.convertMultipleArguments(new Object[] { this.dateInCurrentPeriod, Integer.valueOf(this.currentPeriodsCounter) });
/* 106 */       this.currentPeriodsCounter++;
/* 107 */       return true;
/*     */     } 
/* 110 */     return false;
/*     */   }
/*     */   
/*     */   private String getFileNameIncludingCompressionSuffix(Date date, int counter) {
/* 114 */     return this.tbrp.fileNamePattern.convertMultipleArguments(new Object[] { this.dateInCurrentPeriod, Integer.valueOf(counter) });
/*     */   }
/*     */   
/*     */   public String getCurrentPeriodsFileNameWithoutCompressionSuffix() {
/* 121 */     return this.tbrp.fileNamePatternWCS.convertMultipleArguments(new Object[] { this.dateInCurrentPeriod, Integer.valueOf(this.currentPeriodsCounter) });
/*     */   }
/*     */   
/*     */   public String getMaxFileSize() {
/* 126 */     return this.maxFileSizeAsString;
/*     */   }
/*     */   
/*     */   public void setMaxFileSize(String maxFileSize) {
/* 130 */     this.maxFileSizeAsString = maxFileSize;
/* 131 */     this.maxFileSize = FileSize.valueOf(maxFileSize);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\rolling\SizeAndTimeBasedFNATP.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */