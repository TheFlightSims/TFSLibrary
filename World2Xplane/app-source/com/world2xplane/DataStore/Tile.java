/*    */ package com.world2xplane.DataStore;
/*    */ 
/*    */ import java.io.Externalizable;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInput;
/*    */ import java.io.ObjectOutput;
/*    */ 
/*    */ public class Tile extends StorageItem implements Externalizable {
/*    */   public short lon;
/*    */   
/*    */   public short lat;
/*    */   
/*    */   public short getLon() {
/* 32 */     return this.lon;
/*    */   }
/*    */   
/*    */   public void setLon(short lon) {
/* 36 */     this.lon = lon;
/*    */   }
/*    */   
/*    */   public short getLat() {
/* 40 */     return this.lat;
/*    */   }
/*    */   
/*    */   public void setLat(short lat) {
/* 44 */     this.lat = lat;
/*    */   }
/*    */   
/*    */   public boolean equals(Object other) {
/* 48 */     if (other == null || !(other instanceof Tile))
/* 49 */       return false; 
/* 51 */     return (this.lon == ((Tile)other).lon && this.lat == ((Tile)other).lat);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 55 */     return (new Short(this.lon)).hashCode() + (new Short(this.lat)).hashCode();
/*    */   }
/*    */   
/*    */   public void writeExternal(ObjectOutput objectOutput) throws IOException {
/* 59 */     objectOutput.writeShort(this.lon);
/* 60 */     objectOutput.writeShort(this.lat);
/*    */   }
/*    */   
/*    */   public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
/* 64 */     this.lon = objectInput.readShort();
/* 65 */     this.lat = objectInput.readShort();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\DataStore\Tile.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */