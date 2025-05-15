package service;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;

public class UserService {
    private final UserDao userDao = new UserDaoImpl();

    /**
     * Create a new user.
     */
    public void create(User user) throws Exception {
        userDao.insert(user);
    }

    /**
     * Update an existing user.
     */
    public void update(User user) throws Exception {
        userDao.update(user);
    }

    /**
     * Delete a user by ID.
     */
    public void delete(String userId) throws Exception {
        userDao.delete(userId);
    }

    /**
     * Find a user by ID.
     */
    public User findById(String userId) throws Exception {
        return userDao.findById(userId);
    }
}
