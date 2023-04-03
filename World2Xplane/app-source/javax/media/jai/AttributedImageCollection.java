/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class AttributedImageCollection extends CollectionImage {
/*     */   protected AttributedImageCollection() {}
/*     */   
/*     */   public AttributedImageCollection(Collection images) {
/*  53 */     if (images == null)
/*  54 */       throw new IllegalArgumentException(JaiI18N.getString("AttributedImageCollection0")); 
/*     */     try {
/*  59 */       this.imageCollection = (Collection)images.getClass().newInstance();
/*  60 */     } catch (Exception e) {
/*  62 */       this.imageCollection = new ArrayList(images.size());
/*     */     } 
/*  66 */     Iterator iter = images.iterator();
/*  67 */     while (iter.hasNext()) {
/*  68 */       Object o = iter.next();
/*  70 */       if (o instanceof AttributedImage && !this.imageCollection.contains(o))
/*  72 */         this.imageCollection.add(o); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set getAll(Object attribute) {
/*  86 */     if (attribute == null)
/*  87 */       return (Set)this.imageCollection; 
/*  89 */     HashSet set = null;
/*  90 */     Iterator iter = iterator();
/*  92 */     while (iter.hasNext()) {
/*  93 */       AttributedImage ai = iter.next();
/*  95 */       if (attribute.equals(ai.getAttribute())) {
/*  96 */         if (set == null)
/*  97 */           set = new HashSet(); 
/* 100 */         set.add(ai);
/*     */       } 
/*     */     } 
/* 104 */     return set;
/*     */   }
/*     */   
/*     */   public Set getAll(PlanarImage image) {
/* 116 */     if (image == null)
/* 117 */       return (Set)this.imageCollection; 
/* 119 */     HashSet set = null;
/* 120 */     Iterator iter = iterator();
/* 122 */     while (iter.hasNext()) {
/* 123 */       AttributedImage ai = iter.next();
/* 125 */       if (image.equals(ai.getImage())) {
/* 126 */         if (set == null)
/* 127 */           set = new HashSet(); 
/* 130 */         set.add(ai);
/*     */       } 
/*     */     } 
/* 134 */     return set;
/*     */   }
/*     */   
/*     */   public Set removeAll(Object attribute) {
/* 147 */     if (attribute == null)
/* 148 */       return null; 
/* 150 */     Iterator iter = iterator();
/* 151 */     Set removed = null;
/* 153 */     while (iter.hasNext()) {
/* 154 */       AttributedImage ai = iter.next();
/* 156 */       if (attribute.equals(ai.getAttribute())) {
/* 157 */         iter.remove();
/* 158 */         if (removed == null)
/* 159 */           removed = new HashSet(); 
/* 161 */         removed.add(ai);
/*     */       } 
/*     */     } 
/* 165 */     return removed;
/*     */   }
/*     */   
/*     */   public Set removeAll(PlanarImage image) {
/* 177 */     if (image == null)
/* 178 */       return null; 
/* 180 */     Iterator iter = iterator();
/* 181 */     Set removed = null;
/* 183 */     while (iter.hasNext()) {
/* 184 */       AttributedImage ai = iter.next();
/* 186 */       if (image.equals(ai.getImage())) {
/* 187 */         iter.remove();
/* 188 */         if (removed == null)
/* 189 */           removed = new HashSet(); 
/* 191 */         removed.add(ai);
/*     */       } 
/*     */     } 
/* 195 */     return removed;
/*     */   }
/*     */   
/*     */   public boolean add(Object o) {
/* 214 */     if (o == null || !(o instanceof AttributedImage))
/* 215 */       throw new IllegalArgumentException(JaiI18N.getString("AttributedImageCollection1")); 
/* 219 */     if (this.imageCollection.contains(o))
/* 220 */       return false; 
/* 223 */     return this.imageCollection.add(o);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection c) {
/* 234 */     if (c == null)
/* 234 */       return false; 
/* 237 */     Iterator iter = c.iterator();
/* 238 */     boolean flag = false;
/* 240 */     while (iter.hasNext()) {
/* 241 */       Object o = iter.next();
/* 244 */       if (o instanceof AttributedImage && 
/* 245 */         !this.imageCollection.contains(o) && this.imageCollection.add(o))
/* 247 */         flag = true; 
/*     */     } 
/* 252 */     return flag;
/*     */   }
/*     */   
/*     */   public AttributedImage getAttributedImage(PlanarImage image) {
/* 262 */     if (image == null)
/* 263 */       return null; 
/* 265 */     Iterator iter = iterator();
/* 267 */     while (iter.hasNext()) {
/* 268 */       AttributedImage ai = iter.next();
/* 270 */       if (image.equals(ai.getImage()))
/* 271 */         return ai; 
/*     */     } 
/* 276 */     return null;
/*     */   }
/*     */   
/*     */   public AttributedImage getAttributedImage(Object attribute) {
/* 286 */     if (attribute == null)
/* 287 */       return null; 
/* 289 */     Iterator iter = iterator();
/* 291 */     while (iter.hasNext()) {
/* 292 */       AttributedImage ai = iter.next();
/* 294 */       if (attribute.equals(ai.getAttribute()))
/* 295 */         return ai; 
/*     */     } 
/* 300 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\AttributedImageCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */