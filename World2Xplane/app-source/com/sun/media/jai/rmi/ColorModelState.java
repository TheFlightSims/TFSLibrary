/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import com.sun.media.jai.codecimpl.util.FloatDoubleColorModel;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.DirectColorModel;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import javax.media.jai.FloatDoubleColorModel;
/*     */ 
/*     */ public class ColorModelState extends SerializableStateImpl {
/*     */   private static final int COLORSPACE_OTHERS = 0;
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
/*  67 */   private transient ColorModel colorModel = null;
/*     */   
/*     */   private static int[] getPredefinedColorSpace(ColorSpace cs) {
/*  76 */     int[] colorSpaces = { 1001, 1003, 1004, 1002, 1000 };
/*  82 */     for (int i = 0; i < colorSpaces.length; i++) {
/*     */       try {
/*  84 */         if (cs.equals(ColorSpace.getInstance(colorSpaces[i])))
/*  85 */           return new int[] { colorSpaces[i] }; 
/*  87 */       } catch (Throwable e) {}
/*     */     } 
/*  93 */     int numComponents = cs.getNumComponents();
/*  94 */     int type = cs.getType();
/*  95 */     if (numComponents == 1 && type == 6)
/*  96 */       return new int[] { 1003 }; 
/*  97 */     if (numComponents == 3) {
/*  98 */       if (type == 5)
/*  99 */         return new int[] { 1000 }; 
/* 100 */       if (type == 0)
/* 101 */         return new int[] { 1001 }; 
/*     */     } 
/* 106 */     return null;
/*     */   }
/*     */   
/*     */   private static boolean serializeColorSpace(ColorSpace cs, ObjectOutputStream out) throws IOException {
/* 115 */     int[] colorSpaceType = getPredefinedColorSpace(cs);
/* 116 */     boolean isICCColorSpace = cs instanceof java.awt.color.ICC_ColorSpace;
/* 118 */     if (colorSpaceType == null) {
/* 119 */       out.writeInt(0);
/* 121 */       Object object = cs;
/* 122 */       boolean flag = false;
/*     */       try {
/* 124 */         Class cls = cs.getClass();
/* 125 */         Method getInstance = cls.getMethod("getInstance", null);
/* 127 */         if (Modifier.isPublic(cls.getModifiers())) {
/* 128 */           flag = true;
/* 129 */           object = cls.getName();
/*     */         } 
/* 131 */       } catch (Exception e) {
/*     */       
/*     */       } finally {
/* 133 */         out.writeBoolean(flag);
/* 134 */         out.writeObject(object);
/*     */       } 
/*     */     } else {
/* 137 */       out.writeInt(1);
/* 138 */       out.writeInt(colorSpaceType[0]);
/*     */     } 
/* 141 */     return true;
/*     */   }
/*     */   
/*     */   private static ColorSpace deserializeColorSpace(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 149 */     ColorSpace cs = null;
/* 150 */     int colorSpaceType = in.readInt();
/* 151 */     if (colorSpaceType == 0) {
/* 152 */       if (in.readBoolean()) {
/* 153 */         String name = (String)in.readObject();
/*     */         try {
/* 155 */           Class cls = Class.forName(name);
/* 156 */           Method getInstance = cls.getMethod("getInstance", null);
/* 158 */           cs = (ColorSpace)getInstance.invoke(null, null);
/*     */         } catch (Exception e) {
/* 160 */           e.printStackTrace();
/*     */         } 
/*     */       } else {
/* 162 */         cs = (ColorSpace)in.readObject();
/*     */       } 
/* 164 */     } else if (colorSpaceType == 1) {
/* 165 */       cs = ColorSpace.getInstance(in.readInt());
/*     */     } 
/* 168 */     return cs;
/*     */   }
/*     */   
/*     */   public static Class[] getSupportedClasses() {
/* 172 */     return new Class[] { ComponentColorModel.class, FloatDoubleColorModel.class, IndexColorModel.class, DirectColorModel.class, FloatDoubleColorModel.class };
/*     */   }
/*     */   
/*     */   public ColorModelState(Class c, Object o, RenderingHints h) {
/* 190 */     super(c, o, h);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 199 */     ColorModel colorModel = (ColorModel)this.theObject;
/* 202 */     if (colorModel == null) {
/* 203 */       out.writeInt(0);
/* 204 */     } else if (colorModel instanceof ComponentColorModel) {
/* 205 */       ComponentColorModel cm = (ComponentColorModel)colorModel;
/* 206 */       int type = 2;
/* 207 */       if (colorModel instanceof FloatDoubleColorModel)
/* 208 */         type = 1; 
/* 210 */       out.writeInt(type);
/* 211 */       serializeColorSpace(cm.getColorSpace(), out);
/* 212 */       if (type == 2)
/* 213 */         out.writeObject(cm.getComponentSize()); 
/* 215 */       out.writeBoolean(cm.hasAlpha());
/* 216 */       out.writeBoolean(cm.isAlphaPremultiplied());
/* 217 */       out.writeInt(cm.getTransparency());
/* 220 */       SampleModel sm = cm.createCompatibleSampleModel(1, 1);
/* 221 */       out.writeInt(sm.getTransferType());
/* 222 */     } else if (colorModel instanceof IndexColorModel) {
/* 223 */       IndexColorModel cm = (IndexColorModel)colorModel;
/* 224 */       out.writeInt(3);
/* 225 */       int size = cm.getMapSize();
/* 226 */       int[] cmap = new int[size];
/* 227 */       cm.getRGBs(cmap);
/* 228 */       out.writeInt(cm.getPixelSize());
/* 229 */       out.writeInt(size);
/* 230 */       out.writeObject(cmap);
/* 231 */       out.writeBoolean(cm.hasAlpha());
/* 232 */       out.writeInt(cm.getTransparentPixel());
/* 235 */       SampleModel sm = cm.createCompatibleSampleModel(1, 1);
/* 236 */       out.writeInt(sm.getTransferType());
/* 237 */     } else if (colorModel instanceof DirectColorModel) {
/* 238 */       DirectColorModel cm = (DirectColorModel)colorModel;
/* 239 */       out.writeInt(4);
/* 240 */       boolean csSerialized = serializeColorSpace(cm.getColorSpace(), out);
/* 242 */       if (!csSerialized)
/* 243 */         out.writeBoolean(cm.hasAlpha()); 
/* 245 */       out.writeInt(cm.getPixelSize());
/* 246 */       out.writeInt(cm.getRedMask());
/* 247 */       out.writeInt(cm.getGreenMask());
/* 248 */       out.writeInt(cm.getBlueMask());
/* 249 */       if (csSerialized || cm.hasAlpha())
/* 250 */         out.writeInt(cm.getAlphaMask()); 
/* 252 */       if (csSerialized) {
/* 253 */         out.writeBoolean(cm.isAlphaPremultiplied());
/* 257 */         SampleModel sm = cm.createCompatibleSampleModel(1, 1);
/* 258 */         out.writeInt(sm.getTransferType());
/*     */       } 
/*     */     } else {
/* 261 */       throw new RuntimeException(JaiI18N.getString("ColorModelState0"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*     */     FloatDoubleColorModel floatDoubleColorModel;
/*     */     ComponentColorModel componentColorModel;
/*     */     IndexColorModel indexColorModel;
/*     */     DirectColorModel directColorModel;
/* 272 */     ColorModel colorModel = null;
/* 275 */     ColorSpace cs = null;
/* 278 */     switch (in.readInt()) {
/*     */       case 0:
/* 280 */         colorModel = null;
/*     */         break;
/*     */       case 1:
/* 283 */         if ((cs = deserializeColorSpace(in)) == null) {
/* 284 */           colorModel = null;
/*     */           return;
/*     */         } 
/* 287 */         floatDoubleColorModel = new FloatDoubleColorModel(cs, in.readBoolean(), in.readBoolean(), in.readInt(), in.readInt());
/*     */         break;
/*     */       case 2:
/* 294 */         if ((cs = deserializeColorSpace(in)) == null) {
/* 295 */           floatDoubleColorModel = null;
/*     */           return;
/*     */         } 
/* 298 */         componentColorModel = new ComponentColorModel(cs, (int[])in.readObject(), in.readBoolean(), in.readBoolean(), in.readInt(), in.readInt());
/*     */         break;
/*     */       case 3:
/* 304 */         indexColorModel = new IndexColorModel(in.readInt(), in.readInt(), (int[])in.readObject(), 0, in.readBoolean(), in.readInt(), in.readInt());
/*     */         break;
/*     */       case 4:
/* 311 */         if ((cs = deserializeColorSpace(in)) != null) {
/* 312 */           DirectColorModel directColorModel1 = new DirectColorModel(cs, in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readBoolean(), in.readInt());
/*     */           break;
/*     */         } 
/* 317 */         if (in.readBoolean()) {
/* 318 */           DirectColorModel directColorModel1 = new DirectColorModel(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt());
/*     */           break;
/*     */         } 
/* 323 */         directColorModel = new DirectColorModel(in.readInt(), in.readInt(), in.readInt(), in.readInt());
/*     */         break;
/*     */       default:
/* 330 */         throw new RuntimeException(JaiI18N.getString("ColorModelState1"));
/*     */     } 
/* 333 */     this.theObject = directColorModel;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\ColorModelState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */