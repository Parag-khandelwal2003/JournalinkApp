package net.apmoller.journalink.repository;


import net.apmoller.journalink.entity.JournalEntry;
import net.apmoller.journalink.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    void deleteByUserName(String userName);
}