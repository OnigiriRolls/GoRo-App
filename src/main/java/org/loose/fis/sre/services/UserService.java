package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.PasswordNotOkException;
import org.loose.fis.sre.exceptions.UserDoesNotExistException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.exceptions.WrongPasswordException;
import org.loose.fis.sre.model.TouristAttractions;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class UserService {

    public static ObjectRepository<TouristAttractions> attractionsRepository;

    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);

        attractionsRepository = database.getRepository(TouristAttractions.class);
    }

    public static void printUsers(){
        org.dizitart.no2.objects.Cursor<User> cursor = userRepository.find();

        for(User i: cursor)
            System.out.println(i.getUsername()+" "+i.getRole());
    }

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException, PasswordNotOkException {
        checkUserDoesNotAlreadyExist(username);
        checkPassword(password);
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static void checkPassword(String password) throws PasswordNotOkException{
        if(password.length()<4)
            throw new PasswordNotOkException();
        int nr=0;
        for(int i=0; i<password.length(); i++)
            if(Character.isUpperCase(password.charAt(i)))
                nr++;
        if(nr==0)
            throw new PasswordNotOkException();
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static void checkPassAndUsername(String username) throws UserDoesNotExistException {
        int found = 0;
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                found = 1;
        }
        if(found == 0) throw new UserDoesNotExistException();
    }

    public static void wrongPassword(String username) throws WrongPasswordException{
        for (User user : userRepository.find()) {
            if (!(Objects.equals(username, user.getUsername()))) throw new WrongPasswordException();
        }
    }
}
