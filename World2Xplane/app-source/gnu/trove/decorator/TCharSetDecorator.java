/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TCharIterator;
/*     */ import gnu.trove.set.TCharSet;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class TCharSetDecorator extends AbstractSet<Character> implements Set<Character>, Externalizable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TCharSet _set;
/*     */   
/*     */   public TCharSetDecorator() {}
/*     */   
/*     */   public TCharSetDecorator(TCharSet set) {
/*  76 */     this._set = set;
/*     */   }
/*     */   
/*     */   public TCharSet getSet() {
/*  86 */     return this._set;
/*     */   }
/*     */   
/*     */   public boolean add(Character value) {
/*  96 */     return (value != null && this._set.add(value.charValue()));
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
/* 118 */         if (val instanceof Character) {
/* 119 */           char v = ((Character)val).charValue();
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
/* 152 */     return (value instanceof Character && this._set.remove(((Character)value).charValue()));
/*     */   }
/*     */   
/*     */   public Iterator<Character> iterator() {
/* 162 */     return new Iterator<Character>() {
/* 163 */         private final TCharIterator it = TCharSetDecorator.this._set.iterator();
/*     */         
/*     */         public Character next() {
/* 166 */           return Character.valueOf(this.it.next());
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
/* 205 */     if (!(o instanceof Character))
/* 205 */       return false; 
/* 206 */     return this._set.contains(((Character)o).charValue());
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 215 */     in.readByte();
/* 218 */     this._set = (TCharSet)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 225 */     out.writeByte(0);
/* 228 */     out.writeObject(this._set);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TCharSetDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */