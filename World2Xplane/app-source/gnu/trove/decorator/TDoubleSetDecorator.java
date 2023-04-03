/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TDoubleIterator;
/*     */ import gnu.trove.set.TDoubleSet;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class TDoubleSetDecorator extends AbstractSet<Double> implements Set<Double>, Externalizable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TDoubleSet _set;
/*     */   
/*     */   public TDoubleSetDecorator() {}
/*     */   
/*     */   public TDoubleSetDecorator(TDoubleSet set) {
/*  76 */     this._set = set;
/*     */   }
/*     */   
/*     */   public TDoubleSet getSet() {
/*  86 */     return this._set;
/*     */   }
/*     */   
/*     */   public boolean add(Double value) {
/*  96 */     return (value != null && this._set.add(value.doubleValue()));
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 108 */     if (this._set.equals(other))
/* 109 */       return true; 
/* 110 */     if (other instanceof Set) {
/* 111 */       Set that = (Set)other;
/* 112 */       if (that.size() != this._set.size())
/* 113 */         return false; 
/* 115 */       Iterator it = that.iterator();
/* 116 */       for (int i = that.size(); i-- > 0; ) {
/* 117 */         Object val = it.next();
/* 118 */         if (val instanceof Double) {
/* 119 */           double v = ((Double)val).doubleValue();
/* 120 */           if (this._set.contains(v))
/*     */             continue; 
/* 123 */           return false;
/*     */         } 
/* 126 */         return false;
/*     */       } 
/* 129 */       return true;
/*     */     } 
/* 132 */     return false;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 141 */     this._set.clear();
/*     */   }
/*     */   
/*     */   public boolean remove(Object value) {
/* 152 */     return (value instanceof Double && this._set.remove(((Double)value).doubleValue()));
/*     */   }
/*     */   
/*     */   public Iterator<Double> iterator() {
/* 162 */     return new Iterator<Double>() {
/* 163 */         private final TDoubleIterator it = TDoubleSetDecorator.this._set.iterator();
/*     */         
/*     */         public Double next() {
/* 166 */           return Double.valueOf(this.it.next());
/*     */         }
/*     */         
/*     */         public boolean hasNext() {
/* 170 */           return this.it.hasNext();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 174 */           this.it.remove();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public int size() {
/* 186 */     return this._set.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 196 */     return (this._set.size() == 0);
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/* 205 */     if (!(o instanceof Double))
/* 205 */       return false; 
/* 206 */     return this._set.contains(((Double)o).doubleValue());
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 215 */     in.readByte();
/* 218 */     this._set = (TDoubleSet)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 225 */     out.writeByte(0);
/* 228 */     out.writeObject(this._set);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TDoubleSetDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */