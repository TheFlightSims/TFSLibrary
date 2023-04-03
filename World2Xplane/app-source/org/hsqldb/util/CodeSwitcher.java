package org.hsqldb.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Vector;

public class CodeSwitcher {
  private static final String ls = System.getProperty("line.separator", "\n");
  
  private Vector vList = new Vector();
  
  private Vector vSwitchOn = new Vector();
  
  private Vector vSwitchOff = new Vector();
  
  private Vector vSwitches = new Vector();
  
  private static final int MAX_LINELENGTH = 82;
  
  public static void main(String[] paramArrayOfString) {
    CodeSwitcher codeSwitcher = new CodeSwitcher();
    if (paramArrayOfString.length == 0) {
      showUsage();
      return;
    } 
    File file1 = null;
    File file2 = null;
    for (byte b = 0; b < paramArrayOfString.length; b++) {
      String str = paramArrayOfString[b];
      if (str.startsWith("+")) {
        codeSwitcher.vSwitchOn.addElement(str.substring(1));
      } else if (str.startsWith("--basedir=")) {
        file2 = new File(str.substring("--basedir=".length()));
      } else if (str.startsWith("--pathlist=")) {
        file1 = new File(str.substring("--pathlist=".length()));
      } else if (str.startsWith("-")) {
        codeSwitcher.vSwitchOff.addElement(str.substring(1));
      } else {
        codeSwitcher.addDir(str);
      } 
    } 
    if (file2 != null)
      if (file1 == null) {
        System.err.println("--basedir= setting ignored, since only used for list files");
      } else if (!file2.isDirectory()) {
        System.err.println("Skipping listfile since basedir '" + file2.getAbsolutePath() + "' is not a directory");
        file1 = null;
      }  
    if (file1 != null)
      try {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file1));
        String str;
        while ((str = bufferedReader.readLine()) != null) {
          int i = str.indexOf('#');
          String str1 = ((i > -1) ? str.substring(0, i) : str).trim();
          if (str1.length() < 1)
            continue; 
          File file = (file2 == null) ? new File(str1) : new File(file2, str1);
          if (file.isFile()) {
            codeSwitcher.addDir(file);
            continue;
          } 
          System.err.println("Skipping non-file '" + str1.trim() + "'");
        } 
      } catch (Exception exception) {
        System.err.println("Failed to read pathlist file '" + file1.getAbsolutePath() + "'");
      }  
    if (codeSwitcher.size() < 1) {
      printError("No path specified, or no specified paths qualify");
      showUsage();
    } 
    codeSwitcher.process();
    if (codeSwitcher.vSwitchOff.size() == 0 && codeSwitcher.vSwitchOn.size() == 0)
      codeSwitcher.printSwitches(); 
  }
  
  public int size() {
    return (this.vList == null) ? 0 : this.vList.size();
  }
  
  static void showUsage() {
    System.out.print("Usage: java CodeSwitcher paths|{--pathlist=listfile} [{+|-}label...] [+][-]\nIf no labels are specified then all used\nlabels in the source code are shown.\nUse +MODE to switch on the things labeld MODE\nUse -MODE to switch off the things labeld MODE\nPath: Any number of path or files may be\nspecified. Use . for the current directory\n(including sub-directories).\nExample: java CodeSwitcher +JAVA2 .\nThis example switches on code labeled JAVA2\nin all *.java files in the current directory\nand all subdirectories.\n");
  }
  
  void process() {
    int i = this.vList.size();
    for (byte b = 0; b < i; b++) {
      System.out.print(".");
      String str = this.vList.elementAt(b);
      if (!processFile(str))
        System.out.println("in file " + str + " !"); 
    } 
    System.out.println("");
  }
  
  void printSwitches() {
    System.out.println("Used labels:");
    for (byte b = 0; b < this.vSwitches.size(); b++)
      System.out.println(this.vSwitches.elementAt(b)); 
  }
  
  void addDir(String paramString) {
    addDir(new File(paramString));
  }
  
  void addDir(File paramFile) {
    if (paramFile.isFile() && paramFile.getName().endsWith(".java")) {
      this.vList.addElement(paramFile.getPath());
    } else if (paramFile.isDirectory()) {
      File[] arrayOfFile = paramFile.listFiles();
      for (byte b = 0; b < arrayOfFile.length; b++)
        addDir(arrayOfFile[b]); 
    } 
  }
  
  boolean processFile(String paramString) {
    File file1 = new File(paramString);
    File file2 = new File(paramString + ".new");
    byte b = 0;
    boolean bool1 = false;
    boolean bool2 = false;
    try {
      Vector<String> vector = getFileLines(file1);
      Vector<E> vector1 = new Vector(vector.size());
      byte b1;
      for (b1 = 0; b1 < vector.size(); b1++)
        vector1.addElement(vector.elementAt(b1)); 
      for (b1 = 0; b1 < vector.size(); b1++) {
        String str = vector.elementAt(b1);
        if (str == null)
          break; 
        if (bool2 && (str.equals("/*") || str.equals("*/"))) {
          vector.removeElementAt(b1--);
        } else if (str.startsWith("//#")) {
          if (str.startsWith("//#ifdef ")) {
            if (b) {
              printError("'#ifdef' not allowed inside '#ifdef'");
              return false;
            } 
            b = 1;
            String str1 = str.substring(9);
            if (this.vSwitchOn.indexOf(str1) != -1) {
              bool2 = true;
              bool1 = false;
            } else if (this.vSwitchOff.indexOf(str1) != -1) {
              bool2 = true;
              vector.insertElementAt("/*", ++b1);
              bool1 = true;
            } 
            if (this.vSwitches.indexOf(str1) == -1)
              this.vSwitches.addElement(str1); 
          } else if (str.startsWith("//#ifndef ")) {
            if (b) {
              printError("'#ifndef' not allowed inside '#ifdef'");
              return false;
            } 
            b = 1;
            String str1 = str.substring(10);
            if (this.vSwitchOff.indexOf(str1) != -1) {
              bool2 = true;
              bool1 = false;
            } else if (this.vSwitchOn.indexOf(str1) != -1) {
              bool2 = true;
              vector.insertElementAt("/*", ++b1);
              bool1 = true;
            } 
            if (this.vSwitches.indexOf(str1) == -1)
              this.vSwitches.addElement(str1); 
          } else if (str.startsWith("//#else")) {
            if (b != 1) {
              printError("'#else' without '#ifdef'");
              return false;
            } 
            b = 2;
            if (bool2)
              if (bool1) {
                if (vector.elementAt(b1 - 1).equals("")) {
                  vector.insertElementAt("*/", b1 - 1);
                  b1++;
                } else {
                  vector.insertElementAt("*/", b1++);
                } 
                bool1 = false;
              } else {
                vector.insertElementAt("/*", ++b1);
                bool1 = true;
              }  
          } else if (str.startsWith("//#endif")) {
            if (b == 0) {
              printError("'#endif' without '#ifdef'");
              return false;
            } 
            b = 0;
            if (bool2 && bool1)
              if (vector.elementAt(b1 - 1).equals("")) {
                vector.insertElementAt("*/", b1 - 1);
                b1++;
              } else {
                vector.insertElementAt("*/", b1++);
              }  
            bool2 = false;
          } 
        } 
      } 
      if (b != 0) {
        printError("'#endif' missing");
        return false;
      } 
      b1 = 0;
      for (byte b2 = 0; b2 < vector.size(); b2++) {
        if (!vector1.elementAt(b2).equals(vector.elementAt(b2))) {
          b1 = 1;
          break;
        } 
      } 
      if (b1 == 0)
        return true; 
      writeFileLines(vector, file2);
      File file3 = new File(paramString + ".bak");
      file3.delete();
      file1.renameTo(file3);
      File file4 = new File(paramString);
      file2.renameTo(file4);
      file3.delete();
      return true;
    } catch (Exception exception) {
      printError(exception.toString());
      return false;
    } 
  }
  
  static Vector getFileLines(File paramFile) throws IOException {
    LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(paramFile));
    Vector<String> vector = new Vector();
    while (true) {
      String str = lineNumberReader.readLine();
      if (str == null) {
        lineNumberReader.close();
        return vector;
      } 
      vector.addElement(str);
    } 
  }
  
  static void writeFileLines(Vector<String> paramVector, File paramFile) throws IOException {
    FileWriter fileWriter = new FileWriter(paramFile);
    for (byte b = 0; b < paramVector.size(); b++) {
      fileWriter.write(paramVector.elementAt(b));
      fileWriter.write(ls);
    } 
    fileWriter.flush();
    fileWriter.close();
  }
  
  static void printError(String paramString) {
    System.out.println("");
    System.out.println("ERROR: " + paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqld\\util\CodeSwitcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */