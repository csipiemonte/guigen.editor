package it.csi.mddtools.guigen.editor.wizards.crud;

import it.csi.mddtools.guigen.Field;

public class FieldContext {
	String path;
	Field field;
	
	public FieldContext() {
		
	}

	public FieldContext(String path, Field field) {
		
		this.path = path;
		this.field = field;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	
	@Override
	public boolean equals(Object obj) {
		FieldContext fieldContext = null;
		boolean res = false;
		if(obj instanceof FieldContext) {
			 fieldContext = (FieldContext)obj;
			 res = this.path.equals(fieldContext.path) && (this.field == fieldContext.field); 
		}
		return res;
		
	}
	

}
