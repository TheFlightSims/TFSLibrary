/*     */ package com.vividsolutions.jts.operation.buffer.validate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ 
/*     */ public class BufferResultValidator {
/*     */   private static boolean VERBOSE = false;
/*     */   
/*     */   private static final double MAX_ENV_DIFF_FRAC = 0.012D;
/*     */   
/*     */   private Geometry input;
/*     */   
/*     */   private double distance;
/*     */   
/*     */   private Geometry result;
/*     */   
/*     */   public static boolean isValid(Geometry g, double distance, Geometry result) {
/*  64 */     BufferResultValidator validator = new BufferResultValidator(g, distance, result);
/*  65 */     if (validator.isValid())
/*  66 */       return true; 
/*  67 */     return false;
/*     */   }
/*     */   
/*     */   public static String isValidMsg(Geometry g, double distance, Geometry result) {
/*  82 */     BufferResultValidator validator = new BufferResultValidator(g, distance, result);
/*  83 */     if (!validator.isValid())
/*  84 */       return validator.getErrorMessage(); 
/*  85 */     return null;
/*     */   }
/*     */   
/*     */   private boolean isValid = true;
/*     */   
/*  92 */   private String errorMsg = null;
/*     */   
/*  93 */   private Coordinate errorLocation = null;
/*     */   
/*  94 */   private Geometry errorIndicator = null;
/*     */   
/*     */   public BufferResultValidator(Geometry input, double distance, Geometry result) {
/*  98 */     this.input = input;
/*  99 */     this.distance = distance;
/* 100 */     this.result = result;
/*     */   }
/*     */   
/*     */   public boolean isValid() {
/* 105 */     checkPolygonal();
/* 106 */     if (!this.isValid)
/* 106 */       return this.isValid; 
/* 107 */     checkExpectedEmpty();
/* 108 */     if (!this.isValid)
/* 108 */       return this.isValid; 
/* 109 */     checkEnvelope();
/* 110 */     if (!this.isValid)
/* 110 */       return this.isValid; 
/* 111 */     checkArea();
/* 112 */     if (!this.isValid)
/* 112 */       return this.isValid; 
/* 113 */     checkDistance();
/* 114 */     return this.isValid;
/*     */   }
/*     */   
/*     */   public String getErrorMessage() {
/* 119 */     return this.errorMsg;
/*     */   }
/*     */   
/*     */   public Coordinate getErrorLocation() {
/* 124 */     return this.errorLocation;
/*     */   }
/*     */   
/*     */   public Geometry getErrorIndicator() {
/* 139 */     return this.errorIndicator;
/*     */   }
/*     */   
/*     */   private void report(String checkName) {
/* 144 */     if (!VERBOSE)
/*     */       return; 
/* 145 */     System.out.println("Check " + checkName + ": " + (this.isValid ? "passed" : "FAILED"));
/*     */   }
/*     */   
/*     */   private void checkPolygonal() {
/* 151 */     if (!(this.result instanceof com.vividsolutions.jts.geom.Polygon) && !(this.result instanceof com.vividsolutions.jts.geom.MultiPolygon))
/* 153 */       this.isValid = false; 
/* 154 */     this.errorMsg = "Result is not polygonal";
/* 155 */     this.errorIndicator = this.result;
/* 156 */     report("Polygonal");
/*     */   }
/*     */   
/*     */   private void checkExpectedEmpty() {
/* 162 */     if (this.input.getDimension() >= 2)
/*     */       return; 
/* 164 */     if (this.distance > 0.0D)
/*     */       return; 
/* 167 */     if (!this.result.isEmpty()) {
/* 168 */       this.isValid = false;
/* 169 */       this.errorMsg = "Result is non-empty";
/* 170 */       this.errorIndicator = this.result;
/*     */     } 
/* 172 */     report("ExpectedEmpty");
/*     */   }
/*     */   
/*     */   private void checkEnvelope() {
/* 177 */     if (this.distance < 0.0D)
/*     */       return; 
/* 179 */     double padding = this.distance * 0.012D;
/* 180 */     if (padding == 0.0D)
/* 180 */       padding = 0.001D; 
/* 182 */     Envelope expectedEnv = new Envelope(this.input.getEnvelopeInternal());
/* 183 */     expectedEnv.expandBy(this.distance);
/* 185 */     Envelope bufEnv = new Envelope(this.result.getEnvelopeInternal());
/* 186 */     bufEnv.expandBy(padding);
/* 188 */     if (!bufEnv.contains(expectedEnv)) {
/* 189 */       this.isValid = false;
/* 190 */       this.errorMsg = "Buffer envelope is incorrect";
/* 191 */       this.errorIndicator = this.input.getFactory().toGeometry(bufEnv);
/*     */     } 
/* 193 */     report("Envelope");
/*     */   }
/*     */   
/*     */   private void checkArea() {
/* 198 */     double inputArea = this.input.getArea();
/* 199 */     double resultArea = this.result.getArea();
/* 201 */     if (this.distance > 0.0D && inputArea > resultArea) {
/* 203 */       this.isValid = false;
/* 204 */       this.errorMsg = "Area of positive buffer is smaller than input";
/* 205 */       this.errorIndicator = this.result;
/*     */     } 
/* 207 */     if (this.distance < 0.0D && inputArea < resultArea) {
/* 209 */       this.isValid = false;
/* 210 */       this.errorMsg = "Area of negative buffer is larger than input";
/* 211 */       this.errorIndicator = this.result;
/*     */     } 
/* 213 */     report("Area");
/*     */   }
/*     */   
/*     */   private void checkDistance() {
/* 218 */     BufferDistanceValidator distValid = new BufferDistanceValidator(this.input, this.distance, this.result);
/* 219 */     if (!distValid.isValid()) {
/* 220 */       this.isValid = false;
/* 221 */       this.errorMsg = distValid.getErrorMessage();
/* 222 */       this.errorLocation = distValid.getErrorLocation();
/* 223 */       this.errorIndicator = distValid.getErrorIndicator();
/*     */     } 
/* 225 */     report("Distance");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\validate\BufferResultValidator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */