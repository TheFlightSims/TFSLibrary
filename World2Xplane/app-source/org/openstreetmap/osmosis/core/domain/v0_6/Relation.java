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
/*     */ 
/*     */ public class Relation extends Entity implements Comparable<Relation> {
/*     */   private List<RelationMember> members;
/*     */   
/*     */   public Relation(long id, int version, Date timestamp, OsmUser user, long changesetId) {
/*  44 */     this(id, version, (TimestampContainer)new SimpleTimestampContainer(timestamp), user, changesetId);
/*     */   }
/*     */   
/*     */   public Relation(long id, int version, TimestampContainer timestampContainer, OsmUser user, long changesetId) {
/*  65 */     super(id, version, timestampContainer, user, changesetId);
/*  67 */     this.members = new ArrayList<RelationMember>();
/*     */   }
/*     */   
/*     */   public Relation(CommonEntityData entityData) {
/*  78 */     super(entityData);
/*  80 */     this.members = new ArrayList<RelationMember>();
/*     */   }
/*     */   
/*     */   public Relation(long id, int version, Date timestamp, OsmUser user, long changesetId, Collection<Tag> tags, List<RelationMember> members) {
/* 107 */     this(id, version, (TimestampContainer)new SimpleTimestampContainer(timestamp), user, changesetId, tags, members);
/*     */   }
/*     */   
/*     */   public Relation(long id, int version, TimestampContainer timestampContainer, OsmUser user, long changesetId, Collection<Tag> tags, List<RelationMember> members) {
/* 134 */     super(id, version, timestampContainer, user, changesetId, tags);
/* 136 */     this.members = new ArrayList<RelationMember>(members);
/*     */   }
/*     */   
/*     */   public Relation(CommonEntityData entityData, List<RelationMember> members) {
/* 150 */     super(entityData);
/* 152 */     this.members = new ArrayList<RelationMember>(members);
/*     */   }
/*     */   
/*     */   private Relation(Relation originalRelation) {
/* 163 */     super(originalRelation);
/* 165 */     this.members = new ArrayList<RelationMember>(originalRelation.members);
/*     */   }
/*     */   
/*     */   public Relation(StoreReader sr, StoreClassRegister scr) {
/* 179 */     super(sr, scr);
/* 183 */     int featureCount = sr.readInteger();
/* 185 */     this.members = new ArrayList<RelationMember>();
/* 186 */     for (int i = 0; i < featureCount; i++)
/* 187 */       this.members.add(new RelationMember(sr, scr)); 
/*     */   }
/*     */   
/*     */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 197 */     super.store(sw, scr);
/* 199 */     sw.writeInteger(this.members.size());
/* 200 */     for (RelationMember relationMember : this.members)
/* 201 */       relationMember.store(sw, scr); 
/*     */   }
/*     */   
/*     */   public EntityType getType() {
/* 211 */     return EntityType.Relation;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 220 */     if (o instanceof Relation)
/* 221 */       return (compareTo((Relation)o) == 0); 
/* 223 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 239 */     return (int)getId() + getVersion();
/*     */   }
/*     */   
/*     */   protected int compareMemberList(Collection<RelationMember> comparisonMemberList) {
/* 258 */     if (this.members.size() != comparisonMemberList.size())
/* 259 */       return this.members.size() - comparisonMemberList.size(); 
/* 263 */     Iterator<RelationMember> i = this.members.iterator();
/* 264 */     Iterator<RelationMember> j = comparisonMemberList.iterator();
/* 265 */     while (i.hasNext()) {
/* 266 */       int result = ((RelationMember)i.next()).compareTo(j.next());
/* 268 */       if (result != 0)
/* 269 */         return result; 
/*     */     } 
/* 274 */     return 0;
/*     */   }
/*     */   
/*     */   public int compareTo(Relation comparisonRelation) {
/* 290 */     if (getId() < comparisonRelation.getId())
/* 291 */       return -1; 
/* 293 */     if (getId() > comparisonRelation.getId())
/* 294 */       return 1; 
/* 297 */     if (getVersion() < comparisonRelation.getVersion())
/* 298 */       return -1; 
/* 300 */     if (getVersion() > comparisonRelation.getVersion())
/* 301 */       return 1; 
/* 304 */     if (getTimestamp() == null && comparisonRelation.getTimestamp() != null)
/* 305 */       return -1; 
/* 307 */     if (getTimestamp() != null && comparisonRelation.getTimestamp() == null)
/* 308 */       return 1; 
/* 310 */     if (getTimestamp() != null && comparisonRelation.getTimestamp() != null) {
/* 313 */       int result = getTimestamp().compareTo(comparisonRelation.getTimestamp());
/* 315 */       if (result != 0)
/* 316 */         return result; 
/*     */     } 
/* 320 */     int memberListResult = compareMemberList(comparisonRelation.members);
/* 324 */     if (memberListResult != 0)
/* 325 */       return memberListResult; 
/* 328 */     return compareTags(comparisonRelation.getTags());
/*     */   }
/*     */   
/*     */   public void makeReadOnly() {
/* 337 */     if (!isReadOnly())
/* 338 */       this.members = Collections.unmodifiableList(this.members); 
/* 341 */     super.makeReadOnly();
/*     */   }
/*     */   
/*     */   public Relation getWriteableInstance() {
/* 350 */     if (isReadOnly())
/* 351 */       return new Relation(this); 
/* 353 */     return this;
/*     */   }
/*     */   
/*     */   public List<RelationMember> getMembers() {
/* 365 */     return this.members;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 373 */     String type = null;
/* 374 */     Collection<Tag> tags = getTags();
/* 375 */     for (Tag tag : tags) {
/* 376 */       if (tag.getKey() != null && tag.getKey().equalsIgnoreCase("type")) {
/* 377 */         type = tag.getValue();
/*     */         break;
/*     */       } 
/*     */     } 
/* 381 */     if (type != null)
/* 382 */       return "Relation(id=" + getId() + ", #tags=" + getTags().size() + ", type='" + type + "')"; 
/* 384 */     return "Relation(id=" + getId() + ", #tags=" + getTags().size() + ")";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\Relation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */