/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ class GeometrySmoother {
/* 104 */   private SmootherControl DEFAULT_CONTROL = new SmootherControl() {
/*     */       public double getMinLength() {
/* 106 */         return 0.0D;
/*     */       }
/*     */       
/*     */       public int getNumVertices(double length) {
/* 110 */         return 10;
/*     */       }
/*     */     };
/*     */   
/*     */   private SmootherControl control;
/*     */   
/*     */   private final GeometryFactory geomFactory;
/*     */   
/*     */   private static interface SmootherControl {
/*     */     double getMinLength();
/*     */     
/*     */     int getNumVertices(double param1Double);
/*     */   }
/*     */   
/*     */   private static final class InterpPoint {
/*     */     private InterpPoint() {}
/*     */     
/* 124 */     double[] t = new double[4];
/*     */     
/*     */     double tsum;
/*     */   }
/*     */   
/* 131 */   private Map<Integer, WeakReference<InterpPoint[]>> lookup = new HashMap<Integer, WeakReference<InterpPoint[]>>();
/*     */   
/*     */   GeometrySmoother(GeometryFactory geomFactory) {
/* 142 */     if (geomFactory == null)
/* 143 */       throw new IllegalArgumentException("geomFactory must not be null"); 
/* 145 */     this.geomFactory = geomFactory;
/* 147 */     this.control = this.DEFAULT_CONTROL;
/*     */   }
/*     */   
/*     */   LineString smooth(LineString ls, double alpha) {
/* 162 */     Coordinate[] coords = ls.getCoordinates();
/* 164 */     Coordinate[][] controlPoints = getLineControlPoints(coords, alpha);
/* 166 */     int N = coords.length;
/* 167 */     List<Coordinate> smoothCoords = new ArrayList<Coordinate>();
/* 169 */     for (int i = 0; i < N - 1; i++) {
/* 170 */       double dist = coords[i].distance(coords[i + 1]);
/* 171 */       if (dist < this.control.getMinLength()) {
/* 173 */         smoothCoords.add(new Coordinate(coords[i]));
/*     */       } else {
/* 176 */         int smoothN = this.control.getNumVertices(dist);
/* 177 */         Coordinate[] segment = cubicBezier(coords[i], coords[i + 1], controlPoints[i][1], controlPoints[i + 1][0], smoothN);
/* 182 */         int copyN = (i < N - 1) ? (segment.length - 1) : segment.length;
/* 183 */         for (int k = 0; k < copyN; k++)
/* 184 */           smoothCoords.add(segment[k]); 
/*     */       } 
/*     */     } 
/* 188 */     smoothCoords.add(coords[N - 1]);
/* 190 */     return this.geomFactory.createLineString(smoothCoords.<Coordinate>toArray(new Coordinate[0]));
/*     */   }
/*     */   
/*     */   public Polygon smooth(Polygon p, double alpha) {
/* 207 */     Coordinate[] coords = p.getExteriorRing().getCoordinates();
/* 208 */     int N = coords.length - 1;
/* 210 */     Coordinate[][] controlPoints = getPolygonControlPoints(coords, N, alpha);
/* 212 */     List<Coordinate> smoothCoords = new ArrayList<Coordinate>();
/* 214 */     for (int i = 0; i < N; i++) {
/* 215 */       int next = (i + 1) % N;
/* 217 */       double dist = coords[i].distance(coords[next]);
/* 218 */       if (dist < this.control.getMinLength()) {
/* 220 */         smoothCoords.add(new Coordinate(coords[i]));
/*     */       } else {
/* 223 */         int smoothN = this.control.getNumVertices(dist);
/* 224 */         Coordinate[] segment = cubicBezier(coords[i], coords[next], controlPoints[i][1], controlPoints[next][0], smoothN);
/* 229 */         int copyN = (i < N - 1) ? (segment.length - 1) : segment.length;
/* 230 */         for (int k = 0; k < copyN; k++)
/* 231 */           smoothCoords.add(segment[k]); 
/*     */       } 
/*     */     } 
/* 236 */     LinearRing shell = this.geomFactory.createLinearRing(smoothCoords.<Coordinate>toArray(new Coordinate[0]));
/* 237 */     return this.geomFactory.createPolygon(shell, null);
/*     */   }
/*     */   
/*     */   void setControl(SmootherControl control) {
/* 247 */     this.control = (control == null) ? this.DEFAULT_CONTROL : control;
/*     */   }
/*     */   
/*     */   private Coordinate[][] getLineControlPoints(Coordinate[] coords, double alpha) {
/* 262 */     if (alpha < 0.0D || alpha > 1.0D)
/* 263 */       throw new IllegalArgumentException("alpha must be a value between 0 and 1 inclusive"); 
/* 266 */     int N = coords.length;
/* 267 */     Coordinate[][] ctrl = new Coordinate[N][2];
/* 269 */     Coordinate[] v = new Coordinate[3];
/* 271 */     Coordinate[] mid = new Coordinate[2];
/* 272 */     mid[0] = new Coordinate();
/* 273 */     mid[1] = new Coordinate();
/* 275 */     Coordinate anchor = new Coordinate();
/* 276 */     double[] vdist = new double[2];
/* 280 */     v[1] = new Coordinate(2.0D * (coords[0]).x - (coords[1]).x, 2.0D * (coords[0]).y - (coords[1]).y);
/* 282 */     v[2] = coords[0];
/* 285 */     Coordinate vN = new Coordinate(2.0D * (coords[N - 1]).x - (coords[N - 2]).x, 2.0D * (coords[N - 1]).y - (coords[N - 2]).y);
/* 289 */     (mid[1]).x = ((v[1]).x + (v[2]).x) / 2.0D;
/* 290 */     (mid[1]).y = ((v[1]).y + (v[2]).y) / 2.0D;
/* 291 */     vdist[1] = v[1].distance(v[2]);
/* 293 */     for (int i = 0; i < N; i++) {
/* 294 */       v[0] = v[1];
/* 295 */       v[1] = v[2];
/* 296 */       v[2] = (i < N - 1) ? coords[i + 1] : vN;
/* 298 */       (mid[0]).x = (mid[1]).x;
/* 299 */       (mid[0]).y = (mid[1]).y;
/* 300 */       (mid[1]).x = ((v[1]).x + (v[2]).x) / 2.0D;
/* 301 */       (mid[1]).y = ((v[1]).y + (v[2]).y) / 2.0D;
/* 303 */       vdist[0] = vdist[1];
/* 304 */       vdist[1] = v[1].distance(v[2]);
/* 306 */       double p = vdist[0] / (vdist[0] + vdist[1]);
/* 307 */       (mid[0]).x += p * ((mid[1]).x - (mid[0]).x);
/* 308 */       (mid[0]).y += p * ((mid[1]).y - (mid[0]).y);
/* 310 */       double xdelta = anchor.x - (v[1]).x;
/* 311 */       double ydelta = anchor.y - (v[1]).y;
/* 313 */       ctrl[i][0] = new Coordinate(alpha * ((v[1]).x - (mid[0]).x + xdelta) + (mid[0]).x - xdelta, alpha * ((v[1]).y - (mid[0]).y + ydelta) + (mid[0]).y - ydelta);
/* 317 */       ctrl[i][1] = new Coordinate(alpha * ((v[1]).x - (mid[1]).x + xdelta) + (mid[1]).x - xdelta, alpha * ((v[1]).y - (mid[1]).y + ydelta) + (mid[1]).y - ydelta);
/*     */     } 
/* 322 */     return ctrl;
/*     */   }
/*     */   
/*     */   private Coordinate[][] getPolygonControlPoints(Coordinate[] coords, int N, double alpha) {
/* 337 */     if (alpha < 0.0D || alpha > 1.0D)
/* 338 */       throw new IllegalArgumentException("alpha must be a value between 0 and 1 inclusive"); 
/* 341 */     Coordinate[][] ctrl = new Coordinate[N][2];
/* 343 */     Coordinate[] v = new Coordinate[3];
/* 345 */     Coordinate[] mid = new Coordinate[2];
/* 346 */     mid[0] = new Coordinate();
/* 347 */     mid[1] = new Coordinate();
/* 349 */     Coordinate anchor = new Coordinate();
/* 350 */     double[] vdist = new double[2];
/* 353 */     v[1] = coords[N - 1];
/* 354 */     v[2] = coords[0];
/* 355 */     (mid[1]).x = ((v[1]).x + (v[2]).x) / 2.0D;
/* 356 */     (mid[1]).y = ((v[1]).y + (v[2]).y) / 2.0D;
/* 357 */     vdist[1] = v[1].distance(v[2]);
/* 359 */     for (int i = 0; i < N; i++) {
/* 360 */       v[0] = v[1];
/* 361 */       v[1] = v[2];
/* 362 */       v[2] = coords[(i + 1) % N];
/* 364 */       (mid[0]).x = (mid[1]).x;
/* 365 */       (mid[0]).y = (mid[1]).y;
/* 366 */       (mid[1]).x = ((v[1]).x + (v[2]).x) / 2.0D;
/* 367 */       (mid[1]).y = ((v[1]).y + (v[2]).y) / 2.0D;
/* 369 */       vdist[0] = vdist[1];
/* 370 */       vdist[1] = v[1].distance(v[2]);
/* 372 */       double p = vdist[0] / (vdist[0] + vdist[1]);
/* 373 */       (mid[0]).x += p * ((mid[1]).x - (mid[0]).x);
/* 374 */       (mid[0]).y += p * ((mid[1]).y - (mid[0]).y);
/* 376 */       double xdelta = anchor.x - (v[1]).x;
/* 377 */       double ydelta = anchor.y - (v[1]).y;
/* 379 */       ctrl[i][0] = new Coordinate(alpha * ((v[1]).x - (mid[0]).x + xdelta) + (mid[0]).x - xdelta, alpha * ((v[1]).y - (mid[0]).y + ydelta) + (mid[0]).y - ydelta);
/* 383 */       ctrl[i][1] = new Coordinate(alpha * ((v[1]).x - (mid[1]).x + xdelta) + (mid[1]).x - xdelta, alpha * ((v[1]).y - (mid[1]).y + ydelta) + (mid[1]).y - ydelta);
/*     */     } 
/* 388 */     return ctrl;
/*     */   }
/*     */   
/*     */   private Coordinate[] cubicBezier(Coordinate start, Coordinate end, Coordinate ctrl1, Coordinate ctrl2, int nv) {
/* 406 */     Coordinate[] curve = new Coordinate[nv];
/* 408 */     Coordinate[] buf = new Coordinate[3];
/* 409 */     for (int i = 0; i < buf.length; i++)
/* 410 */       buf[i] = new Coordinate(); 
/* 413 */     curve[0] = new Coordinate(start);
/* 414 */     curve[nv - 1] = new Coordinate(end);
/* 415 */     InterpPoint[] ip = getInterpPoints(nv);
/* 417 */     for (int j = 1; j < nv - 1; j++) {
/* 418 */       Coordinate c = new Coordinate();
/* 420 */       c.x = (ip[j]).t[0] * start.x + (ip[j]).t[1] * ctrl1.x + (ip[j]).t[2] * ctrl2.x + (ip[j]).t[3] * end.x;
/* 421 */       c.x /= (ip[j]).tsum;
/* 422 */       c.y = (ip[j]).t[0] * start.y + (ip[j]).t[1] * ctrl1.y + (ip[j]).t[2] * ctrl2.y + (ip[j]).t[3] * end.y;
/* 423 */       c.y /= (ip[j]).tsum;
/* 425 */       curve[j] = c;
/*     */     } 
/* 428 */     return curve;
/*     */   }
/*     */   
/*     */   private InterpPoint[] getInterpPoints(int npoints) {
/* 440 */     WeakReference<InterpPoint[]> ref = this.lookup.get(Integer.valueOf(npoints));
/* 441 */     InterpPoint[] ip = null;
/* 442 */     if (ref != null)
/* 442 */       ip = ref.get(); 
/* 444 */     if (ip == null) {
/* 445 */       ip = new InterpPoint[npoints];
/* 447 */       for (int i = 0; i < npoints; i++) {
/* 448 */         double t = i / (npoints - 1);
/* 449 */         double tc = 1.0D - t;
/* 451 */         ip[i] = new InterpPoint();
/* 452 */         (ip[i]).t[0] = tc * tc * tc;
/* 453 */         (ip[i]).t[1] = 3.0D * tc * tc * t;
/* 454 */         (ip[i]).t[2] = 3.0D * tc * t * t;
/* 455 */         (ip[i]).t[3] = t * t * t;
/* 456 */         (ip[i]).tsum = (ip[i]).t[0] + (ip[i]).t[1] + (ip[i]).t[2] + (ip[i]).t[3];
/*     */       } 
/* 459 */       this.lookup.put(Integer.valueOf(npoints), (WeakReference)new WeakReference<InterpPoint>(ip));
/*     */     } 
/* 462 */     return ip;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\GeometrySmoother.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */