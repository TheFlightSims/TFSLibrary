/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class ImageSequence extends CollectionImage {
/*     */   protected ImageSequence() {}
/*     */   
/*     */   public ImageSequence(Collection images) {
/*  47 */     super(images);
/*     */   }
/*     */   
/*     */   public PlanarImage getImage(float ts) {
/*  55 */     Iterator iter = iterator();
/*  57 */     while (iter.hasNext()) {
/*  58 */       SequentialImage si = iter.next();
/*  59 */       if (si.timeStamp == ts)
/*  60 */         return si.image; 
/*     */     } 
/*  64 */     return null;
/*     */   }
/*     */   
/*     */   public PlanarImage getImage(Object cp) {
/*  73 */     if (cp != null) {
/*  74 */       Iterator iter = iterator();
/*  76 */       while (iter.hasNext()) {
/*  77 */         SequentialImage si = iter.next();
/*  78 */         if (si.cameraPosition.equals(cp))
/*  79 */           return si.image; 
/*     */       } 
/*     */     } 
/*  84 */     return null;
/*     */   }
/*     */   
/*     */   public float getTimeStamp(PlanarImage pi) {
/*  93 */     if (pi != null) {
/*  94 */       Iterator iter = iterator();
/*  96 */       while (iter.hasNext()) {
/*  97 */         SequentialImage si = iter.next();
/*  98 */         if (si.image.equals(pi))
/*  99 */           return si.timeStamp; 
/*     */       } 
/*     */     } 
/* 104 */     return -3.4028235E38F;
/*     */   }
/*     */   
/*     */   public Object getCameraPosition(PlanarImage pi) {
/* 113 */     if (pi != null) {
/* 114 */       Iterator iter = iterator();
/* 116 */       while (iter.hasNext()) {
/* 117 */         SequentialImage si = iter.next();
/* 118 */         if (si.image.equals(pi))
/* 119 */           return si.cameraPosition; 
/*     */       } 
/*     */     } 
/* 124 */     return null;
/*     */   }
/*     */   
/*     */   public boolean add(Object o) {
/* 136 */     if (o != null && o instanceof SequentialImage)
/* 137 */       return super.add(o); 
/* 139 */     return false;
/*     */   }
/*     */   
/*     */   public boolean remove(PlanarImage pi) {
/* 152 */     if (pi != null) {
/* 153 */       Iterator iter = iterator();
/* 155 */       while (iter.hasNext()) {
/* 156 */         SequentialImage si = iter.next();
/* 157 */         if (si.image.equals(pi))
/* 158 */           return super.remove(si); 
/*     */       } 
/*     */     } 
/* 163 */     return false;
/*     */   }
/*     */   
/*     */   public boolean remove(float ts) {
/* 175 */     Iterator iter = iterator();
/* 177 */     while (iter.hasNext()) {
/* 178 */       SequentialImage si = iter.next();
/* 179 */       if (si.timeStamp == ts)
/* 180 */         return super.remove(si); 
/*     */     } 
/* 184 */     return false;
/*     */   }
/*     */   
/*     */   public boolean remove(Object cp) {
/* 196 */     if (cp != null) {
/* 197 */       Iterator iter = iterator();
/* 199 */       while (iter.hasNext()) {
/* 200 */         SequentialImage si = iter.next();
/* 201 */         if (si.cameraPosition.equals(cp))
/* 202 */           return super.remove(si); 
/*     */       } 
/*     */     } 
/* 207 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ImageSequence.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */