/*     */ package ch.qos.logback.core;
/*     */ 
/*     */ import ch.qos.logback.core.encoder.Encoder;
/*     */ import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
/*     */ import ch.qos.logback.core.spi.DeferredProcessingAware;
/*     */ import ch.qos.logback.core.spi.LogbackLock;
/*     */ import ch.qos.logback.core.status.ErrorStatus;
/*     */ import ch.qos.logback.core.status.Status;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ public class OutputStreamAppender<E> extends UnsynchronizedAppenderBase<E> {
/*     */   protected Encoder<E> encoder;
/*     */   
/*  48 */   protected LogbackLock lock = new LogbackLock();
/*     */   
/*     */   private OutputStream outputStream;
/*     */   
/*     */   public OutputStream getOutputStream() {
/*  61 */     return this.outputStream;
/*     */   }
/*     */   
/*     */   public void start() {
/*  69 */     int errors = 0;
/*  70 */     if (this.encoder == null) {
/*  71 */       addStatus((Status)new ErrorStatus("No encoder set for the appender named \"" + this.name + "\".", this));
/*  73 */       errors++;
/*     */     } 
/*  76 */     if (this.outputStream == null) {
/*  77 */       addStatus((Status)new ErrorStatus("No output stream set for the appender named \"" + this.name + "\".", this));
/*  79 */       errors++;
/*     */     } 
/*  82 */     if (errors == 0)
/*  83 */       super.start(); 
/*     */   }
/*     */   
/*     */   public void setLayout(Layout<E> layout) {
/*  88 */     addWarn("This appender no longer admits a layout as a sub-component, set an encoder instead.");
/*  89 */     addWarn("To ensure compatibility, wrapping your layout in LayoutWrappingEncoder.");
/*  90 */     addWarn("See also http://logback.qos.ch/codes.html#layoutInsteadOfEncoder for details");
/*  91 */     LayoutWrappingEncoder<E> lwe = new LayoutWrappingEncoder();
/*  92 */     lwe.setLayout(layout);
/*  93 */     lwe.setContext(this.context);
/*  94 */     this.encoder = (Encoder<E>)lwe;
/*     */   }
/*     */   
/*     */   protected void append(E eventObject) {
/*  99 */     if (!isStarted())
/*     */       return; 
/* 103 */     subAppend(eventObject);
/*     */   }
/*     */   
/*     */   public void stop() {
/* 114 */     synchronized (this.lock) {
/* 115 */       closeOutputStream();
/* 116 */       super.stop();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void closeOutputStream() {
/* 124 */     if (this.outputStream != null)
/*     */       try {
/* 127 */         encoderClose();
/* 128 */         this.outputStream.close();
/* 129 */         this.outputStream = null;
/* 130 */       } catch (IOException e) {
/* 131 */         addStatus((Status)new ErrorStatus("Could not close output stream for OutputStreamAppender.", this, e));
/*     */       }  
/*     */   }
/*     */   
/*     */   void encoderInit() {
/* 138 */     if (this.encoder != null && this.outputStream != null)
/*     */       try {
/* 140 */         this.encoder.init(this.outputStream);
/* 141 */       } catch (IOException ioe) {
/* 142 */         this.started = false;
/* 143 */         addStatus((Status)new ErrorStatus("Failed to initialize encoder for appender named [" + this.name + "].", this, ioe));
/*     */       }  
/*     */   }
/*     */   
/*     */   void encoderClose() {
/* 151 */     if (this.encoder != null && this.outputStream != null)
/*     */       try {
/* 153 */         this.encoder.close();
/* 154 */       } catch (IOException ioe) {
/* 155 */         this.started = false;
/* 156 */         addStatus((Status)new ErrorStatus("Failed to write footer for appender named [" + this.name + "].", this, ioe));
/*     */       }  
/*     */   }
/*     */   
/*     */   public void setOutputStream(OutputStream outputStream) {
/* 173 */     synchronized (this.lock) {
/* 175 */       closeOutputStream();
/* 177 */       this.outputStream = outputStream;
/* 178 */       if (this.encoder == null) {
/* 179 */         addWarn("Encoder has not been set. Cannot invoke its init method.");
/*     */         return;
/*     */       } 
/* 183 */       encoderInit();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void writeOut(E event) throws IOException {
/* 188 */     this.encoder.doEncode(event);
/*     */   }
/*     */   
/*     */   protected void subAppend(E event) {
/* 200 */     if (!isStarted())
/*     */       return; 
/*     */     try {
/* 205 */       if (event instanceof DeferredProcessingAware)
/* 206 */         ((DeferredProcessingAware)event).prepareForDeferredProcessing(); 
/* 211 */       synchronized (this.lock) {
/* 212 */         writeOut(event);
/*     */       } 
/* 214 */     } catch (IOException ioe) {
/* 217 */       this.started = false;
/* 218 */       addStatus((Status)new ErrorStatus("IO failure in appender", this, ioe));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Encoder<E> getEncoder() {
/* 223 */     return this.encoder;
/*     */   }
/*     */   
/*     */   public void setEncoder(Encoder<E> encoder) {
/* 227 */     this.encoder = encoder;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\OutputStreamAppender.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */