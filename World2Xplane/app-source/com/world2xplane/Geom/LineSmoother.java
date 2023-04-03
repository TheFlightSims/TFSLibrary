/*     */ package com.world2xplane.Geom;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class LineSmoother extends AbstractSmoother {
/*     */   public LineSmoother() {
/*  40 */     super(new GeometryFactory());
/*     */   }
/*     */   
/*     */   public LineSmoother(GeometryFactory geomFactory) {
/*  51 */     super(geomFactory);
/*     */   }
/*     */   
/*     */   private Coordinate[][] getControlPoints(Coordinate[] coords, double alpha) {
/*  65 */     if (alpha < 0.0D || alpha > 1.0D)
/*  66 */       throw new IllegalArgumentException("alpha must be a value between 0 and 1 inclusive"); 
/*  69 */     int N = coords.length;
/*  70 */     Coordinate[][] ctrl = new Coordinate[N][2];
/*  72 */     Coordinate[] v = new Coordinate[3];
/*  74 */     Coordinate[] mid = new Coordinate[2];
/*  75 */     mid[0] = new Coordinate();
/*  76 */     mid[1] = new Coordinate();
/*  78 */     Coordinate anchor = new Coordinate();
/*  79 */     double[] vdist = new double[2];
/*  83 */     v[1] = new Coordinate(2.0D * (coords[0]).x - (coords[1]).x, 2.0D * (coords[0]).y - (coords[1]).y);
/*  85 */     v[2] = coords[0];
/*  88 */     Coordinate vN = new Coordinate(2.0D * (coords[N - 1]).x - (coords[N - 2]).x, 2.0D * (coords[N - 1]).y - (coords[N - 2]).y);
/*  92 */     (mid[1]).x = ((v[1]).x + (v[2]).x) / 2.0D;
/*  93 */     (mid[1]).y = ((v[1]).y + (v[2]).y) / 2.0D;
/*  94 */     vdist[1] = v[1].distance(v[2]);
/*  96 */     for (int i = 0; i < N; i++) {
/*  97 */       v[0] = v[1];
/*  98 */       v[1] = v[2];
/*  99 */       v[2] = (i < N - 1) ? coords[i + 1] : vN;
/* 101 */       (mid[0]).x = (mid[1]).x;
/* 102 */       (mid[0]).y = (mid[1]).y;
/* 103 */       (mid[1]).x = ((v[1]).x + (v[2]).x) / 2.0D;
/* 104 */       (mid[1]).y = ((v[1]).y + (v[2]).y) / 2.0D;
/* 106 */       vdist[0] = vdist[1];
/* 107 */       vdist[1] = v[1].distance(v[2]);
/* 109 */       double p = vdist[0] / (vdist[0] + vdist[1]);
/* 110 */       (mid[0]).x += p * ((mid[1]).x - (mid[0]).x);
/* 111 */       (mid[0]).y += p * ((mid[1]).y - (mid[0]).y);
/* 113 */       double xdelta = anchor.x - (v[1]).x;
/* 114 */       double ydelta = anchor.y - (v[1]).y;
/* 116 */       ctrl[i][0] = new Coordinate(alpha * ((v[1]).x - (mid[0]).x + xdelta) + (mid[0]).x - xdelta, alpha * ((v[1]).y - (mid[0]).y + ydelta) + (mid[0]).y - ydelta);
/* 120 */       ctrl[i][1] = new Coordinate(alpha * ((v[1]).x - (mid[1]).x + xdelta) + (mid[1]).x - xdelta, alpha * ((v[1]).y - (mid[1]).y + ydelta) + (mid[1]).y - ydelta);
/*     */     } 
/* 125 */     return ctrl;
/*     */   }
/*     */   
/*     */   public LineString smooth(LineString ls, double alpha) {
/* 138 */     Coordinate[] coords = ls.getCoordinates();
/* 140 */     Coordinate[][] controlPoints = getControlPoints(coords, alpha);
/* 142 */     int N = coords.length;
/* 143 */     List<Coordinate> smoothCoords = new ArrayList<>();
/* 145 */     for (int i = 0; i < N - 1; i++) {
/* 146 */       double dist = coords[i].distance(coords[i + 1]);
/* 147 */       if (dist < this.control.getMinLength()) {
/* 149 */         smoothCoords.add(new Coordinate(coords[i]));
/*     */       } else {
/* 152 */         int smoothN = this.control.getNumVertices(dist);
/* 153 */         Coordinate[] segment = cubicBezier(coords[i], coords[i + 1], controlPoints[i][1], controlPoints[i + 1][0], smoothN);
/* 158 */         int copyN = (i < N - 1) ? (segment.length - 1) : segment.length;
/* 159 */         for (int k = 0; k < copyN; k++)
/* 160 */           smoothCoords.add(segment[k]); 
/*     */       } 
/*     */     } 
/* 164 */     smoothCoords.add(coords[N - 1]);
/* 166 */     return this.geomFactory.createLineString(smoothCoords.<Coordinate>toArray(new Coordinate[0]));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\LineSmoother.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */