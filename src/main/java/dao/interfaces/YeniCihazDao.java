package dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.YeniCihaz;

public interface YeniCihazDao {
	List<YeniCihaz> all() throws SQLException;
}
