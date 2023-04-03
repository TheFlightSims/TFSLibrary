/*      */ package org.mapdb;
/*      */ 
/*      */ import java.io.DataInput;
/*      */ import java.io.DataOutput;
/*      */ import java.io.EOFException;
/*      */ import java.io.IOError;
/*      */ import java.io.IOException;
/*      */ import java.lang.reflect.Array;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Comparator;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.IdentityHashMap;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.LinkedList;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.TreeMap;
/*      */ import java.util.TreeSet;
/*      */ import java.util.UUID;
/*      */ 
/*      */ public class SerializerBase implements Serializer<Object> {
/*      */   protected static final String EMPTY_STRING = "";
/*      */   
/*      */   protected static final class FastArrayList<K> {
/*   46 */     public int size = 0;
/*      */     
/*   47 */     public K[] data = (K[])new Object[1];
/*      */     
/*      */     public boolean forwardRefs = false;
/*      */     
/*      */     public void add(K o) {
/*   54 */       if (this.data.length == this.size)
/*   56 */         this.data = Arrays.copyOf(this.data, this.data.length * 2); 
/*   59 */       this.data[this.size] = o;
/*   60 */       this.size++;
/*      */     }
/*      */     
/*      */     public int identityIndexOf(Object obj) {
/*   78 */       for (int i = 0; i < this.size; i++) {
/*   79 */         if (obj == this.data[i]) {
/*   80 */           this.forwardRefs = true;
/*   81 */           return i;
/*      */         } 
/*      */       } 
/*   84 */       return -1;
/*      */     }
/*      */   }
/*      */   
/*      */   public void serialize(DataOutput out, Object obj) throws IOException {
/*   94 */     serialize(out, obj, null);
/*      */   }
/*      */   
/*      */   public void serialize(DataOutput out, Object obj, FastArrayList<Object> objectStack) throws IOException {
/*  101 */     if (objectStack != null) {
/*  102 */       int indexInObjectStack = objectStack.identityIndexOf(obj);
/*  103 */       if (indexInObjectStack != -1) {
/*  105 */         out.write(174);
/*  106 */         DataOutput2.packInt(out, indexInObjectStack);
/*      */         return;
/*      */       } 
/*  110 */       objectStack.add(obj);
/*      */     } 
/*  113 */     if (obj == null) {
/*  114 */       out.write(1);
/*      */       return;
/*      */     } 
/*  118 */     Class<?> clazz = obj.getClass();
/*  121 */     if (clazz == Integer.class) {
/*  122 */       serializeInt(out, obj);
/*      */       return;
/*      */     } 
/*  124 */     if (clazz == Long.class) {
/*  125 */       serializeLong(out, obj);
/*      */       return;
/*      */     } 
/*  127 */     if (clazz == String.class) {
/*  128 */       serializeString(out, obj);
/*      */       return;
/*      */     } 
/*  130 */     if (clazz == Boolean.class) {
/*  131 */       out.write(((Boolean)obj).booleanValue() ? 2 : 3);
/*      */       return;
/*      */     } 
/*  133 */     if (clazz == Byte.class) {
/*  134 */       serializeByte(out, obj);
/*      */       return;
/*      */     } 
/*  136 */     if (clazz == Character.class) {
/*  137 */       serializerChar(out, obj);
/*      */       return;
/*      */     } 
/*  139 */     if (clazz == Short.class) {
/*  140 */       serializeShort(out, obj);
/*      */       return;
/*      */     } 
/*  142 */     if (clazz == Float.class) {
/*  143 */       serializeFloat(out, obj);
/*      */       return;
/*      */     } 
/*  145 */     if (clazz == Double.class) {
/*  146 */       serializeDouble(out, obj);
/*      */       return;
/*      */     } 
/*  150 */     serialize2(out, obj, objectStack, clazz);
/*      */   }
/*      */   
/*      */   private void serialize2(DataOutput out, Object obj, FastArrayList<Object> objectStack, Class<?> clazz) throws IOException {
/*  155 */     if (obj instanceof byte[]) {
/*  156 */       byte[] b = (byte[])obj;
/*  157 */       serializeByteArray(out, b);
/*      */       return;
/*      */     } 
/*  160 */     if (obj instanceof boolean[]) {
/*  161 */       out.write(111);
/*  162 */       boolean[] a_bool = (boolean[])obj;
/*  163 */       DataOutput2.packInt(out, a_bool.length);
/*  164 */       byte[] a = booleanToByteArray(a_bool);
/*  165 */       out.write(a);
/*      */       return;
/*      */     } 
/*  167 */     if (obj instanceof short[]) {
/*  168 */       out.write(112);
/*  169 */       short[] a = (short[])obj;
/*  170 */       DataOutput2.packInt(out, a.length);
/*  171 */       for (short s : a)
/*  171 */         out.writeShort(s); 
/*      */       return;
/*      */     } 
/*  173 */     if (obj instanceof char[]) {
/*  174 */       out.write(113);
/*  175 */       char[] a = (char[])obj;
/*  176 */       DataOutput2.packInt(out, a.length);
/*  177 */       for (char s : a)
/*  177 */         out.writeChar(s); 
/*      */       return;
/*      */     } 
/*  179 */     if (obj instanceof float[]) {
/*  180 */       out.write(114);
/*  181 */       float[] a = (float[])obj;
/*  182 */       DataOutput2.packInt(out, a.length);
/*  183 */       for (float s : a)
/*  183 */         out.writeFloat(s); 
/*      */       return;
/*      */     } 
/*  185 */     if (obj instanceof double[]) {
/*  186 */       out.write(115);
/*  187 */       double[] a = (double[])obj;
/*  188 */       DataOutput2.packInt(out, a.length);
/*  189 */       for (double s : a)
/*  189 */         out.writeDouble(s); 
/*      */       return;
/*      */     } 
/*  191 */     if (obj instanceof int[]) {
/*  192 */       serializeIntArray(out, (int[])obj);
/*      */       return;
/*      */     } 
/*  194 */     if (obj instanceof long[]) {
/*  195 */       serializeLongArray(out, (long[])obj);
/*      */       return;
/*      */     } 
/*  197 */     if (clazz == BigInteger.class) {
/*  198 */       out.write(138);
/*  199 */       byte[] buf = ((BigInteger)obj).toByteArray();
/*  200 */       DataOutput2.packInt(out, buf.length);
/*  201 */       out.write(buf);
/*      */       return;
/*      */     } 
/*  203 */     if (clazz == BigDecimal.class) {
/*  204 */       out.write(137);
/*  205 */       BigDecimal d = (BigDecimal)obj;
/*  206 */       byte[] buf = d.unscaledValue().toByteArray();
/*  207 */       DataOutput2.packInt(out, buf.length);
/*  208 */       out.write(buf);
/*  209 */       DataOutput2.packInt(out, d.scale());
/*      */       return;
/*      */     } 
/*  211 */     if (obj instanceof Class) {
/*  212 */       out.write(139);
/*  213 */       serializeClass(out, (Class)obj);
/*      */       return;
/*      */     } 
/*  215 */     if (clazz == Date.class) {
/*  216 */       out.write(140);
/*  217 */       out.writeLong(((Date)obj).getTime());
/*      */       return;
/*      */     } 
/*  219 */     if (clazz == UUID.class) {
/*  220 */       out.write(142);
/*  221 */       out.writeLong(((UUID)obj).getMostSignificantBits());
/*  222 */       out.writeLong(((UUID)obj).getLeastSignificantBits());
/*      */       return;
/*      */     } 
/*  224 */     if (obj == Fun.HI) {
/*  225 */       out.write(141);
/*      */       return;
/*      */     } 
/*  233 */     Integer mapdbSingletonHeader = singletons.all.get(obj);
/*  234 */     if (mapdbSingletonHeader != null) {
/*  235 */       out.write(150);
/*  236 */       DataOutput2.packInt(out, mapdbSingletonHeader.intValue());
/*      */       return;
/*      */     } 
/*  241 */     if (obj instanceof Atomic.Long) {
/*  242 */       out.write(176);
/*  243 */       DataOutput2.packLong(out, ((Atomic.Long)obj).recid);
/*      */       return;
/*      */     } 
/*  245 */     if (obj instanceof Atomic.Integer) {
/*  246 */       out.write(177);
/*  247 */       DataOutput2.packLong(out, ((Atomic.Integer)obj).recid);
/*      */       return;
/*      */     } 
/*  249 */     if (obj instanceof Atomic.Boolean) {
/*  250 */       out.write(178);
/*  251 */       DataOutput2.packLong(out, ((Atomic.Boolean)obj).recid);
/*      */       return;
/*      */     } 
/*  253 */     if (obj instanceof Atomic.String) {
/*  254 */       out.write(179);
/*  255 */       DataOutput2.packLong(out, ((Atomic.String)obj).recid);
/*      */       return;
/*      */     } 
/*  257 */     if (obj == this) {
/*  258 */       out.write(150);
/*  259 */       DataOutput2.packInt(out, 12);
/*      */       return;
/*      */     } 
/*  265 */     if (objectStack == null) {
/*  266 */       objectStack = new FastArrayList();
/*  267 */       objectStack.add(obj);
/*      */     } 
/*  271 */     if (obj instanceof Object[]) {
/*  272 */       Object[] b = (Object[])obj;
/*  273 */       boolean packableLongs = (b.length <= 255);
/*  274 */       boolean allNull = true;
/*  275 */       if (packableLongs) {
/*  277 */         for (Object o : b) {
/*  278 */           if (o != null) {
/*  279 */             allNull = false;
/*  280 */             if (o.getClass() != Long.class || (((Long)o).longValue() < 0L && ((Long)o).longValue() != Long.MAX_VALUE))
/*  281 */               packableLongs = false; 
/*      */           } 
/*  285 */           if (!packableLongs && !allNull)
/*      */             break; 
/*      */         } 
/*      */       } else {
/*  290 */         for (Object o : b) {
/*  291 */           if (o != null) {
/*  292 */             allNull = false;
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*  297 */       if (allNull) {
/*  298 */         out.write(161);
/*  299 */         DataOutput2.packInt(out, b.length);
/*  302 */         Class<?> componentType = obj.getClass().getComponentType();
/*  303 */         serializeClass(out, componentType);
/*  305 */       } else if (packableLongs) {
/*  307 */         out.write(159);
/*  308 */         out.write(b.length);
/*  309 */         for (Object o : b) {
/*  310 */           if (o == null) {
/*  311 */             DataOutput2.packLong(out, 0L);
/*      */           } else {
/*  313 */             DataOutput2.packLong(out, ((Long)o).longValue() + 1L);
/*      */           } 
/*      */         } 
/*      */       } else {
/*  317 */         out.write(158);
/*  318 */         DataOutput2.packInt(out, b.length);
/*  321 */         Class<?> componentType = obj.getClass().getComponentType();
/*  322 */         serializeClass(out, componentType);
/*  324 */         for (Object o : b)
/*  325 */           serialize(out, o, objectStack); 
/*      */       } 
/*  329 */     } else if (clazz == ArrayList.class) {
/*  330 */       ArrayList l = (ArrayList)obj;
/*  331 */       boolean packableLongs = (l.size() < 255);
/*  332 */       if (packableLongs)
/*  334 */         for (Object o : l) {
/*  335 */           if (o != null && (o.getClass() != Long.class || (((Long)o).longValue() < 0L && ((Long)o).longValue() != Long.MAX_VALUE))) {
/*  336 */             packableLongs = false;
/*      */             break;
/*      */           } 
/*      */         }  
/*  341 */       if (packableLongs) {
/*  342 */         out.write(160);
/*  343 */         out.write(l.size());
/*  344 */         for (Object o : l) {
/*  345 */           if (o == null) {
/*  346 */             DataOutput2.packLong(out, 0L);
/*      */             continue;
/*      */           } 
/*  348 */           DataOutput2.packLong(out, ((Long)o).longValue() + 1L);
/*      */         } 
/*      */       } else {
/*  351 */         serializeCollection(163, out, obj, objectStack);
/*      */       } 
/*  354 */     } else if (clazz == LinkedList.class) {
/*  355 */       serializeCollection(170, out, obj, objectStack);
/*  356 */     } else if (clazz == TreeSet.class) {
/*  357 */       TreeSet l = (TreeSet)obj;
/*  358 */       out.write(167);
/*  359 */       DataOutput2.packInt(out, l.size());
/*  360 */       serialize(out, l.comparator(), objectStack);
/*  361 */       for (Object o : l)
/*  362 */         serialize(out, o, objectStack); 
/*  363 */     } else if (clazz == HashSet.class) {
/*  364 */       serializeCollection(168, out, obj, objectStack);
/*  365 */     } else if (clazz == LinkedHashSet.class) {
/*  366 */       serializeCollection(169, out, obj, objectStack);
/*  367 */     } else if (clazz == TreeMap.class) {
/*  368 */       TreeMap l = (TreeMap)obj;
/*  369 */       out.write(164);
/*  370 */       DataOutput2.packInt(out, l.size());
/*  371 */       serialize(out, l.comparator(), objectStack);
/*  372 */       for (Object o : l.keySet()) {
/*  373 */         serialize(out, o, objectStack);
/*  374 */         serialize(out, l.get(o), objectStack);
/*      */       } 
/*  376 */     } else if (clazz == HashMap.class) {
/*  377 */       serializeMap(165, out, obj, objectStack);
/*  378 */     } else if (clazz == LinkedHashMap.class) {
/*  379 */       serializeMap(166, out, obj, objectStack);
/*  380 */     } else if (clazz == Properties.class) {
/*  381 */       serializeMap(171, out, obj, objectStack);
/*  382 */     } else if (clazz == Fun.Tuple2.class) {
/*  383 */       out.write(151);
/*  384 */       Fun.Tuple2 t = (Fun.Tuple2)obj;
/*  385 */       serialize(out, t.a, objectStack);
/*  386 */       serialize(out, t.b, objectStack);
/*  387 */     } else if (clazz == Fun.Tuple3.class) {
/*  388 */       out.write(152);
/*  389 */       Fun.Tuple3 t = (Fun.Tuple3)obj;
/*  390 */       serialize(out, t.a, objectStack);
/*  391 */       serialize(out, t.b, objectStack);
/*  392 */       serialize(out, t.c, objectStack);
/*  393 */     } else if (clazz == Fun.Tuple4.class) {
/*  394 */       out.write(153);
/*  396 */       Fun.Tuple4 t = (Fun.Tuple4)obj;
/*  397 */       serialize(out, t.a, objectStack);
/*  398 */       serialize(out, t.b, objectStack);
/*  399 */       serialize(out, t.c, objectStack);
/*  400 */       serialize(out, t.d, objectStack);
/*  401 */     } else if (clazz == Fun.Tuple5.class) {
/*  402 */       out.write(154);
/*  403 */       Fun.Tuple5 t = (Fun.Tuple5)obj;
/*  404 */       serialize(out, t.a, objectStack);
/*  405 */       serialize(out, t.b, objectStack);
/*  406 */       serialize(out, t.c, objectStack);
/*  407 */       serialize(out, t.d, objectStack);
/*  408 */       serialize(out, t.e, objectStack);
/*  409 */     } else if (clazz == Fun.Tuple6.class) {
/*  410 */       out.write(155);
/*  411 */       Fun.Tuple6 t = (Fun.Tuple6)obj;
/*  412 */       serialize(out, t.a, objectStack);
/*  413 */       serialize(out, t.b, objectStack);
/*  414 */       serialize(out, t.c, objectStack);
/*  415 */       serialize(out, t.d, objectStack);
/*  416 */       serialize(out, t.e, objectStack);
/*  417 */       serialize(out, t.f, objectStack);
/*  418 */     } else if (clazz == BTreeKeySerializer.Tuple2KeySerializer.class) {
/*  419 */       out.write(150);
/*  420 */       DataOutput2.packInt(out, 7);
/*  421 */       BTreeKeySerializer.Tuple2KeySerializer s = (BTreeKeySerializer.Tuple2KeySerializer)obj;
/*  422 */       serialize(out, s.aComparator, objectStack);
/*  423 */       serialize(out, s.aSerializer, objectStack);
/*  424 */       serialize(out, s.bSerializer, objectStack);
/*  425 */     } else if (clazz == BTreeKeySerializer.Tuple3KeySerializer.class) {
/*  426 */       out.write(150);
/*  427 */       DataOutput2.packInt(out, 8);
/*  428 */       BTreeKeySerializer.Tuple3KeySerializer s = (BTreeKeySerializer.Tuple3KeySerializer)obj;
/*  429 */       serialize(out, s.aComparator, objectStack);
/*  430 */       serialize(out, s.bComparator, objectStack);
/*  431 */       serialize(out, s.aSerializer, objectStack);
/*  432 */       serialize(out, s.bSerializer, objectStack);
/*  433 */       serialize(out, s.cSerializer, objectStack);
/*  434 */     } else if (clazz == BTreeKeySerializer.Tuple4KeySerializer.class) {
/*  435 */       out.write(150);
/*  436 */       DataOutput2.packInt(out, 9);
/*  437 */       BTreeKeySerializer.Tuple4KeySerializer s = (BTreeKeySerializer.Tuple4KeySerializer)obj;
/*  438 */       serialize(out, s.aComparator, objectStack);
/*  439 */       serialize(out, s.bComparator, objectStack);
/*  440 */       serialize(out, s.cComparator, objectStack);
/*  441 */       serialize(out, s.aSerializer, objectStack);
/*  442 */       serialize(out, s.bSerializer, objectStack);
/*  443 */       serialize(out, s.cSerializer, objectStack);
/*  444 */       serialize(out, s.dSerializer, objectStack);
/*  445 */     } else if (clazz == BTreeKeySerializer.Tuple5KeySerializer.class) {
/*  446 */       out.write(150);
/*  447 */       DataOutput2.packInt(out, 55);
/*  448 */       BTreeKeySerializer.Tuple5KeySerializer s = (BTreeKeySerializer.Tuple5KeySerializer)obj;
/*  449 */       serialize(out, s.aComparator, objectStack);
/*  450 */       serialize(out, s.bComparator, objectStack);
/*  451 */       serialize(out, s.cComparator, objectStack);
/*  452 */       serialize(out, s.dComparator, objectStack);
/*  453 */       serialize(out, s.aSerializer, objectStack);
/*  454 */       serialize(out, s.bSerializer, objectStack);
/*  455 */       serialize(out, s.cSerializer, objectStack);
/*  456 */       serialize(out, s.dSerializer, objectStack);
/*  457 */       serialize(out, s.eSerializer, objectStack);
/*  458 */     } else if (clazz == BTreeKeySerializer.Tuple6KeySerializer.class) {
/*  459 */       out.write(150);
/*  460 */       DataOutput2.packInt(out, 56);
/*  461 */       BTreeKeySerializer.Tuple6KeySerializer s = (BTreeKeySerializer.Tuple6KeySerializer)obj;
/*  462 */       serialize(out, s.aComparator, objectStack);
/*  463 */       serialize(out, s.bComparator, objectStack);
/*  464 */       serialize(out, s.cComparator, objectStack);
/*  465 */       serialize(out, s.dComparator, objectStack);
/*  466 */       serialize(out, s.eComparator, objectStack);
/*  467 */       serialize(out, s.aSerializer, objectStack);
/*  468 */       serialize(out, s.bSerializer, objectStack);
/*  469 */       serialize(out, s.cSerializer, objectStack);
/*  470 */       serialize(out, s.dSerializer, objectStack);
/*  471 */       serialize(out, s.eSerializer, objectStack);
/*  472 */       serialize(out, s.fSerializer, objectStack);
/*  473 */     } else if (clazz == BTreeKeySerializer.BasicKeySerializer.class) {
/*  474 */       out.write(150);
/*  475 */       DataOutput2.packInt(out, 15);
/*  476 */       serialize(out, ((BTreeKeySerializer.BasicKeySerializer)obj).defaultSerializer, objectStack);
/*  477 */     } else if (clazz == Fun.ArrayComparator.class) {
/*  478 */       out.write(150);
/*  479 */       DataOutput2.packInt(out, 45);
/*  480 */       serialize(out, ((Fun.ArrayComparator)obj).comparators, objectStack);
/*  481 */     } else if (clazz == Serializer.CompressionWrapper.class) {
/*  482 */       out.write(150);
/*  483 */       DataOutput2.packInt(out, 47);
/*  484 */       serialize(out, ((Serializer.CompressionWrapper)obj).serializer, objectStack);
/*  485 */     } else if (obj instanceof Fun.Tuple2Comparator) {
/*  486 */       out.write(150);
/*  487 */       DataOutput2.packInt(out, 22);
/*  488 */       Fun.Tuple2Comparator c = (Fun.Tuple2Comparator)obj;
/*  489 */       serialize(out, c.a, objectStack);
/*  490 */       serialize(out, c.b, objectStack);
/*  491 */     } else if (obj instanceof Fun.Tuple3Comparator) {
/*  492 */       out.write(150);
/*  493 */       DataOutput2.packInt(out, 23);
/*  494 */       Fun.Tuple3Comparator c = (Fun.Tuple3Comparator)obj;
/*  495 */       serialize(out, c.a, objectStack);
/*  496 */       serialize(out, c.b, objectStack);
/*  497 */       serialize(out, c.c, objectStack);
/*  498 */     } else if (obj instanceof Fun.Tuple4Comparator) {
/*  499 */       out.write(150);
/*  500 */       DataOutput2.packInt(out, 24);
/*  501 */       Fun.Tuple4Comparator c = (Fun.Tuple4Comparator)obj;
/*  502 */       serialize(out, c.a, objectStack);
/*  503 */       serialize(out, c.b, objectStack);
/*  504 */       serialize(out, c.c, objectStack);
/*  505 */       serialize(out, c.d, objectStack);
/*  506 */     } else if (obj instanceof Fun.Tuple5Comparator) {
/*  507 */       out.write(150);
/*  508 */       DataOutput2.packInt(out, 51);
/*  509 */       Fun.Tuple5Comparator c = (Fun.Tuple5Comparator)obj;
/*  510 */       serialize(out, c.a, objectStack);
/*  511 */       serialize(out, c.b, objectStack);
/*  512 */       serialize(out, c.c, objectStack);
/*  513 */       serialize(out, c.d, objectStack);
/*  514 */       serialize(out, c.e, objectStack);
/*  515 */     } else if (obj instanceof Fun.Tuple6Comparator) {
/*  516 */       out.write(150);
/*  517 */       DataOutput2.packInt(out, 52);
/*  518 */       Fun.Tuple6Comparator c = (Fun.Tuple6Comparator)obj;
/*  519 */       serialize(out, c.a, objectStack);
/*  520 */       serialize(out, c.b, objectStack);
/*  521 */       serialize(out, c.c, objectStack);
/*  522 */       serialize(out, c.d, objectStack);
/*  523 */       serialize(out, c.e, objectStack);
/*  524 */       serialize(out, c.f, objectStack);
/*  525 */     } else if (obj instanceof Atomic.Var) {
/*  526 */       out.write(180);
/*  527 */       Atomic.Var v = (Atomic.Var)obj;
/*  528 */       DataOutput2.packLong(out, v.recid);
/*  529 */       serialize(out, v.serializer, objectStack);
/*      */     } else {
/*  531 */       serializeUnknownObject(out, obj, objectStack);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void serializeString(DataOutput out, Object obj) throws IOException {
/*  536 */     String val = (String)obj;
/*  537 */     int len = val.length();
/*  538 */     if (len == 0) {
/*  539 */       out.write(125);
/*      */     } else {
/*  541 */       if (len <= 10) {
/*  542 */         out.write(125 + len);
/*      */       } else {
/*  544 */         out.write(136);
/*  545 */         DataOutput2.packInt(out, len);
/*      */       } 
/*  547 */       for (int i = 0; i < len; i++)
/*  548 */         DataOutput2.packInt(out, ((String)obj).charAt(i)); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void serializeLongArray(DataOutput out, long[] val) throws IOException {
/*  554 */     long max = Long.MIN_VALUE;
/*  555 */     long min = Long.MAX_VALUE;
/*  556 */     for (long i : val) {
/*  557 */       max = Math.max(max, i);
/*  558 */       min = Math.min(min, i);
/*      */     } 
/*  560 */     if (-128L <= min && max <= 127L) {
/*  561 */       out.write(120);
/*  562 */       DataOutput2.packInt(out, val.length);
/*  563 */       for (long i : val)
/*  563 */         out.write((int)i); 
/*  564 */     } else if (-32768L <= min && max <= 32767L) {
/*  565 */       out.write(121);
/*  566 */       DataOutput2.packInt(out, val.length);
/*  567 */       for (long i : val)
/*  567 */         out.writeShort((int)i); 
/*  568 */     } else if (0L <= min) {
/*  569 */       out.write(122);
/*  570 */       DataOutput2.packInt(out, val.length);
/*  571 */       for (long l : val)
/*  571 */         DataOutput2.packLong(out, l); 
/*  572 */     } else if (-2147483648L <= min && max <= 2147483647L) {
/*  573 */       out.write(123);
/*  574 */       DataOutput2.packInt(out, val.length);
/*  575 */       for (long i : val)
/*  575 */         out.writeInt((int)i); 
/*      */     } else {
/*  577 */       out.write(124);
/*  578 */       DataOutput2.packInt(out, val.length);
/*  579 */       for (long i : val)
/*  579 */         out.writeLong(i); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void serializeIntArray(DataOutput out, int[] val) throws IOException {
/*  584 */     int max = Integer.MIN_VALUE;
/*  585 */     int min = Integer.MAX_VALUE;
/*  586 */     for (int i : val) {
/*  587 */       max = Math.max(max, i);
/*  588 */       min = Math.min(min, i);
/*      */     } 
/*  590 */     if (-128 <= min && max <= 127) {
/*  591 */       out.write(116);
/*  592 */       DataOutput2.packInt(out, val.length);
/*  593 */       for (int i : val)
/*  593 */         out.write(i); 
/*  594 */     } else if (-32768 <= min && max <= 32767) {
/*  595 */       out.write(117);
/*  596 */       DataOutput2.packInt(out, val.length);
/*  597 */       for (int i : val)
/*  597 */         out.writeShort(i); 
/*  598 */     } else if (0 <= min) {
/*  599 */       out.write(118);
/*  600 */       DataOutput2.packInt(out, val.length);
/*  601 */       for (int l : val)
/*  601 */         DataOutput2.packInt(out, l); 
/*      */     } else {
/*  603 */       out.write(119);
/*  604 */       DataOutput2.packInt(out, val.length);
/*  605 */       for (int i : val)
/*  605 */         out.writeInt(i); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void serializeDouble(DataOutput out, Object obj) throws IOException {
/*  610 */     double v = ((Double)obj).doubleValue();
/*  611 */     if (v == -1.0D) {
/*  612 */       out.write(102);
/*  613 */     } else if (v == 0.0D) {
/*  614 */       out.write(103);
/*  615 */     } else if (v == 1.0D) {
/*  616 */       out.write(104);
/*  617 */     } else if (v >= 0.0D && v <= 255.0D && (int)v == v) {
/*  618 */       out.write(105);
/*  619 */       out.write((int)v);
/*  620 */     } else if (v >= -32768.0D && v <= 32767.0D && (short)(int)v == v) {
/*  621 */       out.write(106);
/*  622 */       out.writeShort((int)v);
/*  623 */     } else if (v >= -2.147483648E9D && v <= 2.147483647E9D && (int)v == v) {
/*  624 */       out.write(107);
/*  625 */       out.writeInt((int)v);
/*      */     } else {
/*  627 */       out.write(108);
/*  628 */       out.writeDouble(v);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void serializeFloat(DataOutput out, Object obj) throws IOException {
/*  633 */     float v = ((Float)obj).floatValue();
/*  634 */     if (v == -1.0F) {
/*  635 */       out.write(96);
/*  636 */     } else if (v == 0.0F) {
/*  637 */       out.write(97);
/*  638 */     } else if (v == 1.0F) {
/*  639 */       out.write(98);
/*  640 */     } else if (v >= 0.0F && v <= 255.0F && (int)v == v) {
/*  641 */       out.write(99);
/*  642 */       out.write((int)v);
/*  643 */     } else if (v >= -32768.0F && v <= 32767.0F && (short)(int)v == v) {
/*  644 */       out.write(100);
/*  645 */       out.writeShort((int)v);
/*      */     } else {
/*  647 */       out.write(101);
/*  648 */       out.writeFloat(v);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void serializeShort(DataOutput out, Object obj) throws IOException {
/*  653 */     short val = ((Short)obj).shortValue();
/*  654 */     if (val == -1) {
/*  655 */       out.write(90);
/*  656 */     } else if (val == 0) {
/*  657 */       out.write(91);
/*  658 */     } else if (val == 1) {
/*  659 */       out.write(92);
/*  660 */     } else if (val > 0 && val < 255) {
/*  661 */       out.write(93);
/*  662 */       out.write(val);
/*  663 */     } else if (val < 0 && val > -255) {
/*  664 */       out.write(94);
/*  665 */       out.write(-val);
/*      */     } else {
/*  667 */       out.write(95);
/*  668 */       out.writeShort(val);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void serializerChar(DataOutput out, Object obj) throws IOException {
/*  673 */     char val = ((Character)obj).charValue();
/*  674 */     if (val == '\000') {
/*  675 */       out.write(86);
/*  676 */     } else if (val == '\001') {
/*  677 */       out.write(87);
/*  678 */     } else if (val <= 'ÿ') {
/*  679 */       out.write(88);
/*  680 */       out.write(val);
/*      */     } else {
/*  682 */       out.write(89);
/*  683 */       out.writeChar(((Character)obj).charValue());
/*      */     } 
/*      */   }
/*      */   
/*      */   private void serializeByte(DataOutput out, Object obj) throws IOException {
/*  688 */     byte val = ((Byte)obj).byteValue();
/*  689 */     if (val == -1) {
/*  690 */       out.write(82);
/*  691 */     } else if (val == 0) {
/*  692 */       out.write(83);
/*  693 */     } else if (val == 1) {
/*  694 */       out.write(84);
/*      */     } else {
/*  696 */       out.write(85);
/*  697 */       out.writeByte(val);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void serializeLong(DataOutput out, Object obj) throws IOException {
/*  702 */     long val = ((Long)obj).longValue();
/*  703 */     if (val >= -9L && val <= 16L) {
/*  704 */       out.write((int)(39L + val + 9L));
/*      */       return;
/*      */     } 
/*  706 */     if (val == Long.MIN_VALUE) {
/*  707 */       out.write(65);
/*      */       return;
/*      */     } 
/*  709 */     if (val == Long.MAX_VALUE) {
/*  710 */       out.write(66);
/*      */       return;
/*      */     } 
/*  712 */     if ((Math.abs(val) >>> 56L & 0xFFL) != 0L) {
/*  713 */       out.write(81);
/*  714 */       out.writeLong(val);
/*      */       return;
/*      */     } 
/*  718 */     int neg = 0;
/*  719 */     if (val < 0L) {
/*  720 */       neg = -1;
/*  721 */       val = -val;
/*      */     } 
/*  725 */     int size = 48;
/*  726 */     while ((val >> size & 0xFFL) == 0L)
/*  727 */       size -= 8; 
/*  731 */     out.write(68 + size / 8 * 2 + neg);
/*  734 */     while (size >= 0) {
/*  735 */       out.write((int)(val >> size & 0xFFL));
/*  736 */       size -= 8;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void serializeInt(DataOutput out, Object obj) throws IOException {
/*  741 */     int val = ((Integer)obj).intValue();
/*  743 */     switch (val) {
/*      */       case -9:
/*      */       case -8:
/*      */       case -7:
/*      */       case -6:
/*      */       case -5:
/*      */       case -4:
/*      */       case -3:
/*      */       case -2:
/*      */       case -1:
/*      */       case 0:
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*      */       case 7:
/*      */       case 8:
/*      */       case 9:
/*      */       case 10:
/*      */       case 11:
/*      */       case 12:
/*      */       case 13:
/*      */       case 14:
/*      */       case 15:
/*      */       case 16:
/*  770 */         out.write(4 + val + 9);
/*      */         return;
/*      */       case -2147483648:
/*  773 */         out.write(30);
/*      */         return;
/*      */       case 2147483647:
/*  776 */         out.write(31);
/*      */         return;
/*      */     } 
/*  780 */     if ((Math.abs(val) >>> 24 & 0xFF) != 0) {
/*  781 */       out.write(38);
/*  782 */       out.writeInt(val);
/*      */       return;
/*      */     } 
/*  786 */     int neg = 0;
/*  787 */     if (val < 0) {
/*  788 */       neg = -1;
/*  789 */       val = -val;
/*      */     } 
/*  793 */     int size = 24;
/*  794 */     while (((val >> size) & 0xFFL) == 0L)
/*  795 */       size -= 8; 
/*  799 */     out.write(33 + size / 8 * 2 + neg);
/*  802 */     while (size >= 0) {
/*  803 */       out.write((int)((val >> size) & 0xFFL));
/*  804 */       size -= 8;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void serializeClass(DataOutput out, Class clazz) throws IOException {
/*  810 */     out.writeUTF(clazz.getName());
/*      */   }
/*      */   
/*      */   private void serializeMap(int header, DataOutput out, Object obj, FastArrayList<Object> objectStack) throws IOException {
/*  815 */     Map l = (Map)obj;
/*  816 */     out.write(header);
/*  817 */     DataOutput2.packInt(out, l.size());
/*  818 */     for (Object o : l.keySet()) {
/*  819 */       serialize(out, o, objectStack);
/*  820 */       serialize(out, l.get(o), objectStack);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void serializeCollection(int header, DataOutput out, Object obj, FastArrayList<Object> objectStack) throws IOException {
/*  825 */     Collection l = (Collection)obj;
/*  826 */     out.write(header);
/*  827 */     DataOutput2.packInt(out, l.size());
/*  829 */     for (Object o : l)
/*  830 */       serialize(out, o, objectStack); 
/*      */   }
/*      */   
/*      */   private void serializeByteArray(DataOutput out, byte[] b) throws IOException {
/*  835 */     boolean allEqual = (b.length > 0);
/*  837 */     for (int i = 1; i < b.length; i++) {
/*  838 */       if (b[i - 1] != b[i]) {
/*  839 */         allEqual = false;
/*      */         break;
/*      */       } 
/*      */     } 
/*  843 */     if (allEqual) {
/*  844 */       out.write(110);
/*  845 */       DataOutput2.packInt(out, b.length);
/*  846 */       out.write(b[0]);
/*      */     } else {
/*  848 */       out.write(109);
/*  849 */       DataOutput2.packInt(out, b.length);
/*  850 */       out.write(b);
/*      */     } 
/*      */   }
/*      */   
/*      */   static String deserializeString(DataInput buf, int len) throws IOException {
/*  856 */     char[] b = new char[len];
/*  857 */     for (int i = 0; i < len; i++)
/*  858 */       b[i] = (char)DataInput2.unpackInt(buf); 
/*  860 */     return new String(b);
/*      */   }
/*      */   
/*      */   public Object deserialize(DataInput is, int capacity) throws IOException {
/*  866 */     if (capacity == 0)
/*  866 */       return null; 
/*  867 */     return deserialize(is, (FastArrayList<Object>)null);
/*      */   }
/*      */   
/*      */   public Object deserialize(DataInput is, FastArrayList<Object> objectStack) throws IOException {
/*  872 */     Object ret = null;
/*  874 */     int ir = 0;
/*  875 */     long lr = 0L;
/*  876 */     int head = is.readUnsignedByte();
/*  879 */     switch (head) {
/*      */       case 0:
/*  881 */         throw new IOError(new IOException("Zero Header, data corrupted"));
/*      */       case 2:
/*  885 */         ret = Boolean.TRUE;
/*      */         break;
/*      */       case 3:
/*  888 */         ret = Boolean.FALSE;
/*      */         break;
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*      */       case 7:
/*      */       case 8:
/*      */       case 9:
/*      */       case 10:
/*      */       case 11:
/*      */       case 12:
/*      */       case 13:
/*      */       case 14:
/*      */       case 15:
/*      */       case 16:
/*      */       case 17:
/*      */       case 18:
/*      */       case 19:
/*      */       case 20:
/*      */       case 21:
/*      */       case 22:
/*      */       case 23:
/*      */       case 24:
/*      */       case 25:
/*      */       case 26:
/*      */       case 27:
/*      */       case 28:
/*      */       case 29:
/*  916 */         ret = Integer.valueOf(head - 4 - 9);
/*      */         break;
/*      */       case 30:
/*  919 */         ret = Integer.valueOf(-2147483648);
/*      */         break;
/*      */       case 31:
/*  922 */         ret = Integer.valueOf(2147483647);
/*      */         break;
/*      */       case 36:
/*      */       case 37:
/*  927 */         ir = is.readUnsignedByte() & 0xFF;
/*      */       case 34:
/*      */       case 35:
/*  930 */         ir = ir << 8 | is.readUnsignedByte() & 0xFF;
/*      */       case 32:
/*      */       case 33:
/*  933 */         ir = ir << 8 | is.readUnsignedByte() & 0xFF;
/*  934 */         if (head % 2 == 0)
/*  935 */           ir = -ir; 
/*  936 */         ret = Integer.valueOf(ir);
/*      */         break;
/*      */       case 38:
/*  940 */         ret = Integer.valueOf(is.readInt());
/*      */         break;
/*      */       case 39:
/*      */       case 40:
/*      */       case 41:
/*      */       case 42:
/*      */       case 43:
/*      */       case 44:
/*      */       case 45:
/*      */       case 46:
/*      */       case 47:
/*      */       case 48:
/*      */       case 49:
/*      */       case 50:
/*      */       case 51:
/*      */       case 52:
/*      */       case 53:
/*      */       case 54:
/*      */       case 55:
/*      */       case 56:
/*      */       case 57:
/*      */       case 58:
/*      */       case 59:
/*      */       case 60:
/*      */       case 61:
/*      */       case 62:
/*      */       case 63:
/*      */       case 64:
/*  969 */         ret = Long.valueOf((head - 39 - 9));
/*      */         break;
/*      */       case 65:
/*  972 */         ret = Long.valueOf(Long.MIN_VALUE);
/*      */         break;
/*      */       case 66:
/*  975 */         ret = Long.valueOf(Long.MAX_VALUE);
/*      */         break;
/*      */       case 79:
/*      */       case 80:
/*  980 */         lr = is.readUnsignedByte() & 0xFFL;
/*      */       case 77:
/*      */       case 78:
/*  983 */         lr = lr << 8L | is.readUnsignedByte() & 0xFFL;
/*      */       case 75:
/*      */       case 76:
/*  986 */         lr = lr << 8L | is.readUnsignedByte() & 0xFFL;
/*      */       case 73:
/*      */       case 74:
/*  989 */         lr = lr << 8L | is.readUnsignedByte() & 0xFFL;
/*      */       case 71:
/*      */       case 72:
/*  992 */         lr = lr << 8L | is.readUnsignedByte() & 0xFFL;
/*      */       case 69:
/*      */       case 70:
/*  995 */         lr = lr << 8L | is.readUnsignedByte() & 0xFFL;
/*      */       case 67:
/*      */       case 68:
/*  998 */         lr = lr << 8L | is.readUnsignedByte() & 0xFFL;
/*  999 */         if (head % 2 == 1)
/*  999 */           lr = -lr; 
/* 1000 */         ret = Long.valueOf(lr);
/*      */         break;
/*      */       case 81:
/* 1004 */         ret = Long.valueOf(is.readLong());
/*      */         break;
/*      */       case 82:
/* 1008 */         ret = Byte.valueOf((byte)-1);
/*      */         break;
/*      */       case 83:
/* 1011 */         ret = Byte.valueOf((byte)0);
/*      */         break;
/*      */       case 84:
/* 1014 */         ret = Byte.valueOf((byte)1);
/*      */         break;
/*      */       case 85:
/* 1017 */         ret = Byte.valueOf(is.readByte());
/*      */         break;
/*      */       case 86:
/* 1021 */         ret = Character.valueOf(false);
/*      */         break;
/*      */       case 87:
/* 1024 */         ret = Character.valueOf('\001');
/*      */         break;
/*      */       case 88:
/* 1027 */         ret = Character.valueOf((char)is.readUnsignedByte());
/*      */         break;
/*      */       case 89:
/* 1030 */         ret = Character.valueOf(is.readChar());
/*      */         break;
/*      */       case 90:
/* 1035 */         ret = Short.valueOf((short)-1);
/*      */         break;
/*      */       case 91:
/* 1038 */         ret = Short.valueOf((short)0);
/*      */         break;
/*      */       case 92:
/* 1041 */         ret = Short.valueOf((short)1);
/*      */         break;
/*      */       case 93:
/* 1044 */         ret = Short.valueOf((short)is.readUnsignedByte());
/*      */         break;
/*      */       case 94:
/* 1047 */         ret = Short.valueOf((short)-is.readUnsignedByte());
/*      */         break;
/*      */       case 95:
/* 1050 */         ret = Short.valueOf(is.readShort());
/*      */         break;
/*      */       case 96:
/* 1054 */         ret = Float.valueOf(-1.0F);
/*      */         break;
/*      */       case 97:
/* 1057 */         ret = Float.valueOf(0.0F);
/*      */         break;
/*      */       case 98:
/* 1060 */         ret = Float.valueOf(1.0F);
/*      */         break;
/*      */       case 99:
/* 1063 */         ret = Float.valueOf(is.readUnsignedByte());
/*      */         break;
/*      */       case 100:
/* 1066 */         ret = Float.valueOf(is.readShort());
/*      */         break;
/*      */       case 101:
/* 1069 */         ret = Float.valueOf(is.readFloat());
/*      */         break;
/*      */       case 102:
/* 1072 */         ret = Double.valueOf(-1.0D);
/*      */         break;
/*      */       case 103:
/* 1075 */         ret = Double.valueOf(0.0D);
/*      */         break;
/*      */       case 104:
/* 1078 */         ret = Double.valueOf(1.0D);
/*      */         break;
/*      */       case 105:
/* 1081 */         ret = Double.valueOf(is.readUnsignedByte());
/*      */         break;
/*      */       case 106:
/* 1084 */         ret = Double.valueOf(is.readShort());
/*      */         break;
/*      */       case 107:
/* 1087 */         ret = Double.valueOf(is.readInt());
/*      */         break;
/*      */       case 108:
/* 1090 */         ret = Double.valueOf(is.readDouble());
/*      */         break;
/*      */       case 136:
/* 1094 */         ret = deserializeString(is, DataInput2.unpackInt(is));
/*      */         break;
/*      */       case 125:
/* 1097 */         ret = "";
/*      */         break;
/*      */       case 126:
/*      */       case 127:
/*      */       case 128:
/*      */       case 129:
/*      */       case 130:
/*      */       case 131:
/*      */       case 132:
/*      */       case 133:
/*      */       case 134:
/*      */       case 135:
/* 1109 */         ret = deserializeString(is, head - 125);
/*      */         break;
/*      */       case -1:
/* 1113 */         throw new EOFException();
/*      */     } 
/* 1116 */     if (ret == null)
/* 1117 */       ret = deserialize2(head, is); 
/* 1120 */     if (ret != null || head == 1) {
/* 1121 */       if (objectStack != null)
/* 1122 */         objectStack.add(ret); 
/* 1123 */       return ret;
/*      */     } 
/* 1128 */     if (objectStack == null)
/* 1129 */       objectStack = new FastArrayList(); 
/* 1130 */     int oldObjectStackSize = objectStack.size;
/* 1132 */     ret = deserialize3(is, objectStack, head);
/* 1134 */     if (head != 174 && objectStack.size == oldObjectStackSize)
/* 1136 */       objectStack.add(ret); 
/* 1140 */     return ret;
/*      */   }
/*      */   
/*      */   private Object deserialize3(DataInput is, FastArrayList<Object> objectStack, int head) throws IOException {
/*      */     Object<Object> ret;
/*      */     Object[] arrayOfObject;
/*      */     LinkedList linkedList;
/*      */     TreeSet<Object> treeSet;
/*      */     HashSet<Object> hashSet;
/*      */     TreeMap<Object, Object> treeMap;
/*      */     HashMap<Object, Object> hashMap;
/*      */     Properties properties;
/*      */     Atomic.Long long_;
/*      */     Atomic.Integer integer;
/*      */     Atomic.Boolean bool;
/*      */     Atomic.String str;
/*      */     Fun.Tuple2<Object, Object> tuple2;
/*      */     Fun.Tuple3<Object, Object, Object> tuple3;
/*      */     Fun.Tuple4<Object, Object, Object, Object> tuple4;
/*      */     Fun.Tuple5<Object, Object, Object, Object, Object> tuple5;
/*      */     Fun.Tuple6<Object, Object, Object, Object, Object, Object> tuple6;
/*      */     Atomic.Var var;
/* 1147 */     switch (head) {
/*      */       case 174:
/* 1149 */         ret = (Object<Object>)objectStack.data[DataInput2.unpackInt(is)];
/* 1219 */         return ret;
/*      */       case 163:
/*      */         ret = (Object<Object>)deserializeArrayList(is, objectStack);
/* 1219 */         return ret;
/*      */       case 158:
/*      */         return deserializeArrayObject(is, objectStack);
/*      */       case 170:
/*      */         return deserializeLinkedList(is, objectStack);
/*      */       case 167:
/*      */         return deserializeTreeSet(is, objectStack);
/*      */       case 168:
/*      */         hashSet = deserializeHashSet(is, objectStack);
/* 1219 */         return hashSet;
/*      */       case 169:
/*      */         hashSet = deserializeLinkedHashSet(is, objectStack);
/* 1219 */         return hashSet;
/*      */       case 164:
/*      */         return deserializeTreeMap(is, objectStack);
/*      */       case 165:
/*      */         hashMap = deserializeHashMap(is, objectStack);
/* 1219 */         return hashMap;
/*      */       case 166:
/*      */         hashMap = deserializeLinkedHashMap(is, objectStack);
/* 1219 */         return hashMap;
/*      */       case 171:
/*      */         return deserializeProperties(is, objectStack);
/*      */       case 176:
/*      */         return new Atomic.Long(getEngine(), DataInput2.unpackLong(is));
/*      */       case 177:
/*      */         return new Atomic.Integer(getEngine(), DataInput2.unpackLong(is));
/*      */       case 178:
/*      */         return new Atomic.Boolean(getEngine(), DataInput2.unpackLong(is));
/*      */       case 179:
/*      */         return new Atomic.String(getEngine(), DataInput2.unpackLong(is));
/*      */       case 151:
/*      */         return new Fun.Tuple2<Object, Object>(this, is, objectStack);
/*      */       case 152:
/*      */         return new Fun.Tuple3<Object, Object, Object>(this, is, objectStack, 0);
/*      */       case 153:
/*      */         return new Fun.Tuple4<Object, Object, Object, Object>(this, is, objectStack);
/*      */       case 154:
/*      */         return new Fun.Tuple5<Object, Object, Object, Object, Object>(this, is, objectStack);
/*      */       case 155:
/*      */         return new Fun.Tuple6<Object, Object, Object, Object, Object, Object>(this, is, objectStack);
/*      */       case 180:
/*      */         return new Atomic.Var(getEngine(), this, is, objectStack);
/*      */       case 150:
/*      */         object = deserializeMapDB(is, objectStack);
/* 1219 */         return object;
/*      */     } 
/*      */     Object object = deserializeUnknownHeader(is, head, objectStack);
/* 1219 */     return object;
/*      */   }
/*      */   
/*      */   private Object deserialize2(int head, DataInput is) throws IOException {
/*      */     Object<Object> ret;
/*      */     byte[] b;
/*      */     int size, i;
/* 1224 */     switch (head) {
/*      */       case 110:
/* 1226 */         b = new byte[DataInput2.unpackInt(is)];
/* 1227 */         Arrays.fill(b, is.readByte());
/* 1228 */         ret = (Object<Object>)b;
/* 1346 */         return ret;
/*      */       case 109:
/*      */         ret = (Object<Object>)deserializeArrayByte(is);
/* 1346 */         return ret;
/*      */       case 111:
/*      */         ret = (Object<Object>)readBooleanArray(DataInput2.unpackInt(is), is);
/* 1346 */         return ret;
/*      */       case 112:
/*      */         size = DataInput2.unpackInt(is);
/*      */         ret = (Object<Object>)new short[size];
/*      */         for (i = 0; i < size; ) {
/*      */           ((short[])ret)[i] = is.readShort();
/*      */           i++;
/*      */         } 
/* 1346 */         return ret;
/*      */       case 115:
/*      */         size = DataInput2.unpackInt(is);
/*      */         ret = (Object<Object>)new double[size];
/*      */         for (i = 0; i < size; ) {
/*      */           ((double[])ret)[i] = is.readDouble();
/*      */           i++;
/*      */         } 
/* 1346 */         return ret;
/*      */       case 114:
/*      */         size = DataInput2.unpackInt(is);
/*      */         ret = (Object<Object>)new float[size];
/*      */         for (i = 0; i < size; ) {
/*      */           ((float[])ret)[i] = is.readFloat();
/*      */           i++;
/*      */         } 
/* 1346 */         return ret;
/*      */       case 113:
/*      */         size = DataInput2.unpackInt(is);
/*      */         ret = (Object<Object>)new char[size];
/*      */         for (i = 0; i < size; ) {
/*      */           ((char[])ret)[i] = is.readChar();
/*      */           i++;
/*      */         } 
/* 1346 */         return ret;
/*      */       case 116:
/*      */         size = DataInput2.unpackInt(is);
/*      */         ret = (Object<Object>)new int[size];
/*      */         for (i = 0; i < size; ) {
/*      */           ((int[])ret)[i] = is.readByte();
/*      */           i++;
/*      */         } 
/* 1346 */         return ret;
/*      */       case 117:
/*      */         size = DataInput2.unpackInt(is);
/*      */         ret = (Object<Object>)new int[size];
/*      */         for (i = 0; i < size; ) {
/*      */           ((int[])ret)[i] = is.readShort();
/*      */           i++;
/*      */         } 
/* 1346 */         return ret;
/*      */       case 118:
/*      */         size = DataInput2.unpackInt(is);
/*      */         ret = (Object<Object>)new int[size];
/*      */         for (i = 0; i < size; ) {
/*      */           ((int[])ret)[i] = DataInput2.unpackInt(is);
/*      */           i++;
/*      */         } 
/* 1346 */         return ret;
/*      */       case 119:
/*      */         size = DataInput2.unpackInt(is);
/*      */         ret = (Object<Object>)new int[size];
/*      */         for (i = 0; i < size; ) {
/*      */           ((int[])ret)[i] = is.readInt();
/*      */           i++;
/*      */         } 
/* 1346 */         return ret;
/*      */       case 120:
/*      */         size = DataInput2.unpackInt(is);
/*      */         ret = (Object<Object>)new long[size];
/*      */         for (i = 0; i < size; ) {
/*      */           ((long[])ret)[i] = is.readByte();
/*      */           i++;
/*      */         } 
/* 1346 */         return ret;
/*      */       case 121:
/*      */         size = DataInput2.unpackInt(is);
/*      */         ret = (Object<Object>)new long[size];
/*      */         for (i = 0; i < size; ) {
/*      */           ((long[])ret)[i] = is.readShort();
/*      */           i++;
/*      */         } 
/* 1346 */         return ret;
/*      */       case 122:
/*      */         size = DataInput2.unpackInt(is);
/*      */         ret = (Object<Object>)new long[size];
/*      */         for (i = 0; i < size; ) {
/*      */           ((long[])ret)[i] = DataInput2.unpackLong(is);
/*      */           i++;
/*      */         } 
/* 1346 */         return ret;
/*      */       case 123:
/*      */         size = DataInput2.unpackInt(is);
/*      */         ret = (Object<Object>)new long[size];
/*      */         for (i = 0; i < size; ) {
/*      */           ((long[])ret)[i] = is.readInt();
/*      */           i++;
/*      */         } 
/* 1346 */         return ret;
/*      */       case 124:
/*      */         size = DataInput2.unpackInt(is);
/*      */         ret = (Object<Object>)new long[size];
/*      */         for (i = 0; i < size; ) {
/*      */           ((long[])ret)[i] = is.readLong();
/*      */           i++;
/*      */         } 
/* 1346 */         return ret;
/*      */       case 138:
/*      */         ret = (Object<Object>)new BigInteger(deserializeArrayByte(is));
/* 1346 */         return ret;
/*      */       case 137:
/*      */         ret = (Object<Object>)new BigDecimal(new BigInteger(deserializeArrayByte(is)), DataInput2.unpackInt(is));
/* 1346 */         return ret;
/*      */       case 139:
/*      */         ret = (Object<Object>)deserializeClass(is);
/* 1346 */         return ret;
/*      */       case 140:
/*      */         ret = (Object<Object>)new Date(is.readLong());
/* 1346 */         return ret;
/*      */       case 142:
/*      */         ret = (Object<Object>)new UUID(is.readLong(), is.readLong());
/* 1346 */         return ret;
/*      */       case 160:
/*      */         ret = (Object<Object>)deserializeArrayListPackedLong(is);
/* 1346 */         return ret;
/*      */       case 141:
/*      */         ret = (Object<Object>)Fun.HI;
/* 1346 */         return ret;
/*      */       case 172:
/*      */         throw new AssertionError("Wrong header, data were probably serialized with java.lang.ObjectOutputStream, not with MapDB serialization");
/*      */       case 159:
/*      */         arrayOfObject = deserializeArrayObjectPackedLong(is);
/* 1346 */         return arrayOfObject;
/*      */       case 161:
/*      */         arrayOfObject = deserializeArrayObjectAllNull(is);
/* 1346 */         return arrayOfObject;
/*      */       case 162:
/*      */         arrayOfObject = deserializeArrayObjectNoRefs(is);
/* 1346 */         return arrayOfObject;
/*      */     } 
/*      */     Object[] arrayOfObject = null;
/* 1346 */     return arrayOfObject;
/*      */   }
/*      */   
/*      */   protected static interface HeaderMapDB {
/*      */     public static final int B_TREE_SERIALIZER_POS_LONG = 1;
/*      */     
/*      */     public static final int B_TREE_SERIALIZER_STRING = 2;
/*      */     
/*      */     public static final int B_TREE_SERIALIZER_POS_INT = 3;
/*      */     
/*      */     public static final int SERIALIZER_LONG = 4;
/*      */     
/*      */     public static final int SERIALIZER_INT = 5;
/*      */     
/*      */     public static final int SERIALIZER_ILLEGAL_ACCESS = 6;
/*      */     
/*      */     public static final int SERIALIZER_KEY_TUPLE2 = 7;
/*      */     
/*      */     public static final int SERIALIZER_KEY_TUPLE3 = 8;
/*      */     
/*      */     public static final int SERIALIZER_KEY_TUPLE4 = 9;
/*      */     
/*      */     public static final int FUN_COMPARATOR = 10;
/*      */     
/*      */     public static final int COMPARABLE_COMPARATOR = 11;
/*      */     
/*      */     public static final int THIS_SERIALIZER = 12;
/*      */     
/*      */     public static final int SERIALIZER_BASIC = 13;
/*      */     
/*      */     public static final int SERIALIZER_STRING_NOSIZE = 14;
/*      */     
/*      */     public static final int B_TREE_BASIC_KEY_SERIALIZER = 15;
/*      */     
/*      */     public static final int SERIALIZER_BOOLEAN = 16;
/*      */     
/*      */     public static final int SERIALIZER_BYTE_ARRAY_NOSIZE = 17;
/*      */     
/*      */     public static final int SERIALIZER_JAVA = 18;
/*      */     
/*      */     public static final int SERIALIZER_UUID = 19;
/*      */     
/*      */     public static final int SERIALIZER_STRING = 20;
/*      */     
/*      */     public static final int BYTE_ARRAY_SERIALIZER = 21;
/*      */     
/*      */     public static final int TUPLE2_COMPARATOR = 22;
/*      */     
/*      */     public static final int TUPLE3_COMPARATOR = 23;
/*      */     
/*      */     public static final int TUPLE4_COMPARATOR = 24;
/*      */     
/*      */     public static final int TUPLE2_COMPARATOR_STATIC = 25;
/*      */     
/*      */     public static final int TUPLE3_COMPARATOR_STATIC = 26;
/*      */     
/*      */     public static final int TUPLE4_COMPARATOR_STATIC = 27;
/*      */     
/*      */     public static final int FUN_COMPARATOR_REVERSE = 28;
/*      */     
/*      */     public static final int SERIALIZER_CHAR_ARRAY = 29;
/*      */     
/*      */     public static final int SERIALIZER_INT_ARRAY = 30;
/*      */     
/*      */     public static final int SERIALIZER_LONG_ARRAY = 31;
/*      */     
/*      */     public static final int SERIALIZER_DOUBLE_ARRAY = 32;
/*      */     
/*      */     public static final int HASHER_BASIC = 33;
/*      */     
/*      */     public static final int HASHER_BYTE_ARRAY = 34;
/*      */     
/*      */     public static final int HASHER_CHAR_ARRAY = 35;
/*      */     
/*      */     public static final int HASHER_INT_ARRAY = 36;
/*      */     
/*      */     public static final int HASHER_LONG_ARRAY = 37;
/*      */     
/*      */     public static final int HASHER_DOUBLE_ARRAY = 38;
/*      */     
/*      */     public static final int COMPARATOR_BYTE_ARRAY = 39;
/*      */     
/*      */     public static final int COMPARATOR_CHAR_ARRAY = 40;
/*      */     
/*      */     public static final int COMPARATOR_INT_ARRAY = 41;
/*      */     
/*      */     public static final int COMPARATOR_LONG_ARRAY = 42;
/*      */     
/*      */     public static final int COMPARATOR_DOUBLE_ARRAY = 43;
/*      */     
/*      */     public static final int COMPARATOR_COMPARABLE_ARRAY = 44;
/*      */     
/*      */     public static final int COMPARATOR_ARRAY = 45;
/*      */     
/*      */     public static final int SERIALIZER_STRING_ASCII = 46;
/*      */     
/*      */     public static final int SERIALIZER_COMPRESSION_WRAPPER = 47;
/*      */     
/*      */     public static final int B_TREE_COMPRESSION_SERIALIZER = 48;
/*      */     
/*      */     public static final int SERIALIZER_STRING_INTERN = 49;
/*      */     
/*      */     public static final int FUN_EMPTY_ITERATOR = 50;
/*      */     
/*      */     public static final int TUPLE5_COMPARATOR = 51;
/*      */     
/*      */     public static final int TUPLE6_COMPARATOR = 52;
/*      */     
/*      */     public static final int TUPLE5_COMPARATOR_STATIC = 53;
/*      */     
/*      */     public static final int TUPLE6_COMPARATOR_STATIC = 54;
/*      */     
/*      */     public static final int SERIALIZER_KEY_TUPLE5 = 55;
/*      */     
/*      */     public static final int SERIALIZER_KEY_TUPLE6 = 56;
/*      */     
/*      */     public static final int HASHER_ARRAY = 57;
/*      */   }
/*      */   
/*      */   protected static final class singletons {
/* 1418 */     static final Map<Object, Integer> all = new IdentityHashMap<Object, Integer>();
/*      */     
/* 1419 */     static final LongHashMap<Object> reverse = new LongHashMap();
/*      */     
/*      */     static {
/* 1422 */       all.put(BTreeKeySerializer.STRING, Integer.valueOf(2));
/* 1423 */       all.put(BTreeKeySerializer.ZERO_OR_POSITIVE_LONG, Integer.valueOf(1));
/* 1424 */       all.put(BTreeKeySerializer.ZERO_OR_POSITIVE_INT, Integer.valueOf(3));
/* 1426 */       all.put(BTreeMap.COMPARABLE_COMPARATOR, Integer.valueOf(11));
/* 1427 */       all.put(Fun.COMPARATOR, Integer.valueOf(10));
/* 1428 */       all.put(Fun.REVERSE_COMPARATOR, Integer.valueOf(28));
/* 1429 */       all.put(Fun.EMPTY_ITERATOR, Integer.valueOf(50));
/* 1430 */       all.put(Fun.TUPLE2_COMPARATOR, Integer.valueOf(25));
/* 1431 */       all.put(Fun.TUPLE3_COMPARATOR, Integer.valueOf(26));
/* 1432 */       all.put(Fun.TUPLE4_COMPARATOR, Integer.valueOf(27));
/* 1433 */       all.put(Fun.TUPLE5_COMPARATOR, Integer.valueOf(53));
/* 1434 */       all.put(Fun.TUPLE6_COMPARATOR, Integer.valueOf(54));
/* 1436 */       all.put(Serializer.STRING_NOSIZE, Integer.valueOf(14));
/* 1437 */       all.put(Serializer.STRING_ASCII, Integer.valueOf(46));
/* 1438 */       all.put(Serializer.STRING_INTERN, Integer.valueOf(49));
/* 1439 */       all.put(Serializer.LONG, Integer.valueOf(4));
/* 1440 */       all.put(Serializer.INTEGER, Integer.valueOf(5));
/* 1441 */       all.put(Serializer.ILLEGAL_ACCESS, Integer.valueOf(6));
/* 1442 */       all.put(Serializer.BASIC, Integer.valueOf(13));
/* 1443 */       all.put(Serializer.BOOLEAN, Integer.valueOf(16));
/* 1444 */       all.put(Serializer.BYTE_ARRAY_NOSIZE, Integer.valueOf(17));
/* 1445 */       all.put(Serializer.BYTE_ARRAY, Integer.valueOf(21));
/* 1446 */       all.put(Serializer.JAVA, Integer.valueOf(18));
/* 1447 */       all.put(Serializer.UUID, Integer.valueOf(19));
/* 1448 */       all.put(Serializer.STRING, Integer.valueOf(20));
/* 1449 */       all.put(Serializer.CHAR_ARRAY, Integer.valueOf(29));
/* 1450 */       all.put(Serializer.INT_ARRAY, Integer.valueOf(30));
/* 1451 */       all.put(Serializer.LONG_ARRAY, Integer.valueOf(31));
/* 1452 */       all.put(Serializer.DOUBLE_ARRAY, Integer.valueOf(32));
/* 1454 */       all.put(Hasher.BASIC, Integer.valueOf(33));
/* 1455 */       all.put(Hasher.BYTE_ARRAY, Integer.valueOf(34));
/* 1456 */       all.put(Hasher.CHAR_ARRAY, Integer.valueOf(35));
/* 1457 */       all.put(Hasher.INT_ARRAY, Integer.valueOf(36));
/* 1458 */       all.put(Hasher.LONG_ARRAY, Integer.valueOf(37));
/* 1459 */       all.put(Hasher.DOUBLE_ARRAY, Integer.valueOf(38));
/* 1460 */       all.put(Hasher.ARRAY, Integer.valueOf(57));
/* 1462 */       all.put(Fun.BYTE_ARRAY_COMPARATOR, Integer.valueOf(39));
/* 1463 */       all.put(Fun.CHAR_ARRAY_COMPARATOR, Integer.valueOf(40));
/* 1464 */       all.put(Fun.INT_ARRAY_COMPARATOR, Integer.valueOf(41));
/* 1465 */       all.put(Fun.LONG_ARRAY_COMPARATOR, Integer.valueOf(42));
/* 1466 */       all.put(Fun.DOUBLE_ARRAY_COMPARATOR, Integer.valueOf(43));
/* 1467 */       all.put(Fun.COMPARABLE_ARRAY_COMPARATOR, Integer.valueOf(44));
/* 1471 */       all.put(Fun.HI, Integer.valueOf(-2147483648));
/* 1473 */       for (Map.Entry<Object, Integer> e : all.entrySet())
/* 1474 */         reverse.put(((Integer)e.getValue()).intValue(), e.getKey()); 
/*      */     }
/*      */   }
/*      */   
/*      */   public static void assertSerializable(Object o) {
/* 1480 */     if (o != null && !(o instanceof java.io.Serializable) && !singletons.all.containsKey(o))
/* 1482 */       throw new IllegalArgumentException("Not serializable: " + o.getClass()); 
/*      */   }
/*      */   
/*      */   protected Object deserializeMapDB(DataInput is, FastArrayList<Object> objectStack) throws IOException {
/* 1488 */     int head = DataInput2.unpackInt(is);
/* 1490 */     Object singleton = singletons.reverse.get(head);
/* 1491 */     if (singleton != null)
/* 1492 */       return singleton; 
/* 1494 */     assert objectStack != null;
/* 1496 */     switch (head) {
/*      */       case 7:
/* 1498 */         return new BTreeKeySerializer.Tuple2KeySerializer<Object, Object>(this, is, objectStack, 0);
/*      */       case 45:
/* 1500 */         return new Fun.ArrayComparator(this, is, objectStack);
/*      */       case 47:
/* 1503 */         return new Serializer.CompressionWrapper(this, is, objectStack);
/*      */       case 8:
/* 1507 */         return new BTreeKeySerializer.Tuple3KeySerializer<Object, Object, Object>(this, is, objectStack);
/*      */       case 9:
/* 1510 */         return new BTreeKeySerializer.Tuple4KeySerializer<Object, Object, Object, Object>(this, is, objectStack);
/*      */       case 55:
/* 1512 */         return new BTreeKeySerializer.Tuple5KeySerializer<Object, Object, Object, Object, Object>(this, is, objectStack);
/*      */       case 56:
/* 1514 */         return new BTreeKeySerializer.Tuple6KeySerializer<Object, Object, Object, Object, Object, Object>(this, is, objectStack);
/*      */       case 22:
/* 1517 */         return new Fun.Tuple2Comparator<Object, Object>(this, is, objectStack);
/*      */       case 23:
/* 1519 */         return new Fun.Tuple3Comparator<Object, Object, Object>(this, is, objectStack, 0);
/*      */       case 24:
/* 1521 */         return new Fun.Tuple4Comparator<Object, Object, Object, Object>(this, is, objectStack);
/*      */       case 51:
/* 1523 */         return new Fun.Tuple5Comparator<Object, Object, Object, Object, Object>(this, is, objectStack);
/*      */       case 52:
/* 1525 */         return new Fun.Tuple6Comparator<Object, Object, Object, Object, Object, Object>(this, is, objectStack);
/*      */       case 15:
/* 1528 */         return new BTreeKeySerializer.BasicKeySerializer(this, is, objectStack);
/*      */       case 12:
/* 1530 */         return this;
/*      */     } 
/* 1533 */     throw new IOError(new IOException("Unknown header byte, data corrupted"));
/*      */   }
/*      */   
/*      */   protected Engine getEngine() {
/* 1538 */     throw new UnsupportedOperationException();
/*      */   }
/*      */   
/*      */   protected Class deserializeClass(DataInput is) throws IOException {
/* 1544 */     return SerializerPojo.classForName(is.readUTF());
/*      */   }
/*      */   
/*      */   private byte[] deserializeArrayByte(DataInput is) throws IOException {
/* 1549 */     byte[] bb = new byte[DataInput2.unpackInt(is)];
/* 1550 */     is.readFully(bb);
/* 1551 */     return bb;
/*      */   }
/*      */   
/*      */   private Object[] deserializeArrayObject(DataInput is, FastArrayList<Object> objectStack) throws IOException {
/* 1558 */     int size = DataInput2.unpackInt(is);
/* 1559 */     Class<?> clazz = deserializeClass(is);
/* 1560 */     Object[] s = (Object[])Array.newInstance(clazz, size);
/* 1561 */     objectStack.add(s);
/* 1562 */     for (int i = 0; i < size; i++)
/* 1563 */       s[i] = deserialize(is, objectStack); 
/* 1565 */     return s;
/*      */   }
/*      */   
/*      */   private Object[] deserializeArrayObjectNoRefs(DataInput is) throws IOException {
/* 1569 */     int size = DataInput2.unpackInt(is);
/* 1570 */     Class<?> clazz = deserializeClass(is);
/* 1571 */     Object[] s = (Object[])Array.newInstance(clazz, size);
/* 1572 */     for (int i = 0; i < size; i++)
/* 1573 */       s[i] = deserialize(is, (FastArrayList<Object>)null); 
/* 1575 */     return s;
/*      */   }
/*      */   
/*      */   private Object[] deserializeArrayObjectAllNull(DataInput is) throws IOException {
/* 1580 */     int size = DataInput2.unpackInt(is);
/* 1581 */     Class<?> clazz = deserializeClass(is);
/* 1582 */     return (Object[])Array.newInstance(clazz, size);
/*      */   }
/*      */   
/*      */   private Object[] deserializeArrayObjectPackedLong(DataInput is) throws IOException {
/* 1587 */     int size = is.readUnsignedByte();
/* 1588 */     Object[] s = new Object[size];
/* 1589 */     for (int i = 0; i < size; i++) {
/* 1590 */       long l = DataInput2.unpackLong(is);
/* 1591 */       if (l == 0L) {
/* 1592 */         s[i] = null;
/*      */       } else {
/* 1594 */         s[i] = Long.valueOf(l - 1L);
/*      */       } 
/*      */     } 
/* 1596 */     return s;
/*      */   }
/*      */   
/*      */   private ArrayList<Object> deserializeArrayList(DataInput is, FastArrayList<Object> objectStack) throws IOException {
/* 1601 */     int size = DataInput2.unpackInt(is);
/* 1602 */     ArrayList<Object> s = new ArrayList(size);
/* 1603 */     objectStack.add(s);
/* 1604 */     for (int i = 0; i < size; i++)
/* 1605 */       s.add(deserialize(is, objectStack)); 
/* 1607 */     return s;
/*      */   }
/*      */   
/*      */   private ArrayList<Object> deserializeArrayListPackedLong(DataInput is) throws IOException {
/* 1611 */     int size = is.readUnsignedByte();
/* 1612 */     if (size < 0)
/* 1613 */       throw new EOFException(); 
/* 1615 */     ArrayList<Object> s = new ArrayList(size);
/* 1616 */     for (int i = 0; i < size; i++) {
/* 1617 */       long l = DataInput2.unpackLong(is);
/* 1618 */       if (l == 0L) {
/* 1619 */         s.add(null);
/*      */       } else {
/* 1621 */         s.add(Long.valueOf(l - 1L));
/*      */       } 
/*      */     } 
/* 1623 */     return s;
/*      */   }
/*      */   
/*      */   private LinkedList deserializeLinkedList(DataInput is, FastArrayList<Object> objectStack) throws IOException {
/* 1628 */     int size = DataInput2.unpackInt(is);
/* 1629 */     LinkedList<Object> s = new LinkedList();
/* 1630 */     objectStack.add(s);
/* 1631 */     for (int i = 0; i < size; i++)
/* 1632 */       s.add(deserialize(is, objectStack)); 
/* 1633 */     return s;
/*      */   }
/*      */   
/*      */   private HashSet<Object> deserializeHashSet(DataInput is, FastArrayList<Object> objectStack) throws IOException {
/* 1640 */     int size = DataInput2.unpackInt(is);
/* 1641 */     HashSet<Object> s = new HashSet(size);
/* 1642 */     objectStack.add(s);
/* 1643 */     for (int i = 0; i < size; i++)
/* 1644 */       s.add(deserialize(is, objectStack)); 
/* 1645 */     return s;
/*      */   }
/*      */   
/*      */   private LinkedHashSet<Object> deserializeLinkedHashSet(DataInput is, FastArrayList<Object> objectStack) throws IOException {
/* 1650 */     int size = DataInput2.unpackInt(is);
/* 1651 */     LinkedHashSet<Object> s = new LinkedHashSet(size);
/* 1652 */     objectStack.add(s);
/* 1653 */     for (int i = 0; i < size; i++)
/* 1654 */       s.add(deserialize(is, objectStack)); 
/* 1655 */     return s;
/*      */   }
/*      */   
/*      */   private TreeSet<Object> deserializeTreeSet(DataInput is, FastArrayList<Object> objectStack) throws IOException {
/* 1660 */     int size = DataInput2.unpackInt(is);
/* 1661 */     TreeSet<Object> s = new TreeSet();
/* 1662 */     objectStack.add(s);
/* 1663 */     Comparator<? super Object> comparator = (Comparator)deserialize(is, objectStack);
/* 1664 */     if (comparator != null)
/* 1665 */       s = new TreeSet(comparator); 
/* 1667 */     for (int i = 0; i < size; i++)
/* 1668 */       s.add(deserialize(is, objectStack)); 
/* 1669 */     return s;
/*      */   }
/*      */   
/*      */   private TreeMap<Object, Object> deserializeTreeMap(DataInput is, FastArrayList<Object> objectStack) throws IOException {
/* 1674 */     int size = DataInput2.unpackInt(is);
/* 1676 */     TreeMap<Object, Object> s = new TreeMap<Object, Object>();
/* 1677 */     objectStack.add(s);
/* 1678 */     Comparator<? super Object> comparator = (Comparator)deserialize(is, objectStack);
/* 1679 */     if (comparator != null)
/* 1680 */       s = new TreeMap<Object, Object>(comparator); 
/* 1681 */     for (int i = 0; i < size; i++)
/* 1682 */       s.put(deserialize(is, objectStack), deserialize(is, objectStack)); 
/* 1683 */     return s;
/*      */   }
/*      */   
/*      */   private HashMap<Object, Object> deserializeHashMap(DataInput is, FastArrayList<Object> objectStack) throws IOException {
/* 1688 */     int size = DataInput2.unpackInt(is);
/* 1690 */     HashMap<Object, Object> s = new HashMap<Object, Object>(size);
/* 1691 */     objectStack.add(s);
/* 1692 */     for (int i = 0; i < size; i++)
/* 1693 */       s.put(deserialize(is, objectStack), deserialize(is, objectStack)); 
/* 1694 */     return s;
/*      */   }
/*      */   
/*      */   private LinkedHashMap<Object, Object> deserializeLinkedHashMap(DataInput is, FastArrayList<Object> objectStack) throws IOException {
/* 1699 */     int size = DataInput2.unpackInt(is);
/* 1701 */     LinkedHashMap<Object, Object> s = new LinkedHashMap<Object, Object>(size);
/* 1702 */     objectStack.add(s);
/* 1703 */     for (int i = 0; i < size; i++)
/* 1704 */       s.put(deserialize(is, objectStack), deserialize(is, objectStack)); 
/* 1705 */     return s;
/*      */   }
/*      */   
/*      */   private Properties deserializeProperties(DataInput is, FastArrayList<Object> objectStack) throws IOException {
/* 1711 */     int size = DataInput2.unpackInt(is);
/* 1713 */     Properties s = new Properties();
/* 1714 */     objectStack.add(s);
/* 1715 */     for (int i = 0; i < size; i++)
/* 1716 */       s.put(deserialize(is, objectStack), deserialize(is, objectStack)); 
/* 1717 */     return s;
/*      */   }
/*      */   
/*      */   protected void serializeUnknownObject(DataOutput out, Object obj, FastArrayList<Object> objectStack) throws IOException {
/* 1722 */     throw new AssertionError("Could not serialize unknown object: " + obj.getClass().getName());
/*      */   }
/*      */   
/*      */   protected Object deserializeUnknownHeader(DataInput is, int head, FastArrayList<Object> objectStack) throws IOException {
/* 1726 */     throw new AssertionError("Unknown serialization header: " + head);
/*      */   }
/*      */   
/*      */   protected static byte[] booleanToByteArray(boolean[] bool) {
/* 1738 */     int boolLen = bool.length;
/* 1739 */     int mod8 = boolLen % 8;
/* 1740 */     byte[] boolBytes = new byte[boolLen / 8 + ((boolLen % 8 == 0) ? 0 : 1)];
/* 1742 */     boolean isFlushWith8 = (mod8 == 0);
/* 1743 */     int length = isFlushWith8 ? boolBytes.length : (boolBytes.length - 1);
/* 1744 */     int x = 0;
/*      */     int boolByteIndex;
/* 1746 */     for (boolByteIndex = 0; boolByteIndex < length; ) {
/* 1747 */       byte b = (byte)((bool[x++] ? 1 : 0) << 0 | (bool[x++] ? 1 : 0) << 1 | (bool[x++] ? 1 : 0) << 2 | (bool[x++] ? 1 : 0) << 3 | (bool[x++] ? 1 : 0) << 4 | (bool[x++] ? 1 : 0) << 5 | (bool[x++] ? 1 : 0) << 6 | (bool[x++] ? 1 : 0) << 7);
/* 1755 */       boolBytes[boolByteIndex++] = b;
/*      */     } 
/* 1757 */     if (!isFlushWith8) {
/* 1758 */       byte b = 0;
/* 1760 */       switch (mod8) {
/*      */         case 1:
/* 1762 */           b = (byte)(b | (bool[x++] ? 1 : 0) << 0);
/*      */           break;
/*      */         case 2:
/* 1765 */           b = (byte)(b | (bool[x++] ? 1 : 0) << 0 | (bool[x++] ? 1 : 0) << 1);
/*      */           break;
/*      */         case 3:
/* 1769 */           b = (byte)(b | (bool[x++] ? 1 : 0) << 0 | (bool[x++] ? 1 : 0) << 1 | (bool[x++] ? 1 : 0) << 2);
/*      */           break;
/*      */         case 4:
/* 1774 */           b = (byte)(b | (bool[x++] ? 1 : 0) << 0 | (bool[x++] ? 1 : 0) << 1 | (bool[x++] ? 1 : 0) << 2 | (bool[x++] ? 1 : 0) << 3);
/*      */           break;
/*      */         case 5:
/* 1780 */           b = (byte)(b | (bool[x++] ? 1 : 0) << 0 | (bool[x++] ? 1 : 0) << 1 | (bool[x++] ? 1 : 0) << 2 | (bool[x++] ? 1 : 0) << 3 | (bool[x++] ? 1 : 0) << 4);
/*      */           break;
/*      */         case 6:
/* 1787 */           b = (byte)(b | (bool[x++] ? 1 : 0) << 0 | (bool[x++] ? 1 : 0) << 1 | (bool[x++] ? 1 : 0) << 2 | (bool[x++] ? 1 : 0) << 3 | (bool[x++] ? 1 : 0) << 4 | (bool[x++] ? 1 : 0) << 5);
/*      */           break;
/*      */         case 7:
/* 1795 */           b = (byte)(b | (bool[x++] ? 1 : 0) << 0 | (bool[x++] ? 1 : 0) << 1 | (bool[x++] ? 1 : 0) << 2 | (bool[x++] ? 1 : 0) << 3 | (bool[x++] ? 1 : 0) << 4 | (bool[x++] ? 1 : 0) << 5 | (bool[x++] ? 1 : 0) << 6);
/*      */           break;
/*      */         case 8:
/* 1804 */           b = (byte)(b | (bool[x++] ? 1 : 0) << 0 | (bool[x++] ? 1 : 0) << 1 | (bool[x++] ? 1 : 0) << 2 | (bool[x++] ? 1 : 0) << 3 | (bool[x++] ? 1 : 0) << 4 | (bool[x++] ? 1 : 0) << 5 | (bool[x++] ? 1 : 0) << 6 | (bool[x++] ? 1 : 0) << 7);
/*      */           break;
/*      */       } 
/* 1850 */       boolBytes[boolByteIndex++] = b;
/*      */     } 
/* 1853 */     return boolBytes;
/*      */   }
/*      */   
/*      */   protected static boolean[] readBooleanArray(int numBools, DataInput is) throws IOException {
/* 1870 */     int length = numBools / 8 + ((numBools % 8 == 0) ? 0 : 1);
/* 1871 */     byte[] boolBytes = new byte[length];
/* 1872 */     is.readFully(boolBytes);
/* 1875 */     boolean[] tmp = new boolean[boolBytes.length * 8];
/* 1876 */     int len = boolBytes.length;
/* 1877 */     int boolIndex = 0;
/* 1878 */     for (byte boolByte : boolBytes) {
/* 1879 */       for (int y = 0; y < 8; y++)
/* 1880 */         tmp[boolIndex++] = ((boolByte & 1 << y) != 0); 
/*      */     } 
/* 1885 */     boolean[] finalBoolArray = new boolean[numBools];
/* 1886 */     System.arraycopy(tmp, 0, finalBoolArray, 0, numBools);
/* 1889 */     return finalBoolArray;
/*      */   }
/*      */   
/*      */   public int fixedSize() {
/* 2122 */     return -1;
/*      */   }
/*      */   
/*      */   protected static interface Header {
/*      */     public static final int ZERO_FAIL = 0;
/*      */     
/*      */     public static final int NULL = 1;
/*      */     
/*      */     public static final int BOOLEAN_TRUE = 2;
/*      */     
/*      */     public static final int BOOLEAN_FALSE = 3;
/*      */     
/*      */     public static final int INT_M9 = 4;
/*      */     
/*      */     public static final int INT_M8 = 5;
/*      */     
/*      */     public static final int INT_M7 = 6;
/*      */     
/*      */     public static final int INT_M6 = 7;
/*      */     
/*      */     public static final int INT_M5 = 8;
/*      */     
/*      */     public static final int INT_M4 = 9;
/*      */     
/*      */     public static final int INT_M3 = 10;
/*      */     
/*      */     public static final int INT_M2 = 11;
/*      */     
/*      */     public static final int INT_M1 = 12;
/*      */     
/*      */     public static final int INT_0 = 13;
/*      */     
/*      */     public static final int INT_1 = 14;
/*      */     
/*      */     public static final int INT_2 = 15;
/*      */     
/*      */     public static final int INT_3 = 16;
/*      */     
/*      */     public static final int INT_4 = 17;
/*      */     
/*      */     public static final int INT_5 = 18;
/*      */     
/*      */     public static final int INT_6 = 19;
/*      */     
/*      */     public static final int INT_7 = 20;
/*      */     
/*      */     public static final int INT_8 = 21;
/*      */     
/*      */     public static final int INT_9 = 22;
/*      */     
/*      */     public static final int INT_10 = 23;
/*      */     
/*      */     public static final int INT_11 = 24;
/*      */     
/*      */     public static final int INT_12 = 25;
/*      */     
/*      */     public static final int INT_13 = 26;
/*      */     
/*      */     public static final int INT_14 = 27;
/*      */     
/*      */     public static final int INT_15 = 28;
/*      */     
/*      */     public static final int INT_16 = 29;
/*      */     
/*      */     public static final int INT_MIN_VALUE = 30;
/*      */     
/*      */     public static final int INT_MAX_VALUE = 31;
/*      */     
/*      */     public static final int INT_MF1 = 32;
/*      */     
/*      */     public static final int INT_F1 = 33;
/*      */     
/*      */     public static final int INT_MF2 = 34;
/*      */     
/*      */     public static final int INT_F2 = 35;
/*      */     
/*      */     public static final int INT_MF3 = 36;
/*      */     
/*      */     public static final int INT_F3 = 37;
/*      */     
/*      */     public static final int INT = 38;
/*      */     
/*      */     public static final int LONG_M9 = 39;
/*      */     
/*      */     public static final int LONG_M8 = 40;
/*      */     
/*      */     public static final int LONG_M7 = 41;
/*      */     
/*      */     public static final int LONG_M6 = 42;
/*      */     
/*      */     public static final int LONG_M5 = 43;
/*      */     
/*      */     public static final int LONG_M4 = 44;
/*      */     
/*      */     public static final int LONG_M3 = 45;
/*      */     
/*      */     public static final int LONG_M2 = 46;
/*      */     
/*      */     public static final int LONG_M1 = 47;
/*      */     
/*      */     public static final int LONG_0 = 48;
/*      */     
/*      */     public static final int LONG_1 = 49;
/*      */     
/*      */     public static final int LONG_2 = 50;
/*      */     
/*      */     public static final int LONG_3 = 51;
/*      */     
/*      */     public static final int LONG_4 = 52;
/*      */     
/*      */     public static final int LONG_5 = 53;
/*      */     
/*      */     public static final int LONG_6 = 54;
/*      */     
/*      */     public static final int LONG_7 = 55;
/*      */     
/*      */     public static final int LONG_8 = 56;
/*      */     
/*      */     public static final int LONG_9 = 57;
/*      */     
/*      */     public static final int LONG_10 = 58;
/*      */     
/*      */     public static final int LONG_11 = 59;
/*      */     
/*      */     public static final int LONG_12 = 60;
/*      */     
/*      */     public static final int LONG_13 = 61;
/*      */     
/*      */     public static final int LONG_14 = 62;
/*      */     
/*      */     public static final int LONG_15 = 63;
/*      */     
/*      */     public static final int LONG_16 = 64;
/*      */     
/*      */     public static final int LONG_MIN_VALUE = 65;
/*      */     
/*      */     public static final int LONG_MAX_VALUE = 66;
/*      */     
/*      */     public static final int LONG_MF1 = 67;
/*      */     
/*      */     public static final int LONG_F1 = 68;
/*      */     
/*      */     public static final int LONG_MF2 = 69;
/*      */     
/*      */     public static final int LONG_F2 = 70;
/*      */     
/*      */     public static final int LONG_MF3 = 71;
/*      */     
/*      */     public static final int LONG_F3 = 72;
/*      */     
/*      */     public static final int LONG_MF4 = 73;
/*      */     
/*      */     public static final int LONG_F4 = 74;
/*      */     
/*      */     public static final int LONG_MF5 = 75;
/*      */     
/*      */     public static final int LONG_F5 = 76;
/*      */     
/*      */     public static final int LONG_MF6 = 77;
/*      */     
/*      */     public static final int LONG_F6 = 78;
/*      */     
/*      */     public static final int LONG_MF7 = 79;
/*      */     
/*      */     public static final int LONG_F7 = 80;
/*      */     
/*      */     public static final int LONG = 81;
/*      */     
/*      */     public static final int BYTE_M1 = 82;
/*      */     
/*      */     public static final int BYTE_0 = 83;
/*      */     
/*      */     public static final int BYTE_1 = 84;
/*      */     
/*      */     public static final int BYTE = 85;
/*      */     
/*      */     public static final int CHAR_0 = 86;
/*      */     
/*      */     public static final int CHAR_1 = 87;
/*      */     
/*      */     public static final int CHAR_255 = 88;
/*      */     
/*      */     public static final int CHAR = 89;
/*      */     
/*      */     public static final int SHORT_M1 = 90;
/*      */     
/*      */     public static final int SHORT_0 = 91;
/*      */     
/*      */     public static final int SHORT_1 = 92;
/*      */     
/*      */     public static final int SHORT_255 = 93;
/*      */     
/*      */     public static final int SHORT_M255 = 94;
/*      */     
/*      */     public static final int SHORT = 95;
/*      */     
/*      */     public static final int FLOAT_M1 = 96;
/*      */     
/*      */     public static final int FLOAT_0 = 97;
/*      */     
/*      */     public static final int FLOAT_1 = 98;
/*      */     
/*      */     public static final int FLOAT_255 = 99;
/*      */     
/*      */     public static final int FLOAT_SHORT = 100;
/*      */     
/*      */     public static final int FLOAT = 101;
/*      */     
/*      */     public static final int DOUBLE_M1 = 102;
/*      */     
/*      */     public static final int DOUBLE_0 = 103;
/*      */     
/*      */     public static final int DOUBLE_1 = 104;
/*      */     
/*      */     public static final int DOUBLE_255 = 105;
/*      */     
/*      */     public static final int DOUBLE_SHORT = 106;
/*      */     
/*      */     public static final int DOUBLE_INT = 107;
/*      */     
/*      */     public static final int DOUBLE = 108;
/*      */     
/*      */     public static final int ARRAY_BYTE = 109;
/*      */     
/*      */     public static final int ARRAY_BYTE_ALL_EQUAL = 110;
/*      */     
/*      */     public static final int ARRAY_BOOLEAN = 111;
/*      */     
/*      */     public static final int ARRAY_SHORT = 112;
/*      */     
/*      */     public static final int ARRAY_CHAR = 113;
/*      */     
/*      */     public static final int ARRAY_FLOAT = 114;
/*      */     
/*      */     public static final int ARRAY_DOUBLE = 115;
/*      */     
/*      */     public static final int ARRAY_INT_BYTE = 116;
/*      */     
/*      */     public static final int ARRAY_INT_SHORT = 117;
/*      */     
/*      */     public static final int ARRAY_INT_PACKED = 118;
/*      */     
/*      */     public static final int ARRAY_INT = 119;
/*      */     
/*      */     public static final int ARRAY_LONG_BYTE = 120;
/*      */     
/*      */     public static final int ARRAY_LONG_SHORT = 121;
/*      */     
/*      */     public static final int ARRAY_LONG_PACKED = 122;
/*      */     
/*      */     public static final int ARRAY_LONG_INT = 123;
/*      */     
/*      */     public static final int ARRAY_LONG = 124;
/*      */     
/*      */     public static final int STRING_0 = 125;
/*      */     
/*      */     public static final int STRING_1 = 126;
/*      */     
/*      */     public static final int STRING_2 = 127;
/*      */     
/*      */     public static final int STRING_3 = 128;
/*      */     
/*      */     public static final int STRING_4 = 129;
/*      */     
/*      */     public static final int STRING_5 = 130;
/*      */     
/*      */     public static final int STRING_6 = 131;
/*      */     
/*      */     public static final int STRING_7 = 132;
/*      */     
/*      */     public static final int STRING_8 = 133;
/*      */     
/*      */     public static final int STRING_9 = 134;
/*      */     
/*      */     public static final int STRING_10 = 135;
/*      */     
/*      */     public static final int STRING = 136;
/*      */     
/*      */     public static final int BIGDECIMAL = 137;
/*      */     
/*      */     public static final int BIGINTEGER = 138;
/*      */     
/*      */     public static final int CLASS = 139;
/*      */     
/*      */     public static final int DATE = 140;
/*      */     
/*      */     public static final int FUN_HI = 141;
/*      */     
/*      */     public static final int UUID = 142;
/*      */     
/*      */     public static final int MAPDB = 150;
/*      */     
/*      */     public static final int TUPLE2 = 151;
/*      */     
/*      */     public static final int TUPLE3 = 152;
/*      */     
/*      */     public static final int TUPLE4 = 153;
/*      */     
/*      */     public static final int TUPLE5 = 154;
/*      */     
/*      */     public static final int TUPLE6 = 155;
/*      */     
/*      */     public static final int TUPLE7 = 156;
/*      */     
/*      */     public static final int TUPLE8 = 157;
/*      */     
/*      */     public static final int ARRAY_OBJECT = 158;
/*      */     
/*      */     public static final int ARRAY_OBJECT_PACKED_LONG = 159;
/*      */     
/*      */     public static final int ARRAYLIST_PACKED_LONG = 160;
/*      */     
/*      */     public static final int ARRAY_OBJECT_ALL_NULL = 161;
/*      */     
/*      */     public static final int ARRAY_OBJECT_NO_REFS = 162;
/*      */     
/*      */     public static final int ARRAYLIST = 163;
/*      */     
/*      */     public static final int TREEMAP = 164;
/*      */     
/*      */     public static final int HASHMAP = 165;
/*      */     
/*      */     public static final int LINKEDHASHMAP = 166;
/*      */     
/*      */     public static final int TREESET = 167;
/*      */     
/*      */     public static final int HASHSET = 168;
/*      */     
/*      */     public static final int LINKEDHASHSET = 169;
/*      */     
/*      */     public static final int LINKEDLIST = 170;
/*      */     
/*      */     public static final int PROPERTIES = 171;
/*      */     
/*      */     public static final int JAVA_SERIALIZATION = 172;
/*      */     
/*      */     public static final int POJO = 173;
/*      */     
/*      */     public static final int OBJECT_STACK = 174;
/*      */     
/*      */     public static final int NAMED = 175;
/*      */     
/*      */     public static final int MA_LONG = 176;
/*      */     
/*      */     public static final int MA_INT = 177;
/*      */     
/*      */     public static final int MA_BOOL = 178;
/*      */     
/*      */     public static final int MA_STRING = 179;
/*      */     
/*      */     public static final int MA_VAR = 180;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\SerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */