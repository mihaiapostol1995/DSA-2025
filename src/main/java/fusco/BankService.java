package fusco;

import io.vavr.control.Try;

import java.math.BigDecimal;
import java.util.Objects;

interface BankConnection {

}

class Balance {
    public final BigDecimal amount;
    public Balance(BigDecimal amount) {
        this.amount = amount;
    }
}

class InsufficientBalanceError extends Exception {}

class Account {
    public final String owner;
    public final String number;
    public final Balance balance;

    public Account(String owner, String number, Balance balance) {
        this.owner = owner;
        this.number = number;
        this.balance = balance;
    }

    public Account credit(BigDecimal value) {
        return new Account(owner, number, new Balance(value));
    }

//    public Try<Account> debit(BigDecimal value) {
//        if (balance.amount.compareTo(value) < 0) {
//            return new Try.Failure<>(new InsufficientBalanceError());
//        }
//        return new Try.Success<>(new Account(
//                owner, number, new Balance(balance.amount.subtract(value))));
//    }
}

public interface BankService {
    static TryReader<BankConnection, Account> open(String owner, String number, BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            return new TryReader<>(bc -> Try.failure(new InsufficientBalanceError()));
        }
        return new TryReader<>(bc -> Try.success(new Account(owner, number, new Balance(balance))));
    }

    static Reader<BankConnection, Account> credit(Account account, BigDecimal value) {
        return new Reader<>(bc -> new Account(account.owner, account.number,
                new Balance(account.balance.amount.add(value))));
    }

    static TryReader<BankConnection, Account> debit(Account account, BigDecimal value) {
        if (account.balance.amount.compareTo(value) < 0) {
            return new TryReader<>(bc -> Try.failure(new InsufficientBalanceError()));
        }
        return new TryReader<>(bc -> Try.success(new Account(account.owner, account.number,
                new Balance(account.balance.amount.subtract(value)))));
    }

    static void main(String[] args) {
        TryReader<BankConnection, Account> reader = open("Alice", "123", new BigDecimal(100.0))
                .mapReader(a -> credit(a, new BigDecimal(200.0)));
//                .mapReader(a -> credit(a, new BigDecimal(300.0)))
//                .flatMap(a -> debit(a, new BigDecimal(400)));

        Try<Account> account = reader.apply(new BankConnection(){});

        assert Objects.equals(account.get().owner, "Alice");
        assert Objects.equals(account.get().number, "123");
        assert account.get().balance.amount.equals(new BigDecimal(200));
    }
}