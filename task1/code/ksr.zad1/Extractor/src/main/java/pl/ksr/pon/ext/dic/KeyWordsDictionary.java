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
            "Jefferson",
            "Lincoln",
            "Wilson",
            "Roosevelt",
            "Truman",
            "Eisenhower",
            "Kennedy",
            "Nixon",
            "Carter",
            "Reagan",
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
            "Douglas",
            "Trudeau",
            "Terry Fox",
            "Banting",
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
            "Miki",
            "Fukuda",
            "Ōhira",
            "Suzuki",
            "Nakasone",
            "Takeshita",
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
            "Queen Elisabeth",
            "Churchill",
            "Thatcher",
            "Callaghan",
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
            "Poher",
            "Pompidou",
            "Mitterrand",
            "Chirac",
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
            "Adenauer",
            "Brandt",
            "Schmidt",
            "Kohl",
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

