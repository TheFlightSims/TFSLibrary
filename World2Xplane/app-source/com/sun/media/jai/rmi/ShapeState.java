/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.CubicCurve2D;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.QuadCurve2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ 
/*     */ public class ShapeState extends SerializableStateImpl {
/*     */   private static final int SHAPE_UNKNOWN = 0;
/*     */   
/*     */   private static final int SHAPE_AREA = 1;
/*     */   
/*     */   private static final int SHAPE_ARC_DOUBLE = 2;
/*     */   
/*     */   private static final int SHAPE_ARC_FLOAT = 3;
/*     */   
/*     */   private static final int SHAPE_CUBICCURVE_DOUBLE = 4;
/*     */   
/*     */   private static final int SHAPE_CUBICCURVE_FLOAT = 5;
/*     */   
/*     */   private static final int SHAPE_ELLIPSE_DOUBLE = 6;
/*     */   
/*     */   private static final int SHAPE_ELLIPSE_FLOAT = 7;
/*     */   
/*     */   private static final int SHAPE_GENERALPATH = 8;
/*     */   
/*     */   private static final int SHAPE_LINE_DOUBLE = 9;
/*     */   
/*     */   private static final int SHAPE_LINE_FLOAT = 10;
/*     */   
/*     */   private static final int SHAPE_QUADCURVE_DOUBLE = 11;
/*     */   
/*     */   private static final int SHAPE_QUADCURVE_FLOAT = 12;
/*     */   
/*     */   private static final int SHAPE_ROUNDRECTANGLE_DOUBLE = 13;
/*     */   
/*     */   private static final int SHAPE_ROUNDRECTANGLE_FLOAT = 14;
/*     */   
/*     */   private static final int SHAPE_RECTANGLE_DOUBLE = 15;
/*     */   
/*     */   private static final int SHAPE_RECTANGLE_FLOAT = 16;
/*     */   
/*     */   public static Class[] getSupportedClasses() {
/*  60 */     return new Class[] { Shape.class };
/*     */   }
/*     */   
/*     */   public ShapeState(Class c, Object o, RenderingHints h) {
/*  72 */     super(c, o, h);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  81 */     boolean serializable = false;
/*  82 */     Object object = this.theObject;
/*  91 */     if (object instanceof java.io.Serializable)
/*  92 */       serializable = true; 
/*  97 */     out.writeBoolean(serializable);
/*  98 */     if (serializable) {
/*  99 */       out.writeObject(object);
/*     */       return;
/*     */     } 
/* 103 */     Object dataArray = null;
/* 104 */     Object otherData = null;
/* 105 */     int type = 0;
/* 108 */     if (this.theObject instanceof Area) {
/* 109 */       type = 1;
/* 110 */     } else if (this.theObject instanceof Arc2D.Double) {
/* 111 */       Arc2D.Double ad = (Arc2D.Double)this.theObject;
/* 112 */       dataArray = new double[] { ad.x, ad.y, ad.width, ad.height, ad.start, ad.extent };
/* 114 */       type = 2;
/* 115 */       otherData = new Integer(ad.getArcType());
/* 116 */     } else if (this.theObject instanceof Arc2D.Float) {
/* 117 */       Arc2D.Float af = (Arc2D.Float)this.theObject;
/* 118 */       dataArray = new float[] { af.x, af.y, af.width, af.height, af.start, af.extent };
/* 120 */       type = 3;
/* 121 */       otherData = new Integer(af.getArcType());
/* 122 */     } else if (this.theObject instanceof CubicCurve2D.Double) {
/* 123 */       CubicCurve2D.Double cd = (CubicCurve2D.Double)this.theObject;
/* 124 */       dataArray = new double[] { cd.x1, cd.y1, cd.ctrlx1, cd.ctrly1, cd.ctrlx2, cd.ctrly2, cd.x2, cd.y2 };
/* 126 */       type = 4;
/* 127 */     } else if (this.theObject instanceof CubicCurve2D.Float) {
/* 128 */       CubicCurve2D.Float cf = (CubicCurve2D.Float)this.theObject;
/* 129 */       dataArray = new float[] { cf.x1, cf.y1, cf.ctrlx1, cf.ctrly1, cf.ctrlx2, cf.ctrly2, cf.x2, cf.y2 };
/* 131 */       type = 5;
/* 132 */     } else if (this.theObject instanceof Ellipse2D.Double) {
/* 133 */       Ellipse2D.Double ed = (Ellipse2D.Double)this.theObject;
/* 134 */       dataArray = new double[] { ed.x, ed.y, ed.width, ed.height };
/* 135 */       type = 6;
/* 136 */     } else if (this.theObject instanceof Ellipse2D.Float) {
/* 137 */       Ellipse2D.Float ef = (Ellipse2D.Float)this.theObject;
/* 138 */       dataArray = new float[] { ef.x, ef.y, ef.width, ef.height };
/* 139 */       type = 7;
/* 140 */     } else if (this.theObject instanceof GeneralPath) {
/* 141 */       type = 8;
/* 142 */     } else if (this.theObject instanceof Line2D.Double) {
/* 143 */       Line2D.Double ld = (Line2D.Double)this.theObject;
/* 144 */       dataArray = new double[] { ld.x1, ld.y1, ld.x2, ld.y2 };
/* 145 */       type = 9;
/* 146 */     } else if (this.theObject instanceof Line2D.Float) {
/* 147 */       Line2D.Float lf = (Line2D.Float)this.theObject;
/* 148 */       dataArray = new float[] { lf.x1, lf.y1, lf.x2, lf.y2 };
/* 149 */       type = 10;
/* 150 */     } else if (this.theObject instanceof QuadCurve2D.Double) {
/* 151 */       QuadCurve2D.Double qd = (QuadCurve2D.Double)this.theObject;
/* 152 */       dataArray = new double[] { qd.x1, qd.y1, qd.ctrlx, qd.ctrly, qd.x2, qd.y2 };
/* 153 */       type = 11;
/* 154 */     } else if (this.theObject instanceof QuadCurve2D.Float) {
/* 155 */       QuadCurve2D.Float qf = (QuadCurve2D.Float)this.theObject;
/* 156 */       dataArray = new float[] { qf.x1, qf.y1, qf.ctrlx, qf.ctrly, qf.x2, qf.y2 };
/* 157 */       type = 12;
/* 158 */     } else if (this.theObject instanceof RoundRectangle2D.Double) {
/* 159 */       RoundRectangle2D.Double rrd = (RoundRectangle2D.Double)this.theObject;
/* 160 */       dataArray = new double[] { rrd.x, rrd.y, rrd.width, rrd.height, rrd.arcwidth, rrd.archeight };
/* 162 */       type = 13;
/* 163 */     } else if (this.theObject instanceof RoundRectangle2D.Float) {
/* 164 */       RoundRectangle2D.Float rrf = (RoundRectangle2D.Float)this.theObject;
/* 165 */       dataArray = new float[] { rrf.x, rrf.y, rrf.width, rrf.height, rrf.arcwidth, rrf.archeight };
/* 167 */       type = 14;
/* 168 */     } else if (this.theObject instanceof Rectangle2D.Double) {
/* 169 */       Rectangle2D.Double rd = (Rectangle2D.Double)this.theObject;
/* 170 */       dataArray = new double[] { rd.x, rd.y, rd.width, rd.height };
/* 171 */       type = 15;
/* 172 */     } else if (this.theObject instanceof Rectangle2D.Float) {
/* 173 */       Rectangle2D.Float rf = (Rectangle2D.Float)this.theObject;
/* 174 */       dataArray = new float[] { rf.x, rf.y, rf.width, rf.height };
/* 175 */       type = 16;
/*     */     } 
/* 178 */     out.writeInt(type);
/* 179 */     if (dataArray != null) {
/* 180 */       out.writeObject(dataArray);
/* 181 */       if (otherData != null)
/* 182 */         out.writeObject(otherData); 
/*     */       return;
/*     */     } 
/* 186 */     PathIterator pathIterator = ((Shape)this.theObject).getPathIterator(null);
/* 190 */     int rule = pathIterator.getWindingRule();
/* 191 */     out.writeInt(rule);
/* 193 */     float[] coordinates = new float[6];
/* 196 */     boolean isDone = pathIterator.isDone();
/* 197 */     while (!isDone) {
/* 198 */       int segmentType = pathIterator.currentSegment(coordinates);
/* 199 */       out.writeBoolean(isDone);
/* 200 */       out.writeInt(segmentType);
/* 201 */       for (int i = 0; i < 6; i++)
/* 202 */         out.writeFloat(coordinates[i]); 
/* 203 */       pathIterator.next();
/* 204 */       isDone = pathIterator.isDone();
/*     */     } 
/* 206 */     out.writeBoolean(isDone);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*     */     double[] arrayOfDouble7;
/*     */     float[] arrayOfFloat6;
/*     */     double[] arrayOfDouble6;
/*     */     float[] arrayOfFloat5;
/*     */     double[] arrayOfDouble5;
/*     */     float[] arrayOfFloat4;
/*     */     double[] arrayOfDouble4;
/*     */     float[] arrayOfFloat3;
/*     */     double[] arrayOfDouble3;
/*     */     float[] arrayOfFloat2;
/*     */     double[] arrayOfDouble2;
/*     */     float[] arrayOfFloat1;
/*     */     double[] arrayOfDouble1;
/*     */     float[] data;
/*     */     int arcType;
/* 217 */     boolean serializable = in.readBoolean();
/* 220 */     if (serializable) {
/* 221 */       this.theObject = in.readObject();
/*     */       return;
/*     */     } 
/* 226 */     int type = in.readInt();
/* 229 */     switch (type) {
/*     */       case 2:
/* 232 */         arrayOfDouble7 = (double[])in.readObject();
/* 233 */         arcType = ((Integer)in.readObject()).intValue();
/* 234 */         this.theObject = new Arc2D.Double(arrayOfDouble7[0], arrayOfDouble7[1], arrayOfDouble7[2], arrayOfDouble7[3], arrayOfDouble7[4], arrayOfDouble7[5], arcType);
/*     */         return;
/*     */       case 3:
/* 240 */         arrayOfFloat6 = (float[])in.readObject();
/* 241 */         arcType = ((Integer)in.readObject()).intValue();
/* 242 */         this.theObject = new Arc2D.Float(arrayOfFloat6[0], arrayOfFloat6[1], arrayOfFloat6[2], arrayOfFloat6[3], arrayOfFloat6[4], arrayOfFloat6[5], arcType);
/*     */         return;
/*     */       case 4:
/* 248 */         arrayOfDouble6 = (double[])in.readObject();
/* 249 */         this.theObject = new CubicCurve2D.Double(arrayOfDouble6[0], arrayOfDouble6[1], arrayOfDouble6[2], arrayOfDouble6[3], arrayOfDouble6[4], arrayOfDouble6[5], arrayOfDouble6[6], arrayOfDouble6[7]);
/*     */         return;
/*     */       case 5:
/* 256 */         arrayOfFloat5 = (float[])in.readObject();
/* 257 */         this.theObject = new CubicCurve2D.Float(arrayOfFloat5[0], arrayOfFloat5[1], arrayOfFloat5[2], arrayOfFloat5[3], arrayOfFloat5[4], arrayOfFloat5[5], arrayOfFloat5[6], arrayOfFloat5[7]);
/*     */         return;
/*     */       case 6:
/* 264 */         arrayOfDouble5 = (double[])in.readObject();
/* 265 */         this.theObject = new Ellipse2D.Double(arrayOfDouble5[0], arrayOfDouble5[1], arrayOfDouble5[2], arrayOfDouble5[3]);
/*     */         return;
/*     */       case 7:
/* 270 */         arrayOfFloat4 = (float[])in.readObject();
/* 271 */         this.theObject = new Ellipse2D.Float(arrayOfFloat4[0], arrayOfFloat4[1], arrayOfFloat4[2], arrayOfFloat4[3]);
/*     */         return;
/*     */       case 9:
/* 277 */         arrayOfDouble4 = (double[])in.readObject();
/* 278 */         this.theObject = new Line2D.Double(arrayOfDouble4[0], arrayOfDouble4[1], arrayOfDouble4[2], arrayOfDouble4[3]);
/*     */         return;
/*     */       case 10:
/* 283 */         arrayOfFloat3 = (float[])in.readObject();
/* 284 */         this.theObject = new Line2D.Float(arrayOfFloat3[0], arrayOfFloat3[1], arrayOfFloat3[2], arrayOfFloat3[3]);
/*     */         return;
/*     */       case 11:
/* 289 */         arrayOfDouble3 = (double[])in.readObject();
/* 290 */         this.theObject = new QuadCurve2D.Double(arrayOfDouble3[0], arrayOfDouble3[1], arrayOfDouble3[2], arrayOfDouble3[3], arrayOfDouble3[4], arrayOfDouble3[5]);
/*     */         return;
/*     */       case 12:
/* 296 */         arrayOfFloat2 = (float[])in.readObject();
/* 297 */         this.theObject = new QuadCurve2D.Float(arrayOfFloat2[0], arrayOfFloat2[1], arrayOfFloat2[2], arrayOfFloat2[3], arrayOfFloat2[4], arrayOfFloat2[5]);
/*     */         return;
/*     */       case 13:
/* 304 */         arrayOfDouble2 = (double[])in.readObject();
/* 305 */         this.theObject = new RoundRectangle2D.Double(arrayOfDouble2[0], arrayOfDouble2[1], arrayOfDouble2[2], arrayOfDouble2[3], arrayOfDouble2[4], arrayOfDouble2[5]);
/*     */         return;
/*     */       case 14:
/* 311 */         arrayOfFloat1 = (float[])in.readObject();
/* 312 */         this.theObject = new RoundRectangle2D.Float(arrayOfFloat1[0], arrayOfFloat1[1], arrayOfFloat1[2], arrayOfFloat1[3], arrayOfFloat1[4], arrayOfFloat1[5]);
/*     */         return;
/*     */       case 15:
/* 318 */         arrayOfDouble1 = (double[])in.readObject();
/* 319 */         this.theObject = new Rectangle2D.Double(arrayOfDouble1[0], arrayOfDouble1[1], arrayOfDouble1[2], arrayOfDouble1[3]);
/*     */         return;
/*     */       case 16:
/* 325 */         data = (float[])in.readObject();
/* 326 */         this.theObject = new Rectangle2D.Float(data[0], data[1], data[2], data[3]);
/*     */         return;
/*     */     } 
/* 333 */     int rule = in.readInt();
/* 335 */     GeneralPath path = new GeneralPath(rule);
/* 336 */     float[] coordinates = new float[6];
/* 339 */     while (!in.readBoolean()) {
/* 340 */       int segmentType = in.readInt();
/* 341 */       for (int i = 0; i < 6; i++)
/* 342 */         coordinates[i] = in.readFloat(); 
/* 344 */       switch (segmentType) {
/*     */         case 0:
/* 346 */           path.moveTo(coordinates[0], coordinates[1]);
/*     */         case 1:
/* 350 */           path.lineTo(coordinates[0], coordinates[1]);
/*     */         case 2:
/* 354 */           path.quadTo(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
/*     */         case 3:
/* 359 */           path.curveTo(coordinates[0], coordinates[1], coordinates[2], coordinates[3], coordinates[4], coordinates[5]);
/*     */         case 4:
/* 365 */           path.closePath();
/*     */       } 
/*     */     } 
/* 373 */     switch (type) {
/*     */       case 1:
/* 375 */         this.theObject = new Area(path);
/*     */         return;
/*     */     } 
/* 378 */     this.theObject = path;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\ShapeState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */