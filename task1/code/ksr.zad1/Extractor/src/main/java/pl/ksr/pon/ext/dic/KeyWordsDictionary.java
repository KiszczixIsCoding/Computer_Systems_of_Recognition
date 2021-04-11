package pl.ksr.pon.ext.dic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyWordsDictionary implements Dictionary {

    @Override
    public List<String> getDictionary() {
        List<String> allDictionary = new ArrayList<>();
        allDictionary.addAll(getUSAKeyWordsDictionary());
        allDictionary.addAll(getCanadaKeyWordsDictionary());
        allDictionary.addAll(getUKKeyWordsDictionary());
        allDictionary.addAll(getFranceKeyWordsDictionary());
        allDictionary.addAll(getJapanKeyWordsDictionary());
        allDictionary.addAll(getWestGermanyKeyWordsDictionary());
        return allDictionary;
    }

    public List<String> getUSAKeyWordsDictionary() {
        return USAKeyWordsDictionary;
    }

    public List<String> getCanadaKeyWordsDictionary() {
        return CanadaKeyWordsDictionary;
    }

    public List<String> getJapanKeyWordsDictionary() {
        return JapanKeyWordsDictionary;
    }

    public List<String> getUKKeyWordsDictionary() {
        return UKKeyWordsDictionary;
    }

    public List<String> getFranceKeyWordsDictionary() {
        return FranceKeyWordsDictionary;
    }

    public List<String> getWestGermanyKeyWordsDictionary() {
        return WestGermanyKeyWordsDictionary;
    }

    List<String> USAKeyWordsDictionary = Arrays.asList(
            "Alabama",
            "Alaska",
            "Arizona",
            "Arkansas",
            "California",
            "Colorado",
            "Connecticut",
            "Delaware",
            "Florida",
            "Georgia",
            "Hawaii",
            "Idaho",
            "Illinois",
            "Indiana",
            "Iowa",
            "Kansas",
            "Kentucky",
            "Louisiana",
            "Maine",
            "Maryland",
            "Massachusetts",
            "Michigan",
            "Minnesota",
            "Missisipi",
            "Missouri",
            "Montana",
            "Nebraska",
            "Nevada",
            "New Hampshire",
            "New Jersey",
            "New Mexico",
            "New York",
            "North Carolina",
            "North Dakota",
            "Ohio",
            "Oklahoma",
            "Oregon",
            "Pennsylvania",
            "Rhode Island",
            "South Carolina",
            "South Dakota",
            "Tennessee",
            "Texas",
            "Utah",
            "Vermont",
            "Virginia",
            "Washington",
            "West Virginia",
            "Wisconsin",
            "Wyoming",
            "New York",
            "Los Angeles",
            "Chicago",
            "Houston",
            "Phoenix",
            "Philadelphia",
            "San Antonio",
            "San Diego",
            "Dallas",
            "San Francisco",
            "George Washington",
            "Thomas Jefferson",
            "Abraham Lincoln",
            "Theodore Roosevelt",
            "Woodrow Wilson",
            "Franklin D. Roosevelt",
            "Harry S. Truman",
            "Dwight D. Eisenhower",
            "John F. Kennedy",
            "Richard Nixon",
            "Jimmy Carter",
            "Ronald Reagan",
            "Smith",
            "Johnson",
            "Williams",
            "Jones",
            "Brown",
            "Davis",
            "Miller",
            "Wilson",
            "Moore",
            "Taylor"
    );
    List<String> CanadaKeyWordsDictionary = Arrays.asList(
            "Alberta",
            "British Columbia",
            "Manitoba",
            "New Brunswick",
            "Newfoundland and Labrador",
            "Northwest Territories",
            "Nova Scotia",
            "Nunavut",
            "Ontario",
            "Prince Edward Island",
            "Quebec",
            "Saskatchewan",
            "Yukon",
            "Toronto",
            "Montreal",
            "Vancouver",
            "Calgary",
            "Edmonton",
            "Ottawa",
            "Winnipeg",
            "Quebec",
            "Hamilton",
            "Tommy Douglas",
            "Pierre Trudeau",
            "Terry Fox",
            "Sir Frederick Banting",
            "Sir John A. Macdonald",
            "Smith",
            "Brown",
            "Tremblay",
            "Martin",
            "Roy",
            "Gagnon",
            "Lee",
            "Wilson",
            "Johnson",
            "MacDonald"
    );

    List<String> JapanKeyWordsDictionary = Arrays.asList(
            "Hokkaido",
            "Honshu",
            "Kyushu",
            "Shikoku",
            "Okinawa ",
            "Tokyo",
            "Yokohama",
            "Osaka",
            "Nagoya",
            "Sapporo",
            "Kobe",
            "Kyoto",
            "Fukuoka",
            "Kawasaki",
            "Hiroshima",
            "Mutsuhito ",
            "Yoshihito ",
            "Hirohito ",
            "Kakuei Tanaka",
            "Takeo Miki",
            "Takeo Fukuda",
            "Masayoshi Ōhira",
            "Zenkō Suzuki",
            "Yasuhiro Nakasone",
            "Noboru Takeshita",
            "Sato",
            "Suzuki",
            "Takahashi",
            "Tanaka",
            "Watanabe",
            "Ito",
            "Nakamura",
            "Kobayashi",
            "Yamamoto",
            "Kato"
    );

    List<String> UKKeyWordsDictionary = Arrays.asList(
            "England",
            "Scotland",
            "Northern Ireland",
            "Wales",
            "Yorkshire",
            "London",
            "Birmingham",
            "Leeds",
            "Glasgow",
            "Sheffield",
            "Manchester",
            "Edinburgh",
            "Liverpool",
            "Bristol",
            "Cardiff",
            "Queen Elisabeth II",
            "Princess Diana",
            "Winston Churchill",
            "Margaret Thatcher",
            "James Callaghan",
            "King Phillip",
            "Smith",
            "Jones",
            "Williams",
            "Brown",
            "Taylor",
            "Davies",
            "Wilson",
            "Evans",
            "Thomas",
            "Roberts"
    );

    List<String> FranceKeyWordsDictionary = Arrays.asList(
            "Auvergne",
            "Rhône-Alpes",
            "Burgundy",
            "Franche-Comté",
            "Brittany",
            "Centre-Val de Loire",
            "Corsica",
            "French Guiana",
            "Alsace",
            "Champagne-Ardenne",
            "Lorraine",
            "Guadeloupe",
            "Nord-Pas-de-Calais",
            "Picardy",
            "Île-de-France",
            "Martinique",
            "Mayotte",
            "Lower Normandy",
            "Upper Normandy",
            "Aquitaine",
            "Limousin",
            "Poitou-Charentes",
            "Languedoc-Roussillon",
            "Midi-Pyrénées",
            "Pays de la Loire",
            "Provence-Alpes-Côte d'Azur",
            "Réunion",
            "Paris",
            "Lyon",
            "Marseille",
            "Toulouse",
            "Lille",
            "Bordeaux",
            "Nice",
            "Nantes",
            "Strasbourg",
            "Cannes",
            "Charles de Gaulle",
            "Alain Poher",
            "Georges Pompidou",
            "François Mitterrand",
            "Jacques Chirac",
            "Martin",
            "Bernard",
            "Dubois",
            "Thomas",
            "Robert",
            "Richard",
            "Petit",
            "Durand",
            "Leroy",
            "Moreau"
    );

    List<String> WestGermanyKeyWordsDictionary = Arrays.asList(
            "Bayern",
            "Hessen",
            "Schleswig-Holstein",
            "Niedersachsen",
            "Nordrhein-Westfallen",
            "Rhein-Pfalz",
            "Saarland",
            "Baden-Wurttemberg",
            "Berlin",
            "Bremen",
            "Dortmund",
            "Bonn",
            "Frankfurt",
            "Hamburg",
            "Stuttgart",
            "Koln",
            "Dusseldorf",
            "Munchen",
            "Konrad Adenauer",
            "Willy Brandt",
            "Helmut Schmidt",
            "Helmut Kohl",
            "Muller",
            "Schmidt",
            "Schneider",
            "Fischer",
            "Weber",
            "Meyer",
            "Wagner",
            "Becker",
            "Schulz",
            "Hoffmann"
    );
}
