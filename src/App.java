import dao.UserDao;
import entity.Address;
import entity.Role;
import entity.User;

import java.util.Optional;

public class App {
    public static void main(String[] args) {

        UserDao userDao = UserDao.getInstance();
//        System.out.println(userDao.delete(1L));

        Address address = new Address(2L, "Rostov", "1stAv", "23", "1");
        Role role = new Role(2L, "Admin");
        User userToSave = new User(1L, "Tom", "Tomson", "tom@mail.ru", "123", address, role);
//        System.out.println(userDao.save(userToSave).getId());
//        System.out.println(userDao.delete(8L));
        Optional<User> mayBeUser = userDao.findOneById(6L);
        System.out.println(mayBeUser.get());

    }
}
