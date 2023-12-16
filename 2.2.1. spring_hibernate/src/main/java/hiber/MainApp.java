package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        User user1 = new User("User5WithCar", "Lastname1", "user1@mail.ru");
        User user2 = new User("User6WithCar", "Lastname2", "user1@mail.ru");
        User user3 = new User("User7WithCar", "Lastname3", "user1@mail.ru");

        user1.setCar(new Car("Model1", 11));
        user2.setCar(new Car("Model2", 22));
        user3.setCar(new Car("Model3", 33));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        printUser(userService.getUserByCar("Model1", 11));
        printUser(userService.getUserByCar("Model2", 22));
        printUser(userService.getUserByCar("Model3", 33));

        List<User> users = userService.listUsers();
        for (User user : users) {
            printUser(user);
        }

        context.close();
    }

    private static void printCar(Car car) {
        System.out.println("Id = " + car.getId());
        System.out.println("Model = " + car.getModel());
        System.out.println("Series = " + car.getSeries());
        System.out.println();
    }

    private static void printUser(User user) {
        System.out.println("Id = " + user.getId());
        System.out.println("First Name = " + user.getFirstName());
        System.out.println("Last Name = " + user.getLastName());
        System.out.println("Email = " + user.getEmail());
        System.out.println();
    }

}
