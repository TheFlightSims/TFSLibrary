/*     */ package com.vividsolutions.jts.util;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class Debug {
/*  62 */   public static String DEBUG_PROPERTY_NAME = "jts.debug";
/*     */   
/*  63 */   public static String DEBUG_PROPERTY_VALUE_ON = "on";
/*     */   
/*  64 */   public static String DEBUG_PROPERTY_VALUE_TRUE = "true";
/*     */   
/*     */   private static boolean debugOn = false;
/*     */   
/*     */   static {
/*  69 */     String debugValue = System.getProperty(DEBUG_PROPERTY_NAME);
/*  70 */     if (debugValue != null && (
/*  71 */       debugValue.equalsIgnoreCase(DEBUG_PROPERTY_VALUE_ON) || debugValue.equalsIgnoreCase(DEBUG_PROPERTY_VALUE_TRUE)))
/*  73 */       debugOn = true; 
/*     */   }
/*     */   
/*  77 */   private static Stopwatch stopwatch = new Stopwatch();
/*     */   
/*     */   private static long lastTimePrinted;
/*     */   
/*     */   public static void main(String[] args) {
/*  87 */     System.out.println("JTS Debugging is " + (debugOn ? "ON" : "OFF"));
/*     */   }
/*     */   
/*  91 */   private static final Debug debug = new Debug();
/*     */   
/*  92 */   private static final GeometryFactory fact = new GeometryFactory();
/*     */   
/*     */   private static final String DEBUG_LINE_TAG = "D! ";
/*     */   
/*     */   private PrintStream out;
/*     */   
/*     */   private Class[] printArgs;
/*     */   
/*  97 */   private Object watchObj = null;
/*     */   
/*  98 */   private Object[] args = new Object[1];
/*     */   
/*     */   public static boolean isDebugging() {
/* 100 */     return debugOn;
/*     */   }
/*     */   
/*     */   public static LineString toLine(Coordinate p0, Coordinate p1) {
/* 103 */     return fact.createLineString(new Coordinate[] { p0, p1 });
/*     */   }
/*     */   
/*     */   public static LineString toLine(Coordinate p0, Coordinate p1, Coordinate p2) {
/* 107 */     return fact.createLineString(new Coordinate[] { p0, p1, p2 });
/*     */   }
/*     */   
/*     */   public static LineString toLine(Coordinate p0, Coordinate p1, Coordinate p2, Coordinate p3) {
/* 111 */     return fact.createLineString(new Coordinate[] { p0, p1, p2, p3 });
/*     */   }
/*     */   
/*     */   public static void print(String str) {
/* 115 */     if (!debugOn)
/*     */       return; 
/* 118 */     debug.instancePrint(str);
/*     */   }
/*     */   
/*     */   public static void print(Object obj) {
/* 128 */     if (!debugOn)
/*     */       return; 
/* 129 */     debug.instancePrint(obj);
/*     */   }
/*     */   
/*     */   public static void print(boolean isTrue, Object obj) {
/* 133 */     if (!debugOn)
/*     */       return; 
/* 134 */     if (!isTrue)
/*     */       return; 
/* 135 */     debug.instancePrint(obj);
/*     */   }
/*     */   
/*     */   public static void println(Object obj) {
/* 139 */     if (!debugOn)
/*     */       return; 
/* 142 */     debug.instancePrint(obj);
/* 143 */     debug.println();
/*     */   }
/*     */   
/*     */   public static void resetTime() {
/* 148 */     stopwatch.reset();
/* 149 */     lastTimePrinted = stopwatch.getTime();
/*     */   }
/*     */   
/*     */   public static void printTime(String tag) {
/* 154 */     if (!debugOn)
/*     */       return; 
/* 157 */     long time = stopwatch.getTime();
/* 158 */     long elapsedTime = time - lastTimePrinted;
/* 159 */     debug.instancePrint(formatField(Stopwatch.getTimeString(time), 10) + " (" + formatField(Stopwatch.getTimeString(elapsedTime), 10) + " ) " + tag);
/* 163 */     debug.println();
/* 164 */     lastTimePrinted = time;
/*     */   }
/*     */   
/*     */   private static String formatField(String s, int fieldLen) {
/* 169 */     int nPad = fieldLen - s.length();
/* 170 */     if (nPad <= 0)
/* 170 */       return s; 
/* 171 */     String padStr = spaces(nPad) + s;
/* 172 */     return padStr.substring(padStr.length() - fieldLen);
/*     */   }
/*     */   
/*     */   private static String spaces(int n) {
/* 177 */     char[] ch = new char[n];
/* 178 */     for (int i = 0; i < n; i++)
/* 179 */       ch[i] = ' '; 
/* 181 */     return new String(ch);
/*     */   }
/*     */   
/*     */   public static boolean equals(Coordinate c1, Coordinate c2, double tolerance) {
/* 186 */     return (c1.distance(c2) <= tolerance);
/*     */   }
/*     */   
/*     */   public static void addWatch(Object obj) {
/* 196 */     debug.instanceAddWatch(obj);
/*     */   }
/*     */   
/*     */   public static void printWatch() {
/* 200 */     debug.instancePrintWatch();
/*     */   }
/*     */   
/*     */   public static void printIfWatch(Object obj) {
/* 204 */     debug.instancePrintIfWatch(obj);
/*     */   }
/*     */   
/*     */   public static void breakIf(boolean cond) {
/* 209 */     if (cond)
/* 209 */       doBreak(); 
/*     */   }
/*     */   
/*     */   public static void breakIfEqual(Object o1, Object o2) {
/* 214 */     if (o1.equals(o2))
/* 214 */       doBreak(); 
/*     */   }
/*     */   
/*     */   public static void breakIfEqual(Coordinate p0, Coordinate p1, double tolerance) {
/* 219 */     if (p0.distance(p1) <= tolerance)
/* 219 */       doBreak(); 
/*     */   }
/*     */   
/*     */   private static void doBreak() {}
/*     */   
/*     */   public static boolean hasSegment(Geometry geom, Coordinate p0, Coordinate p1) {
/* 230 */     SegmentFindingFilter filter = new SegmentFindingFilter(p0, p1);
/* 231 */     geom.apply(filter);
/* 232 */     return filter.hasSegment();
/*     */   }
/*     */   
/*     */   private static class SegmentFindingFilter implements CoordinateSequenceFilter {
/*     */     private Coordinate p0;
/*     */     
/*     */     private Coordinate p1;
/*     */     
/*     */     private boolean hasSegment = false;
/*     */     
/*     */     public SegmentFindingFilter(Coordinate p0, Coordinate p1) {
/* 243 */       this.p0 = p0;
/* 244 */       this.p1 = p1;
/*     */     }
/*     */     
/*     */     public boolean hasSegment() {
/* 247 */       return this.hasSegment;
/*     */     }
/*     */     
/*     */     public void filter(CoordinateSequence seq, int i) {
/* 251 */       if (i == 0)
/*     */         return; 
/* 252 */       this.hasSegment = (this.p0.equals2D(seq.getCoordinate(i - 1)) && this.p1.equals2D(seq.getCoordinate(i)));
/*     */     }
/*     */     
/*     */     public boolean isDone() {
/* 258 */       return this.hasSegment;
/*     */     }
/*     */     
/*     */     public boolean isGeometryChanged() {
/* 263 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   private Debug() {
/* 268 */     this.out = System.out;
/* 269 */     this.printArgs = new Class[1];
/*     */     try {
/* 271 */       this.printArgs[0] = Class.forName("java.io.PrintStream");
/* 273 */     } catch (Exception ex) {}
/*     */   }
/*     */   
/*     */   public void instancePrintWatch() {
/* 279 */     if (this.watchObj == null)
/*     */       return; 
/* 280 */     instancePrint(this.watchObj);
/*     */   }
/*     */   
/*     */   public void instancePrintIfWatch(Object obj) {
/* 284 */     if (obj != this.watchObj)
/*     */       return; 
/* 285 */     if (this.watchObj == null)
/*     */       return; 
/* 286 */     instancePrint(this.watchObj);
/*     */   }
/*     */   
/*     */   public void instancePrint(Object obj) {
/* 291 */     if (obj instanceof Collection) {
/* 292 */       instancePrint(((Collection)obj).iterator());
/* 294 */     } else if (obj instanceof Iterator) {
/* 295 */       instancePrint((Iterator)obj);
/*     */     } else {
/* 298 */       instancePrintObject(obj);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void instancePrint(Iterator it) {
/* 304 */     while (it.hasNext()) {
/* 305 */       Object obj = it.next();
/* 306 */       instancePrintObject(obj);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void instancePrintObject(Object obj) {
/* 311 */     Method printMethod = null;
/*     */     try {
/* 313 */       Class<?> cls = obj.getClass();
/*     */       try {
/* 315 */         printMethod = cls.getMethod("print", this.printArgs);
/* 316 */         this.args[0] = this.out;
/* 317 */         this.out.print("D! ");
/* 318 */         printMethod.invoke(obj, this.args);
/* 320 */       } catch (NoSuchMethodException ex) {
/* 321 */         instancePrint(obj.toString());
/*     */       } 
/* 324 */     } catch (Exception ex) {
/* 325 */       ex.printStackTrace(this.out);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void println() {
/* 330 */     this.out.println();
/*     */   }
/*     */   
/*     */   private void instanceAddWatch(Object obj) {
/* 334 */     this.watchObj = obj;
/*     */   }
/*     */   
/*     */   private void instancePrint(String str) {
/* 338 */     this.out.print("D! ");
/* 339 */     this.out.print(str);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jt\\util\Debug.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */