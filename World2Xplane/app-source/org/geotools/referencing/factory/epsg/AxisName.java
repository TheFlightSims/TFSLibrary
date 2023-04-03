/*    */ package org.geotools.referencing.factory.epsg;
/*    */ 
/*    */ import org.geotools.util.Utilities;
/*    */ 
/*    */ final class AxisName {
/*    */   public final String name;
/*    */   
/*    */   public final String description;
/*    */   
/*    */   public AxisName(String name, String description) {
/* 45 */     this.name = name;
/* 46 */     this.description = description;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 54 */     return this.name.hashCode();
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 62 */     if (object instanceof AxisName) {
/* 63 */       AxisName that = (AxisName)object;
/* 64 */       return (Utilities.equals(this.name, that.name) && Utilities.equals(this.description, that.description));
/*    */     } 
/* 67 */     return false;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 75 */     return this.name;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\AxisName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */