import dao.AddressDao;
import dao.RoleDao;
import dao.UserDao;
import entity.Address;
import entity.Role;

public class App {
    public static void main(String[] args) {

        UserDao userDao = UserDao.getInstance();
        AddressDao addressDao = AddressDao.getInstance();
        RoleDao roleDao = RoleDao.getInstance();

        Address addressOne = new Address(1L, "Rostov", "1stAv", "23", "1");
        Role roleAdmin = new Role(1L, "Adminnn");
//        System.out.println(roleDao.delete(1L));
//        roleDao.update(roleAdmin);
//        addressDao.save(addressOne);
//        roleDao.save(roleAdmin);
//        System.out.println(roleDao.findAll());
//        System.out.println(addressDao.findAll());

//        User userToSave = new User();
//        userToSave.setFirst_name("Tom");
//        userToSave.setLast_name("Tomson");
//        userToSave.setEmail("tom@mail.ru");
//        userToSave.setPassword("123");
//        userToSave.setAddress(addressOne);
//        userToSave.setRole(roleAdmin);
//
//        System.out.println(userDao.save(userToSave).getId());
////        System.out.println(userDao.save(userToSave).getId());
////        System.out.println(userDao.delete(8L));
        System.out.println(userDao.findAll());
//
//        Optional<User> mayBeUser = userDao.findById(1L);
//        System.out.println(mayBeUser);
//        Optional<Address> mayBeAddress = addressDao.findOneById(1L);
//        Optional<Role> mayBeRole = roleDao.findOneById(1L);
//        System.out.println(mayBeRole.get());
//        System.out.println(mayBeAddress.get());

    }
}
