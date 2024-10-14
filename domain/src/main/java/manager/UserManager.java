package manager;

import entity.User;

import lombok.extern.slf4j.Slf4j;

import repository.UserRepo;



@Slf4j
public class UserManager extends DefaultManager<User, UserRepo>{
    public UserManager(UserRepo userRepo){
        super(userRepo);
    }


}
