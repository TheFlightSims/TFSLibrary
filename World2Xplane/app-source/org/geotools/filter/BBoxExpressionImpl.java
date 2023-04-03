/*     */ package org.geotools.filter;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.TopologyException;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ 
/*     */ public class BBoxExpressionImpl extends LiteralExpressionImpl implements BBoxExpression {
/*  47 */   private GeometryFactory gfac = new GeometryFactory();
/*     */   
/*     */   protected BBoxExpressionImpl() throws IllegalFilterException {
/*  55 */     this(new Envelope());
/*     */   }
/*     */   
/*     */   protected BBoxExpressionImpl(Envelope env) throws IllegalFilterException {
/*  66 */     this.expressionType = 104;
/*  67 */     setBounds(env);
/*     */   }
/*     */   
/*     */   public final void setBounds(Envelope env) throws IllegalFilterException {
/*  82 */     Coordinate[] coords = new Coordinate[5];
/*  83 */     coords[0] = new Coordinate(env.getMinX(), env.getMinY());
/*  84 */     coords[1] = new Coordinate(env.getMinX(), env.getMaxY());
/*  85 */     coords[2] = new Coordinate(env.getMaxX(), env.getMaxY());
/*  86 */     coords[3] = new Coordinate(env.getMaxX(), env.getMinY());
/*  87 */     coords[4] = new Coordinate(env.getMinX(), env.getMinY());
/*  89 */     LinearRing ring = null;
/*     */     try {
/*  92 */       ring = this.gfac.createLinearRing(coords);
/*  93 */     } catch (TopologyException tex) {
/*  94 */       throw new IllegalFilterException(tex.toString());
/*     */     } 
/*  97 */     Polygon polygon = this.gfac.createPolygon(ring, null);
/*  98 */     if (env instanceof ReferencedEnvelope) {
/*  99 */       ReferencedEnvelope refEnv = (ReferencedEnvelope)env;
/* 100 */       polygon.setUserData(refEnv.getCoordinateReferenceSystem());
/*     */     } 
/* 102 */     setValue(polygon);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\BBoxExpressionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */