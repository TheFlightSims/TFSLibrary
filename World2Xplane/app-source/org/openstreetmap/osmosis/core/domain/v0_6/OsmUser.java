/*     */ package org.openstreetmap.osmosis.core.domain.v0_6;
/*     */ 
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*     */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*     */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*     */ import org.openstreetmap.osmosis.core.store.Storeable;
/*     */ 
/*     */ public class OsmUser implements Storeable {
/*     */   private String name;
/*     */   
/*     */   private int id;
/*     */   
/*     */   private static final int USER_ID_NONE = -1;
/*     */   
/*  35 */   public static final OsmUser NONE = new OsmUser(-1, "");
/*     */   
/*     */   public OsmUser(int id, String userName) {
/*  47 */     if (userName == null)
/*  48 */       throw new NullPointerException("The user name cannot be null."); 
/*  52 */     if (NONE != null && id == -1)
/*  53 */       throw new OsmosisRuntimeException("A user id of -1 is not permitted."); 
/*  56 */     this.name = userName;
/*  57 */     this.id = id;
/*     */   }
/*     */   
/*     */   public OsmUser(StoreReader sr, StoreClassRegister scr) {
/*  71 */     this.name = sr.readString();
/*  72 */     this.id = sr.readInteger();
/*     */   }
/*     */   
/*     */   public void store(StoreWriter sw, StoreClassRegister scr) {
/*  86 */     sw.writeString(this.name);
/*  87 */     sw.writeInteger(this.id);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  98 */     if (!(o instanceof OsmUser))
/*  99 */       return false; 
/* 102 */     OsmUser ou = (OsmUser)o;
/* 104 */     return (this.name.equals(ou.name) && this.id == ou.id);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 115 */     int result = -17;
/* 116 */     result = 31 * result + this.name.hashCode();
/* 117 */     result = 31 * result + this.id;
/* 119 */     return result;
/*     */   }
/*     */   
/*     */   public int getId() {
/* 127 */     return this.id;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 135 */     return this.name;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\OsmUser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */