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

    //Add Product
    private static String[] prodTitle = {"Add Product", "Lägg till produkt"};
    private static String[] prodCancel = {"Cancel", "Avbryt"};
    private static String[] prodName = {"Product name", "Produktnamn"};
    private static String[] prodStock = {"Stock", "Antal"};
    private static String[] prodPrice = {"Price", "Pris"};
    private static String[] prodCost = {"Cost", "Kostnad"};
    private static String[] prodCategory = {"Category", "Kategori"};
    private static String[] prodShelf = {"Shelf", "Hylla"};
    private static String[] prodSupplier = {"Supplier", "Leverantör"};
    private static String[] prodAdd = {"Add product", "Lägg till produkt"};
    private static String[] prodErrName = {"Product must have a name", "Produkten måste ha ett namn"};
    private static String[] prodErrStock = {"Only use numbers", "Använd endast heltal"};
    private static String[] prodErrCost = {"Only use numbers. If decimals are needed, use a . to separate ", "Använd endast heltal. Om decimaler behövs, använd . för att separera"};
    private static String[] prodErrPrice = {"Only use numbers. If decimals are needed, use a . to separate ", "Använd endast heltal. Om decimaler behövs, använd . för att separera"};
    private static String[] prodErrProd = {"Select a product to edit", "Välj en produkt att ändra"};
    private static String[] prodEditTitle = {"Edit product", "Redigera produkt"};

    public static String getProdTitle() {
        return prodTitle[language];
    }

    public static String getProdCancel() {
        return prodCancel[language];
    }

    public static String getProdName() {
        return prodName[language];
    }

    public static String getProdStock() {
        return prodStock[language];
    }

    public static String getProdPrice() {
        return prodPrice[language];
    }

    public static String getProdCost() {
        return prodCost[language];
    }

    public static String getProdCategory() {
        return prodCategory[language];
    }

    public static String getProdShelf() {
        return prodShelf[language];
    }

    public static String getProdSupplier() {
        return prodSupplier[language];
    }

    public static String getProdAdd() {
        return prodAdd[language];
    }
    public static String getProdEditTitle() {
        return prodEditTitle[language];
    }

    public static String getProdErrName() {
        return prodErrName[language];
    }

    public static String getProdErrStock() {
        return prodErrStock[language];
    }

    public static String getProdErrCost() {
        return prodErrCost[language];
    }

    public static String getProdErrPrice() {
        return prodErrPrice[language];
    }
    public static String getProdErrProd() {
        return prodErrProd[language];
    }
    //AccountSettings 
    private static String[] editPhoneSettings =         {"Phone number", "Telefonnummer"};
    private static String[] editAddressSettings =       {"Address", "Adress"};
    private static String[] editPasswordSettings =      {"Password", "Lösenord"};
    private static String[] editNewPasswordSettings =   {"New Password", "Nytt lösenord"};
    private static String[] editSaveSettings =          {"Save settings", "Spara ändringar"};
    private static String[] editCancel =                {"Cancel", "Avbryt"};
    private static String[] getEditLblTitle =           {"Account Settings", "Kontoinställningar"};

    public static String getPhoneSettingsAS() {
        return editPhoneSettings[language];
    }
    public static String getEditAddressAS() {
        return editAddressSettings[language];
    }
    public static String getPasswordAS() {
        return editPasswordSettings[language];
    }
    public static String getNewPasswordAS() {
        return editNewPasswordSettings[language];
    }
    public static String getSaveSettingsAS() {
        return editSaveSettings[language];
    }
    public static String getEditCancelAS() {
        return editCancel[language];
    }
    public static String getEditLblTitle() {
        return getEditLblTitle[language];
    }

    //AccountSettings label errorhandling
    private static String[] editErrPhoneSettings =          {"Enter a valid number and area code.", "Ange ett riktigt telefonnummer och riktnummer."};
    private static String[] editErrNewPasswordSettings =    {"You can't change to ur current password.\n Password must be: 8 characters long, one uppercase letter and one number.",
                                                            "Du kan inte ändra till ditt nuvarande lösenord. \nLösenordet måste vara: 8 bokstäver långt, en stor bokstav och ett nummer"};
    private static String[] editErrAddressSettings =        {"Enter a valid address.", "Ange en riktigt adress."};
    private static String[] editErrOldPasswordSettings =    {"Incorrect password", "Felaktigt lösenord."};

    public static String getErrPhonePasswordAS() {
        return editErrPhoneSettings[language];
    }
    public static String getErrAddressAS() {
        return editErrAddressSettings[language];
    }
    public static String getOldPasswordAS() {
        return editErrOldPasswordSettings[language];
    }
    public static String getRepeatPasswordAS() {
        return editErrNewPasswordSettings[language];
    }

    //Category
    private static String[] categoryError = {"Category must enter a name","Kategorin måste ha ett namn"};

    public static String getCategoryError() {
        return categoryError[language];
    }

}
