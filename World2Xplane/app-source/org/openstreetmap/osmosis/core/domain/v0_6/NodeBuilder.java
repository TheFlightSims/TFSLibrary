/*     */ package org.openstreetmap.osmosis.core.domain.v0_6;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.openstreetmap.osmosis.core.domain.common.TimestampContainer;
/*     */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*     */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*     */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*     */ 
/*     */ @Deprecated
/*     */ public class NodeBuilder extends EntityBuilder<Node> {
/*     */   private double latitude;
/*     */   
/*     */   private double longitude;
/*     */   
/*     */   public NodeBuilder() {}
/*     */   
/*     */   public NodeBuilder(Node entity) {
/*  40 */     this();
/*  42 */     initialize(entity);
/*     */   }
/*     */   
/*     */   public NodeBuilder(long id, int version, Date timestamp, OsmUser user, long changesetId, double latitude, double longitude) {
/*  66 */     this();
/*  68 */     initialize(id, version, timestamp, user, changesetId, latitude, longitude);
/*     */   }
/*     */   
/*     */   public NodeBuilder(long id, int version, TimestampContainer timestampContainer, OsmUser user, long changesetId, double latitude, double longitude) {
/*  94 */     this();
/*  96 */     initialize(id, version, timestampContainer, user, changesetId, latitude, longitude);
/*     */   }
/*     */   
/*     */   public NodeBuilder(StoreReader sr, StoreClassRegister scr) {
/* 110 */     this();
/* 112 */     initialize(new Node(sr, scr));
/*     */   }
/*     */   
/*     */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 121 */     buildEntity().store(sw, scr);
/*     */   }
/*     */   
/*     */   private void initializeLocal(double newLatitude, double newLongitude) {
/* 132 */     this.latitude = newLatitude;
/* 133 */     this.longitude = newLongitude;
/*     */   }
/*     */   
/*     */   public NodeBuilder initialize(Node node) {
/* 145 */     initialize(node);
/* 146 */     initializeLocal(node.getLatitude(), node.getLongitude());
/* 148 */     return this;
/*     */   }
/*     */   
/*     */   public NodeBuilder initialize(long newId, int newVersion, Date newTimestamp, OsmUser newUser, long newChangesetId, double newLatitude, double newLongitude) {
/* 174 */     initialize(newId, newVersion, newTimestamp, newUser, newChangesetId);
/* 175 */     initializeLocal(newLatitude, newLongitude);
/* 177 */     return this;
/*     */   }
/*     */   
/*     */   public NodeBuilder initialize(long newId, int newVersion, TimestampContainer newTimestampContainer, OsmUser newUser, long newChangesetId, double newLatitude, double newLongitude) {
/* 204 */     initialize(newId, newVersion, newTimestampContainer, newUser, newChangesetId);
/* 205 */     initializeLocal(newLatitude, newLongitude);
/* 207 */     return this;
/*     */   }
/*     */   
/*     */   public NodeBuilder setLatitude(double newLatitude) {
/* 219 */     this.latitude = newLatitude;
/* 221 */     return this;
/*     */   }
/*     */   
/*     */   public double getLatitude() {
/* 231 */     return this.latitude;
/*     */   }
/*     */   
/*     */   public NodeBuilder setLongitude(double newLongitude) {
/* 243 */     this.longitude = newLongitude;
/* 245 */     return this;
/*     */   }
/*     */   
/*     */   public double getLongitude() {
/* 255 */     return this.longitude;
/*     */   }
/*     */   
/*     */   public Node buildEntity() {
/* 264 */     return new Node(this.id, this.version, this.timestampContainer, this.user, this.changesetId, this.tags, this.latitude, this.longitude);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 272 */     String name = null;
/* 273 */     for (Tag tag : this.tags) {
/* 274 */       if (tag.getKey() != null && tag.getKey().equalsIgnoreCase("name")) {
/* 275 */         name = tag.getValue();
/*     */         break;
/*     */       } 
/*     */     } 
/* 279 */     if (name != null)
/* 280 */       return "NodeBuilder(id=" + getId() + ", #tags=" + getTags().size() + ", name='" + name + "')"; 
/* 282 */     return "NodeBuilder(id=" + getId() + ", #tags=" + getTags().size() + ")";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\NodeBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */