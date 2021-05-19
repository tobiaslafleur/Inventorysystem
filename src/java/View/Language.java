package View;

public class Language {
    // 0 = english, 1 = swedish
    private static int language = 0;

    public static void setLanguage(int language) {
        Language.language = language;
    }

    //LoginPage
    private static String[] logCreateAccount = {"Create Account", "Skapa konto"};
    private static String[] logUsername = {"Username", "Användarnamn"};
    private static String[] logPassword = {"Password", "Lösenord"};
    private static String[] logForgotPassword = {"Forgot password?", "Glömt ditt lösenord?"};
    private static String[] logIncorrectInfo = {"Incorrect username or password.", "Felaktigt användarnamn eller lösenord."};
    private static String[] logLoginBtn = {"Log in", "Logga in"};

    public static String getLogCreateAccount() { return logCreateAccount[language]; }

    public static String getLogUsername() { return logUsername[language]; }

    public static String getLogPassword() { return logPassword[language]; }

    public static String getLogForgotPassword() { return logForgotPassword[language]; }

    public static String getLogIncorrectInfo() { return logIncorrectInfo[language]; }

    public static String getLogLoginBtn() { return logLoginBtn[language]; }

    //RegistrationPage
    private static String[] regCancel = {"Cancel", "Avbryt"};
    private static String[] regCreateAccount = {"Create an account", "Skapa ett konto"};
    private static String[] regUsername = {"Username", "Användarnamn"};
    private static String[] regCheckAvailability = {"Check Availability", "Kolla tillgänglighet"};
    private static String[] regEmail = {"Email", "E-post"};
    private static String[] regPhone = {"Phone", "Telefon"};
    private static String[] regAddress = {"Address", "Adress"};
    private static String[] regPassword = {"Password", "Lösenord"};
    private static String[] regRepeatPassword = {"Repeat password", "Upprepa lösenord"};
    private static String[] regRegisterAccountBtn = {"Register Account", "Registrera Konto"};

    public static String getRegCancel() { return regCancel[language]; }

    public static String getRegCreateAccount() { return regCreateAccount[language]; }

    public static String getRegUsername() { return regUsername[language]; }

    public static String getRegCheckAvailability() { return regCheckAvailability[language]; }

    public static String getRegEmail() { return regEmail[language]; }

    public static String getRegPhone() { return regPhone[language]; }

    public static String getRegAddress() { return regAddress[language]; }

    public static String getRegPassword() { return regPassword[language]; }

    public static String getRegRepeatPassword() { return regRepeatPassword[language]; }

    public static String getRegRegisterAccountBtn() { return regRegisterAccountBtn[language]; }

    //RegistrationPage error handling
    private static String[] regErrUsernameOne = {"Enter a username", "Fyll i ett användarnamn"};
    private static String[] regErrUsernameTwo = {"Check if username is available", "Kolla om användarnamnet är tillgängligt"};
    private static String[] regErrUsernameThree = {"Username already in use", "Användarnamnet finns redan"};
    private static String[] regErrEmail = {"Enter a valid email", "Fyll i en korrekt E-postadress"};
    private static String[] regErrPhone = {"Enter a valid number and select area code", "Fyll i ett korrekt telefonnummer samt riktnummer"};
    private static String[] regErrAddress = {"Enter a valid address", "Fyll i en giltlig adress"};
    private static String[] regErrPassword = {"Password must be at least 8 characters long \n and contain at least one uppercase letter and one number", "Lösenordet måste vara minst 8 bokstäver långt \n och innehålla minst en stor bokstav samt en siffra"};
    private static String[] regErrRepeatPassword = {"Password must match", "Lösenorden måste matcha"};

    public static String getRegErrUsernameOne() { return regErrUsernameOne[language]; }

    public static String getRegErrUsernameTwo() { return regErrUsernameTwo[language]; }

    public static String getRegErrUsernameThree() { return regErrUsernameThree[language]; }

    public static String getRegErrEmail() { return regErrEmail[language]; }

    public static String getRegErrPhone() { return regErrPhone[language]; }

    public static String getRegErrAddress() { return regErrAddress[language]; }

    public static String getRegErrPassword() { return regErrPassword[language]; }

    public static String getRegErrRepeatPassword() { return regErrRepeatPassword[language]; }
}
