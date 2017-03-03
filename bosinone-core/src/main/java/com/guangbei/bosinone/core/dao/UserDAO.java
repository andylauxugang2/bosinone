package com.guangbei.bosinone.core.dao;

import com.guangbei.bosinone.client.domain.User;

/**
 * @created by xugang on 16/9/6.
 */
public interface UserDAO {

    User selectById(Long id);

    void insertOne(User user);
}