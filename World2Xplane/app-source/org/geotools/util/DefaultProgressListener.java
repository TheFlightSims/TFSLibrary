/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.Queue;
/*     */ import org.opengis.util.InternationalString;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ public class DefaultProgressListener extends NullProgressListener implements ProgressListener, ProgressListener {
/*     */   public String toString() {
/*  29 */     return "DefaultProgressListener [completed=" + this.completed + ", progress=" + this.progress + ", started=" + this.started + ", task=" + this.task + "]";
/*     */   }
/*     */   
/*     */   public static class Warning {
/*     */     private String source;
/*     */     
/*     */     private String margin;
/*     */     
/*     */     private String warning;
/*     */     
/*     */     public String toString() {
/*  43 */       return "Warning [margin=" + this.margin + ", source=" + this.source + ", warning=" + this.warning + "]";
/*     */     }
/*     */     
/*     */     public String getSource() {
/*  47 */       return this.source;
/*     */     }
/*     */     
/*     */     public void setSource(String source) {
/*  50 */       this.source = source;
/*     */     }
/*     */     
/*     */     public String getMargin() {
/*  53 */       return this.margin;
/*     */     }
/*     */     
/*     */     public void setMargin(String margin) {
/*  56 */       this.margin = margin;
/*     */     }
/*     */     
/*     */     public String getWarning() {
/*  59 */       return this.warning;
/*     */     }
/*     */     
/*     */     public void setWarning(String warning) {
/*  62 */       this.warning = warning;
/*     */     }
/*     */   }
/*     */   
/*  70 */   private final Queue<Warning> warnings = new LinkedList<Warning>();
/*     */   
/*  73 */   private final Queue<Throwable> exceptionQueue = new LinkedList<Throwable>();
/*     */   
/*     */   private boolean completed;
/*     */   
/*     */   private float progress;
/*     */   
/*     */   private InternationalString task;
/*     */   
/*     */   private boolean started;
/*     */   
/*     */   public void complete() {
/*  91 */     this.completed = true;
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 100 */     this.exceptionQueue.clear();
/* 101 */     this.warnings.clear();
/*     */   }
/*     */   
/*     */   public void exceptionOccurred(Throwable exception) {
/* 108 */     this.exceptionQueue.add(exception);
/*     */   }
/*     */   
/*     */   public void progress(float percent) {
/* 117 */     this.progress = percent;
/*     */   }
/*     */   
/*     */   public void started() {
/* 126 */     this.started = true;
/*     */   }
/*     */   
/*     */   public void warningOccurred(String source, String margin, String warning) {
/* 134 */     Warning w = new Warning();
/* 135 */     w.setMargin(margin);
/* 136 */     w.setSource(source);
/* 137 */     w.setWarning(warning);
/* 138 */     this.warnings.add(w);
/*     */   }
/*     */   
/*     */   public float getProgress() {
/* 145 */     return this.progress;
/*     */   }
/*     */   
/*     */   public InternationalString getTask() {
/* 152 */     return this.task;
/*     */   }
/*     */   
/*     */   public void setTask(InternationalString task) {
/* 159 */     this.task = task;
/*     */   }
/*     */   
/*     */   public boolean isCompleted() {
/* 169 */     return this.completed;
/*     */   }
/*     */   
/*     */   public Queue<Throwable> getExceptions() {
/* 178 */     return new LinkedList<Throwable>(this.exceptionQueue);
/*     */   }
/*     */   
/*     */   public boolean hasExceptions() {
/* 187 */     return (this.exceptionQueue.size() > 0);
/*     */   }
/*     */   
/*     */   public boolean isStarted() {
/* 197 */     return this.started;
/*     */   }
/*     */   
/*     */   public Queue<Warning> getWarnings() {
/* 206 */     return new LinkedList<Warning>(this.warnings);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\DefaultProgressListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */