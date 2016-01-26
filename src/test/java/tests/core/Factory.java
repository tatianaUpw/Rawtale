package tests.core;

import java.util.*;

public class Factory{

    public static UserData GenerateUserData()
    {
        String randomString = java.util.UUID.randomUUID().toString().substring(25);
        String countries[] = {"Afghanistan", "Aland Islands", "Albania", "Algeria", "Andorra",
                "Angola", "Anguilla", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba",
                "Australia", "Austria", "Azerbaijan", "Bahrain", "Bangladesh", "Barbados", "Belarus",
                "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina",
                "Botswana", "Brasil", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso",
                "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "CÃ´te d Ivoire",
                "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Colombia",
                "Comoros", "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic",
                "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
                "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
                "Ethiopia", "Falkland Islands", "Faroe Islands", "Federated States of Micronesia", "Fiji",
                "Finland", "France", "Gabon", "Georgia", "Germany", "Ghana", "Greece", "Greenland", "Grenada",
                "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hong Kong", "Hungary",
                "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan",
                "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon",
                "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia",
                "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania",
                "Mauritius", "Mexico", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco",
                "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "New Caledonia", "New Zealand",
                "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea", "Norway", "Oman", "Pakistan",
                "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines",
                "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar", "Republic of the Congo",
                "Romania", "Russia", "Rwanda", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia",
                "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
                "Saudi Arabia", "SÃ£o TomÃ© and PrÃ­ncipe", "Senegal", "Serbia", "Seychelles", "Sierra Leone",
                "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
                "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland",
                "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "The Bahamas",
                "The Gambia", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
                "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates",
                "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City",
                "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"};
        String industries[] = {"Agriculture", "Agriculture", "Biotechnology", "Cosmetics", "Education",
                "Electronics", "Energy", "Entertainment", "Fashion", "Finance", "Food", "Healthcare",
                "Logistics", "Manufacturing", "Real Estate", "Retail", "Software", "Sports",
                "Sustainability", "Technology"};
        String images[] ={"\\coverimg1.png","\\coverimg2.jpg","\\img3.gif"};
        String filePath = System.getProperty("user.dir");

        Random generator = new Random();
        int industryRandIndex = generator.nextInt(industries.length-1);
        int coverImgInd =generator.nextInt(images.length-1);
        int profileImgInd =generator.nextInt(images.length-1);
        int locationRandIndex =generator.nextInt(countries.length-1);

        UserData userData =new UserData();
        userData.FirstName = String.format("Fname %1$s",randomString);
        userData.LastName = String.format("Lname %1$s",randomString);
        userData.Description = String.format("Description %1$s",randomString);
        userData.Location =countries[locationRandIndex];
        userData.Industry=industries[industryRandIndex];
        userData.Facebook = String.format("fb.com/%1$s",randomString);
        userData.LinkedIn = String.format("li.com/%1$s",randomString);
        userData.Angellist = String.format("al.com/%1$s",randomString);
        userData.Twitter = String.format("tw.com/%1$s",randomString);
        userData.Google = String.format("g.com/%1$s",randomString);
        userData.CoverImgSrc = filePath+images[coverImgInd];
        userData.ProfileImgSrc =filePath+images[profileImgInd];
        GregorianCalendar a = GenerateDate();
        GregorianCalendar b = GenerateDate();
        userData.StartDate = (a.before(b))? a:b;
        userData.EndDate = (a.after(b))? a:b;
        userData.CompanyName = String.format("Company %1$s",randomString);
        userData.Role = String.format("Role %1$s",randomString);
        userData.ExpDescription = String.format("ExpDescription %1$s",randomString);
        return userData;
    }

    public static MessageData GenerateMessage(String from)
    {
        String randomString = java.util.UUID.randomUUID().toString().substring(25);
        MessageData message = new MessageData();
        message.MessageText= String.format("Message Text %1$s",randomString);
        message.MessageFrom =from;
        return message;
    }

    public static GregorianCalendar GenerateDate() {

        Calendar now = Calendar.getInstance();   // Gets the current date and time
        int currentyear = now.get(Calendar.YEAR);


        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(1936, currentyear);

        gc.set(gc.YEAR, year);
        int dayMax = (year==currentyear)? now.get(Calendar.DAY_OF_YEAR): gc.getActualMaximum(gc.DAY_OF_YEAR);
        int dayOfYear = randBetween(1, dayMax);

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        System.out.println(gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH));
        return gc;

    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

}

