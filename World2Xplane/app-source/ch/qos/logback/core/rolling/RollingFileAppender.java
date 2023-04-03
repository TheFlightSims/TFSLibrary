/*     */ package ch.qos.logback.core.rolling;
/*     */ 
/*     */ import ch.qos.logback.core.FileAppender;
/*     */ import ch.qos.logback.core.rolling.helper.CompressionMode;
/*     */ import ch.qos.logback.core.rolling.helper.FileNamePattern;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ 
/*     */ public class RollingFileAppender<E> extends FileAppender<E> {
/*     */   File currentlyActiveFile;
/*     */   
/*     */   TriggeringPolicy<E> triggeringPolicy;
/*     */   
/*     */   RollingPolicy rollingPolicy;
/*     */   
/*  41 */   private static String RFA_NO_TP_URL = "http://logback.qos.ch/codes.html#rfa_no_tp";
/*     */   
/*  42 */   private static String RFA_NO_RP_URL = "http://logback.qos.ch/codes.html#rfa_no_rp";
/*     */   
/*  43 */   private static String COLLISION_URL = "http://logback.qos.ch/codes.html#rfa_collision";
/*     */   
/*     */   public void start() {
/*  46 */     if (this.triggeringPolicy == null) {
/*  47 */       addWarn("No TriggeringPolicy was set for the RollingFileAppender named " + getName());
/*  49 */       addWarn("For more information, please visit " + RFA_NO_TP_URL);
/*     */       return;
/*     */     } 
/*  54 */     if (!this.append) {
/*  55 */       addWarn("Append mode is mandatory for RollingFileAppender");
/*  56 */       this.append = true;
/*     */     } 
/*  59 */     if (this.rollingPolicy == null) {
/*  60 */       addError("No RollingPolicy was set for the RollingFileAppender named " + getName());
/*  62 */       addError("For more information, please visit " + RFA_NO_RP_URL);
/*     */       return;
/*     */     } 
/*  67 */     if (fileAndPatternCollide()) {
/*  68 */       addError("File property collides with fileNamePattern. Aborting.");
/*  69 */       addError("For more information, please visit " + COLLISION_URL);
/*     */       return;
/*     */     } 
/*  73 */     if (isPrudent()) {
/*  74 */       if (rawFileProperty() != null) {
/*  75 */         addWarn("Setting \"File\" property to null on account of prudent mode");
/*  76 */         setFile((String)null);
/*     */       } 
/*  78 */       if (this.rollingPolicy.getCompressionMode() != CompressionMode.NONE) {
/*  79 */         addError("Compression is not supported in prudent mode. Aborting");
/*     */         return;
/*     */       } 
/*     */     } 
/*  84 */     this.currentlyActiveFile = new File(getFile());
/*  85 */     addInfo("Active log file name: " + getFile());
/*  86 */     super.start();
/*     */   }
/*     */   
/*     */   private boolean fileAndPatternCollide() {
/*  90 */     if (this.triggeringPolicy instanceof RollingPolicyBase) {
/*  91 */       RollingPolicyBase base = (RollingPolicyBase)this.triggeringPolicy;
/*  92 */       FileNamePattern fileNamePattern = base.fileNamePattern;
/*  94 */       if (fileNamePattern != null && this.fileName != null) {
/*  95 */         String regex = fileNamePattern.toRegex();
/*  96 */         return this.fileName.matches(regex);
/*     */       } 
/*     */     } 
/*  99 */     return false;
/*     */   }
/*     */   
/*     */   public void stop() {
/* 104 */     if (this.rollingPolicy != null)
/* 104 */       this.rollingPolicy.stop(); 
/* 105 */     if (this.triggeringPolicy != null)
/* 105 */       this.triggeringPolicy.stop(); 
/* 106 */     super.stop();
/*     */   }
/*     */   
/*     */   public void setFile(String file) {
/* 113 */     if (file != null && (this.triggeringPolicy != null || this.rollingPolicy != null)) {
/* 114 */       addError("File property must be set before any triggeringPolicy or rollingPolicy properties");
/* 115 */       addError("Visit http://logback.qos.ch/codes.html#rfa_file_after for more information");
/*     */     } 
/* 117 */     super.setFile(file);
/*     */   }
/*     */   
/*     */   public String getFile() {
/* 122 */     return this.rollingPolicy.getActiveFileName();
/*     */   }
/*     */   
/*     */   public void rollover() {
/* 129 */     synchronized (this.lock) {
/* 135 */       closeOutputStream();
/*     */       try {
/* 138 */         this.rollingPolicy.rollover();
/* 139 */       } catch (RolloverFailure rf) {
/* 140 */         addWarn("RolloverFailure occurred. Deferring roll-over.");
/* 142 */         this.append = true;
/*     */       } 
/*     */       try {
/* 148 */         this.currentlyActiveFile = new File(this.rollingPolicy.getActiveFileName());
/* 152 */         openFile(this.rollingPolicy.getActiveFileName());
/* 153 */       } catch (IOException e) {
/* 154 */         addError("setFile(" + this.fileName + ", false) call failed.", e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void subAppend(E event) {
/* 169 */     synchronized (this.triggeringPolicy) {
/* 170 */       if (this.triggeringPolicy.isTriggeringEvent(this.currentlyActiveFile, event))
/* 171 */         rollover(); 
/*     */     } 
/* 175 */     super.subAppend(event);
/*     */   }
/*     */   
/*     */   public RollingPolicy getRollingPolicy() {
/* 179 */     return this.rollingPolicy;
/*     */   }
/*     */   
/*     */   public TriggeringPolicy<E> getTriggeringPolicy() {
/* 183 */     return this.triggeringPolicy;
/*     */   }
/*     */   
/*     */   public void setRollingPolicy(RollingPolicy policy) {
/* 195 */     this.rollingPolicy = policy;
/* 196 */     if (this.rollingPolicy instanceof TriggeringPolicy)
/* 197 */       this.triggeringPolicy = (TriggeringPolicy<E>)policy; 
/*     */   }
/*     */   
/*     */   public void setTriggeringPolicy(TriggeringPolicy<E> policy) {
/* 203 */     this.triggeringPolicy = policy;
/* 204 */     if (policy instanceof RollingPolicy)
/* 205 */       this.rollingPolicy = (RollingPolicy)policy; 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\rolling\RollingFileAppender.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */