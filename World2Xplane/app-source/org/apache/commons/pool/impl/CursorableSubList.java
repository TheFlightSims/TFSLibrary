/*      */ package org.apache.commons.pool.impl;
/*      */ 
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ 
/*      */ class CursorableSubList extends CursorableLinkedList implements List {
/*      */   CursorableSubList(CursorableLinkedList list, int from, int to) {
/* 1211 */     if (0 > from || list.size() < to)
/* 1212 */       throw new IndexOutOfBoundsException(); 
/* 1213 */     if (from > to)
/* 1214 */       throw new IllegalArgumentException(); 
/* 1216 */     this._list = list;
/* 1217 */     if (from < list.size()) {
/* 1218 */       this._head.setNext(this._list.getListableAt(from));
/* 1219 */       this._pre = (null == this._head.next()) ? null : this._head.next().prev();
/*      */     } else {
/* 1221 */       this._pre = this._list.getListableAt(from - 1);
/*      */     } 
/* 1223 */     if (from == to) {
/* 1224 */       this._head.setNext(null);
/* 1225 */       this._head.setPrev(null);
/* 1226 */       if (to < list.size()) {
/* 1227 */         this._post = this._list.getListableAt(to);
/*      */       } else {
/* 1229 */         this._post = null;
/*      */       } 
/*      */     } else {
/* 1232 */       this._head.setPrev(this._list.getListableAt(to - 1));
/* 1233 */       this._post = this._head.prev().next();
/*      */     } 
/* 1235 */     this._size = to - from;
/* 1236 */     this._modCount = this._list._modCount;
/*      */   }
/*      */   
/*      */   public void clear() {
/* 1242 */     checkForComod();
/* 1243 */     Iterator it = iterator();
/* 1244 */     while (it.hasNext()) {
/* 1245 */       it.next();
/* 1246 */       it.remove();
/*      */     } 
/*      */   }
/*      */   
/*      */   public Iterator iterator() {
/* 1251 */     checkForComod();
/* 1252 */     return super.iterator();
/*      */   }
/*      */   
/*      */   public int size() {
/* 1256 */     checkForComod();
/* 1257 */     return super.size();
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/* 1261 */     checkForComod();
/* 1262 */     return super.isEmpty();
/*      */   }
/*      */   
/*      */   public Object[] toArray() {
/* 1266 */     checkForComod();
/* 1267 */     return super.toArray();
/*      */   }
/*      */   
/*      */   public Object[] toArray(Object[] a) {
/* 1271 */     checkForComod();
/* 1272 */     return super.toArray(a);
/*      */   }
/*      */   
/*      */   public boolean contains(Object o) {
/* 1276 */     checkForComod();
/* 1277 */     return super.contains(o);
/*      */   }
/*      */   
/*      */   public boolean remove(Object o) {
/* 1281 */     checkForComod();
/* 1282 */     return super.remove(o);
/*      */   }
/*      */   
/*      */   public Object removeFirst() {
/* 1286 */     checkForComod();
/* 1287 */     return super.removeFirst();
/*      */   }
/*      */   
/*      */   public Object removeLast() {
/* 1291 */     checkForComod();
/* 1292 */     return super.removeLast();
/*      */   }
/*      */   
/*      */   public boolean addAll(Collection c) {
/* 1296 */     checkForComod();
/* 1297 */     return super.addAll(c);
/*      */   }
/*      */   
/*      */   public boolean add(Object o) {
/* 1301 */     checkForComod();
/* 1302 */     return super.add(o);
/*      */   }
/*      */   
/*      */   public boolean addFirst(Object o) {
/* 1306 */     checkForComod();
/* 1307 */     return super.addFirst(o);
/*      */   }
/*      */   
/*      */   public boolean addLast(Object o) {
/* 1311 */     checkForComod();
/* 1312 */     return super.addLast(o);
/*      */   }
/*      */   
/*      */   public boolean removeAll(Collection c) {
/* 1316 */     checkForComod();
/* 1317 */     return super.removeAll(c);
/*      */   }
/*      */   
/*      */   public boolean containsAll(Collection c) {
/* 1321 */     checkForComod();
/* 1322 */     return super.containsAll(c);
/*      */   }
/*      */   
/*      */   public boolean addAll(int index, Collection c) {
/* 1326 */     checkForComod();
/* 1327 */     return super.addAll(index, c);
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1331 */     checkForComod();
/* 1332 */     return super.hashCode();
/*      */   }
/*      */   
/*      */   public boolean retainAll(Collection c) {
/* 1336 */     checkForComod();
/* 1337 */     return super.retainAll(c);
/*      */   }
/*      */   
/*      */   public Object set(int index, Object element) {
/* 1341 */     checkForComod();
/* 1342 */     return super.set(index, element);
/*      */   }
/*      */   
/*      */   public boolean equals(Object o) {
/* 1346 */     checkForComod();
/* 1347 */     return super.equals(o);
/*      */   }
/*      */   
/*      */   public Object get(int index) {
/* 1351 */     checkForComod();
/* 1352 */     return super.get(index);
/*      */   }
/*      */   
/*      */   public Object getFirst() {
/* 1356 */     checkForComod();
/* 1357 */     return super.getFirst();
/*      */   }
/*      */   
/*      */   public Object getLast() {
/* 1361 */     checkForComod();
/* 1362 */     return super.getLast();
/*      */   }
/*      */   
/*      */   public void add(int index, Object element) {
/* 1366 */     checkForComod();
/* 1367 */     super.add(index, element);
/*      */   }
/*      */   
/*      */   public ListIterator listIterator(int index) {
/* 1371 */     checkForComod();
/* 1372 */     return super.listIterator(index);
/*      */   }
/*      */   
/*      */   public Object remove(int index) {
/* 1376 */     checkForComod();
/* 1377 */     return super.remove(index);
/*      */   }
/*      */   
/*      */   public int indexOf(Object o) {
/* 1381 */     checkForComod();
/* 1382 */     return super.indexOf(o);
/*      */   }
/*      */   
/*      */   public int lastIndexOf(Object o) {
/* 1386 */     checkForComod();
/* 1387 */     return super.lastIndexOf(o);
/*      */   }
/*      */   
/*      */   public ListIterator listIterator() {
/* 1391 */     checkForComod();
/* 1392 */     return super.listIterator();
/*      */   }
/*      */   
/*      */   public List subList(int fromIndex, int toIndex) {
/* 1396 */     checkForComod();
/* 1397 */     return super.subList(fromIndex, toIndex);
/*      */   }
/*      */   
/*      */   protected CursorableLinkedList.Listable insertListable(CursorableLinkedList.Listable before, CursorableLinkedList.Listable after, Object value) {
/* 1410 */     this._modCount++;
/* 1411 */     this._size++;
/* 1412 */     CursorableLinkedList.Listable elt = this._list.insertListable((null == before) ? this._pre : before, (null == after) ? this._post : after, value);
/* 1413 */     if (null == this._head.next()) {
/* 1414 */       this._head.setNext(elt);
/* 1415 */       this._head.setPrev(elt);
/*      */     } 
/* 1417 */     if (before == this._head.prev())
/* 1418 */       this._head.setPrev(elt); 
/* 1420 */     if (after == this._head.next())
/* 1421 */       this._head.setNext(elt); 
/* 1423 */     broadcastListableInserted(elt);
/* 1424 */     return elt;
/*      */   }
/*      */   
/*      */   protected void removeListable(CursorableLinkedList.Listable elt) {
/* 1431 */     this._modCount++;
/* 1432 */     this._size--;
/* 1433 */     if (this._head.next() == elt && this._head.prev() == elt) {
/* 1434 */       this._head.setNext(null);
/* 1435 */       this._head.setPrev(null);
/*      */     } 
/* 1437 */     if (this._head.next() == elt)
/* 1438 */       this._head.setNext(elt.next()); 
/* 1440 */     if (this._head.prev() == elt)
/* 1441 */       this._head.setPrev(elt.prev()); 
/* 1443 */     this._list.removeListable(elt);
/* 1444 */     broadcastListableRemoved(elt);
/*      */   }
/*      */   
/*      */   protected void checkForComod() throws ConcurrentModificationException {
/* 1456 */     if (this._modCount != this._list._modCount)
/* 1457 */       throw new ConcurrentModificationException(); 
/*      */   }
/*      */   
/* 1464 */   protected CursorableLinkedList _list = null;
/*      */   
/* 1467 */   protected CursorableLinkedList.Listable _pre = null;
/*      */   
/* 1470 */   protected CursorableLinkedList.Listable _post = null;
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\impl\CursorableSubList.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */