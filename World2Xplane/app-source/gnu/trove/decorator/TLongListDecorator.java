/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.list.TLongList;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.AbstractList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class TLongListDecorator extends AbstractList<Long> implements List<Long>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TLongList list;
/*     */   
/*     */   public TLongListDecorator() {}
/*     */   
/*     */   public TLongListDecorator(TLongList list) {
/*  70 */     this.list = list;
/*     */   }
/*     */   
/*     */   public TLongList getList() {
/*  80 */     return this.list;
/*     */   }
/*     */   
/*     */   public int size() {
/*  86 */     return this.list.size();
/*     */   }
/*     */   
/*     */   public Long get(int index) {
/*  92 */     long value = this.list.get(index);
/*  93 */     if (value == this.list.getNoEntryValue())
/*  93 */       return null; 
/*  94 */     return Long.valueOf(value);
/*     */   }
/*     */   
/*     */   public Long set(int index, Long value) {
/* 100 */     long previous_value = this.list.set(index, value.longValue());
/* 101 */     if (previous_value == this.list.getNoEntryValue())
/* 101 */       return null; 
/* 102 */     return Long.valueOf(previous_value);
/*     */   }
/*     */   
/*     */   public void add(int index, Long value) {
/* 108 */     this.list.insert(index, value.longValue());
/*     */   }
/*     */   
/*     */   public Long remove(int index) {
/* 114 */     long previous_value = this.list.removeAt(index);
/* 115 */     if (previous_value == this.list.getNoEntryValue())
/* 115 */       return null; 
/* 116 */     return Long.valueOf(previous_value);
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 125 */     in.readByte();
/* 128 */     this.list = (TLongList)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 135 */     out.writeByte(0);
/* 138 */     out.writeObject(this.list);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TLongListDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */