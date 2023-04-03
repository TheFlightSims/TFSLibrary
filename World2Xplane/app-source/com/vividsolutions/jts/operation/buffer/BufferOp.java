/*     */ package com.vividsolutions.jts.operation.buffer;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import com.vividsolutions.jts.geom.TopologyException;
/*     */ import com.vividsolutions.jts.math.MathUtil;
/*     */ import com.vividsolutions.jts.noding.Noder;
/*     */ import com.vividsolutions.jts.noding.ScaledNoder;
/*     */ import com.vividsolutions.jts.noding.snapround.MCIndexSnapRounder;
/*     */ 
/*     */ public class BufferOp {
/*     */   public static final int CAP_ROUND = 1;
/*     */   
/*     */   public static final int CAP_BUTT = 2;
/*     */   
/*     */   public static final int CAP_FLAT = 2;
/*     */   
/*     */   public static final int CAP_SQUARE = 3;
/*     */   
/* 106 */   private static int MAX_PRECISION_DIGITS = 12;
/*     */   
/*     */   private Geometry argGeom;
/*     */   
/*     */   private double distance;
/*     */   
/*     */   private static double precisionScaleFactor(Geometry g, double distance, int maxPrecisionDigits) {
/* 129 */     Envelope env = g.getEnvelopeInternal();
/* 130 */     double envMax = MathUtil.max(Math.abs(env.getMaxX()), Math.abs(env.getMaxY()), Math.abs(env.getMinX()), Math.abs(env.getMinY()));
/* 137 */     double expandByDistance = (distance > 0.0D) ? distance : 0.0D;
/* 138 */     double bufEnvMax = envMax + 2.0D * expandByDistance;
/* 141 */     int bufEnvPrecisionDigits = (int)(Math.log(bufEnvMax) / Math.log(10.0D) + 1.0D);
/* 142 */     int minUnitLog10 = maxPrecisionDigits - bufEnvPrecisionDigits;
/* 144 */     double scaleFactor = Math.pow(10.0D, minUnitLog10);
/* 145 */     return scaleFactor;
/*     */   }
/*     */   
/*     */   private static double OLDprecisionScaleFactor(Geometry g, double distance, int maxPrecisionDigits) {
/* 152 */     Envelope env = g.getEnvelopeInternal();
/* 153 */     double envSize = Math.max(env.getHeight(), env.getWidth());
/* 154 */     double expandByDistance = (distance > 0.0D) ? distance : 0.0D;
/* 155 */     double bufEnvSize = envSize + 2.0D * expandByDistance;
/* 158 */     int bufEnvLog10 = (int)(Math.log(bufEnvSize) / Math.log(10.0D) + 1.0D);
/* 159 */     int minUnitLog10 = bufEnvLog10 - maxPrecisionDigits;
/* 161 */     double scaleFactor = Math.pow(10.0D, -minUnitLog10);
/* 162 */     return scaleFactor;
/*     */   }
/*     */   
/*     */   public static Geometry bufferOp(Geometry g, double distance) {
/* 174 */     BufferOp gBuf = new BufferOp(g);
/* 175 */     Geometry geomBuf = gBuf.getResultGeometry(distance);
/* 178 */     return geomBuf;
/*     */   }
/*     */   
/*     */   public static Geometry bufferOp(Geometry g, double distance, BufferParameters params) {
/* 193 */     BufferOp bufOp = new BufferOp(g, params);
/* 194 */     Geometry geomBuf = bufOp.getResultGeometry(distance);
/* 195 */     return geomBuf;
/*     */   }
/*     */   
/*     */   public static Geometry bufferOp(Geometry g, double distance, int quadrantSegments) {
/* 210 */     BufferOp bufOp = new BufferOp(g);
/* 211 */     bufOp.setQuadrantSegments(quadrantSegments);
/* 212 */     Geometry geomBuf = bufOp.getResultGeometry(distance);
/* 213 */     return geomBuf;
/*     */   }
/*     */   
/*     */   public static Geometry bufferOp(Geometry g, double distance, int quadrantSegments, int endCapStyle) {
/* 232 */     BufferOp bufOp = new BufferOp(g);
/* 233 */     bufOp.setQuadrantSegments(quadrantSegments);
/* 234 */     bufOp.setEndCapStyle(endCapStyle);
/* 235 */     Geometry geomBuf = bufOp.getResultGeometry(distance);
/* 236 */     return geomBuf;
/*     */   }
/*     */   
/* 242 */   private BufferParameters bufParams = new BufferParameters();
/*     */   
/* 244 */   private Geometry resultGeometry = null;
/*     */   
/*     */   private RuntimeException saveException;
/*     */   
/*     */   public BufferOp(Geometry g) {
/* 253 */     this.argGeom = g;
/*     */   }
/*     */   
/*     */   public BufferOp(Geometry g, BufferParameters bufParams) {
/* 264 */     this.argGeom = g;
/* 265 */     this.bufParams = bufParams;
/*     */   }
/*     */   
/*     */   public void setEndCapStyle(int endCapStyle) {
/* 277 */     this.bufParams.setEndCapStyle(endCapStyle);
/*     */   }
/*     */   
/*     */   public void setQuadrantSegments(int quadrantSegments) {
/* 287 */     this.bufParams.setQuadrantSegments(quadrantSegments);
/*     */   }
/*     */   
/*     */   public Geometry getResultGeometry(double distance) {
/* 298 */     this.distance = distance;
/* 299 */     computeGeometry();
/* 300 */     return this.resultGeometry;
/*     */   }
/*     */   
/*     */   private void computeGeometry() {
/* 305 */     bufferOriginalPrecision();
/* 306 */     if (this.resultGeometry != null)
/*     */       return; 
/* 308 */     PrecisionModel argPM = this.argGeom.getFactory().getPrecisionModel();
/* 309 */     if (argPM.getType() == PrecisionModel.FIXED) {
/* 310 */       bufferFixedPrecision(argPM);
/*     */     } else {
/* 312 */       bufferReducedPrecision();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void bufferReducedPrecision() {
/* 318 */     for (int precDigits = MAX_PRECISION_DIGITS; precDigits >= 0; precDigits--) {
/*     */       try {
/* 320 */         bufferReducedPrecision(precDigits);
/* 322 */       } catch (TopologyException ex) {
/* 324 */         this.saveException = (RuntimeException)ex;
/*     */       } 
/* 327 */       if (this.resultGeometry != null)
/*     */         return; 
/*     */     } 
/* 331 */     throw this.saveException;
/*     */   }
/*     */   
/*     */   private void bufferOriginalPrecision() {
/*     */     try {
/* 338 */       BufferBuilder bufBuilder = new BufferBuilder(this.bufParams);
/* 339 */       this.resultGeometry = bufBuilder.buffer(this.argGeom, this.distance);
/* 341 */     } catch (RuntimeException ex) {
/* 342 */       this.saveException = ex;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void bufferReducedPrecision(int precisionDigits) {
/* 352 */     double sizeBasedScaleFactor = precisionScaleFactor(this.argGeom, this.distance, precisionDigits);
/* 355 */     PrecisionModel fixedPM = new PrecisionModel(sizeBasedScaleFactor);
/* 356 */     bufferFixedPrecision(fixedPM);
/*     */   }
/*     */   
/*     */   private void bufferFixedPrecision(PrecisionModel fixedPM) {
/* 361 */     ScaledNoder scaledNoder = new ScaledNoder((Noder)new MCIndexSnapRounder(new PrecisionModel(1.0D)), fixedPM.getScale());
/* 364 */     BufferBuilder bufBuilder = new BufferBuilder(this.bufParams);
/* 365 */     bufBuilder.setWorkingPrecisionModel(fixedPM);
/* 366 */     bufBuilder.setNoder((Noder)scaledNoder);
/* 368 */     this.resultGeometry = bufBuilder.buffer(this.argGeom, this.distance);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\BufferOp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */