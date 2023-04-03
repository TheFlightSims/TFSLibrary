/*    */ package org.geotools.data.store;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import java.io.IOException;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ 
/*    */ public class EmptyFeatureCollection extends DataFeatureCollection {
/* 39 */   static ReferencedEnvelope bounds = new ReferencedEnvelope(new Envelope(), null);
/*    */   
/*    */   static {
/* 41 */     bounds.setToNull();
/*    */   }
/*    */   
/*    */   public EmptyFeatureCollection(SimpleFeatureType schema) {
/* 45 */     super(null, schema);
/*    */   }
/*    */   
/*    */   public ReferencedEnvelope getBounds() {
/* 50 */     return bounds;
/*    */   }
/*    */   
/*    */   public int getCount() throws IOException {
/* 54 */     return 0;
/*    */   }
/*    */   
/*    */   protected Iterator openIterator() {
/* 58 */     return new EmptyIterator();
/*    */   }
/*    */   
/*    */   protected void closeIterator(Iterator close) {}
/*    */   
/*    */   public boolean add(SimpleFeature object) {
/* 67 */     return false;
/*    */   }
/*    */   
/*    */   public boolean remove(Object object) {
/* 72 */     return false;
/*    */   }
/*    */   
/*    */   public boolean removeAll(Collection collection) {
/* 76 */     return false;
/*    */   }
/*    */   
/*    */   public boolean isValid() {
/* 79 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\EmptyFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */