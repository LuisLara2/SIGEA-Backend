package com.zentry.sigea.module_certificacion.services.usecases.validacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_certificacion.core.entities.TipoValidadorDomainEntity;
import com.zentry.sigea.module_certificacion.core.entities.ValidacionDomainEntity;
import com.zentry.sigea.module_certificacion.core.repositories.ITipoValidadorRepository;
import com.zentry.sigea.module_certificacion.core.repositories.IValidacionRepository;
import com.zentry.sigea.module_certificacion.services.serviceDTO.CrearValidacionRequestServiceDTO;

@Component
public class CrearValidacionUseCase {
    
    private final IValidacionRepository validacionRepository;
    private final ITipoValidadorRepository tipoValidadorRepository;

    public CrearValidacionUseCase(
        IValidacionRepository validacionRepository , 
        ITipoValidadorRepository tipoValidadorRepository
    ){
        this.validacionRepository = validacionRepository;
        this.tipoValidadorRepository = tipoValidadorRepository;
    }

    public String execute(List<CrearValidacionRequestServiceDTO> listCrearValidacionRequestServiceDTOs){

        List<ValidacionDomainEntity> listValidacionDomainEntities = new ArrayList<>();

        for(CrearValidacionRequestServiceDTO crearValidacionRequestServiceDTO : listCrearValidacionRequestServiceDTOs){

            TipoValidadorDomainEntity tipoValidadorDomainEntity = tipoValidadorRepository.findById(
                crearValidacionRequestServiceDTO.getTipoValidadorId()
            ).orElse(null);

            if(tipoValidadorDomainEntity == null){
                continue;
            }

            ValidacionDomainEntity validacionDomainEntity = ValidacionDomainEntity.create(
                crearValidacionRequestServiceDTO.getCertificadoId(), 
                tipoValidadorDomainEntity, 
                crearValidacionRequestServiceDTO.getResultado(), 
                crearValidacionRequestServiceDTO.getDetalle()
            );

            listValidacionDomainEntities.add(validacionDomainEntity);
        }

        validacionRepository.saveAll(listValidacionDomainEntities);

        return "Validaciones registradas con exito";
    }

}
