/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TLongIterator;
/*     */ import gnu.trove.set.TLongSet;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class TLongSetDecorator extends AbstractSet<Long> implements Set<Long>, Externalizable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TLongSet _set;
/*     */   
/*     */   public TLongSetDecorator() {}
/*     */   
/*     */   public TLongSetDecorator(TLongSet set) {
/*  76 */     this._set = set;
/*     */   }
/*     */   
/*     */   public TLongSet getSet() {
/*  86 */     return this._set;
/*     */   }
/*     */   
/*     */   public boolean add(Long value) {
/*  96 */     return (value != null && this._set.add(value.longValue()));
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
/* 118 */         if (val instanceof Long) {
/* 119 */           long v = ((Long)val).longValue();
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
/* 152 */     return (value instanceof Long && this._set.remove(((Long)value).longValue()));
/*     */   }
/*     */   
/*     */   public Iterator<Long> iterator() {
/* 162 */     return new Iterator<Long>() {
/* 163 */         private final TLongIterator it = TLongSetDecorator.this._set.iterator();
/*     */         
/*     */         public Long next() {
/* 166 */           return Long.valueOf(this.it.next());
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
/* 205 */     if (!(o instanceof Long))
/* 205 */       return false; 
/* 206 */     return this._set.contains(((Long)o).longValue());
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 215 */     in.readByte();
/* 218 */     this._set = (TLongSet)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 225 */     out.writeByte(0);
/* 228 */     out.writeObject(this._set);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TLongSetDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */