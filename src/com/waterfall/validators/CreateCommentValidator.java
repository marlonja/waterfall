package com.waterfall.validators;

import javax.ejb.Stateless;

import com.waterfall.models.CommentModel;

@Stateless
public class CreateCommentValidator {

	public boolean validateComment(CommentModel commentModel) {
		if(validateCommentContent(commentModel.getContent())){
			return true;
		}else{
			return false;
		}
		
	}
	
	public boolean validateCommentContent(String content){
		if(content.trim().equals("")||content.length() > 200){
			return false;
		}else{
			return true;
		}
	}

	
}
