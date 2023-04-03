/*     */ package org.openstreetmap.osmosis.core.progress.v0_6;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.logging.Logger;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
/*     */ import org.openstreetmap.osmosis.core.progress.v0_6.impl.ProgressTracker;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.SinkSource;
/*     */ 
/*     */ public class EntityProgressLogger implements SinkSource {
/*  21 */   private static final Logger LOG = Logger.getLogger(EntityProgressLogger.class.getName());
/*     */   
/*     */   private Sink sink;
/*     */   
/*     */   private ProgressTracker progressTracker;
/*     */   
/*     */   private String prefix;
/*     */   
/*     */   public EntityProgressLogger(int interval, String label) {
/*  37 */     this.progressTracker = new ProgressTracker(interval);
/*  39 */     if (label != null && !label.equals("")) {
/*  40 */       this.prefix = "[" + label + "] ";
/*     */     } else {
/*  42 */       this.prefix = "";
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initialize(Map<String, Object> metaData) {
/*  51 */     this.progressTracker.initialize();
/*  52 */     this.sink.initialize(metaData);
/*     */   }
/*     */   
/*     */   public void process(EntityContainer entityContainer) {
/*  62 */     Entity entity = entityContainer.getEntity();
/*  64 */     if (this.progressTracker.updateRequired())
/*  65 */       LOG.info(this.prefix + "Processing " + entity.getType() + " " + entity.getId() + ", " + this.progressTracker.getObjectsPerSecond() + " objects/second."); 
/*  71 */     this.sink.process(entityContainer);
/*     */   }
/*     */   
/*     */   public void complete() {
/*  79 */     LOG.info("Processing completion steps.");
/*  81 */     long start = System.currentTimeMillis();
/*  82 */     this.sink.complete();
/*  83 */     long duration = System.currentTimeMillis() - start;
/*  85 */     LOG.info("Completion steps took " + (duration / 1000.0D) + " seconds.");
/*  86 */     LOG.info("Processing complete.");
/*     */   }
/*     */   
/*     */   public void release() {
/*  94 */     this.sink.release();
/*     */   }
/*     */   
/*     */   public void setSink(Sink sink) {
/* 102 */     this.sink = sink;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\progress\v0_6\EntityProgressLogger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */