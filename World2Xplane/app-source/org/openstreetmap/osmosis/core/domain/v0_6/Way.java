/*     */ package org.openstreetmap.osmosis.core.domain.v0_6;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.openstreetmap.osmosis.core.domain.common.SimpleTimestampContainer;
/*     */ import org.openstreetmap.osmosis.core.domain.common.TimestampContainer;
/*     */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*     */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*     */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*     */ import org.openstreetmap.osmosis.core.util.IntAsChar;
/*     */ 
/*     */ public class Way extends Entity implements Comparable<Way> {
/*     */   private List<WayNode> wayNodes;
/*     */   
/*     */   public Way(long id, int version, Date timestamp, OsmUser user, long changesetId) {
/*  46 */     this(id, version, (TimestampContainer)new SimpleTimestampContainer(timestamp), user, changesetId);
/*     */   }
/*     */   
/*     */   public Way(long id, int version, TimestampContainer timestampContainer, OsmUser user, long changesetId) {
/*  67 */     super(id, version, timestampContainer, user, changesetId);
/*  69 */     this.wayNodes = new ArrayList<WayNode>();
/*     */   }
/*     */   
/*     */   public Way(CommonEntityData entityData) {
/*  80 */     super(entityData);
/*  82 */     this.wayNodes = new ArrayList<WayNode>();
/*     */   }
/*     */   
/*     */   public Way(long id, int version, Date timestamp, OsmUser user, long changesetId, Collection<Tag> tags, List<WayNode> wayNodes) {
/* 108 */     this(id, version, (TimestampContainer)new SimpleTimestampContainer(timestamp), user, changesetId, tags, wayNodes);
/*     */   }
/*     */   
/*     */   public Way(long id, int version, TimestampContainer timestampContainer, OsmUser user, long changesetId, Collection<Tag> tags, List<WayNode> wayNodes) {
/* 135 */     super(id, version, timestampContainer, user, changesetId, tags);
/* 137 */     this.wayNodes = new ArrayList<WayNode>(wayNodes);
/*     */   }
/*     */   
/*     */   public Way(CommonEntityData entityData, List<WayNode> wayNodes) {
/* 151 */     super(entityData);
/* 153 */     this.wayNodes = new ArrayList<WayNode>(wayNodes);
/*     */   }
/*     */   
/*     */   private Way(Way originalWay) {
/* 164 */     super(originalWay);
/* 166 */     this.wayNodes = new ArrayList<WayNode>(originalWay.wayNodes);
/*     */   }
/*     */   
/*     */   public Way(StoreReader sr, StoreClassRegister scr) {
/* 180 */     super(sr, scr);
/* 184 */     int featureCount = sr.readCharacter();
/* 186 */     this.wayNodes = new ArrayList<WayNode>();
/* 187 */     for (int i = 0; i < featureCount; i++)
/* 188 */       this.wayNodes.add(new WayNode(sr, scr)); 
/*     */   }
/*     */   
/*     */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 198 */     super.store(sw, scr);
/* 200 */     sw.writeCharacter(IntAsChar.intToChar(this.wayNodes.size()));
/* 201 */     for (WayNode wayNode : this.wayNodes)
/* 202 */       wayNode.store(sw, scr); 
/*     */   }
/*     */   
/*     */   public EntityType getType() {
/* 212 */     return EntityType.Way;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 221 */     if (o instanceof Way)
/* 222 */       return (compareTo((Way)o) == 0); 
/* 224 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 240 */     return (int)getId() + getVersion();
/*     */   }
/*     */   
/*     */   protected int compareWayNodes(List<WayNode> comparisonWayNodes) {
/* 258 */     if (this.wayNodes.size() != comparisonWayNodes.size())
/* 259 */       return this.wayNodes.size() - comparisonWayNodes.size(); 
/* 263 */     Iterator<WayNode> i = this.wayNodes.iterator();
/* 264 */     Iterator<WayNode> j = comparisonWayNodes.iterator();
/* 265 */     while (i.hasNext()) {
/* 266 */       int result = ((WayNode)i.next()).compareTo(j.next());
/* 268 */       if (result != 0)
/* 269 */         return result; 
/*     */     } 
/* 274 */     return 0;
/*     */   }
/*     */   
/*     */   public int compareTo(Way comparisonWay) {
/* 290 */     if (getId() < comparisonWay.getId())
/* 291 */       return -1; 
/* 293 */     if (getId() > comparisonWay.getId())
/* 294 */       return 1; 
/* 297 */     if (getVersion() < comparisonWay.getVersion())
/* 298 */       return -1; 
/* 300 */     if (getVersion() > comparisonWay.getVersion())
/* 301 */       return 1; 
/* 304 */     if (getTimestamp() == null && comparisonWay.getTimestamp() != null)
/* 305 */       return -1; 
/* 307 */     if (getTimestamp() != null && comparisonWay.getTimestamp() == null)
/* 308 */       return 1; 
/* 310 */     if (getTimestamp() != null && comparisonWay.getTimestamp() != null) {
/* 313 */       int result = getTimestamp().compareTo(comparisonWay.getTimestamp());
/* 315 */       if (result != 0)
/* 316 */         return result; 
/*     */     } 
/* 320 */     int wayNodeListResult = compareWayNodes(comparisonWay.getWayNodes());
/* 324 */     if (wayNodeListResult != 0)
/* 325 */       return wayNodeListResult; 
/* 328 */     return compareTags(comparisonWay.getTags());
/*     */   }
/*     */   
/*     */   public void makeReadOnly() {
/* 337 */     if (!isReadOnly())
/* 338 */       this.wayNodes = Collections.unmodifiableList(this.wayNodes); 
/* 341 */     super.makeReadOnly();
/*     */   }
/*     */   
/*     */   public List<WayNode> getWayNodes() {
/* 351 */     return this.wayNodes;
/*     */   }
/*     */   
/*     */   public Way getWriteableInstance() {
/* 360 */     if (isReadOnly())
/* 361 */       return new Way(this); 
/* 363 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 374 */     return (((WayNode)this.wayNodes.get(0)).getNodeId() == ((WayNode)this.wayNodes.get(this.wayNodes.size() - 1)).getNodeId());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 382 */     String name = null;
/* 383 */     Collection<Tag> tags = getTags();
/* 384 */     for (Tag tag : tags) {
/* 385 */       if (tag.getKey() != null && tag.getKey().equalsIgnoreCase("name")) {
/* 386 */         name = tag.getValue();
/*     */         break;
/*     */       } 
/*     */     } 
/* 390 */     if (name != null)
/* 391 */       return "Way(id=" + getId() + ", #tags=" + getTags().size() + ", name='" + name + "')"; 
/* 393 */     return "Way(id=" + getId() + ", #tags=" + getTags().size() + ")";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\Way.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */