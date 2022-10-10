package pl.ksr.pon.dao;

public class PlayerDAOFactory {
    public DAO<Player> getPlayerDAO(String path) {
        return new PlayerDAO(path);
    }
}
