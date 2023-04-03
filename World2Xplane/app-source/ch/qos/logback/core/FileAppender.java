/*     */ package ch.qos.logback.core;
/*     */ 
/*     */ import ch.qos.logback.core.recovery.ResilientFileOutputStream;
/*     */ import ch.qos.logback.core.util.FileUtil;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.FileLock;
/*     */ 
/*     */ public class FileAppender<E> extends OutputStreamAppender<E> {
/*     */   protected boolean append = true;
/*     */   
/*  44 */   protected String fileName = null;
/*     */   
/*     */   private boolean prudent = false;
/*     */   
/*     */   public void setFile(String file) {
/*  53 */     if (file == null) {
/*  54 */       this.fileName = file;
/*     */     } else {
/*  58 */       this.fileName = file.trim();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isAppend() {
/*  66 */     return this.append;
/*     */   }
/*     */   
/*     */   public final String rawFileProperty() {
/*  76 */     return this.fileName;
/*     */   }
/*     */   
/*     */   public String getFile() {
/*  87 */     return this.fileName;
/*     */   }
/*     */   
/*     */   public void start() {
/*  96 */     int errors = 0;
/*  97 */     if (getFile() != null) {
/*  98 */       addInfo("File property is set to [" + this.fileName + "]");
/* 100 */       if (this.prudent && 
/* 101 */         !isAppend()) {
/* 102 */         setAppend(true);
/* 103 */         addWarn("Setting \"Append\" property to true on account of \"Prudent\" mode");
/*     */       } 
/*     */       try {
/* 108 */         openFile(getFile());
/* 109 */       } catch (IOException e) {
/* 110 */         errors++;
/* 111 */         addError("openFile(" + this.fileName + "," + this.append + ") call failed.", e);
/*     */       } 
/*     */     } else {
/* 114 */       errors++;
/* 115 */       addError("\"File\" property not set for appender named [" + this.name + "].");
/*     */     } 
/* 117 */     if (errors == 0)
/* 118 */       super.start(); 
/*     */   }
/*     */   
/*     */   public void openFile(String file_name) throws IOException {
/* 139 */     synchronized (this.lock) {
/* 140 */       File file = new File(file_name);
/* 141 */       if (FileUtil.isParentDirectoryCreationRequired(file)) {
/* 142 */         boolean result = FileUtil.createMissingParentDirectories(file);
/* 143 */         if (!result)
/* 144 */           addError("Failed to create parent directories for [" + file.getAbsolutePath() + "]"); 
/*     */       } 
/* 149 */       ResilientFileOutputStream resilientFos = new ResilientFileOutputStream(file, this.append);
/* 151 */       resilientFos.setContext(this.context);
/* 152 */       setOutputStream((OutputStream)resilientFos);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isPrudent() {
/* 162 */     return this.prudent;
/*     */   }
/*     */   
/*     */   public void setPrudent(boolean prudent) {
/* 172 */     this.prudent = prudent;
/*     */   }
/*     */   
/*     */   public void setAppend(boolean append) {
/* 176 */     this.append = append;
/*     */   }
/*     */   
/*     */   private void safeWrite(E event) throws IOException {
/* 180 */     ResilientFileOutputStream resilientFOS = (ResilientFileOutputStream)getOutputStream();
/* 181 */     FileChannel fileChannel = resilientFOS.getChannel();
/* 182 */     if (fileChannel == null)
/*     */       return; 
/* 185 */     FileLock fileLock = null;
/*     */     try {
/* 187 */       fileLock = fileChannel.lock();
/* 188 */       long position = fileChannel.position();
/* 189 */       long size = fileChannel.size();
/* 190 */       if (size != position)
/* 191 */         fileChannel.position(size); 
/* 193 */       super.writeOut(event);
/*     */     } finally {
/* 195 */       if (fileLock != null)
/* 196 */         fileLock.release(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void writeOut(E event) throws IOException {
/* 203 */     if (this.prudent) {
/* 204 */       safeWrite(event);
/*     */     } else {
/* 206 */       super.writeOut(event);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\FileAppender.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */