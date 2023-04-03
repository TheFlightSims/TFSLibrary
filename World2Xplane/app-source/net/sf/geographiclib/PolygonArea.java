/*     */ package net.sf.geographiclib;
/*     */ 
/*     */ public class PolygonArea {
/*     */   private Geodesic _earth;
/*     */   
/*     */   private double _area0;
/*     */   
/*     */   private boolean _polyline;
/*     */   
/*     */   private int _mask;
/*     */   
/*     */   private int _num;
/*     */   
/*     */   private int _crossings;
/*     */   
/*     */   private Accumulator _areasum;
/*     */   
/*     */   private Accumulator _perimetersum;
/*     */   
/*     */   private double _lat0;
/*     */   
/*     */   private double _lon0;
/*     */   
/*     */   private double _lat1;
/*     */   
/*     */   private double _lon1;
/*     */   
/*     */   private static int transit(double lon1, double lon2) {
/*  90 */     lon1 = GeoMath.AngNormalize(lon1);
/*  91 */     lon2 = GeoMath.AngNormalize(lon2);
/*  92 */     double lon12 = GeoMath.AngDiff(lon1, lon2);
/*  93 */     int cross = (lon1 < 0.0D && lon2 >= 0.0D && lon12 > 0.0D) ? 1 : ((lon2 < 0.0D && lon1 >= 0.0D && lon12 < 0.0D) ? -1 : 0);
/*  96 */     return cross;
/*     */   }
/*     */   
/*     */   public PolygonArea(Geodesic earth, boolean polyline) {
/* 107 */     this._earth = earth;
/* 108 */     this._area0 = this._earth.EllipsoidArea();
/* 109 */     this._polyline = polyline;
/* 110 */     this._mask = 0x589 | (this._polyline ? 0 : 16400);
/* 113 */     this._perimetersum = new Accumulator(0.0D);
/* 114 */     if (!this._polyline)
/* 115 */       this._areasum = new Accumulator(0.0D); 
/* 116 */     Clear();
/*     */   }
/*     */   
/*     */   public void Clear() {
/* 123 */     this._num = 0;
/* 124 */     this._crossings = 0;
/* 125 */     this._perimetersum.Set(0.0D);
/* 126 */     if (!this._polyline)
/* 126 */       this._areasum.Set(0.0D); 
/* 127 */     this._lat0 = this._lon0 = this._lat1 = this._lon1 = Double.NaN;
/*     */   }
/*     */   
/*     */   public void AddPoint(double lat, double lon) {
/* 140 */     lon = GeoMath.AngNormalize(lon);
/* 142 */     this._lat0 = this._lat1 = lat;
/* 143 */     this._lon0 = this._lon1 = lon;
/* 145 */     GeodesicData g = this._earth.Inverse(this._lat1, this._lon1, lat, lon, this._mask);
/* 146 */     this._perimetersum.Add(g.s12);
/* 147 */     if (!this._polyline) {
/* 148 */       this._areasum.Add(g.S12);
/* 149 */       this._crossings += transit(this._lon1, lon);
/*     */     } 
/* 151 */     this._lat1 = lat;
/* 151 */     this._lon1 = lon;
/* 153 */     this._num++;
/*     */   }
/*     */   
/*     */   public void AddEdge(double azi, double s) {
/* 167 */     if (this._num > 0) {
/* 168 */       GeodesicData g = this._earth.Direct(this._lat1, this._lon1, azi, s, this._mask);
/* 169 */       this._perimetersum.Add(g.s12);
/* 170 */       if (!this._polyline) {
/* 171 */         this._areasum.Add(g.S12);
/* 172 */         this._crossings += transit(this._lon1, g.lon2);
/*     */       } 
/* 174 */       this._lat1 = g.lat2;
/* 174 */       this._lon1 = g.lon2;
/* 175 */       this._num++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public PolygonResult Compute() {
/* 190 */     return Compute(false, true);
/*     */   }
/*     */   
/*     */   public PolygonResult Compute(boolean reverse, boolean sign) {
/* 206 */     if (this._num < 2)
/* 207 */       return new PolygonResult(this._num, 0.0D, this._polyline ? Double.NaN : 0.0D); 
/* 208 */     if (this._polyline)
/* 209 */       return new PolygonResult(this._num, this._perimetersum.Sum(), Double.NaN); 
/* 211 */     GeodesicData g = this._earth.Inverse(this._lat1, this._lon1, this._lat0, this._lon0, this._mask);
/* 212 */     Accumulator tempsum = new Accumulator(this._areasum);
/* 213 */     tempsum.Add(g.S12);
/* 214 */     int crossings = this._crossings + transit(this._lon1, this._lon0);
/* 215 */     if ((crossings & 0x1) != 0)
/* 216 */       tempsum.Add(((tempsum.Sum() < 0.0D) ? true : -1) * this._area0 / 2.0D); 
/* 219 */     if (!reverse)
/* 220 */       tempsum.Negate(); 
/* 222 */     if (sign) {
/* 223 */       if (tempsum.Sum() > this._area0 / 2.0D) {
/* 224 */         tempsum.Add(-this._area0);
/* 225 */       } else if (tempsum.Sum() <= -this._area0 / 2.0D) {
/* 226 */         tempsum.Add(this._area0);
/*     */       } 
/* 228 */     } else if (tempsum.Sum() >= this._area0) {
/* 229 */       tempsum.Add(-this._area0);
/* 230 */     } else if (tempsum.Sum() < 0.0D) {
/* 231 */       tempsum.Add(this._area0);
/*     */     } 
/* 233 */     return new PolygonResult(this._num, this._perimetersum.Sum(g.s12), 0.0D + tempsum.Sum());
/*     */   }
/*     */   
/*     */   public PolygonResult TestPoint(double lat, double lon, boolean reverse, boolean sign) {
/* 262 */     if (this._num == 0)
/* 263 */       return new PolygonResult(1, 0.0D, this._polyline ? Double.NaN : 0.0D); 
/* 265 */     double perimeter = this._perimetersum.Sum();
/* 266 */     double tempsum = this._polyline ? 0.0D : this._areasum.Sum();
/* 267 */     int crossings = this._crossings;
/* 268 */     int num = this._num + 1;
/* 269 */     for (int i = 0; i < (this._polyline ? 1 : 2); i++) {
/* 270 */       GeodesicData g = this._earth.Inverse((i == 0) ? this._lat1 : lat, (i == 0) ? this._lon1 : lon, (i != 0) ? this._lat0 : lat, (i != 0) ? this._lon0 : lon, this._mask);
/* 274 */       perimeter += g.s12;
/* 275 */       if (!this._polyline) {
/* 276 */         tempsum += g.S12;
/* 277 */         crossings += transit((i == 0) ? this._lon1 : lon, (i != 0) ? this._lon0 : lon);
/*     */       } 
/*     */     } 
/* 282 */     if (this._polyline)
/* 283 */       return new PolygonResult(num, perimeter, Double.NaN); 
/* 285 */     if ((crossings & 0x1) != 0)
/* 286 */       tempsum += ((tempsum < 0.0D) ? true : -1) * this._area0 / 2.0D; 
/* 289 */     if (!reverse)
/* 290 */       tempsum *= -1.0D; 
/* 292 */     if (sign) {
/* 293 */       if (tempsum > this._area0 / 2.0D) {
/* 294 */         tempsum -= this._area0;
/* 295 */       } else if (tempsum <= -this._area0 / 2.0D) {
/* 296 */         tempsum += this._area0;
/*     */       } 
/* 298 */     } else if (tempsum >= this._area0) {
/* 299 */       tempsum -= this._area0;
/* 300 */     } else if (tempsum < 0.0D) {
/* 301 */       tempsum += this._area0;
/*     */     } 
/* 303 */     return new PolygonResult(num, perimeter, 0.0D + tempsum);
/*     */   }
/*     */   
/*     */   public PolygonResult TestEdge(double azi, double s, boolean reverse, boolean sign) {
/* 331 */     if (this._num == 0)
/* 332 */       return new PolygonResult(0, Double.NaN, Double.NaN); 
/* 334 */     int num = this._num + 1;
/* 335 */     double perimeter = this._perimetersum.Sum() + s;
/* 336 */     if (this._polyline)
/* 337 */       return new PolygonResult(num, perimeter, Double.NaN); 
/* 339 */     double tempsum = this._areasum.Sum();
/* 340 */     int crossings = this._crossings;
/* 343 */     GeodesicData g = this._earth.Direct(this._lat1, this._lon1, azi, false, s, this._mask);
/* 345 */     tempsum += g.S12;
/* 346 */     crossings += transit(this._lon1, g.lon2);
/* 347 */     g = this._earth.Inverse(g.lat2, g.lon2, this._lat0, this._lon0, this._mask);
/* 348 */     perimeter += g.s12;
/* 349 */     tempsum += g.S12;
/* 350 */     crossings += transit(g.lon2, this._lon0);
/* 353 */     if ((crossings & 0x1) != 0)
/* 354 */       tempsum += ((tempsum < 0.0D) ? true : -1) * this._area0 / 2.0D; 
/* 357 */     if (!reverse)
/* 358 */       tempsum *= -1.0D; 
/* 360 */     if (sign) {
/* 361 */       if (tempsum > this._area0 / 2.0D) {
/* 362 */         tempsum -= this._area0;
/* 363 */       } else if (tempsum <= -this._area0 / 2.0D) {
/* 364 */         tempsum += this._area0;
/*     */       } 
/* 366 */     } else if (tempsum >= this._area0) {
/* 367 */       tempsum -= this._area0;
/* 368 */     } else if (tempsum < 0.0D) {
/* 369 */       tempsum += this._area0;
/*     */     } 
/* 372 */     return new PolygonResult(num, perimeter, 0.0D + tempsum);
/*     */   }
/*     */   
/*     */   public double MajorRadius() {
/* 380 */     return this._earth.MajorRadius();
/*     */   }
/*     */   
/*     */   public double Flattening() {
/* 386 */     return this._earth.Flattening();
/*     */   }
/*     */   
/*     */   public Pair CurrentPoint() {
/* 396 */     return new Pair(this._lat1, this._lon1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\net\sf\geographiclib\PolygonArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */