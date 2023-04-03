/*     */ package javax.media.jai.remote;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class NegotiableCollection implements Negotiable {
/*     */   private Vector elements;
/*     */   
/*     */   private Class elementClass;
/*     */   
/*     */   public NegotiableCollection(Collection collection) {
/*  40 */     if (collection == null)
/*  41 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableCollection0")); 
/*  45 */     this.elements = new Vector();
/*  48 */     Iterator i = collection.iterator();
/*  49 */     if (i.hasNext()) {
/*  50 */       Object obj = i.next();
/*  51 */       this.elements.add(obj);
/*  52 */       this.elementClass = obj.getClass();
/*     */     } 
/*  59 */     while (i.hasNext()) {
/*  60 */       Object obj = i.next();
/*  61 */       if (obj.getClass() != this.elementClass)
/*  62 */         throw new IllegalArgumentException(JaiI18N.getString("NegotiableCollection1")); 
/*  65 */       this.elements.add(obj);
/*     */     } 
/*     */   }
/*     */   
/*     */   public NegotiableCollection(Object[] objects) {
/*  80 */     if (objects == null)
/*  81 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableCollection0")); 
/*  85 */     int length = objects.length;
/*  86 */     if (length != 0)
/*  87 */       this.elementClass = objects[0].getClass(); 
/*  94 */     this.elements = new Vector(length);
/*  95 */     for (int i = 0; i < length; i++) {
/*  96 */       if (objects[i].getClass() != this.elementClass)
/*  97 */         throw new IllegalArgumentException(JaiI18N.getString("NegotiableCollection1")); 
/* 100 */       this.elements.add(objects[i]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Collection getCollection() {
/* 109 */     if (this.elements.isEmpty())
/* 110 */       return null; 
/* 111 */     return this.elements;
/*     */   }
/*     */   
/*     */   public Negotiable negotiate(Negotiable other) {
/* 127 */     if (other == null)
/* 128 */       return null; 
/* 132 */     if (!(other instanceof NegotiableCollection) || other.getNegotiatedValueClass() != this.elementClass)
/* 134 */       return null; 
/* 138 */     Vector result = new Vector();
/* 140 */     Collection otherCollection = ((NegotiableCollection)other).getCollection();
/* 145 */     if (otherCollection == null)
/* 146 */       return null; 
/* 150 */     for (Iterator i = this.elements.iterator(); i.hasNext(); ) {
/* 151 */       Object obj = i.next();
/* 153 */       if (otherCollection.contains(obj))
/* 155 */         if (!result.contains(obj))
/* 156 */           result.add(obj);  
/*     */     } 
/* 162 */     if (result.isEmpty())
/* 163 */       return null; 
/* 166 */     return new NegotiableCollection(result);
/*     */   }
/*     */   
/*     */   public Object getNegotiatedValue() {
/* 181 */     if (this.elements != null && this.elements.size() > 0)
/* 182 */       return this.elements.elementAt(0); 
/* 184 */     return null;
/*     */   }
/*     */   
/*     */   public Class getNegotiatedValueClass() {
/* 196 */     return this.elementClass;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\NegotiableCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */