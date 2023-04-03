/*     */ package org.openstreetmap.osmosis.core.domain.v0_6;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.openstreetmap.osmosis.core.domain.common.TimestampContainer;
/*     */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*     */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*     */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*     */ 
/*     */ @Deprecated
/*     */ public class WayBuilder extends EntityBuilder<Way> {
/*  32 */   private List<WayNode> wayNodes = new ArrayList<WayNode>();
/*     */   
/*     */   public WayBuilder(Way entity) {
/*  43 */     this();
/*  45 */     initialize(entity);
/*     */   }
/*     */   
/*     */   public WayBuilder(long id, int version, Date timestamp, OsmUser user, long changesetId) {
/*  64 */     this();
/*  66 */     initialize(id, version, timestamp, user, changesetId);
/*     */   }
/*     */   
/*     */   public WayBuilder(long id, TimestampContainer timestampContainer, OsmUser user, long changesetId, int version) {
/*  86 */     this();
/*  88 */     initialize(id, version, timestampContainer, user, changesetId);
/*     */   }
/*     */   
/*     */   public WayBuilder(StoreReader sr, StoreClassRegister scr) {
/* 102 */     this();
/* 104 */     initialize(new Way(sr, scr));
/*     */   }
/*     */   
/*     */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 113 */     buildEntity().store(sw, scr);
/*     */   }
/*     */   
/*     */   private void initializeLocal() {
/* 121 */     this.wayNodes.clear();
/*     */   }
/*     */   
/*     */   public WayBuilder initialize(Way way) {
/* 133 */     initialize(way);
/* 134 */     initializeLocal();
/* 135 */     this.wayNodes.addAll(way.getWayNodes());
/* 137 */     return this;
/*     */   }
/*     */   
/*     */   public WayBuilder initialize(long newId, int newVersion, Date newTimestamp, OsmUser newUser, long newChangesetId) {
/* 158 */     super.initialize(newId, newVersion, newTimestamp, newUser, newChangesetId);
/* 159 */     initializeLocal();
/* 161 */     return this;
/*     */   }
/*     */   
/*     */   public WayBuilder initialize(long newId, int newVersion, TimestampContainer newTimestampContainer, OsmUser newUser, long newChangesetId) {
/* 184 */     super.initialize(newId, newVersion, newTimestampContainer, newUser, newChangesetId);
/* 185 */     initializeLocal();
/* 187 */     return this;
/*     */   }
/*     */   
/*     */   public List<WayNode> getWayNodes() {
/* 197 */     return this.wayNodes;
/*     */   }
/*     */   
/*     */   public WayBuilder clearWayNodes() {
/* 207 */     this.wayNodes.clear();
/* 209 */     return this;
/*     */   }
/*     */   
/*     */   public WayBuilder setWayNodes(List<WayNode> newWayNodes) {
/* 221 */     this.wayNodes.clear();
/* 222 */     this.wayNodes.addAll(newWayNodes);
/* 224 */     return this;
/*     */   }
/*     */   
/*     */   public WayBuilder addWayNode(WayNode wayNode) {
/* 236 */     this.wayNodes.add(wayNode);
/* 238 */     return this;
/*     */   }
/*     */   
/*     */   public Way buildEntity() {
/* 247 */     return new Way(this.id, this.version, this.timestampContainer, this.user, this.changesetId, this.tags, this.wayNodes);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 255 */     String name = null;
/* 256 */     for (Tag tag : this.tags) {
/* 257 */       if (tag.getKey() != null && tag.getKey().equalsIgnoreCase("name")) {
/* 258 */         name = tag.getValue();
/*     */         break;
/*     */       } 
/*     */     } 
/* 262 */     if (name != null)
/* 263 */       return "WayBuilder(id=" + getId() + ", #tags=" + getTags().size() + ", name='" + name + "')"; 
/* 265 */     return "WayBuilder(id=" + getId() + ", #tags=" + getTags().size() + ")";
/*     */   }
/*     */   
/*     */   public WayBuilder() {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\WayBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */