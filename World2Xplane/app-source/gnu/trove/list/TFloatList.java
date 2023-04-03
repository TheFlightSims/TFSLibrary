package gnu.trove.list;

import gnu.trove.TFloatCollection;
import gnu.trove.function.TFloatFunction;
import gnu.trove.procedure.TFloatProcedure;
import java.util.Random;

public interface TFloatList extends TFloatCollection {
  float getNoEntryValue();
  
  int size();
  
  boolean isEmpty();
  
  boolean add(float paramFloat);
  
  void add(float[] paramArrayOffloat);
  
  void add(float[] paramArrayOffloat, int paramInt1, int paramInt2);
  
  void insert(int paramInt, float paramFloat);
  
  void insert(int paramInt, float[] paramArrayOffloat);
  
  void insert(int paramInt1, float[] paramArrayOffloat, int paramInt2, int paramInt3);
  
  float get(int paramInt);
  
  float set(int paramInt, float paramFloat);
  
  void set(int paramInt, float[] paramArrayOffloat);
  
  void set(int paramInt1, float[] paramArrayOffloat, int paramInt2, int paramInt3);
  
  float replace(int paramInt, float paramFloat);
  
  void clear();
  
  boolean remove(float paramFloat);
  
  float removeAt(int paramInt);
  
  void remove(int paramInt1, int paramInt2);
  
  void transformValues(TFloatFunction paramTFloatFunction);
  
  void reverse();
  
  void reverse(int paramInt1, int paramInt2);
  
  void shuffle(Random paramRandom);
  
  TFloatList subList(int paramInt1, int paramInt2);
  
  float[] toArray();
  
  float[] toArray(int paramInt1, int paramInt2);
  
  float[] toArray(float[] paramArrayOffloat);
  
  float[] toArray(float[] paramArrayOffloat, int paramInt1, int paramInt2);
  
  float[] toArray(float[] paramArrayOffloat, int paramInt1, int paramInt2, int paramInt3);
  
  boolean forEach(TFloatProcedure paramTFloatProcedure);
  
  boolean forEachDescending(TFloatProcedure paramTFloatProcedure);
  
  void sort();
  
  void sort(int paramInt1, int paramInt2);
  
  void fill(float paramFloat);
  
  void fill(int paramInt1, int paramInt2, float paramFloat);
  
  int binarySearch(float paramFloat);
  
  int binarySearch(float paramFloat, int paramInt1, int paramInt2);
  
  int indexOf(float paramFloat);
  
  int indexOf(int paramInt, float paramFloat);
  
  int lastIndexOf(float paramFloat);
  
  int lastIndexOf(int paramInt, float paramFloat);
  
  boolean contains(float paramFloat);
  
  TFloatList grep(TFloatProcedure paramTFloatProcedure);
  
  TFloatList inverseGrep(TFloatProcedure paramTFloatProcedure);
  
  float max();
  
  float min();
  
  float sum();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\list\TFloatList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */