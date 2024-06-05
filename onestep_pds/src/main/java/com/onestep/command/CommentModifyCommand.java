package com.onestep.command;

import com.onestep.dto.FreeCommentVO;



public class CommentModifyCommand extends CommentRegistCommand{
	private int commentid;
	
	
	
	public int getCommentid() {
		return commentid;
	}



	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}



   @Override
   public FreeCommentVO toFreeCommentVO() {
      FreeCommentVO comment = super.toFreeCommentVO();
      comment.setCommentid(this.commentid);
      
      return comment;
	   }
		
}



