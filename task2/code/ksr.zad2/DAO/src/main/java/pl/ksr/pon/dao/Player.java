package pl.ksr.pon.dao;

import lombok.Data;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;

@Data
@Getter
public class Player {

    @CsvBindByName(column = "id")
    private Double index;

    @CsvBindByName(column = "player_name")
    private String name;

    @CsvBindByName(column = "team_abbreviation")
    private String team;

    @CsvBindByName(column = "age")
    private Double age;

    @CsvBindByName(column = "player_height")
    private Double height;

    @CsvBindByName(column = "player_weight")
    private Double weight;

    @CsvBindByName(column = "country")
    private String country;

    @CsvBindByName(column = "draft_number")
    private String draftNumber;

    @CsvBindByName(column = "gp")
    private Integer gamesPlayed;

    @CsvBindByName(column = "pts")
    private Double averagePoints;

    @CsvBindByName(column = "reb")
    private Double averageRebounds;

    @CsvBindByName(column = "ast")
    private Double averageAssists;

    @CsvBindByName(column = "net_rating")
    private Double teamImpact;

    @CsvBindByName(column = "ts_pct")
    private Double throwAccuracy;

    @CsvBindByName(column = "ast_pct")
    private String assistsPercent;
}
