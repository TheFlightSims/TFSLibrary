package org.hsqldb.lib.tar;

public enum TarHeaderField {
  name(0, 100),
  mode(100, 107),
  uid(108, 115),
  gid(116, 123),
  size(124, 135),
  mtime(136, 147),
  checksum(148, 156),
  typeflag(156, 157),
  magic(257, 263),
  uname(265, 296),
  gname(297, 328),
  prefix(345, 399);
  
  private int start;
  
  private int stop;
  
  TarHeaderField(int paramInt1, int paramInt2) {
    this.start = paramInt1;
    this.stop = paramInt2;
  }
  
  public int getStart() {
    return this.start;
  }
  
  public int getStop() {
    return this.stop;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\tar\TarHeaderField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */