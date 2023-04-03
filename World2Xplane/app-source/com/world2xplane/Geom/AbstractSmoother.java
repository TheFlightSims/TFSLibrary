/*     */ package com.world2xplane.Geom;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class AbstractSmoother {
/*  43 */   protected SmootherControl DEFAULT_CONTROL = new SmootherControl() {
/*     */       public double getMinLength() {
/*  45 */         return 0.0D;
/*     */       }
/*     */       
/*     */       public int getNumVertices(double length) {
/*  49 */         return 10;
/*     */       }
/*     */     };
/*     */   
/*     */   protected SmootherControl control;
/*     */   
/*     */   protected final GeometryFactory geomFactory;
/*     */   
/*     */   protected static final class InterpPoint {
/*  63 */     double[] t = new double[4];
/*     */     
/*     */     double tsum;
/*     */   }
/*     */   
/*  70 */   protected Map<Integer, WeakReference<InterpPoint[]>> lookup = new HashMap<>();
/*     */   
/*     */   public AbstractSmoother(GeometryFactory geomFactory) {
/*  81 */     if (geomFactory == null)
/*  82 */       throw new IllegalArgumentException("geomFactory must not be null"); 
/*  84 */     this.geomFactory = geomFactory;
/*  86 */     this.control = this.DEFAULT_CONTROL;
/*     */   }
/*     */   
/*     */   public void setControl(SmootherControl control) {
/*  96 */     this.control = (control == null) ? this.DEFAULT_CONTROL : control;
/*     */   }
/*     */   
/*     */   protected Coordinate[] cubicBezier(Coordinate start, Coordinate end, Coordinate ctrl1, Coordinate ctrl2, int nv) {
/* 114 */     Coordinate[] curve = new Coordinate[nv];
/* 116 */     Coordinate[] buf = new Coordinate[3];
/* 117 */     for (int i = 0; i < buf.length; i++)
/* 118 */       buf[i] = new Coordinate(); 
/* 121 */     curve[0] = new Coordinate(start);
/* 122 */     curve[nv - 1] = new Coordinate(end);
/* 123 */     InterpPoint[] ip = getInterpPoints(nv);
/* 125 */     for (int j = 1; j < nv - 1; j++) {
/* 126 */       Coordinate c = new Coordinate();
/* 128 */       c.x = (ip[j]).t[0] * start.x + (ip[j]).t[1] * ctrl1.x + (ip[j]).t[2] * ctrl2.x + (ip[j]).t[3] * end.x;
/* 129 */       c.x /= (ip[j]).tsum;
/* 130 */       c.y = (ip[j]).t[0] * start.y + (ip[j]).t[1] * ctrl1.y + (ip[j]).t[2] * ctrl2.y + (ip[j]).t[3] * end.y;
/* 131 */       c.y /= (ip[j]).tsum;
/* 133 */       curve[j] = c;
/*     */     } 
/* 136 */     return curve;
/*     */   }
/*     */   
/*     */   protected InterpPoint[] getInterpPoints(int npoints) {
/* 148 */     WeakReference<InterpPoint[]> ref = this.lookup.get(Integer.valueOf(npoints));
/* 149 */     InterpPoint[] ip = null;
/* 150 */     if (ref != null)
/* 150 */       ip = ref.get(); 
/* 152 */     if (ip == null) {
/* 153 */       ip = new InterpPoint[npoints];
/* 155 */       for (int i = 0; i < npoints; i++) {
/* 156 */         double t = i / (npoints - 1);
/* 157 */         double tc = 1.0D - t;
/* 159 */         ip[i] = new InterpPoint();
/* 160 */         (ip[i]).t[0] = tc * tc * tc;
/* 161 */         (ip[i]).t[1] = 3.0D * tc * tc * t;
/* 162 */         (ip[i]).t[2] = 3.0D * tc * t * t;
/* 163 */         (ip[i]).t[3] = t * t * t;
/* 164 */         (ip[i]).tsum = (ip[i]).t[0] + (ip[i]).t[1] + (ip[i]).t[2] + (ip[i]).t[3];
/*     */       } 
/* 167 */       this.lookup.put(Integer.valueOf(npoints), (WeakReference)new WeakReference<>(ip));
/*     */     } 
/* 170 */     return ip;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\AbstractSmoother.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */