package com.shachar.pharmacy.logic;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.shachar.pharmacy.dao.IUsersDao;
import com.shachar.pharmacy.data.UserLoginData;
import com.shachar.pharmacy.databean.SuccessfulLoginData;
import com.shachar.pharmacy.databean.UserLoginDetails;
import com.shachar.pharmacy.entities.Users;
import com.shachar.pharmacy.enums.ErrorType;
import com.shachar.pharmacy.enums.UserType;
import com.shachar.pharmacy.exceptions.ApplicationException;


@Controller
public class UsersController {

	@Autowired
	private IUsersDao usersDao;
	
	@Autowired
	private CacheController cacheController;
	
	
	private static final String ENCRYPTION_TOKEN_SALT = "AASDFHSJFHJHKAAAAA-3423@#$@#$";

	public long createUser(Users user) throws ApplicationException {
		userValidations(user);
		user.setPassword(String.valueOf(user.getPassword().hashCode()));
		try {
			return this.usersDao.save(user).getId();
		}catch(Exception e) {
			throw new ApplicationException(ErrorType.FAIL_TO_CREATE_USER, "Failed to create user");
		}
	}

	private void userValidations(Users user) {
//		if (user.getUserType() == UserType.company) {
//			if (user.getCompany() == null) {
//				throw new ApplicationException(ErrorTypes.INVALID_COMPANY, "Company id must be specified for a company user.");
//			}
//			companiesController.isCompanyExsitById(user.getCompany().getId()); 
//
//		}	
//		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//		if ( !user.getUserName().matches(regex)) {
//			throw new ApplicationException(ErrorTypes.INVALID_USERNAME,"Email not valid");
//		}

//		if (user.getPassword().length() > 15 || user.getPassword().length() < 6) {
//			throw new ApplicationException(ErrorTypes.INVALID_PASSWORD, "Password should be less than 15 and more than 6 characters in length.");
//
//		}
//		if (user.getPassword() == user.getUserName()) {
//			throw new ApplicationException(ErrorTypes.INVALID_PASSWORD, "Password Should not be same as user name");
//
//		}
//
//		String lowerCaseChars = "(.*[a-z].*)";
//		if (!user.getPassword().matches(lowerCaseChars)) {
//			throw new ApplicationException(ErrorTypes.INVALID_PASSWORD, "Password should contain atleast one lower case alphabet");
//
//		}
//		String numbers = "(.*[0-9].*)";
//		if (!user.getPassword().matches(numbers)) {
//			throw new ApplicationException(ErrorTypes.INVALID_PASSWORD, "Password should contain atleast one number.");
//
//		}		
	}
	
	public void updateUser(Users user) throws ApplicationException {
		isUserExsitById(user.getId());
		userValidations(user);
		user.setPassword(String.valueOf(user.getPassword().hashCode()));
		try {
			this.usersDao.save(user);
		}catch(Exception e) {
			throw new ApplicationException(ErrorType.FAIL_TO_UPDATE_USER, "Failed to update user");
		}
	}

	public void deleteUser(long userId) throws ApplicationException {
		isUserExsitById(userId);
		try {
			this.usersDao.deleteById(userId);
		}catch(Exception e) {
			throw new ApplicationException(ErrorType.FAIL_TO_DELETE_USER, "Failed to delete user");
		}
	}

	public Users getUser(long userId) throws ApplicationException {
		isUserExsitById(userId);
		try {
			return this.usersDao.findById(userId);
		}catch(Exception e) {
			throw new ApplicationException(ErrorType.FAIL_TO_GET_USER, "Failed to get user");
		}
	}

	public List<Users> findAllUsers() throws ApplicationException {
		try {
			return (List<Users>) this.usersDao.findAll();
		}catch (Exception e) {
			throw new ApplicationException(ErrorType.INVALID_USERS, "Failed to get users", e);
		}
	}



	public void isUserExsitById (long userId) throws ApplicationException {
		if (this.usersDao.findById(userId) == null) {
			throw new ApplicationException(ErrorType.INVALID_USER_ID, "There is no user with this ID");
		}
	}
	
	public SuccessfulLoginData login(UserLoginDetails userLoginDetails ) throws ApplicationException {
		System.out.println(userLoginDetails);
		String userName = userLoginDetails.getUserName();
		try {
			Users user = usersDao.findByUserName(userName);
			if (user == null) {
				throw new ApplicationException(ErrorType.INVALID_USER, "No such user");
			}
			int userHashPassword = Integer.parseInt(user.getPassword());

			System.out.println("1 " + userLoginDetails.getPassword().hashCode());
			System.out.println("2 " +userHashPassword);

			if (userHashPassword != userLoginDetails.getPassword().hashCode()) {

				throw new ApplicationException(ErrorType.INVALID_USER, "Invalid password");
			}

			long id = user.getId();
			UserType userType = user.getUserType();
			Long companyId;
			if (userType == UserType.Organization) {
				companyId = user.getOrganizations().getId();
				}
			else {
				companyId = null;
			}
			UserLoginData userLoginData = new UserLoginData(id, userType, companyId);
			int token = generateToken(userLoginDetails);


			cacheController.put(String.valueOf(token), userLoginData);

			return new SuccessfulLoginData(token, userLoginData.getUserType());

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.FAIL_TO_GET_PASSWORD, "Failed get the hash password", e);
		}

	}

	private int generateToken(UserLoginDetails userLoginDetails) {
		String text = userLoginDetails.getUserName() + Calendar.getInstance().getTime().toString() + ENCRYPTION_TOKEN_SALT;
		return text.hashCode();
	}
}
