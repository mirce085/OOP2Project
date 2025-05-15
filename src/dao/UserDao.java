package dao;

import model.User;
public interface UserDao {
    User findById(String id) throws Exception;
    void insert(User user) throws Exception;
    void update(User user) throws Exception;
    void delete(String id) throws Exception;
}
