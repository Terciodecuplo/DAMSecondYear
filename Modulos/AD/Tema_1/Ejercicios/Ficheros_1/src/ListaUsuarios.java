import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaUsuarios {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        ListaUsuarios listaUsuarios = new ListaUsuarios();
        List<User> userList = new ArrayList<>();
        listaUsuarios.runProgram(userList);
        serializeUserData(userList);
        scanner.close();
    }

    private void runProgram(List<User> userList) {
        String[] userChoice = new String[5];
        String addNewUserOption;
        System.out.println("Welcome to Register User Inc,.Please follow the instructions to proceed.");
        do {
            addUser(userChoice, userList);
            System.out.print("Would you like to add a new user? (Y for Yes)");
            addNewUserOption = scanner.nextLine();
        } while (addNewUserOption.equalsIgnoreCase("y"));
    }

    private void addUser(String[] userChoice, List<User> userList) {
        System.out.print("Insert a name: ");
        userChoice[0] = scanner.nextLine();
        System.out.print("Insert a surname: ");
        userChoice[1] = scanner.nextLine();
        System.out.print("Insert a phone number: ");
        userChoice[2] = scanner.nextLine();
        System.out.print("Insert an ID number: ");
        userChoice[3] = scanner.nextLine();
        userChoice[4] = selectAge();
        User user = new User(userChoice[0], userChoice[1], userChoice[2], userChoice[3], Integer.parseInt(userChoice[4]));
        userList.add(user);
    }

    private String selectAge() {
        String age;
        System.out.print("Insert an age: ");
        age = scanner.nextLine();
        try {
            if (Integer.parseInt(age) < 0) {
                throw new UserAgeInvalidFormatException();
            }
            return age;

        } catch (UserAgeInvalidFormatException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return selectAge();

        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return selectAge();

        }
    }

    private static void serializeUserData(List<User> userList) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/josemurciabelmonte/Documents/DAM/SEGUNDO CURSO/DAMSecondYear/Modulos/AD/Tema_1/Ejercicios/Ficheros_1/src/Files/usuarios.txt"))) {
            String serializedOutput;
            int userCount = 1;
            for (User user : userList) {
                serializedOutput = "Usuario " + userCount + "\n" +
                        "- Nombre: " + user.getName() + "\n" +
                        "- Apellido: " + user.getSurname() + "\n" +
                        "- Teléfono: " + user.getPhoneNumber() + "\n" +
                        "- DNI: " + user.getIdNumber() + "\n" +
                        "- Edad: " + user.getAge() + "\n\n\n";
                userCount++;
                bufferedWriter.write(serializedOutput);
            }
        }
    }


}
