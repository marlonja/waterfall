package com.waterfall.RESTResources;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJB;
import javax.enterprise.inject.New;
import javax.jws.WebService;

import org.eclipse.persistence.jpa.jpql.parser.ElseExpressionBNF;

import com.sun.xml.ws.security.trust.elements.RequestSecurityTokenResponse;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.hashing.pbkdf2.PBKDF2;
import com.waterfall.models.CommentModel;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;
import com.waterfall.serviceEJB.UserServiceEJB;

@WebService
public class UserSoapResource {

	@EJB
	private LocalUser userEjb;

	public UserModel createUser(String birthdateFromUser, String city, String country, String email, String firstName,
			String gender, String lastName, String pwFromUser, String username)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
		UserModel userModel = new UserModel();

		String[] birthdateInfo = birthdateFromUser.split(",");
		int birthyear = Integer.parseInt(birthdateInfo[0]);
		int birthMonth = Integer.parseInt(birthdateInfo[1]);
		int birthDay = Integer.parseInt(birthdateInfo[2]);
		Date birthdate = new Date(birthyear - 1900, birthMonth - 1, birthDay);

		userModel.setBirthdate(birthdate);
		userModel.setCity(city);
		userModel.setCountry(country);
		userModel.setEmail(email);
		userModel.setFirstName(firstName);
		userModel.setGender(gender);
		userModel.setLastName(lastName);
		userModel.setPassword(PBKDF2.generatePasswordHash(pwFromUser));
		userModel.setUsername(username);

		if (userEjb.storeUser(userModel)) {
			System.out.println("sparat");
			return userModel;
		} else {
			System.out.println("INTE sparat");
			return null;
		}
	}

	public UserModel getUser(Long userId) {
		return userEjb.getUser(userId);
	}

	public UserModel updateUser(Long userId, String birthdateFromUser, String city, String country, String email,
			String firstName, String gender, String lastName, String pwFromUser, String username)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {

		UserModel userModel = userEjb.getUser(userId);

		String[] birthdateInfo = birthdateFromUser.split(",");
		int birthyear = Integer.parseInt(birthdateInfo[0]);
		int birthMonth = Integer.parseInt(birthdateInfo[1]);
		int birthDay = Integer.parseInt(birthdateInfo[2]);
		Date birthdate = new Date(birthyear - 1900, birthMonth - 1, birthDay);

		userModel.setBirthdate(birthdate);
		userModel.setCity(city);
		userModel.setCountry(country);
		userModel.setEmail(email);
		userModel.setFirstName(firstName);
		userModel.setGender(gender);
		userModel.setLastName(lastName);
		userModel.setPassword(PBKDF2.generatePasswordHash(pwFromUser));
		userModel.setUsername(username);

		if (userEjb.storeUser(userModel)) {
			System.out.println("sparat");
			return userModel;
		} else {
			System.out.println("INTE sparat");
			return null;
		}
	}

	public UserModel deleteUser(Long userId) {
		UserModel userModel = userEjb.getUser(userId);
		userEjb.deleteUser(userModel);

		return new UserModel();
	}

	public List<UserModel> getAllUsers() {
		return userEjb.getAllUsers();
	}

	public List<DropModel> getUserDrops(Long userId) {
		UserModel userModel = userEjb.getUser(userId);
		List<DropModel> dropList = userModel.getDrops();
		
		for (DropModel dropModel : dropList) {
			dropModel.setComments(removeOwnerFromCommentList( (Vector<CommentModel>) dropModel.getComments()));
		}

		return dropList;
	}

	private List<CommentModel> removeOwnerFromCommentList(Vector<CommentModel> commentList) {

		for (CommentModel commentModel : commentList) {
			commentModel.setOwner(null);
			commentModel.setDropHost(null);
		}
		return commentList;
	}

}
