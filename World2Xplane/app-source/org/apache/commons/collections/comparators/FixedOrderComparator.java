/*     */ package org.apache.commons.collections.comparators;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class FixedOrderComparator implements Comparator {
/*     */   public static final int UNKNOWN_BEFORE = 0;
/*     */   
/*     */   public static final int UNKNOWN_AFTER = 1;
/*     */   
/*     */   public static final int UNKNOWN_THROW_EXCEPTION = 2;
/*     */   
/*  73 */   private final Map map = new HashMap();
/*     */   
/*  75 */   private int counter = 0;
/*     */   
/*     */   private boolean isLocked = false;
/*     */   
/*  79 */   private int unknownObjectBehavior = 2;
/*     */   
/*     */   public FixedOrderComparator() {}
/*     */   
/*     */   public FixedOrderComparator(Object[] items) {
/* 101 */     if (items == null)
/* 102 */       throw new IllegalArgumentException("The list of items must not be null"); 
/* 104 */     for (int i = 0; i < items.length; i++)
/* 105 */       add(items[i]); 
/*     */   }
/*     */   
/*     */   public FixedOrderComparator(List items) {
/* 120 */     if (items == null)
/* 121 */       throw new IllegalArgumentException("The list of items must not be null"); 
/* 123 */     for (Iterator it = items.iterator(); it.hasNext();)
/* 124 */       add(it.next()); 
/*     */   }
/*     */   
/*     */   public boolean isLocked() {
/* 138 */     return this.isLocked;
/*     */   }
/*     */   
/*     */   protected void checkLocked() {
/* 147 */     if (isLocked())
/* 148 */       throw new UnsupportedOperationException("Cannot modify a FixedOrderComparator after a comparison"); 
/*     */   }
/*     */   
/*     */   public int getUnknownObjectBehavior() {
/* 159 */     return this.unknownObjectBehavior;
/*     */   }
/*     */   
/*     */   public void setUnknownObjectBehavior(int unknownObjectBehavior) {
/* 171 */     checkLocked();
/* 172 */     if (unknownObjectBehavior != 1 && unknownObjectBehavior != 0 && unknownObjectBehavior != 2)
/* 175 */       throw new IllegalArgumentException("Unrecognised value for unknown behaviour flag"); 
/* 177 */     this.unknownObjectBehavior = unknownObjectBehavior;
/*     */   }
/*     */   
/*     */   public boolean add(Object obj) {
/* 193 */     checkLocked();
/* 194 */     Object position = this.map.put(obj, new Integer(this.counter++));
/* 195 */     return (position == null);
/*     */   }
/*     */   
/*     */   public boolean addAsEqual(Object existingObj, Object newObj) {
/* 212 */     checkLocked();
/* 213 */     Integer position = (Integer)this.map.get(existingObj);
/* 214 */     if (position == null)
/* 215 */       throw new IllegalArgumentException(existingObj + " not known to " + this); 
/* 217 */     Object result = this.map.put(newObj, position);
/* 218 */     return (result == null);
/*     */   }
/*     */   
/*     */   public int compare(Object obj1, Object obj2) {
/* 238 */     this.isLocked = true;
/* 239 */     Integer position1 = (Integer)this.map.get(obj1);
/* 240 */     Integer position2 = (Integer)this.map.get(obj2);
/* 241 */     if (position1 == null || position2 == null) {
/*     */       Object unknownObj;
/* 242 */       switch (this.unknownObjectBehavior) {
/*     */         case 0:
/* 244 */           if (position1 == null)
/* 245 */             return (position2 == null) ? 0 : -1; 
/* 247 */           return 1;
/*     */         case 1:
/* 250 */           if (position1 == null)
/* 251 */             return (position2 == null) ? 0 : 1; 
/* 253 */           return -1;
/*     */         case 2:
/* 256 */           unknownObj = (position1 == null) ? obj1 : obj2;
/* 257 */           throw new IllegalArgumentException("Attempting to compare unknown object " + unknownObj);
/*     */       } 
/* 259 */       throw new UnsupportedOperationException("Unknown unknownObjectBehavior: " + this.unknownObjectBehavior);
/*     */     } 
/* 262 */     return position1.compareTo(position2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\comparators\FixedOrderComparator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */