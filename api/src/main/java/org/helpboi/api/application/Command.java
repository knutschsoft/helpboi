package org.helpboi.api.application;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.reactivex.Maybe;

public abstract class Command<T> {

    private Maybe<T> result = Maybe.error(
            new IllegalStateException(
                    String.format("Command: '%s' was not rejected or resolved", this)));

    @JsonIgnore
    public Maybe<T> getResult() {
        return result;
    }

    @JsonIgnore
    public T getResultBlocking() {
        return result.blockingGet();
    }

    public Maybe<T> result(Maybe<T> result) {
        return this.result = result;
    }

    public Maybe<T> resolve(T value) {
        if (value == null) {
            // 'just(...)' doesn't accept null values
            return result(
                    Maybe.fromCallable(() -> null));
        }
        return result(Maybe.just(value));
    }

    public Maybe<T> reject(Throwable reason) {
        return result(Maybe.error(reason));
    }

    public Maybe<T> notFound() {
        return result(Maybe.empty());
    }
}
