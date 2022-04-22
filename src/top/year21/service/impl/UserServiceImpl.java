package top.year21.service.impl;

import top.year21.bean.User;
import top.year21.dao.UserDao;
import top.year21.dao.impl.UserDaoImpl;
import top.year21.service.UserService;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/24 18:41
 */
public class UserServiceImpl implements UserService {
      private UserDao userDao =   new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.insert(user);
    }

    @Override
    public User login(User user) {
        return userDao.getUser(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean isExistsUserName(String username) {
        if (userDao.getUserByName(username) == null){
            return false;
        }
        return true;

    }
}
