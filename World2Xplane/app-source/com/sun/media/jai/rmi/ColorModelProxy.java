/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.color.ICC_ColorSpace;
/*     */ import java.awt.color.ICC_Profile;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.DirectColorModel;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import javax.media.jai.FloatDoubleColorModel;
/*     */ 
/*     */ public class ColorModelProxy implements Serializable {
/*     */   private static final int COLORSPACE_UNKNOWN = 0;
/*     */   
/*     */   private static final int COLORSPACE_PREDEFINED = 1;
/*     */   
/*     */   private static final int COLORSPACE_ICC = 2;
/*     */   
/*     */   private static final int COLORMODEL_NULL = 0;
/*     */   
/*     */   private static final int COLORMODEL_FLOAT_DOUBLE_COMPONENT = 1;
/*     */   
/*     */   private static final int COLORMODEL_COMPONENT = 2;
/*     */   
/*     */   private static final int COLORMODEL_INDEX = 3;
/*     */   
/*     */   private static final int COLORMODEL_DIRECT = 4;
/*     */   
/*  66 */   private transient ColorModel colorModel = null;
/*     */   
/*     */   private static int[] getPredefinedColorSpace(ColorSpace cs) {
/*  75 */     int[] colorSpaces = { 1001, 1003, 1004, 1002, 1000 };
/*  81 */     for (int i = 0; i < colorSpaces.length; i++) {
/*     */       try {
/*  83 */         if (cs.equals(ColorSpace.getInstance(colorSpaces[i])))
/*  84 */           return new int[] { colorSpaces[i] }; 
/*  86 */       } catch (Throwable e) {}
/*     */     } 
/*  92 */     int numComponents = cs.getNumComponents();
/*  93 */     int type = cs.getType();
/*  94 */     if (numComponents == 1 && type == 6)
/*  95 */       return new int[] { 1003 }; 
/*  96 */     if (numComponents == 3) {
/*  97 */       if (type == 5)
/*  98 */         return new int[] { 1000 }; 
/*  99 */       if (type == 0)
/* 100 */         return new int[] { 1001 }; 
/*     */     } 
/* 105 */     return null;
/*     */   }
/*     */   
/*     */   private static boolean serializeColorSpace(ColorSpace cs, ObjectOutputStream out) throws IOException {
/* 114 */     int[] colorSpaceType = null;
/* 115 */     if (!(cs instanceof ICC_ColorSpace) && (colorSpaceType = getPredefinedColorSpace(cs)) == null) {
/* 117 */       out.writeInt(0);
/* 118 */       out.writeInt(cs.getNumComponents());
/* 119 */       return false;
/*     */     } 
/* 122 */     if (cs instanceof ICC_ColorSpace) {
/* 123 */       out.writeInt(2);
/* 124 */       ((ICC_ColorSpace)cs).getProfile().write(out);
/*     */     } else {
/* 126 */       out.writeInt(1);
/* 127 */       out.writeInt(colorSpaceType[0]);
/*     */     } 
/* 130 */     return true;
/*     */   }
/*     */   
/*     */   private static ColorSpace deserializeColorSpace(ObjectInputStream in) throws IOException {
/* 138 */     ColorSpace cs = null;
/* 139 */     int colorSpaceType = in.readInt();
/* 140 */     if (colorSpaceType == 2) {
/* 141 */       cs = new ICC_ColorSpace(ICC_Profile.getInstance(in));
/* 142 */     } else if (colorSpaceType == 1) {
/* 143 */       cs = ColorSpace.getInstance(in.readInt());
/* 144 */     } else if (colorSpaceType == 0) {
/* 146 */       switch (in.readInt()) {
/*     */         case 1:
/* 148 */           cs = ColorSpace.getInstance(1003);
/* 158 */           return cs;
/*     */         case 3:
/*     */           cs = ColorSpace.getInstance(1000);
/* 158 */           return cs;
/*     */       } 
/*     */       cs = null;
/*     */     } 
/* 158 */     return cs;
/*     */   }
/*     */   
/*     */   public ColorModelProxy(ColorModel source) {
/* 168 */     this.colorModel = source;
/*     */   }
/*     */   
/*     */   public ColorModel getColorModel() {
/* 176 */     return this.colorModel;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 186 */     if (this.colorModel == null) {
/* 187 */       out.writeInt(0);
/* 188 */     } else if (this.colorModel instanceof ComponentColorModel) {
/* 189 */       ComponentColorModel cm = (ComponentColorModel)this.colorModel;
/* 190 */       int type = 2;
/* 191 */       if (this.colorModel instanceof FloatDoubleColorModel)
/* 192 */         type = 1; 
/* 194 */       out.writeInt(type);
/* 195 */       serializeColorSpace(cm.getColorSpace(), out);
/* 196 */       if (type == 2)
/* 197 */         out.writeObject(cm.getComponentSize()); 
/* 199 */       out.writeBoolean(cm.hasAlpha());
/* 200 */       out.writeBoolean(cm.isAlphaPremultiplied());
/* 201 */       out.writeInt(cm.getTransparency());
/* 204 */       SampleModel sm = cm.createCompatibleSampleModel(1, 1);
/* 205 */       out.writeInt(sm.getTransferType());
/* 206 */     } else if (this.colorModel instanceof IndexColorModel) {
/* 207 */       IndexColorModel cm = (IndexColorModel)this.colorModel;
/* 208 */       out.writeInt(3);
/* 209 */       int size = cm.getMapSize();
/* 210 */       int[] cmap = new int[size];
/* 211 */       cm.getRGBs(cmap);
/* 212 */       out.writeInt(cm.getPixelSize());
/* 213 */       out.writeInt(size);
/* 214 */       out.writeObject(cmap);
/* 215 */       out.writeBoolean(cm.hasAlpha());
/* 216 */       out.writeInt(cm.getTransparentPixel());
/* 219 */       SampleModel sm = cm.createCompatibleSampleModel(1, 1);
/* 220 */       out.writeInt(sm.getTransferType());
/* 221 */     } else if (this.colorModel instanceof DirectColorModel) {
/* 222 */       DirectColorModel cm = (DirectColorModel)this.colorModel;
/* 223 */       out.writeInt(4);
/* 224 */       boolean csSerialized = serializeColorSpace(cm.getColorSpace(), out);
/* 226 */       if (!csSerialized)
/* 227 */         out.writeBoolean(cm.hasAlpha()); 
/* 229 */       out.writeInt(cm.getPixelSize());
/* 230 */       out.writeInt(cm.getRedMask());
/* 231 */       out.writeInt(cm.getGreenMask());
/* 232 */       out.writeInt(cm.getBlueMask());
/* 233 */       if (csSerialized || cm.hasAlpha())
/* 234 */         out.writeInt(cm.getAlphaMask()); 
/* 236 */       if (csSerialized) {
/* 237 */         out.writeBoolean(cm.isAlphaPremultiplied());
/* 241 */         SampleModel sm = cm.createCompatibleSampleModel(1, 1);
/* 242 */         out.writeInt(sm.getTransferType());
/*     */       } 
/*     */     } else {
/* 245 */       throw new RuntimeException(JaiI18N.getString("ColorModelProxy0"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 257 */     ColorSpace cs = null;
/* 260 */     switch (in.readInt()) {
/*     */       case 0:
/* 262 */         this.colorModel = null;
/*     */         return;
/*     */       case 1:
/* 265 */         if ((cs = deserializeColorSpace(in)) == null) {
/* 266 */           this.colorModel = null;
/*     */           return;
/*     */         } 
/* 269 */         this.colorModel = (ColorModel)new FloatDoubleColorModel(cs, in.readBoolean(), in.readBoolean(), in.readInt(), in.readInt());
/*     */         return;
/*     */       case 2:
/* 276 */         if ((cs = deserializeColorSpace(in)) == null) {
/* 277 */           this.colorModel = null;
/*     */           return;
/*     */         } 
/* 280 */         this.colorModel = new ComponentColorModel(cs, (int[])in.readObject(), in.readBoolean(), in.readBoolean(), in.readInt(), in.readInt());
/*     */         return;
/*     */       case 3:
/* 286 */         this.colorModel = new IndexColorModel(in.readInt(), in.readInt(), (int[])in.readObject(), 0, in.readBoolean(), in.readInt(), in.readInt());
/*     */         return;
/*     */       case 4:
/* 293 */         if ((cs = deserializeColorSpace(in)) != null) {
/* 294 */           this.colorModel = new DirectColorModel(cs, in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readBoolean(), in.readInt());
/* 299 */         } else if (in.readBoolean()) {
/* 300 */           this.colorModel = new DirectColorModel(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt());
/*     */         } else {
/* 305 */           this.colorModel = new DirectColorModel(in.readInt(), in.readInt(), in.readInt(), in.readInt());
/*     */         } 
/*     */         return;
/*     */     } 
/* 312 */     throw new RuntimeException(JaiI18N.getString("ColorModelProxy1"));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\ColorModelProxy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */