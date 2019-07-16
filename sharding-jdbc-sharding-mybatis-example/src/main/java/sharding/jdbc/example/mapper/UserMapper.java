package sharding.jdbc.example.mapper;

import sharding.jdbc.example.model.User;

import java.util.List;

public interface UserMapper {

    int save(User info);

    /**
     * 批量保存
     */
    int batchSave(List<User> list);
}
