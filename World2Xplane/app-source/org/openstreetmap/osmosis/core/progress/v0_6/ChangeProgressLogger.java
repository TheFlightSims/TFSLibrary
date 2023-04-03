/*     */ package org.openstreetmap.osmosis.core.progress.v0_6;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.logging.Logger;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.ChangeContainer;
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
/*     */ import org.openstreetmap.osmosis.core.progress.v0_6.impl.ProgressTracker;
/*     */ import org.openstreetmap.osmosis.core.task.common.ChangeAction;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSink;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSinkChangeSource;
/*     */ 
/*     */ public class ChangeProgressLogger implements ChangeSinkChangeSource {
/*  22 */   private static final Logger LOG = Logger.getLogger(ChangeProgressLogger.class.getName());
/*     */   
/*     */   private ChangeSink changeSink;
/*     */   
/*     */   private ProgressTracker progressTracker;
/*     */   
/*     */   private String prefix;
/*     */   
/*     */   public ChangeProgressLogger(int interval, String label) {
/*  39 */     this.progressTracker = new ProgressTracker(interval);
/*  41 */     if (label != null && !label.equals("")) {
/*  42 */       this.prefix = "[" + label + "] ";
/*     */     } else {
/*  44 */       this.prefix = "";
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initialize(Map<String, Object> metaData) {
/*  53 */     this.progressTracker.initialize();
/*  54 */     this.changeSink.initialize(metaData);
/*     */   }
/*     */   
/*     */   public void process(ChangeContainer changeContainer) {
/*  65 */     Entity entity = changeContainer.getEntityContainer().getEntity();
/*  66 */     ChangeAction action = changeContainer.getAction();
/*  68 */     if (this.progressTracker.updateRequired())
/*  69 */       LOG.info(this.prefix + "Processing " + entity.getType() + " " + entity.getId() + " with action " + action + ", " + this.progressTracker.getObjectsPerSecond() + " objects/second."); 
/*  75 */     this.changeSink.process(changeContainer);
/*     */   }
/*     */   
/*     */   public void complete() {
/*  83 */     LOG.info("Processing completion steps.");
/*  85 */     long start = System.currentTimeMillis();
/*  86 */     this.changeSink.complete();
/*  87 */     long duration = System.currentTimeMillis() - start;
/*  89 */     LOG.info("Completion steps took " + (duration / 1000.0D) + " seconds.");
/*  90 */     LOG.info("Processing complete.");
/*     */   }
/*     */   
/*     */   public void release() {
/*  98 */     this.changeSink.release();
/*     */   }
/*     */   
/*     */   public void setChangeSink(ChangeSink changeSink) {
/* 106 */     this.changeSink = changeSink;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\progress\v0_6\ChangeProgressLogger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */