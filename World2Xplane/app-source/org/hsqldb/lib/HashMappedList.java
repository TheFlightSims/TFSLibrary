package org.hsqldb.lib;

public class HashMappedList extends HashMap {
  public HashMappedList() {
    this(8);
  }
  
  public HashMappedList(int paramInt) throws IllegalArgumentException {
    super(paramInt);
  }
  
  public Object get(int paramInt) throws IndexOutOfBoundsException {
    checkRange(paramInt);
    return this.objectValueTable[paramInt];
  }
  
  public Object remove(Object paramObject) {
    int i = getLookup(paramObject, paramObject.hashCode());
    if (i < 0)
      return null; 
    Object object = super.remove(paramObject);
    removeRow(i);
    return object;
  }
  
  public Object remove(int paramInt) throws IndexOutOfBoundsException {
    checkRange(paramInt);
    return remove(this.objectKeyTable[paramInt]);
  }
  
  public boolean add(Object paramObject1, Object paramObject2) {
    int i = getLookup(paramObject1, paramObject1.hashCode());
    if (i >= 0)
      return false; 
    super.put(paramObject1, paramObject2);
    return true;
  }
  
  public Object put(Object paramObject1, Object paramObject2) {
    return super.put(paramObject1, paramObject2);
  }
  
  public Object set(int paramInt, Object paramObject) throws IndexOutOfBoundsException {
    checkRange(paramInt);
    Object object = this.objectKeyTable[paramInt];
    this.objectKeyTable[paramInt] = paramObject;
    return object;
  }
  
  public boolean insert(int paramInt, Object paramObject1, Object paramObject2) throws IndexOutOfBoundsException {
    if (paramInt < 0 || paramInt > size())
      throw new IndexOutOfBoundsException(); 
    int i = getLookup(paramObject1, paramObject1.hashCode());
    if (i >= 0)
      return false; 
    if (paramInt == size())
      return add(paramObject1, paramObject2); 
    HashMappedList hashMappedList = new HashMappedList(size());
    int j;
    for (j = paramInt; j < size(); j++)
      hashMappedList.add(getKey(j), get(j)); 
    for (j = size() - 1; j >= paramInt; j--)
      remove(j); 
    for (j = 0; j < hashMappedList.size(); j++)
      add(hashMappedList.getKey(j), hashMappedList.get(j)); 
    return true;
  }
  
  public boolean set(int paramInt, Object paramObject1, Object paramObject2) throws IndexOutOfBoundsException {
    checkRange(paramInt);
    if (keySet().contains(paramObject1) && getIndex(paramObject1) != paramInt)
      return false; 
    super.remove(this.objectKeyTable[paramInt]);
    super.put(paramObject1, paramObject2);
    return true;
  }
  
  public boolean setKey(int paramInt, Object paramObject) throws IndexOutOfBoundsException {
    checkRange(paramInt);
    Object object = this.objectValueTable[paramInt];
    return set(paramInt, paramObject, object);
  }
  
  public boolean setValue(int paramInt, Object paramObject) throws IndexOutOfBoundsException {
    boolean bool;
    Object object = this.objectValueTable[paramInt];
    if (paramObject == null) {
      bool = (paramObject != object) ? true : false;
    } else {
      bool = !paramObject.equals(object) ? true : false;
    } 
    this.objectValueTable[paramInt] = paramObject;
    return bool;
  }
  
  public Object getKey(int paramInt) throws IndexOutOfBoundsException {
    checkRange(paramInt);
    return this.objectKeyTable[paramInt];
  }
  
  public int getIndex(Object paramObject) {
    return getLookup(paramObject, paramObject.hashCode());
  }
  
  public Object[] toValuesArray(Object[] paramArrayOfObject) {
    int i = size();
    if (paramArrayOfObject == null || paramArrayOfObject.length < i)
      paramArrayOfObject = new Object[i]; 
    for (byte b = 0; b < i; b++)
      paramArrayOfObject[b] = this.objectValueTable[b]; 
    return paramArrayOfObject;
  }
  
  public Object[] toKeysArray(Object[] paramArrayOfObject) {
    int i = size();
    if (paramArrayOfObject == null || paramArrayOfObject.length < i)
      paramArrayOfObject = new Object[i]; 
    for (byte b = 0; b < i; b++)
      paramArrayOfObject[b] = this.objectKeyTable[b]; 
    return paramArrayOfObject;
  }
  
  private void checkRange(int paramInt) {
    if (paramInt < 0 || paramInt >= size())
      throw new IndexOutOfBoundsException(); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\HashMappedList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */