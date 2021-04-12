package com.pun.poc.security;

import com.pun.poc.dao.UserDao;
import com.pun.poc.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JdbcUserDetailsService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcUserDetailsService.class);

    @Autowired
    private UserDao userDao;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(Objects.isNull(user)) {
            LOG.info("no user exists for username {}", username);
            throw  new UsernameNotFoundException(String.format("no user exists for username %s", username));
        }
        LOG.info("user {}", user);
        return MyUserDetailBuilder.getBuilder()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setAuthorities(user.getRole())
                .build();
    }
}
