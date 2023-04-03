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
/*     */ public class RelationBuilder extends EntityBuilder<Relation> {
/*  32 */   private List<RelationMember> members = new ArrayList<RelationMember>();
/*     */   
/*     */   public RelationBuilder(Relation entity) {
/*  43 */     this();
/*  45 */     initialize(entity);
/*     */   }
/*     */   
/*     */   public RelationBuilder(long id, int version, Date timestamp, OsmUser user, long changesetId) {
/*  64 */     this();
/*  66 */     initialize(id, version, timestamp, user, changesetId);
/*     */   }
/*     */   
/*     */   public RelationBuilder(long id, TimestampContainer timestampContainer, OsmUser user, int version, long changesetId) {
/*  87 */     this();
/*  89 */     initialize(id, version, timestampContainer, user, changesetId);
/*     */   }
/*     */   
/*     */   public RelationBuilder(StoreReader sr, StoreClassRegister scr) {
/* 103 */     this();
/* 105 */     initialize(new Relation(sr, scr));
/*     */   }
/*     */   
/*     */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 114 */     buildEntity().store(sw, scr);
/*     */   }
/*     */   
/*     */   private void initializeLocal() {
/* 122 */     this.members.clear();
/*     */   }
/*     */   
/*     */   public RelationBuilder initialize(Relation relation) {
/* 134 */     initialize(relation);
/* 135 */     initializeLocal();
/* 136 */     this.members.addAll(relation.getMembers());
/* 138 */     return this;
/*     */   }
/*     */   
/*     */   public RelationBuilder initialize(long newId, int newVersion, Date newTimestamp, OsmUser newUser, long newChangesetId) {
/* 160 */     super.initialize(newId, newVersion, newTimestamp, newUser, newChangesetId);
/* 161 */     initializeLocal();
/* 163 */     return this;
/*     */   }
/*     */   
/*     */   public RelationBuilder initialize(long newId, int newVersion, TimestampContainer newTimestampContainer, OsmUser newUser, long newChangesetId) {
/* 186 */     super.initialize(newId, newVersion, newTimestampContainer, newUser, newChangesetId);
/* 187 */     initializeLocal();
/* 189 */     return this;
/*     */   }
/*     */   
/*     */   public List<RelationMember> getMembers() {
/* 199 */     return this.members;
/*     */   }
/*     */   
/*     */   public RelationBuilder clearMembers() {
/* 209 */     this.members.clear();
/* 211 */     return this;
/*     */   }
/*     */   
/*     */   public RelationBuilder setMembers(List<RelationMember> newMembers) {
/* 223 */     newMembers.clear();
/* 224 */     newMembers.addAll(newMembers);
/* 226 */     return this;
/*     */   }
/*     */   
/*     */   public RelationBuilder addMember(RelationMember member) {
/* 238 */     this.members.add(member);
/* 240 */     return this;
/*     */   }
/*     */   
/*     */   public Relation buildEntity() {
/* 249 */     return new Relation(this.id, this.version, this.timestampContainer, this.user, this.changesetId, this.tags, this.members);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 257 */     String type = null;
/* 258 */     for (Tag tag : this.tags) {
/* 259 */       if (tag.getKey() != null && tag.getKey().equalsIgnoreCase("type")) {
/* 260 */         type = tag.getValue();
/*     */         break;
/*     */       } 
/*     */     } 
/* 264 */     if (type != null)
/* 265 */       return "RelationBuilder(id=" + getId() + ", #tags=" + getTags().size() + ", type='" + type + "')"; 
/* 267 */     return "RelationBuilder(id=" + getId() + ", #tags=" + getTags().size() + ")";
/*     */   }
/*     */   
/*     */   public RelationBuilder() {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\RelationBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */