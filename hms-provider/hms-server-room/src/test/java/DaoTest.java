import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import per.luo.service.room.RoomServiceApplication;
import per.luo.service.room.dao.RoomDao;
import per.luo.service.room.vo.Room;

/**
 * @author luoshao
 * @date 2019/5/14 22:38
 * @projectname HMS
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RoomServiceApplication.class)
public class DaoTest {

    @Autowired
    private RoomDao roomdao;

    @Test
    public void testInsert() {

        Room room = new Room();
        room.setId(1000);
        room.setIs_usage(1);
        room.setRest_room_count(1);
        room.setRoom_num(1);
        room.setRoom_money(1);

        roomdao.update(room);
        System.out.println("更新成功！");
    }
}