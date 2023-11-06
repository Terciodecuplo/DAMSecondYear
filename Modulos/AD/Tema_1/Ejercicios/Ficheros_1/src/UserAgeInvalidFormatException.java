public class UserAgeInvalidFormatException extends Exception{
    public UserAgeInvalidFormatException(){
        super("The age format is not correct. It should be a positive Integer.");
    }
}
