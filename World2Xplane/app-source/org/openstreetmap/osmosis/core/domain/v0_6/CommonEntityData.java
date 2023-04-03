/*     */ package org.openstreetmap.osmosis.core.domain.v0_6;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ import org.openstreetmap.osmosis.core.domain.common.SimpleTimestampContainer;
/*     */ import org.openstreetmap.osmosis.core.domain.common.TimestampContainer;
/*     */ import org.openstreetmap.osmosis.core.domain.common.TimestampFormat;
/*     */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*     */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*     */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*     */ import org.openstreetmap.osmosis.core.store.Storeable;
/*     */ import org.openstreetmap.osmosis.core.util.LazyHashMap;
/*     */ import org.openstreetmap.osmosis.core.util.LongAsInt;
/*     */ 
/*     */ public class CommonEntityData implements Storeable {
/*     */   private long id;
/*     */   
/*     */   private int version;
/*     */   
/*     */   private int changesetId;
/*     */   
/*     */   private TimestampContainer timestampContainer;
/*     */   
/*     */   private OsmUser user;
/*     */   
/*     */   private TagCollection tags;
/*     */   
/*     */   private Map<String, Object> metaTags;
/*     */   
/*     */   private boolean readOnly;
/*     */   
/*     */   public CommonEntityData(long id, int version, Date timestamp, OsmUser user, long changesetId) {
/*  56 */     this(id, version, (TimestampContainer)new SimpleTimestampContainer(timestamp), user, changesetId);
/*     */   }
/*     */   
/*     */   public CommonEntityData(long id, int version, TimestampContainer timestampContainer, OsmUser user, long changesetId) {
/*  77 */     init(id, timestampContainer, user, version, changesetId);
/*  78 */     this.tags = new TagCollectionImpl();
/*  79 */     this.metaTags = (Map<String, Object>)new LazyHashMap();
/*     */   }
/*     */   
/*     */   public CommonEntityData(long id, int version, Date timestamp, OsmUser user, long changesetId, Collection<Tag> tags) {
/* 102 */     this(id, version, (TimestampContainer)new SimpleTimestampContainer(timestamp), user, changesetId, tags);
/*     */   }
/*     */   
/*     */   public CommonEntityData(long id, int version, TimestampContainer timestampContainer, OsmUser user, long changesetId, Collection<Tag> tags) {
/* 125 */     init(id, timestampContainer, user, version, changesetId);
/* 126 */     this.tags = new TagCollectionImpl(tags);
/* 127 */     this.metaTags = (Map<String, Object>)new LazyHashMap();
/*     */   }
/*     */   
/*     */   private void init(long newId, TimestampContainer newTimestampContainer, OsmUser newUser, int newVersion, long newChangesetId) {
/* 147 */     this.id = newId;
/* 148 */     this.timestampContainer = newTimestampContainer;
/* 149 */     this.user = newUser;
/* 150 */     this.version = newVersion;
/* 151 */     this.changesetId = LongAsInt.longToInt(newChangesetId);
/*     */   }
/*     */   
/*     */   private static TimestampContainer readTimestampContainer(StoreReader sr, StoreClassRegister scr) {
/* 156 */     if (sr.readBoolean())
/* 157 */       return (TimestampContainer)new SimpleTimestampContainer(new Date(sr.readLong())); 
/* 159 */     return null;
/*     */   }
/*     */   
/*     */   private static OsmUser readOsmUser(StoreReader sr, StoreClassRegister scr) {
/* 172 */     OsmUser user = new OsmUser(sr, scr);
/* 174 */     if (user.equals(OsmUser.NONE))
/* 175 */       return OsmUser.NONE; 
/* 177 */     return user;
/*     */   }
/*     */   
/*     */   public CommonEntityData(StoreReader sr, StoreClassRegister scr) {
/* 192 */     this(sr.readLong(), sr.readInteger(), readTimestampContainer(sr, scr), readOsmUser(sr, scr), sr.readInteger(), new TagCollectionImpl(sr, scr));
/* 203 */     int metaTagCount = sr.readInteger();
/* 204 */     this.metaTags = (Map<String, Object>)new LazyHashMap();
/* 205 */     for (int i = 0; i < metaTagCount; i++)
/* 206 */       this.metaTags.put(sr.readString(), sr.readString()); 
/*     */   }
/*     */   
/*     */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 215 */     sw.writeLong(this.id);
/* 217 */     sw.writeInteger(this.version);
/* 219 */     if (getTimestamp() != null) {
/* 220 */       sw.writeBoolean(true);
/* 221 */       sw.writeLong(this.timestampContainer.getTimestamp().getTime());
/*     */     } else {
/* 223 */       sw.writeBoolean(false);
/*     */     } 
/* 226 */     this.user.store(sw, scr);
/* 228 */     sw.writeInteger(this.changesetId);
/* 230 */     this.tags.store(sw, scr);
/* 232 */     sw.writeInteger(this.metaTags.size());
/* 233 */     for (Map.Entry<String, Object> tag : this.metaTags.entrySet()) {
/* 234 */       sw.writeString(tag.getKey());
/* 235 */       sw.writeString(tag.getValue().toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected int compareTags(Collection<Tag> comparisonTags) {
/* 253 */     List<Tag> tags1 = new ArrayList<Tag>(this.tags);
/* 254 */     List<Tag> tags2 = new ArrayList<Tag>(comparisonTags);
/* 256 */     Collections.sort(tags1);
/* 257 */     Collections.sort(tags2);
/* 260 */     if (tags1.size() != tags2.size())
/* 261 */       return tags1.size() - tags2.size(); 
/* 265 */     for (int i = 0; i < tags1.size(); i++) {
/* 266 */       int result = ((Tag)tags1.get(i)).compareTo(tags2.get(i));
/* 268 */       if (result != 0)
/* 269 */         return result; 
/*     */     } 
/* 274 */     return 0;
/*     */   }
/*     */   
/*     */   public long getId() {
/* 284 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(long id) {
/* 295 */     assertWriteable();
/* 297 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getVersion() {
/* 307 */     return this.version;
/*     */   }
/*     */   
/*     */   public void setVersion(int version) {
/* 318 */     assertWriteable();
/* 320 */     this.version = version;
/*     */   }
/*     */   
/*     */   public Date getTimestamp() {
/* 331 */     return this.timestampContainer.getTimestamp();
/*     */   }
/*     */   
/*     */   public void setTimestamp(Date timestamp) {
/* 342 */     assertWriteable();
/* 344 */     this.timestampContainer = (TimestampContainer)new SimpleTimestampContainer(timestamp);
/*     */   }
/*     */   
/*     */   public TimestampContainer getTimestampContainer() {
/* 357 */     return this.timestampContainer;
/*     */   }
/*     */   
/*     */   public void setTimestampContainer(TimestampContainer timestampContainer) {
/* 370 */     assertWriteable();
/* 372 */     this.timestampContainer = timestampContainer;
/*     */   }
/*     */   
/*     */   public String getFormattedTimestamp(TimestampFormat timestampFormat) {
/* 387 */     return this.timestampContainer.getFormattedTimestamp(timestampFormat);
/*     */   }
/*     */   
/*     */   public OsmUser getUser() {
/* 397 */     return this.user;
/*     */   }
/*     */   
/*     */   public void setUser(OsmUser user) {
/* 408 */     assertWriteable();
/* 410 */     this.user = user;
/*     */   }
/*     */   
/*     */   public long getChangesetId() {
/* 420 */     return this.changesetId;
/*     */   }
/*     */   
/*     */   public void setChangesetId(long changesetId) {
/* 431 */     assertWriteable();
/* 433 */     this.changesetId = LongAsInt.longToInt(changesetId);
/*     */   }
/*     */   
/*     */   public Collection<Tag> getTags() {
/* 444 */     return this.tags;
/*     */   }
/*     */   
/*     */   public Map<String, Object> getMetaTags() {
/* 455 */     return this.metaTags;
/*     */   }
/*     */   
/*     */   public boolean isReadOnly() {
/* 467 */     return this.readOnly;
/*     */   }
/*     */   
/*     */   protected void assertWriteable() {
/* 476 */     if (this.readOnly)
/* 477 */       throw new OsmosisRuntimeException("The object has been marked as read-only.  It must be cloned to make changes."); 
/*     */   }
/*     */   
/*     */   public void makeReadOnly() {
/* 490 */     if (!this.readOnly) {
/* 491 */       this.tags = new UnmodifiableTagCollection(this.tags);
/* 492 */       this.metaTags = Collections.unmodifiableMap(this.metaTags);
/* 494 */       this.readOnly = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public CommonEntityData getWriteableInstance() {
/* 506 */     if (isReadOnly())
/* 507 */       return new CommonEntityData(this.id, this.version, this.timestampContainer, this.user, this.changesetId, this.tags); 
/* 509 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\CommonEntityData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */