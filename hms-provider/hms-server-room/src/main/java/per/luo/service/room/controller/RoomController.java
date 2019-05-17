package per.luo.service.room.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import per.luo.service.room.vo.Room;

/**
 * room服务
 */
@RestController
@RefreshScope
public class RoomController {


    @RequestMapping(value = "/room/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Room findByRoonNum(@PathVariable long id){

        Room room = new Room();
        room.setId(1);
        room.setIs_usage(1);
        room.setRoom_num(2001);
        return room;
    }

//    @RequestMapping(value = "/get_room_info",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
//    public Room doRoomInfo(){
//
//    }
}

