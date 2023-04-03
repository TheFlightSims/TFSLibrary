/*     */ package org.apache.commons.collections.bag;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ import org.apache.commons.collections.SortedBag;
/*     */ 
/*     */ public class TreeBag extends AbstractMapBag implements SortedBag, Serializable {
/*     */   private static final long serialVersionUID = -7740146511091606676L;
/*     */   
/*     */   public TreeBag() {
/*  59 */     super(new TreeMap());
/*     */   }
/*     */   
/*     */   public TreeBag(Comparator comparator) {
/*  69 */     super(new TreeMap(comparator));
/*     */   }
/*     */   
/*     */   public TreeBag(Collection coll) {
/*  79 */     this();
/*  80 */     addAll(coll);
/*     */   }
/*     */   
/*     */   public Object first() {
/*  85 */     return ((SortedMap)getMap()).firstKey();
/*     */   }
/*     */   
/*     */   public Object last() {
/*  89 */     return ((SortedMap)getMap()).lastKey();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/*  93 */     return ((SortedMap)getMap()).comparator();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 101 */     out.defaultWriteObject();
/* 102 */     out.writeObject(comparator());
/* 103 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 110 */     in.defaultReadObject();
/* 111 */     Comparator comp = (Comparator)in.readObject();
/* 112 */     doReadObject(new TreeMap(comp), in);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bag\TreeBag.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */