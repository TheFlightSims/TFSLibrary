/*     */ package org.openstreetmap.osmosis.core.container.v0_6;
/*     */ 
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.EntityBuilder;
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.NodeBuilder;
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.RelationBuilder;
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.WayBuilder;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.Source;
/*     */ 
/*     */ @Deprecated
/*     */ public class EntityContainerBuilder implements EntityProcessor, Source {
/*     */   private Sink sink;
/*     */   
/*  33 */   private NodeBuilder nodeBuilder = new NodeBuilder();
/*     */   
/*  34 */   private WayBuilder wayBuilder = new WayBuilder();
/*     */   
/*  35 */   private RelationBuilder relationBuilder = new RelationBuilder();
/*     */   
/*     */   protected boolean processEntity(EntityBuilder<?> builder) {
/*  48 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean processNode(NodeBuilder entityBuilder) {
/*  61 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean processWay(WayBuilder entityBuilder) {
/*  74 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean processRelation(RelationBuilder entityBuilder) {
/*  87 */     return false;
/*     */   }
/*     */   
/*     */   public void process(BoundContainer boundContainer) {
/*  95 */     this.sink.process(boundContainer);
/*     */   }
/*     */   
/*     */   public void process(NodeContainer nodeContainer) {
/* 105 */     this.nodeBuilder.initialize(nodeContainer.getEntity());
/* 107 */     boolean modified = false;
/* 108 */     modified = (modified || processEntity((EntityBuilder<?>)this.nodeBuilder));
/* 109 */     modified = (modified || processNode(this.nodeBuilder));
/* 111 */     if (modified) {
/* 112 */       this.sink.process(new NodeContainer(this.nodeBuilder.buildEntity()));
/*     */     } else {
/* 114 */       this.sink.process(nodeContainer);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void process(WayContainer wayContainer) {
/* 125 */     this.wayBuilder.initialize(wayContainer.getEntity());
/* 127 */     boolean modified = false;
/* 128 */     modified = (modified || processEntity((EntityBuilder<?>)this.wayBuilder));
/* 129 */     modified = (modified || processWay(this.wayBuilder));
/* 131 */     if (modified) {
/* 132 */       this.sink.process(new WayContainer(this.wayBuilder.buildEntity()));
/*     */     } else {
/* 134 */       this.sink.process(wayContainer);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void process(RelationContainer relationContainer) {
/* 145 */     this.relationBuilder.initialize(relationContainer.getEntity());
/* 147 */     boolean modified = false;
/* 148 */     modified = (modified || processEntity((EntityBuilder<?>)this.relationBuilder));
/* 149 */     modified = (modified || processRelation(this.relationBuilder));
/* 151 */     if (modified) {
/* 152 */       this.sink.process(new RelationContainer(this.relationBuilder.buildEntity()));
/*     */     } else {
/* 154 */       this.sink.process(relationContainer);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSink(Sink sink) {
/* 163 */     this.sink = sink;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\EntityContainerBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */