package sg.edu.nus.iss.paf21_jul2023.repo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf21_jul2023.model.Room;

@Repository
public class RoomRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private String countSQL = "SELECT COUNT(*) FROM room";
    private String findAllSQL = "SELECT * FROM room";
    private String findByIdSQL = "SELECT * FROM room WHERE id = ?";
    private String insertSQL = "INSERT INTO room(room_type, price) VALUES (?, ?)";
    private String deleteByIdSQL = "DELETE FROM room WHERE id = ?";
    private String updateSQL = "UPDATE room SET price = ? WHERE id = ?";

    public int count() {
        Integer result = 0;
        result = jdbcTemplate.queryForObject(countSQL, Integer.class);

        return result;
    }

    public List<Room> getAllRooms() {
        List<Room> roomList = new ArrayList<>();
        roomList = jdbcTemplate.query(findAllSQL, BeanPropertyRowMapper.newInstance(Room.class));

        return roomList;
    }

    public Room getRoomById(int id) {
        Room room = new Room();
        room = jdbcTemplate.queryForObject(findByIdSQL, BeanPropertyRowMapper.newInstance(Room.class), id);

        return room;
    }

    public Boolean save(Room room) {
        Boolean saved = false;

        saved = jdbcTemplate.execute(insertSQL, new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, room.getRoomType());
                ps.setInt(2, room.getPrice());
                return ps.execute();
            }
        });

        return saved;
    }

    public int update(Room room) { 
        int updated = 0;
        updated = jdbcTemplate.update(updateSQL, room.getPrice(), room.getId());

        return updated;
    }

    public int deleteById(int id) { 
        int deleted = 0;
        deleted = jdbcTemplate.update(deleteByIdSQL, id);

        return deleted;
    }
}
