/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TByteIterator;
/*     */ import gnu.trove.set.TByteSet;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class TByteSetDecorator extends AbstractSet<Byte> implements Set<Byte>, Externalizable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TByteSet _set;
/*     */   
/*     */   public TByteSetDecorator() {}
/*     */   
/*     */   public TByteSetDecorator(TByteSet set) {
/*  76 */     this._set = set;
/*     */   }
/*     */   
/*     */   public TByteSet getSet() {
/*  86 */     return this._set;
/*     */   }
/*     */   
/*     */   public boolean add(Byte value) {
/*  96 */     return (value != null && this._set.add(value.byteValue()));
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
/* 118 */         if (val instanceof Byte) {
/* 119 */           byte v = ((Byte)val).byteValue();
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
/* 152 */     return (value instanceof Byte && this._set.remove(((Byte)value).byteValue()));
/*     */   }
/*     */   
/*     */   public Iterator<Byte> iterator() {
/* 162 */     return new Iterator<Byte>() {
/* 163 */         private final TByteIterator it = TByteSetDecorator.this._set.iterator();
/*     */         
/*     */         public Byte next() {
/* 166 */           return Byte.valueOf(this.it.next());
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
/* 205 */     if (!(o instanceof Byte))
/* 205 */       return false; 
/* 206 */     return this._set.contains(((Byte)o).byteValue());
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 215 */     in.readByte();
/* 218 */     this._set = (TByteSet)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 225 */     out.writeByte(0);
/* 228 */     out.writeObject(this._set);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TByteSetDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */