package fusco;

import io.vavr.control.Try;

import java.util.function.Function;

public class TryReader<R, A> {
    private final Function<R, Try<A>> run;
    public TryReader(Function<R, Try<A>> run) { this.run = run; }

    public <B> TryReader<R, B> map(Function<A, B> f) {
        return new TryReader<>(r -> apply(r).map(a -> f.apply(a)));
    }

    public <B> TryReader<R, B> flatMap(Function<A, TryReader<R, B>> f) {
        return new TryReader<>(r -> apply(r).flatMap(a -> f.apply(a).apply(r)));
    }

    public <B> TryReader<R, B> mapReader(Function<A, Reader<R, B>> f) {
        return new TryReader<>(r -> apply(r).map( a -> f.apply(a).apply(r) ) );
    }

    public Try<A> apply(R r) {
        return run.apply(r);
    }
}
