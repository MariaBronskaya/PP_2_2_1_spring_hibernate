package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {

      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public User getUser(String model, int series) {
      User user = sessionFactory.getCurrentSession().createQuery(
                      "FROM User user WHERE user.car.series = :series AND user.car.model = :model", User.class)
              .setParameter("series", series) // установка полей
              .setParameter("model", model)
              .getSingleResult();
      return user;
   }
}
