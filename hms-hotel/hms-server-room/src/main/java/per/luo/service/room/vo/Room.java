package per.luo.service.room.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Room implements Serializable {

    private static final long serialVersionUID = -6249397911566315813L;

    int id;

    int room_num;

    int is_usage;

}
