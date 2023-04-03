/*    */ package org.geotools.data;
/*    */ 
/*    */ import org.opengis.feature.type.AttributeDescriptor;
/*    */ 
/*    */ public abstract class AbstractAttributeIO {
/*    */   protected AttributeDescriptor[] metaData;
/*    */   
/*    */   protected AbstractAttributeIO(AttributeDescriptor[] metaData) {
/* 35 */     this.metaData = metaData;
/*    */   }
/*    */   
/*    */   protected AbstractAttributeIO(AttributeReader defs) {
/* 42 */     this(copy(defs));
/*    */   }
/*    */   
/*    */   public static AttributeDescriptor[] copy(AttributeReader defs) {
/* 46 */     AttributeDescriptor[] d = new AttributeDescriptor[defs.getAttributeCount()];
/* 47 */     for (int i = 0, ii = d.length; i < ii; i++)
/* 48 */       d[i] = defs.getAttributeType(i); 
/* 50 */     return d;
/*    */   }
/*    */   
/*    */   public final int getAttributeCount() {
/* 54 */     return this.metaData.length;
/*    */   }
/*    */   
/*    */   public final AttributeDescriptor getAttributeType(int position) {
/* 58 */     return this.metaData[position];
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\AbstractAttributeIO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */