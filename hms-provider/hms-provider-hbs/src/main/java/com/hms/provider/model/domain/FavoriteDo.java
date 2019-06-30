package com.hms.provider.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/27 23:21
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Entity(name = "favorite_info")
@Table(name = "favorite_info") //映射的表名称
@Data
public class FavoriteDo implements Serializable {

    private static final long serialVersionUID = 8375547628479463100L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id")
    private long user_id;

    @Column(name = "hotel_id")
    private int hotel_id;

    @Column(name = "favorite")
    private boolean favorite;

}
