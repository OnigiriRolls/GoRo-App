package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.PasswordNotOkException;
import org.loose.fis.sre.exceptions.UserDoesNotExistException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.exceptions.WrongPasswordException;
import org.loose.fis.sre.model.Request;
import org.loose.fis.sre.model.TouristAttractions;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class UserService {

    public static ObjectRepository<TouristAttractions> attractionsRepository;
    public static ObjectRepository<Request> requestsRepository;

    private static ObjectRepository<User> userRepository;

    private static Nitrite database;

    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);

        attractionsRepository = database.getRepository(TouristAttractions.class);
        requestsRepository = database.getRepository(Request.class);

        findLastRequestId();
    }

    public static void closeDatabase(){
        database.close();
    }

    private static void findLastRequestId(){
        if(requestsRepository!=null) {
            for (Request request : requestsRepository.find()) {
                Request.nr = request.getRequestID();
            }
            return;
        }

        Request.nr=0;
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

    public static void checkPassword(String password) throws PasswordNotOkException{
        if(password.length()<4)
            throw new PasswordNotOkException();
        int nr=0;
        for(int i=0; i<password.length(); i++)
            if(Character.isUpperCase(password.charAt(i)))
                nr++;
        if(nr==0)
            throw new PasswordNotOkException();
    }

    public static String encodePassword(String salt, String password) {
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

    public static List<User> getAllUsers() {
        return userRepository.find().toList();
    }
}
