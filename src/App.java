import dao.AddressDao;
import dao.RoleDao;
import dao.UserDao;
import entity.Address;
import entity.Role;
import entity.User;

import java.util.Optional;

public class App {
    public static void main(String[] args) {

        UserDao userDao = UserDao.getInstance();
        AddressDao addressDao = new AddressDao();
        RoleDao roleDao = new RoleDao();
//        System.out.println(userDao.delete(1L));

        Address address = new Address(2L, "Rostov", "1stAv", "23", "1");
        Role role = new Role(2L, "Admin");
        User userToSave = new User(1L, "Tom", "Tomson", "tom@mail.ru", "123", address, role);
//        System.out.println(userDao.save(userToSave).getId());
//        System.out.println(userDao.delete(8L));

        Optional<User> mayBeUser = userDao.findOneById(1L);
        System.out.println(mayBeUser);
//        Optional<Address> mayBeAddress = addressDao.findOneById(1L);
        Optional<Role> mayBeRole = roleDao.findOneById(1L);
        System.out.println(mayBeRole.get());

    }
}
