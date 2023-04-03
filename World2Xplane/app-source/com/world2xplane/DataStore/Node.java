/*    */ package com.world2xplane.DataStore;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.Externalizable;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInput;
/*    */ import java.io.ObjectOutput;
/*    */ import java.io.Serializable;
/*    */ import org.mapdb.Serializer;
/*    */ 
/*    */ public class Node extends StorageItem implements Externalizable {
/*    */   public Double lon;
/*    */   
/*    */   public Double lat;
/*    */   
/*    */   public Node(Double lon, Double lat) {
/* 34 */     this.lon = lon;
/* 35 */     this.lat = lat;
/*    */   }
/*    */   
/*    */   public Node() {
/* 39 */     this.lon = Double.valueOf(0.0D);
/* 40 */     this.lat = Double.valueOf(0.0D);
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 45 */     if (this == o)
/* 45 */       return true; 
/* 46 */     if (o == null || getClass() != o.getClass())
/* 46 */       return false; 
/* 48 */     Node node = (Node)o;
/* 50 */     return (node.lon.equals(this.lon) && node.lat.equals(this.lat));
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 54 */     return 7 * this.lon.hashCode() + 7 + 7 * this.lat.hashCode();
/*    */   }
/*    */   
/*    */   public void writeExternal(ObjectOutput output) throws IOException {
/* 58 */     output.writeDouble(this.lon.doubleValue());
/* 59 */     output.writeDouble(this.lat.doubleValue());
/*    */   }
/*    */   
/*    */   public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
/* 64 */     this.lon = Double.valueOf(objectInput.readDouble());
/* 65 */     this.lat = Double.valueOf(objectInput.readDouble());
/*    */   }
/*    */   
/*    */   public static class NodeSerializer implements Serializer<Node>, Serializable {
/*    */     public void serialize(DataOutput out, Node value) throws IOException {
/* 72 */       out.writeDouble(value.lon.doubleValue());
/* 73 */       out.writeDouble(value.lat.doubleValue());
/*    */     }
/*    */     
/*    */     public Node deserialize(DataInput in, int available) throws IOException {
/* 78 */       return new Node(Double.valueOf(in.readDouble()), Double.valueOf(in.readDouble()));
/*    */     }
/*    */     
/*    */     public int fixedSize() {
/* 82 */       return -1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\DataStore\Node.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */