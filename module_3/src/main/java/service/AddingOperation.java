package service;

import entity.Account;
import entity.Category;
import entity.Operation;
import entity.User;
import exception.IllegalInputException;
import exception.IndefiniteEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.Instant;
import java.util.List;
import java.util.function.Supplier;

import static console.Console.*;

public class AddingOperation {

    private final Supplier<EntityManager> persistence;
    private static final Logger LOG = LoggerFactory.getLogger(AddingOperation.class);

    public AddingOperation(Supplier<EntityManager> persistence) {
        this.persistence = persistence;
    }

    public void add(String phone) {
        EntityManager entityManager = persistence.get();
        entityManager.getTransaction().begin();

        try {
            LOG.info("Create transaction");

            checkInputPhone(phone);

            TypedQuery<User> queryUser = entityManager.createQuery("SELECT u FROM User u WHERE u.phoneNumber LIKE :phoneNumber", User.class)
                    .setParameter("phoneNumber", phone);

            User user = queryUser.getSingleResult();

            if (user == null) {
                LOG.error("We don't have User with phoneNumber {}", phone);
                throw new IndefiniteEntityException("User doesn't exist");
            }

            List<Account> accounts = user.getAccounts();
            Account account = getAccount(accounts);

            printMessage("Please, enter your amount and category operation");
            String input = getString();
            if (input.isBlank()) {
                LOG.error("User doesn't enter anything");
                throw new IllegalInputException("Blank input");
            }
            String[] values = input.split(" ");
            if (values.length != 2) {
                LOG.error("User enter incorrect data");
                throw new IllegalInputException("Incorrect input");
            }
            int amount = Integer.parseInt(values[0].trim());
            String category = values[1].trim();

            if (amount == 0) {
                LOG.error("Amount can't be zero");
                throw new IllegalInputException("Incorrect input");
            }

            TypedQuery<Category> queryCategory = entityManager.createQuery("SELECT c FROM Category c WHERE c.name LIKE :category", Category.class)
                    .setParameter("category", category);

            Category currentCategory = queryCategory.getSingleResult();

            if (currentCategory == null) {
                LOG.error("We don't have Category with name {}", category);
                throw new IndefiniteEntityException("Category doesn't exist");
            }
            if ((currentCategory.isIncome() && amount < 0) || (!currentCategory.isIncome() && amount > 0)) {
                LOG.error("Category and type of amount don't match");
                throw new IllegalInputException("Incorrect data input");
            }
            account.setBalance(account.getBalance() + amount);
            Operation operation = new Operation();
            operation.setAccount(account);
            operation.setDate(Instant.now());
            operation.setAmount(amount);
            operation.setCategory(currentCategory);
            entityManager.persist(account);
            entityManager.persist(operation);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Transaction rollback");
            entityManager.getTransaction().rollback();
        }
    }

    private void checkInputPhone(String phone) {
        if (!phone.matches("\\d{10,13}")) {
            LOG.error("You input incorrect phone number - {}", phone);
            throw new IllegalArgumentException("Input incorrect phone number");
        }
    }

    private Account getAccount(List<Account> accounts) throws IndefiniteEntityException {
        if (!accounts.isEmpty()) {
            printMessage("Choose what account you want use");
            for (int i = 0; i < accounts.size(); i++) {
                printMessage("Account number " + (i + 1) + " - " + accounts.get(i).getName());
            }
            int input = getInt();
            if (input > accounts.size() || input < 1) {
                LOG.error("We don't have " + input + " account");
                throw new IllegalArgumentException("This account does not exist");
            } else {
                Account account = accounts.get(input - 1);
                LOG.info("Users choice is {}", account.getName());
                return account;
            }
        } else {
            LOG.error("We don't have any account");
            throw new IndefiniteEntityException("Any account doesn't exist");
        }
    }
}
