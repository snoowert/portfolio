package com.onestep.command;

import com.onestep.dto.FreeVO;

public class FreeModifyCommand extends FreeRegistCommand{
	
	
	private int freeid;
	
	

	public int getFreeid() {
		return freeid;
	}
	public void setFreeid(int freeid) {
		this.freeid = freeid;
	}

	   @Override
	   public FreeVO toFreeVO() {
	      FreeVO free = super.toFreeVO();
	      free.setFreeid(this.freeid);
	      
	      return free;
	   }
		
	
}
