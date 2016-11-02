package com.waterfall.validators;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.models.CommentModel;
import com.waterfall.utils.ValidationMessageService;

@Stateless
public class CreateCommentValidator {
	
	@EJB
	private ValidationMessageService validationMessageService;

	public boolean validateComment(CommentModel commentModel) {
		if(validateCommentContent(commentModel.getContent())){
			return true;
		}else{
			return false;
		}
		
	}
	
	public boolean validateCommentContent(String content){
		if(content.trim().equals("")){
			validationMessageService.errorMsg("Comment is empty");
			return false;
		}if(content.length() > 200){
			validationMessageService.errorMsg("Comment cannot exceed 200 characters");
			return false;
		}else{
			return true;
		}
	}

	
}
