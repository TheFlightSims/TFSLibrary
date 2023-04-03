/*     */ package org.openstreetmap.osmosis.core.domain.v0_6;
/*     */ 
/*     */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*     */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*     */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*     */ import org.openstreetmap.osmosis.core.store.Storeable;
/*     */ 
/*     */ public class RelationMember implements Comparable<RelationMember>, Storeable {
/*     */   private long memberId;
/*     */   
/*     */   private EntityType memberType;
/*     */   
/*     */   private String memberRole;
/*     */   
/*     */   public RelationMember(long memberId, EntityType memberType, String memberRole) {
/*  33 */     this.memberId = memberId;
/*  34 */     this.memberType = memberType;
/*  35 */     this.memberRole = memberRole;
/*  36 */     if (memberType == null)
/*  37 */       throw new IllegalArgumentException("null type given for relation-member"); 
/*  39 */     if (memberRole == null)
/*  40 */       throw new IllegalArgumentException("null role given for relation-member"); 
/*     */   }
/*     */   
/*     */   public RelationMember(StoreReader sr, StoreClassRegister scr) {
/*  55 */     this(sr.readLong(), EntityType.valueOf(sr.readString()), sr.readString());
/*     */   }
/*     */   
/*     */   public void store(StoreWriter sw, StoreClassRegister scr) {
/*  67 */     sw.writeLong(this.memberId);
/*  68 */     sw.writeString(this.memberType.toString());
/*  69 */     sw.writeString(this.memberRole);
/*     */   }
/*     */   
/*     */   public int compareTo(RelationMember relationMember) {
/*  87 */     long result = this.memberType.compareTo(relationMember.memberType);
/*  88 */     if (result > 0L)
/*  89 */       return 1; 
/*  90 */     if (result < 0L)
/*  91 */       return -1; 
/*  95 */     result = this.memberId - relationMember.memberId;
/*  96 */     if (result > 0L)
/*  97 */       return 1; 
/*  98 */     if (result < 0L)
/*  99 */       return -1; 
/* 103 */     result = this.memberRole.compareTo(relationMember.memberRole);
/* 104 */     if (result > 0L)
/* 105 */       return 1; 
/* 106 */     if (result < 0L)
/* 107 */       return -1; 
/* 111 */     return 0;
/*     */   }
/*     */   
/*     */   public long getMemberId() {
/* 121 */     return this.memberId;
/*     */   }
/*     */   
/*     */   public EntityType getMemberType() {
/* 131 */     return this.memberType;
/*     */   }
/*     */   
/*     */   public String getMemberRole() {
/* 141 */     return this.memberRole;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 149 */     return "RelationMember(" + getMemberType() + " with id " + getMemberId() + " in the role '" + getMemberRole() + "')";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\RelationMember.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */