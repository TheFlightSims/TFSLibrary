/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeSet;
/*     */ import org.geotools.factory.Hints;
/*     */ 
/*     */ public class CollectionConverterFactory implements ConverterFactory {
/*  56 */   protected static final Converter CollectionToCollection = new Converter() {
/*     */       public Object convert(Object source, Class target) throws Exception {
/*  60 */         if (target.isInstance(source))
/*  61 */           return source; 
/*  65 */         Collection converted = CollectionConverterFactory.newCollection(target);
/*  66 */         if (converted != null)
/*  67 */           converted.addAll((Collection)source); 
/*  70 */         return converted;
/*     */       }
/*     */     };
/*     */   
/*  78 */   protected static final Converter CollectionToArray = new Converter() {
/*     */       public Object convert(Object source, Class target) throws Exception {
/*  81 */         Collection s = (Collection)source;
/*  82 */         Object array = Array.newInstance(target.getComponentType(), s.size());
/*     */         try {
/*  85 */           int x = 0;
/*  86 */           for (Iterator i = s.iterator(); i.hasNext(); x++)
/*  87 */             Array.set(array, x, i.next()); 
/*  90 */           return array;
/*  92 */         } catch (Exception e) {
/*  97 */           return null;
/*     */         } 
/*     */       }
/*     */     };
/*     */   
/* 105 */   protected static final Converter ArrayToCollection = new Converter() {
/*     */       public Object convert(Object source, Class target) throws Exception {
/* 108 */         Collection<Object> collection = CollectionConverterFactory.newCollection(target);
/* 109 */         if (collection != null) {
/* 110 */           int length = Array.getLength(source);
/* 111 */           for (int i = 0; i < length; i++)
/* 112 */             collection.add(Array.get(source, i)); 
/*     */         } 
/* 116 */         return collection;
/*     */       }
/*     */     };
/*     */   
/* 124 */   protected static final Converter ArrayToArray = new Converter() {
/*     */       public Object convert(Object source, Class target) throws Exception {
/* 128 */         Class<?> s = source.getClass().getComponentType();
/* 129 */         Class<?> t = target.getComponentType();
/* 132 */         if (t.isAssignableFrom(s)) {
/* 133 */           int length = Array.getLength(source);
/* 134 */           Object converted = Array.newInstance(t, length);
/* 136 */           for (int i = 0; i < length; i++)
/* 137 */             Array.set(converted, i, Array.get(source, i)); 
/* 140 */           return converted;
/*     */         } 
/* 143 */         return null;
/*     */       }
/*     */     };
/*     */   
/*     */   protected static Collection newCollection(Class<?> target) throws Exception {
/* 148 */     if (target.isInterface()) {
/* 150 */       if (List.class.isAssignableFrom(target))
/* 151 */         return new ArrayList(); 
/* 153 */       if (SortedSet.class.isAssignableFrom(target))
/* 154 */         return new TreeSet(); 
/* 156 */       if (Set.class.isAssignableFrom(target))
/* 157 */         return new HashSet(); 
/* 161 */       return null;
/*     */     } 
/* 165 */     return (Collection)target.newInstance();
/*     */   }
/*     */   
/*     */   public Converter createConverter(Class<?> source, Class<?> target, Hints hints) {
/* 169 */     if ((Collection.class.isAssignableFrom(source) || source.isArray()) && (Collection.class.isAssignableFrom(target) || target.isArray())) {
/* 173 */       if (Collection.class.isAssignableFrom(source) && Collection.class.isAssignableFrom(target))
/* 175 */         return CollectionToCollection; 
/* 179 */       if (source.getClass().isArray() && target.isArray())
/* 180 */         return ArrayToArray; 
/* 184 */       if (Collection.class.isAssignableFrom(source) && target.isArray())
/* 185 */         return CollectionToArray; 
/* 189 */       if (source.isArray() && Collection.class.isAssignableFrom(target))
/* 190 */         return ArrayToCollection; 
/*     */     } 
/* 194 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\CollectionConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */