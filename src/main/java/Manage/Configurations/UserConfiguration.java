package Manage.Configurations;



public interface UserConfiguration {
    String USERS_TABLE = "users";
    String USERS_INFO_TABLE = "usersInfo";
    String EMPTY = "არცერთი ველი არ უნდა იყოს ცარიელი";  // for both feature

    //-----------------------------------------------login
    String FOUND = "მომხმარებელი მოიძებნა";
    String NOT_FOUND = "მომხმარებელი ვერ მოიძებნა";

    //----------------------------------------------registration
    String INCORRECT_FIRST_NAME = "გთხოვთ, სახელის ველში შემოიყვანოთ მხოლოდ ლათინური პატარა ასოები";
    String CORRECT_FIRST_NAME = "სახელი მისაღებია";
    String INCORRECT_LAST_NAME = "გთხოვთ, გვარის ველში მხოლოდ ლათინური პატარა ასოები შემოიყვანოთ";
    String CORRECT_LAST_NAME = "გვარი მისაღებია";
    String CORRECT_USERNAME = "იუზერნეიმი მისაღებია";
    String ILLEGAL_SYMBOL = "იუზერნეიმის ინფუთში არალეგალური ასოები ურევია! გთხოვთ შეასწოროთ";
    String INCORRECT_PASSWORD_LENGTH = "პაროლის სიგრძე უნდა იყოს 4-16 დიაპაზონში";
    String WRONG_FORMAT = "პაროლი უნდა შედგებოდეს მინიმუმ 1 დიდი ასოსგან და მინიმუმ 1 ციფრისგან";
    String CORRECT_PASSWORD = "პაროლი მისაღებია";
    String CORRECT_MAIL = "მეილი ნამდვილად არსებობს";
    String INCORRECT_MAIL = "ასეთი მეილი, აღნიშნული ადამიანის სახელით, თავისუფალ უნივერსიტეტს არ ეკუთვნის";

    //----------------------------------------if everything is ok
    String ALL_GOOD = "ყველაფერი რიგზეა";
}