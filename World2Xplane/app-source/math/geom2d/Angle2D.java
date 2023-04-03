/*     */ package math.geom2d;
/*     */ 
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ 
/*     */ public class Angle2D {
/*     */   public static final double M_PI = 3.141592653589793D;
/*     */   
/*     */   public static final double M_2PI = 6.283185307179586D;
/*     */   
/*     */   public static final double M_PI_2 = 1.5707963267948966D;
/*     */   
/*     */   public static final double M_3PI_2 = 4.71238898038469D;
/*     */   
/*     */   public static final double M_PI_4 = 0.7853981633974483D;
/*     */   
/*     */   public static double formatAngle(double angle) {
/*  62 */     return (angle % 6.283185307179586D + 6.283185307179586D) % 6.283185307179586D;
/*     */   }
/*     */   
/*     */   public static double horizontalAngle(Point2D point) {
/*  70 */     return (Math.atan2(point.y, point.x) + 6.283185307179586D) % 6.283185307179586D;
/*     */   }
/*     */   
/*     */   public static double horizontalAngle(double x, double y) {
/*  78 */     return (Math.atan2(y, x) + 6.283185307179586D) % 6.283185307179586D;
/*     */   }
/*     */   
/*     */   public static double horizontalAngle(Vector2D vect) {
/*  86 */     return (Math.atan2(vect.y, vect.x) + 6.283185307179586D) % 6.283185307179586D;
/*     */   }
/*     */   
/*     */   public static double horizontalAngle(LinearShape2D object) {
/*  94 */     Vector2D vect = object.supportingLine().direction();
/*  95 */     return (Math.atan2(vect.y, vect.x) + 6.283185307179586D) % 6.283185307179586D;
/*     */   }
/*     */   
/*     */   public static double horizontalAngle(Point2D p1, Point2D p2) {
/* 103 */     return (Math.atan2(p2.y - p1.y, p2.x - p1.x) + 6.283185307179586D) % 6.283185307179586D;
/*     */   }
/*     */   
/*     */   public static double horizontalAngle(double x1, double y1, double x2, double y2) {
/* 112 */     return (Math.atan2(y2 - y1, x2 - x1) + 6.283185307179586D) % 6.283185307179586D;
/*     */   }
/*     */   
/*     */   public static double pseudoAngle(Point2D p1, Point2D p2) {
/* 129 */     double dx = p2.x - p1.x;
/* 130 */     double dy = p2.y - p1.y;
/* 131 */     double s = Math.abs(dx) + Math.abs(dy);
/* 132 */     double t = (s == 0.0D) ? 0.0D : (dy / s);
/* 133 */     if (dx < 0.0D) {
/* 134 */       t = 2.0D - t;
/* 135 */     } else if (dy < 0.0D) {
/* 136 */       t += 4.0D;
/*     */     } 
/* 138 */     return t * 90.0D;
/*     */   }
/*     */   
/*     */   public static double angle(LinearShape2D obj1, LinearShape2D obj2) {
/* 146 */     double angle1 = obj1.horizontalAngle();
/* 147 */     double angle2 = obj2.horizontalAngle();
/* 148 */     return (angle2 - angle1 + 6.283185307179586D) % 6.283185307179586D;
/*     */   }
/*     */   
/*     */   public static double angle(Vector2D vect1, Vector2D vect2) {
/* 156 */     double angle1 = horizontalAngle(vect1);
/* 157 */     double angle2 = horizontalAngle(vect2);
/* 158 */     return (angle2 - angle1 + 6.283185307179586D) % 6.283185307179586D;
/*     */   }
/*     */   
/*     */   public static double angle(Point2D p1, Point2D p2, Point2D p3) {
/* 167 */     double angle1 = horizontalAngle(p2, p1);
/* 168 */     double angle2 = horizontalAngle(p2, p3);
/* 169 */     return (angle2 - angle1 + 6.283185307179586D) % 6.283185307179586D;
/*     */   }
/*     */   
/*     */   public static double angle(double x1, double y1, double x2, double y2, double x3, double y3) {
/* 179 */     double angle1 = horizontalAngle(x2, y2, x1, y1);
/* 180 */     double angle2 = horizontalAngle(x2, y2, x3, y3);
/* 181 */     return (angle2 - angle1 + 6.283185307179586D) % 6.283185307179586D;
/*     */   }
/*     */   
/*     */   public static double absoluteAngle(Point2D p1, Point2D p2, Point2D p3) {
/* 190 */     double angle1 = horizontalAngle(new Vector2D(p2, p1));
/* 191 */     double angle2 = horizontalAngle(new Vector2D(p2, p3));
/* 192 */     angle1 = (angle2 - angle1 + 6.283185307179586D) % 6.283185307179586D;
/* 193 */     if (angle1 < Math.PI)
/* 194 */       return angle1; 
/* 196 */     return 6.283185307179586D - angle1;
/*     */   }
/*     */   
/*     */   public static double absoluteAngle(double x1, double y1, double x2, double y2, double x3, double y3) {
/* 206 */     double angle1 = horizontalAngle(x2, y2, x1, y1);
/* 207 */     double angle2 = horizontalAngle(x2, y2, x3, y3);
/* 208 */     angle1 = (angle2 - angle1 + 6.283185307179586D) % 6.283185307179586D;
/* 209 */     if (angle1 < Math.PI)
/* 210 */       return angle1; 
/* 212 */     return 6.283185307179586D - angle1;
/*     */   }
/*     */   
/*     */   public static boolean almostEquals(double angle1, double angle2, double eps) {
/* 228 */     angle1 = formatAngle(angle1);
/* 229 */     angle2 = formatAngle(angle2);
/* 230 */     double diff = formatAngle(angle1 - angle2);
/* 231 */     if (diff < eps)
/* 232 */       return true; 
/* 233 */     if (Math.abs(diff - 6.283185307179586D) < eps)
/* 234 */       return true; 
/* 235 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean equals(double angle1, double angle2) {
/* 248 */     angle1 = formatAngle(angle1);
/* 249 */     angle2 = formatAngle(angle2);
/* 250 */     double diff = formatAngle(angle1 - angle2);
/* 251 */     if (diff < 1.0E-12D)
/* 252 */       return true; 
/* 253 */     if (Math.abs(diff - 6.283185307179586D) < 1.0E-12D)
/* 254 */       return true; 
/* 255 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean containsAngle(double startAngle, double endAngle, double angle) {
/* 272 */     startAngle = formatAngle(startAngle);
/* 273 */     endAngle = formatAngle(endAngle);
/* 274 */     angle = formatAngle(angle);
/* 275 */     if (startAngle < endAngle)
/* 276 */       return (angle >= startAngle && angle <= endAngle); 
/* 278 */     return !(angle > endAngle && angle < startAngle);
/*     */   }
/*     */   
/*     */   public static boolean containsAngle(double startAngle, double endAngle, double angle, boolean direct) {
/* 298 */     startAngle = formatAngle(startAngle);
/* 299 */     endAngle = formatAngle(endAngle);
/* 300 */     angle = formatAngle(angle);
/* 301 */     if (direct) {
/* 302 */       if (startAngle < endAngle)
/* 303 */         return (angle >= startAngle && angle <= endAngle); 
/* 305 */       return !(angle > endAngle && angle < startAngle);
/*     */     } 
/* 307 */     if (startAngle < endAngle)
/* 308 */       return !(angle > startAngle && angle < endAngle); 
/* 310 */     return (angle >= endAngle && angle <= startAngle);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\Angle2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */