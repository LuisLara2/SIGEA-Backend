package com.zentry.sigea.infrastructure.database.adapters;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.core.entities.ActividadDomainEntity;
import com.zentry.sigea.core.repositories.IActividadRespository;
import com.zentry.sigea.infrastructure.database.entities.EstadoActividadEntity;
import com.zentry.sigea.infrastructure.database.entities.TipoActividadEntity;
import com.zentry.sigea.infrastructure.database.entities.UsuarioEntity;
import com.zentry.sigea.infrastructure.database.mappers.ActividadMapper;
import com.zentry.sigea.infrastructure.repositories.ActividadJPARepository;
import com.zentry.sigea.infrastructure.repositories.EstadoActividadJPARepository;
import com.zentry.sigea.infrastructure.repositories.TipoActividadJPARepository;
import com.zentry.sigea.infrastructure.repositories.UsuarioJPARepository;

@Repository
public class ActividadRepositoryAdapter implements IActividadRespository {
    
    private final ActividadJPARepository actividadJPARepository;
    
    private final EstadoActividadJPARepository estadoActividadJPARepository;
    private final UsuarioJPARepository usuarioJPARepository;
    private final TipoActividadJPARepository tipoActividadJPARepository;


    public ActividadRepositoryAdapter(
        ActividadJPARepository actividadJPARepository , 
        EstadoActividadJPARepository estadoActividadJPARepository , 
        UsuarioJPARepository usuarioJPARepository ,
        TipoActividadJPARepository tipoActividadJPARepository
    ){
        this.actividadJPARepository = actividadJPARepository;

        this.estadoActividadJPARepository = estadoActividadJPARepository;
        this.usuarioJPARepository = usuarioJPARepository;
        this.tipoActividadJPARepository = tipoActividadJPARepository;
    }

    public void save(ActividadDomainEntity actividadDomainEntity){

        EstadoActividadEntity estadoActividadEntity = estadoActividadJPARepository.findById(
            UUID.fromString(actividadDomainEntity.getEstadoActividadId())
        ).orElse(null);

        UsuarioEntity usuarioEntity = usuarioJPARepository.findById(
            UUID.fromString(actividadDomainEntity.getOrganizadorId())
        ).orElse(null);
    
        TipoActividadEntity tipoActividadEntity = tipoActividadJPARepository.findById(
            UUID.fromString(actividadDomainEntity.getTipoActividadId())
        ).orElse(null);

        actividadJPARepository.save(
            ActividadMapper.toEntity(
                actividadDomainEntity, 
                estadoActividadEntity, 
                usuarioEntity, 
                tipoActividadEntity
            )
        );
    }

    public Optional<ActividadDomainEntity> findById(String id){
        return actividadJPARepository.findById(
            UUID.fromString(id)
        ).map(a -> ActividadMapper.toDomain(a));
    }

    public List<ActividadDomainEntity> findByOrganizadorId(String organizadorId){
        return actividadJPARepository.findByOrganizadorId(
            UUID.fromString(organizadorId)
        ).stream()
        .map(ActividadMapper::toDomain)
        .collect(Collectors.toList());
    }
}
