/*      */ package org.apache.commons.collections;
/*      */ 
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ 
/*      */ class CursorableSubList extends CursorableLinkedList implements List {
/*      */   CursorableSubList(CursorableLinkedList list, int from, int to) {
/* 1207 */     if (0 > from || list.size() < to)
/* 1208 */       throw new IndexOutOfBoundsException(); 
/* 1209 */     if (from > to)
/* 1210 */       throw new IllegalArgumentException(); 
/* 1212 */     this._list = list;
/* 1213 */     if (from < list.size()) {
/* 1214 */       this._head.setNext(this._list.getListableAt(from));
/* 1215 */       this._pre = (null == this._head.next()) ? null : this._head.next().prev();
/*      */     } else {
/* 1217 */       this._pre = this._list.getListableAt(from - 1);
/*      */     } 
/* 1219 */     if (from == to) {
/* 1220 */       this._head.setNext(null);
/* 1221 */       this._head.setPrev(null);
/* 1222 */       if (to < list.size()) {
/* 1223 */         this._post = this._list.getListableAt(to);
/*      */       } else {
/* 1225 */         this._post = null;
/*      */       } 
/*      */     } else {
/* 1228 */       this._head.setPrev(this._list.getListableAt(to - 1));
/* 1229 */       this._post = this._head.prev().next();
/*      */     } 
/* 1231 */     this._size = to - from;
/* 1232 */     this._modCount = this._list._modCount;
/*      */   }
/*      */   
/*      */   public void clear() {
/* 1238 */     checkForComod();
/* 1239 */     Iterator it = iterator();
/* 1240 */     while (it.hasNext()) {
/* 1241 */       it.next();
/* 1242 */       it.remove();
/*      */     } 
/*      */   }
/*      */   
/*      */   public Iterator iterator() {
/* 1247 */     checkForComod();
/* 1248 */     return super.iterator();
/*      */   }
/*      */   
/*      */   public int size() {
/* 1252 */     checkForComod();
/* 1253 */     return super.size();
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/* 1257 */     checkForComod();
/* 1258 */     return super.isEmpty();
/*      */   }
/*      */   
/*      */   public Object[] toArray() {
/* 1262 */     checkForComod();
/* 1263 */     return super.toArray();
/*      */   }
/*      */   
/*      */   public Object[] toArray(Object[] a) {
/* 1267 */     checkForComod();
/* 1268 */     return super.toArray(a);
/*      */   }
/*      */   
/*      */   public boolean contains(Object o) {
/* 1272 */     checkForComod();
/* 1273 */     return super.contains(o);
/*      */   }
/*      */   
/*      */   public boolean remove(Object o) {
/* 1277 */     checkForComod();
/* 1278 */     return super.remove(o);
/*      */   }
/*      */   
/*      */   public Object removeFirst() {
/* 1282 */     checkForComod();
/* 1283 */     return super.removeFirst();
/*      */   }
/*      */   
/*      */   public Object removeLast() {
/* 1287 */     checkForComod();
/* 1288 */     return super.removeLast();
/*      */   }
/*      */   
/*      */   public boolean addAll(Collection c) {
/* 1292 */     checkForComod();
/* 1293 */     return super.addAll(c);
/*      */   }
/*      */   
/*      */   public boolean add(Object o) {
/* 1297 */     checkForComod();
/* 1298 */     return super.add(o);
/*      */   }
/*      */   
/*      */   public boolean addFirst(Object o) {
/* 1302 */     checkForComod();
/* 1303 */     return super.addFirst(o);
/*      */   }
/*      */   
/*      */   public boolean addLast(Object o) {
/* 1307 */     checkForComod();
/* 1308 */     return super.addLast(o);
/*      */   }
/*      */   
/*      */   public boolean removeAll(Collection c) {
/* 1312 */     checkForComod();
/* 1313 */     return super.removeAll(c);
/*      */   }
/*      */   
/*      */   public boolean containsAll(Collection c) {
/* 1317 */     checkForComod();
/* 1318 */     return super.containsAll(c);
/*      */   }
/*      */   
/*      */   public boolean addAll(int index, Collection c) {
/* 1322 */     checkForComod();
/* 1323 */     return super.addAll(index, c);
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1327 */     checkForComod();
/* 1328 */     return super.hashCode();
/*      */   }
/*      */   
/*      */   public boolean retainAll(Collection c) {
/* 1332 */     checkForComod();
/* 1333 */     return super.retainAll(c);
/*      */   }
/*      */   
/*      */   public Object set(int index, Object element) {
/* 1337 */     checkForComod();
/* 1338 */     return super.set(index, element);
/*      */   }
/*      */   
/*      */   public boolean equals(Object o) {
/* 1342 */     checkForComod();
/* 1343 */     return super.equals(o);
/*      */   }
/*      */   
/*      */   public Object get(int index) {
/* 1347 */     checkForComod();
/* 1348 */     return super.get(index);
/*      */   }
/*      */   
/*      */   public Object getFirst() {
/* 1352 */     checkForComod();
/* 1353 */     return super.getFirst();
/*      */   }
/*      */   
/*      */   public Object getLast() {
/* 1357 */     checkForComod();
/* 1358 */     return super.getLast();
/*      */   }
/*      */   
/*      */   public void add(int index, Object element) {
/* 1362 */     checkForComod();
/* 1363 */     super.add(index, element);
/*      */   }
/*      */   
/*      */   public ListIterator listIterator(int index) {
/* 1367 */     checkForComod();
/* 1368 */     return super.listIterator(index);
/*      */   }
/*      */   
/*      */   public Object remove(int index) {
/* 1372 */     checkForComod();
/* 1373 */     return super.remove(index);
/*      */   }
/*      */   
/*      */   public int indexOf(Object o) {
/* 1377 */     checkForComod();
/* 1378 */     return super.indexOf(o);
/*      */   }
/*      */   
/*      */   public int lastIndexOf(Object o) {
/* 1382 */     checkForComod();
/* 1383 */     return super.lastIndexOf(o);
/*      */   }
/*      */   
/*      */   public ListIterator listIterator() {
/* 1387 */     checkForComod();
/* 1388 */     return super.listIterator();
/*      */   }
/*      */   
/*      */   public List subList(int fromIndex, int toIndex) {
/* 1392 */     checkForComod();
/* 1393 */     return super.subList(fromIndex, toIndex);
/*      */   }
/*      */   
/*      */   protected CursorableLinkedList.Listable insertListable(CursorableLinkedList.Listable before, CursorableLinkedList.Listable after, Object value) {
/* 1406 */     this._modCount++;
/* 1407 */     this._size++;
/* 1408 */     CursorableLinkedList.Listable elt = this._list.insertListable((null == before) ? this._pre : before, (null == after) ? this._post : after, value);
/* 1409 */     if (null == this._head.next()) {
/* 1410 */       this._head.setNext(elt);
/* 1411 */       this._head.setPrev(elt);
/*      */     } 
/* 1413 */     if (before == this._head.prev())
/* 1414 */       this._head.setPrev(elt); 
/* 1416 */     if (after == this._head.next())
/* 1417 */       this._head.setNext(elt); 
/* 1419 */     broadcastListableInserted(elt);
/* 1420 */     return elt;
/*      */   }
/*      */   
/*      */   protected void removeListable(CursorableLinkedList.Listable elt) {
/* 1427 */     this._modCount++;
/* 1428 */     this._size--;
/* 1429 */     if (this._head.next() == elt && this._head.prev() == elt) {
/* 1430 */       this._head.setNext(null);
/* 1431 */       this._head.setPrev(null);
/*      */     } 
/* 1433 */     if (this._head.next() == elt)
/* 1434 */       this._head.setNext(elt.next()); 
/* 1436 */     if (this._head.prev() == elt)
/* 1437 */       this._head.setPrev(elt.prev()); 
/* 1439 */     this._list.removeListable(elt);
/* 1440 */     broadcastListableRemoved(elt);
/*      */   }
/*      */   
/*      */   protected void checkForComod() throws ConcurrentModificationException {
/* 1452 */     if (this._modCount != this._list._modCount)
/* 1453 */       throw new ConcurrentModificationException(); 
/*      */   }
/*      */   
/* 1460 */   protected CursorableLinkedList _list = null;
/*      */   
/* 1463 */   protected CursorableLinkedList.Listable _pre = null;
/*      */   
/* 1466 */   protected CursorableLinkedList.Listable _post = null;
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\CursorableSubList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */