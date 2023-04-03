/*     */ package com.world2xplane.DataStore;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.mapdb.Serializer;
/*     */ 
/*     */ public class WayInfo extends StorageItem implements Serializable {
/*  36 */   public static final Byte OUTLINE = Byte.valueOf((byte)3);
/*     */   
/*  37 */   public static final Byte OUTER = Byte.valueOf((byte)2);
/*     */   
/*  38 */   public static final Byte INNER = Byte.valueOf((byte)1);
/*     */   
/*  39 */   public static final Byte PART = Byte.valueOf((byte)0);
/*     */   
/*     */   public List<Byte> roles;
/*     */   
/*     */   public List<Long> relations;
/*     */   
/*     */   public List<Byte> getRoles() {
/*  45 */     return this.roles;
/*     */   }
/*     */   
/*     */   public void setRoles(List<Byte> roles) {
/*  49 */     this.roles = roles;
/*     */   }
/*     */   
/*     */   public List<Long> getRelations() {
/*  53 */     return this.relations;
/*     */   }
/*     */   
/*     */   public void setRelations(List<Long> relations) {
/*  57 */     this.relations = relations;
/*     */   }
/*     */   
/*     */   public static class WaySerializer implements Serializer<WayInfo>, Serializable {
/*     */     public void serialize(DataOutput out, WayInfo value) throws IOException {
/*  66 */       if (value.roles != null) {
/*  68 */         out.writeShort(value.roles.size());
/*  69 */         for (Iterator<Byte> i$ = value.roles.iterator(); i$.hasNext(); ) {
/*  69 */           byte item = ((Byte)i$.next()).byteValue();
/*  70 */           out.writeByte(item);
/*     */         } 
/*     */       } else {
/*  73 */         out.writeShort(0);
/*     */       } 
/*  75 */       if (value.relations != null) {
/*  76 */         out.writeShort(value.relations.size());
/*     */       } else {
/*  78 */         out.writeShort(0);
/*     */       } 
/*  80 */       if (value.relations != null && value.relations.size() > 0)
/*  81 */         for (Iterator<Long> i$ = value.relations.iterator(); i$.hasNext(); ) {
/*  81 */           long item = ((Long)i$.next()).longValue();
/*  82 */           StorageItem.packNumber(out, item);
/*     */         }  
/*     */     }
/*     */     
/*     */     public WayInfo deserialize(DataInput in, int available) throws IOException {
/*  89 */       WayInfo wayInfo = new WayInfo();
/*  90 */       short roleCount = in.readShort();
/*  92 */       if (roleCount > 0) {
/*  93 */         wayInfo.roles = new ArrayList<>();
/*  95 */         for (int x = 0; x < roleCount; x++)
/*  96 */           wayInfo.roles.add(Byte.valueOf(in.readByte())); 
/*     */       } 
/* 100 */       short relationCount = in.readShort();
/* 102 */       if (relationCount > 0) {
/* 103 */         wayInfo.relations = new ArrayList<>();
/* 104 */         for (int x = 0; x < relationCount; x++)
/* 105 */           wayInfo.relations.add(Long.valueOf(StorageItem.unpackNumber(in))); 
/*     */       } 
/* 108 */       return wayInfo;
/*     */     }
/*     */     
/*     */     public int fixedSize() {
/* 112 */       return -1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\DataStore\WayInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */