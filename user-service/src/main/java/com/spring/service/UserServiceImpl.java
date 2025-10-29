package com.spring.service;

import com.spring.entity.User;
import com.spring.exception.UserException;
import com.spring.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    private final UserRepo repository;

    @Override
    public User create( User user) {
        return repository.save(user);
    }

    @Override
    public User getUserById(Integer id) throws Exception {
        Optional<User> opt=repository.findById(id);
        if(opt.isPresent())
        {
          return   opt.get();
        }
       throw new UserException("user is not found");
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User updateUserById(Integer id) throws Exception {
        Optional<User> optional = repository.findById(id);
        if(optional.isPresent())
        {
            return repository.save(optional.get());
        }
        throw new UserException("existing user not found");
    }

    @Override
    public String deleteUserById(Integer id) throws Exception {
        Optional<User> opti= repository.findById(id);
        if(opti.isPresent())
        {
             repository.deleteById(opti.get().getId());
             return "user deleted";
        }
        throw new UserException("existing user not found"+id);
    }
}
