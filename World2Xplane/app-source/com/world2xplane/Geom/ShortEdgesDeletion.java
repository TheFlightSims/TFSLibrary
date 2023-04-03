/*     */ package com.world2xplane.Geom;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.operation.valid.IsValidOp;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ShortEdgesDeletion {
/*     */   private static class LineEdge {
/*  48 */     public int id = -1;
/*     */     
/*  49 */     public double length = -1.0D;
/*     */     
/*     */     public LineEdge(double l, int id) {
/*  52 */       this.length = l;
/*  53 */       this.id = id;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class PolyEdge {
/*  64 */     public int ringId = -999;
/*     */     
/*  65 */     public ShortEdgesDeletion.LineEdge edge = null;
/*     */     
/*     */     public PolyEdge(int ringId, ShortEdgesDeletion.LineEdge seg) {
/*  68 */       this.ringId = ringId;
/*  69 */       this.edge = seg;
/*     */     }
/*     */   }
/*     */   
/*     */   private static ArrayList<LineEdge> getShort(LineString line, double tol) {
/*  82 */     ArrayList<LineEdge> out = new ArrayList<>();
/*  83 */     Coordinate[] cs = line.getCoordinates();
/*  84 */     for (int i = 0; i < cs.length - 1; i++) {
/*  85 */       double len = cs[i].distance(cs[i + 1]);
/*  86 */       if (len < tol)
/*  86 */         out.add(new LineEdge(len, i)); 
/*     */     } 
/*  88 */     return out;
/*     */   }
/*     */   
/*     */   private static ArrayList<PolyEdge> getShort(Polygon poly, double tol) {
/*  99 */     ArrayList<PolyEdge> out = new ArrayList<>();
/* 100 */     ArrayList<LineEdge> lss = getShort(poly.getExteriorRing(), tol);
/* 101 */     for (LineEdge cls : lss)
/* 101 */       out.add(new PolyEdge(-1, cls)); 
/* 102 */     for (int i = 0; i < poly.getNumInteriorRing(); i++) {
/* 103 */       lss = getShort(poly.getInteriorRingN(i), tol);
/* 104 */       for (LineEdge cls : lss)
/* 104 */         out.add(new PolyEdge(i, cls)); 
/*     */     } 
/* 106 */     return out;
/*     */   }
/*     */   
/*     */   private static class RingEdgeDeletionOut {
/* 116 */     public LinearRing ring = null;
/*     */     
/*     */     public boolean success = false;
/*     */     
/*     */     public RingEdgeDeletionOut(LinearRing ring, boolean success) {
/* 120 */       this.ring = ring;
/* 121 */       this.success = success;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class PolyEdgeDeletionRes {
/* 132 */     public Polygon poly = null;
/*     */     
/*     */     public boolean success = false;
/*     */     
/*     */     public PolyEdgeDeletionRes(Polygon poly, boolean success) {
/* 136 */       this.poly = poly;
/* 137 */       this.success = success;
/*     */     }
/*     */   }
/*     */   
/*     */   public static Geometry get(Polygon poly, double tolmm, double scale) {
/* 151 */     return get(poly, tolmm * scale * 0.001D);
/*     */   }
/*     */   
/*     */   public static Geometry get(Polygon poly, double tol) {
/* 163 */     Polygon p_ = (Polygon)poly.clone();
/* 164 */     ArrayList<PolyEdge> shSegs = getShort(p_, tol);
/* 166 */     while (shSegs.size() > 0) {
/* 167 */       PolyEdge shst = shSegs.get(0);
/* 168 */       for (PolyEdge seg : shSegs) {
/* 168 */         if (seg.edge.length < shst.edge.length)
/* 168 */           shst = seg; 
/*     */       } 
/* 169 */       shSegs.remove(shst);
/* 171 */       PolyEdgeDeletionRes out = deleteEdge(p_, shst);
/* 173 */       if (!out.success)
/*     */         continue; 
/* 175 */       p_ = out.poly;
/* 176 */       shSegs = getShort(p_, tol);
/*     */     } 
/* 178 */     return (Geometry)p_;
/*     */   }
/*     */   
/*     */   private static PolyEdgeDeletionRes deleteEdge(Polygon p, PolyEdge seg) {
/*     */     LineString r;
/*     */     LinearRing shell;
/* 184 */     if (seg.ringId == -1) {
/* 184 */       r = p.getExteriorRing();
/*     */     } else {
/* 185 */       r = p.getInteriorRingN(seg.ringId);
/*     */     } 
/* 187 */     RingEdgeDeletionOut out = deleteEdge(r, seg.edge);
/* 189 */     if (!out.success)
/* 189 */       return new PolyEdgeDeletionRes(null, false); 
/* 191 */     LinearRing linearRing1 = out.ring;
/* 194 */     LinearRing[] holes = new LinearRing[p.getNumInteriorRing()];
/* 195 */     if (seg.ringId == -1) {
/* 196 */       shell = linearRing1;
/* 197 */       for (int i = 0; i < p.getNumInteriorRing(); ) {
/* 197 */         holes[i] = (LinearRing)p.getInteriorRingN(i).clone();
/* 197 */         i++;
/*     */       } 
/*     */     } else {
/* 200 */       shell = (LinearRing)p.getExteriorRing();
/* 201 */       for (int i = 0; i < p.getNumInteriorRing(); ) {
/* 201 */         holes[i] = (LinearRing)p.getInteriorRingN(i).clone();
/* 201 */         i++;
/*     */       } 
/* 202 */       holes[seg.ringId] = linearRing1;
/*     */     } 
/* 204 */     Polygon p_ = (new GeometryFactory()).createPolygon(shell, holes);
/* 206 */     if (!IsValidOp.isValid((Geometry)p_) || p_.isEmpty())
/* 206 */       return new PolyEdgeDeletionRes(p_, false); 
/* 208 */     return new PolyEdgeDeletionRes(p_, true);
/*     */   }
/*     */   
/* 213 */   private static double TOL_ANGLE = 0.3490658503988659D;
/*     */   
/*     */   private static RingEdgeDeletionOut deleteEdge(LineString lr, LineEdge seg) {
/* 217 */     Coordinate[] cs = lr.getCoordinates();
/* 218 */     if (cs.length <= 4)
/* 218 */       return new RingEdgeDeletionOut(null, false); 
/* 221 */     Coordinate p1 = cs[seg.id];
/* 222 */     Coordinate p2 = cs[seg.id + 1];
/* 223 */     Coordinate p0 = (seg.id == 0) ? cs[cs.length - 2] : cs[seg.id - 1];
/* 224 */     Coordinate p3 = (seg.id + 2 == cs.length) ? cs[1] : cs[seg.id + 2];
/* 227 */     double a = Math.atan2(p3.y - p2.y, p3.x - p2.x) - Math.atan2(p0.y - p1.y, p0.x - p1.x);
/* 228 */     if (a <= -3.141592653589793D) {
/* 228 */       a += 6.283185307179586D;
/* 229 */     } else if (a > Math.PI) {
/* 229 */       a -= 6.283185307179586D;
/*     */     } 
/* 232 */     if (Math.abs(a) <= 1.5707963267948966D + TOL_ANGLE && Math.abs(a) >= 1.5707963267948966D - TOL_ANGLE) {
/* 235 */       double x1 = p0.x - p1.x, ya = p0.y - p1.y;
/* 236 */       double x2 = p3.x - p2.x, yb = p3.y - p2.y;
/* 237 */       double t = (x2 * (p1.y - p2.y) - yb * (p1.x - p2.x)) / (x1 * yb - ya * x2);
/* 238 */       Coordinate c = new Coordinate(p1.x + t * x1, p1.y + t * ya);
/* 240 */       Coordinate[] cs_ = new Coordinate[cs.length - 1];
/* 242 */       if (seg.id != 0) {
/*     */         int i;
/* 243 */         for (i = 0; i < seg.id; ) {
/* 243 */           cs_[i] = cs[i];
/* 243 */           i++;
/*     */         } 
/* 244 */         cs_[seg.id] = c;
/* 245 */         for (i = seg.id + 1; i < cs.length - 1; ) {
/* 245 */           cs_[i] = cs[i + 1];
/* 245 */           i++;
/*     */         } 
/* 246 */         if (seg.id == cs.length - 2)
/* 246 */           cs_[0] = c; 
/*     */       } else {
/* 249 */         cs_[0] = c;
/* 250 */         for (int i = 1; i < cs.length - 2; ) {
/* 250 */           cs_[i] = cs[i - 1];
/* 250 */           i++;
/*     */         } 
/* 251 */         cs_[cs.length - 2] = c;
/*     */       } 
/* 254 */       if ((cs_[0]).x != (cs_[cs_.length - 1]).x || (cs_[0]).y != (cs_[cs_.length - 1]).y)
/* 254 */         return new RingEdgeDeletionOut(null, false); 
/* 255 */       if (cs_.length <= 3)
/* 255 */         return new RingEdgeDeletionOut(null, false); 
/* 256 */       return new RingEdgeDeletionOut((new GeometryFactory()).createLinearRing(cs_), true);
/*     */     } 
/* 260 */     if (Math.abs(a) >= Math.PI - TOL_ANGLE) {
/* 261 */       double dx = p1.x - p0.x + p3.x - p2.x;
/* 262 */       double dy = p1.y - p0.y + p3.y - p2.y;
/* 263 */       double length = Math.sqrt(dx * dx + dy * dy);
/* 264 */       dx /= length;
/* 265 */       dy /= length;
/* 267 */       double xMid = (p0.x + p3.x) * 0.5D, yMid = (p0.y + p3.y) * 0.5D;
/* 269 */       double t1 = (p0.x - xMid) * dx + (p0.y - yMid) * dy;
/* 270 */       double t2 = (p3.x - xMid) * dx + (p3.y - yMid) * dy;
/* 271 */       Coordinate c1 = new Coordinate(xMid + t1 * dx, yMid + t1 * dy);
/* 272 */       Coordinate c2 = new Coordinate(xMid + t2 * dx, yMid + t2 * dy);
/* 274 */       Coordinate[] cs_ = new Coordinate[cs.length - 2];
/* 275 */       cs_[0] = c1;
/* 275 */       cs_[1] = c2;
/* 276 */       if (seg.id != 0) {
/*     */         int i;
/* 277 */         for (i = seg.id + 3; i < cs.length; ) {
/* 277 */           cs_[i - seg.id - 1] = cs[i];
/* 277 */           i++;
/*     */         } 
/* 278 */         for (i = 1; i < seg.id - 1; ) {
/* 278 */           cs_[cs.length - seg.id - 2 + i] = cs[i];
/* 278 */           i++;
/*     */         } 
/* 279 */         cs_[cs.length - 3] = c1;
/*     */       } else {
/* 282 */         for (int i = 2; i < cs.length - 3; ) {
/* 282 */           cs_[i] = cs[i + 1];
/* 282 */           i++;
/*     */         } 
/* 283 */         cs_[cs.length - 3] = c1;
/*     */       } 
/* 286 */       if ((cs_[0]).x != (cs_[cs_.length - 1]).x || (cs_[0]).y != (cs_[cs_.length - 1]).y)
/* 286 */         return new RingEdgeDeletionOut(null, false); 
/* 287 */       if (cs_.length <= 3)
/* 287 */         return new RingEdgeDeletionOut(null, false); 
/* 288 */       return new RingEdgeDeletionOut((new GeometryFactory()).createLinearRing(cs_), true);
/*     */     } 
/* 292 */     if (Math.abs(a) <= TOL_ANGLE) {
/*     */       Coordinate c1, c2;
/* 293 */       if (p0 == p3)
/* 293 */         return new RingEdgeDeletionOut(null, false); 
/* 295 */       double t1 = ((p2.x - p3.x) * (p0.x - p3.x) + (p2.y - p3.y) * (p0.y - p3.y)) / ((p2.x - p3.x) * (p2.x - p3.x) + (p2.y - p3.y) * (p2.y - p3.y));
/* 296 */       double t2 = ((p1.x - p0.x) * (p3.x - p0.x) + (p1.y - p0.y) * (p3.y - p0.y)) / ((p1.x - p0.x) * (p1.x - p0.x) + (p1.y - p0.y) * (p1.y - p0.y));
/* 297 */       Coordinate c1_ = new Coordinate(p3.x + t1 * (p2.x - p3.x), p3.y + t1 * (p2.y - p3.y));
/* 298 */       Coordinate c2_ = new Coordinate(p0.x + t2 * (p1.x - p0.x), p0.y + t2 * (p1.y - p0.y));
/* 299 */       boolean v1 = ((p3.x - c1_.x) * (p2.x - c1_.x) + (p3.y - c1_.y) * (p2.y - c1_.y) < 0.0D);
/* 300 */       boolean v2 = ((p0.x - c2_.x) * (p1.x - c2_.x) + (p0.y - c2_.y) * (p1.y - c2_.y) < 0.0D);
/* 304 */       if (!v1 && !v2)
/* 304 */         return new RingEdgeDeletionOut(null, false); 
/* 305 */       if (!v1 && v2) {
/* 305 */         c1 = p0;
/* 305 */         c2 = c2_;
/* 306 */       } else if (v1 && !v2) {
/* 306 */         c1 = c1_;
/* 306 */         c2 = p3;
/*     */       } else {
/* 308 */         double d1 = p0.distance(p1);
/* 309 */         double d2 = p3.distance(p2);
/* 310 */         if (d1 < d2) {
/* 310 */           c1 = c1_;
/* 310 */           c2 = p3;
/*     */         } else {
/* 311 */           c1 = p0;
/* 311 */           c2 = c2_;
/*     */         } 
/*     */       } 
/* 314 */       Coordinate[] cs_ = new Coordinate[cs.length - 2];
/* 315 */       cs_[0] = c1;
/* 316 */       cs_[1] = c2;
/* 317 */       if (seg.id != 0) {
/*     */         int i;
/* 318 */         for (i = seg.id + 3; i < cs.length; i++)
/* 319 */           cs_[i - seg.id - 1] = cs[i]; 
/* 320 */         for (i = 1; i < seg.id - 1; ) {
/* 320 */           cs_[cs.length - seg.id - 2 + i] = cs[i];
/* 320 */           i++;
/*     */         } 
/* 321 */         cs_[cs.length - 3] = c1;
/*     */       } else {
/* 324 */         for (int i = 2; i < cs.length - 3; ) {
/* 324 */           cs_[i] = cs[i + 1];
/* 324 */           i++;
/*     */         } 
/* 325 */         cs_[cs.length - 3] = c1;
/*     */       } 
/* 328 */       if ((cs_[0]).x != (cs_[cs_.length - 1]).x || (cs_[0]).y != (cs_[cs_.length - 1]).y)
/* 328 */         return new RingEdgeDeletionOut(null, false); 
/* 329 */       if (cs_.length <= 3)
/* 329 */         return new RingEdgeDeletionOut(null, false); 
/* 330 */       return new RingEdgeDeletionOut((new GeometryFactory()).createLinearRing(cs_), true);
/*     */     } 
/* 334 */     return new RingEdgeDeletionOut(null, false);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\ShortEdgesDeletion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */