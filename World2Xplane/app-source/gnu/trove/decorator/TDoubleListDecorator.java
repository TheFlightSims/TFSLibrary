/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.list.TDoubleList;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.AbstractList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class TDoubleListDecorator extends AbstractList<Double> implements List<Double>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TDoubleList list;
/*     */   
/*     */   public TDoubleListDecorator() {}
/*     */   
/*     */   public TDoubleListDecorator(TDoubleList list) {
/*  70 */     this.list = list;
/*     */   }
/*     */   
/*     */   public TDoubleList getList() {
/*  80 */     return this.list;
/*     */   }
/*     */   
/*     */   public int size() {
/*  86 */     return this.list.size();
/*     */   }
/*     */   
/*     */   public Double get(int index) {
/*  92 */     double value = this.list.get(index);
/*  93 */     if (value == this.list.getNoEntryValue())
/*  93 */       return null; 
/*  94 */     return Double.valueOf(value);
/*     */   }
/*     */   
/*     */   public Double set(int index, Double value) {
/* 100 */     double previous_value = this.list.set(index, value.doubleValue());
/* 101 */     if (previous_value == this.list.getNoEntryValue())
/* 101 */       return null; 
/* 102 */     return Double.valueOf(previous_value);
/*     */   }
/*     */   
/*     */   public void add(int index, Double value) {
/* 108 */     this.list.insert(index, value.doubleValue());
/*     */   }
/*     */   
/*     */   public Double remove(int index) {
/* 114 */     double previous_value = this.list.removeAt(index);
/* 115 */     if (previous_value == this.list.getNoEntryValue())
/* 115 */       return null; 
/* 116 */     return Double.valueOf(previous_value);
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 125 */     in.readByte();
/* 128 */     this.list = (TDoubleList)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 135 */     out.writeByte(0);
/* 138 */     out.writeObject(this.list);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TDoubleListDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */