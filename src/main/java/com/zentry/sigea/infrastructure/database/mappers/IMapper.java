package com.zentry.sigea.infrastructure.database.mappers;

public interface IMapper<EntityJPA , EntityDomain> {
    EntityDomain toDomain(EntityJPA entity_jpa);
    EntityJPA toJPA(EntityDomain entity_domain);
}
