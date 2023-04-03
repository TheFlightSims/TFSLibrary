package org.hsqldb.persist;

import org.hsqldb.error.Error;
import org.hsqldb.lib.DoubleIntIndex;
import org.hsqldb.lib.IntKeyHashMap;
import org.hsqldb.lib.Iterator;

public class DataSpaceManagerBlocks implements DataSpaceManager {
  DataFileCache cache;
  
  TableSpaceManagerBlocks defaultSpaceManager;
  
  TableSpaceManagerBlocks directorySpaceManager;
  
  IntKeyHashMap spaceManagerList;
  
  BlockObjectStore rootStore;
  
  BlockObjectStore directoryStore;
  
  BlockObjectStore bitMapStore;
  
  IntArrayCachedObject rootBlock;
  
  DirectoryBlockCachedObject firstDirectory;
  
  int spaceIdSequence = 8;
  
  int blockSize = 2048;
  
  int bitmapIntSize = 2048;
  
  int fileBlockItemCount = this.bitmapIntSize * 32;
  
  int fileBlockSize;
  
  int dataFileScale;
  
  int freeItemCacheSize = 2048;
  
  BlockAccessor ba;
  
  DataSpaceManagerBlocks() {}
  
  public DataSpaceManagerBlocks(DataFileCache paramDataFileCache) {
    this.cache = paramDataFileCache;
    this.dataFileScale = this.cache.getDataFileScale();
    this.fileBlockSize = this.fileBlockItemCount * this.dataFileScale;
    this.ba = new BlockAccessor();
    this.spaceManagerList = new IntKeyHashMap();
    this.directorySpaceManager = new TableSpaceManagerBlocks(this, 1, this.fileBlockSize, 16, this.dataFileScale);
    this.defaultSpaceManager = new TableSpaceManagerBlocks(this, 7, this.fileBlockSize, this.freeItemCacheSize, this.dataFileScale);
    this.spaceManagerList.put(1, this.directorySpaceManager);
    this.spaceManagerList.put(7, this.defaultSpaceManager);
    this.rootStore = new BlockObjectStore(this.cache, this.directorySpaceManager, IntArrayCachedObject.class, 4 * this.blockSize, this.blockSize);
    this.directoryStore = new BlockObjectStore(this.cache, this.directorySpaceManager, DirectoryBlockCachedObject.class, 12 * this.blockSize, this.blockSize);
    this.bitMapStore = new BlockObjectStore(this.cache, this.directorySpaceManager, BitMapCachedObject.class, 4 * this.bitmapIntSize, this.bitmapIntSize);
    if (this.cache.spaceManagerPosition == 0L) {
      initNewSpaceDirectory();
      this.cache.spaceManagerPosition = this.rootBlock.getPos() * this.dataFileScale;
      this.cache.setFileModified();
    } else {
      long l = this.cache.spaceManagerPosition / this.dataFileScale;
      this.rootBlock = (IntArrayCachedObject)this.rootStore.get(l, true);
      if (getBlockIndexLimit() < 2)
        throw Error.error(452); 
      this.spaceIdSequence = getMaxSpaceId() + 1;
      initialiseTableSpace(this.directorySpaceManager);
      initialiseTableSpace(this.defaultSpaceManager);
    } 
    this.firstDirectory = getDirectory(0, true);
  }
  
  private void initNewSpaceDirectory() {
    long l1 = this.cache.getFileFreePos();
    long l2 = l1 / this.fileBlockSize + 1L;
    long l3 = this.cache.enlargeFileSpace(l2 * this.fileBlockSize - l1);
    this.defaultSpaceManager.initialiseFileBlock(null, l3, this.cache.getFileFreePos());
    long l4 = l2;
    long l5 = calculateDirectorySpaceBlocks(l2);
    l3 = this.cache.enlargeFileSpace(l5 * this.fileBlockSize);
    this.directorySpaceManager.initialiseFileBlock(null, l3, this.cache.getFileFreePos());
    IntArrayCachedObject intArrayCachedObject = new IntArrayCachedObject(this.blockSize);
    this.rootStore.add(null, intArrayCachedObject, false);
    this.rootBlock = (IntArrayCachedObject)this.rootStore.get(intArrayCachedObject.getPos(), true);
    createFileBlocksInDirectory((int)l4, (int)l5, 1);
    createFileBlocksInDirectory(0, (int)l4, 7);
    long l6 = getBlockIndexLimit();
    if (l6 * this.fileBlockSize != this.cache.getFileFreePos())
      throw Error.error(452); 
  }
  
  private long calculateDirectorySpaceBlocks(long paramLong) {
    long l = (4 * this.blockSize);
    l += 12L * (paramLong + this.blockSize);
    l += 4L * this.bitmapIntSize * (paramLong + this.blockSize);
    return l / this.fileBlockSize + 1L;
  }
  
  public long getFileBlocks(int paramInt1, int paramInt2) {
    this.cache.writeLock.lock();
    try {
      long l = getExistingBlockIndex(paramInt1, paramInt2);
      if (l > 0L)
        return l * this.fileBlockSize; 
      return getNewFileBlocks(paramInt1, paramInt2);
    } finally {
      this.cache.writeLock.unlock();
    } 
  }
  
  private long getNewFileBlocks(int paramInt1, int paramInt2) {
    long l = 4L * this.bitmapIntSize * paramInt2;
    if (!this.directorySpaceManager.hasFileRoom(l)) {
      long l1 = getNewFileBlocksNoCheck(1, 1);
      this.directorySpaceManager.addFileBlock(l1, l1 + this.fileBlockSize);
      long l2 = getBlockIndexLimit();
      if (l2 * this.fileBlockSize != this.cache.getFileFreePos())
        throw Error.error(452); 
    } 
    return getNewFileBlocksNoCheck(paramInt1, paramInt2);
  }
  
  private long getNewFileBlocksNoCheck(int paramInt1, int paramInt2) {
    long l1 = getBlockIndexLimit();
    if (l1 * this.fileBlockSize != this.cache.getFileFreePos())
      throw Error.error(452); 
    long l2 = this.cache.enlargeFileSpace(paramInt2 * this.fileBlockSize);
    createFileBlocksInDirectory((int)l1, paramInt2, paramInt1);
    return l2;
  }
  
  private void createFileBlocksInDirectory(int paramInt1, int paramInt2, int paramInt3) {
    for (byte b = 0; b < paramInt2; b++)
      createFileBlockInDirectory(paramInt1 + b, paramInt3); 
  }
  
  private void createFileBlockInDirectory(int paramInt1, int paramInt2) {
    DirectoryBlockCachedObject directoryBlockCachedObject = getOrCreateDirectory(paramInt1);
    int i = paramInt1 % this.blockSize;
    BitMapCachedObject bitMapCachedObject = (BitMapCachedObject)this.bitMapStore.getNewInstance(this.bitmapIntSize);
    this.bitMapStore.add(null, bitMapCachedObject, false);
    int j = (int)(bitMapCachedObject.getPos() * this.dataFileScale / 4096L);
    updateDirectory(directoryBlockCachedObject, i, paramInt2, j);
  }
  
  private DirectoryBlockCachedObject getDirectory(int paramInt, boolean paramBoolean) {
    int i = paramInt / this.blockSize;
    long l = this.rootBlock.getIntArray()[i];
    if (l == 0L)
      return null; 
    l *= (4096 / this.dataFileScale);
    return (DirectoryBlockCachedObject)this.directoryStore.get(l, paramBoolean);
  }
  
  private DirectoryBlockCachedObject getOrCreateDirectory(int paramInt) {
    DirectoryBlockCachedObject directoryBlockCachedObject;
    int i = paramInt / this.blockSize;
    long l = this.rootBlock.getIntArray()[i];
    if (l == 0L) {
      directoryBlockCachedObject = createDirectory(paramInt);
    } else {
      l *= (4096 / this.dataFileScale);
      directoryBlockCachedObject = (DirectoryBlockCachedObject)this.directoryStore.get(l, false);
    } 
    return directoryBlockCachedObject;
  }
  
  private DirectoryBlockCachedObject createDirectory(int paramInt) {
    DirectoryBlockCachedObject directoryBlockCachedObject = new DirectoryBlockCachedObject(this.blockSize);
    this.directoryStore.add(null, directoryBlockCachedObject, false);
    int i = paramInt / this.blockSize;
    int j = (int)(directoryBlockCachedObject.getPos() * this.dataFileScale / 4096L);
    this.rootBlock.getIntArray()[i] = j;
    this.rootBlock.hasChanged = true;
    return directoryBlockCachedObject;
  }
  
  private void updateDirectory(DirectoryBlockCachedObject paramDirectoryBlockCachedObject, int paramInt1, int paramInt2, int paramInt3) {
    paramDirectoryBlockCachedObject = (DirectoryBlockCachedObject)this.directoryStore.get(paramDirectoryBlockCachedObject.getPos(), true);
    paramDirectoryBlockCachedObject.getTableIdArray()[paramInt1] = paramInt2;
    paramDirectoryBlockCachedObject.getBitmapAddressArray()[paramInt1] = paramInt3;
    paramDirectoryBlockCachedObject.hasChanged = true;
    paramDirectoryBlockCachedObject.keepInMemory(false);
  }
  
  private int getBlockIndexLimit() {
    int[] arrayOfInt1 = this.rootBlock.getIntArray();
    byte b1;
    for (b1 = 0; b1 < arrayOfInt1.length && arrayOfInt1[b1] != 0; b1++);
    if (b1 == 0)
      return 0; 
    long l = arrayOfInt1[--b1];
    l *= (4096 / this.dataFileScale);
    DirectoryBlockCachedObject directoryBlockCachedObject = (DirectoryBlockCachedObject)this.directoryStore.get(l, false);
    int[] arrayOfInt2 = directoryBlockCachedObject.getBitmapAddressArray();
    byte b2;
    for (b2 = 0; b2 < arrayOfInt2.length && arrayOfInt2[b2] != 0; b2++);
    return b1 * this.blockSize + b2;
  }
  
  private int getMaxSpaceId() {
    int i = 7;
    this.ba.initialise(false);
    while (true) {
      boolean bool = this.ba.nextBlock();
      if (!bool) {
        this.ba.reset();
        return i;
      } 
      int j = this.ba.getTableId();
      if (j > i)
        i = j; 
    } 
  }
  
  private int getExistingBlockIndex(int paramInt1, int paramInt2) {
    this.ba.initialise(false);
    int i = -1;
    int j = -1;
    while (true) {
      boolean bool = this.ba.nextBlockForTable(0);
      if (!bool) {
        i = -1;
        break;
      } 
      if (paramInt2 == 1) {
        i = this.ba.currentBlockIndex;
        break;
      } 
      if (i == -1) {
        i = this.ba.currentBlockIndex;
        j = i;
        continue;
      } 
      if (this.ba.currentBlockIndex - i + 1 == paramInt2)
        break; 
      if (this.ba.currentBlockIndex == j + 1) {
        j = this.ba.currentBlockIndex;
        continue;
      } 
      j = -1;
      i = -1;
    } 
    this.ba.reset();
    if (i > 0)
      setDirectoryBlocksAsTable(paramInt1, i, paramInt2); 
    return i;
  }
  
  private void setDirectoryBlocksAsTable(int paramInt1, int paramInt2, int paramInt3) {
    int i = -1;
    DirectoryBlockCachedObject directoryBlockCachedObject = null;
    for (int j = paramInt2; j < paramInt2 + paramInt3; j++) {
      if (i != j / this.blockSize) {
        if (directoryBlockCachedObject != null)
          directoryBlockCachedObject.setInMemory(false); 
        directoryBlockCachedObject = getDirectory(j, true);
        i = j / this.blockSize;
      } 
      int k = j % this.blockSize;
      directoryBlockCachedObject.getTableIdArray()[k] = paramInt1;
    } 
    directoryBlockCachedObject.setInMemory(false);
  }
  
  public TableSpaceManager getDefaultTableSpace() {
    return this.defaultSpaceManager;
  }
  
  public TableSpaceManager getTableSpace(int paramInt) {
    if (paramInt == 7)
      return this.defaultSpaceManager; 
    if (paramInt >= this.spaceIdSequence)
      this.spaceIdSequence = paramInt + 1; 
    TableSpaceManagerBlocks tableSpaceManagerBlocks = (TableSpaceManagerBlocks)this.spaceManagerList.get(paramInt);
    if (tableSpaceManagerBlocks == null) {
      tableSpaceManagerBlocks = new TableSpaceManagerBlocks(this, paramInt, this.fileBlockSize, this.cache.database.logger.propMaxFreeBlocks, this.dataFileScale);
      initialiseTableSpace(tableSpaceManagerBlocks);
      this.spaceManagerList.put(paramInt, tableSpaceManagerBlocks);
    } 
    return tableSpaceManagerBlocks;
  }
  
  public int getNewTableSpaceID() {
    this.cache.writeLock.lock();
    try {
      return this.spaceIdSequence++;
    } finally {
      this.cache.writeLock.unlock();
    } 
  }
  
  public void freeTableSpace(int paramInt) {
    if (paramInt == 7 || paramInt == 1)
      return; 
    TableSpaceManager tableSpaceManager = (TableSpaceManager)this.spaceManagerList.get(paramInt);
    if (tableSpaceManager != null) {
      tableSpaceManager.reset();
      this.spaceManagerList.remove(paramInt);
    } 
    this.cache.writeLock.lock();
    try {
      this.ba.initialise(true);
      while (true) {
        boolean bool = this.ba.nextBlockForTable(paramInt);
        if (!bool) {
          this.ba.reset();
          return;
        } 
        this.cache.releaseRange((this.ba.currentBlockIndex * this.fileBlockItemCount), ((this.ba.currentBlockIndex + 1) * this.fileBlockItemCount));
        this.ba.setTableId(0);
        this.ba.setFreeSpaceValue(0);
        this.ba.setFreeBlockValue(0);
        this.ba.currentDir.hasChanged = true;
        this.ba.currentBitMap.bitMap.reset();
        this.ba.currentBitMap.hasChanged = true;
      } 
    } finally {
      this.cache.writeLock.unlock();
    } 
  }
  
  public void freeTableSpace(DoubleIntIndex paramDoubleIntIndex, long paramLong1, long paramLong2, boolean paramBoolean) {
    DataSpaceManagerSimple.compactLookup(paramDoubleIntIndex, this.dataFileScale);
    if (!paramBoolean) {
      int i = paramDoubleIntIndex.size() - paramDoubleIntIndex.capacity() / 2;
      if (i < 0) {
        paramDoubleIntIndex.setValuesSearchTarget();
        paramDoubleIntIndex.sort();
        return;
      } 
    } 
    this.cache.writeLock.lock();
    try {
      this.ba.initialise(true);
      int[] arrayOfInt1 = paramDoubleIntIndex.getKeys();
      int[] arrayOfInt2 = paramDoubleIntIndex.getValues();
      for (byte b = 0; b < paramDoubleIntIndex.size(); b++) {
        int k = arrayOfInt1[b];
        int m = arrayOfInt2[b];
        int n = m / this.dataFileScale;
        freeTableSpacePart(k, n);
      } 
      long l = paramLong1 / this.dataFileScale;
      int i = (int)((paramLong2 - paramLong1) / this.dataFileScale);
      freeTableSpacePart(l, i);
      int j = (int)((l + i) / this.fileBlockItemCount);
      this.ba.endBlockUpdate(j + 1);
      this.ba.reset();
    } finally {
      this.cache.writeLock.unlock();
    } 
    paramDoubleIntIndex.clear();
    paramDoubleIntIndex.setValuesSearchTarget();
  }
  
  private void freeTableSpacePart(long paramLong, int paramInt) {
    while (paramInt > 0) {
      int i = (int)(paramLong / this.fileBlockItemCount);
      int j = (int)(paramLong % this.fileBlockItemCount);
      int k = this.fileBlockItemCount - j;
      if (k > paramInt)
        k = paramInt; 
      this.ba.endBlockUpdate(i);
      this.ba.moveToBlock(i);
      this.ba.currentBitMap.bitMap.setRange(j, k);
      this.ba.currentBitMap.hasChanged = true;
      paramInt -= k;
      paramLong += k;
    } 
  }
  
  int findTableSpace(long paramLong) {
    int i = (int)(paramLong / this.fileBlockItemCount);
    this.ba.initialise(false);
    boolean bool = this.ba.moveToBlock(i);
    if (!bool) {
      this.ba.reset();
      return 7;
    } 
    int j = this.ba.getTableId();
    this.ba.reset();
    return j;
  }
  
  public long getLostBlocksSize() {
    long l = 0L;
    this.ba.initialise(false);
    while (true) {
      boolean bool = this.ba.nextBlock();
      if (!bool) {
        this.ba.reset();
        return l;
      } 
      l += (this.ba.getFreeSpaceValue() * this.dataFileScale);
      if (this.ba.getTableId() == 0)
        l += this.fileBlockSize; 
    } 
  }
  
  public int getFileBlockSize() {
    return this.fileBlockSize;
  }
  
  public boolean isModified() {
    return true;
  }
  
  public void initialiseSpaces() {
    Iterator iterator = this.spaceManagerList.values().iterator();
    while (iterator.hasNext()) {
      TableSpaceManagerBlocks tableSpaceManagerBlocks = (TableSpaceManagerBlocks)iterator.next();
      initialiseTableSpace(tableSpaceManagerBlocks);
    } 
  }
  
  public void reset() {
    Iterator iterator = this.spaceManagerList.values().iterator();
    while (iterator.hasNext()) {
      TableSpaceManagerBlocks tableSpaceManagerBlocks = (TableSpaceManagerBlocks)iterator.next();
      tableSpaceManagerBlocks.reset();
    } 
  }
  
  public boolean isMultiSpace() {
    return true;
  }
  
  private void initialiseTableSpace(TableSpaceManagerBlocks paramTableSpaceManagerBlocks) {
    int i = paramTableSpaceManagerBlocks.getSpaceID();
    char c = Character.MIN_VALUE;
    int j = -1;
    this.ba.initialise(false);
    while (this.ba.nextBlockForTable(i)) {
      char c3 = this.ba.getFreeBlockValue();
      if (c3 > c) {
        j = this.ba.currentBlockIndex;
        c = c3;
      } 
    } 
    this.ba.reset();
    if (j < 0)
      return; 
    this.ba.initialise(true);
    this.ba.moveToBlock(j);
    char c1 = this.ba.getFreeBlockValue();
    long l = j * this.fileBlockSize;
    paramTableSpaceManagerBlocks.initialiseFileBlock(null, l + (this.fileBlockSize - c1 * this.dataFileScale), l + this.fileBlockSize);
    char c2 = this.ba.getFreeSpaceValue();
    int k = c2 - c1;
    this.ba.setFreeSpaceValue((char)k);
    this.ba.setFreeBlockValue(0);
    this.ba.currentDir.hasChanged = true;
    this.ba.currentBitMap.bitMap.unsetRange(this.fileBlockItemCount - c1, c1);
    this.ba.currentBitMap.hasChanged = true;
    this.ba.reset();
  }
  
  private class BlockAccessor {
    boolean currentKeep;
    
    int currentBlockIndex = -1;
    
    int currentDirIndex = -1;
    
    int currentBlockOffset = -1;
    
    DirectoryBlockCachedObject currentDir = null;
    
    BitMapCachedObject currentBitMap = null;
    
    private BlockAccessor() {}
    
    void initialise(boolean param1Boolean) {
      this.currentKeep = param1Boolean;
    }
    
    boolean nextBlock() {
      return moveToBlock(this.currentBlockIndex + 1);
    }
    
    boolean nextBlockForTable(int param1Int) {
      while (true) {
        boolean bool = moveToBlock(this.currentBlockIndex + 1);
        if (!bool)
          return false; 
        if (getTableId() == param1Int)
          return true; 
      } 
    }
    
    boolean moveToBlock(int param1Int) {
      if (this.currentBlockIndex != param1Int) {
        if (this.currentDirIndex != param1Int / DataSpaceManagerBlocks.this.blockSize) {
          reset();
          this.currentDirIndex = param1Int / DataSpaceManagerBlocks.this.blockSize;
          this.currentDir = DataSpaceManagerBlocks.this.getDirectory(param1Int, this.currentKeep);
        } 
        this.currentBlockIndex = param1Int;
        this.currentBlockOffset = param1Int % DataSpaceManagerBlocks.this.blockSize;
        if (this.currentBitMap != null) {
          this.currentBitMap.keepInMemory(false);
          this.currentBitMap = null;
        } 
        if (this.currentDir == null)
          return false; 
        long l = this.currentDir.getBitmapAddressArray()[this.currentBlockOffset];
        if (l == 0L)
          return false; 
        if (this.currentKeep) {
          l *= (4096 / DataSpaceManagerBlocks.this.dataFileScale);
          this.currentBitMap = (BitMapCachedObject)DataSpaceManagerBlocks.this.bitMapStore.get(l, this.currentKeep);
        } 
      } 
      return true;
    }
    
    void reset() {
      if (this.currentDir != null && this.currentKeep)
        this.currentDir.keepInMemory(false); 
      if (this.currentBitMap != null && this.currentKeep)
        this.currentBitMap.keepInMemory(false); 
      this.currentBlockIndex = -1;
      this.currentDirIndex = -1;
      this.currentBlockOffset = -1;
      this.currentDir = null;
      this.currentBitMap = null;
    }
    
    boolean endBlockUpdate(int param1Int) {
      if (this.currentBlockIndex != -1 && this.currentBlockIndex != param1Int) {
        int i = this.currentBitMap.bitMap.countSetBits();
        int j = this.currentBitMap.bitMap.countSetBitsEnd();
        setFreeSpaceValue(i);
        setFreeBlockValue(j);
        this.currentDir.hasChanged = true;
        if (i == DataSpaceManagerBlocks.this.fileBlockItemCount) {
          setTableId(0);
          setFreeSpaceValue(0);
          setFreeBlockValue(0);
          this.currentBitMap.bitMap.reset();
        } 
        return true;
      } 
      return false;
    }
    
    int getTableId() {
      return DataSpaceManagerBlocks.this.ba.currentDir.getTableIdArray()[DataSpaceManagerBlocks.this.ba.currentBlockOffset];
    }
    
    void setTableId(int param1Int) {
      DataSpaceManagerBlocks.this.ba.currentDir.getTableIdArray()[DataSpaceManagerBlocks.this.ba.currentBlockOffset] = param1Int;
    }
    
    void setFreeSpaceValue(int param1Int) {
      DataSpaceManagerBlocks.this.ba.currentDir.getFreeSpaceArray()[DataSpaceManagerBlocks.this.ba.currentBlockOffset] = (char)param1Int;
    }
    
    char getFreeSpaceValue() {
      return DataSpaceManagerBlocks.this.ba.currentDir.getFreeSpaceArray()[DataSpaceManagerBlocks.this.ba.currentBlockOffset];
    }
    
    void setFreeBlockValue(int param1Int) {
      DataSpaceManagerBlocks.this.ba.currentDir.getFreeBlockArray()[DataSpaceManagerBlocks.this.ba.currentBlockOffset] = (char)param1Int;
    }
    
    char getFreeBlockValue() {
      return DataSpaceManagerBlocks.this.ba.currentDir.getFreeBlockArray()[DataSpaceManagerBlocks.this.ba.currentBlockOffset];
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\DataSpaceManagerBlocks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */