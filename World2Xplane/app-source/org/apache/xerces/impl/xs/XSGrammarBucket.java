package org.apache.xerces.impl.xs;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class XSGrammarBucket {
  Hashtable fGrammarRegistry = new Hashtable();
  
  SchemaGrammar fNoNSGrammar = null;
  
  public SchemaGrammar getGrammar(String paramString) {
    return (paramString == null) ? this.fNoNSGrammar : (SchemaGrammar)this.fGrammarRegistry.get(paramString);
  }
  
  public void putGrammar(SchemaGrammar paramSchemaGrammar) {
    if (paramSchemaGrammar.getTargetNamespace() == null) {
      this.fNoNSGrammar = paramSchemaGrammar;
    } else {
      this.fGrammarRegistry.put(paramSchemaGrammar.getTargetNamespace(), paramSchemaGrammar);
    } 
  }
  
  public boolean putGrammar(SchemaGrammar paramSchemaGrammar, boolean paramBoolean) {
    SchemaGrammar schemaGrammar = getGrammar(paramSchemaGrammar.fTargetNamespace);
    if (schemaGrammar != null)
      return (schemaGrammar == paramSchemaGrammar); 
    if (!paramBoolean) {
      putGrammar(paramSchemaGrammar);
      return true;
    } 
    Vector vector = paramSchemaGrammar.getImportedGrammars();
    if (vector == null) {
      putGrammar(paramSchemaGrammar);
      return true;
    } 
    Vector vector1 = (Vector)vector.clone();
    for (byte b = 0; b < vector1.size(); b++) {
      SchemaGrammar schemaGrammar1 = vector1.elementAt(b);
      SchemaGrammar schemaGrammar2 = getGrammar(schemaGrammar1.fTargetNamespace);
      if (schemaGrammar2 == null) {
        Vector vector2 = schemaGrammar1.getImportedGrammars();
        if (vector2 != null)
          for (int j = vector2.size() - 1; j >= 0; j--) {
            schemaGrammar2 = vector2.elementAt(j);
            if (!vector1.contains(schemaGrammar2))
              vector1.addElement(schemaGrammar2); 
          }  
      } else if (schemaGrammar2 != schemaGrammar1) {
        return false;
      } 
    } 
    putGrammar(paramSchemaGrammar);
    for (int i = vector1.size() - 1; i >= 0; i--)
      putGrammar(vector1.elementAt(i)); 
    return true;
  }
  
  public SchemaGrammar[] getGrammars() {
    int i = this.fGrammarRegistry.size() + ((this.fNoNSGrammar == null) ? 0 : 1);
    SchemaGrammar[] arrayOfSchemaGrammar = new SchemaGrammar[i];
    Enumeration enumeration = this.fGrammarRegistry.elements();
    byte b = 0;
    while (enumeration.hasMoreElements())
      arrayOfSchemaGrammar[b++] = enumeration.nextElement(); 
    if (this.fNoNSGrammar != null)
      arrayOfSchemaGrammar[i - 1] = this.fNoNSGrammar; 
    return arrayOfSchemaGrammar;
  }
  
  public void reset() {
    this.fNoNSGrammar = null;
    this.fGrammarRegistry.clear();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\XSGrammarBucket.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */