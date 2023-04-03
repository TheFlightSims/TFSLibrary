/*     */ package org.openstreetmap.osmosis.core.domain.v0_6;
/*     */ 
/*     */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*     */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*     */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*     */ import org.openstreetmap.osmosis.core.store.Storeable;
/*     */ 
/*     */ public class Tag implements Comparable<Tag>, Storeable {
/*     */   private String key;
/*     */   
/*     */   private String value;
/*     */   
/*     */   public Tag(String key, String value) {
/*  36 */     this.key = key;
/*  37 */     this.value = value;
/*     */   }
/*     */   
/*     */   public Tag(StoreReader sr, StoreClassRegister scr) {
/*  51 */     this(sr.readString(), sr.readString());
/*     */   }
/*     */   
/*     */   public void store(StoreWriter sw, StoreClassRegister scr) {
/*  59 */     sw.writeString(this.key);
/*  60 */     sw.writeString(this.value);
/*     */   }
/*     */   
/*     */   public int compareTo(Tag tag) {
/*  76 */     int keyResult = this.key.compareTo(tag.key);
/*  78 */     if (keyResult != 0)
/*  79 */       return keyResult; 
/*  82 */     return this.value.compareTo(tag.value);
/*     */   }
/*     */   
/*     */   public String getKey() {
/*  90 */     return this.key;
/*     */   }
/*     */   
/*     */   public String getValue() {
/*  98 */     return this.value;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 106 */     return "Tag('" + getKey() + "'='" + getValue() + "')";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\Tag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */