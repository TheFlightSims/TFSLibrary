/*     */ package com.world2xplane.DataStore;
/*     */ 
/*     */ import com.world2xplane.Rules.AcceptingRule;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.mapdb.Serializer;
/*     */ 
/*     */ public class RelationInfo extends StorageItem implements Externalizable {
/*     */   public List<AcceptingRule> rules;
/*     */   
/*     */   public List<Tile> tiles;
/*     */   
/*     */   public boolean declined;
/*     */   
/*     */   public boolean wayAccepted;
/*     */   
/*     */   public Float height;
/*     */   
/*     */   public boolean buildingPart;
/*     */   
/*     */   public Integer customFacade;
/*     */   
/*     */   public boolean isDeclined() {
/*  62 */     return this.declined;
/*     */   }
/*     */   
/*     */   public void setDeclined(boolean declined) {
/*  66 */     this.declined = declined;
/*     */   }
/*     */   
/*     */   public List<Tile> getTiles() {
/*  70 */     return this.tiles;
/*     */   }
/*     */   
/*     */   public void setTiles(List<Tile> tiles) {
/*  74 */     this.tiles = tiles;
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput objectOutput) throws IOException {
/*  80 */     if (this.rules != null) {
/*  81 */       objectOutput.writeShort(this.rules.size());
/*     */     } else {
/*  83 */       objectOutput.writeShort(0);
/*     */     } 
/*  85 */     if (this.rules != null && this.rules.size() > 0)
/*  86 */       for (AcceptingRule item : this.rules) {
/*  87 */         objectOutput.writeShort(item.ruleNumber.shortValue());
/*  88 */         objectOutput.writeByte(item.filterNumber.byteValue());
/*     */       }  
/*  94 */     if (this.tiles != null) {
/*  95 */       objectOutput.writeByte(this.tiles.size());
/*     */     } else {
/*  97 */       objectOutput.writeByte(0);
/*     */     } 
/*  99 */     if (this.tiles != null && this.tiles.size() > 0)
/* 100 */       for (Tile item : this.tiles) {
/* 101 */         objectOutput.writeShort(item.lon);
/* 102 */         objectOutput.writeShort(item.lat);
/*     */       }  
/* 106 */     objectOutput.writeBoolean(this.declined);
/* 107 */     if (this.height == null) {
/* 108 */       objectOutput.writeByte(0);
/*     */     } else {
/* 110 */       objectOutput.writeByte(1);
/* 111 */       objectOutput.writeFloat(this.height.floatValue());
/*     */     } 
/* 114 */     objectOutput.writeBoolean(this.buildingPart);
/* 117 */     if (this.customFacade == null) {
/* 118 */       objectOutput.writeByte(0);
/*     */     } else {
/* 120 */       objectOutput.writeByte(1);
/* 121 */       objectOutput.writeInt(this.customFacade.intValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
/* 127 */     int ruleCount = objectInput.readShort();
/* 128 */     if (ruleCount > 0) {
/* 129 */       this.rules = new ArrayList<>();
/* 130 */       for (int x = 0; x < ruleCount; x++)
/* 131 */         this.rules.add(new AcceptingRule(Short.valueOf(objectInput.readShort()), Byte.valueOf(objectInput.readByte()))); 
/*     */     } 
/* 136 */     byte tileCount = objectInput.readByte();
/* 137 */     if (tileCount > 0) {
/* 138 */       this.tiles = new ArrayList<>();
/* 139 */       for (int x = 0; x < tileCount; x++) {
/* 140 */         Tile tile = new Tile();
/* 141 */         tile.lon = objectInput.readShort();
/* 142 */         tile.lat = objectInput.readShort();
/* 143 */         this.tiles.add(tile);
/*     */       } 
/*     */     } 
/* 147 */     this.declined = objectInput.readBoolean();
/* 149 */     byte hasHeight = objectInput.readByte();
/* 150 */     if (hasHeight == 1)
/* 151 */       this.height = Float.valueOf(objectInput.readFloat()); 
/* 154 */     this.buildingPart = objectInput.readBoolean();
/* 156 */     byte hasFacade = objectInput.readByte();
/* 157 */     if (hasFacade == 1)
/* 158 */       this.customFacade = Integer.valueOf(objectInput.readInt()); 
/*     */   }
/*     */   
/*     */   public static class RelationSerializer implements Serializer<RelationInfo>, Serializable {
/*     */     public void serialize(DataOutput objectOutput, RelationInfo value) throws IOException {
/* 168 */       if (value.rules != null) {
/* 169 */         objectOutput.writeShort(value.rules.size());
/*     */       } else {
/* 171 */         objectOutput.writeShort(0);
/*     */       } 
/* 173 */       if (value.rules != null && value.rules.size() > 0)
/* 174 */         for (AcceptingRule item : value.rules) {
/* 175 */           objectOutput.writeShort(item.ruleNumber.shortValue());
/* 176 */           objectOutput.writeByte(item.filterNumber.byteValue());
/*     */         }  
/* 182 */       if (value.tiles != null) {
/* 183 */         objectOutput.writeByte(value.tiles.size());
/*     */       } else {
/* 185 */         objectOutput.writeByte(0);
/*     */       } 
/* 187 */       if (value.tiles != null && value.tiles.size() > 0)
/* 188 */         for (Tile item : value.tiles) {
/* 189 */           objectOutput.writeShort(item.lon);
/* 190 */           objectOutput.writeShort(item.lat);
/*     */         }  
/* 194 */       objectOutput.writeBoolean(value.declined);
/* 195 */       if (value.height == null) {
/* 196 */         objectOutput.writeByte(0);
/*     */       } else {
/* 198 */         objectOutput.writeByte(1);
/* 199 */         objectOutput.writeFloat(value.height.floatValue());
/*     */       } 
/* 202 */       if (value.customFacade == null) {
/* 203 */         objectOutput.writeByte(0);
/*     */       } else {
/* 205 */         objectOutput.writeByte(1);
/* 206 */         objectOutput.writeInt(value.customFacade.intValue());
/*     */       } 
/*     */     }
/*     */     
/*     */     public RelationInfo deserialize(DataInput objectInput, int available) throws IOException {
/* 211 */       RelationInfo relationInfo = new RelationInfo();
/* 214 */       int ruleCount = objectInput.readShort();
/* 215 */       if (ruleCount > 0) {
/* 216 */         relationInfo.rules = new ArrayList<>();
/* 217 */         for (int x = 0; x < ruleCount; x++)
/* 218 */           relationInfo.rules.add(new AcceptingRule(Short.valueOf(objectInput.readShort()), Byte.valueOf(objectInput.readByte()))); 
/*     */       } 
/* 223 */       byte tileCount = objectInput.readByte();
/* 224 */       if (tileCount > 0) {
/* 225 */         relationInfo.tiles = new ArrayList<>();
/* 226 */         for (int x = 0; x < tileCount; x++) {
/* 227 */           Tile tile = new Tile();
/* 228 */           tile.lon = objectInput.readShort();
/* 229 */           tile.lat = objectInput.readShort();
/* 230 */           relationInfo.tiles.add(tile);
/*     */         } 
/*     */       } 
/* 234 */       relationInfo.declined = objectInput.readBoolean();
/* 236 */       byte hasHeight = objectInput.readByte();
/* 237 */       if (hasHeight == 1)
/* 238 */         relationInfo.height = Float.valueOf(objectInput.readFloat()); 
/* 241 */       byte hasFacade = objectInput.readByte();
/* 242 */       if (hasFacade == 1)
/* 243 */         relationInfo.customFacade = Integer.valueOf(objectInput.readInt()); 
/* 245 */       return relationInfo;
/*     */     }
/*     */     
/*     */     public int fixedSize() {
/* 249 */       return -1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\DataStore\RelationInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */