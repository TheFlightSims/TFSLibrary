/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.list.TFloatList;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.AbstractList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class TFloatListDecorator extends AbstractList<Float> implements List<Float>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TFloatList list;
/*     */   
/*     */   public TFloatListDecorator() {}
/*     */   
/*     */   public TFloatListDecorator(TFloatList list) {
/*  70 */     this.list = list;
/*     */   }
/*     */   
/*     */   public TFloatList getList() {
/*  80 */     return this.list;
/*     */   }
/*     */   
/*     */   public int size() {
/*  86 */     return this.list.size();
/*     */   }
/*     */   
/*     */   public Float get(int index) {
/*  92 */     float value = this.list.get(index);
/*  93 */     if (value == this.list.getNoEntryValue())
/*  93 */       return null; 
/*  94 */     return Float.valueOf(value);
/*     */   }
/*     */   
/*     */   public Float set(int index, Float value) {
/* 100 */     float previous_value = this.list.set(index, value.floatValue());
/* 101 */     if (previous_value == this.list.getNoEntryValue())
/* 101 */       return null; 
/* 102 */     return Float.valueOf(previous_value);
/*     */   }
/*     */   
/*     */   public void add(int index, Float value) {
/* 108 */     this.list.insert(index, value.floatValue());
/*     */   }
/*     */   
/*     */   public Float remove(int index) {
/* 114 */     float previous_value = this.list.removeAt(index);
/* 115 */     if (previous_value == this.list.getNoEntryValue())
/* 115 */       return null; 
/* 116 */     return Float.valueOf(previous_value);
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 125 */     in.readByte();
/* 128 */     this.list = (TFloatList)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 135 */     out.writeByte(0);
/* 138 */     out.writeObject(this.list);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TFloatListDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */