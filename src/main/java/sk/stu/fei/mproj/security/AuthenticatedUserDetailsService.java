package sk.stu.fei.mproj.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import sk.stu.fei.mproj.domain.dao.AccountDao;
import sk.stu.fei.mproj.domain.entities.Account;

@Component
public class AuthenticatedUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationException {
        final Account account = this.accountDao.findByEmail(username);

        if ( account == null ) {
            throw new UsernameNotFoundException(String.format("No account found with email %s", username));
        }
        else if ( !account.getActive() ) {
            throw new DisabledException(String.format("Account email=%s is not activated", username));
        }
        else if ( account.getDeletedAt() != null ) {
            throw new SecurityException(String.format("Account email=%s was deleted", username));
        }
        else {
            return new AuthenticatedUserDetails(account);
        }
    }

}
