import dao.AddressDao;
import dao.ArchiveDao;
import dao.CourseDao;
import dao.RoleDao;
import dao.UserDao;
import dto.UserFilter;
import entity.Address;
import entity.Archive;
import entity.Course;
import entity.Role;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class App {

   private static final UserDao userDao = UserDao.getInstance();
   private static final AddressDao addressDao = AddressDao.getInstance();
   private static final RoleDao roleDao = RoleDao.getInstance();
   private static final ArchiveDao archiveDao = ArchiveDao.getInstance();
   private static final CourseDao courseDao = CourseDao.getInstance();
    public static void main(String[] args) {
        Address addressOne = new Address(1L, "Rostov", "1stAv", "23", "1");
        Role roleAdmin = new Role(1L, "Adminnn");
        UserFilter filter = new UserFilter(1, 0, "Tom", "omso", "tom@mail.ru");
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
        System.out.println("=====================================");
        System.out.println(userDao.findAll(filter));
//
//        Optional<User> mayBeUser = userDao.findById(1L);
//        System.out.println(mayBeUser);
//        Optional<Address> mayBeAddress = addressDao.findOneById(1L);
//        Optional<Role> mayBeRole = roleDao.findOneById(1L);
//        System.out.println(mayBeRole.get());
//        System.out.println(mayBeAddress.get());

        checkCourseDao();
//        checkArchiveDao();
    }

    private static void checkArchiveDao() {
        System.out.println("-----save()-----------");
        Archive archive = new Archive(3L, 3L,1L, 4, new Date(System.currentTimeMillis()));
//        System.out.println(archiveDao.save(archive));

        System.out.println("-----findById()-----------");
        System.out.println(archiveDao.findById(1L));

        System.out.println("-----findAll()-----------");
        System.out.println(archiveDao.findAll());

        System.out.println("-----update()-----------");
        archive.setMark(6);
        System.out.println(archiveDao.update(archive));

        System.out.println("-----delete()-----------");
        System.out.println(archiveDao.delete(3L));
    }

    private static void checkCourseDao() {
        System.out.println("-----save()-----------");
        Course chemistry = new Course();
        chemistry.setName("chemistry");
        chemistry.setDescription("chemistry course");
//        System.out.println(courseDao.save(chemistry));

        System.out.println("-----findById()-----------");
        Optional<Course> optionalCourse = courseDao.findById(1L);
        optionalCourse.ifPresent(System.out::println);

        System.out.println("-----findByUserId()-----------");
        System.out.println(courseDao.findAllByUserId(1L));

        System.out.println("-----findAll()-----------");
        List<Course> courses = courseDao.findAll();
        System.out.println(courses);

        System.out.println("-----update()-----------");
        chemistry.setId(4L);
        chemistry.setDescription("description updated");
        System.out.println(courseDao.update(chemistry));

        System.out.println("-----delete()-----------");
        System.out.println(courseDao.delete(4L));
    }
}
