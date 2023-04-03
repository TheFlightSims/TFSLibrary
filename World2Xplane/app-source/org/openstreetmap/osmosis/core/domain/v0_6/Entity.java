/*     */ package org.openstreetmap.osmosis.core.domain.v0_6;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import org.openstreetmap.osmosis.core.domain.common.TimestampContainer;
/*     */ import org.openstreetmap.osmosis.core.domain.common.TimestampFormat;
/*     */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*     */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*     */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*     */ import org.openstreetmap.osmosis.core.store.Storeable;
/*     */ 
/*     */ public abstract class Entity implements Storeable {
/*     */   private CommonEntityData entityData;
/*     */   
/*     */   public Entity(long id, int version, Date timestamp, OsmUser user, long changesetId) {
/*  43 */     this.entityData = new CommonEntityData(id, version, timestamp, user, changesetId);
/*     */   }
/*     */   
/*     */   public Entity(long id, int version, TimestampContainer timestampContainer, OsmUser user, long changesetId) {
/*  64 */     this.entityData = new CommonEntityData(id, version, timestampContainer, user, changesetId);
/*     */   }
/*     */   
/*     */   public Entity(long id, int version, Date timestamp, OsmUser user, long changesetId, Collection<Tag> tags) {
/*  86 */     this.entityData = new CommonEntityData(id, version, timestamp, user, changesetId, tags);
/*     */   }
/*     */   
/*     */   public Entity(long id, int version, TimestampContainer timestampContainer, OsmUser user, long changesetId, Collection<Tag> tags) {
/* 110 */     this.entityData = new CommonEntityData(id, version, timestampContainer, user, changesetId, tags);
/*     */   }
/*     */   
/*     */   public Entity(CommonEntityData entityData) {
/* 121 */     this.entityData = entityData.getWriteableInstance();
/*     */   }
/*     */   
/*     */   protected Entity(Entity originalEntity) {
/* 132 */     this.entityData = originalEntity.entityData.getWriteableInstance();
/*     */   }
/*     */   
/*     */   public Entity(StoreReader sr, StoreClassRegister scr) {
/* 146 */     this.entityData = new CommonEntityData(sr, scr);
/*     */   }
/*     */   
/*     */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 154 */     this.entityData.store(sw, scr);
/*     */   }
/*     */   
/*     */   protected int compareTags(Collection<Tag> comparisonTags) {
/* 168 */     return this.entityData.compareTags(comparisonTags);
/*     */   }
/*     */   
/*     */   public abstract EntityType getType();
/*     */   
/*     */   public long getId() {
/* 186 */     return this.entityData.getId();
/*     */   }
/*     */   
/*     */   public void setId(long id) {
/* 197 */     this.entityData.setId(id);
/*     */   }
/*     */   
/*     */   public int getVersion() {
/* 207 */     return this.entityData.getVersion();
/*     */   }
/*     */   
/*     */   public void setVersion(int version) {
/* 218 */     this.entityData.setVersion(version);
/*     */   }
/*     */   
/*     */   public Date getTimestamp() {
/* 229 */     return this.entityData.getTimestamp();
/*     */   }
/*     */   
/*     */   public void setTimestamp(Date timestamp) {
/* 240 */     this.entityData.setTimestamp(timestamp);
/*     */   }
/*     */   
/*     */   public TimestampContainer getTimestampContainer() {
/* 253 */     return this.entityData.getTimestampContainer();
/*     */   }
/*     */   
/*     */   public void setTimestampContainer(TimestampContainer timestampContainer) {
/* 266 */     this.entityData.setTimestampContainer(timestampContainer);
/*     */   }
/*     */   
/*     */   public String getFormattedTimestamp(TimestampFormat timestampFormat) {
/* 281 */     return this.entityData.getFormattedTimestamp(timestampFormat);
/*     */   }
/*     */   
/*     */   public OsmUser getUser() {
/* 291 */     return this.entityData.getUser();
/*     */   }
/*     */   
/*     */   public void setUser(OsmUser user) {
/* 302 */     this.entityData.setUser(user);
/*     */   }
/*     */   
/*     */   public long getChangesetId() {
/* 312 */     return this.entityData.getChangesetId();
/*     */   }
/*     */   
/*     */   public void setChangesetId(long changesetId) {
/* 323 */     this.entityData.setChangesetId(changesetId);
/*     */   }
/*     */   
/*     */   public Collection<Tag> getTags() {
/* 334 */     return this.entityData.getTags();
/*     */   }
/*     */   
/*     */   public Map<String, Object> getMetaTags() {
/* 345 */     return this.entityData.getMetaTags();
/*     */   }
/*     */   
/*     */   public boolean isReadOnly() {
/* 357 */     return this.entityData.isReadOnly();
/*     */   }
/*     */   
/*     */   protected void assertWriteable() {
/* 366 */     this.entityData.assertWriteable();
/*     */   }
/*     */   
/*     */   public void makeReadOnly() {
/* 377 */     this.entityData.makeReadOnly();
/*     */   }
/*     */   
/*     */   public abstract Entity getWriteableInstance();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\Entity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */