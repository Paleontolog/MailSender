//package com.mailsender.demo.database;
//
//import com.mailsender.demo.database.dto.AddresseesDB;
//import com.mailsender.demo.exceptions.DatabaseException;
//import com.mailsender.demo.exceptions.DatabaseExceptionCode;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Slf4j
//@Repository
//public class DatabaseAccessorMongo implements DatabaseAccessor {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Override
//    public List<AddresseesDB> getAllAddresses() {
//        return mongoTemplate.findAll(AddresseesDB.class, "Addressees");
//    }
//
//    @Override
//    public int addAddressees(AddresseesDB addresseesDB) {
//        try {
//            mongoTemplate.save(addresseesDB, "Addressees");
//        } catch (Exception e) {
//            log.error(e.toString());
//            return 1;
//        }
//        return 0;
//    }
//
//    @Override
//    public int updateAddresses(AddresseesDB addresseesDB) {
//        Query query = new Query(Criteria.where("id").is(addresseesDB.getId()));
//        Update update = new Update();
//        update.set("email", addresseesDB.getEmail());
//        try {
//            mongoTemplate.updateFirst(query, update, AddresseesDB.class);
//        } catch (Exception e) {
//            log.error("User not found");
//            throw new DatabaseException(DatabaseExceptionCode.USER_NOT_FOUND);
//        }
//        return 0;
//    }
//}
