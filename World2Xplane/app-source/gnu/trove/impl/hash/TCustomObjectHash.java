/*     */ package gnu.trove.impl.hash;
/*     */ 
/*     */ import gnu.trove.strategy.HashingStrategy;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ 
/*     */ public abstract class TCustomObjectHash<T> extends TObjectHash<T> {
/*     */   static final long serialVersionUID = 8766048185963756400L;
/*     */   
/*     */   protected HashingStrategy<? super T> strategy;
/*     */   
/*     */   public TCustomObjectHash() {}
/*     */   
/*     */   public TCustomObjectHash(HashingStrategy<? super T> strategy) {
/*  56 */     this.strategy = strategy;
/*     */   }
/*     */   
/*     */   public TCustomObjectHash(HashingStrategy<? super T> strategy, int initialCapacity) {
/*  68 */     super(initialCapacity);
/*  70 */     this.strategy = strategy;
/*     */   }
/*     */   
/*     */   public TCustomObjectHash(HashingStrategy<? super T> strategy, int initialCapacity, float loadFactor) {
/*  85 */     super(initialCapacity, loadFactor);
/*  87 */     this.strategy = strategy;
/*     */   }
/*     */   
/*     */   protected int hash(Object obj) {
/*  94 */     return this.strategy.computeHashCode(obj);
/*     */   }
/*     */   
/*     */   protected boolean equals(Object one, Object two) {
/* 100 */     return (two != REMOVED && this.strategy.equals(one, two));
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 108 */     out.writeByte(0);
/* 111 */     super.writeExternal(out);
/* 114 */     out.writeObject(this.strategy);
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 123 */     in.readByte();
/* 126 */     super.readExternal(in);
/* 130 */     this.strategy = (HashingStrategy<? super T>)in.readObject();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\hash\TCustomObjectHash.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */