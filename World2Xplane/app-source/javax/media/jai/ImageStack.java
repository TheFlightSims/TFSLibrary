/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public abstract class ImageStack extends CollectionImage {
/*     */   protected ImageStack() {}
/*     */   
/*     */   public ImageStack(Collection images) {
/*  44 */     super(images);
/*     */   }
/*     */   
/*     */   public PlanarImage getImage(Object c) {
/*  55 */     if (c != null) {
/*  56 */       Iterator iter = iterator();
/*  58 */       while (iter.hasNext()) {
/*  59 */         CoordinateImage ci = iter.next();
/*  60 */         if (ci.coordinate.equals(c))
/*  61 */           return ci.image; 
/*     */       } 
/*     */     } 
/*  66 */     return null;
/*     */   }
/*     */   
/*     */   public Object getCoordinate(PlanarImage pi) {
/*  77 */     if (pi != null) {
/*  78 */       Iterator iter = iterator();
/*  80 */       while (iter.hasNext()) {
/*  81 */         CoordinateImage ci = iter.next();
/*  82 */         if (ci.image.equals(pi))
/*  83 */           return ci.coordinate; 
/*     */       } 
/*     */     } 
/*  88 */     return null;
/*     */   }
/*     */   
/*     */   public boolean add(Object o) {
/* 100 */     if (o != null && o instanceof CoordinateImage)
/* 101 */       return super.add(o); 
/* 103 */     return false;
/*     */   }
/*     */   
/*     */   public boolean remove(PlanarImage pi) {
/* 116 */     if (pi != null) {
/* 117 */       Iterator iter = iterator();
/* 119 */       while (iter.hasNext()) {
/* 120 */         CoordinateImage ci = iter.next();
/* 121 */         if (ci.image.equals(pi))
/* 122 */           return super.remove(ci); 
/*     */       } 
/*     */     } 
/* 127 */     return false;
/*     */   }
/*     */   
/*     */   public boolean remove(Object c) {
/* 139 */     if (c != null) {
/* 140 */       Iterator iter = iterator();
/* 142 */       while (iter.hasNext()) {
/* 143 */         CoordinateImage ci = iter.next();
/* 144 */         if (ci.coordinate.equals(c))
/* 145 */           return super.remove(ci); 
/*     */       } 
/*     */     } 
/* 150 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ImageStack.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */