package com.kdu.jpa.service;

import com.kdu.jpa.entity.User;
import com.kdu.jpa.exception.EntityNotExist;
import com.kdu.jpa.exception.InvalidEntityException;
import com.kdu.jpa.exception.ItemsNotFound;
import com.kdu.jpa.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class for managing User entities.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructs a new UserService with the provided UserRepository.
     *
     * @param userRepository The UserRepository to be used by the service.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Adds a new User to the repository.
     *
     * @param user The User object to be added.
     * @throws InvalidEntityException If the user is invalid or cannot be added.
     */
    public void addUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception ex) {
            throw new InvalidEntityException("Invalid User : Unable to add Cause " + ex.getMessage(), "user");
        }
    }

    /**
     * Retrieves all Users for a specific tenant.
     *
     * @param tenantId The UUID of the tenant.
     * @return List of Users for the provided tenant.
     * @throws ItemsNotFound If Users for the provided tenant ID cannot be found.
     */
    public List<User> getAllUsersForTenant(UUID tenantId) {
        List<User> users;
        try {
            users = userRepository.findAllByTenantId(tenantId);
        } catch (Exception ex) {
            throw new ItemsNotFound("Not able to find Users for provided tenant id with Cause : " + ex.getMessage());
        }
        return users;
    }

    /**
     * Updates an existing User by ID.
     *
     * @param userId The UUID of the User to be updated.
     * @param user   The updated User object.
     * @throws EntityNotExist If the User for the provided user ID cannot be found.
     * @throws ItemsNotFound  If there is an issue updating the User.
     */
    public void updateUser(UUID userId, User user) {
        try {
            int row = 0;
            row = userRepository.updateUser(userId, user.getUsername(), user.getLoggedIn(), user.getTimeZone());
            if (row == 0) {
                throw new EntityNotExist("Not able to find user for provided user id", "user");
            }
        } catch (Exception ex) {
            throw new ItemsNotFound("Not able to find user for provided user id with Cause : " + ex.getMessage());
        }
    }

    /**
     * Retrieves paginated Users.
     *
     * @param pageable The Pageable object representing the pagination information.
     * @return Page of Users with pagination information.
     */
    public Page<User> getAllPaginatedUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
