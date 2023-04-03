/*    */ package org.geotools.data.collection;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.geotools.data.DataSourceException;
/*    */ import org.geotools.data.FeatureReader;
/*    */ import org.geotools.feature.FeatureIterator;
/*    */ import org.geotools.feature.IllegalAttributeException;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.type.FeatureType;
/*    */ 
/*    */ public class DelegateFeatureReader<T extends FeatureType, F extends Feature> implements FeatureReader<T, F> {
/*    */   FeatureIterator<F> delegate;
/*    */   
/*    */   T schema;
/*    */   
/*    */   public DelegateFeatureReader(T featureType, FeatureIterator<F> features) {
/* 49 */     this.schema = featureType;
/* 50 */     this.delegate = features;
/*    */   }
/*    */   
/*    */   public T getFeatureType() {
/* 54 */     return this.schema;
/*    */   }
/*    */   
/*    */   public F next() throws IOException, IllegalAttributeException, NoSuchElementException {
/* 58 */     if (this.delegate == null)
/* 59 */       throw new IOException("Feature Reader has been closed"); 
/*    */     try {
/* 62 */       return (F)this.delegate.next();
/* 65 */     } catch (NoSuchElementException end) {
/* 66 */       throw new DataSourceException("There are no more Features", end);
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean hasNext() throws IOException {
/* 71 */     return (this.delegate != null && this.delegate.hasNext());
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 75 */     if (this.delegate != null)
/* 75 */       this.delegate.close(); 
/* 76 */     this.delegate = null;
/* 77 */     this.schema = null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\collection\DelegateFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */