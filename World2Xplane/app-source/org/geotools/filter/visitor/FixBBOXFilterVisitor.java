/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.spatial.BBOX;
/*     */ 
/*     */ public class FixBBOXFilterVisitor extends DuplicatingFilterVisitor {
/*     */   ReferencedEnvelope maxbbox;
/*     */   
/*     */   public FixBBOXFilterVisitor(ReferencedEnvelope fsd) {
/*  56 */     this.maxbbox = fsd;
/*     */   }
/*     */   
/*     */   public Object visit(BBOX filter, Object extraData) {
/*  62 */     Envelope bbox = null;
/*  63 */     Expression leftGeometry = filter.getExpression1();
/*  64 */     Expression rightGeometry = filter.getExpression2();
/*  65 */     boolean clipped = false;
/*  67 */     Literal le = null;
/*  68 */     if (leftGeometry != null && leftGeometry instanceof Literal) {
/*  69 */       le = (Literal)leftGeometry;
/*  70 */     } else if (rightGeometry != null && rightGeometry instanceof Literal) {
/*  71 */       le = (Literal)rightGeometry;
/*     */     } 
/*  73 */     if (le != null && le.getValue() != null && le.getValue() instanceof Geometry) {
/*  74 */       Geometry geometry = (Geometry)le.getValue();
/*  75 */       bbox = geometry.getEnvelopeInternal();
/*     */     } 
/*  77 */     if (bbox == null)
/*  79 */       return super.visit(filter, extraData); 
/*  82 */     double minx = bbox.getMinX();
/*  83 */     double miny = bbox.getMinY();
/*  84 */     double maxx = bbox.getMaxX();
/*  85 */     double maxy = bbox.getMaxY();
/*  86 */     if (this.maxbbox != null) {
/*  87 */       if (minx < this.maxbbox.getMinX()) {
/*  88 */         minx = this.maxbbox.getMinX();
/*  89 */         clipped = true;
/*     */       } 
/*  91 */       if (maxx > this.maxbbox.getMaxX()) {
/*  92 */         maxx = this.maxbbox.getMaxX();
/*  93 */         clipped = true;
/*     */       } 
/*  95 */       if (miny < this.maxbbox.getMinY()) {
/*  96 */         miny = this.maxbbox.getMinY();
/*  97 */         clipped = true;
/*     */       } 
/*  99 */       if (maxy > this.maxbbox.getMaxY()) {
/* 100 */         maxy = this.maxbbox.getMaxY();
/* 101 */         clipped = true;
/*     */       } 
/*     */     } 
/* 104 */     if (clipped) {
/* 106 */       String propertyName = filter.getPropertyName();
/* 107 */       String srs = filter.getSRS();
/* 108 */       return getFactory(extraData).bbox(propertyName, minx, miny, maxx, maxy, srs);
/*     */     } 
/* 110 */     return super.visit(filter, extraData);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\FixBBOXFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */