package io.loom.core.event;

import io.loom.core.entity.VersionedEntity;

import java.time.ZonedDateTime;
import java.util.UUID;

public abstract class AbstractDomainEvent implements DomainEvent {
    private UUID aggregateId;
    private long version;
    private ZonedDateTime occurrenceTime;

    protected AbstractDomainEvent() {
    }

    protected AbstractDomainEvent(UUID aggregateId, long version, ZonedDateTime occurrenceTime) {
        guardHeaderProperties(aggregateId, version, occurrenceTime);
        this.aggregateId = aggregateId;
        this.version = version;
        this.occurrenceTime = occurrenceTime;
    }

    @Override
    public void raise(VersionedEntity versionedEntity) {
        UUID aggregateId = versionedEntity.getId();
        long version = versionedEntity.getVersion();
        ZonedDateTime occurrenceTime = ZonedDateTime.now();
        guardHeaderProperties(aggregateId, version, occurrenceTime);
        this.aggregateId = aggregateId;
        this.version = version;
        this.occurrenceTime = occurrenceTime;
    }

    @Override
    public final UUID getAggregateId() {
        return this.aggregateId;
    }

    @Override
    public final long getVersion() {
        return this.version;
    }

    @Override
    public final ZonedDateTime getOccurrenceTime() {
        return this.occurrenceTime;
    }

    private static void guardHeaderProperties(
            UUID aggregateId, long version, ZonedDateTime occurrenceTime) {
        if (aggregateId == null) {
            throw new IllegalArgumentException("The parameter 'aggregateId' cannot be null.");
        }
        if (version < 1) {
            throw new IllegalArgumentException("The parameter 'version' must be greater than 0.");
        }
        if (occurrenceTime == null) {
            throw new IllegalArgumentException("The parameter 'occurrenceTime' cannot be null.");
        }
    }
}
