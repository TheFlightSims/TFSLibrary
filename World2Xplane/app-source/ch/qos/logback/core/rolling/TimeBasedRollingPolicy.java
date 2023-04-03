/*     */ package ch.qos.logback.core.rolling;
/*     */ 
/*     */ import ch.qos.logback.core.rolling.helper.ArchiveRemover;
/*     */ import ch.qos.logback.core.rolling.helper.AsynchronousCompressor;
/*     */ import ch.qos.logback.core.rolling.helper.CompressionMode;
/*     */ import ch.qos.logback.core.rolling.helper.Compressor;
/*     */ import ch.qos.logback.core.rolling.helper.FileFilterUtil;
/*     */ import ch.qos.logback.core.rolling.helper.FileNamePattern;
/*     */ import ch.qos.logback.core.rolling.helper.RenameUtil;
/*     */ import java.io.File;
/*     */ import java.util.Date;
/*     */ import java.util.concurrent.Future;
/*     */ 
/*     */ public class TimeBasedRollingPolicy<E> extends RollingPolicyBase implements TriggeringPolicy<E> {
/*     */   static final String FNP_NOT_SET = "The FileNamePattern option must be set before using TimeBasedRollingPolicy. ";
/*     */   
/*     */   static final int INFINITE_HISTORY = 0;
/*     */   
/*     */   FileNamePattern fileNamePatternWCS;
/*     */   
/*     */   private Compressor compressor;
/*     */   
/*  45 */   private RenameUtil renameUtil = new RenameUtil();
/*     */   
/*     */   Future<?> future;
/*     */   
/*  48 */   private int maxHistory = 0;
/*     */   
/*     */   private ArchiveRemover archiveRemover;
/*     */   
/*     */   TimeBasedFileNamingAndTriggeringPolicy<E> timeBasedFileNamingAndTriggeringPolicy;
/*     */   
/*     */   boolean cleanHistoryOnStart = false;
/*     */   
/*     */   public void start() {
/*  57 */     this.renameUtil.setContext(this.context);
/*  60 */     if (this.fileNamePatternStr != null) {
/*  61 */       this.fileNamePattern = new FileNamePattern(this.fileNamePatternStr, this.context);
/*  62 */       determineCompressionMode();
/*     */     } else {
/*  64 */       addWarn("The FileNamePattern option must be set before using TimeBasedRollingPolicy. ");
/*  65 */       addWarn("See also http://logback.qos.ch/codes.html#tbr_fnp_not_set");
/*  66 */       throw new IllegalStateException("The FileNamePattern option must be set before using TimeBasedRollingPolicy. See also http://logback.qos.ch/codes.html#tbr_fnp_not_set");
/*     */     } 
/*  70 */     this.compressor = new Compressor(this.compressionMode);
/*  71 */     this.compressor.setContext(this.context);
/*  74 */     this.fileNamePatternWCS = new FileNamePattern(Compressor.computeFileNameStr_WCS(this.fileNamePatternStr, this.compressionMode), this.context);
/*  77 */     addInfo("Will use the pattern " + this.fileNamePatternWCS + " for the active file");
/*  80 */     if (this.compressionMode == CompressionMode.ZIP) {
/*  81 */       String zipEntryFileNamePatternStr = transformFileNamePattern2ZipEntry(this.fileNamePatternStr);
/*  82 */       this.zipEntryFileNamePattern = new FileNamePattern(zipEntryFileNamePatternStr, this.context);
/*     */     } 
/*  85 */     if (this.timeBasedFileNamingAndTriggeringPolicy == null)
/*  86 */       this.timeBasedFileNamingAndTriggeringPolicy = new DefaultTimeBasedFileNamingAndTriggeringPolicy<E>(); 
/*  88 */     this.timeBasedFileNamingAndTriggeringPolicy.setContext(this.context);
/*  89 */     this.timeBasedFileNamingAndTriggeringPolicy.setTimeBasedRollingPolicy(this);
/*  90 */     this.timeBasedFileNamingAndTriggeringPolicy.start();
/*  95 */     if (this.maxHistory != 0) {
/*  96 */       this.archiveRemover = this.timeBasedFileNamingAndTriggeringPolicy.getArchiveRemover();
/*  97 */       this.archiveRemover.setMaxHistory(this.maxHistory);
/*  98 */       if (this.cleanHistoryOnStart) {
/*  99 */         addInfo("Cleaning on start up");
/* 100 */         this.archiveRemover.clean(new Date(this.timeBasedFileNamingAndTriggeringPolicy.getCurrentTime()));
/*     */       } 
/*     */     } 
/* 104 */     super.start();
/*     */   }
/*     */   
/*     */   public void stop() {
/* 109 */     if (!isStarted())
/*     */       return; 
/* 111 */     waitForAsynchronousJobToStop();
/* 112 */     super.stop();
/*     */   }
/*     */   
/*     */   private void waitForAsynchronousJobToStop() {}
/*     */   
/*     */   private String transformFileNamePattern2ZipEntry(String fileNamePatternStr) {
/* 129 */     String slashified = FileFilterUtil.slashify(fileNamePatternStr);
/* 130 */     return FileFilterUtil.afterLastSlash(slashified);
/*     */   }
/*     */   
/*     */   public void setTimeBasedFileNamingAndTriggeringPolicy(TimeBasedFileNamingAndTriggeringPolicy<E> timeBasedTriggering) {
/* 135 */     this.timeBasedFileNamingAndTriggeringPolicy = timeBasedTriggering;
/*     */   }
/*     */   
/*     */   public TimeBasedFileNamingAndTriggeringPolicy<E> getTimeBasedFileNamingAndTriggeringPolicy() {
/* 139 */     return this.timeBasedFileNamingAndTriggeringPolicy;
/*     */   }
/*     */   
/*     */   public void rollover() throws RolloverFailure {
/* 147 */     String elapsedPeriodsFileName = this.timeBasedFileNamingAndTriggeringPolicy.getElapsedPeriodsFileName();
/* 150 */     String elapsedPeriodStem = FileFilterUtil.afterLastSlash(elapsedPeriodsFileName);
/* 152 */     if (this.compressionMode == CompressionMode.NONE) {
/* 153 */       if (getParentsRawFileProperty() != null)
/* 154 */         this.renameUtil.rename(getParentsRawFileProperty(), elapsedPeriodsFileName); 
/* 157 */     } else if (getParentsRawFileProperty() == null) {
/* 158 */       this.future = asyncCompress(elapsedPeriodsFileName, elapsedPeriodsFileName, elapsedPeriodStem);
/*     */     } else {
/* 160 */       this.future = renamedRawAndAsyncCompress(elapsedPeriodsFileName, elapsedPeriodStem);
/*     */     } 
/* 164 */     if (this.archiveRemover != null)
/* 165 */       this.archiveRemover.clean(new Date(this.timeBasedFileNamingAndTriggeringPolicy.getCurrentTime())); 
/*     */   }
/*     */   
/*     */   Future asyncCompress(String nameOfFile2Compress, String nameOfCompressedFile, String innerEntryName) throws RolloverFailure {
/* 171 */     AsynchronousCompressor ac = new AsynchronousCompressor(this.compressor);
/* 172 */     return ac.compressAsynchronously(nameOfFile2Compress, nameOfCompressedFile, innerEntryName);
/*     */   }
/*     */   
/*     */   Future renamedRawAndAsyncCompress(String nameOfCompressedFile, String innerEntryName) throws RolloverFailure {
/* 177 */     String parentsRawFile = getParentsRawFileProperty();
/* 178 */     String tmpTarget = parentsRawFile + System.nanoTime() + ".tmp";
/* 179 */     this.renameUtil.rename(parentsRawFile, tmpTarget);
/* 180 */     return asyncCompress(tmpTarget, nameOfCompressedFile, innerEntryName);
/*     */   }
/*     */   
/*     */   public String getActiveFileName() {
/* 204 */     String parentsRawFileProperty = getParentsRawFileProperty();
/* 205 */     if (parentsRawFileProperty != null)
/* 206 */       return parentsRawFileProperty; 
/* 208 */     return this.timeBasedFileNamingAndTriggeringPolicy.getCurrentPeriodsFileNameWithoutCompressionSuffix();
/*     */   }
/*     */   
/*     */   public boolean isTriggeringEvent(File activeFile, E event) {
/* 214 */     return this.timeBasedFileNamingAndTriggeringPolicy.isTriggeringEvent(activeFile, event);
/*     */   }
/*     */   
/*     */   public int getMaxHistory() {
/* 223 */     return this.maxHistory;
/*     */   }
/*     */   
/*     */   public void setMaxHistory(int maxHistory) {
/* 233 */     this.maxHistory = maxHistory;
/*     */   }
/*     */   
/*     */   public boolean isCleanHistoryOnStart() {
/* 238 */     return this.cleanHistoryOnStart;
/*     */   }
/*     */   
/*     */   public void setCleanHistoryOnStart(boolean cleanHistoryOnStart) {
/* 247 */     this.cleanHistoryOnStart = cleanHistoryOnStart;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 253 */     return "c.q.l.core.rolling.TimeBasedRollingPolicy";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\rolling\TimeBasedRollingPolicy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */