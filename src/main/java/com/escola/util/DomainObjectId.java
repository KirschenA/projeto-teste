package com.escola.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Objects;

public abstract class DomainObjectId<T> implements Serializable, ValueObject {

	private static final long serialVersionUID = 7556349653319120449L;

	@JsonValue
	private final T id;

	@JsonCreator
	protected DomainObjectId(@NonNull T id) {
		this.id = id;
	}

	/**
	 * Return identification.
	 * 
	 * @return id
	 */
	@NonNull
	public T getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null || !getClass().equals(obj.getClass()))
			return false;

		return Objects.equals(id, ((DomainObjectId<?>) obj).id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return Objects.toString(id);
	}
}