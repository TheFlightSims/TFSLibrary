/*     */ package org.openstreetmap.osmosis.core.bound.v0_6;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.BoundContainer;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.EntityProcessor;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.NodeContainer;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.RelationContainer;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.WayContainer;
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.Bound;
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.Node;
/*     */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*     */ import org.openstreetmap.osmosis.core.store.GenericObjectSerializationFactory;
/*     */ import org.openstreetmap.osmosis.core.store.ObjectSerializationFactory;
/*     */ import org.openstreetmap.osmosis.core.store.SimpleObjectStore;
/*     */ import org.openstreetmap.osmosis.core.store.Storeable;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.SinkSource;
/*     */ 
/*     */ public class BoundComputer implements SinkSource, EntityProcessor {
/*     */   private Sink sink;
/*     */   
/*     */   private SimpleObjectStore<EntityContainer> objects;
/*     */   
/*     */   private double top;
/*     */   
/*     */   private double bottom;
/*     */   
/*     */   private double left;
/*     */   
/*     */   private double right;
/*     */   
/*     */   private boolean nodesSeen;
/*     */   
/*     */   private String origin;
/*     */   
/*     */   public BoundComputer(String origin) {
/*  55 */     this.objects = new SimpleObjectStore((ObjectSerializationFactory)new GenericObjectSerializationFactory(), "cbbo", true);
/*  56 */     this.bottom = 0.0D;
/*  57 */     this.top = 0.0D;
/*  58 */     this.left = 0.0D;
/*  59 */     this.right = 0.0D;
/*  60 */     this.nodesSeen = false;
/*  61 */     this.origin = origin;
/*     */   }
/*     */   
/*     */   public void initialize(Map<String, Object> metaTags) {
/*  67 */     this.sink.initialize(metaTags);
/*     */   }
/*     */   
/*     */   public void process(EntityContainer entityContainer) {
/*  73 */     entityContainer.process(this);
/*     */   }
/*     */   
/*     */   public void complete() {
/*  79 */     this.objects.complete();
/*  81 */     if (this.nodesSeen)
/*  82 */       this.sink.process((EntityContainer)new BoundContainer(new Bound(this.right, this.left, this.top, this.bottom, this.origin))); 
/*  85 */     ReleasableIterator<EntityContainer> iter = null;
/*     */     try {
/*  88 */       iter = this.objects.iterate();
/*  90 */       while (iter.hasNext())
/*  91 */         this.sink.process((EntityContainer)iter.next()); 
/*     */     } finally {
/*  94 */       if (iter != null)
/*  95 */         iter.release(); 
/*     */     } 
/*  99 */     this.sink.complete();
/*     */   }
/*     */   
/*     */   public void release() {
/* 105 */     this.sink.release();
/* 106 */     this.objects.release();
/*     */   }
/*     */   
/*     */   public void setSink(Sink sink) {
/* 112 */     this.sink = sink;
/*     */   }
/*     */   
/*     */   public void process(BoundContainer bound) {}
/*     */   
/*     */   public void process(NodeContainer nodeContainer) {
/* 124 */     Node node = nodeContainer.getEntity();
/* 126 */     if (this.nodesSeen) {
/* 127 */       this.left = Math.min(this.left, node.getLongitude());
/* 128 */       this.right = Math.max(this.right, node.getLongitude());
/* 130 */       this.bottom = Math.min(this.bottom, node.getLatitude());
/* 131 */       this.top = Math.max(this.top, node.getLatitude());
/*     */     } else {
/* 133 */       this.left = node.getLongitude();
/* 134 */       this.right = node.getLongitude();
/* 135 */       this.top = node.getLatitude();
/* 136 */       this.bottom = node.getLatitude();
/* 137 */       this.nodesSeen = true;
/*     */     } 
/* 140 */     this.objects.add((Storeable)nodeContainer);
/*     */   }
/*     */   
/*     */   public void process(WayContainer way) {
/* 146 */     this.objects.add((Storeable)way);
/*     */   }
/*     */   
/*     */   public void process(RelationContainer relation) {
/* 152 */     this.objects.add((Storeable)relation);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\bound\v0_6\BoundComputer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */