package Manage;



public interface Configuration {
    String USERS_TABLE = "Users";
    String EMPTY = "Empty fields";  // for both feature

    //-----------------------------------------------login
    String FOUND = "User found";
    String NOT_FOUND = "User not found";

    //----------------------------------------------registration
    String INCORRECT_FIRST_NAME = "Please input only latin symbols";
    String CORRECT_FIRST_NAME = "First name is acceptable";
    String INCORRECT_LAST_NAME = "Please input only latin symbols";
    String CORRECT_LAST_NAME = "Last name is acceptable";
    String CORRECT_USERNAME = "Username is acceptable";
    String ILLEGAL_SYMBOL = "Illegal symbol found";
    String INCORRECT_PASSWORD_LENGTH = "Password length should be 4-16";
    String WRONG_FORMAT = "Password should contain at least 1 capital letter and 1 digit";
    String CORRECT_PASSWORD = "Password is acceptable";
    String CORRECT_MAIL = "Mail is correct";
    String INCORRECT_MAIL = "Such mail with such person doesn't belong to Free University";

    //----------------------------------------if everything is ok
    String ALL_GOOD = "Ok, ok, every field is acceptable";
}