/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BorderExtender implements Serializable {
/*     */   public static final int BORDER_ZERO = 0;
/*     */   
/*     */   public static final int BORDER_COPY = 1;
/*     */   
/*     */   public static final int BORDER_REFLECT = 2;
/*     */   
/*     */   public static final int BORDER_WRAP = 3;
/*     */   
/*  75 */   private static BorderExtender borderExtenderZero = null;
/*     */   
/*  78 */   private static BorderExtender borderExtenderCopy = null;
/*     */   
/*  81 */   private static BorderExtender borderExtenderReflect = null;
/*     */   
/*  84 */   private static BorderExtender borderExtenderWrap = null;
/*     */   
/*     */   public abstract void extend(WritableRaster paramWritableRaster, PlanarImage paramPlanarImage);
/*     */   
/*     */   public static BorderExtender createInstance(int extenderType) {
/* 153 */     switch (extenderType) {
/*     */       case 0:
/* 155 */         if (borderExtenderZero == null)
/* 156 */           borderExtenderZero = new BorderExtenderZero(); 
/* 158 */         return borderExtenderZero;
/*     */       case 1:
/* 161 */         if (borderExtenderCopy == null)
/* 162 */           borderExtenderCopy = new BorderExtenderCopy(); 
/* 164 */         return borderExtenderCopy;
/*     */       case 2:
/* 167 */         if (borderExtenderReflect == null)
/* 168 */           borderExtenderReflect = new BorderExtenderReflect(); 
/* 170 */         return borderExtenderReflect;
/*     */       case 3:
/* 173 */         if (borderExtenderWrap == null)
/* 174 */           borderExtenderWrap = new BorderExtenderWrap(); 
/* 176 */         return borderExtenderWrap;
/*     */     } 
/* 179 */     throw new IllegalArgumentException(JaiI18N.getString("BorderExtender0"));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\BorderExtender.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */