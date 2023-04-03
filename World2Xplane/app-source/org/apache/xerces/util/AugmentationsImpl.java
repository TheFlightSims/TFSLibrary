package org.apache.xerces.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import org.apache.xerces.xni.Augmentations;

public class AugmentationsImpl implements Augmentations {
  private AugmentationsItemsContainer fAugmentationsContainer = new SmallContainer(this);
  
  public Object putItem(String paramString, Object paramObject) {
    Object object = this.fAugmentationsContainer.putItem(paramString, paramObject);
    if (object == null && this.fAugmentationsContainer.isFull())
      this.fAugmentationsContainer = this.fAugmentationsContainer.expand(); 
    return object;
  }
  
  public Object getItem(String paramString) {
    return this.fAugmentationsContainer.getItem(paramString);
  }
  
  public Object removeItem(String paramString) {
    return this.fAugmentationsContainer.removeItem(paramString);
  }
  
  public Enumeration keys() {
    return this.fAugmentationsContainer.keys();
  }
  
  public void removeAllItems() {
    this.fAugmentationsContainer.clear();
  }
  
  public String toString() {
    return this.fAugmentationsContainer.toString();
  }
  
  class LargeContainer extends AugmentationsItemsContainer {
    final Hashtable fAugmentations;
    
    private final AugmentationsImpl this$0;
    
    LargeContainer(AugmentationsImpl this$0) {
      super(this$0);
      this.this$0 = this$0;
      this.fAugmentations = new Hashtable();
    }
    
    public Object getItem(Object param1Object) {
      return this.fAugmentations.get(param1Object);
    }
    
    public Object putItem(Object param1Object1, Object param1Object2) {
      return this.fAugmentations.put(param1Object1, param1Object2);
    }
    
    public Object removeItem(Object param1Object) {
      return this.fAugmentations.remove(param1Object);
    }
    
    public Enumeration keys() {
      return this.fAugmentations.keys();
    }
    
    public void clear() {
      this.fAugmentations.clear();
    }
    
    public boolean isFull() {
      return false;
    }
    
    public AugmentationsImpl.AugmentationsItemsContainer expand() {
      return this;
    }
    
    public String toString() {
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append("LargeContainer");
      Enumeration enumeration = this.fAugmentations.keys();
      while (enumeration.hasMoreElements()) {
        Object object = enumeration.nextElement();
        stringBuffer.append("\nkey == ");
        stringBuffer.append(object);
        stringBuffer.append("; value == ");
        stringBuffer.append(this.fAugmentations.get(object));
      } 
      return stringBuffer.toString();
    }
  }
  
  class SmallContainer extends AugmentationsItemsContainer {
    static final int SIZE_LIMIT = 10;
    
    final Object[] fAugmentations;
    
    int fNumEntries;
    
    private final AugmentationsImpl this$0;
    
    SmallContainer(AugmentationsImpl this$0) {
      super(this$0);
      this.this$0 = this$0;
      this.fAugmentations = new Object[20];
      this.fNumEntries = 0;
    }
    
    public Enumeration keys() {
      return new SmallContainerKeyEnumeration(this);
    }
    
    public Object getItem(Object param1Object) {
      for (int i = 0; i < this.fNumEntries * 2; i += 2) {
        if (this.fAugmentations[i].equals(param1Object))
          return this.fAugmentations[i + 1]; 
      } 
      return null;
    }
    
    public Object putItem(Object param1Object1, Object param1Object2) {
      for (int i = 0; i < this.fNumEntries * 2; i += 2) {
        if (this.fAugmentations[i].equals(param1Object1)) {
          Object object = this.fAugmentations[i + 1];
          this.fAugmentations[i + 1] = param1Object2;
          return object;
        } 
      } 
      this.fAugmentations[this.fNumEntries * 2] = param1Object1;
      this.fAugmentations[this.fNumEntries * 2 + 1] = param1Object2;
      this.fNumEntries++;
      return null;
    }
    
    public Object removeItem(Object param1Object) {
      for (int i = 0; i < this.fNumEntries * 2; i += 2) {
        if (this.fAugmentations[i].equals(param1Object)) {
          Object object = this.fAugmentations[i + 1];
          for (int j = i; j < this.fNumEntries * 2 - 2; j += 2) {
            this.fAugmentations[j] = this.fAugmentations[j + 2];
            this.fAugmentations[j + 1] = this.fAugmentations[j + 3];
          } 
          this.fAugmentations[this.fNumEntries * 2 - 2] = null;
          this.fAugmentations[this.fNumEntries * 2 - 1] = null;
          this.fNumEntries--;
          return object;
        } 
      } 
      return null;
    }
    
    public void clear() {
      for (int i = 0; i < this.fNumEntries * 2; i += 2) {
        this.fAugmentations[i] = null;
        this.fAugmentations[i + 1] = null;
      } 
      this.fNumEntries = 0;
    }
    
    public boolean isFull() {
      return (this.fNumEntries == 10);
    }
    
    public AugmentationsImpl.AugmentationsItemsContainer expand() {
      AugmentationsImpl.LargeContainer largeContainer = new AugmentationsImpl.LargeContainer(this.this$0);
      for (int i = 0; i < this.fNumEntries * 2; i += 2)
        largeContainer.putItem(this.fAugmentations[i], this.fAugmentations[i + 1]); 
      return largeContainer;
    }
    
    public String toString() {
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append("SmallContainer - fNumEntries == ").append(this.fNumEntries);
      for (int i = 0; i < 20; i += 2) {
        stringBuffer.append("\nfAugmentations[");
        stringBuffer.append(i);
        stringBuffer.append("] == ");
        stringBuffer.append(this.fAugmentations[i]);
        stringBuffer.append("; fAugmentations[");
        stringBuffer.append(i + 1);
        stringBuffer.append("] == ");
        stringBuffer.append(this.fAugmentations[i + 1]);
      } 
      return stringBuffer.toString();
    }
    
    class SmallContainerKeyEnumeration implements Enumeration {
      Object[] enumArray;
      
      int next;
      
      private final AugmentationsImpl.SmallContainer this$1;
      
      SmallContainerKeyEnumeration(AugmentationsImpl.SmallContainer this$0) {
        this.this$1 = this$0;
        this.enumArray = new Object[this.this$1.fNumEntries];
        this.next = 0;
        for (byte b = 0; b < this$0.fNumEntries; b++)
          this.enumArray[b] = this$0.fAugmentations[b * 2]; 
      }
      
      public boolean hasMoreElements() {
        return (this.next < this.enumArray.length);
      }
      
      public Object nextElement() {
        if (this.next >= this.enumArray.length)
          throw new NoSuchElementException(); 
        Object object = this.enumArray[this.next];
        this.enumArray[this.next] = null;
        this.next++;
        return object;
      }
    }
  }
  
  abstract class AugmentationsItemsContainer {
    private final AugmentationsImpl this$0;
    
    AugmentationsItemsContainer(AugmentationsImpl this$0) {
      this.this$0 = this$0;
    }
    
    public abstract Object putItem(Object param1Object1, Object param1Object2);
    
    public abstract Object getItem(Object param1Object);
    
    public abstract Object removeItem(Object param1Object);
    
    public abstract Enumeration keys();
    
    public abstract void clear();
    
    public abstract boolean isFull();
    
    public abstract AugmentationsItemsContainer expand();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\AugmentationsImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */