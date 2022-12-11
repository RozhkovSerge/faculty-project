package service;

import dao.Dao;
import dao.UserDao;
import dto.UserDto;
import entity.User;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;



public class UserService {

    private final static UserService INSTANCE = new UserService();

    private UserService() {
    }

    private final  UserDao userDao = UserDao.getInstance();

    public List<UserDto> findAll() {
        return userDao.findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getFirst_name(),
                        user.getLast_name(),
                        user.getEmail(),
                        user.getAddress()))
                .collect(toList());
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
