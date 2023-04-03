/*     */ package org.openstreetmap.osmosis.core.domain.v0_6;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import org.openstreetmap.osmosis.core.domain.common.SimpleTimestampContainer;
/*     */ import org.openstreetmap.osmosis.core.domain.common.TimestampContainer;
/*     */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*     */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*     */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*     */ import org.openstreetmap.osmosis.core.util.FixedPrecisionCoordinateConvertor;
/*     */ 
/*     */ public class Node extends Entity implements Comparable<Node> {
/*     */   private double latitude;
/*     */   
/*     */   private double longitude;
/*     */   
/*     */   public Node(long id, int version, Date timestamp, OsmUser user, long changesetId, double latitude, double longitude) {
/*  48 */     this(id, version, (TimestampContainer)new SimpleTimestampContainer(timestamp), user, changesetId, latitude, longitude);
/*     */   }
/*     */   
/*     */   public Node(long id, int version, TimestampContainer timestampContainer, OsmUser user, long changesetId, double latitude, double longitude) {
/*  73 */     super(id, version, timestampContainer, user, changesetId);
/*  75 */     init(latitude, longitude);
/*     */   }
/*     */   
/*     */   public Node(CommonEntityData entityData, double latitude, double longitude) {
/*  90 */     super(entityData);
/*  92 */     init(latitude, longitude);
/*     */   }
/*     */   
/*     */   public Node(long id, int version, Date timestamp, OsmUser user, long changesetId, Collection<Tag> tags, double latitude, double longitude) {
/* 120 */     this(id, version, (TimestampContainer)new SimpleTimestampContainer(timestamp), user, changesetId, tags, latitude, longitude);
/*     */   }
/*     */   
/*     */   public Node(long id, int version, TimestampContainer timestampContainer, OsmUser user, long changesetId, Collection<Tag> tags, double latitude, double longitude) {
/* 147 */     super(id, version, timestampContainer, user, changesetId, tags);
/* 149 */     init(latitude, longitude);
/*     */   }
/*     */   
/*     */   private Node(Node originalNode) {
/* 160 */     super(originalNode);
/* 162 */     init(originalNode.latitude, originalNode.longitude);
/*     */   }
/*     */   
/*     */   private void init(double newLatitude, double newLongitude) {
/* 175 */     this.latitude = newLatitude;
/* 176 */     this.longitude = newLongitude;
/*     */   }
/*     */   
/*     */   public Node(StoreReader sr, StoreClassRegister scr) {
/* 189 */     super(sr, scr);
/* 191 */     this.latitude = FixedPrecisionCoordinateConvertor.convertToDouble(sr.readInteger());
/* 192 */     this.longitude = FixedPrecisionCoordinateConvertor.convertToDouble(sr.readInteger());
/*     */   }
/*     */   
/*     */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 201 */     super.store(sw, scr);
/* 203 */     sw.writeInteger(FixedPrecisionCoordinateConvertor.convertToFixed(this.latitude));
/* 204 */     sw.writeInteger(FixedPrecisionCoordinateConvertor.convertToFixed(this.longitude));
/*     */   }
/*     */   
/*     */   public EntityType getType() {
/* 213 */     return EntityType.Node;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 222 */     if (o instanceof Node)
/* 223 */       return (compareTo((Node)o) == 0); 
/* 225 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 240 */     return (int)getId() + getVersion();
/*     */   }
/*     */   
/*     */   public int compareTo(Node comparisonNode) {
/* 253 */     if (getId() < comparisonNode.getId())
/* 254 */       return -1; 
/* 257 */     if (getId() > comparisonNode.getId())
/* 258 */       return 1; 
/* 261 */     if (getVersion() < comparisonNode.getVersion())
/* 262 */       return -1; 
/* 265 */     if (getVersion() > comparisonNode.getVersion())
/* 266 */       return 1; 
/* 269 */     if (this.latitude < comparisonNode.latitude)
/* 270 */       return -1; 
/* 273 */     if (this.latitude > comparisonNode.latitude)
/* 274 */       return 1; 
/* 277 */     if (this.longitude < comparisonNode.longitude)
/* 278 */       return -1; 
/* 281 */     if (this.longitude > comparisonNode.longitude)
/* 282 */       return 1; 
/* 285 */     if (getTimestamp() == null && comparisonNode.getTimestamp() != null)
/* 286 */       return -1; 
/* 288 */     if (getTimestamp() != null && comparisonNode.getTimestamp() == null)
/* 289 */       return 1; 
/* 291 */     if (getTimestamp() != null && comparisonNode.getTimestamp() != null) {
/* 294 */       int result = getTimestamp().compareTo(comparisonNode.getTimestamp());
/* 296 */       if (result != 0)
/* 297 */         return result; 
/*     */     } 
/* 301 */     return compareTags(comparisonNode.getTags());
/*     */   }
/*     */   
/*     */   public double getLatitude() {
/* 311 */     return this.latitude;
/*     */   }
/*     */   
/*     */   public void setLatitude(double latitude) {
/* 322 */     assertWriteable();
/* 324 */     this.latitude = latitude;
/*     */   }
/*     */   
/*     */   public double getLongitude() {
/* 334 */     return this.longitude;
/*     */   }
/*     */   
/*     */   public void setLongitude(double longitude) {
/* 345 */     assertWriteable();
/* 347 */     this.longitude = longitude;
/*     */   }
/*     */   
/*     */   public Node getWriteableInstance() {
/* 356 */     if (isReadOnly())
/* 357 */       return new Node(this); 
/* 359 */     return this;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 369 */     String name = null;
/* 370 */     Collection<Tag> tags = getTags();
/* 371 */     for (Tag tag : tags) {
/* 372 */       if (tag.getKey() != null && tag.getKey().equalsIgnoreCase("name")) {
/* 373 */         name = tag.getValue();
/*     */         break;
/*     */       } 
/*     */     } 
/* 377 */     if (name != null)
/* 378 */       return "Node(id=" + getId() + ", #tags=" + getTags().size() + ", name='" + name + "')"; 
/* 380 */     return "Node(id=" + getId() + ", #tags=" + getTags().size() + ")";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\Node.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */