/*      */ package org.mapdb;
/*      */ 
/*      */ import java.io.DataInput;
/*      */ import java.io.DataOutput;
/*      */ import java.io.IOException;
/*      */ import java.io.Serializable;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.Comparator;
/*      */ 
/*      */ public abstract class BTreeKeySerializer<K> {
/*   56 */   public static final BTreeKeySerializer BASIC = new BasicKeySerializer(Serializer.BASIC);
/*      */   
/*      */   public static final class BasicKeySerializer extends BTreeKeySerializer<Object> implements Serializable {
/*      */     private static final long serialVersionUID = 1654710710946309279L;
/*      */     
/*      */     protected final Serializer defaultSerializer;
/*      */     
/*      */     public BasicKeySerializer(Serializer defaultSerializer) {
/*   69 */       this.defaultSerializer = defaultSerializer;
/*      */     }
/*      */     
/*      */     protected BasicKeySerializer(SerializerBase serializerBase, DataInput is, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/*   74 */       objectStack.add(this);
/*   75 */       this.defaultSerializer = (Serializer)serializerBase.deserialize(is, objectStack);
/*      */     }
/*      */     
/*      */     public void serialize(DataOutput out, int start, int end, Object[] keys) throws IOException {
/*   80 */       for (int i = start; i < end; i++)
/*   81 */         this.defaultSerializer.serialize(out, keys[i]); 
/*      */     }
/*      */     
/*      */     public Object[] deserialize(DataInput in, int start, int end, int size) throws IOException {
/*   87 */       Object[] ret = new Object[size];
/*   88 */       for (int i = start; i < end; i++)
/*   89 */         ret[i] = this.defaultSerializer.deserialize(in, -1); 
/*   91 */       return ret;
/*      */     }
/*      */     
/*      */     public Comparator<Object> getComparator() {
/*   96 */       return null;
/*      */     }
/*      */   }
/*      */   
/*  106 */   public static final BTreeKeySerializer<Long> ZERO_OR_POSITIVE_LONG = new BTreeKeySerializer<Long>() {
/*      */       public void serialize(DataOutput out, int start, int end, Object[] keys) throws IOException {
/*  109 */         if (start >= end)
/*      */           return; 
/*  110 */         long prev = ((Long)keys[start]).longValue();
/*  111 */         DataOutput2.packLong(out, prev);
/*  112 */         for (int i = start + 1; i < end; i++) {
/*  113 */           long curr = ((Long)keys[i]).longValue();
/*  114 */           DataOutput2.packLong(out, curr - prev);
/*  115 */           prev = curr;
/*      */         } 
/*      */       }
/*      */       
/*      */       public Object[] deserialize(DataInput in, int start, int end, int size) throws IOException {
/*  121 */         Long[] arrayOfLong = new Long[size];
/*  122 */         long prev = 0L;
/*  123 */         for (int i = start; i < end; i++)
/*  124 */           arrayOfLong[i] = Long.valueOf(prev += DataInput2.unpackLong(in)); 
/*  126 */         return (Object[])arrayOfLong;
/*      */       }
/*      */       
/*      */       public Comparator<Long> getComparator() {
/*  131 */         return BTreeMap.COMPARABLE_COMPARATOR;
/*      */       }
/*      */     };
/*      */   
/*  140 */   public static final BTreeKeySerializer<Integer> ZERO_OR_POSITIVE_INT = new BTreeKeySerializer<Integer>() {
/*      */       public void serialize(DataOutput out, int start, int end, Object[] keys) throws IOException {
/*  143 */         if (start >= end)
/*      */           return; 
/*  144 */         int prev = ((Integer)keys[start]).intValue();
/*  145 */         DataOutput2.packLong(out, prev);
/*  146 */         for (int i = start + 1; i < end; i++) {
/*  147 */           int curr = ((Integer)keys[i]).intValue();
/*  148 */           DataOutput2.packInt(out, curr - prev);
/*  149 */           prev = curr;
/*      */         } 
/*      */       }
/*      */       
/*      */       public Object[] deserialize(DataInput in, int start, int end, int size) throws IOException {
/*  155 */         Integer[] arrayOfInteger = new Integer[size];
/*  156 */         int prev = 0;
/*  157 */         for (int i = start; i < end; i++)
/*  158 */           arrayOfInteger[i] = Integer.valueOf(prev += DataInput2.unpackInt(in)); 
/*  160 */         return (Object[])arrayOfInteger;
/*      */       }
/*      */       
/*      */       public Comparator<Integer> getComparator() {
/*  165 */         return BTreeMap.COMPARABLE_COMPARATOR;
/*      */       }
/*      */     };
/*      */   
/*  175 */   public static final BTreeKeySerializer<String> STRING = new BTreeKeySerializer<String>() {
/*  177 */       private final Charset UTF8_CHARSET = Charset.forName("UTF8");
/*      */       
/*      */       public void serialize(DataOutput out, int start, int end, Object[] keys) throws IOException {
/*  181 */         byte[] previous = null;
/*  182 */         for (int i = start; i < end; i++) {
/*  183 */           byte[] b = ((String)keys[i]).getBytes(this.UTF8_CHARSET);
/*  184 */           leadingValuePackWrite(out, b, previous, 0);
/*  185 */           previous = b;
/*      */         } 
/*      */       }
/*      */       
/*      */       public Object[] deserialize(DataInput in, int start, int end, int size) throws IOException {
/*  191 */         Object[] ret = new Object[size];
/*  192 */         byte[] previous = null;
/*  193 */         for (int i = start; i < end; i++) {
/*  194 */           byte[] b = leadingValuePackRead(in, previous, 0);
/*  195 */           if (b != null) {
/*  196 */             ret[i] = new String(b, this.UTF8_CHARSET);
/*  197 */             previous = b;
/*      */           } 
/*      */         } 
/*  199 */         return ret;
/*      */       }
/*      */       
/*      */       public Comparator<String> getComparator() {
/*  204 */         return BTreeMap.COMPARABLE_COMPARATOR;
/*      */       }
/*      */     };
/*      */   
/*      */   public abstract void serialize(DataOutput paramDataOutput, int paramInt1, int paramInt2, Object[] paramArrayOfObject) throws IOException;
/*      */   
/*      */   public abstract Object[] deserialize(DataInput paramDataInput, int paramInt1, int paramInt2, int paramInt3) throws IOException;
/*      */   
/*      */   public abstract Comparator<K> getComparator();
/*      */   
/*      */   public static byte[] leadingValuePackRead(DataInput in, byte[] previous, int ignoreLeadingCount) throws IOException {
/*  215 */     int len = DataInput2.unpackInt(in) - 1;
/*  216 */     if (len == -1)
/*  217 */       return null; 
/*  219 */     int actualCommon = DataInput2.unpackInt(in);
/*  221 */     byte[] buf = new byte[len];
/*  223 */     if (previous == null)
/*  224 */       actualCommon = 0; 
/*  228 */     if (actualCommon > 0) {
/*  229 */       in.readFully(buf, 0, ignoreLeadingCount);
/*  230 */       System.arraycopy(previous, ignoreLeadingCount, buf, ignoreLeadingCount, actualCommon - ignoreLeadingCount);
/*      */     } 
/*  232 */     in.readFully(buf, actualCommon, len - actualCommon);
/*  233 */     return buf;
/*      */   }
/*      */   
/*      */   public static void leadingValuePackWrite(DataOutput out, byte[] buf, byte[] previous, int ignoreLeadingCount) throws IOException {
/*  244 */     if (buf == null) {
/*  245 */       DataOutput2.packInt(out, 0);
/*      */       return;
/*      */     } 
/*  249 */     int actualCommon = ignoreLeadingCount;
/*  251 */     if (previous != null) {
/*  252 */       int maxCommon = (buf.length > previous.length) ? previous.length : buf.length;
/*  254 */       if (maxCommon > 32767)
/*  254 */         maxCommon = 32767; 
/*  256 */       for (; actualCommon < maxCommon && 
/*  257 */         buf[actualCommon] == previous[actualCommon]; actualCommon++);
/*      */     } 
/*  264 */     DataOutput2.packInt(out, buf.length + 1);
/*  265 */     DataOutput2.packInt(out, actualCommon);
/*  266 */     out.write(buf, 0, ignoreLeadingCount);
/*  267 */     out.write(buf, actualCommon, buf.length - actualCommon);
/*      */   }
/*      */   
/*  274 */   public static final Tuple2KeySerializer TUPLE2 = new Tuple2KeySerializer<Object, Object>(null, null, null);
/*      */   
/*      */   public static final class Tuple2KeySerializer<A, B> extends BTreeKeySerializer<Fun.Tuple2<A, B>> implements Serializable {
/*      */     private static final long serialVersionUID = 2183804367032891772L;
/*      */     
/*      */     protected final Comparator<A> aComparator;
/*      */     
/*      */     protected final Serializer<A> aSerializer;
/*      */     
/*      */     protected final Serializer<B> bSerializer;
/*      */     
/*      */     public Tuple2KeySerializer(Comparator<A> aComparator, Serializer<A> aSerializer, Serializer<B> bSerializer) {
/*  308 */       this.aComparator = aComparator;
/*  309 */       this.aSerializer = aSerializer;
/*  310 */       this.bSerializer = bSerializer;
/*      */     }
/*      */     
/*      */     Tuple2KeySerializer(SerializerBase serializerBase, DataInput is, SerializerBase.FastArrayList<Object> objectStack, int extra) throws IOException {
/*  315 */       objectStack.add(this);
/*  316 */       this.aComparator = (Comparator<A>)serializerBase.deserialize(is, objectStack);
/*  317 */       this.aSerializer = (Serializer<A>)serializerBase.deserialize(is, objectStack);
/*  318 */       this.bSerializer = (Serializer<B>)serializerBase.deserialize(is, objectStack);
/*      */     }
/*      */     
/*      */     public void serialize(DataOutput out, int start, int end, Object[] keys) throws IOException {
/*  323 */       int acount = 0;
/*  324 */       for (int i = start; i < end; i++) {
/*  325 */         Fun.Tuple2<A, B> t = (Fun.Tuple2<A, B>)keys[i];
/*  326 */         if (acount == 0) {
/*  328 */           this.aSerializer.serialize(out, t.a);
/*  330 */           acount = 1;
/*  331 */           while (i + acount < end && this.aComparator.compare(t.a, ((Fun.Tuple2)keys[i + acount]).a) == 0)
/*  332 */             acount++; 
/*  334 */           DataOutput2.packInt(out, acount);
/*      */         } 
/*  336 */         this.bSerializer.serialize(out, t.b);
/*  338 */         acount--;
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object[] deserialize(DataInput in, int start, int end, int size) throws IOException {
/*  344 */       Object[] ret = new Object[size];
/*  345 */       A a = null;
/*  346 */       int acount = 0;
/*  348 */       for (int i = start; i < end; i++) {
/*  349 */         if (acount == 0) {
/*  351 */           a = this.aSerializer.deserialize(in, -1);
/*  352 */           acount = DataInput2.unpackInt(in);
/*      */         } 
/*  354 */         B b = this.bSerializer.deserialize(in, -1);
/*  355 */         ret[i] = Fun.t2(a, b);
/*  356 */         acount--;
/*      */       } 
/*  358 */       assert acount == 0;
/*  360 */       return ret;
/*      */     }
/*      */     
/*      */     public Comparator<Fun.Tuple2<A, B>> getComparator() {
/*  365 */       return BTreeMap.COMPARABLE_COMPARATOR;
/*      */     }
/*      */     
/*      */     public boolean equals(Object o) {
/*  370 */       if (this == o)
/*  370 */         return true; 
/*  371 */       if (o == null || getClass() != o.getClass())
/*  371 */         return false; 
/*  373 */       Tuple2KeySerializer t = (Tuple2KeySerializer)o;
/*  375 */       return (Fun.eq(this.aComparator, t.aComparator) && Fun.eq(this.aSerializer, t.aSerializer) && Fun.eq(this.bSerializer, t.bSerializer));
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  383 */       int result = (this.aComparator != null) ? this.aComparator.hashCode() : 0;
/*  384 */       result = 31 * result + ((this.aSerializer != null) ? this.aSerializer.hashCode() : 0);
/*  385 */       result = 31 * result + ((this.bSerializer != null) ? this.bSerializer.hashCode() : 0);
/*  386 */       return result;
/*      */     }
/*      */   }
/*      */   
/*  393 */   public static final Tuple3KeySerializer TUPLE3 = new Tuple3KeySerializer<Object, Object, Object>(null, null, null, null, null);
/*      */   
/*      */   public static class Tuple3KeySerializer<A, B, C> extends BTreeKeySerializer<Fun.Tuple3<A, B, C>> implements Serializable {
/*      */     private static final long serialVersionUID = 2932442956138713885L;
/*      */     
/*      */     protected final Comparator<A> aComparator;
/*      */     
/*      */     protected final Comparator<B> bComparator;
/*      */     
/*      */     protected final Serializer<A> aSerializer;
/*      */     
/*      */     protected final Serializer<B> bSerializer;
/*      */     
/*      */     protected final Serializer<C> cSerializer;
/*      */     
/*      */     public Tuple3KeySerializer(Comparator<A> aComparator, Comparator<B> bComparator, Serializer<A> aSerializer, Serializer<B> bSerializer, Serializer<C> cSerializer) {
/*  433 */       this.aComparator = aComparator;
/*  434 */       this.bComparator = bComparator;
/*  435 */       this.aSerializer = aSerializer;
/*  436 */       this.bSerializer = bSerializer;
/*  437 */       this.cSerializer = cSerializer;
/*      */     }
/*      */     
/*      */     Tuple3KeySerializer(SerializerBase serializerBase, DataInput is, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/*  442 */       objectStack.add(this);
/*  443 */       this.aComparator = (Comparator<A>)serializerBase.deserialize(is, objectStack);
/*  444 */       this.bComparator = (Comparator<B>)serializerBase.deserialize(is, objectStack);
/*  445 */       this.aSerializer = (Serializer<A>)serializerBase.deserialize(is, objectStack);
/*  446 */       this.bSerializer = (Serializer<B>)serializerBase.deserialize(is, objectStack);
/*  447 */       this.cSerializer = (Serializer<C>)serializerBase.deserialize(is, objectStack);
/*      */     }
/*      */     
/*      */     public void serialize(DataOutput out, int start, int end, Object[] keys) throws IOException {
/*  453 */       int acount = 0;
/*  454 */       int bcount = 0;
/*  455 */       for (int i = start; i < end; i++) {
/*  456 */         Fun.Tuple3<A, B, C> t = (Fun.Tuple3<A, B, C>)keys[i];
/*  457 */         if (acount == 0) {
/*  459 */           this.aSerializer.serialize(out, t.a);
/*  461 */           acount = 1;
/*  462 */           while (i + acount < end && this.aComparator.compare(t.a, ((Fun.Tuple3)keys[i + acount]).a) == 0)
/*  463 */             acount++; 
/*  465 */           DataOutput2.packInt(out, acount);
/*      */         } 
/*  467 */         if (bcount == 0) {
/*  469 */           this.bSerializer.serialize(out, t.b);
/*  471 */           bcount = 1;
/*  472 */           while (i + bcount < end && this.bComparator.compare(t.b, ((Fun.Tuple3)keys[i + bcount]).b) == 0)
/*  473 */             bcount++; 
/*  475 */           DataOutput2.packInt(out, bcount);
/*      */         } 
/*  479 */         this.cSerializer.serialize(out, t.c);
/*  481 */         acount--;
/*  482 */         bcount--;
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object[] deserialize(DataInput in, int start, int end, int size) throws IOException {
/*  490 */       Object[] ret = new Object[size];
/*  491 */       A a = null;
/*  492 */       int acount = 0;
/*  493 */       B b = null;
/*  494 */       int bcount = 0;
/*  496 */       for (int i = start; i < end; i++) {
/*  497 */         if (acount == 0) {
/*  499 */           a = this.aSerializer.deserialize(in, -1);
/*  500 */           acount = DataInput2.unpackInt(in);
/*      */         } 
/*  502 */         if (bcount == 0) {
/*  504 */           b = this.bSerializer.deserialize(in, -1);
/*  505 */           bcount = DataInput2.unpackInt(in);
/*      */         } 
/*  507 */         C c = this.cSerializer.deserialize(in, -1);
/*  508 */         ret[i] = Fun.t3(a, b, c);
/*  509 */         acount--;
/*  510 */         bcount--;
/*      */       } 
/*  512 */       assert acount == 0;
/*  513 */       assert bcount == 0;
/*  515 */       return ret;
/*      */     }
/*      */     
/*      */     public Comparator<Fun.Tuple3<A, B, C>> getComparator() {
/*  520 */       return BTreeMap.COMPARABLE_COMPARATOR;
/*      */     }
/*      */     
/*      */     public boolean equals(Object o) {
/*  526 */       if (this == o)
/*  526 */         return true; 
/*  527 */       if (o == null || getClass() != o.getClass())
/*  527 */         return false; 
/*  529 */       Tuple3KeySerializer t = (Tuple3KeySerializer)o;
/*  531 */       return (Fun.eq(this.aComparator, t.aComparator) && Fun.eq(this.bComparator, t.bComparator) && Fun.eq(this.aSerializer, t.aSerializer) && Fun.eq(this.bSerializer, t.bSerializer) && Fun.eq(this.cSerializer, t.cSerializer));
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  541 */       int result = (this.aComparator != null) ? this.aComparator.hashCode() : 0;
/*  542 */       result = 31 * result + ((this.bComparator != null) ? this.bComparator.hashCode() : 0);
/*  543 */       result = 31 * result + ((this.aSerializer != null) ? this.aSerializer.hashCode() : 0);
/*  544 */       result = 31 * result + ((this.bSerializer != null) ? this.bSerializer.hashCode() : 0);
/*  545 */       result = 31 * result + ((this.cSerializer != null) ? this.cSerializer.hashCode() : 0);
/*  546 */       return result;
/*      */     }
/*      */   }
/*      */   
/*  553 */   public static final Tuple4KeySerializer TUPLE4 = new Tuple4KeySerializer<Object, Object, Object, Object>(null, null, null, null, null, null, null);
/*      */   
/*      */   public static class Tuple4KeySerializer<A, B, C, D> extends BTreeKeySerializer<Fun.Tuple4<A, B, C, D>> implements Serializable {
/*      */     private static final long serialVersionUID = -1835761249723528530L;
/*      */     
/*      */     protected final Comparator<A> aComparator;
/*      */     
/*      */     protected final Comparator<B> bComparator;
/*      */     
/*      */     protected final Comparator<C> cComparator;
/*      */     
/*      */     protected final Serializer<A> aSerializer;
/*      */     
/*      */     protected final Serializer<B> bSerializer;
/*      */     
/*      */     protected final Serializer<C> cSerializer;
/*      */     
/*      */     protected final Serializer<D> dSerializer;
/*      */     
/*      */     public Tuple4KeySerializer(Comparator<A> aComparator, Comparator<B> bComparator, Comparator<C> cComparator, Serializer<A> aSerializer, Serializer<B> bSerializer, Serializer<C> cSerializer, Serializer<D> dSerializer) {
/*  598 */       this.aComparator = aComparator;
/*  599 */       this.bComparator = bComparator;
/*  600 */       this.cComparator = cComparator;
/*  601 */       this.aSerializer = aSerializer;
/*  602 */       this.bSerializer = bSerializer;
/*  603 */       this.cSerializer = cSerializer;
/*  604 */       this.dSerializer = dSerializer;
/*      */     }
/*      */     
/*      */     Tuple4KeySerializer(SerializerBase serializerBase, DataInput is, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/*  609 */       objectStack.add(this);
/*  610 */       this.aComparator = (Comparator<A>)serializerBase.deserialize(is, objectStack);
/*  611 */       this.bComparator = (Comparator<B>)serializerBase.deserialize(is, objectStack);
/*  612 */       this.cComparator = (Comparator<C>)serializerBase.deserialize(is, objectStack);
/*  613 */       this.aSerializer = (Serializer<A>)serializerBase.deserialize(is, objectStack);
/*  614 */       this.bSerializer = (Serializer<B>)serializerBase.deserialize(is, objectStack);
/*  615 */       this.cSerializer = (Serializer<C>)serializerBase.deserialize(is, objectStack);
/*  616 */       this.dSerializer = (Serializer<D>)serializerBase.deserialize(is, objectStack);
/*      */     }
/*      */     
/*      */     public void serialize(DataOutput out, int start, int end, Object[] keys) throws IOException {
/*  622 */       int acount = 0;
/*  623 */       int bcount = 0;
/*  624 */       int ccount = 0;
/*  625 */       for (int i = start; i < end; i++) {
/*  626 */         Fun.Tuple4<A, B, C, D> t = (Fun.Tuple4<A, B, C, D>)keys[i];
/*  627 */         if (acount == 0) {
/*  629 */           this.aSerializer.serialize(out, t.a);
/*  631 */           acount = 1;
/*  632 */           while (i + acount < end && this.aComparator.compare(t.a, ((Fun.Tuple4)keys[i + acount]).a) == 0)
/*  633 */             acount++; 
/*  635 */           DataOutput2.packInt(out, acount);
/*      */         } 
/*  637 */         if (bcount == 0) {
/*  639 */           this.bSerializer.serialize(out, t.b);
/*  641 */           bcount = 1;
/*  642 */           while (i + bcount < end && this.bComparator.compare(t.b, ((Fun.Tuple4)keys[i + bcount]).b) == 0)
/*  643 */             bcount++; 
/*  645 */           DataOutput2.packInt(out, bcount);
/*      */         } 
/*  647 */         if (ccount == 0) {
/*  649 */           this.cSerializer.serialize(out, t.c);
/*  651 */           ccount = 1;
/*  652 */           while (i + ccount < end && this.cComparator.compare(t.c, ((Fun.Tuple4)keys[i + ccount]).c) == 0)
/*  653 */             ccount++; 
/*  655 */           DataOutput2.packInt(out, ccount);
/*      */         } 
/*  659 */         this.dSerializer.serialize(out, t.d);
/*  661 */         acount--;
/*  662 */         bcount--;
/*  663 */         ccount--;
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object[] deserialize(DataInput in, int start, int end, int size) throws IOException {
/*  669 */       Object[] ret = new Object[size];
/*  670 */       A a = null;
/*  671 */       int acount = 0;
/*  672 */       B b = null;
/*  673 */       int bcount = 0;
/*  674 */       C c = null;
/*  675 */       int ccount = 0;
/*  678 */       for (int i = start; i < end; i++) {
/*  679 */         if (acount == 0) {
/*  681 */           a = this.aSerializer.deserialize(in, -1);
/*  682 */           acount = DataInput2.unpackInt(in);
/*      */         } 
/*  684 */         if (bcount == 0) {
/*  686 */           b = this.bSerializer.deserialize(in, -1);
/*  687 */           bcount = DataInput2.unpackInt(in);
/*      */         } 
/*  689 */         if (ccount == 0) {
/*  691 */           c = this.cSerializer.deserialize(in, -1);
/*  692 */           ccount = DataInput2.unpackInt(in);
/*      */         } 
/*  695 */         D d = this.dSerializer.deserialize(in, -1);
/*  696 */         ret[i] = Fun.t4(a, b, c, d);
/*  697 */         acount--;
/*  698 */         bcount--;
/*  699 */         ccount--;
/*      */       } 
/*  701 */       assert acount == 0;
/*  702 */       assert bcount == 0;
/*  703 */       assert ccount == 0;
/*  705 */       return ret;
/*      */     }
/*      */     
/*      */     public Comparator<Fun.Tuple4<A, B, C, D>> getComparator() {
/*  710 */       return BTreeMap.COMPARABLE_COMPARATOR;
/*      */     }
/*      */     
/*      */     public boolean equals(Object o) {
/*  716 */       if (this == o)
/*  716 */         return true; 
/*  717 */       if (o == null || getClass() != o.getClass())
/*  717 */         return false; 
/*  719 */       Tuple4KeySerializer t = (Tuple4KeySerializer)o;
/*  721 */       return (Fun.eq(this.aComparator, t.aComparator) && Fun.eq(this.bComparator, t.bComparator) && Fun.eq(this.cComparator, t.cComparator) && Fun.eq(this.aSerializer, t.aSerializer) && Fun.eq(this.bSerializer, t.bSerializer) && Fun.eq(this.cSerializer, t.cSerializer) && Fun.eq(this.dSerializer, t.dSerializer));
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  734 */       int result = (this.aComparator != null) ? this.aComparator.hashCode() : 0;
/*  735 */       result = 31 * result + ((this.bComparator != null) ? this.bComparator.hashCode() : 0);
/*  736 */       result = 31 * result + ((this.cComparator != null) ? this.cComparator.hashCode() : 0);
/*  737 */       result = 31 * result + ((this.aSerializer != null) ? this.aSerializer.hashCode() : 0);
/*  738 */       result = 31 * result + ((this.bSerializer != null) ? this.bSerializer.hashCode() : 0);
/*  739 */       result = 31 * result + ((this.cSerializer != null) ? this.cSerializer.hashCode() : 0);
/*  740 */       result = 31 * result + ((this.dSerializer != null) ? this.dSerializer.hashCode() : 0);
/*  741 */       return result;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Tuple5KeySerializer<A, B, C, D, E> extends BTreeKeySerializer<Fun.Tuple5<A, B, C, D, E>> implements Serializable {
/*      */     private static final long serialVersionUID = 8607477718850453705L;
/*      */     
/*      */     protected final Comparator<A> aComparator;
/*      */     
/*      */     protected final Comparator<B> bComparator;
/*      */     
/*      */     protected final Comparator<C> cComparator;
/*      */     
/*      */     protected final Comparator<D> dComparator;
/*      */     
/*      */     protected final Serializer<A> aSerializer;
/*      */     
/*      */     protected final Serializer<B> bSerializer;
/*      */     
/*      */     protected final Serializer<C> cSerializer;
/*      */     
/*      */     protected final Serializer<D> dSerializer;
/*      */     
/*      */     protected final Serializer<E> eSerializer;
/*      */     
/*      */     public Tuple5KeySerializer(Comparator<A> aComparator, Comparator<B> bComparator, Comparator<C> cComparator, Comparator<D> dComparator, Serializer<A> aSerializer, Serializer<B> bSerializer, Serializer<C> cSerializer, Serializer<D> dSerializer, Serializer<E> eSerializer) {
/*  783 */       this.aComparator = aComparator;
/*  784 */       this.bComparator = bComparator;
/*  785 */       this.cComparator = cComparator;
/*  786 */       this.dComparator = dComparator;
/*  787 */       this.aSerializer = aSerializer;
/*  788 */       this.bSerializer = bSerializer;
/*  789 */       this.cSerializer = cSerializer;
/*  790 */       this.dSerializer = dSerializer;
/*  791 */       this.eSerializer = eSerializer;
/*      */     }
/*      */     
/*      */     Tuple5KeySerializer(SerializerBase serializerBase, DataInput is, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/*  796 */       objectStack.add(this);
/*  797 */       this.aComparator = (Comparator<A>)serializerBase.deserialize(is, objectStack);
/*  798 */       this.bComparator = (Comparator<B>)serializerBase.deserialize(is, objectStack);
/*  799 */       this.cComparator = (Comparator<C>)serializerBase.deserialize(is, objectStack);
/*  800 */       this.dComparator = (Comparator<D>)serializerBase.deserialize(is, objectStack);
/*  801 */       this.aSerializer = (Serializer<A>)serializerBase.deserialize(is, objectStack);
/*  802 */       this.bSerializer = (Serializer<B>)serializerBase.deserialize(is, objectStack);
/*  803 */       this.cSerializer = (Serializer<C>)serializerBase.deserialize(is, objectStack);
/*  804 */       this.dSerializer = (Serializer<D>)serializerBase.deserialize(is, objectStack);
/*  805 */       this.eSerializer = (Serializer<E>)serializerBase.deserialize(is, objectStack);
/*      */     }
/*      */     
/*      */     public void serialize(DataOutput out, int start, int end, Object[] keys) throws IOException {
/*  811 */       int acount = 0;
/*  812 */       int bcount = 0;
/*  813 */       int ccount = 0;
/*  814 */       int dcount = 0;
/*  815 */       for (int i = start; i < end; i++) {
/*  816 */         Fun.Tuple5<A, B, C, D, E> t = (Fun.Tuple5<A, B, C, D, E>)keys[i];
/*  817 */         if (acount == 0) {
/*  819 */           this.aSerializer.serialize(out, t.a);
/*  821 */           acount = 1;
/*  822 */           while (i + acount < end && this.aComparator.compare(t.a, ((Fun.Tuple5)keys[i + acount]).a) == 0)
/*  823 */             acount++; 
/*  825 */           DataOutput2.packInt(out, acount);
/*      */         } 
/*  827 */         if (bcount == 0) {
/*  829 */           this.bSerializer.serialize(out, t.b);
/*  831 */           bcount = 1;
/*  832 */           while (i + bcount < end && this.bComparator.compare(t.b, ((Fun.Tuple5)keys[i + bcount]).b) == 0)
/*  833 */             bcount++; 
/*  835 */           DataOutput2.packInt(out, bcount);
/*      */         } 
/*  837 */         if (ccount == 0) {
/*  839 */           this.cSerializer.serialize(out, t.c);
/*  841 */           ccount = 1;
/*  842 */           while (i + ccount < end && this.cComparator.compare(t.c, ((Fun.Tuple5)keys[i + ccount]).c) == 0)
/*  843 */             ccount++; 
/*  845 */           DataOutput2.packInt(out, ccount);
/*      */         } 
/*  848 */         if (dcount == 0) {
/*  850 */           this.dSerializer.serialize(out, t.d);
/*  852 */           dcount = 1;
/*  853 */           while (i + dcount < end && this.dComparator.compare(t.d, ((Fun.Tuple5)keys[i + dcount]).d) == 0)
/*  854 */             dcount++; 
/*  856 */           DataOutput2.packInt(out, dcount);
/*      */         } 
/*  860 */         this.eSerializer.serialize(out, t.e);
/*  862 */         acount--;
/*  863 */         bcount--;
/*  864 */         ccount--;
/*  865 */         dcount--;
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object[] deserialize(DataInput in, int start, int end, int size) throws IOException {
/*  871 */       Object[] ret = new Object[size];
/*  872 */       A a = null;
/*  873 */       int acount = 0;
/*  874 */       B b = null;
/*  875 */       int bcount = 0;
/*  876 */       C c = null;
/*  877 */       int ccount = 0;
/*  878 */       D d = null;
/*  879 */       int dcount = 0;
/*  881 */       for (int i = start; i < end; i++) {
/*  882 */         if (acount == 0) {
/*  884 */           a = this.aSerializer.deserialize(in, -1);
/*  885 */           acount = DataInput2.unpackInt(in);
/*      */         } 
/*  887 */         if (bcount == 0) {
/*  889 */           b = this.bSerializer.deserialize(in, -1);
/*  890 */           bcount = DataInput2.unpackInt(in);
/*      */         } 
/*  892 */         if (ccount == 0) {
/*  894 */           c = this.cSerializer.deserialize(in, -1);
/*  895 */           ccount = DataInput2.unpackInt(in);
/*      */         } 
/*  897 */         if (dcount == 0) {
/*  899 */           d = this.dSerializer.deserialize(in, -1);
/*  900 */           dcount = DataInput2.unpackInt(in);
/*      */         } 
/*  904 */         E e = this.eSerializer.deserialize(in, -1);
/*  905 */         ret[i] = Fun.t5(a, b, c, d, e);
/*  906 */         acount--;
/*  907 */         bcount--;
/*  908 */         ccount--;
/*  909 */         dcount--;
/*      */       } 
/*  911 */       assert acount == 0;
/*  912 */       assert bcount == 0;
/*  913 */       assert ccount == 0;
/*  914 */       assert dcount == 0;
/*  916 */       return ret;
/*      */     }
/*      */     
/*      */     public Comparator<Fun.Tuple5<A, B, C, D, E>> getComparator() {
/*  921 */       return BTreeMap.COMPARABLE_COMPARATOR;
/*      */     }
/*      */     
/*      */     public boolean equals(Object o) {
/*  927 */       if (this == o)
/*  927 */         return true; 
/*  928 */       if (o == null || getClass() != o.getClass())
/*  928 */         return false; 
/*  930 */       Tuple5KeySerializer t = (Tuple5KeySerializer)o;
/*  932 */       return (Fun.eq(this.aComparator, t.aComparator) && Fun.eq(this.bComparator, t.bComparator) && Fun.eq(this.cComparator, t.cComparator) && Fun.eq(this.dComparator, t.dComparator) && Fun.eq(this.aSerializer, t.aSerializer) && Fun.eq(this.bSerializer, t.bSerializer) && Fun.eq(this.cSerializer, t.cSerializer) && Fun.eq(this.dSerializer, t.dSerializer) && Fun.eq(this.eSerializer, t.eSerializer));
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  946 */       int result = (this.aComparator != null) ? this.aComparator.hashCode() : 0;
/*  947 */       result = 31 * result + ((this.bComparator != null) ? this.bComparator.hashCode() : 0);
/*  948 */       result = 31 * result + ((this.cComparator != null) ? this.cComparator.hashCode() : 0);
/*  949 */       result = 31 * result + ((this.dComparator != null) ? this.dComparator.hashCode() : 0);
/*  950 */       result = 31 * result + ((this.aSerializer != null) ? this.aSerializer.hashCode() : 0);
/*  951 */       result = 31 * result + ((this.bSerializer != null) ? this.bSerializer.hashCode() : 0);
/*  952 */       result = 31 * result + ((this.cSerializer != null) ? this.cSerializer.hashCode() : 0);
/*  953 */       result = 31 * result + ((this.dSerializer != null) ? this.dSerializer.hashCode() : 0);
/*  954 */       result = 31 * result + ((this.eSerializer != null) ? this.eSerializer.hashCode() : 0);
/*  955 */       return result;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Tuple6KeySerializer<A, B, C, D, E, F> extends BTreeKeySerializer<Fun.Tuple6<A, B, C, D, E, F>> implements Serializable {
/*      */     private static final long serialVersionUID = 3666600849149868404L;
/*      */     
/*      */     protected final Comparator<A> aComparator;
/*      */     
/*      */     protected final Comparator<B> bComparator;
/*      */     
/*      */     protected final Comparator<C> cComparator;
/*      */     
/*      */     protected final Comparator<D> dComparator;
/*      */     
/*      */     protected final Comparator<E> eComparator;
/*      */     
/*      */     protected final Serializer<A> aSerializer;
/*      */     
/*      */     protected final Serializer<B> bSerializer;
/*      */     
/*      */     protected final Serializer<C> cSerializer;
/*      */     
/*      */     protected final Serializer<D> dSerializer;
/*      */     
/*      */     protected final Serializer<E> eSerializer;
/*      */     
/*      */     protected final Serializer<F> fSerializer;
/*      */     
/*      */     public Tuple6KeySerializer(Comparator<A> aComparator, Comparator<B> bComparator, Comparator<C> cComparator, Comparator<D> dComparator, Comparator<E> eComparator, Serializer<A> aSerializer, Serializer<B> bSerializer, Serializer<C> cSerializer, Serializer<D> dSerializer, Serializer<E> eSerializer, Serializer<F> fSerializer) {
/*  998 */       this.aComparator = aComparator;
/*  999 */       this.bComparator = bComparator;
/* 1000 */       this.cComparator = cComparator;
/* 1001 */       this.dComparator = dComparator;
/* 1002 */       this.eComparator = eComparator;
/* 1003 */       this.aSerializer = aSerializer;
/* 1004 */       this.bSerializer = bSerializer;
/* 1005 */       this.cSerializer = cSerializer;
/* 1006 */       this.dSerializer = dSerializer;
/* 1007 */       this.eSerializer = eSerializer;
/* 1008 */       this.fSerializer = fSerializer;
/*      */     }
/*      */     
/*      */     Tuple6KeySerializer(SerializerBase serializerBase, DataInput is, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/* 1013 */       objectStack.add(this);
/* 1014 */       this.aComparator = (Comparator<A>)serializerBase.deserialize(is, objectStack);
/* 1015 */       this.bComparator = (Comparator<B>)serializerBase.deserialize(is, objectStack);
/* 1016 */       this.cComparator = (Comparator<C>)serializerBase.deserialize(is, objectStack);
/* 1017 */       this.dComparator = (Comparator<D>)serializerBase.deserialize(is, objectStack);
/* 1018 */       this.eComparator = (Comparator<E>)serializerBase.deserialize(is, objectStack);
/* 1019 */       this.aSerializer = (Serializer<A>)serializerBase.deserialize(is, objectStack);
/* 1020 */       this.bSerializer = (Serializer<B>)serializerBase.deserialize(is, objectStack);
/* 1021 */       this.cSerializer = (Serializer<C>)serializerBase.deserialize(is, objectStack);
/* 1022 */       this.dSerializer = (Serializer<D>)serializerBase.deserialize(is, objectStack);
/* 1023 */       this.eSerializer = (Serializer<E>)serializerBase.deserialize(is, objectStack);
/* 1024 */       this.fSerializer = (Serializer<F>)serializerBase.deserialize(is, objectStack);
/*      */     }
/*      */     
/*      */     public void serialize(DataOutput out, int start, int end, Object[] keys) throws IOException {
/* 1030 */       int acount = 0;
/* 1031 */       int bcount = 0;
/* 1032 */       int ccount = 0;
/* 1033 */       int dcount = 0;
/* 1034 */       int ecount = 0;
/* 1035 */       for (int i = start; i < end; i++) {
/* 1036 */         Fun.Tuple6<A, B, C, D, E, F> t = (Fun.Tuple6<A, B, C, D, E, F>)keys[i];
/* 1037 */         if (acount == 0) {
/* 1039 */           this.aSerializer.serialize(out, t.a);
/* 1041 */           acount = 1;
/* 1042 */           while (i + acount < end && this.aComparator.compare(t.a, ((Fun.Tuple6)keys[i + acount]).a) == 0)
/* 1043 */             acount++; 
/* 1045 */           DataOutput2.packInt(out, acount);
/*      */         } 
/* 1047 */         if (bcount == 0) {
/* 1049 */           this.bSerializer.serialize(out, t.b);
/* 1051 */           bcount = 1;
/* 1052 */           while (i + bcount < end && this.bComparator.compare(t.b, ((Fun.Tuple6)keys[i + bcount]).b) == 0)
/* 1053 */             bcount++; 
/* 1055 */           DataOutput2.packInt(out, bcount);
/*      */         } 
/* 1057 */         if (ccount == 0) {
/* 1059 */           this.cSerializer.serialize(out, t.c);
/* 1061 */           ccount = 1;
/* 1062 */           while (i + ccount < end && this.cComparator.compare(t.c, ((Fun.Tuple6)keys[i + ccount]).c) == 0)
/* 1063 */             ccount++; 
/* 1065 */           DataOutput2.packInt(out, ccount);
/*      */         } 
/* 1068 */         if (dcount == 0) {
/* 1070 */           this.dSerializer.serialize(out, t.d);
/* 1072 */           dcount = 1;
/* 1073 */           while (i + dcount < end && this.dComparator.compare(t.d, ((Fun.Tuple6)keys[i + dcount]).d) == 0)
/* 1074 */             dcount++; 
/* 1076 */           DataOutput2.packInt(out, dcount);
/*      */         } 
/* 1079 */         if (ecount == 0) {
/* 1081 */           this.eSerializer.serialize(out, t.e);
/* 1083 */           ecount = 1;
/* 1084 */           while (i + ecount < end && this.eComparator.compare(t.e, ((Fun.Tuple6)keys[i + ecount]).e) == 0)
/* 1085 */             ecount++; 
/* 1087 */           DataOutput2.packInt(out, ecount);
/*      */         } 
/* 1091 */         this.fSerializer.serialize(out, t.f);
/* 1093 */         acount--;
/* 1094 */         bcount--;
/* 1095 */         ccount--;
/* 1096 */         dcount--;
/* 1097 */         ecount--;
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object[] deserialize(DataInput in, int start, int end, int size) throws IOException {
/* 1103 */       Object[] ret = new Object[size];
/* 1104 */       A a = null;
/* 1105 */       int acount = 0;
/* 1106 */       B b = null;
/* 1107 */       int bcount = 0;
/* 1108 */       C c = null;
/* 1109 */       int ccount = 0;
/* 1110 */       D d = null;
/* 1111 */       int dcount = 0;
/* 1112 */       E e = null;
/* 1113 */       int ecount = 0;
/* 1116 */       for (int i = start; i < end; i++) {
/* 1117 */         if (acount == 0) {
/* 1119 */           a = this.aSerializer.deserialize(in, -1);
/* 1120 */           acount = DataInput2.unpackInt(in);
/*      */         } 
/* 1122 */         if (bcount == 0) {
/* 1124 */           b = this.bSerializer.deserialize(in, -1);
/* 1125 */           bcount = DataInput2.unpackInt(in);
/*      */         } 
/* 1127 */         if (ccount == 0) {
/* 1129 */           c = this.cSerializer.deserialize(in, -1);
/* 1130 */           ccount = DataInput2.unpackInt(in);
/*      */         } 
/* 1132 */         if (dcount == 0) {
/* 1134 */           d = this.dSerializer.deserialize(in, -1);
/* 1135 */           dcount = DataInput2.unpackInt(in);
/*      */         } 
/* 1138 */         if (ecount == 0) {
/* 1140 */           e = this.eSerializer.deserialize(in, -1);
/* 1141 */           ecount = DataInput2.unpackInt(in);
/*      */         } 
/* 1145 */         F f = this.fSerializer.deserialize(in, -1);
/* 1146 */         ret[i] = Fun.t6(a, b, c, d, e, f);
/* 1147 */         acount--;
/* 1148 */         bcount--;
/* 1149 */         ccount--;
/* 1150 */         dcount--;
/* 1151 */         ecount--;
/*      */       } 
/* 1153 */       assert acount == 0;
/* 1154 */       assert bcount == 0;
/* 1155 */       assert ccount == 0;
/* 1156 */       assert dcount == 0;
/* 1157 */       assert ecount == 0;
/* 1159 */       return ret;
/*      */     }
/*      */     
/*      */     public Comparator<Fun.Tuple6<A, B, C, D, E, F>> getComparator() {
/* 1164 */       return BTreeMap.COMPARABLE_COMPARATOR;
/*      */     }
/*      */     
/*      */     public boolean equals(Object o) {
/* 1170 */       if (this == o)
/* 1170 */         return true; 
/* 1171 */       if (o == null || getClass() != o.getClass())
/* 1171 */         return false; 
/* 1173 */       Tuple6KeySerializer t = (Tuple6KeySerializer)o;
/* 1175 */       return (Fun.eq(this.aComparator, t.aComparator) && Fun.eq(this.bComparator, t.bComparator) && Fun.eq(this.cComparator, t.cComparator) && Fun.eq(this.dComparator, t.dComparator) && Fun.eq(this.eComparator, t.eComparator) && Fun.eq(this.aSerializer, t.aSerializer) && Fun.eq(this.bSerializer, t.bSerializer) && Fun.eq(this.cSerializer, t.cSerializer) && Fun.eq(this.dSerializer, t.dSerializer) && Fun.eq(this.eSerializer, t.eSerializer) && Fun.eq(this.fSerializer, t.fSerializer));
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 1192 */       int result = (this.aComparator != null) ? this.aComparator.hashCode() : 0;
/* 1193 */       result = 31 * result + ((this.bComparator != null) ? this.bComparator.hashCode() : 0);
/* 1194 */       result = 31 * result + ((this.cComparator != null) ? this.cComparator.hashCode() : 0);
/* 1195 */       result = 31 * result + ((this.dComparator != null) ? this.dComparator.hashCode() : 0);
/* 1196 */       result = 31 * result + ((this.eComparator != null) ? this.eComparator.hashCode() : 0);
/* 1197 */       result = 31 * result + ((this.aSerializer != null) ? this.aSerializer.hashCode() : 0);
/* 1198 */       result = 31 * result + ((this.bSerializer != null) ? this.bSerializer.hashCode() : 0);
/* 1199 */       result = 31 * result + ((this.cSerializer != null) ? this.cSerializer.hashCode() : 0);
/* 1200 */       result = 31 * result + ((this.dSerializer != null) ? this.dSerializer.hashCode() : 0);
/* 1201 */       result = 31 * result + ((this.eSerializer != null) ? this.eSerializer.hashCode() : 0);
/* 1202 */       result = 31 * result + ((this.fSerializer != null) ? this.fSerializer.hashCode() : 0);
/* 1203 */       return result;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\BTreeKeySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */