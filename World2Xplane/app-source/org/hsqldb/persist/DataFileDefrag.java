package org.hsqldb.persist;

import org.hsqldb.Database;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.DoubleIntIndex;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.LongLookup;
import org.hsqldb.lib.StopWatch;
import org.hsqldb.lib.StringUtil;

final class DataFileDefrag {
  DataFileCache dataFileOut;
  
  StopWatch stopw = new StopWatch();
  
  String dataFileName;
  
  long[][] rootsList;
  
  Database database;
  
  DataFileCache dataCache;
  
  int scale;
  
  DoubleIntIndex pointerLookup;
  
  DataFileDefrag(Database paramDatabase, DataFileCache paramDataFileCache, String paramString) {
    this.database = paramDatabase;
    this.dataCache = paramDataFileCache;
    this.scale = paramDataFileCache.getDataFileScale();
    this.dataFileName = paramString;
  }
  
  void process() {
    Throwable throwable = null;
    this.database.logger.logDetailEvent("Defrag process begins");
    HsqlArrayList hsqlArrayList = this.database.schemaManager.getAllTables(true);
    this.rootsList = new long[hsqlArrayList.size()][];
    long l = 0L;
    byte b = 0;
    int i = hsqlArrayList.size();
    while (b < i) {
      Table table = (Table)hsqlArrayList.get(b);
      if (table.getTableType() == 5) {
        PersistentStore persistentStore = this.database.persistentStoreCollection.getStore(table);
        long l1 = persistentStore.elementCount();
        if (l1 > l)
          l = l1; 
      } 
      b++;
    } 
    if (l > 2147483647L)
      throw Error.error(3426); 
    try {
      this.pointerLookup = new DoubleIntIndex((int)l, false);
      this.dataFileOut = new DataFileCache(this.database, this.dataFileName, true);
      this.pointerLookup.setKeysSearchTarget();
      b = 0;
      i = hsqlArrayList.size();
      while (b < i) {
        Table table = (Table)hsqlArrayList.get(b);
        if (table.getTableType() == 5) {
          long[] arrayOfLong = writeTableToDataFile(table);
          this.rootsList[b] = arrayOfLong;
        } else {
          this.rootsList[b] = null;
        } 
        this.database.logger.logDetailEvent("table complete " + (table.getName()).name);
        b++;
      } 
      this.dataFileOut.fileModified = true;
      this.dataFileOut.close();
      this.dataFileOut = null;
      b = 0;
      i = this.rootsList.length;
      while (b < i) {
        long[] arrayOfLong = this.rootsList[b];
        if (arrayOfLong != null)
          this.database.logger.logDetailEvent("roots: " + StringUtil.getList(arrayOfLong, ",", "")); 
        b++;
      } 
    } catch (OutOfMemoryError outOfMemoryError) {
      throwable = outOfMemoryError;
      throw Error.error(460, outOfMemoryError);
    } catch (Throwable throwable1) {
      throwable = throwable1;
      throw Error.error(458, throwable1);
    } finally {
      try {
        if (this.dataFileOut != null)
          this.dataFileOut.release(); 
      } catch (Throwable throwable1) {}
      if (throwable instanceof OutOfMemoryError)
        this.database.logger.logInfoEvent("defrag failed - out of memory - required: " + (l * 8L)); 
      if (throwable == null) {
        this.database.logger.logDetailEvent("Defrag transfer complete: " + this.stopw.elapsedTime());
      } else {
        this.database.logger.logSevereEvent("defrag failed ", throwable);
        this.database.logger.getFileAccess().removeElement(this.dataFileName + ".new");
      } 
    } 
  }
  
  long[] writeTableToDataFile(Table paramTable) {
    PersistentStore persistentStore = paramTable.database.persistentStoreCollection.getStore(paramTable);
    TableSpaceManager tableSpaceManager = this.dataFileOut.spaceManager.getTableSpace(paramTable.getSpaceID());
    long[] arrayOfLong = paramTable.getIndexRootsArray();
    this.pointerLookup.clear();
    this.database.logger.logDetailEvent("lookup begins " + (paramTable.getName()).name + " " + this.stopw.elapsedTime());
    RowStoreAVLDisk.moveDataToSpace(persistentStore, this.dataFileOut, tableSpaceManager, (LongLookup)this.pointerLookup);
    for (byte b = 0; b < paramTable.getIndexCount(); b++) {
      if (arrayOfLong[b] != -1L) {
        long l1 = this.pointerLookup.lookup(arrayOfLong[b], -1L);
        if (l1 == -1L)
          throw Error.error(466); 
        arrayOfLong[b] = l1;
      } 
    } 
    long l = arrayOfLong[paramTable.getIndexCount() * 2];
    if (l != this.pointerLookup.size())
      this.database.logger.logSevereEvent("discrepency in row count " + (paramTable.getName()).name + " " + l + " " + this.pointerLookup.size(), null); 
    arrayOfLong[paramTable.getIndexCount()] = 0L;
    arrayOfLong[paramTable.getIndexCount() * 2] = this.pointerLookup.size();
    this.database.logger.logDetailEvent("table written " + (paramTable.getName()).name);
    return arrayOfLong;
  }
  
  public long[][] getIndexRoots() {
    return this.rootsList;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\DataFileDefrag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */