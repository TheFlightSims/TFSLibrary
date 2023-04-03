/*    */ package org.geotools.data.store;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.geotools.data.DataUtilities;
/*    */ import org.geotools.data.FeatureReader;
/*    */ import org.geotools.data.collection.DelegateFeatureReader;
/*    */ import org.geotools.data.simple.SimpleFeatureCollection;
/*    */ import org.geotools.data.simple.SimpleFeatureIterator;
/*    */ import org.geotools.feature.FeatureCollection;
/*    */ import org.geotools.feature.FeatureIterator;
/*    */ import org.geotools.feature.collection.DecoratingSimpleFeatureCollection;
/*    */ import org.geotools.feature.visitor.FeatureAttributeVisitor;
/*    */ import org.geotools.filter.FilterAttributeExtractor;
/*    */ import org.opengis.feature.FeatureVisitor;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ import org.opengis.feature.type.AttributeDescriptor;
/*    */ import org.opengis.feature.type.FeatureType;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.expression.ExpressionVisitor;
/*    */ import org.opengis.filter.expression.PropertyName;
/*    */ 
/*    */ public class ReTypingFeatureCollection extends DecoratingSimpleFeatureCollection {
/*    */   SimpleFeatureType featureType;
/*    */   
/*    */   public ReTypingFeatureCollection(FeatureCollection<SimpleFeatureType, SimpleFeature> delegate, SimpleFeatureType featureType) {
/* 50 */     this(DataUtilities.simple(delegate), featureType);
/*    */   }
/*    */   
/*    */   public ReTypingFeatureCollection(SimpleFeatureCollection delegate, SimpleFeatureType featureType) {
/* 53 */     super(delegate);
/* 54 */     this.featureType = featureType;
/*    */   }
/*    */   
/*    */   public SimpleFeatureType getSchema() {
/* 58 */     return this.featureType;
/*    */   }
/*    */   
/*    */   public FeatureReader<SimpleFeatureType, SimpleFeature> reader() throws IOException {
/* 62 */     return (FeatureReader<SimpleFeatureType, SimpleFeature>)new DelegateFeatureReader((FeatureType)getSchema(), (FeatureIterator)features());
/*    */   }
/*    */   
/*    */   public SimpleFeatureIterator features() {
/* 66 */     return new ReTypingFeatureIterator(this.delegate.features(), (SimpleFeatureType)this.delegate.getSchema(), this.featureType);
/*    */   }
/*    */   
/*    */   protected boolean canDelegate(FeatureVisitor visitor) {
/* 71 */     if (visitor instanceof FeatureAttributeVisitor) {
/* 73 */       FilterAttributeExtractor extractor = new FilterAttributeExtractor(this.featureType);
/* 74 */       for (Expression e : ((FeatureAttributeVisitor)visitor).getExpressions())
/* 75 */         e.accept((ExpressionVisitor)extractor, null); 
/* 78 */       for (PropertyName pname : extractor.getPropertyNameSet()) {
/* 79 */         AttributeDescriptor att = (AttributeDescriptor)pname.evaluate(this.featureType);
/* 80 */         if (att == null)
/* 81 */           return false; 
/*    */       } 
/* 84 */       return true;
/*    */     } 
/* 86 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\ReTypingFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */