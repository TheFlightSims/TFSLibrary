/*     */ package org.openstreetmap.osmosis.core.domain.v0_6;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import org.openstreetmap.osmosis.core.domain.common.SimpleTimestampContainer;
/*     */ import org.openstreetmap.osmosis.core.domain.common.TimestampContainer;
/*     */ import org.openstreetmap.osmosis.core.store.Storeable;
/*     */ import org.openstreetmap.osmosis.core.util.LongAsInt;
/*     */ 
/*     */ @Deprecated
/*     */ public abstract class EntityBuilder<T extends Entity> implements Storeable {
/*     */   protected long id;
/*     */   
/*     */   protected int version;
/*     */   
/*     */   protected TimestampContainer timestampContainer;
/*     */   
/*     */   protected OsmUser user;
/*     */   
/*     */   protected int changesetId;
/*     */   
/*  59 */   protected Collection<Tag> tags = new ArrayList<Tag>();
/*     */   
/*     */   public EntityBuilder() {}
/*     */   
/*     */   public EntityBuilder(Entity entity) {
/*  70 */     this();
/*  72 */     initialize(entity);
/*     */   }
/*     */   
/*     */   public EntityBuilder(long id, int version, Date timestamp, OsmUser user, long changesetId) {
/*  91 */     this();
/*  93 */     initialize(id, version, timestamp, user, changesetId);
/*     */   }
/*     */   
/*     */   public EntityBuilder(long id, int version, TimestampContainer timestampContainer, OsmUser user, long changesetId) {
/* 113 */     this();
/* 115 */     initialize(id, version, timestampContainer, user, changesetId);
/*     */   }
/*     */   
/*     */   protected EntityBuilder<T> initialize(Entity entity) {
/* 128 */     initialize(entity.getId(), entity.getVersion(), entity.getTimestampContainer(), entity.getUser(), entity.getChangesetId());
/* 131 */     this.tags.addAll(entity.getTags());
/* 133 */     return this;
/*     */   }
/*     */   
/*     */   protected EntityBuilder<T> initialize(long newId, int newVersion, Date newTimestamp, OsmUser newUser, long newChangesetId) {
/* 155 */     initialize(newId, newVersion, (TimestampContainer)new SimpleTimestampContainer(newTimestamp), newUser, newChangesetId);
/* 157 */     return this;
/*     */   }
/*     */   
/*     */   protected EntityBuilder<T> initialize(long newId, int newVersion, TimestampContainer newTimestampContainer, OsmUser newUser, long newChangesetId) {
/* 180 */     this.id = newId;
/* 181 */     this.timestampContainer = newTimestampContainer;
/* 182 */     this.user = newUser;
/* 183 */     this.changesetId = LongAsInt.longToInt(newChangesetId);
/* 184 */     this.version = newVersion;
/* 186 */     this.tags.clear();
/* 188 */     return this;
/*     */   }
/*     */   
/*     */   public EntityBuilder<T> setId(long newId) {
/* 200 */     this.id = newId;
/* 202 */     return this;
/*     */   }
/*     */   
/*     */   public long getId() {
/* 212 */     return this.id;
/*     */   }
/*     */   
/*     */   public EntityBuilder<T> setVersion(int newVersion) {
/* 224 */     this.version = newVersion;
/* 226 */     return this;
/*     */   }
/*     */   
/*     */   public int getVersion() {
/* 236 */     return this.version;
/*     */   }
/*     */   
/*     */   public EntityBuilder<T> setTimestamp(Date timestamp) {
/* 248 */     this.timestampContainer = (TimestampContainer)new SimpleTimestampContainer(timestamp);
/* 250 */     return this;
/*     */   }
/*     */   
/*     */   public Date getTimestamp() {
/* 260 */     return this.timestampContainer.getTimestamp();
/*     */   }
/*     */   
/*     */   public EntityBuilder<T> setTimestamp(TimestampContainer newTimestampContainer) {
/* 272 */     this.timestampContainer = newTimestampContainer;
/* 274 */     return this;
/*     */   }
/*     */   
/*     */   public TimestampContainer getTimestampContainer() {
/* 284 */     return this.timestampContainer;
/*     */   }
/*     */   
/*     */   public EntityBuilder<T> setUser(OsmUser newUser) {
/* 296 */     this.user = newUser;
/* 298 */     return this;
/*     */   }
/*     */   
/*     */   public OsmUser getUser() {
/* 308 */     return this.user;
/*     */   }
/*     */   
/*     */   public long getChangesetId() {
/* 318 */     return this.changesetId;
/*     */   }
/*     */   
/*     */   public EntityBuilder<T> setChangesetId(long newChangesetId) {
/* 330 */     this.changesetId = LongAsInt.longToInt(newChangesetId);
/* 332 */     return this;
/*     */   }
/*     */   
/*     */   public Collection<Tag> getTags() {
/* 342 */     return this.tags;
/*     */   }
/*     */   
/*     */   public EntityBuilder<T> clearTags() {
/* 352 */     this.tags.clear();
/* 354 */     return this;
/*     */   }
/*     */   
/*     */   public EntityBuilder<T> setTags(Collection<Tag> newTags) {
/* 366 */     this.tags.clear();
/* 367 */     this.tags.addAll(newTags);
/* 369 */     return this;
/*     */   }
/*     */   
/*     */   public EntityBuilder<T> addTag(Tag tag) {
/* 381 */     this.tags.add(tag);
/* 383 */     return this;
/*     */   }
/*     */   
/*     */   public abstract T buildEntity();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\EntityBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */