package me.mustafaesattemel.business.concretes;

import me.mustafaesattemel.business.abstracts.UserCheckService;
import me.mustafaesattemel.business.abstracts.UserService;
import me.mustafaesattemel.business.abstracts.UserVerififiactionService;
import me.mustafaesattemel.core.LoggerService;
import me.mustafaesattemel.dataAccess.abstracts.UserDao;
import me.mustafaesattemel.entities.concretes.User;

public class UserManager implements UserService{
	private UserCheckService userCheckService;
	private UserVerififiactionService userVerififiactionService;
	private UserDao userDao;
	private LoggerService loggerService;
	
	public UserManager(UserCheckService userCheckService,UserVerififiactionService userVerififiactionService
			,UserDao userDao,LoggerService loggerService) {
		this.loggerService = loggerService;
		this.userDao=userDao;
		this.userCheckService=userCheckService;
		this.userVerififiactionService=userVerififiactionService;
	}

	
	@Override
	public void signIn(User user) {
		System.out.println("Ba�ar�l� bir �ekilde giri� yapt�n! "+user.getName());
		
	}

	@Override
	public void signUp(User user) {
				if(userCheckService.getEmail(user)&&userCheckService.getPassword(user)
				&&userCheckService.getName(user)&&userCheckService.getSurname(user))
		{
					System.out.println("Mailini onaylarsan kayd�n yap�lacak.");
			if(userVerififiactionService.isEmailApproval(user)) {
				userDao.add(user);
				System.out.println("Ba�ar�l� bir �ekilde kay�t oldun");
				loggerService.log("Google ile eklendi");
			}
			
		}else {
			System.out.println("Bilgilerini kontrol et ");
		}
		
		
	}

}
