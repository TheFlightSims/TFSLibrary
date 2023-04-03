/*     */ package com.world2xplane.Network;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import math.geom2d.Point2D;
/*     */ import org.mapdb.Serializer;
/*     */ 
/*     */ public class Junction implements Externalizable {
/*     */   private long id;
/*     */   
/*     */   private double lon;
/*     */   
/*     */   private double lat;
/*     */   
/*  37 */   private long junctionId = 0L;
/*     */   
/*  38 */   private short junctionCount = 1;
/*     */   
/*  40 */   public List<Long> roadIds = new ArrayList<>();
/*     */   
/*     */   public boolean equals(Object other) {
/*  43 */     if (other == null)
/*  43 */       return false; 
/*  44 */     if (other == this)
/*  44 */       return true; 
/*  45 */     if (!(other instanceof Junction))
/*  45 */       return false; 
/*  46 */     Junction otherMyClass = (Junction)other;
/*  47 */     return (otherMyClass.id == this.id);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  52 */     return (new Long(this.id)).hashCode();
/*     */   }
/*     */   
/*     */   public long getId() {
/*  56 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(long id) {
/*  60 */     this.id = id;
/*     */   }
/*     */   
/*     */   public double getLon() {
/*  64 */     return this.lon;
/*     */   }
/*     */   
/*     */   public void setLon(double lon) {
/*  68 */     this.lon = lon;
/*     */   }
/*     */   
/*     */   public double getLat() {
/*  72 */     return this.lat;
/*     */   }
/*     */   
/*     */   public void setLat(double lat) {
/*  76 */     this.lat = lat;
/*     */   }
/*     */   
/*     */   public Long getJunctionId() {
/*  80 */     return Long.valueOf(this.junctionId);
/*     */   }
/*     */   
/*     */   public void setJunctionId(Long junctionId) {
/*  84 */     this.junctionId = junctionId.longValue();
/*     */   }
/*     */   
/*     */   public Junction upCount() {
/*  88 */     this.junctionCount = (short)(this.junctionCount + 1);
/*  89 */     return this;
/*     */   }
/*     */   
/*     */   public int getJunctionCount() {
/*  93 */     return this.junctionCount;
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/*  98 */     out.writeLong(this.id);
/*  99 */     out.writeDouble(this.lon);
/* 100 */     out.writeDouble(this.lat);
/* 101 */     out.writeLong(this.junctionId);
/* 102 */     out.writeInt(this.junctionCount);
/* 104 */     out.writeInt(this.roadIds.size());
/* 105 */     for (Iterator<Long> i$ = this.roadIds.iterator(); i$.hasNext(); ) {
/* 105 */       long item = ((Long)i$.next()).longValue();
/* 106 */       out.writeLong(item);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 112 */     this.id = in.readLong();
/* 113 */     this.lon = in.readDouble();
/* 114 */     this.lat = in.readDouble();
/* 115 */     this.junctionId = in.readLong();
/* 116 */     this.junctionCount = in.readShort();
/* 118 */     int roadC = in.readInt();
/* 119 */     for (int x = 0; x < roadC; x++)
/* 120 */       this.roadIds.add(Long.valueOf(in.readLong())); 
/*     */   }
/*     */   
/*     */   public static class JunctionSerializer implements Serializer<Junction>, Serializable {
/*     */     public void serialize(DataOutput out, Junction value) throws IOException {
/* 128 */       out.writeLong(value.id);
/* 129 */       out.writeDouble(value.lon);
/* 130 */       out.writeDouble(value.lat);
/* 131 */       out.writeLong(value.junctionId);
/* 132 */       out.writeInt(value.junctionCount);
/* 134 */       out.writeInt(value.roadIds.size());
/* 135 */       for (Iterator<Long> i$ = value.roadIds.iterator(); i$.hasNext(); ) {
/* 135 */         long item = ((Long)i$.next()).longValue();
/* 136 */         out.writeLong(item);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Junction deserialize(DataInput in, int available) throws IOException {
/* 143 */       Junction junction = new Junction();
/* 144 */       junction.id = in.readLong();
/* 145 */       junction.lon = in.readDouble();
/* 146 */       junction.lat = in.readDouble();
/* 147 */       junction.junctionId = in.readLong();
/* 148 */       junction.junctionCount = in.readShort();
/* 150 */       int roadC = in.readInt();
/* 151 */       for (int x = 0; x < roadC; x++)
/* 152 */         junction.roadIds.add(Long.valueOf(in.readLong())); 
/* 155 */       return junction;
/*     */     }
/*     */     
/*     */     public int fixedSize() {
/* 159 */       return -1;
/*     */     }
/*     */   }
/*     */   
/*     */   public List<Long> getRoadIds() {
/* 167 */     return this.roadIds;
/*     */   }
/*     */   
/*     */   public Point2D getPoint() {
/* 171 */     return new Point2D(this.lon, this.lat);
/*     */   }
/*     */   
/*     */   public void setJunctionCount(short junctionCount) {
/* 175 */     this.junctionCount = junctionCount;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Network\Junction.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */